package com.officina_hide.projects.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;

/**
 * プロジェクト管理に関するパッケージを構築する。<br>
 * @author officine-hide.com
 * @version 2.12
 * @since 2020/09/28
 */
public class CreateProjectPackage {

	/**
	 * 実体化時に、プロジェクト管理に関するパッケージを構築する。<br>
	 * @author officine-hide.com
	 * @since 2.12 2020/09/29
	 * @param env 環境情報
	 */
	public CreateProjectPackage(FD_EnvData env) {
		//開始メッセージ
		env.getLog().add(env, FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "プロジェクト管理リソース構築開始");
		
		/*
		 * プロジェクト管理では、クライアント毎に複数の階層化されたプロジェクトを管理する。<br>
		 * 今回は、単一階層のプロジェクトに対して以下の機能を実現する<br>
		 * ・メモ
		 * ・ToDo管理
		 * ・作業管理
		 */
		//ToDo情報
		PJToDo todo = new PJToDo();
		todo.createTable(env);
		//ToDo画面情報登録
		FxToDoView ft = new FxToDoView();
		ft.createViewData(env);

		//終了メッセージ
		env.getLog().add(env, FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "プロジェクト管理リソース構築終了");
	}
}
