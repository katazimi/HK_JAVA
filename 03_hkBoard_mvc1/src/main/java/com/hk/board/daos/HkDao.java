package com.hk.board.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hk.board.datasource.Database;
import com.hk.board.dtos.HkDto;

public class HkDao extends Database{
	
	public HkDao() {
		super(); //생략가능
	}
	
	//글 목록 조회: 여러개의 행이 반환 -> 반환타입=List
	public List<HkDto> getAllList() {
		List<HkDto> list = new ArrayList<HkDto>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT seq, id, title, content, regdate FROM hkboard ORDER BY regdate DESC ";
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				HkDto dto = new HkDto();
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
				list.add(dto);
				System.out.println(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, psmt, conn);
		}
		
		return list;
	}
	
	//글 추가하기: insert문 실행, 반환값 boolean타입
	public boolean insertBoard(HkDto dto) {
		int count=0;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " INSERT INTO hkboard VALUES(NULL,?,?,?,SYSDATE()) ";
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, psmt, conn);
		}
		
		return count>0?true:false;
	}
	
	//글 상세보기: select where절 반환값은 HkDto
	public HkDto getBoard(int seq
			) {
		HkDto dto = new HkDto();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT seq, id, title, content, regdate FROM hkboard WHERE seq=? ";
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			rs = psmt.executeQuery();
			while(rs.next()) {
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
				System.out.println(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, psmt, conn);
		}
		
		return dto;
	}
	
	public boolean updateBoard(HkDto dto) {
		int count=0;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " UPDATE hkboard SET title=?, content=? WHERE seq=?";
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getSeq());
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, psmt, conn);
		}
		
		return count>0?true:false;
	}
	
	public boolean deleteBoard(int seq) {
		int count=0;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " DELETE FROM hkboard WHERE seq=?";
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, psmt, conn);
		}
		
		return count>0?true:false;
	}
	
	public boolean mulDel(String[] seqs) {
		boolean isS = true;
		int[] count=null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		String sql = "DELETE FROM hkboard WHERE seq=?";
		
		try {
			conn = getConnection();
			//TX처리: 기본 자동커밋 -> 수동 설정
			conn.setAutoCommit(false);
			
			//batch 작업: 동일한 쿼리에 ?만 달라지면서 실행갯수가 변하는 작업
			psmt=conn.prepareStatement(sql);
			for (int i=0; i<seqs.length; i++) {
				psmt.setString(1, seqs[i]);
				psmt.addBatch(); //쿼리문을 준비시킴
			}
			
			count = psmt.executeBatch();
			//TX처리
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				//TX처리
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				//TX처리
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close(psmt,conn);
			
			//화면처리를 위한 성공여부 확인
			for(int i=0; i<count.length; i++) {
				if (count[i]!=1) {
					isS = false;
					break;
				}
			}
		}
		
		return isS;
	}
}
