package com.officina_hide.accounts.fx;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.officina_hide.accounts.model.I_Fx_CashBook_View;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Reference;
import com.officina_hide.base.model.I_FD_TableColumn;
import com.officina_hide.base.model.I_Fx_View;
import com.officina_hide.base.model.I_Fx_ViewItem;
import com.officina_hide.base.model.X_FD_TableColumn;
import com.officina_hide.base.model.X_Fx_View;
import com.officina_hide.base.tools.FDProcess;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Fx金銭出納画面<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/09/15
 */
public class Fx_CashBook_View extends Application implements I_Fx_CashBook_View {

	/** 環境情報 */
	private FD_EnvData env;
	/** プロセス情報クラス */
	FDProcess process = new FDProcess();
	/** データベースIOクラス */
	FD_DB DB = new FD_DB();
	/** 画面項目リスト */
	List<Fx_Item> fxItemList = new ArrayList<>();
	/** フォント:Meiryo UI 12Pt */
	Font fontMeiryo12 = new Font("Meiryo UI", 12);
	
	/**
	 * コンストラクター<br>
	 * <p>実体化時に、環境情報を生成する。</p>
	 * @author officine-hide.com
	 * @since 1.00 2020/09/15
	 */
	public Fx_CashBook_View() {
		env = new FD_EnvData(View_Name);
	}

	@Override
	public void start(Stage stage) throws Exception {
		//画面情報取得
		FD_WhereData where = new FD_WhereData(I_Fx_View.COLUMNNAME_VIEW_NAME, View_Name);
		X_Fx_View view = new X_Fx_View(env, where);
		//画面項目リスト生成
		createFxItemList(view.getIntOfValue(I_Fx_View.COLUMNNAME_FX_VIEW_ID));
		
		//ルート設定
		VBox root = new VBox(5);
		createItem(root);
		
		//画面表示
		Scene scene = new Scene(root, view.getIntOfValue(I_Fx_View.COLUMNNAME_VIEW_PRE_WIDTH)
				, view.getIntOfValue(I_Fx_View.COLUMNNAME_VIEW_PRE_HEIGHT));
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * 画面項目生成<br>
	 * @author officine-hide.com
	 * @since 2.11 2020/09/19
	 * @param root ルート
	 */
	private void createItem(VBox root) {
		root.setPadding(new Insets(10, 20, 10, 20));
		for(Fx_Item item : fxItemList) {
			HBox row = new HBox(5);
			row.setAlignment(Pos.CENTER_LEFT);
			root.getChildren().add(row);
			//ラベル設定
			Label label = new Label(item.getItemName());
			label.setFont(fontMeiryo12);

			row.getChildren().addAll(label, item.getItemNode());
		}
	}

	/**
	 * 画面項目リスト生成<br>
	 * @author officine-hide.com
	 * @since 2.11 2020/09/19
	 * FIXME 汎用化予定(2020/09/22 ueno)
	 * @param viewId 画面情報ID
	 */
	private void createFxItemList(int viewId) {
		fxItemList.clear();
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT * FROM ").append(I_Fx_ViewItem.Table_Name).append(" ");
			sql.append("LEFT JOIN ").append(I_FD_Reference.Table_Name).append(" ON ")
				.append(I_FD_Reference.COLUMNNAME_FD_REFERENCE_ID).append(" = ")
				.append(I_Fx_ViewItem.COLUMNNAME_VIEWITEM_TYPE_ID).append(" ");
			sql.append("WHERE ").append(I_Fx_ViewItem.COLUMNNAME_FX_VIEW_ID).append(" = ").append(viewId).append(" ");
			DB.connection(env);
			stmt = DB.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				Fx_Item item = new Fx_Item();
				item.setItemName(rs.getString(I_Fx_ViewItem.COLUMNNAME_FD_NAME));
				item.setItemType(rs.getString(I_FD_Reference.COLUMNNAME_REFERENCE_NAME));
				//項目桁数取得
				X_FD_TableColumn column = null;
				if(item.getItemType().equals(I_Fx_ViewItem.VIEWTYPE_ID_FX_TABLE)) {
					column = new X_FD_TableColumn(env, rs.getInt(I_Fx_ViewItem.COLUMNNAME_SEARCH_COLUMN_ID));
				} else {
					column = new X_FD_TableColumn(env, rs.getInt(I_Fx_ViewItem.COLUMNNAME_TABLECOLUMN_ID));
				}
				item.setItemSize(column.getIntOfValue(I_FD_TableColumn.COLUMNNAME_TABLECOLUMN_SIZE));
				
				//ノードの設定
				switch(item.getItemType()) {
				case I_Fx_ViewItem.VIEWTYPE_ID_FX_DATE:
					DatePicker date = new DatePicker();
					date.setStyle("-fx-font-family: Meiryo UI; -fx-font-size: 12px;");
					date.setPrefWidth(110);
					item.setItemNode(date);
					break;
				case I_Fx_ViewItem.VIEWTYPE_ID_FX_TABLE:
					HBox TableBox = new HBox(0);
					TableBox.setAlignment(Pos.CENTER);
					TableBox.setStyle("-fx-font-family: Meiryo UI; -fx-font-size: 12px;");
					item.setItemNode(TableBox);
					TextField text = new TextField();
					text.addEventFilter(KeyEvent.ANY,  textEvents(text, item));
					text.setPrefWidth(200);
					Button searchButton = new Button("?");
					Button clearButton = new Button("X");
					TableBox.getChildren().addAll(text, searchButton, clearButton);
					break;
				}
				fxItemList.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param text
	 * @param item
	 * @return
	 */
	private EventHandler<KeyEvent> textEvents(TextField text, Fx_Item item) {
		return new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
//				if(event.getEventType() == KeyEvent.KEY_PRESSED) {
//					System.out.println("PresseD");
//					if(text.getText().length() >= item.getItemSize()) {
//						event.consume();
//						return;
//					}
//				}
//				if(event.getEventType() == KeyEvent.KEY_RELEASED) {
//					System.out.println("Released");
//					if(text.getText().length() >= item.getItemSize()) {
//						event.consume();
//						return;
//					}
//				}
				if(event.getEventType() == KeyEvent.KEY_TYPED) {
					//event.getCharacter().getBytes()[0]で13が改行
					System.out.println(event.getCharacter().getBytes()[0]);
				}
			}
		};
	}

}
