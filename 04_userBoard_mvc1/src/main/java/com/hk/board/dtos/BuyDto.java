package com.hk.board.dtos;

import java.io.Serializable;

public class BuyDto implements Serializable{

	private static final long serialVersionUID = 8674338055032548279L;
	
	private int num;
	private String userID;
	private String prodName;
	private String groupName;
	private int price;
	private int amount;
	
	public BuyDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BuyDto(int num, String userID, String prodName, String groupName, int price, int amount) {
		super();
		this.num = num;
		this.userID = userID;
		this.prodName = prodName;
		this.groupName = groupName;
		this.price = price;
		this.amount = amount;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BuyDto [num=" + num + ", userID=" + userID + ", prodName=" + prodName + ", groupName=" + groupName
				+ ", price=" + price + ", amount=" + amount + "]";
	}
	
	
}
