package com.officina_hide.documents.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_TableColumn;
import com.officina_hide.base.tools.FDNumbering;
import com.officina_hide.base.tools.FDTable;
import com.officina_hide.base.tools.FDTableColumn;
import com.officina_hide.documents.model.I_DD_Project;
import com.officina_hide.documents.model.X_DD_Project;

/**
 * プロジェクト情報クラス<br>
 * @author officina-hide.com
 * @version 1.00
 * @sinse 2020/09/12
 */
public class DDProject extends FD_DB implements I_DD_Project {

	/**
	 * プロジェクト情報テーブル生成
	 * @author officina-hide.com
	 * @sinse 1.00 2020/09/12
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FDTable table = new FDTable();
		int tableId = table.addData(env, 0, I_DD_Project.Table_Name, "プロジェクト情報", "");
		
		//テーブル項目情報登録
		FDTableColumn column = new FDTableColumn();
		column.add(env, tableId, COLUMNNAME_DD_PROJECT_ID, "プロジェクト情報ID", "プロジェクトを識別する為の情報ID"
				, COLUMN_TYPE_INFORMATION_ID, 0, 10, I_FD_TableColumn.IS_PRIMARY_YES);
		column.add(env, tableId, COLUMNNAME_PROJECT_NAME, "プロジェクト名", "プロジェクトの物理名称"
				, COLUMN_TYPE_TEXT, 100, 20, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_FD_NAME, "プロジェクト表示名", "プロジェクトの論理名"
				, COLUMN_TYPE_TEXT, 100, 30, I_FD_TableColumn.IS_PRIMARY_NO);
		
		column.add(env, tableId, COLUMNNAME_FD_CREATE, "登録日", "情報の登録日"
				, COLUMN_TYPE_DATE, 0, 900, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_FD_CREATED, "登録者ID", "情報の登録者ID（ユーザー情報ID）"
				, COLUMN_TYPE_INFORMATION_ID, 0, 910, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_FD_UPDATE, "更新日", "情報の更新日"
				, COLUMN_TYPE_DATE, 0, 920, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_FD_UPDATED, "更新者ID", "情報の更新者ID（ユーザー情報ID）"
				, COLUMN_TYPE_INFORMATION_ID, 0, 930, I_FD_TableColumn.IS_PRIMARY_NO);
		
		//テーブル生成
		createDBTable(env, Table_Name);
		
		//採番情報登録
		FDNumbering num = new FDNumbering();
		num.add(env, tableId, 1000001, 0);
		
		env.getLog().add(env, FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "プロジェクト情報テーブル生成完了");
	}

	/**
	 * プロジェクト情報登録<br>
	 * @param env 環境情報
	 * @param projectName プロジェクト名
	 * @param name プロジェクト表示名
	 * @return プロジェクト情報ID
	 */
	public int addData(FD_EnvData env, String projectName, String name) {
		X_DD_Project project = new X_DD_Project(env);
		project.setValue(env, COLUMNNAME_DD_PROJECT_ID, 0);
		project.setValue(env, COLUMNNAME_PROJECT_NAME, projectName);
		project.setValue(env, COLUMNNAME_FD_NAME, name);
		project.save(env);
		
		return project.getIntOfValue(COLUMNNAME_DD_PROJECT_ID);
	}
}
