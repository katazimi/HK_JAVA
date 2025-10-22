package com.hk.board.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// client: 파라미터 ----> command객체가 받음
// controller --> service --> DTO --> DB

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertCalCommand {
	
	private int seq;
	@NotBlank(message = "아이디를 입력하세요.")
	private String id;
	@NotBlank(message = "제목을 입력하세요.")
	private String title;
	@NotBlank(message = "내용을 입력하세요.")
	private String content;
	
	//mdate 컬럼에 저장할 값: 12자리로 변환해서 나중에 DTO에 전달
	@NotNull(message = "년도를 입력하세요")
	private int year;
	@NotNull(message = "월을 입력하세요")
	private int month;
	@NotNull(message = "일을 입력하세요")
	private int date;
	@NotNull(message = "시간을 입력하세요")
	private int hour;
	@NotNull(message = "분을 입력하세요")
	private int min;
}