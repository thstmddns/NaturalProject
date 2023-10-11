package kr.or.ozz.dao;

import java.util.List;

import kr.or.ozz.dto.QnaDTO;
import kr.or.ozz.dto.PagingDTO;

public interface QnaDAO {
	//�۵��
	public int QnawriteOk(QnaDTO dto);
	//�۸�� ���� (paging, search)
	public List<QnaDTO> Qnalist(PagingDTO pDTO);
	//�� ���ڵ� ��
	public int totalRecord(PagingDTO pDTO);
	//1�� ���ڵ� ����(�۳��뺸��)
	public QnaDTO getQna(int no);
	//��ȸ������
	public void hitCount(int no);
	//�� ����(update)
	public int QnaEdit(QnaDTO dto);
	//�� ����(delete)
	public int QnaDel(int no, String userid);
	
	//�̼ǿ��� ��¹޴� QnA
	public List<QnaDTO> M_Qnalist(int no);
	
	// Ŀ�´�Ƽ���� ž �̴� DAO
	public List<QnaDTO> QnaToplist();
}