package kr.or.ozz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ozz.dao.QnaDAO;
import kr.or.ozz.dto.QnaDTO;
import kr.or.ozz.dto.PagingDTO;

@Service
public class QnaServiceImpl implements QnaService {
	@Autowired
	QnaDAO dao;

	@Override
	public int QnawriteOk(QnaDTO dto) {
		return dao.QnawriteOk(dto);
	}

	@Override
	public List<QnaDTO> Qnalist(PagingDTO pDTO) {
		return dao.Qnalist(pDTO);
	}

	@Override
	public int totalRecord(PagingDTO pDTO) {
		return dao.totalRecord(pDTO);
	}

	@Override
	public QnaDTO getQna(int no) {
		return dao.getQna(no);
	}

	@Override
	public void hitCount(int no) {
		dao.hitCount(no);
	}

	@Override
	public int QnaEdit(QnaDTO dto) {
		return dao.QnaEdit(dto);
	}

	@Override
	public int QnaDel(int no, String userid) {
		return dao.QnaDel(no, userid);
	}
}