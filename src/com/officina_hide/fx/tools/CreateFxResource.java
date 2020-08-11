package com.officina_hide.fx.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.common.I_FD_Base;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_FxView;
import com.officina_hide.base.model.I_FD_FxViewItem;
import com.officina_hide.base.model.I_FD_FxViewParam;
import com.officina_hide.base.model.I_FD_RefGroup;
import com.officina_hide.base.model.X_FD_FxView;
import com.officina_hide.base.model.X_FD_FxViewParam;
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
		//画面変数情報テーブル生成
		createViewParamTable(env);
		//画面項目情報テーブル生成
		createViewItemTable(env);
		//リファレンスグループ情報テーブル生成
		createRefGroupTable(env);
		//項目用リファレンス登録
		addReferenceData(env, "Fx_Text", I_FD_Base.GROUP_NAME_VIEW_ITEM);
		//ログイン画面情報登録
		int viewId = addFxViewData(env, "Fx_Login", "ログイン画面");
		addFxViewParam(env, viewId, "View_Pre_Width", 300);
		addFxViewParam(env, viewId, "View_Pre_Height", 200);
		//ログイン画面項目情報登録
	}

	/**
	 * リファレンスグループ情報テーブル生成<br>
	 * @author ueno hideo
	 * @since 2020/08/11
	 * @param env 環境情報
	 */
	private void createRefGroupTable(FD_EnvData env) {
		//テーブル情報登録
		int tableId = addTableData(env, 0, "FD_RefGroup", "リファレンスグループ情報", "リファレンス情報をグループ化するための情報");
		//テーフル項目登録
		addTableColumnData(env, tableId, "FD_RefGroup_ID", "情報ID", 0, "リファレンスグループ情報ID", "リファレンスグループ情報を識別するためのID", 10, true);
		addTableColumnData(env, tableId, "Reference_GroupName", "テキスト", 100, "リファレンスグループ名", "リファレンスグループの名称", 20, false);
		addTableColumnData(env, tableId, "FD_Create", "日時", 0, "登録日","リファレンスグループ情報の登録日", 900, false);
		addTableColumnData(env, tableId, "FD_Created", "情報ID", 0, "登録者ID","リファレンスグループ情報の登録者のID", 910, false);
		addTableColumnData(env, tableId, "FD_Update", "日時", 0, "更新日","リファレンスグループ情報の更新日", 920, false);
		addTableColumnData(env, tableId, "FD_Updated", "情報ID", 0, "更新者ID","リファレンスグループ情報の更新者のID", 930, false);
		//データベースIOモデル生成
		new CreateModel(env, "FD_RefGroup");
		//採番情報登録
		addNumberingData(env, tableId, 0, 1000001);
		//テーブル生成
		new CreateTable(env, I_FD_RefGroup.Table_ID);
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
		addNumberingData(env, tableId, 0, 1000001);
		//テーブル生成
		new CreateTable(env, I_FD_FxView.Table_ID);
	}

	/**
	 * 画面変数情報テーブル生成<br>
	 * <p>画面変数情報とは、画面情報に対して項目の追加・変更が柔軟的にできる変数情報を言います。</p>
	 * @param env 環境情報
	 */
	private void createViewParamTable(FD_EnvData env) {
		//テーブル情報登録
		int tableId = addTableData(env, 0, "FD_FxViewParam", "Fx画面変数情報", "Fx画面情報に付属して管理される変数情報");
		//テーブル項目情報登録
		addTableColumnData(env, tableId, "FD_FxViewParam_ID", "情報ID", 0, "Fx画面変数情報ID", "Fx画面変数情報を識別するためのID", 10, true);
		addTableColumnData(env, tableId, "FD_FxView_ID", "情報ID", 0, "Fx画面情報ID", "Fx画面変数情報が紐づく画面情報の識別ID", 20, false);
		addTableColumnData(env, tableId, "FxView_ParamName", "テキスト", 100, "画面変数名", "画面変数の名称", 30, false);
		addTableColumnData(env, tableId, "FxView_ParamData", "テキスト", 100, "画面変数データ", "画面変数が保持するデータ", 40, false);
		addTableColumnData(env, tableId, "FD_Name", "テキスト", 100, "画面変数表示名", "画面変数を表示する際の識別名", 50, false);
		addTableColumnData(env, tableId, "FD_Comment", "複数行テキスト", 0, "画面変数説明", "画面変数をの内容を説明する。", 60, false);
		addTableColumnData(env, tableId, "FD_Create", "日時", 0, "登録日","Fx画面変数情報の登録日", 900, false);
		addTableColumnData(env, tableId, "FD_Created", "情報ID", 0, "登録者ID","Fx画面変数情報の登録者のID", 910, false);
		addTableColumnData(env, tableId, "FD_Update", "日時", 0, "更新日","Fx画面変数情報の更新日", 920, false);
		addTableColumnData(env, tableId, "FD_Updated", "情報ID", 0, "更新者ID","Fx画面変数情報の更新者のID", 930, false);
		//データベースIOモデル生成
		new CreateModel(env, "FD_FxViewParam");
		//採番情報登録
		addNumberingData(env, tableId, 0, 1000001);
		//テーブル生成
		new CreateTable(env, I_FD_FxViewParam.Table_ID);
	}

	/**
	 * 画面項目情報テーブル生成<br>
	 * @author ueno hideo
	 * @since 2020/08/08
	 * @param env 環境情報
	 */
	private void createViewItemTable(FD_EnvData env) {
		//テーブル情報登録
		int tableId = addTableData(env, 0, "FD_FxViewItem", "Fx画面項目情報", "Fx画面情報で使用される項目情報");
		//テーブル項目情報登録
		addTableColumnData(env, tableId, "FD_FxViewItem_ID", "情報ID", 0, "Fx画面項目情報ID", "Fx画面項目情報を識別するためのID", 10, true);
		addTableColumnData(env, tableId, "FxViewItem_Name", "テキスト", 100, "画面項目名", "画面項目の名称", 20, false);
		addTableColumnData(env, tableId, "FxViewItem_Type_ID", "情報ID", 0, "画面項目属性", "画面項目の属性（リファレンスID）", 20, false);
		addTableColumnData(env, tableId, "FD_Create", "日時", 0, "登録日","Fx画面項目情報の登録日", 900, false);
		addTableColumnData(env, tableId, "FD_Created", "情報ID", 0, "登録者ID","Fx画面項目情報の登録者のID", 910, false);
		addTableColumnData(env, tableId, "FD_Update", "日時", 0, "更新日","Fx画面項目情報の更新日", 920, false);
		addTableColumnData(env, tableId, "FD_Updated", "情報ID", 0, "更新者ID","Fx画面項目情報の更新者のID", 930, false);
		//データベースIOモデル生成
		new CreateModel(env, "FD_FxViewItem");
		//採番情報登録
		addNumberingData(env, tableId, 0, 1000001);
		//テーブル生成
		new CreateTable(env, I_FD_FxViewItem.Table_ID);
	}

	/**
	 * 画面情報登録<br>
	 * @author ueno hideo
	 * @since 2020/08/02
	 * TODO 汎用クラス化予定(2020/08/02 ueno)
	 * @param env 環境情報
	 * @param viewName 画面名
	 * @param name　画面表示名
	 * @return Fx画面情報ID
	 */
	private int addFxViewData(FD_EnvData env, String viewName, String name) {
		X_FD_FxView view = new X_FD_FxView(env);
		view.setFxView_Name(viewName);
		view.setFD_Name(name);
		view.save();
		return view.getFD_FxView_ID();
	}

	/**
	 * 画面変数情報登録<br>
	 * @author ueno hideo
	 * @since 2020/08/05
	 * TODO 汎用クラス化予定(2020/08/05 ueno)
	 * @param env 環境情報
	 * @param viewId 画面情報ID
	 * @param paramName 変数名
	 * @param paramData(int) 変数情報
	 */
	private void addFxViewParam(FD_EnvData env, int viewId, String paramName, int paramData) {
		X_FD_FxViewParam param = new X_FD_FxViewParam(env);
		param.setFD_FxView_ID(viewId);
		param.setFxView_ParamName(paramName);
		param.setFxView_ParamData(Integer.toString(paramData));
		param.save();
	}

}
