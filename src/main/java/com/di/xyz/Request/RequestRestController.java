package com.di.xyz.Request;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.ReportAsSingleViolation;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.di.xyz.Common.util;
import com.di.xyz.Settings.ConnectionVo;
import com.di.xyz.Settings.SettingsService;
import com.di.xyz.antlr.p2;

@RestController
@RequestMapping("/requestRest")
public class RequestRestController {

	@Autowired
	RequestService requestService;
	
	@Autowired
	SettingsService settingsService;
	util u= new util();
	@RequestMapping("/parsingQuery")
	public List<String> parsingQuery(){
		p2 parser = new p2();
		return parser.parsing("");
	}
	
	@RequestMapping("/getTargetList")
	public List<TargetVo> getTargetList(TargetVo targetVo)throws Exception{
		List<TargetVo> targetList = requestService.getTargetList(targetVo);
		
		for(TargetVo tVo :targetList) {
			LogicVo lVo = new LogicVo();
			lVo.setTargetId(tVo.getTargetId());
			tVo.setLogicList(getLogicList(lVo));
		}
		
		return targetList;
	}
	
	@RequestMapping("/getTarget")
	public TargetVo getTarget(TargetVo targetVo)throws Exception{
		TargetVo targeVo = requestService.getTarget(targetVo);
		
			
		
		return targeVo;
	}
	
	@RequestMapping("/addTarget")
	public JSONObject addTarget(TargetVo targetVo)throws Exception {
		
		String max = String.valueOf(requestService.getTargetMaxNum());
		targetVo.setTargetId(u.getUuid());
		targetVo.setTargetNum(max);
		targetVo.setTargetName("NEW_TARGET_"+max);
		if(requestService.addTarget(targetVo)>0) {
			return u.success("add Target 성공",targetVo);
		};
		
		
		return u.fail("add Target 실패");
	}
	
	@RequestMapping("/editTarget")
	public JSONObject editTarget(TargetVo targetVo)throws Exception {
		
		if(requestService.getTargetNameCount(targetVo)>0) {
			return u.fail("중복된 타겟 이름입니다.");
		}
		
		if(requestService.editTarget(targetVo)>0) {
			return u.success("edit Target 성공",null);
		};
		
		
		return u.fail("edit Target 실패");
	}
	
	@RequestMapping("/deleteTarget")
	public JSONObject delTarget(TargetVo targetVo)throws Exception {
		if(requestService.deleteTarget(targetVo)>0) {
			return u.success("del Target 성공",null);
		};
		
		
		return u.fail("del Target 실패");
	}
	@RequestMapping("/deleteLogic")
	public JSONObject deleteLogic(LogicVo logicVo)throws Exception {
		
		if(requestService.deleteLogic(logicVo)>0) {
			return u.success("del Logic 성공",null);
		};
		
		
		return u.fail("del Logic 실패");
	}
	
	@RequestMapping("/addLogic")
	public JSONObject addLogic(LogicVo logicVo)throws Exception {
		logicVo.setLogicId(u.getUuid());
		logicVo.setLogicNum(String.valueOf(requestService.getLogicMaxNum(logicVo)));
		if(requestService.addLogic(logicVo)>0) {
			return u.success("add Logic 성공",logicVo);
		};
		
		
		return u.fail("add Logic 실패");
	}

	@RequestMapping("/getLogicList")
	public List<LogicVo> getLogicList(LogicVo logicVo)throws Exception {
		
		List<LogicVo> voList =requestService.getLogicList(logicVo);
		
		for(LogicVo vo :voList) {
			List<SourceConnectionVo> sourceConnectionList =requestService.getSourceConnectionList(vo);
			List<TargetConnectionVo> targetConnectionList =requestService.getTargetConnectionList(vo);
			
			for(SourceConnectionVo srcVo :sourceConnectionList) {
				ConnectionVo connVo = new ConnectionVo();
				connVo.setConnId(srcVo.getSourceConnId());
				connVo=settingsService.getConnection(connVo);
				srcVo.setConnVo(connVo);
			}
			
			for(TargetConnectionVo tgtVo :targetConnectionList) {
				ConnectionVo connVo = new ConnectionVo();
				connVo.setConnId(tgtVo.getTargetConnId());
				connVo=settingsService.getConnection(connVo);
				tgtVo.setConnVo(connVo);
			}
			
			vo.setSourceConnectionList(sourceConnectionList);
			vo.setTargetConnectionList(targetConnectionList);
			vo.setStepSize(requestService.getStepSize(vo));
			vo.setMapSize(requestService.getMappingSize(vo));
		}
		return voList;
	}
	@RequestMapping("/getLogic")
	public LogicVo getLogic(LogicVo logicVo)throws Exception {
		
		return requestService.getLogic(logicVo);
	}
	
