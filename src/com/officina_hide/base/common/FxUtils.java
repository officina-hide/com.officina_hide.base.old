package com.officina_hide.base.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.officina_hide.accounts.fx.Fx_Item;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_DB;
import com.officina_hide.base.model.I_FD_Reference;
import com.officina_hide.base.model.I_FD_TableColumn;
import com.officina_hide.base.model.I_FD_TableColumnList;
import com.officina_hide.base.model.I_Fx_ViewItem;
import com.officina_hide.base.model.X_FD_TableColumn;
import com.officina_hide.base.model.X_Fx_ViewItem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Fx画面共通クラス<br>
 * @author officine-hide.com
 * @version 2.12
 * @since 2020/10/02
 */
public class FxUtils {
	
	/** データベースIOクラス */
	FD_DB DB = new FD_DB();
	/** 画面項目リスト */
	private List<Fx_Item> fxItemList = new ArrayList<>();
	/** フォント:Meiryo UI 12Pt */
	Font fontMeiryo12 = new Font("Meiryo UI", 12);

	/**
	 * 画面項目生成<br>
	 * @author officine-hide.com
	 * @since 2.11 2020/09/19
	 * @param root ルート
	 */
	public void createItem(VBox root) {
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
	 * @param env 環境情報
	 * @param viewId 画面情報ID
	 */
	public void createFxItemList(FD_EnvData env, int viewId) {
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
				//画面項目情報
				item.setViewItem(new X_Fx_ViewItem(env, rs.getInt(I_Fx_ViewItem.COLUMNNAME_FX_VIEWITEM_ID)));
				//項目桁数取得
				X_FD_TableColumn column = null;
				if(item.getItemType().equals(I_Fx_ViewItem.VIEWTYPE_ID_FX_TABLE)) {
					column = new X_FD_TableColumn(env, item.getViewItem().getIntOfValue(I_Fx_ViewItem.COLUMNNAME_SEARCH_COLUMN_ID));
				} else {
					column = new X_FD_TableColumn(env, item.getViewItem().getIntOfValue(I_Fx_ViewItem.COLUMNNAME_TABLECOLUMN_ID));
				}
				item.setItemSize(column.getIntOfValue(I_FD_TableColumn.COLUMNNAME_TABLECOLUMN_SIZE));
				
				//ノードの設定
				switch(item.getItemType()) {
				case I_Fx_ViewItem.VIEWTYPE_ID_FX_TEXT:
					TextField fxText = new TextField();
					fxText.setStyle("-fx-font-family: Meiryo UI; -fx-font-size: 12px;");
					item.setItemNode(fxText);
					break;
				case I_Fx_ViewItem.VIEWTYPE_ID_FX_TEXTFIELD:
					TextArea textArea = new TextArea();
					textArea.setStyle("-fx-font-family: Meiryo UI; -fx-font-size: 12px;");
					textArea.setPrefColumnCount(item.getViewItem().getIntOfValue(I_Fx_ViewItem.COLUMNNAME_FIELD_LENGTH));
					textArea.setPrefRowCount(item.getViewItem().getIntOfValue(I_Fx_ViewItem.COLUMNNAME_FIELD_ROWS));
					item.setItemNode(textArea);
					break;
				case I_Fx_ViewItem.VIEWTYPE_ID_FX_DATE:
					DatePicker date = new DatePicker();
					date.setStyle("-fx-font-family: Meiryo UI; -fx-font-size: 12px;");
					date.setPrefWidth(110);
					item.setItemNode(date);
					break;
				case I_Fx_ViewItem.VIEWTYPE_ID_FX_TABLE:
					HBox TableBox = new HBox(5);
					TableBox.setAlignment(Pos.CENTER);
					TableBox.setStyle("-fx-font-family: Meiryo UI; -fx-font-size: 12px;");
					item.setItemNode(TableBox);
					TextField text = new TextField();
					text.addEventFilter(KeyEvent.KEY_TYPED,  textEvents(env, text, item));
					text.setPrefWidth(100);
					Button searchButton = new Button("?");
					Button clearButton = new Button("X");
					clearButton.setOnMouseClicked(event->{
						text.clear();
					});
					TextField dispText = new TextField();
					dispText.setDisable(true);
					searchButton.setOnMouseClicked(event->{
						searchData(env, item, text);
					});
					TableBox.getChildren().addAll(text, searchButton, clearButton, dispText);
					break;
				case I_Fx_ViewItem.VIEWTYPE_ID_FX_LIST:
					item.setItemNode(createListNode(env, item));
					break;
				}
				fxItemList.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * リスト項目ノート生成<br>
	 * @author officine-hide.com
	 * @since 2.11 2020/09/28
	 * @param env 環境情報
	 * @param item Fx項目情報
	 * @return リスト項目ノード
	 */
	private Node createListNode(FD_EnvData env, Fx_Item item) {
		HBox listBox = new HBox(5);
		
		//テーブル項目リスト情報からリストを取得する。
		String[] strList = getColumnList(env
				, item.getViewItem().getIntOfValue(I_Fx_ViewItem.COLUMNNAME_TABLECOLUMN_ID));
		ObservableList<String> list = FXCollections.observableArrayList(strList);

		ChoiceBox<String> selectBox = new ChoiceBox<>(list);
		listBox.getChildren().add(selectBox);
		return listBox;
	}

	/**
	 * リスト配列取得<br>
	 * @author officine-hide.com
	 * @since 2.12 2020/10/01
	 * @param env 環境情報
	 * @param columnId 対象テーブル項目情報ID
	 * @return リスト配列
	 */
	private String[] getColumnList(FD_EnvData env, int columnId) {
		String[] strList = null;
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT * FROM ").append(I_FD_TableColumnList.Table_Name).append(" ");
			sql.append("WHERE ").append(I_FD_TableColumnList.COLUMNNAME_FD_TABLECOLUMN_ID).append(" = ").append(columnId);
			DB.connection(env);
			stmt = DB.createStatement();
			rs = stmt.executeQuery(sql.toString());
			List<String> list = new ArrayList<>();
			while(rs.next()) {
				list.add(rs.getString(I_FD_TableColumnList.COLUMNNAME_LIST_NAME));
			}
			if(list.size() > 0) {
				strList = new String[list.size()];
				for(int ix = 0; ix < list.size(); ix++) {
					strList[ix] = list.get(ix);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs, stmt);
		}
		return strList;
	}

	/**
	 * Table属性テキスト処理<br>
	 * @author officine-hide.com
	 * @since 2.11 2020/09/24
	 * @param env 環境情報
	 * @param text 検索対象項目
	 * @param item 項目情報
	 * @return
	 */
	private EventHandler<KeyEvent> textEvents(FD_EnvData env, TextField text, Fx_Item item) {
		return new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				//event.getCharacter().getBytes()[0]で13が改行
				//改行キーが押された時に検索処理を行う。
				if(event.getCharacter().getBytes()[0] == 13) {
					searchData(env, item, text);
				}
			}
		};
	}

