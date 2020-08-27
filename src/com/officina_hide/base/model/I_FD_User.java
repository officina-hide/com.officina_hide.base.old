package com.officina_hide.base.model;

/**
 * ユーザー情報<br>
 * @author ueno hideo
 * @version 2.00
 * @since 2020/08/27
 */
public interface I_FD_User {

	/** テーブル名 */
	public  final String Table_Name ="FD_User";
	
	/** テーブル情報ID */
	public final int Table_ID = 1001;
	
	/** ユーザー情報ID */
	public final String COLUMNNAME_FD_USER_ID = "FD_User_ID";
	
	/** ユーザー名 */
	public final String COLUMNNAME_USER_NAME = "User_Name";
	
	/** パスワード */
	public final String COLUMNNAME_PASSWARD ="Passward";
}
