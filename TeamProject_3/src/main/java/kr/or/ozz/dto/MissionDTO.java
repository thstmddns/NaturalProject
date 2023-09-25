package kr.or.ozz.dto;

import lombok.Data;

@Data
public class MissionDTO {
	private int mission_no;			// �̼� ��ȣ
	private String userid;			// ȸ�� ���̵�
	private String mission_title;	// �̼� ����
	private String mission_tag;		// �̼� �±�
	private String mission_content;	// �̼� ����(�Ұ�)
	private String created_at;		// �̼� �ۼ���
	private String mission_data;	// �̼� Ȱ�� ������
	private String mission_tool;	// �̼� Ȱ�� ��
	private String mission_cate;	// �̼� ī�װ���
	private int hit;				// ��ȸ��
}