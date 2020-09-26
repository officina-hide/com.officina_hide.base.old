package com.officina_hide.accounts.model;

/**
 * 現金出納情報<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/09/13
 */
public interface I_AC_CashBook {
	/** 現金出納情報 : テーブル名 */
	public final String Table_Name = "AC_CashBook";
	/** 現金出納情報 : テーブル論理名 */
	public final String Name = "現金出納情報";
	
	/** 現金出納情報ID */
	public final String COLUMNNAME_AC_CASHBOOK_ID = Table_Name + "_ID";
	/** クライアント情報ID */
	public final String COLUMNNAME_FD_CLIENT_ID = "FD_Client_ID";
	/** 出納日 */
	public final String COLUMNNAME_TREASURER_DATE  = "Treasirer_Date";
	public final String NAME_TREASURER_DATE = "出納日";
	public final String COMMENT_TREASURER_DATE = "出納の発生日";
	/** 勘定科目 */
	public final String COLUMNNAME_AC_ACOUNT_CODE_ID = "AC_Account_Code_ID";
	public final String NAME_AC_ACOUNT_CODE_ID = "勘定科目情報ID";
	public final String COMMENT_AC_ACOUNT_CODE_ID = "出納の勘定科目の情報ID";
	/** 収支区分 */
	public final String COLUMNNAME_BALANCE_CODE = "Income_Balance_Code";
	public final String NAME_BALANCE_CODE = "収支区分";
	public final String COMMENT_BALANCE_CODE = "出納の収支を表す区分";
	/** 金額 */
	public final String COLUMNNAME_CASH_AMOUNT = "Cash_Amount";
	/** 備考 */
	public final String COLUMNNAME_FD_MEMO = "FD_Memo";
	
	/** 収支区分 : 収入 */
	public final String BALANCE_CODE_INCOME = "収入";
	public final String BALANCE_CODE_OUTCOME = "支出";
}
