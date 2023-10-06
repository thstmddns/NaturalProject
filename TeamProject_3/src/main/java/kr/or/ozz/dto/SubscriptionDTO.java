package kr.or.ozz.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class SubscriptionDTO {
	    // ���� ����
	    private int subno;
	    // ���� �ɼ�
	    private String suboption;
	    // ������������
	    private Date started_at;
	    // ������������
	    private Date expired_at;
	    // ���� ���
	    private int subpayment;
	}
