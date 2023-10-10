package kr.or.ozz.dto;

import lombok.Data;

@Data
public class ReplyDTO {
	private int qnr_no;
	private int qna_no;
	private String coment;
	private String created_at;
	private String userid;
	private String file_name;
}
