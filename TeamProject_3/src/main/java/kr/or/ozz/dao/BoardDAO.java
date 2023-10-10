package kr.or.ozz.dao;

import java.util.List;

import kr.or.ozz.dto.BoardDTO;
import kr.or.ozz.dto.PagingDTO;

public interface BoardDAO {
	//�۵��
	public int BoardwriteOk(BoardDTO dto);
	//�۸�� ���� (paging, search)
	public List<BoardDTO> Boardlist(PagingDTO pDTO);
	//�� ���ڵ� ��
	public int totalRecord(PagingDTO pDTO);
	//1�� ���ڵ� ����(�۳��뺸��)
	public BoardDTO getBoard(int no);
	//��ȸ������
	public void hitCount(int no);
	//�� ����(update)
	public int BoardEdit(BoardDTO dto);
	//�� ����(delete)
	public int BoardDel(int no, String userid);
}
