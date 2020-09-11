package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.model.FD_DB;

/**
 * ログ情報クラス<br>
 * @author officine-hide.com ueno
 * @version 2.00
 * @since 2020/09/11
 */
public class FDLog extends FD_DB {

	/**
	 * ログ情報テーブル生成
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		//既に登録されているテーフル情報を削除する。
		sql.append("DROP TABLE IF EXISTS FD_Log");
		execute(env, sql.toString());
		//テーフル情報を生成する。
		sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS FD_Log (");
		sql.append("FD_Log_ID INT UNSIGNED NOT NULL PRIMARY KEY COMMENT 'ログ情報ID'").append(",");
		sql.append("SQL_Text Text COMMENT ").append(FD_SQ).append("使用SQL文").append(FD_SQ).append(",");
		sql.append("FD_Create DATETIME  COMMENT '登録日'").append(",");
		sql.append("FD_Created INT UNSIGNED  COMMENT '登録者ID'").append(",");
		sql.append("FD_Update DATETIME  COMMENT '更新日'").append(",");
		sql.append("FD_Updated INT UNSIGNED  COMMENT '更新者ID'");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ログ情報'");
		execute(env, sql.toString());
		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "テーブル情報構築");
	}

}
