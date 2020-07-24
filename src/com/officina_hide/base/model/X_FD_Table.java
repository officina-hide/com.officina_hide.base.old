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

public class X_FD_Table extends FD_DB implements I_DB, I_FD_Table {
	private FD_EnvData env;

	public X_FD_Table(FD_EnvData env) {
		this.env = env;
	}

	public X_FD_Table(FD_EnvData env, FD_WhereData where) {
		this.env = env;
		List<Integer> ids = getIds(env, where);
		if(ids.size() > 0) {
			load(env, ids.get(0));
		}
	}

	public X_FD_Table(FD_EnvData env, int id) {
		this.env = env;
		load(env, id);
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
	/**
	 * FD_Tableを保存する。.<br>
	 */
	public void save() {
		StringBuffer sql = new StringBuffer();
		boolean isNewData = false;
		if(getFD_Table_ID() == 0 ) {
			setFD_Table_ID(getNewID(env, "FD_Table"));
			isNewData = true;
		}
		if(isNewData) {
			sql.append("INSERT INTO ").append(I_FD_Table.Table_Name);
			getFD_Create().setTime(new Date());
			getFD_Update().setTime(new Date());
			setFD_Created(env.getLoginUserID());
			setFD_Updated(env.getLoginUserID());
		} else {
			sql.append("UPDATE ").append(I_FD_Table.Table_Name);
			getFD_Update().setTime(new Date());
			setFD_Updated(env.getLoginUserID());
		}
		sql.append(" SET ");
		sql.append(I_FD_Table.COLUMNNAME_FD_TABLE_ID).append(" = ").append(getFD_Table_ID()).append(",");
		sql.append(I_FD_Table.COLUMNNAME_TABLE_NAME).append(" = '").append(getTable_Name()).append("'").append(",");
		sql.append(I_FD_Table.COLUMNNAME_FD_NAME).append(" = '").append(getFD_Name()).append("'").append(",");
		sql.append(I_FD_Table.COLUMNNAME_FD_COMMENT).append(" = '").append(getFD_COMMENT()).append("'").append(",");
		sql.append(I_FD_Table.COLUMNNAME_FD_CREATE).append(" = '").append(dateFormat.format(getFD_Create().getTime())).append("'").append(",");
		sql.append(I_FD_Table.COLUMNNAME_FD_CREATED).append(" = ").append(getFD_Created()).append(",");
		sql.append(I_FD_Table.COLUMNNAME_FD_UPDATE).append(" = '").append(dateFormat.format(getFD_Update().getTime())).append("'").append(",");
		sql.append(I_FD_Table.COLUMNNAME_FD_UPDATED).append(" = ").append(getFD_Updated());
		if(isNewData == false) {
			sql.append(" WHERE ").append(I_FD_Table.COLUMNNAME_FD_TABLE_ID).append(" = ").append(getFD_Table_ID());
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
		sql.append(" WHERE ").append(COLUMNNAME_FD_TABLE_ID).append(" = ").append(id);
		try {
			ResultSet rs = queryDB(env, sql.toString());
			if(rs.next()) {
			}
		} catch (SQLException e) {
			env.getLog().add(FD_Logging.TYPE_ERROR, FD_Logging.MODE_NORMAL, "SQL Execution Error !!");
		}
		return chk;
	}
}

