

<%--<%@ page session="false"%>--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
   
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accounting - Sales Return</title>
        <meta name="description" content="Common form elements and layouts" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/font-awesome/4.5.0/css/font-awesome.min.css" />

		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="assets/css/jquery-ui.custom.min.css" />
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
                <script src="assets/js/jquery-ui.min.js"></script>
                <style>
                    table
                    {
                        border-collapse: collapse !important; /* 'cellspacing' equivalent */
                    }

                    table td
                    {
                        padding: 0 !important; /* 'cellpadding' equivalent */
                   
                    }
                    table th
                    {
                        text-align: center !important;
                    }
                </style>

    </head>
    <body class="no-skin">
           
         <c:set var="listLength" value="${fn:length(listGroup)}"/>
        <c:forEach items="${listGroup}" var="group">
            <c:choose>
                <c:when test="${group.moduleName=='Sales Return'}">
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
         <jsp:include page="Header.jsp" />
      
         <div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>
      
        <div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="#">Home</a>
							</li>
                                                        <li>
								
								<a href="SalesReturnGrid.html">Sales Register</a>
							</li>
                                                        <li>
								
								<a href="SaleReturnReportForm.html">Sales Return Reports</a>
							</li>
                                                        <li>
								
								  <li class="active">Sales Return</li>
							</li>

						</ul><!-- /.breadcrumb -->

						<div class="nav-search" id="nav-search">
                                                    <form class="form-search" >
								<!--<span class="input-icon">-->
									<!--<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />-->
									<!--<i class="ace-icon fa fa-search nav-search-icon"></i>-->
								<!--</span>-->
							</form>
						</div><!-- /.nav-search -->
					</div>

					<div class="page-content">
						<!--- ACE Settings for template -->
						<div class="row">
							<div class="col-xs-12" id="salesFormDiv">
								<!-- PAGE CONTENT BEGINS -->
								<form:form class="form-horizontal" action="SaveSalesReturn.html"  commandName="salesReturnForm" method="POST" id="saleReturnForm">
                                                                    <form:hidden path="sale.id"/>
                                                        <div class="widget-box lighter  widget-color-blue2">
                                                        <div class="widget-header">
                                                                <h5 class="widget-title">Sales Return</h5>
                                                        </div>

                                                        <div class="widget-body">
                                                                <div class="widget-main alert-success">
                                                                    
                                                                <div class="row">
								    <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Invoice no</label>
                                                                               
										<div class="col-sm-12">
                                                                                    <form:input type="text" path="sale.invoiceNo" id="Box1"  class="form-control" readonly="true"/>
                                                                                        <form:errors path="sale.invoiceNo"/>
										</div>
									</div>
                                                                    <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Date</label>

										<div class="col-sm-12">
                                                                                    <form:input path="sale.date" class="form-control date-picker" id="id-date-picker-1"  type="text" data-date-format="dd/mm/yyyy" readonly="true" required='true' />
                                                                                    <form:errors path="sale.date" />
										</div>
									</div>
                                                                     <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1">Return Date<span style="color:red;">*</span></label>

										<div class="col-sm-12">
                                                                                    <form:input path="sale.returnDate" class="form-control date-picker" id="id-date-picker-1" type="text" data-date-format="dd/mm/yyyy" onchange="setCustomValidity('')"  oninvalid="setCustomValidity('  Select the Date')" required="true"/>
                                                                                    <form:errors path="sale.returnDate" />
										</div>
                                                                    </div>
                                                                        <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Category <span style="color:red;">*</span></label>

										<div class="col-sm-12">
                                                                                   <form:select path="sale.category"   class="form-control" id="category" onChange="onChangeCategory();show();onchangePosorTin();" oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Please Select Category')">
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

									<div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Cash buyer </label>

										<div class="col-sm-12">
											<form:input type="text" path="sale.cashBuyer" id="form-field-1"  class="form-control" readonly="true"/>
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
									
								<div class="row">
                                                                    
                                                                        <div class="form-group col-xs-3">
										<label class="col-sm-12" for="form-field-1"> Mode</label>

										<div class="col-sm-12">
                                                                                    <form:select path="sale.mode" class="form-control" id="mode" onChange="onChangeMode()" readonly="true">
                                                                                        <form:option value="" label="------Select------"/>
                                                                                        <form:option value="Cash" label="Cash"/>
                                                                                        <form:option value="Credit" label="Credit"/>  
                                                                                    </form:select>
                                                                                    
                                                                                    <form:errors path="sale.mode" />
										</div>
									</div>
									<div class="form-group col-xs-3">
										<label class="col-sm-12"> Name of buyer </label>
                                                                                    <form:hidden path="sale.buyerId" id="buyerNameId"/>
										<div class="col-sm-12">
											<form:input type="text" path="sale.nameOfBuyer" id="buyerName"  class="form-control ui-autocomplete-input" autocomplete="off" readonly="true"/>
                                                                                        <form:errors path="sale.nameOfBuyer" />
                                                                                </div>
									</div>
                                                                        <div class="form-group col-xs-3">
										<label class="col-sm-12">Voucher Closing Balance </label>

										<div class="col-sm-9">
											<form:input type="text" path="sale.buyerBalance" class="form-control" id="buyerBalance" readonly="true"/>
                                                                                        <form:errors path="sale.buyerBalance" />
										</div>
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
										<label class="col-sm-12" for="form-field-1"> Work site</label>

										<div class="col-sm-12">
                                                                                    <form:input type="text" path="sale.workSite" id="form-field-1"  class="form-control" readonly="true"/>
                                                                                    <form:errors path="sale.workSite"/>
										</div>
                                                                        </div>
                                                                    
                                                                    
									<div class="form-group col-xs-3">
										<label class="col-sm-12"> Name of mediator </label>
                                                                                <form:hidden path="sale.mediatorId" id="mediatorId"/>
										<div class="col-sm-12">
											<form:input type="text" path="sale.nameOfMediator" id="mediator"  class="form-control ui-autocomplete-input" autocomplete="off" readonly="true"/>
                                                                                        <form:errors path="sale.nameOfMediator"/>
										</div>
									</div>
                                                                        <div class="form-group col-xs-3">
										<label class="col-sm-12">Voucher Closing Balance </label>

										<div class="col-sm-9">
											<form:input type="text" path="sale.mediatorBalance"  class="form-control" id="mediatorBalance" readonly="true"/>
                                                                                        <form:errors path="sale.mediatorBalance"/>
										</div>
                                                                                <div class="col-sm-3"> 
                                                                                <form:input type="text" path="sale.mediatorType" class="form-control" id="mediatorType" readonly="true"/>
										
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
                                                        </div>        
                                                                    
                                                <div class="widget-box lighter  widget-color-blue2">
                                                        <div class="widget-body">
                                                                <div class="widget-main alert-info">
                                                                        <div class="row">
                                                                        <div class="col-xs-12 col-sm-12 widget-container-col" id="widget-container-col-2">
                                                                             
											<div class="widget-box widget-color-blue" id="widget-box-2">
                                                                                            
                                                                        <div class="widget-body">
                                                                                <div class="widget-main no-padding">
                                                                                        <table class="table table-striped table-bordered table-hover" >
                                                                                                <thead class="thin-border-bottom">
                                                                                                    <tr>
                                                                                                                <th rowspan="2" style="width: 7%;">
                                                                                                                       
                                                                                                                        Item code
                                                                                                                </th>

                                                                                                                <th rowspan="2" style="width: 25%;">
                                                                                                                        Name of the item
                                                                                                                </th>
                                                                                                                <th rowspan="2">
                                                                                                                        Qty
                                                                                                                </th>
                                                                                                                <th rowspan="2">
                                                                                                                       Rt.Qty<span style="color:red;">*</span>
                                                                                                                </th>
                                                                                                                <th rowspan="2" style="width:75px;">
                                                                                                                    &nbsp;&nbsp;&nbsp;Unit&nbsp;&nbsp;
                                                                                                                </th>
                                                                                                                <th rowspan="2">
                                                                                                                        Margin
                                                                                                                </th>
                                                                                                                <th rowspan="2">
                                                                                                                        Rate
                                                                                                                </th>
                                                                                                                 <th rowspan="2" style="width:75px;">
                                                                                                                      <c:choose>
                                                                                                                    
                                                                                                                    <c:when test="${CategoryAuto=='Consumers(B2C)'}">
                                                                                                                        
                                                                                                                         <p id="demo">CP+GST</p> 
                                                                                                                    </c:when>   
                                                                                                                    <c:otherwise>
                                                                                                                        CP
                                                                                                                    </c:otherwise>
                                                                                                                        </c:choose>
                                                                                                                </th>
                                                                                                                 <th rowspan="2">
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
                                                                                                <c:forEach items="${salesReturnForm.sales}" var="sale" varStatus="status">
                                                                                                <tbody id="siva">
                                                                                                        <tr>    
                                                                                                            <form:hidden path="sales[${status.index}].withoutTax" id="amountWithoutTax${status.index}" />
                                                                                                               <input type="hidden"  id="hiddenUnit${status.index}" value="${sale.unit}"/>
                                                                                                                <form:hidden path="sales[${status.index}].id"/>
                                                                                                                <form:hidden path="sales[${status.index}].tax" id="salesTax${status.index}" />
                                                                                                                <form:hidden path="sales[${status.index}].cpcpvAmount" id="salesAmountCpCpv${status.index}" />
                                                                                                                <form:hidden path="sales[${status.index}].mcmcaAmount" id="salesAmountMcMca${status.index}" />
                                                                                                                <form:hidden path="sales[${status.index}].mcmca" id="salesMcMca${status.index}" />
                                                                                                                
                                                                                                                <td><form:input path="sales[${status.index}].itemCode" type="text" id="salesItemCode${status.index}" class="form-control ui-autocomplete-input" autocomplete="off" readonly="true" onKeypress="autoCompleteForSales('${status.index}'); salesCalculation(${fn:length(salesReturnForm.sales)})"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].itemName" type="text" id="salesItemName${status.index}"  class="form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].quantity" type="text" style="text-align:center;" id="Quantitys${status.index}"  class="form-control" readonly="true"/></td>
                                                                                                                <c:if test="${CurrentQty=='0.0'}">
                                                                                                                <td><form:input path="sales[${status.index}].returnQuantity" type="text" style="text-align:center;" id="salesQuantity${status.index}" class="form-control" onkeyup="onChangeQtyOrRate(${status.index}); salesCalculation(${fn:length(salesReturnForm.sales)})" readonly="true"  step='0.01' oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Allowed Number Only')"  onkeypress="return isNumberKey(event)" min="0"/><form:errors path="sales[${status.index}].returnQuantity" /></td>
                                                                                                               </c:if>
                                                                                                                <c:if test="${CurrentQty!='0.0'}">
                                                                                                                <td><form:input path="sales[${status.index}].returnQuantity" type="text" style="text-align:center;" id="salesQuantity${status.index}" class="form-control" onkeyup="onChangeQtyOrRate(${status.index}); salesCalculation(${fn:length(salesReturnForm.sales)})" required="true" step='0.01' oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Allowed Number Only')"  onkeypress="return isNumberKey(event)" min="0"/><form:errors path="sales[${status.index}].returnQuantity"/></td>
                                                                                                               </c:if>
                                                                                                                <td><form:select path="sales[${status.index}].unit" id="salesUnit${status.index}" class="form-control" onChange="onChangeMargin(${status.index});" onKeyUp="salesCalculation(${fn:length(salesForm.sales)})" readonly="true">
                                                                                                                        
                                                                                                                    </form:select></td>
                                                                                                                <td><form:select path="sales[${status.index}].margin" id="salesMargin${status.index}" class="form-control" readonly="true" onChange="onChangeMargin(${status.index}); salesCalculation(${fn:length(salesReturnForm.sales)})">
                                                                                                                        <form:option value="A" label="A"/>
                                                                                                                        <form:option value="B" label="B"/>
                                                                                                                        <form:option value="R" label="R"/>
                                                                                                                    </form:select></td>
                                                                                                                <td><form:input path="sales[${status.index}].rate" type="text" id="salesRate${status.index}" style="text-align:right" class="form-control" readonly="true" onkeyup="onChangeQtyOrRate(${status.index}); salesCalculation(${fn:length(salesReturnForm.sales)})"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].epepV" type="text" id="salesCpcpV${status.index}"  class="form-control" style="text-align:right" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].discount" type="text" id="salesDiscount${status.index}"  class="form-control" style="text-align:right" readonly="true" onChange="onChangeDiscount(${status.index}); salesCalculation(${fn:length(salesReturnForm.sales)})"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].amount" type="text" id="salesAmount${status.index}" style="text-align:right" class="form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].taxIgst" type="text" id="salestaxIgst${status.index}" style="text-align:right" class="inputs lst form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].igst" type="text" id="salesIgst${status.index}" style="text-align:center;" class="form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].taxCgst" type="text" id="salestaxCgst${status.index}" style="text-align:right" class="inputs lst form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].cgst" type="text" id="salesCgst${status.index}" style="text-align:center;" class="form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].taxSgst" type="text" id="salestaxSgst${status.index}" style="text-align:right" class="inputs lst form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].vat" type="text" id="salesVat${status.index}" style="text-align:center;" class="form-control" readonly="true"/></td>
                                                                                                                
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
											<form:input type="text" path="sale.vaa" id="salesVaa" class="form-control" readonly="true"/>
                                                                                        <form:errors path="sale.vaa"/>
                                                                                </div>
									</div>
                                                                    <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Actual MCA</label>

										<div class="col-sm-12">
                                                                                    <form:input type="text" path="sale.actualMca" id="salesActualMCA" class="form-control" readonly="true" value="0.0"/>
                                                                                    <form:errors path="sale.actualMca"/>
                                                                                </div>
									</div>
                                                                        <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> MCA </label>

										<div class="col-sm-12">
                                                                                    <form:input type="text" path="sale.mca" id="salesMCA"  class="form-control" readonly="true"/>
											<form:errors path="sale.mca"/>
										</div>
									</div>

									<div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Assess Value </label>

										<div class="col-sm-12">
											<form:input type="text" path="sale.assessValue" id="salesAssessValue" class="form-control" readonly="true"/>
											<form:errors path="sale.assessValue"/>
										</div>
									</div>
                                                                        <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1">IGST</label>

										<div class="col-sm-12">
											<form:input type="text" path="sale.totalIgst" id="Igst" class="form-control" readonly="true"/>
                                                                                        <form:errors path="sale.totalIgst"/>
										</div>
                                                                        </div>        
                                                                                <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1">CGST</label>

										<div class="col-sm-12">
											<form:input type="text" path="sale.totalCgst" id="Cgst" class="form-control" readonly="true"/>
                                                                                        <form:errors path="sale.totalCgst"/>
										</div>
                                                                        </div>
                                                                        <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> SGST</label>

										<div class="col-sm-12">
											<form:input type="text" path="sale.totalVat" id="salesVat" class="form-control" readonly="true"/>
                                                                                        <form:errors path="sale.totalVat"/>
										</div>
                                                                        </div>
                                                                        
								</div>
                                                                    <div class="row">
									
                                                                    
                                                                    
                                                                    <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Add/Less</label>

										<div class="col-sm-12">
                                                                                    <form:input type="text" path="sale.addLess" id="salesAddOrLess" value="0.0" class="form-control" onkeyup="onChangeAddOrLess()"/>
                                                                                    <form:errors path="sale.addLess"/>
										</div>
									</div>
                                                                        <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Invoice Amount </label>

										<div class="col-sm-12">
                                                                                    <form:input type="text" path="sale.invoiceAmount" id="salesInvoiceAmount" class="form-control" readonly="true"/>
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
                                                                             <c:choose>
                                                                            <c:when test="${salesReturnForm.sale.id==null ||  empty salesReturnForm.sale.id}">
                                                                                
                                                                             <input type="submit" id="btnSubmit" class="btn btn-info" value="Save"/>
                                                                            </c:when>
                                                                            <c:otherwise>
