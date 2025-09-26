package com.hk.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.hk.board.config.SqlMapConfig;
import com.hk.dto.AnsDto;

public class AnsDao extends SqlMapConfig{

	private String namespace="com.hk.dao.";
	
	//1.글 목록조회
	public List<AnsDto> getAllList(){
		List<AnsDto> list = new ArrayList<AnsDto>();
		SqlSession sqlSession=null;
		
		try {
			//sqlSession 객체를 구하려면 openSession()을 통해서 얻어옴
			//openSession(true/false): t(autocommit)
			sqlSession=getSessionFactory().openSession(true);
			//selectList(쿼리ID)를 실행한다.
			list=sqlSession.selectList(namespace+"boardlist");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return list;
	}
	//2.글 추가하기
	public boolean insertBoard(AnsDto dto){
		int count = 0;
		SqlSession sqlSession=null;
		
		try {
			sqlSession=getSessionFactory().openSession(true);
			
			count=sqlSession.insert(namespace+"insertboard",dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return count>0?true:false;
	}
	//3.글 상세조회
	public AnsDto getBoard(int seq){
		AnsDto dto = new AnsDto();
		SqlSession sqlSession=null;
		
		try {
			//sqlSession 객체를 구하려면 openSession()을 통해서 얻어옴
			//openSession(true/false): t(autocommit)
			sqlSession=getSessionFactory().openSession(true);
			//파라미터 전달 방식의 기본은 map에 답아서 전달
			dto=sqlSession.selectOne(namespace+"getboard",seq);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return dto;
	}
	
	public boolean readCount(int seq){
		int count = 0;
		SqlSession sqlSession=null;
		
		try {
			sqlSession=getSessionFactory().openSession(true);
			
			count=sqlSession.update(namespace+"readcount",seq);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return count>0?true:false;
	}
}
