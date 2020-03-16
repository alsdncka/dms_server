$(function(){
	
	clickEvent();
});

function clickEvent(){
	
	$("#connAddBt").click(function(){
		if(valiCheck()==0){
			return false;
		}
		
		addConnection();
	});
	
	$("#connTest").click(function(){
		console.log("A");
		if(valiCheck()==0){
			return false;
		}
		
		testConnection();
	});
	

}




function valiCheck(){
	var connName = $("#connName");
	var connType = $("#connType");
	var connDbType = $("#connDbType");
	var connString = $("#connString");
	var connCodePage = $("#connCodePage");
	var connUser = $("#connUser");
	var connPw = $("#connPw");
	
	if(connName.val()==""){
		alert("커넥션 이름을 입력해주세요");
		return 0;
	}
	if(connType.val()==""){
		alert("커넥션 타입을 입력해주세요");
		return 0;
	}
	if(connDbType.val()==""){
		alert("커넥션 DB 타입을 입력해주세요");
		return 0;
	}
	if(connString.val()==""){
		alert("커넥션 문자열을 입력해주세요");
		return 0;
	}
	if(connCodePage.val()==""){
		alert("커넥션 코드 페이지를 입력해주세요");
		return 0;
	}
	if(connUser.val()==""){
		alert("커넥션 계정을 입력해주세요");
		return 0;
	}
	if(connPw.val()==""){
		alert("커넥션 비밀번호를 입력해주세요");
		return 0;
	}
	return  1;
}


function addConnection(){
	$.ajax({
		 method: "POST",
		url:"settingsRest/addConnection",
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

function testConnection(){
	$.ajax({
		 method: "POST",
		url:"settingsRest/testConnection",
		dataType:"json", 
		cache: false,
		data:$("#form").serialize(),
		success:function(data){
			if(data.status){
				alert(data.text)
			}else{
				alert(data.text);
			}
			console.log(data);
		},error:function(err){
			console.log(err);
		}
	
		
	});
}
