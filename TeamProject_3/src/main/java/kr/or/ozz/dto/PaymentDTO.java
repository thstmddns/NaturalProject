package kr.or.ozz.dto;

import java.sql.Date;

import lombok.Data;
@Data
public class PaymentDTO {

	    // ���� ��ȣ
	    private int payno;
	    // ȸ�� ���̵�
	    private String userid;
	    // ���� �ɼ�
	    private String suboption;
	    // ���� ���
	    private int payamount;

}

