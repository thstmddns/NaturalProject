package kr.or.ozz.dao;

import java.util.List;

import kr.or.ozz.dto.StepDTO;
import kr.or.ozz.dto.PagingDTO;

public interface StepDAO {
	//���ܵ��
	public int StepwriteOk(StepDTO dto);
	//���ܸ�� ���� (paging, search)
	public List<StepDTO> Steplist(int no, PagingDTO pDTO);
	//1�� ���ڵ� ����(���ܳ��뺸��)
	public StepDTO getStep(int no);
	//���� ����(update)
	public int StepEdit(StepDTO dto);
	//���� ����(delete)
	public int StepDel(int no, String userid);
}
