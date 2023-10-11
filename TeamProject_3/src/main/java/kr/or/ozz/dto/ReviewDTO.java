package kr.or.ozz.dto;

import lombok.Data;

@Data
public class ReviewDTO {
	private int review_no;			// 리뷰 번호
	private int mission_no;			// 미션 번호
	private String review_title;	// 리뷰 제목
	private int review_score;		// 리뷰 점수
	private String review_content;	// 리뷰 내용
	private String created_at;		// 리뷰 작성일
	private String userid;			// 리뷰 작성자
	private String file_name;		// 파일 이름
	private int hit;				// 조회수
}
