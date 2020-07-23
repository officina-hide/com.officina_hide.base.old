package com.officina_hide.base.model;

public interface I_FD_Table {
	/**
	 * テーブル名.<br>
	 */
	public final String Table_Name = "FD_Table";

	/**
	 * テーブルID.<br>
	 */
	public final int Table_ID =101;

	/**
	 * テーブル情報ID.<br>
	 */
	public final String COLUMNNAME_FD_TABLE_ID = "FD_Table_ID";

	/**
	 * テーブル物理名.<br>
	 */
	public final String COLUMNNAME_TABLE_NAME = "Table_Name";

	/**
	 * テーブル論理名.<br>
	 */
	public final String COLUMNNAME_FD_NAME = "FD_Name";

	/**
	 * 説明.<br>
	 */
	public final String COLUMNNAME_FD_COMMENT = "FD_COMMENT";

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
