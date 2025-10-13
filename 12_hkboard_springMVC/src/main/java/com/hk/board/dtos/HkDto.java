package com.hk.board.dtos;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
// DTO: 데이터를 저장해서 전달할때 사용하는 객체
public class HkDto implements Serializable{

	private static final long serialVersionUID = 5066693606872502219L;
	
	//은닉화: 중요한 데이터는 맴버필드에 바로 접근 못하게 처리
	private int seq;
	private String id;
	private String title;
	private String content;
	private Date regDate;
	
	//default 생성자: 단독으로 사용할 경우는 생략가능
	//              -> 생성자 오버로딩을 하면 생략X
//	public HkDto() {
//
//	}
	
	//생성자 오버로딩: 객체 생성할때 초기화도 동시에 하고 싶을 경우
	public HkDto(int seq, String id, String title, String content, Date regDate) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
	}

	public HkDto(String id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}
	
	public HkDto(int seq, String title, String content) {
		super();
		this.seq = seq;
		this.title = title;
		this.content = content;
	}

//	public int getSeq() {
//		return seq;
//	}
//
//	public void setSeq(int seq) {
//		this.seq = seq;
//	}
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//	public Date getRegDate() {
//		return regDate;
//	}
//
//	public void setRegDate(Date regDate) {
//		this.regDate = regDate;
//	}
//
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}

	//부모의 메서드를 자식이 재정의한다.
	// parentObj.method() --> 자식메서드가 호출된다. 
//	@Override
//	public String toString() {
//		return "HkDto [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", regDate=" + regDate
//				+ "]";
//	}
	
	
}






