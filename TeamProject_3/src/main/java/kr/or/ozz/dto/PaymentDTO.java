package kr.or.ozz.dto;

import java.sql.Date;

import lombok.Data;
@Data
public class PaymentDTO {

	    // 결제 번호
	    private int payno;
	    // 회원 아이디
	    private String userid;
	    // 구독 옵션
	    private String suboption;
	    // 결제 요금
	    private int payamount;

}

