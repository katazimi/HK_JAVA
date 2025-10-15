package com.hk.ansboard.service;

import java.util.List;

import com.hk.ansboard.dtos.AnsDto;

public interface IAnsService {
	//1.글 목록조회
	public List<AnsDto> getAllList(String pnum);
	//1-2 페이지 수 구하기
	public int getPCount();
	//2.글 추가하기
	public boolean insertBoard(AnsDto dto);
	//3.글 상세조회
	public AnsDto getBoard(int seq);
	//4.조회수 올리기
	public boolean readCount(int seq);
	//5.수정하기
	public boolean boardUpdate(int seq, String title, String content);
	//6.삭제하기
	public boolean boardDelete(int seq);
	//7.여러글 삭제하기
	public boolean mulDel(String[] seqs);
	//8.답글추가하기: update,insert --> Transction처리 필요!!
	public boolean replyBoard(AnsDto dto);
}
