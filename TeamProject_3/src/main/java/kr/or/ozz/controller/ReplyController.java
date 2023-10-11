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
	
	//��۵��
	@PostMapping("/reply/replyWrite")
	public String replyWrite(ReplyDTO dto, HttpSession session) {
		//session �۾��� ���ϱ�
		dto.setUserid((String)session.getAttribute("logId"));
		
		int result = service.replyInsert(dto);
		return result+"";
	}
	///��۸��
	@GetMapping("/reply/replyList")
	public List<ReplyDTO> replyList(int qna_no) {//���� �۹�ȣ
		return service.replySelect(qna_no);
	}
	//��ۼ���(DB:update)
	@PostMapping("/reply/replyEditOk")
	 public String replyEditOk(ReplyDTO dto) {
		return String.valueOf(service.replyUpdate(dto));
	 }
	//��ۻ���
	@GetMapping("/reply/replyDel")
	public String replyDel(int qnr_no) {
		return String.valueOf(service.replyDelete(qnr_no));
	}
}
