package com.hk.emr.dtos;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Alias(value="doctorDto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DoctorDto {
	private int doctorId;
	private int userId;
	private int deptId;
	private String licensNumber;
	private String name;
	private String deptName;
}
