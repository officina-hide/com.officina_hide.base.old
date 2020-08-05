package com.officina_hide.fx.view;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.base.model.FD_Query;
import com.officina_hide.base.model.I_DB;
import com.officina_hide.base.model.I_FD_FxView;
import com.officina_hide.base.model.I_FD_FxViewParam;
import com.officina_hide.base.model.X_FD_FxView;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Fx画面開始<br>
 * @author ueno hideo
 * @version 1.20
 * @since 2020/08/02
 */
public class FxStartMain extends Application implements I_DB {

	@Override
	public void start(Stage stage) throws Exception {
		//環境情報生成
		// TODO 環境の取込み方ほについて、当面は個別の処理毎に設定する。(2020/08/03 ueno)
		FD_EnvData env = new FD_EnvData();
		//開始メッセージ
		env.getLog().open(env, FD_Logging.LOG_INITIALIZE, FD_Logging.MODE_DEBAG);
		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "Start Fx View");
		//画面情報取得
		String viewName =  "Fx_Login";
		FD_WhereData where = new FD_WhereData(I_FD_FxView.COLUMNNAME_FXVIEW_NAME, viewName);
		X_FD_FxView view = new X_FD_FxView(env, where);
		FD_Query query = new FD_Query(env, "FD_FxViewParam", new FD_WhereData(I_FD_FxViewParam.COLUMNNAME_FD_FXVIEW_ID, view.getFD_FxView_ID()));
		
		Scene scene = new Scene(getViewRoot(env, view));
		stage.setScene(scene);
		stage.setTitle(view.getFD_Name());
		stage.show();

		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "End Fx View");
	}

	/**
	 * 画面情報からRootを作成する。<br>
	 * @author ueno hideo
	 * @since 2020/08/03
	 * @param env 環境情報
	 * @param view 画面情報
	 * @return root
	 */
	private Parent getViewRoot(FD_EnvData env, X_FD_FxView view) {
		VBox root = new VBox();
		return root;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
