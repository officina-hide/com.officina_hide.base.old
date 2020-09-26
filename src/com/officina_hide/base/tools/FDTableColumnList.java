package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_TableColumn;
import com.officina_hide.base.model.I_FD_TableColumnList;
import com.officina_hide.base.model.X_FD_TableColumnList;

/**
 * テーブル項目リスト情報クラス<br>
 * @author officine-hide.com
 * @version 2.11
 * @since 2020/09/26
 */
public class FDTableColumnList extends FD_DB implements I_FD_TableColumnList {

	/**
	 * テーブル項目リストテーブル生成<br>
	 * @author officine-hide.com
	 * @since 2.11 2020/09/26
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FDTable table = new FDTable();
		int tableId = table.addData(env, 0, Table_Name, Name, "");

		//テーブル項目情報登録
		FDTableColumn column = new FDTableColumn();
		column.add(env, tableId, COLUMNNAME_FD_TABLECOLUMNLIST_ID, NAME_FD_TABLECOLUMNLIST_ID, COMMENT_FD_TABLECOLUMNLIST_ID
				, COLUMN_TYPE_INFORMATION_ID, 0, 10, I_FD_TableColumn.IS_PRIMARY_YES);
		column.add(env, tableId, COLUMNNAME_FD_TABLECOLUMN_ID, NAME_FD_TABLECOLUMN_ID, COMMENT_FD_TABLECOLUMN_ID
				, COLUMN_TYPE_INFORMATION_ID, 0, 20, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_LIST_NAME, NAME_LIST_NAME, COMMENT_LIST_NAME
				, COLUMN_TYPE_TEXT, 100, 30, I_FD_TableColumn.IS_PRIMARY_NO);
		
		column.add(env, tableId, COLUMNNAME_FD_CREATE, NAME_FD_CREATE, COMMENT_FD_CREATE
				, COLUMN_TYPE_DATE, 0, 910, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_FD_CREATED, NAME_FD_CREATED, COMMENT_FD_CREATED
				, COLUMN_TYPE_INFORMATION_ID, 0, 920, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_FD_UPDATE, NAME_FD_UPDATE, COMMENT_FD_UPDATE
				, COLUMN_TYPE_DATE, 0, 930, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_FD_UPDATED, NAME_FD_UPDATED, COMMENT_FD_UPDATED
				, COLUMN_TYPE_INFORMATION_ID, 0, 940, I_FD_TableColumn.IS_PRIMARY_NO);
		
		//テーブル生成
		createDBTable(env, Table_Name);
		
		//採番情報登録
		FDNumbering num  = new FDNumbering();
		num.add(env, tableId, 1000001, 0);
		
		env.getLog().add(env, FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "テーブル項目リスト情報テーブル生成完了");
}

	/**
	 * テーブル項目リスト情報登録<br>
	 * @author officine-hide.com
	 * @since 2.11 2020/09/26
	 * @param env 環境情報
	 * @param columnId テーブル項目情報ID
	 * @param listName リスト名
	 */
	public void add(FD_EnvData env, int columnId, String listName) {
		X_FD_TableColumnList columnList = new X_FD_TableColumnList(env);
		columnList.setValue(env, COLUMNNAME_FD_TABLECOLUMN_ID, columnId);
		columnList.setValue(env, COLUMNNAME_LIST_NAME, listName);
		columnList.save(env);
	}

}
