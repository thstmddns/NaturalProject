package kr.or.ozz.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ozz.dao.ReplyDAO;
import kr.or.ozz.dto.ReplyDTO;

@Service
public class ReplyServiceImp implements ReplyService{
	@Inject
	ReplyDAO dao;

	@Override
	public int replyInsert(ReplyDTO dto) {
		return dao.replyInsert(dto);
	}

	@Override
	public List<ReplyDTO> replySelect(int qna_no) {
		return dao.replySelect(qna_no);
	}

	@Override
	public int replyUpdate(ReplyDTO dto) {
		return dao.replyUpdate(dto);
	}

	@Override
	public int replyDelete(int qnr_no) {
		return dao.replyDelete(qnr_no);
	}
}