	/**
	 * 項目検索処理<br>
	 * @author officine-hide.com
	 * @since 2.11 2020/09/24
	 * @param env 環境情報
	 * @param item 項目情報
	 * @param text 項目
	 * @param dispText 
	 */
	protected void searchData(FD_EnvData env, Fx_Item item, TextField text) {
		String TableName =  DB.getTableName(env, item.getViewItem().getIntOfValue(I_Fx_ViewItem.COLUMNNAME_SEARCH_TABLE_ID));
		String searchColumnName = DB.getTableColumnName(env, item.getViewItem().getIntOfValue(I_Fx_ViewItem.COLUMNNAME_SEARCH_COLUMN_ID));
		String dispColumnName = DB.getTableColumnName(env, item.getViewItem().getIntOfValue(I_Fx_ViewItem.COLUMNNAME_SEARCH_DISP_ID));
		HBox tableBox = (HBox) item.getItemNode();
		TextField dispText = (TextField) tableBox.getChildren().get(3);
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT ").append(dispColumnName).append(" FROM ").append(TableName).append(" ");
			sql.append("WHERE ")
				.append(searchColumnName).append(" = ").append(I_DB.FD_SQ).append(text.getText()).append(I_DB.FD_SQ);
			DB.connection(env);
			stmt = DB.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				dispText.setText(rs.getString(dispColumnName));
			} else {
				// TODO 検索画面を表示して選択できるようにする。(2020/09/24 ueno)
				dispText.setText("Error!! not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs, stmt);
		}
	}

	public List<Fx_Item> getFxItemList() {
		return fxItemList;
	}

}
