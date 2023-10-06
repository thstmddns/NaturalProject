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
	public int insertPerformers(PerformersDTO performersDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PerformersDTO> getPerformersByUserId(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PerformersDTO> getPerformersByDateRange(Date started_at, Date completed_at) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PerformersDTO> getPerformersByInProgressStep(int step_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PerformersDTO> getPerformersByUserAndMission(String userid, int mission_no) {
		return dao.getPerformersByUserAndMission(userid, mission_no);
	}

	@Override
	public List<PerformersDTO> getInProgressTasks(String userid, int task_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateInProgressTask(PerformersDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PerformersDTO> getUserAchievement(String userid) {
		return null;
	}


}
