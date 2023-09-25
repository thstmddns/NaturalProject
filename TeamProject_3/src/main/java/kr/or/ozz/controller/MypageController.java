package kr.or.ozz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ozz.dto.UserDTO;
import kr.or.ozz.service.UserService;

@Controller
@RequestMapping("/mypage")
public class MypageController {

	@Autowired
	UserService service;
	
	//마이페이지 메인으로 이동
	@GetMapping("/mypage_main")
	public String String () {
		return "mypage/mypage_main";
	}
	
	// 회원수정 폼으로 이동 ->
	@GetMapping("/myinfo")
	public ModelAndView UserUpdateForm(HttpSession session) {
		String logId = (String)session.getAttribute("logId");
		ModelAndView mav = new ModelAndView();
		UserDTO dto = null;
		try {
			dto = service.getUser(logId);
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
			result = service.UserUpdate(dto);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("수정실패" + e.getMessage());
		}

		ModelAndView mav = new ModelAndView();
		if (result > 0) {
			mav.setViewName("ozz");
		} else {
			mav.setViewName("mypage/myinfo");
		}
		return mav;
	}

}
