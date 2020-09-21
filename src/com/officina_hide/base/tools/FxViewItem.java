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
 * @version 2.00 新規作成
 * @since 2020/09/01
 */
public class FxViewItem extends FD_DB implements I_Fx_ViewItem {

	/**
	 * 画面項目情報テーブル生成<br>
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FDTable table = new FDTable();
		int tableId = table.addData(env, 0, Table_Name, Name);

		//テーブル項目情報登録
		FDTableColumn column = new FDTableColumn();
		column.add(env, tableId, COLUMNNAME_FX_VIEWITEM_ID, "画面項目情報ID", "画面項目を識別するための情報ID"
				, COLUMN_TYPE_INFORMATION_ID, 0, 10, I_FD_TableColumn.IS_PRIMARY_YES);
		column.add(env, tableId, COLUMNNAME_FX_VIEW_ID, "画面情報ID", "画面項目を紐づける画面の情報ID"
				, COLUMN_TYPE_INFORMATION_ID, 0, 20, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_VIEWITEM_NAME, "画面項目名", "画面項目の物理名"
				, COLUMN_TYPE_TEXT, 100, 30, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_FD_NAME, "画面項目論理名", "画面項目のラベルなどでの表示名"
				, COLUMN_TYPE_TEXT, 100, 40, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_VIEWITEM_TYPE_ID, "画面項目属性ID", "画面項目の属性を表すリファレンス情報ID"
				, COLUMN_TYPE_INFORMATION_ID, 0, 50, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_TABLECOLUMN_ID, NAME_TABLECOLUMN_ID, COMMENT_TABLECOLUMN_ID
				, COLUMN_TYPE_INFORMATION_ID, 0, 60, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_SEARCH_TABLE_ID, NAME_SEARCH_TABLE_ID, COMMENT_SEARCH_TABLE_ID
				, COLUMN_TYPE_INFORMATION_ID, 0, 70, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_SEARCH_COLUMN_ID, NAME_SEARCH_COLUMN_ID, COMMENT_SEARCH_COLUMN_ID
				, COLUMN_TYPE_INFORMATION_ID, 0, 80, I_FD_TableColumn.IS_PRIMARY_NO);
		
		column.add(env, tableId, COLUMNNAME_FD_CREATE, NAME_FD_CREATE, COMMENT_FD_CREATE
				, COLUMN_TYPE_DATE, 0, 910, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_FD_CREATED, NAME_FD_CREATED, COMMENT_FD_CREATED
				, COLUMN_TYPE_INFORMATION_ID, 0, 920, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_FD_UPDATE, NAME_FD_UPDATE, COMMENT_FD_UPDATE
				, COLUMN_TYPE_DATE, 0, 930, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_FD_UPDATED, NAME_FD_UPDATED, COMMENT_FD_UPDATED
				, COLUMN_TYPE_INFORMATION_ID, 0, 940, I_FD_TableColumn.IS_PRIMARY_NO);
		
		//テーブル生成
		createDBTable(env, Table_Name);
		
		//採番情報登録
		FDNumbering num = new FDNumbering();
		num.add(env, tableId, 1000001, 0);
		
		env.getLog().add(env, FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "画面項目情報テーブル構築");
	}

	/**
	 * 画面項目情報登録<br>
	 * @author officine-hide.com ueno
	 * @since 2.00 2020/09/01
	 * @param env 環境情報
	 * @param ViewID 画面情報ID
	 * @param ViewItemName 画面項目名
	 * @param name 画面項目表示名
	 * @param typeName 画面項目属性名
	 * @param columnId 画面項目ID 
	 * @return 画面項目情報ID
	 */
	public int addData(FD_EnvData env, int viewID, String viewItemName, String name, String typeName, int columnId) {
		X_Fx_ViewItem item = new X_Fx_ViewItem(env);
		int viewItemId = getNewID(env, getTableID(env, Table_Name));
		int typeId = getReferenceID(env, typeName);
		item.setValue(env, COLUMNNAME_FX_VIEWITEM_ID, viewItemId);
		item.setValue(env, COLUMNNAME_FX_VIEW_ID, viewID);
		item.setValue(env, COLUMNNAME_VIEWITEM_NAME, viewItemName);
		item.setValue(env, COLUMNNAME_FD_NAME, name);
		item.setValue(env, COLUMNNAME_VIEWITEM_TYPE_ID, typeId);
		item.setValue(env, COLUMNNAME_TABLECOLUMN_ID, columnId);
		item.save(env);
		
		return item.getIntOfValue(COLUMNNAME_FX_VIEWITEM_ID);
	}

}
