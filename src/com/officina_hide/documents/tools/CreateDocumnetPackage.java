package com.officina_hide.documents.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;

/**
 * ドキュメント管理基本構築<br>
 * <p>ドキュメント管理に必要なリソースを構築していく。<br>
 * Build the resources needed for document management.</p>
 * @author officina-hide.com
 * @version 1.00
 * @since 2020/09/12
 */
public class CreateDocumnetPackage {

	/**
	 * コンストラクター<br>
	 * <p>実体化時にドキュメント管理に必要なリソースの構築を行う。<br>
	 * Build the resources required for document management at the time of instance.</p>
	 * @param env
	 */
	public CreateDocumnetPackage(FD_EnvData env) {
		//開始メッセージ
		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "ドキュメント管理リソース構築開始");
		
		/*
		 * プロジェクト情報生成<br>
		 * TODO プロジェクト情報については、機能横断的な要素があるため、管理プロジェクトについては検討する事（2020/09/12 ueno)
		 */
		DDProject project = new DDProject();
		project.createTable(env);
		//ドキュメント情報生成
		DDDocument doc = new DDDocument();
		doc.createTable(env);
		
		/*
		 * システムプロジェクト登録<br>
		 * 本パッケージの開発に関するドキュメントを管理するためのプロジェクトを登録します。<br>
		 */
		int projectId = project.addData(env, "FDPackageProject", "パッケージ管理プロジェクト");
		doc.addData(env, projectId, "Overview", "システム概要", 10);
		
		//終了メッセージ
		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "ドキュメント管理リソース構築終了");
	}

}
