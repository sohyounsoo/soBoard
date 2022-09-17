package com.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.BoardVO;
import com.board.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Inject
	private SqlSession sql;
	
	private static String namespace = "com.board.mappers.reply";
	
	@Override
	public List<ReplyVO> selectReplyList(ReplyVO vo) throws Exception {
		return sql.selectList(namespace + ".selectReplyList", vo);
		
	}

	@Override
	public void writeReply(ReplyVO vo) throws Exception {
		sql.insert(namespace + ".writeReply", vo);
	}

	@Override
	public void deleteReply(ReplyVO vo) throws Exception {
		sql.delete(namespace + ".deleteReply", vo);
		
	}

	@Override
	public void updateReply(ReplyVO vo) throws Exception {
		sql.delete(namespace + ".updateReply", vo);
		
	}

	@Override
	public int replyCnt(ReplyVO vo) throws Exception {
		return sql.selectOne(namespace + ".replyCnt", vo);
		
	}

}
