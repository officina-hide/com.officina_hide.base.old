package com.officina_hide.projects.fx;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.base.common.FxUtils;
import com.officina_hide.base.model.I_Fx_View;
import com.officina_hide.base.model.X_Fx_View;
import com.officina_hide.projects.model.I_Fx_ToDo_View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Fx_ToDo_View extends Application implements I_Fx_ToDo_View {
	/** 環境情報 */
	private FD_EnvData env;

	/**
	 * コンストラクター<br>
	 * <p>実体化時に、環境情報を生成する。</p>
	 * @author officine-hide.com
	 * @since 1.00 2020/09/15
	 */
	public Fx_ToDo_View() {
		env = new FD_EnvData(View_Name);
	}

	@Override
	public void start(Stage stage) throws Exception {
		//画面情報取得
		FD_WhereData where = new FD_WhereData(I_Fx_View.COLUMNNAME_VIEW_NAME, View_Name);
		X_Fx_View view = new X_Fx_View(env, where);
		
		//画面項目リスト生成
		FxUtils fu = new FxUtils();		
		fu.createFxItemList(env, view.getIntOfValue(I_Fx_View.COLUMNNAME_FX_VIEW_ID));

		//ルート設定
		VBox root = new VBox(5);
		fu.createItem(root);
		
		//画面表示
		Scene scene = new Scene(root, view.getIntOfValue(I_Fx_View.COLUMNNAME_VIEW_PRE_WIDTH)
				, view.getIntOfValue(I_Fx_View.COLUMNNAME_VIEW_PRE_HEIGHT));
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
