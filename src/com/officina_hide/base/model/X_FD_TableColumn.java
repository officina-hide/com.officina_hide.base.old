package com.officina_hide.base.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class X_FD_TableColumn extends FD_DB implements I_DB, I_FD_TableColumn {
	/**
	 * テーブル項目情報ID.<br>
	 */
	private int fD_TableColumn_ID;
	/**
	 * テーブル項目情報IDを取得する。.<br>
	 */
	public int getFD_TableColumn_ID() {
		return fD_TableColumn_ID;
	}
	/**
	 * テーブル項目情報IDをセットする。.<br>
	 */
	public void setFD_TableColumn_ID( int fD_TableColumn_ID) {
		this.fD_TableColumn_ID = fD_TableColumn_ID;
	}
	/**
	 * テーブル情報ID.<br>
	 */
	private int oFN_Table_ID;
	/**
	 * テーブル情報IDを取得する。.<br>
	 */
	public int getOFN_Table_ID() {
		return oFN_Table_ID;
	}
	/**
	 * テーブル情報IDをセットする。.<br>
	 */
	public void setOFN_Table_ID( int oFN_Table_ID) {
		this.oFN_Table_ID = oFN_Table_ID;
	}
	/**
	 * テーブル項目物理名.<br>
	 */
	private String column_Name;
	/**
	 * テーブル項目物理名を取得する。.<br>
	 */
	public String getColumn_Name() {
		return column_Name;
	}
	/**
	 * テーブル項目物理名をセットする。.<br>
	 */
	public void setColumn_Name (String column_Name) {
		this.column_Name = column_Name;
	}
	/**
	 * 種別ID（リファレンス情報ID）.<br>
	 */
	private int column_Type_ID;
	/**
	 * 種別ID（リファレンス情報ID）を取得する。.<br>
	 */
	public int getColumn_Type_ID() {
		return column_Type_ID;
	}
	/**
	 * 種別ID（リファレンス情報ID）をセットする。.<br>
	 */
	public void setColumn_Type_ID( int column_Type_ID) {
		this.column_Type_ID = column_Type_ID;
	}
	/**
	 * 桁数.<br>
	 */
	private int column_Size;
	/**
	 * 桁数を取得する。.<br>
	 */
	public int getColumn_Size() {
		return column_Size;
	}
	/**
	 * 桁数をセットする。.<br>
	 */
	public void setColumn_Size( int column_Size) {
		this.column_Size = column_Size;
	}
	/**
	 * 物理名.<br>
	 */
	private String fD_Name;
	/**
	 * 物理名を取得する。.<br>
	 */
	public String getFD_Name() {
		return fD_Name;
	}
	/**
	 * 物理名をセットする。.<br>
	 */
	public void setFD_Name (String fD_Name) {
		this.fD_Name = fD_Name;
	}
	/**
	 * 説明.<br>
	 */
	private String fD_Comment;
	/**
	 * 説明を取得する。.<br>
	 */
	public String getFD_Comment() {
		return fD_Comment;
	}
	/**
	 * 説明をセットする。.<br>
	 */
	public void setFD_Comment (String fD_Comment) {
		this.fD_Comment = fD_Comment;
	}
	/**
	 * プライマリーキー判定.<br>
	 */
	private boolean primary_Key_Check;
	/**
	 * プライマリーキー判定を判定する。.<br>
	 */
	public boolean isPrimary_Key_Check() {
		return primary_Key_Check;
	}
	/**
	 * プライマリーキー判定をセットする。.<br>
	 */
	public void setPrimary_Key_Check( boolean primary_Key_Check) {
		this.primary_Key_Check = primary_Key_Check;
	}
	/**
	 * 項目並び順.<br>
	 */
	private int column_Sort_Order;
	/**
	 * 項目並び順を取得する。.<br>
	 */
	public int getColumn_Sort_Order() {
		return column_Sort_Order;
	}
	/**
	 * 項目並び順をセットする。.<br>
	 */
	public void setColumn_Sort_Order( int column_Sort_Order) {
		this.column_Sort_Order = column_Sort_Order;
	}
	/**
	 * 登録日.<br>
	 */
	private Calendar fD_Create;
	/**
	 * 登録日を取得する。.<br>
	 */
	public Calendar getFD_Create() {
		if(fD_Create == null) {
			fD_Create = new GregorianCalendar(new Locale("ja", "JP"));
		}
		return fD_Create;
	}
	/**
	 * 登録日をセットする。.<br>
	 */
	public void setFD_Create(Calendar fD_Create) {
		this.fD_Create = fD_Create;
	}
	/**
	 * 登録者ID.<br>
	 */
	private int fD_Created;
	/**
	 * 登録者IDを取得する。.<br>
	 */
	public int getFD_Created() {
		return fD_Created;
	}
	/**
	 * 登録者IDをセットする。.<br>
	 */
	public void setFD_Created( int fD_Created) {
		this.fD_Created = fD_Created;
	}
	/**
	 * 更新日.<br>
	 */
	private Calendar fD_Update;
	/**
	 * 更新日を取得する。.<br>
	 */
	public Calendar getFD_Update() {
		if(fD_Update == null) {
			fD_Update = new GregorianCalendar(new Locale("ja", "JP"));
		}
		return fD_Update;
	}
	/**
	 * 更新日をセットする。.<br>
	 */
	public void setFD_Update(Calendar fD_Update) {
		this.fD_Update = fD_Update;
	}
	/**
	 * 更新者ID.<br>
	 */
	private int fD_Updated;
	/**
	 * 更新者IDを取得する。.<br>
	 */
	public int getFD_Updated() {
		return fD_Updated;
	}
	/**
	 * 更新者IDをセットする。.<br>
	 */
	public void setFD_Updated( int fD_Updated) {
		this.fD_Updated = fD_Updated;
	}
}

