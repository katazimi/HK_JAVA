package com.hk.ansboard.dtos;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data //getter,setter
@NoArgsConstructor //default 생성자
@AllArgsConstructor // 전체 필드 초기화 생성자
@RequiredArgsConstructor //원하는 필드만 초기화하는 생성자
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
