package com.officina_hide.documents.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.tools.FDTable;
import com.officina_hide.base.tools.FDTableColumn;

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
		
		DDProject projct = new DDProject();
		FDTable table = new FDTable();
		table.addData(env, 0, "DD_Project", "プロジェクト情報");
		FDTableColumn column = new FDTableColumn();
	}

}
