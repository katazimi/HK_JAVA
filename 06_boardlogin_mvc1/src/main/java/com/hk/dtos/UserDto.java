package com.hk.dtos;

import java.io.Serializable;
import java.sql.Date;

public class UserDto implements Serializable{
	
	private static final long serialVersionUID = 8076875390372850812L;
	
	private String id;
	private String password;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String enabled;
	private String role;
	private Date regdate;
	
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDto(String id, String password, String name, String address, String phone,String email) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
		this.email = email;
	}

	public UserDto(String id, String name, String password, String address, String email) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
		this.email = email;
	}
	
	public UserDto(String id, String address, String email) {
		super();
		this.id = id;
		this.address = address;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", password=" + password + ", name=" + name + ", address=" + address + ", email="
				+ email + ", enabled=" + enabled + ", role=" + role + ", regdate=" + regdate + "]";
	}
	
}
