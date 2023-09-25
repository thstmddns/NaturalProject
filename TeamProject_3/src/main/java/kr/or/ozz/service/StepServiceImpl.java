package kr.or.ozz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ozz.dao.StepDAO;
import kr.or.ozz.dto.StepDTO;
import kr.or.ozz.dto.PagingDTO;

@Service
public class StepServiceImpl implements StepService{
	@Autowired
	StepDAO dao;

	@Override
	public int StepwriteOk(StepDTO dto) {
		return dao.StepwriteOk(dto);
	}

	@Override
	public List<StepDTO> Steplist(int no, PagingDTO pDTO) {
		return dao.Steplist(no, pDTO);
	}

	@Override
	public StepDTO getStep(int no) {
		return dao.getStep(no);
	}

	@Override
	public int StepEdit(StepDTO dto) {
		return dao.StepEdit(dto);
	}

	@Override
	public int StepDel(int no, String userid) {
		return dao.StepDel(no, userid);
	}
}
