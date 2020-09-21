package com.officina_hide.accounts.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;

/**
 * ドキュメント管理基本機能構築<br>
 * @author officine-hide.com
 * @version 2.11 新規作成
 * @since 2020/09/12
 */
public class CreateAccountPackage {

	/**
	 * コンストラクター<br>
	 * <p>実体化時に、会計管理に必要なリソースり構築を行う。</p>
	 * @author officine-hide.com
	 * @since 1.00 2020/09/12
	 * @param env 環境情報
	 */
	public CreateAccountPackage(FD_EnvData env) {
		//開始メッセージ
		env.getLog().add(env, FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "会計管理リソース構築開始");
		
		/*
		 * 金銭出納帳情報は、クライアント単位に構築する。<br>
		 * クライアント情報はbaseの管理下に有る。
		 */
		ACCashBook cash = new ACCashBook();
		cash.createTable(env);
		ACAcountCode code = new ACAcountCode();
		code.createTable(env);
		addAcountCode(env);

		FxCashBookView cb = new FxCashBookView();
		cb.createViewData(env);

		//終了メッセージ
		env.getLog().add(env, FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "会計管理リソース構築終了");
	}

	/**
	 * 勘定科目コード登録<br>
	 * @author officina-hide.com
	 * @sinse 2.11 2020/09/20
	 * @param env 環境情報
	 */
	private void addAcountCode(FD_EnvData env) {
		ACAcountCode code = new ACAcountCode();
		code.addData(env, "1111", "現金");
		code.addData(env, "1112", "当座預金");
		code.addData(env, "1113", "普通預金");
	}

}
