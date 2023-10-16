package kr.or.ozz.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class TaskDTO {
	private int task_no;			// 태스크 번호
	private int step_no;			// 스텝 번호
	private String task_title;		// 태스크 제목
	private String task_content;	// 태스크 내용
	private String userid;			// 작성자
	private int task_yn;			// 태스크 완료여부
	private String file_name;		// 태스크 첨부파일
	private MultipartFile task_file_name;
	private int task;
}