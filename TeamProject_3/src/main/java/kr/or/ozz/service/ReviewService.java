package kr.or.ozz.service;

import java.util.List;

import kr.or.ozz.dto.ReviewDTO;
import kr.or.ozz.dto.PagingDTO;

public interface ReviewService {
	public int ReviewwriteOk(ReviewDTO dto);
	public List<ReviewDTO> Reviewlist(PagingDTO pDTO);
	public int r_totalRecord(PagingDTO pDTO);
	public ReviewDTO getReview(int no);
	public void hitCount(int no);
	public int ReviewEdit(ReviewDTO dto);
	public int ReviewDel(int no, String userid);
	public List<ReviewDTO> M_Reviewlist(int no);
}