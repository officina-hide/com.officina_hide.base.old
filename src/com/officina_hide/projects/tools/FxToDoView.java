package com.officina_hide.projects.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.tools.FxView;
import com.officina_hide.projects.model.I_Fx_ToDo_View;
import com.officina_hide.projects.model.I_PJ_ToDo;

/**
 * FxToDo画面クラス
 * @author officine-hide.com
 * @version 2.12
 * @since 2020/10/02
 */
public class FxToDoView implements I_Fx_ToDo_View {

	/**
	 * FxToDo画面情報登録<br>
	 * @author officine-hide.com
	 * @since 2.12 2020/10/02
	 * @param env 環境情報
	 */
	public void createViewData(FD_EnvData env) {
		//画面情報登録
		FxView view = new FxView();
		int viewId = view.addData(env, View_Name, Name, 500, 300, I_PJ_ToDo.Table_Name);
	}

}
