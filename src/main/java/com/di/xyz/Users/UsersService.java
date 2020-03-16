package com.di.xyz.Users;

import java.util.List;

import org.apache.catalina.mbeans.UserMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.di.xyz.Users.UserVo;

@Service
public class UsersService {

	@Autowired
	private UsersMapper usersMapper;
	
	public int addUser(UserVo userVo) {
		
		return usersMapper.addUser(userVo);
	}
	
public int editUser(UserVo userVo) {
		
		return usersMapper.editUser(userVo);
	}
	
public UserVo getUser( UserVo userVo) {
		
		return usersMapper.getUser(userVo);
	}
	public List<UserVo> getUserList(UserVo userVo){
		return usersMapper.getUserList(userVo);
	}
}
