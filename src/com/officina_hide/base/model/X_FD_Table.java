package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Item;
import com.officina_hide.base.common.FD_WhereData;

/**
 * テーブル情報IOクラス<br>
 * @author officina-hide.com
 * @version 2.00
 * @since 2020/08/29
 */
public class X_FD_Table extends FD_DB implements I_FD_Table {
	
	/**
	 * コンストラクター<br>
	 * <p>インスタンス時に項目を初夏設定する。</p>
	 * @author officina-hide.com ueno
	 * @since 2.00 2020/08/30
	 * @param env 環境情報
	 */
	public X_FD_Table(FD_EnvData env) {
		//項目リスト初期化
		// TODO テーブル項目情報から取得できるようにする。(2020/08/30 ueno)
		itemList.clear();
		itemList.add(new FD_Item(COLUMNNAME_FD_TABLE_ID, null, COLUMN_TYPE_INFORMATION_ID));
		itemList.add(new FD_Item(COLUMNNAME_TABLE_NAME, null, COLUMN_TYPE_TEXT));
		itemList.add(new FD_Item(COLUMNNAME_FD_NAME, null, COLUMN_TYPE_TEXT));
		itemList.add(new FD_Item(COLUMNNAME_FD_COMMENT, null, COLUMN_TYPE_FIELD_TEXT));
		itemList.add(new FD_Item(COLUMNNAME_FD_CREATE, null, COLUMN_TYPE_DATE));
		itemList.add(new FD_Item(COLUMNNAME_FD_CREATED, null, COLUMN_TYPE_INFORMATION_ID));
		itemList.add(new FD_Item(COLUMNNAME_FD_UPDATE, null, COLUMN_TYPE_DATE));
		itemList.add(new FD_Item(COLUMNNAME_FD_UPDATED, null, COLUMN_TYPE_INFORMATION_ID));
	}

	/**
	 * コンストラクター<br>
	 * <p>実体化時に、指定されたIDを持つ情報を抽出する。</p>
	 * @author officine-hide.com ueno
	 * @since 2.00 2020/09/10
	 * @param env 環境情報
	 * @param tableId　テーブル情報ID
	 */
	public X_FD_Table(FD_EnvData env, int tableId) {
		createItemList(env, Table_ID);
		FD_WhereData where = new FD_WhereData(COLUMNNAME_FD_TABLE_ID, tableId);
		load(env, where, Table_Name);
	}

	/**
	 * テーブル情報を初夏保存する。<br>
	 * @author officina-hide.com ueno
	 * @since 2.00 2020/08/30
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

}
