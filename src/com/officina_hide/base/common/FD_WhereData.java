package com.officina_hide.base.common;

/**
 * 条件クラス<br>
 * @author ueno hideo
 * @version 1.20
 * @since 2020/07/23
 */
public class FD_WhereData {
	private StringBuffer where = new StringBuffer();

	public StringBuffer getWhere() {
		return where;
	}

	@Override
	public String toString() {
		return getWhere().toString();
	}

	/**
	 * SQL条件分の初期化
	 */
	public void clear() {
		where = new StringBuffer();
	}
}
