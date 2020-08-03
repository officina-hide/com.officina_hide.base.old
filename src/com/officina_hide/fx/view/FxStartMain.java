package com.officina_hide.fx.view;

import com.officina_hide.base.common.FD_EnvData;

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
public class FxStartMain extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		FD_EnvData env = new FD_EnvData();
		// TODO 環境の取込み方ほについて、当面は個別の処理毎に設定する。(2020/08/03 ueno)
		Scene scene = new Scene(getViewRoot(env, "Fx_Login"));
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * 画面情報からRootを作成する。<br>
	 * @author ueno hideo
	 * @since 2020/08/03
	 * @param env 環境情報
	 * @param viewName 画面名
	 * @return root
	 */
	private Parent getViewRoot(FD_EnvData env, String viewName) {
		VBox root = new VBox();
		//画面情報取得
		return root;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
