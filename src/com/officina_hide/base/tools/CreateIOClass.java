package com.officina_hide.base.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Logging;
import com.officina_hide.base.model.FD_DB;

/**
 * テーブルIOクラス生成<br>
 * @author ueno hideo
 * @version 1.21 
 * @since 2020/08/26
 */
public class CreateIOClass extends FD_DB {
	/**
	 * インポートリスト
	 */
	private List<String> importClassList = new ArrayList<String>();

	/**
	 * コンストラクター<br>
	 * @author ueno hideo
	 * @since 1.21 2020/08/26
	 * @param env
	 * @param tableName
	 */
	public CreateIOClass(FD_EnvData env, String tableName) {
		createIOClass(env, tableName);
		env.getLog().add(FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_NORMAL, "I/O Class created ["+tableName+"]");
	}

	/**
	 * テーブルIOクラス生成<br>
	 * @author ueno hideo
	 * @since 2020/08/25
	 * @param env 環境情報
	 * @param tableName テーブル名
	 */
	private void createIOClass(FD_EnvData env, String tableName) {
		StringBuffer packageSource = new StringBuffer();
		StringBuffer source = new StringBuffer();
		StringBuffer importSource = new StringBuffer();
		try {
			File file = new File(env.getModelPath()+"\\"+"X_"+tableName+".java");
			FileWriter fw  = new FileWriter(file);

			//Package宣言
			packageSource.append("package ").append(env.getModelURI()).append(";").append(FD_RETURN).append(FD_RETURN);
			//クラス開始
			source.append("public class X_").append(tableName).append(" extends FD_DB implements ")
				.append("I_").append(tableName).append(" ").append("{").append(FD_RETURN);
			//共通変数定義
			source.append(editComment("環境情報", 1));
			source.append(setTab(1)).append("private FD_EnvData env;").append(FD_RETURN);
			source.append(FD_RETURN);
			addImportClass(importClassList, "com.officina_hide.base.common.FD_EnvData");

			//クラス終了
			source.append("}").append(FD_RETURN);
			//インポート編集
			importSource = editImportClass(importClassList);
			
			fw.write(packageSource.toString() + importSource.toString() + source.toString());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
