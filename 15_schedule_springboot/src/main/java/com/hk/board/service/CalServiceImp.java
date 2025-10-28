package com.hk.board.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.board.command.DeleteCalCommand;
import com.hk.board.command.InsertCalCommand;
import com.hk.board.command.UpdateCalCommand;
import com.hk.board.dtos.CalDto;
import com.hk.board.mapper.CalMapper;
import com.hk.board.utils.Util;

import jakarta.servlet.http.HttpServletRequest;

@Service
@Transactional
public class CalServiceImp {
	
	@Autowired
	private Util util;
	
	@Autowired
	private CalMapper calMapper;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//달력폼 만드는 기능
	//request객체를 전달받음
	public Map<String, Integer> makeCalendar(HttpServletRequest request) {
		String paramYear=request.getParameter("year");
		String paramMonth=request.getParameter("month");
		
		//달력에 필요한 값을 구하기 위해 calendar객체 필요
		Calendar cal = Calendar.getInstance();
		
		//paramYear등의 값이 null이 아니면 사용자가 원하는 달을 요청
		//이외의 요청은 현재날짜로 처리
		int year = (paramYear==null)?cal.get(Calendar.YEAR):Integer.parseInt(paramYear);
		int month = (paramMonth==null)?cal.get(Calendar.MONTH)+1:Integer.parseInt(paramMonth);
		
		if (month>12) {
			month=1;
			year++;
		}
		
		if (month<1) {
			month=12;
			year--;
		}
		
		//1. 해당 월의 1일에 대한 요일을 구하기
		//	- 1~7 숫자를 반환: 1=일요일 ~ 7=토요일
		cal.set(year, month-1,1); //월:0~11월 -> -1했음
		int dayOfWeek=cal.get(Calendar.DAY_OF_WEEK);
		
		//2. 월의 마지막 날 구하기
		int lastDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		//달력 구현에 필요한 값을 map에 저장
		Map<String, Integer>map = new HashMap<>();
		map.put("year", year);
		map.put("month", month);
		map.put("dayOfWeek", dayOfWeek);
		map.put("lastDay", lastDay);
		
		//한달 단위 일일별 일정 구하기
		String id="JH"; //session에서 가져오기
		String yyyyMM=year+util.isTwo(month+"");
		List<CalDto>clist=calViewList(id, yyyyMM);
		
		//구한일정을 request Scope에 저장
		request.setAttribute("clist", clist);
		
		return map;
	}
	
	//일정 추가하기
	public boolean insertCalBoard(InsertCalCommand insertCalCommand) {
		//command에는 id,title,content,year,month,date,min <- DB와 일치하지 않음
		// year,month,date,min -> mdate(12자리)로 변환
		
		String mdate = insertCalCommand.getYear() 
					 + util.isTwo(insertCalCommand.getMonth()+"")
					 + util.isTwo(insertCalCommand.getDate()+"")
					 + util.isTwo(insertCalCommand.getHour()+"")
					 + util.isTwo(insertCalCommand.getMin()+"");
		
		//command -> dto로 값을 복사해서 넣는 작업
//		CalDto dto = new CalDto();
//		dto.setId(insertCalCommand.getId());
//		dto.setTitle(insertCalCommand.getTitle());
//		dto.setContent(insertCalCommand.getContent());
//		dto.setMdate(mdate);
		
								  //(복사할 대상 객체, 붙여넣을 객체의 타입)
		CalDto dto = modelMapper.map(insertCalCommand, CalDto.class);
		dto.setMdate(mdate);
		
		int count = calMapper.insertCalBoard(dto);
		
		return count>0?true:false;
	}
	
	//일정목록보기
	public List<CalDto> calBoardList(String id, Map<String, String>paramMap) {
		
		//조회할 일정의 날짜 8자리 만들기
		String yyyyMMdd=paramMap.get("year")
					   +util.isTwo(paramMap.get("month"))
					   +util.isTwo(paramMap.get("date"));
		Map<String,String>map = new HashMap<>();
		map.put("id", id);
		map.put("yyyyMMdd", yyyyMMdd);
		
		
		return calMapper.calBoardList(map);
	}
	
	//일정 삭제하기
	public boolean calMulDel(String[] seqs) {
		Map<String,String[]>map = new HashMap<>();
		map.put("seqs", seqs);
		return calMapper.calMulDel(map);
	}
	
	//일정 상세보기
	public CalDto calBoardDetail(int seq) {
		return calMapper.calBoardDetail(seq);
	}
	
	//일정 수정하기
	public boolean calBoardUpdate(UpdateCalCommand updateCalCommand) {
		//Client				Server
		//		 ----> Command --> dto --> mapper 전달
		CalDto dto=modelMapper.map(updateCalCommand, CalDto.class);
		
		String mdate = updateCalCommand.getYear() 
				 + util.isTwo(updateCalCommand.getMonth()+"")
				 + util.isTwo(updateCalCommand.getDate()+"")
				 + util.isTwo(updateCalCommand.getHour()+"")
				 + util.isTwo(updateCalCommand.getMin()+"");
		dto.setMdate(mdate);
		
		return calMapper.calBoardUpdate(dto);
	}
	
	public List<CalDto> calViewList(String id, String yyyyMM) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("id",id);
		map.put("yyyyMM", yyyyMM);
		return calMapper.calViewList(map);
	}
	
	public int calBoardCount(String id, String yyyyMMdd) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("id",id);
		map.put("yyyyMMdd", yyyyMMdd);
		return calMapper.calBoardCount(map);
	}
}
