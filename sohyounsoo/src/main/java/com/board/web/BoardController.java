package com.board.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.domain.BoardVO;
import com.board.domain.Page;
import com.board.service.BoardSerivce;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	@Inject
	BoardSerivce service;
	public final static String  PATH = "/board";

	// 게시물 목록페이지 이동
	@RequestMapping(value = "/selectListPage.do", method = RequestMethod.GET)
	public String selectListPage(Model model) throws Exception {
		String jsp = PATH + "/listPage";
		
		return jsp;
	}

	// 게시물 리스트 불러오기
	@RequestMapping(value = "/listPage.do", method = RequestMethod.GET)
	public String selectList(BoardVO vo, Model model) throws Exception {
		String jsp = PATH + "/list";
		
 		Page page = new Page();
 		page.setNum(vo.getNowPage()); //현재 페이지
 		page.setCount(service.boardCnt(vo));
 		page.setScriptName("listPage.selectList");
 		
 		vo.setDisplayPost(page.getDisplayPost());
 		vo.setPostNum(page.getPostNum());
 		List<BoardVO> list = service.list(vo);
 		
		model.addAttribute("list", list);
		model.addAttribute("Pagging", page.getPagging());

		return jsp;
	}
	
	// 게시물 작성페이지로 이동
	@RequestMapping(value = "/write.do", method = RequestMethod.GET)
	public String getWritePage(BoardVO vo) throws Exception {
		String jsp = PATH + "/write";
			
		return jsp;
	}
	
	// 게시물 작성
	@ResponseBody
	@RequestMapping(value = "/writeAjax.do", method = RequestMethod.POST)
	public Map<String, Object> insertWrite(@RequestBody BoardVO vo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		service.write(vo);
		map.put("msg", "성공적으로 등록하였습니다.");
		
		return map;
	}
	
	// 게시물 상세 조회
	@RequestMapping(value = "/seletBoardDetail.do", method = RequestMethod.POST)
	public String seletBoardDetail(BoardVO vo, Model model) throws Exception {
		String jsp = PATH + "/view";
		BoardVO view = service.selectBoardDetail(vo);
		model.addAttribute("view", view);
		
		return jsp;
	}
	 
	// 게시믈 수정
//	@ResponseBody
//	@RequestMapping(value = "/updateBoard.do", method = RequestMethod.POST)
//	public Map<String, Object> updateBoard(@RequestBody BoardVO vo) throws Exception {
// 		Map<String, Object> map = new HashMap<String, Object>();
//		service.updateBoard(vo);
//		
//		map.put("msg", "성공적으로 수정하였습니다.");
//		
//		return map;
//	}
	
	// 게시믈 수정
	@ResponseBody
	@RequestMapping(value = "/updateBoard.do", method = RequestMethod.POST)
	public Map<String, Object> updateBoard(@ModelAttribute BoardVO vo) throws Exception {
 		Map<String, Object> map = new HashMap<String, Object>();
		service.updateBoard(vo);
		map.put("msg", "성공적으로 수정하였습니다.");
		
		return map;
	}
	
	// 게시믈 삭제
	@ResponseBody
	@RequestMapping(value = "/deleteBoard.do", method = RequestMethod.POST)
	public Map<String, Object> deleteBoard(BoardVO vo) throws Exception {
  		Map<String, Object> map = new HashMap<String, Object>();
		service.deleteBoard(vo);
		map.put("msg", "성공적으로 삭제하였습니다.");
		
		return map;
	}
	
}