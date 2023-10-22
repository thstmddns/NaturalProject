package kr.or.ozz.dto;

import java.util.List;

import lombok.Data;

@Data
public class StepDTO {
	private int step_no;			// ���� �Ϸù�ȣ
	private int mission_no;			// �̼� ��ȣ
	private int step;				// �ش� ����(�� �̼Ǻ� ����)
	private String step_title;		// ���� ����
	private String userid;			// ȸ�� ���̵�
	private List<TaskDTO> tasks;
}