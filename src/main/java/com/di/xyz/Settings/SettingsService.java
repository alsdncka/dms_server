package com.di.xyz.Settings;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Service
public class SettingsService {

	@Autowired
	private SettingsMapper settingsMapper;
	
	public List<ConnectionVo> getConnectionList(ConnectionVo connectionVo){
		
		return settingsMapper.getConnectionList(connectionVo);
	}
	
	public ConnectionVo getConnection(ConnectionVo connectionVo) {
		
		return settingsMapper.getConnection(connectionVo);
	}
		

	
	public int addConnection(ConnectionVo connectionVo) {
		return settingsMapper.addConnection(connectionVo);
	}
	
	public int editConnection(ConnectionVo connectionVo) {
		return settingsMapper.editConnection(connectionVo);
	}
	
	
	
	
}
