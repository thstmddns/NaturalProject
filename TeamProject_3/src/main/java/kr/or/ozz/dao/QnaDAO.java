package kr.or.ozz.dao;

import java.util.List;

import kr.or.ozz.dto.QnaDTO;
import kr.or.ozz.dto.PagingDTO;

public interface QnaDAO {
	//글등록
	public int QnawriteOk(QnaDTO dto);
	//글목록 선택 (paging, search)
	public List<QnaDTO> Qnalist(PagingDTO pDTO);
	//총 레코드 수
	public int totalRecord(PagingDTO pDTO);
	//1개 레코드 선택(글내용보기)
	public QnaDTO getQna(int no);
	//조회수증가
	public void hitCount(int no);
	//글 수정(update)
	public int QnaEdit(QnaDTO dto);
	//글 삭제(delete)
	public int QnaDel(int no, String userid);
	
	//미션에서 출력받는 QnA
	public List<QnaDTO> M_Qnalist(int no);
	
	// 커뮤니티에서 탑 뽑는 DAO
	public List<QnaDTO> QnaToplist();
}