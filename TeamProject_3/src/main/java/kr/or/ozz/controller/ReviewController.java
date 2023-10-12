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

		/*
		 * for (ReviewDTO dto : list) { byte[] imageData = dto.getFile_name(); String
		 * base64ImageData = Base64.getEncoder().encodeToString(imageData); String
		 * base64ImageData; if (imageData != null) { base64ImageData =
		 * Base64.getEncoder().encodeToString(imageData);
		 * 
		 * } else { base64ImageData = "None"; }
		 * 
		 * dto.setFile_name_base64(base64ImageData); }
		 */
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
	public ModelAndView ReviewwriteOk(ReviewDTO dto, HttpServletRequest request) {
//		@RequestParam("file_name_base64") String base64ImageData
//		byte[] imageData;
//		if (base64ImageData == "") {
//			imageData = new byte[0];
//		} else {
//			imageData = Base64.getDecoder().decode(base64ImageData.split(",")[1]);
//		}
//
//		dto.setFile_name(imageData);
		// HttpServletRequest -> request, HttpSession
		// HttpSession -> session

		// no, hit, writedate -> 오라클
		// userid -> 세션

		// HttpSession session = request.getSession();
		// String userid = (String)session.getAttribute("logId");
		// dto.setUserid(userid);
		// 세개 합치면 아래 코드랑 동일
		dto.setUserid((String) request.getSession().getAttribute("logId"));

		int result = 0;
		try {
			result = service.ReviewwriteOk(dto);
		} catch (Exception e) {
			System.out.println("게시판 글 등록 예외발생..." + e.getMessage());
		}
		// 등록결과에 따른 스크립트 생성하기
//		String tag = "<script>";
//		if (result > 0) { // 성공 -> 게시판 목록
//			tag += "location.href='/ozz/Review/Reviewlist';";
//		} else { // 실패 -> 글 등록 폼으로 이동
//			tag += "alert('글 등록이 실패하였습니다.');";
//			tag += "history.back();";
//		}
//		tag += "</script>";

		// ResponseEntity 객체는 프론트페이지를 작성할 수 있다.
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(new MediaType("text", "html", Charset.forName("UTF-8")));
//		return new ResponseEntity<String>(tag, headers, HttpStatus.OK);
		ModelAndView mav = new ModelAndView();
		if (result > 0) { // 성공 또는 실패 모두 해당 URL로 이동
	        mav.setViewName("redirect:/Mission/MissionView?no="+dto.getMission_no());
	    } else { // 실패 시 alert 메시지만 띄우고 해당 URL로 이동
	        mav.addObject("message", "글 등록이 실패하였습니다.");
	        mav.setViewName("redirect:/Mission/MissionView?no="+dto.getMission_no());
	    }

	    return mav;
		
	}

	// 글내용보기
	@GetMapping("/ReviewView")
	public ModelAndView ReviewView(int no, PagingDTO pDTO) {
		//조회수 증가
		service.hitCount(no);
		// 레코드선택
		ReviewDTO dto = service.getReview(no);

//	    byte[] imageData = dto.getFile_name();
//        String base64ImageData = Base64.getEncoder().encodeToString(imageData);
//        dto.setFile_name_base64(base64ImageData);
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", dto);
		mav.addObject("pDTO", pDTO);
		mav.setViewName("Review/ReviewView");

		return mav;
	}

	// 글 수정 폼
	@GetMapping("/ReviewEdit")
	public ModelAndView ReviewEdit(int no) {
//		ReviewDTO dto = service.getReview(no);
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