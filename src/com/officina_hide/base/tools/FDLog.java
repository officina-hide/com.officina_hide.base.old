package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Log;
import com.officina_hide.base.model.I_FD_TableColumn;
import com.officina_hide.base.model.X_FD_Log;

/**
 * ログ情報クラス<br>
 * @author officine-hide.com ueno
 * @version 2.00
 * @since 2020/09/11
 */
public class FDLog extends FD_DB implements I_FD_Log {

	/**
	 * ログ情報テーブル生成
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FDTable table = new FDTable();
		int tableId = table.addData(env, 0, Table_Name, Name, "");
		
		//テーブル項目情報生成
		FDTableColumn column = new FDTableColumn();
		column.add(env, tableId, COLUMNNAME_FD_LOG_ID, NAME_FD_LOG_ID, COMMENT_FD_LOG_ID
				, COLUMN_TYPE_INFORMATION_ID, 0, 10, I_FD_TableColumn.IS_PRIMARY_YES);
		column.add(env, tableId, COLUMNNAME_LOG_PROCESS_ID, NAME_LOG_PROCESS_ID, COMMENT_LOG_PROCESS_ID
				, COLUMN_TYPE_INFORMATION_ID, 0, 20, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_MESSAGE_TYPE_CODE, NAME_MESSAGE_TYPE_CODE, COMMENT_MESSAGE_TYPE_CODE
				, COLUMN_TYPE_NUMBER, 0, 30, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_MESSAGE_MODE_CODE, NAME_MESSAGE_MODE_CODE, COMMENT_MESSAGE_MODE_CODE
				, COLUMN_TYPE_NUMBER, 0, 40, I_FD_TableColumn.IS_PRIMARY_NO);
		column.add(env, tableId, COLUMNNAME_MESSAGE_TEXT, NAME_MESSAGE_TEXT, COMMENT_MESSAGE_TEXT
				, COLUMN_TYPE_TEXT, 200, 50, I_FD_TableColumn.IS_PRIMARY_NO);
		
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
		FDNumbering num = new FDNumbering();
		num.add(env, tableId, 1000001, 0);
		
		env.getLog().add(env, FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "ログ情報テーブル生成完了");		
		
	}

	/**
	 * ログ情報登録<br>
	 * @author officine-hide.com
	 * @since 2020/09/17
	 * @param env 環境情報
	 * @param typeCD メッセージ種別
	 * @param modeCD メッセージモード
	 * @param message メッセージ本文
	 */
	public void addData(FD_EnvData env, int typeCD, int modeCD, String message) {
		X_FD_Log log = new X_FD_Log(env);
		log.setValue(env, COLUMNNAME_FD_LOG_ID, getNewID(env, getTableID(env, Table_Name)));
		log.setValue(env, COLUMNNAME_LOG_PROCESS_ID, env.getProcessId());
		log.setValue(env, COLUMNNAME_MESSAGE_TYPE_CODE, typeCD);
		log.setValue(env, COLUMNNAME_MESSAGE_MODE_CODE, modeCD);
		log.setValue(env, COLUMNNAME_MESSAGE_TEXT, changeEscape(message));
		log.save(env);
	}

}
