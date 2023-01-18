package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.exception.AuthenticationException;
import kr.or.ddit.board.exception.NotExistBoardException;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;

@Controller
@RequestMapping("/board/boardUpdate.do")
public class BoardUpdateController {

	@Inject
	private BoardService service;

	@GetMapping
	public String getForm(@RequestParam("what") int boNo, Model model) {
		BoardVO board = service.retrieveBoard(boNo);
		model.addAttribute("board", board);
		return "board/boardEdit";
	}

//	@PostMapping
//	public String updateForm(
//			@Validated(UpdateGroup.class) @ModelAttribute("board") BoardVO board, Errors errors,
//			Model model) {
//		boolean valid = !errors.hasErrors();
//
//		String viewName = null;
//
//		if (valid) {
//			try {
//				int rowcnt = service.modifyBoard(board);
//				if (rowcnt > 0) {
//					viewName = "redirect:/board/boardView.do?what=" + board.getBoNo();
//				} else {
//					model.addAttribute("message", "서버 오류. 이따가 다시");
//					viewName = "board/boardEdit";
//				}
//			} catch (AuthenticationException ae) {
//				model.addAttribute("message", "비밀번호 실패");
//				viewName = "board/boardEdit";
//			} catch (NotExistBoardException ne) {
//				model.addAttribute("message", "서버 오류. 이따가 다시");
//				viewName = "board/boardEdit";
//			}
//		} else {
//			viewName="board/boardView";
//		}
//		return viewName;
//	}
	
	@PostMapping
	public String boardUpdate(
		@Validated(UpdateGroup.class) @ModelAttribute("board") BoardVO board
		, Errors errors
		, Model model
	) {
		String viewName = null;	
		if(!errors.hasErrors()) {
			try {
				int rowcnt = service.modifyBoard(board);
				if(rowcnt > 0) {
					viewName="redirect:/board/boardView.do?what="+board.getBoNo();
				} else {
					model.addAttribute("message", "서버오류, 좀따다시");
					viewName="board/boardForm";
				}
			} catch(AuthenticationException ae) {
				model.addAttribute("message", "비밀번호 오류");
				viewName = "board/boardEdit";
			} catch(NotExistBoardException ne) {
				
			}
		} else {
			viewName="board/boardEdit";
		}
		return viewName;
	}
}
