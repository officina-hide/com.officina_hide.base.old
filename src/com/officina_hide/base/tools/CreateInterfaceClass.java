package com.officina_hide.base.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;

/**
 * インターフェースクラス生成<br>
 * @author ueno hideo
 * @version 1.21
 * @since 2020/08/24
 */
public class CreateInterfaceClass extends FD_DB {
	
	/**
	 * コンストラクター<br>
	 * <p>インスタンス化時にインターフェースクラスを生成する。</p>
	 * @author ueno hideo
	 * @since 1.21 2020/08/24
	 * @param env
	 * @param tableName
	 */
	public CreateInterfaceClass(FD_EnvData env, String tableName) {
		createInterfaceClass(env, tableName);
	}

	/**
	 * インターフェースクラス生成
	 * @param env 　環境情報
	 * @param tableName テーブル名
	 */
	private void createInterfaceClass(FD_EnvData env, String tableName) {
		StringBuffer source = new StringBuffer();
		File file = new File(env.getModelPath()+"\\"+"I_"+tableName+".java");
		try {
			FileWriter fw  = new FileWriter(file);
			//Package宣言
			source.append("package ").append(env.getModelURI()).append(";").append(FD_RETURN).append(FD_RETURN);
			//クラス開始
			source.append("public interface I_").append(tableName).append(" {").append(FD_RETURN);
			//テーブル名定数
			source.append(editComment("テーブル名", 1));
			source.append(setTab(1)).append("public final String Table_Name = ")
				.append(FD_DQ).append(tableName).append(FD_DQ).append(";").append(FD_RETURN).append(FD_RETURN);
			//テーブルID定数
			source.append(editComment("テーブルID", 1));
			source.append(setTab(1)).append("public final int Table_ID =")
				.append(getTableID(env, tableName)).append(";").append(FD_RETURN).append(FD_RETURN);
			//テーブル各項目
			List<Map<String, String>> columns = getColumnData(env, tableName);
			for(Map<String, String> map : columns) {
				source.append(editComment(map.get("FD_Name").toString(), 1));
				source.append(setTab(1)).append("public final String COLUMNNAME_").append(map.get("TableColumn_Name").toString().toUpperCase())
					.append(" = ").append(FD_DQ).append(map.get("TableColumn_Name").toString()).append(FD_DQ).append(";").append(FD_RETURN);
				source.append(FD_RETURN);
			}
			
			//クラス終了
			source.append("}").append(FD_RETURN);
			
			fw.write(source.toString());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * テーブル項目リスト生成<br>
	 * @author ueno hideo
	 * @param tableName 
	 * @param env 
	 * @since 1.20 2020/07/16
	 * @return テーブル項目リスト
	 */
		private List<Map<String, String>> getColumnData(FD_EnvData env, String tableName) {
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			int tableID = getTableID(env, tableName);
			StringBuffer sql = new StringBuffer();
			ResultSet rs = null;
			Statement stmt = null;
			try {
				sql.append("SELECT * FROM FD_TableColumn ");
				sql.append("LEFT JOIN FD_Reference ON FD_Reference.FD_Reference_ID = FD_TableColumn.TableColumn_Type_ID ");
				sql.append("WHERE FD_Table_ID = ").append(tableID).append(" ");
				sql.append("ORDER BY Column_Sort_Order");
				connection(env);
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql.toString());
				while(rs.next()) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("TableColumn_Name", rs.getNString("TableColumn_Name"));
					map.put("FD_Name", rs.getNString("FD_Name"));
					map.put("Reference_Class", rs.getString("Reference_Class"));
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
