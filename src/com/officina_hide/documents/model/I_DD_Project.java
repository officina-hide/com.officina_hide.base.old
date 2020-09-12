package com.officina_hide.documents.model;

public interface I_DD_Project {

	/** プロジェクト情報 : テーブル名 */
	public final String Table_Name = "DD_Project";
	
	/** プロジェクト情報ID */
	public final String COLUMNNAME_DD_PROJECT_ID = Table_Name+"_ID";
	/** プロジェクト名 */
	public final String COLUMNNAME_PROJECT_NAME = "Project_Name";
	/** プロジェクト表示名 */
	public final String COLUMNNAME_FD_NAME = "FD_Name";
}
