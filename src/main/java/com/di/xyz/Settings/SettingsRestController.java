package com.di.xyz.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.di.xyz.Common.util;

@RestController
@RequestMapping("/settingsRest")
public class SettingsRestController {

	@Autowired
	SettingsService settingsService;
	
	util u = new util();
	
	@RequestMapping("addConnection")
	public JSONObject addConnecton(HttpSession session,ConnectionVo connectionVo) {
		connectionVo.setRegDtm(u.getTime());
		connectionVo.setConnId(u.getUuid());
		connectionVo.setRegUser(session.getAttribute("id").toString());
		
		if(settingsService.addConnection(connectionVo)>0) {
			return u.success("add Connection 성공",null);
		}
		
		return u.fail("add Connection 실패");
		
	}
	
	@RequestMapping("editConnection")
	public JSONObject editConnecton(HttpSession session,ConnectionVo connectionVo) {
		connectionVo.setUdtDtm(u.getTime());
		
		System.out.println(connectionVo);
		if(settingsService.editConnection(connectionVo)>0) {
			return u.success("edit Connection 성공",null);
		}
		
		return u.fail("edit Connection 실패");
		
	}
	
	@RequestMapping("testConnection")
	public JSONObject testConnecton(ConnectionVo connectionVo) {
		System.setProperty("oracle.net.tns_admin",
		        "Z:\\app\\alsdncka\\virtual\\product\\12.2.0\\dbhome_1\\network\\admin");
		try {
			 String dbURL = "jdbc:oracle:thin:@"+connectionVo.getConnString();
			    Class.forName("oracle.jdbc.OracleDriver");
			    Connection conn = DriverManager.getConnection(dbURL, connectionVo.getConnUser(),
			            connectionVo.getConnPw());
			    PreparedStatement ps =conn.prepareStatement("SELECT 1 FROM dual");

			    ps.execute();
			    
			  
			    return u.success("Test Connection 성공",null);
		}catch (Exception e) {
			// TODO: handle exception
			return u.fail(e.getMessage());
		}
		
	}
	
	@RequestMapping("getConnection")
	public ConnectionVo getConnection(ConnectionVo connectionVo) {
		return settingsService.getConnection(connectionVo);
	}
	
	@RequestMapping("getConnectionList")
	public List<ConnectionVo> getConnectionList(ConnectionVo connectionVo) {
		return settingsService.getConnectionList(connectionVo);
	}
	
	
}
