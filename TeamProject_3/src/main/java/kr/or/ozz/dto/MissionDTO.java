package kr.or.ozz.dto;

import java.util.List;

import lombok.Data;

@Data
public class MissionDTO {
	private int mission_no;			// 미션 번호
	private String userid;			// 회원 아이디
	private String mission_title;	// 미션 제목
	private String mission_tag;		// 미션 태그
	private String mission_content;	// 미션 내용(소개)
	private String created_at;		// 미션 작성일
	private String mission_data;	// 미션 활용 데이터
	private String mission_tool;	// 미션 활용 툴
	private String mission_cate;	// 미션 카테고리
	private int hit;				// 조회수
	private String mission_skill;	// 미션 스킬
	private int mission_yn;	// 미션 완료여부
	private List<StepDTO> steps;
}
