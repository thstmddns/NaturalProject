package kr.or.ozz.dao;

import java.util.List;

import kr.or.ozz.dto.PagingDTO;
import kr.or.ozz.dto.MissionDTO;

public interface MissionDAO {
	//미션등록
	public int MissionwriteOk(MissionDTO dto);
	//미션목록 선택 (paging, search)
	public List<MissionDTO> Missionlist(PagingDTO pDTO);
	//총 레코드 수
	public int totalRecord(PagingDTO pDTO);
	//1개 레코드 선택(미션내용보기)
	public MissionDTO getMission(int no);
	//조회수증가
	public void hitCount(int no);
	//미션 수정(update)
	public int MissionEdit(MissionDTO dto);
	//미션 삭제(delete)
	public int MissionDel(int no, String userid);
}
