package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_DB;
import com.officina_hide.base.model.I_FD_TableColumn;
import com.officina_hide.base.model.X_FD_TableColumn;

/**
 * テーブル項目情報クラス<br>
 * @author officina-hide.com ueno
 * @version 2.00
 * @since 2020/09/01
 */
public class FDTableColumn extends FD_DB implements I_FD_TableColumn {

	/**
	 * テーブル項目情報テーブル生成<br>
	 * @author officina-hide.com ueno
	 * @since 2.00 2020/09/01
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		//既に登録されているテーブル項目情報を削除します。
		sql.append("DROP TABLE IF EXISTS FD_TableColumn");
		execute(env, sql.toString());
		//テーブル項目情報を生成する。
		sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS FD_TableColumn  (");
		sql.append("FD_TableColumn_ID INT UNSIGNED NOT NULL PRIMARY KEY COMMENT 'テーブル項目情報ID'").append(",");
		sql.append("FD_Table_ID INT UNSIGNED NOT NULL  COMMENT 'テーブル情報ID'").append(",");
		sql.append("TableColumn_Name Varchar(100) COMMENT 'テーブル項目名'").append(",");
		sql.append("FD_Name Varchar(100) COMMENT 'テーフル項目表示名'").append(",");
		sql.append("FD_COMMENT Varchar(3000) COMMENT 'テーブル項目説明'").append(",");
		sql.append("TableColumn_Type_ID INT UNSIGNED COMMENT 'テーブル項目属性ID'").append(",");
		sql.append("TableColumn_Size INT UNSIGNED COMMENT 'テーブル項目桁数'").append(",");
		sql.append("Column_Sort_Order INT UNSIGNED COMMENT '項目並び順'").append(",");
		sql.append("IS_Null INT COMMENT 'NULL必須判定'").append(",");
		sql.append("IS_Primary INT COMMENT 'プライマリーKey判定'").append(",");
		sql.append("FD_RefGroup_ID INT UNSIGNED COMMENT 'リファレンスグループ情報ID'").append(",");
		sql.append("FD_Create DATETIME  COMMENT '登録日'").append(",");
		sql.append("FD_Created INT UNSIGNED  COMMENT '登録者ID'").append(",");
		sql.append("FD_Update DATETIME  COMMENT '更新日'").append(",");
		sql.append("FD_Updated INT UNSIGNED  COMMENT '更新者ID'");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='テーブル項目情報'");
		execute(env, sql.toString());
		
		//テーブル情報登録
		FDTable table = new FDTable();
		table.addData(env, Table_ID, Table_Name, "テーブル項目情報");
		
		//採番情報登録<br>
		FDNumbering num = new FDNumbering();
		num.add(env, I_FD_TableColumn.Table_ID, 1000001, 0);

		//テーブル項目情報追加
		add(env, Table_ID, Table_Name+"_ID", "テーブル項目情報ID", "テーブル項目を識別するための情報ID"
				, I_DB.COLUMN_TYPE_INFORMATION_ID, 0	, 10, IS_PRIMARY_YES);
		add(env, Table_ID, COLUMNNAME_FD_TABLE_ID, "テーブル情報ID", "テーブル項目を紐付けるテーブルの情報ID"
				, COLUMN_TYPE_INFORMATION_ID, 0, 20, IS_PRIMARY_NO);
		add(env, Table_ID, COLUMNNAME_TABLECOLUMN_NAME, "テーブル項目名", "テーブル項目の物理名称"
				, COLUMN_TYPE_TEXT, 100	, 30, IS_PRIMARY_NO);
		add(env, Table_ID, COLUMNNAME_FD_NAME, "テーブル項目表示名", "テーブル項目の論理名称"
				, COLUMN_TYPE_TEXT, 100	, 40, IS_PRIMARY_NO);
		add(env, Table_ID, COLUMNNAME_FD_COMMENT, "テーブル項目説明", "テーブル項目の内容説明"
				, COLUMN_TYPE_FIELD_TEXT, 0	, 60, IS_PRIMARY_NO);
		add(env, Table_ID, COLUMNNAME_TABLECOLUMN_TYPE_ID, "テーブル項目属性ID", "テーブル項目の属性を表すリファレンス情報ID"
				, COLUMN_TYPE_INFORMATION_ID, 0	, 70, IS_PRIMARY_NO);
}

	/**
	 * テーブル項目情報登録<br>
	 * @param env 環境情報
	 * @param tableCoumnId テーブル項目情報ID
	 * @param tableId テーブル情報ID
	 * @param tableColumnName テーブル項目名
	 * @param name テーブル項目論理名
	 * @param comment テーブル項目説明
	 * @param type テーブル項目種別
	 * @param size テーブル項目桁数
	 * @param sortNo テーブル項目並び順
	 * @param isPKey プライマリキー判定
	 */
	public void add(FD_EnvData env, int tableId, String tableColumnName
			, String name, String comment, String type, int size, int sortNo, String isPKey) {
		X_FD_TableColumn column = new X_FD_TableColumn(env);
		column.setValue(COLUMNNAME_FD_TABLECOLUMN_ID, getNewID(env, Table_ID));
		column.setValue(COLUMNNAME_FD_TABLE_ID, tableId);
		column.setValue(COLUMNNAME_TABLECOLUMN_NAME, tableColumnName);
		column.setValue(COLUMNNAME_FD_NAME, name);
		column.setValue(COLUMNNAME_FD_COMMENT, comment);
		int typeId = getReferenceID(env, type);
		column.setValue(COLUMNNAME_TABLECOLUMN_TYPE_ID, typeId);
		column.setValue(COLUMNNAME_TABLECOLUMN_SIZE, size);
		column.setValue(COLUMNNAME_COLUMN_SORT_ORDER, sortNo);
		if(isPKey.equals(IS_PRIMARY_YES)) {
			column.setValue(COLUMNNAME_IS_PRIMARY, IS_PRIMARY_YES);
			column.setValue(COLUMNNAME_IS_NULL, IS_NULL_YES);
		} else {
			column.setValue(COLUMNNAME_IS_PRIMARY, IS_PRIMARY_NO);
			column.setValue(COLUMNNAME_IS_NULL, IS_NULL_NO);
		}
		column.save(env);
	}

}
