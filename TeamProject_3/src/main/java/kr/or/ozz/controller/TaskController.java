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

// @Controller : ��, �並 �������ش�.
//				 ModelAndView,
//				 Model, String

// @RestController : ���� ���ϵȴ�.
//					 Model+viewPage -> ModelAndView�� ����
@RestController
@RequestMapping("/Task")
public class TaskController {
	
	@Autowired
	TaskService service;

	// �۾��� ������ �̵�
	@GetMapping("/Taskwrite")
	public ModelAndView Taskwrite(int s_no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("step_no", s_no);
		mav.setViewName("Mission/Taskwrite");
		return mav;
	}

	// �۾��� DB ���
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

		// no, hit, writedate -> ����Ŭ
		// userid -> ����

		// HttpSession session = request.getSession();
		// String userid = (String)session.getAttribute("logId");
		// dto.setUserid(userid);
		// ���� ��ġ�� �Ʒ� �ڵ�� ����
		dto.setUserid((String) request.getSession().getAttribute("logId"));

		int result = 0;
		try {
			result = service.TaskwriteOk(dto);
		} catch (Exception e) {
			System.out.println("�Խ��� �� ��� ���ܹ߻�..." + e.getMessage());
		}
		// ��ϰ���� ���� ��ũ��Ʈ �����ϱ�
//		String tag = "<script>";
//		if (result > 0) { // ���� -> �Խ��� ���
//			tag += "location.href='/ozz/Mission/Missionlist';";
//		} else { // ���� -> �� ��� ������ �̵�
//			tag += "alert('�� ����� �����Ͽ����ϴ�.');";
//			tag += "history.back();";
//		}
//		tag += "</script>";
//
//		// ResponseEntity ��ü�� ����Ʈ�������� �ۼ��� �� �ִ�.
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(new MediaType("text", "html", Charset.forName("UTF-8")));
//		return new ResponseEntity<String>(tag, headers, HttpStatus.OK);
		ModelAndView mav = new ModelAndView();
		if (result > 0) { // ���� �Ǵ� ���� ��� �ش� URL�� �̵�
	        mav.setViewName("redirect:/Step/StepView?no="+dto.getStep_no());
	    } else { // ���� �� alert �޽����� ���� �ش� URL�� �̵�
	        mav.addObject("message", "�� ����� �����Ͽ����ϴ�.");
	        mav.setViewName("redirect:/Step/StepView?no="+dto.getStep_no());
	    }

	    return mav;
	}

	// �۳��뺸��
//	@GetMapping("/TaskView")
//	public ModelAndView TaskView(int no, PagingDTO pDTO) {
//		// ���ڵ弱��
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

	// �� ���� ��
	@GetMapping("/TaskEdit")
	public ModelAndView TaskEdit(int no) {
//		StepDTO dto = service.getStep(no);
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", service.getTask(no)); // dto ���� ���� ��� �����Է�
		mav.setViewName("Mission/TaskEdit");

		return mav;
	}

	@PostMapping("/TaskEditOk") // no, subject, content
	public ModelAndView TaskEditOk(TaskDTO dto, int step_no, HttpSession session) {
		dto.setUserid((String) session.getAttribute("logId"));

		int result = service.TaskEdit(dto);

		ModelAndView mav = new ModelAndView();
		mav.addObject("no", dto.getTask_no());
		if (result > 0) { // �ۼ������� -> �� ���� ����
			mav.setViewName("redirect:/Step/StepView?no=" + step_no);
		} else { // �ۼ������� -> ����������
			mav.setViewName("redirect:TaskEdit");
		}
		return mav;
	}

	// �ۻ���
	@GetMapping("/TaskDel")
	public ModelAndView TaskDel(int no, HttpSession session, int step_no, HttpServletRequest request) {
//		StepDTO sDTO = service.getStep(no);
//		int m_no = sDTO.getMission_no();
		
		int result = service.TaskDel(no, (String) session.getAttribute("logId"));

		ModelAndView mav = new ModelAndView();
		
//		System.out.println(m_no);
		if (result > 0) {// �������� -> ���
			mav.setViewName("redirect:/Step/StepView?no=" + step_no);
		} else {// �������� -> �۳���
			mav.addObject("no", no);
			mav.setViewName("redirect:/Step/StepView?no=" + step_no);
		}
		return mav;
	}
}