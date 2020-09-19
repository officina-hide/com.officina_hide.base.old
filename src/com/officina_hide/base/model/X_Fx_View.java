package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;
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
		createItemList(env, getTableID(env, Table_Name));
	}

	/**
	 * コンストラクター<br>
	 * <p>実体化時に、指定された条件を持つ画面情報を取得する。</p>
	 * @param env 環境情報
	 * @param where 抽出条件
	 */
	public X_Fx_View(FD_EnvData env, FD_WhereData where) {
		//項目リストセット
		createItemList(env, getTableID(env, Table_Name));
		//情報抽出
		load(env, where, Table_Name);
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
