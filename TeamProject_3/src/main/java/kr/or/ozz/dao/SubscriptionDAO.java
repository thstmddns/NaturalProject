package kr.or.ozz.dao;

import java.util.List;

import kr.or.ozz.dto.PaymentDTO;
import kr.or.ozz.dto.SubscriptionDTO;

public interface SubscriptionDAO {
	
    // 구독중인 
	public void subscriptionIng(SubscriptionDTO dto);

    // 구독만료된 리스트
	public List<PaymentDTO> subscriptionEnd(String userid);
	
}
