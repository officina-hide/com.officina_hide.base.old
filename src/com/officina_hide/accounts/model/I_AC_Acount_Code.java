package com.officina_hide.accounts.model;

/**
 * 勘定科目情報インターフェース<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/09/14
 */
public interface I_AC_Acount_Code {
	
	/** 勘定科目情報 : テーブル名 */
	public final String Table_Name = "AC_Acount_Code";
	/** 勘定科目情報 : テーブル論理名 */
	public final String Name = "勘定科目情報";
	
	/** 勘定科目情報ID */
	public final String COLUMNNAME_AC_ACOUNT_CODE_ID = Table_Name + "_ID";
	/** 勘定科目コード */
	public final String COLUMNNAME_ACOUNT_CODE = "Acount_Code";
	/** 勘定科目名 */
	public final String COLUMNNAME_ACOUNT_CODE_NAME = "Acount_Code_Name";
}
