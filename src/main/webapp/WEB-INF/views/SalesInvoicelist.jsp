
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>KOT - Sales Invoice List</title>
        <meta name="description" content="Common form elements and layouts" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/font-awesome/4.5.0/css/font-awesome.min.css" />

		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="assets/css/jquery-ui.custom.min.css" />
                <link rel="stylesheet" href="assets/css/jquery-ui.min.css" />
		<link rel="stylesheet" href="assets/css/chosen.min.css" />
		<link rel="stylesheet" href="assets/css/bootstrap-datepicker3.min.css" />
		<link rel="stylesheet" href="assets/css/bootstrap-timepicker.min.css" />
		<link rel="stylesheet" href="assets/css/daterangepicker.min.css" />
		<link rel="stylesheet" href="assets/css/bootstrap-datetimepicker.min.css" />
		<link rel="stylesheet" href="assets/css/bootstrap-colorpicker.min.css" />

		<!-- text fonts -->
		<link rel="stylesheet" href="assets/css/fonts.googleapis.com.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->
		<link rel="stylesheet" href="assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->
		<script src="assets/js/ace-extra.min.js"></script>                 
                <script src="assets/js/jquery-2.1.4.min.js"></script>                
                
    </head>
    <script src="assets/accounting-js/editkotfromSales.js"></script>
	<body class="no-skin">
            <div class="main-container ace-save-state" id="main-container">
              <c:set var="listLength" value="${fn:length(listGroup)}"/>
        <c:forEach items="${listGroup}" var="group">
            <c:choose>
                <c:when test="${group.moduleName=='SalesInvoicelist'}">
                    <p style="display: none"> ${listLength=listLength+1} </p>
                  
                </c:when>
                <c:otherwise>
                    <p style="display: none">  ${listLength=listLength-1}</p>
                    <c:if test="${listLength==0}">
                        <c:redirect url="Dashboard.html"/>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </c:forEach>       
                
                <%@ include file="Header.jsp" %>
                <div class="main-content">
                    <div class="main-content-inner">
                        <div class="page-content">
                           
                            <div class="page-header">
							<h1>Sales Invoice List</h1>
                                                        
							
						</div>
                           
                        
                          
                           
                               
<form method="post" action="SalesInvoicelist.html">
   
    
     <div class="col-xs-12">
										
										<div class="table-header">
											Sales Invoice Records
										</div>

										<div>
											<table id="dynamic-table" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														
						<th>Bill No</th>
                                                <th>Sale Date</th>
                                                <th>Waiter Name</th>
                                                <th>Table Name</th>
                                                <th>Net Total</th>
                                                <th>Kot No's</th>
                                                <th>Payement Mode</th>
                                               
                                                <th>VoidBills</th>
                                                <th>RGB</th>
                                                <th>Change Mode</th>
                                                                                              

														
													</tr>
												</thead>

												<tbody>
                          
                                                        
                                                        
                                                         
                                                         
                                                        
                                                        
                                                        
                                                        
                                                        
                                                   
                                </tbody>
											</table>
										</div>
									</div>
                            </form>
                      
                               <!-- Modal -->

</div>                  
                                                 
                                                 
                                                 
                                             

<div id="myModal" class="modal fade" tabindex="50" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    
</div>                        
                                                        
						
						
					</div> 
                           </div>
                                                            </div>
                                                                
                                                                </div>
                                                               
                        </div>
                    </div>
                </div>    
              
            </div>    


		<!-- <![endif]-->

		<!--[if IE]>
