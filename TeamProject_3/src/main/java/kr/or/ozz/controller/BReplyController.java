package kr.or.ozz.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ozz.dto.BReplyDTO;
import kr.or.ozz.service.BReplyService;

@RestController
@RequestMapping("/Breply")
public class BReplyController {
	@Autowired
	BReplyService service;
	
	//댓글등록
	@PostMapping("/replyWrite")
	public String replyWrite(BReplyDTO dto, HttpSession session) {
		//session 글쓴이 구하기
		dto.setUserid((String)session.getAttribute("logId"));
		
		int result = service.replyInsert(dto);
		return result+"";
	}
	///댓글목록
	@GetMapping("/replyList")
	public List<BReplyDTO> replyList(int board_no) {//���� �۹�ȣ
		return service.replySelect(board_no);
	}
	//댓글수정(DB:update)
	@PostMapping("/replyEditOk")
	 public String replyEditOk(BReplyDTO dto) {
		return String.valueOf(service.replyUpdate(dto));
	 }
	//댓글삭제
	@GetMapping("/replyDel")
	public String replyDel(int board_r_no) {
		return String.valueOf(service.replyDelete(board_r_no));
	}
}
