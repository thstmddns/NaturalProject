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
		// 세개 합치면 아래 코드랑 동일
		dto.setUserid((String) request.getSession().getAttribute("logId"));

		int result = 0;
		try {
			result = service.TaskwriteOk(dto);
		} catch (Exception e) {
			System.out.println("게시판 글 등록 예외발생..." + e.getMessage());
		}

		ModelAndView mav = new ModelAndView();
		if (result > 0) { // 성공 또는 실패 모두 해당 URL로 이동
	        mav.setViewName("redirect:/Step/StepView?no="+dto.getStep_no());
	    } else { // 실패 시 alert 메시지만 띄우고 해당 URL로 이동
	        mav.addObject("message", "글 등록이 실패하였습니다.");
	        mav.setViewName("redirect:/Step/StepView?no="+dto.getStep_no());
	    }

	    return mav;
	}

	// 글 수정 폼
	@GetMapping("/TaskEdit")
	public ModelAndView TaskEdit(int no) {
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
		
		int result = service.TaskDel(no, (String) session.getAttribute("logId"));

		ModelAndView mav = new ModelAndView();
		
		if (result > 0) {// 삭제성공 -> 목록
			mav.setViewName("redirect:/Step/StepView?no=" + step_no);
		} else {// 삭제실패 -> 글내용
			mav.addObject("no", no);
			mav.setViewName("redirect:/Step/StepView?no=" + step_no);
		}
		return mav;
	}
}