package com.officina_hide.documents.tools;

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
import com.officina_hide.base.model.I_FD_Reference;
import com.officina_hide.base.model.I_FD_TableColumn;
import com.officina_hide.base.model.X_FD_Table;
import com.officina_hide.base.tools.FDTable;
import com.officina_hide.base.tools.FDTableColumn;
import com.officina_hide.documents.model.I_DD_Project;

/**
 * プロジェクト情報クラス<br>
 * @author officina-hide.com
 * @version 1.00
 * @sinse 2020/09/12
 */
public class DDProject extends FD_DB implements I_DD_Project {

	/**
	 * プロジェクト情報テーブル生成
	 * @author officina-hide.com
	 * @sinse 1.00 2020/09/12
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FDTable table = new FDTable();
		int tableId = table.addData(env, 0, I_DD_Project.Table_Name, "プロジェクト情報");
		
		//テーブル項目情報登録
		FDTableColumn column = new FDTableColumn();
		column.add(env, tableId, COLUMNNAME_DD_PROJECT_ID, "プロジェクト情報ID", "プロジェクトを識別する為の情報ID"
				, COLUMN_TYPE_INFORMATION_ID, 0, 10, I_FD_TableColumn.IS_PRIMARY_YES);
		column.add(env, tableId, COLUMNNAME_PROJECT_NAME, "プロジェクト名", "プロジェクトの物理名称"
				, COLUMN_TYPE_TEXT, 100, 20, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_FD_NAME, "プロジェクト表示名", "プロジェクトの論理名"
				, COLUMN_TYPE_TEXT, 100, 30, I_FD_TableColumn.IS_PRIMARY_NO);
		
		column.add(env, tableId, COLUMNNAME_FD_CREATE, "登録日", "情報の登録日"
				, COLUMN_TYPE_DATE, 0, 900, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_FD_CREATED, "登録者ID", "情報の登録者ID（ユーザー情報ID）"
				, COLUMN_TYPE_INFORMATION_ID, 0, 910, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_FD_UPDATE, "更新日", "情報の更新日"
				, COLUMN_TYPE_DATE, 0, 920, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_FD_UPDATED, "更新者ID", "情報の更新者ID（ユーザー情報ID）"
				, COLUMN_TYPE_INFORMATION_ID, 0, 930, I_FD_TableColumn.IS_PRIMARY_NO);
		
		//テーブル生成
		createDBTable(env);
		
		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "プロジェクト情報テーブル生成完了");
	}

	/**
	 * データベーステーブルの生成<br>
	 * TODO このメソッドは完成時FDTableクラスに移行する。(2020/09/12 ueno)
	 * @author officina-hide.com
	 * @sinse 1.00 2020/09/12
	 * @param env 環境情報
	 */
	public void createDBTable(FD_EnvData env) {
		ResultSet rs = null;
		Statement stmt = null;
		StringBuffer sql = new StringBuffer();
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		//テーブル情報ID取得
		int tableId = getTableID(env, Table_Name);
		X_FD_Table table = new X_FD_Table(env, tableId);
		//既にテーブルが存在するときは削除する。
		sql.append("DROP TABLE IF EXISTS ").append(Table_Name);
		execute(env, sql.toString());
		//テーブルの新規作成
		try {
			//テーブル項目情報一覧取得
			sql = new StringBuffer();
			sql.append("SELECT * FROM FD_TableColumn ");
			sql.append("LEFT JOIN FD_Reference ON FD_Reference.FD_Reference_ID = FD_TableColumn.")
				.append(I_FD_TableColumn.COLUMNNAME_TABLECOLUMN_TYPE_ID).append(" ");
			sql.append("WHERE ").append(I_FD_TableColumn.COLUMNNAME_FD_TABLE_ID).append(" = ").append(tableId).append(" ");
			sql.append("ORDER BY Column_Sort_Order");
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				Map<String, String> map = new HashMap<String, String>();
				map.put(I_FD_TableColumn.COLUMNNAME_TABLECOLUMN_NAME, rs.getString(I_FD_TableColumn.COLUMNNAME_TABLECOLUMN_NAME));
				map.put("Column_Type_Name", rs.getString(I_FD_Reference.COLUMNNAME_REFERENCE_NAME));
				map.put(I_FD_TableColumn.COLUMNNAME_TABLECOLUMN_SIZE, rs.getString(I_FD_TableColumn.COLUMNNAME_TABLECOLUMN_SIZE));
				map.put(I_FD_TableColumn.COLUMNNAME_FD_NAME, rs.getString(I_FD_TableColumn.COLUMNNAME_FD_NAME));
				if(rs.getInt(I_FD_TableColumn.COLUMNNAME_IS_PRIMARY) == 1) {
					map.put(I_FD_TableColumn.COLUMNNAME_IS_PRIMARY, I_FD_TableColumn.IS_PRIMARY_YES);
				} else {
					map.put(I_FD_TableColumn.COLUMNNAME_IS_PRIMARY, I_FD_TableColumn.IS_PRIMARY_NO);
				}
				if(rs.getInt(I_FD_TableColumn.COLUMNNAME_IS_NULL) == 1) {
					map.put(I_FD_TableColumn.COLUMNNAME_IS_NULL, I_FD_TableColumn.IS_NULL_YES);
				} else {
					map.put(I_FD_TableColumn.COLUMNNAME_IS_NULL, I_FD_TableColumn.IS_NULL_NO);
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, stmt);
		}
		//テーブル生成
		sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS ").append(Table_Name);
		sql.append("(");
		StringBuffer items = new StringBuffer();	//項目用SQL文
		for(Map<String, String> map : list) {
			if(items.length() > 0) {
				items.append(", ");
			}
			items.append(map.get(I_FD_TableColumn.COLUMNNAME_TABLECOLUMN_NAME).toString()).append(" ");
			switch(map.get("Column_Type_Name").toString()) {
			case COLUMN_TYPE_INFORMATION_ID:
				items.append("INT UNSIGNED").append(" ");
				break;
			case COLUMN_TYPE_TEXT:
				items.append("Varchar(").append(map.get(I_FD_TableColumn.COLUMNNAME_TABLECOLUMN_SIZE).toString()).append(")").append(" ");
				break;
			case COLUMN_TYPE_DATE:
				items.append("Datetime").append(" ");
				break;
			}
			if(map.get(I_FD_TableColumn.COLUMNNAME_IS_NULL).toString().equals(I_FD_TableColumn.IS_NULL_YES)){
				items.append("NOT NULL").append(" ");
			}
			if(map.get(I_FD_TableColumn.COLUMNNAME_IS_PRIMARY).toString().equals(I_FD_TableColumn.IS_PRIMARY_YES)){
				items.append("PRIMARY KEY").append(" ");
			}
			if(map.get(I_FD_TableColumn.COLUMNNAME_FD_NAME) != null &&
					map.get(I_FD_TableColumn.COLUMNNAME_FD_NAME).toString().length() > 0) {
				items.append("COMMENT ").append(FD_SQ).append(map.get(I_FD_TableColumn.COLUMNNAME_FD_NAME).toString()).append(FD_SQ).append(" ");
			}
		}
		sql.append(items.toString()).append(")");
		if(table.getStringOfValue(COLUMNNAME_FD_NAME).length() > 0) {
			sql.append(" COMMENT ").append(FD_SQ).append(table.getStringOfValue(COLUMNNAME_FD_NAME)).append(FD_SQ).append(" ");
		}
		
		//生成処理実行
		execute(env, sql.toString());
	}
}
