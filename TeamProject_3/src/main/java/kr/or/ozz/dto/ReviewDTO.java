package kr.or.ozz.dto;

import lombok.Data;

@Data
public class ReviewDTO {
	private int review_no;			// ���� ��ȣ
	private int mission_no;			// �̼� ��ȣ
	private String review_title;	// ���� ����
	private int review_score;		// ���� ����
	private String review_content;	// ���� ����
	private String created_at;		// ���� �ۼ���
	private String userid;			// ���� �ۼ���
	private String file_name;		// ���� �̸�
	private int hit;				// ��ȸ��
}
