package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.model.FD_DB;

/**
 * 画面リソース生成<br>
 * @author ueno hideo
 * @version 1.20
 * @since 2020/07/28
 */
public class CreateFxResource extends FD_DB {

	/**
	 * コンストラクタ<br>
	 * @author ueno hideo
	 * @since 1.20 2020/07/28
	 * @param env 環境情報
	 */
	public CreateFxResource(FD_EnvData env) {
		//生成開始メッセージ
		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "画面リソース生成開始");
		//画面情報テーブル生成
		createViewTable(env);
	}

	/**
	 * 画面情報テーブル生成
	 * @param env 環境情報
	 */
	private void createViewTable(FD_EnvData env) {
		//テーブル情報登録
		addTableData(env, 0, "FD_Fx_View", "Fx画面情報", "Fx画面の定義と管理対象のテーブルを紐づける。");
		//テーブル項目登録
		
	}

}
