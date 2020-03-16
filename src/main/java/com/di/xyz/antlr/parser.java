package com.di.xyz.antlr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.json.simple.JSONObject;

public class parser {
	
	public static testLi lis = null;

	public static String firstKey = null ;
	public static List<String> sl =new ArrayList<String>();
	public static void main(String[] args) {

		try {
			PlSqlLexer l = new PlSqlLexer(new ANTLRFileStream("C:\\Users\\alsdncka\\Documents\\sample\\test.sql"));
			CommonTokenStream ts = new CommonTokenStream(l);
			PlSqlParser p = new PlSqlParser(ts);
			ParseTree tree = p.sql_script();
			//vv v = new vv();
			
			// System.out.println(tree.toStringTree(p));

			// whileChiled(tree,p);

			;
			//v.visit(tree);

			lis = new testLi();
			ParseTreeWalker w = new ParseTreeWalker();
			w.walk(lis, tree);
			
			System.out.println(lis.selectJo);
			 System.out.println(lis.tableJo);

			fatchMap(lis);
			test(lis);
			//start(lis);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	
	public static void fatchMap(testLi li) {
		
		JSONObject tableJo = li.tableJo;
		JSONObject selectJo = li.selectJo;
		
		
		selectJo.keySet().forEach(bs->{
			JSONObject selJo = (JSONObject)selectJo.get(bs);
			JSONObject tabJo = (JSONObject)tableJo.get(bs);
			selJo.keySet().forEach(ss->{
				JSONObject sel = (JSONObject)selJo.get(ss);
				if(sel.get("C_ID")!=null) {
					matchTable(sel,tabJo, 1);
				}else if(sel.get("C_CHILD")!=null) {
					
					//�꽌釉뚯옘由�
					
				}else {
					matchTable(sel,tabJo, 3);
					//�떒�씪 �뀒�씠釉�
					
				}
			});
		});
		
	}
	
	public static void matchTable(JSONObject selJo,JSONObject tabJo,int ca) {
		
		tabJo.keySet().forEach(tk->{
			
		JSONObject t = (JSONObject)tabJo.get(tk);
		if(ca==1) {
		if(t.get("T_AS").equals(selJo.get("C_ID"))) {
			t.keySet().forEach(ti->{
				
				selJo.put(ti, t.get(ti));
			});
		}
		}else if(ca==3) {
			
				t.keySet().forEach(ti->{
					
					selJo.put(ti, t.get(ti));
				});
			
		}
		});
		
	}
	
	public static void test(testLi li) {
			JSONObject selectJo = li.selectJo;

			JSONObject selJo = (JSONObject)selectJo.get("[1]");
			selJo.keySet().forEach(ss->{
				
			
				getNext("[1]",(JSONObject)selJo.get(ss));
			});
		
		
	}
	
	public static JSONObject getNext(String bSeq,JSONObject selJo) {
		
		
		JSONObject selectJo = lis.selectJo;
		JSONObject nextJo =new JSONObject();
		if(selJo.get("T_CHILD")!=null) {
			JSONObject bJo =(JSONObject)selectJo.get(selJo.get("T_CHILD"));
			bJo.keySet().forEach(bk->{
				JSONObject sJo = (JSONObject)bJo.get(bk);
				if(sJo.get("C_AS")!=null) {
					if(sJo.get("C_AS").equals(selJo.get("C_NAME"))) {
					
						saveData(selJo.get("T_CHILD").toString(),selJo, sJo);
						getNext(selJo.get("T_CHILD").toString(),sJo);
					}
				}else {
					if(sJo.get("C_NAME").equals(selJo.get("C_NAME"))) {
						saveData(selJo.get("T_CHILD").toString(),selJo, sJo);
						getNext(selJo.get("T_CHILD").toString(),sJo);
						
					}
				}
			});
			
		}
		
		else  if(selJo.get("C_CHILD")!=null) {
			JSONObject bJo =(JSONObject)selectJo.get(selJo.get("C_CHILD"));
			
			bJo.keySet().forEach(bk->{
				JSONObject sJo = (JSONObject)bJo.get(bk);
				if(sJo.get("C_AS")!=null) {
					if(sJo.get("C_AS").equals(selJo.get("C_NAME"))) {
						saveData(selJo.get("T_CHILD").toString(),selJo, sJo);
						getNext(selJo.get("T_CHILD").toString(),sJo);
					}
				}else {
					saveData(bSeq,selJo, sJo);
					getNext(bSeq.toString(),sJo);
					
				}
			});
			
		}else {
			
		}
		
		
		return null;
	}
	
	public static void saveData(String bSeq,JSONObject into,JSONObject from) {
		System.out.println("BLOCK: "+bSeq);
		System.out.println("into : "+into);
		System.out.println("from : "+from);
	}

}
