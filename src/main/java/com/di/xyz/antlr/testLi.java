package com.di.xyz.antlr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.antlr.v4.parse.ANTLRParser.blockSet_return;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.stringtemplate.v4.compiler.STParser.mapExpr_return;

import com.di.xyz.antlr.PlSqlParser.Collection_assoc_expressionContext;
import com.di.xyz.antlr.PlSqlParser.Column_nameContext;
import com.di.xyz.antlr.PlSqlParser.ExpressionContext;
import com.di.xyz.antlr.PlSqlParser.Function_argumentContext;
import com.di.xyz.antlr.PlSqlParser.General_element_partContext;
import com.di.xyz.antlr.PlSqlParser.General_table_refContext;
import com.di.xyz.antlr.PlSqlParser.Insert_into_clauseContext;
import com.di.xyz.antlr.PlSqlParser.Insert_statementContext;
import com.di.xyz.antlr.PlSqlParser.Into_clauseContext;
import com.di.xyz.antlr.PlSqlParser.Order_by_clauseContext;
import com.di.xyz.antlr.PlSqlParser.Query_blockContext;
import com.di.xyz.antlr.PlSqlParser.Select_list_elementsContext;
import com.di.xyz.antlr.PlSqlParser.Single_table_insertContext;
import com.di.xyz.antlr.PlSqlParser.SubqueryContext;
import com.di.xyz.antlr.PlSqlParser.Subquery_basic_elementsContext;
import com.di.xyz.antlr.PlSqlParser.Table_ref_auxContext;

public class testLi extends PlSqlBaseListener{

	JSONObject blockJo =new JSONObject();
	JSONObject tempJo = new JSONObject();
	
	
	
	public static int blockSeq = 0;
	public static List<Integer> blockList = new ArrayList<Integer>();
	
	
	public static int selectSeq = 0;
	public static List<Integer> selectList = new ArrayList<Integer>();
	public static JSONObject selectJo = new JSONObject();
	
	public static int tableSeq = 0;
	public static List<Integer> tableList = new ArrayList<Integer>();
	public static JSONObject tableJo = new JSONObject();
	
	public static vv v = new vv();
	@Override
	public void enterSql_script(PlSqlParser.Sql_scriptContext ctx) {
		
		
	}
	
	@Override
	public void enterSingle_table_insert(Single_table_insertContext ctx) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void exitInsert_statement(Insert_statementContext ctx) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void enterGeneral_table_ref(General_table_refContext ctx) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public  void enterFrom_clause(PlSqlParser.From_clauseContext ctx) {
		
	}
	
	@Override
	public  void exitFrom_clause(PlSqlParser.From_clauseContext ctx) {
	}
	
	@Override
	public void enterInsert_into_clause(Insert_into_clauseContext ctx) {

	}
	
	@Override
	public void enterSelect_list_elements(Select_list_elementsContext ctx) {
		
		
		
	}
	
	@Override
	public void exitSelect_list_elements(Select_list_elementsContext ctx) {
		
		
	}
	
	@Override
	public void enterSelected_element(PlSqlParser.Selected_elementContext ctx) {
		//blockMap.get(blockList.toString());
		selectSeq++;
		selectList.add(selectSeq);
		JSONObject bJo = (JSONObject)selectJo.get(blockList.toString());
		bJo.put(selectList.toString(), new JSONObject());
		
	}
	
	
	@Override
	public void exitSelected_element(PlSqlParser.Selected_elementContext ctx) {
		
		selectList.remove(selectList.size()-1);
	}
	
	 @Override
	public void enterGeneral_element_part(General_element_partContext ctx) {
		 if(blockList.size()>0&& selectList.size()>0) {
				JSONObject bJo = (JSONObject)selectJo.get(blockList.toString());
				JSONObject sJo = (JSONObject)bJo.get(selectList.toString());
				
				sJo.put("EXP", ctx.getText());
				
		 }
	}
	 
	 @Override
	public void enterCollection_assoc_expression(Collection_assoc_expressionContext ctx) {
		// TODO Auto-generated method stub
		 if(blockList.size()>0&& selectList.size()>0) {
				JSONObject bJo = (JSONObject)selectJo.get(blockList.toString());
				JSONObject sJo = (JSONObject)bJo.get(selectList.toString());
				//
				if(ctx.expression().size()>0) {
					sJo.put("EXP", ctx.getText());
				}
				
		 }
	}

	 

	 @Override
		public void exitGeneral_element_part(General_element_partContext ctx) {
	 }
	 
	 @Override
	public void enterFunction_argument(Function_argumentContext ctx) {

	}
	 
