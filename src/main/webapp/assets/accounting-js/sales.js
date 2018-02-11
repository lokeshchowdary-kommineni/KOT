
//// ***** For deleting single row in table ***** -- Start /////
function myFunction(r,totalRow,index) {  
   var tableSize = $("#Mytable td").closest("tr").length;
   
  if(tableSize > 1){
      
       var i = r.parentNode.parentNode.rowIndex;
        salesCalcForDeleteRow(totalRow,index);
    document.getElementById("Mytable").deleteRow(i);
   //salesCalculation(i-2);
   
    
  }
  
}

function myFunctionPre(r,totalrows,index) {  
   var tableSize = $("#Mytable td").closest("tr").length;
   
  if(tableSize > 1){
     
       var i = r.parentNode.parentNode.rowIndex;
       salesCalculationAfterDelete(totalrows,index);
    document.getElementById("Mytable").deleteRow(i);
  //  salesCalculation(i-2);
    
  }
  
}
//// ***** For deleting single row in table ***** -- End /////


function addRowforSales() {
     alert('seju');
    $.ajax({  
     type : "POST",   
     url : "sales_addrow.html",   
     data : $("#kotForm").serialize(),
     dataType: 'html',
     success : function(response) {
     $('#salesFormDiv').empty().append(response);
     onChangeCategory();
     onChangeMode();
     },  
     error : function(e) {  
      
      alert('Error: ' + e);   
     }  
    });  
}
   
   
function addRowforSalesEstimate() {  
    $.ajax({  
     type : "POST",   
     url : "SE_Addrow.html",   
     data : $("#saleEstimateForm").serialize(),
     success : function(response) {
     $('#salesEstimateFormDiv').empty().append(response);
       onChangeCategory();
     
     },  
     error : function(e) {  
      
      alert('Error: ' + e);   
     }  
    });  
   } 
   
   function addRowforSalesReturn() {  
    $.ajax({  
     type : "POST",   
     url : "SE_Addrow.html",   
     data : $("#saleEstimateForm").serialize(),
     success : function(response) {
     $('#salesEstimateFormDiv').empty().append(response);
    
     },  
     error : function(e) {  
      
      alert('Error: ' + e);   
     }  
    });  
   } 
   
