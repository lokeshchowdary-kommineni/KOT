
// to check IGST 
  function gstinEqual()
       {
       
         var companyGstin=document.getElementById("compGstin").value;
     
         var purchase=document.getElementById("pos").value;
        
         var purchaseGstin=purchase.substring(0,2);
    
        
        if(purchaseGstin == companyGstin)
        {
        
          document.getElementById("final").value="no"; 
        
        }
        else
        {
            
           document.getElementById("final").value="yes"; 
         
           
        }
       }
       function posStatesEqual()
       {
         var companyGstin=document.getElementById("CompanyGstno").value;   
//          alert(companyGstin);
         var purchase=document.getElementById("pos").value;  
//          alert(purchase);
         var purchaseGstin=purchase.substring(0,2);
//         alert(purchaseGstin);
        if(purchaseGstin == companyGstin)
        {
          document.getElementById("final").value="no";        
//           alert("seka if");
        }
        else
        {
           document.getElementById("final").value="yes";    
//           alert("seka else");
        }
       }

// Script to insert value as '0' in hidden --- CpPriceUpdate -- StartCode---(Abi)
function UpdateInfo(inp){
    $("#priceUpdateId"+inp+"").val('0');
}


// To Delete Record in ADD ROW --Abi // 
  function myFunction(r,totalRow,index) {  
   var tableSize = $("#Mytable td").closest("tr").length;
   
  if(tableSize > 1){
       var i = r.parentNode.parentNode.rowIndex;
        purchasesReCalculationAddRow(totalRow,index);    
    document.getElementById("Mytable").deleteRow(i);
  } 
  
}   

   function deleteFunction(r,totalRow,index) {  
   var tableSize = $("#Mytable td").closest("tr").length;
   
  if(tableSize > 1){
       var i = r.parentNode.parentNode.rowIndex;
     purchaseCalciForm(totalRow,index); 
    document.getElementById("Mytable").deleteRow(i);
  }
  
}  
      
    $('#myforms').submit(function (e) {    
    $.ajax({  
     type : "POST",   
     url : "SaveItemPopUp.html",   
     data : $("#myforms").serialize(), 
     success : function(response) {
         var ChangeRate= $('#basicPrice').val();
         var ChangeEP= $('#ep').val();
         var ItenIndex= $('#itemIndexValue').val();  
         var trlength= $('#itemTableValue').val(); 
         
        $('#purchaseRate'+ItenIndex+'').val(ChangeRate);
        $('#purchaseEp'+ItenIndex+'').val(ChangeEP);
        
      $('#myforms').hide();          
     $('#close').click();
     $('#purchaseEp'+ItenIndex+'').focus();
      onChangeQty(ItenIndex);
      purchasesCalculation(trlength);
     },  
     error : function(e) {  
      
      alert('Error: ' + e);   
     }  
    }); 
     e.preventDefault();
     });
   
   
  function addRowforPurchase() {  
    $.ajax({  
     type : "POST",   
     url : "purchase_addrow.html",   
     data : $("#pFrom").serialize(),
     success : function(response) {
     $('#addrowDiv').empty().append(response);
     
     purchaseCategoryCheck();
    
     },  
     error : function(e) {  
      
      alert('Error: ' + e);   
     }  
    }); 
  
   }
     
   function onChangePMode() {  
    var mode=$("#mode").val();
    if(mode==='Cash'){        
    $.ajax({  
     type : "POST",   
     url : "GetSupplierList.html",   
     success : function(response) {
       $.each(response, function(index, row) {
           
             $('#supplierName').val(''); 
             $('#supplierName').attr('readonly', false);
//             $('#openingAmount').val(''); 
//             $('#tinNo').val('');   
//             $('#tinNo').attr('readonly', false);    
             
             // TabIndex Order
//            document.getElementById("Box1").tabIndex = 1;
//            document.getElementById("mydate").tabIndex =2;
            document.getElementById("category").tabIndex = 3;
//            document.getElementById("mode").tabIndex = 4;
            document.getElementById("supplierName").tabIndex = 5;
//            document.getElementById("cashSupplier").tabIndex = -1;
//            document.getElementById("tinNo").tabIndex =-1;
//            document.getElementById("purchase.narration").tabIndex =7;
//            document.getElementById("InvoiceAmount").tabIndex =8;
//           if(row.supplierName==='CASH SUPPLIER'){              
//              $('#supplierNameId').val(row.idSupplier);
//             $('#supplierName').val(row.supplierName); 
//             $('#supplierName').attr('readonly', true);
//             $('#currentBalance').val(row.openingAmount);
//              $('#openingType').val(row.openingType);                         
//             $('#tinNo').attr('readonly',false);  
//             $('#cashSupplier').attr('readonly',false);  
//              $('#supplierNameId').val(row.idSupplier); 
//              
//              // TabIndex Order
//              
//              var category=document.getElementById("category").value;
//              
//              if(category != 'ITC Purchase'){
//                 document.getElementById("pos").tabIndex =-1;
//              }
//          
//            document.getElementById("Box1").tabIndex = 1;
//            document.getElementById("mydate").tabIndex =2;
//            document.getElementById("category").tabIndex = 3;
//            document.getElementById("mode").tabIndex = 4;
//            document.getElementById("cashSupplier").tabIndex = 5;
//            document.getElementById("tinNo").tabIndex =6; 
//            document.getElementById("purchase.narration").tabIndex =7;
//            document.getElementById("InvoiceAmount").tabIndex =8;
//            $("#cashSupplier").focus();
//           }

       });
       supplierChange();
     },  
     error : function(e) {
      alert('Error: ' + e);   
     }  
    });  
}
//else {  
   
//             $('#supplierName').val(''); 
//             $('#supplierName').attr('readonly', false);
//             $('#openingAmount').val(''); 
//             $('#tinNo').val('');   
//             $('#tinNo').attr('readonly', false);    
//             
//             // TabIndex Order
//            document.getElementById("Box1").tabIndex = 1;
//            document.getElementById("mydate").tabIndex =2;
//            document.getElementById("category").tabIndex = 3;
//            document.getElementById("mode").tabIndex = 4;
//            document.getElementById("supplierName").tabIndex = 5;
//            document.getElementById("cashSupplier").tabIndex = -1;
//            document.getElementById("tinNo").tabIndex =-1;
//            document.getElementById("purchase.narration").tabIndex =7;
//            document.getElementById("InvoiceAmount").tabIndex =8;
          //  $("#supplierName").focus();
      
//}
}
//** Set Current Balance & Type(Supplier) from ledger Account Master using ledger id -START(ABI) **//

   function supplierChange(){
                var id=$('#supplierNameId').val();
                
                var termA = "id="+id+"&type=Supplier";
                    $.ajax({
                        url: "getSupplierAccount.html",
                        dataType: "json",
                        type: "GET",
                        data: termA,
                        success: function(response){
                            if(response.openingAmount.length===0){
                               
                                $('#currentBalance').val('');
                                $('#openingType').val('');
                            }else{
                                $('#currentBalance').val(response.openingAmount);
                                $('#openingType').val(response.openingType);
                            }
                        }
                    });                       
             }

