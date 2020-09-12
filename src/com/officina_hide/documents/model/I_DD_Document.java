package com.officina_hide.documents.model;

/**
 * ドキュメント情報インターフェース<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/09/12
 */
public interface I_DD_Document {

	/** ドキュメント情報 : テーブル名 */
	public final String Table_Name = "DD_Document";
	
	/** ドキュメント情報ID */
	public final String COLUMNNAME_DD_DOCUMENT_ID = Table_Name+"_ID";
	/** プロジェクト情報ID */
	public final String COLUMNNAME_DD_PROJECT_ID = I_DD_Project.COLUMNNAME_DD_PROJECT_ID;
	/** ドキュメント名 */
	public final String COLUMNNAME_DOCUMENT_NAME = "Document_Name";
	/** ドキュメント表示名 */
	public final String COLUMNNAME_FD_NAME = "FD_Name";
	/** ドキュメント順序 */
	public final String COLUMNNAME_DOCUMENT_SORT_NO = "Document_Sort_No";
}
