function myFunction(r,totalRow,index) { 
    var tableSize = $("#example1 td").closest("tr").length;
    
        
  
    if(tableSize > 1){
        var id=$('#hiddenItemId'+index+'').val();
       
        if(id!=""){
            $.ajax({  
                 type : "GET",   
                 url : "DeleteItemById.html",   
                 data : {itemId:id},
                 success : function(response) {
                    var i = r.parentNode.parentNode.rowIndex;
                    kotCalculationAfterDelete(totalRow,index);
                    document.getElementById("example1").deleteRow(i);
                },  
                 error : function(e) {  

                  alert('Error: ' + e);   
                 }  
                }); 
        }
        else{
            var i = r.parentNode.parentNode.rowIndex;
            kotCalculationAfterDelete(totalRow,index);
            document.getElementById("example1").deleteRow(i);
        }
   //salesCalculation(i-2);
   
    }

}


function myFunctionDeleteFromSales(r,totalRow,index) { 
    var tableSize = $("#example1 td").closest("tr").length;
    
        
  
    if(tableSize > 1){
        var id=$('#hiddenItemId'+index+'').val();
        if(id!=""){
            $.ajax({  
                 type : "GET",   
                 url : "DeleteItemByIdfromKOTList.html",   
                 data : {itemId:id},
                 success : function(response) {
                    var i = r.parentNode.parentNode.rowIndex;
                    kotCalculationAfterDelete(totalRow,index);
                    document.getElementById("example1").deleteRow(i);
                },  
                 error : function(e) {  

                  alert('Error: ' + e);   
                 }  
                }); 
        }
        else{
            var i = r.parentNode.parentNode.rowIndex;
            kotCalculationAfterDelete(totalRow,index);
            document.getElementById("example1").deleteRow(i);
        }
   //salesCalculation(i-2);
   
    }
  
  
  
}
function addRowforKOTFromSales() {  
   
    $.ajax({  
     type : "POST",   
     url : "SalesKOTedit.html",   
     data : $("#kotForm").serialize(),
     dataType: 'html',
     success : function(response) {
     $('#myModal').empty().append(response);
     },  
     error : function(e) {  

      alert('Error: ' + e);   
     }  
    });
    $('#myModal').modal('show');
}

function editKotFromSalesInvoiceList(kotId){
    $.ajax({  
            type : "get",   
            url : "EditKOTPopUp.html",   
            data : "kotId=" + kotId,
             dataType: 'html',
             success : function(response) {
             $('#myModal').html(response);
             },  
             error : function(e) {  

              alert('Error: ' + e);   
             }  
        });
        
        $('#myModal').modal('show');
    }
    
function voidKotFromSalesInvoiceList(billNo){
    alert(billNo);
//    $.ajax({  
//        type : "get",   
//        url : "void.html",   
//        data : "invoice=" + billNo,
//        success : function(response) {
//            alert("Bill Voided");
//        },  
//        error : function(e) {  
//            alert('Error: ' + e);   
//        }  
//    });
}   

function emptyKOTForm(tableName) {  
    $.ajax({  
        type : "POST",   
        url : "EmptyKOTForm.html",   
        data : $("#kotForm").serialize(),
        dataType: 'html',
        success : function(response) {
        $('#kotFormDiv').html(response);
        },  
        error : function(e) {  

         alert('Error: ' + e);   
        }  
    });
}

function getWaiterNameByIdfromsalesinvoicelist(waiterId){
    if(waiterId!=""){
        $.ajax({  
             type : "GET",   
             url : "GetWaiterNameById.html",   
             data : {waiterId:waiterId},
             success : function(response) {
                 $('#waiterName').val(response);
             },  
             error : function(e) {  

    //          alert('Error: ' + e);   
             }  
            }); 
        }
}

function getSelectedTableName(tableName){
    $('#selectedTables').val(tableName);
    $('#hiddenTableName').val(tableName);
}

//$(document).ready(function() {
//
//    $('#dynamic-table').DataTable();
//    $('#example1').DataTable();
//    $('input:button').click(function() {
//        $('input:button').removeClass("active");
//        $(this).addClass("active");
//    });
//
//}); 

function getTables(category){
    $('#kotListDiv').hide();
    $('input:button').removeClass("active");
    $(this).addClass("active");
    $.ajax({  
        type : "get",   
        url : "AjaxTableList.html",
//                dataType: "JSON",
//                contentType: "application/json; charset=UTF-8",
        data : "category=" + category,
        success : function(response) {
            
           $("#tableNames").html(response);
        },  
        error : function(e) {  
         alert('Error: ' + e);   
        }  
    }); 
    $('#hiddenTableCategory').val(category);
}

