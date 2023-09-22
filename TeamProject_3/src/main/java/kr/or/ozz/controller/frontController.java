package kr.or.ozz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class frontController {
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
}
