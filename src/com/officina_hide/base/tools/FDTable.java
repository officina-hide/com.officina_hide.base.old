package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Table;
import com.officina_hide.base.model.X_FD_Table;

/**
 * テーブル情報クラス<br>
 * @author ueno hideo
 * @version 2.00
 * @since 2020/08/29
 */
public class FDTable extends FD_DB {

	/**
	 * テーブル情報生成<br>
	 * @author ueno hideo
	 * @since 2.00 2020/08/29
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		//既に登録されているテーフル情報を削除する。
		sql.append("DROP TABLE IF EXISTS FD_Table");
		execute(env, sql.toString());
		//テーフル情報を生成する。
		sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS FD_Table  (");
		sql.append("FD_Table_ID INT UNSIGNED NOT NULL PRIMARY KEY COMMENT 'テーブル情報ID'").append(",");
		sql.append("Table_Name Varchar(100)  COMMENT 'テーブル物理名'").append(",");
		sql.append("FD_Name Varchar(100)  COMMENT 'テーブル論理名'").append(",");
		sql.append("fD_COMMENT Text  COMMENT '説明'").append(",");
		sql.append("FD_Create DATETIME  COMMENT '登録日'").append(",");
		sql.append("FD_Created INT UNSIGNED  COMMENT '登録者ID'").append(",");
		sql.append("FD_Update DATETIME  COMMENT '更新日'").append(",");
		sql.append("FD_Updated INT UNSIGNED  COMMENT '更新者ID'");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='テーブル情報'");
		execute(env, sql.toString());
		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "テーブル情報構築");
	}

	/**
	 * テーブル情報登録<br>
	 * @author ueno hideo
	 * @since 2.00 2020/08/29
	 * @param env 環境情報
	 * @param tableId テーブル情報ID
	 * @param tableName テーブル物理名
	 * @param name テーブル論理名
	 */
	public void addData(FD_EnvData env, int tableId, String tableName, String name) {
		X_FD_Table table = new X_FD_Table(env);
		table.setValue(I_FD_Table.COLUMNNAME_FD_TABLE_ID, tableId);
		table.setValue(I_FD_Table.COLUMNNAME_TABLE_NAME, tableName);
		table.setValue(I_FD_Table.COLUMNNAME_FD_NAME, name);
		table.save(env);
	}
	
 }