//** Set Current Balance & Type(Supplier) from ledger Account Master using ledger id - END(ABI) **//

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

                
 var columns1 = [{name:'Name', minWidth: '40%', valueField:'label'}, {name: 'Code', minWidth: '20%', valueField: 'code'},{name: 'location', minWidth: '40%', valueField: 'location'}];

    
    $("#supplierName").mcautocomplete({
  showHeader: true,
  columns: columns1,
  minLength:0,
  autoFocus: true,
 source: function(request, response) {
    var category = $('#category').val();
    
        $.ajax({
             
            url: "GetSupplier.html",
            dataType: "json",
            type: "POST",
          
            data: {
              
                term: request.term,
                category: category
            },
            success: function(data){
                response( $.map( data, function( item ) {
                   
                    return {
                        value: item.supplierName,   
                        label: item.supplierName,
                        id: item.idSupplier,
                        tin:item.tin,
                        openingAmount:item.openingAmount,
                        openingType:item.openingType,
                        location:item.location
                        
                    } 
                }));
             }
        });
    },
     select: function(event, ui) {         
        $('#cashSupplier').val(ui.item.cashSupplier);
        $('#cashSupplier').attr('readonly', true);        
//        $('#currentBalance').val(ui.item.openingAmount);
//        $('#openingType').val(ui.item.openingType);  
         $('#supplierNameId').val(ui.item.id);
//        $('#tinNo').val(ui.item.tin);
//        $('#tinNo').attr('readonly', true);
        
        gstinEqual();
    },
     open: function(){
                $('.ui-autocomplete').css('width', '500px');
            },
    change:supplierChange
    
}).bind('focus', function(){ $(this).mcautocomplete("search"); });

});


//
//$(document).ready((function(){
//    alert();
//$("#InvoiceNo").autocomplete({
//    source: function(request, response) {
//        $.ajax({
//            url: "GetPurchaseInvoiceNo.html",
//            dataType: "json",
//            type: "POST",
//            data: {
//                term: request.term
//            },
//            success: function(data){
//                response( $.map( data, function( item ) {
//                   
//                    return {
//                        value: item.invoiceNo,   
//                        label: item.invoiceNo,
//                        id: item.invoiceNo,
//                        
//                    } 
//                }));
//             }
//        });
//    },
//    select: function(event, ui) {         
//         $('#supplierNameId').val(ui.item.id);
//           $('#InvoiceNo').val(ui.item.id);
//
//     }    
//});
//})); 

function purchaseCategoryCheck(i){
    
    
    var category=$("#category").val();
    var EnterInvoiceAmount=$("#InvoiceAmount").val();
    // Change Tp Or EP
    show();
    if(category.length===0 ){
         alert("Select Category");
       
    }   
    else{       
        if(category==='ITC Purchase'){
           
            autoCompleteForPurchase(i);
            autoCompleteForPurchaseItemName(i)
            $("#purchaseAssessValue").prop('readonly',null);
         $("#totalIgst").prop('readonly',null);
         $("#totalCgst").prop('readonly',null);
         $("#totalVat").prop('readonly',null);
//          var tinNo=$("#tinNo").val();
//            if(tinNo=='' || tinNo.length==0)
            {
//              $("#tinNo").focus(); 
//              alert("Please Enter GSTIN");    
              
            }
        }
        else if(category==='Purchase from Composite Dealers'){
        
         autoCompleteForPurchase(i); 
         autoCompleteForPurchaseItemName(i)
         $("#purchaseAssessValue").prop('readonly',null);
         $("#totalIgst").prop('readonly',null);
         $("#totalCgst").prop('readonly',null);
         $("#totalVat").prop('readonly',null);
         
               
        }
        else if(category==='Purchases from unregistered Persons')
        {          
         autoCompleteForPurchase(i); 
         autoCompleteForPurchaseItemName(i)  
         $("#purchaseAssessValue").prop('readonly',true);
         $("#totalIgst").prop('readonly',true);
         $("#totalCgst").prop('readonly',true);
         $("#totalVat").prop('readonly',true);
        }    
       
    }
    
}
// Autocomplete for Item Code -- Start Code (Abi) //
function autoCompleteForPurchase(i) {   
   var columnsItems1 = [{name: 'Code', minWidth: '20%', valueField: 'label'},{name:'Name', minWidth: '60%', valueField:'itemName'},{name: 'Stock', minWidth: '10%', valueField: 'currentStock'},{name: 'Tax', minWidth: '10%', valueField: 'tr'}];  
   var category=$("#category").val();
   var taxIdless="";
   if(i==0){
    taxIdless=i;       
   }       
   else
   {
    taxIdless=i-1;   
   }
   var purchaseTax=$("#purchasesTax"+taxIdless+"").val();
    // Skip previous selected items start
       var previous_Item_code = new Array();
        $('input[id^="purchaseItemCode"]').each(function(){
            previous_Item_code.push("'"+$(this).val()+"'");
        });                   
       var selected_items=previous_Item_code.join(',');
       selected_items = selected_items.replace(/,\s*$/, "");    
        // Skip previous selected items end

    $("#purchaseItemCode"+i+"").mcautocomplete({
          showHeader: true,
  columns: columnsItems1,
  minLength:0,
   autoFocus: true,
    source: function(request, response) {
        $.ajax({
            url: "GetItemCodeForSales.html",
            dataType: "json",
            type: "GET",
            data: {
                term: request.term,
                tax: purchaseTax,
                items:selected_items
            },
            autoFocus: true,
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
                        ep:item.ep, 
                        currentStock:item.currentStock,
                        basicPrice:item.basicPrice,
                        tr:item.tr,
                        taxSgst:item.taxSgst,
                        taxCgst:item.taxCgst,
                        taxIgst:item.taxIgst,
                        tp:item.tp
                   
                };
  
                }));
                
             }
      
        });
    },
    select: function(event, ui) { 
        $('#purchaseNameOfTheItem'+i+'').val(ui.item.itemName);
        $('#purchaseUnit'+i+'').val(ui.item.unit);
        $('#purchaseRate'+i+'').val(ui.item.basicPrice);
        $('#purchaseEp'+i+'').val(ui.item.ep); 
        $('#purchasesTax'+i+'').val(ui.item.tr); 
        
         
        if(category=='ITC Purchase'){
        
        $('#purchaseNameOfTheItem'+i+'').val(ui.item.itemName);
        $('#purchaseUnit'+i+'').val(ui.item.unit);
        $('#purchaseRate'+i+'').val(ui.item.basicPrice);
        $('#purchaseEp'+i+'').val(ui.item.ep); 
       
        var igstCalci = $('#final').val(); 
        if(igstCalci == 'yes')
        {
        $('#purchasetaxCgst'+i+'').val(0.0);  
        $('#purchasetaxSgst'+i+'').val(0.0);
        $('#purchasetaxIgst'+i+'').val(ui.item.taxIgst); 
        }
        else
        {
        $('#purchasetaxCgst'+i+'').val(ui.item.taxCgst);  
        $('#purchasetaxSgst'+i+'').val(ui.item.taxSgst);
        $('#purchasetaxIgst'+i+'').val(0.0);   
        }
        
        
        }
       else if(category=='Purchase from Composite Dealers' ){
          $('#purchaseNameOfTheItem'+i+'').val(ui.item.itemName);
          $('#purchaseUnit'+i+'').val(ui.item.unit);  
          $('#purchaseRate'+i+'').val(ui.item.tp);
          $('#purchaseEp'+i+'').val(ui.item.tp); 
          $('#purchasesTax'+i+'').val(ui.item.tr);          
          // for all tax value is zero
           $('#purchasetaxCgst'+i+'').val(0.00);  
           $('#purchasetaxSgst'+i+'').val(0.00);
           $('#purchasetaxIgst'+i+'').val(0.00);   
        }
        else if(category=='Purchases from unregistered Persons')
        {
            
          // this categoary of purchase from intrastare only   
          $('#purchaseNameOfTheItem'+i+'').val(ui.item.itemName);
          $('#purchaseUnit'+i+'').val(ui.item.unit);  
          $('#purchaseRate'+i+'').val(ui.item.tp);
          $('#purchaseEp'+i+'').val(ui.item.tp); 
          $('#purchasesTax'+i+'').val(ui.item.tr);          
          // for all tax value is zero
           $('#purchasetaxCgst'+i+'').val(ui.item.taxCgst);  
           $('#purchasetaxSgst'+i+'').val(ui.item.taxSgst);
           $('#purchasetaxIgst'+i+'').val(0.00);    
        }    
         $(".txtMult input").keyup(multInputs);

       function multInputs() {
         
           var mult = 0;
           var amount=0;

           $("tr.txtMult").each(function () {
               
            var qty=parseInt($("#purchaseQty"+i+"").val());            
            var ep=parseFloat($("#purchaseEp"+i+"").val());            
            amount=qty*ep;
             
            $("#purchaseAmount"+i+"").val(amount);
           
            
            $("#purchaseCgst"+i+"").val('');
            $("#purchaseVat"+i+"").val('');
         
            
            if(category=='ITC Purchase'){
                
            var taxCGST=parseFloat($("#purchasetaxCgst"+i+"").val());            
            var taxValueCGST=amount*taxCGST/100;
            
            var taxSGST=parseFloat($("#purchasetaxSgst"+i+"").val());            
            var taxValueSGST=amount*taxSGST/100;
            
             var taxIGST=parseFloat($("#purchasetaxIgst"+i+"").val());            
            var taxValueIGST=amount*taxIGST/100;
            
            if(isNaN(taxValueCGST))
               {
                  $("#purchaseCgst"+i+"").val(0.0);
               }
               else
               {
                    $("#purchaseCgst"+i+"").val(taxValueCGST);
               } 
               if(isNaN(taxValueSGST))
               {
                 $("#purchaseVat"+i+"").val(0.0);                    
               }
               else
               {
                  $("#purchaseVat"+i+"").val(taxValueSGST);
               } 
               if(isNaN(taxValueIGST))
               {
                  $("#purchaseIgst"+i+"").val(0.0);                  
               }
               else
               {
                   $("#purchaseIgst"+i+"").val(taxValueIGST);
               }    
            
              }
           });  
                } 
        getUnitforRow(i,ui.item.itemCode);         
    },
     open: function(){
                                    $('.ui-autocomplete').css('width', '700px');
                                }
   
}).bind('focus', function(){ $(this).mcautocomplete("search"); } );

}