function AjaxKOTList(tableName){
     
    $.ajax({  
        type : "get",   
        url : "AjaxKOTList.html",
//                dataType: "JSON",
//                contentType: "application/json; charset=UTF-8",
        data : "tableName=" + tableName,
        success : function(response) {
           $('#kotListDiv').show(); 
           $("#kotListDiv").html(response);
        },  
        error : function(e) {  
         alert('Error: ' + e);   
        }  
    });
}

function checkTableSelectionFromSales(i){
    var selectedTable=$("#selectedTables").val();
    var kot=$("#kotNo").val();
    var waiterId=$("#waiterId").val();
    if(selectedTable.length===0){
        alert("Please Select Table");
    }
    else if(kot.length===0){
        alert("Please Enter kot");
    }
    else if(waiterId.length===0){
        alert("Please Enter Waiter ID");
    }
    else{
        autoCompleteForKOTfromSales(i);
    }
}

//function autoCompleteForKOTfromSales(i) {
//    var columnsItems = [{name: 'Code', minWidth: '20%', valueField: 'label'},{name:'Name', minWidth: '60%', valueField:'itemName'}];
//       // Skip previous selected items start
//       var previous_Item_code = new Array();
//        $('input[id^="itemcode"]').each(function(){
//            previous_Item_code.push("'"+$(this).val()+"'");
//        });                   
//       var selected_items=previous_Item_code.join(',');
//       selected_items = selected_items.replace(/,\s*$/, "");     
//        // Skip previous selected items end
//    $("#itemcode"+i+"").mcautocomplete({
//        appendTo: '#myModal',
//        showHeader: true,
//        columns: columnsItems,
//        minLength:0,
//        autoFocus: true,
//        source: function(request, response) {
//        $.ajax({
//            url: "GetItemCodeForKOT.html",
//            dataType: "json",
//            type: "GET",
//            data: {
//                term: request.term,
//                tax: "",
//                items:selected_items
//            },
//            success: function(data){
//                response( $.map( data, function( item ) {
//                  
//                    return {
//                        value: item.itemCode,   
//                        label: item.itemCode,
//                        itemCode:item.itemCode,
//                        itemName:item.itemName,
//                        unit:item.unit,
//                        altUnit:item.altUnit,
//                        rate:item.rate,
//                        currentStock:item.currentStock,
//                        vap:item.vap,
//                        vapAlt:item.vapAlt,
//                        vbp:item.vbp,
//                        vbpAlt:item.vbpAlt,
//                        cap:item.cap,
//                        capAlt:item.capAlt,
//                        cbp:item.cbp,
//                        cbpAlt:item.cbpAlt,
//                        basicPrice:item.basicPrice,
//                        tr:item.tr,
//                        mc:item.mc,
//                        mca:item.mca,
//                        taxSgst:item.taxSgst,
//                        taxCgst:item.taxCgst,
//                        taxIgst:item.taxIgst,
//                        cp:item.cp,
//                        whereTotalunit:item.totalUnit
//                    };
//  
//                }));
//     
//             }
//      
//        });
//    },
//     select: function(event, ui) {   
//        $('#itemName'+i+'').val(ui.item.itemName);
//        $('#unit'+i+'').val(ui.item.unit);
//        $('#quantity'+i+'').val(ui.item.currentStock);
//        $('#rate'+i+'').val(ui.item.cbp);
//        
//        $('#auditRate'+i+'').val(ui.item.cap);
//        $('#taxCgstPercent'+i+'').val(ui.item.taxCgst);
//        $('#taxSgstPercent'+i+'').val(ui.item.taxSgst);
//        
//        
//        getUnitforRowKOT(i,ui.item.itemCode);
//        
//        $('#cap'+i+'').val("");
//        $('#taxCgst'+i+'').val("");
//        $('#taxSgst'+i+'').val("");
//    },    
//    open: function(){
//                $('.ui-autocomplete').css('width', '400px');
//                }
//    }).bind('focus', function(){ $(this).mcautocomplete("search");} );
//}        

