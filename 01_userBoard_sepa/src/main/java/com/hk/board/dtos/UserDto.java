package com.hk.board.dtos;
// DTO객체: 데이터를 운발할때 사용할 객체
//		java OOP 3대 개념: 상속, 은닉화, 다형성 -> 은닉화 적용
//		DB테이블에 컬럼과 매칭
// 구성내용: 멤버필드, 생성자, get/set메서드, toString 메서드

import java.io.Serializable;
import java.util.Date;

public class UserDto implements Serializable{
	
	private static final long serialVersionUID = 2321399647172914564L;
	
	private String userID;
	private String name;
	private int birthYear;
	private String addr;
	private String mobile1;
	private String mobile2;
	private int height;
	private Date mDate;
	
	//default 생성자 : 단독으로 사용할떄는 생략가능
	//					-> 생성자 오버로딩을 하게 되면 생략X
	public UserDto() {
		super();
	} 
	
	public UserDto(String userID, String name, int birthYear, String addr, String mobile1, String mobile2, int height,
			Date mDate) {
		super();
		this.userID = userID;
		this.name = name;
		this.birthYear = birthYear;
		this.addr = addr;
		this.mobile1 = mobile1;
		this.mobile2 = mobile2;
		this.height = height;
		this.mDate = mDate;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getMobile1() {
		return mobile1;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	public String getMobile2() {
		return mobile2;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Date getmDate() {
		return mDate;
	}

	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}

	@Override
	public String toString() {
		return "UserDto [userID=" + userID + ", name=" + name + ", birthYear=" + birthYear + ", addr=" + addr
				+ ", mobile1=" + mobile1 + ", mobile2=" + mobile2 + ", height=" + height + ", mDate=" + mDate + "]";
	}
	
	
	
	
}
