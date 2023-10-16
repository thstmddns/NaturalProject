package kr.or.ozz.service;

import java.util.List;

import kr.or.ozz.dto.PagingDTO;
import kr.or.ozz.dto.StepDTO;
import kr.or.ozz.dto.TaskDTO;
import kr.or.ozz.dto.MissionDTO;

public interface MissionService {
	public int MissionwriteOk(MissionDTO dto);
	public List<MissionDTO> Missionlist(PagingDTO pDTO);
	public int m_totalRecord(PagingDTO pDTO);
	public MissionDTO getMission(int no);
	public void hitCount(int no);
	public int MissionEdit(MissionDTO dto);
	public int MissionDel(int no, String userid);
	public List<MissionDTO> MissionToplist();
	public int missionCreate(MissionDTO dto);
	public int getmission_no();
	public int stepCreate(StepDTO sdto);
	public int getstep_no();
	public int taskCreate(TaskDTO tdto);
}
