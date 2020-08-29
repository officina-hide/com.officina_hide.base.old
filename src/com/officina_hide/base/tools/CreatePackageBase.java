package com.officina_hide.base.tools;

import java.text.DecimalFormat;
import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.system.CreateUserTalbe;

/**
 * パッケージで使用する為の基本設定を行う<br>
 * @author ueno hideo
 * @version 1.20 新規作成<br>
 * @version 2.00 システム機能の作りこみを優先する。<br>
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
		FD_EnvData env = new FD_EnvData();
		//開始メッセージ
		env.getLog().open(env, FD_Logging.LOG_INITIALIZE, FD_Logging.MODE_DEBAG);
		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "Start Package Base Creating");
		/*
		 * システムに関する機能を優先して構築していくこととした。(Ver 2.00 2020/08/27 ueno)
		 * ログインに必要なテーブルの作成
		 * ・テーブル情報
		 * ・ユーザー情報テーブル
		 * ・アクセスログ情報テーブル
		 */
		TableInformation table = new TableInformation();
		table.createTable(env);
		table.addData(env, 100, "FD_Table", "テーブル情報");
		
		CreateUserTalbe createUserTable = new CreateUserTalbe();
		createUserTable.createUserTable(env);
		createUserTable.addData(env, 100, "System", "admin");
//		new CreateUserTalbe(env);
		/*
		 * 基本となるテーブルを生成する。
		 */
//		new CreateBaseTable(env);
//		new CreateBaseResource(env);
		/*
		 * 画面用リソース生成
		 */
//		new CreateFxResource(env);
		
		//終了メッセージ
		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "Package Base Creating is completed!!");
		double startTime = StartDate.getTime();
		double endTime = new Date().getTime();
		double elapseTime = (endTime - startTime) / 1000;
		DecimalFormat df = new DecimalFormat("0.000");
		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "elapsed time " + df.format(elapseTime) + " Seconds");
		env.getLog().close();
	}

}
