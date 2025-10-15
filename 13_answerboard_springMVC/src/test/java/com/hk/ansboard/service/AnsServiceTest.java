package com.hk.ansboard.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hk.ansboard.daos.IAnsDao;
import com.hk.ansboard.dtos.AnsDto;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}
		)
@WebAppConfiguration
class AnsServiceTest {
	
	@Autowired
	private IAnsDao ansdao;

	@Test
	void testGetAllList() {
		List<AnsDto> list= ansdao.getAllList("1");
		
		assertNotNull(list, "조회 결과가 null이 아니어야 합니다");
        System.out.println("조회된 게시글 수: " + list.size());
        // 실제 데이터가 있다면 추가 검증
        if (!list.isEmpty()) {
            assertNotNull(list.get(0).getSeq());
            assertNotNull(list.get(0).getTitle());
        }
	}

	@Test
	void testGetPCount() {
		int pcount = ansdao.getPCount();
		assertNotNull(pcount, "조회결과가 null이 아니어야 합니다");
		System.out.println("pCount: " + pcount);
	}

	@Test
	void testInsertBoard() {
		AnsDto dto = new AnsDto(1,"jh","테스트","테스트 내용");
		boolean isS = ansdao.insertBoard(dto);
		
		if (isS) {
			System.out.println(dto.getSeq());
			System.out.println(dto.getId());
			System.out.println(dto.getTitle());
			System.out.println(dto.getContent());
		}else {
			System.out.println("실패");
		}
	}

	@Test
	void testGetBoard() {
		AnsDto dto = ansdao.getBoard(3);
		
		assertNotNull(dto, "조회 결과가 null이 아니어야 합니다");
		System.out.println(dto.getSeq());
		System.out.println(dto.getId());
		System.out.println(dto.getTitle());
		System.out.println(dto.getContent());
	}

	@Test
	void testReadCount() {
		boolean isS = ansdao.readCount(1);
		
		if (isS) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
	}

	@Test
	void testBoardUpdate() {
		boolean isS = ansdao.boardUpdate(1, "테스트 성공", "테스트 성공");
		
		if (isS) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
	}

	@Test
	void testBoardDelete() {
		boolean isS = ansdao.boardDelete(1);
		
		if (isS) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
	}

	@Test
	void testMulDel() {
		String[] s = {"1","2","3"};
		
		boolean isS = ansdao.mulDel(null);
		
		if (isS) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
	}

	@Test
	void testReplyBoard() {
		
	}

}
