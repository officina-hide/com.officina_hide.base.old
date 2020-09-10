package com.officina_hide.fx.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Item;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_OrderData;
import com.officina_hide.base.model.I_FD_Reference;
import com.officina_hide.base.model.I_FD_Table;
import com.officina_hide.base.model.I_Fx_View;
import com.officina_hide.base.model.I_Fx_ViewItem;
import com.officina_hide.base.model.X_FD_Reference;
import com.officina_hide.base.model.X_FD_Table;
import com.officina_hide.base.model.X_Fx_View;
import com.officina_hide.base.model.X_Fx_ViewItem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * テーブル情報画面<br>
 * @author officina-hide.com ueno
 * @version 2.00
 * @since 2020/09/01
 */
public class Fx_TableInformation extends Application {

	/** 画面名 */
	private static final String VIEW_NAME = "Fx_TableInfoemation";
	/** データベースクラス */
	private FD_DB DB = new FD_DB();
	/** 画面情報 */
	private X_Fx_View view;
	/** ベースフォント */
	private Font baseFont = new Font("Meiryo UI", 12);
	/** 項目リスト */
	private List<FD_Item> fxItemList = new ArrayList<>();
	
	@Override
	public void start(Stage stage) throws Exception {
		//環境情報取得
		FD_EnvData env = new FD_EnvData();
		//開始メッセージ
		env.getLog().open(env, "", FD_Logging.MODE_DEBAG);
		//画面情報取得
		FD_WhereData where = new FD_WhereData(I_Fx_View.COLUMNNAME_VIEW_NAME, VIEW_NAME);
		view = new X_Fx_View(env, where);
		//ルート設定
		VBox root = new VBox(5);
		root.setPadding(new Insets(10, 10, 10, 10));
		//ヘッダーボタン領域設定
		setHeaderButton(env, root);
		//画面項目情報取得
		getViewItem(env, root, view.getIntOfValue(I_Fx_View.COLUMNNAME_FX_VIEW_ID));
		
		Scene scene = new Scene(root
				, view.getIntOfValue(I_Fx_View.COLUMNNAME_VIEW_PRE_WIDTH)
				, view.getIntOfValue(I_Fx_View.COLUMNNAME_VIEW_PRE_HEIGHT));
		stage.setScene(scene);
		stage.setTitle(view.getStringOfValue(I_Fx_View.COLUMNNAME_FD_NAME));
		stage.show();
	}

	/**
	 * ヘッダーボタン領域設定<br>
	 * @author officine-hide.com ueno
	 * @since 2.00 2020/09/08
	 * @param env 環境情報
	 * @param root 項目ルート
	 */
	private void setHeaderButton(FD_EnvData env, VBox root) {
		HBox row = new HBox(5);
		root.getChildren().add(row);
		//保存ボタン
		Button saveButton = new Button("保存");
		row.getChildren().add(saveButton);
		saveButton.setFont(baseFont);
		saveButton.setOnAction(event->{
			saveData(env, view.getIntOfValue(I_Fx_View.COLUMNNAME_TABLE_ID));
		});
	}

	/**
	 * 「保存」ボタン処理<br>
	 * @author officina-hide.com ueno
	 * @param env 環境情報
	 * @param tableId テーブル情報ID
	 * @since 2020/09/09
	 */
	private void saveData(FD_EnvData env, int tableId) {
		//テーブル情報取得
		X_FD_Table table = new X_FD_Table(env, tableId);
		//保存確認
		Alert alert = new Alert(AlertType.INFORMATION, "テーブル情報を保存しますか？", ButtonType.OK, ButtonType.CANCEL);
		alert.setHeaderText("保存確認");
		Optional<ButtonType> rs = alert.showAndWait();
		if(rs.get() == ButtonType.CANCEL) {
			return;
		}
		try {
			//対象テーブル情報クラス名
			String tableClazzName = "X_"+table.getStringOfValue(I_FD_Table.COLUMNNAME_TABLE_NAME);
			//テーブル情報生成
			Class<?> dataClazz = Class.forName("com.officina_hide.base.model."+tableClazzName);
			Constructor<?> con = dataClazz.getConstructor(new Class[] {FD_EnvData.class});
			Object dataInstance = con.newInstance(new Object[] {env});
			
			Method setValueMethod = dataClazz.getMethod("setValue", String.class, Object.class);
			Method saveMethod = dataClazz.getMethod("save", FD_EnvData.class);
			
			//保存処理
			// TODO とりあえず新規保存で項目チェックも無しで保存のみを行う。(2020/09/10 ueno)
			for(FD_Item item : fxItemList) {
				switch(item.getItemType()) {
				case I_Fx_ViewItem.VIEWTYPE_ID_FX_TEXT:
					TextField textField = (TextField) item.getItemData();
					setValueMethod.invoke(dataInstance, item.getItemName(), textField.getText());
					break;
				case I_Fx_ViewItem.VIEWTYPE_ID_FX_TEXTFIELD:
					TextArea textArea = (TextArea) item.getItemData();
					setValueMethod.invoke(dataInstance, item.getItemName(), textArea.getText());
				}
			}
			//データ保存
			setValueMethod.invoke(dataInstance, I_FD_Table.COLUMNNAME_FD_TABLE_ID, 0);
			saveMethod.invoke(dataInstance, env);
			
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "テーブル情報登録完了");
		alert = new Alert(AlertType.INFORMATION, "登録完了しました。", ButtonType.OK);
		alert.showAndWait();
	}

