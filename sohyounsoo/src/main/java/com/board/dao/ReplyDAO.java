package com.board.dao;

import java.util.List;

import com.board.domain.BoardVO;
import com.board.domain.ReplyVO;

public interface ReplyDAO {

	public List<ReplyVO> selectReplyList(ReplyVO vo) throws Exception;

	public void writeReply(ReplyVO vo) throws Exception;

	public void deleteReply(ReplyVO vo) throws Exception;

	public void updateReply(ReplyVO vo) throws Exception;

	public int replyCnt(ReplyVO vo) throws Exception;

}
