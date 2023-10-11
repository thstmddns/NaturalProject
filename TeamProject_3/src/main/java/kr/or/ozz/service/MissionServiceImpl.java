package kr.or.ozz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ozz.dao.MissionDAO;
import kr.or.ozz.dto.PagingDTO;
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
	public int totalRecord(PagingDTO pDTO) {
		return dao.totalRecord(pDTO);
	}

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

}
