package kr.or.ozz.dao;

import java.util.List;

import kr.or.ozz.dto.ReviewDTO;
import kr.or.ozz.dto.PagingDTO;

public interface ReviewDAO {
	//글등록
	public int ReviewwriteOk(ReviewDTO dto);
	//글목록 선택 (paging, search)
	public List<ReviewDTO> Reviewlist(PagingDTO pDTO);
	//총 레코드 수
	public int r_totalRecord(PagingDTO pDTO);
	//1개 레코드 선택(글내용보기)
	public ReviewDTO getReview(int no);
	//조회수증가
	public void hitCount(int no);
	//글 수정(update)
	public int ReviewEdit(ReviewDTO dto);
	//글 삭제(delete)
	public int ReviewDel(int no, String userid);
	//미션에서 리뷰 리스트 출력
	public List<ReviewDTO> M_Reviewlist(int no);
	//검색에서 리뷰 리스트 출력
	public List<ReviewDTO> S_Reviewlist(PagingDTO pDTO);
}