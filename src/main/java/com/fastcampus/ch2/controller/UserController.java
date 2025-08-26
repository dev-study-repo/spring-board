package com.fastcampus.ch2.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fastcampus.ch2.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/login-form")
	public String loginForm(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		//세션에 id 있으면 출력.
		if(session.getAttribute("id") !=null)
			System.out.print(session.getAttribute("id"));
		
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			for(Cookie cookie : cookies) {
				//if ("id".equals(cookie.getName())) model.addAttribute("savedId", cookie.getValue());
				if("rem".equals(cookie.getName())) model.addAttribute("rem",cookie.getValue());
			}
		}
		return "loginForm";
	}
	@PostMapping(value="/user-info")
	public String userInfo(HttpServletRequest request, Model model, HttpServletResponse response, @RequestParam String id, @RequestParam String pwd, @RequestParam(defaultValue = "n") String remember) {
		if(id.equals("") || pwd.equals("")){
			return "loginForm";
		}
		//id,비번 맞는지 확인
		if(userService.login(id, pwd)) {
			//쿠키 저장 
//			Cookie cookie = new Cookie("id",id);
//			response.addCookie(cookie);
			Cookie cookieRem = new Cookie("rem",remember);
			response.addCookie(cookieRem);
			
			//세션 저장
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("pwd", pwd);
			
			//모델 저장 
//			model.addAttribute("id", id );
//			model.addAttribute("pwd", pwd );
			
			return "redirect:/board/list";
		}
		return "loginForm";
	}
	@GetMapping(value="/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
}
