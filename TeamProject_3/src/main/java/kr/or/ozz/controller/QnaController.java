package kr.or.ozz.controller;

import java.nio.charset.Charset;
import java.util.Base64;
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

import kr.or.ozz.dto.PagingDTO;
import kr.or.ozz.dto.QnaDTO;
import kr.or.ozz.service.QnaService;

// @Controller : ��, �並 �������ش�.
//				 ModelAndView,
//				 Model, String

// @RestController : ���� ���ϵȴ�.
//					 Model+viewPage -> ModelAndView�� ����
@RestController
@RequestMapping("/Qna")
public class QnaController {
	@Autowired
	QnaService service;
	
	@GetMapping("/Qnalist")
	public ModelAndView Qnalist(PagingDTO pDTO) {
		//�ѷ��ڵ��
		pDTO.setTotalRecord(service.totalRecord(pDTO));
		
		//�ش��������� ���ڵ� ����
		List<QnaDTO> list = service.Qnalist(pDTO);
		
		//ModelAndView
		ModelAndView mav = new ModelAndView();
		
        for (QnaDTO dto : list) {
        	byte[] imageData = dto.getFile_name();
			/* String base64ImageData = Base64.getEncoder().encodeToString(imageData); */
        	String base64ImageData;
        	if (imageData != null) {
        	    base64ImageData = Base64.getEncoder().encodeToString(imageData);
        	    
        	} else {
        		base64ImageData = "None";
        	}
        	
			dto.setFile_name_base64(base64ImageData);
        }
		mav.addObject("list", list);
		mav.addObject("pDTO", pDTO);
		mav.setViewName("Qna/Qnalist");
		return mav;
	}
	//�۾��� ������ �̵�
	@GetMapping("/Qnawrite")
	public ModelAndView Qnawrite() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Qna/Qnawrite");
		return mav;
	}
	//�۾��� DB ���
	@PostMapping("/QnawriteOk")
	public ResponseEntity<String> QnawriteOk(QnaDTO dto, HttpServletRequest request, @RequestParam("file_name_base64") String base64ImageData) {
			byte[] imageData;
			if (base64ImageData == "") {
		    	imageData = new byte[0]; 
		    } else {
		    	imageData = Base64.getDecoder().decode(base64ImageData.split(",")[1]);
		    }
			
	    
		dto.setFile_name(imageData);
		//HttpServletRequest -> request, HttpSession
		//HttpSession -> session
		
		//no, hit, writedate -> ����Ŭ
		//userid -> ����
		
		//HttpSession session = request.getSession();
		//String userid = (String)session.getAttribute("logId");
		//dto.setUserid(userid);
		//���� ��ġ�� �Ʒ� �ڵ�� ����
		dto.setUserid( (String)request.getSession().getAttribute("logId") );
		
		int result = 0;
		try {
			result = service.QnawriteOk(dto);
		} catch (Exception e) {
			System.out.println("�Խ��� �� ��� ���ܹ߻�..."+e.getMessage());
		}
		//��ϰ���� ���� ��ũ��Ʈ �����ϱ�
		String tag = "<script>";
		if(result>0) { //���� -> �Խ��� ���
			tag += "location.href='/ozz/Qna/Qnalist';";
		}else { //���� -> �� ��� ������ �̵�
			tag += "alert('�� ����� �����Ͽ����ϴ�.');";
			tag += "history.back();";
		}
		tag += "</script>";
		
		//ResponseEntity ��ü�� ����Ʈ�������� �ۼ��� �� �ִ�.
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text","html", Charset.forName("UTF-8")));
		return new ResponseEntity<String>(tag, headers, HttpStatus.OK);
	}
	//�۳��뺸��
	@GetMapping("/QnaView")
	public ModelAndView QnaView(int no, PagingDTO pDTO) {
		//��ȸ�� ����
		service.hitCount(no);
		//���ڵ弱��
		QnaDTO dto = service.getQna(no);
		
//	    byte[] imageData = dto.getFile_name();
//        String base64ImageData = Base64.getEncoder().encodeToString(imageData);
//        dto.setFile_name_base64(base64ImageData);
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", dto);
		mav.addObject("pDTO", pDTO);
		mav.setViewName("Qna/QnaView");
		
		return mav;
	}
	//�� ���� ��
	@GetMapping("/QnaEdit")
	public ModelAndView QnaEdit(int no) {
//		QnaDTO dto = service.getQna(no);
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", service.getQna(no)); //dto ���� ���� ��� �����Է�
		mav.setViewName("Qna/QnaEdit");
		
		return mav;
	}
	@PostMapping("/QnaEditOk") //no, subject, content
	public ModelAndView QnaEditOk(QnaDTO dto, HttpSession session) {
		dto.setUserid((String)session.getAttribute("logId"));
		
		int result = service.QnaEdit(dto);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("no", dto.getQna_no());
		if(result>0) { //�ۼ������� -> �� ���� ����
			mav.setViewName("redirect:QnaView");
		}else { //�ۼ������� -> ����������
			mav.setViewName("redirect:QnaEdit");
		}
		return mav;
	}
	//�ۻ���
	@GetMapping("/QnaDel")
	public ModelAndView QnaDel(int no, HttpSession session) {
		int result = service.QnaDel(no, (String)session.getAttribute("logId"));
	
		ModelAndView mav = new ModelAndView();
		if(result>0) {//�������� -> ���
			mav.setViewName("redirect:Qnalist");
		}else {//�������� -> �۳���
			mav.addObject("no", no);
			mav.setViewName("redirect:QnaView");
		}
		return mav;
	}
}