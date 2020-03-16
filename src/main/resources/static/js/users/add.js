$(function(){
	
	clickEvent();
});

function clickEvent(){
	$("#userAddBt").click(function(){
		addUser();
	});

}


function addUser(){
	var userId = $("#userId");
	var userPw = $("#userPw");
	var userPwChk = $("#userPwChk");
	var userName = $("#userName");
	var userEmail = $("#userEmail");
	var userRole = $("#userRole");
	
	if(userId.val()==""){
		alert("아이디를 입력해주세요");
		return false;
	}
	
	if(userPw.val()==""){
		alert("비밀번호를 입력해주세요");
		return false
	}
	
	
	if(userPw.val()!=userPwChk.val()){
		alert("비밀번호가 일치하지 않습니다.");
		return false;
	}
	
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
		url:"usersRest/addUser",
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
	


