package com.officina_hide.base.model;

/**
 * 画面情報インターフェースクラス<br>
 * @author officine-hide.com ueno
 * @version 2.00
 * @since 2020/08/25
 */
public interface I_Fx_View {
	/** テーブル名.<br> */
	public final String Table_Name = "Fx_View";

	/** テーブルID */
	public final int Table_ID =110;

	/** 画面情報ID */
	public final String COLUMNNAME_FX_VIEW_ID = "Fx_View_ID";
	
	/** 画面名 */
	public final String COLUMNNAME_VIEW_NAME = "View_Name";
	
	/** 画面表示名 */
	public final String COLUMNNAME_FD_NAME = "FD_Name";
	
	/** 画面幅初期値 */
	public final String COLUMNNAME_VIEW_PRE_WIDTH = "View_Pre_Width";
	
	/** 画面高さ初期値 */
	public final String COLUMNNAME_VIEW_PRE_HEIGHT = "View_Pre_Height";
	
	/** 画面取扱テーブル情報ID */
	public final String COLUMNNAME_TABLE_ID = "FD_Table_ID";
}
