package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * ユーザー情報I/Oクラス<br>
 * @author ueno hideo
 * @version 2.00
 * @since 2020/08/27
 */
public class X_FD_User extends FD_DB {

	/**
	 * コンストラクター<br>
	 * @author ueno hideo
	 * @since 2020/08/27
	 * @param env 環境情報
	 */
	public X_FD_User(FD_EnvData env) {
		
	}

	/** ユーザー情報ID */
	private int fD_User_ID;

	/** ユーザー情報IDの取得 */
	public int getfD_User_ID() {
		return fD_User_ID;
	}

	/** ユーザー情報IDをセットする。 */
	public void setfD_User_ID(int fD_User_ID) {
		this.fD_User_ID = fD_User_ID;
	}
	

	
}
