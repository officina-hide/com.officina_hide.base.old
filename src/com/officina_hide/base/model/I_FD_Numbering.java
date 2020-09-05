package com.officina_hide.base.model;

public interface I_FD_Numbering {
	/** テーブル名 */
	public final String Table_Name = "FD_Numbering";

	/** テーブルID */
	public final int Table_ID =103;

	/** 採番情報ID */
	public final String COLUMNNAME_FD_NUMBERING_ID = "FD_Numbering_ID";
	/** テーブル情報ID */
	public final String COLUMNNAME_FD_TABLE_ID = "FD_Table_ID";
	/** 開始値 */
	public final String COLUMNNAME_START_NUMBER = "Start_Number";
	/** 現在値 */
	public final String COLUMNNAME_CURRENT_NUMBER = "Current_Number";
	
}
