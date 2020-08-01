package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.model.FD_DB;

/**
 * 基本テーブル生成<br>
 * <p>以下のテーブルを生成する。<br>
 * ・テーブル情報(FD_Table)</p>
 * @author ueno hideo
 * @version 1.20
 * @since 2020/07/14
 */
public class CreateBaseTable {

	/**
	 * データベース基本クラス
	 */
	private FD_DB DB = null;
	
	/**
	 * コンストラクタ－<br>
	 * 生成処理<br>
	 * @author ueno hideo
	 * @param env 
	 * @since 2020/07/14
	 */
	public CreateBaseTable(FD_EnvData env) {
		//データベース情報
		DB = new FD_DB(env);
		//テーブル情報生成
		createTable(env);
		//テーブル項目情報生成
		createTableColumn(env);
		//リファレンス情報生成
		createRefarence(env);
		//採番情報生成
		createNumbering(env);
		//リファレンスパラメータ情報生成
		createReferenceParameter(env);
		//テーフル項目情報追加
		entryTableColumnData(env);
		//テーブル情報モデル生成
		new CreateModel(env, "FD_Table");
		new CreateModel(env, "FD_TableColumn");
		new CreateModel(env, "FD_Reference");
		new CreateModel(env, "FD_RefParam");
		new CreateModel(env, "FD_Numbering");
		
	}

	/**
	 * テーフル情報テーブル生成
	 * @author ueno hideo
	 * @since 1.20 2020/07/15
	 * @param env 環境情報
	 */
	private void createTable(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		//既に登録されているテーフル情報を削除する。
		sql.append("DROP TABLE IF EXISTS FD_Table");
		DB.execute(env, sql.toString());
		//テーフル情報を生成する。
		sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS FD_Table  (");
		sql.append("FD_Table_ID INT UNSIGNED NOT NULL PRIMARY KEY COMMENT 'テーブル情報ID'").append(",");
		sql.append("Table_Name Varchar(100)  COMMENT 'テーブル物理名名'").append(",");
		sql.append("FD_Name Varchar(100)  COMMENT 'テーブル論理名'").append(",");
		sql.append("fD_COMMENT Text  COMMENT '説明'").append(",");
		sql.append("FD_Create DATETIME  COMMENT '登録日'").append(",");
		sql.append("FD_Created INT UNSIGNED  COMMENT '登録者ID'").append(",");
		sql.append("FD_Update DATETIME  COMMENT '更新日'").append(",");
		sql.append("FD_Updated INT UNSIGNED  COMMENT '更新者ID'");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='テーブル情報'");
		DB.execute(env, sql.toString());
		//テーブル情報の情報を登録する。
		DB.addTableData(env, 101, "FD_Table", "テーブル情報","テーブルに関する情報を管理する。");
		
		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "Table Information created.");
	}

	/**
	 * テーブル項目情報生成<br>
	 * @author ueno hideo
	 * @since 1.20 2020/07/16
	 * @param env 環境情報
	 */
	private void createTableColumn(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		//既に登録されているテーブル項目情報を削除します。
		sql.append("DROP TABLE IF EXISTS FD_TableColumn");
		DB.execute(env, sql.toString());
		//テーブル項目情報を生成する。
		sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS FD_TableColumn  (");
		sql.append("FD_TableColumn_ID INT UNSIGNED NOT NULL PRIMARY KEY COMMENT 'テーブル項目情報ID'").append(",");
		sql.append("FD_Table_ID INT UNSIGNED NOT NULL COMMENT 'テーブル情報ID'").append(",");
		sql.append("Column_Name Varchar(100) NULL COMMENT 'テーブル項目名'").append(",");
		sql.append("Column_Type_ID INT UNSIGNED NOT NULL COMMENT 'テーブル項目種別ID（リファレンスID）'").append(",");
		sql.append("Column_Size INT UNSIGNED COMMENT 'テーブル項目桁数'").append(",");
		sql.append("FD_Name Varchar(100)  COMMENT 'テーブル項目論理名'").append(",");
		sql.append("FD_Comment Text  COMMENT '説明'").append(",");
		sql.append("Primary_Key_Check TINYINT(1) COMMENT 'プライマリーキー判定'").append(",");
		sql.append("Column_Sort_Order INT UNSIGNED COMMENT '項目並び順'").append(",");
		sql.append("FD_Create DATETIME  COMMENT '登録日'").append(",");
		sql.append("FD_Created INT UNSIGNED  COMMENT '登録者ID'").append(",");
		sql.append("FD_Update DATETIME  COMMENT '更新日'").append(",");
		sql.append("FD_Updated INT UNSIGNED  COMMENT '更新者ID'");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='テーブル項目情報'");			
		DB.execute(env, sql.toString());
		//テーブル項目情報をテーブル情報に登録する。
		DB.addTableData(env, 102, "FD_TableColumn","テーブル項目情報","テーブルで使用する項目に課する情報を管理する。");

		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "Table Column Information created.");
	}

