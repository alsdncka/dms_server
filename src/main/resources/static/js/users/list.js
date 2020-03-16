$(function(){
	clickEvent();
});

function clickEvent(){
	$("#addModalBt").click(function(){
		getView("users/add","userModal",null,true);
		
	});
	
	$(".list_item").click(function(){
		data={
				userId:$(this).attr("id")
		}
		getView("users/edit","userModal",data,true);
	});
	
}