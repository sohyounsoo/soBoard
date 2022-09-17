package com.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.dao.BoardDAO;
import com.board.domain.BoardVO;

@Service
public class BoardServiceImpl implements BoardSerivce {
	
	@Inject
	BoardDAO dao;
	
	// 게시물 목록페이지로 이동
	@Override
	public List<BoardVO> list(BoardVO vo) throws Exception {
		return dao.list(vo); 
	}
	
	// 게시물 작성
	@Override
	public void write(BoardVO vo) throws Exception {
		dao.wirte(vo);
	}
	
	// 게시판 상세 조회
	@Override
	public BoardVO selectBoardDetail(BoardVO vo) throws Exception {
		return dao.selectBoardDetail(vo);
	}
	
	// 게시믈 수정
	@Override
	public void updateBoard(BoardVO vo) throws Exception {
		dao.updateBoard(vo);
	}
	
	// 게시글 삭제
	@Override
	public void deleteBoard(BoardVO vo) throws Exception {
		dao.deleteBoard(vo);
	}
	
	// 게시글 갯수
	@Override
	public int boardCnt(BoardVO vo) throws Exception {
		return dao.boardCnt(vo);
	}

}
