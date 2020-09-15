package com.officina_hide.accounts.tools;

import com.officina_hide.accounts.model.I_AC_Acount_Code;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_TableColumn;
import com.officina_hide.base.tools.FDNumbering;
import com.officina_hide.base.tools.FDTable;
import com.officina_hide.base.tools.FDTableColumn;

/**
 * 勘定科目情報クラス<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/09/14
 */
public class ACAcountCode extends FD_DB implements I_AC_Acount_Code {

	/**
	 * 勘定科目情報テーブル生成<br>
	 * @author officine-hide.com
	 * @since 2020/09/14
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FDTable table = new FDTable();
		int tableId = table.addData(env, 0, Table_Name, Name);
		
		//テーブル項目情報登録
		FDTableColumn column = new FDTableColumn();
		column.add(env, tableId, COLUMNNAME_AC_ACOUNT_CODE_ID, "勘定科目情報ID", "勘定科目情報を識別するための情報ID"
				, COLUMN_TYPE_INFORMATION_ID, 0, 10, I_FD_TableColumn.IS_PRIMARY_YES);
		column.add(env, tableId, COLUMNNAME_ACOUNT_CODE, "勘定科目コード", "勘定科目を表すコード"
				, COLUMN_TYPE_TEXT, 4, 20, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_ACOUNT_CODE_NAME, "勘定科目名"
				, "勘定科目の表示名", COLUMN_TYPE_TEXT, 100, 30, I_FD_TableColumn.IS_PRIMARY_NO);
		
		column.add(env, tableId, COLUMNNAME_FD_CREATE, "登録日", "情報の登録日"
				, COLUMN_TYPE_DATE, 0, 900, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_FD_CREATED, "登録者ID", "情報の登録者ID（ユーザー情報ID）"
				, COLUMN_TYPE_INFORMATION_ID, 0, 910, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_FD_UPDATE, "更新日", "情報の更新日"
				, COLUMN_TYPE_DATE, 0, 920, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_FD_UPDATED, "更新者ID", "情報の更新者ID（ユーザー情報ID）"
				, COLUMN_TYPE_INFORMATION_ID, 0, 930, I_FD_TableColumn.IS_PRIMARY_NO);
		
		//テーブル生成
		createDBTable(env, Table_Name);
		
		//採番情報登録
		FDNumbering num = new FDNumbering();
		num.add(env, tableId, 1000001, 0);
	}

}