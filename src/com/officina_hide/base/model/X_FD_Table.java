package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * テーブル情報IOクラス<br>
 * @author officina-hide.com
 * @version 2.00
 * @since 2020/08/29
 */
public class X_FD_Table extends FD_DB implements I_FD_Table {

	public X_FD_Table(FD_EnvData env) {
		
	}

	/** テーブル情報ID */
	private int fD_Table_ID;
	
	/** テーブル名 */
	private String tabl_name;

	/** テーブル表示名 */
	private String fD_Name;

	public int getfD_Table_ID() {
		return fD_Table_ID;
	}

	public void setfD_Table_ID(int fD_Table_ID) {
		this.fD_Table_ID = fD_Table_ID;
	}

	public String getTabl_name() {
		return tabl_name;
	}

	public void setTabl_name(String tabl_name) {
		this.tabl_name = tabl_name;
	}

	public String getfD_Name() {
		return fD_Name;
	}

	public void setfD_Name(String fD_Name) {
		this.fD_Name = fD_Name;
	}
}
