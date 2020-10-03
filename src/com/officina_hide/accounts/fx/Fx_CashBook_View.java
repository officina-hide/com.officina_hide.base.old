package com.officina_hide.accounts.fx;

import com.officina_hide.accounts.model.I_Fx_CashBook_View;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.base.common.FxUtils;
import com.officina_hide.base.model.I_Fx_View;
import com.officina_hide.base.model.X_Fx_View;
import com.officina_hide.base.tools.FDProcess;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
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
//	/** データベースIOクラス */
//	FD_DB DB = new FD_DB();
//	/** 画面項目リスト */
//	List<Fx_Item> fxItemList = new ArrayList<>();
//	/** フォント:Meiryo UI 12Pt */
//	Font fontMeiryo12 = new Font("Meiryo UI", 12);
	
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
//
//	/**
//	 * 画面項目生成<br>
//	 * @author officine-hide.com
//	 * @since 2.11 2020/09/19
//	 * @param root ルート
//	 */
//	private void createItem(VBox root) {
//		root.setPadding(new Insets(10, 20, 10, 20));
//		for(Fx_Item item : fxItemList) {
//			HBox row = new HBox(5);
//			row.setAlignment(Pos.CENTER_LEFT);
//			root.getChildren().add(row);
//			//ラベル設定
//			Label label = new Label(item.getItemName());
//			label.setFont(fontMeiryo12);
//
//			row.getChildren().addAll(label, item.getItemNode());
//		}
//	}
//
//	/**
//	 * 画面項目リスト生成<br>
//	 * @author officine-hide.com
//	 * @since 2.11 2020/09/19
//	 * FIXME 汎用化予定(2020/09/22 ueno)
//	 * @param viewId 画面情報ID
//	 */
//	private void createFxItemList(int viewId) {
//		fxItemList.clear();
//		Statement stmt = null;
//		ResultSet rs = null;
//		StringBuffer sql = new StringBuffer();
//		try {
//			sql.append("SELECT * FROM ").append(I_Fx_ViewItem.Table_Name).append(" ");
//			sql.append("LEFT JOIN ").append(I_FD_Reference.Table_Name).append(" ON ")
//				.append(I_FD_Reference.COLUMNNAME_FD_REFERENCE_ID).append(" = ")
//				.append(I_Fx_ViewItem.COLUMNNAME_VIEWITEM_TYPE_ID).append(" ");
//			sql.append("WHERE ").append(I_Fx_ViewItem.COLUMNNAME_FX_VIEW_ID).append(" = ").append(viewId).append(" ");
//			DB.connection(env);
//			stmt = DB.createStatement();
//			rs = stmt.executeQuery(sql.toString());
//			while(rs.next()) {
//				Fx_Item item = new Fx_Item();
//				item.setItemName(rs.getString(I_Fx_ViewItem.COLUMNNAME_FD_NAME));
//				item.setItemType(rs.getString(I_FD_Reference.COLUMNNAME_REFERENCE_NAME));
//				//画面項目情報
//				item.setViewItem(new X_Fx_ViewItem(env, rs.getInt(I_Fx_ViewItem.COLUMNNAME_FX_VIEWITEM_ID)));
//				//項目桁数取得
//				X_FD_TableColumn column = null;
//				if(item.getItemType().equals(I_Fx_ViewItem.VIEWTYPE_ID_FX_TABLE)) {
//					column = new X_FD_TableColumn(env, item.getViewItem().getIntOfValue(I_Fx_ViewItem.COLUMNNAME_SEARCH_COLUMN_ID));
//				} else {
//					column = new X_FD_TableColumn(env, item.getViewItem().getIntOfValue(I_Fx_ViewItem.COLUMNNAME_TABLECOLUMN_ID));
//				}
//				item.setItemSize(column.getIntOfValue(I_FD_TableColumn.COLUMNNAME_TABLECOLUMN_SIZE));
//				
//				//ノードの設定
//				switch(item.getItemType()) {
//				case I_Fx_ViewItem.VIEWTYPE_ID_FX_DATE:
//					DatePicker date = new DatePicker();
//					date.setStyle("-fx-font-family: Meiryo UI; -fx-font-size: 12px;");
//					date.setPrefWidth(110);
//					item.setItemNode(date);
//					break;
//				case I_Fx_ViewItem.VIEWTYPE_ID_FX_TABLE:
//					HBox TableBox = new HBox(5);
//					TableBox.setAlignment(Pos.CENTER);
//					TableBox.setStyle("-fx-font-family: Meiryo UI; -fx-font-size: 12px;");
//					item.setItemNode(TableBox);
//					TextField text = new TextField();
//					text.addEventFilter(KeyEvent.KEY_TYPED,  textEvents(text, item));
//					text.setPrefWidth(100);
//					Button searchButton = new Button("?");
//					Button clearButton = new Button("X");
//					clearButton.setOnMouseClicked(event->{
//						text.clear();
//					});
//					TextField dispText = new TextField();
//					dispText.setDisable(true);
//					searchButton.setOnMouseClicked(event->{
//						searchData(item, text);
//					});
//					TableBox.getChildren().addAll(text, searchButton, clearButton, dispText);
//					break;
//				case I_Fx_ViewItem.VIEWTYPE_ID_FX_LIST:
//					item.setItemNode(createListNode(env, item));
//					break;
//				}
//				fxItemList.add(item);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * リスト項目ノート生成<br>
//	 * @author officine-hide.com
//	 * @since 2.11 2020/09/28
//	 * @param env 環境情報
//	 * @param item Fx項目情報
//	 * @return リスト項目ノード
//	 */
//	private Node createListNode(FD_EnvData env, Fx_Item item) {
//		HBox listBox = new HBox(5);
//		
//		//テーブル項目リスト情報からリストを取得する。
//		String[] strList = getColumnList(env
//				, item.getViewItem().getIntOfValue(I_Fx_ViewItem.COLUMNNAME_TABLECOLUMN_ID));
//		ObservableList<String> list = FXCollections.observableArrayList(strList);
//
//		ChoiceBox<String> selectBox = new ChoiceBox<>(list);
//		listBox.getChildren().add(selectBox);
//		return listBox;
//	}
//
//	/**
//	 * リスト配列取得<br>
//	 * @author officine-hide.com
//	 * @since 2.12 2020/10/01
//	 * @param env 環境情報
//	 * @param columnId 対象テーブル項目情報ID
//	 * @return リスト配列
//	 */
//	private String[] getColumnList(FD_EnvData env, int columnId) {
//		String[] strList = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		StringBuffer sql = new StringBuffer();
//		try {
//			sql.append("SELECT * FROM ").append(I_FD_TableColumnList.Table_Name).append(" ");
//			sql.append("WHERE ").append(I_FD_TableColumnList.COLUMNNAME_FD_TABLECOLUMN_ID).append(" = ").append(columnId);
//			DB.connection(env);
//			stmt = DB.createStatement();
//			rs = stmt.executeQuery(sql.toString());
//			List<String> list = new ArrayList<>();
//			while(rs.next()) {
//				list.add(rs.getString(I_FD_TableColumnList.COLUMNNAME_LIST_NAME));
//			}
//			if(list.size() > 0) {
//				strList = new String[list.size()];
//				for(int ix = 0; ix < list.size(); ix++) {
//					strList[ix] = list.get(ix);
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			DB.close(rs, stmt);
//		}
//		return strList;
//	}
//
//	/**
//	 * Table属性テキスト処理<br>
//	 * @author officine-hide.com
//	 * @since 2.11 2020/09/24
//	 * @param text 検索対象項目
//	 * @param item 項目情報
//	 * @return
//	 */
//	private EventHandler<KeyEvent> textEvents(TextField text, Fx_Item item) {
//		return new EventHandler<KeyEvent>() {
//			@Override
//			public void handle(KeyEvent event) {
//				//event.getCharacter().getBytes()[0]で13が改行
//				//改行キーが押された時に検索処理を行う。
//				if(event.getCharacter().getBytes()[0] == 13) {
//					searchData(item, text);
//				}
//			}
//		};
//	}
//
//	/**
//	 * 項目検索処理<br>
//	 * @author officine-hide.com
//	 * @since 2.11 2020/09/24
//	 * @param item 項目情報
//	 * @param text 項目
//	 * @param dispText 
//	 */
//	protected void searchData(Fx_Item item, TextField text) {
//		String TableName =  DB.getTableName(env, item.getViewItem().getIntOfValue(I_Fx_ViewItem.COLUMNNAME_SEARCH_TABLE_ID));
//		String searchColumnName = DB.getTableColumnName(env, item.getViewItem().getIntOfValue(I_Fx_ViewItem.COLUMNNAME_SEARCH_COLUMN_ID));
//		String dispColumnName = DB.getTableColumnName(env, item.getViewItem().getIntOfValue(I_Fx_ViewItem.COLUMNNAME_SEARCH_DISP_ID));
//		HBox tableBox = (HBox) item.getItemNode();
//		TextField dispText = (TextField) tableBox.getChildren().get(3);
//		Statement stmt = null;
//		ResultSet rs = null;
//		StringBuffer sql = new StringBuffer();
//		try {
//			sql.append("SELECT ").append(dispColumnName).append(" FROM ").append(TableName).append(" ");
//			sql.append("WHERE ")
//				.append(searchColumnName).append(" = ").append(I_DB.FD_SQ).append(text.getText()).append(I_DB.FD_SQ);
//			DB.connection(env);
//			stmt = DB.createStatement();
//			rs = stmt.executeQuery(sql.toString());
//			if(rs.next()) {
//				dispText.setText(rs.getString(dispColumnName));
//			} else {
//				// TODO 検索画面を表示して選択できるようにする。(2020/09/24 ueno)
//				dispText.setText("Error!! not found");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			DB.close(rs, stmt);
//		}
//	}

}
