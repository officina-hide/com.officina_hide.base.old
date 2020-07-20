package com.officina_hide.base.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class X_FD_Table extends FD_DB implements I_DB, I_FD_Table {
	/**
	 * テーブル情報ID.<br>
	 */
	private int oFN_Table_ID;
	/**
	 * テーブル情報IDを取得する。.<br>
	 */
	public int getOFN_Table_ID() {
		return oFN_Table_ID;
	}
	/**
	 * テーブル情報IDをセットする。.<br>
	 */
	public void setOFN_Table_ID( int oFN_Table_ID) {
		this.oFN_Table_ID = oFN_Table_ID;
	}
	/**
	 * テーブル物理名.<br>
	 */
	private String table_Name;
	/**
	 * テーブル物理名を取得する。.<br>
	 */
	public String getTable_Name() {
		return table_Name;
	}
	/**
	 * テーブル物理名をセットする。.<br>
	 */
	public void setTable_Name (String table_Name) {
		this.table_Name = table_Name;
	}
	/**
	 * テーブル論理名.<br>
	 */
	private String fD_Name;
	/**
	 * テーブル論理名を取得する。.<br>
	 */
	public String getFD_Name() {
		return fD_Name;
	}
	/**
	 * テーブル論理名をセットする。.<br>
	 */
	public void setFD_Name (String fD_Name) {
		this.fD_Name = fD_Name;
	}
	/**
	 * 説明.<br>
	 */
	private String fD_COMMENT;
	/**
	 * 説明を取得する。.<br>
	 */
	public String getFD_COMMENT() {
		return fD_COMMENT;
	}
	/**
	 * 説明をセットする。.<br>
	 */
	public void setFD_COMMENT (String fD_COMMENT) {
		this.fD_COMMENT = fD_COMMENT;
	}
	/**
	 * 登録日.<br>
	 */
	private Calendar fD_Create;
	/**
	 * 登録日を取得する。.<br>
	 */
	public Calendar getFD_Create() {
		if(fD_Create == null) {
			fD_Create = new GregorianCalendar(new Locale("ja", "JP"));
		}
		return fD_Create;
	}
	/**
	 * 登録日をセットする。.<br>
	 */
	public void setFD_Create(Calendar fD_Create) {
		this.fD_Create = fD_Create;
	}
	/**
	 * 登録者ID.<br>
	 */
	private int fD_Created;
	/**
	 * 登録者IDを取得する。.<br>
	 */
	public int getFD_Created() {
		return fD_Created;
	}
	/**
	 * 登録者IDをセットする。.<br>
	 */
	public void setFD_Created( int fD_Created) {
		this.fD_Created = fD_Created;
	}
	/**
	 * 更新日.<br>
	 */
	private Calendar fD_Update;
	/**
	 * 更新日を取得する。.<br>
	 */
	public Calendar getFD_Update() {
		if(fD_Update == null) {
			fD_Update = new GregorianCalendar(new Locale("ja", "JP"));
		}
		return fD_Update;
	}
	/**
	 * 更新日をセットする。.<br>
	 */
	public void setFD_Update(Calendar fD_Update) {
		this.fD_Update = fD_Update;
	}
	/**
	 * 更新者ID.<br>
	 */
	private int fD_Updated;
	/**
	 * 更新者IDを取得する。.<br>
	 */
	public int getFD_Updated() {
		return fD_Updated;
	}
	/**
	 * 更新者IDをセットする。.<br>
	 */
	public void setFD_Updated( int fD_Updated) {
		this.fD_Updated = fD_Updated;
	}
}

