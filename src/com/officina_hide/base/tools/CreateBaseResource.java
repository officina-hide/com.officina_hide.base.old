package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;

/**
 * 基本情報生成<br>
 * <p>パッケージの構築で基本となる情報の内、手作業で作成する必要があるリソースを生成する。</p>
 * <p>Generate the resources that need to be created manually, out of the basic information for building a package.</p>
 * @author ueno hideo
 * @version 1.21
 * @since 2020/08/13
 */
public class CreateBaseResource extends FD_DB {
	/*
	 * 次の処理を行う。
	 * テーブル情報テーブルの構築 (createTableInfoTable)
	 * リファレンス項目情報テーブルの構築 (createReferenceTable)
	 * テーブル項目用リファレンスの登録 (addReferenceData)
	 * テーブル項目情報テーブルの構築 (createTabelItemTable)
	 */

	/**
	 * コンストラクター<br>
	 * @author ueno hideo
	 * @since 1.21 2020/08/13
	 * @param env 環境情報
	 */
	public CreateBaseResource(FD_EnvData env) {
		/** テーブル情報テーブルの構築 */
		createTableInfoTable(env);
		/** リファレンス項目情報テーブルの構築 */
		createReferenceTable(env);
	}

	/**
	 * テーフル情報テーブル生成
	 * @author ueno hideo
	 * @since 1.21 2020/08/13
	 * @param env 環境情報
	 */
	private void createTableInfoTable(FD_EnvData env) {
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
	}

	/**
	 * リファレンス情報テーブル生成
	 * @author ueno hideo
	 * @since 1.21 2020/08/13
	 * @param env 環境情報
	 */
	private void createReferenceTable(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		//既に登録されているリファレンス情報を削除します。
		sql.append("DROP TABLE IF EXISTS FD_Reference");
		execute(env, sql.toString());
		//リファレンス情報を生成する。
		sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS FD_Reference  (");
		sql.append("FD_Reference_ID INT UNSIGNED NOT NULL PRIMARY KEY COMMENT 'リファレンス情報ID'").append(",");
		sql.append("Reference_Name Varchar(100) COMMENT 'リファレンス名'").append(",");
		sql.append("Reference_GroupName Varchar(100) COMMENT 'リファレンスグループ名'").append(",");
		sql.append("Reference_Class varchar(200) COMMENT 'リファレンスクラスURI'").append(",");
		sql.append("FD_Create DATETIME  COMMENT '登録日'").append(",");
		sql.append("FD_Created INT UNSIGNED  COMMENT '登録者ID'").append(",");
		sql.append("FD_Update DATETIME  COMMENT '更新日'").append(",");
		sql.append("FD_Updated INT UNSIGNED  COMMENT '更新者ID'");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='リファレンス情報'");			
		execute(env, sql.toString());
	}
}
