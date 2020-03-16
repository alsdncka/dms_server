package com.di.xyz.Users;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.di.xyz.Common.util;

@RestController
@RequestMapping("usersRest")
public class UsersRestController {

	@Autowired
	UsersService usersService;

	util u = new util();

	@RequestMapping("/addUser")
	public JSONObject addUser(UserVo userVo) {
		System.out.println(userVo);
		userVo.setUserPw(u.encript(userVo.getUserPw()));
		userVo.setRegDtm(u.getTime());
		userVo.setUserUid(u.getUuid());
		if (usersService.getUser(userVo) != null) {

			return u.fail("증복된 아이디입니다.");
		}

		if (usersService.addUser(userVo) > 0) {

			return u.success("add user 성공",null);

		} else {

			return u.fail("add user 실패");

		}

	}

	@RequestMapping("/editUser")
	public JSONObject editUser(UserVo userVo) {
		if(userVo.getUserPw()!=null) {
			userVo.setUserPw(u.encript(userVo.getUserPw()));
		}
		userVo.setUdtDtm(u.getTime());


		if (usersService.editUser(userVo) > 0) {

			return u.success("add user 성공",null);

		} else {

			return u.fail("add user 실패");

		}

	}

	@RequestMapping("/getUser")
	public UserVo getUser(UserVo userVo) {

		return usersService.getUser(userVo);

	}
	
	
	@RequestMapping("/loginUser")
	public JSONObject loginUser(HttpSession session, UserVo userVo) {
		userVo.setUserPw(u.encript(userVo.getUserPw()));
		UserVo user = usersService.getUser(userVo);
		
		if(userVo.getUserPw().equals(user.getUserPw())){
			
			session.setAttribute("id", user.getUserId());
			return u.success("로그인 성공",null);
		}else {
			return u.fail("로그인 실패");
		}
		
		
		
		

	}

}