	@Override
	public void enterCollection_name(PlSqlParser.Collection_nameContext ctx) {
		if(blockList.size()>0&& selectList.size()>0) {
			JSONObject bJo = (JSONObject)selectJo.get(blockList.toString());
			JSONObject sJo = (JSONObject)bJo.get(selectList.toString());
			
			if(ctx.id_expression()!=null) {
				sJo.put("C_ID", ctx.id().getText());
				sJo.put("C_NAME", ctx.id_expression().getText());
			}else {
				sJo.put("C_NAME", ctx.id().getText());
			}
		}
		
	
	}
	
	@Override
	public void enterColumn_alias(PlSqlParser.Column_aliasContext ctx) {
		
		if(blockList.size()>0&& selectList.size()>0) {
			JSONObject bJo = (JSONObject)selectJo.get(blockList.toString());
			JSONObject sJo = (JSONObject)bJo.get(selectList.toString());
			
				sJo.put("C_AS", ctx.id().getText());
	
		}

	
	}
	
	

	
	@Override
	public void enterTable_ref(PlSqlParser.Table_refContext ctx) {
		
		tableSeq++;
		tableList.add(tableSeq);
		JSONObject bJo = (JSONObject)tableJo.get(blockList.toString());
		bJo.put(tableList.toString(), new JSONObject());
		
	
	}
	
	@Override
	public void exitTable_ref(PlSqlParser.Table_refContext ctx) {

		tableList.remove(tableList.size()-1);
		
	}
	

	
	@Override
	public void enterTable_ref_aux(PlSqlParser.Table_ref_auxContext ctx) {
	
		
		
		
	}
	
	@Override
	public void enterQuery_block(Query_blockContext ctx) {
		boolean subTable = false;
		boolean subCol = false;
		JSONObject bJo = new JSONObject();
		JSONObject tJo = new JSONObject();
		JSONObject cJo = new JSONObject();
		if(tableList.size()>0) {
			subTable = true;
			 bJo = (JSONObject)tableJo.get(blockList.toString());
			 tJo = (JSONObject)bJo.get(tableList.toString());
		}
		
		if(selectList.size()>0) {
			subCol =true;
			 bJo = (JSONObject)selectJo.get(blockList.toString());
			 cJo = (JSONObject)bJo.get(selectList.toString());
			//System.out.println(blockList.toString());
		}
		
		
		blockSeq++;
		
		blockList.add(blockSeq);
		selectJo.put(blockList.toString(), new JSONObject());
		tableJo.put(blockList.toString(), new JSONObject());
	
		if(subTable) {
			
			tJo.put("T_CHILD",blockList.toString() );
		}
		
		if(subCol) {
			//System.out.println(selectList.toString());
			//System.out.println(blockList.toString());
			cJo.put("C_CHILD", blockList.toString());
		}
		
	
	
	}
	
	
	@Override
	public void exitQuery_block(Query_blockContext ctx) {
		
		blockList.remove(blockList.size()-1);
		
	}
	
	@Override
	public void enterTable_alias(PlSqlParser.Table_aliasContext ctx) {
		
		JSONObject bJo = (JSONObject)tableJo.get(blockList.toString());
		JSONObject tJo = (JSONObject)bJo.get(tableList.toString());
		tJo.put("T_AS", ctx.getText());
		
	}
	
	@Override
	public void enterTableview_name(PlSqlParser.Tableview_nameContext ctx) {
		
		if(blockList.size()>0&&tableList.size()>0) {
			JSONObject bJo = (JSONObject)tableJo.get(blockList.toString());
			JSONObject tJo = (JSONObject)bJo.get(tableList.toString());
			if(ctx.id_expression()!=null) {
				tJo.put("T_ID", ctx.id().getText());
				tJo.put("T_NAME", ctx.id_expression().getText());
			}else {
				tJo.put("T_NAME", ctx.id().getText());
			}
			
		}
	
		
	}
	
	
	

	
	
	@Override
	public void enterGroup_by_clause(PlSqlParser.Group_by_clauseContext ctx) {
	
		
	}
	
	@Override
	public void exitGroup_by_clause(PlSqlParser.Group_by_clauseContext ctx) {
	
		
	}
	
	@Override
	public void enterOrder_by_clause(Order_by_clauseContext ctx) {
		
	}
	
	@Override
	public void exitOrder_by_clause(Order_by_clauseContext ctx) {
		
	}
	
	@Override
	public void enterSubquery(PlSqlParser.SubqueryContext ctx) {
	
	}
	
	@Override
	public void exitSubquery(PlSqlParser.SubqueryContext ctx) {
		
	}
	
	
	public static void getMap() {
		
	}



}
