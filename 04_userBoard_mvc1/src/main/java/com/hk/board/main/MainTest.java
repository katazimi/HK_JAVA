package com.hk.board.main;

import java.util.ArrayList;
import java.util.List;

import com.hk.board.daos.BuyDao;
import com.hk.board.daos.UserDao;
import com.hk.board.dtos.BuyDto;
import com.hk.board.dtos.UserDto;

public class MainTest {
	public MainTest() {
		
	}
	
	//회원목록 조회 Test
	public static void getAllListTest(){
		List<UserDto> list = new ArrayList<UserDto>();
		
		UserDao uDao=new UserDao();
		list=uDao.getAllUser();
		for (UserDto userDto : list) {
			System.out.println(userDto);
		}
		
		List<BuyDto> list2 = new ArrayList<BuyDto>();
		BuyDao bDao = new BuyDao();
		list2 = bDao.getAllBuying();
		for (BuyDto dto : list2) {
			System.out.println(dto);
		}
	}
	public static void main(String[] args) {
		getAllListTest();
	}
}

