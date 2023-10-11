package kr.or.ozz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ozz.dao.BoardDAO;
import kr.or.ozz.dto.PagingDTO;
import kr.or.ozz.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDAO dao;

	@Override
	public int BoardwriteOk(BoardDTO dto) {
		return dao.BoardwriteOk(dto);
	}

	@Override
	public List<BoardDTO> Boardlist(PagingDTO pDTO) {
		return dao.Boardlist(pDTO);
	}

	@Override
	public int totalRecord(PagingDTO pDTO) {
		return dao.totalRecord(pDTO);
	}

	@Override
	public BoardDTO getBoard(int no) {
		return dao.getBoard(no);
	}

	@Override
	public void hitCount(int no) {
		dao.hitCount(no);
	}

	@Override
	public int BoardEdit(BoardDTO dto) {
		return dao.BoardEdit(dto);
	}

	@Override
	public int BoardDel(int no, String userid) {
		return dao.BoardDel(no, userid);
	}
	
	@Override
	public List<BoardDTO> BoardToplist(){
		return dao.BoardToplist();
	}
}
