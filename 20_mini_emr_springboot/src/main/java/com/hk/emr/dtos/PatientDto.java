package com.hk.emr.dtos;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Alias(value="patientDto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientDto {
	private int patientId;
	private String chartNum;
	private String name;
	private String phone;
	private String birth;
}