	@RequestMapping("/getSourceConnectionCount")
	public JSONObject getSourceConnectionCount(LogicVo logicVo)throws Exception{
		
		System.out.println(logicVo);
		
		return u.success("source count", requestService.getSourceConnectionCount(logicVo));
		
	}
	
	@RequestMapping("/addSourceConnection")
	public JSONObject addSourceConnection(@RequestBody LogicVo logicVo)throws Exception {
	
		

			if(logicVo.getSourceConnectionList().size()>1&&requestService.getTargetConnectionCount(logicVo)>1) {
				return u.fail("다중 타겟 커넥션 입니다.\r\n 소스는 단일 선택 가능합니다.");
			}
			requestService.deleteAllSourceConnection(logicVo);
		
		
		for(SourceConnectionVo srcVo: logicVo.getSourceConnectionList()) {
			requestService.addSourceConnection(srcVo);
		}
		
		return u.success("성공", null);
	}
	
	@RequestMapping("/deleteSourceConnection")
	public JSONObject deleteSourceConnection(List<SourceConnectionVo> sourceConnectionList)throws Exception {
		
		for(SourceConnectionVo vo: sourceConnectionList) {
			LogicVo logicVo = new LogicVo();
			vo.setLogicId(sourceConnectionList.get(0).getLogicId());
			requestService.deleteAllSourceConnection(logicVo);
		}
		
		return u.success("성공", null);
	}
	
	@RequestMapping("/getTargetConnectionCount")
	public JSONObject getTargetConnectionCount(LogicVo logicVo)throws Exception{
		
		
		return u.success("target count", requestService.getTargetConnectionCount(logicVo));
		
	}
	
	@RequestMapping("/addTargetConnection")
	public JSONObject addTargetConnection(@RequestBody LogicVo logicVo)throws Exception {
		
		
		
		
			

			if(logicVo.getTargetConnectionList().size()>1&&requestService.getSourceConnectionCount(logicVo)>1) {
				return u.fail("다중 소스 커넥션 입니다.\r\n 타겟은 단일 선택 가능합니다.");
			}
			
			requestService.deleteAllTargetConnection(logicVo);
			
			
		
		
		
		for(TargetConnectionVo vo: logicVo.getTargetConnectionList()) {
			requestService.addTargetConnection(vo);
		}
		
		requestService.deleteAllTargetColumn(logicVo);
		if(logicVo.getTargetConnectionList().size()>0) {
			
			if(addTargetColumn(logicVo)!=null) {
				return u.fail("해당 커넥션에 테이블 정보가 없습니다.");
			}
			
		}
		
		return u.success("성공", null);
	}
	


	
	@RequestMapping("/deleteTargetConnection")
	public JSONObject deleteTargetConnection(List<TargetConnectionVo> targetConnectionList)throws Exception {
		
		for(TargetConnectionVo vo: targetConnectionList) {
			LogicVo logicVo = new LogicVo();
			vo.setLogicId(targetConnectionList.get(0).getLogicId());
			requestService.deleteAllTargetConnection(logicVo);
		}
		
		return u.success("성공", null);
	}
	
