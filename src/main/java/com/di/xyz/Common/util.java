package com.di.xyz.Common;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.json.simple.JSONObject;

public class util {
	
	public JSONObject success(String text,Object vo) {
		JSONObject jo = new JSONObject();
		jo.put("status", true);
		jo.put("text", text);
		jo.put("vo", vo);
		return jo;
	}
	
	public JSONObject fail(String text) {
		JSONObject jo = new JSONObject();
		jo.put("status", false);
		jo.put("text", text);
		return jo;
	}
	
	public String encript(String text) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(text.getBytes());
			;
			StringBuilder sb=new StringBuilder();
			for(byte b : md.digest()) {
				sb.append(String.format("%02x", b));
			}
			
			return sb.toString();

			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		return null;
		
	}
	
	public String getTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	public String getUuid() {
		
		return UUID.randomUUID().toString();
	}
}
