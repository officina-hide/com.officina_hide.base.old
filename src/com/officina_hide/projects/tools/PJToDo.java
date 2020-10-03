package com.officina_hide.projects.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_TableColumn;
import com.officina_hide.base.tools.FDNumbering;
import com.officina_hide.base.tools.FDTable;
import com.officina_hide.base.tools.FDTableColumn;
import com.officina_hide.projects.model.I_PJ_ToDo;

/**
 * ToDo情報クラス
 * @author officine-hide.com
 * @version 2.12
 * @since 2020/10
 */
public class PJToDo extends FD_DB implements I_PJ_ToDo {

	/**
	 * ToDo情報テーブル生成
	 * @param env
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FDTable table = new FDTable();
		int tableId = table.addData(env, 0, Table_Name, Name, "");
		
		//テーブル項目情報登録
		FDTableColumn column = new FDTableColumn();
		column.add(env, tableId, COMMENT_PJ_TODO_ID, NAME_PJ_TODO_ID, COMMENT_PJ_TODO_ID
				, COLUMN_TYPE_INFORMATION_ID, 0, 10, I_FD_TableColumn.IS_PRIMARY_YES);
		column.add(env, tableId, COMMENT_FD_CLIENT_ID, NAME_FD_CLIENT_ID, COMMENT_FD_CLIENT_ID
				, COLUMN_TYPE_INFORMATION_ID, 0, 20, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_TODO_TITLE, NAME_TODO_TITLE, COMMENT_TODO_TITLE
				, COLUMN_TYPE_TEXT, 100, 30, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_TODO_MEMO, NAME_TODO_MEMO, COMMENT_TODO_MEMO
				, COLUMN_TYPE_FIELD_TEXT, 0, 40, I_FD_TableColumn.IS_PRIMARY_NO);
		
		column.add(env, tableId, COLUMNNAME_FD_CREATE, "登録日", "情報の登録日"
				, COLUMN_TYPE_DATE, 0, 900, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_FD_CREATED, "登録者ID", "情報の登録者ID（ユーザー情報ID）"
				, COLUMN_TYPE_INFORMATION_ID, 0, 910, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_FD_UPDATE, "更新日", "情報の更新日"
				, COLUMN_TYPE_DATE, 0, 920, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_FD_UPDATED, "更新者ID", "情報の更新者ID（ユーザー情報ID）"
				, COLUMN_TYPE_INFORMATION_ID, 0, 930, I_FD_TableColumn.IS_PRIMARY_NO);
		
		//採番情報登録
		FDNumbering num = new FDNumbering();
		num.add(env, tableId, 1000001, 0);
		
		//テーブル生成
		createDBTable(env, Table_Name);
		
		env.getLog().add(env, FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, Name+"テーブル構築");
	}

}
