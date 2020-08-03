package com.officina_hide.base.common;

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
	 * @param columnName 項目情報
	 * @param dataName 文字列情報
	 */
	public FD_WhereData(String columnName, String dataName) {
		where.append(columnName).append(" = ").append(FD_SQ).append(dataName).append(FD_SQ);
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
