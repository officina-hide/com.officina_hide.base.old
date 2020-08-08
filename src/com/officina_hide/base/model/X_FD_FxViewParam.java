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

public class X_FD_FxViewParam extends FD_DB implements I_DB, I_FD_FxViewParam {
	private FD_EnvData env;

	public X_FD_FxViewParam(FD_EnvData env) {
		this.env = env;
	}

	public X_FD_FxViewParam(FD_EnvData env, FD_WhereData where) {
		this.env = env;
		List<Integer> ids = getIds(env, where);
		if(ids.size() > 0) {
			load(env, ids.get(0));
		}
	}

	public X_FD_FxViewParam(FD_EnvData env, int id) {
		this.env = env;
		load(env, id);
	}

	/**
	 * Fx画面変数情報ID.<br>
	 */
	private int fD_FxViewParam_ID;
	/**
	 * Fx画面変数情報IDを取得する。.<br>
	 */
	public int getFD_FxViewParam_ID() {
		return fD_FxViewParam_ID;
	}
	/**
	 * Fx画面変数情報IDをセットする。.<br>
	 */
	public void setFD_FxViewParam_ID( int fD_FxViewParam_ID) {
		this.fD_FxViewParam_ID = fD_FxViewParam_ID;
	}
	/**
	 * Fx画面情報ID.<br>
	 */
	private int fD_FxView_ID;
	/**
	 * Fx画面情報IDを取得する。.<br>
	 */
	public int getFD_FxView_ID() {
		return fD_FxView_ID;
	}
	/**
	 * Fx画面情報IDをセットする。.<br>
	 */
	public void setFD_FxView_ID( int fD_FxView_ID) {
		this.fD_FxView_ID = fD_FxView_ID;
	}
	/**
	 * 画面変数名.<br>
	 */
	private String fxView_ParamName;
	/**
	 * 画面変数名を取得する。.<br>
	 */
	public String getFxView_ParamName() {
		return fxView_ParamName;
	}
	/**
	 * 画面変数名をセットする。.<br>
	 */
	public void setFxView_ParamName (String fxView_ParamName) {
		this.fxView_ParamName = fxView_ParamName;
	}
	/**
	 * 画面変数データ.<br>
	 */
	private String fxView_ParamData;
	/**
	 * 画面変数データを取得する。.<br>
	 */
	public String getFxView_ParamData() {
		return fxView_ParamData;
	}
	/**
	 * 画面変数データをセットする。.<br>
	 */
	public void setFxView_ParamData (String fxView_ParamData) {
		this.fxView_ParamData = fxView_ParamData;
	}
	/**
	 * 画面変数表示名.<br>
	 */
	private String fD_Name;
	/**
	 * 画面変数表示名を取得する。.<br>
	 */
	public String getFD_Name() {
		return fD_Name;
	}
	/**
	 * 画面変数表示名をセットする。.<br>
	 */
	public void setFD_Name (String fD_Name) {
		this.fD_Name = fD_Name;
	}
	/**
	 * 画面変数説明.<br>
	 */
	private String fD_Comment;
	/**
	 * 画面変数説明を取得する。.<br>
	 */
	public String getFD_Comment() {
		return fD_Comment;
	}
	/**
	 * 画面変数説明をセットする。.<br>
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
	/**
	 * FD_FxViewParamを保存する。.<br>
	 */
	public void save() {
		StringBuffer sql = new StringBuffer();
		boolean isNewData = false;
		if(getFD_FxViewParam_ID() == 0 ) {
			setFD_FxViewParam_ID(getNewID(env, "FD_FxViewParam"));
			isNewData = true;
		}
		if(isNewData) {
			sql.append("INSERT INTO ").append(I_FD_FxViewParam.Table_Name);
			getFD_Create().setTime(new Date());
			getFD_Update().setTime(new Date());
			setFD_Created(env.getLoginUserID());
			setFD_Updated(env.getLoginUserID());
		} else {
			sql.append("UPDATE ").append(I_FD_FxViewParam.Table_Name);
			getFD_Update().setTime(new Date());
			setFD_Updated(env.getLoginUserID());
		}
		sql.append(" SET ");
		sql.append(I_FD_FxViewParam.COLUMNNAME_FD_FXVIEWPARAM_ID).append(" = ").append(getFD_FxViewParam_ID()).append(",");
		sql.append(I_FD_FxViewParam.COLUMNNAME_FD_FXVIEW_ID).append(" = ").append(getFD_FxView_ID()).append(",");
		sql.append(I_FD_FxViewParam.COLUMNNAME_FXVIEW_PARAMNAME).append(" = '").append(getFxView_ParamName()).append("'").append(",");
		sql.append(I_FD_FxViewParam.COLUMNNAME_FXVIEW_PARAMDATA).append(" = '").append(getFxView_ParamData()).append("'").append(",");
		sql.append(I_FD_FxViewParam.COLUMNNAME_FD_NAME).append(" = '").append(getFD_Name()).append("'").append(",");
		sql.append(I_FD_FxViewParam.COLUMNNAME_FD_COMMENT).append(" = '").append(getFD_Comment()).append("'").append(",");
		sql.append(I_FD_FxViewParam.COLUMNNAME_FD_CREATE).append(" = '").append(dateFormat.format(getFD_Create().getTime())).append("'").append(",");
		sql.append(I_FD_FxViewParam.COLUMNNAME_FD_CREATED).append(" = ").append(getFD_Created()).append(",");
		sql.append(I_FD_FxViewParam.COLUMNNAME_FD_UPDATE).append(" = '").append(dateFormat.format(getFD_Update().getTime())).append("'").append(",");
		sql.append(I_FD_FxViewParam.COLUMNNAME_FD_UPDATED).append(" = ").append(getFD_Updated());
		if(isNewData == false) {
			sql.append(" WHERE ").append(I_FD_FxViewParam.COLUMNNAME_FD_FXVIEWPARAM_ID).append(" = ").append(getFD_FxViewParam_ID());
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
		sql.append("SELECT ").append(I_FD_FxViewParam.COLUMNNAME_FD_FXVIEWPARAM_ID).append(" FROM ").append(I_FD_FxViewParam.Table_Name);
		sql.append(" WHERE ").append(where.toString());
		if(order != null) {
			sql.append(" ORDER BY ").append(order.toString());
		}
		try {
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				ids.add(rs.getInt(I_FD_FxViewParam.COLUMNNAME_FD_FXVIEWPARAM_ID));
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
		sql.append(" WHERE ").append(COLUMNNAME_FD_FXVIEWPARAM_ID).append(" = ").append(id);
		try {
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			env.getLog().add(FD_Logging.TYPE_DB, FD_Logging.MODE_NORMAL, sql.toString());
			if(rs.next()) {
				setFD_FxViewParam_ID(rs.getInt(COLUMNNAME_FD_FXVIEWPARAM_ID));
				setFD_FxView_ID(rs.getInt(COLUMNNAME_FD_FXVIEW_ID));
				if(rs.getString(COLUMNNAME_FXVIEW_PARAMNAME) != null) {
					setFxView_ParamName(rs.getString(COLUMNNAME_FXVIEW_PARAMNAME));
				} else {
					setFxView_ParamName("");
				}
				if(rs.getString(COLUMNNAME_FXVIEW_PARAMDATA) != null) {
					setFxView_ParamData(rs.getString(COLUMNNAME_FXVIEW_PARAMDATA));
				} else {
					setFxView_ParamData("");
				}
				if(rs.getString(COLUMNNAME_FD_NAME) != null) {
					setFD_Name(rs.getString(COLUMNNAME_FD_NAME));
				} else {
					setFD_Name("");
				}
				if(rs.getString(COLUMNNAME_FD_COMMENT) != null) {
					setFD_Comment(rs.getString(COLUMNNAME_FD_COMMENT));
				} else {
					setFD_Comment("");
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

