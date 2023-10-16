package kr.or.ozz.dao;

import java.util.List;

import kr.or.ozz.dto.PagingDTO;
import kr.or.ozz.dto.StepDTO;
import kr.or.ozz.dto.TaskDTO;
import kr.or.ozz.dto.MissionDTO;

public interface MissionDAO {
	//占싱션듸옙占�
	public int MissionwriteOk(MissionDTO dto);
	//占싱션몌옙占� 占쏙옙占쏙옙 (paging, search)
	public List<MissionDTO> Missionlist(PagingDTO pDTO);
	//占쏙옙 占쏙옙占쌘듸옙 占쏙옙
	public int m_totalRecord(PagingDTO pDTO);

	/*
	 * public int q_totalRecord(PagingDTO pDTO); public int r_totalRecord(PagingDTO
	 * pDTO); public int b_totalRecord(PagingDTO pDTO); public int
	 * u_totalRecord(PagingDTO pDTO);
	 */

	//1占쏙옙 占쏙옙占쌘듸옙 占쏙옙占쏙옙(占싱션놂옙占쎈보占쏙옙)
	public MissionDTO getMission(int no);
	//占쏙옙회占쏙옙占쏙옙占쏙옙
	public void hitCount(int no);
	//占싱쇽옙 占쏙옙占쏙옙(update)
	public int MissionEdit(MissionDTO dto);
	//占싱쇽옙 占쏙옙占쏙옙(delete)
	public int MissionDel(int no, String userid);
	// 占싸깍옙抉占� 占쏙옙占쏙옙占쏙옙占쏙옙
	public List<MissionDTO> MissionToplist();
	
	public int missionCreate(MissionDTO dto);
	
	public int getmission_no();

	public int stepCreate(StepDTO sdto);

	public int getstep_no();

	public int taskCreate(TaskDTO tdto);
	
	
}
