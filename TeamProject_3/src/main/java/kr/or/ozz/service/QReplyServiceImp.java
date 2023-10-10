package kr.or.ozz.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ozz.dao.QReplyDAO;
import kr.or.ozz.dto.QReplyDTO;

@Service
public class QReplyServiceImp implements QReplyService{
	@Inject
	QReplyDAO dao;

	@Override
	public int replyInsert(QReplyDTO dto) {
		return dao.replyInsert(dto);
	}

	@Override
	public List<QReplyDTO> replySelect(int qna_no) {
		return dao.replySelect(qna_no);
	}

	@Override
	public int replyUpdate(QReplyDTO dto) {
		return dao.replyUpdate(dto);
	}

	@Override
	public int replyDelete(int qnr_no) {
		return dao.replyDelete(qnr_no);
	}
}
