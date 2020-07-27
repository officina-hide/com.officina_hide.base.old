package com.officina_hide.base.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JavaDoc用パラメータクラス<br>
 * @author ueno hideo
 * @version 1.20
 * @since 2020/07/27
 */
public class FD_JavaDocParam extends FD_DB {

	/**
	 * パラメータリスト
	 */
	private List<Map<String, String>> paramList = new ArrayList<Map<String,String>>();
	
	/**
	 * パラメータを追加する。
	 * @author ueno hideo
	 * @since 1.11 2020/07/02
	 * @param paramName 引数名
	 * @param paramComment 引数説明
	 */
	public void add(String paramName, String paramComment) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", paramName);
		map.put("comment", paramComment);
		paramList.add(map);
	}

	/**
	 * パラメータの登録数を返す。<br>
	 * @author ueno hideo
	 * @since 1.11 2020/07/02
	 * @return パラメータ登録数
	 */
	public int length() {
		return paramList.size();
	}

	/**
	 * JavaDoc文字列取得<br>
	 * @param tabCount タブ数
	 * @return JavaDoc文字列
	 */
	public String getJavadocOfParam(int tabCount) {
		StringBuffer source = new StringBuffer();
		for(Map<String, String> map : paramList) {
			source.append(setTab(tabCount)).append(" * ").append("@param")
				.append(map.get("name").toString()).append(" ").append(map.get("comment").toString()).append(FD_RETURN);
		}
		return source.toString();
	}


}
