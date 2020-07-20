package com.officina_hide.base.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class X_FD_RefParam extends FD_DB implements I_DB, I_FD_RefParam {
	/**
	 * リファレンス用パラメータ情報ID.<br>
	 */
	private int fD_RefParam_ID;
	/**
	 * リファレンス用パラメータ情報IDを取得する。.<br>
	 */
	public int getFD_RefParam_ID() {
		return fD_RefParam_ID;
	}
	/**
	 * リファレンス用パラメータ情報IDをセットする。.<br>
	 */
	public void setFD_RefParam_ID( int fD_RefParam_ID) {
		this.fD_RefParam_ID = fD_RefParam_ID;
	}
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
	 * パラメータ名.<br>
	 */
	private String parameter_Name;
	/**
	 * パラメータ名を取得する。.<br>
	 */
	public String getParameter_Name() {
		return parameter_Name;
	}
	/**
	 * パラメータ名をセットする。.<br>
	 */
	public void setParameter_Name (String parameter_Name) {
		this.parameter_Name = parameter_Name;
	}
	/**
	 * パラメータ種別ID.<br>
	 */
	private int parameter_Type_ID;
	/**
	 * パラメータ種別IDを取得する。.<br>
	 */
	public int getParameter_Type_ID() {
		return parameter_Type_ID;
	}
	/**
	 * パラメータ種別IDをセットする。.<br>
	 */
	public void setParameter_Type_ID( int parameter_Type_ID) {
		this.parameter_Type_ID = parameter_Type_ID;
	}
	/**
	 * パラメータ情報.<br>
	 */
	private String parameter_Data;
	/**
	 * パラメータ情報を取得する。.<br>
	 */
	public String getParameter_Data() {
		return parameter_Data;
	}
	/**
	 * パラメータ情報をセットする。.<br>
	 */
	public void setParameter_Data (String parameter_Data) {
		this.parameter_Data = parameter_Data;
	}
	/**
	 * 説明.<br>
	 */
	private String fD_Comment;
	/**
	 * 説明を取得する。.<br>
	 */
	public String getFD_Comment() {
		return fD_Comment;
	}
	/**
	 * 説明をセットする。.<br>
	 */
	public void setFD_Comment (String fD_Comment) {
		this.fD_Comment = fD_Comment;
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

