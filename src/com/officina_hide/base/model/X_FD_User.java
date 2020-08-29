package com.officina_hide.base.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.common.FD_WhereData;

/**
 * ユーザー情報I/Oクラス<br>
 * @author ueno hideo
 * @version 2.00
 * @since 2020/08/27
 */
public class X_FD_User extends FD_DB implements I_FD_User {

	/**
	 * コンストラクター<br>
	 * @author ueno hideo
	 * @since 2020/08/27
	 * @param env 環境情報
	 */
	public X_FD_User(FD_EnvData env) {
		
	}

	/**
	 * コンストラクター<br>
	 * <p>指定された条件で情報を取得する。</p>
	 * @author officina-hide.com ueno
	 * @param env 環境情報
	 * @param where 抽出条件
	 */
	public X_FD_User(FD_EnvData env, FD_WhereData where) {
		if(load(env, where) == false) {
			env.getLog().add(FD_Logging.TYPE_ERROR, FD_Logging.MODE_NORMAL, "Load Error FD_User : "+where.toString());
		}
	}

	/** ユーザー情報ID */
	private int fD_User_ID;
	/** ユーザー名 */
	private String user_Name;
	/** パスワード */
	private String password;

	/**
	 * ユーザー情報IDの取得<br>
	 * @return FD_User_ID(ユーザー情報ID)
	 */
	public int getfD_User_ID() {
		return fD_User_ID;
	}

	/**
	 * ユーザー情報IDをセットする。<br>
	 * @param fD_User_ID ユーザー情報ID
	 */
	public void setfD_User_ID(int fD_User_ID) {
		this.fD_User_ID = fD_User_ID;
	}

	/**
	 * ユーザー名の取得
	 * @return User_Name (ユーザー名)
	 */
	public String getUser_Name() {
		return user_Name;
	}

	/**
	 * ユーザー名をセットする。<br>
	 * @param user_Name  ユーザー名
	 */
	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}

	/**
	 * パスワードの取得<br>
	 * @return Password(パスワード)
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * パスワードをセットセットする<br>
	 * @param password パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 保管<br>
	 * TODO 汎用化予定(2020/08/29 ueno)
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO FD_User SET ");
		sql.append("FD_User_ID = ").append(getfD_User_ID()).append(",");
		sql.append("User_Name = ").append(FD_SQ).append(getUser_Name()).append(FD_SQ).append(",");
		sql.append("Password = ").append(FD_SQ).append(getPassword()).append(FD_SQ).append(",");
		sql.append("FD_Create = ").append(FD_SQ).append(dateFormat.format(new Date())).append(FD_SQ).append(",");
		sql.append("FD_Created = ").append(env.getSystemUserID()).append(",");
		sql.append("FD_Update = ").append(FD_SQ).append(dateFormat.format(new Date())).append(FD_SQ).append(",");
		sql.append("FD_Updated = ").append(env.getSystemUserID());
		execute(env, sql.toString());
	}

	/**
	 * 抽出<br>
	 * @param env 環境情報
	 * @param where 条件
	 */
	private boolean load(FD_EnvData env, FD_WhereData where) {
		boolean chk = true;
		StringBuffer sql = new StringBuffer();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			sql.append("SELECT * FROM FD_User ");
			sql.append("WHERE ").append(where.toString());
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				this.setfD_User_ID(rs.getInt(COLUMNNAME_FD_USER_ID));
				this.setUser_Name(rs.getNString(COLUMNNAME_USER_NAME));
				this.setPassword(rs.getString(COLUMNNAME_PASSWORD));
			} else {
				chk = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chk;
	}
	
}
