package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

// /board/boardList.do (검색조건 : 작성자, 글의 내용, 전체)
@Controller
@RequestMapping("/board")
public class BoardRetrieveController {
	
	@Inject
	private BoardService boardService;
	
	@RequestMapping("boardList.do")
	public String boardList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@ModelAttribute("simpleCondition") SearchVO seachVO,
			Model model
		) {
		PagingVO<BoardVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleCondition(seachVO);
		
		boardService.retrieveBoardList(pagingVO);
		model.addAttribute("pagingVO", pagingVO);
		
		return "board/boardList";
	}
	
	@RequestMapping("boardView.do")
	public String boardView(
		@RequestParam("what") int boNo
		, Model model
	) {
		BoardVO board = boardService.retrieveBoard(boNo);
		model.addAttribute("board", board);
		return "board/boardView";
	}
}
