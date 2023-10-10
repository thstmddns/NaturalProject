package kr.or.ozz.dao;

import java.util.List;

import kr.or.ozz.dto.QReplyDTO;

public interface QReplyDAO {
	public int replyInsert(QReplyDTO dto);
	public List<QReplyDTO> replySelect(int qna_no);
	public int replyUpdate(QReplyDTO dto);
	public int replyDelete(int qnr_no);
}