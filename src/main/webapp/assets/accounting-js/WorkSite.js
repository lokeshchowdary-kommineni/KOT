function onChangeBuyerName() {  
    var id=$("#buyerId").val();
   alert();
    $.ajax({  
     type : "POST",   
     url : "buyerOnChenage.html",   
     data : {id:id},
     dataType: "json",
     success : function(response) {
        if(response.workSite===null){
            $("#workSite").val('');
        }else{
            $("#workSite").val(response.workSite);
        }
     },  
     error : function(e) {   
      alert('Error: ' + e);   
     }  
    });  
}
