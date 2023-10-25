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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ozz.dto.StepDTO;
import kr.or.ozz.dto.TaskDTO;
import kr.or.ozz.dto.MissionDTO;
import kr.or.ozz.dto.PagingDTO;
import kr.or.ozz.dto.StepDTO;
import kr.or.ozz.service.MissionService;
import kr.or.ozz.service.StepService;
import kr.or.ozz.service.TaskService;
import kr.or.ozz.service.StepService;

@RestController
@RequestMapping("/Step")
public class StepController {
	@Autowired
	StepService service;
	
	@Autowired
	TaskService Tservice;
	
	@Autowired
	MissionService Mservice;

	// 글쓰기 폼으로 이동
	@GetMapping("/Stepwrite")
	public ModelAndView Stepwrite(int m_no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("mission_no", m_no);
		mav.setViewName("Mission/Stepwrite");
		return mav;
	}

	// 글쓰기 DB 기록
	@PostMapping("/StepwriteOk")
	public ResponseEntity<String> StepwriteOk(StepDTO dto, HttpServletRequest request) {		
		// 세개 합치면 아래 코드랑 동일
		dto.setUserid((String) request.getSession().getAttribute("logId"));

		int result = 0;
		try {
			result = service.StepwriteOk(dto);
		} catch (Exception e) {
			System.out.println("게시판 글 등록 예외발생..." + e.getMessage());
		}
		// 등록결과에 따른 스크립트 생성하기
		String tag = "<script>";
		if (result > 0) { // 성공 -> 게시판 목록
			tag += "location.href='/ozz/Mission/Missionlist';";
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
	@GetMapping("/StepView")
	public ModelAndView StepView(@RequestParam("no") int no, @RequestParam("mission_no") int mission_no, PagingDTO pDTO) {
		// 레코드선택
		StepDTO dto = service.getStep(no);
		
		List<StepDTO> Steplist = service.Steplist(mission_no, pDTO);
		
		List<TaskDTO> Tasklist = Tservice.Tasklist(no, pDTO);
		
		MissionDTO Mdto = Mservice.getMission(mission_no);

		ModelAndView mav = new ModelAndView();
		mav.addObject("no", no);
		mav.addObject("dto", dto);
		mav.addObject("Steplist", Steplist);
		mav.addObject("Tasklist", Tasklist);
		mav.addObject("Mdto", Mdto);
		mav.addObject("pDTO", pDTO);
		mav.setViewName("Mission/missionStep");

		return mav;
	}

	// 글 수정 폼
	@GetMapping("/StepEdit")
	public ModelAndView StepEdit(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", service.getStep(no)); // dto 변수 생성 대신 직접입력
		mav.setViewName("Step/StepEdit");

		return mav;
	}

	@PostMapping("/StepEditOk") // no, subject, content
	public ModelAndView StepEditOk(StepDTO dto, HttpSession session) {
		dto.setUserid((String) session.getAttribute("logId"));

		int result = service.StepEdit(dto);

		ModelAndView mav = new ModelAndView();
		mav.addObject("no", dto.getStep_no());
		if (result > 0) { // 글수정성공 -> 글 내용 보기
			mav.setViewName("redirect:StepView");
		} else { // 글수정실패 -> 수정폼으로
			mav.setViewName("redirect:StepEdit");
		}
		return mav;
	}

	// 글삭제
	@GetMapping("/StepDel")
	public ModelAndView StepDel(int no, HttpSession session) {
		int result = service.StepDel(no, (String) session.getAttribute("logId"));

		ModelAndView mav = new ModelAndView();
		if (result > 0) {// 삭제성공 -> 목록
			mav.setViewName("redirect:Steplist");
		} else {// 삭제실패 -> 글내용
			mav.addObject("no", no);
			mav.setViewName("redirect:StepView");
		}
		return mav;
	}
}