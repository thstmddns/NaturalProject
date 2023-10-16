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
	
	// ȸ������ ������ �̵�
	@GetMapping("/registerJoin")
	public String UserRegForm() {
		return "register/registerForm";
	}
	
	// ȸ������
	@PostMapping("/registerJoinOk")
	public ModelAndView UserRegOk(UserDTO dto) {
	    
	    System.out.println(dto.toString());
	    // DB�� insert
	    int result = 0;
	    try {
	        result = service.UserInsert(dto);
	    } catch (Exception e) {
	        System.out.println("ȸ������ ����" + e.getMessage());
	    }

	    ModelAndView mav = new ModelAndView();
	    if (result > 0) {
	        mav.setViewName("main/landing"); // ȸ������ ���� �� Ȩ �������� �����̷�Ʈ
	    } else {
	        mav.setViewName("register/registerForm"); // ȸ������ ���� �� ȸ������ ������ �̵�
	    }
	    
	    return mav; // ModelAndView ��ü�� ��ȯ
	}


       // �α��� ȭ������ �̵�
	   //@GetMapping("/login")
	   //public String login() {
	      //return "register/login";
	   //}
	   
	   // �α���
	   @PostMapping("/loginOk")
	   public ModelAndView loginOk(String userid, String pwd, HttpSession session) {
	      //dto��ġ�ϴ� ������ ������ ���̵�, �̸� ���������.
	      //             ������ null
	      UserDTO dto = service.loginOk(userid, pwd);
	      //List<PerformersDTO> mymissionList = Pservice.getPerfomersList(userid);

	      ModelAndView mav = new ModelAndView();
	      if(dto != null) { //����
	         //���ǿ� ���̵�, �̸�, �α��λ��� ���
	         session.setAttribute("logId", dto.getUserid());
	         session.setAttribute("logName", dto.getUsername());
	         session.setAttribute("logStatus", "Y");
	         
	         mav.setViewName("main/landing");
	      }else { //����
	         //�α��� ������ �̵��ϱ�
	         mav.setViewName("redirect:login");
	         
	      }
	      // mav.addObject("mymissionList", mymissionList);
	      return mav;
	   }
	

	// �α׾ƿ� -> �Ϸ�
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main/landing");
		return mav;
	}
	
	// ���̵� ã�� ȭ������ �̵� -> �Ϸ�
	@GetMapping("/findId")
	public String findIdForm() {
		return "register/idSearch";
	}
	
	// ���̵� ã�Ƽ� ���̵� ��ȯ
	@PostMapping("/findIdOk")	// ajax
	@ResponseBody
	public String findIdOk(UserDTO dto) {
		// �̸�, ����ó�� ��ġ�ϴ� ���̵�� �̸����� ���Ѵ�
		
		String resultDTO = service.findId(dto.getUsername(), dto.getEmail());
		String resultTxt = "N";
		if(resultDTO!=null) {	// ��ġ�ϴ� ������ ������
			//�̸��Ϻ�����
			
			String subject = "���̵� ã�� ���";
			String content = "<div style='background:pink; "
					+ "border:1px solid #ddd; padding:50px; text-align:center;'>";
			content += "�˻��� ���̵�� :"+ resultDTO;
			content += "</div>";
			
			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				messageHelper.setFrom("chg30528@naver.com");		// ������ �����ּ�
				messageHelper.setTo(dto.getEmail());		// �޴� �����ּ�
				messageHelper.setSubject(subject);
				messageHelper.setText("text/html; charset=UTF-8", content);
				mailSender.send(message);
								
				resultTxt = "Y";
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {	// ��ġ�ϴ� ������ ������
			resultTxt = "N";
		}
		return resultTxt;
	}
	
	// ���̵� �ߺ� üũ -> �Ϸ�
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
	
	// ��й�ȣ ã�� ������ �̵� -> �Ϸ�
	@GetMapping("/findPw")
	public String findPwdForm() {
		return "register/pwSearch";
	}

	// ��й�ȣ ã�� -> �Ϸ�
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
		String title = "��й�ȣ ã�� �̸��� �Դϴ�.";
		String content = "Ȩ�������� �湮���ּż� �����մϴ�." + "<br><br>" + "ȸ������ ��й�ȣ�� " + pwd + "�Դϴ�.";
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
	
	// īī���α����̵�
	@GetMapping("/kakaologin")
	public String kakaologin() {
		return "register/kakaologin";
	}
	

	}

