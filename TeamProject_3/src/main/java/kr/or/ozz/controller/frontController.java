package kr.or.ozz.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ozz.dto.BoardDTO;
import kr.or.ozz.dto.MissionDTO;
import kr.or.ozz.dto.PagingDTO;
import kr.or.ozz.dto.PerformersDTO;
import kr.or.ozz.dto.QnaDTO;
import kr.or.ozz.dto.ReviewDTO;
import kr.or.ozz.dto.UserDTO;
import kr.or.ozz.service.BoardService;
import kr.or.ozz.service.MissionService;
import kr.or.ozz.service.PerformersService;
import kr.or.ozz.service.QnaService;
import kr.or.ozz.service.ReviewService;
import kr.or.ozz.service.UserService;

@Controller
@RequestMapping("/main")
public class frontController {
	@Autowired
	   MissionService Mservice;
	   
	   @Autowired
	   QnaService Qservice;
	   
	   @Autowired
	   ReviewService Rservice;
	   
	   @Autowired
	   BoardService Bservice;
	   
	   @Autowired
	   UserService Uservice;
	   
	   @Autowired
	    PerformersService Pservice;
	
	@GetMapping("/login")
	public String login() {
		return "register/login";
	}
	
	@GetMapping("/register")
	public String registerForm() {
		return "register/registerForm";
	}
	
	@GetMapping("/landing")
	public ModelAndView landing(HttpSession session) {
	   List<MissionDTO> MissionToplist = Mservice.MissionToplist();

	   ModelAndView mav = new ModelAndView();
	   mav.addObject("MissionToplist", MissionToplist);
	   
	   // 세션에서 현재 사용자의 아이디를 가져옴
       String userid = (String)session.getAttribute("logId");
       
       
       // 현재 사용자의 달성률 정보를 가져옴 (예시: 사용자 아이디로 달성률 정보를 가져옴)
       List<PerformersDTO> mymissionList = Pservice.getPerfomersList(userid);
       //System.out.println("UserId from session: " + userid);
       
       // 모델에 데이터 추가
       mav.addObject("mymissionList", mymissionList);
       
       
       
       List<PerformersDTO> mymissioningCnt = Pservice.missioningcnt(userid);
       List<PerformersDTO> mymissionendCnt = Pservice.missionendcnt(userid);
       
       mav.addObject("mymissioningCnt", mymissioningCnt);
       mav.addObject("mymissionendCnt", mymissionendCnt);
       mav.setViewName("main/landing");
       
       return mav; // 뷰 이름 설정
   
	}
	
	@GetMapping("/idSearch")
	public String idSearch() {
		return "register/idSearch";
	}
	
	@GetMapping("/pwSearch")
	public String pwSearch() {
		return "register/pwSearch";
	}

	@GetMapping("/mainMission")
	public ModelAndView MissionToplist() {
	   List<MissionDTO> MissionToplist = Mservice.MissionToplist();

	   ModelAndView mav = new ModelAndView();
	   mav.addObject("MissionToplist", MissionToplist);
	   
	   mav.setViewName("main/mission");
	   
	   return mav;
	}
	
	@GetMapping("/mainMaster")
	public String mainMa() {
		return "main/master";
	}
	
	@GetMapping("/mainCom")
	public String mainCommu() {
		return "main/community";
	}
	
	@GetMapping("/comWrite")
	public String comWrite() {
		return "community/comWrite";
	}
	
	@GetMapping("/QnAWrite")
	public String QnAWrite() {
		return "mission/QnA";
	}
	
	@GetMapping("/feedbackWrite")
	public String feedbackWrite() {
		return "mission/feedback";
	}
	

	@GetMapping("/searchCom")
	public ModelAndView searchCom(PagingDTO pDTO) {
		pDTO.setM_totalRecord(Mservice.m_totalRecord(pDTO));
		pDTO.setQ_totalRecord(Qservice.q_totalRecord(pDTO));
		pDTO.setR_totalRecord(Rservice.r_totalRecord(pDTO));
		pDTO.setB_totalRecord(Bservice.b_totalRecord(pDTO));
		pDTO.setU_totalRecord(Uservice.u_totalRecord(pDTO));
		
		List<QnaDTO> Q_list = Qservice.Qnalist(pDTO);
		List<ReviewDTO> R_list = Rservice.Reviewlist(pDTO);
		List<BoardDTO> B_list = Bservice.Boardlist(pDTO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("Q_list", Q_list);
		mav.addObject("R_list", R_list);
		mav.addObject("B_list", B_list);
		mav.addObject("pDTO", pDTO);
		
		mav.setViewName("main/searchCom");
		return mav;
	}
	
	@GetMapping("/searchMission")
	public ModelAndView searchMission(PagingDTO pDTO) {
		pDTO.setM_totalRecord(Mservice.m_totalRecord(pDTO));
		pDTO.setQ_totalRecord(Qservice.q_totalRecord(pDTO));
		pDTO.setR_totalRecord(Rservice.r_totalRecord(pDTO));
		pDTO.setB_totalRecord(Bservice.b_totalRecord(pDTO));
		pDTO.setU_totalRecord(Uservice.u_totalRecord(pDTO));

		
		List<MissionDTO> list = Mservice.Missionlist(pDTO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("M_list", list);
		mav.addObject("pDTO", pDTO);
		mav.setViewName("main/searchMission");
		return mav;
	}
	
	@GetMapping("/searchMaster")
	public ModelAndView searchMaster(PagingDTO pDTO) {
		pDTO.setM_totalRecord(Mservice.m_totalRecord(pDTO));
		pDTO.setQ_totalRecord(Qservice.q_totalRecord(pDTO));
		pDTO.setR_totalRecord(Rservice.r_totalRecord(pDTO));
		pDTO.setB_totalRecord(Bservice.b_totalRecord(pDTO));
		pDTO.setU_totalRecord(Uservice.u_totalRecord(pDTO));
		
		List<UserDTO> list = Uservice.Userlist(pDTO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("U_list", list);
		mav.addObject("pDTO", pDTO);
		mav.setViewName("main/searchMaster");
		return mav;
	}

	@GetMapping("/communityView")
	public String communityView() {
		return "community/communityView";
	}
	
	@GetMapping("/myPage")
	public String myPage() {
		return "main/mypage";
	}
	
	@GetMapping("/subscribe")
	public String popup() {
		return "main/popup";
	}
	
	@GetMapping("/missionIng")
	public String missionGoPopup() {
		return "main/missionGoPopup";
	}
	
	@GetMapping("/missionView")
	public String missionView() {
		return "mission/missionView";
	}
	
	@GetMapping("/missionStep")
	public String missionStep() {
		return "mission/missionStep";
	}
	
	@GetMapping("/missionMake")
	public String missionMake() {
		return "mission/missionMake";
	}
}
