package com.officina_hide.base.tools;

import java.sql.Timestamp;
import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Process;
import com.officina_hide.base.model.I_FD_TableColumn;
import com.officina_hide.base.model.X_FD_Process;

/**
 * プロセス情報クラス<br>
 * @author officine-hide.com
 * @version 2.11
 * @since 2020/09/17
 */
public class FDProcess extends FD_DB implements I_FD_Process {

	/**
	 * プロセス情報テーブル生成<br>
	 * @author officine-hide.com
	 * @since 2.11 2020/09/17
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FDTable table = new FDTable();
		int tableId = table.addData(env, 0, Table_Name, Name);
		
		//テーブル項目情報登録
		FDTableColumn column = new FDTableColumn();
		column.add(env, tableId, COLUMNNAME_FD_PROCESS_ID, NAME_FD_PROCESS_ID, COMMENT_FD_PROCESS_ID
				, COLUMN_TYPE_INFORMATION_ID, 0, 10, I_FD_TableColumn.IS_PRIMARY_YES);
		column.add(env, tableId, COLUMNNAME_PROCESS_NAME, NAME_PROCESS_NAME, COMMENT_PROCESS_NAME
				, COLUMN_TYPE_TEXT, 100, 20, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_PROCESS_START, NAME_PROCESS_START, COMMENT_PROCESS_START
				, COLUMN_TYPE_DATE, 0, 30, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_PROCESS_END, NAME_PROCESS_END, COMMENT_PROCESS_END
				, COLUMN_TYPE_DATE, 0, 40, I_FD_TableColumn.IS_PRIMARY_NO);
		
		column.add(env, tableId, COLUMNNAME_FD_CREATE, NAME_FD_CREATE, COMMENT_FD_CREATE
				, COLUMN_TYPE_DATE, 0, 910, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_FD_CREATED, NAME_FD_CREATED, COMMENT_FD_CREATED
				, COLUMN_TYPE_INFORMATION_ID, 0, 920, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_FD_UPDATE, NAME_FD_UPDATE, COMMENT_FD_UPDATE
				, COLUMN_TYPE_DATE, 0, 930, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_FD_UPDATED, NAME_FD_UPDATED, COMMENT_FD_UPDATED
				, COLUMN_TYPE_INFORMATION_ID, 0, 940, I_FD_TableColumn.IS_PRIMARY_NO);
		
		//テーブル生成
		createDBTable(env, Table_Name);
		
		//採番情報登録
		FDNumbering num  = new FDNumbering();
		num.add(env, tableId, 1000001, 0);
		
		env.getLog().add(env, FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "プロセス情報テーブル生成完了");
	}

	/**
	 * プロセス開始処理<br>
	 * <p>プロセス情報を新規に作成し、プロセスの開始を記録する。</p>
	 * @author officine-hide.com
	 * @since 2020/09/17
	 * @param env 環境情報
	 * @param processName プロセス名
	 */
	public void startProcess(FD_EnvData env, String processName) {
		X_FD_Process process = new X_FD_Process(env);
		process.setValue(COLUMNNAME_FD_PROCESS_ID, getNewID(env, getTableID(env, Table_Name)));
		process.setValue(COLUMNNAME_PROCESS_NAME, processName);
		process.setValue(NAME_PROCESS_START, new Timestamp(new Date().getTime()));
		process.save(env);
		env.setProcessId(process.getIntOfValue(COLUMNNAME_FD_PROCESS_ID));
	}

}
