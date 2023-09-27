package kr.or.ozz.dao;

import java.util.List;

import kr.or.ozz.dto.TaskDTO;
import kr.or.ozz.dto.PagingDTO;

public interface TaskDAO {
	//스텝등록
	public int TaskwriteOk(TaskDTO dto);
	//스텝목록 선택 (paging, search)
	public List<TaskDTO> Tasklist(int no, PagingDTO pDTO);
	//1개 레코드 선택(스텝내용보기)
	public TaskDTO getTask(int no);
	//스텝 수정(update)
	public int TaskEdit(TaskDTO dto);
	//스텝 삭제(delete)
	public int TaskDel(int no, String userid);
}
