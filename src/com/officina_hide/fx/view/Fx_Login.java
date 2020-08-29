package com.officina_hide.fx.view;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.base.model.I_FD_User;
import com.officina_hide.base.model.X_FD_User;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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

	/** ユーザーID項目 */
	private TextField userID = new TextField();
	/** パスワード項目 */
	private PasswordField pass = new PasswordField();
	
	
	@Override
	public void start(Stage stage) throws Exception {
		//環境情報取得
		FD_EnvData env = new FD_EnvData();
		//開始メッセージ
		env.getLog().open(env, "", FD_Logging.MODE_DEBAG);
		
		System.out.println(System.getProperty("user.name"));
		
		VBox root = new VBox();
		root.setPadding(new Insets(15, 10, 10, 10));
		root.setSpacing(10);
		//画面項目セット
		setItem(stage, root, env);
		
		Scene scene = new Scene(root, 300, 130);
		stage.setOnCloseRequest(event -> {
			windowClose(stage, env);
		});
		stage.setTitle("ログイン画面");
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * 画面項目設定<br>
	 * @author ueno hideo
	 * @since 2.00 
	 * @param stage 画面ステージ
	 * @param root ルート
	 * @param env 環境情報
	 */
	private void setItem(Stage stage, VBox root, FD_EnvData env) {
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
		row02.getChildren().add(pass);
		pass.setFont(meiryo12);
		pass.setPrefWidth(itemWidth);
		
		HBox buttonRow = new HBox(10);
		root.getChildren().add(buttonRow);
		buttonRow.setAlignment(Pos.CENTER_RIGHT);
		Button okButton = new Button("ログイン");
		okButton.setFont(meiryo12);
		okButton.setOnMouseClicked(event -> {
			userConfirm(env);
		});
		buttonRow.getChildren().add(okButton);
		Button cancelButton = new Button("キャンセル");
		buttonRow.getChildren().add(cancelButton);
		cancelButton.setFont(meiryo12);
		cancelButton.setOnMouseClicked(event -> {
			windowClose(stage, env);
		});
	}

	/**
	 * 認証処理<br>
	 * @author ueno hideo
	 * @param env 環境情報
	 * @since 2.00 2020/08/27
	 */
	private void userConfirm(FD_EnvData env) {
		/*
		 * 認証の手順<br>
		 * 1.ユーザー情報取得
		 * 2.パスワード判定
		 * ※上記エラーの場合、認証エラーとしてメッセージを表示する。
		 * 3.総合メニユー表示
		 */
		FD_WhereData where = new FD_WhereData(I_FD_User.COLUMNNAME_USER_NAME, userID.getText());
		X_FD_User user = new X_FD_User(env, where);
		if(user.getfD_User_ID() > 0 && user.getPassword().equals(pass.getText())) {
			//認証後の処理を行う。
		} else {
			//認証エラー
			env.getLog().add(FD_Logging.TYPE_ERROR, FD_Logging.MODE_NORMAL, "認証エラー");
			Alert alert = new Alert(AlertType.ERROR, "認証エラー", ButtonType.OK);
			alert.showAndWait();
		}
	}

	/**
	 * 画面を閉じる<br>
	 * @author ueno hideo
	 * @since 2.00 2020/08/27
	 * @param stage
	 * @param env 
	 */
	private void windowClose(Stage stage, FD_EnvData env) {
		System.out.println("ログイン画面 Close");
		env.getLog().close();
		stage.close();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
