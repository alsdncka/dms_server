var downType;
var upType;
var activeSource;
var activeTarget;

$(document).ready(function(){
	initEvent();
	clearLine();
	if($("#mappingView").attr("mapSize")==0){
		initLine();
	}else{
		repositionLine();
	}
	
});

function clearLine(){
	$.each($("line"),function(i,d){
		var sn = $(d).attr("s_col_name");
		var tn = $(d).attr("t_col_name");
		var sd = $(".colList[s_col_name="+sn+"]");
		var td = $(".colList[t_col_name="+tn+"]");
		
		if(sd.length==0){
			$(d).remove();
		}else if(td.length==0){
			$(d).remove();
		}
		
	});
}

function repositionLine(){
	
	$.each($("line"),function(i,d){
		
		var sn = $(d).attr("s_col_name");
		var tn = $(d).attr("t_col_name");
		
		var sd = $(".colList[s_col_name="+sn+"]");
		var td = $(".colList[t_col_name="+tn+"]");
		
		addLine(sd,td);
		
		
	});
	
	
}

function initEvent(){
	
	$(".colList").mousedown(function(e){
		downType=$(this).attr("type");
		
		if(downType=='source'){
			activeSource=$(this);
		}else{
			activeTarget=$(this);
		}
		
		$(this).addClass("ableCol");
	});
	
	$(".colList").mouseup(function(e){
		upType=$(this).attr("type");
		if(downType!=upType){
			
			if(upType=='source'){
				activeSource=$(this);
			}else{
				activeTarget=$(this);
			}
			addLine(activeSource,activeTarget);
			
		}
		
		
	});
	
	$("#mappingView").mouseup(function(e){
		
		$(".ableCol").removeClass("ableCol");
		
	});
	
	$("#mapping_save_bt").click(function(){
		
		saveMapping();
		
	});
	$('html').keyup(function(e){
		 if(e.keyCode == 46) {
		$(".line_active").remove();
		 }
	});
	
}






function saveMapping(){
	var dataA=new Array();
	$.each($("line"),function(i,d){
		
		var data ={
				logicId:$("#mappingView").attr("logic_id"),
				sourceColumnName:$(d).attr("s_col_name"),
				targetColumnName:$(d).attr("t_col_name")
		}
		dataA.push(data);
	});
	
	var logicData = {
			logicId:$("#mappingView").attr("logic_id"),
			mappingList:dataA
	}
	
	$.ajax({
		 method: "POST",
		url:"requestRest/addMapping",
		dataType:"json", 
		contentType:"application/json",
		data:JSON.stringify(logicData),
		success:function(data){
			if(!data.status){
				alert(data.text);
			}else{
				if(dataA.length>0){
					$.ui.fancytree.getTree("#tree").getNodeByKey("M_"+$("#mappingView").attr("logic_id")).removeClass("empty_folder");
				}else{
					$.ui.fancytree.getTree("#tree").getNodeByKey("M_"+$("#mappingView").attr("logic_id")).addClass("empty_folder");
				}
					
			}
			
		},error:function(err){
			console.log(err);
		}
	});
	checkLogic($("#mappingView").attr("logic_id"));
}

function initLine(){
	
	$.each($(".sColList"),function(i,sd){
		
		var sColName = $(sd).attr("s_col_name");
		
		
		$.each($(".tColList"),function(i,td){
			
			var tColName = $(td).attr("t_col_name");
			
			if(sColName==tColName){
				addLine($(sd),$(td));
			}
			
		});
		
	});
}

function addLine(sd,td){
	
	$("line[s_col_name="+sd.attr("s_col_name")+"]").remove();
	$("line[t_col_name="+td.attr("t_col_name")+"]").remove();
	
	var newLine = document.createElementNS('http://www.w3.org/2000/svg','line');
	var x1 = sd.position().left+sd.width();
	var y1 = sd.position().top+sd.height()/2;
	var x2 = td.position().left
	
	var y2 = td.position().top+td.height()/2;
	
	
    newLine.setAttribute('s_col_name',$(sd).attr("s_col_name"));
    newLine.setAttribute('t_col_name',$(td).attr("t_col_name"));
    newLine.setAttribute('x1',x1);
    newLine.setAttribute('y1',y1);
    newLine.setAttribute('x2',x2);
    newLine.setAttribute('y2',y2);
    newLine.setAttribute('stroke-width',5);
    newLine.setAttribute('stroke','black');
    
    $(newLine).click(function(){
    	if($(newLine).hasClass("line_active")){
    		$(newLine).removeClass("line_active");
    	}else{
    		$(newLine).addClass("line_active");
    	}
    	
    });
    
	$(".line_table").append(newLine);
	
	
}