package com.hk.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든 필드 초기화 생성자
@RequiredArgsConstructor
@Data //getter,setter
@ToString
public class AnsDto {
	
	private @NonNull Integer seq;
	private @NonNull String id;
	private @NonNull String title;
	private @NonNull String content;
	private Date regdate;
	private int refer;
	private int step;
	private int depth;
	private String readCount;
	private String delFlag;
	
}
