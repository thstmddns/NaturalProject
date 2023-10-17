package kr.or.ozz.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class TaskDTO {
	private int task_no;			// �½�ũ ��ȣ
	private int step_no;			// ���� ��ȣ
	private String task_title;		// �½�ũ ����
	private String task_content;	// �½�ũ ����
	private String userid;			// �ۼ���
	private int task_yn;			// �½�ũ �ϷῩ��
	private String file_name;		// �½�ũ ÷������
	private MultipartFile task_file_name;
	private int task;
}