package com.hk.emr.command;

import org.hibernate.validator.constraints.Length;

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
public class AddUserCommand {
	@NotBlank(message = "아이디를 입력하세요")
	private String id;
	
	@NotBlank(message = "이름을 입력하세요")
	private String name;
	
	@NotBlank(message = "비밀번호를 입력하세요")
	@Length(min = 8 , max = 16, message = "8자리이상, 16자이하로 입력하세요")
	private String password;
	
	@NotBlank(message = "직종을 선택하세요")
	private String role; 
}
