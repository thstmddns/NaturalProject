package kr.or.ozz.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class SubscriptionDTO {
	    // 구독 옵션
	    private String suboption;
	    // 구독시작일자
	    private Date started_at;
	    // 구독만료일자
	    private Date expired_at;
	    // 구독 요금
	    private String subpayment;
	}
