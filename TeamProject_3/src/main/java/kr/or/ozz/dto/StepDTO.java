package kr.or.ozz.dto;

import lombok.Data;

@Data
public class StepDTO {
	private int step_no;			// ���� �Ϸù�ȣ
	private int mission_no;			// �̼� ��ȣ
	private int step;				// �ش� ����(�� �̼Ǻ� ����)
	private String step_title;		// ���� ����
	private String step_content;	// ���� ����
	private String step_time;		// �ҿ� �ð�
	private String mission_skill;	// ��� ���
	private String userid;			// ȸ�� ���̵�
}
