package com.officina_hide.documents.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.documents.model.I_DD_Project;

/**
 * プロジェクト情報IOクラス<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/09/12
 */
public class X_DD_Project extends FD_DB implements I_DD_Project {

	/**
	 * コンストラクター<br>
	 * <p>実体化時に、項目一覧を初期化する。</p>
	 * @author officine-hide.com
	 * @since 1.00 2020/09/12
	 * @param env 環境情報
	 */
	public X_DD_Project(FD_EnvData env) {
		createItemList(env, getTableID(env, Table_Name));
	}

	/**
	 * 情報登録<br>
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

}
