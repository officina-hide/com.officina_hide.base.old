package com.officina_hide.fx.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * ログイン画面<br>
 * <p>システムを最初に起動するときに表示する画面<br>
 * 本画面では、ユーザーがシステムを使用することを許可されたユーザーかどうかを判定する。</p>
 * <p>On this screen, it is determined whether the user is authorized to use the system.</p>
 * TODO 本画面は直接作りこんだシステム画面です。汎用化が必要です。(2020/08/27 ueno)
 * @author ueno hideo
 * @version 2.00
 * @since 2020/08/27
 */
public class Fx_Login extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox();
		root.setPadding(new Insets(5, 5, 5, 10));
		//画面項目セット
		setItem(root);
		
		Scene scene = new Scene(root, 400, 200);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * 画面項目設定<br>
	 * @author ueno hideo
	 * @since 2.00 
	 * @param root
	 */
	private void setItem(VBox root) {
		HBox row01 = new HBox();
		row01.setAlignment(Pos.CENTER_LEFT);
		root.getChildren().add(row01);
		Label row01Label = new Label("ユーザーID");
		row01Label.setPrefWidth(80);
		row01.getChildren().add(row01Label);
		row01Label.setFont(new Font("Meiryo UI", 12));
		TextField userID = new TextField();
		row01.getChildren().add(userID);
		userID.setFont(new Font("Meiryo UI", 12));
		userID.setPrefWidth(200);
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
