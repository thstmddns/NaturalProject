package kr.or.ozz.dao;

import java.util.List;

import kr.or.ozz.dto.TaskDTO;
import kr.or.ozz.dto.PagingDTO;

public interface TaskDAO {
	//���ܵ��
	public int TaskwriteOk(TaskDTO dto);
	//���ܸ�� ���� (paging, search)
	public List<TaskDTO> Tasklist(int no, PagingDTO pDTO);
	//1�� ���ڵ� ����(���ܳ��뺸��)
	public TaskDTO getTask(int no);
	//���� ����(update)
	public int TaskEdit(TaskDTO dto);
	//���� ����(delete)
	public int TaskDel(int no, String userid);
}
