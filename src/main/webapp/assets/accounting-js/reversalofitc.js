function autoCompleteForPurchase(i) { 
    $("#reversalofitcsItemCode"+i+"").autocomplete({
    source: function(request, response) {
        $.ajax({
            url: "GetItemCodeForSales.html",
            dataType: "json",
            type: "GET",
            data: {
                term: request.term,
                tax: ""
            },
            success: function(data){
                response( $.map( data, function( item ) {
                   
                    return {
                        value: item.itemCode,   
                        label: item.itemCode,
                        itemCode:item.itemCode,
                        itemName:item.itemName,
                        unit:item.unit,
                        rate:item.rate,
                        ep:item.ep, 
                        currentStock:item.currentStock,
                        basicPrice:item.basicPrice,
                        taxSgst:item.taxSgst,
                        taxCgst:item.taxCgst,
                        taxIgst:item.taxIgst,
                        tr:item.tr,
                        tp:item.tp
                   
                }
  
                }));
                
             }
      
        })
    },
    select: function(event, ui) {     
        $('#reversalofitcsNameOfTheItem'+i+'').val(ui.item.itemName);
        $('#reversalofitcstaxCgst'+i+'').val(ui.item.taxCgst); 
        $('#reversalofitcstaxSgst'+i+'').val(ui.item.taxSgst)
        $('#reversalofitcstaxIgst'+i+'').val(ui.item.taxIgst);; 
               
         $(".txtMult input").keyup(multInputs);

       function multInputs() {
         
           var mult = 0;
           var amount=0;

           $("tr.txtMult").each(function () {
       
           var Rqty=parseInt($("#reversalofitcsreturnQty"+i+"").val());            
            var ep=parseFloat($("#reversalofitcsEp"+i+"").val());            
            var amount=Rqty*ep;
             
            $("#reversalofitcsAmount"+i+"").val(parseFloat(amount).toFixed(2));
            var taxCGST=parseFloat($("#reversalofitcstaxCgst"+i+"").val()); 
            var taxValueCGST=amount*taxCGST/100;
            var taxSGST=parseFloat($("#reversalofitcstaxSgst"+i+"").val()); 
            var taxValueSGST=amount*taxSGST/100;
            var taxIGST=parseFloat($("#reversalofitcstaxIgst"+i+"").val()); 
            var taxValueIGST=amount*taxIGST/100;

            $("#reversalofitcsCgst"+i+"").val(taxValueCGST);
            $("#reversalofitcsVat"+i+"").val(taxValueSGST);
            $("#reversalofitcsIgst"+i+"").val(taxValueIGST);
              
           });  
                } 
                 
    }
});

}

function onChangeRQty(i) {  
    
            var Rqty=parseInt($("#reversalofitcsreturnQty"+i+"").val()|| 0);            
            var ep=parseFloat($("#reversalofitcsEp"+i+"").val()|| 0);            
            var amount=Rqty*ep;
             
            $("#reversalofitcsAmount"+i+"").val(parseFloat(amount).toFixed(2));
           var taxCGST=parseFloat($("#reversalofitcstaxCgst"+i+"").val()); 
            var taxValueCGST=amount*taxCGST/100;
            var taxSGST=parseFloat($("#reversalofitcstaxSgst"+i+"").val()); 
            var taxValueSGST=amount*taxSGST/100;
            var taxIGST=parseFloat($("#reversalofitcstaxIgst"+i+"").val()); 
            var taxValueIGST=amount*taxIGST/100;
           

            $("#reversalofitcsCgst"+i+"").val(taxValueCGST.toFixed(2));
            $("#reversalofitcsVat"+i+"").val(taxValueSGST.toFixed(2));
            $("#reversalofitcsIgst"+i+"").val(taxValueIGST.toFixed(2));
}

function reversalCalculation(trLength) { 
    var totalAmount=0;
    var vat=0;
    var cgst=0;
    var igst=0;
    var AddLess=0;
    var RInvoiceAmount=0;
    var RAssessValue=0;
    
   
    
     
    for (var i = 0; i < trLength; i++) {     
          
         var amount=parseFloat($("#reversalofitcsAmount"+i+"").val());
        totalAmount=totalAmount+amount;

           var rCgst=parseFloat($("#reversalofitcsCgst"+i+"").val());
           cgst=cgst+rCgst;  
           var rVat=parseFloat($("#reversalofitcsVat"+i+"").val());
           vat=vat+rVat;
           var rIgst=parseFloat($("#reversalofitcsIgst"+i+"").val());
           igst=igst+rIgst;
          
           
          
          

    }
   var gross = totalAmount+cgst+vat+igst;
  
   
   var roundedvalue = Math.round(gross);
   
    
    $("#RCgst").val(cgst.toFixed(2));
    $("#RVat").val(vat.toFixed(2));
    $("#RIgst").val(igst.toFixed(2));
    $("#reversalofitcsAmount").val(totalAmount.toFixed(2));  
    
//    var RInvoiceAmount=parseFloat($("#RInvoiceAmount").val());
    
//    var RAssessValue=parseFloat($("#RAssessValue").val());
    
     AddLess=roundedvalue-gross;  
     
     $("#RAddLess ").val(AddLess.toFixed(2));
     
    var RInvoiceAmount=cgst+vat+igst+AddLess+totalAmount;
  //   alert( RInvoiceAmount);
     $("#RTInvoiceAmount ").val(RInvoiceAmount.toFixed(2));

   
}


function getUnit(trLength) { 
   
   for (var m = 0; m < trLength; m++) {
     var unit='#reversalofitcsUnit'+m+'';  
     var itemCode= $("#reversalofitcsItemCode"+m+"").val(); 
     var hiddenUnit=$("#hiddenUnit"+m+"").val();   

   
     if(itemCode.length>0){
         
        $.ajax({  
         type : "POST",   
         url : "getUnit.html",   
         data : {itemCode:itemCode},
         async:false,
         success : function(response) {
             $(unit).empty();                        
             $.each(response, function(index, row) {
                var text="";
                if(hiddenUnit==row.idUnit)
                { 
                  text="selected='selected'"; 
                 
                }   
                $(unit).append('<option value="'+row.idUnit+'" '+text+'>'+row.unitSymbol+'</option>');
                 
             });
         },  
         error : function(e) {  

          alert('Error: ' + e);   
         }  
        });  
    
    }
   
   } 
}