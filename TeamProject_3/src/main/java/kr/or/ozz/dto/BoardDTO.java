package kr.or.ozz.dto;

import lombok.Data;

@Data
public class BoardDTO {
	private int board_no;
	private String board_title;
	private String board_content;
	private String created_at;
	private int hit;
	private String userid;
	private String file_name;
	private String work_cate;
	private String board_cate;
}
