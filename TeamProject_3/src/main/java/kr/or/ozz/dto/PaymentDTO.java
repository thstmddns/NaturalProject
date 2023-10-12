package kr.or.ozz.dto;

import java.sql.Date;

import lombok.Data;
@Data
public class PaymentDTO {

	    // 결제 번호
	    private int pay_no;
	    // 회원 아이디
	    private String userid;
	    // 구독 옵션
	    private String sub_option;
	    // 결제 요금
	    private int pay_amount;
	    
	    private Date started_at;

}

