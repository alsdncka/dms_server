package com.di.xyz.DashBoard;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashBoardController {
	
	
	@RequestMapping("/dashBoard")
	public ModelAndView goDashBoard(HttpSession session) {
		
		ModelAndView mv = new ModelAndView("/dashBoard/dashBoard");
		
		
		
		
		return mv;
	}

}
