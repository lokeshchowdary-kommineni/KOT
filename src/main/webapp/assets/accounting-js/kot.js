function validateForm(){
    var totalKotVal=$("#totalKOTValue").val();
    var selectedTable=$("#selectedTables").val();
    var kot=$("#kotNo").val();
    var waiterId=$("#waiterId").val();
    if(selectedTable.length===0){
        alert("Please Select Table");
        return false;
    }
    if(kot.length===0){
        alert("Please Enter kot");
        return false;
    }
    if(waiterId.length===0){
        alert("Please Enter Waiter ID");
        return false;
    }
    if(totalKotVal=="0.00"||totalKotVal==""){
        alert("Please add Items.");
        return false;
    }
    else{
        return true;
    }
    
}

function getWaiterNameById(waiterId){
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

function myFunctionDelete(r,totalRow,index) { 
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

function addRowforKOT() {  
    $.ajax({  
     type : "POST",   
     url : "KOT_AddRow.html",   
     data : $("#kotForm").serialize(),
     dataType: 'html',
     success : function(response) {
     $('#kotFormDiv').empty().append(response);
     },  
     error : function(e) {  

      alert('Error: ' + e);   
     }  
    });  
}

function addRowforKOTFromKOTList() {
    $.ajax({  
     type : "POST",   
     url : "EditKOTFromKOTList_AddRow.html",   
     data : $("#kotForm").serialize(),
     dataType: 'html',
     success : function(response) {
     $('#kotFormDiv').empty().append(response);
     },  
     error : function(e) {  

      alert('Error: ' + e);   
     }  
    });  
}

function editKOT(kotId) {
    $.ajax({  
     type : "get",   
     url : "EditKOT.html",   
//     data : $("#kotForm").serialize(),
    data : "kotId=" + kotId,
     dataType: 'html',
     success : function(response) {
     $('#kotFormDiv').html(response);
     },  
     error : function(e) {  

      alert('Error: ' + e);   
     }  
    });  
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

function getSelectedTableName(tableName){
    $('#selectedTables').val(tableName);
    $('#hiddenTableName').val(tableName);
    getWaiterNameOnselectTable(tableName);
}

function getWaiterNameOnselectTable(tableName){
    $.ajax({  
        type : "get",   
        url : "AjaxWaiterName.html",
        data : "tableName=" + tableName,
        success : function(response) {
           var split=response.split(",");
           $("#waiterId").val(split[0]);
           $("#waiterName").val(split[1]);
           
        },  
        error : function(e) {  
         alert('Error: ' + e);
         
        }  
    }); 
}
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

function checkTableSelection(i){
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
        autoCompleteForKOT(i);
    }
}

function autoCompleteForKOT(i) {
    var columnsItems = [{name: 'Code', minWidth: '20%', valueField: 'label'},{name:'Name', minWidth: '60%', valueField:'itemName'}];
       // Skip previous selected items start
       var previous_Item_code = new Array();
//        $('input[id^="itemcode"]').each(function(){
//            previous_Item_code.push("'"+$(this).val()+"'");
//        });                   
       var selected_items=previous_Item_code.join(',');
       selected_items = selected_items.replace(/,\s*$/, "");     
        // Skip previous selected items end
    $("#itemcode"+i+"").mcautocomplete({
        
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
        
        
        
    },    
    open: function(){
                $('.ui-autocomplete').css('width', '400px');
            }
    }).bind('focus', function(){ $(this).mcautocomplete("search");$('select')[$('select').index(this)+1].focus();} );
}

function autoCompleteForWaiter() {
    var columnsItems = [{name: 'Waiter Name', minWidth: '70%', valueField: 'label'},{name:'Waiter ID', minWidth: '30%', valueField:'waiterId'}];
       // Skip previous selected items start
    $("#waiterName").mcautocomplete({
        
        showHeader: true,
        columns: columnsItems,
        minLength:0,
        autoFocus: true,
        source: function(request, response) {
        $.ajax({
            url: "GetWaiterCode.html",
            dataType: "json",
            type: "GET",
            data: {
                term: request.term
            },
            success: function(data){
                response( $.map( data, function( item ) {
                  
                    return {
                        value: item.waiterName,   
                        label: item.waiterName,
                        waiterId:item.waiterId,
                        waiterName:item.waiterName
                    };
  
                }));
     
             }
      
        });
    },
     select: function(event, ui) {   
        $('#waiterId').val(ui.item.waiterId);
        $('#waiterName').val(ui.item.waiterName);
    },    
    open: function(){
                $('.ui-autocomplete').css('width', '300px');
            }
    }).bind('focus', function(){ $(this).mcautocomplete("search");} );
    
}


function autoCompleteForTable() {
    var columnsItems = [{name: 'Table Name', minWidth: '70%', valueField: 'label'}];
       // Skip previous selected items start
    $("#selectedTables").mcautocomplete({
        
        showHeader: true,
        columns: columnsItems,
        minLength:0,
        autoFocus: true,
        source: function(request, response) {
        $.ajax({
            url: "GetTableNames.html",
            dataType: "json",
            type: "GET",
            data: {
                term: request.term
            },
            success: function(data){
                response( $.map( data, function( item ) {
                  
                    return {
                        label: item.tableName
                    };
  
                }));
     
             }
      
        });
    },
     select: function(event, ui) {   
        $('#selectedTables').val(ui.item.label);
        getWaiterNameOnselectTable(ui.item.label);
        AjaxKOTList(ui.item.label);
        $('#hiddenTableName').val(ui.item.label);
    },    
    open: function(){
                $('.ui-autocomplete').css('width', '300px');
            }
    }).bind('focus', function(){ $(this).mcautocomplete("search");} );
    
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
//    alert("hello");
    var qty=$('#quantity'+i+'').val();
    var rate=$('#rate'+i+'').val();
    var auditRate=$('#auditRate'+i+'').val();
    var cgstPercent=parseFloat($('#taxCgstPercent'+i+'').val());
    var sgstPercent=parseFloat($('#taxSgstPercent'+i+'').val());
    var tax=parseFloat(cgstPercent+sgstPercent);
    var totalcustomerRate=parseFloat(qty*rate);
    var taxamount=(totalcustomerRate*tax)/(100+tax);
    var totalAuditRate=parseFloat(qty*auditRate);
    var taxCgst=parseFloat((taxamount*cgstPercent)/tax).toFixed(3);
    var taxSgst=parseFloat((taxamount*sgstPercent)/tax).toFixed(3);
    $('#cap'+i+'').val(parseFloat(totalcustomerRate).toFixed(2));
    $('#vap'+i+'').val(parseFloat(totalAuditRate).toFixed(2));
    if(isNaN(taxCgst))
    {
        $("#taxCgst"+i+"").val(0.0);                      
    }
    else
    {
        $('#taxCgst'+i+'').val(parseFloat((taxamount*cgstPercent)/tax).toFixed(3));  
    } 
    if(isNaN(taxSgst))
    {
        $("#taxSgst"+i+"").val(0.0);                      
    }
    else
    {
        $('#taxSgst'+i+'').val(parseFloat((taxamount*sgstPercent)/tax).toFixed(3));
    }
    
    
    
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

function kotCalculation(trLength,index) { 
//   alert("hi");
    var kotAmount=0;
    var cgst=0;
    var sgst=0.00;
    
    for (var i = 0; i < trLength; i++) {   
        var amount=parseFloat($("#cap"+i+"").val() || 0);
        kotAmount=kotAmount+amount;
        var kotCgst=parseFloat($("#taxCgst"+i+"").val()|| 0);
        cgst=cgst+kotCgst;
//        alert(cgst);
        var kotSgst=parseFloat($("#taxSgst"+i+"").val()|| 0);
        sgst=sgst+kotSgst;
//         alert(sgst);
       //onChangeQtyOrRate(i)
    
    }    
    $("#totalCGST").val(cgst.toFixed(2));
    $("#totalSGST").val(sgst.toFixed(2));
    $("#totalKOTValue").val(kotAmount.toFixed(2));   
    
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

                
// var columns1 = [{name:'Name', minWidth: '40%', valueField:'label'}, {name: 'Code', minWidth: '20%', valueField: 'code'},{name: 'Mobile', minWidth: '40%', valueField: 'mobile'}];
//
// 
//    $("#waiterId").mcautocomplete({
//     
//  showHeader: true,
//  columns: columns1,
//  minLength:0,
//   autoFocus: true,
// source: function(request, response) {
//      $.ajax({
//            url: "GetBuyer.html",
//            dataType: "json",
//            type: "POST",
//            data: {
//                term: request.term
//            },
//           
//            success: function(data){
//                
//                response( $.map( data, function( item ) {
//                   
//                    return {
//                        value: item.buyerName,   
//                        label: item.buyerName,
//                        id: item.idBuyer,
//                        tin:item.tin,
//                        openingAmount:item.openingAmount,
//                        creditAmount:item.creditAmount,
//                        openingType:item.openingType,
//                        code:item.buyerCode,
//                        mobile:item.cellNo
//                        
//                    } 
//                }));
//             }
//        });
//    },
//    select: function(event, ui) {   
////        $('#buyerBalance').val(ui.item.openingAmount);
////        $('#buyerType').val(ui.item.openingType);
//        $('#tinNo').val(ui.item.tin);
//        $('#buyerNameId').val(ui.item.id);
//         $('#idBuyer').val(ui.item.creditAmount);
//         $('#creditAmount').val(ui.item.creditAmount);        
//        $('#tinNo').attr('readonly', true);
//        gstinEqual();
//    },
//     open: function(){
//                $('.ui-autocomplete').css('width', '500px');
//            },
//    change: buyerChange
//}).bind('focus', function(){ $(this).mcautocomplete("search"); } );
//
////$('#buyerName').change(function() {
//
//
//
//var columns2 = [{name:'Name', minWidth: '40%', valueField:'label'}, {name: 'Code', minWidth: '20%', valueField: 'code'},{name: 'Mobile', minWidth: '40%', valueField: 'mobile'}];
//$("#mediator").mcautocomplete({
//  showHeader: true,
//  columns: columns2,
//  minLength:0,
//   autoFocus: true,
// source: function(request, response) {
//        $.ajax({
//            url: "GetBuyerMediator.html",
//            dataType: "json",
//            type: "POST",
//            data: {
//                term: request.term
//            },
//           
//            success: function(data){
//                
//                response( $.map( data, function( item ) {
//                   
//                    return {
//                        value: item.buyerName,   
//                        label: item.buyerName,
//                        id: item.idBuyer,
//                        openingAmount:item.openingAmount,
//                        openingType:item.openingType,
//                        code:item.buyerCode,
//                        mobile:item.cellNo
//                        
//                    } 
//                }));
//             }
//        });
//    },
//    select: function(event, ui) {   
////        $('#buyerBalance').val(ui.item.openingAmount);
////        $('#buyerType').val(ui.item.openingType);
//        $('#tinNo').val(ui.item.tin);
//        $('#mediatorNameId').val(ui.item.id);
//        $('#tinNo').attr('readonly', true);
//         $('#salesMCA').attr('readonly', true);
//    },
//     open: function(){
//                $('.ui-autocomplete').css('width', '500px');
//            },
//    change:mediatorChange
//    
//}).bind('focus', function(){ $(this).mcautocomplete("search"); } );


});
/* --- Autocomplete to bring three columns in single textbox As Table --- End Code (Abi) */