// Autocomplete for Item Name -- Start Code (Abi)//
function autoCompleteForPurchaseItemName(i) {   
   var columnsItems1 = [{name:'Name', minWidth: '60%', valueField:'itemName'},{name: 'Code', minWidth: '20%', valueField: 'label'},{name: 'Stock', minWidth: '10%', valueField: 'currentStock'},{name: 'Tax', minWidth: '10%', valueField: 'tr'}];  
   var category=$("#category").val();
   var taxIdless="";
   if(i==0){
    taxIdless=i;       
   }       
   else
   {
    taxIdless=i-1;   
   }
   var purchaseTax=$("#purchasesTax"+taxIdless+"").val();
    // Skip previous selected items start
       var previous_Item_code = new Array();
        $('input[id^="purchaseItemCode"]').each(function(){
            previous_Item_code.push("'"+$(this).val()+"'");
        });                   
       var selected_items=previous_Item_code.join(',');
       selected_items = selected_items.replace(/,\s*$/, "");    
        // Skip previous selected items end

    $("#purchaseNameOfTheItem"+i+"").mcautocomplete({
          showHeader: true,
  columns: columnsItems1,
  minLength:0,
   autoFocus: true,
    source: function(request, response) {
        $.ajax({
            url: "GetItemNameForSales.html",
            dataType: "json",
            type: "GET",
            data: {
                term: request.term,
                tax: purchaseTax,
                items:selected_items
            },
            autoFocus: true,
            success: function(data){
                response( $.map( data, function( item ) {
                   
                    return {
                        value: item.itemName,   
                        label: item.itemCode,
                        itemCode:item.itemCode,
                        itemName:item.itemName,
                        unit:item.unit,
                        altUnit:item.altUnit,
                        rate:item.rate,
                        ep:item.ep, 
                        currentStock:item.currentStock,
                        basicPrice:item.basicPrice,
                        tr:item.tr,
                        taxSgst:item.taxSgst,
                        taxCgst:item.taxCgst,
                        taxIgst:item.taxIgst,
                        tp:item.tp
                   
                };
  
                }));
                
             }
      
        });
    },
    select: function(event, ui) { 
        $('#purchaseNameOfTheItem'+i+'').val(ui.item.itemName);
        $('#purchaseUnit'+i+'').val(ui.item.unit);
        $('#purchaseRate'+i+'').val(ui.item.basicPrice);
        $('#purchaseEp'+i+'').val(ui.item.ep); 
        $('#purchasesTax'+i+'').val(ui.item.tr); 
        
         
        if(category=='ITC Purchase'){
        
        $('#purchaseNameOfTheItem'+i+'').val(ui.item.itemName);
        $('#purchaseUnit'+i+'').val(ui.item.unit);
        $('#purchaseRate'+i+'').val(ui.item.basicPrice);
        $('#purchaseEp'+i+'').val(ui.item.ep); 
       
        var igstCalci = $('#final').val(); 
        if(igstCalci == 'yes')
        {
        $('#purchasetaxCgst'+i+'').val(0.0);  
        $('#purchasetaxSgst'+i+'').val(0.0);
        $('#purchasetaxIgst'+i+'').val(ui.item.taxIgst); 
        }
        else
        {
        $('#purchasetaxCgst'+i+'').val(ui.item.taxCgst);  
        $('#purchasetaxSgst'+i+'').val(ui.item.taxSgst);
        $('#purchasetaxIgst'+i+'').val(0.0);   
        }
        
        
        }
       else if(category=='Purchase from Composite Dealers' ){
          $('#purchaseNameOfTheItem'+i+'').val(ui.item.itemName);
          $('#purchaseUnit'+i+'').val(ui.item.unit);  
          $('#purchaseRate'+i+'').val(ui.item.tp);
          $('#purchaseEp'+i+'').val(ui.item.tp); 
          $('#purchasesTax'+i+'').val(ui.item.tr);          
          // for all tax value is zero
           $('#purchasetaxCgst'+i+'').val(0.00);  
           $('#purchasetaxSgst'+i+'').val(0.00);
           $('#purchasetaxIgst'+i+'').val(0.00);   
        }
        else if(category=='Purchases from unregistered Persons')
        {
            
          // this categoary of purchase from intrastare only   
          $('#purchaseNameOfTheItem'+i+'').val(ui.item.itemName);
          $('#purchaseUnit'+i+'').val(ui.item.unit);  
          $('#purchaseRate'+i+'').val(ui.item.tp);
          $('#purchaseEp'+i+'').val(ui.item.tp); 
          $('#purchasesTax'+i+'').val(ui.item.tr);          
          // for all tax value is zero
           $('#purchasetaxCgst'+i+'').val(ui.item.taxCgst);  
           $('#purchasetaxSgst'+i+'').val(ui.item.taxSgst);
           $('#purchasetaxIgst'+i+'').val(0.00);    
        }    
         $(".txtMult input").keyup(multInputs);

       function multInputs() {
         
           var mult = 0;
           var amount=0;

           $("tr.txtMult").each(function () {
               
            var qty=parseInt($("#purchaseQty"+i+"").val());            
            var ep=parseFloat($("#purchaseEp"+i+"").val());            
            amount=qty*ep;
             
            $("#purchaseAmount"+i+"").val(amount);
           
            
            $("#purchaseCgst"+i+"").val('');
            $("#purchaseVat"+i+"").val('');
         
            
            if(category=='ITC Purchase'){
                
            var taxCGST=parseFloat($("#purchasetaxCgst"+i+"").val());            
            var taxValueCGST=amount*taxCGST/100;
            
            var taxSGST=parseFloat($("#purchasetaxSgst"+i+"").val());            
            var taxValueSGST=amount*taxSGST/100;
            
             var taxIGST=parseFloat($("#purchasetaxIgst"+i+"").val());            
            var taxValueIGST=amount*taxIGST/100;
            
            if(isNaN(taxValueCGST))
               {
                  $("#purchaseCgst"+i+"").val(0.0);
               }
               else
               {
                    $("#purchaseCgst"+i+"").val(taxValueCGST);
               } 
               if(isNaN(taxValueSGST))
               {
                 $("#purchaseVat"+i+"").val(0.0);                    
               }
               else
               {
                  $("#purchaseVat"+i+"").val(taxValueSGST);
               } 
               if(isNaN(taxValueIGST))
               {
                  $("#purchaseIgst"+i+"").val(0.0);                  
               }
               else
               {
                   $("#purchaseIgst"+i+"").val(taxValueIGST);
               }    
            
              }
           });  
                } 
        getUnitforRow(i,ui.item.itemCode);         
    },
     open: function(){
                                    $('.ui-autocomplete').css('width', '700px');
                                }
   
}).bind('focus', function(){ $(this).mcautocomplete("search"); } );

}
// Autocomplete for Item Name -- End Code(Abi) //
function onChangeQty(i) {
  
     var category=$("#category").val();
            var qty=parseFloat($("#purchaseQty"+i+"").val()|| 0);     
           
            var ep=parseFloat($("#purchaseEp"+i+"").val()|| 0);            
            var amount=(qty*ep) || 0;
             
            $("#purchaseAmount"+i+"").val(amount.toFixed(2));
            
            $("#purchaseCgst"+i+"").val('');
            $("#purchaseVat"+i+"").val('') ;
            
            if(category=='ITC Purchase'){
            var taxCGST=parseFloat($("#purchasetaxCgst"+i+"").val());            
            var taxValueCGST=(amount*taxCGST/100)||0;
            
            var taxSGST=parseFloat($("#purchasetaxSgst"+i+"").val());            
            var taxValueSGST=(amount*taxSGST/100)||0;
            
             var taxIGST=parseFloat($("#purchasetaxIgst"+i+"").val());            
            var taxValueIGST=(amount*taxIGST/100)||0;

            var cgstResult=taxValueCGST.toFixed(2);
            var sgstResult=taxValueSGST.toFixed(2);
            var igstResult=taxValueIGST.toFixed(2);
            
             if(isNaN(cgstResult))
               {
                  $("#purchaseCgst"+i+"").val(0.0);
               }
               else
               {
                    $("#purchaseCgst"+i+"").val(taxValueCGST.toFixed(2));
               } 
               if(isNaN(sgstResult))
               {
                 $("#purchaseVat"+i+"").val(0.0);            
               }
               else
               {
                  $("#purchaseVat"+i+"").val(taxValueSGST.toFixed(2));
               } 
               if(isNaN(igstResult))
               {
                 $("#purchaseIgst"+i+"").val(0.0);        
               }
               else
               {
                  $("#purchaseIgst"+i+"").val(taxValueIGST.toFixed(2));
               }
               
              }
              if(category=='Purchase from Composite Dealers'){
             var taxValue=0.00;
             var cgst=$("#purchaseCgst"+i+"").val(taxValue);
             var vat=$("#purchaseVat"+i+"").val(taxValue);
             var igst=$("#purchaseIgst"+i+"").val(taxValue);
              }
              if(category=='Purchases from unregistered Persons')
              {
                var taxCGST=parseFloat($("#purchasetaxCgst"+i+"").val());            
            var taxValueCGST=(amount*taxCGST/100)||0;
            
            var taxSGST=parseFloat($("#purchasetaxSgst"+i+"").val());            
            var taxValueSGST=(amount*taxSGST/100)||0;
            
             var taxIGST=parseFloat($("#purchasetaxIgst"+i+"").val());            
            var taxValueIGST=(amount*taxIGST/100)||0;

            var cgstResult=taxValueCGST.toFixed(2);
            var sgstResult=taxValueSGST.toFixed(2);
            var igstResult=taxValueIGST.toFixed(2);
            
             if(isNaN(cgstResult))
               {
                  $("#purchaseCgst"+i+"").val(0.0);
               }
               else
               {
                    $("#purchaseCgst"+i+"").val(taxValueCGST.toFixed(2));
               } 
               if(isNaN(sgstResult))
               {
                 $("#purchaseVat"+i+"").val(0.0);            
               }
               else
               {
                  $("#purchaseVat"+i+"").val(taxValueSGST.toFixed(2));
               } 
               if(isNaN(igstResult))
               {
                 $("#purchaseIgst"+i+"").val(0.0);        
               }
               else
               {
                  $("#purchaseIgst"+i+"").val(taxValueIGST.toFixed(2));
               }  
              }    
}
function onChangeEP(i) {
     var category=$("#category").val();
            var qty=parseFloat($("#purchaseQty"+i+"").val() || 0);            
            var ep=parseFloat($("#purchaseEp"+i+"").val() || 0);            
            var amount=qty*ep;
             
            $("#purchaseAmount"+i+"").val(amount.toFixed(2));
            
            $("#purchaseCgst"+i+"").val('');
            $("#purchaseVat"+i+"").val('');
            
             if(category=='ITC Purchase'){
            var taxCGST=parseFloat($("#purchasetaxCgst"+i+"").val());            
            var taxValueCGST=(amount*taxCGST/100)||0;
            
            var taxSGST=parseFloat($("#purchasetaxSgst"+i+"").val());            
            var taxValueSGST=(amount*taxSGST/100)||0;
            
             var taxIGST=parseFloat($("#purchasetaxIgst"+i+"").val());            
            var taxValueIGST=(amount*taxIGST/100)||0;

            var cgstResult=taxValueCGST.toFixed(2);
            var sgstResult=taxValueSGST.toFixed(2);
            var igstResult=taxValueIGST.toFixed(2);
            
             if(isNaN(cgstResult))
               {
                  $("#purchaseCgst"+i+"").val(0.0);
               }
               else
               {
                    $("#purchaseCgst"+i+"").val(taxValueCGST.toFixed(2));
               } 
               if(isNaN(sgstResult))
               {
                 $("#purchaseVat"+i+"").val(0.0);            
               }
               else
               {
                  $("#purchaseVat"+i+"").val(taxValueSGST.toFixed(2));
               } 
               if(isNaN(igstResult))
               {
                 $("#purchaseIgst"+i+"").val(0.0);        
               }
               else
               {
                  $("#purchaseIgst"+i+"").val(taxValueIGST.toFixed(2));
               }
               
              }
              if(category=='Purchase from Composite Dealers'){
             var taxValue=0.00;
             var cgst=$("#purchaseCgst"+i+"").val(taxValue);
             var vat=$("#purchaseVat"+i+"").val(taxValue);
             var igst=$("#purchaseIgst"+i+"").val(taxValue);
              }
              if(category=='Purchases from unregistered Persons')
              {
                var taxCGST=parseFloat($("#purchasetaxCgst"+i+"").val());            
            var taxValueCGST=(amount*taxCGST/100)||0;
            
            var taxSGST=parseFloat($("#purchasetaxSgst"+i+"").val());            
            var taxValueSGST=(amount*taxSGST/100)||0;
            
             var taxIGST=parseFloat($("#purchasetaxIgst"+i+"").val());            
            var taxValueIGST=(amount*taxIGST/100)||0;

            var cgstResult=taxValueCGST.toFixed(2);
            var sgstResult=taxValueSGST.toFixed(2);
            var igstResult=taxValueIGST.toFixed(2);
            
             if(isNaN(cgstResult))
               {
                  $("#purchaseCgst"+i+"").val(0.0);
               }
               else
               {
                    $("#purchaseCgst"+i+"").val(taxValueCGST.toFixed(2));
               } 
               if(isNaN(sgstResult))
               {
                 $("#purchaseVat"+i+"").val(0.0);            
               }
               else
               {
                  $("#purchaseVat"+i+"").val(taxValueSGST.toFixed(2));
               } 
               if(isNaN(igstResult))
               {
                 $("#purchaseIgst"+i+"").val(0.0);        
               }
               else
               {
                  $("#purchaseIgst"+i+"").val(taxValueIGST.toFixed(2));
               }  
              }    
}
function purchasesCalculation(trLength) { 
    
    var category=$("#category").val();
    var totalAmount=0;
    var vat=0;
    var cgst=0;
    var igst=0;
    var assessValue=0;
    var AddLess=0;
    var TInvoiceAmount=0;
    var purchaseAssessValue=0;
    var ManAddLess=0;
     
    for (var i = 0; i < trLength; i++) { 
         var amount=parseFloat($("#purchaseAmount"+i+"").val() || 0);
         totalAmount=totalAmount+amount;     
         var purchaseCgst=parseFloat($("#purchaseCgst"+i+"").val() || 0);
         cgst=cgst+purchaseCgst;  
           //alert(cgst);           
         var purchaseVat=parseFloat($("#purchaseVat"+i+"").val() || 0);
           //alert(purchaseCgst);
         vat=vat+purchaseVat;
           //alert(vat);           
         var purchaseIgst=parseFloat($("#purchaseIgst"+i+"").val() || 0);          
         igst=igst+purchaseIgst;
    }
    $("#totalCgst").val(cgst.toFixed(2));
    $("#totalVat").val(vat.toFixed(2));
    $("#totalIgst").val(igst.toFixed(2));
    $("#Cgst").val(cgst.toFixed(2));
    $("#purchaseVat").val(vat.toFixed(2));
    $("#Igst").val(igst.toFixed(2));
    
    $("#purchaseAmount").val(totalAmount.toFixed(2));  
    $("#purchaseAssessValue").val(totalAmount.toFixed(2));
    var InvoiceAmount=parseFloat($("#InvoiceAmount").val() || 0);    
    var purchaseAssessValue=parseFloat($("#purchaseAssessValue").val() || 0);     
    var DefpurchaseCgst=parseFloat($("#totalCgst").val() || 0);         
    var DefpurchaseVat=parseFloat($("#totalVat").val() || 0);     
    var DefpurchaseIgst=parseFloat($("#totalIgst").val() || 0);   
    
    //seka removed code
//     AddLess=totalAmount-(totalAmount);
//     $("#AddLess ").val(AddLess.toFixed(2));
//     alert(AddLess);
     
      TInvoiceAmount=cgst+vat+igst+totalAmount;
     $("#TInvoiceAmount ").val(TInvoiceAmount.toFixed(2));
//     alert(TInvoiceAmount);
     var DefInAmount=(purchaseAssessValue-totalAmount);
     var DefInCgst=(DefpurchaseCgst-cgst);
     var DefInVat=(DefpurchaseVat-vat);
     var DefInIgst=(DefpurchaseIgst-igst);
     var DefADDLESS=DefInAmount+DefInCgst+DefInVat+DefInIgst+AddLess;
     $("#defAddless ").val(DefADDLESS.toFixed(2));   
//     TInvoiceAmount=cgst+vat+igst+AddLess+purchaseAssessValue;
//     $("#TInvoiceAmount ").val(TInvoiceAmount.toFixed(2));
     if(category=='Purchases from unregistered Persons')
     {
        var Addorless= ($("#InvoiceAmount").val()-$("#purchaseAmount").val())|| 0;       
        $("#AddLess").val(Addorless.toFixed(2));  
        $("#TInvoiceAmount ").val($("#InvoiceAmount").val().toFixed(2));
     }    
   
}


