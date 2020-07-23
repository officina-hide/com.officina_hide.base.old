package com.officina_hide.base.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_DB;

/**
 * データベースIOモデル生成<br>
 * @author ueno hideo
 * @version 1.20
 * @since 2020/07/16
 */
public class CreateModel extends FD_DB implements I_DB {
	/**
	 * 環境情報
	 */
	private FD_EnvData env;
	/**
	 * テーブル名
	 */
	private String tableName;
	/**
	 * インポートリスト
	 */
	private List<String> importClassList = new ArrayList<String>();

	/**
	 * コンストラクタ－<br>
	 * @author ueno hideo
	 * @since 1.20 2020/07/16
	 * @param env 環境情報
	 * @param tableName テーブル名
	 */
	public CreateModel(FD_EnvData env, String tableName) {
		this.env = env;
		this.tableName = tableName;
		//インターフェースクラス生成
		createInterfaceClass();
		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "Interface Class created ["+tableName+"]");
		//IOクラス生成
		createIOClass();
	}

	/**
	 * インターフェースクラス生成
	 */
	private void createInterfaceClass() {
		StringBuffer source = new StringBuffer();
		File file = new File(env.getModelPath()+"\\"+"I_"+tableName+".java");
		try {
			FileWriter fw  = new FileWriter(file);
			//Package宣言
			source.append("package ").append(env.getModelURI()).append(";").append(FD_RETURN).append(FD_RETURN);
			//クラス開始
			source.append("public interface I_").append(tableName).append(" {").append(FD_RETURN);
			//テーブル名定数
			source.append(editComment("テーブル名", 1));
			source.append(setTab(1)).append("public final String Table_Name = ")
				.append(FD_DQ).append(tableName).append(FD_DQ).append(";").append(FD_RETURN).append(FD_RETURN);
			//テーブルID定数
			source.append(editComment("テーブルID", 1));
			source.append(setTab(1)).append("public final int Table_ID =")
				.append(getTableID(env, tableName)).append(";").append(FD_RETURN).append(FD_RETURN);
			//テーブル各項目
			List<Map<String, String>> columns = getColumnData();
			for(Map<String, String> map : columns) {
				source.append(editComment(map.get("FD_Name").toString(), 1));
				source.append(setTab(1)).append("public final String COLUMNNAME_").append(map.get("Column_Name").toString().toUpperCase())
					.append(" = ").append(FD_DQ).append(map.get("Column_Name").toString()).append(FD_DQ).append(";").append(FD_RETURN);
				source.append(FD_RETURN);
			}
			
			//クラス終了
			source.append("}").append(FD_RETURN);
			
			fw.write(source.toString());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * IOクラス生成<br>
	 * @author ueno hideo
	 * @since 1.20 2020/07/16
	 */
	private void createIOClass() {
		StringBuffer packageSource = new StringBuffer();
		StringBuffer source = new StringBuffer();
		StringBuffer importSource = new StringBuffer();

		File file = new File(env.getModelPath()+"\\"+"X_"+tableName+".java");
		try {
			FileWriter fw  = new FileWriter(file);
			//Package宣言
			packageSource.append("package ").append(env.getModelURI()).append(";").append(FD_RETURN).append(FD_RETURN);
			//クラス開始
			source.append("public class X_").append(tableName).append(" extends FD_DB implements I_DB").append(", ")
				.append("I_").append(tableName).append(" ").append("{").append(FD_RETURN);
			//共通変数定義
			source.append(setTab(1)).append("private FD_EnvData env;").append(FD_RETURN);
			source.append(FD_RETURN);
			//コンストラクター(取得用)定義
			source.append(createConstractor());
			
			//テーブル項目定義0
			List<Map<String, String>> columns = getColumnData();
			for(Map<String, String> map : columns) {
				try {
					Class<?> cl = Class.forName(map.get("Parameter_Data").toString());
					Constructor<?> con = cl.getConstructor(new Class[] {List.class});
					Object obj = con.newInstance(new Object[] {importClassList});
					//変数定義
					Method method = cl.getMethod("toVariableDefinitions", String.class, String.class);
					source.append(method.invoke(obj, map.get("Column_Name").toString(), map.get("FD_Name").toString()).toString());
					//getter定義
					method = cl.getMethod("toGetterDefinition", String.class, String.class);
					source.append(method.invoke(obj, map.get("Column_Name").toString(), map.get("FD_Name").toString()).toString());
					//setter定義
					method = cl.getMethod("toSetterDefinition", String.class, String.class);
					source.append(method.invoke(obj, map.get("Column_Name").toString(), map.get("FD_Name").toString()).toString());
				} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
						| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			//saveメソッド定義
			source.append(createSaveMethod());
			
			//クラス終了
			source.append("}").append(FD_RETURN).append(FD_RETURN);
			//インポート編集
			importSource = editImportClass(importClassList);

			fw.write(packageSource.toString() + importSource.toString() + source.toString());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * コンストラクタ－定義<br>
	 * @author ueno hideo
	 * @since 1.20 2020/07/23
	 * @return 定義文字列
	 */
	private String createConstractor() {
		StringBuffer source = new StringBuffer();
		//インスタンス化のみのコンストラクター
		source.append(setTab(1)).append("public X_").append(tableName).append("(FD_EnvData env) {").append(FD_RETURN);
		source.append(setTab(2)).append("this.env = env;").append(FD_RETURN);
		source.append(setTab(1)).append("}").append(FD_RETURN).append(FD_RETURN);
		addImportClass(importClassList, "com.officina_hide.base.common.FD_EnvData");
		
		//インスタンス化時に条件分で指定した情報を読取るコンストラクター生成
		source.append(setTab(1)).append("public X_").append(tableName)
			.append("(FD_EnvData env, FD_WhereData where) {").append(FD_RETURN);
		source.append(setTab(2)).append("this.env = env;").append(FD_RETURN);
		source.append(setTab(2)).append("List<Integer> ids = getIds(env, where);").append(FD_RETURN);
		//idsの0件対応
		// TODO idsが0件の時（条件らマッチした情報が抽出できなかった時）の対応について要検討(2020/06/15 ueno)
		source.append(setTab(2)).append("if(ids.size() > 0) {").append(FD_RETURN);
		source.append(setTab(3)).append("load(env, ids.get(0));").append(FD_RETURN);
		source.append(setTab(2)).append("}").append(FD_RETURN);
		source.append(setTab(1)).append("}").append(FD_RETURN).append(FD_RETURN);
		
		//インスタンス化時に情報IDで情報を取得するコンストラクター生成
		source.append(setTab(1)).append("public X_").append(tableName).append("(FD_EnvData env, int id) {").append(FD_RETURN);
		source.append(setTab(2)).append("this.env = env;").append(FD_RETURN);
		source.append(setTab(2)).append("load(env, id);").append(FD_RETURN);
		source.append(setTab(1)).append("}").append(FD_RETURN).append(FD_RETURN);
		
		addImportClass(importClassList, "com.officina_hide.base.common.FD_WhereData");
		addImportClass(importClassList, "java.util.List");
		return source.toString();
	}

	/**
	 * Saveメソッド定義<br>
	 * @author ueno hideo
	 * @since 1.20 2020/07/21
	 * @return 定義文字列
	 */
	private String createSaveMethod() {
		StringBuffer source = new StringBuffer();
		source.append(editComment(tableName + "を保存する。", 1));
		//メソッド開始
		source.append(setTab(1)).append("public void save() {").append(FD_RETURN);
		//SQL文字列変数定義
		source.append(setTab(2)).append("StringBuffer sql = new StringBuffer();").append(FD_RETURN);
		//新規登録判定
		source.append(setTab(2)).append("boolean isNewData = false;").append(FD_RETURN);
		//新規情報ID取得
		source.append(setTab(2)).append("if(get").append(tableName.substring(0, 1))
			.append(tableName.substring(1)).append("_ID() == 0 ) {").append(FD_RETURN);
		source.append(setTab(3)).append("set").append(tableName.substring(0, 1).toUpperCase())
			.append(tableName.substring(1)).append("_ID(")
			.append("getNewID(env, getTableID(env, ").append(FD_DQ).append(tableName).append(FD_DQ)
			.append(")));").append(FD_RETURN);
		source.append(setTab(3)).append("isNewData = true;").append(FD_RETURN);
		source.append(setTab(2)).append("}").append(FD_RETURN);
		//追加・更新SQL分ヘッダー
		source.append(setTab(2)).append("if(isNewData) {").append(FD_RETURN);
		source.append(setTab(3)).append("sql.append(").append(FD_DQ).append("INSERT INTO ").append(FD_DQ)
			.append(").append(I_").append(tableName).append(".Table_Name);").append(FD_RETURN);
		source.append(setTab(3)).append("getFD_Create().setTime(new Date());").append(FD_RETURN);
		source.append(setTab(3)).append("getFD_Update().setTime(new Date());").append(FD_RETURN);
		source.append(setTab(3)).append("setFD_Created(").append("env.getLoginUserID()").append(");").append(FD_RETURN);
		source.append(setTab(3)).append("setFD_Updated(").append("env.getLoginUserID()").append(");").append(FD_RETURN);
		source.append(setTab(2)).append("} else {").append(FD_RETURN);
		source.append(setTab(3)).append("sql.append(").append(FD_DQ).append("UPDATE ").append(FD_DQ)
			.append(").append(I_").append(tableName).append(".Table_Name);").append(FD_RETURN);
		source.append(setTab(3)).append("getFD_Update().setTime(new Date());").append(FD_RETURN);
		source.append(setTab(3)).append("setFD_Updated(").append("env.getLoginUserID()").append(");").append(FD_RETURN);
		source.append(setTab(2)).append("}").append(FD_RETURN);
		addImportClass(importClassList, "java.util.Date");
		//項目セット
		source.append(setTab(2)).append("sql.append(").append(FD_DQ).append(" SET ").append(FD_DQ).append(");").append(FD_RETURN);
		List<Map<String, String>> columns = getColumnData();
		StringBuffer setItems = new StringBuffer();
		for(Map<String, String> map : columns) {
			if(setItems.length() > 0) {
				setItems.append(".append(").append(FD_DQ).append(",").append(FD_DQ).append(");").append(FD_RETURN);
			}
			//項目の該当する属性クラスから保存用のSQL文字列を取得する。
			try {
				Class<?> cl = Class.forName(map.get("Parameter_Data").toString());
				Constructor<?> con = cl.getConstructor(new Class[] {List.class});
				Object obj = con.newInstance(new Object[] {importClassList});
				Method method = cl.getMethod("toSaveSQL", String.class, String.class);
				setItems.append(method.invoke(obj, tableName, map.get("Column_Name").toString()));
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		source.append(setItems.toString()).append(";").append(FD_RETURN);

		//更新時の条件セット
		source.append(setTab(2)).append("if(isNewData == false) {").append(FD_RETURN);
		source.append(setTab(3)).append("sql.append(").append(FD_DQ).append(" WHERE ").append(FD_DQ).append(")")
			.append(".append(I_").append(tableName).append(".").append("COLUMNNAME_").append(tableName.toUpperCase())
			.append("_ID)").append(".append(").append(FD_DQ).append(" = ").append(FD_DQ).append(")")
			.append(".append(").append("get").append(tableName.substring(0, 1).toUpperCase())
			.append(tableName.substring(1)).append("_ID").append("());").append(FD_RETURN);
		source.append(setTab(2)).append("}").append(FD_RETURN);
		
		//SQL実行
		source.append(setTab(2)).append("execute(env, sql.toString());").append(FD_RETURN);

		//メソッド終了
		source.append(setTab(1)).append("}").append(FD_RETURN).append(FD_RETURN);

		return source.toString();
	}

	/**
		 * テーブル項目リスト生成<br>
		 * @author ueno hideo
		 * @since 1.20 2020/07/16
		 * @return テーブル項目リスト
		 */
		private List<Map<String, String>> getColumnData() {
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			int tableID = getTableID(env, tableName);
			StringBuffer sql = new StringBuffer();
			ResultSet rs = null;
			Statement stmt = null;
			try {
				sql.append("SELECT * FROM FD_TableColumn ");
				sql.append("LEFT JOIN FD_Reference ON FD_Reference.FD_Reference_ID = FD_TableColumn.Column_Type_ID ");
				sql.append("LEFT JOIN FD_RefParam ON FD_RefParam.FD_Reference_ID = FD_Reference.FD_Reference_ID ")
					.append("AND FD_RefParam.Parameter_Type_ID = ").append(getReferenceID(env, "ClassName")).append(" ");
				sql.append("WHERE FD_Table_ID = ").append(tableID).append(" ");
				sql.append("ORDER BY Column_Sort_Order");
				connection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql.toString());
				while(rs.next()) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("Column_Name", rs.getNString("Column_Name"));
					map.put("FD_Name", rs.getNString("FD_Name"));
					map.put("Parameter_Data", rs.getString("Parameter_Data"));
					list.add(map);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs, stmt);
			}
			return list;
		}

}
