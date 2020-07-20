package com.officina_hide.base.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class X_FD_Numbering extends FD_DB implements I_DB, I_FD_Numbering {
	/**
	 * 採番情報ID.<br>
	 */
	private int fD_Numbering_ID;
	/**
	 * 採番情報IDを取得する。.<br>
	 */
	public int getFD_Numbering_ID() {
		return fD_Numbering_ID;
	}
	/**
	 * 採番情報IDをセットする。.<br>
	 */
	public void setFD_Numbering_ID( int fD_Numbering_ID) {
		this.fD_Numbering_ID = fD_Numbering_ID;
	}
	/**
	 * テーブル情報ID.<br>
	 */
	private int fD_Table_ID;
	/**
	 * テーブル情報IDを取得する。.<br>
	 */
	public int getFD_Table_ID() {
		return fD_Table_ID;
	}
	/**
	 * テーブル情報IDをセットする。.<br>
	 */
	public void setFD_Table_ID( int fD_Table_ID) {
		this.fD_Table_ID = fD_Table_ID;
	}
	/**
	 * 現在値.<br>
	 */
	private int current_Number;
	/**
	 * 現在値を取得する。.<br>
	 */
	public int getCurrent_Number() {
		return current_Number;
	}
	/**
	 * 現在値をセットする。.<br>
	 */
	public void setCurrent_Number( int current_Number) {
		this.current_Number = current_Number;
	}
	/**
	 * 開始値.<br>
	 */
	private int start_Number;
	/**
	 * 開始値を取得する。.<br>
	 */
	public int getStart_Number() {
		return start_Number;
	}
	/**
	 * 開始値をセットする。.<br>
	 */
	public void setStart_Number( int start_Number) {
		this.start_Number = start_Number;
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