// new code for add or less
         function Addorless()
         { 
            var addorless=0;
            var invoiceTotal=0;
            var finalAmt=0;
            var cgst=0;
            var sgst=0;
            var igst=0;
            
            addorless=document.getElementById("AddLess").value;
//            alert(addorless);
            invoiceTotal=document.getElementById("purchaseAmount").value;
            cgst=document.getElementById("totalCgst").value;
            sgst=document.getElementById("totalVat").value;
            igst=document.getElementById("totalIgst").value;
            finalAmt=parseInt(invoiceTotal)+parseInt(addorless)+parseInt(cgst)+parseInt(sgst)+parseInt(igst);
            document.getElementById("TInvoiceAmount").value=finalAmt;
//            alert(finalAmt);
        }


// For Form Page of Purchase Calculation - start
function purchaseCalciForm(trLength,index) { 
    
    var category=$("#category").val();
    var totalAmount=0;
    var vat=0;
    var cgst=0;
    var igst=0;
    var assessValue=0;
    var AddLess=0;
    var TInvoiceAmount=0;
    var purchaseAssessValue=0;
    var ManAddLess=0;
     
    for (var i = 0; i < trLength; i++) {
          if("#purchaseAmount"+i+"" != "#purchaseAmount"+index+""){

        var amount=parseFloat($("#purchaseAmount"+i+"").val() || 0);
         totalAmount=totalAmount+amount;     
         var purchaseCgst=parseFloat($("#purchaseCgst"+i+"").val() || 0);
         cgst=cgst+purchaseCgst;  
           //alert(cgst);           
         var purchaseVat=parseFloat($("#purchaseVat"+i+"").val() || 0);
           //alert(purchaseCgst);
         vat=vat+purchaseVat;
           //alert(vat);           
         var purchaseIgst=parseFloat($("#purchaseIgst"+i+"").val() || 0);          
         igst=igst+purchaseIgst;
    } 

    }
   $("#totalCgst").val(cgst.toFixed(2));
    $("#totalVat").val(vat.toFixed(2));
    $("#totalIgst").val(igst.toFixed(2));
    $("#Cgst").val(cgst.toFixed(2));
    $("#purchaseVat").val(vat.toFixed(2));
    $("#Igst").val(igst.toFixed(2));
    
    $("#purchaseAmount").val(totalAmount.toFixed(2));  
    $("#purchaseAssessValue").val(totalAmount.toFixed(2));
    var InvoiceAmount=parseFloat($("#InvoiceAmount").val() || 0);    
    var purchaseAssessValue=parseFloat($("#purchaseAssessValue").val() || 0);     
    var DefpurchaseCgst=parseFloat($("#totalCgst").val() || 0);         
    var DefpurchaseVat=parseFloat($("#totalVat").val() || 0);     
    var DefpurchaseIgst=parseFloat($("#totalIgst").val() || 0);    
     AddLess=InvoiceAmount-(cgst+vat+igst+purchaseAssessValue);
     $("#AddLess ").val(AddLess.toFixed(2));
   
     var DefInAmount=(purchaseAssessValue-totalAmount);
     var DefInCgst=(DefpurchaseCgst-cgst);
     var DefInVat=(DefpurchaseVat-vat);
     var DefInIgst=(DefpurchaseIgst-igst);
     var DefADDLESS=DefInAmount+DefInCgst+DefInVat+DefInIgst+AddLess;
     $("#defAddless ").val(DefADDLESS.toFixed(2));   
     
     TInvoiceAmount=cgst+vat+igst+AddLess+purchaseAssessValue;
     $("#TInvoiceAmount ").val(TInvoiceAmount.toFixed(2));
     
}

