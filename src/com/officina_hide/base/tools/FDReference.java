package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Reference;
import com.officina_hide.base.model.X_FD_Reference;

/**
 * リファレンス情報クラス<br>
 * @author officina-hide.com ueno
 * @version 2.00
 * @since 2020/09/05
 */
public class FDReference extends FD_DB implements I_FD_Reference {

	/**
	 * リファレンス情報テーブル生成<br>
	 * @author officine-hide.com ueno
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		//既に登録されているリファレンス情報を削除します。
		sql.append("DROP TABLE IF EXISTS ").append(Table_Name);
		execute(env, sql.toString());
		//リファレンス情報を生成する。
		sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS ").append(Table_Name).append(" (");
		sql.append(COLUMNNAME_FD_REFERENCE_ID)
			.append(" INT UNSIGNED NOT NULL PRIMARY KEY COMMENT 'リファレンス情報ID").append(FD_SQ).append(",");
		sql.append(COLUMNNAME_REFERENCE_NAME).append(" Varchar(100) COMMENT 'リファレンス名'").append(",");
		sql.append(COLUMNNAME_FD_NAME).append(" Varchar(100) COMMENT 'リファレンス表示名").append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_CREATE).append(" DATETIME  COMMENT ").append(FD_SQ).append("登録日").append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_CREATED).append(" INT UNSIGNED COMMENT ").append(FD_SQ).append("登録者ID").append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_UPDATE).append(" DATETIME  COMMENT ").append(FD_SQ).append("更新日").append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_UPDATED).append(" INT UNSIGNED COMMENT ").append(FD_SQ).append("更新者ID").append(FD_SQ);
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='リファレンス情報'");
		execute(env, sql.toString());
		
		//テーブル情報登録
		FDTable table = new FDTable();
		table.addData(env, Table_ID, Table_Name, "リファレンス情報");
		//採番情報登録
		FDNumbering num = new FDNumbering();
		num.add(env, I_FD_Reference.Table_ID, 1000001, 0);

	}

	/**
	 * システムのベースとなるリファレンス情報を登録する。<br>
	 * @author officine-hide.com ueno
	 * @since 2020/09/07
	 * @param env 環境情報
	 */
	public void addBaseData(FD_EnvData env) {
		addData(env, "FD_Information_ID", "情報ID");
		addData(env, "FD_Text", "テキスト");
		addData(env, "FD_Field_Text", "複数行テキスト");
		addData(env, "FD_Date", "日時");
		addData(env, "FD_YESNO", "YESNO");
		addData(env, "FD_Number", "自然数");
	}

	/**
	 * リファレンス情報登録<br>
	 * @param env 環境情報
	 * @param refName リファレンス情報名
	 * @param name リファレンス情報表示名
	 */
	private void addData(FD_EnvData env, String refName, String name) {
		X_FD_Reference ref = new X_FD_Reference(env);
		ref.setValue(COLUMNNAME_FD_REFERENCE_ID, getNewID(env, Table_ID));
		ref.setValue(COLUMNNAME_REFERENCE_NAME, refName);
		ref.setValue(COLUMNNAME_FD_NAME, name);
		ref.save(env);
	}

}
