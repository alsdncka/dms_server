package com.di.xyz.Request;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.di.xyz.Settings.ConnectionVo;
import com.di.xyz.Settings.SettingsService;
import com.di.xyz.Users.UserVo;
import com.di.xyz.Users.UsersService;

@Controller
@RequestMapping("request")
public class RequestController {

	@Autowired
	RequestService requestService;
	
	@Autowired
	UsersService usersSerivce;
	
	@Autowired
	SettingsService settingsService;
	
	@RequestMapping("")
	public ModelAndView goRequest()throws Exception {
		ModelAndView mv= new ModelAndView("request/list");
		mv.addObject("pageC", "request");
		mv.addObject("pageD", "list");
		mv.addObject("targetList", requestService.getTargetList(new TargetVo()));
		return mv;
	}
	
	@RequestMapping("/sourceConnectionView")
	public ModelAndView sourceConnectionView(LogicVo logicVo)throws Exception {
		
		ModelAndView mv= new ModelAndView("request/sourceConnectionView");
		
		mv.addObject("pageC", "request");
		mv.addObject("pageD", "sourceConnectionView");
		mv.addObject("logicId", logicVo.getLogicId());
		
		List<SourceConnectionVo> sourceConnectionList =requestService.getSourceConnectionList(logicVo);
		
		for(SourceConnectionVo vo :sourceConnectionList) {
			ConnectionVo connVo = new ConnectionVo();
			connVo.setConnId(vo.getSourceConnId());
			connVo=settingsService.getConnection(connVo);
			vo.setConnVo(connVo);
		}
		
		mv.addObject("list", sourceConnectionList);
		
		return mv;
		
	}
	
	@RequestMapping("/targetConnectionView")
	public ModelAndView targetConnectionView(LogicVo logicVo)throws Exception {
		ModelAndView mv= new ModelAndView("request/targetConnectionView");
		mv.addObject("pageC", "request");
		mv.addObject("pageD", "targetConnectionView");
		mv.addObject("logicId", logicVo.getLogicId());
		
		List<TargetConnectionVo> targetConnectionList =requestService.getTargetConnectionList(logicVo);
		
		for(TargetConnectionVo vo :targetConnectionList) {
			ConnectionVo connVo = new ConnectionVo();
			connVo.setConnId(vo.getTargetConnId());
			connVo=settingsService.getConnection(connVo);
			vo.setConnVo(connVo);
		}
		
		mv.addObject("list", targetConnectionList);
		return mv;
	}
	
	@RequestMapping("/stepView")
	public ModelAndView stepView(LogicVo logicVo)throws Exception {
		ModelAndView mv= new ModelAndView("request/stepView");
		mv.addObject("pageC", "request");
		mv.addObject("pageD", "stepView");
		mv.addObject("logicId", logicVo.getLogicId());
		
		List<StepVo> stepList = requestService.getStepList(logicVo);
		for(StepVo vo : stepList) {
			StringBuffer sb=new StringBuffer();
			File f = new File(vo.getStepQueryFile());
			FileReader fr = new FileReader(f);
			int cur = 0;
	         while((cur = fr.read()) != -1){
	        	 sb.append((char)cur);
	         }
	         fr.close();
            vo.setQuery(sb.toString());
            fr.close();

		}
		
		mv.addObject("list", stepList);
		
		
		return mv;
	}
	
	@RequestMapping("/mappingView")
	public ModelAndView mappingView(LogicVo logicVo)throws Exception {
		ModelAndView mv= new ModelAndView("request/mappingView");
		mv.addObject("pageC", "request");
		mv.addObject("pageD", "mappingView");
		mv.addObject("logicId", logicVo.getLogicId());
		List<SourceColumnVo> scvList = requestService.getSourceColumnList(logicVo);
		List<TargetColumnVo> tcvList = requestService.getTargetColumnList(logicVo);
		List<MappingVo> mapList =requestService.getMappingList(logicVo);
		mv.addObject("SCL",scvList);
		mv.addObject("SCLSize",scvList.size());
		mv.addObject("TCL", tcvList);
		mv.addObject("TCLSize", tcvList.size());
		mv.addObject("map", mapList);
		
		mv.addObject("mapSize", mapList.size());
		return mv;
	}
	
	@RequestMapping("/addConnection")
	public ModelAndView addConnection(LogicVo logicVo)throws Exception {
		ModelAndView mv= new ModelAndView("request/addConnection");
		mv.addObject("pageC", "request");
		mv.addObject("pageD", "addConnection");
		return mv;
	}
	
	

	
}
