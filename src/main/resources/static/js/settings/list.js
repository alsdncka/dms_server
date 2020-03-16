$(function(){
	clickEvent();
});

function clickEvent(){
	$("#addModalBt").click(function(){
		getView("settings/add","settingsModal",null,true);
		
	});
	
	$(".list_item").click(function(){
		data={
				connId:$(this).attr("id")
		}
		getView("settings/edit","settingsModal",data,true);
	});
	
}

