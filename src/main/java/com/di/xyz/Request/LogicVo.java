package com.di.xyz.Request;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;


@Data
@Alias("logicVo")
public class LogicVo {
	private String targetId;
	private String logicId;
	private String logicNum;
	private String logicVersion;
	private String logicName;
	private String regUsr;
	private String regDtm;
	private String viewType;
	private List<SourceConnectionVo> sourceConnectionList;
	private List<TargetConnectionVo> targetConnectionList;
	private List<StepVo> stepList;
	private List<SourceColumnVo> sourceColumnList;
	private List<TargetColumnVo> targetColumnList;
	private List<MappingVo> mappingList;
	private int stepSize;
	private int mapSize;
	
}
