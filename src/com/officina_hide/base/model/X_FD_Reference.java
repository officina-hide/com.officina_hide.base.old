package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.common.FD_WhereData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
			setFD_Reference_ID(getNewID(env, "FD_Reference"));
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
		sql.append(I_FD_Reference.COLUMNNAME_FD_REFERENCE_ID).append(" = ").append(getFD_Reference_ID()).append(",");
		sql.append(I_FD_Reference.COLUMNNAME_REFERENCE_NAME).append(" = '").append(getReference_Name()).append("'").append(",");
		sql.append(I_FD_Reference.COLUMNNAME_FD_CREATE).append(" = '").append(dateFormat.format(getFD_Create().getTime())).append("'").append(",");
		sql.append(I_FD_Reference.COLUMNNAME_FD_CREATED).append(" = ").append(getFD_Created()).append(",");
		sql.append(I_FD_Reference.COLUMNNAME_FD_UPDATE).append(" = '").append(dateFormat.format(getFD_Update().getTime())).append("'").append(",");
		sql.append(I_FD_Reference.COLUMNNAME_FD_UPDATED).append(" = ").append(getFD_Updated());
		if(isNewData == false) {
			sql.append(" WHERE ").append(I_FD_Reference.COLUMNNAME_FD_REFERENCE_ID).append(" = ").append(getFD_Reference_ID());
		}
		execute(env, sql.toString());
	}

	/**
	 * 条件文に該当する情報のIDリストを取得する。<br>.<br>
	 * @paramenv 環境情報
	 * @paramwhere 抽出条件
	 * @paramorder 並び順
	 */
	public List<Integer> getIds(FD_EnvData env, FD_WhereData where, FD_OrderData order) {
		List<Integer> ids = new ArrayList<Integer>();
		Statement stmt = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ").append(I_FD_Reference.COLUMNNAME_FD_REFERENCE_ID).append(" FROM ").append(I_FD_Reference.Table_Name);
		sql.append(" WHERE ").append(where.toString());
		if(order != null) {
			sql.append(" ORDER BY ").append(order.toString());
		}
		try {
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				ids.add(rs.getInt(I_FD_Reference.COLUMNNAME_FD_REFERENCE_ID));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, stmt);
		}
		return ids;
	}

	/**
	 * 条件文に該当する情報のIDリストを取得する。<br>.<br>
	 * @paramenv 環境情報
	 * @paramwhere 抽出条件
	 */
	public List<Integer> getIds(FD_EnvData env, FD_WhereData where) {
		return getIds(env, where, null);
	}

	/**
	 * 指定された情報IDを持つ情報を抽出する。<br>.<br>
	 */
	public boolean load(FD_EnvData env, int id) {
		boolean chk = false;
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM ").append(Table_Name);
		sql.append(" WHERE ").append(COLUMNNAME_FD_REFERENCE_ID).append(" = ").append(id);
		try {
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				setFD_Reference_ID(rs.getInt(COLUMNNAME_FD_REFERENCE_ID));
				if(rs.getString(COLUMNNAME_REFERENCE_NAME) != null) {
					setReference_Name(rs.getString(COLUMNNAME_REFERENCE_NAME));
				} else {
					setReference_Name("");
				}
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
		} finally {
			close(rs, stmt);
		}
		return chk;
	}
}

