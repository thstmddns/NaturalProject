package kr.or.ozz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ozz.dao.ReviewDAO;
import kr.or.ozz.dto.ReviewDTO;
import kr.or.ozz.dto.PagingDTO;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	ReviewDAO dao;

	@Override
	public int ReviewwriteOk(ReviewDTO dto) {
		return dao.ReviewwriteOk(dto);
	}

	@Override
	public List<ReviewDTO> Reviewlist(PagingDTO pDTO) {
		return dao.Reviewlist(pDTO);
	}

	@Override
	public int r_totalRecord(PagingDTO pDTO) {
		return dao.r_totalRecord(pDTO);
	}

	@Override
	public ReviewDTO getReview(int no) {
		return dao.getReview(no);
	}

	@Override
	public void hitCount(int no) {
		dao.hitCount(no);
	}

	@Override
	public int ReviewEdit(ReviewDTO dto) {
		return dao.ReviewEdit(dto);
	}

	@Override
	public int ReviewDel(int no, String userid) {
		return dao.ReviewDel(no, userid);
	}
	
	@Override
	public List<ReviewDTO> M_Reviewlist(int no){
		return dao.M_Reviewlist(no);
	}
}