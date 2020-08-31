package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Item;

/**
 * 画面項目情報IOクラス<br>
 * @author officina-hide.com ueno
 * @version 2.00
 * @since 2020/09/01
 */
public class X_Fx_ViewItem extends FD_DB implements I_Fx_ViewItem {

	/**
	 * コンストラクター<br>
	 * <p>実体化時に、各項目を初期化する。</p>
	 * @author officine-hide.com ueno
	 * @since 2.00 2020/09/01
	 * @param env 環境情報
	 */
	public X_Fx_ViewItem(FD_EnvData env) {
		itemList.clear();
		itemList.add(new FD_Item(COLUMNNAME_FX_VIEWITEM_ID	, null, COLUMN_TYPE_INFORMATION_ID));
		itemList.add(new FD_Item(COLUMNNAME_FX_VIEW_ID, null, COLUMN_TYPE_INFORMATION_ID));
		itemList.add(new FD_Item(COLUMNNAME_VIEWITEM_NAME, null, COLUMN_TYPE_FIELD_TEXT));
		itemList.add(new FD_Item(COLUMNNAME_VIEWITEM_TYPE_ID, null, COLUMN_TYPE_INFORMATION_ID));
		itemList.add(new FD_Item(COLUMNNAME_FD_NAME, null, COLUMN_TYPE_TEXT));
		itemList.add(new FD_Item(COLUMNNAME_FD_CREATE, null, COLUMN_TYPE_DATE));
		itemList.add(new FD_Item(COLUMNNAME_FD_CREATED, null, COLUMN_TYPE_INFORMATION_ID));
		itemList.add(new FD_Item(COLUMNNAME_FD_UPDATE, null, COLUMN_TYPE_DATE));
		itemList.add(new FD_Item(COLUMNNAME_FD_UPDATED, null, COLUMN_TYPE_INFORMATION_ID));
	}

	/**
	 * 画面項目情報保存<br>
	 * @author officine-hide.com ueno
	 * @since 2.00 2020/09/01
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

}
