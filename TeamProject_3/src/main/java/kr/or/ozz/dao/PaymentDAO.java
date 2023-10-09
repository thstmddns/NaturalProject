package kr.or.ozz.dao;

import java.util.List;

import kr.or.ozz.dto.PaymentDTO;

public interface PaymentDAO {
	
    // 결제 정보를 저장하는 메서드
	public void insertPayment(PaymentDTO dto);

    // 특정 사용자의 결제 내역 조회 메서드
	public List<PaymentDTO> getUserPayment(String userid);

    
}