function salesCategoryCheck(i){
    var category=$("#category").val();
    var taxPercentage=$("#taxPercentage").val();
    if(category.length===0){
        alert("Please Select Category");
    }
    else{
        if(category==='Consumers(B2C)'){
            var pos=$("#pos").val();
            if(pos=='' || pos.length==0)
            {
              $("#pos").focus(); 
              alert("Please Select Pos");    
            }
            autoCompleteForSales(i);
            autoCompleteForSalesItemName(i)
        }
        if(category==='GST Dealers(B2B)'){
            var tinNo=$("#tinNo").val();
            if(tinNo=='' || tinNo.length==0)
            {
              $("#tinNo").focus(); 
              alert("Please Enter GSTIN");    
            }
            autoCompleteForSales(i);
            autoCompleteForSalesItemName(i)
         
        }
    }
}
   
   // Autocomplete for Itemcode -- StartCode(Abi) //
   function autoCompleteForSales(i) {  
    var category=$("#category").val();
    var margin=$("#salesMargin"+i+"").val();
    var taxPercentage=$("#taxPercentage").val();
     var columnsItems = [{name: 'Code', minWidth: '20%', valueField: 'label'},{name:'Name', minWidth: '60%', valueField:'itemName'}, {name: 'Stock', minWidth: '10%', valueField: 'currentStock'},{name: 'Tax', minWidth: '10%', valueField: 'tr'}];
       // Skip previous selected items start
       var previous_Item_code = new Array();
        $('input[id^="salesItemCode"]').each(function(){
            previous_Item_code.push("'"+$(this).val()+"'");
        });                   
       var selected_items=previous_Item_code.join(',');
       selected_items = selected_items.replace(/,\s*$/, "");     
        // Skip previous selected items end
    $("#salesItemCode"+i+"").mcautocomplete({
        
          showHeader: true,
  columns: columnsItems,
  minLength:0,
  autoFocus: true,
  
    source: function(request, response) {
        $.ajax({
            url: "GetItemCodeForSales.html",
            dataType: "json",
            type: "GET",
            data: {
                term: request.term,
                tax: "",
                items:selected_items
            },
            success: function(data){
                response( $.map( data, function( item ) {
                  
                    return {
                        value: item.itemCode,   
                        label: item.itemCode,
                        itemCode:item.itemCode,
                        itemName:item.itemName,
                        unit:item.unit,
                        altUnit:item.altUnit,
                        rate:item.rate,
                        currentStock:item.currentStock,
                        vap:item.vap,
                        vapAlt:item.vapAlt,
                        vbp:item.vbp,
                        vbpAlt:item.vbpAlt,
                        cap:item.cap,
                        capAlt:item.capAlt,
                        cbp:item.cbp,
                        cbpAlt:item.cbpAlt,
                        basicPrice:item.basicPrice,
                        tr:item.tr,
                        mc:item.mc,
                        mca:item.mca,
                        taxSgst:item.taxSgst,
                        taxCgst:item.taxCgst,
                        taxIgst:item.taxIgst,
                        cp:item.cp,
                        whereTotalunit:item.totalUnit
                    };
  
                }));
     
             }
      
        });
    },
    select: function(event, ui) {   
        $('#salesItemName'+i+'').val(ui.item.itemName);
        $('#salesUnit'+i+'').val(ui.item.unit);
        $('#salesQuantity'+i+'').val(ui.item.currentStock);
        $('#salesCpcpV'+i+'').val(ui.item.cp);        
        $('#salesTax'+i+'').val(ui.item.tr);
        var isIgst=$('#IgstCalc').val();
        if(isIgst=='yes')
        {
        $('#salestaxCgst'+i+'').val(ui.item.taxCgst);
        $('#salestaxSgst'+i+'').val(ui.item.taxSgst);
        $('#salestaxIgst'+i+'').val(0.00);     
       
        }
        else
        {
        $('#salestaxCgst'+i+'').val(0.00);
        $('#salestaxSgst'+i+'').val(0.00);
        $('#salestaxIgst'+i+'').val(ui.item.taxIgst);  
        }    
       
        $('#totalUnit'+i+'').val(ui.item.whereTotalunit);
        // Get Value of IGST is yes or no
       
            //calc for CP+V
            var cpp=parseFloat($("#salesCpcpV"+i+"").val());          
            var taxx=parseFloat($("#salesTax"+i+"").val());      
            var taxxValue=cpp*taxx/100;      
            var cpcpV=cpp+taxxValue;             
            var tax=parseFloat($("#salesTax"+i+"").val());
          
        if(margin==='A'){
            if(category==='GST Dealers(B2B)'){              
                    $('#salesRate'+i+'').val(ui.item.vap);
                    $('#salesCpcpV'+i+'').val(cpp);
                }
            if(category==='Consumers(B2C)'){
                $('#salesRate'+i+'').val(ui.item.cap);
                $('#salesCpcpV'+i+'').val(cpcpV);
               
            }
        }
        if(margin==='B'){
            
            if(category==='Consumers(B2C)'){            
                $('#salesRate'+i+'').val(ui.item.cbp);
                $('#salesCpcpV'+i+'').val(cpcpV);
            }
            if(category==='GST Dealers(B2B)'){
                $('#salesRate'+i+'').val(ui.item.vbp);
                $('#salesCpcpV'+i+'').val(cpp);
                 
            }
        }
         
            $("#salesDiscount"+i+"").val('0');
            $("#salesCgst"+i+"").val('');
            $("#salesVat"+i+"").val('');
       
            var qty=parseFloat($("#salesQuantity"+i+"").val());
            var rate=parseFloat($("#salesRate"+i+"").val());
            var amount=qty*rate;
            $("#salesAmount"+i+"").val(amount.toFixed(2));
            if(category==='Consumers(B2C)'){                 
               
               var taxamount=(amount*tax)/(100+tax)
               var taxCGST=parseFloat($("#salestaxCgst"+i+"").val());
               var taxSGST=parseFloat($("#salestaxSgst"+i+"").val());
               var taxIGST=parseFloat($("#salestaxIgst"+i+"").val());
               var  taxValueCGST=(taxamount*taxCGST)/(tax);
               var  taxValueSGST=(taxamount*taxSGST)/(tax);
               var  taxValueIGST=(taxamount*taxIGST)/(tax);
               if(isNaN(taxValueCGST))
               {
                   $("#salesCgst"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesCgst"+i+"").val(taxValueCGST.toFixed(2));  
               } 
               if(isNaN(taxValueSGST))
               {
                   $("#salesVat"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesVat"+i+"").val(taxValueSGST.toFixed(2));  
               } 
               if(isNaN(taxValueIGST))
               {
                   $("#salesIgst"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesIgst"+i+"").val(taxValueIGST.toFixed(2));  
               }    
            
             
            var cpGST=parseFloat($("#salesCpcpV"+i+"").val());
            var cp=(cpGST*100)/(100+tax);            
            $("#salesAmountCpCpv"+i+"").val(qty*cp);     
             //With out Tax Clac
            var WithousalesCgstt=parseFloat($("#salesCgst"+i+"").val());
            var WithousalesVat=parseFloat($("#salesVat"+i+"").val());  
            var WithousalesIgst=parseFloat($("#salesIgst"+i+"").val());  
            var amountWithoutTax=amount-(WithousalesCgstt+WithousalesVat+WithousalesIgst);
            $("#amountWithoutTax"+i+"").val(amountWithoutTax);  
            
               
            }
            else
            {
               var taxCGST=parseFloat($("#salestaxCgst"+i+"").val());
               var taxSGST=parseFloat($("#salestaxSgst"+i+"").val());  
               var taxIGST=parseFloat($("#salestaxIgst"+i+"").val());
               var  taxValueCGST=(amount*taxCGST/100);
               var  taxValueSGST=(amount*taxSGST/100);
               var  taxValueIGST=(amount*taxIGST)/100;
               if(isNaN(taxValueCGST))
               {
                   $("#salesCgst"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesCgst"+i+"").val(taxValueCGST.toFixed(2));  
               } 
               if(isNaN(taxValueSGST))
               {
                   $("#salesVat"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesVat"+i+"").val(taxValueSGST.toFixed(2));  
               } 
               if(isNaN(taxValueIGST))
               {
                   $("#salesIgst"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesIgst"+i+"").val(taxValueIGST.toFixed(2));  
               }  
                // if same tax item added open add or less if not make it as read only
                   var taxArray = new Array();
                    $('input[id^="salesTax"]').each(function(){
                        taxArray.push($(this).val());
                    });
                   // alert(myFunc(taxArray));
                    if(myFunc(taxArray)===true)
                    {  
                        
                     $('#salesAddOrLess').attr('readonly', false);
                    }
                    else
                    {
                      $('#salesAddOrLess').attr('readonly', true);   
                    }    
            var cp=parseFloat($("#salesCpcpV"+i+"").val());
            $("#salesAmountCpCpv"+i+"").val(qty*cp);   
            //With out Tax Clac
            var WithousalesCgstt=parseFloat($("#salesCgst"+i+"").val());
            var WithousalesVat=parseFloat($("#salesVat"+i+"").val());  
            var WithousalesIgst=parseFloat($("#salesIgst"+i+"").val());  
            var amountWithoutTax=amount;
            $("#amountWithoutTax"+i+"").val(amountWithoutTax);  
                
            }
            
              
            
              
            var mc=ui.item.mc;
            var mca=ui.item.mca;
            if(mc!==null && mc!==0){
                $("#salesMcMca"+i+"").val(mc);
            }
            if(mca!==null && mca!==0){
                $("#salesMcMca"+i+"").val(mca);
            }
            
            var mcmca=$("#salesMcMca"+i+"").val();
            var mcmcaAmount=qty*mcmca;
            $("#salesAmountMcMca"+i+"").val(mcmcaAmount);
            getUnitforRow(i,ui.item.itemCode);
            
       },
       open: function(){
                $('.ui-autocomplete').css('width', '700px');
            }

}).bind('focus', function(){ $(this).mcautocomplete("search"); } );
}


// Autocomplete for Name of Item - Start Code (Abi) //

function autoCompleteForSalesItemName(i) {  
    var category=$("#category").val();
    var margin=$("#salesMargin"+i+"").val();
    var taxPercentage=$("#taxPercentage").val();
     var columnsItems = [{name: 'Name', minWidth: '60%', valueField: 'itemName'},{name:'Code', minWidth: '20%', valueField:'itemCode'},{name: 'Stock', minWidth: '10%', valueField: 'currentStock'},{name: 'Tax', minWidth: '10%', valueField: 'tr'}];
       // Skip previous selected items start
       var previous_Item_code = new Array();
        $('input[id^="salesItemCode"]').each(function(){
            previous_Item_code.push("'"+$(this).val()+"'");
        });                   
       var selected_items=previous_Item_code.join(',');
       selected_items = selected_items.replace(/,\s*$/, "");     
        // Skip previous selected items end
    $("#salesItemName"+i+"").mcautocomplete({
        
          showHeader: true,
  columns: columnsItems,
  minLength:0,
  autoFocus: true,
  
    source: function(request, response) {
        $.ajax({
            url: "GetItemNameForSales.html",
            dataType: "json",
            type: "GET",
            data: {
                term: request.term,
                tax: "",
                items:selected_items
            },
            success: function(data){
                response( $.map( data, function( item ) {
                  
                    return {
                        value: item.itemName,   
                        label: item.itemName,
                        itemCode:item.itemCode,
                        itemName:item.itemName,
                        unit:item.unit,
                        altUnit:item.altUnit,
                        rate:item.rate,
                        currentStock:item.currentStock,
                        vap:item.vap,
                        vapAlt:item.vapAlt,
                        vbp:item.vbp,
                        vbpAlt:item.vbpAlt,
                        cap:item.cap,
                        capAlt:item.capAlt,
                        cbp:item.cbp,
                        cbpAlt:item.cbpAlt,
                        basicPrice:item.basicPrice,
                        tr:item.tr,
                        mc:item.mc,
                        mca:item.mca,
                        cp:item.cp,
                        taxSgst:item.taxSgst,
                        taxCgst:item.taxCgst,                     
                        whereTotalunit:item.totalUnit
                    };
  
                }));
     
             }
      
        });
    },
    select: function(event, ui) {   
         $('#salesItemName'+i+'').val(ui.item.itemName);
        $('#salesUnit'+i+'').val(ui.item.unit);
        $('#salesQuantity'+i+'').val(ui.item.currentStock);
        $('#salesCpcpV'+i+'').val(ui.item.cp);        
        $('#salesTax'+i+'').val(ui.item.tr);
        var isIgst=$('#IgstCalc').val();
        if(isIgst=='yes')
        {
        $('#salestaxCgst'+i+'').val(ui.item.taxCgst);
        $('#salestaxSgst'+i+'').val(ui.item.taxSgst);
        $('#salestaxIgst'+i+'').val(0.00);     
       
        }
        else
        {
        $('#salestaxCgst'+i+'').val(0.00);
        $('#salestaxSgst'+i+'').val(0.00);
        $('#salestaxIgst'+i+'').val(ui.item.taxIgst);  
        }  
        $('#totalUnit'+i+'').val(ui.item.whereTotalunit);
        // Get Value of IGST is yes or no
       
            //calc for CP+V
            var cpp=parseFloat($("#salesCpcpV"+i+"").val());          
            var taxx=parseFloat($("#salesTax"+i+"").val());      
            var taxxValue=cpp*taxx/100;      
            var cpcpV=cpp+taxxValue;             
            var tax=parseFloat($("#salesTax"+i+"").val());
          
        if(margin==='A'){
            if(category==='GST Dealers(B2B)'){              
                    $('#salesRate'+i+'').val(ui.item.vap);
                    $('#salesCpcpV'+i+'').val(cpp);
                }
            if(category==='Consumers(B2C)'){
                $('#salesRate'+i+'').val(ui.item.cap);
                $('#salesCpcpV'+i+'').val(cpcpV);
               
            }
        }
        if(margin==='B'){
            
            if(category==='Consumers(B2C)'){            
                $('#salesRate'+i+'').val(ui.item.cbp);
                $('#salesCpcpV'+i+'').val(cpcpV);
            }
            if(category==='GST Dealers(B2B)'){
                $('#salesRate'+i+'').val(ui.item.vbp);
                $('#salesCpcpV'+i+'').val(cpp);
                 
            }
        }
         
            $("#salesDiscount"+i+"").val('0');
            $("#salesCgst"+i+"").val('');
            $("#salesVat"+i+"").val('');
       
            var qty=parseFloat($("#salesQuantity"+i+"").val());
            var rate=parseFloat($("#salesRate"+i+"").val());
            var amount=qty*rate;
            $("#salesAmount"+i+"").val(amount.toFixed(2));
            if(category==='Consumers(B2C)'){                 
               
               var taxamount=(amount*tax)/(100+tax)
               var taxCGST=parseFloat($("#salestaxCgst"+i+"").val());
               var taxSGST=parseFloat($("#salestaxSgst"+i+"").val());
               var taxIGST=parseFloat($("#salestaxIgst"+i+"").val());
               var  taxValueCGST=(taxamount*taxCGST)/(tax);
               var  taxValueSGST=(taxamount*taxSGST)/(tax);
               var  taxValueIGST=(taxamount*taxIGST)/(tax);
               if(isNaN(taxValueCGST))
               {
                   $("#salesCgst"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesCgst"+i+"").val(taxValueCGST.toFixed(2));  
               } 
               if(isNaN(taxValueSGST))
               {
                   $("#salesVat"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesVat"+i+"").val(taxValueSGST.toFixed(2));  
               } 
               if(isNaN(taxValueIGST))
               {
                   $("#salesIgst"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesIgst"+i+"").val(taxValueIGST.toFixed(2));  
               }    
            
             
            var cpGST=parseFloat($("#salesCpcpV"+i+"").val());
            var cp=(cpGST*100)/(100+tax);            
            $("#salesAmountCpCpv"+i+"").val(qty*cp);     
             //With out Tax Clac
            var WithousalesCgstt=parseFloat($("#salesCgst"+i+"").val());
            var WithousalesVat=parseFloat($("#salesVat"+i+"").val());  
            var WithousalesIgst=parseFloat($("#salesIgst"+i+"").val());  
            var amountWithoutTax=amount-(WithousalesCgstt+WithousalesVat+WithousalesIgst);
            $("#amountWithoutTax"+i+"").val(amountWithoutTax);  
            
               
            }
            else
            {
               var taxCGST=parseFloat($("#salestaxCgst"+i+"").val());
               var taxSGST=parseFloat($("#salestaxSgst"+i+"").val());  
               var taxIGST=parseFloat($("#salestaxIgst"+i+"").val());
               var  taxValueCGST=(amount*taxCGST/100);
               var  taxValueSGST=(amount*taxSGST/100);
               var  taxValueIGST=(amount*taxIGST)/100;
               if(isNaN(taxValueCGST))
               {
                   $("#salesCgst"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesCgst"+i+"").val(taxValueCGST.toFixed(2));  
               } 
               if(isNaN(taxValueSGST))
               {
                   $("#salesVat"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesVat"+i+"").val(taxValueSGST.toFixed(2));  
               } 
               if(isNaN(taxValueIGST))
               {
                   $("#salesIgst"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesIgst"+i+"").val(taxValueIGST.toFixed(2));  
               }  
                // if same tax item added open add or less if not make it as read only
                   var taxArray = new Array();
                    $('input[id^="salesTax"]').each(function(){
                        taxArray.push($(this).val());
                    });
                   // alert(myFunc(taxArray));
                    if(myFunc(taxArray)===true)
                    {  
                        
                     $('#salesAddOrLess').attr('readonly', false);
                    }
                    else
                    {
                      $('#salesAddOrLess').attr('readonly', true);   
                    }    
            var cp=parseFloat($("#salesCpcpV"+i+"").val());
            $("#salesAmountCpCpv"+i+"").val(qty*cp);   
            //With out Tax Clac
            var WithousalesCgstt=parseFloat($("#salesCgst"+i+"").val());
            var WithousalesVat=parseFloat($("#salesVat"+i+"").val());  
            var WithousalesIgst=parseFloat($("#salesIgst"+i+"").val());  
            var amountWithoutTax=amount;
            $("#amountWithoutTax"+i+"").val(amountWithoutTax);  
                
            }
            
              
            
              
            var mc=ui.item.mc;
            var mca=ui.item.mca;
            if(mc!==null && mc!==0){
                $("#salesMcMca"+i+"").val(mc);
            }
            if(mca!==null && mca!==0){
                $("#salesMcMca"+i+"").val(mca);
            }
            
            var mcmca=$("#salesMcMca"+i+"").val();
            var mcmcaAmount=qty*mcmca;
            $("#salesAmountMcMca"+i+"").val(mcmcaAmount);
            getUnitforRow(i,ui.item.itemCode);
        
       },
       open: function(){
                $('.ui-autocomplete').css('width', '700px');
            }

}).bind('focus', function(){ $(this).mcautocomplete("search"); } );
}
//Autocomplete for Name Of Item -- End Code (Abi) //



// *** To set Current Balance and Type(buyer & mediator) From Ledger Account Master using ledger id - START(Abi) ***//

  function buyerChange(){
    var id=$('#buyerNameId').val();
               
                var termA = "id="+id+"&type=Buyer";
              
                    $.ajax({
                        url: "getBuyerAccount.html",
                        dataType: "json",
                        type: "GET",
                        data: termA,
                       
                        success: function(response){
                           
                            if(response.openingAmount.length===0){
                                $('#buyerBalance').val('');
                                $('#buyerType').val('');
                                 $('#idBuyer').val('');
                            }else{
                                $('#buyerBalance').val(response.openingAmount);
         
                                $('#buyerType').val(response.openingType);
                                $('#idBuyer').val(response.openingType);
//                                $('#creditAmount').val(response.creditAmount);
                                        
                            } 
                            
                            
                           
                        }
                    });                       
             }


 function mediatorChange(){
   
                var id=$('#mediatorNameId').val();
                
                var termA = "id="+id+"&type=Buyer";
                    $.ajax({
                        url: "getBuyerAccount.html",
                        dataType: "json",
                        type: "GET",
                        data: termA,
                     
                        success: function(response){
                            if(response.openingAmount.length===0){
                           
                                $('#mediatorBalance').val('');
                                $('#mediatorType').val('');
                               
                            }else{

                                $('#mediatorBalance').val(response.openingAmount);
                                $('#mediatorType').val(response.openingType);
                               
                            }
                        }
                    });
                    
             }

// *** To set Current Balance and Type(buyer & mediator) From Ledger Account Master using ledger id - END(Abi) ***//

 /* --- Autocomplete to bring three columns in single textbox As Table --- Start Code (Abi) */
$(window).load(function(){          
   $.widget('custom.mcautocomplete', $.ui.autocomplete, {
    _create: function() {
      this._super();
      this.widget().menu( "option", "items", "> :not(.ui-widget-header)" );
    },
    _renderMenu: function(ul, items) {
        var self = this, thead;
    
        if (this.options.showHeader) {
            table=$('<div class="ui-widget-header" style="width:100%"></div>');
            // Column headers
            $.each(this.options.columns, function(index, item) {
                table.append('<span style="float:left;min-width:' + item.minWidth + ';">' + item.name + '</span>');
            });
			table.append('<div style="clear: both;"></div>');
            ul.append(table);
        }
        // List items
        $.each(items, function(index, item) {
            self._renderItem(ul, item);
        });
    },
    _renderItem: function(ul, item) {
		var t = '',
			result = '';
		
		$.each(this.options.columns, function(index, column) {
			t += '<span style="float:left;min-width:' + column.minWidth + ';">' + item[column.valueField ? column.valueField : index] + '</span>'
		});
	
		result = $('<li></li>')
			.data('ui-autocomplete-item', item)
			.append('<a class="mcacAnchor">' + t + '<div style="clear: both;"></div></a>')
			.appendTo(ul);
		return result;
    }
});

                
 var columns1 = [{name:'Name', minWidth: '40%', valueField:'label'}, {name: 'Code', minWidth: '20%', valueField: 'code'},{name: 'Mobile', minWidth: '40%', valueField: 'mobile'}];

 
    $("#buyerName").mcautocomplete({
     
  showHeader: true,
  columns: columns1,
  minLength:0,
   autoFocus: true,
 source: function(request, response) {
      var categoary=$('#category').val();
        $.ajax({
            url: "GetBuyer.html",
            dataType: "json",
            type: "POST",
            data: {
                term: request.term,
                categoary:categoary
            },
           
            success: function(data){
                
                response( $.map( data, function( item ) {
                   
                    return {
                        value: item.buyerName,   
                        label: item.buyerName,
                        id: item.idBuyer,
                        tin:item.tin,
                        openingAmount:item.openingAmount,
                        creditAmount:item.creditAmount,
                        openingType:item.openingType,
                        code:item.buyerCode,
                        mobile:item.cellNo
                        
                    } 
                }));
             }
        });
    },
    select: function(event, ui) {   
//        $('#buyerBalance').val(ui.item.openingAmount);
//        $('#buyerType').val(ui.item.openingType);
        $('#tinNo').val(ui.item.tin);
        $('#buyerNameId').val(ui.item.id);
         $('#idBuyer').val(ui.item.creditAmount);
         $('#creditAmount').val(ui.item.creditAmount);        
        $('#tinNo').attr('readonly', true);
        gstinEqual();
    },
     open: function(){
                $('.ui-autocomplete').css('width', '500px');
            },
    change: buyerChange
}).bind('focus', function(){ $(this).mcautocomplete("search"); } );

//$('#buyerName').change(function() {



var columns2 = [{name:'Name', minWidth: '40%', valueField:'label'}, {name: 'Code', minWidth: '20%', valueField: 'code'},{name: 'Mobile', minWidth: '40%', valueField: 'mobile'}];
$("#mediator").mcautocomplete({
  showHeader: true,
  columns: columns2,
  minLength:0,
   autoFocus: true,
 source: function(request, response) {
        $.ajax({
            url: "GetBuyerMediator.html",
            dataType: "json",
            type: "POST",
            data: {
                term: request.term
            },
           
            success: function(data){
                
                response( $.map( data, function( item ) {
                   
                    return {
                        value: item.buyerName,   
                        label: item.buyerName,
                        id: item.idBuyer,
                        openingAmount:item.openingAmount,
                        openingType:item.openingType,
                        code:item.buyerCode,
                        mobile:item.cellNo
                        
                    } 
                }));
             }
        });
    },
    select: function(event, ui) {   
//        $('#buyerBalance').val(ui.item.openingAmount);
//        $('#buyerType').val(ui.item.openingType);
        $('#tinNo').val(ui.item.tin);
        $('#mediatorNameId').val(ui.item.id);
        $('#tinNo').attr('readonly', true);
         $('#salesMCA').attr('readonly', true);
    },
     open: function(){
                $('.ui-autocomplete').css('width', '500px');
            },
    change:mediatorChange
    
}).bind('focus', function(){ $(this).mcautocomplete("search"); } );


});
/* --- Autocomplete to bring three columns in single textbox As Table --- End Code (Abi) */



function onChangeCategory() {  
       
     var category=$("#category").val();
    if(category==='GST Dealers(B2B)'){
        $('#mediator').attr('readonly', true);
        $('#mediator').val('');
        $('#mediatorBalance').val('');
        $('#salesActualMCA').attr('readonly', true);
    }
    else{
       $('#mediator').attr('readonly', false);
       $('#salesActualMCA').attr('readonly', false);
    }
     show();
}


function onChangeMode() {    
    var mode=$("#mode").val();
    if(mode==='Cash'){
        
    $.ajax({  
     type : "POST",   
     url : "GetBuyerList.html",   
     success : function(response) {
       $.each(response, function(index, row) {
           if(row.buyerName==='CASH BUYER'){
             $('#buyerName').val(row.buyerName);
             $('#buyerBalance').val(row.openingAmount);
             $('#buyerName').attr('readonly', true);
             $('#buyerNameId').val(row.idBuyer);
             $('#idBuyer').val(row.idBuyer);
             $('#tinNo').val('');    
             $('#tinNo').attr('readonly', false);
           }
           var category=document.getElementById("category").value;
              
              if(category == 'Consumers(B2C)'){
                 
                 document.getElementById("Box1").tabIndex = 1;
            document.getElementById("theDate").tabIndex =2;
            document.getElementById("category").tabIndex = 3;
            document.getElementById("mode").tabIndex = 4;
            document.getElementById("cashBuyer").tabIndex = 5;
            document.getElementById("pos").tabIndex =6;
            document.getElementById("form-field-1").tabIndex =7;
              }
              else
              {
            document.getElementById("Box1").tabIndex = 1;
            document.getElementById("theDate").tabIndex =2;
            document.getElementById("category").tabIndex = 3;
            document.getElementById("mode").tabIndex = 4;
            document.getElementById("cashBuyer").tabIndex = 5;
            document.getElementById("tinNo").tabIndex =6;
            document.getElementById("form-field-1").tabIndex =7;  
                  
              }
            
           
           
       });
       buyerChange();
     },  
     error : function(e) {
      alert('Error: ' + e);   
     }  
    });  
}
else{
    $('#buyerName').val('');
    $('#buyerBalance').val('');
    $('#buyerName').attr('readonly', false);  
    $('#tinNo').attr('readonly', true);
     $('#cashBuyer').attr('readonly', false);
            document.getElementById("Box1").tabIndex = 1;
            document.getElementById("theDate").tabIndex =2;
            document.getElementById("category").tabIndex = 3;
            document.getElementById("mode").tabIndex = 4;
            document.getElementById("buyerName").tabIndex = 5;
            document.getElementById("cashBuyer").tabIndex = -1;
            document.getElementById("tinNo").tabIndex =-1;
            document.getElementById("form-field-1").tabIndex =7;
}
}

function onChangeMargin(i) { 
    $("#salesRate"+i+"").attr('readonly', true);
    
    var category=$("#category").val();
    var unit=$("#salesUnit"+i+"").val();
    var margin=$("#salesMargin"+i+"").val();
    var itemCode=$("#salesItemCode"+i+"").val();
    var totalunit=$('#totalUnit'+i+'').val();    
    if(itemCode.length>0 && margin!=='R'){
    $.ajax({  
     type : "POST",   
     url : "GetItemByItemCode.html",   
     dataType:"json",
     data : {itemCode:itemCode},
     success : function(response) {     
            if(unit==response.unit){
               
                               
                
                if(category=='GST Dealers(B2B)'){
                    var cpValue=response.cp;
                    var tax=response.tr;                
                    var cpGST=0;
                    if(cpValue!='')
                    {                        
                    cpGST=(cpValue).toFixed(2); 
                    $("#salesCpcpV"+i+"").val(cpGST);
                    }
                  
                    if(margin=='A'){
                        $("#salesRate"+i+"").val(response.vap);  
                    }
                    if(margin=='B'){
                        $("#salesRate"+i+"").val(response.vbp); 
                    }
                }
                if(category=='Consumers(B2C)'){
                    var cpValue=response.cp;
                    var tax=response.tr;                
                    var cpGST=0;
                    if(cpValue!='')
                    {
                    taxValue=(cpValue*tax)/(100);
                    cpGST=(cpValue+taxValue).toFixed(2);                  
                    $("#salesCpcpV"+i+"").val(cpGST);
                    }
                    
                        if(margin==='A'){
                            $("#salesRate"+i+"").val(response.cap);  
                        }
                        if(margin==='B'){
                            $("#salesRate"+i+"").val(response.cbp); 
                        }     
                }
            }          
            if(unit==response.altUnit){
               
            
                if(category=='GST Dealers(B2B)'){
                    var cpValue=response.cp;
                    var tax=response.tr;                
                    var cpGST=0;
                    if(cpValue!='')
                    {                
                    cpGST=(cpValue).toFixed(2);                    
                       if(response.totalUnit!='')
                        {                          
                          var altCp=(cpGST/parseFloat(response.totalUnit)).toFixed(2);
                          $("#salesCpcpV"+i+"").val(altCp);                           
                        }
                   
                    }
                    if(margin=='A'){
                        $("#salesRate"+i+"").val(response.vapAlt);  
                    }
                    if(margin=='B'){
                        $("#salesRate"+i+"").val(response.capAlt); 
                    }
                }
                if(category==='Consumers(B2C)'){
                      
                    var cpValue=response.cp;
                    var tax=response.tr;                
                    var cpGST=0;                
                    if(cpValue!='')
                    {                      
                    taxValue=(cpValue*tax)/(100);
                    cpGST=(cpValue+taxValue).toFixed(2);             
                    if(response.totalUnit!='')
                        {
                          var altCp=(cpGST/parseFloat(response.totalUnit)).toFixed(2);
                          $("#salesCpcpV"+i+"").val(altCp);                            
                        }
                    }
                        if(margin=='A'){
                            $("#salesRate"+i+"").val(response.capAlt);  
                        }
                        if(margin=='B'){
                            $("#salesRate"+i+"").val(response.cbpAtl); 
                        }     
                }
            }
            
            var qty=parseFloat($("#salesQuantity"+i+"").val());
            var rate=parseFloat($("#salesRate"+i+"").val());
            var amount=qty*rate;
            $("#salesAmount"+i+"").val(amount.toFixed(2));
            
            $("#salesDiscount"+i+"").val('0');
            
            var tax=parseFloat($("#salesTax"+i+"").val());
            var taxValue=0.0;
            if(category==='Consumers(B2C)'){            
               var taxamount=(amount*tax)/(100+tax)
               var taxCGST=parseFloat($("#salestaxCgst"+i+"").val());
               var taxSGST=parseFloat($("#salestaxSgst"+i+"").val());
               var taxIGST=parseFloat($("#salestaxIgst"+i+"").val());
               var  taxValueCGST=(taxamount*taxCGST)/(tax);
               var  taxValueSGST=(taxamount*taxSGST)/(tax);
               var  taxValueIGST=(taxamount*taxIGST)/(tax);
               if(isNaN(taxValueCGST))
               {
                   $("#salesCgst"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesCgst"+i+"").val(taxValueCGST.toFixed(2));  
               } 
               if(isNaN(taxValueSGST))
               {
                   $("#salesVat"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesVat"+i+"").val(taxValueSGST.toFixed(2));  
               } 
               if(isNaN(taxValueIGST))
               {
                   $("#salesIgst"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesIgst"+i+"").val(taxValueIGST.toFixed(2));  
               }    
            
             
            var cpGST=parseFloat($("#salesCpcpV"+i+"").val());
            var cp=(cpGST*100)/(100+tax);            
            $("#salesAmountCpCpv"+i+"").val(qty*cp);     
             //With out Tax Clac
            var WithousalesCgstt=parseFloat($("#salesCgst"+i+"").val());
            var WithousalesVat=parseFloat($("#salesVat"+i+"").val());  
            var WithousalesIgst=parseFloat($("#salesIgst"+i+"").val());  
            var amountWithoutTax=amount-(WithousalesCgstt+WithousalesVat+WithousalesIgst);
            $("#amountWithoutTax"+i+"").val(amountWithoutTax);       
            }
            else
            {
               var taxCGST=parseFloat($("#salestaxCgst"+i+"").val());
               var taxSGST=parseFloat($("#salestaxSgst"+i+"").val()); 
               var taxIGST=parseFloat($("#salestaxIgst"+i+"").val());             
               var  taxValueCGST=(amount*taxCGST/100);
               var  taxValueSGST=(amount*taxSGST/100);
               var  taxValueIGST=(amount*taxIGST/100);
            $("#salesCgst"+i+"").val(taxValueCGST.toFixed(2));
            $("#salesVat"+i+"").val(taxValueSGST.toFixed(2));
            $("#salesIgst"+i+"").val(taxValueIGST.toFixed(2));
            var cp=parseFloat($("#salesCpcpV"+i+"").val());                     
            $("#salesAmountCpCpv"+i+"").val((qty*cp).toFixed(2));  
            var WithousalesCgstt=parseFloat($("#salesCgst"+i+"").val());
            var WithousalesVat=parseFloat($("#salesVat"+i+"").val());  
            var WithousalesIgst=parseFloat($("#salesIgst"+i+"").val());  
            var amountWithoutTax=amount;
            $("#amountWithoutTax"+i+"").val(amountWithoutTax);     
            }
      
            
            
     },  
     error : function(e) {  
      
      alert('Error: ' + e);   
     }  
    });   
}
else{
    $("#salesRate"+i+"").attr('readonly', false);
}
}

function onChangeQtyOrRate(i) { 
  
    var category=$("#category").val();
    var qty=parseFloat($("#salesQuantity"+i+"").val()||0);
     
    var rate=parseFloat($("#salesRate"+i+"").val()||0);
    var tax=parseFloat($("#salesTax"+i+"").val());
    var amount=(qty*rate) || 0;
    $("#salesAmount"+i+"").val(amount.toFixed(2));
    if(category==='Consumers(B2C)'){  
               var taxamount=(amount*tax)/(100+tax)
               var taxCGST=parseFloat($("#salestaxCgst"+i+"").val());
               var taxSGST=parseFloat($("#salestaxSgst"+i+"").val());
               var taxIGST=parseFloat($("#salestaxIgst"+i+"").val());
               var  taxValueCGST=(taxamount*taxCGST)/(tax);
               var  taxValueSGST=(taxamount*taxSGST)/(tax);
               var  taxValueIGST=(taxamount*taxIGST)/(tax);
               if(isNaN(taxValueCGST))
               {
                   $("#salesCgst"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesCgst"+i+"").val(taxValueCGST.toFixed(2));  
               } 
               if(isNaN(taxValueSGST))
               {
                   $("#salesVat"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesVat"+i+"").val(taxValueSGST.toFixed(2));  
               } 
               if(isNaN(taxValueIGST))
               {
                   $("#salesIgst"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesIgst"+i+"").val(taxValueIGST.toFixed(2));  
               }
            
            var cpGST=parseFloat($("#salesCpcpV"+i+"").val());
            var cp=(cpGST*100)/(100+tax);            
            $("#salesAmountCpCpv"+i+"").val(qty*cp);         
            var WithousalesCgstt=parseFloat($("#salesCgst"+i+"").val());
            var WithousalesVat=parseFloat($("#salesVat"+i+"").val());    
            var WithousalesIgst=parseFloat($("#salesIgst"+i+"").val());  
            var amountWithoutTax=amount-(WithousalesCgstt+WithousalesVat+WithousalesIgst);
            $("#amountWithoutTax"+i+"").val(amountWithoutTax);  
          
    }
    else
    {
             var taxCGST=parseFloat($("#salestaxCgst"+i+"").val());
               var taxSGST=parseFloat($("#salestaxSgst"+i+"").val());  
               var taxIGST=parseFloat($("#salestaxIgst"+i+"").val());
               var  taxValueCGST=(amount*taxCGST/100);
               var  taxValueSGST=(amount*taxSGST/100);
               var  taxValueIGST=(amount*taxIGST)/100;
               if(isNaN(taxValueCGST))
               {
                   $("#salesCgst"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesCgst"+i+"").val(taxValueCGST.toFixed(2));  
               } 
               if(isNaN(taxValueSGST))
               {
                   $("#salesVat"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesVat"+i+"").val(taxValueSGST.toFixed(2));  
               } 
               if(isNaN(taxValueIGST))
               {
                   $("#salesIgst"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesIgst"+i+"").val(taxValueIGST.toFixed(2));  
               } 
            var cp=parseFloat($("#salesCpcpV"+i+"").val());                     
            $("#salesAmountCpCpv"+i+"").val(qty*cp);  
            var WithousalesCgstt=parseFloat($("#salesCgst"+i+"").val());
            var WithousalesVat=parseFloat($("#salesVat"+i+"").val());    
            var amountWithoutTax=amount;
            $("#amountWithoutTax"+i+"").val(amountWithoutTax);            
    }
   // var taxValue=amount*tax/100;
    
    //With out Tax Clac
          
    var mcmca=$("#salesMcMca"+i+"").val();
    var mcmcaAmount=qty*mcmca;
    $("#salesAmountMcMca"+i+"").val(mcmcaAmount.toFixed(2));
}

function onChangeDiscount(i) { 
    var category=$("#category").val();
    var qty=parseFloat($("#salesQuantity"+i+"").val()||0);
    var rate=parseFloat($("#salesRate"+i+"").val());
    var discount=$("#salesDiscount"+i+"").val();
    if(discount.length>0){
        var beforedisamount=qty*rate;
        var amount=beforedisamount-discount;
        $("#salesAmount"+i+"").val(amount.toFixed(2));
        
            var tax=parseFloat($("#salesTax"+i+"").val());
            var taxValue=0.0;       
            
            if(category==='Consumers(B2C)'){  
               var taxamount=(amount*tax)/(100+tax)
               var taxCGST=parseFloat($("#salestaxCgst"+i+"").val());
               var taxSGST=parseFloat($("#salestaxSgst"+i+"").val());
               var taxIGST=parseFloat($("#salestaxIgst"+i+"").val());
               var  taxValueCGST=(taxamount*taxCGST)/(tax);
               var  taxValueSGST=(taxamount*taxSGST)/(tax);
               var  taxValueIGST=(taxamount*taxIGST)/(tax);
               if(isNaN(taxValueCGST))
               {
                   $("#salesCgst"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesCgst"+i+"").val(taxValueCGST.toFixed(2));  
               } 
               if(isNaN(taxValueSGST))
               {
                   $("#salesVat"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesVat"+i+"").val(taxValueSGST.toFixed(2));  
               } 
               if(isNaN(taxValueIGST))
               {
                   $("#salesIgst"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesIgst"+i+"").val(taxValueIGST.toFixed(2));  
               }
            
            var cpGST=parseFloat($("#salesCpcpV"+i+"").val());
            var cp=(cpGST*100)/(100+tax);            
            $("#salesAmountCpCpv"+i+"").val(qty*cp);         
            var WithousalesCgstt=parseFloat($("#salesCgst"+i+"").val());
            var WithousalesVat=parseFloat($("#salesVat"+i+"").val());    
            var WithousalesIgst=parseFloat($("#salesIgst"+i+"").val());  
            var amountWithoutTax=amount-(WithousalesCgstt+WithousalesVat+WithousalesIgst);
            $("#amountWithoutTax"+i+"").val(amountWithoutTax);  
               
            }
            else
            {
                var taxCGST=parseFloat($("#salestaxCgst"+i+"").val());
               var taxSGST=parseFloat($("#salestaxSgst"+i+"").val());  
               var taxIGST=parseFloat($("#salestaxIgst"+i+"").val());
               var  taxValueCGST=(amount*taxCGST/100);
               var  taxValueSGST=(amount*taxSGST/100);
               var  taxValueIGST=(amount*taxIGST)/100;
               if(isNaN(taxValueCGST))
               {
                   $("#salesCgst"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesCgst"+i+"").val(taxValueCGST.toFixed(2));  
               } 
               if(isNaN(taxValueSGST))
               {
                   $("#salesVat"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesVat"+i+"").val(taxValueSGST.toFixed(2));  
               } 
               if(isNaN(taxValueIGST))
               {
                   $("#salesIgst"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesIgst"+i+"").val(taxValueIGST.toFixed(2));  
               } 
            var cp=parseFloat($("#salesCpcpV"+i+"").val());                     
            $("#salesAmountCpCpv"+i+"").val(qty*cp);  
            var WithousalesCgstt=parseFloat($("#salesCgst"+i+"").val());
            var WithousalesVat=parseFloat($("#salesVat"+i+"").val());    
            var amountWithoutTax=amount;
            $("#amountWithoutTax"+i+"").val(amountWithoutTax);            
         
            }
         
        }
    else{
        var qty=parseFloat($("#salesQuantity"+i+"").val());
        var rate=parseFloat($("#salesRate"+i+"").val());
        var amount=qty*rate;        
        $("#salesAmount"+i+"").val(amount);
        
            var tax=parseFloat($("#salesTax"+i+"").val());
            var taxValue=0.0;
            if(category==='Consumers(B2C)'){            
              var taxamount=(amount*tax)/(100+tax)
               var taxCGST=parseFloat($("#salestaxCgst"+i+"").val());
               var taxSGST=parseFloat($("#salestaxSgst"+i+"").val());
               var taxIGST=parseFloat($("#salestaxIgst"+i+"").val());
               var  taxValueCGST=(taxamount*taxCGST)/(tax);
               var  taxValueSGST=(taxamount*taxSGST)/(tax);
               var  taxValueIGST=(taxamount*taxIGST)/(tax);
               if(isNaN(taxValueCGST))
               {
                   $("#salesCgst"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesCgst"+i+"").val(taxValueCGST.toFixed(2));  
               } 
               if(isNaN(taxValueSGST))
               {
                   $("#salesVat"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesVat"+i+"").val(taxValueSGST.toFixed(2));  
               } 
               if(isNaN(taxValueIGST))
               {
                   $("#salesIgst"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesIgst"+i+"").val(taxValueIGST.toFixed(2));  
               }
            
            var cpGST=parseFloat($("#salesCpcpV"+i+"").val());
            var cp=(cpGST*100)/(100+tax);            
            $("#salesAmountCpCpv"+i+"").val(qty*cp);         
            var WithousalesCgstt=parseFloat($("#salesCgst"+i+"").val());
            var WithousalesVat=parseFloat($("#salesVat"+i+"").val());    
            var WithousalesIgst=parseFloat($("#salesIgst"+i+"").val());  
            var amountWithoutTax=amount-(WithousalesCgstt+WithousalesVat+WithousalesIgst);
            $("#amountWithoutTax"+i+"").val(amountWithoutTax);    
            }
            else
            {
                var taxCGST=parseFloat($("#salestaxCgst"+i+"").val());
               var taxSGST=parseFloat($("#salestaxSgst"+i+"").val());  
               var taxIGST=parseFloat($("#salestaxIgst"+i+"").val());
               var  taxValueCGST=(amount*taxCGST/100);
               var  taxValueSGST=(amount*taxSGST/100);
               var  taxValueIGST=(amount*taxIGST)/100;
               if(isNaN(taxValueCGST))
               {
                   $("#salesCgst"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesCgst"+i+"").val(taxValueCGST.toFixed(2));  
               } 
               if(isNaN(taxValueSGST))
               {
                   $("#salesVat"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesVat"+i+"").val(taxValueSGST.toFixed(2));  
               } 
               if(isNaN(taxValueIGST))
               {
                   $("#salesIgst"+i+"").val(0.0);                      
               }
               else
               {
                   $("#salesIgst"+i+"").val(taxValueIGST.toFixed(2));  
               } 
            var cp=parseFloat($("#salesCpcpV"+i+"").val());                     
            $("#salesAmountCpCpv"+i+"").val(qty*cp);  
            var WithousalesCgstt=parseFloat($("#salesCgst"+i+"").val());
            var WithousalesVat=parseFloat($("#salesVat"+i+"").val());    
            var amountWithoutTax=amount;
            $("#amountWithoutTax"+i+"").val(amountWithoutTax);              
             }
       
           
        }       
}

function salesCalculation(trLength) { 
   
    var amountWithoutTax=0;
    var invoiceAmount=0;
    var vaa=0;
    var vat=0;
    var cgst=0;
    var Igst=0.00;
    var addOrLess=0;
    var assessValue=0;
    var mca=0;
    var newAmount=0;
    
    var category=$("#category").val();    
    for (var i = 0; i < trLength; i++) {   
        var amount=parseFloat($("#salesAmount"+i+"").val() || 0);
        invoiceAmount=invoiceAmount+amount;
        var cpAmount=parseFloat($("#salesAmountCpCpv"+i+"").val() || 0); 
        vaa=vaa+cpAmount;
        var salesCgst=parseFloat($("#salesCgst"+i+"").val()|| 0);
        cgst=cgst+salesCgst;
        var salesVat=parseFloat($("#salesVat"+i+"").val() || 0);
        vat=vat+salesVat;
        var salesIgst=parseFloat($("#salesIgst"+i+"").val()|| 0);
        Igst=Igst+salesIgst;
        var mcmcaAmount=parseFloat($("#salesAmountMcMca"+i+"").val() || 0);
        mca=(mca+mcmcaAmount)|| 0;   
        var CusVaa=((invoiceAmount-vat-cgst-Igst)-vaa) || 0;  
        var Vatvaa=(invoiceAmount-vaa) || 0;
      //onChangeQtyOrRate(i)
    
    }    
    $("#salesVaa").val(vaa.toFixed(2));
    $("#Cgst").val(cgst.toFixed(2));
    $("#Igst").val(Igst.toFixed(2));
    $("#salesVat").val(vat.toFixed(2));
    $("#salesMCA").val(mca.toFixed(2)); 
    if(category==='GST Dealers(B2B)'){
        assessValue=invoiceAmount;
       $("#salesVaa").val(Vatvaa.toFixed(2));
       $("#salesAssessValue ").val(assessValue.toFixed(2));   
    }
    if(category==='Consumers(B2C)'){       
        assessValue=invoiceAmount-(cgst+vat); 
        $("#salesVaa").val(CusVaa.toFixed(2));
    }
    $("#salesAssessValue ").val(assessValue.toFixed(2));    
    $("#salesInvoiceAmount").val((cgst+vat+Igst+assessValue+addOrLess).toFixed(2));   
    
}
function salesCalculationAfterDelete(trLength,index) { 
    var amountWithoutTax=0;
    var invoiceAmount=0;
    var vaa=0;
    var vat=0;
    var cgst=0;
    var Igst=0.00;
    var addOrLess=0;
    var assessValue=0;
    var mca=0;
    var newAmount=0;
    
    var category=$("#category").val();
    
   
    
    for (var i = 0; i < trLength; i++) {
        if("#salesAmount"+i+""!="#salesAmount"+index+"")
        {   
        var amount=parseFloat($("#salesAmount"+i+"").val() || 0);
        invoiceAmount=invoiceAmount+amount;
        var cpAmount=parseFloat($("#salesAmountCpCpv"+i+"").val() || 0); 
        vaa=vaa+cpAmount;
        var salesCgst=parseFloat($("#salesCgst"+i+"").val()|| 0);
        cgst=cgst+salesCgst;
        var salesVat=parseFloat($("#salesVat"+i+"").val() || 0);
        vat=vat+salesVat;
        var salesIgst=parseFloat($("#salesIgst"+i+"").val()|| 0);
        Igst=Igst+salesIgst;
        var mcmcaAmount=parseFloat($("#salesAmountMcMca"+i+"").val() || 0);
        mca=(mca+mcmcaAmount)|| 0;   
        var CusVaa=((invoiceAmount-vat-cgst-Igst)-vaa) || 0;  
        var Vatvaa=(invoiceAmount-vaa) || 0;
      }
    
    }
    $("#salesVaa").val(vaa.toFixed(2));
    $("#Cgst").val(cgst.toFixed(2));
    $("#Igst").val(Igst.toFixed(2));
    $("#salesVat").val(vat.toFixed(2));
    $("#salesMCA").val(mca.toFixed(2)); 
    if(category==='GST Dealers(B2B)'){
        assessValue=invoiceAmount;
       $("#salesVaa").val(Vatvaa.toFixed(2));
       $("#salesAssessValue ").val(assessValue.toFixed(2));   
    }
    if(category==='Consumers(B2C)'){       
        assessValue=invoiceAmount-(cgst+vat); 
        $("#salesVaa").val(CusVaa.toFixed(2));
    }
    $("#salesAssessValue ").val(assessValue.toFixed(2));    
    $("#salesInvoiceAmount").val((cgst+vat+Igst+assessValue+addOrLess).toFixed(2));  
}
// start -- Calculation for deleting row in addRow --abi

function salesCalcForDeleteRow(trLength,index) { 
    var amountWithoutTax=0;
    var invoiceAmount=0;
    var vaa=0;
    var vat=0;
    var cgst=0;
    var Igst=0.00;
    var addOrLess=0;
    var assessValue=0;
    var mca=0;
    var newAmount=0;    
    var category=$("#category").val();
    for (var i = 0; i < trLength; i++) {
        if("#salesAmount"+i+""!="#salesAmount"+index+"")
        {   
        var amount=parseFloat($("#salesAmount"+i+"").val() || 0);
        invoiceAmount=invoiceAmount+amount;
        var cpAmount=parseFloat($("#salesAmountCpCpv"+i+"").val() || 0); 
        vaa=vaa+cpAmount;
        var salesCgst=parseFloat($("#salesCgst"+i+"").val()|| 0);
        cgst=cgst+salesCgst;
        var salesVat=parseFloat($("#salesVat"+i+"").val() || 0);
        vat=vat+salesVat;
        var salesIgst=parseFloat($("#salesIgst"+i+"").val()|| 0);
        Igst=Igst+salesIgst;
        var mcmcaAmount=parseFloat($("#salesAmountMcMca"+i+"").val() || 0);
        mca=(mca+mcmcaAmount)|| 0;   
        var CusVaa=((invoiceAmount-vat-cgst-Igst)-vaa) || 0;  
        var Vatvaa=(invoiceAmount-vaa) || 0;
      }
    
    }
    $("#salesVaa").val(vaa.toFixed(2));
    $("#Cgst").val(cgst.toFixed(2));
    $("#Igst").val(Igst.toFixed(2));
    $("#salesVat").val(vat.toFixed(2));
    $("#salesMCA").val(mca.toFixed(2)); 
    if(category==='GST Dealers(B2B)'){
        assessValue=invoiceAmount;
       $("#salesVaa").val(Vatvaa.toFixed(2));
       $("#salesAssessValue ").val(assessValue.toFixed(2));   
    }
    if(category==='Consumers(B2C)'){       
        assessValue=invoiceAmount-(cgst+vat); 
        $("#salesVaa").val(CusVaa.toFixed(2));
    }
    $("#salesAssessValue ").val(assessValue.toFixed(2));    
    $("#salesInvoiceAmount").val((cgst+vat+Igst+assessValue+addOrLess).toFixed(2));  
    
}

// end -- Calculation -- abi

function onChangeAddOrLess(){
   var addOrLess= parseFloat($("#salesAddOrLess ").val()||0);
   var cgst= parseFloat($("#Cgst").val());
   var vat= parseFloat($("#salesVat").val());
   var Igst= parseFloat($("#Igst").val());
   
   var assessValue= parseFloat($("#salesAssessValue ").val()||0);
   
   if(isNaN(addOrLess)){
       addOrLess=0;
   }
   if(isNaN(cgst)){
       cgst=0;
   }
   if(isNaN(vat)){
       vat=0;
   }
   if(isNaN(assessValue)){
       assessValue=0;
   }
   
   var invoiceAmount=cgst+vat+Igst+assessValue+addOrLess;
   $("#salesInvoiceAmount").val((invoiceAmount).toFixed(2));
}

function getUnit(trLength) { 
   for (var m = 0; m < trLength; m++) {
     var unit='#salesUnit'+m+'';  
     var itemCode= $("#salesItemCode"+m+"").val(); 
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
// getunit when select Itemcode or name for row
function getUnitforRow(rownumber,code) { 
  
     var unit='#salesUnit'+rownumber+'';  
     var itemCode= code;    
     if(itemCode.length>0){
         
        $.ajax({  
         type : "POST",   
         url : "getUnit.html",   
         data : {itemCode:itemCode},
         async:false,
         success : function(response) {
              $(unit).empty();
              var i=1;
             $.each(response, function(index, row) {              
                if(i==1)  
                $(unit).append('<option value="'+row.idUnit+'" selected="selected">'+row.unitSymbol+'</option>');
                else
                $(unit).append('<option value="'+row.idUnit+'">'+row.unitSymbol+'</option>');    
                i++; 
             });
         },  
         error : function(e) {  

          alert('Error: ' + e);   
         }  
        });  
    
    }
  
}
// Pre Defined Bill Start

function addRowforPredefined() {  
    $.ajax({  
     type : "POST",   
     url : "predefinedbill_addrow.html",   
     data : $("#pDForm").serialize(),
     success : function(response) {
     $('#addrowPreDiv').empty().append(response);
    
     },  
     error : function(e) {  
      
      alert('Error: ' + e);   
     }  
    });  
   } 
   // TO ALLOW ONLY NUMBER IN TEXTBOX -- ABI -- START CODE
   function isNumberKey(e)
       {
        var keyCode = (e.which) ? e.which : e.keyCode;
            if ((keyCode >= 48 && keyCode <= 57) || (keyCode == 8))
                return true;
            else if (keyCode == 46) {
                var curVal = document.activeElement.value;
                if (curVal != null && curVal.trim().indexOf('.') == -1)
                    return true;
                else
                    return false;
            }
            else
                return false;
       }

// Pre Defined Bill End
// Check array value are same or different
 function myFunc(arr){
        var x= arr[0];
        return arr.every(function(item){
            return item=== x;
        });
    }
    //Check GST Number are Equals
     function gstinEqual()
       {
         var companyGstin=document.getElementById("CompanyGstno").value;      
         var purchase=document.getElementById("tinNo").value;        
         var purchaseGstin=purchase.substring(0,2);
        if(purchaseGstin == companyGstin)
        {
          document.getElementById("IgstCalc").value="yes";        
        }
        else
        {
           document.getElementById("IgstCalc").value="no";            
        }
       }
       function posStatesEqual()
       {
         var companyGstin=document.getElementById("CompanyGstno").value;      
         var purchase=document.getElementById("pos").value;        
         var purchaseGstin=purchase.substring(0,2);
        if(purchaseGstin == companyGstin)
        {
          document.getElementById("IgstCalc").value="yes";        
          
        }
        else
        {
           document.getElementById("IgstCalc").value="no";    
          
        }
       }

function checkpos()
{  
     var pos=$('#pos').val();
     if(pos=='')
     {
      $("#pos").focus();   
      alert("Select POS");
     }    
     
}
