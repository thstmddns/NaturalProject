package kr.or.ozz.dto;

import java.sql.Date;

import lombok.Data;
@Data
public class PaymentDTO {

	    // ���� ��ȣ
	    private int pay_no;
	    // ȸ�� ���̵�
	    private String userid;
	    // ���� �ɼ�
	    private String sub_option;
	    // ���� ���
	    private int pay_amount;
	    
	    private Date started_at;

}

