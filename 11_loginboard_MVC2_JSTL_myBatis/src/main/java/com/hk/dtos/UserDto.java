package com.hk.dtos;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든 필드 초기화 생성자
@Data //getter,setter
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
