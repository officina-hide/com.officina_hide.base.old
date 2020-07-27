package com.officina_hide.base.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SQL並び順クラス<br>
 * @author ueno hideo
 * @version 1.20
 * @since 2020/07/27
 */
public class FD_OrderData {

	/**
	 * 並び順 : 昇順
	 */
	public static final String ASCENDING = "ASC";
	/**
	 * 並び順 : 昇順
	 */
	public static final String DESCENDING = "DESC";
	
	/**
	 * 並び順リスト<br>
	 * <p>Map項目<br>
	 * ・name - 項目名<br>
	 * ・sort - 並び順(ASCENDING/DESCENDING)</p>
	 * @author ueno hideo
	 * @since 1.11 2020/07/05
	 */
	private List<Map<String, String>> orderList = new ArrayList<Map<String, String>>();

	/**
	 * 並び順リストに対象項目と昇降順を追加する。<br>
	 * @author ueno hideo
	 * @since 1.11 2020/07/05 
	 * @param name 項目名
	 * @param sort 昇降順
	 */
	public void add(String name, String sort) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("sort", sort);
		orderList.add(map);
	}

	/**
	 * SQLのOrder文作成<br>
	 * @author ueno hideo
	 * @since 1.11 2020/07/06
	 */
	@Override
	public String toString() {
		StringBuffer sql = new StringBuffer();
		for(Map<String, String> map : orderList) {
			if(sql.toString().length() > 0) {
				sql.append(", ");
			}
			sql.append(map.get("name")).append(" ").append(map.get("sort"));
		}
		return sql.toString();
	}
}
