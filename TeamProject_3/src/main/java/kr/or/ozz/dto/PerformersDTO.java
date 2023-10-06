package kr.or.ozz.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PerformersDTO {

	    // 수행 번호
	    private int performer_no;

	    // 미션 번호
	    private int mission_no;

	    // 회원 아이디
	    private String userid;
	    
	    private int task_no;
	    private Date started_at;
	    private Date completed_at;
	    private int step_no;
}

	