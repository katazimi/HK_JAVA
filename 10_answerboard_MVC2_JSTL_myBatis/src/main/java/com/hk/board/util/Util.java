package com.hk.board.util;

//Actio Tag: <userbean/> ->  주로 DTO객체를 사용
// 			 <setproperty>: dto의 setter 메서드 호출
//			 <getpropoerty>: dto의 getter 메서드 호출
public class Util {
	
	private String arrowNbsp; //공백+화살표 이미지 문자열 저장
	
	public String getArrowNbsp() {
		return arrowNbsp;
	}
	
	// "&nbsp;&nbsp;&nbsp;<img src='img/arrow.png'/>"
	public void setArrowNbsp(String depth) {
		String nbsp = "";
		
		int depthInt = Integer.parseInt(depth);
		
		for (int i=0; i<depthInt; i++) {
			nbsp+= "&nbsp;&nbsp;&nbsp;";
		}
		this.arrowNbsp = nbsp+(depthInt>0?"<img src='img/arrow.png' width='15px' height='15px'/>":"");
	}
 }
