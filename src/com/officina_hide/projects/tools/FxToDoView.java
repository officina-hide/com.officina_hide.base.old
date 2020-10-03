package com.officina_hide.projects.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_Fx_ViewItem;
import com.officina_hide.base.tools.FxView;
import com.officina_hide.base.tools.FxViewItem;
import com.officina_hide.projects.model.I_Fx_ToDo_View;
import com.officina_hide.projects.model.I_PJ_ToDo;

/**
 * FxToDo画面クラス
 * @author officine-hide.com
 * @version 2.12
 * @since 2020/10/02
 */
public class FxToDoView extends FD_DB  implements I_Fx_ToDo_View, I_PJ_ToDo {

	/**
	 * FxToDo画面情報登録<br>
	 * @author officine-hide.com
	 * @since 2.12 2020/10/02
	 * @param env 環境情報
	 */
	public void createViewData(FD_EnvData env) {
		//画面情報登録
		FxView view = new FxView();
		int viewId = view.addData(env, View_Name, I_Fx_ToDo_View.Name, 500, 400, I_PJ_ToDo.Table_Name);
		
		//画面項目情報登録
		FxViewItem item = new FxViewItem();
		item.addData(env, viewId, COLUMNNAME_TODO_TITLE, NAME_TODO_TITLE, I_Fx_ViewItem.VIEWTYPE_ID_FX_TEXT
				, getTableColumnID(env, I_PJ_ToDo.Table_Name, I_PJ_ToDo.COLUMNNAME_TODO_TITLE), null);
		item.addData(env, viewId, COLUMNNAME_TODO_MEMO, NAME_TODO_MEMO, I_Fx_ViewItem.VIEWTYPE_ID_FX_TEXTFIELD
				, getTableColumnID(env, Table_Name, COLUMNNAME_TODO_MEMO),
				I_Fx_ViewItem.COLUMNNAME_FIELD_LENGTH+"=30,"+I_Fx_ViewItem.COLUMNNAME_FIELD_ROWS+"=3");
	}

}
