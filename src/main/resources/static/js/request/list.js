var rClick=false;
var SOURCE = [
{"key":"root","title":"TARGET LIST","type":"ROOT","expanded": true, "folder": true}
		];
$(function(){
	initTree()
	clickEvent();
	
	
});



function initTree(){

	
	
	
		
	
	    // Initialize Fancytree
	    $("#tree").fancytree({
	    	source: SOURCE,
	      extensions: [ "glyph","edit","contextMenu"],
	      checkbox: false,
	      selectMode:2,
	      debugLevel: 0,
	      clickFolderMode: 4,
	      activate: function(event, data){
	    	 
	    	  var node = data.node;
	    	  var logicNode = node.parent
	    	 
	    	  if(logicNode.type=="LOGIC"){
	    		  $("#content_views").empty();
	    		  $(".content_view").attr("logic_key","");
	    		  var targetNode = logicNode.parent;
	    		  if(node.type=="SC"){
	    			  showSC(logicNode.key);
	    		  }else if(node.type=="TC"){
	    			  showTC(logicNode.key);
	    		  }else if(node.type=="STEP"){
	    			  showSTEP(logicNode.key);
	    		  }else if(node.type=="M"){
	    			  showM(logicNode.key);
	    		  }else if(node.type=="V"){
	    			  showV(logicNode.key);
	    		  }
	    		 
	    	  }
	          // A node was activated: display its title:
	         
	        },
	      beforeSelect: function(event, data){
	          // A node is about to be selected: prevent this for folders:
	        },
	      edit:{
	      edit:function(e,d){
	    	  console.log("RENAME TARGET");
	      },
	      save:function(e,d){
	    	console.log("save"); 
	    	var data={
	    			"targetId":d.node.key,
	    			"targetName":d.input.val()
	    	}
	    	
	    	$.ajax({
	   		 method: "POST",
	   		url:"requestRest/editTarget",
	   		dataType:"json", 
	   		data:data,
	   		async:false,
	   		success:function(data){
	   			if(data.status){
	   				d.node.setTitle(d.input.val());
	   			}else{
	   				alert(data.text);
	   				d.input.val(d.orgTitle);
	   				d.node.setTitle(d.orgTitle);
	   			}
	   			
	   		},error:function(err){
	   			console.log(err);
	   			return false;
	   		}
	   	});
	    return true;
	  
	      }
	      },
	      contextMenu:{
	    		menu: function(n){
	    			var menu={"none": { "name": "No event", "disabled": true }};
	    			n.setActive();
	    			if(n.type=="ROOT"){
	    				
	    				menu ={"newTarget": { "name": "NEW TARGET", "icon": "add" }};
	    				
	    			}else if(n.type=="TARGET"){
	    				menu ={
	    						"newLogic": { "name": "NEW LOGIC", "icon": "add" },
	    						"renameTarget": { "name": "RENAME", "icon": "edit" },
	    						"deleteTarget": { "name": "DELETE", "icon": "delete" },
	    						
	    				};
	    				
	    			}else if(n.type=="LOGIC"){
	    				var able = true;
	    				$.each(n.children,function(i,d){
	    					if(d.hasClass("empty_folder")){
	    						able=false;
	    					}
	    				});
	    				
	    				if(able){
	    					menu ={
	    		    				"deleteLogic": { "name": "DELETE", "icon": "delete" },
	    		    				"sep1": "---------",
	    							"request": { "name": "REQUEST", "icon": "add" },
	    		    				}
	    				}else{
	    					
	    					menu ={
	    		    				"deleteLogic": { "name": "DELETE", "icon": "delete" },
	    		    				"sep1": "---------",
	    							"request": { "name": "REQUEST", "icon": "add" ,"disabled": true },
	    		    				}
	    					
	    				}
	    				
	    			}
	    			else{
	    			}
	    			
	    			return menu;
	    		},
				actions: function(node, action, options) {
					if(action=="newTarget"){
						newTarget();
					}else if(action=="newLogic"){
						newLogic(node);
					}else if(action=="renameTarget"){
						renameTarget(node);
					}else if(action=="deleteTarget"){
						deleteTarget(node);
					}else if(action=="deleteLogic"){
						deleteLogic(node);
					}else if(action=="deleteLogic"){
						requestLogic(node);
					}
				}
	      },
	     
	      glyph: {
	        preset: "awesome5",
	        map: {}
	      }

	    });
	    getTargetList();
	  
	 
	
	function newTarget(){
		
		$.ajax({
			 method: "POST",
			url:"requestRest/addTarget",
			dataType:"json", 
			success:function(data){
				if(data.status){
					var target={"key":data.vo.targetId,"num":data.vo.targetNum,"type":"TARGET","title":data.vo.targetName,"folder": true};
					$.ui.fancytree.getTree("#tree").getNodeByKey("root").addChildren(target);
					$('.fancytree-node').off('contextmenu');
					
					$(".fancytree-node").contextmenu(function(e){
						rClick=true;
						$(e.target).trigger("click");
						
					});
					$.ui.fancytree.getTree("#tree").getNodeByKey(data.vo.targetId).editStart();
				}
					
				
			},error:function(err){
				console.log(err);
			}
		
			
		});
	}
		
		
	
	
	function newLogic(n){
		var key = n.key;
		var data ={
				targetId:key
				
		}
		
		$.ajax({
	   		 method: "POST",
	   		url:"requestRest/addLogic",
	   		dataType:"json", 
	   		data:data,
	   		async:false,
	   		success:function(data){
	   			if(data.status){
	   				
	   				var node = {"key":data.vo.logicId,"type":"LOGIC","title":data.vo.logicNum,"folder": true,"children":[
	   					{"key":"SRC_"+data.vo.logicId,"title":"SOURCE CONNECTION","type":"SC","folder": true},
	   					{"key":"TGT_"+data.vo.logicId,"title":"TARGET CONNECTION","type":"TC","folder": true},
	   					{"key":"S_"+data.vo.logicId,"title":"STEP","type":"STEP","folder": true},
	   					{"key":"M_"+data.vo.logicId,"title":"MAPPING","type":"M","folder": true},
	   					{"title":"VERSION","type":"V","folder": true}
	   				]}
	   				
	   				
	   				
	   				$.ui.fancytree.getTree("#tree").getNodeByKey(key).addChildren(node);
	   				
	   				$.ui.fancytree.getTree("#tree").getNodeByKey("SRC_"+data.vo.logicId).addClass("empty_folder");
	   				$.ui.fancytree.getTree("#tree").getNodeByKey("TGT_"+data.vo.logicId).addClass("empty_folder");
	   				$.ui.fancytree.getTree("#tree").getNodeByKey("S_"+data.vo.logicId).addClass("empty_folder");
	   				$.ui.fancytree.getTree("#tree").getNodeByKey("M_"+data.vo.logicId).addClass("empty_folder");
	   				
	   				$('.fancytree-node').off('contextmenu');
					
					$(".fancytree-node").contextmenu(function(e){
						rClick=true;
						$(e.target).trigger("click");
						
					});
	   			
	   			}else{
	   				alert("로직 추가 실패");
	   			}
	   			
	   		},error:function(err){
	   			console.log(err);
	   			return false;
	   		}
	   	});
	}
	
	
	function renameTarget(n){
		var key = n.key;
		$.ui.fancytree.getTree("#tree").getNodeByKey(key).editStart();
	
	};
	
	function deleteTarget(n){
		var key = n.key;
		var node =$.ui.fancytree.getTree("#tree").getNodeByKey(key);
		if(confirm(node.title + " 가 삭제됩니다.\r\n계속 하시겠습니까.")){
		
		
		var data ={
				"targetId":key
		}
		
		$.ajax({
	   		 method: "POST",
	   		url:"requestRest/deleteTarget",
	   		dataType:"json", 
	   		data:data,
	   		async:false,
	   		success:function(data){
	   			if(data.status){
	   				$.ui.fancytree.getTree("#tree").getNodeByKey(key).remove();
	   			}
	   			
	   		},error:function(err){
	   			console.log(err);
	   			return false;
	   		}
	   	});
		
		}
		
		
		
	};
	
	function deleteLogic(n){
		var tKey = n.parent.key
		var key = n.key;
		var node =$.ui.fancytree.getTree("#tree").getNodeByKey(key);
		if(confirm("로직 : "+node.title + " 가 삭제됩니다.\r\n계속 하시겠습니까.")){
		
		var data ={
				"targetId":tKey,
				"logicId":key
		}
		
		$.ajax({
	   		 method: "POST",
	   		url:"requestRest/deleteLogic",
	   		dataType:"json", 
	   		data:data,
	   		async:false,
	   		success:function(data){
	   			if(data.status){
	   				$.ui.fancytree.getTree("#tree").getNodeByKey(key).remove();
	   			}
	   			
	   		},error:function(err){
	   			console.log(err);
	   			return false;
	   		}
	   	});
		
		}	
	}
	
	function showSC(logicId){
		data ={
				logicId:logicId,
		}
		getView("request/sourceConnectionView","content_views",data,false);

		
	}
	
	function showTC(logicId){
		data ={
				logicId:logicId,
		}
		getView("request/targetConnectionView","content_views",data,false);
		
	}

	function showSTEP(logicId){
		
		data ={
				logicId:logicId,
		}
		
		getView("request/stepView","content_views",data,false);
		
	}
	function showM(logicId){
		data ={
				logicId:logicId,
		}
		getView("request/mappingView","content_views",data,false);
		
	}
	function showV(logicId){
		$("#versionView").attr("logic_key",logicId);
		$("#versionView").show();
		
	}
}

