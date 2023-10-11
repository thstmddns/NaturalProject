package kr.or.ozz.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ozz.dao.PerformersDAO;
import kr.or.ozz.dto.PerformersDTO;

@Service
public class PerformersServiceImpl implements PerformersService {
	
	@Autowired
	PerformersDAO dao;

	@Override
	public int insertPerformers(PerformersDTO dto) {
		return dao.insertPerformers(dto);
	}

	@Override
	public List<PerformersDTO> getPerfomersList(String userid) {
		return dao.getPerfomersList(userid);
	}

	@Override
	public List<PerformersDTO> getPerfomersEndList(String userid) {
		return dao.getPerfomersEndList(userid);
	}



}
