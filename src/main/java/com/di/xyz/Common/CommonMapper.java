package com.di.xyz.Common;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.di.xyz.Users.UserVo;

@Mapper
public interface CommonMapper {

	
	public int createUsersTable()throws Exception;
	public int createConnectionTable()throws Exception;
	public int createRequestTable()throws Exception;
	public int createLogicTable()throws Exception;
	public int createTableTable()throws Exception;
	public int createColTable()throws Exception;
	public int createQueryTable()throws Exception;
	 
	public int dropUsersTable()throws Exception;
	public int dropConnectionTable() throws Exception;
	public int dropRequestTable()throws Exception;
	public int dropLogicTable()throws Exception;
	public int dropTableTable()throws Exception;
	public int dropColTable()throws Exception;
	public int dropQueryTable()throws Exception;
}