function getTargetList(){
	$.ajax({
		 method: "POST",
		url:"requestRest/getTargetList",
		dataType:"json", 
		async:false,
		success:function(data){
			$.each(data,function(i,d){
				var target={"key":d.targetId,"type":"TARGET","title":d.targetName,"folder": true};
				$.ui.fancytree.getTree("#tree").getNodeByKey("root").addChildren(target);
			
				$.each(d.logicList,function(ii,dd){
					var logic = {"key":dd.logicId,"type":"LOGIC","title":dd.logicNum,"folder": true,"children":[
						{"key":"SRC_"+dd.logicId,"title":"SOURCE CONNECTION","type":"SC","folder": true},
	   					{"key":"TGT_"+dd.logicId,"title":"TARGET CONNECTION","type":"TC","folder": true},
	   					{"key":"S_"+dd.logicId,"title":"STEP","type":"STEP","folder": true},
	   					{"key":"M_"+dd.logicId,"title":"MAPPING","type":"M","folder": true},
	   					{"title":"VERSION","type":"V","folder": true}
						
					]}
					
					$.ui.fancytree.getTree("#tree").getNodeByKey(d.targetId).addChildren(logic);
					
					if(dd.sourceConnectionList.length==0){
						$.ui.fancytree.getTree("#tree").getNodeByKey("SRC_"+dd.logicId).addClass("empty_folder");
					}
					
					$.each(dd.sourceConnectionList,function(si,sd){
						var sourConn ={
								"title":sd.connVo.connName,"type":"SRC_ITEM"
						}
						$.ui.fancytree.getTree("#tree").getNodeByKey("SRC_"+dd.logicId).addChildren(sourConn);
					})
					
					if(dd.targetConnectionList.length==0){
						$.ui.fancytree.getTree("#tree").getNodeByKey("TGT_"+dd.logicId).addClass("empty_folder")
					}
					
					$.each(dd.targetConnectionList,function(ti,td){
						var tgtConn ={
								"title":td.connVo.connName,"type":"TGT_ITEM"
						}
						$.ui.fancytree.getTree("#tree").getNodeByKey("TGT_"+dd.logicId).addChildren(tgtConn);
					})
					
					if(dd.stepSize==0){
						$.ui.fancytree.getTree("#tree").getNodeByKey("S_"+dd.logicId).addClass("empty_folder");
					}
					
					if(dd.mapSize==0){
						$.ui.fancytree.getTree("#tree").getNodeByKey("M_"+dd.logicId).addClass("empty_folder");
					}
					checkLogic(dd.logicId);
				});
				
			})
			
		},error:function(err){
			console.log(err);
		}
	});
	
}





function clickEvent(){
$(document).contextmenu(function(){
	return false;
});
	
}

function checkLogic(logicId){
	var nodes = $.ui.fancytree.getTree("#tree").getNodeByKey(logicId).children;
	
	$.each(nodes,function(i,d){
		if(d.hasClass("empty_folder")){
			$.ui.fancytree.getTree("#tree").getNodeByKey(logicId).addClass("empty_folder");
		}
	});
	
}


