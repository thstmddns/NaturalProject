package kr.or.ozz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ozz.dto.BoardDTO;
import kr.or.ozz.dto.QnaDTO;
import kr.or.ozz.service.BoardService;
import kr.or.ozz.service.QnaService;

@RestController
public class CommunityController {
	@Autowired
	QnaService Qservice;
	
	@Autowired
	BoardService Bservice;
	
	@GetMapping("/Community")
	public ModelAndView QnaToplist() {
		
		List<QnaDTO> QnaToplist = Qservice.QnaToplist();
		List<BoardDTO> BoardToplist = Bservice.BoardToplist();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("QnaToplist", QnaToplist);
		mav.addObject("BoardToplist", BoardToplist);
		mav.setViewName("Community/Community");
		return mav;
	}
}
