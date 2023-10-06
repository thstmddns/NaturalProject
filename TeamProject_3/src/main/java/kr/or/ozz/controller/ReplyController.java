package kr.or.ozz.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ozz.dto.ReplyDTO;
import kr.or.ozz.service.ReplyService;

@RestController
public class ReplyController {
	@Autowired
	ReplyService service;
	
	//댓글등록
	@PostMapping("/reply/replyWrite")
	public String replyWrite(ReplyDTO dto, HttpSession session) {
		//session 글쓴이 구하기
		dto.setUserid((String)session.getAttribute("logId"));
		
		int result = service.replyInsert(dto);
		return result+"";
	}
	///댓글목록
	@GetMapping("/reply/replyList")
	public List<ReplyDTO> replyList(int qna_no) {//원글 글번호
		return service.replySelect(qna_no);
	}
	//댓글수정(DB:update)
	@PostMapping("/reply/replyEditOk")
	 public String replyEditOk(ReplyDTO dto) {
		return String.valueOf(service.replyUpdate(dto));
	 }
	//댓글삭제
	@GetMapping("/reply/replyDel")
	public String replyDel(int qnr_no) {
		return String.valueOf(service.replyDelete(qnr_no));
	}
}
