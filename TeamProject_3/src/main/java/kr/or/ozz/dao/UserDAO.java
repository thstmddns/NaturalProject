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
	
	public UserDTO getUser(String logId); //회원정보 조회
	
	public int UserUpdate(UserDTO dto); //회원정보 수정
	
	public List<UserDTO> Userlist(PagingDTO pDTO);
	
	public int u_totalRecord(PagingDTO pDTO);
}

