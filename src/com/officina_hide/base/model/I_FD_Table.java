package com.officina_hide.base.model;

/**
 * テーブル情報<br>
 * @author officina-hide.com ueno
 * @version 2.00
 * @since 2020/08/29
 */
public interface I_FD_Table {
	/** テーブル名.<br> */
	public final String Table_Name = "FD_Table";

	/**
	 * テーブルID.<br>
	 */
	public final int Table_ID =1002;

	/**
	 * テーブル情報ID.<br>
	 */
	public final String COLUMNNAME_FD_TABLE_ID = "FD_Table_ID";

	/**
	 * テーブル名.<br>
	 */
	public final String COLUMNNAME_TABLE_NAME = "Table_Name";

	/**
	 * テーブル表示名.<br>
	 */
	public final String COLUMNNAME_FD_NAME = "FD_Name";

	/**
	 * テーブル説明.<br>
	 */
	public final String COLUMNNAME_FD_COMMENT = "FD_Comment";

}
