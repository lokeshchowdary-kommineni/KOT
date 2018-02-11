<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

        
        <script type="text/javascript">
            
			jQuery(function($) {
			//datepicker plugin
				//link
				$('.date-picker').datepicker({
                                    
                                  
                                     format: 'dd/mm/yyyy',
                                     startDate:'${previousInvoiceDate}',
                                            endDate: '${currentDate}',
//					 format: 'dd/mm/yyyy',
//                                            startDate:'22-04-2017',
//                                            endDate: '22-04-2017',
                                            autoclose: true
				})
				//show datepicker when clicking on the icon
				.next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
                                 var columns1 = [{name:'Name', minWidth: '40%', valueField:'label'}, {name: 'Code', minWidth: '20%', valueField: 'code'},{name: 'Mobile', minWidth: '40%', valueField: 'mobile'}];

    
                                $("#buyerName").mcautocomplete({
                              showHeader: true,
                              columns: columns1,
                              minLength:0,

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
                                                    openingType:item.openingType,
                                                    code:item.buyerCode,
                                                    mobile:item.cellNo,
                                                    

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
                                },
                                   open: function(){
                                   $('.ui-autocomplete').css('width', '500px');
                                   },
                                change: buyerChange
                            }).bind('focus', function(){ $(this).mcautocomplete("search"); } );
                                                    
                            var columns2 = [{name:'Name', minWidth: '40%', valueField:'label'}, {name: 'Code', minWidth: '20%', valueField: 'code'},{name: 'Mobile', minWidth: '40%', valueField: 'mobile'}];
                            $("#mediator").mcautocomplete({
                              showHeader: true,
                              columns: columns2,
                              minLength:0,

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
                                    $('#buyerNameId').val(ui.item.id);
                                    $('#tinNo').attr('readonly', true);
                                },
                                 open: function(){
                                    $('.ui-autocomplete').css('width', '500px');
                                },
                                change:mediatorChange

                            }).bind('focus', function(){ $(this).mcautocomplete("search"); } );
//                             $('#Mytable').tableNav();
//                            return $('#Mytable input').eq(1).click();
                            });
		</script>
                
 <!-- accounting scripts -->
<script src="assets/accounting-js/sales.js"></script>