	@RequestMapping("/addStep")
	public JSONObject addStep(@RequestBody LogicVo logicVo)throws Exception {
		
		
	
		requestService.deleteAllStep(logicVo);
		
		String root= "C:\\Users\\alsdncka\\Desktop\\2di\\dms/dms_query/"+logicVo.getLogicId();
		File f= new File(root);
		
		if(!f.exists()) {
			f.mkdirs();
		}else {
			for(File ff: f.listFiles()) {
				ff.delete();
			}
		}
		
		
		
		for(StepVo vo: logicVo.getStepList()) {
			vo.setStepId(u.getUuid());
			try {
			String path=root+"/"+vo.getStepId()+".sql";
			File ff= new File(path);
			FileWriter fw = new FileWriter(ff,false);
			fw.write(vo.getQuery());
			fw.flush();
			fw.close();
			vo.setStepQueryFile(path);
			
			requestService.addStep(vo);
			
				
			}catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			System.out.println(p2.getSelectAble(vo.getQuery()));
			if(p2.getSelectAble(vo.getQuery())) {
				List<String> colList= p2.parsing(vo.getQuery());
				List<SourceColumnVo> sourceColList= new ArrayList<SourceColumnVo>();
				
				for(int i =0;i<colList.size();i++) {
					SourceColumnVo scVo =new SourceColumnVo();
					scVo.setLogicId(vo.getLogicId());
					scVo.setSourceColumnNum(String.valueOf(i));
					scVo.setSourceColumnName(colList.get(i).toUpperCase());
					sourceColList.add(scVo);
				}
				
				requestService.deleteAllSourceColumn(logicVo);
				addSourceColumn(sourceColList);
			}
			
			
		}
		
		return u.success("성공", null);
	}
	
	@RequestMapping("/addSourceColumn")
	public JSONObject addSourceColumn(List<SourceColumnVo> sourceColumnList)throws Exception {
		
		for(SourceColumnVo vo :sourceColumnList) {
			requestService.addSourceColumn(vo);
		}
		
		return u.success("add source column 성공", null);
	}
	
	@RequestMapping("/addTargetColumn")
	public JSONObject addTargetColumn(LogicVo logicVo)throws Exception {

		
		LogicVo lVo = getLogic(logicVo); 
		TargetVo targetVo = new TargetVo();
		targetVo.setTargetId(lVo.getTargetId());
		targetVo = getTarget(targetVo);
		
		
		
		ConnectionVo connVo = new ConnectionVo();
		connVo.setConnId(logicVo.getTargetConnectionList().get(0).getTargetConnId());
		connVo = settingsService.getConnection(connVo);
		
		List<TargetColumnVo> targetColList =setTargetColumn(logicVo.getLogicId(),connVo,targetVo.getTargetName());
		
		if(targetColList==null) {
			return 
		}
		
		
		for(TargetColumnVo vo:targetColList) {
			requestService.addTargetColumn(vo);
		}
		
		
		return null;
	}
	
	
	
	
	public List<TargetColumnVo> setTargetColumn(String logicId,ConnectionVo connectionVo,String targetTable) {
		System.setProperty("oracle.net.tns_admin",
		        "Z:\\app\\alsdncka\\virtual\\product\\12.2.0\\dbhome_1\\network\\admin");
		try {
			String query="SELECT \r\n" + 
					"COLUMN_NAME,\r\n" + 
					"COLUMN_ID\r\n" + 
					"FROM USER_TAB_COLS WHERE TABLE_NAME = '"+targetTable+"'\r\n" + 
					"ORDER BY COLUMN_ID";
			
			 String dbURL = "jdbc:oracle:thin:@"+connectionVo.getConnString();
			    Class.forName("oracle.jdbc.OracleDriver");
			    Connection conn = DriverManager.getConnection(dbURL, connectionVo.getConnUser(),
			            connectionVo.getConnPw());
			    PreparedStatement ps =conn.prepareStatement(query);
			    
			    ResultSet rs =ps.executeQuery();
			    List<TargetColumnVo> targetColList= new ArrayList<TargetColumnVo>();
			    while(rs.next()) {
			    	TargetColumnVo targetCol = new TargetColumnVo();
			    	targetCol.setLogicId(logicId);
			    	targetCol.setTargetColumnName(rs.getString("COLUMN_NAME"));
			    	targetCol.setTargetColumnNum(rs.getString("COLUMN_ID"));
			    	
			    	targetColList.add(targetCol);
			    }
			    
			    rs.close();
			    ps.close();
			    conn.close();
			    
			    return targetColList;
			  
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/addMapping")
	public JSONObject addMapping(@RequestBody LogicVo logicVo)throws Exception {
		
		requestService.deleteAllMapping(logicVo);
		
		for(MappingVo vo: logicVo.getMappingList()) {
			requestService.addMapping(vo);
		}
		
		
	return u.success("addMapping", null);
	}
	
}
