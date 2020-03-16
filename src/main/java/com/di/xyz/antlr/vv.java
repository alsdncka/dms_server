package com.di.xyz.antlr;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import com.di.xyz.antlr.PlSqlParser.BlockContext;
import com.di.xyz.antlr.PlSqlParser.Collection_nameContext;
import com.di.xyz.antlr.PlSqlParser.Column_aliasContext;
import com.di.xyz.antlr.PlSqlParser.Query_blockContext;
import com.di.xyz.antlr.PlSqlParser.Select_statementContext;
import com.di.xyz.antlr.PlSqlParser.Selected_elementContext;
import com.di.xyz.antlr.PlSqlParser.Table_refContext;

public class vv extends PlSqlBaseVisitor<String>{
	
	public static int blockSeq = 0;
	public static JSONObject colJo 	;

	

	@Override
	public String visitQuery_block(Query_blockContext ctx) {
		// TODO Auto-generated method stub
	//	System.out.println(ctx.getText());
		//System.out.println(ctx.selected_element().size());
		ctx.selected_element().forEach(sel->{
			System.out.println(sel.getText());
			visitSelected_element(sel);
		});
	
		return null;
	}
	
	@Override
	public String visitSelected_element(Selected_elementContext ctx) {
		// TODO Auto-generated method stub
	
			
	return null;
		
	}
	
	@Override
	public String visitCollection_name(Collection_nameContext ctx) {
		// TODO Auto-generated method stub
		if(ctx.id_expression()!=null) {
			return ctx.id_expression().getText();
		}else {
			return ctx.id().getText();
		}
		
	}
	
	
}
