package com.hk.board.dtos;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

@Data
public class HkDto implements Serializable{

	private static final long serialVersionUID = 5066693606872502219L;
	
	//은닉화: 중요한데이터는 멤버필드에 바로 접근하지 못하게 처리
	private int seq;
	private String id;
	private String title;
	private String content;
	private Date regdate;
	
	//default 생성자: 단독사용시에는 생략가능하지만 -> 오버로딩시에는 생략이 불가능
	public HkDto() {
		super();
	}

	public HkDto(int seq, String id, String title, String content, Date regdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
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
//	public Date getRegdate() {
//		return regdate;
//	}
//
//	public void setRegdate(Date regdate) {
//		this.regdate = regdate;
//	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "HkDto [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", regdate=" + regdate
				+ "]";
	}
	
	
}
