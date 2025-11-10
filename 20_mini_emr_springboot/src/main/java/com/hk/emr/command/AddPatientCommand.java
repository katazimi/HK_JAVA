package com.hk.emr.command;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddPatientCommand {
	@NotBlank(message = "환자 이름을 입력하세요")
	private String name;
	
	@NotBlank(message = "환자의 전화번호를 입력하세요")
	private String phone;
	
	@NotBlank(message = "환자의 생년월일을 입력하세요")
	private String birth;
}
