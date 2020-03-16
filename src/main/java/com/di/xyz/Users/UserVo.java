package com.di.xyz.Users;

import org.apache.ibatis.type.Alias;

import lombok.Data;


@Data
@Alias("userVo")
public class UserVo {
	
		private String userUid;
		private String userId;
		private String userName;
		private String userPw;
		private String userEmail;
		private String userRole;
		private String regDtm;
		private String regUser;
		private String udtDtm;
		
		
}