function purchasesCalculationForm(trLength,index) {
    
    var category=$("#category").val();
    var totalAmount=0;
    var vat=0;
    var cgst=0;
    var igst=0;
    var assessValue=0;
    var AddLess=0;
    var TInvoiceAmount=0;
    var purchaseAssessValue=0;
    var ManAddLess=0;
     
    for (var i = 0; i < trLength; i++) {
        if("#purchaseAmount"+i+"" != "#purchaseAmount"+index+""){
         var amount=parseFloat($("#purchaseAmount"+i+"").val() || 0);
         totalAmount=totalAmount+amount;     
         var purchaseCgst=parseFloat($("#purchaseCgst"+i+"").val() || 0);
         cgst=cgst+purchaseCgst;  
           //alert(cgst);           
         var purchaseVat=parseFloat($("#purchaseVat"+i+"").val() || 0);
           //alert(purchaseCgst);
         vat=vat+purchaseVat;
           //alert(vat);           
         var purchaseIgst=parseFloat($("#purchaseIgst"+i+"").val() || 0);          
         igst=igst+purchaseIgst;
        }

    }
  $("#totalCgst").val(cgst.toFixed(2));
    $("#totalVat").val(vat.toFixed(2));
    $("#totalIgst").val(igst.toFixed(2));
    $("#Cgst").val(cgst.toFixed(2));
    $("#purchaseVat").val(vat.toFixed(2));
    $("#Igst").val(igst.toFixed(2));
    
    $("#purchaseAmount").val(totalAmount.toFixed(2));  
    $("#purchaseAssessValue").val(totalAmount.toFixed(2));
    var InvoiceAmount=parseFloat($("#InvoiceAmount").val() || 0);    
    var purchaseAssessValue=parseFloat($("#purchaseAssessValue").val() || 0);     
    var DefpurchaseCgst=parseFloat($("#totalCgst").val() || 0);         
    var DefpurchaseVat=parseFloat($("#totalVat").val() || 0);     
    var DefpurchaseIgst=parseFloat($("#totalIgst").val() || 0);    
     AddLess=InvoiceAmount-(cgst+vat+igst+purchaseAssessValue);
     $("#AddLess ").val(AddLess.toFixed(2));
     var DefInAmount=(purchaseAssessValue-totalAmount);
     var DefInCgst=(DefpurchaseCgst-cgst);
     var DefInVat=(DefpurchaseVat-vat);
     var DefInIgst=(DefpurchaseIgst-igst);
     var DefADDLESS=DefInAmount+DefInCgst+DefInVat+DefInIgst+AddLess;
     $("#defAddless ").val(DefADDLESS.toFixed(2));   
     
     TInvoiceAmount=cgst+vat+igst+AddLess+purchaseAssessValue;
     $("#TInvoiceAmount ").val(TInvoiceAmount.toFixed(2));
}




