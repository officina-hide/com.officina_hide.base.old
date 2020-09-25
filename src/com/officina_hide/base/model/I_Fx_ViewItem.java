package com.officina_hide.base.model;

/**
 * 画面項目情報インターフェース<br>
 * @author officina-hide.com ueno
 * @version 2.00　新規作成
 * @version 2.11 画面項目属性ID追加(テーブル)
 * @since 2020/09/01
 */
public interface I_Fx_ViewItem {

	/** 画面項目情報 : テーブル名 */
	public final String Table_Name = "Fx_ViewItem";
	/** 画面項目情報 : テーブル論理名 */
	public final String Name = "画面項目情報";
	/** 画面項目情報 : テーブル説明 */
	public final String Comment = "Fx画面に表示される項目の情報";
	
	/** 画面項目情報ID */
	public final String COLUMNNAME_FX_VIEWITEM_ID = "Fx_ViewItem_ID";
	/** 画面情報ID */
	public final String COLUMNNAME_FX_VIEW_ID = "Fx_View_ID";
	/** 画面項目名 */
	public final String COLUMNNAME_VIEWITEM_NAME = "ViewItem_Name";
	/** 画面表示名 */
	public final String COLUMNNAME_FD_NAME = "FD_Name";
	/** 画面項目属性ID */
	public final String COLUMNNAME_VIEWITEM_TYPE_ID = "ViewItem_Type_ID";
	/** テーブル項目情報ID */
	public final String COLUMNNAME_TABLECOLUMN_ID = "TableColumn_ID";
	public final String NAME_TABLECOLUMN_ID = "対象カラム情報ID";
	public final String COMMENT_TABLECOLUMN_ID = "画面項目の対象となるテーブル項目情報のID";
	/** 
	 * 検索対象テーブル<br>
	 * 項目属性がFx_Tableの時に、画面上で検索・表示の対象となるテーブルを設定する。<br>
	 */
	public final String COLUMNNAME_SEARCH_TABLE_ID = "Search_Table_ID";
	public final String NAME_SEARCH_TABLE_ID = "検索対象テーブル情報ID";
	public final String COMMENT_SEARCH_TABLE_ID = "項目属性がFx_Tableの時に、画面上で検索・表示の対象となるテーブルの情報ID";
	/** 
	 * 検索対象検索項目<br>
	 * 項目属性がFx_Tableの時に、画面項目の入力に対して検索の対象となる項目の情報IDを設定する。<br>
	 */
	public final String COLUMNNAME_SEARCH_COLUMN_ID = "Search_Column_ID";
	public final String NAME_SEARCH_COLUMN_ID = "検索対象検索項目情報ID";
	public final String COMMENT_SEARCH_COLUMN_ID = "項目属性がFx_Tableの時に、画面項目の入力に対して検索の対象となるテーブル項目の情報ID";
	/**
	 * 検索対象表示項目<br>
	 * 項目属性がFx_Tableの時に、画面項目に表示する対象となる項目の情報IDを設定する。<br>
	 */
	public final String COLUMNNAME_SEARCH_DISP_ID = "Search_Disp_ID";
	public final String NAME_SEARCH_DISP_ID = "検索対象表示項目";
	public final String COMMENT_SEARCH_DISP_ID = "項目属性がFx_Tableの時に、画面項目に表示する対象となる項目の情報ID";
	
	/**
	 * 画面項目属性
	 */
	/** 画面項目属性ID : テキスト */
	public final String VIEWTYPE_ID_FX_TEXT =  "Fx_Text";
	/** 画面項目属性ID : 数値 */
	public final String VIEWTYPE_ID_FX_NUMBER =  "Fx_Number";
	/** 画面項目属性ID : 複数行テキスト */
	public final String VIEWTYPE_ID_FX_TEXTFIELD = "Fx_TextField";
	/** 画面項目属性ID : 日付 */
	public final String VIEWTYPE_ID_FX_DATE = "Fx_Date";
	/** 
	 * 画面項目属性ID : テーブル<br>
	 * <p>テーブルは項目上は情報IDを保持し、表示は指定された項目を表示する。<br>
	 * 画面上では項目表示用のテキストフィールドと検索・クリアボタンが同時に表示される。</p>
	 */
	public final String VIEWTYPE_ID_FX_TABLE = "Fx_Table";
	/** 画面項目属性ID : リスト */
	public final String VIEWTYPE_ID_FX_LIST = "Fx_List";
}
