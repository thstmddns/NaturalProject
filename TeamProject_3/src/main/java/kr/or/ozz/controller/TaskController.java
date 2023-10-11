package kr.or.ozz.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ozz.dto.TaskDTO;
import kr.or.ozz.service.TaskService;

// @Controller : 모델, 뷰를 리턴해준다.
//				 ModelAndView,
//				 Model, String

// @RestController : 모델이 리턴된다.
//					 Model+viewPage -> ModelAndView로 리턴
@RestController
@RequestMapping("/Task")
public class TaskController {
	
	@Autowired
	TaskService service;

	// 글쓰기 폼으로 이동
	@GetMapping("/Taskwrite")
	public ModelAndView Taskwrite(int s_no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("step_no", s_no);
		mav.setViewName("Mission/Taskwrite");
		return mav;
	}

	// 글쓰기 DB 기록
	@PostMapping("/TaskwriteOk")
	public ModelAndView TaskwriteOk(TaskDTO dto, HttpServletRequest request) {
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
			result = service.TaskwriteOk(dto);
		} catch (Exception e) {
			System.out.println("게시판 글 등록 예외발생..." + e.getMessage());
		}
		// 등록결과에 따른 스크립트 생성하기
//		String tag = "<script>";
//		if (result > 0) { // 성공 -> 게시판 목록
//			tag += "location.href='/ozz/Mission/Missionlist';";
//		} else { // 실패 -> 글 등록 폼으로 이동
//			tag += "alert('글 등록이 실패하였습니다.');";
//			tag += "history.back();";
//		}
//		tag += "</script>";
//
//		// ResponseEntity 객체는 프론트페이지를 작성할 수 있다.
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(new MediaType("text", "html", Charset.forName("UTF-8")));
//		return new ResponseEntity<String>(tag, headers, HttpStatus.OK);
		ModelAndView mav = new ModelAndView();
		if (result > 0) { // 성공 또는 실패 모두 해당 URL로 이동
	        mav.setViewName("redirect:/Step/StepView?no="+dto.getStep_no());
	    } else { // 실패 시 alert 메시지만 띄우고 해당 URL로 이동
	        mav.addObject("message", "글 등록이 실패하였습니다.");
	        mav.setViewName("redirect:/Step/StepView?no="+dto.getStep_no());
	    }

	    return mav;
	}

	// 글내용보기
//	@GetMapping("/TaskView")
//	public ModelAndView TaskView(int no, PagingDTO pDTO) {
//		// 레코드선택
//		TaskDTO dto = service.getTask(no);
//		
////		List<TaskDTO> Tasklist = Tservice.Tasklist(no, pDTO);
//
////	    byte[] imageData = dto.getFile_name();
////        String base64ImageData = Base64.getEncoder().encodeToString(imageData);
////        dto.setFile_name_base64(base64ImageData);
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("no", no);
//		mav.addObject("dto", dto);
////		mav.addObject("Tasklist", Tasklist);
//		mav.addObject("pDTO", pDTO);
//		mav.setViewName("Mission/TaskView");
//
//		return mav;
//	}

	// 글 수정 폼
	@GetMapping("/TaskEdit")
	public ModelAndView TaskEdit(int no) {
//		StepDTO dto = service.getStep(no);
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", service.getTask(no)); // dto 변수 생성 대신 직접입력
		mav.setViewName("Mission/TaskEdit");

		return mav;
	}

	@PostMapping("/TaskEditOk") // no, subject, content
	public ModelAndView TaskEditOk(TaskDTO dto, int step_no, HttpSession session) {
		dto.setUserid((String) session.getAttribute("logId"));

		int result = service.TaskEdit(dto);

		ModelAndView mav = new ModelAndView();
		mav.addObject("no", dto.getTask_no());
		if (result > 0) { // 글수정성공 -> 글 내용 보기
			mav.setViewName("redirect:/Step/StepView?no=" + step_no);
		} else { // 글수정실패 -> 수정폼으로
			mav.setViewName("redirect:TaskEdit");
		}
		return mav;
	}

	// 글삭제
	@GetMapping("/TaskDel")
	public ModelAndView TaskDel(int no, HttpSession session, int step_no, HttpServletRequest request) {
//		StepDTO sDTO = service.getStep(no);
//		int m_no = sDTO.getMission_no();
		
		int result = service.TaskDel(no, (String) session.getAttribute("logId"));

		ModelAndView mav = new ModelAndView();
		
//		System.out.println(m_no);
		if (result > 0) {// 삭제성공 -> 목록
			mav.setViewName("redirect:/Step/StepView?no=" + step_no);
		} else {// 삭제실패 -> 글내용
			mav.addObject("no", no);
			mav.setViewName("redirect:/Step/StepView?no=" + step_no);
		}
		return mav;
	}
}