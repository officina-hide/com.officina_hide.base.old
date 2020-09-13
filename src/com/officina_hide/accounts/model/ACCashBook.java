package com.officina_hide.accounts.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.tools.FDTable;
import com.officina_hide.base.tools.FDTableColumn;

/**
 * 現金出納情報<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/09/13
 */
public class ACCashBook extends FD_DB implements I_AC_CashBook {

	/**
	 * 現金出納情報テーブル生成<br>
	 * @author officine-hide.com
	 * @since 2020/09/13
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FDTable table = new FDTable();
		int tableId = table.addData(env, 0, Table_Name, Name);
		
		//テーブル項目情報登録
		FDTableColumn column = new FDTableColumn();
	}

}
