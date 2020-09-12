package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Item;

/**
 * テーブル項目IOクラス<br>
 * @author officine-hide.com ueno
 * @version 2.00
 * @since 2020/09/03
 */
public class X_FD_TableColumn extends FD_DB implements I_DB, I_FD_TableColumn {


	/**
	 * コンストラクター<br>
	 * <p>実体化時に、テーブル項目を初期化する。</p>
	 * @author officine-hide.com ueno
	 * @since 2.00 2020/09/03
	 * @param env 環境情報
	 */
	public X_FD_TableColumn(FD_EnvData env) {
		crearItemList();
	}

	/**
	 * 項目一覧リストをクリアな状態で生成する。<br>
	 * @author officine-hide.com ueno
	 * @since 2.00 2020/09/03
	 */
	private void crearItemList() {
		itemList.clear();
		itemList.add(new FD_Item(COLUMNNAME_FD_TABLECOLUMN_ID, null, COLUMN_TYPE_INFORMATION_ID));
		itemList.add(new FD_Item(COLUMNNAME_FD_TABLE_ID, null, COLUMN_TYPE_INFORMATION_ID));
		itemList.add(new FD_Item(COLUMNNAME_TABLECOLUMN_NAME, null, COLUMN_TYPE_TEXT));
		itemList.add(new FD_Item(COLUMNNAME_FD_NAME, null, COLUMN_TYPE_TEXT));
		itemList.add(new FD_Item(COLUMNNAME_FD_COMMENT, null, COLUMN_TYPE_FIELD_TEXT));
		itemList.add(new FD_Item(COLUMNNAME_TABLECOLUMN_TYPE_ID, null, COLUMN_TYPE_INFORMATION_ID));
		itemList.add(new FD_Item(COLUMNNAME_TABLECOLUMN_SIZE, null, COLUMN_TYPE_NUMBER));
		itemList.add(new FD_Item(COLUMNNAME_COLUMN_SORT_ORDER, null, COLUMN_TYPE_NUMBER));
		itemList.add(new FD_Item(COLUMNNAME_IS_NULL, null, COLUMN_TYPE_YESNO));
		itemList.add(new FD_Item(COLUMNNAME_IS_PRIMARY, null, COLUMN_TYPE_YESNO));
		itemList.add(new FD_Item(COLUMNNAME_FD_CREATE, null, COLUMN_TYPE_DATE));
		itemList.add(new FD_Item(COLUMNNAME_FD_CREATED, null, COLUMN_TYPE_INFORMATION_ID));
		itemList.add(new FD_Item(COLUMNNAME_FD_UPDATE, null, COLUMN_TYPE_DATE));
		itemList.add(new FD_Item(COLUMNNAME_FD_UPDATED, null, COLUMN_TYPE_INFORMATION_ID));
}

	/**
	 * 保存<br>
	 * @author officina-hide.com ueno
	 * @since 2.00 2020/09/04
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}
}