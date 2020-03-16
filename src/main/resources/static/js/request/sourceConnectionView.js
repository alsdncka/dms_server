$(function(){
	init();
});

function init(){
	
	$(".src_conn_item").click(function(){
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
	
		$("#src_conn_table tbody .active").remove();
		
	});
	
	$("#src_conn_save_bt").click(function(){
		saveSrcConn()
	});
}

function saveSrcConn(){
	var tr = $("#src_conn_table tbody tr");
	var dataA = new Array();
	
	$.each(tr,function(i,d){
		
		
		
		var connData={
				
				logicId:$("#sourceConnectionView").attr("logic_id"),
				sourceConnId:$(d).attr("src_conn_id"),
				sourceConnNum:i,
				sourceConnName:$(d).attr("src_conn_name")
				
		}
		dataA.push(connData);
		
	});
	var logicData={
			logicId:$("#sourceConnectionView").attr("logic_id"),
			sourceConnectionList:dataA
	}
	$.ajax({
		 method: "POST",
		url:"requestRest/addSourceConnection",
		dataType:"json", 
		contentType:"application/json",
		data:JSON.stringify(logicData),
		success:function(data){
			
			if(!data.status){
				alert(data.text);
			}else{
				var node = $.ui.fancytree.getTree("#tree").getNodeByKey("SRC_"+$("#sourceConnectionView").attr("logic_id"));
				var able = true;
				node.removeChildren();
				$.each(dataA,function(i,d){
					able= false;
					var srcConn ={
							"title":d.sourceConnName,"type":"TGT_ITEM"
					}
					
					node.addChildren(srcConn);
					
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
	checkLogic($("#sourceConnectionView").attr("logic_id"));
}

function addConn(connId){
	if($(".src_conn_item[src_conn_id="+connId+"]").length>0){
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
			createSourceConn(data);
			
		},error:function(err){
			console.log(err);
		}
	});
	
	
	
}

function getAddAccess(){
	
	var rData={
			type:"TARGET",
			status:true,
			text:""
	}
	
	var logicData={
			logicId:$("#sourceConnectionView").attr("logic_id")
	}
	
	$.ajax({
		 method: "POST",
		url:"requestRest/getTargetConnectionCount",
		dataType:"json", 
		data:logicData,
		async:false,
		success:function(data){
			if(data.status){
				if(data.vo>1){
					rData.status = false;
					rData.text="다중 타겟입니다.\r\n단일 소스 선택만 가능합니다";
					
				}
			}
			
		},error:function(err){
			console.log(err);
		}
	});
	return rData;
}

function createSourceConn(d){

	var tr =document.createElement("tr");
	$(tr).addClass("src_conn_item");
	
	$(tr).attr("src_conn_id",d.connId);
	$(tr).attr("src_conn_name",d.connName);
	
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
	
	$("#src_conn_table tbody").append(tr);
}

