package com.hk.board.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateCalCommand {
	
	private int seq;
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
	
	public void mdateToYMDHM(String mdate) {
		this.year=Integer.parseInt(mdate.substring(0,4));
		this.month=Integer.parseInt(mdate.substring(4,6));
		this.date=Integer.parseInt(mdate.substring(6,8));
		this.hour=Integer.parseInt(mdate.substring(8,10));
		this.min=Integer.parseInt(mdate.substring(10));
	}
}
