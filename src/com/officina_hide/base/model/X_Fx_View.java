package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Item;

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
	 * 情報を返す保存する。<br>
	 * @author officina-hide.com ueno
	 * @since 2.00 2020/08/31
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}
	
}
