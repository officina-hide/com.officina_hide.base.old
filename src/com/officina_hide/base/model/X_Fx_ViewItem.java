package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

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
		createItemList(env, getTableID(env, Table_Name));
	}

	/**
	 * コンストラクター<br>
	 * <p>実体化時に、指定されたIDを持つ情報を抽出する。</p>
	 * @param env 環境情報
	 * @param id 情報ID
	 */
	public X_Fx_ViewItem(FD_EnvData env, int id) {
		createItemList(env, getTableID(env, Table_Name));
		load(env, Table_Name, id);
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
