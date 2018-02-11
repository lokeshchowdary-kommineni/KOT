
//// ***** For deleting single row in table ***** -- Start(ABI) /////
function deleteFunction(r,totalRow,index) {  
    
   var tableSize = $("#Mytable td").closest("tr").length;
   
  if(tableSize > 1){
      
       var i = r.parentNode.parentNode.rowIndex;
       stocksCalculationDelete(totalRow,index); 
    document.getElementById("Mytable").deleteRow(i);
   // stocksCalculation(i-1);
  }
  
}
function myFunctionFinish(s,totalRow,index) {  
    
   var tableSize = $("#MyTableFinish td").closest("tr").length;
 
  
  if(tableSize > 1){
     
       var i = s.parentNode.parentNode.rowIndex;
     stocksReCalcFinish(totalRow,index);   
    document.getElementById("MyTableFinish").deleteRow(i);
   
  }
  
}
//// ***** For deleting single row in table ***** -- End /////

function addRowforstockjournal() {  
    
    $.ajax({  
     type : "POST",   
     url : "stockjournal_addrow.html",   
     data : $("#sForm").serialize(),
     success : function(response) {         
     $('#addrowSDiv').empty().append(response);
    
     },  
     error : function(e) {  
      
      alert('Error: ' + e);   
     }  
    }); 
  
   } 
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
// Autocomplete for Stock Item Code -- Start (Abi) //
 function autoCompleteForStock(i) {    
    var columnsItems1 = [{name: 'Code', minWidth: '20%', valueField: 'label'},{name:'Name', minWidth: '60%', valueField:'itemName'},{name: 'Stock', minWidth: '10%', valueField: 'currentStock'},{name: 'Tax', minWidth: '10%', valueField: 'tr'}];  
   var previous_Item_code = new Array();
        $('input[id^="stocksItemCode"]').each(function(){
            previous_Item_code.push("'"+$(this).val()+"'");
        });                   
       var selected_items=previous_Item_code.join(',');
       selected_items = selected_items.replace(/,\s*$/, "");   
       
    $("#stocksItemCode"+i+"").mcautocomplete({
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
                        rate:item.rate,
                        ep:item.ep, 
                        currentStock:item.currentStock,
                        basicPrice:item.basicPrice,
                        tr:item.tr,
                        tp:item.tp,
                        cp:item.cp
                        
                           
                    }
  
                }));
     
             }
      
        })
    },
    select: function(event, ui) {   
        $('#stocksNameOfTheItem'+i+'').val(ui.item.itemName);
        $('#stocksUnit'+i+'').val(ui.item.unit);
        $('#stocksRate'+i+'').val(ui.item.basicPrice);
        $('#stocksEp'+i+'').val(ui.item.cp); 
        
    $(".txtMult input").keyup(multInputs);

       function multInputs() {
         
           var mult = 0;
           var amount=0;

           $("tr.txtMult").each(function () {
               
            var qty=parseInt($("#stocksQty"+i+"").val());
            
            var ep=parseFloat($("#stocksEp"+i+"").val());
           
            amount=qty*ep;
            $("#stocksAmount"+i+"").val(amount);
              
           });  
       }
       getUnitforSourceRow(i,ui.item.itemCode);
    },
     open: function(){
                                    $('.ui-autocomplete').css('width', '700px');
                                }

}).bind('focus', function(){ $(this).mcautocomplete("search"); } );
}

