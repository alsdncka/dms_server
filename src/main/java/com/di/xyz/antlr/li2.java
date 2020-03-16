package com.di.xyz.antlr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.di.xyz.antlr.PlSqlParser.AtomContext;
import com.di.xyz.antlr.PlSqlParser.Collection_assoc_expressionContext;
import com.di.xyz.antlr.PlSqlParser.ConstantContext;
import com.di.xyz.antlr.PlSqlParser.Function_argumentContext;
import com.di.xyz.antlr.PlSqlParser.General_element_partContext;
import com.di.xyz.antlr.PlSqlParser.General_table_refContext;
import com.di.xyz.antlr.PlSqlParser.Insert_into_clauseContext;
import com.di.xyz.antlr.PlSqlParser.Insert_statementContext;
import com.di.xyz.antlr.PlSqlParser.Order_by_clauseContext;
import com.di.xyz.antlr.PlSqlParser.Query_blockContext;
import com.di.xyz.antlr.PlSqlParser.Select_list_elementsContext;
import com.di.xyz.antlr.PlSqlParser.Single_table_insertContext;

public class li2 extends PlSqlBaseListener {

	List<String> colList=new ArrayList<String>();
	List<Integer> colSeqList = new ArrayList<Integer>(); 
	Map<Integer, String> colMap = new HashMap<Integer, String>();
	int colSeq =0;
	
	int idx=0;
	boolean able = false;
	@Override
	public void enterQuery_block(Query_blockContext ctx) {
		idx++;
		if(idx==1) {
			able=true;
		}else {
			able=false;
		}
	}

	@Override
	public void exitQuery_block(Query_blockContext ctx) {
		idx--;
		if(idx==1) {
			able=true;
		}else {
			able=false;
		}
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
	public void enterFrom_clause(PlSqlParser.From_clauseContext ctx) {

	}

	@Override
	public void exitFrom_clause(PlSqlParser.From_clauseContext ctx) {
	}

	@Override
	public void enterInsert_into_clause(Insert_into_clauseContext ctx) {

	}

	@Override
	public void exitInsert_into_clause(Insert_into_clauseContext ctx) {

	}

	@Override
	public void enterSelect_list_elements(Select_list_elementsContext ctx) {
	if(able) {
		int seq = colSeqList.get(colSeqList.size()-1);
		if(colMap.get(seq)==null) {
			
			colMap.put(seq, ctx.getText());
			
		}
	}
		
		
		
		
	}

	@Override
	public void exitSelect_list_elements(Select_list_elementsContext ctx) {
		
		
	}

	@Override
	public void enterSelected_element(PlSqlParser.Selected_elementContext ctx) {
		// blockMap.get(blockList.toString());
		if(able) {
		colSeq++;
		colSeqList.add(colSeq);	
		if(ctx.column_alias()!=null) {
			
		
		colMap.put(colSeq, ctx.column_alias().id().getText());
		
		}else {
			
		}
		
		}
		
		
	}

	@Override
	public void exitSelected_element(PlSqlParser.Selected_elementContext ctx) {
		if(able) {
		colSeqList.remove(colSeqList.size()-1);
		}
	}

	@Override
	public void enterGeneral_element_part(General_element_partContext ctx) {
	
		
	}

	@Override
	public void enterCollection_assoc_expression(Collection_assoc_expressionContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitGeneral_element_part(General_element_partContext ctx) {
	
	}

	@Override
	public void enterFunction_argument(Function_argumentContext ctx) {
		//System.out.println(ctx.getText());
	}

	@Override
	public void enterCollection_name(PlSqlParser.Collection_nameContext ctx) {
		
		
	}

	@Override
	public void enterColumn_alias(PlSqlParser.Column_aliasContext ctx) {
		

	}

	@Override
	public void enterTable_ref(PlSqlParser.Table_refContext ctx) {
		
	
		
	}

	@Override
	public void exitTable_ref(PlSqlParser.Table_refContext ctx) {
		
	}

	@Override
	public void enterTable_ref_aux(PlSqlParser.Table_ref_auxContext ctx) {
		
		
	}

	@Override
	public void enterTable_alias(PlSqlParser.Table_aliasContext ctx) {
		
		
	}

	@Override
	public void enterTableview_name(PlSqlParser.Tableview_nameContext ctx) {
		
	}

	@Override
	public void enterAtom(AtomContext ctx) {
		// TODO Auto-generated method stub
	
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



	
	
	

	
	
}
