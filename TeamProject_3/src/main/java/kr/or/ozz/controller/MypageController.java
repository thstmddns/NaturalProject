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
  
	
	//���������� �������� �̵�
	@GetMapping("/mypage_main")
	public String Mypage () {
		return "mypage/mypage_main";
	}
	// ȸ������ ��������
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

	
	// ȸ������ ������ �̵� ->
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

	// ȸ������ ���� -> 
	@PostMapping("/updateOk")
	public ModelAndView UserUpdateOk(UserDTO dto) {
		int result = 0;
		System.out.println(dto);
		try {
			result = Uservice.UserUpdate(dto);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��������" + e.getMessage());
		}

		ModelAndView mav = new ModelAndView();
		if (result > 0) {
			mav.setViewName("main/landing");
		} else {
			mav.addObject("errorMessage", "������ ������ �����߽��ϴ�.");
			mav.setViewName("mypage/myinfo");
		}
		return mav;
	}
	
	 //�������� �̼Ǹ���Ʈ
	 @GetMapping("/mission_ing")
	    public ModelAndView mymissionList(HttpSession session) {
	        // ���ǿ��� ���� ������� ���̵� ������
	        String userid = (String)session.getAttribute("logId");
	        
	        ModelAndView mav = new ModelAndView();
	        
	        if (userid == null) {
				// ����ڰ� �α��εǾ� ���� ������ �α��� �������� �����̷�Ʈ �Ǵ� �ٸ� ó���� �� �� ����
	            mav.setViewName("register/login");
	        	return mav; // �α��� �������� �̵�
	        }else {
	        
	        // ���� ������� �޼��� ������ ������ (����: ����� ���̵�� �޼��� ������ ������)
	        List<PerformersDTO> mymissionList = Pservice.getPerfomersList(userid);
	        System.out.println("UserId from session: " + userid);
	        
	        // �𵨿� ������ �߰�
	        mav.addObject("mymissionList", mymissionList);
	        mav.setViewName("main/missionGoPopup");
	        return mav; // �� �̸� ����
	    }
	 }
	        
	   	 //�Ϸ��� �̼Ǹ���Ʈ
	   	 @GetMapping("/mission_end")
	   	    public ModelAndView myendmissionList(HttpSession session) {
	   	        // ���ǿ��� ���� ������� ���̵� ������
	   	        String userid = (String)session.getAttribute("logId");
	   	        
	   	        ModelAndView mav = new ModelAndView();
	   	        
	   	        if (userid == null) {
	   				// ����ڰ� �α��εǾ� ���� ������ �α��� �������� �����̷�Ʈ �Ǵ� �ٸ� ó���� �� �� ����
	   	            mav.setViewName("register/login");
	   	        	return mav; // �α��� �������� �̵�
	   	        }else {
	   	        
	   	        // ���� ������� �޼��� ������ ������ (����: ����� ���̵�� �޼��� ������ ������)
	   	        List<PerformersDTO> myendmissionList = Pservice.getPerfomersEndList(userid);
	   	        System.out.println("UserId from session: " + userid);
	   	        
	   	        // �𵨿� ������ �߰�
	   	        mav.addObject("myendmissionList", myendmissionList);
	   	        mav.setViewName("mypage/mission_end");
	   	        return mav; // �� �̸� ����
	   	    }
	   	 }
	   	 
	   	
	   	


	 }