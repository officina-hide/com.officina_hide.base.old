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
	/** 勘定科目 */
	public final String COLUMNNAME_AC_ACOUNT_CODE_ID = "AC_Account_Code_ID";
	/** 収支区分 */
	public final String COLUMNNAME_BALANCE_CODE = "Income_Balance_Code";
	/** 金額 */
	public final String COLUMNNAME_CASH_AMOUNT = "Cash_Amount";
	/** 備考 */
	public final String COLUMNNAME_FD_MEMO = "FD_Memo";
}
