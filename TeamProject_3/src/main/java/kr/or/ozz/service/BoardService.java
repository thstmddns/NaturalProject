package kr.or.ozz.service;

import java.util.List;

import kr.or.ozz.dto.PagingDTO;
import kr.or.ozz.dto.BoardDTO;

public interface BoardService {
	public int BoardwriteOk(BoardDTO dto);
	public List<BoardDTO> Boardlist(PagingDTO pDTO);
	public int b_totalRecord(PagingDTO pDTO);
	public BoardDTO getBoard(int no);
	public void hitCount(int no);
	public int BoardEdit(BoardDTO dto);
	public int BoardDel(int no, String userid);
	
	public List<BoardDTO> BoardToplist();
}