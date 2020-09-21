package com.officina_hide.accounts.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;

/**
 * 勘定科目IOクラス<br>
 * @author officina-hide.com
 * @version 2.11 新規作成
 * @sinse 2020/09/21
 */
public class X_AC_Acount_Code extends FD_DB implements I_AC_Acount_Code {

	/**
	 * コンストラクター<br>
	 * <p>実体化時に、項目の初期設定を行う。</p>
	 * @author officina-hide.com
	 * @sinse 2.11 2020/09/21
	 * @param env 環境情報
	 */
	public X_AC_Acount_Code(FD_EnvData env) {
		createItemList(env, getTableID(env, Table_Name));
	}

	/**
	 * コンストラクター<br>
	 * <p>実体化時に、項目の初期化と指定された情報IDを持つ情報の抽出を行う。</p>
	 * @author officina-hide.com
	 * @sinse 2.11 2020/09/21
	 * @param env 環境情報
	 * @param Id 情報ID
	 */
	public X_AC_Acount_Code(FD_EnvData env, int Id) {
		createItemList(env, getTableID(env, Table_Name));
		load(env, Table_Name, Id);
	}

	/**
	 * 情報保存<br>
	 * @author officina-hide.com
	 * @sinse 2.11 2020/09/21
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

}
