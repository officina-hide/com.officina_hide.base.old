package com.officina_hide.base.tools;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_DB;
import com.officina_hide.base.model.I_FD_RefParam;
import com.officina_hide.base.model.I_FD_Reference;
import com.officina_hide.base.model.I_FD_TableColumn;
import com.officina_hide.base.model.X_FD_Table;

/**
 * テーブル生成<br>
 * @author ueno hideo
 * @version 1.20
 * @since 2020/07/30
 */
public class CreateTable extends FD_DB implements I_DB {
	/**
	 * インポートリスト
	 */
	private List<String> importClassList = new ArrayList<String>();

	/**
	 * コンストラクタ－<br>
	 * @author ueno hideo
	 * @since 2020-04-28
	 * @param env 環境情報
	 * @param tableName テーブル名
	 * @param name テーブル論理名(COMMENT)
	 */
	public CreateTable(FD_EnvData env, int tableId) {
		StringBuffer sql = new StringBuffer();
		//テーブル情報取得
		X_FD_Table table = new X_FD_Table(env, tableId);
		//テーブル削除
		sql.append("DROP TABLE IF EXISTS ").append(table.getTable_Name());
		execute(env, sql.toString());
		//テーブル作成
		List<Map<String,String>> columns = getColumnData(env, tableId);
		sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS ").append(table.getTable_Name());
		sql.append("(");
		StringBuffer items = new StringBuffer();	//項目用SQL文
		for(Map<String, String> map : columns) {
			if(items.length() > 0) {
				items.append(", ");
			}
			try {
				Class<?> cl = Class.forName(map.get("Parameter_Data").toString());
				Constructor<?> con = cl.getConstructor(new Class[] {List.class});
				Object obj = con.newInstance(new Object[] {importClassList});
				Method method = cl.getMethod("toTableCreateSQL", Map.class);
				items.append(method.invoke(obj, map).toString());
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		sql.append(items.toString()).append(")");
		if(table.getFD_Name().length() > 0) {
			sql.append(" COMMENT ").append(FD_SQ).append(table.getFD_Name()).append(FD_SQ).append(" ");
		}
		execute(env, sql.toString());
		
		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "Table created : "+table.getTable_Name());
	}

	/**
	 * テーブル項目情報一覧取得<br>
	 * @param env 環境情報
	 * @param tableName テーブル名
	 * @return テーブル項目情報一覧
	 */
	private List<Map<String, String>> getColumnData(FD_EnvData env, int tableId) {
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		StringBuffer sql = new StringBuffer();
		ResultSet rs = null;
		Statement stmt = null;
		try {
			sql.append("SELECT * FROM FD_TableColumn ");
			sql.append("LEFT JOIN FD_Reference ON FD_Reference.FD_Reference_ID = FD_TableColumn.Column_Type_ID ");
			sql.append("LEFT JOIN FD_RefParam ON FD_RefParam.FD_Reference_ID = FD_Reference.FD_Reference_ID ")
				.append("AND FD_RefParam.Parameter_Type_ID = ").append(getReferenceID(env, "ClassName")).append(" ");
			sql.append("WHERE ").append(I_FD_TableColumn.COLUMNNAME_FD_TABLE_ID).append(" = ").append(tableId).append(" ");
			sql.append("ORDER BY Column_Sort_Order");
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				Map<String, String> map = new HashMap<String, String>();
				map.put(I_FD_TableColumn.COLUMNNAME_COLUMN_NAME, rs.getString(I_FD_TableColumn.COLUMNNAME_COLUMN_NAME));
				map.put(I_FD_RefParam.COLUMNNAME_PARAMETER_DATA, rs.getString(I_FD_RefParam.COLUMNNAME_PARAMETER_DATA));
				map.put(I_FD_Reference.COLUMNNAME_REFERENCE_NAME, rs.getString(I_FD_Reference.COLUMNNAME_REFERENCE_NAME));
				map.put(I_FD_TableColumn.COLUMNNAME_COLUMN_SIZE, rs.getString(I_FD_TableColumn.COLUMNNAME_COLUMN_SIZE));
				map.put(I_FD_TableColumn.COLUMNNAME_FD_NAME, rs.getString(I_FD_TableColumn.COLUMNNAME_FD_NAME));
				if(rs.getInt(I_FD_TableColumn.COLUMNNAME_PRIMARY_KEY_CHECK) == 1) {
					map.put(I_FD_TableColumn.COLUMNNAME_PRIMARY_KEY_CHECK, "YES");
				} else {
					map.put(I_FD_TableColumn.COLUMNNAME_PRIMARY_KEY_CHECK, "NO");
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, stmt);
		}
		return list;
	}

}
