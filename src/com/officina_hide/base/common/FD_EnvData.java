package com.officina_hide.base.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.officina_hide.base.tools.FDProcess;

/**
 * 環境情報<br>
 * <p>本クラスはパッケージを利用する上で普遍的な情報を扱う。</p>
 * @author officine-hide.com
 * @version 1.20 新規作成<br>
 * @since 2020/07/13
 */
public class FD_EnvData {
	/** データベースホストURI */
	private static final String DB_HOST = "DB_host";
	/** データベース名 */
	private static final String DB_NAME = "DB_Name";
	/** データベース使用ユーザー */
	private static final String DB_USER = "DB_User";
	/** データベース使用ユーザーパスワード */
	private static final String DB_PASWORD = "DB_Passward";
	/** システムユーザーID */
	private static final String SYSTEM_USER_ID = "System_User_ID";
	/** ログインユーザーID */
	private static final String LOGIN_USER_ID = "Login_User_ID";
	/** ログ保管フォルダーPath */
	private static final String LOG_FILE_PATH = "Log_Path";

	/*
	 * 改造案<br>
	 * @sinse 2020/09/29 ueno
	 * 環境情報をプロパティ化する。<br>
	 * 開発用フロパティと自分の利用用プロパティを作成し、開発済みでデータ作成を続けていく場合に分けて使用する。<br>
	 * ユーザーデータの互換性を保つためのツールを作成する。<br>
	 */
	
	/**
	 * コンストラクター<br>
	 * <p>インスタンス時に環境情報の内ベースとなる情報を取得し実体化する。</p>
	 * @author ueno hideo
	 * @since 1.20 2020/08/03
	 */
	public FD_EnvData() {
		Properties prop = new Properties();
		
		try {
			String currentPath = new File(".").getCanonicalPath();
			prop.load(new FileInputStream(new File(currentPath+"\\data\\base.properties")));
			setEnv(prop);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		getEnvData();
	}

	/**プロパティから環境情報セット<br>
	 * @author officine-hide.com
	 * @since 2,12 2020/09/29
	 * @param prop プロパティ情報
	 */
	private void setEnv(Properties prop) {
		setDB_Host(prop.getProperty(DB_HOST));
		setDB_Name(prop.getProperty(DB_NAME));
		setDB_User(prop.getProperty(DB_USER));
		setDB_Password(prop.getProperty(DB_PASWORD));
		setSystemUserID(Integer.parseInt(prop.getProperty(SYSTEM_USER_ID)));
		setLoginUserID(Integer.parseInt(prop.getProperty(LOGIN_USER_ID)));
		String logPath = prop.getProperty(LOG_FILE_PATH);
		if(logPath.indexOf("$currentPath$") >= 0) {
			try {
				String currentPath = new File(".").getCanonicalPath();
				logPath = logPath.replaceAll("\\$currentPath\\$", currentPath.replaceAll("\\\\", "\\\\\\\\"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		setLogFile_Path(logPath);
	}

	/**
	 * コンストラクター<br>
	 * <p>実体化時に、DB出力モードでログをOpenする。</p>
	 * @param processName プロセス名
	 */
	public FD_EnvData(String processName) {
		Properties prop = new Properties();
		
		try {
			String currentPath = new File(".").getCanonicalPath();
			prop.load(new FileInputStream(new File(currentPath+"\\data\\base.properties")));
			setEnv(prop);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		getEnvData();
		//ログOpen(DBモード)
		this.getLog().open(this, FD_Logging.LOG_DB_OUT, FD_Logging.MODE_DEBAG);
		//プロセススタート
		FDProcess process = new FDProcess();
		process.startProcess(this, processName);
	}

//	/**
//	 * 環境情報取得<br>
//	 * @author ueno hideo
//	 * @since 2020/08/03
//	 * TODO 当面はここで環境情報を直に設定する。(2020/08/03 ueno)
//	 */
//	private void getEnvData() {
//		setDB_Host("www.officina-hide.com");
//		setDB_Name("datatest");
//		setDB_User("root");
//		setDB_Password("kan2*Sin");
//		setSystemUserID(1000001);
//		setLoginUserID(1000001);
//		setModelURI("com.officina_hide.base.model");
//		try {
//			String currentPath = new File(".").getCanonicalPath();
//			setLogFile_Path(currentPath + "\\log");
//			setModelPath(currentPath + "\\src\\com\\officina_hide\\base\\model");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

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
	/** プロセスID */
	private int processId;
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

	/**
	 * @return processId
	 */
	public int getProcessId() {
		return processId;
	}

	/**
	 * @param processId セットする processId
	 */
	public void setProcessId(int processId) {
		this.processId = processId;
	}

}
