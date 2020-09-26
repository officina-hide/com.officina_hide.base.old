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
	/** 項目種別 : 自然数 */
	public static final String COLUMN_TYPE_NUMBER = "FD_Number";
	/** 項目種別 : YESNO */
	public static final String COLUMN_TYPE_YESNO = "FD_YesNo";
	/** 項目種別 : リスト */
	public static final String COLUMN_TYPE_LIST = "FD_List";
	public static final String NAME_TYPE_LIST = "リスト";


	/**
	 * 改行
	 */
	public static final String FD_RETURN = "\n";

	/**
	 * タブ
	 */
	public final String FD_TAB = "\t";

	/** ダブルクォーテーション */
	public final String FD_DQ ="\"";
	
	/** シングルクォーテーション */
	public final String FD_SQ = "'";

	//テーブル共通項目
	/** 登録日 */
	public final String COLUMNNAME_FD_CREATE = "FD_Create";
	public final String NAME_FD_CREATE = "登録日";
	public final String COMMENT_FD_CREATE = "情報の登録日";

	/** 登録者ID	 */
	public final String COLUMNNAME_FD_CREATED = "FD_Created";
	public final String NAME_FD_CREATED = "登録者";
	public final String COMMENT_FD_CREATED = "情報の登録者ID";

	/** 更新日 */
	public final String COLUMNNAME_FD_UPDATE = "FD_Update";
	public final String NAME_FD_UPDATE = "更新日";
	public final String COMMENT_FD_UPDATE = "情報の更新日";

	/** 更新者ID	 */
	public final String COLUMNNAME_FD_UPDATED = "FD_Updated";
	public final String NAME_FD_UPDATED = "更新者";
	public final String COMMENT_FD_UPDATED = "情報の更新者ID";

}
