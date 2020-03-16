package com.di.xyz.Common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CommonRestController {

	@Autowired
	CommonService commonService;
	
	@RequestMapping("init")
	public void init() {
		if(commonService.dropTable()==1) {
			log.info("DROP TABLES");
		}
		if(commonService.initTable()==1) {
			log.info("CREATE TABLES");
		}
		
	}
	


	public void createRequestTable() {

	}

	public void createSettingTable() {

	}

	public void createUserTable() {

	}

}