// Autocomplete for Stock Item Name -- Start (Abi) //
function autoCompleteForStockItemName(i) {    
    var columnsItems1 = [{name: 'Name', minWidth: '60%', valueField: 'itemName'},{name:'Code', minWidth: '20%', valueField:'label'},{name: 'Stock', minWidth: '10%', valueField: 'currentStock'},{name: 'Tax', minWidth: '10%', valueField: 'tr'}];  
   var previous_Item_code = new Array();
        $('input[id^="stocksItemCode"]').each(function(){
            previous_Item_code.push("'"+$(this).val()+"'");
        });                   
       var selected_items=previous_Item_code.join(',');
       selected_items = selected_items.replace(/,\s*$/, "");   
       
    $("#stocksNameOfTheItem"+i+"").mcautocomplete({
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
                tax: "",
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
                        tp:item.tp,
                        cp:item.cp
                        
                           
                    }
  
                }));
     
             }
      
        })
    },
    select: function(event, ui) {   
        $('#stocksItemCode'+i+'').val(ui.item.itemCode);
        $('#stocksUnit'+i+'').val(ui.item.unit);
        $('#stocksRate'+i+'').val(ui.item.basicPrice);
        $('#stocksEp'+i+'').val(ui.item.cp); 
        
    $(".txtMult input").keyup(multInputs);

       function multInputs() {
         
           var mult = 0;
           var amount=0;

           $("tr.txtMult").each(function () {
               
            var qty=parseInt($("#stocksQty"+i+"").val());
            
            var ep=parseFloat($("#stocksEp"+i+"").val());
           
            amount=qty*ep;
            $("#stocksAmount"+i+"").val(amount);
              
           });  
       }
       getUnitforSourceRow(i,ui.item.itemCode);
    },
     open: function(){
                                    $('.ui-autocomplete').css('width', '700px');
                                }

}).bind('focus', function(){ $(this).mcautocomplete("search"); } );
}

function onChangeQty(i) {
            var qty=parseInt($("#stocksQty"+i+"").val()|| 0); 
            
            var ep=parseFloat($("#stocksEp"+i+"").val()|| 0); 
            
            var amount=(qty*ep) || 0;
           
            
            $("#stocksAmount"+i+"").val(amount);

}
function stocksCalculation(trLength) { 
    var totalAmount=0;
    var valuAdd=0;
    var valueOfFinish=0;
    for (var i = 0; i < trLength; i++) {
          
         var amount=parseFloat($("#stocksAmount"+i+"").val()|| 0);
       
         totalAmount=totalAmount+amount;
   }
    $("#stocksAmount").val(totalAmount);  
    
     var valuAdd=parseFloat($("#valuAdditiion").val()|| 0);
    
     
     valueOfFinish=totalAmount+valuAdd;
     
     $("#valueOfFinishGood").val(valueOfFinish);
     
     
     }

// ------------------------------------------------------------------------------------------------------------------


function stocksCalculationDelete(trLength,index) {   
    var totalAmount=0;
    var valuAdd=0;
    var valueOfFinish=0;
    for (var i = 0; i < trLength; i++) {
          if("#stocksAmount"+i+""!= "#stocksAmount"+index+""){
         var amount=parseFloat($("#stocksAmount"+i+"").val() || 0);
       
         totalAmount=totalAmount+amount;
   }
    }
    $("#stocksAmount").val(totalAmount);  
    
     var valuAdd=parseFloat($("#valuAdditiion").val()|| 0);
    
     
     valueOfFinish=totalAmount+valuAdd;
     
     $("#valueOfFinishGood").val(valueOfFinish);
     
     
     }
     
     
 function addRowforstockjournalFinish() {  
    $.ajax({  
     type : "POST",   
     url : "stockjournalFinish_addrow.html",   
     data : $("#sFForm").serialize(),
     success : function(response) { 
     $('#addrowSFinishDiv').empty().append(response);
    
     },
  
     error : function(e) { 

      alert('Error: ' + e);   
     }  
    }); 
  
   } 
