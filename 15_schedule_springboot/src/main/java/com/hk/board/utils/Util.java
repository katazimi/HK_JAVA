package com.hk.board.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hk.board.dtos.CalDto;

@Component
public class Util {
	
	//문자열을 2자리로 변환해주는 메서드
	public String isTwo(String s) {
		return s.length()<2?"0"+s:s;
	}
	
	public String toDates(String mdate) {
		//Date형식으로 만들기: "yyyy-MM-dd hh:mm:ss"
		//mdate 12자리 -> "yyyy-MM-dd hh:mm:ss"
		String m = mdate.substring(0,4)+"-"
				 + mdate.substring(4,6)+"-"
				 + mdate.substring(6,8)+" "
				 + mdate.substring(8,10)+":"
				 + mdate.substring(10) + ":00";
		
		Timestamp tm = Timestamp.valueOf(m); //String->Date	변환
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일 HH:mm");
		return sdf.format(tm);
	}
	
	//일일별 일정 목록을 출력해주는 기능
	public String getCalViewList(int i, List<CalDto> clist) {
		String d = isTwo(i+"");
		String calList=""; //"<p>일정제목</p><p>일정제목</p>"
		for (int j=0; j<clist.size(); j++) {
			if(clist.get(j).getMdate().substring(6, 8).equals(d)) {
				String ctitle=clist.get(j).getTitle();
				calList+="<p class=calcontent>"+(ctitle.length()>7?ctitle.substring(0, 7)+"..":ctitle)+"</p>";
			}
		}
		
		return calList;
	}
}
