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
import com.officina_hide.base.model.I_Fx_View;
import com.officina_hide.base.model.I_Fx_ViewItem;
import com.officina_hide.base.model.X_Fx_View;
import com.officina_hide.base.tools.FDProcess;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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
	FD_DB DB = new FD_DB();
	/** 画面項目リスト */
	List<Fx_Item> fxItemList = new ArrayList<>();

	
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
		for(Fx_Item item : fxItemList) {
			HBox row = new HBox(5);
			root.getChildren().add(row);
			//ラベル設定
			System.out.println(item.getItemName());
			Label label = new Label(item.getItemName());
			row.getChildren().add(label);
		}
	}

	/**
	 * 画面項目リスト生成<br>
	 * @author officine-hide.com
	 * @since 2.11 2020/09/19
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
			System.out.println(sql.toString());
			while(rs.next()) {
				Fx_Item item = new Fx_Item();
				item.setItemName(rs.getString(I_Fx_ViewItem.COLUMNNAME_FD_NAME));
				item.setItemType(rs.getString(I_FD_Reference.COLUMNNAME_REFERENCE_NAME));
				fxItemList.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
