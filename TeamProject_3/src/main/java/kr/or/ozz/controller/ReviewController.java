package kr.or.ozz.controller;

import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ozz.dto.PagingDTO;
import kr.or.ozz.dto.ReviewDTO;
import kr.or.ozz.service.ReviewService;

// @Controller : 모델, 뷰를 리턴해준다.
//				 ModelAndView,
//				 Model, String

// @RestController : 모델이 리턴된다.
//					 Model+viewPage -> ModelAndView로 리턴
@RestController
@RequestMapping("/Review")
public class ReviewController {
	@Autowired
	ReviewService service;

	@GetMapping("/Reviewlist")
	public ModelAndView Reviewlist(PagingDTO pDTO) {
		// 총레코드수
		pDTO.setR_totalRecord(service.r_totalRecord(pDTO));

		// 해당페이지의 레코드 선택
		List<ReviewDTO> list = service.Reviewlist(pDTO);

		// ModelAndView
		ModelAndView mav = new ModelAndView();

		mav.addObject("list", list);
		mav.addObject("pDTO", pDTO);
		mav.setViewName("Review/Reviewlist");
		return mav;
	}

	// 글쓰기 폼으로 이동
	@GetMapping("/Reviewwrite")
	public ModelAndView Reviewwrite(int m_no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("m_no", m_no);
		mav.setViewName("Review/Reviewwrite");
		return mav;
	}

	// 글쓰기 DB 기록

	 @PostMapping("/ReviewwriteOk")
	 public String ReviewwriteOk(ReviewDTO dto, HttpSession session) {

		 dto.setUserid((String)session.getAttribute("logId"));
		int result = service.ReviewwriteOk(dto);
	
		System.out.println(dto);
		return result+"";	
	 }
	 
	// 글내용보기
	@GetMapping("/ReviewView")
	public ModelAndView ReviewView(int no, PagingDTO pDTO) {
		//조회수 증가
		service.hitCount(no);
		// 레코드선택
		ReviewDTO dto = service.getReview(no);

		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", dto);
		mav.addObject("pDTO", pDTO);
		mav.setViewName("Review/ReviewView");

		return mav;
	}

	// 글 수정 폼
	@GetMapping("/ReviewEdit")
	public ModelAndView ReviewEdit(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", service.getReview(no)); // dto 변수 생성 대신 직접입력
		mav.setViewName("Review/ReviewEdit");

		return mav;
	}

	@PostMapping("/ReviewEditOk") // no, subject, content
	public ModelAndView ReviewEditOk(ReviewDTO dto, HttpSession session) {
		dto.setUserid((String) session.getAttribute("logId"));

		int result = service.ReviewEdit(dto);

		ModelAndView mav = new ModelAndView();
		mav.addObject("no", dto.getReview_no());
		if (result > 0) { // 글수정성공 -> 글 내용 보기
			mav.setViewName("redirect:ReviewView");
		} else { // 글수정실패 -> 수정폼으로
			mav.setViewName("redirect:ReviewEdit");
		}
		return mav;
	}

	// 글삭제
	@GetMapping("/ReviewDel")
	public ModelAndView ReviewDel(int no, HttpSession session) {
		int result = service.ReviewDel(no, (String) session.getAttribute("logId"));

		ModelAndView mav = new ModelAndView();
		if (result > 0) {// 삭제성공 -> 목록
			mav.setViewName("redirect:Reviewlist");
		} else {// 삭제실패 -> 글내용
			mav.addObject("no", no);
			mav.setViewName("redirect:ReviewView");
		}
		return mav;
	}
	
}