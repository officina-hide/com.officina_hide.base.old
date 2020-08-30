package com.officina_hide.base.model;

/**
 * データベース操作に関する情報を扱う。<br>
 * @author ueno hideo
 * @version 1.20
 * @since 2020/07/16
 */
public interface I_DB {
	
	/** 項目種別 : 情報ID */
	public static final String COLUMN_TYPE_INFORMATION_ID = "FD_Information_ID";
	/** 項目種別 : テキスト */
	public static final String COLUMN_TYPE_TEXT = "FD_Text";
	/** 項目種別 : 複数行テキスト */
	public static final String COLUMN_TYPE_FIELD_TEXT = "FD_Field_Text";
	/** 項目種別 : 日付 */
	public static final String COLUMN_TYPE_DATE = "FD_Date";

	/**
	 * 改行
	 */
	public static final String FD_RETURN = "\n";

	/**
	 * タブ
	 */
	public static final String FD_TAB = "\t";

	/**
	 * ダブルクォーテーション
	 */
	public static final String FD_DQ ="\"";
	
	/**
	 * シングルクォーテーション
	 */
	public static final String FD_SQ = "'";

	//テーブル共通項目
	/** 登録日 */
	public final String COLUMNNAME_FD_CREATE = "FD_Create";

	/** 登録者ID	 */
	public final String COLUMNNAME_FD_CREATED = "FD_Created";

	/** 更新日 */
	public final String COLUMNNAME_FD_UPDATE = "FD_Update";

	/** 更新者ID	 */
	public final String COLUMNNAME_FD_UPDATED = "FD_Updated";

}
