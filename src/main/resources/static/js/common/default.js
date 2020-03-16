$(function(){
	
	
	
});

function reload(){
	location.reload();
}



function getView(url,divId,data,modalAble){
	

	
    $.ajax({
    	method:"POST",
        url: url,
        data:data,
        success: function(d) {
            $("#"+divId).html(d);
            if(modalAble){
            	   $("#"+divId).modal({
           	    	escapeClose: false,
           	    	clickClose: false,
           	    });
            }else{
            	$("#"+divId).show();
            }
         
            
        },error:function(a,b){
        	console.log(a);
        }
        });

	 /* $.get(url, function(html) {
		  $("#"+divId).html(html);
	    $("#"+divId).modal({
	    	escapeClose: false,
	    	clickClose: false,
	    	  showClose: false
	    });
	  });*/
}