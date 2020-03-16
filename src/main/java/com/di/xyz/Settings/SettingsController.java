package com.di.xyz.Settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/settings")
public class SettingsController {

	@Autowired
	SettingsService settingsService;
	
	@RequestMapping("")
	public ModelAndView goList() {
		ModelAndView mv = new ModelAndView("settings/list");
		 mv.addObject("pageC", "settings");
		 mv.addObject("pageD", "list");
		 mv.addObject("list", settingsService.getConnectionList(new ConnectionVo()));
		return mv;
		
	}
	
	@RequestMapping("/add")
	public ModelAndView goAdd() {
		ModelAndView mv = new ModelAndView("settings/add");
		 mv.addObject("pageC", "settings");
		 mv.addObject("pageD", "add");
		return mv;
		
	}
	@RequestMapping("/edit")
	public ModelAndView goEdit(ConnectionVo connectionVo) {
		System.out.println(connectionVo);
		ModelAndView mv = new ModelAndView("settings/add");
		 mv.addObject("pageC", "settings");
		 mv.addObject("pageD", "edit");
		 mv.addObject("conn", settingsService.getConnection(connectionVo));
		return mv;
		
	}
	
}
