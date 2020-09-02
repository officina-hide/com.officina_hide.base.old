package com.officina_hide.fx.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_OrderData;
import com.officina_hide.base.model.I_Fx_View;
import com.officina_hide.base.model.I_Fx_ViewItem;
import com.officina_hide.base.model.X_Fx_View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
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
	
	@Override
	public void start(Stage stage) throws Exception {
		//環境情報取得
		FD_EnvData env = new FD_EnvData();
		//開始メッセージ
		env.getLog().open(env, "", FD_Logging.MODE_DEBAG);
		//画面情報取得
		FD_WhereData where = new FD_WhereData(I_Fx_View.COLUMNNAME_VIEW_NAME, VIEW_NAME);
		X_Fx_View view = new X_Fx_View(env, where);
		//画面項目情報取得
		getViewItem(env, view.getIntOfValue(I_Fx_View.COLUMNNAME_FX_VIEW_ID));
		
		VBox root = new VBox(5);
		
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle(view.getStringOfValue(I_Fx_View.COLUMNNAME_FD_NAME));
		stage.show();
	}

	/**
	 * 画面項目情報取得<br>
	 * @author officine-hide.com ueno
	 * @since 2.00 2020/09/01
	 * @param env 環境情報
	 * @param viewId 画面情報ID
	 */
	private void getViewItem(FD_EnvData env, int viewId) {
		FD_WhereData where = new FD_WhereData(I_Fx_ViewItem.COLUMNNAME_FX_VIEW_ID, viewId);
		List<Integer> ids = getIds(env, I_Fx_ViewItem.Table_Name, where, null);
		System.out.println(ids.size());
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
			System.out.println(sql.toString());
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
