package kr.or.ozz.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ozz.dto.MissionDTO;
import kr.or.ozz.dto.PagingDTO;
import kr.or.ozz.dto.PerformersDTO;
import kr.or.ozz.dto.SubscriptionDTO;
import kr.or.ozz.dto.UserDTO;
import kr.or.ozz.service.MissionService;
import kr.or.ozz.service.PerformersService;
import kr.or.ozz.service.SubscriptionService;
import kr.or.ozz.service.UserService;

@Controller
@RequestMapping("/mypage")
public class MypageController {

	@Autowired
	UserService Uservice;
	
	@Autowired
	MissionService service;
	
    @Autowired
    PerformersService Pservice;
   
    @Autowired
    SubscriptionService Sservice;
  
	
	//마이페이지 메인으로 이동
	@GetMapping("/mypage_main")
	public String Mypage () {
		return "mypage/mypage_main";
	}
	// 회원정보 가져오기
	@GetMapping("/myPageDetail")
	public ModelAndView UserInfo(HttpSession session) {
		String logId = (String)session.getAttribute("logId");
		ModelAndView mav = new ModelAndView();
		UserDTO dto = null;
		try {
			dto = Uservice.getUser(logId);
			mav.addObject("dto", dto);
			mav.setViewName("main/mypage");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(dto.toString());
		return mav;
	}

	
	// 회원수정 폼으로 이동 ->
	@GetMapping("/myInfoEdit")
	public ModelAndView UserUpdateForm(HttpSession session) {
		String logId = (String)session.getAttribute("logId");
		ModelAndView mav = new ModelAndView();
		UserDTO dto = null;
		try {
			dto = Uservice.getUser(logId);
			mav.addObject("dto", dto);
			mav.setViewName("mypage/myinfo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(dto.toString());
		return mav;
	}

	// 회원정보 수정 -> 
	@PostMapping("/updateOk")
	public ModelAndView UserUpdateOk(UserDTO dto) {
		int result = 0;
		System.out.println(dto);
		try {
			result = Uservice.UserUpdate(dto);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("수정실패" + e.getMessage());
		}

		ModelAndView mav = new ModelAndView();
		if (result > 0) {
			mav.setViewName("main/landing");
		} else {
			mav.addObject("errorMessage", "프로필 수정에 실패했습니다.");
			mav.setViewName("mypage/myinfo");
		}
		return mav;
	}
	
	 //진행중이 미션리스트
	 @GetMapping("/mission_ing")
	    public ModelAndView mymissionList(HttpSession session) {
	        // 세션에서 현재 사용자의 아이디를 가져옴
	        String userid = (String)session.getAttribute("logId");
	        
	        ModelAndView mav = new ModelAndView();
	        
	        if (userid == null) {
				// 사용자가 로그인되어 있지 않으면 로그인 페이지로 리다이렉트 또는 다른 처리를 할 수 있음
	            mav.setViewName("register/login");
	        	return mav; // 로그인 페이지로 이동
	        }else {
	        
	        // 현재 사용자의 달성률 정보를 가져옴 (예시: 사용자 아이디로 달성률 정보를 가져옴)
	        List<PerformersDTO> mymissionList = Pservice.getPerfomersList(userid);
	        System.out.println("UserId from session: " + userid);
	        
	        // 모델에 데이터 추가
	        mav.addObject("mymissionList", mymissionList);
	        mav.setViewName("main/missionGoPopup");
	        return mav; // 뷰 이름 설정
	    }
	 }
	        
	   	 //완료한 미션리스트
	   	 @GetMapping("/mission_end")
	   	    public ModelAndView myendmissionList(HttpSession session) {
	   	        // 세션에서 현재 사용자의 아이디를 가져옴
	   	        String userid = (String)session.getAttribute("logId");
	   	        
	   	        ModelAndView mav = new ModelAndView();
	   	        
	   	        if (userid == null) {
	   				// 사용자가 로그인되어 있지 않으면 로그인 페이지로 리다이렉트 또는 다른 처리를 할 수 있음
	   	            mav.setViewName("register/login");
	   	        	return mav; // 로그인 페이지로 이동
	   	        }else {
	   	        
	   	        // 현재 사용자의 달성률 정보를 가져옴 (예시: 사용자 아이디로 달성률 정보를 가져옴)
	   	        List<PerformersDTO> myendmissionList = Pservice.getPerfomersEndList(userid);
	   	        System.out.println("UserId from session: " + userid);
	   	        
	   	        // 모델에 데이터 추가
	   	        mav.addObject("myendmissionList", myendmissionList);
	   	        mav.setViewName("mypage/mission_end");
	   	        return mav; // 뷰 이름 설정
	   	    }
	   	 }
	   	 
	   	
	   	


	 }