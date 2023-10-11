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

import kr.or.ozz.dto.ReplyDTO;

import kr.or.ozz.dto.MissionDTO;
import kr.or.ozz.dto.PagingDTO;
import kr.or.ozz.dto.StepDTO;
import kr.or.ozz.service.MissionService;
import kr.or.ozz.service.StepService;

// @Controller : 모델, 뷰를 리턴해준다.
//				 ModelAndView,
//				 Model, String

// @RestController : 모델이 리턴된다.
//					 Model+viewPage -> ModelAndView로 리턴
@RestController
@RequestMapping("/Mission")
public class MissionController {
	@Autowired
	MissionService service;
	
	@Autowired
	StepService Sservice;

	@GetMapping("/Missionlist")
	public ModelAndView Missionlist(PagingDTO pDTO) {
		// 총레코드수
		pDTO.setTotalRecord(service.totalRecord(pDTO));

		// 해당페이지의 레코드 선택
		List<MissionDTO> list = service.Missionlist(pDTO);

		// ModelAndView
		ModelAndView mav = new ModelAndView();

		/*
		 * for (MissionDTO dto : list) { byte[] imageData = dto.getFile_name(); String
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
		mav.setViewName("Mission/Missionlist");
		return mav;
	}

	// 글쓰기 폼으로 이동
	@GetMapping("/Missionwrite")
	public ModelAndView Missionwrite() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mission/missionMake");
		return mav;
	}

	// 글쓰기 DB 기록
	@PostMapping("/MissionwriteOk")
	public ResponseEntity<String> MissionwriteOk(MissionDTO dto, HttpServletRequest request) {
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
			result = service.MissionwriteOk(dto);
		} catch (Exception e) {
			System.out.println("게시판 글 등록 예외발생..." + e.getMessage());
		}
		// 등록결과에 따른 스크립트 생성하기
		String tag = "<script>";
		if (result > 0) { // 성공 -> 게시판 목록
			tag += "location.href='/ozz/main/mainMission';";
		} else { // 실패 -> 글 등록 폼으로 이동
			tag += "alert('글 등록이 실패하였습니다.');";
			tag += "history.back();";
		}
		tag += "</script>";

		// ResponseEntity 객체는 프론트페이지를 작성할 수 있다.
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text", "html", Charset.forName("UTF-8")));
		return new ResponseEntity<String>(tag, headers, HttpStatus.OK);
	}

	// 글내용보기
	@GetMapping("/MissionView")
	public ModelAndView MissionView(int no, PagingDTO pDTO) {
		//조회수 증가
		service.hitCount(no);
		// 레코드선택
		MissionDTO dto = service.getMission(no);
		List<StepDTO> Steplist = Sservice.Steplist(no, pDTO);

//	    byte[] imageData = dto.getFile_name();
//        String base64ImageData = Base64.getEncoder().encodeToString(imageData);
//        dto.setFile_name_base64(base64ImageData);
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", dto);
		mav.addObject("Steplist", Steplist);
		mav.addObject("pDTO", pDTO);
		mav.setViewName("Mission/MissionView");

		return mav;
	}

	// 글 수정 폼
	@GetMapping("/MissionEdit")
	public ModelAndView MissionEdit(int no) {
//		MissionDTO dto = service.getMission(no);
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", service.getMission(no)); // dto 변수 생성 대신 직접입력
		mav.setViewName("Mission/MissionEdit");

		return mav;
	}

	@PostMapping("/MissionEditOk") // no, subject, content
	public ModelAndView MissionEditOk(MissionDTO dto, HttpSession session) {
		dto.setUserid((String) session.getAttribute("logId"));

		int result = service.MissionEdit(dto);

		ModelAndView mav = new ModelAndView();
		mav.addObject("no", dto.getMission_no());
		if (result > 0) { // 글수정성공 -> 글 내용 보기
			mav.setViewName("redirect:MissionView");
		} else { // 글수정실패 -> 수정폼으로
			mav.setViewName("redirect:MissionEdit");
		}
		return mav;
	}

	// 글삭제
	@GetMapping("/MissionDel")
	public ModelAndView MissionDel(int no, HttpSession session) {
		int result = service.MissionDel(no, (String) session.getAttribute("logId"));

		ModelAndView mav = new ModelAndView();
		if (result > 0) {// 삭제성공 -> 목록
			mav.setViewName("redirect:Missionlist");
		} else {// 삭제실패 -> 글내용
			mav.addObject("no", no);
			mav.setViewName("redirect:MissionView");
		}
		return mav;
	}
	
	/*
	 * // 리뷰 등록
	 * 
	 * @PostMapping("/review/reviewWrite") public String reviewWrite(ReplyDTO dto,
	 * HttpSession session) { // session 글쓴이 구하기
	 * dto.setUserid((String)session.getAttribute("logId"));
	 * 
	 * int result = service.replyInsert(dto);
	 * 
	 * return result+""; }
	 */
}