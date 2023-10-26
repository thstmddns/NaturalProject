package kr.or.ozz.dao;

import java.util.List;

import kr.or.ozz.dto.ReviewDTO;
import kr.or.ozz.dto.PagingDTO;

public interface ReviewDAO {
	//�۵��
	public int ReviewwriteOk(ReviewDTO dto);
	//�۸�� ���� (paging, search)
	public List<ReviewDTO> Reviewlist(PagingDTO pDTO);
	//�� ���ڵ� ��
	public int r_totalRecord(PagingDTO pDTO);
	//1�� ���ڵ� ����(�۳��뺸��)
	public ReviewDTO getReview(int no);
	//��ȸ������
	public void hitCount(int no);
	//�� ����(update)
	public int ReviewEdit(ReviewDTO dto);
	//�� ����(delete)
	public int ReviewDel(int no, String userid);
	//�̼ǿ��� ���� ����Ʈ ���
	public List<ReviewDTO> M_Reviewlist(int no);
	//�˻����� ���� ����Ʈ ���
	public List<ReviewDTO> S_Reviewlist(PagingDTO pDTO);
}