package kr.or.ozz.controller;

import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ozz.dto.PerformersDTO;
import kr.or.ozz.dto.UserDTO;
import kr.or.ozz.service.PerformersService;
import kr.or.ozz.service.UserService;

@Controller
@RequestMapping("/register")
public class UserController {
	
	@Autowired
	UserService service;
	
	@Autowired
	PerformersService Pservice;
	
	@Autowired
	private JavaMailSender mailSender;
	
	// 회원가입 폼으로 이동
	@GetMapping("/registerJoin")
	public String UserRegForm() {
		return "register/registerForm";
	}
	
	// 회원가입
	@PostMapping("/registerJoinOk")
	public ModelAndView UserRegOk(UserDTO dto) {
	    
	    System.out.println(dto.toString());
	    // DB에 insert
	    int result = 0;
	    try {
	        result = service.UserInsert(dto);
	    } catch (Exception e) {
	        System.out.println("회원가입 실패" + e.getMessage());
	    }

	    ModelAndView mav = new ModelAndView();
	    if (result > 0) {
	        mav.setViewName("main/landing"); // 회원가입 성공 시 홈 페이지로 리다이렉트
	    } else {
	        mav.setViewName("register/registerForm"); // 회원가입 실패 시 회원가입 폼으로 이동
	    }
	    
	    return mav; // ModelAndView 객체를 반환
	}


       // 로그인 화면으로 이동
	   //@GetMapping("/login")
	   //public String login() {
	      //return "register/login";
	   //}
	   
	   // 로그인
	   @PostMapping("/loginOk")
	   public ModelAndView loginOk(String userid, String pwd, HttpSession session) {
	      //dto일치하는 정보가 있으면 아이디, 이름 담겨져있음.
	      //             없으면 null
	      UserDTO dto = service.loginOk(userid, pwd);
	      //List<PerformersDTO> mymissionList = Pservice.getPerfomersList(userid);

	      ModelAndView mav = new ModelAndView();
	      if(dto != null) { //성공
	         //세션에 아이디, 이름, 로그인상태 기록
	         session.setAttribute("logId", dto.getUserid());
	         session.setAttribute("logName", dto.getUsername());
	         session.setAttribute("logStatus", "Y");
	         
	         mav.setViewName("main/landing");
	      }else { //실패
	         //로그인 폼으로 이동하기
	         mav.setViewName("redirect:login");
	         
	      }
	      // mav.addObject("mymissionList", mymissionList);
	      return mav;
	   }
	

	// 로그아웃 -> 완료
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main/landing");
		return mav;
	}
	
	// 아이디 찾기 화면으로 이동 -> 완료
	@GetMapping("/findId")
	public String findIdForm() {
		return "register/idSearch";
	}
	
	// 아이디 찾아서 아이디만 반환
	@PostMapping("/findIdOk")	// ajax
	@ResponseBody
	public String findIdOk(UserDTO dto) {
		// 이름, 연락처가 일치하는 아이디와 이메일을 구한다
		
		String resultDTO = service.findId(dto.getUsername(), dto.getEmail());
		String resultTxt = "N";
		if(resultDTO!=null) {	// 일치하는 정보가 있을때
			//이메일보내기
			
			String subject = "아이디 찾기 결과";
			String content = "<div style='background:pink; "
					+ "border:1px solid #ddd; padding:50px; text-align:center;'>";
			content += "검색한 아이디는 :"+ resultDTO;
			content += "</div>";
			
			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				messageHelper.setFrom("chg30528@naver.com");		// 보내는 메일주소
				messageHelper.setTo(dto.getEmail());		// 받는 메일주소
				messageHelper.setSubject(subject);
				messageHelper.setText("text/html; charset=UTF-8", content);
				mailSender.send(message);
								
				resultTxt = "Y";
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {	// 일치하는 정보가 없을때
			resultTxt = "N";
		}
		return resultTxt;
	}
	
	// 아이디 중복 체크 -> 완료
	@GetMapping("/dupChk")
	@ResponseBody
	public Integer dupChk(String id) {
		Integer result = 0;
		String dupId = null;

		try {
			dupId = service.dupChk(id);
			if (dupId == null) {
				result = 0;
			} else {
				result = 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 비밀번호 찾기 페이지 이동 -> 완료
	@GetMapping("/findPw")
	public String findPwdForm() {
		return "register/pwSearch";
	}

	// 비밀번호 찾기 -> 완료
	@PostMapping("/findPwOk")
	@ResponseBody
	public int findPwd(String userid, String email) {
		String pwd = null;
		int result = 0;
		try {
			pwd = service.findPwd(userid, email);
			if (pwd == null) {
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String setFrom = "chg30528@naver.com";
		String toMail = email;
		String title = "비밀번호 찾기 이메일 입니다.";
		String content = "홈페이지를 방문해주셔서 감사합니다." + "<br><br>" + "회원님의 비밀번호는 " + pwd + "입니다.";
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(message);
			result = 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 카카오로그인이동
	@GetMapping("/kakaologin")
	public String kakaologin() {
		return "register/kakaologin";
	}
	

	}

