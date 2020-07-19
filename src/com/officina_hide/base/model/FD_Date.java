package com.officina_hide.base.model;

import java.util.List;

/**
 * 「日時」項目クラス<br>
 * @author ueno hideo
 * @version 1.20
 * @since 2020/07/16
 */
public class FD_Date extends FD_DB implements I_DB {

	/**
	 * インポートクラスリスト
	 */
	private List<String> importList;

	public FD_Date(List<String> importList) {
		this.importList = importList;
	}

	/**
	 * 項目定義文字列作成<br>
	 * @author ueno hideo
	 * @since 1.20 2020/07/16
	 * @param columnName 項目物理名
	 * @param name 項目論理名
	 * @return 項目定義文字列
	 */
	public String toVariableDefinitions(String columnName, String name) {
		StringBuffer source = new  StringBuffer();
		source.append(editComment(name, 1));
		source.append(setTab(1)).append("private Calendar ").append(columnName.substring(0, 1).toLowerCase())
			.append(columnName.substring(1)).append(";").append(FD_RETURN);
		addImportClass(importList, "java.util.Calendar");
		return source.toString();
	}

	/**
	 * クラスのgetter定義用文字列を返す。
	 * @author ueno hideo
	 * @since 1.20 2020/07/20
	 * @param columnName 変数名
	 * @param name 変数説明
	 * @return 定義用文字列
	 */
	public String toGetterDefinition(String columnName, String name) {
		StringBuffer source = new StringBuffer();
		String variable = columnName.substring(0, 1).toLowerCase()+columnName.substring(1);
		source.append(editComment(name+"を取得する。", 1));
		source.append(setTab(1)).append("public Calendar get").append(columnName.substring(0, 1).toUpperCase())
			.append(columnName.substring(1)).append("() {").append(FD_RETURN);
		source.append(setTab(2)).append("if(").append(variable).append(" == null) {").append(FD_RETURN);
		source.append(setTab(3)).append(variable).append(" = new GregorianCalendar(new Locale(\"ja\", \"JP\"));").append(FD_RETURN);
		source.append(setTab(2)).append("}").append(FD_RETURN);
		source.append(setTab(2)).append("return ").append(columnName.substring(0, 1).toLowerCase())
			.append(columnName.substring(1)).append(";").append(FD_RETURN);
		source.append(setTab(1)).append("}").append(FD_RETURN);
		addImportClass(importList, "java.util.GregorianCalendar");
		addImportClass(importList, "java.util.Locale");
		return source.toString();				
	}
}
