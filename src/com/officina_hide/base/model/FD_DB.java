package com.officina_hide.base.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;

/**
 * データベース基本操作<br>
 * <p>本クラスでは、データベースの基本的な操作に関する機能を提供します。<br>
 * This class provides functions related to basic database operations.</p>
 * @author ueno hideo
 * @version 1.20
 * @since 2020/07/15
 */
public class FD_DB {

	/**
	 * 環境情報
	 */
	private FD_EnvData env;
	/**
	 * データベース接続情報
	 */
	protected static Connection conn = null;
	
	/**
	 * コンストラクタ－<br>
	 * <p>インスタンス時にデータベースの操作に必要な環境情報を取得します。<br>
	 * Acquires the environment information required for database operations during instance.</p>
	 * @author ueno hideo
	 * @since 1.20 2020/07/15
	 * @param env 環境情報
	 */
	public FD_DB(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * SQL実行<br>
	 * <p>更新系のSQL文を実行します。</p>
	 * @author ueno hideo
	 * @since 1.20 2020/07/15
	 * @param sql SQL文
	 */
	public int execute(String sql) {
		int chk = 0;
		Statement stmt = null;
		try {
			connection();
			stmt = conn.createStatement();
			chk = stmt.executeUpdate(sql.toString());
			env.getLog().add(FD_Logging.TYPE_DB, FD_Logging.MODE_DEBAG, sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return chk;
	}

	/**
	 * データベース接続<br>
	 * @author ueno hideo
	 * @since 1.20 2020/07/15
	 */
	private void connection() {
		if(conn == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				StringBuffer url  = new StringBuffer().append("jdbc:mysql://")
						.append(env.getDB_Host())
						.append(":3306/")
						.append(env.getDB_Name());
				conn = DriverManager.getConnection(url.toString(), env.getDB_User(), env.getDB_Password());
				env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_DEBAG, "Database Connected.");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * テーブル情報登録<br>
	 * @author ueno hideo
	 * @since 1.20 2020/07/16
	 * @param Id テーブル情報ID
	 * @param tableName テーブル識別名
	 * @param name テーブル表示名
	 * @param comment コメント
	 */
	public void addTableData(int id, String tableName, String name, String comment) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		//Idが0の時は新規に情報IDを発行する。
		if(id == 0) {
			// TODO 情報IDの新規発行は未実装(2020/07/16 ueno)
		}
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO FD_Table SET ");
		sql.append("FD_Table_ID = ").append(id).append(",");
		sql.append("Table_Name = '").append(tableName).append("'").append(",");
		sql.append("FD_Name = '").append(name).append("'").append(",");
		sql.append("FD_Comment = '").append(comment).append("'").append(",");
		sql.append("FD_Create = '").append(dateFormat.format(new Date())).append("'").append(",");
		sql.append("FD_Created = ").append(env.getSystemUserID()).append(",");
		sql.append("FD_Update = '").append(dateFormat.format(new Date())).append("'").append(",");
		sql.append("FD_Updated = ").append(env.getSystemUserID());
		execute(sql.toString());
	}

	/**
	 * リファレンス情報登録<br>
	 * @author ueno hideo
	 * @since 1.20 2020/07/16
	 * @param id
	 * @param name
	 */
	public void addReferenceData(int id, String name) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		//Idが0の時は新規に情報IDを発行する。
		if(id == 0) {
			// TODO 情報IDの新規発行は未実装(2020/07/16 ueno)
		}
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO FD_Reference SET ");
		sql.append("FD_Reference_ID = ").append(id).append(",");
		sql.append("Reference_Name = '").append(name).append("'").append(",");
		sql.append("FD_Create = '").append(dateFormat.format(new Date())).append("'").append(",");
		sql.append("FD_Created = ").append(env.getSystemUserID()).append(",");
		sql.append("FD_Update = '").append(dateFormat.format(new Date())).append("'").append(",");
		sql.append("FD_Updated = ").append(env.getSystemUserID());
		execute(sql.toString());
	}

	/**
	 * テーフル情報ID取得<br>
	 * <p>指定されたテーブル名のテーブル情報IDを取得する。<br></p>
	 * @author ueno hideo
	 * @since 2020/07/16
	 * @param tableName テーブル名
	 * @return テーフル情報ID
	 */
	public int getTableID(String tableName) {
		int id = 0;
		StringBuffer sql = new StringBuffer();
		Statement stmt = null;
		ResultSet rs = null;
		sql.append("SELECT FD_Table_ID FROM FD_Table ")
			.append("WHERE Table_Name = '").append(tableName).append("'");
		try {
			connection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				id = rs.getInt("FD_Table_ID");
			} else {
				env.getLog().add(FD_Logging.TYPE_ERROR, FD_Logging.MODE_NORMAL, "Table ID not found ["+tableName+"]");
				new Exception();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, stmt);
		}
		return id;
	}

	/**
	 * データベースクローズ処理<br>
	 * @param stmt 処理ステートメント
	 */
	public void close(Statement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * データベースクローズ処理<br>
	 * @param rs　検索結果
	 * @param stmt 処理ステートメント
	 */
	public void close(ResultSet rs, Statement stmt) {
		try {
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * テーフル項目情報登録<br>
	 * @author ueno hideo
	 * @since 1.20 2020/07/16
	 * @param tableId テーブル情報ID
	 * @param culimnName 項目名
	 * @param columnType 項目種別
	 * @param size 桁数
	 * @param name 項目表示名
	 * @param comment 項目説明
	 * @param order 項目並び順
	 * @param priKey プライマリーキー
	 */
	public void addTableColumnData(int tableId, String culimnName, String columnType, int size, String name, String comment,
			int order, boolean priKey) {
		int columnId = getNewID("FD_TableColumn");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO FD_TableColumn SET ");
		sql.append("FD_TableColumn_ID = ").append(columnId).append(",");
		sql.append("FD_Table_ID = ").append(tableId).append(",");
		sql.append("Column_Name = '").append(culimnName).append("'").append(",");
		sql.append("Column_Type_ID = ").append(getReferenceID(env, columnType)).append(",");
		sql.append("Column_Size = ").append(size).append(",");
		sql.append("FD_Name = '").append(name).append("'").append(",");
		sql.append("FD_Comment = '").append(comment).append("'").append(",");
		sql.append("Column_Sort_Order = ").append(order).append(",");
		if(priKey == true) {
			sql.append("Primary_Key_Check = 1").append(",");
		} else {
			sql.append("Primary_Key_Check = 0").append(",");
		}
		sql.append("FD_Create = '").append(dateFormat.format(new Date())).append("'").append(",");
		sql.append("FD_Created = ").append(env.getSystemUserID()).append(",");
		sql.append("FD_Update = '").append(dateFormat.format(new Date())).append("'").append(",");
		sql.append("FD_Updated = ").append(env.getSystemUserID());
		execute(sql.toString());
	}

	/**
	 * リファレンスID取得<br>
	 * @param env 環境情報
	 * @param referenceName リファレンス名
	 * @return リファレンス情報ID
	 */
	private int getReferenceID(FD_EnvData env, String referenceName) {
		int id = 0;
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT FD_Reference_ID FROM FD_Reference ");
		sql.append("Where Reference_Name = '").append(referenceName).append("'");
		try {
			connection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				id = rs.getInt("FD_Reference_ID");
			} else {
				env.getLog().add(FD_Logging.TYPE_ERROR, FD_Logging.MODE_NORMAL, "Reference not found ["+referenceName+"]");
				new Exception();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, stmt);
		}
		return id;
	}

	/**
	 * 採番情報登録<br>
	 * @author ueno hideo
	 * @since 1.20 2020/07/16
	 * @param numberingId 採番情報ID
	 * @param TableId テーブル情報ID
	 * @param current 現在値
	 * @param start 開始値
	 */
	public void addNumberingData(int numberingId, int tableId, int current, int start) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO FD_Numbering SET ");
		sql.append("FD_Numbering_ID = ").append(numberingId).append(",");
		sql.append("FD_Table_ID = ").append(tableId).append(",");
		sql.append("Current_Number = ").append(current).append(",");
		sql.append("Start_Number = ").append(start).append(",");
		sql.append("FD_Create = '").append(dateFormat.format(new Date())).append("'").append(",");
		sql.append("FD_Created = ").append(env.getSystemUserID()).append(",");
		sql.append("FD_Update = '").append(dateFormat.format(new Date())).append("'").append(",");
		sql.append("FD_Updated = ").append(env.getSystemUserID());
		execute(sql.toString());
	}

	/**
	 * 新規情報ID取得<br>
	 * @param tableName テーブル名
	 * @return 情報ID
	 */
	private int getNewID(String tableName) {
		int number = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		StringBuffer sql = new StringBuffer();
		int tableId = getTableID(tableName);
		sql.append("SELECT Current_Number, Start_Number FROM FD_Numbering ");
		sql.append("Where FD_Table_ID = ").append(tableId).append(" ");
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				if(rs.getInt("Current_Number") == 0) {
					number = rs.getInt("Start_Number");
				} else {
					number = rs.getInt("Current_Number") + 1;
				}
			} else {
				env.getLog().add(FD_Logging.TYPE_ERROR, FD_Logging.MODE_NORMAL, "Numbering Table not found ["+tableName+"]");
				new Exception();
			}
			//採番情報更新
			sql = new StringBuffer();
			sql.append("UPDATE FD_Numbering SET ");
			sql.append("Current_Number = ").append(number).append(",");
			sql.append("FD_Update = '").append(dateFormat.format(new Date())).append("'").append(",");
			sql.append("FD_Updated = ").append(env.getSystemUserID()).append(" ");
			sql.append("WHERE FD_Table_ID = ").append(tableId);
			execute(sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, stmt);
		}
		return number;
	}

}
