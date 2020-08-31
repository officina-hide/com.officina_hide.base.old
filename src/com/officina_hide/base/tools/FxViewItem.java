package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.model.FD_DB;

/**
 * 画面項目情報クラス<br>
 * @author officina-hide.com ueno
 * @version 2.00
 * @since 2020/09/01
 */
public class FxViewItem extends FD_DB implements I_Fx_ViewItem {

	/**
	 * 画面項目情報テーブル生成<br>
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		//既に登録されている 画面項目情報を削除します。
		sql.append("DROP TABLE IF EXISTS ").append(Table_Name);
		execute(env, sql.toString());
		//画面項目情報を生成する。
		sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS Fx_ViewItem  (");
		sql.append(COLUMNNAME_FX_VIEWITEM_ID).append(" INT UNSIGNED NOT NULL PRIMARY KEY COMMENT ")
			.append(FD_SQ).append("画面項目情報ID").append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FX_VIEW_ID).append(" INT UNSIGNED NOT NULL COMMENT ")
			.append(FD_SQ).append("画面情報ID").append(FD_SQ).append(",");
		sql.append(COLUMNNAME_VIEWITEM_NAME).append(" Varchar(100) COMMENT ")
			.append(FD_SQ).append("画面項目名").append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_NAME).append(" Varchar(100) COMMENT ")
			.append(FD_SQ).append("画面項目表示名").append(FD_SQ).append(",");
		sql.append(COLUMNNAME_VIEWITEM_TYPE_ID).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append("画面項目属性ID").append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_CREATE).append(" DATETIME  COMMENT ").append(FD_SQ).append("登録日").append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_CREATED).append(" INT UNSIGNED COMMENT ").append(FD_SQ).append("登録者ID").append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_UPDATE).append(" DATETIME  COMMENT ").append(FD_SQ).append("更新日").append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_UPDATED).append(" INT UNSIGNED COMMENT ").append(FD_SQ).append("更新者ID").append(FD_SQ);
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='テーブル項目情報'");
		execute(env, sql.toString());
		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "画面項目情報テーブル構築");
	}

}