// Autocomplete for StockFinish Item Code -- Start (Abi)  //
function autoCompleteForStockFinish(i) {    
  var columnsItems1 = [{name: 'Code', minWidth: '20%', valueField: 'label'},{name:'Name', minWidth: '60%', valueField:'itemName'},{name: 'Stock', minWidth: '10%', valueField: 'currentStock'},{name: 'Tax', minWidth: '10%', valueField: 'tr'}];  
   var previous_Item_code_source = new Array();
        $('input[id^="stocksItemCode"]').each(function(){
            previous_Item_code_source.push("'"+$(this).val()+"'");
        });   
    var previous_Item_code = new Array();
        $('input[id^="stocksItemCodee"]').each(function(){
            previous_Item_code.push("'"+$(this).val()+"'");
        });  
       var slected_ItemCodes = previous_Item_code_source.concat(previous_Item_code).unique();  
       var selected_items=slected_ItemCodes.join(',');
       selected_items = selected_items.replace(/,\s*$/, "");         
       
    $("#stocksItemCodee"+i+"").mcautocomplete({
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
                        rate:item.rate,
                        ep:item.ep, 
                        currentStock:item.currentStock,
                        basicPrice:item.basicPrice,
                        tr:item.tr,
                        tp:item.tp
                        
                           
                    }
  
                }));
     
             }
      
        })
    },
    select: function(event, ui) {   
        $('#stocksNameOfTheItemm'+i+'').val(ui.item.itemName);
        $('#stocksUnitt'+i+'').val(ui.item.unit);
        $('#stocksRatee'+i+'').val(ui.item.basicPrice);
        $('#stocksEpp'+i+'').val(ui.item.ep); 
       $('#stocksQtyy'+i+'').val(1);        
         var qty=parseInt($("#stocksQtyy"+i+"").val());             
            var ep=parseFloat($("#stocksEpp"+i+"").val());             
            var amount=qty*ep;
             $("#stocksAmountt"+i+"").val(amount);
      getUnitforFinishRow(i,ui.item.itemCode);

    },
     open: function(){
                                    $('.ui-autocomplete').css('width', '700px');
                                }

}).bind('focus', function(){ $(this).mcautocomplete("search"); } );
}
// Autocomplete for StockFinish ItemName -- Start (Abi) //
function autoCompleteForStockFinishItemName(i) {    
  var columnsItems1 = [{name: 'Name', minWidth: '60%', valueField: 'itemName'},{name:'Code', minWidth: '20%', valueField:'label'},{name: 'Stock', minWidth: '10%', valueField: 'currentStock'},{name: 'Tax', minWidth: '10%', valueField: 'tr'}];  
    var previous_Item_code_source = new Array();
        $('input[id^="stocksItemCode"]').each(function(){
            previous_Item_code_source.push("'"+$(this).val()+"'");
        });   
    var previous_Item_code = new Array();
        $('input[id^="stocksItemCodee"]').each(function(){
            previous_Item_code.push("'"+$(this).val()+"'");
        });  
       var slected_ItemCodes = previous_Item_code_source.concat(previous_Item_code).unique();  
       var selected_items=slected_ItemCodes.join(',');
       selected_items = selected_items.replace(/,\s*$/, "");    
       
    $("#stocksNameOfTheItemm"+i+"").mcautocomplete({
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
                tax: "",
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
                        tp:item.tp
                        
                           
                    }
  
                }));
     
             }
      
        })
    },
    select: function(event, ui) {   
        $('#stocksItemCodee'+i+'').val(ui.item.itemCode);
        $('#stocksUnitt'+i+'').val(ui.item.unit);
        $('#stocksRatee'+i+'').val(ui.item.basicPrice);
        $('#stocksEpp'+i+'').val(ui.item.ep); 
        $('#stocksQtyy'+i+'').val(1); 
        
         var qty=parseInt($("#stocksQtyy"+i+"").val());             
         var ep=parseFloat($("#stocksEpp"+i+"").val());             
         var amount=qty*ep;
         $("#stocksAmountt"+i+"").val(amount);
         getUnitforFinishRow(i,ui.item.itemCode);


    },
     open: function(){
                                    $('.ui-autocomplete').css('width', '700px');
                                }

}).bind('focus', function(){ $(this).mcautocomplete("search"); } );
}
function onChangeQtyFinish(i) {
             var valueOfFinishGoodF=parseInt($("#valueOfFinishGoodF").val()|| 0);
             
             var Qtyy=parseInt($("#stocksQtyy"+i+"").val()|| 0);
           
            
             var ep=parseInt($("#stocksEpp"+i+"").val()|| 0);
             
             var rowAmount=(Qtyy*ep) || 0;
             
            $("#stocksAmountt"+i+"").val(rowAmount);
          
            
}

