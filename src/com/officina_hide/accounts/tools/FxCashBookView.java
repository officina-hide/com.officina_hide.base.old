package com.officina_hide.accounts.tools;

import com.officina_hide.accounts.model.I_AC_Acount_Code;
import com.officina_hide.accounts.model.I_AC_CashBook;
import com.officina_hide.accounts.model.I_Fx_CashBook_View;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_Fx_ViewItem;
import com.officina_hide.base.model.X_Fx_ViewItem;
import com.officina_hide.base.tools.FxView;
import com.officina_hide.base.tools.FxViewItem;

/**
 * Fx現金出納画面クラス<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/09/15
 */
public class FxCashBookView extends FD_DB implements I_Fx_CashBook_View, I_AC_CashBook {

	/**
	 * Fx現金出納画面の生成<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/09/15
	 * @param env 環境情報
	 */
	public void createViewData(FD_EnvData env) {
		//画面情報登録
		FxView view = new FxView();
		int viewId = view.addData(env, View_Name, I_Fx_CashBook_View.Name, 500, 300, I_AC_CashBook.Table_Name);
		
		//画面項目登録
		FxViewItem item = new FxViewItem();
		item.addData(env, viewId, COLUMNNAME_TREASURER_DATE, NAME_TREASURER_DATE, I_Fx_ViewItem.VIEWTYPE_ID_FX_DATE
				, getTableColumnID(env, I_AC_CashBook.Table_Name, I_AC_CashBook.COLUMNNAME_TREASURER_DATE));
		
		int viewItemId = item.addData(env, viewId, COLUMNNAME_AC_ACOUNT_CODE_ID, "勘定科目", I_Fx_ViewItem.VIEWTYPE_ID_FX_TABLE
				, getTableColumnID(env, I_AC_CashBook.Table_Name, I_AC_CashBook.COLUMNNAME_AC_ACOUNT_CODE_ID));
		X_Fx_ViewItem acode = new X_Fx_ViewItem(env, viewItemId);
		acode.setValue(env, I_Fx_ViewItem.COLUMNNAME_SEARCH_TABLE_ID, getTableID(env, I_AC_Acount_Code.Table_Name));
		acode.setValue(env, I_Fx_ViewItem.COLUMNNAME_SEARCH_COLUMN_ID
				, getTableColumnID(env, I_AC_Acount_Code.Table_Name, I_AC_Acount_Code.COLUMNNAME_ACOUNT_CODE));
		acode.setValue(env, I_Fx_ViewItem.COLUMNNAME_SEARCH_DISP_ID
				, getTableColumnID(env, I_AC_Acount_Code.Table_Name, I_AC_Acount_Code.COLUMNNAME_ACOUNT_CODE_NAME));
		acode.save(env);
		
		item.addData(env, viewId, COLUMNNAME_BALANCE_CODE, NAME_BALANCE_CODE, I_Fx_ViewItem.VIEWTYPE_ID_FX_LIST
				, getTableColumnID(env, I_AC_CashBook.Table_Name, I_AC_CashBook.COLUMNNAME_BALANCE_CODE));
	}

}
