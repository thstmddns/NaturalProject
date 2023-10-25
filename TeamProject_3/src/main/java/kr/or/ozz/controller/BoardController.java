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

@RestController
@RequestMapping("/Board")
public class BoardController {
	@Autowired
	BoardService service;

	@GetMapping("/Boardlist")
	public ModelAndView Boardlist(PagingDTO pDTO) {
		// 총레코드수
		pDTO.setB_totalRecord(service.b_totalRecord(pDTO));

		// 해당페이지의 레코드 선택
		List<BoardDTO> list = service.Boardlist(pDTO);

		// ModelAndView
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("pDTO", pDTO);
		mav.setViewName("Board/Boardlist");
		return mav;
	}

	// 글쓰기 폼으로 이동
	@GetMapping("/Boardwrite")
	public ModelAndView Boardwrite() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Community/comWrite");
		return mav;
	}

	// 글쓰기 DB 기록
	@PostMapping("/BoardwriteOk")
	public ResponseEntity<String> BoardwriteOk(BoardDTO dto, HttpServletRequest request) {
		dto.setUserid((String) request.getSession().getAttribute("logId"));

		int result = 0;
		try {
			result = service.BoardwriteOk(dto);
		} catch (Exception e) {
			System.out.println("�Խ��� �� ��� ���ܹ߻�..." + e.getMessage());
		}
		// 등록결과에 따른 스크립트 생성하기
		String tag = "<script>";
		if (result > 0) { // 성공 -> 게시판 목록
			tag += "location.href='/ozz/Board/Boardlist';";
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
	@GetMapping("/BoardView")
	public ModelAndView BoardView(int no, PagingDTO pDTO) {
		//조회수 증가
		service.hitCount(no);
		// 레코드선택
		BoardDTO dto = service.getBoard(no);
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", dto);
		mav.addObject("pDTO", pDTO);
		mav.setViewName("Community/communityView");

		return mav;
	}

	// 글 수정 폼
	@GetMapping("/BoardEdit")
	public ModelAndView BoardEdit(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", service.getBoard(no)); // dto 변수 생성 대신 직접입력
		mav.setViewName("Board/BoardEdit");

		return mav;
	}

	@PostMapping("/BoardEditOk") // no, subject, content
	public ModelAndView BoardEditOk(BoardDTO dto, HttpSession session) {
		dto.setUserid((String) session.getAttribute("logId"));

		int result = service.BoardEdit(dto);

		ModelAndView mav = new ModelAndView();
		mav.addObject("no", dto.getBoard_no());
		if (result > 0) { // 글수정성공 -> 글 내용 보기
			mav.setViewName("redirect:BoardView");
		} else { // 글수정실패 -> 수정폼으로
			mav.setViewName("redirect:BoardEdit");
		}
		return mav;
	}

	// 글삭제
	@GetMapping("/BoardDel")
	public ModelAndView BoardDel(int no, HttpSession session) {
		int result = service.BoardDel(no, (String) session.getAttribute("logId"));

		ModelAndView mav = new ModelAndView();
		if (result > 0) {// 삭제성공 -> 목록
			mav.setViewName("redirect:Boardlist");
		} else {// 삭제실패 -> 글내용
			mav.addObject("no", no);
			mav.setViewName("redirect:BoardView");
		}
		return mav;
	}
}