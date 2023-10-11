package kr.or.ozz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ozz.dto.MissionDTO;
import kr.or.ozz.service.MissionService;

@Controller
@RequestMapping("/main")
public class frontController {
	@Autowired
	MissionService Mservice;
	
	@GetMapping("/login")
	public String login() {
		return "register/login";
	}
	
	@GetMapping("/register")
	public String registerForm() {
		return "register/registerForm";
	}
	
	@GetMapping("/landing")
	public String home() {
		return "main/landing";
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
	public String searchC() {
		return "main/searchCom";
	}
	
	@GetMapping("/searchMission")
	public String searchM() {
		return "main/searchMission";
	}
	
	@GetMapping("/searchMa")
	public String searchMa() {
		return "main/searchMaster";
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