package com.hk.ansboard.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}
		)
@WebAppConfiguration
class AnsControllerTest {
	
	// 웹 테스트 환경에서 서버 없이 컨트롤러 테스트를 수행하기 위한 Spring Context객체
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mock; //가상의 클라이언트 요청을 처리할 객체
	
	@Test
	void testBoardList() throws Exception {
		//mock객체 생성
		this.mock=MockMvcBuilders.webAppContextSetup(wac).build();
		
		//boardlist.do 요청하기
		MvcResult result = mock.perform(
				MockMvcRequestBuilders.get("/boardlist.do").param("pnum", "1")
				).andExpect(MockMvcResultMatchers.status().isOk())//요청처리 수 상태가 200인지 검증
				.andReturn();//MvcResult 객체 반환
		
		//처리된 결과 내용을 받아옴
		int statusCode = result.getResponse().getStatus();
		System.out.println("status코드: "+statusCode);
		System.out.println("HttpStatus: "+HttpStatus.OK.value());
		//OK는 200코드 == 현재실행된 코드와 비교
		assertEquals(HttpStatus.OK.value(), statusCode);
	}

	//상세보기
	@Test
	void testBoardDetail() throws Exception {
		//mock객체 생성
		this.mock=MockMvcBuilders.webAppContextSetup(wac).build();
		
		MvcResult result=mock.perform(
				                      MockMvcRequestBuilders
				                      .get("/boarddetail.do")
				                      .param("seq","79")
									)
							 .andExpect(MockMvcResultMatchers.view().name("boarddetail"))
							 .andExpect(MockMvcResultMatchers.model().attributeExists("dto"))
				             .andExpect(MockMvcResultMatchers.status().isOk()) //요청처리 수 상태가 200인지 검증
				             .andDo(MockMvcResultHandlers.print()) //상세 로그 출력
				             .andReturn();//MvcResult객체 반환
		
		//처리된 결과 내용을 받아옴
		int statusCode=result.getResponse().getStatus();
		System.out.println("status코드:"+statusCode);
		System.out.println("HttpStatus:"+HttpStatus.OK.value());
		//OK는 200코드 == 현재실행 된 상태코드와 비교
		assertEquals(HttpStatus.OK.value(), statusCode);
	}
	
	//글추가폼이동
	@Test
	void testInsertBoardForm() throws Exception {
		//mock객체 생성
		this.mock=MockMvcBuilders.webAppContextSetup(wac).build();
		
		MvcResult result=mock.perform(
				                      MockMvcRequestBuilders
				                      .get("/insertboardform.do")
									)
							 .andExpect(MockMvcResultMatchers.view().name("insertboardform"))
				             .andExpect(MockMvcResultMatchers.status().isOk()) //요청처리 수 상태가 200인지 검증
				             .andDo(MockMvcResultHandlers.print()) //상세 로그 출력
				             .andReturn();//MvcResult객체 반환
		
		//처리된 결과 내용을 받아옴
		int statusCode=result.getResponse().getStatus();
		System.out.println("status코드:"+statusCode);
		System.out.println("HttpStatus:"+HttpStatus.OK.value());
		//OK는 200코드 == 현재실행 된 상태코드와 비교
		assertEquals(HttpStatus.OK.value(), statusCode);
	}
	//글추가하기
	@Test
	void testInsertBoard() throws Exception {
		//mock객체 생성
		this.mock=MockMvcBuilders.webAppContextSetup(wac).build();
		
		MvcResult result=mock.perform(
				                      MockMvcRequestBuilders
				                      .post("/insertboard.do")
				                      .param("id", "hk")
				                      .param("title", "test제목")
				                      .param("content","test내용")
									)
				             .andExpect(MockMvcResultMatchers.status().is3xxRedirection()) //요청처리 수 상태가 200인지 검증     
				             .andDo(MockMvcResultHandlers.print()) //상세 로그 출력
				             .andReturn();//MvcResult객체 반환
		
		//처리된 결과 내용을 받아옴
		int statusCode=result.getResponse().getStatus();
		System.out.println("status코드:"+statusCode);
		System.out.println("HttpStatus:"+HttpStatus.OK.value());
		// 기능 실행후 redirect되면 상태코드는 302를 반환한다. 만약 302를 기대한다면 아래와 같이 작성해야 됨
		//is3xxRedirection()은 300~~코드 == 현재실행 된 상태코드와 비교(글추가기능은 작업완료 후 redirect된다. -> 302
		assertTrue(HttpStatus.valueOf(statusCode).is3xxRedirection());
	}
}