<!--                                                                                <a href="SalesReturnInvoice.html?id=${salesReturnForm.sale.id}" target="_blank" class="btn btn-info">Inclusive of GST</a>-->
                                                                                <c:if test="${salesReturnForm.sale.category=='Consumers(B2C)'}">
<!--                                                                                <a href="SalesReturninvoice.html?id=${salesReturnForm.sale.id}" target="_blank" class="btn btn-info">GST Invoice</a>-->
                                                                                </c:if>
                                                                            </c:otherwise>
                                                                             </c:choose>
										
									</div>
								  
									</form:form>
                                                                        
                                                        </div>
								</div><!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
                                         <jsp:include page="Footer.jsp" />
    </div>
       
        <script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="assets/js/bootstrap.min.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="assets/js/excanvas.min.js"></script>
		<![endif]-->
		<script src="assets/js/jquery-ui.custom.min.js"></script>
		<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="assets/js/chosen.jquery.min.js"></script>
		<script src="assets/js/spinbox.min.js"></script>
		<script src="assets/js/bootstrap-datepicker.min.js"></script>
		<script src="assets/js/bootstrap-timepicker.min.js"></script>
		<script src="assets/js/moment.min.js"></script>
		<script src="assets/js/daterangepicker.min.js"></script>
		<script src="assets/js/bootstrap-datetimepicker.min.js"></script>
		<script src="assets/js/bootstrap-colorpicker.min.js"></script>
		<script src="assets/js/jquery.knob.min.js"></script>
		<script src="assets/js/autosize.min.js"></script>
		<script src="assets/js/jquery.inputlimiter.min.js"></script>
		<script src="assets/js/jquery.maskedinput.min.js"></script>
		<script src="assets/js/bootstrap-tag.min.js"></script>

		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>
                  
                    <script src="assets/accounting-js/sales.js"></script>
		<!-- inline scripts related to this page -->
                <script>
                      $('#saleReturnForm').submit( function() {
  
    var i=0;
    var check=false;
    $('input[id^="salesQuantity"]').each(function(){
       var reqty=parseFloat($('#salesQuantity'+i).val());
       var qty=parseFloat($('#Quantitys'+i).val());
       
       
        // Compare _a to _b
        if(reqty > qty) {
            alert("Returned quantity should not exceed the purchased quantity"); 
            $('#salesQuantity'+i).focus(); 
           check=false;
           
        }
        else
        {
           
           check=true; 
        }
        i++;
        }); 
       
    
    return check;
});
      
                    </script>
                <script>
                  $("#Box1").focus();
                  // to show cursor on first TextBox
                  getUnit(${fn:length(salesReturnForm.sales)});
                  salesCalculation(${fn:length(salesReturnForm.sales)});
 
                </script>
