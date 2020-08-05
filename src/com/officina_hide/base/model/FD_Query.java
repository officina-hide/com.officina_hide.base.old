package com.officina_hide.base.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;

/**
 * 情報汎用抽出クラス<br>
 * @author ueno hideo
 * @version 1.20
 * @since 2020/08/05
 */
public class FD_Query extends FD_DB implements I_DB {

	/**
	 * 抽出情報リスト<br>
	 */
	private List<Object> dataList;
	
	/**
	 * コンストラクター<br>
	 * @author ueno hideo
	 * @since 1.20 2020/08/05
	 * @param env 環境情報
	 * @param tableName 抽出対象テーブル
	 * @param where 抽出条件
	 */
	public FD_Query(FD_EnvData env, String tableName, FD_WhereData where) {
		List<Integer> ids = getIds(env, tableName, where);
		for(int id : ids) {
			
		}
	}

	/**
	 * テーブル情報IDリスト取得<br>
	 * <p>指定されたテーブルに対して、指定された条件でテーブルの識別用情報IDを抽出する。</p>
	 * TODO 汎用化予定(2020/08/05 ueno)
	 * @author ueno hideo
	 * @since 1.20 2020/08/05
	 * @param env 環境情報
	 * @param tableName テーブル名
	 * @param where 抽出条件
	 * @return 情報IDリスト
	 */
	private List<Integer> getIds(FD_EnvData env, String tableName, FD_WhereData where) {
		List<Integer> list = new ArrayList<Integer>();
		StringBuffer sql = new StringBuffer();
		ResultSet rs = null;
		Statement stmt = null;

		sql.append("SELECT ").append(tableName).append("_ID FROM ").append(tableName).append(" ");
		sql.append("WHERE ").append(where.toString());
		try {
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				list.add(rs.getInt(tableName+"_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, stmt);
		}
		return list;
	}

	/**
	 * @return dataList
	 */
	public List<Object> getDataList() {
		if(dataList == null) {
			dataList = new ArrayList<Object>();
		}
		return dataList;
	}

}
