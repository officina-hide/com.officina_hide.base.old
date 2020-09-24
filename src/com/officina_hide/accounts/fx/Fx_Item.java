package com.officina_hide.accounts.fx;

import com.officina_hide.base.model.X_Fx_ViewItem;

import javafx.scene.Node;

/**
 * 画面項目クラス<br>
 * @author officine-hide.com
 * @version 2.11 新規作成
 * @sinse 2020/09/19
 */
public class Fx_Item {

	/** 項目名 */
	private String itemName;
	/** 項目種別名 */
	private String itemType;
	/** 画面ノード */
	private Node itemNode;
	/** 項目桁数 */
	private int itemSize;
	/** 画面項目情報 */
	private X_Fx_ViewItem viewItem;

	/**
	 * @return itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName セットする itemName
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return itemType
	 */
	public String getItemType() {
		return itemType;
	}

	/**
	 * @param itemType セットする itemType
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public Node getItemNode() {
		return itemNode;
	}

	public void setItemNode(Node itemNode) {
		this.itemNode = itemNode;
	}

	public int getItemSize() {
		return itemSize;
	}

	public void setItemSize(int itemSize) {
		this.itemSize = itemSize;
	}

	/**
	 * @return viewItem
	 */
	public X_Fx_ViewItem getViewItem() {
		return viewItem;
	}

	public void setViewItem(X_Fx_ViewItem viewItem) {
		this.viewItem = viewItem;
	}
}
