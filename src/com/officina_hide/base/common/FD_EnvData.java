package com.officina_hide.base.common;

import java.io.File;
import java.io.IOException;

/**
 * 環境情報<br>
 * <p>本クラスはパッケージを利用する上で普遍的な情報を扱う。</p>
 * @author ueno hideo
 * @version 1.20
 * @since 2020/07/13
 */
public class FD_EnvData {
	
	/**
	 * コンストラクター<br>
	 * <p>インスタンス時に環境情報の内ベースとなる情報を取得し実体化する。</p>
	 * @author ueno hideo
	 * @since 1.20 2020/08/03
	 */
	public FD_EnvData() {
		getEnvData();
	}

	/**
	 * 環境情報取得<br>
	 * @author ueno hideo
	 * @since 2020/08/03
	 * TODO 当面はここで環境情報を直に設定する。(2020/08/03 ueno)
	 */
	private void getEnvData() {
		setDB_Host("www.officina-hide.com");
		setDB_Name("datatest");
		setDB_User("root");
		setDB_Password("kan2*Sin");
		setSystemUserID(1000001);
		setLoginUserID(1000001);
		setModelURI("com.officina_hide.base.model");
		try {
			String currentPath = new File(".").getCanonicalPath();
			setLogFile_Path(currentPath + "\\log");
			setModelPath(currentPath + "\\src\\com\\officina_hide\\base\\model");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
	/** ログDB出力判定 */
	private boolean DBOut;
	/**
	 * システムユーザーID
	 */
	private int systemUserID;
	/**
	 * モデルファイルパス
	 */
	private String modelPath;
	/**
	 * モデルURI
	 */
	private String modelURI;
	/**
	 * ログインユーザーID
	 */
	private int loginUserID;
	
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
	public String getModelPath() {
		return modelPath;
	}
	public void setModelPath(String modelPath) {
		this.modelPath = modelPath;
	}
	public String getModelURI() {
		return modelURI;
	}
	public void setModelURI(String modelURI) {
		this.modelURI = modelURI;
	}
	/**
	 * @return loginUserID
	 */
	public int getLoginUserID() {
		return loginUserID;
	}
	/**
	 * @param loginUserID セットする loginUserID
	 */
	public void setLoginUserID(int loginUserID) {
		this.loginUserID = loginUserID;
	}
	/**
	 * @return dBOut
	 */
	public boolean isDBOut() {
		return DBOut;
	}
	/**
	 * @param dBOut セットする dBOut
	 */
	public void setDBOut(boolean dBOut) {
		DBOut = dBOut;
	}

}
