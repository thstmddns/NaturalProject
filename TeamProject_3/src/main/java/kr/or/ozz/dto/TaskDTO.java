package kr.or.ozz.dto;

import lombok.Data;

@Data
public class TaskDTO {
	private int task_no;			// 태스크 번호
	private int step_no;			// 스텝 번호
	private String task_title;		// 태스크 제목
	private String task_content;	// 태스크 내용
	private String userid;			// 작성자
}
