package kr.or.ozz.dto;

import lombok.Data;

@Data
public class TaskDTO {
	private int task_no;			// �½�ũ ��ȣ
	private int step_no;			// ���� ��ȣ
	private String task_title;		// �½�ũ ����
	private String task_content;	// �½�ũ ����
	private String userid;			// �ۼ���
}
