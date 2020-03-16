package com.di.xyz.Request;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Mapper
public interface RequestMapper {
	
	
	public List<TargetVo> getTargetList(TargetVo targetVo)throws Exception;
	public TargetVo getTarget(TargetVo targetVo)throws Exception;
	public int getTargetNameCount(TargetVo targetVo)throws Exception;
	public int getTargetMaxNum()throws Exception;
	public int addTarget(TargetVo targetVo)throws Exception;
	public int editTarget(TargetVo targetVo)throws Exception;
	public int deleteTarget(TargetVo targetVo)throws Exception;

	public int getLogicMaxNum(LogicVo logicVo)throws Exception;
	public int addLogic(LogicVo logicVo)throws Exception;
	public List<LogicVo> getLogicList(LogicVo logicVo)throws Exception;
	public LogicVo getLogic(LogicVo logicVo)throws Exception;
	public int deleteLogic(LogicVo logicVo)throws Exception;
	
	public List<SourceConnectionVo> getSourceConnectionList(LogicVo logicVo)throws Exception;
	public int getSourceConnectionCount(LogicVo logicVo)throws Exception;
	public int addSourceConnection(SourceConnectionVo sourceConnectionVo)throws Exception;
	public int deleteAllSourceConnection(LogicVo logicVo)throws Exception;

	public List<TargetConnectionVo> getTargetConnectionList(LogicVo logicVo)throws Exception;
	public int getTargetConnectionCount(LogicVo logicVo)throws Exception;
	public int addTargetConnection(TargetConnectionVo targetConnectionVo)throws Exception;
	public int deleteAllTargetConnection(LogicVo logicVo)throws Exception;

	public int addStep(StepVo stepVo)throws Exception;
	public int deleteAllStep(LogicVo logicVo)throws Exception;
	public List<StepVo> getStepList(LogicVo logicVo)throws Exception;
	
	public int addSourceColumn(SourceColumnVo sourceColumnVo)throws Exception;
	public List<SourceColumnVo> getSourceColumnList(LogicVo logicVo)throws Exception;
	public int deleteAllSourceColumn(LogicVo logicVo)throws Exception;
	
	public int addTargetColumn(TargetColumnVo targetColumnVo)throws Exception;
	public List<TargetColumnVo> getTargetColumnList(LogicVo logicVo)throws Exception;
	public int deleteAllTargetColumn(LogicVo logicVo)throws Exception;
	
	
	public List<MappingVo> getMappingList(LogicVo logicVo)throws Exception;
	public int addMapping(MappingVo MappingVo)throws Exception;
	public int deleteAllMapping(LogicVo logicVo)throws Exception;
	
	
	public int getStepSize(LogicVo logicVo)throws Exception;
	public int getMappingSize(LogicVo logicVo)throws Exception;
	
}
