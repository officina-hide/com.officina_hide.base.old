package com.officina_hide.base.model;

public interface I_FD_Reference {
	/**
	 * テーブル名.<br>
	 */
	public final String Table_Name = "FD_Reference";

	/**
	 * テーブルID.<br>
	 */
	public final int Table_ID =103;

	/**
	 * リファレンス情報ID.<br>
	 */
	public final String COLUMNNAME_FD_REFERENCE_ID = "FD_Reference_ID";

	/**
	 * リファレンス名.<br>
	 */
	public final String COLUMNNAME_REFERENCE_NAME = "Reference_Name";

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
