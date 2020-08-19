package com.officina_hide.base.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;

/**
 * 基本情報生成<br>
 * <p>パッケージの構築で基本となる情報の内、手作業で作成する必要があるリソースを生成する。</p>
 * <p>Generate the resources that need to be created manually, out of the basic information for building a package.</p>
 * @author ueno hideo
 * @version 1.21
 * @since 2020/08/13
 */
public class CreateBaseResource extends FD_DB {
	/*
	 * 次の処理を行う。
	 * テーブル情報テーブルの構築 (createTableInfoTable)
	 * リファレンス項目情報テーブルの構築 (createReferenceTable)
	 * テーブル項目用リファレンスの登録 (addReferenceData)
	 * テーブル項目情報テーブルの構築 (createTabelItemTable)
	 */

	/** リファレンス採番 */
	private int referenceID = 1000001;

	/**
	 * コンストラクター<br>
	 * @author ueno hideo
	 * @since 1.21 2020/08/13
	 * @param env 環境情報
	 */
	public CreateBaseResource(FD_EnvData env) {
		/** テーブル情報テーブルの構築 */
		createTableInfoTable(env);
		/** リファレンス項目情報テーブルの構築 */
		createReferenceTable(env);
		/** リファレンスグループ情報テーブルの構築 */
		createReferenceGroupTable(env);
		/** テーブル項目用のリファレンスグループ情報登録 */
		int refGroupID = 100001;
		addReferenceGroupData(env, refGroupID, "Tebla_Item", "テーブル項目");
		/** テーブル項目属性用のリファレンス情報登録 */
		addTableColumnReference(env, refGroupID);
		/** テーブル項目情報テーブルの構築 */
		createTableColumnTable(env);
		/** 採番情報テーブルの構築 */
		createNumberingTable(env);
		/** 採番情報、テーブル情報登録 */
		addNumbering(env);
		/** テーブル項目情報登録 */
		addTableColumn(env);
	}

