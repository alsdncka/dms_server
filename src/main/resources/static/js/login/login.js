$(function(){
	
	clickEvent();
	
	
});

function clickEvent(){
	$("#loginBt").click(function(){
		var userId = $("#userId");
		var userPw = $("#userPw");
		
		if(userId.val() ==""){
			alert("아이디를 입력해주세요");
			return false;
		}
		
		if(userPw.val() == ""){
			alert("비밀번호를 입력해주세요");
			return false;
		}
		loginUser();
	});
}

function loginUser(){
	$.ajax({
		 method: "POST",
		url:"usersRest/loginUser",
		dataType:"json", 
		cache: false,
		data:$("#form").serialize(),
		success:function(data){
			if(data.status){
				$(".close-modal ").trigger("click");
				location.href="/dms/";
			}else{
				alert(data.text);
			}
			console.log(data);
		},error:function(err){
			console.log(err);
		}
	
		
	});
}