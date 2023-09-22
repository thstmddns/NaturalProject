package kr.or.ozz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ozz.dto.PagingDTO;
import kr.or.ozz.service.SampleService;

@RestController
@RequestMapping("/sample")
public class SampleController {
	@Autowired
	SampleService service;
	
	@GetMapping("/sampleList")
	public ModelAndView sampleList(PagingDTO pDTO) {
		//pDTO.setTotalRecord(service.totalRecord(pDTO));
		
		ModelAndView mav = new ModelAndView();
		//mav.addObject("dto", service.getList());
		//mav.addObject("pDTO", pDTO);
		
		mav.setViewName("sample/sampleList");
		
		return mav;
	}
	// Í∏? ?Ç¥?ö© Î≥¥Í∏∞
	@GetMapping("/sampleView")
	public ModelAndView boardView(int no, PagingDTO pDTO) {
		service.hitCount(no);
				
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", service.getView(no));
		mav.addObject("pDTO", pDTO);	// ?éò?ù¥Ïß? ?†ïÎ≥¥Î?? Ï∂îÍ?
				
		mav.setViewName("notice/noticeView");
				
		return mav;
	}
}
