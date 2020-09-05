package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Numbering;
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
		
		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "採番情報テーブル構築完了");
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
		num.setValue(COLUMNNAME_FD_NUMBERING_ID, tableId);
		num.setValue(COLUMNNAME_FD_TABLE_ID, tableId);
		num.setValue(COLUMNNAME_START_NUMBER, start);
		num.setValue(COLUMNNAME_CURRENT_NUMBER, current);
		num.save(env);
	}

}
