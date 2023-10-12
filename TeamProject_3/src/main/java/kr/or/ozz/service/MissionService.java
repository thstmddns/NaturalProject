package kr.or.ozz.service;

import java.util.List;

import kr.or.ozz.dto.PagingDTO;
import kr.or.ozz.dto.MissionDTO;

public interface MissionService {
	public int MissionwriteOk(MissionDTO dto);
	public List<MissionDTO> Missionlist(PagingDTO pDTO);
	
	public int m_totalRecord(PagingDTO pDTO);
	/*
	 * public int q_totalRecord(PagingDTO pDTO); public int r_totalRecord(PagingDTO
	 * pDTO); public int b_totalRecord(PagingDTO pDTO); public int
	 * u_totalRecord(PagingDTO pDTO);
	 */
	
	public MissionDTO getMission(int no);
	public void hitCount(int no);
	public int MissionEdit(MissionDTO dto);
	public int MissionDel(int no, String userid);
	public List<MissionDTO> MissionToplist();
}
