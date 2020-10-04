package com.officina_hide.projects.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;

/**
 * ToDo情報IOクラス<br>
 * @author officine-hide.com
 * @version 2.12
 * @sinse 2.12 2020/10/03
 */
public class X_PJ_ToDo extends FD_DB implements I_PJ_ToDo {

	/**
	 * コンストラクター<br>
	 * <p>実体化時に、項目を初期化する。</p>
	 * @author officine-hide.com
	 * @since 2.12 2020/10/03
	 * @param env 環境情報
	 */
	public X_PJ_ToDo(FD_EnvData env) {
		createItemList(env, getTableID(env, Table_Name));
	}

	/**
	 * 情報保存<br>
	 * @author officine-hide.com
	 * @since 2.12 2020/10/05
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

}
