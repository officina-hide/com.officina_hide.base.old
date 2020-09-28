package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Item;

/**
 * 採番情報IOクラス<br>
 * @author officina-hide.com ueno
 * @version 2.00
 * @since 2020/09/05
 */
public class X_FD_Numbering extends FD_DB implements I_DB, I_FD_Numbering {

	/**
	 * コンストラクタ<br>
	 * @author officina-hide.com ueno
	 * @since 2.00 2020/09/05
	 * @param env 環境情報
	 */
	public X_FD_Numbering(FD_EnvData env) {
		//項目初期化
		InitializeItemList();
	}

	/**
	 * 項目リスト初期化<br>
	 * @author officina-hide.com ueno
	 * @since 2020/09/05
	 */
	private void InitializeItemList() {
		itemList.clear();
		itemList.add(new FD_Item(COLUMNNAME_FD_NUMBERING_ID, null, COLUMN_TYPE_INFORMATION_ID));
		itemList.add(new FD_Item(COLUMNNAME_FD_TABLE_ID, null, COLUMN_TYPE_INFORMATION_ID));
		itemList.add(new FD_Item(COLUMNNAME_START_NUMBER, null, COLUMN_TYPE_NUMBER));
		itemList.add(new FD_Item(COLUMNNAME_CURRENT_NUMBER, null, COLUMN_TYPE_NUMBER));
		itemList.add(new FD_Item(COLUMNNAME_FD_CREATE, null, COLUMN_TYPE_DATE));
		itemList.add(new FD_Item(COLUMNNAME_FD_CREATED, null, COLUMN_TYPE_INFORMATION_ID));
		itemList.add(new FD_Item(COLUMNNAME_FD_UPDATE, null, COLUMN_TYPE_DATE));
		itemList.add(new FD_Item(COLUMNNAME_FD_UPDATED, null, COLUMN_TYPE_INFORMATION_ID));
	}

	/**
	 * 保存<br>
	 * @author officina-hide.com ueno
	 * @since 2.00 2020/09/05
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

}

