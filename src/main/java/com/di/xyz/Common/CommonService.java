package com.di.xyz.Common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class CommonService {

	@Autowired
	CommonMapper commonMapper;
	
	public int initTable() {
		try {
			commonMapper.createUsersTable();
			commonMapper.createConnectionTable();
			commonMapper.createRequestTable();
			commonMapper.createLogicTable();
			commonMapper.createColTable();
			commonMapper.createTableTable();
			commonMapper.createQueryTable();
			return 1;
		}catch (Exception e) {
			
			
			
			log.error(e.getMessage());
			// TODO: handle exception
			return 0;
		}
		
		
	}
	
	public int dropTable() {
		
		try {
			commonMapper.dropUsersTable();
		}catch (Exception ee) {
			// TODO: handle exception
			return 0;
		}
		
		try {
			commonMapper.dropConnectionTable();
		}catch (Exception ee) {
			// TODO: handle exception
			return 0;
		}
		try {
			commonMapper.dropRequestTable();
		}catch (Exception ee) {
			// TODO: handle exception
			return 0;
		}
		try {
			commonMapper.dropLogicTable();
		}catch (Exception ee) {
			// TODO: handle exception
			return 0;
		}
		
		try {
			commonMapper.dropColTable();
		}catch (Exception ee) {
			// TODO: handle exception
			return 0;
		}
		try {
			commonMapper.dropTableTable();
		}catch (Exception ee) {
			// TODO: handle exception
			return 0;
		}
		try {
			commonMapper.dropQueryTable();
		}catch (Exception ee) {
			// TODO: handle exception
			return 0;
		}
		
		return 1;
		
		
	}
	
	
	
	
}
