package com.officina_hide.fx.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
		root.setPadding(new Insets(15, 10, 10, 10));
		root.setSpacing(10);
		//画面項目セット
		setItem(stage, root);
		
		Scene scene = new Scene(root, 300, 130);
		stage.setOnCloseRequest(event -> {
			windowClose(stage);
		});
		stage.setTitle("ログイン画面");
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * 画面項目設定<br>
	 * @author ueno hideo
	 * @since 2.00 
	 * @param stage 
	 * @param root
	 */
	private void setItem(Stage stage, VBox root) {
		Font meiryo12 = new Font("Meiryo UI", 12);
		int labelWidth = 80;
		int itemWidth = 200;
		
		HBox row01 = new HBox();
		row01.setAlignment(Pos.CENTER_LEFT);
		root.getChildren().add(row01);
		Label row01Label = new Label("ユーザーID");
		row01Label.setPrefWidth(labelWidth);
		row01.getChildren().add(row01Label);
		row01Label.setFont(meiryo12);
		TextField userID = new TextField();
		row01.getChildren().add(userID);
		userID.setFont(meiryo12);
		userID.setPrefWidth(itemWidth);
		
		HBox row02 = new HBox();
		root.getChildren().add(row02);
		row02.setAlignment(Pos.CENTER_LEFT);
		
		Label row02Label = new Label("パスワード");
		row02.getChildren().add(row02Label);
		row02Label.setFont(meiryo12);
		row02Label.setPrefWidth(labelWidth);
		PasswordField pass = new PasswordField();
		row02.getChildren().add(pass);
		pass.setFont(meiryo12);
		pass.setPrefWidth(itemWidth);
		
		HBox buttonRow = new HBox(10);
		root.getChildren().add(buttonRow);
		buttonRow.setAlignment(Pos.CENTER_RIGHT);
		Button okButton = new Button("ログイン");
		okButton.setFont(meiryo12);
		okButton.setOnMouseClicked(event -> {
			userConfirm();
		});
		buttonRow.getChildren().add(okButton);
		Button cancelButton = new Button("キャンセル");
		buttonRow.getChildren().add(cancelButton);
		cancelButton.setFont(meiryo12);
		cancelButton.setOnMouseClicked(event -> {
			windowClose(stage);
		});
	}

	/**
	 * 認証処理<br>
	 * @author ueno hideo
	 * @since 2.00 2020/08/27
	 */
	private void userConfirm() {
		
	}

	/**
	 * 画面を閉じる<br>
	 * @author ueno hideo
	 * @since 2.00 2020/08/27
	 * @param stage
	 */
	private void windowClose(Stage stage) {
		System.out.println("ログイン画面 Close");
		stage.close();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
