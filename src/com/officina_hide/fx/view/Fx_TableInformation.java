package com.officina_hide.fx.view;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.base.model.I_Fx_View;
import com.officina_hide.base.model.I_Fx_ViewItem;
import com.officina_hide.base.model.X_Fx_View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * テーブル情報画面<br>
 * @author officina-hide.com ueno
 * @version 2.00
 * @since 2020/09/01
 */
public class Fx_TableInformation extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		//環境情報取得
		FD_EnvData env = new FD_EnvData();
		//開始メッセージ
		env.getLog().open(env, "", FD_Logging.MODE_DEBAG);
		//画面情報取得
		FD_WhereData where = new FD_WhereData(I_Fx_View.COLUMNNAME_VIEW_NAME, "Fx_TableInfoemation");
		X_Fx_View view = new X_Fx_View(env, where);
		//画面項目情報取得
		getViewItem(env, "Fx_TableInfoemation");
		
		VBox root = new VBox(5);
		
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle(view.getStringOfValue(I_Fx_View.COLUMNNAME_FD_NAME));
		stage.show();
	}

	/**
	 * 画面項目情報取得<br>
	 * @author officine-hide.com ueno
	 * @since 2.00 2020/09/01
	 * @param env 環境情報
	 * @param viewName 画面名
	 */
	private void getViewItem(FD_EnvData env, String viewName) {
		int[] ids = getIds(env);
	}

	/**
	 * @param env
	 * @return
	 */
	private int[] getIds(FD_EnvData env) {
		return null;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
