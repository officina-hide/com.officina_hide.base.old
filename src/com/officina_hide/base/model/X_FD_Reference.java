package com.officina_hide.base.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class X_FD_Reference extends FD_DB implements I_DB, I_FD_Reference {
	/**
	 * リファレンス情報ID.<br>
	 */
	private int fD_Reference_ID;
	/**
	 * リファレンス情報IDを取得する。.<br>
	 */
	public int getFD_Reference_ID() {
		return fD_Reference_ID;
	}
	/**
	 * リファレンス情報IDをセットする。.<br>
	 */
	public void setFD_Reference_ID( int fD_Reference_ID) {
		this.fD_Reference_ID = fD_Reference_ID;
	}
	/**
	 * リファレンス名.<br>
	 */
	private String reference_Name;
	/**
	 * リファレンス名を取得する。.<br>
	 */
	public String getReference_Name() {
		return reference_Name;
	}
	/**
	 * リファレンス名をセットする。.<br>
	 */
	public void setReference_Name (String reference_Name) {
		this.reference_Name = reference_Name;
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

