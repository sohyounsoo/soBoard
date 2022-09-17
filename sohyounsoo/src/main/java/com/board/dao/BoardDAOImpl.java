package com.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	private SqlSession sql;
	
	private String namespace = "com.board.mappers.board";
	
	// 게시물 목록페이지로 이동
	@Override
	public List<BoardVO> list(BoardVO vo) throws Exception {
		return sql.selectList(namespace + ".list", vo);
	}
	
	// 게시물 작성
	@Override
	public void wirte(BoardVO vo) throws Exception {
		sql.update(namespace + ".write", vo);
	}
	
	// 게시판 상세 조회
	@Override
	public BoardVO selectBoardDetail(BoardVO vo) throws Exception {
		return sql.selectOne(namespace + ".selectBoardDetail", vo);
	}
	
	// 게시믈 수정
	@Override
	public void updateBoard(BoardVO vo) throws Exception {
		sql.update(namespace + ".updateBoard", vo);
	}
	
	// 게시글 삭제
	@Override
	public void deleteBoard(BoardVO vo) throws Exception {
		sql.delete(namespace + ".delete", vo);
	}

	@Override
	public int boardCnt(BoardVO vo) throws Exception {
		return sql.selectOne(namespace + ".boardCnt", vo);
	}

}
