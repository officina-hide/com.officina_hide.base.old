package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Numbering;
import com.officina_hide.base.model.I_FD_TableColumn;
import com.officina_hide.base.model.X_FD_Numbering;

/**
 * 採番情報クラス<br>
 * @author officina-hide.com ueno
 * @version 2.00
 * @since 2020/09/05
 */
public class FDNumbering extends FD_DB implements I_FD_Numbering {

	/**
	 * 採番情報テーブル生成<br>
	 * @author officina-hide.com ueno
	 * @since 2.00 2020/09/05
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		//既に登録されている採番情報を削除します。
		sql.append("DROP TABLE IF EXISTS FD_Numbering");
		execute(env, sql.toString());
		//採番情報テーブルを生成する。
		sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS FD_Numbering  (");
		sql.append("FD_Numbering_ID INT UNSIGNED NOT NULL PRIMARY KEY COMMENT '採番情報ID'").append(",");
		sql.append("FD_Table_ID INT UNSIGNED NOT NULL COMMENT 'テーブル情報ID'").append(",");
		sql.append("Start_Number INT UNSIGNED COMMENT '開始値'").append(",");
		sql.append("Current_Number INT UNSIGNED COMMENT '現在値'").append(",");
		sql.append("FD_Create DATETIME  COMMENT '登録日'").append(",");
		sql.append("FD_Created INT UNSIGNED  COMMENT '登録者ID'").append(",");
		sql.append("FD_Update DATETIME  COMMENT '更新日'").append(",");
		sql.append("FD_Updated INT UNSIGNED  COMMENT '更新者ID'");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='採番情報'");			
		execute(env, sql.toString());
		
		//テーブル情報登録
		FDTable table = new FDTable();
		table.addData(env, I_FD_Numbering.Table_ID, I_FD_Numbering.Table_Name, "採番情報");
		
		//採番情報登録
		add(env, I_FD_Numbering.Table_ID, 1000001, 0);

		env.getLog().add(env, FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "採番情報テーブル構築完了");
	}

	/**
	 * 採番情報のテーブル項目情報登録<br>
	 * @author officina-hide.com ueno
	 * @since 2.00 2020/09/07
	 * @param env 環境情報
	 */
	public void addColumnData(FD_EnvData env) {
		FDTableColumn column = new FDTableColumn();
		column.add(env, Table_ID, COLUMNNAME_FD_NUMBERING_ID, "採番情報ID", "採番情報を識別するための情報ID"
				, COLUMN_TYPE_INFORMATION_ID, 0, 10, I_FD_TableColumn.IS_PRIMARY_YES);
		column.add(env, Table_ID, COLUMNNAME_FD_TABLE_ID, "テーブル情報ID", "採番の対象となるテーブルの情報ID"
				, COLUMN_TYPE_INFORMATION_ID, 0, 20, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, Table_ID, COLUMNNAME_START_NUMBER, "開始値", "採番を開始する値"
				, COLUMN_TYPE_NUMBER, 0, 30, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, Table_ID, COLUMNNAME_CURRENT_NUMBER, "現在値", "現在裁判されている最大の値"
				, COLUMN_TYPE_NUMBER, 0, 40, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, Table_ID, COLUMNNAME_FD_CREATE, "登録日", "情報の登録日"
				, COLUMN_TYPE_DATE, 0, 900, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, Table_ID, COLUMNNAME_FD_CREATED, "登録者ID", "情報の登録者ID（ユーザー情報ID）"
				, COLUMN_TYPE_INFORMATION_ID, 0, 920, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, Table_ID, COLUMNNAME_FD_UPDATE, "更新日", "情報の更新日"
				, COLUMN_TYPE_DATE, 0, 900, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, Table_ID, COLUMNNAME_FD_UPDATED, "更新者ID", "情報の更新者ID（ユーザー情報ID）"
				, COLUMN_TYPE_INFORMATION_ID, 0, 920, I_FD_TableColumn.IS_PRIMARY_NO);
	}

	/**
	 * 採番情報登録<br>
	 * @author officina-hide.com ueno
	 * @since 2.00 2020/09/05
	 * @param env 環境情報
	 * @param tableId テーブル情報ID
	 * @param start 開始値
	 * @param current 現在値
	 */
	public void add(FD_EnvData env, int tableId, int start, int current) {
		X_FD_Numbering num = new X_FD_Numbering(env);
		num.setValue(env, COLUMNNAME_FD_NUMBERING_ID, tableId);
		num.setValue(env, COLUMNNAME_FD_TABLE_ID, tableId);
		num.setValue(env, COLUMNNAME_START_NUMBER, start);
		num.setValue(env, COLUMNNAME_CURRENT_NUMBER, current);
		num.save(env);
	}

}
