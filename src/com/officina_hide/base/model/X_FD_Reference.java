package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class X_FD_Reference extends FD_DB implements I_DB, I_FD_Reference {
	private FD_EnvData env;

	public X_FD_Reference(FD_EnvData env) {
		this.env = env;
	}

	public X_FD_Reference(FD_EnvData env, FD_WhereData where) {
		this.env = env;
		List<Integer> ids = getIds(env, where);
		if(ids.size() > 0) {
			load(env, ids.get(0));
		}
	}

	public X_FD_Reference(FD_EnvData env, int id) {
		this.env = env;
		load(env, id);
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
	/**
	 * FD_Referenceを保存する。.<br>
	 */
	public void save() {
		StringBuffer sql = new StringBuffer();
		boolean isNewData = false;
		if(getFD_Reference_ID() == 0 ) {
			setFD_Reference_ID(getNewID(env, getTableID(env, "FD_Reference")));
			isNewData = true;
		}
		if(isNewData) {
			sql.append("INSERT INTO ").append(I_FD_Reference.Table_Name);
			getFD_Create().setTime(new Date());
			getFD_Update().setTime(new Date());
			setFD_Created(env.getLoginUserID());
			setFD_Updated(env.getLoginUserID());
		} else {
			sql.append("UPDATE ").append(I_FD_Reference.Table_Name);
			getFD_Update().setTime(new Date());
			setFD_Updated(env.getLoginUserID());
		}
		sql.append(" SET ");
;
		if(isNewData == false) {
			sql.append(" WHERE ").append(I_FD_Reference.COLUMNNAME_FD_REFERENCE_ID).append(" = ").append(getFD_Reference_ID());
		}
		execute(env, sql.toString());
	}

}