function autoCompleteForKOTfromSales(i) {
       var columnsItems = [{name: 'Code', minWidth: '20%', valueField: 'label'},{name:'Name', minWidth: '60%', valueField:'itemName'}];
       // Skip previous selected items start
       var previous_Item_code = new Array();
//        $('input[id^="itemcode"]').each(function(){
//            previous_Item_code.push("'"+$(this).val()+"'");
//        });                   
       var selected_items=previous_Item_code.join(',');
       selected_items = selected_items.replace(/,\s*$/, "");     
        // Skip previous selected items end
        $("#itemcode"+i+"").autocomplete({
//            source: availableTags
            
        showHeader: true,
        columns: columnsItems,
        minLength:0,
        autoFocus: true,
        source: function(request, response) {
        $.ajax({
            url: "GetItemCodeForKOT.html",
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
                        cbpAlt:item.cbpAtl,
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
           $('#itemName'+i+'').val(ui.item.itemName);
           $('#unit'+i+'').val(ui.item.altUnit);
           $('#quantity'+i+'').val(ui.item.currentStock);
           $('#rate'+i+'').val(ui.item.cbpAlt);

           $('#auditRate'+i+'').val(ui.item.capAlt);
           $('#taxCgstPercent'+i+'').val(ui.item.taxCgst);
           $('#taxSgstPercent'+i+'').val(ui.item.taxSgst);


           getUnitforRowKOT(i,ui.item.itemCode);

           $('#cap'+i+'').val("");
           $('#taxCgst'+i+'').val("");
           $('#taxSgst'+i+'').val("");
       }
    });
         $('#myModal').modal('show');
         $("#itemcode"+i+"").autocomplete( "option", "appendTo", ".eventInsForm" );
        
    }

function getUnitforRowKOT(rownumber,code) { 
    
     var unit='#unit'+rownumber+'';  
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
                $(unit).append('<option value="'+row.idUnit+'" >'+row.unitSymbol+'</option>');
                else
                $(unit).append('<option value="'+row.idUnit+'" selected="selected">'+row.unitSymbol+'</option>');    
                i++;  
             });
         },  
         error : function(e) {  

          alert('Error: ' + e);   
         }  
        });  
    
    }
  
} 

function changeRate(i) { 
    var unit=$("#unit"+i+"").val();
    var itemCode=$("#itemcode"+i+"").val();
    $.ajax({  
        type : "POST",   
        url : "GetItemByItemCode.html",   
        dataType:"json",
        data : {itemCode:itemCode},
        success : function(response) {
                if(unit==response.unit){
                   $("#rate"+i+"").val(response.cbp);
               }          
               if(unit==response.altUnit){
                   $("#rate"+i+"").val(response.cbpAtl);
               }
        }
    });
}    

function getTotal(i){
    var qty=$('#quantity'+i+'').val();
    var rate=$('#rate'+i+'').val();
    var auditRate=$('#auditRate'+i+'').val();
    var cgstPercent=$('#taxCgstPercent'+i+'').val();
    var sgstPercent=$('#taxSgstPercent'+i+'').val();
    var totalcustomerRate=parseFloat(qty*rate);
    var totalAuditRate=parseFloat(qty*auditRate)
    $('#cap'+i+'').val(parseFloat(totalcustomerRate).toFixed(2));
    $('#vap'+i+'').val(parseFloat(totalAuditRate).toFixed(2));
    $('#taxCgst'+i+'').val(parseFloat((totalcustomerRate*cgstPercent)/100).toFixed(2));
    $('#taxSgst'+i+'').val(parseFloat((totalcustomerRate*sgstPercent)/100).toFixed(2));
}

function getUnit(trLength) { 
   for (var m = 0; m < trLength; m++) {
     var unit='#unit'+m+'';  
     var itemCode= $("#itemcode"+m+"").val(); 
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

function kotCalculationAfterDelete(trLength,index) { 
   
    var kotAmount=0;
    var cgst=0;
    var sgst=0.00;
    
    for (var i = 0; i < trLength; i++) {
        if("#totalKOTValue"+i+"" != "#totalKOTValue"+index+""){
        var amount=parseFloat($("#cap"+i+"").val() || 0);
        kotAmount=kotAmount+amount;
        var kotCgst=parseFloat($("#taxCgst"+i+"").val()|| 0);
        cgst=cgst+kotCgst;
        var kotSgst=parseFloat($("#taxSgst"+i+"").val()|| 0);
        sgst=sgst+kotSgst;
       //onChangeQtyOrRate(i)
    
        }
    }    
    $("#totalCGST").val(cgst.toFixed(2));
    $("#totalSGST").val(sgst.toFixed(2));
    $("#totalKOTValue").val(kotAmount.toFixed(2));   
    
}
function kotCalculation(trLength) { 
   
    var kotAmount=0;
    var cgst=0;
    var sgst=0.00;
    
    for (var i = 0; i < trLength; i++) {
        var amount=parseFloat($("#cap"+i+"").val() || 0);
        kotAmount=kotAmount+amount;
        var kotCgst=parseFloat($("#taxCgst"+i+"").val()|| 0);
        cgst=cgst+kotCgst;
        var kotSgst=parseFloat($("#taxSgst"+i+"").val()|| 0);
        sgst=sgst+kotSgst;
       //onChangeQtyOrRate(i)
    
        
    }    
    $("#totalCGST").val(cgst.toFixed(2));
    $("#totalSGST").val(sgst.toFixed(2));
    $("#totalKOTValue").val(kotAmount.toFixed(2));   
    
}

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
          
});
/* --- Autocomplete to bring three columns in single textbox As Table --- End Code (Abi) */