	/**
	 * リファレンス情報生成<br>
	 * @author ueno hideo
	 * @since 1.20 2020/07/16
	 * @param env
	 */
	private void createRefarence(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		//既に登録されているリファレンス情報を削除します。
		sql.append("DROP TABLE IF EXISTS FD_Reference");
		DB.execute(env, sql.toString());
		//リファレンス情報を生成する。
		sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS FD_Reference  (");
		sql.append("FD_Reference_ID INT UNSIGNED NOT NULL COMMENT 'リファレンス情報ID'").append(",");
		sql.append("Reference_Name Varchar(100) COMMENT 'リファレンス名'").append(",");
		sql.append("FD_Create DATETIME  COMMENT '登録日'").append(",");
		sql.append("FD_Created INT UNSIGNED  COMMENT '登録者ID'").append(",");
		sql.append("FD_Update DATETIME  COMMENT '更新日'").append(",");
		sql.append("FD_Updated INT UNSIGNED  COMMENT '更新者ID'");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='リファレンス情報'");			
		DB.execute(env, sql.toString());
		//リファレンス情報をテーブル情報に登録する。
		DB.addTableData(env, 103, "FD_Reference", "リファレンス情報","システムで管理する項目の辞書としての管理を行う。");
		//リファレンス情報登録
		DB.addReferenceData(1000001,"情報ID");
		DB.addReferenceData(1000002,"テキスト");
		DB.addReferenceData(1000003,"自然数");
		DB.addReferenceData(1000004,"複数行テキスト");
		DB.addReferenceData(1000005,"日時");
		DB.addReferenceData(1000006,"YESNO");
		DB.addReferenceData(1000007,"ClassName");

		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "Reference Information created.");
	}

