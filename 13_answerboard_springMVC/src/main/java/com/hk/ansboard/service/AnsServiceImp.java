package com.hk.ansboard.service;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.reactive.TransactionSynchronizationManager;

import com.hk.ansboard.daos.IAnsDao;
import com.hk.ansboard.dtos.AnsDto;

@Service
public class AnsServiceImp implements IAnsService{

	@Autowired
	private IAnsDao ansDao;
	
	@Override
	public List<AnsDto> getAllList(String pnum) {
		return ansDao.getAllList(pnum);
	}

	@Override
	public int getPCount() {
		return ansDao.getPCount();
	}

	@Override
	public boolean insertBoard(AnsDto dto) {
		return ansDao.insertBoard(dto);
	}

	@Override
	public AnsDto getBoard(int seq) {
		return ansDao.getBoard(seq);
	}

	@Override
	public boolean readCount(int seq) {
		return ansDao.readCount(seq);
	}

	@Override
	public boolean boardUpdate(int seq, String title, String content) {
		return ansDao.boardUpdate(seq, title, content);
	}

	@Override
	public boolean boardDelete(int seq) {
		return ansDao.boardDelete(seq);
	}

	@Override
	public boolean mulDel(String[] seqs) {
		return ansDao.mulDel(seqs);
	}

	//Transaction 처리
	// - 선언적 처리 방법 : 어노테이션 작성 방식
	// - AOP 처리 방법 : AOP 적용 처리 방식
//	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean replyBoard(AnsDto dto) {
//		boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
//		System.out.println("트랜잭션 활성 상태: " + txActive);
		ansDao.replyUpdate(dto);
		
		if(true) {
			throw new RuntimeException("트랜잭션 롤백 테스트");
		}
		
		int count = ansDao.replyInsert(dto);
		return count>0?true:false;
	}

}
