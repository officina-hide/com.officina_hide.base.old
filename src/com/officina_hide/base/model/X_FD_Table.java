package com.officina_hide.base.model;

import java.util.Calendar;

public class X_FD_Table extends FD_DB implements I_DB, I_FD_Table {
	/**
	 * テーブル情報ID.<br>
	 */
	private int oFN_Table_ID;
	/**
	 * テーブル物理名.<br>
	 */
	private String table_Name;
	/**
	 * テーブル論理名.<br>
	 */
	private String fD_Name;
	/**
	 * 説明.<br>
	 */
	private String fD_COMMENT;
	/**
	 * 登録日.<br>
	 */
	private Calendar fD_Create;
	/**
	 * 登録者ID.<br>
	 */
	private int fD_Created;
	/**
	 * 更新日.<br>
	 */
	private Calendar fD_Update;
	/**
	 * 更新者ID.<br>
	 */
	private int fD_Updated;
}