<script src="assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
		
		<script src="assets/js/bootstrap.min.js"></script>

		<!-- page specific plugin scripts -->
                <script src="assets/js/jquery.dataTables.min.js"></script>
		<script src="assets/js/jquery.dataTables.bootstrap.min.js"></script>
		<script src="assets/js/jquery.bootstrap-duallistbox.min.js"></script>
		<script src="assets/js/jquery.raty.min.js"></script>
		<script src="assets/js/bootstrap-multiselect.min.js"></script>
		<script src="assets/js/select2.min.js"></script>
		<script src="assets/js/jquery-typeahead.js"></script>
                
                
                <script src="assets/js/jquery-ui.custom.min.js"></script>
                <script src="assets/js/jquery-ui.min.js"></script>
		<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="assets/js/chosen.jquery.min.js"></script>
		<script src="assets/js/spinbox.min.js"></script>
		<script src="assets/js/bootstrap-datepicker.min.js"></script>
		<script src="assets/js/bootstrap-timepicker.min.js"></script>
		<script src="assets/js/moment.min.js"></script>		
		<script src="assets/js/bootstrap-datetimepicker.min.js"></script>
		<script src="assets/js/bootstrap-colorpicker.min.js"></script>
		<script src="assets/js/jquery.knob.min.js"></script>
		<script src="assets/js/autosize.min.js"></script>
		<script src="assets/js/jquery.inputlimiter.min.js"></script>
		<script src="assets/js/jquery.maskedinput.min.js"></script>
		<script src="assets/js/bootstrap-tag.min.js"></script>
                <script type="text/javascript" src="assets/datepicker/daterangepicker.js"></script>
		<!-- ace scripts -->
		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>
                <script src="assets/accounting-js/enter2tab.js"></script>
   

<script> 
                    
        // Checking date for Return Limit with Invoice Date ---> Abi
        
            $(document).ready(function() {
                
    $('#dynamic-table').DataTable( {
        "processing": true,
        "serverSide": true,
        "ajax": "GetDatatablesGrid.html",
        "fnRowCallback": function( nRow, aData, iDisplayIndex,returnLimit ) { 
            
       var kotnos=aData[5].toString().split(",");
       var kotIds=aData[10].toString().split(",");
       var kot=[];
       var link="";
       var b="void";
       for(var i=0;i<kotnos.length;i++){
           link+='<a href="#" onclick="editKotFromSalesInvoiceList('+kotIds[i]+')" >'+kotnos[i]+'</a>'+'  ';
           kot.push(kotnos[i]);
       
   }
   
 
        
        $('td:eq(5)', nRow).html(link);
            
        
           var data=aData[7];
             var rgb=aData[8];
           
           if(data=="void"&rgb!="RGB"){
            $('td:eq(8)', nRow).html('<a href="RegenateBill.html?kot_id='+aData[10]+'&kot_no='+aData[5]+'&invoice='+aData[0]+'" class="" role="">RGB</a>');
        }else{
            
        }
          
               if (data!="void"){
             $('td:eq(7)', nRow).html('<a href="void.html?invoice='+aData[0]+'" class="" role="">Void</a>');
               }else {
                   
               }
              var data=aData[6];
                if(data=="CASH"){
              $('td:eq(9)', nRow).html('<a href="paymentmode.html?invoice='+aData[0]+'" class="" role="">CARD</a> | <a href="cashandcard.html?invoice='+aData[0]+'" class="" role="">CASH & CARD</a>');
                }else{
                     $('td:eq(9)', nRow).html('<a href="paymentcashmode.html?invoice='+aData[0]+'" class="" role="">CASH</a> | <a href="cashandcard.html?invoice='+aData[0]+'" class="" role="">CASH & CARD</a>');
                }
              $('td:eq(0)', nRow).html('<a href="Duplicateprint.html?invoice='+aData[0]+'">'+aData[0]+'</a>');
    },
} );

});   
     
</script>
 
<!--GetDatatablesGrid-->
<!--<script>
   $(document).on("click", ".open-AddBookDialog", function popup(){
        var myBookId=document.getElementById("id"); 
        var ids=document.getElementById("bookId").value;
        myBookId.value=(ids);
                    }
    
</script>-->

<!--<script>
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
    
    function autoCompleteForKOTfromSales(i) {
       var columnsItems = [{name: 'Code', minWidth: '20%', valueField: 'label'},{name:'Name', minWidth: '60%', valueField:'itemName'}];
       // Skip previous selected items start
       var previous_Item_code = new Array();
        $('input[id^="itemcode"]').each(function(){
            previous_Item_code.push("'"+$(this).val()+"'");
        });                   
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
           $('#itemName'+i+'').val(ui.item.itemName);
           $('#unit'+i+'').val(ui.item.unit);
           $('#quantity'+i+'').val(ui.item.currentStock);
           $('#rate'+i+'').val(ui.item.cbp);

           $('#auditRate'+i+'').val(ui.item.cap);
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
    
</script>-->

	</body>
</html>