package com.hk.board.command;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//command 역할: client와 controller 간에 데이터 주고받는 중간역할
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCalCommand {
	//seq의 값이 null이면 하나이상 체크하지 않고 삭제 요청을 함
	// 에러메세지: "삭제하려면 최소 하나 이상 체크하세요."
	@NotEmpty(message = "삭제하려면 최소 하나 이상 체크하세요.")
	private String[] seq;
}
