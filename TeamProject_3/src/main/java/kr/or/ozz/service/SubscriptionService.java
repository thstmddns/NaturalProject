package kr.or.ozz.service;

import java.util.List;

import kr.or.ozz.dto.PaymentDTO;
import kr.or.ozz.dto.SubscriptionDTO;

public interface SubscriptionService {

    // �������� 
	public void subscriptionIng(SubscriptionDTO dto);

    // ��������� ����Ʈ
	public List<PaymentDTO> subscriptionEnd(String userid);
	
}

