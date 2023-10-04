package kr.or.ozz.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ozz.dao.BReplyDAO;
import kr.or.ozz.dto.BReplyDTO;

@Service
public class BReplyServiceImp implements BReplyService{
	@Inject
	BReplyDAO dao;

	@Override
	public int replyInsert(BReplyDTO dto) {
		return dao.replyInsert(dto);
	}

	@Override
	public List<BReplyDTO> replySelect(int board_no) {
		return dao.replySelect(board_no);
	}

	@Override
	public int replyUpdate(BReplyDTO dto) {
		return dao.replyUpdate(dto);
	}

	@Override
	public int replyDelete(int board_r_no) {
		return dao.replyDelete(board_r_no);
	}
}
