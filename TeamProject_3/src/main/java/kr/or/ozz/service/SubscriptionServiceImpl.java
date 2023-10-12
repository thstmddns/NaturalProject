package kr.or.ozz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ozz.dao.PaymentDAO;
import kr.or.ozz.dao.SubscriptionDAO;
import kr.or.ozz.dto.PaymentDTO;
import kr.or.ozz.dto.PerformersDTO;
import kr.or.ozz.dto.SubscriptionDTO;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{

	 @Autowired
	    private SubscriptionDAO dao;

	@Override
	public String getSubstatus(String userid) {
		return dao.getSubstatus(userid);
	}

}
