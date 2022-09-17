package com.board.domain;

import java.util.Date;

public class ReplyVO extends BaseVO {
	private int rno;
	private int bno;
	private String writer;
	private String content;
	private Date regDate;
	private int parnt_rno;
	private int depth;
	
	// 페이징 처리
	public int nowPage;
	public int displayPost;
	public int postNum;

	public int getParnt_rno() {
		return parnt_rno;
	}

	public void setParnt_rno(int parnt_rno) {
		this.parnt_rno = parnt_rno;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getDisplayPost() {
		return displayPost;
	}

	public void setDisplayPost(int displayPost) {
		this.displayPost = displayPost;
	}

	public int getPostNum() {
		return postNum;
	}

	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
}
