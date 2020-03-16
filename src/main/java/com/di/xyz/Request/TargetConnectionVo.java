package com.di.xyz.Request;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.di.xyz.Settings.ConnectionVo;

import lombok.Data;


@Data
@Alias("TargetConnectionVo")
public class TargetConnectionVo {
	private String logicId;
	private String targetConnId;
	private String targetConnNum;
	private String regUsr;
	private String regDtm;
	
	private ConnectionVo connVo;
}
