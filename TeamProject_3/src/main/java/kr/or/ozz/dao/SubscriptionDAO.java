package kr.or.ozz.dao;

import java.util.List;

import kr.or.ozz.dto.PaymentDTO;
import kr.or.ozz.dto.PerformersDTO;
import kr.or.ozz.dto.SubscriptionDTO;

public interface SubscriptionDAO {

	// ���� ���� Ȯ��
	public String getSubstatus(String userid);
	
}
