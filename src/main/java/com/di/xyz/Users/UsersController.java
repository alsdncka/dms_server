package com.di.xyz.Users;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.di.xyz.Common.util;

@Controller
@RequestMapping("users")
public class UsersController {

	@Autowired
	UsersService usersService;
	
	util u = new util();
	
	@RequestMapping("")
	public ModelAndView goUsers(UserVo userVo) {
		ModelAndView mv=new ModelAndView("users/list");
		mv.addObject("pageC", "users");
		mv.addObject("pageD", "list");
		
		mv.addObject("list",usersService.getUserList(new UserVo()));
		return mv;
		
	}
	
	@RequestMapping("/add")
	public ModelAndView goUserAdd(UserVo userVo) {
		ModelAndView mv=new ModelAndView("users/add");
		mv.addObject("pageC", "users");
		mv.addObject("pageD", "add");
		return mv;
	}
	
	@RequestMapping("/edit")
	public ModelAndView goUserEdit(UserVo userVo) {
		ModelAndView mv=new ModelAndView("users/add");
		mv.addObject("pageC", "users");
		mv.addObject("pageD", "edit");
		System.out.println(userVo);
		System.out.println(usersService.getUser(userVo));
		mv.addObject("user", usersService.getUser(userVo));
		return mv;
	}

	
	

}
