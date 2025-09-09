package com.hk.board.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hk.board.dtos.BuyDto;

public class BuyDao {
	public BuyDao() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<BuyDto> getAllBuying(){
		List<BuyDto> list = new ArrayList<BuyDto>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:mariadb://localhost:3306/sqlDB";
		String user = "root";
		String password = "wlgns5002!";
		
		String sql = " SELECT num, userID, prodName, groupName, price, amount FROM buyTbl";
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				BuyDto bDto = new BuyDto();
				bDto.setNum(rs.getInt(1));
				bDto.setUserID(rs.getString(2));
				bDto.setProdName(rs.getString(3));
				bDto.setGroupName(rs.getString(4));
				bDto.setPrice(rs.getInt(5));
				bDto.setAmount(rs.getInt(6));
				list.add(bDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				psmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
}
