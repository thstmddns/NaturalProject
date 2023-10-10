package kr.or.ozz.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ozz.dto.QReplyDTO;
import kr.or.ozz.service.QReplyService;

@RestController
@RequestMapping("/Qreply")
public class QReplyController {
	@Autowired
	QReplyService service;
	
	//��۵��
	@PostMapping("/replyWrite")
	public String replyWrite(QReplyDTO dto, HttpSession session) {
		//session �۾��� ���ϱ�
		dto.setUserid((String)session.getAttribute("logId"));
		
		int result = service.replyInsert(dto);
		return result+"";
	}
	///��۸��
	@GetMapping("/replyList")
	public List<QReplyDTO> replyList(int qna_no) {//���� �۹�ȣ
		return service.replySelect(qna_no);
	}
	//��ۼ���(DB:update)
	@PostMapping("/replyEditOk")
	 public String replyEditOk(QReplyDTO dto) {
		return String.valueOf(service.replyUpdate(dto));
	 }
	//��ۻ���
	@GetMapping("/replyDel")
	public String replyDel(int qnr_no) {
		return String.valueOf(service.replyDelete(qnr_no));
	}
}