<div class="col-xs-12" id="salesFormDiv">
								<!-- PAGE CONTENT BEGINS -->
                                                    <form:form class="form-horizontal" action="SaveSales.html"  commandName="salesForm" method="POST" id="saleForm"  autocomplete="off" onsubmit="return myFunctionSub()">
                                                       
                                                        <input type="hidden" value="${transactionId}" name="ItemID">
                                                       
                                                        <input type="hidden" value="${billId}" name="EntryID">

                                                        
                                                        <div class="widget-box lighter  widget-color-blue2">
                                                        <div class="widget-header">
                                                                <h5 class="widget-title">Sales</h5>
                                                        </div>
                                                        <% String eid=request.getParameter("eId");%>
                                                        <input type="hidden" value="<%=eid%>" name="estimateId"/>
                                                        <div class="widget-body">
                                                                <div class="widget-main alert-success">
                                                                    
                                                                <div class="row">
								    <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Invoice No</label>
                                                                               
										<div class="col-sm-12">
                                                                                    <form:input type="text" path="sale.invoiceNo" id="Box1"   class="form-control" readonly="true"/>
                                                                                        <form:errors path="sale.invoiceNo"/>
										</div>
									</div>
                                                                    <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Date <span style="color:red;">*</span></label>

										<div class="col-sm-12">
                                                                                    <form:input path="sale.date"    class="form-control date-picker"  id="theDate"  type="text" data-date-format="dd/mm/yyyy" readonly="true" required="true" />
                                                                                    <form:errors path="sale.date" />
										</div>
									</div>
                                                                        <div class="form-group col-xs-3">
										<label class="col-sm-12" for="form-field-1"> Category <span style="color:red;">*</span></label>

										<div class="col-sm-12">
                                                                                   <form:select path="sale.category"   class="form-control" id="category" onChange="onChangeCategory();show();" oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Please Select Category')">
                                                                                        <form:option value="" label="-------Select-------"/>
                                                                                        <form:option value="Consumers(B2C)" label="Consumers(B2C)"/>
                                                                                        <form:option value="GST Dealers(B2B)" label="GST Dealers(B2B)"/>
                                                                                        <form:option value="Interstate Sales" label="Interstate Sales"/>
                                                                                        <form:option value="Zero Rate Export" label="Zero Rate Export"/>
                                                                                        <form:option value="Zero Rate Non Export" label="Zero Rate Non Export"/>
                                                                                        
                                                                                        
                                                                                    </form:select>
                                                                                    <form:errors path="sale.category" />
											<!-- <label class="col-sm-12 control-label">Error</label> -->
										</div>
									</div>

									<div class="form-group col-xs-3">
										<label class="col-sm-12" for="form-field-1">Name Of Cash Buyer </label>

										<div class="col-sm-12">
											<form:input type="text"   path="sale.cashBuyer" id="cashBuyer"  class="form-control" oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Please Select Buyer')"/>
                                                                                        <form:errors path="sale.cashBuyer" />
										</div>
									</div>
									<div class="form-group col-xs-3" id="gstDiv">
										<label class="col-sm-12" for="form-field-1"> GSTIN</label>

										<div class="col-sm-12">
                                                                                    <form:input type="text"  path="sale.tinNo" id="tinNo" class="form-control" pattern="\d{2}[A-Z]{5}\d{4}[A-Z]{1}\d{1}[A-Z]{1}\d{1}" maxlength="15" onchange="gstinEqual();" />
                                                                                    <form:errors path="sale.tinNo" />
										</div>
                                                                              
									</div>
                                                                         <div class="form-group col-xs-3" id="posDiv" hidden>
										<label class="col-sm-12" for="form-field-1"> POS<span style="color:red;">*</span></label>
                                                                                    <div class="col-sm-12">
                                                                                    <form:select path="sale.pos"  class="form-control" id="pos" required="true" onChange="posStatesEqual()" oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Please Select Pos')">
                                                                                        <form:option value="" label="------Select------"/>
                                                                                        <form:option value="35" label="Andaman and Nicobar Islands"/>
                                                                                        <form:option value="28" label="Andhra Pradesh"/>
                                                                                         <form:option value="37" label="Andhra Pradesh(New)"/>
                                                                                          <form:option value="12" label="Arunachal Pradesh"/>
                                                                                           <form:option value="18" label="Assam"/>
                                                                                           <form:option value="10" label="Bihar"/>
                                                                                           <form:option value="04" label="Chandigarh"/>
                                                                                           <form:option value="22" label="Chattisgarh"/>
                                                                                           <form:option value="26" label="Dadra and Nagar Haveli"/>
                                                                                           <form:option value="25" label="Daman and Diu"/>
                                                                                           <form:option value="07" label="Delhi"/>
                                                                                           <form:option value="30" label="Goa"/>
                                                                                           <form:option value="24" label="Gujarat"/>
                                                                                           <form:option value="06" label="Haryana"/>
                                                                                           <form:option value="02" label="Himachal Pradesh"/>
                                                                                           <form:option value="01" label="Jammu and Kashmir"/>
                                                                                           <form:option value="20" label="Jharkhand"/>
                                                                                           <form:option value="29" label="Karnataka"/>
                                                                                           <form:option value="32" label="Kerala"/>
                                                                                           <form:option value="31" label="Lakshadweep Islands"/>
                                                                                           <form:option value="23" label="Madhya Pradesh"/>
                                                                                           <form:option value="27" label="Maharashtra"/>
                                                                                           <form:option value="14" label="Manipur"/>
                                                                                           <form:option value="17" label="Meghalaya"/>
                                                                                           <form:option value="15" label="Mizoram"/>
                                                                                           <form:option value="13" label="Nagaland"/>
                                                                                           <form:option value="21" label="Odisha"/>
                                                                                           <form:option value="34" label="Pondicherry"/>
                                                                                           <form:option value="03" label="Punjab"/>
                                                                                           <form:option value="08" label="Rajasthan"/>
                                                                                           <form:option value="11" label="Sikkim"/>
                                                                                           <form:option value="33" label="Tamil Nadu"/>
                                                                                           <form:option value="36" label="Telangana"/>
                                                                                           <form:option value="16" label="Tripura"/>
                                                                                           <form:option value="09" label="Uttar Pradesh"/>
                                                                                           <form:option value="05" label="Uttarakhand"/>
                                                                                           <form:option value="19" label="West Bengal" />
                                                                                    </form:select>
                                                                                    
                                                                                    <form:errors path="sale.mode" />
										</div>
									</div>	
                                                                        <input type="hidden" name="CompanyGstno" id="CompanyGstno" value="${CompanyGstno}" />
                                                                        <form:hidden   path="sale.isIgst" id="IgstCalc" class="form-control" />        
                                                                     
								</div>
								
								<div class="row">
                                                                    
                                                                    <div class="form-group col-xs-3">
										<label class="col-sm-12" for="form-field-1"> Mode<span style="color:red;">*</span></label>

										<div class="col-sm-12">
                                                                                    <form:select path="sale.mode"  class="form-control" id="mode" required="true" onChange="onChangeMode()" oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Please Select Mode')">
                                                                                        <form:option value="" label="------Select------"/>
                                                                                        <form:option value="Cash" label="Cash"/>
                                                                                        <form:option value="Credit" label="Credit"/>  
                                                                                    </form:select>
                                                                                    
                                                                                    <form:errors path="sale.mode" />
										</div>
									</div>	
									<div class="form-group col-xs-3">
										<label class="col-sm-12"> Name of Buyer <span style="color:red;">*</span></label>
                                                                                <form:hidden path="sale.buyerId" id="buyerNameId"/>
										<div class="col-sm-12">
											<form:input type="text"  path="sale.nameOfBuyer" id="buyerName"  class="form-control ui-autocomplete-input" required="true" autocomplete="off"/>
                                                                                        <form:errors path="sale.nameOfBuyer" />
                                                                                </div>
									</div>
                                                                                
                                                                        <div class="form-group col-xs-3">
										<label class="col-sm-12"> Voucher Closing Balance </label> 

										<div class="col-sm-9">
											<form:input type="text"  path="sale.buyerBalance" class="form-control" id="buyerBalance" readonly="true"/>
                                                                                        <form:errors path="sale.buyerBalance" />
                                                                                       
										</div>
                                                                                <form:input type="hidden" path=""  class="form-control" id="creditAmount" />
										<div class="col-sm-3"> 
                                                                                <form:input type="text" path="sale.buyerType" class="form-control" id="buyerType" readonly="true"/>
										</div>
                                                                                  
									</div>
                                                                         <c:if test="${not empty currentBuyerBalance}">       
                                                                         <div class="form-group col-xs-3">
										<label class="col-sm-12"> Current Closing Balance </label> 

										<div class="col-sm-9">
                                                                                    <input type="text" class="form-control"  value="${currentBuyerBalance}" readonly="true" />                                                                                       
                                                                    </div>   
										<div class="col-sm-3"> 
                                                                                    <input type="text" class="form-control"  value="${TypeBuyer}" readonly="true" />    
										</div>
                                                                                  
									</div> 
                                                                        </c:if>        
                                                                    </div>   
                                                                                   <div class="row">
                                                                        <div class="form-group col-xs-3">
										<label class="col-sm-12" for="form-field-1"> Work Site </label>

										<div class="col-sm-12">
                                                                                    <form:input type="text"  path="sale.workSite" id="form-field-1" class="form-control" oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Please Select Work Site')"/>
                                                                                    <form:errors path="sale.workSite"/>
										</div>
                                                                        </div>
                                                                        <div class="form-group col-xs-3">
										<label class="col-sm-12"> Name of Mediator </label>
                                                                                <form:hidden path="sale.mediatorId" id="mediatorNameId"/>
										<div class="col-sm-12">
											<form:input type="text"  path="sale.nameOfMediator" id="mediator" placeholder="No Mediator" class="form-control ui-autocomplete-input" autocomplete="off" />
                                                                                        <form:errors path="sale.nameOfMediator"/>
										</div>
									</div>
								
                                                          
                                                             
                                                                    
									
                                                                        <div class="form-group col-xs-3">
										<label class="col-sm-12">Voucher Closing Balance </label>

										<div class="col-sm-9">
											<form:input type="text"  path="sale.mediatorBalance"  class="form-control" id="mediatorBalance" readonly="true"/>
                                                                                        <form:errors path="sale.mediatorBalance"/>
										</div>
                                                                                <div class="col-sm-3"> 
                                                                                <form:input type="text" path="sale.mediatorType" class="form-control" id="mediatorType"  readonly="true"/>
										
										</div>      
									</div>
                                                                          <c:if test="${not empty currentMediatorBalance}">       
                                                                         <div class="form-group col-xs-3">
										<label class="col-sm-12"> Current Closing Balance </label> 
                                                                       
										<div class="col-sm-9">
                                                                                    <input type="text" class="form-control"  value="${currentMediatorBalance}" readonly="true" />                                                                                       
								</div>									
										<div class="col-sm-3"> 
                                                                                    <input type="text" class="form-control"  value="${TypeM}" readonly="true" />    
                                                            </div>
                                                                                  
                                                        </div>
                                                                         </c:if>          
                                                                       
								</div>									
                                                            </div>
                                                        </div>
                                                    </div>                                                              
                                                                    
                                                                    
                                                <div class="widget-box lighter  widget-color-blue2">
                                                        <div class="widget-body">
                                                                <div class="widget-main alert-info">
                                                                        <div class="row">
                                                                        <div class="col-xs-12 col-sm-12 widget-container-col" id="widget-container-col-2">
                                                                            <input type="button" class="btn btn-yellow m-b-sm" value="Add Row" onclick="addRowforSales()"/> 
											<div class="widget-box widget-color-blue" id="widget-box-2">
                                                                                            
                                                                        <div class="widget-body">
                                                                                <div class="widget-main no-padding">
                                                                                          <table class="table table-striped table-bordered table-hover" id="Mytable">
                                                                                                <thead class="thin-border-bottom">
                                                                                                        <tr>
                                                                                                                <th rowspan="2" style="width: 7%;">
                                                                                                                       
                                                                                                                        Item code
                                                                                                                </th>

                                                                                                                <th rowspan="2" style="width: 30%;">
                                                                                                                        Name of the item
                                                                                                                </th>
                                                                                                                <th rowspan="2" style="width:75px;">
                                                                                                                        Qty
                                                                                                                </th>
                                                                                                                <th rowspan="2" style="width:75px;">
                                                                                                                    &nbsp;&nbsp;&nbsp;Unit&nbsp;&nbsp;
                                                                                                                </th>
                                                                                                                <th rowspan="2">
                                                                                                                        Margin
                                                                                                                </th>
                                                                                                                <th rowspan="2" style="width: 7%;">
                                                                                                                        Rate
                                                                                                                </th>
                                                                                                                 <th rowspan="2" style="width: 7%;">
                                                                                                                       <c:choose>
                                                                                                                    
                                                                                                                    <c:when test="${CategoryAuto=='Consumers(B2C)'}">
                                                                                                                        
                                                                                                                         <p id="demo">CP+GST</p> 
                                                                                                                    </c:when>  
                                                                                                                     <c:when test="${CategoryAuto==''}">
                                                                                                                        
                                                                                                                         <p id="demo">CP+GST</p> 
                                                                                                                    </c:when> 
                                                                                                                    <c:otherwise>
                                                                                                                        CP
                                                                                                                    </c:otherwise>
                                                                                                                        </c:choose> 
                                                                                                                </th>
                                                                                                                 <th rowspan="2" style="width: 5px;">
                                                                                                                        Discount
                                                                                                                </th>
                                                                                                                 <th rowspan="2">
                                                                                                                        Amount
                                                                                                                </th>
                                                                                                                <th style="text-align:center;" colspan="2">
                                                                                                                        IGST
                                                                                                                </th>
                                                                                                                 <th style="text-align:center;" colspan="2">
                                                                                                                        CGST
                                                                                                                </th>
                                                                                                                 <th style="text-align:center;" colspan="2">
                                                                                                                        SGST
                                                                                                                </th>
                                                                                                                <th rowspan="2">                                                                
                                                                                                                   Delete                                                   
                                                                                                                </th>
                                                                                                        </tr>
                                                                                                        <tr>  
                                                                                                            <th rowspan="2" style="width:50px; "> % </th>
                                                                                                            <th rowspan="2" style="width:70px; "> Amt </th>
                                                                                                            <th rowspan="2" style="width:50px; "> % </th>
                                                                                                            <th rowspan="2" style="width:70px; "> Amt </th>
                                                                                                            <th rowspan="2" style="width:50px; "> % </th>
                                                                                                            <th rowspan="2" style="width:70px; "> Amt </th>

                                                                                                        </tr>
                                                                                                </thead>
                                                                                                <c:forEach items="${salesForm.sales}" var="sale" varStatus="status">
                                                                                                     <c:set var = "saleFocus" value = "${status.index}"/>
                                                                                                 <tbody id="siva">
                                                                                                        <tr>    
                                                                                                             <form:hidden path="sales[${status.index}].withoutTax" id="amountWithoutTax${status.index}" />
                                                                                                                <form:hidden path="sales[${status.index}].id"/>
                                                                                                                 <input type="hidden"  id="hiddenUnit${status.index}" value="${sale.unit}"/>
                                                                                                                 <input type="hidden"  id="totalUnit${status.index}" />
                                                                                                                <form:hidden path="sales[${status.index}].tax" id="salesTax${status.index}" />
                                                                                                                <form:hidden path="sales[${status.index}].cpcpvAmount" id="salesAmountCpCpv${status.index}" />
                                                                                                                <input type="hidden"  id="SalesCp${status.index}" />
                                                                                                                <form:hidden path="sales[${status.index}].mcmcaAmount" id="salesAmountMcMca${status.index}" />
                                                                                                                <form:hidden path="sales[${status.index}].mcmca" id="salesMcMca${status.index}" />

                                                                                                                <td><form:input path="sales[${status.index}].itemCode" type="text" id="salesItemCode${status.index}" class="form-control ui-autocomplete-input" required="true" autocomplete="off" onKeypress="salesCategoryCheck('${status.index}');" onChange="salesCalculation(${fn:length(salesForm.sales)}); "/><form:errors path="sales[${status.index}].itemCode"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].itemName" type="text" id="salesItemName${status.index}"  class="form-control" onKeypress="salesCategoryCheck('${status.index}');" onChange="salesCalculation(${fn:length(salesForm.sales)}); "/></td>
                                                                                                                <td><form:input path="sales[${status.index}].quantity" style="text-align:center;" type="text" id="salesQuantity${status.index}"  class="form-control" oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Allowed Number Only')"  onkeypress="return isNumberKey(event)" onkeyup="onChangeQtyOrRate(${status.index}); salesCalculation(${fn:length(salesForm.sales)})"/><form:errors path="sales[${status.index}].quantity"/></td>
                                                                                                                <td><form:select path="sales[${status.index}].unit" id="salesUnit${status.index}" class="form-control" onblur=" salesCalculation(${fn:length(salesForm.sales)});" onChange="onChangeMargin(${status.index});">
                                                                                                                        
                                                                                                                </form:select></td>
                                                                                                                <td><form:select path="sales[${status.index}].margin" id="salesMargin${status.index}" class="form-control" onChange="onChangeMargin(${status.index}); salesCalculation(${fn:length(salesForm.sales)})">
                                                                                                                        <form:option value="A" label="A"/>
                                                                                                                        <form:option value="B" label="B"/>
                                                                                                                        <form:option value="R" label="R"/>
                                                                                                                    </form:select></td>
                                                                                                                <td><form:input path="sales[${status.index}].rate" type="text" id="salesRate${status.index}" style="text-align:right" class="form-control" readonly="true" onkeyup="onChangeQtyOrRate(${status.index}); salesCalculation(${fn:length(salesForm.sales)})"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].cpcpV" type="text" id="salesCpcpV${status.index}" style="text-align:right" class="form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].discount" type="text" id="salesDiscount${status.index}" style="text-align:right" class="input lst form-control" required="true" onChange="onChangeDiscount(${status.index}); salesCalculation(${fn:length(salesForm.sales)})"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].amount" type="text" id="salesAmount${status.index}" style="text-align:right" class="form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].taxIgst" type="text" id="salestaxIgst${status.index}" style="text-align:center;" class="inputs  form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].igst" type="text" id="salesIgst${status.index}" style="text-align:right" class="form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].taxCgst" type="text" id="salestaxCgst${status.index}" style="text-align:center;" class="inputs  form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].cgst" type="text" id="salesCgst${status.index}" style="text-align:right" class="form-control" readonly="true"/></td>
                                                                                                                
                                                                                                                <td><form:input path="sales[${status.index}].taxSgst" type="text" id="salestaxSgst${status.index}" style="text-align:center;" class="inputs  form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].vat" type="text" id="salesVat${status.index}" style="text-align:right"  class="form-control" readonly="true"/></td>
                                                                                                                
                                                                                                                     <td style="text-align:center;"><a href="#"  class="ace-icon fa fa-trash-o bigger-120 btn-danger" onclick="myFunction(this,${fn:length(salesForm.sales)},${status.index})"></a></td>
                                                                                        </tr>

                                                                                                        
                                                                                                </tbody>
                                                                                                </c:forEach>
                                                                                        </table>
                                                                                </div>
                                                                        </div>
                                                                                            </div>
                                                                        </div>
                                                                    </div>									
			</div>
		</div>
	</div>
           <div class="widget-box lighter  widget-color-blue2">
		
		<div class="widget-body">
			<div class="widget-main  alert-info">
				<div class="row">
                                                                           
                                                                    <div class="form-group col-xs-1">
										<label class="col-sm-12" for="form-field-1"> VAA</label>

										<div class="col-sm-12">
											<form:input type="text" path="sale.vaa" id="salesVaa" readonly="true" class="form-control"/>
                                                                                        <form:errors path="sale.vaa"/>
                                                                                </div>
									</div>
                                                                    <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Actual MCA</label>

										<div class="col-sm-12">
                                                                                    <form:input type="number" path="sale.actualMca" id="salesActualMCA" class="form-control"  step='0.01' oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Allowed Number Only')"  onkeypress="return isNumberKey(event)" min="0" />
                                                                                    <form:errors path="sale.actualMca"/>
                                                                                </div>
									</div>
                                                                        <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> MCA </label>

										<div class="col-sm-12">
                                                                                    <form:input type="text" path="sale.mca" id="salesMCA" readonly="true"  class="form-control"  />
											<form:errors path="sale.mca"/>
										</div>
									</div>

									<div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Assess Value </label>

										<div class="col-sm-12">
											<form:input type="text" path="sale.assessValue" readonly="true" id="salesAssessValue" class="form-control"/>
											<form:errors path="sale.assessValue"/>
										</div>
									</div>
                                                                         <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1">IGST</label>

										<div class="col-sm-12">
											<form:input type="text" path="sale.totalIgst" id="Igst" readonly="true" class="form-control"/>
                                                                                        <form:errors path="sale.totalIgst"/>
										</div>
                                                                        </div>         
                                                                                <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1">CGST</label>

										<div class="col-sm-12">
											<form:input type="text" path="sale.totalCgst" id="Cgst" readonly="true" class="form-control"/>
                                                                                        <form:errors path="sale.totalCgst"/>
										</div>
                                                                        </div>
                                                                        <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> SGST</label>

										<div class="col-sm-12">
											<form:input type="text" path="sale.totalVat" id="salesVat" readonly="true" class="form-control"/>
                                                                                        <form:errors path="sale.totalVat"/>
										</div>
                                                                        </div>
                                                                        
								</div>
                                                                    <div class="row">
									
                                                                    
                                                                    
                                                                    <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Add/Less</label>

										<div class="col-sm-12">
                                                                                    <form:input type="number" path="sale.addLess" id="salesAddOrLess" class="form-control" value="0" onkeyup="onChangeAddOrLess()" step='0.01' oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Allowed Number Only')"  onkeypress="return isNumberKey(event)" min="0"/>
                                                                                    <form:errors path="sale.addLess"/>
										</div>
									</div>
                                                                        <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Invoice Amount </label>

										<div class="col-sm-12">
                                                                                    <form:input type="text" path="sale.invoiceAmount" id="salesInvoiceAmount" readonly="true" class="form-control"/>
											<form:errors path="sale.invoiceAmount"/>
										</div>
									</div>
                                                                         
                                                                        <div class="form-group col-xs-8">
										<label class="col-sm-12" for="form-field-11">Narration</label>
										<div class="col-sm-11">
                                                                                    <form:input path="sale.narration" class="form-control"/>	
                                                                                </div>
										
									</div>
								</div>									
			</div>
		</div>
	</div>

									<div class="space-4"></div>
									
									<div style="text-align:center">
                                                                            
                                                                            
                                                                        
