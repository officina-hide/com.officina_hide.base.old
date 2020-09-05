package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_TableColumn;
import com.officina_hide.base.model.I_Fx_ViewItem;
import com.officina_hide.base.model.X_Fx_ViewItem;

/**
 * 画面項目情報クラス<br>
 * @author officina-hide.com ueno
 * @version 2.00
 * @since 2020/09/01
 */
public class FxViewItem extends FD_DB implements I_Fx_ViewItem {

	/**
	 * 画面項目情報テーブル生成<br>
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		//既に登録されている 画面項目情報を削除します。
		sql.append("DROP TABLE IF EXISTS ").append(Table_Name);
		execute(env, sql.toString());
		//画面項目情報を生成する。
		sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS Fx_ViewItem  (");
		sql.append(COLUMNNAME_FX_VIEWITEM_ID).append(" INT UNSIGNED NOT NULL PRIMARY KEY COMMENT ")
			.append(FD_SQ).append("画面項目情報ID").append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FX_VIEW_ID).append(" INT UNSIGNED NOT NULL COMMENT ")
			.append(FD_SQ).append("画面情報ID").append(FD_SQ).append(",");
		sql.append(COLUMNNAME_VIEWITEM_NAME).append(" Varchar(100) COMMENT ")
			.append(FD_SQ).append("画面項目名").append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_NAME).append(" Varchar(100) COMMENT ")
			.append(FD_SQ).append("画面項目表示名").append(FD_SQ).append(",");
		sql.append(COLUMNNAME_VIEWITEM_TYPE_ID).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append("画面項目属性ID").append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_CREATE).append(" DATETIME  COMMENT ").append(FD_SQ).append("登録日").append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_CREATED).append(" INT UNSIGNED COMMENT ").append(FD_SQ).append("登録者ID").append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_UPDATE).append(" DATETIME  COMMENT ").append(FD_SQ).append("更新日").append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_UPDATED).append(" INT UNSIGNED COMMENT ").append(FD_SQ).append("更新者ID").append(FD_SQ);
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='テーブル項目情報'");
		execute(env, sql.toString());
		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "画面項目情報テーブル構築");
		
		//テーブル情報登録
		FDTable table = new FDTable();
		table.addData(env, I_Fx_ViewItem.Table_ID, I_Fx_ViewItem.Table_Name, "画面項目情報");

		//テーブル項目情報登録
		FDTableColumn column = new FDTableColumn();
		column.add(env, Table_ID, COLUMNNAME_FX_VIEWITEM_ID, "画面項目情報ID", "画面項目を識別するための情報ID"
				, COLUMN_TYPE_INFORMATION_ID, 0, 10, I_FD_TableColumn.IS_PRIMARY_YES);
		column.add(env, Table_ID, COLUMNNAME_FX_VIEW_ID, "画面情報ID", "画面項目を紐づける画面の情報ID"
				, COLUMN_TYPE_INFORMATION_ID, 0, 20, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, Table_ID, COLUMNNAME_VIEWITEM_NAME, "画面項目名", "画面項目の物理名"
				, COLUMN_TYPE_TEXT, 100, 30, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, Table_ID, COLUMNNAME_FD_NAME, "画面項目論理名", "画面項目のラベルなどでの表示名"
				, COLUMN_TYPE_TEXT, 100, 40, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, Table_ID, COLUMNNAME_VIEWITEM_TYPE_ID, "画面項目属性ID", "画面項目の属性を表すリファレンス情報ID"
				, COLUMN_TYPE_INFORMATION_ID, 0, 50, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, Table_ID, COLUMNNAME_FD_CREATE, "登録日", "情報の登録日"
				, COLUMN_TYPE_DATE, 0, 910, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, Table_ID, COLUMNNAME_FD_CREATED, "登録者ID", "情報の登録者の情報ID"
				, COLUMN_TYPE_INFORMATION_ID, 0, 920, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, Table_ID, COLUMNNAME_FD_UPDATE, "更新日", "情報の更新日"
				, COLUMN_TYPE_DATE, 0, 930, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, Table_ID, COLUMNNAME_FD_UPDATED, "更新者ID", "情報の更新者の情報ID"
				, COLUMN_TYPE_INFORMATION_ID, 0, 940, I_FD_TableColumn.IS_PRIMARY_NO);
		
	}

	/**
	 * 画面項目情報登録<br>
	 * @author officine-hide.com ueno
	 * @since 2.00 2020/09/01
	 * @param env 環境情報
	 * @param ViewID 画面情報ID
	 * @param ViewItemName 画面項目名
	 * @param name 
	 * @param ViewTypeID 画面項目属性ID
	 */
	public void addData(FD_EnvData env, int viewID, String viewItemName, String name, int viewTypeID) {
		X_Fx_ViewItem item = new X_Fx_ViewItem(env);
		int viewItemId = getNewID(env, Table_ID);
		item.setValue(COLUMNNAME_FX_VIEWITEM_ID, viewItemId);
		item.setValue(COLUMNNAME_FX_VIEW_ID, viewID);
		item.setValue(COLUMNNAME_VIEWITEM_NAME, viewItemName);
		item.setValue(COLUMNNAME_FD_NAME, name);
		item.setValue(COLUMNNAME_VIEWITEM_TYPE_ID, viewTypeID);
		item.save(env);
	}

}
