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

@RestController
@RequestMapping("/Qna")
public class QnaController {
	@Autowired
	QnaService service;
	
	@GetMapping("/Qnalist")
	public ModelAndView Qnalist(PagingDTO pDTO) {
		//총레코드수
		pDTO.setQ_totalRecord(service.q_totalRecord(pDTO));
		
		//해당페이지의 레코드 선택
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

	//글쓰기 폼으로 이동
	@GetMapping("/QnaWrite")
	public ModelAndView Qnawrite(int no, String mission_title) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("m_no", no);
		mav.addObject("mission_title", mission_title);
		mav.setViewName("Mission/QnA");
		return mav;
	}
	
	//글쓰기 DB 기록
	@PostMapping("/QnawriteOk")
	public ResponseEntity<String> QnawriteOk(QnaDTO dto, HttpServletRequest request, @RequestParam("file_name_base64") String base64ImageData) {
			byte[] imageData;
			if (base64ImageData == "") {
		    	imageData = new byte[0]; 
		    } else {
		    	imageData = Base64.getDecoder().decode(base64ImageData.split(",")[1]);
		    }
			
	    
		dto.setFile_name(imageData);
		dto.setUserid( (String)request.getSession().getAttribute("logId") );
		int m_no = dto.getMission_no();
		
		int result = 0;
		try {
			result = service.QnawriteOk(dto);
		} catch (Exception e) {
			System.out.println("�Խ��� �� ��� ���ܹ߻�..."+e.getMessage());
		}
		//등록결과에 따른 스크립트 생성하기
		String tag = "<script>";
		if(result>0) { //성공 -> 게시판 목록
			if(m_no == 0) {
				tag += "location.href='/ozz/Qna/Qnalist';";
			} else {
				tag += "location.href='/ozz/Mission/MissionView?no="+m_no+"';";
			}
				
		}else { //실패 -> 글 등록 폼으로 이동
			tag += "alert('글 등록이 실패하였습니다.');";
			tag += "history.back();";
		}
		tag += "</script>";
		
		//ResponseEntity 객체는 프론트페이지를 작성할 수 있다.
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text","html", Charset.forName("UTF-8")));
		return new ResponseEntity<String>(tag, headers, HttpStatus.OK);
	}
	//글내용보기
	@GetMapping("/QnaView")
	public ModelAndView QnaView(int no, PagingDTO pDTO) {
		//조회수 증가
		service.hitCount(no);
		//레코드선택
		QnaDTO dto = service.getQna(no);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", dto);
		mav.addObject("pDTO", pDTO);
		mav.setViewName("Qna/QnaView");
		
		return mav;
	}
	//글 수정 폼
	@GetMapping("/QnaEdit")
	public ModelAndView QnaEdit(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", service.getQna(no)); //dto 변수 생성 대신 직접입력
		mav.setViewName("Qna/QnaEdit");
		
		return mav;
	}
	@PostMapping("/QnaEditOk") //no, subject, content
	public ModelAndView QnaEditOk(QnaDTO dto, HttpSession session) {
		dto.setUserid((String)session.getAttribute("logId"));
		
		int result = service.QnaEdit(dto);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("no", dto.getQna_no());
		if(result>0) { //글수정성공 -> 글 내용 보기
			mav.setViewName("redirect:QnaView");
		}else { //글수정실패 -> 수정폼으로
			mav.setViewName("redirect:QnaEdit");
		}
		return mav;
	}
	//글삭제
	@GetMapping("/QnaDel")
	public ModelAndView QnaDel(int no, HttpSession session) {
		int result = service.QnaDel(no, (String)session.getAttribute("logId"));
	
		ModelAndView mav = new ModelAndView();
		if(result>0) {//삭제성공 -> 목록
			mav.setViewName("main/searchCom");
		}else {//삭제실패 -> 글내용
			mav.addObject("no", no);
			mav.setViewName("redirect:QnaView");
		}
		return mav;
	}
}