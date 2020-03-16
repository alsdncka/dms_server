var selectList = new Array();

$(function(){
	initEvent();
});



function initEvent(){
	
	$("#input_search").keydown(function(key) {

		if (key.keyCode == 13) {
		
			var connName = $("#input_search").val();
			
			var data={
					connName:connName
			}
			
			$.ajax({
				 method: "POST",
				url:"settingsRest/getConnectionList",
				dataType:"json", 
				data:data,
				async:false,
				success:function(data){
					$("#add_conn_table tbody").empty();
					$.each(data,function(i,d){
					createEle(d);
					})
					
				},error:function(err){
					console.log(err);
				}
			});
			

		}

		});

	$("#selectBt").click(function(){
		
		var len = selectList.length+$(".conn_table tbody tr").length;
		if(len>1){
			var rData =getAddAccess();
			if(!rData.status){
				alert(rData.text);
				return false;
			}
		}
		$.each(selectList,function(i,d){
		
			addConn(d);
			
		});
		
		$(".close-modal").trigger("click");
	});
	


	
}

function createEle(d){  
	
	var tr =document.createElement("tr");
	$(tr).addClass("conn_item");
	if($.inArray(d.connId,selectList)!= -1){
		$(tr).addClass("active");
	}
	$(tr).attr("conn_id",d.connId);
	
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
			var itemtoRemove = d.connId;
			selectList.splice($.inArray(itemtoRemove, selectList),1);
		}else{
			$(tr).addClass("active");
			selectList.push(d.connId);
		}
		
	});
	
	$("#add_conn_table tbody").append(tr);
}