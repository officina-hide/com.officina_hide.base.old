package com.officina_hide.base.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Item;
import com.officina_hide.base.common.FD_JavaDocParam;
import com.officina_hide.base.common.FD_Logging;

/**
 * データベース基本操作<br>
 * <p>本クラスでは、データベースの基本的な操作に関する機能を提供します。<br>
 * This class provides functions related to basic database operations.</p>
 * @author ueno hideo
 * @version 1.20
 * @since 2020/07/15
 */
public class FD_DB implements I_DB {
	/** 項目リスト */
	protected List<FD_Item> itemList = new ArrayList<>();
	
	/**
	 * 環境情報
	 */
	private FD_EnvData env;
	/**
	 * データベース接続情報
	 */
	protected static Connection conn;
	/**
	 * 日付フォーマット
	 */
	protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public FD_DB() {
	}
	
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
	 * 項目リストにデータを登録する。
	 * @param itemName 項目名
	 * @param data データ
	 */
	public void setValue(String itemName, Object data) {
		for(FD_Item item : itemList) {
			if(item.getItemName().equals(itemName)) {
				item.setItemData(data);
				break;
			}
		}
	}
	
	/**
	 * SQL実行<br>
	 * <p>更新系のSQL文を実行します。</p>
	 * @author ueno hideo
	 * @since 1.20 2020/07/15
	 * @param env 環境情報
	 * @param sql SQL文
	 */
	public int execute(FD_EnvData env, String sql) {
		int chk = 0;
		Statement stmt = null;
		try {
			connection(env);
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
	 * テーブル情報を初夏保存する。<br>
	 * @author officina-hide.com ueno
	 * @since 2.00 2020/08/30
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env, String TableName) {
		if(getDateOfValue(COLUMNNAME_FD_CREATE) == null) {
			setValue(COLUMNNAME_FD_CREATE, new Date());
			setValue(COLUMNNAME_FD_UPDATE, new Date());
			setValue(COLUMNNAME_FD_CREATED, env.getLoginUserID());
			setValue(COLUMNNAME_FD_UPDATED, env.getLoginUserID());
		} else {
			setValue(COLUMNNAME_FD_UPDATE, new Date());
			setValue(COLUMNNAME_FD_UPDATED, env.getLoginUserID());
		}

		StringBuffer sql = new StringBuffer();
		StringBuffer setitem = new StringBuffer();
		sql.append("INSERT INTO ").append(TableName).append(" SET ");
		for(FD_Item item : itemList) {
			if(setitem.toString().length() > 0) {
				setitem.append(",");
			}
			if(item.getItemData() != null) {
				switch(item.getItemType()) {
				case COLUMN_TYPE_INFORMATION_ID:
					setitem.append(item.getItemName()).append(" = ").append(getIntOfValue(item.getItemName()));
					break;
				case COLUMN_TYPE_TEXT:
				case COLUMN_TYPE_FIELD_TEXT:
					setitem.append(item.getItemName()).append(" = ").append(FD_SQ).append(getStringOfValue(item.getItemName())).append(FD_SQ);
					break;
				case COLUMN_TYPE_DATE:
					setitem.append(item.getItemName()).append(" = ")
						.append(FD_SQ).append(dateFormat.format(getDateOfValue(item.getItemName()).getTime())).append(FD_SQ);
					break;
				}
			} else {
				setitem.append(item.getItemName()).append(" = null");
			}
		}
		sql.append(setitem);

		execute(env, sql.toString());
	}

	/**
	 * テーブル項目情報を取得する。(int型)<br>
	 * <p>もし、項目が見つからないときもしくは数値型と違うときは、0を返す。</p>
	 * @param itemName 項目名
	 * @return 項目情報(int型)
	 */
	public int getIntOfValue(String itemName) {
		try {
			return (int) getItemData(itemName);
		} catch (ClassCastException e) {
			return 0;
		}
	}

	/**
	 * テーブル項目情報を取得する。(String型)<br>
	 * <p>もし、項目が見つからないときはnullを返す</p>
	 * @param itemName 項目名
	 * @return
	 */
	public String getStringOfValue(String itemName) {
		return (String) getItemData(itemName);
	}

	/**
	 * @param itemName
	 * @return
	 */
	public Date getDateOfValue(String itemName) {
		return (Date) getItemData(itemName);
	}

	/**
	 * 指定された項目名から項目情報を抽出する。<br>
	 * @param itemName
	 * @return
	 */
	public Object getItemData(String itemName) {
		for(FD_Item item : itemList) {
			if(item.getItemName().equals(itemName)) {
				return item.getItemData();
			}
		}
		return null;
	}

	/**
	 * データベース接続<br>
	 * @author ueno hideo
	 * @param env 環境情報
	 * @since 1.20 2020/07/15
	 */
	public void connection(FD_EnvData env) {
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
	 * テーフル情報ID取得<br>
	 * <p>指定されたテーブル名のテーブル情報IDを取得する。<br></p>
	 * @author ueno hideo
	 * @since 2020/07/16
	 * @param tableName テーブル名
	 * @return テーフル情報ID
	 */
	public int getTableID(FD_EnvData env, String tableName) {
		int id = 0;
		StringBuffer sql = new StringBuffer();
		Statement stmt = null;
		ResultSet rs = null;
		sql.append("SELECT FD_Table_ID FROM FD_Table ")
			.append("WHERE Table_Name = '").append(tableName).append("'");
		try {
			connection(env);
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
	 * リファレンスID取得<br>
	 * @param env 環境情報
	 * @param referenceName リファレンス名
	 * @return リファレンス情報ID
	 */
	public int getReferenceID(FD_EnvData env, String referenceName) {
		int id = 0;
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT FD_Reference_ID FROM FD_Reference ");
		sql.append("Where Reference_Name = '").append(referenceName).append("'");
		try {
			connection(env);
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
	 * 新規情報ID取得<br>
	 * @param env 環境情報
	 * @param tableName テーブル名
	 * @return 情報ID
	 */
	public int getNewID(FD_EnvData env,  String tableName) {
		int number = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		StringBuffer sql = new StringBuffer();
		int tableId = getTableID(env, tableName);
		sql.append("SELECT Current_Number, Start_Number FROM FD_Numbering ");
		sql.append("Where FD_Table_ID = ").append(tableId).append(" ");
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection(env);
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
			execute(env, sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, stmt);
		}
		return number;
	}

	/**
	 * コメント編集<br>
	 * @author ueno hideo
	 * @since 1.10 2020-04-12
	 * @param title タイトル
	 * @param tabCount タブ位置
	 * @return コメント文字列
	 */
	public String editComment(String title, int tabCount) {
		return editComment(title, tabCount, null);
	}

	/**
	 * コメント編集<br>
	 * @author ueno hideo
	 * @since 1.11 2020/07/02
	 * @param title タイトル
	 * @param tabCount タブ位置
	 * @param param パラメータ
	 * @return コメント文字列
	 */
	public String editComment(String title, int tabCount, FD_JavaDocParam param) {
		StringBuffer source = new StringBuffer();
		source.append(setTab(tabCount)).append("/**").append(FD_RETURN);
		source.append(setTab(tabCount)).append(" * ").append(title).append(".<br>").append(FD_RETURN);
		//パラメータ追加
		if(param != null && param.length() > 0) {
			source.append(param.getJavadocOfParam(tabCount));
		}
		source.append(setTab(tabCount)).append(" */").append(FD_RETURN);
		return source.toString();
	}

	public String setTab(int tabCount) {
		StringBuffer source = new StringBuffer();
		for(int ix = 0 ; ix < tabCount; ix++) {
			source.append(FD_TAB);
		}
		return source.toString();
	}

	/**
	 * テーブル情報登録<br>
	 * @author ueno hideo
	 * @since 1.20 2020/07/16
	 * @param env 環境情報
	 * @param Id テーブル情報ID
	 * @param tableName テーブル識別名
	 * @param name テーブル表示名
	 * @param comment コメント
	 * @return 
	 */
	public int addTableData(FD_EnvData env, int id, String tableName, String name, String comment) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		//Idが0の時は新規に情報IDを発行する。
		if(id == 0) {
			id = getNewID(env, I_FD_Table.Table_Name);
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
		execute(env, sql.toString());
		
		return id;
	}

	/**
	 * リファレンス情報登録<br>
	 * @param env 環境情報
	 * @param name リファレンス名
	 */
	public void addReferenceData(FD_EnvData env, String name) {
		X_FD_Reference ref = new X_FD_Reference(env);
		ref.setReference_Name(name);
		ref.save();
	}
	
	/**
	 * 採番情報登録<br>
	 * @param env　環境情報
	 * @param tableId テーブル情報ID
	 * @param current 現在値
	 * @param start 開始値
	 */
	public void addNumberingData(FD_EnvData env, int tableId, int current, int start) {
		X_FD_Numbering num = new X_FD_Numbering(env);
		num.setFD_Table_ID(tableId);
		num.setStart_Number(start);
		num.setCurrent_Number(current);
		num.save();
	}

	/**
	 * テーフル項目情報登録<br>
	 * @author ueno hideo
	 * @since 1.20 2020/07/16
	 * @param env 環境情報
	 * @param tableId テーブル情報ID
	 * @param culimnName 項目名
	 * @param columnType 項目種別
	 * @param size 桁数
	 * @param name 項目表示名
	 * @param comment 項目説明
	 * @param order 項目並び順
	 * @param priKey プライマリーキー
	 */
	public void addTableColumnData(FD_EnvData env, int tableId, String culimnName, String columnType, int size, String name, String comment,
			int order, boolean priKey) {
		//テーブル項目情報ID生成
		int columnId = getNewID(env, "FD_TableColumn");
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
		execute(env, sql.toString());
	}

	/**
	 * リファレンス用パラメータ情報登録<br>
	 * @author ueno hideo
	 * @since 1.20 2020/07/16
	 * @param referenceID リファレンス情報ID
	 * @param paramName パラメータ名
	 * @param paramType　パラメータ種別名
	 * @param paramData パラメータ情報
	 * @param comment 説明
	 */
	public void addRefParamData(int referenceID, String paramName, String paramType, String paramData, String comment) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO FD_RefParam SET ");
		sql.append("FD_RefParam_ID = ").append(getNewID(env, "FD_RefParam")).append(",");
		sql.append("FD_Reference_ID = ").append(referenceID).append(",");
		sql.append("Parameter_Name = ").append(FD_SQ).append(paramName).append(FD_SQ).append(",");
		sql.append("Parameter_Type_ID = ").append(getReferenceID(env, paramType)).append(",");
		sql.append("Parameter_Data  = ").append(FD_SQ).append(paramData).append(FD_SQ).append(",");
		sql.append("FD_Comment = ").append(FD_SQ).append(paramData).append(FD_SQ).append(",");
		sql.append("FD_Create = '").append(dateFormat.format(new Date())).append("'").append(",");
		sql.append("FD_Created = ").append(env.getSystemUserID()).append(",");
		sql.append("FD_Update = '").append(dateFormat.format(new Date())).append("'").append(",");
		sql.append("FD_Updated = ").append(env.getSystemUserID());
		execute(env, sql.toString());
	}

	/**
	 * インポートクラスを追加する。<br>
	 * @param importList インポートクラスリスト
	 * @param className 追加クラス名
	 */
	public void addImportClass(List<String> importList, String className) {
		boolean isSee = false;		//追加スイッチ
		for(String clazz : importList) {
			if(clazz.equals(className)) {
				isSee = true;
				break;
			}
		}
		if(isSee == false) {
			importList.add(className);
		}
	}

	/**
	 * インポートクラス編集<br>
	 * <p>リストの対象クラスは昇順に並び替えて出力される。</p>
	 * @author ueno hideo
	 * @since 1.20 2020/07/16
	 * @param importClassList　インサート対象クラスリスト
	 * @return インサート文字列
	 */
	public StringBuffer editImportClass(List<String> importClassList) {
		StringBuffer source = new StringBuffer();
		Collections.sort(importClassList);
		for(String clazz : importClassList) {
			source.append("import ").append(clazz).append(";").append(FD_RETURN);
		}
		if(source.length() > 0) {
			source.append(FD_RETURN);
		}
		return source;
	}

	/**
	 * エスケープ処理<br>
	 * <p>SQLインジェクション対策の為、指定されたデータのコードを表示するエスケープ処理する。</p>
	 * @author officina-hide.com ueno
	 * @since 2020/08/29
	 * @param data 処理対象情報
	 * @return エスケープ処理済情報
	 */
	public String changeEscape(String data) {
		String out = data;
		out = out.replaceAll("\'", "\"");
		out = out.replaceAll("\\\\", "\\\\\\\\");
		System.out.println(out);
		return out;
	}

	/**
	 * DBステートメント生成<br>
	 * @author officine-hide.com ueno
	 * @since 2.00 2020/09/01
	 * @return DBステートメント
	 */
	public Statement createStatement() {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}

}
