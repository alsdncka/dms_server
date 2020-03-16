package com.di.xyz.antlr;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.json.simple.JSONObject;

public class p2 {
	public static li2 lis = null;
	public static type_listener tl = null;

	public static List<String> parsing(String query) {

		
		if(query!="" && query!=null) {
			
	
		try {
		

			PlSqlLexer l = new PlSqlLexer(new ANTLRInputStream(query));
			CommonTokenStream ts = new CommonTokenStream(l);
			PlSqlParser p = new PlSqlParser(ts);
			ParseTree tree = p.sql_script();



			lis = new li2();
			ParseTreeWalker w = new ParseTreeWalker();
			w.walk(lis, tree);

		
			Map<Integer, String> colMap =lis.colMap;
			
		List<String> colList= new ArrayList<String>();
			
			colMap.keySet().forEach(v->{
				colList.add(colMap.get(v));
			});
			
			return colList;
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		}
		return null;
	}
	
	public static boolean getSelectAble(String query) {
		
		PlSqlLexer l = new PlSqlLexer(new ANTLRInputStream(query));
		CommonTokenStream ts = new CommonTokenStream(l);
		PlSqlParser p = new PlSqlParser(ts);
		ParseTree tree = p.sql_script();



		tl = new type_listener();
		ParseTreeWalker w = new ParseTreeWalker();
		w.walk(tl, tree);
		
		
		return tl.selectAble;
	}
	

}
