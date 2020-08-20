package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.common.FD_WhereData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
;
		if(isNewData == false) {
			sql.append(" WHERE ").append(I_FD_Table.COLUMNNAME_FD_TABLE_ID).append(" = ").append(getFD_Table_ID());
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
		sql.append("SELECT ").append(I_FD_Table.COLUMNNAME_FD_TABLE_ID).append(" FROM ").append(I_FD_Table.Table_Name);
		sql.append(" WHERE ").append(where.toString());
		if(order != null) {
			sql.append(" ORDER BY ").append(order.toString());
		}
		try {
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				ids.add(rs.getInt(I_FD_Table.COLUMNNAME_FD_TABLE_ID));
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
		sql.append(" WHERE ").append(COLUMNNAME_FD_TABLE_ID).append(" = ").append(id);
		try {
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			env.getLog().add(FD_Logging.TYPE_DB, FD_Logging.MODE_NORMAL, sql.toString());
			if(rs.next()) {
			}
		} catch (SQLException e) {
			env.getLog().add(FD_Logging.TYPE_ERROR, FD_Logging.MODE_NORMAL, "SQL Execution Error !!");
		} finally {
			close(rs, stmt);
		}
		return chk;
	}
}

