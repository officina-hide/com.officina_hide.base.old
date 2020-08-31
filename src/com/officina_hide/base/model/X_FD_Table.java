package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Item;

/**
 * テーブル情報IOクラス<br>
 * @author officina-hide.com
 * @version 2.00
 * @since 2020/08/29
 */
public class X_FD_Table extends FD_DB implements I_FD_Table {
	
	/**
	 * コンストラクター<br>
	 * <p>インスタンス時に項目を初夏設定する。</p>
	 * @author officina-hide.com ueno
	 * @since 2.00 2020/08/30
	 * @param env 環境情報
	 */
	public X_FD_Table(FD_EnvData env) {
		//項目リスト初期化
		// TODO テーブル項目情報から取得できるようにする。(2020/08/30 ueno)
		itemList.clear();
		itemList.add(new FD_Item(COLUMNNAME_FD_TABLE_ID, null, COLUMN_TYPE_INFORMATION_ID));
		itemList.add(new FD_Item(COLUMNNAME_TABLE_NAME, null, COLUMN_TYPE_TEXT));
		itemList.add(new FD_Item(COLUMNNAME_FD_NAME, null, COLUMN_TYPE_TEXT));
		itemList.add(new FD_Item(COLUMNNAME_FD_COMMENT, null, COLUMN_TYPE_FIELD_TEXT));
		itemList.add(new FD_Item(COLUMNNAME_FD_CREATE, null, COLUMN_TYPE_DATE));
		itemList.add(new FD_Item(COLUMNNAME_FD_CREATED, null, COLUMN_TYPE_INFORMATION_ID));
		itemList.add(new FD_Item(COLUMNNAME_FD_UPDATE, null, COLUMN_TYPE_DATE));
		itemList.add(new FD_Item(COLUMNNAME_FD_UPDATED, null, COLUMN_TYPE_INFORMATION_ID));
	}

	/**
	 * テーブル情報を初夏保存する。<br>
	 * @author officina-hide.com ueno
	 * @since 2.00 2020/08/30
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
//		if(getDateOfValue(COLUMNNAME_FD_CREATE) == null) {
//			setValue(COLUMNNAME_FD_CREATE, new Date());
//			setValue(COLUMNNAME_FD_UPDATE, new Date());
//			setValue(COLUMNNAME_FD_CREATED, env.getLoginUserID());
//			setValue(COLUMNNAME_FD_UPDATED, env.getLoginUserID());
//		} else {
//			setValue(COLUMNNAME_FD_UPDATE, new Date());
//			setValue(COLUMNNAME_FD_UPDATED, env.getLoginUserID());
//		}
//
//		StringBuffer sql = new StringBuffer();
//		StringBuffer setitem = new StringBuffer();
//		sql.append("INSERT INTO ").append(Table_Name).append(" SET ");
//		for(FD_Item item : itemList) {
//			if(setitem.toString().length() > 0) {
//				setitem.append(",");
//			}
//			if(item.getItemData() != null) {
//				switch(item.getItemType()) {
//				case COLUMN_TYPE_INFORMATION_ID:
//					setitem.append(item.getItemName()).append(" = ").append(getIntOfValue(item.getItemName()));
//					break;
//				case COLUMN_TYPE_TEXT:
//				case COLUMN_TYPE_FIELD_TEXT:
//					setitem.append(item.getItemName()).append(" = ").append(FD_SQ).append(getStringOfValue(item.getItemName())).append(FD_SQ);
//					break;
//				case COLUMN_TYPE_DATE:
//					setitem.append(item.getItemName()).append(" = ")
//						.append(FD_SQ).append(dateFormat.format(getDateOfValue(item.getItemName()).getTime())).append(FD_SQ);
//					break;
//				}
//			} else {
//				setitem.append(item.getItemName()).append(" = null");
//			}
//		}
//		sql.append(setitem);
//
//		execute(env, sql.toString());
	}
//
//	/**
//	 * テーブル項目情報を取得する。(int型)<br>
//	 * <p>もし、項目が見つからないときもしくは数値型と違うときは、0を返す。</p>
//	 * @param itemName 項目名
//	 * @return 項目情報(int型)
//	 */
//	private int getIntOfValue(String itemName) {
//		try {
//			return (int) getItemData(itemName);
//		} catch (ClassCastException e) {
//			return 0;
//		}
//	}
//
//	/**
//	 * テーブル項目情報を取得する。(String型)<br>
//	 * <p>もし、項目が見つからないときはnullを返す</p>
//	 * @param itemName 項目名
//	 * @return
//	 */
//	private String getStringOfValue(String itemName) {
//		return (String) getItemData(itemName);
//	}
//
//	/**
//	 * @param itemName
//	 * @return
//	 */
//	private Date getDateOfValue(String itemName) {
//		return (Date) getItemData(itemName);
//	}
//
//	/**
//	 * 指定された項目名から項目情報を抽出する。<br>
//	 * @param itemName
//	 * @return
//	 */
//	private Object getItemData(String itemName) {
//		for(FD_Item item : itemList) {
//			if(item.getItemName().equals(itemName)) {
//				return item.getItemData();
//			}
//		}
//		return null;
//	}
}
