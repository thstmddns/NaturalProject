package kr.or.ozz.dao;

import java.util.List;

import kr.or.ozz.dto.ReplyDTO;

public interface ReplyDAO {
	public int replyInsert(ReplyDTO dto);
	public List<ReplyDTO> replySelect(int qna_no);
	public int replyUpdate(ReplyDTO dto);
	public int replyDelete(int qnr_no);
}