function purchaseTotal(){
   var category=$("#category").val(); 
   var assess = parseFloat($("#purchaseAssessValue").val() || 0);
   var cgst= parseFloat($("#totalCgst").val() || 0);
   var vat= parseFloat($("#totalVat").val() || 0);
   var igst= parseFloat($("#totalIgst").val() || 0);   
   var v1= assess+cgst+vat+igst;   
   var v2=parseFloat($("#InvoiceAmount").val() || 0);   
   var addLess=v2-v1;  
   $("#AddLess").val(addLess.toFixed(2));     
   var total=assess+cgst+vat+igst+addLess;
   $("#TInvoiceAmount ").val(total.toFixed(2));
   
   
   var purchaseTotalAmount=$("#purchaseAmount").val();
   var purchaseCgst=parseFloat($("#Cgst").val() || 0);         
   var purchaseVat=parseFloat($("#purchaseVat").val() || 0);     
   var purchaseIgst=parseFloat($("#Igst").val() || 0);     
   var diffamt=(assess-purchaseTotalAmount);
   
   var diffCgst=(cgst-purchaseCgst);
   var diffSgst=(vat-purchaseVat);
   var diffIgst=(igst-purchaseIgst);   
   var DefADDLESS=diffamt+diffCgst+diffSgst+diffIgst+addLess;
  // alert(diffamt+"/ "+diffCgst+"/"+diffSgst+"/"+diffIgst+"/"+addLess);
    
   $("#defAddless ").val(DefADDLESS.toFixed(2));
  
   if(category=='Purchases from unregistered Persons')
     {
        var Addorless= ($("#InvoiceAmount").val()-$("#purchaseAmount").val())|| 0;       
        $("#AddLess").val(Addorless.toFixed(2));  
        $("#TInvoiceAmount ").val($("#InvoiceAmount").val().toFixed(2));
     }  
}

