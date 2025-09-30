package com.hk.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.hk.board.config.SqlMapConfig;
import com.hk.dto.AnsDto;

public class AnsDao extends SqlMapConfig{

	private String namespace="com.hk.dao.";
	
	//1.글 목록조회
	public List<AnsDto> getAllList(String pnum){
		List<AnsDto> list = new ArrayList<AnsDto>();
		SqlSession sqlSession=null;
		
		Map<String, String>map = new HashMap<String, String>();
		map.put("pnum", pnum);
		
		try {
			//sqlSession 객체를 구하려면 openSession()을 통해서 얻어옴
			//openSession(true/false): t(autocommit)
			sqlSession=getSessionFactory().openSession(true);
			//selectList(쿼리ID)를 실행한다.
			list=sqlSession.selectList(namespace+"boardlist",map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return list;
	}
	//1-2 페이지 수 구하기
	public int getPCount() {
		int count=0;
		SqlSession sqlSession=null;
		
		try {
			sqlSession=getSessionFactory().openSession(true);
			count=sqlSession.selectOne(namespace+"getpcount");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return count;
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
	
	public boolean boardUpdate(int seq, String title, String content) {
		int count = 0;
		SqlSession sqlSession=null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seq", seq);
		map.put("title", title);
		map.put("content", content);
		
		try {
			sqlSession=getSessionFactory().openSession(true);
			
			count=sqlSession.update(namespace+"boardupdate",map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return count>0?true:false;
	}
	
	public boolean boardDelete(int seq) {
		int count = 0;
		SqlSession sqlSession=null;
		
		try {
			sqlSession=getSessionFactory().openSession(true);
			
			count=sqlSession.update(namespace+"boarddelete",seq);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return count>0?true:false;
	}
	
	public boolean mulDel(String[] seqs) {
		int count=0;
		SqlSession sqlSession=null;
		
		//동적쿼리는 map에 답아서 전달!!
		Map<String,String[]>map = new HashMap<String, String[]>();
		map.put("seqs", seqs);
		
		try {
			sqlSession=getSessionFactory().openSession(true);
			count=sqlSession.update(namespace+"muldel",map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return count>0?true:false;
	}
	
	public boolean replyBoard(AnsDto dto) {
		int count=0;
		SqlSession sqlSession=null;
		
		try {
			//transaction 처리: auto commit -> false
			sqlSession=getSessionFactory().openSession(false);
			// 같은 그룹에서 부모의 step보다 큰 값을 갖는 글들의 step을 +1
			sqlSession.update(namespace+"replyupdate",dto);
			count=sqlSession.insert(namespace+"replyinsert",dto);
			sqlSession.commit();
			
		} catch (Exception e) {
			//중간에 작업이 실패하면 롤백
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return count>0?true:false;
	}
}
