package com.board.service;

import java.util.List;

import com.board.domain.BoardVO;
import com.board.domain.ReplyVO;

public interface ReplyService {
	
	// 댓글 조회
	public List<ReplyVO> selectReplyList(ReplyVO vo) throws Exception;
	
	// 댓글 작성
	public void writeReply(ReplyVO vo) throws Exception;
	
	// 댓글 삭제
	public void deleteReply(ReplyVO vo) throws Exception;
	
	// 댓글 수정
	public void updateReply(ReplyVO vo) throws Exception;
	
	// 댓글 갯수
	public int replyCnt(ReplyVO vo) throws Exception;

}
