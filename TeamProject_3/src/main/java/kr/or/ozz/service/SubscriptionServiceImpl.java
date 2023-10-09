package kr.or.ozz.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import kr.or.ozz.dao.SubscriptionDAO;
import kr.or.ozz.dto.SubscriptionDTO;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	SubscriptionDAO dao;

	@Override
	public List<SubscriptionDTO> getAllSubscriptions(String logId) {
		return dao.getAllSubscriptions(logId);
	}


	
	


}
