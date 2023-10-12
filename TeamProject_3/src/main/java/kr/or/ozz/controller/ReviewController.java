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

// @Controller : ��, �並 �������ش�.
//				 ModelAndView,
//				 Model, String

// @RestController : ���� ���ϵȴ�.
//					 Model+viewPage -> ModelAndView�� ����
@RestController
@RequestMapping("/Review")
public class ReviewController {
	@Autowired
	ReviewService service;

	@GetMapping("/Reviewlist")
	public ModelAndView Reviewlist(PagingDTO pDTO) {
		// �ѷ��ڵ��
		pDTO.setR_totalRecord(service.r_totalRecord(pDTO));

		// �ش��������� ���ڵ� ����
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

	// �۾��� ������ �̵�
	@GetMapping("/Reviewwrite")
	public ModelAndView Reviewwrite(int m_no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("m_no", m_no);
		mav.setViewName("Review/Reviewwrite");
		return mav;
	}

	// �۾��� DB ���
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

		// no, hit, writedate -> ����Ŭ
		// userid -> ����

		// HttpSession session = request.getSession();
		// String userid = (String)session.getAttribute("logId");
		// dto.setUserid(userid);
		// ���� ��ġ�� �Ʒ� �ڵ�� ����
		dto.setUserid((String) request.getSession().getAttribute("logId"));

		int result = 0;
		try {
			result = service.ReviewwriteOk(dto);
		} catch (Exception e) {
			System.out.println("�Խ��� �� ��� ���ܹ߻�..." + e.getMessage());
		}
		// ��ϰ���� ���� ��ũ��Ʈ �����ϱ�
//		String tag = "<script>";
//		if (result > 0) { // ���� -> �Խ��� ���
//			tag += "location.href='/ozz/Review/Reviewlist';";
//		} else { // ���� -> �� ��� ������ �̵�
//			tag += "alert('�� ����� �����Ͽ����ϴ�.');";
//			tag += "history.back();";
//		}
//		tag += "</script>";

		// ResponseEntity ��ü�� ����Ʈ�������� �ۼ��� �� �ִ�.
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(new MediaType("text", "html", Charset.forName("UTF-8")));
//		return new ResponseEntity<String>(tag, headers, HttpStatus.OK);
		ModelAndView mav = new ModelAndView();
		if (result > 0) { // ���� �Ǵ� ���� ��� �ش� URL�� �̵�
	        mav.setViewName("redirect:/Mission/MissionView?no="+dto.getMission_no());
	    } else { // ���� �� alert �޽����� ���� �ش� URL�� �̵�
	        mav.addObject("message", "�� ����� �����Ͽ����ϴ�.");
	        mav.setViewName("redirect:/Mission/MissionView?no="+dto.getMission_no());
	    }

	    return mav;
		
	}

	// �۳��뺸��
	@GetMapping("/ReviewView")
	public ModelAndView ReviewView(int no, PagingDTO pDTO) {
		//��ȸ�� ����
		service.hitCount(no);
		// ���ڵ弱��
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

	// �� ���� ��
	@GetMapping("/ReviewEdit")
	public ModelAndView ReviewEdit(int no) {
//		ReviewDTO dto = service.getReview(no);
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", service.getReview(no)); // dto ���� ���� ��� �����Է�
		mav.setViewName("Review/ReviewEdit");

		return mav;
	}

	@PostMapping("/ReviewEditOk") // no, subject, content
	public ModelAndView ReviewEditOk(ReviewDTO dto, HttpSession session) {
		dto.setUserid((String) session.getAttribute("logId"));

		int result = service.ReviewEdit(dto);

		ModelAndView mav = new ModelAndView();
		mav.addObject("no", dto.getReview_no());
		if (result > 0) { // �ۼ������� -> �� ���� ����
			mav.setViewName("redirect:ReviewView");
		} else { // �ۼ������� -> ����������
			mav.setViewName("redirect:ReviewEdit");
		}
		return mav;
	}

	// �ۻ���
	@GetMapping("/ReviewDel")
	public ModelAndView ReviewDel(int no, HttpSession session) {
		int result = service.ReviewDel(no, (String) session.getAttribute("logId"));

		ModelAndView mav = new ModelAndView();
		if (result > 0) {// �������� -> ���
			mav.setViewName("redirect:Reviewlist");
		} else {// �������� -> �۳���
			mav.addObject("no", no);
			mav.setViewName("redirect:ReviewView");
		}
		return mav;
	}
}