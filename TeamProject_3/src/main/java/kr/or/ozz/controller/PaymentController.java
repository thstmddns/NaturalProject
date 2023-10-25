package kr.or.ozz.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ozz.dto.PaymentDTO;
import kr.or.ozz.dto.PerformersDTO;
import kr.or.ozz.service.PaymentService;
import kr.or.ozz.service.SubscriptionService;



@RestController
@RequestMapping("/mypage")
public class PaymentController {

	@Autowired
	PaymentService service;
	
	@Autowired
	SubscriptionService Sservice;
	
	//결제페이지 이동
	@GetMapping("/paymentForm")
	public ModelAndView paymentForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/mypage/paymentForm");
		return mav;
	}
	
	//결제요청
	@PostMapping("/paymentRequest")
	@ResponseBody
	public String paymentRequest(PaymentDTO dto, HttpSession session) {
		
		try {
            // 세션에서 사용자 아이디를 가져와 PaymentDTO에 설정
	            dto.setUserid((String)session.getAttribute("logId"));
	            // 결제 정보를 저장
				dto.getSub_option();
				dto.getPay_amount();
	            service.insertPayment(dto);
	            
	            // 결제가 성공하면 사용자 아이디를 반환
	            return dto.getUserid();
	        } catch (Exception e) {
	            e.printStackTrace();
	            // 결제 실패 시 "fail"을 반환
	            return "fail";
	        }
		 
	    }
	
	//구독내역(결제내역) 리스트
		@GetMapping("/subscription_list")
		public ModelAndView mysublist(HttpSession session) {
			ModelAndView mav = new ModelAndView();
			List<PaymentDTO> mysublist = null;
			
			String mysubstatus = null;
			try {
				mysublist = service.getUserPayment((String)session.getAttribute("logId"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				mysubstatus = Sservice.getSubstatus((String)session.getAttribute("logId"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			mav.addObject("mysublist", mysublist);
			mav.addObject("mysubstatus", mysubstatus);
			mav.setViewName("/mypage/subscription_list");
			return mav;
		}
		
		
	
}
	
	