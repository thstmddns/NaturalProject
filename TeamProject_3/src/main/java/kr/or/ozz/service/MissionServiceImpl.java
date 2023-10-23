package kr.or.ozz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ozz.dao.MissionDAO;
import kr.or.ozz.dto.PagingDTO;
import kr.or.ozz.dto.StepDTO;
import kr.or.ozz.dto.TaskDTO;
import kr.or.ozz.dto.MissionDTO;

@Service
public class MissionServiceImpl implements MissionService{
	@Autowired
	MissionDAO dao;

	@Override
	public int MissionwriteOk(MissionDTO dto) {
		return dao.MissionwriteOk(dto);
	}

	@Override
	public List<MissionDTO> Missionlist(PagingDTO pDTO) {
		return dao.Missionlist(pDTO);
	}

	@Override
	public int m_totalRecord(PagingDTO pDTO) {
		return dao.m_totalRecord(pDTO);
	}
	
	/*
	 * @Override public int q_totalRecord(PagingDTO pDTO) { return
	 * dao.q_totalRecord(pDTO); }
	 * 
	 * @Override public int r_totalRecord(PagingDTO pDTO) { return
	 * dao.r_totalRecord(pDTO); }
	 * 
	 * @Override public int b_totalRecord(PagingDTO pDTO) { return
	 * dao.b_totalRecord(pDTO); }
	 * 
	 * @Override public int u_totalRecord(PagingDTO pDTO) { return
	 * dao.u_totalRecord(pDTO); }
	 */

	@Override
	public MissionDTO getMission(int no) {
		return dao.getMission(no);
	}

	@Override
	public void hitCount(int no) {
		dao.hitCount(no);
	}

	@Override
	public int MissionEdit(MissionDTO dto) {
		return dao.MissionEdit(dto);
	}

	@Override
	public int MissionDel(int no, String userid) {
		return dao.MissionDel(no, userid);
	}

	@Override
	public List<MissionDTO> MissionToplist(){
	   return dao.MissionToplist();
	   }

	@Override
	public int missionCreate(MissionDTO dto) {
		
		return dao.missionCreate(dto);
	}

	@Override
	public int getmission_no() {
		
		return dao.getmission_no();
	}

	@Override
	public int stepCreate(StepDTO sdto) {
		
		return dao.stepCreate(sdto);
	}

	@Override
	public int getstep_no() {
		
		return dao.getstep_no();
	}

	@Override
	public int taskCreate(TaskDTO tdto) {
		
		return dao.taskCreate(tdto);
	}

}
