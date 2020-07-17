package com.officina_hide.base.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ログ管理クラス<br>
 * @author ueno hideo
 * @version 1.20
 * @since 2020/07/16
 */
public class FD_Logging {

	/**
	 * ログ出力ファイル<br>
	 */
	private static FileWriter logFile;
//	/**
//	 * 環境情報
//	 */
//	private FD_EnvData env;
	/**
	 * 表示モード
	 */
	private String dispMode;
	
	/**
	 * ロギング対象 : メッセージ<br>
	 */
	public static final String TYPE_MESSAGE = "message";
	/**
	 * ロギング対象 : エラー>
	 */
	public static final String TYPE_ERROR = "error";
	/**
	 * ロギング対象 : データベース
	 */
	public static final String TYPE_DB = "db";
	/**
	 * ロギングレベル : ノーマル
	 */
	public static final String MODE_NORMAL = "normal";
	/**
	 * ロギングレベル : デバッグ
	 */
	public static final String MODE_DEBAG = "debug";
	/**
	 * ログオープン方法 : 初期化してオープン
	 */
	public static final String LOG_INITIALIZE = "initial";
	
	/**
	 * ログを開く<br>
	 * @param env 環境情報
	 * @param start オープン時処理
	 * @param mode ログ対象モード
	 */
	public void open(FD_EnvData env, String start, String mode) {
//		//環境情報補完
//		this.env = env;
		//表示モードセット
		this.dispMode = mode;
		//ログファイルオープン
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String fileName = df.format(new Date().getTime());
		//ログファイルの探索
		File[] files = new File(env.getLogFile_Path()).listFiles();
		int count = 0;
		for(File file : files) {
			if(file.isFile()) {
				if(file.getName().split("_")[1].equals(fileName)) {
					count = Integer.parseInt(file.getName().split("_")[2].split("\\.")[0]);
				}
			}
		}
		DecimalFormat nf = new DecimalFormat("00");
		try {
			if(start.equals(LOG_INITIALIZE)) {
				fileName = "Log_"+fileName + "_" + nf.format(count + 1)+".log";
				logFile = new FileWriter(env.getLogFile_Path()+"\\"+fileName, false);
			} else {
				fileName = "Log_"+fileName + "_" + nf.format(count)+".log";
				logFile = new FileWriter(env.getLogFile_Path()+"\\"+fileName, true);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ログ追加<br>
	 * @author ueno hideo
	 * @since 1.20 2020/07/16
	 * @param type ログ種別
	 * @param mode ログモードl
	 * @param message メッセージ
	 */
	public void add(String type, String mode, String message) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		StringBuffer msg = new StringBuffer();
		switch(type) {
			case TYPE_MESSAGE:
				msg.append(df.format(new Date())).append(" info : ").append(message);
				break;
			case TYPE_ERROR:
				msg.append(df.format(new Date())).append(" error!! : ").append(message);
				break;
			case TYPE_DB:
				msg.append(df.format(new Date())).append(" DB : ").append(message);
				break;
		}
		//コンソール表示
		switch(dispMode) {
		case MODE_NORMAL:
			if(mode.equals(MODE_NORMAL)) {
				//no operation
			} else {
				return;
			}
			break;
		case MODE_DEBAG:
			if(mode.equals(MODE_DEBAG) || mode.equals(MODE_NORMAL)) {
				//no operation
			} else {
				return;
			}
		}
		//画面表示
		System.out.println(msg.toString());
		//ログファイル記録
		try {
			logFile.write(msg.toString()+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ログファイルをクローズする。
	 * @author ueno hideo
	 * @since 1.20 2020/07/16
	 */
	public void close() {
		try {
			logFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
