package com.di.xyz.Request;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;


@Data
@Alias("targetVo")
public class TargetVo {
	
	private String targetId;
	private String targetNum;
	private String targetName;
	private List<LogicVo> logicList;
}
