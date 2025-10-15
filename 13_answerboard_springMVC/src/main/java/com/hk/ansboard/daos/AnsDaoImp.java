package com.hk.ansboard.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.hk.ansboard.dtos.AnsDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AnsDaoImp implements IAnsDao{
	
	//생성자 주입
	private final SqlSessionTemplate sqlSession;
	
	//lombok을 이용하면 생략 가능
//	public AnsDaoImp(SqlSessionTemplate sqlSession) {
//		this.sqlSession=sqlSession;
//	}
	
//	@Autowired
//	@Qualifier("sqlSessionTemplate") //보조역할: Autowired랑 같이 사용(이름으로 구별)
//	private SqlSessionTemplate sqlSession;
	
	private String namespace = "com.hk.ansboard.dao.";

	@Override
	public List<AnsDto> getAllList(String pnum) {
		Map<String, String>map=new HashMap<>();
		map.put("pnum", pnum);
		return sqlSession.selectList(namespace+"boardlist",map);
	}

	@Override
	public int getPCount() {
		return sqlSession.selectOne(namespace+"getpcount");
	}

	@Override
	public boolean insertBoard(AnsDto dto) {
		int count=sqlSession.insert(namespace+"insertboard",dto);
		return count>0?true:false;
	}

	@Override
	public AnsDto getBoard(int seq) {
		Map<String, Integer> map = new HashMap<>();
		map.put("seq", seq);
		//boardlist 쿼리를 동적쿼리로 작성해서 상세보기도 가능
		return sqlSession.selectOne(namespace+"boardlist",map);
	}

	@Override
	public boolean readCount(int seq) {
		int count=sqlSession.update(namespace+"readcount",seq);
		return count>0?true:false;
	}

	@Override
	public boolean boardUpdate(int seq, String title, String content) {
		Map<String, Object> map = new HashMap<>();
		map.put("seq", seq);
		map.put("title", title);
		map.put("content", content);
		int count=sqlSession.update(namespace+"boardupdate",map);
		return count>0?true:false;
	}

	@Override
	public boolean boardDelete(int seq) {
		int count=sqlSession.update(namespace+"boarddelete",seq);
		return count>0?true:false;
	}

	@Override
	public boolean mulDel(String[] seqs) {
		Map<String,String[]>map = new HashMap<>();
		map.put("seq", seqs);
		int count=sqlSession.update(namespace+"muldel",map);
		return count>0?true:false;
	}

	@Override
	public int replyUpdate(AnsDto dto) {
		return sqlSession.update(namespace+"replyupdate",dto);
	}

	@Override
	public int replyInsert(AnsDto dto) {
		return sqlSession.insert(namespace+"replyinsert",dto);
	}

	
}
