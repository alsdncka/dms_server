package com.di.xyz.Request;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.di.xyz.Settings.ConnectionVo;

import lombok.Data;


@Data
@Alias("SourceConnectionVo")
public class SourceConnectionVo {
	private String logicId;
	private String sourceConnId;
	private String sourceConnNum;
	private String regUsr;
	private String regDtm;
	
	private ConnectionVo connVo;
}
