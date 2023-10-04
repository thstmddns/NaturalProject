package kr.or.ozz.dao;

import java.util.List;

import kr.or.ozz.dto.BoardDTO;
import kr.or.ozz.dto.PagingDTO;

public interface BoardDAO {
	//글등록
	public int BoardwriteOk(BoardDTO dto);
	//글목록 선택 (paging, search)
	public List<BoardDTO> Boardlist(PagingDTO pDTO);
	//총 레코드 수
	public int totalRecord(PagingDTO pDTO);
	//1개 레코드 선택(글내용보기)
	public BoardDTO getBoard(int no);
	//조회수증가
	public void hitCount(int no);
	//글 수정(update)
	public int BoardEdit(BoardDTO dto);
	//글 삭제(delete)
	public int BoardDel(int no, String userid);
}
