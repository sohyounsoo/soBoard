package com.board.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.domain.BoardVO;
import com.board.domain.Page;
import com.board.domain.ReplyVO;
import com.board.service.ReplyService;

@Controller
@RequestMapping("/reply/*")
public class ReplyController {
	
	@Inject
	private ReplyService replyService;
	
	public final static String  PATH = "/board/reply";
	
	// 댓글 리스트 불러오기
	@RequestMapping(value = "/selectReply.do", method = RequestMethod.POST)
	public String selectReply(int bno, Model model, ReplyVO vo) throws Exception {
		String jsp = PATH + "/reply";
		int num = vo.getNowPage();
		
 		Page page = new Page();
 		page.setNum(vo.getNowPage()); //현재 페이지
 		page.setCount(replyService.replyCnt(vo));
 		page.setScriptName("view.selectComent");
 		
 		vo.setDisplayPost(page.getDisplayPost());
 		vo.setPostNum(page.getPostNum());
		List<ReplyVO> replyList = replyService.selectReplyList(vo);
		
		model.addAttribute("bno", bno);
		model.addAttribute("replyList", replyList);
		model.addAttribute("Pagging", page.getPagging());
		
		return jsp;
	}
	
	// 댓글 작성, 답글
	@ResponseBody
	@RequestMapping(value = "/writeReply.do", method = RequestMethod.POST)
	public Map<String, Object> writeReply(@ModelAttribute ReplyVO vo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		replyService.writeReply(vo);
		map.put("msg", "성공적으로 등록하였습니다.");
		
		return map;
	}
	
	// 댓글 삭제
	@ResponseBody
	@RequestMapping(value = "/deleteReply.do", method = RequestMethod.POST)
	public Map<String, Object> deleteReply(ReplyVO vo) throws Exception {
  		Map<String, Object> map = new HashMap<String, Object>();
  		replyService.deleteReply(vo);
		map.put("msg", "성공적으로 삭제하였습니다.");
		
		return map;
	}
	
	// 댓글 수정
	@ResponseBody
	@RequestMapping(value = "/updateReply.do", method = RequestMethod.POST)
	public Map<String, Object> updateReply(ReplyVO vo) throws Exception {
 		Map<String, Object> map = new HashMap<String, Object>();
		replyService.updateReply(vo);
 		map.put("msg", "성공적으로 수정하였습니다.");
		
		return map;
	}
}
