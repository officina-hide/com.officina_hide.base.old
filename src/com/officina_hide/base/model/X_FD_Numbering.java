package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.common.FD_WhereData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class X_FD_Numbering extends FD_DB implements I_DB, I_FD_Numbering {
	private FD_EnvData env;

	public X_FD_Numbering(FD_EnvData env) {
		this.env = env;
	}

	public X_FD_Numbering(FD_EnvData env, FD_WhereData where) {
		this.env = env;
		List<Integer> ids = getIds(env, where);
		if(ids.size() > 0) {
			load(env, ids.get(0));
		}
	}

	public X_FD_Numbering(FD_EnvData env, int id) {
		this.env = env;
		load(env, id);
	}

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
	/**
	 * FD_Numberingを保存する。.<br>
	 */
	public void save() {
		StringBuffer sql = new StringBuffer();
		boolean isNewData = false;
		if(getFD_Numbering_ID() == 0 ) {
			setFD_Numbering_ID(getNewID(env, "FD_Numbering"));
			isNewData = true;
		}
		if(isNewData) {
			sql.append("INSERT INTO ").append(I_FD_Numbering.Table_Name);
			getFD_Create().setTime(new Date());
			getFD_Update().setTime(new Date());
			setFD_Created(env.getLoginUserID());
			setFD_Updated(env.getLoginUserID());
		} else {
			sql.append("UPDATE ").append(I_FD_Numbering.Table_Name);
			getFD_Update().setTime(new Date());
			setFD_Updated(env.getLoginUserID());
		}
		sql.append(" SET ");
		sql.append(I_FD_Numbering.COLUMNNAME_FD_NUMBERING_ID).append(" = ").append(getFD_Numbering_ID()).append(",");
		sql.append(I_FD_Numbering.COLUMNNAME_FD_TABLE_ID).append(" = ").append(getFD_Table_ID()).append(",");
		sql.append(I_FD_Numbering.COLUMNNAME_CURRENT_NUMBER).append(" = ").append(getCurrent_Number()).append(",");
		sql.append(I_FD_Numbering.COLUMNNAME_START_NUMBER).append(" = ").append(getStart_Number()).append(",");
		sql.append(I_FD_Numbering.COLUMNNAME_FD_CREATE).append(" = '").append(dateFormat.format(getFD_Create().getTime())).append("'").append(",");
		sql.append(I_FD_Numbering.COLUMNNAME_FD_CREATED).append(" = ").append(getFD_Created()).append(",");
		sql.append(I_FD_Numbering.COLUMNNAME_FD_UPDATE).append(" = '").append(dateFormat.format(getFD_Update().getTime())).append("'").append(",");
		sql.append(I_FD_Numbering.COLUMNNAME_FD_UPDATED).append(" = ").append(getFD_Updated());
		if(isNewData == false) {
			sql.append(" WHERE ").append(I_FD_Numbering.COLUMNNAME_FD_NUMBERING_ID).append(" = ").append(getFD_Numbering_ID());
		}
		execute(env, sql.toString());
	}

	/**
	 * 指定された情報IDを持つ情報を抽出する。<br>.<br>
	 */
	public boolean load(FD_EnvData env, int id) {
		boolean chk = false;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM ").append(Table_Name);
		sql.append(" WHERE ").append(COLUMNNAME_FD_NUMBERING_ID).append(" = ").append(id);
		try {
			ResultSet rs = queryDB(env, sql.toString());
			if(rs.next()) {
				setFD_Numbering_ID(rs.getInt(COLUMNNAME_FD_NUMBERING_ID));
				setFD_Table_ID(rs.getInt(COLUMNNAME_FD_TABLE_ID));
				setCurrent_Number(rs.getInt(COLUMNNAME_CURRENT_NUMBER));
				setStart_Number(rs.getInt(COLUMNNAME_START_NUMBER));
				if(rs.getDate(COLUMNNAME_FD_CREATE) != null) {
					getFD_Create().setTime(rs.getDate(COLUMNNAME_FD_CREATE));
				}
				setFD_Created(rs.getInt(COLUMNNAME_FD_CREATED));
				if(rs.getDate(COLUMNNAME_FD_UPDATE) != null) {
					getFD_Update().setTime(rs.getDate(COLUMNNAME_FD_UPDATE));
				}
				setFD_Updated(rs.getInt(COLUMNNAME_FD_UPDATED));
			}
		} catch (SQLException e) {
			env.getLog().add(FD_Logging.TYPE_ERROR, FD_Logging.MODE_NORMAL, "SQL Execution Error !!");
		}
		return chk;
	}
}

