package com.di.xyz.Settings;

import org.apache.ibatis.type.Alias;

import lombok.Data;


@Data
@Alias("connectionVo")
public class ConnectionVo {
	private String connId;
	private String connName;
	private String connType;
	private String connDbType;
	private String connString;
	private String connCodePage;
	private String connHost;
	private String connUser;
	private String connPw;
	private String regDtm;
	private String regUser;
	private String udtDtm;

		
}
