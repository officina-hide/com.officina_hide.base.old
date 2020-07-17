package com.officina_hide.base.common;

/**
 * 環境情報<br>
 * <p>本クラスはパッケージを利用する上で普遍的な情報を扱う。</p>
 * @author ueno hideo
 * @version 1.20
 * @since 2020/07/13
 */
public class FD_EnvData {
	/**
	 * ログ情報
	 */
	private FD_Logging log = new FD_Logging();
	/**
	 * データベース管理システム名<br>
	 * <p>本パッケージでは登録はMySQLを使用するので、現時点ではこの変数は使用しない。<br>
	 * In this package, registration uses MySQL, so this variable is not used at this time.</p>
	 */
	@SuppressWarnings("unused")
	private String DB_System_Name;
	/**
	 * データベース名
	 */
	private String DB_Name;
	/**
	 * データベースホスト名
	 */
	private String DB_Host;
	/**
	 * データベースユーザーID
	 */
	private String DB_User;
	/**
	 * データベースパスワード
	 */
	private String DB_Password;
	/**
	 * ログファイルパス
	 */
	private String logFile_Path;
	/**
	 * システムユーザーID
	 */
	private int systemUserID;
	
	public String getDB_Name() {
		return DB_Name;
	}
	public void setDB_Name(String dB_Name) {
		DB_Name = dB_Name;
	}
	public String getDB_Host() {
		return DB_Host;
	}
	public void setDB_Host(String dB_Host) {
		DB_Host = dB_Host;
	}
	public String getDB_User() {
		return DB_User;
	}
	public void setDB_User(String dB_User) {
		DB_User = dB_User;
	}
	public String getDB_Password() {
		return DB_Password;
	}
	public void setDB_Password(String dB_Password) {
		DB_Password = dB_Password;
	}
	/**
	 * ログ取得<br>
	 * @return 環境情報として設定しているロギング情報を返す。
	 */
	public FD_Logging getLog() {
		return log;
	}
	public String getLogFile_Path() {
		return logFile_Path;
	}
	public void setLogFile_Path(String logFile_Path) {
		this.logFile_Path = logFile_Path;
	}
	public int getSystemUserID() {
		return systemUserID;
	}
	public void setSystemUserID(int systemUserID) {
		this.systemUserID = systemUserID;
	}

}
