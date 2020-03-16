package com.di.xyz.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.di.xyz.Users.UsersService;

@Controller
public class LoginController {

	@Autowired
	UsersService usersService;
	
	@RequestMapping("/login")
	public ModelAndView goLogin() {
		ModelAndView mv =new ModelAndView("/login/login");
		mv.addObject("pageC", "login");
		mv.addObject("pageD", "login");
		return mv;
	}
	
	
	

	
}