<c:if test="${returnId==null || empty returnId}">
    <script type="text/javascript">
    $(document).ready(function() {
            $("#salesVaa").val("0");
            $("#salesActualMCA").val("0");
            $("#salesMCA").val("0");
            $("#salesAssessValue").val("0");
            $("#Cgst").val("0");
            $("#Igst").val("0");
            $("#salesVat").val("0");
            $("#salesAddOrLess").val("0");
            $("#salesInvoiceAmount").val("0");
           
            });
</script>
</c:if>
                 <script>
                    
$(document).ready(function(){
$(document).on('keydown',':tabbable', function (e) {
    if (e.which == 13) {
        e.preventDefault();
        // Get all focusable elements on the page
        var $canfocus = $(':tabbable');
        var index = $canfocus.index(this) + 1;
        if (index >= $canfocus.length) index = 0;
        $canfocus.eq(index).focus();
    }
   $('#btnSubmit').keydown(function(event){
          //Check if the key pressed is ENTER key using the keyCode=13
          if(event.keyCode === 13){
//           alert('Successfully!'); 
          }
          event.cancelBubble = true;
             if (event.stopPropagation) event.stopPropagation();
         });
          
});
});
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
<script type="text/javascript">
			jQuery(function($) {
				
				$('.date-picker').datepicker({
					autoclose: true,
					todayHighlight: true
				})
				//show datepicker when clicking on the icon
				.next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
			
			
			});
		</script>
    </body>
    
</html>
