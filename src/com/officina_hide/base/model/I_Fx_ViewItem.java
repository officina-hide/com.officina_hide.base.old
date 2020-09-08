package com.officina_hide.base.model;

/**
 * 画面項目情報インターフェース<br>
 * @author officina-hide.com ueno
 * @version 2.00
 * @since 2020/09/01
 */
public interface I_Fx_ViewItem {

	/** テーブル名 */
	public final String Table_Name = "Fx_ViewItem";

	/** テーブル情報ID */
	public final int Table_ID = 111;
	
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
	
	/**
	 * 画面項目属性
	 */
	/** 画面項目属性ID : テキスト */
	public final String VIEWTYPE_ID_FX_TEXT =  "Fx_Text";
	/** 画面項目属性ID : 数値 */
	public final String VIEWTYPE_ID_FX_NUMBER =  "Fx_Number";
	/** 画面項目属性ID : 複数行テキスト */
	public final String VIEWTYPE_ID_FX_TEXTFIELD = "Fx_TextField";
}
