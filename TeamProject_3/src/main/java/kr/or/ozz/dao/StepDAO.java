package kr.or.ozz.dao;

import java.util.List;

import kr.or.ozz.dto.StepDTO;
import kr.or.ozz.dto.PagingDTO;

public interface StepDAO {
	//스텝등록
	public int StepwriteOk(StepDTO dto);
	//스텝목록 선택 (paging, search)
	public List<StepDTO> Steplist(int no, PagingDTO pDTO);
	//1개 레코드 선택(스텝내용보기)
	public StepDTO getStep(int no);
	//스텝 수정(update)
	public int StepEdit(StepDTO dto);
	//스텝 삭제(delete)
	public int StepDel(int no, String userid);
}
