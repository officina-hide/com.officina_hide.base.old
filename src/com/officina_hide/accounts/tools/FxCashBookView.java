package com.officina_hide.accounts.tools;

import com.officina_hide.accounts.model.I_AC_CashBook;
import com.officina_hide.accounts.model.I_Fx_CashBook_View;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.I_Fx_ViewItem;
import com.officina_hide.base.tools.FxView;
import com.officina_hide.base.tools.FxViewItem;

/**
 * Fx現金出納画面クラス<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/09/15
 */
public class FxCashBookView implements I_Fx_CashBook_View, I_AC_CashBook {

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
		item.addData(env, viewId, COLUMNNAME_TREASURER_DATE, NAME_TREASURER_DATE, I_Fx_ViewItem.VIEWTYPE_ID_FX_DATE);
	}

}
