package kr.or.ozz.dto;

import lombok.Data;

@Data
public class QnaDTO {
	private int qna_no;				// 파일 번호
	private String userid;			// 회원 아이디
	private int mission_no;			// 미션 번호
	private String qna_title;		// qna 제목
	private String qna_content;		// qna 내용
	private String created_at;		// qna 작성일

	private byte[] file_name;		// qna 첨부파일명
	
	private String file_name_base64;
}
