package kr.or.ozz.dto;

import java.util.List;

import lombok.Data;

@Data
public class MissionDTO {
	private int mission_no;			// 미션 넘버
	private String userid;			// 회원아이디
	private String mission_title;	// 미션 제목
	private String mission_tag;		// 미션 태그
	private String mission_content;	// 미션 컨텐츠
	private String created_at;		// 미션 생성시간
	private String mission_data;	// 미션 첨부파일
	private String mission_tool;	// 미션 도구
	private String mission_cate;	// 미션 카테고리
	private int hit;				// 미션 조회 수
	private String mission_skill;	// 미션스킬
	private int mission_yn;
	private List<StepDTO> steps;
}
