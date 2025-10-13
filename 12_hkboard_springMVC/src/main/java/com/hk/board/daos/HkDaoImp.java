package com.hk.board.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.board.dtos.HkDto;

//Dao 클래스: 쿼리를 실행하는 클래스
@Repository
public class HkDaoImp implements IHkDao{

	private String namespace="com.hk.board.dao.";
	
	//스프링이 객체의 타입을 확인해서 주입해줌
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<HkDto> getAllList() {
		return sqlSession.selectList(namespace+"boardlist");
	}

	@Override
	public boolean insertBoard(HkDto dto) {
		int count=sqlSession.insert(namespace+"insertboard",dto);
		return count>0?true:false;
	}

	@Override
	public HkDto getBoard(int seq) {
		return sqlSession.selectOne(namespace+"getboard", seq);
	}

	@Override
	public boolean updateBoard(HkDto dto) {
		int count=sqlSession.update(namespace+"boardupdate", dto);
		return count>0?true:false;
	}

	@Override
	public boolean deleteBoard(int seq) {
		int count=sqlSession.delete(namespace+"deleteboard", seq);
		return count>0?true:false;
	}

	@Override
	public boolean mulDel(String[] seqs) {
		//동적쿼리 사용할 경우 동적쿼리에 전달하는 파라미터는 Map을 사용
		Map<String, String[]>map=new HashMap<>();
		map.put("seqs", seqs);
		int count=sqlSession.delete(namespace+"muldel", map);
		return count>0?true:false;
	}

}






