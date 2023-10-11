package kr.or.ozz.dao;

import java.util.List;

import kr.or.ozz.dto.PaymentDTO;

public interface PaymentDAO {

	public int GetPayment(String userid);
	
    // ���� ������ �����ϴ� �޼���
	public void insertPayment(PaymentDTO dto);

    // Ư�� ������� ���� ���� ��ȸ �޼���
	public List<PaymentDTO> getUserPayment(String userid);

    
}
