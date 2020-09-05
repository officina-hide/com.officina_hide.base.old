package com.officina_hide.base.common;

/**
 * 項目情報クラス<br>
 * @author officina-hide.com ueno
 * @version 2.00
 * @since 2020/08/30 
 */
public class FD_Item {

	/**
	 * コンストラクター<br>
	 * <p>項目の初期設定を行う。</p>
	 * @author officina-hide.com ueno
	 * @param itemName 項目名
	 * @param dataType 
	 * @param data　項目情報
	 */
	public FD_Item(String itemName, Object data, String dataType) {
		setItemData(data);
		setItemName(itemName);
		setItemType(dataType);
	}

	/**
	 * 項目名<br>
	 */
	private String ItemName;
	
	/**
	 * 項目情報
	 */
	private Object itemData;
	
	/**
	 * 項目属性ID (リファレンス情報ID)
	 */
	private String itemType;

	/**
	 * @return itemName
	 */
	public String getItemName() {
		return ItemName;
	}

	/**
	 * @param itemName セットする itemName
	 */
	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	/**
	 * @return itemData
	 */
	public Object getItemData() {
		return itemData;
	}

	/**
	 * @param itemData セットする itemData
	 */
	public void setItemData(Object itemData) {
		this.itemData = itemData;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	/**
	 * Dataを文字列で返す。<br>
	 * @author officina-hide.com ueno
	 * @since 2.00 2020/09/04
	 * @return 文字列
	 */
	public String getStringOfData() {
		return (String) getItemData();
	}
}
