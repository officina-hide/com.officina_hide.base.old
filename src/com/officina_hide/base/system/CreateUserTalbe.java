package com.officina_hide.base.system;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.X_FD_User;

/**
 * ユーザー情報テーブル生成<br>
 * @author ueno hideo
 * @version 2.00
 * @since 2020/08/27
 */
public class CreateUserTalbe extends FD_DB {
	
	/**
	 * コンストラクター<br>
	 */
	public CreateUserTalbe() {
	}

	/**
	 * ユーザー情報テーブル生成<br>
	 * @param env 環境情報
	 */
	public void createUserTable(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		//既に登録されているテーフル情報を削除する。
		sql.append("DROP TABLE IF EXISTS FD_Table");
		execute(env, sql.toString());
		//テーフル情報を生成する。
		sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS FD_User  (");
		sql.append("FD_User_ID INT UNSIGNED NOT NULL PRIMARY KEY COMMENT 'ユーザー情報ID'").append(",");
		sql.append("User_Name Varchar(100) COMMENT ").append(FD_SQ).append("ユーザー名").append(FD_SQ).append(",");
		sql.append("Password Varchar(100) COMMENT ").append(FD_SQ).append("パスワード").append(FD_SQ).append(",");
		sql.append("FD_Create DATETIME  COMMENT '登録日'").append(",");
		sql.append("FD_Created INT UNSIGNED  COMMENT '登録者ID'").append(",");
		sql.append("FD_Update DATETIME  COMMENT '更新日'").append(",");
		sql.append("FD_Updated INT UNSIGNED  COMMENT '更新者ID'");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ユーザー情報'");
		execute(env, sql.toString());
	}

	/**
	 * ユーザー情報登録<br>
	 * @author ueno hideo
	 * @since 2.00 2020/08/227
	 * @param env 環境情報
	 * @param id  ユーザーID
	 * @param userNname ユーザー名
	 * @param passward パスワード
	 */
	public void addData(FD_EnvData env, int id, String userNname, String passward) {
		X_FD_User user = new X_FD_User(env);
		user.setfD_User_ID(id);
	}

}
