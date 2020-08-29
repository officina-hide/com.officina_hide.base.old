package com.officina_hide.base.common;

import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_DB;

/**
 * 条件クラス<br>
 * @author ueno hideo
 * @version 1.20
 * @since 2020/07/23
 */
public class FD_WhereData implements I_DB {
	private StringBuffer where = new StringBuffer();

	public FD_WhereData() {
	}

	/**
	 * コンストラクター<br>
	 * <p>インスタンス時に項目と文字列データで単一の条件情報を生成する。</p>
	 * @author ueno hideo
	 * @since 1.20 2020/07/23
	 * @param columnName 項目名
	 * @param data 文字列情報
	 */
	public FD_WhereData(String columnName, String data) {
		FD_DB DB = new FD_DB();
		data = DB.changeEscape(data);
		getWhere().append(columnName).append(" = ").append(FD_SQ).append(data).append(FD_SQ);
	}

	/**
	 * コンストラクター<br>
	 * <p>インスタンス時に項目と数値データで単一の条件情報を生成する。</p>
	 * @author ueno hideo
	 * @since 1.20 2020/08/05
	 * @param columnName 項目名
	 * @param data 数値情報
	 */
	public FD_WhereData(String columnName, int data) {
		getWhere().append(columnName).append(" = ").append(data);
	}

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
