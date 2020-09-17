package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * ログ情報IOクラス<br>
 * @author officine-hide.com
 * @version 2.11 新規作成
 * @since 2020/09/17 
 */
public class X_FD_Log extends FD_DB implements I_FD_Log {

	/**
	 * コンストラクター<br>
	 * <p>実体化時に、項目を初期化する。</p>
	 * @author officine-hide.com
	 * @since 2.11 2020/09/17
	 * @param env 環境情報
	 */
	public X_FD_Log(FD_EnvData env) {
		createItemList(env, getTableID(env, Table_Name));
	}

}
