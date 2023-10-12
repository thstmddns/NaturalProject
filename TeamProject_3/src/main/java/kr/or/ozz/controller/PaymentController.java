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
	
	//���������� �̵�
	@GetMapping("/paymentForm")
	public ModelAndView paymentForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/mypage/paymentForm");
		return mav;
	}
	
	//������û
	@PostMapping("/paymentRequest")
	@ResponseBody
	public String paymentRequest(PaymentDTO dto, HttpSession session) {
		
		try {
	            // ���ǿ��� ����� ���̵� ������ PaymentDTO�� ����
	            dto.setUserid((String)session.getAttribute("logId"));
	            // ���� ������ ����
				dto.getSub_option();
				dto.getPay_amount();
	            service.insertPayment(dto);
	            
	            // ������ �����ϸ� ����� ���̵� ��ȯ
	            return dto.getUserid();
	        } catch (Exception e) {
	            e.printStackTrace();
	            // ���� ���� �� "fail"�� ��ȯ
	            return "fail";
	        }
		 
	    }
	
		//��������(��������) ����Ʈ
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
	
	