<!--                                                                            <p hidden id="total" ></p>
                                                                             <p  hidden id="buId" ></p>  
                                                                             <p   hidden id="ca" ></p>  
                                                                            -->
                                                                            
                                                                            <c:choose>
                                                                            <c:when test="${salesForm.sale.invoiceNo==null ||  empty salesForm.sale.invoiceNo}">
                                                                                  <input type="submit" id="btnSubmit" class="btn btn-info" value="Save"/>
                                                                                   &nbsp; &nbsp; &nbsp;
                                                                                                       <input type="reset" class="btn" value="Reset" onClick="window.location.reload()">
                                                                            
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <a href="SalesInvoice.html?id=${salesForm.sale.invoiceNo}" target="_blank" class="btn btn-info">Inclusive of GST</a>
                                                                                <c:if test="${salesForm.sale.category=='Consumers(B2C)'}">
                                                                                <a href="Salesinvoice.html?id=${salesForm.sale.invoiceNo}" target="_blank" class="btn btn-info">GST Invoice</a>                                                                                
                                                                                </c:if>
                                                                                <c:if test="${salesForm.sale.mediatorId!=null && !empty salesForm.sale.mediatorId}">
                                                                                <a href="SalesMediator.html?id=${salesForm.sale.invoiceNo}" target="_blank" class="btn btn-info">Print Mediator</a>
                                                                                 </c:if>
                                                                            </c:otherwise>
                                                                             </c:choose>
									</div>
								  
									</form:form>
                                                                        
								</div>
