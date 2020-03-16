package com.di.xyz.Settings;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Mapper
public interface SettingsMapper {

	public List<ConnectionVo> getConnectionList(ConnectionVo connectionVo);
	public ConnectionVo getConnection(ConnectionVo connectionVo);
	public int addConnection(ConnectionVo connectionVo);
	public int editConnection(ConnectionVo connectionVo);
	

}
