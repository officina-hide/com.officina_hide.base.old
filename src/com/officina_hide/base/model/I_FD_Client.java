package com.officina_hide.base.model;

/**
 * クライアント情報インターフェース<br>
 * @author officine-hide.com
 * @version 1.00 新規作成
 * @since 2020/09/13
 */
public interface I_FD_Client {

	/** テーブル名 */
	public final String Table_Name = "FD_Client";
	/** テーブル論理名 */
	public final String Name = "クライアント情報";
	
	/** クライアント情報ID */
	public final String COLUMNNAME_FD_CLIENT_ID = Table_Name + "_ID";
	/** クライアント名 */
	public final String COLUMNNAME_CLIENT_NAME = "Client_Name";
}
