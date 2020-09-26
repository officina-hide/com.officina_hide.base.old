package com.officina_hide.base.model;

/**
 * テーブル項目リストインターフェース<br>
 * @author officine-hide.com
 * @version 2.11
 * @since 2020/09/26
 */
public interface I_FD_TableColumnList {
	
	/** テーブル項目リスト情報 : テーブル名 */
	public final String Table_Name = "FD_TableColumnList";
	/** テーブル項目リスト情報 : テーブル論理名 */
	public final String Name = "テーブル項目リスト情報";
	
	/** テーブル項目リスト情報ID */
	public final String COLUMNNAME_FD_TABLECOLUMNLIST_ID = Table_Name + "_ID";
	public final String NAME_FD_TABLECOLUMNLIST_ID = "テーブル項目リスト情報ID";
	public final String COMMENT_FD_TABLECOLUMNLIST_ID = "テーブル項目リスト情報を識別する為の情報ID";
	/** テーブル項目情報ID */
	public final String COLUMNNAME_FD_TABLECOLUMN_ID = "FD_TableColumn_ID";
	public final String NAME_FD_TABLECOLUMN_ID = "テーブル項目情報ID";
	public final String COMMENT_FD_TABLECOLUMN_ID = "テーブル項目リストが紐づくテーブルの項目情報ID";
	/** リスト名 */
	public final String COLUMNNAME_LIST_NAME = "List_Name";
	public final String NAME_LIST_NAME = "リスト名";
	public final String COMMENT_LIST_NAME = "項目上で選択しとして表示されるリストの名称";
}
