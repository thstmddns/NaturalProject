package kr.or.ozz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ozz.dao.UserDAO;
import kr.or.ozz.dto.PagingDTO;
import kr.or.ozz.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO dao;

	@Override
	public int UserInsert(UserDTO dto) {
		return dao.UserInsert(dto);
	}

	@Override
	public UserDTO loginOk(String userid, String pwd) {
		return dao.loginOk(userid, pwd);
	}

	@Override
	public String findId(String username, String email) {
		return dao.findId(username, email);
	}

	@Override
	public String dupChk(String userid) {
		return dao.dupChk(userid);
	}

	@Override
	public String findPwd(String userid, String email) {
		return dao.findPwd(userid, email);
	}

	@Override
	public UserDTO getUser(String logId) {
		return dao.getUser(logId);
	}

	@Override
	public int UserUpdate(UserDTO dto) {
		return dao.UserUpdate(dto);
	}
	
	@Override
	public List<UserDTO> Userlist(PagingDTO pDTO) {
		return dao.Userlist(pDTO);
	}
	
	@Override
	public int u_totalRecord(PagingDTO pDTO) {
		return dao.u_totalRecord(pDTO);
	}
}
