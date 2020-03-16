package com.di.xyz.Users;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.di.xyz.Users.UserVo;

@Mapper
public interface UsersMapper {

	
	public int addUser(UserVo userVo);
	public UserVo getUser(UserVo userVo);
	public List<UserVo> getUserList(UserVo userVo);
	public int editUser(UserVo userVo);
}
