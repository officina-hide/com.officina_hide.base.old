package com.officina_hide.base.model;

public interface I_FD_Numbering {
	/**
	 * テーブル名.<br>
	 */
	public final String Table_Name = "FD_Numbering";

	/**
	 * テーブルID.<br>
	 */
	public final int Table_ID =104;

	/**
	 * 採番情報ID.<br>
	 */
	public final String COLUMNNAME_FD_NUMBERING_ID = "FD_Numbering_ID";

	/**
	 * テーブル情報ID.<br>
	 */
	public final String COLUMNNAME_FD_TABLE_ID = "FD_Table_ID";

	/**
	 * 現在値.<br>
	 */
	public final String COLUMNNAME_CURRENT_NUMBER = "Current_Number";

	/**
	 * 開始値.<br>
	 */
	public final String COLUMNNAME_START_NUMBER = "Start_Number";

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
