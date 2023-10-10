package kr.or.ozz.dao;

import java.util.List;

import kr.or.ozz.dto.BReplyDTO;

public interface BReplyDAO {
	public int replyInsert(BReplyDTO dto);
	public List<BReplyDTO> replySelect(int board_no);
	public int replyUpdate(BReplyDTO dto);
	public int replyDelete(int board_r_no);
}