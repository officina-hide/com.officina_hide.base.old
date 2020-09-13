package com.officina_hide.documents.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;

/**
 * ドキュメント情報IOクラス<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/09/12
 */
public class X_DD_Document extends FD_DB implements I_DD_Document {

	/**
	 * コンストラクター<br>
	 * 実体化時に、項目の初期化を行う。<br>
	 * @param env 環境情報
	 */
	public X_DD_Document(FD_EnvData env) {
		createItemList(env, getTableID(env, Table_Name));
	}

	/**
	 * 保存<br>
	 * @author officine-hide.com
	 * @since 2020/09/12
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}
	
}
