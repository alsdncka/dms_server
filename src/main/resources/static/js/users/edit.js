$(function(){
	
	clickEvent();
});

function clickEvent(){
	$("#userEditBt").click(function(){
		editUser();
	});

}


function editUser(){
	var userId = $("#userId");
	var userPw = $("#userPw");
	var userPwChk = $("#userPwChk");
	var userName = $("#userName");
	var userEmail = $("#userEmail");
	var userRole = $("#userRole");
	

	

	
	if(userName.val()==""){
		alert("이름을 입력해주세요");
		return false;
	}
	
	if(userEmail.val()==""){
		alert("이메일을 입력주세요");
		return false;
	}
	
	if(userRole.val()==""){
		alert("역활을 선택해주세요");
		return false;
	}
	
	$.ajax({
		 method: "POST",
		url:"usersRest/editUser",
		dataType:"json", 
		cache: false,
		data:$("#form").serialize(),
		success:function(data){
			if(data.status){
				$(".close-modal ").trigger("click");
				reload();
			}else{
				alert(data.text);
			}
			console.log(data);
		},error:function(err){
			console.log(err);
		}
	
		
	});
	
}

