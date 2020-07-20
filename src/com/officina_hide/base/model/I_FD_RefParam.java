package com.officina_hide.base.model;

public interface I_FD_RefParam {
	/**
	 * テーブル名.<br>
	 */
	public final String Table_Name = "FD_RefParam";

	/**
	 * テーブルID.<br>
	 */
	public final int Table_ID =105;

	/**
	 * リファレンス用パラメータ情報ID.<br>
	 */
	public final String COLUMNNAME_FD_REFPARAM_ID = "FD_RefParam_ID";

	/**
	 * リファレンス情報ID.<br>
	 */
	public final String COLUMNNAME_FD_REFERENCE_ID = "FD_Reference_ID";

	/**
	 * パラメータ名.<br>
	 */
	public final String COLUMNNAME_PARAMETER_NAME = "Parameter_Name";

	/**
	 * パラメータ種別ID.<br>
	 */
	public final String COLUMNNAME_PARAMETER_TYPE_ID = "Parameter_Type_ID";

	/**
	 * パラメータ情報.<br>
	 */
	public final String COLUMNNAME_PARAMETER_DATA = "Parameter_Data";

	/**
	 * 説明.<br>
	 */
	public final String COLUMNNAME_FD_COMMENT = "FD_Comment";

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