	/**
	 * リファレンス用パラメータ情報生成<br>
	 * @author ueno hideo
	 * @since 1.20 2020/07/16
	 * @param env 環境情報
	 */
	private void createReferenceParameter(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		//既に登録されている採番情報を削除します。
		sql.append("DROP TABLE IF EXISTS FD_RefParam");
		DB.execute(env, sql.toString());
		//リファレンス用パラメータ情報を生成する。
		sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS FD_RefParam  (");
		sql.append("FD_RefParam_ID INT UNSIGNED NOT NULL COMMENT 'リファレンス用パラメータ情報ID'").append(",");
		sql.append("FD_Reference_ID INT UNSIGNED NOT NULL COMMENT 'リファレンス情報ID'").append(",");
		sql.append("Parameter_Name Varchar(100) COMMENT 'パラメータ名'").append(",");
		sql.append("Parameter_Type_ID INT UNSIGNED COMMENT 'パラメータ種別ID'").append(",");
		sql.append("Parameter_Data Varchar(200) COMMENT 'パラメータ情報'").append(",");
		sql.append("FD_Comment Text  COMMENT '説明'").append(",");
		sql.append("FD_Create DATETIME  COMMENT '登録日'").append(",");
		sql.append("FD_Created INT UNSIGNED  COMMENT '登録者ID'").append(",");
		sql.append("FD_Update DATETIME  COMMENT '更新日'").append(",");
		sql.append("FD_Updated INT UNSIGNED  COMMENT '更新者ID'");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='採番情報'");
		DB.execute(env, sql.toString());
		//リファレンス用パラメータ情報をテーブル情報に登録する。
		DB.addTableData(env, 105, "FD_RefParam", "リファレンス用パラメータ情報", "リファレンス情報で使用する各種情報を管理する。");
		//リファレンス用パラメータ情報登録
		DB.addRefParamData(DB.getReferenceID(env, "テキスト"), "ColumnTypeClass", "ClassName", "com.officina_hide.base.model.FD_Text",
				"テキストを扱うクラス");
		DB.addRefParamData(DB.getReferenceID(env, "情報ID"), "ColumnTypeClass", "ClassName", "com.officina_hide.base.model.FD_ImformationID",
				"情報IDを扱うクラス");
		DB.addRefParamData(DB.getReferenceID(env, "複数行テキスト"), "ColumnTypeClass", "ClassName", "com.officina_hide.base.model.FD_FieldText",
				"複数行テキストを扱うクラス");
		DB.addRefParamData(DB.getReferenceID(env, "日時"), "ColumnTypeClass", "ClassName", "com.officina_hide.base.model.FD_Date",
				"日時を扱うクラス");
		DB.addRefParamData(DB.getReferenceID(env, "YESNO"), "ColumnTypeClass", "ClassName", "com.officina_hide.base.model.FD_YESNO",
				"YESNO項目を扱うクラス");
		DB.addRefParamData(DB.getReferenceID(env, "自然数"), "ColumnTypeClass", "ClassName", "com.officina_hide.base.model.FD_Number",
				"自然数を扱うクラス");
	}

	/**
	 * 採番情報生成<br>
	 * @author ueno hideo
	 * @since 2020/07/16
	 * @param env 環境情報
	 */
	private void createNumbering(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		//既に登録されている採番情報を削除します。
		sql.append("DROP TABLE IF EXISTS FD_Numbering");
		DB.execute(env, sql.toString());
		//採番情報を生成する。
		sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS FD_Numbering  (");
		sql.append("FD_Numbering_ID INT UNSIGNED NOT NULL COMMENT '採番情報ID'").append(",");
		sql.append("FD_Table_ID INT UNSIGNED NOT NULL COMMENT 'テーブル情報ID'").append(",");
		sql.append("Current_Number INT UNSIGNED COMMENT '現在値'").append(",");
		sql.append("Start_Number INT UNSIGNED COMMENT '開始値'").append(",");
		sql.append("FD_Create DATETIME  COMMENT '登録日'").append(",");
		sql.append("FD_Created INT UNSIGNED  COMMENT '登録者ID'").append(",");
		sql.append("FD_Update DATETIME  COMMENT '更新日'").append(",");
		sql.append("FD_Updated INT UNSIGNED  COMMENT '更新者ID'");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='採番情報'");
		DB.execute(env, sql.toString());
		//採番情報をテーブル情報に登録する。
		DB.addTableData(env, 104, "FD_Numbering", "採番情報","テーブル毎に情報に対して付与する情報IDの採番を管理する。");
		//採番情報登録
		DB.addNumberingData(101, 101, 0, 201);	//テーブル情報
		DB.addNumberingData(102, 102, 0, 1000001);	//テーブル項目情報
		DB.addNumberingData(103, 103, 0, 1000001);	//リファレンス情報情報
		DB.addNumberingData(104, 104, 0, 1000001);	//採番情報
		DB.addNumberingData(105, 105, 0, 1000001);	//リファレンス用パラメータ情報

		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "Numbering Information created.");
	}

	/**
	 * テーブル項目情報登録<br>
	 * @author ueno hideo
	 * @since 1.20 2020/07/16
	 * @param env 環境情報
	 */
	private void entryTableColumnData(FD_EnvData env) {
		//テーフル情報
		int tableId = DB.getTableID(env, "FD_Table");
		DB.addTableColumnData(env, tableId, "FD_Table_ID", "情報ID", 0, "テーブル情報ID","テーブル情報を識別するためのID", 10, true);
		DB.addTableColumnData(env, tableId, "Table_Name", "テキスト", 100, "テーブル物理名","テーブルの物理名", 20, false);
		DB.addTableColumnData(env, tableId, "FD_Name", "テキスト", 100, "テーブル論理名","テーブルの論理名", 30, false);
		DB.addTableColumnData(env, tableId, "FD_COMMENT", "複数行テキスト", 0, "説明","テーブルの説明", 30, false);
		DB.addTableColumnData(env, tableId, "FD_Create", "日時", 0, "登録日","テーブル情報の登録日", 900, false);
		DB.addTableColumnData(env, tableId, "FD_Created", "情報ID", 0, "登録者ID","テーブル情報の登録者のID", 910, false);
		DB.addTableColumnData(env, tableId, "FD_Update", "日時", 0, "更新日","テーブル情報の更新日", 920, false);
		DB.addTableColumnData(env, tableId, "FD_Updated", "情報ID", 0, "更新者ID","テーブル情報の更新者のID", 930, false);
		//テーフル項目情報
		tableId = DB.getTableID(env, "FD_TableColumn");
		DB.addTableColumnData(env, tableId, "FD_TableColumn_ID", "情報ID", 0, "テーブル項目情報ID","テーブル項目情報を識別するためのID", 10, true);
		DB.addTableColumnData(env, tableId, "FD_Table_ID", "情報ID", 0, "テーブル情報ID","テーブル項目を紐づけるテーブルのID", 20, false);
		DB.addTableColumnData(env, tableId, "Column_Name", "テキスト", 100, "テーブル項目物理名","テーブル項目の物理名", 30, false);
		DB.addTableColumnData(env, tableId, "Column_Type_ID", "情報ID", 0, "種別ID（リファレンス情報ID）","テーブル項目の属性を表すリファレンス情報のID", 40, false);
		DB.addTableColumnData(env, tableId, "Column_Size", "自然数", 0, "桁数","テーブル項目の桁数", 50, false);
		DB.addTableColumnData(env, tableId, "FD_Name", "テキスト", 100, "物理名","テーブル項目の物理名", 60, false);
		DB.addTableColumnData(env, tableId, "FD_Comment", "複数行テキスト", 0, "説明","テーブル項目の説明", 70, false);
		DB.addTableColumnData(env, tableId, "Primary_Key_Check", "YESNO", 0, "プライマリーキー判定","テーブル項目がプライマリーKeyの時にYesをセットする。", 80, false);
		DB.addTableColumnData(env, tableId, "Column_Sort_Order", "自然数", 0, "項目並び順","テーブル項目の一覧表示を行う時の順番", 80, false);
		DB.addTableColumnData(env, tableId, "FD_Create", "日時", 0, "登録日","テーブル項目情報の登録日", 900, false);
		DB.addTableColumnData(env, tableId, "FD_Created", "情報ID", 0, "登録者ID","テーブル項目情報の登録者のID", 910, false);
		DB.addTableColumnData(env, tableId, "FD_Update", "日時", 0, "更新日","テーブル項目情報の更新日", 920, false);
		DB.addTableColumnData(env, tableId, "FD_Updated", "情報ID", 0, "更新者ID","テーブル項目情報の更新者のID", 930, false);
		//リファレンス情報
		tableId = DB.getTableID(env, "FD_Reference");
		DB.addTableColumnData(env, tableId, "FD_Reference_ID", "情報ID", 0, "リファレンス情報ID","リファレンス情報を識別するためのID", 10, true);
		DB.addTableColumnData(env, tableId, "Reference_Name", "テキスト", 100, "リファレンス名","リファレンス情報の名称", 20, false);
		DB.addTableColumnData(env, tableId, "FD_Create", "日時", 0, "登録日","リファレンス情報の登録日", 900, false);
		DB.addTableColumnData(env, tableId, "FD_Created", "情報ID", 0, "登録者ID","リファレンス情報の登録者のID", 910, false);
		DB.addTableColumnData(env, tableId, "FD_Update", "日時", 0, "更新日","リファレンス情報の更新日", 920, false);
		DB.addTableColumnData(env, tableId, "FD_Updated", "情報ID", 0, "更新者ID","リファレンス情報の更新者のID", 930, false);
		//採番情報
		tableId = DB.getTableID(env, "FD_Numbering");
		DB.addTableColumnData(env, tableId, "FD_Numbering_ID", "情報ID", 0, "採番情報ID","採番情報を識別するためのID", 10, true);
		DB.addTableColumnData(env, tableId, "FD_Table_ID", "情報ID", 0, "テーブル情報ID","採番の対象となるテーブルの情報ID", 20, false);
		DB.addTableColumnData(env, tableId, "Current_Number", "自然数", 0, "現在値","採番された値", 30, false);
		DB.addTableColumnData(env, tableId, "Start_Number", "自然数", 0, "開始値","採番を開始する値", 40, false);
		DB.addTableColumnData(env, tableId, "FD_Create", "日時", 0, "登録日","採番情報の登録日", 900, false);
		DB.addTableColumnData(env, tableId, "FD_Created", "情報ID", 0, "登録者ID","採番情報の登録者のID", 910, false);
		DB.addTableColumnData(env, tableId, "FD_Update", "日時", 0, "更新日","採番情報の更新日", 920, false);
		DB.addTableColumnData(env, tableId, "FD_Updated", "情報ID", 0, "更新者ID","採番情報の更新者のID", 930, false);
		//リファレンス用パラメータ情報
		tableId = DB.getTableID(env, "FD_RefParam");
		DB.addTableColumnData(env, tableId, "FD_RefParam_ID", "情報ID", 0, "リファレンス用パラメータ情報ID","リファレンス用パラメータ情報を識別するためのID", 10, true);
		DB.addTableColumnData(env, tableId, "FD_Reference_ID", "情報ID", 0, "リファレンス情報ID","リファレンス用パラメータ情報を紐づけるリファレンス情報のID", 20, false);
		DB.addTableColumnData(env, tableId, "Parameter_Name", "テキスト", 100, "パラメータ名","パラメータを識別するための名称", 30, false);
		DB.addTableColumnData(env, tableId, "Parameter_Type_ID", "情報ID", 0, "パラメータ種別ID","パラメータの種別を表すID（リファレンス情報ID）", 40, false);
		DB.addTableColumnData(env, tableId, "Parameter_Data", "テキスト", 200, "パラメータ情報","パラメータの情報", 50, false);
		DB.addTableColumnData(env, tableId, "FD_Comment", "複数行テキスト", 0, "説明","パラメータの説明", 60, false);
		DB.addTableColumnData(env, tableId, "FD_Create", "日時", 0, "登録日","リファレンス用パラメータ情報の登録日", 900, false);
		DB.addTableColumnData(env, tableId, "FD_Created", "情報ID", 0, "登録者ID","リファレンス用パラメータ情報の登録者のID", 910, false);
		DB.addTableColumnData(env, tableId, "FD_Update", "日時", 0, "更新日","リファレンス用パラメータ情報の更新日", 920, false);
		DB.addTableColumnData(env, tableId, "FD_Updated", "情報ID", 0, "更新者ID","リファレンス用パラメータ情報の更新者のID", 930, false);
	}

}
