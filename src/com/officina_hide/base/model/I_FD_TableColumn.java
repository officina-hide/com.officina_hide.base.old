package com.officina_hide.base.model;

public interface I_FD_TableColumn {
	/** テーブル名 */
	public final String Table_Name = "FD_TableColumn";

	/** テーブルID */
	public final int Table_ID =1003;

	/** テーブル項目情報ID　*/
	public final String COLUMNNAME_FD_TABLECOLUMN_ID = "FD_TableColumn_ID";

	/** テーブル情報ID */
	public final String COLUMNNAME_FD_TABLE_ID = "FD_Table_ID";

	/** テーブル項目名 */
	public final String COLUMNNAME_TABLECOLUMN_NAME = "TableColumn_Name";

	/** テーブル表示名 */
	public final String COLUMNNAME_FD_NAME = "FD_Name";

	/** 説明 */
	public final String COLUMNNAME_FD_COMMENT = "FD_COMMENT";

	/** テーブル項目属性ID　*/
	public final String COLUMNNAME_TABLECOLUMN_TYPE_ID = "TableColumn_Type_ID";

	/** テーブル項目桁数 */
	public final String COLUMNNAME_TABLECOLUMN_SIZE = "TableColumn_Size";

	/** テーブル項目並び順  */
	public final String COLUMNNAME_COLUMN_SORT_ORDER = "Column_Sort_Order";

	/** null必須判定 */
	public final String COLUMNNAME_IS_NULL = "IS_Null";
	public final String IS_NULL_YES = "YES";
	public final String IS_NULL_NO = "NO";

	/** Primaryキー判定 */
	public final String COLUMNNAME_IS_PRIMARY = "IS_Primary";
	public static final String IS_PRIMARY_YES = "YES";
	public static final String IS_PRIMARY_NO = "NO";

//
//	/**
//	 * 登録日.<br>
//	 */
//	public final String COLUMNNAME_FD_CREATE = "FD_Create";
//
//	/**
//	 * 登録者ID.<br>
//	 */
//	public final String COLUMNNAME_FD_CREATED = "FD_Created";
//
//	/**
//	 * 更新日.<br>
//	 */
//	public final String COLUMNNAME_FD_UPDATE = "FD_Update";
//
//	/**
//	 * 更新者ID.<br>
//	 */
//	public final String COLUMNNAME_FD_UPDATED = "FD_Updated";

}
