package com.officina_hide.base.model;

public interface I_FD_TableColumn {
	/**
	 * テーブル名.<br>
	 */
	public final String Table_Name = "FD_TableColumn";

	/**
	 * テーブルID.<br>
	 */
	public final int Table_ID =102;

	/**
	 * テーブル項目情報ID.<br>
	 */
	public final String COLUMNNAME_FD_TABLECOLUMN_ID = "FD_TableColumn_ID";

	/**
	 * テーブル情報ID.<br>
	 */
	public final String COLUMNNAME_FD_TABLE_ID = "FD_Table_ID";

	/**
	 * テーブル項目物理名.<br>
	 */
	public final String COLUMNNAME_COLUMN_NAME = "Column_Name";

	/**
	 * 種別ID（リファレンス情報ID）.<br>
	 */
	public final String COLUMNNAME_COLUMN_TYPE_ID = "Column_Type_ID";

	/**
	 * 桁数.<br>
	 */
	public final String COLUMNNAME_COLUMN_SIZE = "Column_Size";

	/**
	 * 物理名.<br>
	 */
	public final String COLUMNNAME_FD_NAME = "FD_Name";

	/**
	 * 説明.<br>
	 */
	public final String COLUMNNAME_FD_COMMENT = "FD_Comment";

	/**
	 * プライマリーキー判定.<br>
	 */
	public final String COLUMNNAME_PRIMARY_KEY_CHECK = "Primary_Key_Check";

	/**
	 * 項目並び順.<br>
	 */
	public final String COLUMNNAME_COLUMN_SORT_ORDER = "Column_Sort_Order";

	/**
	 * 登録日.<br>
	 */
	public final String COLUMNNAME_FD_CREATE = "FD_Create";

	/**
	 * 登録者ID.<br>
	 */
	public final String COLUMNNAME_FD_CREATED = "FD_Created";

	/**
	 * 更新日.<br>
	 */
	public final String COLUMNNAME_FD_UPDATE = "FD_Update";

	/**
	 * 更新者ID.<br>
	 */
	public final String COLUMNNAME_FD_UPDATED = "FD_Updated";

}
