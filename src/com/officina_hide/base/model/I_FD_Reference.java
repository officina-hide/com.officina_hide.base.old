package com.officina_hide.base.model;

/**
 * リファレンス情報インターフェース<br>
 * @author officina-hide.com ueno
 * @version 2.00
 * @since 2020/09/07
 */
public interface I_FD_Reference {
	/** リファレンス情報 テーブル名	 */
	public final String Table_Name = "FD_Reference";

	/** リファレンス情報 テーブルID */
	public final int Table_ID =104;

	/** リファレンス情報ID */
	public final String COLUMNNAME_FD_REFERENCE_ID = "FD_Reference_ID";
	/** リファレンス名' */
	public final String COLUMNNAME_REFERENCE_NAME = "Reference_Name";
	/** リファレンス表示名' */
	public final String COLUMNNAME_FD_NAME = "FD_Name";
	
}
