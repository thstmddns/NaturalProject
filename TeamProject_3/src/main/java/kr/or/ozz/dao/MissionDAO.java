package kr.or.ozz.dao;

import java.util.List;

import kr.or.ozz.dto.PagingDTO;
import kr.or.ozz.dto.MissionDTO;

public interface MissionDAO {
	//�̼ǵ��
	public int MissionwriteOk(MissionDTO dto);
	//�̼Ǹ�� ���� (paging, search)
	public List<MissionDTO> Missionlist(PagingDTO pDTO);
	//�� ���ڵ� ��
	public int totalRecord(PagingDTO pDTO);
	//1�� ���ڵ� ����(�̼ǳ��뺸��)
	public MissionDTO getMission(int no);
	//��ȸ������
	public void hitCount(int no);
	//�̼� ����(update)
	public int MissionEdit(MissionDTO dto);
	//�̼� ����(delete)
	public int MissionDel(int no, String userid);
}
