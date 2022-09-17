package com.board.service;

import java.util.List;

import com.board.domain.BoardVO;

public interface BoardSerivce {
	
	// 게시물 목록페이지로 이동
	public List<BoardVO> list(BoardVO vo) throws Exception;
	
	// 게시물 작성
	public void write(BoardVO vo) throws Exception;
	
	// 게시판 상세 조회
	public BoardVO selectBoardDetail(BoardVO vo) throws Exception;
	
	// 게시믈 수정
	public void updateBoard(BoardVO vo) throws Exception;
	
	// 게시글 삭제
	public void deleteBoard(BoardVO vo) throws Exception;
	
	// 게시글 갯수
	public int boardCnt(BoardVO vo) throws Exception;
}
