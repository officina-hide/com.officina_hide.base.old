package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_TableColumn;
import com.officina_hide.base.model.I_Fx_View;
import com.officina_hide.base.model.X_Fx_View;

/**
 * 画面情報管理クラス
 * @author officina-hide.com
 * @version 2.00
 * @since 2020/08/31
 */
public class FxView extends FD_DB implements I_Fx_View {

	/**
	 * 画面情報テーブル生成<br>
	 * @author officine-hide.com ueno
	 * @since 2.00 2020/08/31
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		//既に登録されている画面情報を削除する。
		sql.append("DROP TABLE IF EXISTS Fx_View");
		execute(env, sql.toString());
		//画面情報テーブルを生成する。
		sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS Fx_View  (");
		sql.append("Fx_View_ID INT UNSIGNED NOT NULL PRIMARY KEY COMMENT '画面情報ID'").append(",");
		sql.append("View_Name Varchar(100) COMMENT ").append(FD_SQ).append("画面名").append(FD_SQ).append(",");
		sql.append("FD_Name Varchar(100) COMMENT ").append(FD_SQ).append("画面表示名").append(FD_SQ).append(",");
//		sql.append("View_Pre_Width int COMMENT").append(FD_SQ).append("画面幅初期値").append(FD_SQ).append(",");
//		sql.append("View_Pre_Height int COMMENT").append(FD_SQ).append("画面高さ初期値").append(FD_SQ).append(",");
		sql.append("FD_Create DATETIME  COMMENT '登録日'").append(",");
		sql.append("FD_Created INT UNSIGNED  COMMENT '登録者ID'").append(",");
		sql.append("FD_Update DATETIME  COMMENT '更新日'").append(",");
		sql.append("FD_Updated INT UNSIGNED  COMMENT '更新者ID'");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='画面情報'");
		execute(env, sql.toString());
		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "画面情報構築");
		
		//テーブル情報登録
		FDTable table = new FDTable();
		table.addData(env, I_Fx_View.Table_ID, I_Fx_View.Table_Name, "画面情報");

		//テーブル項目情報登録
		FDTableColumn column = new FDTableColumn();
		column.add(env, Table_ID, COLUMNNAME_FX_VIEW_ID, "画面情報ID", "画面情報を識別するための情報ID"
				, COLUMN_TYPE_INFORMATION_ID, 0, 10, I_FD_TableColumn.IS_PRIMARY_YES);
		column.add(env, Table_ID, COLUMNNAME_VIEW_NAME, "画面名", "画面の物理名"
				, COLUMN_TYPE_TEXT, 100, 20, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, Table_ID, COLUMNNAME_FD_NAME, "画面表示名", "画面の論理名、画面タイトル等で使用される。"
				, COLUMN_TYPE_TEXT, 100, 30, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, Table_ID, COLUMNNAME_FD_CREATE, "登録日", "情報の登録日"
				, COLUMN_TYPE_DATE, 0, 900, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, Table_ID, COLUMNNAME_FD_CREATED, "登録者ID", "情報の登録者ID（ユーザー情報ID）"
				, COLUMN_TYPE_INFORMATION_ID, 0, 920, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, Table_ID, COLUMNNAME_FD_UPDATE, "更新日", "情報の更新日"
				, COLUMN_TYPE_DATE, 0, 900, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, Table_ID, COLUMNNAME_FD_UPDATED, "更新者ID", "情報の更新者ID（ユーザー情報ID）"
				, COLUMN_TYPE_INFORMATION_ID, 0, 920, I_FD_TableColumn.IS_PRIMARY_NO);
		
		//採番情報登録
		FDNumbering num = new FDNumbering();
		num.add(env, I_Fx_View.Table_ID, 1000001, 0);
	}

	/**
	 * 画面情報登録<br>
	 * @author officine-hide.com ueno
	 * @since 2.00 2020/08/25
	 * @param env 環境情報
	 * @param ViewName 画面名
	 * @param name 画面表示名
	 * @return 画面情報ID
	 */
	public int addData(FD_EnvData env, String ViewName, String name) {
		int viewId = getNewID(env, Table_ID);
		X_Fx_View view = new X_Fx_View(env);
		view.setValue(COLUMNNAME_FX_VIEW_ID, viewId);
		view.setValue(COLUMNNAME_VIEW_NAME, ViewName);
		view.setValue(COLUMNNAME_FD_NAME, name);
		view.save(env);
		
		return viewId;
	}

}
