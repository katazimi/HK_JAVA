package com.hk.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hk.datasource.Database;
import com.hk.dtos.BoardDto;

public class BoardDao extends Database{
	private BoardDao() {}
	
	private static BoardDao boardDao;
	
	public static BoardDao getBoardDao() {
		if (boardDao==null) {
			boardDao = new BoardDao();
		} 
		return boardDao;
	}
	
	public List<BoardDto> getAllList() {
		List<BoardDto> list = new ArrayList<BoardDto>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT TSEQ,TID,TTITLE,TCONTENT,TREGDATE FROM T_BOARD";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			while(rs.next()) {
				BoardDto dto = new BoardDto();
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs, psmt, conn);
		}
		
		return list;
	}
	
	public boolean insertBoard(BoardDto dto) {
		int count=0;
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql = "INSERT INTO T_BOARD VALUES(NULL,?,?,?,SYSDATE(),'N')";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			System.out.println(dto.getId());
			System.out.println(dto.getContent());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(null, psmt, conn);
		}
		
		return count>0?true:false;
	}
}
