package kr.or.ozz.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class SubscriptionDTO {
	    // ���� �ɼ�
	    private String suboption;
	    // ������������
	    private Date started_at;
	    // ������������
	    private Date expired_at;
	    // ���� ���
	    private String subpayment;
	}
