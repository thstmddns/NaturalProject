package kr.or.ozz.service;

import java.util.List;

import kr.or.ozz.dto.PaymentDTO;

public interface PaymentService {

    // ���� ������ �����ϴ� �޼���
	public void insertPayment(PaymentDTO dto);

    // Ư�� ������� ���� ���� ��ȸ �޼���
	public List<PaymentDTO> getUserPayment(String userid);

    
}
