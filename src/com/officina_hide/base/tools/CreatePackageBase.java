package com.officina_hide.base.tools;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.fx.tools.CreateFxResource;

/**
 * パッケージで使用する為の基本設定を行う<br>
 * @author ueno hideo
 * @version 1.20
 * @since 2020/07/13
 * @param args 
 */
public class CreatePackageBase {

	/**
	 * メイン処理<br>
	 * @author ueno hideo
	 * @since 1.20 2020/07/13
	 * @param args
	 */
	public static void main(String[] args) {
		Date StartDate = new Date();

		//環境情報設定
		FD_EnvData env = createEnv();
		//開始メッセージ
		env.getLog().open(env, FD_Logging.LOG_INITIALIZE, FD_Logging.MODE_DEBAG);
		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "Start Package Base Creating");
		/*
		 * 基本となるテーブルを生成する。
		 */
		new CreateBaseTable(env);
		/*
		 * 画面用リソース生成
		 */
		new CreateFxResource(env);
		
		//終了メッセージ
		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "Package Base Creating is completed!!");
		double startTime = StartDate.getTime();
		double endTime = new Date().getTime();
		double elapseTime = (endTime - startTime) / 1000;
		DecimalFormat df = new DecimalFormat("0.000");
		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "elapsed time " + df.format(elapseTime) + " Seconds");
		env.getLog().close();
	}

	/**
	 * 環境情報の設定<br>
	 * TODO 本メソッドはパッケージ初期開発時に使用する。将来的には設定用の画面・データベースから取得できるようにする。(2020/07/13 ueno)
	 * @author ueno hideo
	 * @since 1.20 2020/07/13
	 * @return 環境情報
	 */
	private static FD_EnvData createEnv() {
		FD_EnvData env = new FD_EnvData();
		env.setDB_Host("www.officina-hide.com");
		env.setDB_Name("datatest");
		env.setDB_User("root");
		env.setDB_Password("kan2*Sin");
		env.setSystemUserID(1000001);
		env.setLoginUserID(1000001);
		env.setModelURI("com.officina_hide.base.model");
		try {
			String currentPath = new File(".").getCanonicalPath();
			env.setLogFile_Path(currentPath + "\\log");
			env.setModelPath(currentPath + "\\src\\com\\officina_hide\\base\\model");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return env;
	}

}
