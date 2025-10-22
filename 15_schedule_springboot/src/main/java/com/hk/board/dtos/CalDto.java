package com.hk.board.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
//@Alias(value = "calDto") //mapper.xml에서 사용하는 변수명 설정
public class CalDto {
	private int seq;
	private @NonNull String id;
	private @NonNull String title;
	private @NonNull String content;
	private @NonNull String mdate;
	private Date regdate;
}
