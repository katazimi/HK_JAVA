package com.hk.loginboard.dtos;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
	private int seq;
	private String id;
	private String name;
	private String password;
	private String address;
	private String email;
	private String enabled;
	private String role;
	private Date regdate;
}