	/**
	 * テーフル情報テーブル生成
	 * @author ueno hideo
	 * @since 1.21 2020/08/13
	 * @param env 環境情報
	 */
	private void createTableInfoTable(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		//既に登録されているテーフル情報を削除する。
		sql.append("DROP TABLE IF EXISTS FD_Table");
		execute(env, sql.toString());
		//テーフル情報を生成する。
		sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS FD_Table  (");
		sql.append("FD_Table_ID INT UNSIGNED NOT NULL PRIMARY KEY COMMENT 'テーブル情報ID'").append(",");
		sql.append("Table_Name Varchar(100)  COMMENT 'テーブル物理名'").append(",");
		sql.append("FD_Name Varchar(100)  COMMENT 'テーブル論理名'").append(",");
		sql.append("fD_COMMENT Text  COMMENT '説明'").append(",");
		sql.append("FD_Create DATETIME  COMMENT '登録日'").append(",");
		sql.append("FD_Created INT UNSIGNED  COMMENT '登録者ID'").append(",");
		sql.append("FD_Update DATETIME  COMMENT '更新日'").append(",");
		sql.append("FD_Updated INT UNSIGNED  COMMENT '更新者ID'");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='テーブル情報'");
		execute(env, sql.toString());
	}

	/**
	 * テーブル項目テーブル構築<br>
	 * @author ueno hideo
	 * @since 1.21 2020/08/15
	 * @param env 環境情報
	 */
	private void createTableColumnTable(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		//既に登録されているテーブル項目情報を削除します。
		sql.append("DROP TABLE IF EXISTS FD_TableColumn");
		execute(env, sql.toString());
		//テーブル項目情報を生成する。
		sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS FD_TableColumn  (");
		sql.append("FD_TableColumn_ID INT UNSIGNED NOT NULL PRIMARY KEY COMMENT 'テーブル項目情報ID'").append(",");
		sql.append("FD_Table_ID INT UNSIGNED NOT NULL  COMMENT 'テーブル情報ID'").append(",");
		sql.append("TableColumn_Name Varchar(100) COMMENT 'テーブル項目名'").append(",");
		sql.append("FD_Name Varchar(100) COMMENT 'テーフル項目表示名'").append(",");
		sql.append("FD_COMMENT Varchar(3000) COMMENT 'テーブル項目説明'").append(",");
		sql.append("TableColumn_Type_ID INT UNSIGNED COMMENT 'テーブル項目属性ID'").append(",");
		sql.append("TableColumn_Size INT UNSIGNED COMMENT 'テーブル項目桁数'").append(",");
		sql.append("IS_Null INT COMMENT 'NULL必須判定'").append(",");
		sql.append("IS_Primary INT COMMENT 'プライマリーKey判定'").append(",");
		sql.append("FD_Create DATETIME  COMMENT '登録日'").append(",");
		sql.append("FD_Created INT UNSIGNED  COMMENT '登録者ID'").append(",");
		sql.append("FD_Update DATETIME  COMMENT '更新日'").append(",");
		sql.append("FD_Updated INT UNSIGNED  COMMENT '更新者ID'");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='テーブル項目情報'");
		execute(env, sql.toString());
	}

	/**
	 * リファレンス情報ID所得<br>
	 */
	@Override
	public int getReferenceID(FD_EnvData env, String referenceName) {
		int id = 0;
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT FD_Reference_ID FROM FD_Reference ");
			sql.append("WHERE Reference_Name = ").append(FD_SQ).append(referenceName).append(FD_SQ);
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				id = rs.getInt("FD_Reference_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, stmt);
		}
		return id;
	}

	/**
	 * リファレンス情報テーブル生成
	 * @author ueno hideo
	 * @since 1.21 2020/08/13
	 * @param env 環境情報
	 */
	private void createReferenceTable(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		//既に登録されているリファレンス情報を削除します。
		sql.append("DROP TABLE IF EXISTS FD_Reference");
		execute(env, sql.toString());
		//リファレンス情報を生成する。
		sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS FD_Reference  (");
		sql.append("FD_Reference_ID INT UNSIGNED NOT NULL PRIMARY KEY COMMENT 'リファレンス情報ID'").append(",");
		sql.append("Reference_Name Varchar(100) COMMENT 'リファレンス名'").append(",");
		sql.append("FD_Name Varchar(100) COMMENT 'リファレンス表示名'").append(",");
		sql.append("Reference_Group_ID INT UNSIGNED COMMENT 'リファレンスグループ情報ID'").append(",");
		sql.append("Reference_Class varchar(200) COMMENT 'リファレンス処理クラスURI'").append(",");
		sql.append("FD_Create DATETIME  COMMENT '登録日'").append(",");
		sql.append("FD_Created INT UNSIGNED  COMMENT '登録者ID'").append(",");
		sql.append("FD_Update DATETIME  COMMENT '更新日'").append(",");
		sql.append("FD_Updated INT UNSIGNED  COMMENT '更新者ID'");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='リファレンス情報'");
		execute(env, sql.toString());
	}

	/**
	 * リファレンスグループ情報生成<br>
	 * @author ueno hideo
	 * @since 1.21 2020/08/15
	 * @param env 環境情報
	 */
	private void createReferenceGroupTable(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		//既に登録されているリファレンスグループ情報を削除します。
		sql.append("DROP TABLE IF EXISTS FD_RefGroup");
		execute(env, sql.toString());
		//リファレンスグループ情報テーブルを生成する。
		sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS FD_RefGroup  (");
		sql.append("FD_RefGroup_ID INT UNSIGNED NOT NULL PRIMARY KEY COMMENT 'リファレンスグループ情報ID'").append(",");
		sql.append("ReferenceGroup_Name Varchar(100) COMMENT 'リファレンスグループ名'").append(",");
		sql.append("FD_Name Varchar(100) COMMENT 'リファレンスグループ表示名'" ).append(",");
		sql.append("FD_Create DATETIME  COMMENT '登録日'").append(",");
		sql.append("FD_Created INT UNSIGNED  COMMENT '登録者ID'").append(",");
		sql.append("FD_Update DATETIME  COMMENT '更新日'").append(",");
		sql.append("FD_Updated INT UNSIGNED  COMMENT '更新者ID'");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='リファレンスグループ情報'");			
		execute(env, sql.toString());
	}

	/**
	 * 採番情報テーブルの構築<br>
	 * @author ueno hideo
	 * @since 1.21 2020/08/15
	 * @param env 環境情報
	 */
	private void createNumberingTable(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		//既に登録されている採番情報を削除します。
		sql.append("DROP TABLE IF EXISTS FD_Numbering");
		execute(env, sql.toString());
		//採番情報テーブルを生成する。
		sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS FD_Numbering  (");
		sql.append("FD_Numbering_ID INT UNSIGNED NOT NULL PRIMARY KEY COMMENT '採番情報ID'").append(",");
		sql.append("FD_Table_ID INT UNSIGNED NOT NULL COMMENT 'テーブル情報ID'").append(",");
		sql.append("Start_Number INT UNSIGNED COMMENT '開始値'").append(",");
		sql.append("Current_Number INT UNSIGNED COMMENT '現在値'").append(",");
		sql.append("FD_Create DATETIME  COMMENT '登録日'").append(",");
		sql.append("FD_Created INT UNSIGNED  COMMENT '登録者ID'").append(",");
		sql.append("FD_Update DATETIME  COMMENT '更新日'").append(",");
		sql.append("FD_Updated INT UNSIGNED  COMMENT '更新者ID'");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='採番情報'");			
		execute(env, sql.toString());
	}

	/**
	 * テーブル項目情報登録<br>
	 * @author ueno hideo
	 * @since 1.21 2020/08/15
	 * @param env 環境情報
	 */
	private void addTableColumn(FD_EnvData env) {
		int tableId = getTableId(env, "FD_Table");
		addTableColumnData(env, tableId, "FD_Table_ID", "FD_Information_ID", 0, "テーブル情報ID", "テーブル情報を識別するためのID", 10, true);
	}

	/**
	 *テーブル項目登録<br>
	 *<p>SQL直処理版</p>
	 */
	@Override
	public void addTableColumnData(FD_EnvData env, int tableId, String culimnName, String columnType, int size,
			String name, String comment, int order, boolean priKey) {
		int id = getNewID(env, tableId);
		int typeId = getReferenceID(env, columnType);
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO FD_TableColumn SET ");
		sql.append("FD_TableColumn_ID = ").append(id).append(",");
		sql.append("FD_Table_ID = ").append(tableId).append(",");
		sql.append("TableColumn_Name = ").append(FD_SQ).append(culimnName).append(FD_SQ).append(",");
		sql.append("FD_Name = ").append(FD_SQ).append(name).append(FD_SQ).append(",");
		sql.append("FD_COMMENT = ").append(FD_SQ).append(comment).append(FD_SQ).append(",");
		sql.append("TableColumn_Type_ID = ").append(typeId).append(",");
		sql.append("TableColumn_Size = ").append(size).append(",");
		if(priKey) {
			sql.append("IS_NULL = 1").append(",");
			sql.append("IS_Primary = 1").append(",");
		} else {
			sql.append("IS_NULL = 0").append(",");		
			sql.append("IS_Primary = 0").append(",");
		}
		sql.append("FD_Create = ").append(FD_SQ).append(dateFormat.format(new Date())).append(FD_SQ).append(",");
		sql.append("FD_Created = ").append(env.getSystemUserID()).append(",");
		sql.append("FD_Update = ").append(FD_SQ).append(dateFormat.format(new Date())).append(FD_SQ).append(",");
		sql.append("FD_Updated = ").append(env.getSystemUserID());
		execute(env, sql.toString());
	}

	/**
	 * テーブル情報ID取得<br>
	 * @author ueno hideo
	 * @since 1.21 2020/08/15
	 * @param env 環境情報
	 * @param tableName テーブル名
	 * @return テーブル情報ID
	 */
	private int getTableId(FD_EnvData env, String tableName) {
		int id = 0;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ").append(tableName).append("_ID FROM FD_Table ");
			sql.append("WHERE TABLE_NAME = ").append(FD_SQ).append(tableName).append(FD_SQ);
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				id = rs.getInt(tableName+"_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, stmt);
		}
		
		return id;
	}

	/**
	 * テーブル項目用のリファレンス情報を使っていました登録する。
	 * @author ueno hideo
	 * @since 1.21 2020/08/15
	 * @param env 環境情報
	 * @param refGroupID リファレンスグループ情報ID
	 */
	private void addTableColumnReference(FD_EnvData env, int refGroupID) {
//		int referenceID = 1000001;
		addReferenceData(env, referenceID++, "FD_Information_ID", "情報ID", refGroupID, "com.officina_hide.base.model.FD_ImformationID");
		addReferenceData(env, referenceID++, "FD_Text", "テキスト", refGroupID, "com.officina_hide.base.model.FD_Text");
		addReferenceData(env, referenceID++, "FD_Field_Text", "複数行テキスト", refGroupID, "com.officina_hide.base.model.FD_FieldText");
		addReferenceData(env, referenceID++, "FD_Date", "日時", refGroupID, "com.officina_hide.base.model.FD_Date");
		addReferenceData(env, referenceID++, "FD_YESNO", "YESNO", refGroupID, "com.officina_hide.base.model.FD_YESNO");
		addReferenceData(env, referenceID, "FD_Number", "自然数", refGroupID, "com.officina_hide.base.model.FD_Number");
	}

	/**
	 * リファレンス情報登録<br>
	 * @author ueno hideo
	 * @since 1.21 2020/08/15
	 * @param env 環境情報
	 * @param referenceID リファレンス情報ID
	 * @param referenceName リファレンス名
	 * @param name リファレンス表示名
	 * @param refGroupID リファレンスグループ情報ID
	 * @param refClassURI リファレンス処理クラスURI
	 */
	private void addReferenceData(FD_EnvData env, int referenceID, String referenceName, String name, int refGroupID,
			String refClassURI) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO FD_Reference SET ");
		sql.append("FD_Reference_ID = ").append(referenceID).append(",");
		sql.append("Reference_Name = ").append(FD_SQ).append(referenceName).append(FD_SQ).append(",");
		sql.append("FD_Name = ").append(FD_SQ).append(name).append(FD_SQ).append(",");
		sql.append("Reference_Group_ID = ").append(refGroupID).append(",");
		sql.append("Reference_Class = ").append(FD_SQ).append(refClassURI).append(FD_SQ).append(",");
		sql.append("FD_Create = ").append(FD_SQ).append(dateFormat.format(new Date())).append(FD_SQ).append(",");
		sql.append("FD_Created = ").append(env.getSystemUserID()).append(",");
		sql.append("FD_Update = ").append(FD_SQ).append(dateFormat.format(new Date())).append(FD_SQ).append(",");
		sql.append("FD_Updated = ").append(env.getSystemUserID());
		execute(env, sql.toString());
	}

	/**
	 * リファレンスグループ情報登録<br>
	 * @author ueno hideo
	 * @since 2020/08/15 
	 * @param env 環境情報
	 * @param refGroupID リファレンスグループ情報ID
	 * @param groupName リファレンスグループ名
	 * @param name リファレンスグループ表示名
	 * @return リファレンスグループID
	 */
	private void addReferenceGroupData(FD_EnvData env, int refGroupID, String groupName, String name) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO FD_RefGroup SET ");
		sql.append("FD_RefGroup_ID = ").append(refGroupID).append(",");
		sql.append("ReferenceGroup_name = ").append(FD_SQ).append(groupName).append(FD_SQ).append(",");
		sql.append("FD_Name = ").append(FD_SQ).append(name).append(FD_SQ).append(",");
		sql.append("FD_Create = ").append(FD_SQ).append(dateFormat.format(new Date())).append(FD_SQ).append(",");
		sql.append("FD_Created = ").append(env.getSystemUserID()).append(",");
		sql.append("FD_Update = ").append(FD_SQ).append(dateFormat.format(new Date())).append(FD_SQ).append(",");
		sql.append("FD_Updated = ").append(env.getSystemUserID());
		execute(env, sql.toString());
	}

	/**
	 * 採番情報・テーブル情報登録<br>
	 * @author ueno hideo
	 * @since 1.21 2020/08/15
	 * @param env 環境情報
	 */
	private void addNumbering(FD_EnvData env) {
		/** 最初の情報登録（採番情報自身） */
		addNumberingDataOrg(env, 1001, 1001, 1001, 1001);
		/** テーブル情報採番　*/
		int numberingId = getNewID(env, 1001);
		addNumberingDataOrg(env, numberingId, 1002, 1001, 1002);
		/** テーブル情報、テーブルに付随する採番情報登録 */
		addTableData(env, 1001, "FD_Numbering", "採番情報");
		addTableData(env, 1002, "FD_Table", "テーブル情報");
		int tableId = addTableData(env, 0, "FD_TableColumn", "テーブル項目情報");
		addNumberingDataOrg(env, getNewID(env, 1001), tableId, 1000001, 0);
		tableId = addTableData(env, 0, "FD_Reference", "リファレンス情報");
		addNumberingDataOrg(env, getNewID(env, 1001), tableId, 1000001, referenceID);
		tableId = addTableData(env, 0, "FD_RefGroup", "リファレンスグループ情報");
		addNumberingDataOrg(env, getNewID(env, 1001), tableId, 1000001, 0);
	}

	/**
	 * 新規情報ID採番<br>
	 * @author ueno hideo
	 * @since 1.21 2020/08/15
	 * @param env 環境情報　
	 * @param tableId テーブル情報ID
	 * @return 新規情報ID
	 */
	private int getNewID(FD_EnvData env, int tableId) {
		int id = 0;
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			//採番情報の取得
			sql.append("SELECT Current_Number, Start_Number FROM FD_Numbering ")
				.append("WHERE FD_Table_ID = ").append(tableId);
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				if(rs.getInt("Current_Number") == 0) {
					id = rs.getInt("Start_Number");
				} else {
					id = rs.getInt("Current_Number") + 1;
				}
			}
			//採番情報の更新
			if(id > 0) {
				sql = new StringBuffer();
				sql.append("UPDATE FD_Numbering SET ");
				sql.append("Current_Number = ").append(id).append(",");
				sql.append("FD_Update = ").append(FD_SQ).append(dateFormat.format(new Date())).append(FD_SQ).append(",");
				sql.append("FD_Updated = ").append(env.getLoginUserID()).append(" ");
				sql.append("WHERE FD_Table_ID = ").append(tableId);
				execute(env, sql.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, stmt);
		}
		return id;
	}

	/**
	 * 採番情報登録（SQL直接指定版）<br>
	 * @param env
	 * @param numberingId
	 * @param start
	 * @param current
	 */
	private void addNumberingDataOrg(FD_EnvData env, int numberingId, int tableId, int start, int current) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO FD_Numbering SET ");
		sql.append("FD_Numbering_ID = ").append(numberingId).append(",");
		sql.append("FD_Table_ID = ").append(tableId).append(",");
		sql.append("Start_Number = ").append(start).append(",");
		sql.append("Current_Number = ").append(current).append(",");
		sql.append("FD_Create = ").append(FD_SQ).append(dateFormat.format(new Date())).append(FD_SQ).append(",");
		sql.append("FD_Created = ").append(env.getSystemUserID()).append(",");
		sql.append("FD_Update = ").append(FD_SQ).append(dateFormat.format(new Date())).append(FD_SQ).append(",");
		sql.append("FD_Updated = ").append(env.getSystemUserID());
		execute(env, sql.toString());
	}

	/**
	 * テーブル情報登録<br>
	 * @author ueno hideo
	 * @since 1.21 2020/08/17
	 * @param env 環境情報
	 * @param i 
	 * @param tableName テーブル名
	 * @param name テーブル表示名
	 * @return 
	 */
	private int addTableData(FD_EnvData env, int id, String tableName, String name) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO FD_Table SET ");
		if(id == 0) {
			id = getNewID(env, 1002);
		}
		sql.append("FD_Table_ID = ").append(id).append(",");
		sql.append("Table_Name = ").append(FD_SQ).append(tableName).append(FD_SQ).append(",");
		sql.append("FD_Name = ").append(FD_SQ).append(name).append(FD_SQ).append(",");
		sql.append("FD_Create = ").append(FD_SQ).append(dateFormat.format(new Date())).append(FD_SQ).append(",");
		sql.append("FD_Created = ").append(env.getSystemUserID()).append(",");
		sql.append("FD_Update = ").append(FD_SQ).append(dateFormat.format(new Date())).append(FD_SQ).append(",");
		sql.append("FD_Updated = ").append(env.getSystemUserID());
		execute(env, sql.toString());
		return id;
	}
}
