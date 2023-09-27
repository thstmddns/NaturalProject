package kr.or.ozz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ozz.dao.TaskDAO;
import kr.or.ozz.dto.TaskDTO;
import kr.or.ozz.dto.PagingDTO;

@Service
public class TaskServiceImpl implements TaskService{
	@Autowired
	TaskDAO dao;

	@Override
	public int TaskwriteOk(TaskDTO dto) {
		return dao.TaskwriteOk(dto);
	}

	@Override
	public List<TaskDTO> Tasklist(int no, PagingDTO pDTO) {
		return dao.Tasklist(no, pDTO);
	}

	@Override
	public TaskDTO getTask(int no) {
		return dao.getTask(no);
	}

	@Override
	public int TaskEdit(TaskDTO dto) {
		return dao.TaskEdit(dto);
	}

	@Override
	public int TaskDel(int no, String userid) {
		return dao.TaskDel(no, userid);
	}
}
