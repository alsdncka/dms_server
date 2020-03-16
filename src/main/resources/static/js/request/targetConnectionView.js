$(function(){
	init();
});

function init(){
	
	$(".tgt_conn_item").click(function(){
		if($(this).hasClass("active")){
			
			$(this).removeClass("active");
		}else{
			
			$(this).addClass("active");
		}
	});
	
	$("#addConn").click(function(){
		getView("request/addConnection","requestModal",null,true);
	});
	
	$("#delConn").click(function(){
	
		$("#tgt_conn_table tbody .active").remove();
		
	});
	
	$("#tgt_conn_save_bt").click(function(){
		saveTgtConn()
	});
}

function saveTgtConn(){
	var tr = $("#tgt_conn_table tbody tr");
	var dataA = new Array();
	
	$.each(tr,function(i,d){
		
		var connData={
				
				logicId:$("#targetConnectionView").attr("logic_id"),
				targetConnId:$(d).attr("tgt_conn_id"),
				targetConnNum:i,
				targetConnName:$(d).attr("tgt_conn_name")
				
		}
		dataA.push(connData);
		
	});
	
	var logicData={
			logicId:$("#targetConnectionView").attr("logic_id"),
			targetConnectionList:dataA
	}
	
	$.ajax({
		 method: "POST",
		url:"requestRest/addTargetConnection",
		dataType:"json", 
		contentType:"application/json",
		data:JSON.stringify(logicData),
		success:function(data){
			if(!data.status){
				alert(data.text);
			}else{
				var node = $.ui.fancytree.getTree("#tree").getNodeByKey("TGT_"+$("#targetConnectionView").attr("logic_id"));
				var able = true;
				node.removeChildren();
				$.each(dataA,function(i,d){
					able =false;
				var tgtConn ={
						"title":d.targetConnName,"type":"TGT_ITEM"
				}
				
				node.addChildren(tgtConn);
				});
				
				if(able){
					node.addClass("empty_folder");
				}else{
					node.removeClass("empty_folder");
				}
				
			}
		},error:function(err){
			console.log(err);
		}
	});
	checkLogic($("#targetConnectionView").attr("logic_id"));
}

function getAddAccess(){
	
	var rData={
			type:"SOURCE",
			status:true,
			text:""
	}
	
	var logicData={
			logicId:$("#targetConnectionView").attr("logic_id")
	}
	
	$.ajax({
		 method: "POST",
		url:"requestRest/getSourceConnectionCount",
		dataType:"json", 
		data:logicData,
		async:false,
		success:function(data){
			if(data.status){
				if(data.vo>1){
					rData.status = false;
					rData.text="다중 소스입니다.\r\n단일 타겟 선택만 가능합니다";
					
				}
			}
			
		},error:function(err){
			console.log(err);
		}
	});
	return rData;
	
}

function addConn(connId){
	
	if($(".tgt_conn_item[tgt_conn_id="+connId+"]").length>0){
		return false;
	}
	
	
	var data ={
			connId:connId
	}
	
	$.ajax({
		 method: "POST",
		url:"settingsRest/getConnection",
		dataType:"json", 
		data:data,
		success:function(data){
			createTargetConn(data);
			
		},error:function(err){
			console.log(err);
		}
	});
	
	
	
}

function createTargetConn(d){

	var tr =document.createElement("tr");
	$(tr).addClass("tgt_conn_item");
	
	$(tr).attr("tgt_conn_id",d.connId);
	$(tr).attr("tgt_conn_name",d.connName);
	var connName =document.createElement("td");
	$(connName).text(d.connName);
	var connDbType =document.createElement("td");
	$(connDbType).text(d.connDbType)
	var connString =document.createElement("td");
	$(connString).text(d.connString);
	var host =document.createElement("td");
	
	var connUser =document.createElement("td");
	$(connUser).text(d.connUser);
	$(tr).append(connName);
	$(tr).append(connDbType);
	$(tr).append(connString);
	$(tr).append(host);
	$(tr).append(connUser);
	
	$(tr).click(function(){
		if($(tr).hasClass("active")){
			
			$(tr).removeClass("active");
		}else{
			
			$(tr).addClass("active");
		}
		
		
	});
	
	$("#tgt_conn_table tbody").append(tr);
}

