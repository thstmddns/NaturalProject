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

import kr.or.ozz.dto.StepDTO;
import kr.or.ozz.dto.TaskDTO;
import kr.or.ozz.dto.MissionDTO;
import kr.or.ozz.dto.PagingDTO;
import kr.or.ozz.dto.StepDTO;
import kr.or.ozz.service.MissionService;
import kr.or.ozz.service.StepService;
import kr.or.ozz.service.TaskService;
import kr.or.ozz.service.StepService;

// @Controller : ��, �並 �������ش�.
//				 ModelAndView,
//				 Model, String

// @RestController : ���� ���ϵȴ�.
//					 Model+viewPage -> ModelAndView�� ����
@RestController
@RequestMapping("/Step")
public class StepController {
	@Autowired
	StepService service;
	
	@Autowired
	TaskService Tservice;
	
	@Autowired
	MissionService Mservice;

	// �۾��� ������ �̵�
	@GetMapping("/Stepwrite")
	public ModelAndView Stepwrite(int m_no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("mission_no", m_no);
		mav.setViewName("Mission/Stepwrite");
		return mav;
	}

	// �۾��� DB ���
	@PostMapping("/StepwriteOk")
	public ResponseEntity<String> StepwriteOk(StepDTO dto, HttpServletRequest request) {
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
			result = service.StepwriteOk(dto);
		} catch (Exception e) {
			System.out.println("�Խ��� �� ��� ���ܹ߻�..." + e.getMessage());
		}
		// ��ϰ���� ���� ��ũ��Ʈ �����ϱ�
		String tag = "<script>";
		if (result > 0) { // ���� -> �Խ��� ���
			tag += "location.href='/ozz/Mission/Missionlist';";
		} else { // ���� -> �� ��� ������ �̵�
			tag += "alert('�� ����� �����Ͽ����ϴ�.');";
			tag += "history.back();";
		}
		tag += "</script>";

		// ResponseEntity ��ü�� ����Ʈ�������� �ۼ��� �� �ִ�.
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text", "html", Charset.forName("UTF-8")));
		return new ResponseEntity<String>(tag, headers, HttpStatus.OK);
	}

	// �۳��뺸��
	@GetMapping("/StepView")
	public ModelAndView StepView(int no, PagingDTO pDTO) {
		// ���ڵ弱��
		StepDTO dto = service.getStep(no);
		
		List<TaskDTO> Tasklist = Tservice.Tasklist(no, pDTO);
		
		MissionDTO Mdto = Mservice.getMission(no);

//	    byte[] imageData = dto.getFile_name();
//        String base64ImageData = Base64.getEncoder().encodeToString(imageData);
//        dto.setFile_name_base64(base64ImageData);
		ModelAndView mav = new ModelAndView();
		mav.addObject("no", no);
		mav.addObject("dto", dto);
		mav.addObject("Tasklist", Tasklist);
		mav.addObject("Mdto", Mdto);
		mav.addObject("pDTO", pDTO);
		mav.setViewName("Mission/missionStep");

		return mav;
	}

	// �� ���� ��
	@GetMapping("/StepEdit")
	public ModelAndView StepEdit(int no) {
//		StepDTO dto = service.getStep(no);
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", service.getStep(no)); // dto ���� ���� ��� �����Է�
		mav.setViewName("Step/StepEdit");

		return mav;
	}

	@PostMapping("/StepEditOk") // no, subject, content
	public ModelAndView StepEditOk(StepDTO dto, HttpSession session) {
		dto.setUserid((String) session.getAttribute("logId"));

		int result = service.StepEdit(dto);

		ModelAndView mav = new ModelAndView();
		mav.addObject("no", dto.getStep_no());
		if (result > 0) { // �ۼ������� -> �� ���� ����
			mav.setViewName("redirect:StepView");
		} else { // �ۼ������� -> ����������
			mav.setViewName("redirect:StepEdit");
		}
		return mav;
	}

	// �ۻ���
	@GetMapping("/StepDel")
	public ModelAndView StepDel(int no, HttpSession session) {
		int result = service.StepDel(no, (String) session.getAttribute("logId"));

		ModelAndView mav = new ModelAndView();
		if (result > 0) {// �������� -> ���
			mav.setViewName("redirect:Steplist");
		} else {// �������� -> �۳���
			mav.addObject("no", no);
			mav.setViewName("redirect:StepView");
		}
		return mav;
	}
}