<script>
var i = $('table tr').length;

$(document).on('keydown', '.lst', function(e) {
  var code = (e.keyCode ? e.keyCode : e.which);
  if (code === 13) {
   e.preventDefault();    
   addRowforSales(); 
  }
});
</script>
  <script>
                window.onload = function() {
                    onChangeMode();
                    $("#category").focus(); 
                    buyerChange();
                    
}
  
   </script>

  <script>
                    function show()
                    {
                     
                        var myselect = document.getElementById("category").value;
                        
                       if(myselect == "Consumers(B2C)")
                       
                        {                           
                             txt = "CP+GST";
                  }
                  else {
                             txt = "CP";
                         }
                  document.getElementById("demo").innerHTML = txt;
                        
                    }
                </script>                                                                      
 
                 <script>
                
 var tabindex = 10;
    $('input,select,button').each(function() {
        if (this.type != "hidden") {
            var $input = $(this);
            $input.attr("tabindex", tabindex);
            tabindex++;
        }
});  
 $("#saleForm").enableEnterToTab({ captureTabKey: true });
  // Assign Manual Tabindex order 

document.getElementById("Box1").tabIndex = 1;
document.getElementById("theDate").tabIndex =2;
document.getElementById("category").tabIndex = 3;
document.getElementById("mode").tabIndex = 4;
document.getElementById("cashBuyer").tabIndex = 5;
document.getElementById("pos").tabIndex =6;
document.getElementById("form-field-1").tabIndex =7;

 $('#btnSubmit').keydown(function(event){
          //Check if the key pressed is ENTER key using the keyCode=13
          if(event.keyCode === 13){
//           alert('Successfully!'); 
          }
          event.cancelBubble = true;
             if (event.stopPropagation) event.stopPropagation();
 });
 $("#category").focus();
 // to show cursor on first TextBox
                </script>

    
<script>                    


 $('#salesItemCode${saleFocus}').focus();
getUnit(${fn:length(salesForm.sales)});
 function onchangePosorTin()
    { 
        var categoary=$('#category').val();
        if (categoary == 'GST Dealers(B2B)') {
                 $("#gstDiv").show();
                    $("#tinNo").prop('required',true);
                 $("#posDiv").hide();
                     $("#pos").prop('required',null);
            }
            else if(categoary == 'Consumers(B2C)')
            {
               $("#posDiv").show();
                    $("#posDiv").prop('required',true);
               $("#gstDiv").hide();
                    $("#tinNo").prop('required',null);
                
            }    
        }
onchangePosorTin();
</script>