function getUnit(trLength) { 
   
   for (var m = 0; m < trLength; m++) {
     var unit='#purchaseUnit'+m+'';  
     var itemCode= $("#purchaseItemCode"+m+"").val(); 
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

function getUnitPE(trLength) {   
   for (var m = 0; m < trLength; m++) {
     var unit='#purchaseEUnit'+m+'';  
     var itemCode= $("#purchaseEItemCode"+m+"").val(); 
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
  
     var unit='#purchaseUnit'+rownumber+'';  
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

// getunit when select Itemcode or name for row
function getUnitforPERow(rownumber,code) { 
  
     var unit='#purchaseEUnit'+rownumber+'';  
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

 // Purchase End

  // Epurchase Start
 function addRowforEPurchase() {  
    $.ajax({  
     type : "POST",   
     url : "purchaseE_addrow.html",
     data : $("#pEFrom").serialize(),
     success : function(response) {
     $('#addrowEDiv').empty().append(response);
     
    
     },  
     error : function(e) {  
      
      alert('Error: ' + e);   
     }  
    });  
   } 
   
   
    function FullrequirementEPurchase() {  
    $.ajax({  
     type : "POST",   
     url : "FullrequirementGrid.html",   
     data : $("#pEFrom").serialize(),
     success : function(response) {
     $('#addrowEDiv').empty().append(response);
    
     },  
     error : function(e) {  
      
      alert('Error: ' + e);   
     }  
    });  
   }





// Autocomplete for Purchase Estimate Item Code -- Start Code(Abi) //
function autoCompleteForPurchaseEstimate(i) {    
   var columnsItems1 = [{name:'Code', minWidth: '20%', valueField:'label'}, {name: 'Name', minWidth: '60%', valueField: 'itemName'},{name: 'Stock', minWidth: '10%', valueField: 'currentStock'},{name: 'Tax', minWidth: '10%', valueField: 'tr'}];  
   var category=$("#category").val();
    var taxIdless="";
   if(i==0){
    taxIdless=i;       
   }       
   else
   {
    taxIdless=i-1;   
   }    
   var purchaseTax=$("#epurchasesTax"+taxIdless+"").val();
   
    // Skip previous selected items start
       var previous_Item_code = new Array();
        $('input[id^="purchaseEItemCode"]').each(function(){
            previous_Item_code.push("'"+$(this).val()+"'");
        });                   
       var selected_items=previous_Item_code.join(',');
       selected_items = selected_items.replace(/,\s*$/, "");    
        // Skip previous selected items end
    $("#purchaseEItemCode"+i+"").mcautocomplete({
          showHeader: true,
  columns: columnsItems1,
  minLength:0,
  autoFocus: true,
    source: function(request, response) {
     
        $.ajax({
            url: "GetItemCodeForSales.html",
            dataType: "json",
            type: "GET",
            data: {
                term: request.term,
                tax: '',
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
                        rate:item.rate,
                        ep:item.ep, 
                        currentStock:item.currentStock,
                        basicPrice:item.basicPrice,
                        taxSgst:item.taxSgst,
                        taxCgst:item.taxCgst,
                        taxIgst:item.taxIgst,
                        tr:item.tr,
                        tp:item.tp,
                        rol:item.rol
                        
                           
                    }
  
                }));
     
             }
      
        })
    },
    select: function(event, ui) {   
        $('#purchaseENameOfTheItem'+i+'').val(ui.item.itemName);
        $('#purchaseEUnit'+i+'').val(ui.item.unit);
        $('#purchaseETpRate'+i+'').val(ui.item.tp);
        $('#epurchasesTax'+i+'').val(ui.item.tr); 
        var igstCalci = $('#final').val(); 
        if(igstCalci == 'yes')
        {
        $('#purchaseEtaxCgst'+i+'').val(0.0);  
        $('#purchaseEtaxSgst'+i+'').val(0.0);
        $('#purchaseEtaxIgst'+i+'').val(ui.item.taxIgst); 
        }
        else
        {
        $('#purchaseEtaxCgst'+i+'').val(ui.item.taxCgst);  
        $('#purchaseEtaxSgst'+i+'').val(ui.item.taxSgst);
        $('#purchaseEtaxIgst'+i+'').val(0.0);   
        }
      
          $(".txtMult input").keyup(multInputs);

       function multInputs() {
         
           var mult = 0;
           var amount=0;

           $("tr.txtMult").each(function () {
               
            var qty=parseFloat($("#purchaseEQty"+i+"").val() || 0);            
            var tp=parseFloat($("#purchaseETpRate"+i+"").val() || 0);            
            amount=qty*tp;
            $("#purchaseEAmount"+i+"").val(amount.toFixed(2));
            var taxCGST=parseFloat($("#purchaseEtaxCgst"+i+"").val()||0);
            var taxValueCGST=amount*taxCGST/100;            
            var taxSGST=parseFloat($("#purchaseEtaxSgst"+i+"").val()||0);
            var taxValueSGST=amount*taxSGST/100;            
            var taxIGST=parseFloat($("#purchaseEtaxIgst"+i+"").val()||0);
            var taxValueIGST=amount*taxIGST/100;
            if(isNaN(taxValueCGST))
               {
                  $("#purchaseECgst"+i+"").val(0.0);
               }
               else
               {
                    $("#purchaseECgst"+i+"").val(taxValueCGST.toFixed(2));
               } 
               if(isNaN(taxValueSGST))
               {
                 $("#purchaseEVat"+i+"").val(0.0);            
               }
               else
               {
                  $("#purchaseEVat"+i+"").val(taxValueSGST.toFixed(2));
               } 
               if(isNaN(taxValueIGST))
               {
                 $("#purchaseEIgst"+i+"").val(0.0);        
               }
               else
               {
                  $("#purchaseEIgst"+i+"").val(taxValueIGST.toFixed(2));
               }
              
           });  
                }
           getUnitforPERow(i,ui.item.itemCode);     
    },
    open: function(){
                                    $('.ui-autocomplete').css('width', '700px');
                                }

}).bind('focus', function(){ $(this).mcautocomplete("search"); } );
}

// Autocomplete for Purchase Estimate Item Name -- Start Code (Abi) //

function autoCompleteForPurchaseEstimateItemName(i) {    
   var columnsItems1 = [{name:'Name', minWidth: '60%', valueField:'itemName'}, {name: 'Code', minWidth: '20%', valueField: 'label'},{name: 'Stock', minWidth: '10%', valueField: 'currentStock'},{name: 'Tax', minWidth: '10%', valueField: 'tr'}];  
   var category=$("#category").val();
    var taxIdless="";
   if(i==0){
    taxIdless=i;       
   }       
   else
   {
    taxIdless=i-1;   
   }    
   var purchaseTax=$("#epurchasesTax"+taxIdless+"").val();
   
    // Skip previous selected items start
       var previous_Item_code = new Array();
        $('input[id^="purchaseEItemCode"]').each(function(){
            previous_Item_code.push("'"+$(this).val()+"'");
        });                   
       var selected_items=previous_Item_code.join(',');
       selected_items = selected_items.replace(/,\s*$/, "");    
        // Skip previous selected items end
    $("#purchaseENameOfTheItem"+i+"").mcautocomplete({
          showHeader: true,
  columns: columnsItems1,
  minLength:0,
  autoFocus: true,
    source: function(request, response) {
      
        $.ajax({
            url: "GetItemNameForSales.html",
            dataType: "json",
            type: "GET",
            data: {
                term: request.term,
                tax: '',
                items:selected_items
            },
            success: function(data){
                response( $.map( data, function( item ) {
                  
                    return {
                        value: item.itemName,   
                        label: item.itemCode,
                        itemCode:item.itemCode,
                        itemName:item.itemName,
                        unit:item.unit,
                        rate:item.rate,
                        ep:item.ep, 
                        currentStock:item.currentStock,
                        basicPrice:item.basicPrice,
                        tr:item.tr,
                        taxSgst:item.taxSgst,
                        taxCgst:item.taxCgst,
                        tp:item.tp
                        
                           
                    }
  
                }));
     
             }
      
        })
    },
    select: function(event, ui) {   
        $('#purchaseEItemCode'+i+'').val(ui.item.itemCode);
        $('#purchaseEUnit'+i+'').val(ui.item.unit);
        $('#purchaseETpRate'+i+'').val(ui.item.tp);
        var igstCalci = $('#final').val(); 
        if(igstCalci == 'yes')
        {
        $('#purchaseEtaxCgst'+i+'').val(0.0);  
        $('#purchaseEtaxSgst'+i+'').val(0.0);
        $('#purchaseEtaxIgst'+i+'').val(ui.item.taxIgst); 
        }
        else
        {
        $('#purchaseEtaxCgst'+i+'').val(ui.item.taxCgst);  
        $('#purchaseEtaxSgst'+i+'').val(ui.item.taxSgst);
        $('#purchaseEtaxIgst'+i+'').val(0.0);   
        }
        
          $(".txtMult input").keyup(multInputs);

       function multInputs() {
         
           var mult = 0;
           var amount=0;

           $("tr.txtMult").each(function () {
               
            var qty=parseFloat($("#purchaseEQty"+i+"").val() || 0);            
            var tp=parseFloat($("#purchaseETpRate"+i+"").val() || 0);            
            amount=qty*tp;
             
            $("#purchaseEAmount"+i+"").val(amount.toFixed(2));           
            var taxCGST=parseFloat($("#purchaseEtaxCgst"+i+"").val()||0);
            var taxValueCGST=amount*taxCGST/100;            
            var taxSGST=parseFloat($("#purchaseEtaxSgst"+i+"").val()||0);
            var taxValueSGST=amount*taxSGST/100;            
            var taxIGST=parseFloat($("#purchaseEtaxIgst"+i+"").val()||0);
            var taxValueIGST=amount*taxIGST/100;
            if(isNaN(taxValueCGST))
               {
                  $("#purchaseECgst"+i+"").val(0.0);
               }
               else
               {
                    $("#purchaseECgst"+i+"").val(taxValueCGST.toFixed(2));
               } 
               if(isNaN(taxValueSGST))
               {
                 $("#purchaseEVat"+i+"").val(0.0);            
               }
               else
               {
                  $("#purchaseEVat"+i+"").val(taxValueSGST.toFixed(2));
               } 
               if(isNaN(taxValueIGST))
               {
                 $("#purchaseEIgst"+i+"").val(0.0);        
               }
               else
               {
                  $("#purchaseEIgst"+i+"").val(taxValueIGST.toFixed(2));
               }
              
              
           });  
                }
           getUnitforPERow(i,ui.item.itemCode);     
    },
    open: function(){
                                    $('.ui-autocomplete').css('width', '700px');
                                }

}).bind('focus', function(){ $(this).mcautocomplete("search"); } );
}


function onChangeEQty(i) {
            var qty=parseFloat($("#purchaseEQty"+i+"").val() || 0);            
            var tp=parseFloat($("#purchaseETpRate"+i+"").val() || 0);            
            var amount=(qty*tp) || 0;
             
            $("#purchaseEAmount"+i+"").val(amount.toFixed(2));
            var taxCGST=parseFloat($("#purchaseEtaxCgst"+i+"").val()||0);
            var taxValueCGST=amount*taxCGST/100;            
            var taxSGST=parseFloat($("#purchaseEtaxSgst"+i+"").val()||0);
            var taxValueSGST=amount*taxSGST/100;            
            var taxIGST=parseFloat($("#purchaseEtaxIgst"+i+"").val()||0);
            var taxValueIGST=amount*taxIGST/100;
            if(isNaN(taxValueCGST))
               {
                  $("#purchaseECgst"+i+"").val(0.0);
               }
               else
               {
                    $("#purchaseECgst"+i+"").val(taxValueCGST.toFixed(2));
               } 
               if(isNaN(taxValueSGST))
               {
                 $("#purchaseEVat"+i+"").val(0.0);            
               }
               else
               {
                  $("#purchaseEVat"+i+"").val(taxValueSGST.toFixed(2));
               } 
               if(isNaN(taxValueIGST))
               {
                 $("#purchaseEIgst"+i+"").val(0.0);        
               }
               else
               {
                  $("#purchaseEIgst"+i+"").val(taxValueIGST.toFixed(2));
               }
}

function purchasesECalculation(trLength) { 
    var totalAmount=0;
    var vat=0;
    var cgst=0; 
    var igst=0;
    for (var i = 0; i < trLength; i++) {
          
         var amount=parseFloat($("#purchaseEAmount"+i+"").val() || 0);
         totalAmount=totalAmount+amount;
         var purchaseCgst=parseFloat($("#purchaseECgst"+i+"").val() || 0);
         cgst=cgst+purchaseCgst;  
         var purchaseIgst=parseFloat($("#purchaseEIgst"+i+"").val() || 0);
         igst=igst+purchaseIgst; 
         var purchaseVat=parseFloat($("#purchaseEVat"+i+"").val() || 0);
         vat=vat+purchaseVat;
   
    }
    $("#ECgst").val(cgst.toFixed(2));
    $("#EIgst").val(igst.toFixed(2));
    $("#purchaseEVat").val(vat.toFixed(2));
    $("#purchaseEAmount").val(totalAmount.toFixed(2));  
}
// Re-Calculation for Add Row
function purchasesReCalculationAddRow(trLength,index) { 
    
    var totalAmount=0;
    var vat=0;
    var cgst=0; 
    var igst=0;
    
    for (var i = 0; i < trLength; i++) {
        if("#purchaseEAmount"+i+"" != "#purchaseEAmount"+index+""){         
           
         var amount=parseFloat($("#purchaseEAmount"+i+"").val() || 0);
         totalAmount=totalAmount+amount;
         var purchaseCgst=parseFloat($("#purchaseECgst"+i+"").val() || 0);
         cgst=cgst+purchaseCgst;  
         var purchaseIgst=parseFloat($("#purchaseEIgst"+i+"").val() || 0);
         igst=igst+purchaseIgst; 
         var purchaseVat=parseFloat($("#purchaseEVat"+i+"").val() || 0);
         vat=vat+purchaseVat;
        }
    }
    $("#ECgst").val(cgst.toFixed(2));
    $("#EIgst").val(igst.toFixed(2));
    $("#purchaseEVat").val(vat.toFixed(2));
    $("#purchaseEAmount").val(totalAmount.toFixed(2));  
}
function onChangeFullQty(i) {
            var qty=parseInt($("#purchaseEQty"+i+"").val() || 0);            
            var tp=parseFloat($("#purchaseETpRate"+i+"").val() || 0);            
            var amount=qty*tp;
             
            $("#purchaseEAmount"+i+"").val(amount.toFixed(2));
           var taxCGST=parseFloat($("#purchaseEtaxCgst"+i+"").val()||0);
            var taxValueCGST=amount*taxCGST/100;            
            var taxSGST=parseFloat($("#purchaseEtaxSgst"+i+"").val()||0);
            var taxValueSGST=amount*taxSGST/100;            
            var taxIGST=parseFloat($("#purchaseEtaxIgst"+i+"").val()||0);
            var taxValueIGST=amount*taxIGST/100;
            if(isNaN(taxValueCGST))
               {
                  $("#purchaseECgst"+i+"").val(0.0);
               }
               else
               {
                    $("#purchaseECgst"+i+"").val(taxValueCGST.toFixed(2));
               } 
               if(isNaN(taxValueSGST))
               {
                 $("#purchaseEVat"+i+"").val(0.0);            
               }
               else
               {
                  $("#purchaseEVat"+i+"").val(taxValueSGST.toFixed(2));
               } 
               if(isNaN(taxValueIGST))
               {
                 $("#purchaseEIgst"+i+"").val(0.0);        
               }
               else
               {
                  $("#purchaseEIgst"+i+"").val(taxValueIGST.toFixed(2));
               }
}

function purchasesFullCalculation(trLength) { 
    var totalAmount=0;
    var vat=0;
    var cgst=0; 
    var igst=0;
    for (var i = 0; i < trLength; i++) {
          
         var amount=parseFloat($("#purchaseEAmount"+i+"").val() || 0);
         totalAmount=totalAmount+amount;
         var purchaseCgst=parseFloat($("#purchaseECgst"+i+"").val() || 0);
         cgst=cgst+purchaseCgst;  
         var purchaseIgst=parseFloat($("#purchaseEIgst"+i+"").val() || 0);
         igst=igst+purchaseIgst; 
         var purchaseVat=parseFloat($("#purchaseEVat"+i+"").val() || 0);
         vat=vat+purchaseVat;
   
    }
    $("#ECgst").val(cgst.toFixed(2));
    $("#EIgst").val(igst.toFixed(2));
    $("#purchaseEVat").val(vat.toFixed(2));
    $("#purchaseEAmount").val(totalAmount.toFixed(2));  
  
}
 // EPurchase End
 
 
 
//Reversal of ITC Start
function onChangeRQty(i) {  
   
            var Rqty=parseInt($("#reversalofitcsreturnQty"+i+"").val() || 0);            
            var ep=parseFloat($("#reversalofitcsEp"+i+"").val() || 0);            
            var amount=Rqty*ep;
             
            $("#reversalofitcsAmount"+i+"").val(amount.toFixed(2));
            var taxCGST=parseFloat($("#purchaseEtaxCgst"+i+"").val());
            var taxValueCGST=amount*taxCGST/100;
            
            var taxSGST=parseFloat($("#purchaseEtaxSgst"+i+"").val());
            var taxValueSGST=amount*taxSGST/100;

            $("#purchaseECgst"+i+"").val(taxValueCGST.toFixed(2));
            $("#purchaseEVat"+i+"").val(taxValueSGST.toFixed(2));
}

function reversalCalculation(trLength) { 
    
    var totalAmount=0;
    var vat=0;
    var cgst=0;
    var AddLess=0;
    var RInvoiceAmount=0;
    var RAssessValue=0;
   
    
     
    for (var i = 0; i < trLength; i++) {   
        
        onChangeRQty(i)
          
         var amount=parseFloat($("#reversalofitcsAmount"+i+"").val() || 0);
        totalAmount=totalAmount+amount;

            var rCgst=parseFloat($("#reversalofitcsCgst"+i+"").val() || 0);
           cgst=cgst+rCgst;  
           
           var rVat=parseFloat($("#reversalofitcsVat"+i+"").val() || 0);
           vat=vat+rVat;
 

    }
    $("#RCgst").val(cgst.toFixed(2));
    $("#RVat").val(vat.toFixed(2));
    $("#reversalofitcsAmount").val(totalAmount.toFixed(2));  
    
    var RInvoiceAmount=parseFloat($("#RInvoiceAmount").val() || 0);
    
    var RAssessValue=parseFloat($("#RAssessValue").val() || 0);
    
     AddLess=RInvoiceAmount-(cgst+vat+RAssessValue);
     $("#RAddLess ").val(AddLess.toFixed(2));
     
     RInvoiceAmount=cgst+vat+AddLess+RAssessValue;
     $("#RTInvoiceAmount ").val(RInvoiceAmount.toFixed(2));

   
}
//function checkpop()
//{  
//     var pos=$('#tinNo').val();
//     if(pos=='')
//     {
//      $("#tinNo").focus();   
//      alert("Select POP");
//     }    
//     
//}
 function isNumberKey(e)
       {
        var keyCode = (e.which) ? e.which : e.keyCode;
      //  alert("keyCode "+keyCode);
        if(keyCode!=9)
        {
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
       }
//Reversal of ITC End
//
//$('#myforms').submit(function (e)
//{    
// 
//      
//         var ChangeEP= $('#ep').val();
//         var ItenIndex= $('#itemIndexValue').val();  
//         var trlength= $('#itemTableValue').val(); 
//         
//     
//        $('#purchaseEp'+ItenIndex+'').val(ChangeEP);
//        
//      $('#myforms').hide();          
//     $('#close').click();
//     $('#purchaseEp'+ItenIndex+'').focus();
//      
//   
//     e.preventDefault();
//     }



