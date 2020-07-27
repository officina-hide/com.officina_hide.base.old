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

public class X_FD_TableColumn extends FD_DB implements I_DB, I_FD_TableColumn {
	private FD_EnvData env;

	public X_FD_TableColumn(FD_EnvData env) {
		this.env = env;
	}

	public X_FD_TableColumn(FD_EnvData env, FD_WhereData where) {
		this.env = env;
		List<Integer> ids = getIds(env, where);
		if(ids.size() > 0) {
			load(env, ids.get(0));
		}
	}

	public X_FD_TableColumn(FD_EnvData env, int id) {
		this.env = env;
		load(env, id);
	}

	/**
	 * テーブル項目情報ID.<br>
	 */
	private int fD_TableColumn_ID;
	/**
	 * テーブル項目情報IDを取得する。.<br>
	 */
	public int getFD_TableColumn_ID() {
		return fD_TableColumn_ID;
	}
	/**
	 * テーブル項目情報IDをセットする。.<br>
	 */
	public void setFD_TableColumn_ID( int fD_TableColumn_ID) {
		this.fD_TableColumn_ID = fD_TableColumn_ID;
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
	 * テーブル項目物理名.<br>
	 */
	private String column_Name;
	/**
	 * テーブル項目物理名を取得する。.<br>
	 */
	public String getColumn_Name() {
		return column_Name;
	}
	/**
	 * テーブル項目物理名をセットする。.<br>
	 */
	public void setColumn_Name (String column_Name) {
		this.column_Name = column_Name;
	}
	/**
	 * 種別ID（リファレンス情報ID）.<br>
	 */
	private int column_Type_ID;
	/**
	 * 種別ID（リファレンス情報ID）を取得する。.<br>
	 */
	public int getColumn_Type_ID() {
		return column_Type_ID;
	}
	/**
	 * 種別ID（リファレンス情報ID）をセットする。.<br>
	 */
	public void setColumn_Type_ID( int column_Type_ID) {
		this.column_Type_ID = column_Type_ID;
	}
	/**
	 * 桁数.<br>
	 */
	private int column_Size;
	/**
	 * 桁数を取得する。.<br>
	 */
	public int getColumn_Size() {
		return column_Size;
	}
	/**
	 * 桁数をセットする。.<br>
	 */
	public void setColumn_Size( int column_Size) {
		this.column_Size = column_Size;
	}
	/**
	 * 物理名.<br>
	 */
	private String fD_Name;
	/**
	 * 物理名を取得する。.<br>
	 */
	public String getFD_Name() {
		return fD_Name;
	}
	/**
	 * 物理名をセットする。.<br>
	 */
	public void setFD_Name (String fD_Name) {
		this.fD_Name = fD_Name;
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
	 * プライマリーキー判定.<br>
	 */
	private boolean primary_Key_Check;
	/**
	 * プライマリーキー判定を判定する。.<br>
	 */
	public boolean isPrimary_Key_Check() {
		return primary_Key_Check;
	}
	/**
	 * プライマリーキー判定をセットする。.<br>
	 */
	public void setPrimary_Key_Check( boolean primary_Key_Check) {
		this.primary_Key_Check = primary_Key_Check;
	}
	/**
	 * 項目並び順.<br>
	 */
	private int column_Sort_Order;
	/**
	 * 項目並び順を取得する。.<br>
	 */
	public int getColumn_Sort_Order() {
		return column_Sort_Order;
	}
	/**
	 * 項目並び順をセットする。.<br>
	 */
	public void setColumn_Sort_Order( int column_Sort_Order) {
		this.column_Sort_Order = column_Sort_Order;
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
	 * FD_TableColumnを保存する。.<br>
	 */
	public void save() {
		StringBuffer sql = new StringBuffer();
		boolean isNewData = false;
		if(getFD_TableColumn_ID() == 0 ) {
			setFD_TableColumn_ID(getNewID(env, "FD_TableColumn"));
			isNewData = true;
		}
		if(isNewData) {
			sql.append("INSERT INTO ").append(I_FD_TableColumn.Table_Name);
			getFD_Create().setTime(new Date());
			getFD_Update().setTime(new Date());
			setFD_Created(env.getLoginUserID());
			setFD_Updated(env.getLoginUserID());
		} else {
			sql.append("UPDATE ").append(I_FD_TableColumn.Table_Name);
			getFD_Update().setTime(new Date());
			setFD_Updated(env.getLoginUserID());
		}
		sql.append(" SET ");
		sql.append(I_FD_TableColumn.COLUMNNAME_FD_TABLECOLUMN_ID).append(" = ").append(getFD_TableColumn_ID()).append(",");
		sql.append(I_FD_TableColumn.COLUMNNAME_FD_TABLE_ID).append(" = ").append(getFD_Table_ID()).append(",");
		sql.append(I_FD_TableColumn.COLUMNNAME_COLUMN_NAME).append(" = '").append(getColumn_Name()).append("'").append(",");
		sql.append(I_FD_TableColumn.COLUMNNAME_COLUMN_TYPE_ID).append(" = ").append(getColumn_Type_ID()).append(",");
		sql.append(I_FD_TableColumn.COLUMNNAME_COLUMN_SIZE).append(" = ").append(getColumn_Size()).append(",");
		sql.append(I_FD_TableColumn.COLUMNNAME_FD_NAME).append(" = '").append(getFD_Name()).append("'").append(",");
		sql.append(I_FD_TableColumn.COLUMNNAME_FD_COMMENT).append(" = '").append(getFD_Comment()).append("'").append(",");
		if(isPrimary_Key_Check() == true) {
			sql.append(I_FD_TableColumn.COLUMNNAME_PRIMARY_KEY_CHECK).append(" = 1");
		} else {
			sql.append(I_FD_TableColumn.COLUMNNAME_PRIMARY_KEY_CHECK).append(" = 0");
		}
		sql.append(",");
		sql.append(I_FD_TableColumn.COLUMNNAME_COLUMN_SORT_ORDER).append(" = ").append(getColumn_Sort_Order()).append(",");
		sql.append(I_FD_TableColumn.COLUMNNAME_FD_CREATE).append(" = '").append(dateFormat.format(getFD_Create().getTime())).append("'").append(",");
		sql.append(I_FD_TableColumn.COLUMNNAME_FD_CREATED).append(" = ").append(getFD_Created()).append(",");
		sql.append(I_FD_TableColumn.COLUMNNAME_FD_UPDATE).append(" = '").append(dateFormat.format(getFD_Update().getTime())).append("'").append(",");
		sql.append(I_FD_TableColumn.COLUMNNAME_FD_UPDATED).append(" = ").append(getFD_Updated());
		if(isNewData == false) {
			sql.append(" WHERE ").append(I_FD_TableColumn.COLUMNNAME_FD_TABLECOLUMN_ID).append(" = ").append(getFD_TableColumn_ID());
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
		sql.append("SELECT ").append(I_FD_TableColumn.COLUMNNAME_FD_TABLECOLUMN_ID).append(" FROM ").append(I_FD_TableColumn.Table_Name);
		sql.append(" WHERE ").append(where.toString());
		if(order != null) {
			sql.append(" ORDER BY ").append(order.toString());
		}
		try {
			connection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				ids.add(rs.getInt(I_FD_TableColumn.COLUMNNAME_FD_TABLECOLUMN_ID));
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
		sql.append(" WHERE ").append(COLUMNNAME_FD_TABLECOLUMN_ID).append(" = ").append(id);
		try {
			connection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				setFD_TableColumn_ID(rs.getInt(COLUMNNAME_FD_TABLECOLUMN_ID));
				setFD_Table_ID(rs.getInt(COLUMNNAME_FD_TABLE_ID));
				if(rs.getString(COLUMNNAME_COLUMN_NAME) != null) {
					setColumn_Name(rs.getString(COLUMNNAME_COLUMN_NAME));
				} else {
					setColumn_Name("");
				}
				setColumn_Type_ID(rs.getInt(COLUMNNAME_COLUMN_TYPE_ID));
				setColumn_Size(rs.getInt(COLUMNNAME_COLUMN_SIZE));
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
				if(rs.getInt(COLUMNNAME_PRIMARY_KEY_CHECK) != 0) {
					setPrimary_Key_Check(true);
				} else {
					setPrimary_Key_Check(false);
				}
				setColumn_Sort_Order(rs.getInt(COLUMNNAME_COLUMN_SORT_ORDER));
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

