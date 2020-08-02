package com.officina_hide.fx.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_FxView;
import com.officina_hide.base.model.X_FD_FxView;
import com.officina_hide.base.tools.CreateModel;
import com.officina_hide.base.tools.CreateTable;

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
		//ログイン画面情報登録
		addFxViewData(env, "Fx_View", "ログイン画面");
	}

	/**
	 * 画面情報テーブル生成
	 * @param env 環境情報
	 */
	private void createViewTable(FD_EnvData env) {
		//テーブル情報登録
		int tableId = addTableData(env, 0, "FD_FxView", "Fx画面情報", "Fx画面の定義と管理対象のテーブルを紐づける。");
		//テーブル項目登録
		addTableColumnData(env, tableId, "FD_FxView_ID", "情報ID", 0, "画面情報ID", "Fx画面情報を識別するためのID", 10, true);
		addTableColumnData(env, tableId, "FxView_Name", "テキスト", 100, "画面名", "Fx画面の名前", 20, false);
		addTableColumnData(env, tableId, "FD_Name", "テキスト", 100, "画面表示名","Fx画面の呼称（表示名）", 30, false);
		addTableColumnData(env, tableId, "FD_Create", "日時", 0, "登録日","Fx画面情報の登録日", 900, false);
		addTableColumnData(env, tableId, "FD_Created", "情報ID", 0, "登録者ID","Fx画面情報の登録者のID", 910, false);
		addTableColumnData(env, tableId, "FD_Update", "日時", 0, "更新日","Fx画面情報の更新日", 920, false);
		addTableColumnData(env, tableId, "FD_Updated", "情報ID", 0, "更新者ID","Fx画面情報の更新者のID", 930, false);
		//データベースIOモデル生成
		new CreateModel(env, "FD_FxView");
		//採番情報登録
		addNumberingData(env, 0, tableId, 0, 1000001);
		//テーブル生成
		new CreateTable(env, I_FD_FxView.Table_ID);
	}

	/**
	 * 画面情報登録<br>
	 * @author ueno hideo
	 * @since 2020/08/02
	 * TODO 汎用クラス化予定(2020/08/02 ueno)
	 * @param env 環境情報
	 * @param viewName 画面名
	 * @param name　画面表示名
	 */
	private void addFxViewData(FD_EnvData env, String viewName, String name) {
		X_FD_FxView view = new X_FD_FxView(env);
		view.setFxView_Name(viewName);
		view.setFD_Name(name);
		view.save();
	}

}
