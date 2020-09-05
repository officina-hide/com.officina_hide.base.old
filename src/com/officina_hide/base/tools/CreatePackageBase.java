package com.officina_hide.base.tools;

import java.text.DecimalFormat;
import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.model.I_FD_Numbering;
import com.officina_hide.base.model.I_FD_Table;
import com.officina_hide.base.model.I_FD_TableColumn;
import com.officina_hide.base.model.I_Fx_View;
import com.officina_hide.base.model.I_Fx_ViewItem;
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
		 * ・テーブル情報、テーブル項目情報、採番情報
		 * ・画面情報
		 * ・ユーザー情報テーブル
		 * ・アクセスログ情報テーブル
		 */
		
		//テーブル情報
		FDTable table = new FDTable();
		table.createTable(env);
		table.addData(env, 101, "FD_Table", "テーブル情報");
		//採番情報
		FDNumbering num = new FDNumbering();
		num.createTable(env);
		table.addData(env, I_FD_Numbering.Table_ID, I_FD_Numbering.Table_Name, "採番情報");
		num.add(env, I_FD_Table.Table_ID, 1000001, 0);
		num.add(env, I_FD_TableColumn.Table_ID, 1000001, 0);
		num.add(env, I_FD_Numbering.Table_ID, 1000001, 0);
		num.add(env, I_Fx_View.Table_ID, 1000001, 0);
		num.add(env, I_Fx_ViewItem.Table_ID, 1000001, 0);
		//リファレンス情報
		FDReference ref = new FDReference();
		ref.createTable(env);
//		//テーブル項目情報
//		FDTableColumn tableColumn = new FDTableColumn();
//		tableColumn.createTable(env);
//		
//		//画面情報
//		FxViewInformation view = new FxViewInformation();
//		view.createTable(env);
//		table.addData(env, I_Fx_View.Table_ID, I_Fx_View.Table_Name, "画面情報");
//		view.addData(env,  "Fx_Login", "ログイン画面");
//		view.addData(env,  "Fx_Menu", "総合メニュー画面");
//		int viewItemId = view.addData(env,  "Fx_TableInfoemation", "テーブル情報画面");
//		view.addData(env,  "Fx_View", "画面情報画面");
//		//画面項目情報
//		FxViewItem viewItem = new FxViewItem();
//		viewItem.createTable(env);
//		viewItem.addData(env, viewItemId, "Table_Name", "テーブル名", I_Fx_ViewItem.VIEWTYPE_ID_FX_TEXT);
//		
//		CreateUserTalbe createUserTable = new CreateUserTalbe();
//		createUserTable.createUserTable(env);
//		createUserTable.addData(env, 100, "System", "admin");
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
