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

import kr.or.ozz.dto.BoardDTO;
import kr.or.ozz.dto.PagingDTO;
import kr.or.ozz.dto.StepDTO;
import kr.or.ozz.service.BoardService;
import kr.or.ozz.service.StepService;

// @Controller : ��, �並 �������ش�.
//				 ModelAndView,
//				 Model, String

// @RestController : ���� ���ϵȴ�.
//					 Model+viewPage -> ModelAndView�� ����
@RestController
@RequestMapping("/Board")
public class BoardController {
	@Autowired
	BoardService service;

	@GetMapping("/Boardlist")
	public ModelAndView Boardlist(PagingDTO pDTO) {
		// �ѷ��ڵ��
		pDTO.setTotalRecord(service.totalRecord(pDTO));

		// �ش��������� ���ڵ� ����
		List<BoardDTO> list = service.Boardlist(pDTO);

		// ModelAndView
		ModelAndView mav = new ModelAndView();

		/*
		 * for (BoardDTO dto : list) { byte[] imageData = dto.getFile_name(); String
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
		mav.setViewName("Board/Boardlist");
		return mav;
	}

	// �۾��� ������ �̵�
	@GetMapping("/Boardwrite")
	public ModelAndView Boardwrite() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Board/Boardwrite");
		return mav;
	}

	// �۾��� DB ���
	@PostMapping("/BoardwriteOk")
	public ResponseEntity<String> BoardwriteOk(BoardDTO dto, HttpServletRequest request) {
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
			result = service.BoardwriteOk(dto);
		} catch (Exception e) {
			System.out.println("�Խ��� �� ��� ���ܹ߻�..." + e.getMessage());
		}
		// ��ϰ���� ���� ��ũ��Ʈ �����ϱ�
		String tag = "<script>";
		if (result > 0) { // ���� -> �Խ��� ���
			tag += "location.href='/ozz/Board/Boardlist';";
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
	@GetMapping("/BoardView")
	public ModelAndView BoardView(int no, PagingDTO pDTO) {
		//��ȸ�� ����
		service.hitCount(no);
		// ���ڵ弱��
		BoardDTO dto = service.getBoard(no);

//	    byte[] imageData = dto.getFile_name();
//        String base64ImageData = Base64.getEncoder().encodeToString(imageData);
//        dto.setFile_name_base64(base64ImageData);
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", dto);
		mav.addObject("pDTO", pDTO);
		mav.setViewName("Board/BoardView");

		return mav;
	}

	// �� ���� ��
	@GetMapping("/BoardEdit")
	public ModelAndView BoardEdit(int no) {
//		BoardDTO dto = service.getBoard(no);
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", service.getBoard(no)); // dto ���� ���� ��� �����Է�
		mav.setViewName("Board/BoardEdit");

		return mav;
	}

	@PostMapping("/BoardEditOk") // no, subject, content
	public ModelAndView BoardEditOk(BoardDTO dto, HttpSession session) {
		dto.setUserid((String) session.getAttribute("logId"));

		int result = service.BoardEdit(dto);

		ModelAndView mav = new ModelAndView();
		mav.addObject("no", dto.getBoard_no());
		if (result > 0) { // �ۼ������� -> �� ���� ����
			mav.setViewName("redirect:BoardView");
		} else { // �ۼ������� -> ����������
			mav.setViewName("redirect:BoardEdit");
		}
		return mav;
	}

	// �ۻ���
	@GetMapping("/BoardDel")
	public ModelAndView BoardDel(int no, HttpSession session) {
		int result = service.BoardDel(no, (String) session.getAttribute("logId"));

		ModelAndView mav = new ModelAndView();
		if (result > 0) {// �������� -> ���
			mav.setViewName("redirect:Boardlist");
		} else {// �������� -> �۳���
			mav.addObject("no", no);
			mav.setViewName("redirect:BoardView");
		}
		return mav;
	}
}