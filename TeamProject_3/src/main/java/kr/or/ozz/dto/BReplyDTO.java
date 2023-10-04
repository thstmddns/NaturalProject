package kr.or.ozz.dto;

import lombok.Data;

@Data
public class BReplyDTO {
	private int board_r_no;
	private int board_no;
	private String coment;
	private String created_at;
	private String userid;
}
