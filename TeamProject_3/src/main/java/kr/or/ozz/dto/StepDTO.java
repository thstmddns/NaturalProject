package kr.or.ozz.dto;

import lombok.Data;

@Data
public class StepDTO {
	private int step_no;			// 스텝 일련번호
	private int mission_no;			// 미션 번호
	private int step;				// 해당 스텝(각 미션별 스텝)
	private String step_title;		// 스텝 제목
	private String step_content;	// 스텝 내용
	private String step_time;		// 소요 시간
	private String mission_skill;	// 사용 기술
	private String userid;			// 회원 아이디
}
