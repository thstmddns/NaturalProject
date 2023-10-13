package kr.or.ozz.dao;

import java.util.List;

import kr.or.ozz.dto.PaymentDTO;
import kr.or.ozz.dto.PerformersDTO;
import kr.or.ozz.dto.SubscriptionDTO;

public interface SubscriptionDAO {

	// 구독 여부 확인
	public String getSubstatus(String userid);
	
}
