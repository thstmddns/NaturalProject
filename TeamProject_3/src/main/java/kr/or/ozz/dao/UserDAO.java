package kr.or.ozz.dao;

import java.util.List;

import kr.or.ozz.dto.PagingDTO;
import kr.or.ozz.dto.UserDTO;

public interface UserDAO {
	
	public int UserInsert(UserDTO dto);

	public UserDTO loginOk(String userid, String pwd);

	public String findId(String username, String email);

	public String dupChk(String userid);
	
	public String findPwd(String userid, String email);
	
	public UserDTO getUser(String logId); //ȸ������ ��ȸ
	
	public int UserUpdate(UserDTO dto); //ȸ������ ����
	
	public List<UserDTO> Userlist(PagingDTO pDTO);
	
	public int u_totalRecord(PagingDTO pDTO);
}

