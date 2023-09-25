package kr.or.ozz.service;

import java.util.List;

import kr.or.ozz.dto.StepDTO;
import kr.or.ozz.dto.PagingDTO;

public interface StepService {
	public int StepwriteOk(StepDTO dto);
	public List<StepDTO> Steplist(int no, PagingDTO pDTO);
	public StepDTO getStep(int no);
	public int StepEdit(StepDTO dto);
	public int StepDel(int no, String userid);
}
