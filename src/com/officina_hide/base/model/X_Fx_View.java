package com.officina_hide.base.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Item;
import com.officina_hide.base.common.FD_WhereData;

/**
 * 画面情報iOクラス<br>
 * @author officine-hide.com ueno
 * @version 2.00
 * @since 2020/08/25
 */
public class X_Fx_View extends FD_DB implements I_Fx_View {

	/**
	 * コンストラクター<br>
	 * <p>実体化時に各項目を初期化する。</p>
	 * @param env 環境情報
	 */
	public X_Fx_View(FD_EnvData env) {
		//項目リストセット
		setItemList(env);
	}

	/**
	 * コンストラクター<br>
	 * <p>実体化時に、指定された条件を持つ画面情報を取得する。</p>
	 * @param env 環境情報
	 * @param where 抽出条件
	 */
	public X_Fx_View(FD_EnvData env, FD_WhereData where) {
		//項目リストセット
		setItemList(env);
		//情報抽出
		load(env, Table_Name, where);
	}

	/**
	 * 項目リストセット<br>
	 * @author officine-hide.com ueno
	 * @since 2020/09/01
	 * @param env 環境情報
	 */
	private void setItemList(FD_EnvData env) {
		//項目リスト初期化
		// TODO テーブル項目情報から取得できるようにする。(2020/08/30 ueno)
		itemList.clear();
		itemList.add(new FD_Item(I_Fx_View.COLUMNNAME_FX_VIEW_ID, null, COLUMN_TYPE_INFORMATION_ID));
		itemList.add(new FD_Item(I_Fx_View.COLUMNNAME_VIEW_NAME, null, COLUMN_TYPE_FIELD_TEXT));
		itemList.add(new FD_Item(I_Fx_View.COLUMNNAME_FD_NAME, null, COLUMN_TYPE_FIELD_TEXT));
		itemList.add(new FD_Item(COLUMNNAME_FD_CREATE, null, COLUMN_TYPE_DATE));
		itemList.add(new FD_Item(COLUMNNAME_FD_CREATED, null, COLUMN_TYPE_INFORMATION_ID));
		itemList.add(new FD_Item(COLUMNNAME_FD_UPDATE, null, COLUMN_TYPE_DATE));
		itemList.add(new FD_Item(COLUMNNAME_FD_UPDATED, null, COLUMN_TYPE_INFORMATION_ID));
	}

	/**
	 * 情報取得<br>
	 * @author officine-hide.com ueno
	 * @since 2.00 2020/09/01
	 * @param env 環境情報
	 * @param tableName テーブル名
	 * @param where 抽出条件
	 */
	private void load(FD_EnvData env, String tableName, FD_WhereData where) {
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT * FROM ").append(Table_Name).append(" ");
			sql.append("WHERE ").append(where.toString());
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				for(FD_Item item : itemList) {
					switch(item.getItemType()) {
					case COLUMN_TYPE_INFORMATION_ID:
						setValue(item.getItemName(), rs.getInt(item.getItemName()));
						break;
					case COLUMN_TYPE_TEXT:
					case COLUMN_TYPE_FIELD_TEXT:
						setValue(item.getItemName(), rs.getString(item.getItemName()));
						break;
					case COLUMN_TYPE_DATE:
						setValue(item.getItemName(), rs.getDate(item.getItemName()));
						break;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 情報を返す保存する。<br>
	 * @author officina-hide.com ueno
	 * @since 2.00 2020/08/31
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}
	
}
