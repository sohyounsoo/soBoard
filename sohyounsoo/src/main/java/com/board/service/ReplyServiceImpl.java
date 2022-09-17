package com.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.dao.ReplyDAO;
import com.board.domain.BoardVO;
import com.board.domain.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	private ReplyDAO dao;

	@Override
	public List<ReplyVO> selectReplyList(ReplyVO vo) throws Exception {
		return dao.selectReplyList(vo);
	}

	@Override
	public void writeReply(ReplyVO vo) throws Exception {
		dao.writeReply(vo);
	}

	@Override
	public void deleteReply(ReplyVO vo) throws Exception {
		dao.deleteReply(vo);
	}

	@Override
	public void updateReply(ReplyVO vo) throws Exception {
		dao.updateReply(vo);
	}

	@Override
	public int replyCnt(ReplyVO vo) throws Exception {
		return dao.replyCnt(vo);
	}

}
