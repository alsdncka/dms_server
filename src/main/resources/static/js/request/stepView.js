var maxNum;

$(document).ready(function(){
	initEvent();
})

function initEvent(){
	
	
	activeBox($(".step_box").first());
		activeQuery($(".textarea").first());
		activeComment($(".commentarea").first());
	maxNum = $(".step_list").children().length;
	
	$("#addStep").click(function(){
		addStep();
	});
	
	$("#delStep").click(function(){
		
		
		$(".textarea[num="+$(".step_list .active").attr("num")+"]").remove();
		$(".commentarea[num="+$(".step_list .active").attr("num")+"]").remove();
		$(".step_list .active").remove();
		
		activeBox($(".step_box").first());
		activeQuery($(".textarea").first());
		activeComment($(".commentarea").first());
		
		resetNumber();
		
	});
	
	$("#step_save_bt").click(function(){
		saveStep();
	});
	
	$(".step_box").click(function(){
		var num =$(this).attr("num");
		var stepBox = $(".step_box[num="+num+"]");
		var ta = $(".textarea[num="+num+"]");
		var comm = $(".commentarea[num="+num+"]");
		activeBox(stepBox);
		activeQuery(ta);
		activeComment(comm);
		ta.focus();
	});
	
	
	$("#upStep").click(function(){
		var item = $(".step_list .active");
		var pItem =item.prev();
		
		if(pItem.index()>=0){
			pItem.before(item);
			resetNumber();
		}
	});
	
	$("#downStep").click(function(){
		var item = $(".step_list .active");
		var pItem =item.next();
		if(pItem[0]){
			pItem.after(item);
			resetNumber();
		}
		
	});
}
function resetNumber() {
	$.each($(".step_list").children(),function(i,d){
		$(d).children(".step_label").text("STEP_"+(i+1));
		
	});
	}
function addStep(){
	maxNum=maxNum+1
	var step = $(".step_list").children().length*1+1;
	var div = document.createElement("div");
	$(div).addClass("step_box");
	$(div).attr("num",maxNum);
	
	var stepLabel =document.createElement("div");
	$(stepLabel).addClass("step_label");
	$(stepLabel).text("STEP_"+step);
	
	var udBox = document.createElement("div");
	$(udBox).addClass("ud_box");
	
	var ubt = document.createElement("input");
	$(ubt).attr("type","button");
	$(ubt).addClass("mBt");
	$(ubt).attr("id","upStep");
	$(ubt).val("▲");
	
	var dbt = document.createElement("input");
	$(dbt).attr("type","button");
	$(dbt).addClass("mBt");
	$(dbt).attr("id","downStep");
	$(dbt).val("▼");
	
	$(udBox).append(ubt);
	$(udBox).append(dbt);
	$(div).append(stepLabel);
	$(div).append(udBox);
	
	
	
	
	var ta = document.createElement("textarea");
	$(ta).addClass("textarea");
	$(ta).attr("num",maxNum);
	
	var comm=document.createElement("textarea");
	$(comm).addClass("commentarea");
	$(comm).attr("num",maxNum);
	$(comm).attr("placeholder","최대 1000자");
	
	$(".step_list").append(div);
	$(".query_box").append(ta);
	$(".comment_box").append(comm);
	
	$(div).click(function(){
		activeBox($(div));
		activeQuery($(ta));
		activeComment($(comm));
		$(ta).focus();
	});
	
	activeBox($(div));
	activeQuery($(ta));
	activeComment($(comm));
	$(ta).focus();
}

function activeBox(ele){
	$(".step_list .active").removeClass("active");
	ele.addClass("active");
}

function activeQuery(ele){
	$(".textarea").hide()
	ele.show();
}
function activeComment(ele){
	$(".commentarea").hide();
	ele.show();
}

function saveStep(){
	var dataA = new Array();
	
	$.each($(".step_list").children(),function(i,d){
		
		var num = $(d).attr("num");
		
		var data = {
				logicId:$("#stepView").attr("logic_id"),
				stepNum:i+1,
				query:$(".textarea[num="+num+"]").val(),
				stepComment:$(".commentarea[num="+num+"]").val()
		}
		dataA.push(data);
		
	});
	
	var logicData = {
			logicId:$("#stepView").attr("logic_id"),
			stepList:dataA
	}
	
	$.ajax({
		 method: "POST",
		url:"requestRest/addStep",
		dataType:"json", 
		contentType:"application/json",
		data:JSON.stringify(logicData),
		success:function(data){
			if(!data.status){
				alert(data.text);
			}else{
				if(dataA.length>0){
					$.ui.fancytree.getTree("#tree").getNodeByKey("S_"+$("#stepView").attr("logic_id")).removeClass("empty_folder");
				}else{
					$.ui.fancytree.getTree("#tree").getNodeByKey("S_"+$("#stepView").attr("logic_id")).addClass("empty_folder");
				}
			}
			
		},error:function(err){
			console.log(err);
		}
	});
	
	checkLogic($("#stepView").attr("logic_id"));
}