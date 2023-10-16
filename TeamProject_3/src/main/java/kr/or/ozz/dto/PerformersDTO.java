package kr.or.ozz.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PerformersDTO {

	    // ���� ��ȣ
	    private int performer_no;

	    // �̼� ��ȣ
	    private int mission_no;

	    // ȸ�� ���̵�
	    private String userid;
	    private int task_no;
	    private Date started_at;
	    private Date completed_at;
	    private int step_no;
	    private float mission_rate;
	    private String mission_title;
	    private int feedback_score;
	    private int restcnt;
	    private int ingcnt;
	    private int endcnt;
	    

}

	