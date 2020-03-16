package com.di.xyz.Request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

		@Autowired
		RequestMapper requestMapper;
		
		public int addTarget(TargetVo targetVo)throws Exception{
			return requestMapper.addTarget(targetVo);
		}
		public int getTargetMaxNum()throws Exception{
			return requestMapper.getTargetMaxNum();
		}
		
		public int editTarget(TargetVo targetVo)throws Exception{
			return requestMapper.editTarget(targetVo);
		}
		public int deleteTarget(TargetVo targetVo)throws Exception{
			return requestMapper.deleteTarget(targetVo);
		}
		
		public List<TargetVo> getTargetList(TargetVo targetVo)throws Exception{
			
			
			return requestMapper.getTargetList(targetVo);
		}
		public TargetVo getTarget(TargetVo targetVo)throws Exception{
			return requestMapper.getTarget(targetVo);
		}
		
		public int getTargetNameCount(TargetVo targetVo)throws Exception{
			return requestMapper.getTargetNameCount(targetVo);
		}
		

		
		public int getLogicMaxNum(LogicVo logicVo)throws Exception{
			return requestMapper.getLogicMaxNum(logicVo);
		}
		
		public int addLogic(LogicVo logicVo)throws Exception{
			
			return requestMapper.addLogic(logicVo);
		}
		
		public List<LogicVo> getLogicList(LogicVo logicVo)throws Exception{
			
			return requestMapper.getLogicList(logicVo);
		}
		
		public LogicVo getLogic(LogicVo logicVo)throws Exception{
			
			return requestMapper.getLogic(logicVo);
		}
		
		public int deleteLogic(LogicVo logicVo)throws Exception{
			return requestMapper.deleteLogic(logicVo);
		}
		
		public List<SourceConnectionVo> getSourceConnectionList(LogicVo logicVo)throws Exception{
			return requestMapper.getSourceConnectionList(logicVo);
		}
		
		public int getSourceConnectionCount(LogicVo logicVo)throws Exception{
			return requestMapper.getSourceConnectionCount(logicVo);
		}
		
		public int addSourceConnection(SourceConnectionVo sourceConnectionVo)throws Exception{
			
			return requestMapper.addSourceConnection(sourceConnectionVo);
		}
		
		public int deleteAllSourceConnection(LogicVo logicVo)throws Exception{
			
			return requestMapper.deleteAllSourceConnection(logicVo);
		}
		
		public List<TargetConnectionVo> getTargetConnectionList(LogicVo logicVo)throws Exception{
			return requestMapper.getTargetConnectionList(logicVo);
		}
		
		
		
		public int getTargetConnectionCount(LogicVo logicVo)throws Exception{
			return requestMapper.getTargetConnectionCount(logicVo);
		}
		
		public int addTargetConnection(TargetConnectionVo targetConnectionVo)throws Exception{
			
			return requestMapper.addTargetConnection(targetConnectionVo);
		}
		
		public int deleteAllTargetConnection(LogicVo logicVo)throws Exception{
			
			return requestMapper.deleteAllTargetConnection(logicVo);
		}
		
		public int addStep(StepVo stepVo)throws Exception{
			
			return requestMapper.addStep(stepVo);
		}
		
		public int deleteAllStep(LogicVo logicVo)throws Exception{
			
			return requestMapper.deleteAllStep(logicVo);
		}
		
		public List<StepVo> getStepList(LogicVo logicVo)throws Exception{
			
			return requestMapper.getStepList(logicVo);
		}
		
		public int addSourceColumn(SourceColumnVo sourceColumnVo)throws Exception{
			
			return requestMapper.addSourceColumn(sourceColumnVo);
		}
		
		public List<SourceColumnVo> getSourceColumnList(LogicVo logicVo)throws Exception{
			return requestMapper.getSourceColumnList(logicVo);
		}
		
		public int deleteAllSourceColumn(LogicVo logicVo)throws Exception{
			return requestMapper.deleteAllSourceColumn(logicVo);
		}
		
		public int addTargetColumn(TargetColumnVo targetColumnVo)throws Exception{
			
			return requestMapper.addTargetColumn(targetColumnVo);
		}
		
		public List<TargetColumnVo> getTargetColumnList(LogicVo logicVo)throws Exception{
			return requestMapper.getTargetColumnList(logicVo);
		}
		
		public int deleteAllTargetColumn(LogicVo logicVo)throws Exception{
			return requestMapper.deleteAllTargetColumn(logicVo);
		}
		
		public List<MappingVo> getMappingList(LogicVo logicVo)throws Exception{
			return requestMapper.getMappingList(logicVo);
		}
		
		public int addMapping(MappingVo MappingVo)throws Exception{
			return requestMapper.addMapping(MappingVo);
		}
		
		public int deleteAllMapping(LogicVo logicVo)throws Exception{
			return requestMapper.deleteAllMapping(logicVo);
		}
		
		public int getStepSize(LogicVo logicVo)throws Exception{
			return requestMapper.getStepSize(logicVo);
		}
		
		public int getMappingSize(LogicVo logicVo)throws Exception{
			return requestMapper.getMappingSize(logicVo);
		}
}
