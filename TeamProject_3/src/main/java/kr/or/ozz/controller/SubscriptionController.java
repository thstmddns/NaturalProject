package kr.or.ozz.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ozz.dto.SubscriptionDTO;
import kr.or.ozz.service.SubscriptionService;

@RestController
@RequestMapping("/mypage")
public class SubscriptionController {

	@Autowired
	SubscriptionService service;
	
	// 구독내역 리스트
	@GetMapping("/subscription_list")
	public ModelAndView mysubList(HttpSession session) {
		String logId = (String)session.getAttribute("logId");
	    ModelAndView mav = new ModelAndView();
	    List<SubscriptionDTO> mysubList = null;
	    
	    try {
	    	mysubList = service.getAllSubscriptions(logId);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    if (mysubList == null || mysubList.isEmpty()) {
	        // 내역 정보가 없을 경우 "구독내역이 없습니다"를 출력
	        mav.addObject("message", "구독내역이 없습니다.");
	    } else {
	        mav.addObject("mysubList", mysubList);
	    }
	    
	    mav.setViewName("/mypage/subscription_list");
	    return mav;
	}
}