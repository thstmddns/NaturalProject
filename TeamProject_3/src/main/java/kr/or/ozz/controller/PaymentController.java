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
import kr.or.ozz.service.PaymentService;



@RestController
@RequestMapping("/mypage")
public class PaymentController {

	@Autowired
	PaymentService service;
	
	
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
	
	@PostMapping("/subscription_list")
	public ModelAndView mysublist(String userid) {
		ModelAndView mav = new ModelAndView();
		List<PaymentDTO> mysublist = null;
		try {
			mysublist = service.getUserPayment(userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("mysublist", mysublist);
		mav.setViewName("${pageContext.request.contextPath}/mypage/subscription_list");
		return mav;
	}
}
	
	