	/**
	 * 画面項目情報取得<br>
	 * @author officine-hide.com ueno
	 * @since 2.00 2020/09/01
	 * @param env 環境情報
	 * @param root ルート
	 * @param viewId 画面情報ID
	 */
	private void getViewItem(FD_EnvData env, VBox root, int viewId) {
		FD_WhereData where = new FD_WhereData(I_Fx_ViewItem.COLUMNNAME_FX_VIEW_ID, viewId);
		List<Integer> ids = getIds(env, I_Fx_ViewItem.Table_Name, where, null);
		for(int id : ids) {
			X_Fx_ViewItem viewItem = new X_Fx_ViewItem(env, id);
			HBox row = new HBox(5);
			row.setAlignment(Pos.CENTER_LEFT);
			root.getChildren().add(row);
			//項目ラベル
			Label label = new Label(viewItem.getStringOfValue(I_Fx_ViewItem.COLUMNNAME_FD_NAME));
			label.setFont(baseFont);
			row.getChildren().add(label);
			//項目属性用リファレンス情報取得
			X_FD_Reference ref = new X_FD_Reference(env
					, viewItem.getIntOfValue(I_Fx_ViewItem.COLUMNNAME_VIEWITEM_TYPE_ID));
			//項目リスト用情報生成
			FD_Item fxItem = new FD_Item(viewItem.getStringOfValue(I_Fx_ViewItem.COLUMNNAME_VIEWITEM_NAME)
					, null, ref.getStringOfValue(I_FD_Reference.COLUMNNAME_REFERENCE_NAME));
			//項目生成
			switch(ref.getStringOfValue(I_FD_Reference.COLUMNNAME_REFERENCE_NAME)) {
			case I_Fx_ViewItem.VIEWTYPE_ID_FX_TEXT:
				TextField text = new TextField();
				text.setFont(baseFont);
				row.getChildren().add(text);
				fxItem.setItemData(text);
				break;
			case I_Fx_ViewItem.VIEWTYPE_ID_FX_NUMBER:
				break;
			case I_Fx_ViewItem.VIEWTYPE_ID_FX_TEXTFIELD:
				TextArea textfield = new TextArea();
				textfield.setFont(baseFont);
				textfield.setPrefRowCount(3);
				textfield.setPrefColumnCount(40);
				row.getChildren().add(textfield);
				fxItem.setItemData(textfield);
				break;
			}
			
			fxItemList.add(fxItem);
		}
	}

	/**
	 * テーブル情報ID抽出リスト取得<br>
	 * <p>指定されたテーブルに対して、指定された条件を満たす情報の情報IDリストを取得する。</p>
	 * @param env 環境情報
	 * @param tableName テーブル名
	 * @param where 抽出条件 
	 * @param order 並び順
	 * @return テーブル情報ID抽出リスト
	 */
	private List<Integer> getIds(FD_EnvData env, String tableName, FD_WhereData where, FD_OrderData order) {
		List<Integer> list = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT ").append(tableName).append("_ID FROM ").append(tableName).append(" ");
			if(where != null) {
				sql.append("WHERE ").append(where.toString());
			}
			if(order != null) {
				sql.append("Order BY ").append(order.toString());
			}
			DB.connection(env);
			stmt = DB.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				list.add(rs.getInt(tableName+"_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs, stmt);
		}
		return list;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
