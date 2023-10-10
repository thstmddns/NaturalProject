package kr.or.ozz.dto;

import lombok.Data;

@Data
public class QnaDTO {
	private int qna_no;				// ���� ��ȣ
	private String userid;			// ȸ�� ���̵�
	private int mission_no;			// �̼� ��ȣ
	private String qna_title;		// qna ����
	private String qna_content;		// qna ����
	private String created_at;		// qna �ۼ���

	private byte[] file_name;		// qna ÷�����ϸ�
	
	private String file_name_base64;
}