function stockssCalculation(trLength) { 
    var totalAmount=0;
    var valuAdd=0;
    var valueOfFinishGoodF=parseInt($("#valueOfFinishGoodF").val() || 0); 
    
     var stocksQtyy=parseInt($("#stocksQtyy"+i+"").val() || 0);
            
             var SumofAmount=0;
    
    for (var i = 0; i < trLength; i++) {
          
           SumofAmount=SumofAmount+parseInt($("#stocksAmountt"+i+"").val() || 0);
           

   }
   
    for (var i = 0; i < trLength; i++) {
         var stocksQtyy=parseInt($("#stocksQtyy"+i+"").val()|| 0);
         var amount=parseFloat($("#stocksAmountt"+i+"").val()|| 0);     
            var alertAmout=(valueOfFinishGoodF/SumofAmount)*amount;
            var ep=alertAmout.toFixed(2)/stocksQtyy;
            $("#stocksEpp"+i+"").val(ep.toFixed(2));
            $("#stocksAmountt"+i+"").val(alertAmout.toFixed(2));
   }   

     $("#valueOfFinishGoodF").val(valueOfFinishGoodF);
     
     }
  // Re - Calculation in Delete   
function stocksReCalcFinish(trLength,index) { 
   
    var totalAmount=0;
    var valuAdd=0;
    var valueOfFinishGoodF=parseInt($("#valueOfFinishGoodF").val() || 0); 
    
     var stocksQtyy=parseInt($("#stocksQtyy"+i+"").val() || 0);
            
             var SumofAmount=0;
    
    for (var i = 0; i < trLength; i++) {
        if("#stocksAmountt"+i+"" != "#stocksAmountt"+index+""){
          
           SumofAmount=SumofAmount+parseInt($("#stocksAmountt"+i+"").val() || 0);
           
           }
   }
   
    for (var i = 0; i < trLength; i++) {
         var stocksQtyy=parseInt($("#stocksQtyy"+i+"").val() || 0);
         var amount=parseFloat($("#stocksAmountt"+i+"").val() || 0);     
            var alertAmout=(valueOfFinishGoodF/SumofAmount)*amount;
            var ep=alertAmout.toFixed(2)/stocksQtyy;
            $("#stocksEpp"+i+"").val(ep.toFixed(2));
            $("#stocksAmountt"+i+"").val(alertAmout.toFixed(2));
   }   

     $("#valueOfFinishGoodF").val(valueOfFinishGoodF);
     
     }
// -----> Ends
function getUnitSource(trLength) {   
   for (var m = 0; m < trLength; m++) {
     var unit='#stocksUnit'+m+'';  
     var itemCode= $("#stocksItemCode"+m+"").val(); 
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
function getUnitforSourceRow(rownumber,code) { 
  
     var unit='#stocksUnit'+rownumber+'';  
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
function getUnitFinish(trLength) {   
   for (var m = 0; m < trLength; m++) {
     var unit='#stocksUnitt'+m+'';  
     var itemCode= $("#stocksItemCodee"+m+"").val(); 
     var hiddenUnit=$("#hiddenUnitFinish"+m+"").val();       
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
function getUnitforFinishRow(rownumber,code) { 
  
     var unit='#stocksUnitt'+rownumber+'';  
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
Array.prototype.unique = function () {
    var r = new Array();
    o:for(var i = 0, n = this.length; i < n; i++)
    {
        for(var x = 0, y = r.length; x < y; x++)
        {
            if(r[x]==this[i])
            {
                continue o;
            }
        }
        r[r.length] = this[i];
    }
    return r;
}
// To Allow Only Numbers in textBox
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