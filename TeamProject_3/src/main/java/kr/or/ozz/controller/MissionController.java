package kr.or.ozz.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ozz.dto.ReplyDTO;

import kr.or.ozz.dto.MissionDTO;
import kr.or.ozz.dto.PagingDTO;
import kr.or.ozz.dto.QnaDTO;
import kr.or.ozz.dto.ReviewDTO;
import kr.or.ozz.dto.StepDTO;
import kr.or.ozz.dto.TaskDTO;
import kr.or.ozz.service.MissionService;
import kr.or.ozz.service.QnaService;
import kr.or.ozz.service.ReviewService;
import kr.or.ozz.service.StepService;
import kr.or.ozz.service.TaskService;

// @Controller : ��, �並 �������ش�.
//				 ModelAndView,
//				 Model, String

// @RestController : ���� ���ϵȴ�.
//					 Model+viewPage -> ModelAndView�� ����
@RestController
@RequestMapping("/Mission")
public class MissionController {
	@Autowired
	MissionService service;
	
	@Autowired
	StepService Sservice;
	
	@Autowired
	TaskService Tservice;
	
	@Autowired
	QnaService Qservice;
	
	@Autowired
	ReviewService Rservice;

	@GetMapping("/Missionlist")
	public ModelAndView Missionlist(PagingDTO pDTO) {
		// �ѷ��ڵ��
		pDTO.setM_totalRecord(service.m_totalRecord(pDTO));

		// �ش��������� ���ڵ� ����
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

	// �۾��� ������ �̵�
	@GetMapping("/Missionwrite")
	public ModelAndView Missionwrite() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Mission/missionMake");
		return mav;
	}

	// �۾��� DB ���
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

		// no, hit, writedate -> ����Ŭ
		// userid -> ����

		// HttpSession session = request.getSession();
		// String userid = (String)session.getAttribute("logId");
		// dto.setUserid(userid);
		// ���� ��ġ�� �Ʒ� �ڵ�� ����
		dto.setUserid((String) request.getSession().getAttribute("logId"));

		int result = 0;
		try {
			result = service.MissionwriteOk(dto);
		} catch (Exception e) {
			System.out.println("�Խ��� �� ��� ���ܹ߻�..." + e.getMessage());
		}
		// ��ϰ���� ���� ��ũ��Ʈ �����ϱ�
		String tag = "<script>";
		if (result > 0) { // ���� -> �Խ��� ���
			tag += "location.href='/ozz/main/mainMission';";
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
	@GetMapping("/MissionView")
	public ModelAndView MissionView(@RequestParam("no")int no, PagingDTO pDTO) {
		//��ȸ�� ����
		service.hitCount(no);
		// ���ڵ弱��
		MissionDTO dto = service.getMission(no);
		List<StepDTO> Steplist = Sservice.Steplist(no, pDTO);
		List<QnaDTO> M_Qnalist = Qservice.M_Qnalist(no);
		List<ReviewDTO> M_Reviewlist = Rservice.M_Reviewlist(no);
		
		// FastAPI에 전송할 데이터를 생성
		List<String> contents = new ArrayList<String>();
		contents.add(dto.getMission_cate());

        // FastAPI 엔드포인트 URL
        String fastApiUrl = "http://localhost:8000/dmission_recommand"; // FastAPI 서버 URL로 수정

        // FastAPI에 HTTP POST 요청을 보냅니다.
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> requestBody = new HashMap<String, Object>();
        requestBody.put("contents", contents);
        
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String, Object>>(requestBody, headers);
        
        ResponseEntity<String> response = restTemplate.postForEntity(fastApiUrl, requestEntity, String.class);

        // FastAPI에서 받은 결과 데이터를 사용하여 ModelAndView 생성
        String resultData = response.getBody();
        
        System.out.println(contents);
        System.out.println(resultData);
//		List<> contents = dto.getMission_content();
//	    byte[] imageData = dto.getFile_name();
//        String base64ImageData = Base64.getEncoder().encodeToString(imageData);
//        dto.setFile_name_base64(base64ImageData);
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", dto);
		mav.addObject("Steplist", Steplist);
		mav.addObject("M_Qnalist", M_Qnalist);
		mav.addObject("M_Reviewlist", M_Reviewlist);
		mav.addObject("pDTO", pDTO);
		mav.addObject("contents", resultData);
		mav.setViewName("Mission/missionView");

		return mav;
	}

	// �� ���� ��
	@GetMapping("/MissionEdit")
	public ModelAndView MissionEdit(int no) {
//		MissionDTO dto = service.getMission(no);
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", service.getMission(no)); // dto ���� ���� ��� �����Է�
		mav.setViewName("Mission/MissionEdit");

		return mav;
	}

	@PostMapping("/MissionEditOk") // no, subject, content
	public ModelAndView MissionEditOk(MissionDTO dto, HttpSession session) {
		dto.setUserid((String) session.getAttribute("logId"));

		int result = service.MissionEdit(dto);

		ModelAndView mav = new ModelAndView();
		mav.addObject("no", dto.getMission_no());
		if (result > 0) { // �ۼ������� -> �� ���� ����
			mav.setViewName("redirect:MissionView");
		} else { // �ۼ������� -> ����������
			mav.setViewName("redirect:MissionEdit");
		}
		return mav;
	}

	// �ۻ���
	@GetMapping("/MissionDel")
	public ModelAndView MissionDel(int no, HttpSession session) {
		int result = service.MissionDel(no, (String) session.getAttribute("logId"));

		ModelAndView mav = new ModelAndView();
		if (result > 0) {// �������� -> ���
			mav.setViewName("redirect:Missionlist");
		} else {// �������� -> �۳���
			mav.addObject("no", no);
			mav.setViewName("redirect:MissionView");
		}
		return mav;
	}
	
	/*
	 * // ���� ���
	 * 
	 * @PostMapping("/review/reviewWrite") public String reviewWrite(ReplyDTO dto,
	 * HttpSession session) { // session �۾��� ���ϱ�
	 * dto.setUserid((String)session.getAttribute("logId"));
	 * 
	 * int result = service.replyInsert(dto);
	 * 
	 * return result+""; }
	 */
}