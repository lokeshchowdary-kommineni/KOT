
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
   
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accounting - Sales Estimate </title>
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
                <c:when test="${group.moduleName=='Sales Estimate'}">
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
								<a href="SalesEstimateGrid.html">Sales Estimate Records</a>
							</li>
							<li class="active">Sales Estimate</li>
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
							<div class="col-xs-12" id="salesEstimateFormDiv">
								<!-- PAGE CONTENT BEGINS -->
								<form:form class="form-horizontal" action="SaveSalesEstimate.html"  commandName="salesEstimateForm" method="POST" id="saleEstimateForm">
                                                                  
                                                                    <div class="widget-box lighter  widget-color-blue2">
                                                        <div class="widget-header">
                                                                <h5 class="widget-title">Sales Estimate</h5>
                                                        </div>

                                                        <div class="widget-body">
                                                                <div class="widget-main alert-success">
                                                                 
                                                                <div class="row">
								    <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Invoice no</label>
                                                                               
										<div class="col-sm-12">
                                                                                    <form:input type="text" id="Box1" path="sale.invoiceNo"   class="form-control" readonly="true"/>
                                                                                        <form:errors path="sale.invoiceNo"/>
										</div>
									</div>
                                                                    <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Date<span style="color:red;">*</span></label>

										<div class="col-sm-12">
                                                                                    <form:input path="sale.date" class="form-control date-picker" id="theDate"  type="text" data-date-format="dd/mm/yyyy" required="true" readonly="true"/>
                                                                                    <form:errors path="sale.date" />
										</div>
									</div>
                                                                        <div class="form-group col-xs-3">
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

									<div class="form-group col-xs-3">
										<label class="col-sm-12" for="form-field-1"> Name Of Cash Buyer </label>

										<div class="col-sm-12">
											<form:input type="text" path="sale.cashBuyer" id="cashBuyer"  class="form-control"/>
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
                                                                                    
                                                                                    <form:errors path="sale.pos" />
										</div>
									</div>	
                                                                        <input type="hidden" name="CompanyGstno" id="CompanyGstno" value="${CompanyGstno}" />
                                                                        <form:hidden   path="sale.isIgst" id="IgstCalc" class="form-control" />    
                                                                        
								</div>
									
								<div class="row">
                                                                        <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Mode<span style="color:red;">*</span></label>

										<div class="col-sm-12">
                                                                                    <form:select path="sale.mode" class="form-control" id="mode" onChange="onChangeMode()" oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Please Select Mode')" required="true">
                                                                                        <form:option value="" label="------Select------"/>
                                                                                        <form:option value="Cash" label="Cash"/>
                                                                                        <form:option value="Credit" label="Credit"/>  
                                                                                    </form:select>
                                                                                    
                                                                                    <form:errors path="sale.mode" />
										</div>
									</div>
									<div class="form-group col-xs-3">
										<label class="col-sm-12"> Name of Buyer </label>
                                                                                    <form:hidden path="sale.buyerId" id="buyerNameId"/>
										<div class="col-sm-12">
											<form:input type="text" path="sale.nameOfBuyer" id="buyerName"  class="form-control ui-autocomplete-input" autocomplete="off"/>
                                                                                        <form:errors path="sale.nameOfBuyer" />
                                                                                </div>
									</div>
                                                                                <input type="hidden" id="form-field-1" />
                                                                      
                                                                        <c:if test="${salesEstimateForm.sale.invoiceNo!=null || !empty salesEstimateForm.sale.invoiceNo}">
                                                                        <div class="form-group col-xs-4">
                                                                        <label class="col-sm-6">&nbsp; </label>
                                                                        <div class="col-sm-12">
                                                                            <a href="ConvertToSales.html?eId=${salesEstimateForm.sale.invoiceNo}" class="btn btn-success">Convert to Sale</a>
                                                                        </div>
                                                                        </div>
                                                                          </c:if>
								</div>
                                                                        <div class="row" >
                                                                          <div class="form-group col-xs-2">
										
                                                                                
										<div class="col-sm-12">
											
										</div>
									</div>
                                                                                <div class="form-group col-xs-3">
										<label class="col-sm-12"> Name of Mediator </label>
                                                                                <form:hidden path="sale.mediatorId" id="mediatorId"/>
										<div class="col-sm-12">
											<form:input type="text" path="sale.nameOfMediator" id="mediator" placeholder="Mediator" class="form-control ui-autocomplete-input" autocomplete="off" />
                                                                                        <form:errors path="sale.nameOfMediator"/>
										</div>
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
                                                                              <%--<c:forEach items="${salesEstimateForm.sales}" var="sale" varStatus="status">--%>
                                                                              <%--<c:if test="${salesEstimateForm.sale.invoiceNo==0 || empty salesEstimateForm.sale.invoiceNo}">--%>     
                                                                            <input type="button" class="btn btn-yellow m-b-sm" value="Add Row" onclick="addRowforSalesEstimate()"/> 
                                                                            <%--</c:if>--%>
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
                                                                                                                <th rowspan="2" style="width: 5%;">
                                                                                                                        Qty
                                                                                                                </th>
                                                                                                                <th rowspan="2" style="width: 5%;">
                                                                                                                    &nbsp;&nbsp;&nbsp;Unit&nbsp;&nbsp;
                                                                                                                </th>
                                                                                                                <th rowspan="2" style="width: 5%;">
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
                                                                                                                       <p id="demo">CP</p> 
                                                                                                                    </c:otherwise>
                                                                                                                        </c:choose> 
                                                                                                                </th>
                                                                                                                 <th rowspan="2" >
                                                                                                                        Discount
                                                                                                                </th>
                                                                                                                 <th rowspan="2" style="width: 7%;">
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
                                                                                                            <th style="width: 4%;"> % </th>
                                                                                                            <th style="width: 6%;"> Amt </th>
                                                                                                            <th style="width: 4%;"> % </th>
                                                                                                            <th style="width: 6%;"> Amt </th>
                                                                                                            <th style="width: 4%;">  % </th>
                                                                                                            <th style="width: 6%;"> Amt </th>

                                                                                                        </tr>
                                                                                                </thead>
                                                                                                 <%--</c:forEach>--%>
                                                                                                <c:forEach items="${salesEstimateForm.sales}" var="sale" varStatus="status">
                                                                                                <tbody id="siva">
                                                                                                        <tr>    
                                                                                                                <form:hidden path="sales[${status.index}].id"/>
                                                                                                                <input type="hidden"  id="hiddenUnit${status.index}" value="${sale.unit}"/>
                                                                                                                <form:hidden path="sales[${status.index}].tax" id="salesTax${status.index}" />
                                                                                                                <form:hidden path="sales[${status.index}].cpcpvAmount" id="salesAmountCpCpv${status.index}" />
                                                                                                                <form:hidden path="sales[${status.index}].mcmcaAmount" id="salesAmountMcMca${status.index}" />
                                                                                                                <form:hidden path="sales[${status.index}].mcmca" id="salesMcMca${status.index}" />
                                                                                                               
                                                                                                                <td><form:input path="sales[${status.index}].itemCode" type="text" id="salesItemCode${status.index}" class="form-control ui-autocomplete-input" required="true" autocomplete="off" onKeypress="salesCategoryCheck('${status.index}');" onChange="salesCalculation(${fn:length(salesEstimateForm.sales)}); "/><form:errors path="sales[${status.index}].itemCode"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].itemName" type="text" id="salesItemName${status.index}"  class="form-control" onKeypress="salesCategoryCheck('${status.index}');" onChange="salesCalculation(${fn:length(salesEstimateForm.sales)}); "/></td>
                                                                                                                <td><form:input path="sales[${status.index}].quantity" type="text" id="salesQuantity${status.index}" style="text-align:center;" class="form-control" onkeyup="onChangeQtyOrRate(${status.index}); salesCalculation(${fn:length(salesEstimateForm.sales)})" oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Allowed Number Only')"  onkeypress="return isNumberKey(event)"/></td>
                                                                                                                
                                                                                                                <td><form:select path="sales[${status.index}].unit" id="salesUnit${status.index}" class="form-control" onChange="onChangeMargin(${status.index});" onKeyUp="salesCalculation(${fn:length(salesEstimateForm.sales)})">
                                                                                                                         
                                                                                                                    </form:select></td>
                                                                                                                <td><form:select path="sales[${status.index}].margin" id="salesMargin${status.index}"  class="form-control" onChange="onChangeMargin(${status.index}); salesCalculation(${fn:length(salesEstimateForm.sales)})">
                                                                                                                        <form:option value="A" label="A"/>
                                                                                                                        <form:option value="B" label="B"/>
                                                                                                                        <form:option value="R" label="R"/>
                                                                                                                    </form:select></td>
                                                                                                                <td><form:input path="sales[${status.index}].rate" type="text" id="salesRate${status.index}" style="text-align:right" class="form-control" readonly="true" onkeyup="onChangeQtyOrRate(${status.index}); salesCalculation(${fn:length(salesEstimateForm.sales)})"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].cpcpV" type="text" id="salesCpcpV${status.index}" style="text-align:right" class="form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].discount" type="text"  value="0" id="salesDiscount${status.index}" style="text-align:right" class="inputs lst form-control" required="true" onChange="onChangeDiscount(${status.index}); salesCalculation(${fn:length(salesEstimateForm.sales)})"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].amount" type="text" id="salesAmount${status.index}" style="text-align:right" class="form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].taxIgst" type="text" style="text-align:center;" id="salestaxIgst${status.index}"  class="inputs  form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].igst" type="text" id="salesIgst${status.index}" style="text-align:right" class="form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].taxCgst" type="text" style="text-align:center;" id="salestaxCgst${status.index}"  class="inputs  form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].cgst" type="text" id="salesCgst${status.index}" style="text-align:right" class="form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].taxSgst" type="text" style="text-align:center;" id="salestaxSgst${status.index}"  class="inputs  form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].vat" type="text" id="salesVat${status.index}" style="text-align:right" class=" inputs  form-control" readonly="true"/></td>
                                                                                                                
                                                                                                                <%--<c:if test="${salesEstimateForm.sale.invoiceNo==0 || empty salesEstimateForm.sale.invoiceNo}">--%>
                                                                                                                    
                                                                                                                 <td style="text-align:center;"><a href="#"  class="ace-icon fa fa-trash-o bigger-120 btn-danger" onclick="myFunctionPre(this,${fn:length(salesEstimateForm.sales)},${status.index})"></a></td>
                                                                                                                 <%--</c:if>--%>    
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
										<label class="col-sm-12" for="form-field-1"> MCA </label>

										<div class="col-sm-12">
                                                                                    <form:input type="number" path="sale.mca" id="salesMCA"   value="0" class="form-control" step='0.01' oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Allowed Number Only')"  onkeypress="return isNumberKey(event)" min="0" required="true"/>
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
                                                                                    <form:input type="number" path="sale.addLess" id="salesAddOrLess"  value="0" class="form-control" onkeyup="onChangeAddOrLess()" step='0.01' oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Allowed Number Only')"  onkeypress="return isNumberKey(event)" min="0" required="true"/>
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
                                                                              <input type="submit" id="btnSubmit" class="btn btn-info" value="Save"/>
                                                                               &nbsp; &nbsp; &nbsp;
                                                                                                       <input type="reset" class="btn" value="Reset" onClick="window.location.reload()">
                                                                            <c:choose>
                                                                           <c:when test="${salesEstimateForm.sale.invoiceNo==null ||  empty salesEstimateForm.sale.invoiceNo}">
                                                                                
                                                                           
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                               <a href="SalesEstimateInvoice.html?id=${salesEstimateForm.sale.invoiceNo}" target="_blank" class="btn btn-info">Inclusive of GST</a>
                                                                                <c:if test="${salesEstimateForm.sale.category=='Consumers(B2C)'}">
                                                                                <a href="SalesEstimateinvoice.html?id=${salesEstimateForm.sale.invoiceNo}" target="_blank" class="btn btn-info">GST Invoice</a>
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

		<!-- ace scripts -->
		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>
                <script src="assets/accounting-js/sales.js"></script>
                <script src="assets/accounting-js/enter2tab.js"></script>
                
 <script>
 <c:if test="${salesEstimateForm.sale.invoiceNo==null ||  empty salesEstimateForm.sale.invoiceNo}">
                window.onload = function() {
                    onChangeMode();                  
                }
         </c:if>       

        function isNumberKey(evt)
       {
          var charCode = (evt.which) ? evt.which : evt.keyCode;
          if (charCode != 46 && charCode > 31 
            && (charCode < 48 || charCode > 57))
             return false;

          return true;
       }
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
                      // for onchange Categoary
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

                  $("#id-date-picker-1").focus();
                  // to show cursor on first TextBox
                </script>
                
               
 <script>
//    $(document).on('ready', function() {
//  $('#Mytable').tableNav();
//  return $('#Mytable input').eq(1).click();
//});
</script>  
               
<script>
var i = $('table tr').length;

$(document).on('keydown', '.lst', function(e) {
  var code = (e.keyCode ? e.keyCode : e.which);
  if (code == 13) {
   e.preventDefault();    
   addRowforSalesEstimate(); 
  }
});
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
 $("#saleEstimateForm").enableEnterToTab({ captureTabKey: true });
  // Assign Manual Tabindex order 

document.getElementById("Box1").tabIndex = 1;
document.getElementById("theDate").tabIndex =2;
document.getElementById("category").tabIndex = 3;
document.getElementById("mode").tabIndex = 4;
document.getElementById("cashBuyer").tabIndex = 5;
document.getElementById("tinNo").tabIndex =6;
document.getElementById("mediator").tabIndex =7;

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
  


		<script type="text/javascript">
			jQuery(function($) {
				$('#id-disable-check').on('click', function() {
					var inp = $('#form-input-readonly').get(0);
					if(inp.hasAttribute('disabled')) {
						inp.setAttribute('readonly' , 'true');
						inp.removeAttribute('disabled');
						inp.value="This text field is readonly!";
					}
					else {
						inp.setAttribute('disabled' , 'disabled');
						inp.removeAttribute('readonly');
						inp.value="This text field is disabled!";
					}
				});
			
			
				if(!ace.vars['touch']) {
					$('.chosen-select').chosen({allow_single_deselect:true}); 
					//resize the chosen on window resize
			
					$(window)
					.off('resize.chosen')
					.on('resize.chosen', function() {
						$('.chosen-select').each(function() {
							 var $this = $(this);
							 $this.next().css({'width': $this.parent().width()});
						})
					}).trigger('resize.chosen');
					//resize chosen on sidebar collapse/expand
					$(document).on('settings.ace.chosen', function(e, event_name, event_val) {
						if(event_name != 'sidebar_collapsed') return;
						$('.chosen-select').each(function() {
							 var $this = $(this);
							 $this.next().css({'width': $this.parent().width()});
						})
					});
			
			
					$('#chosen-multiple-style .btn').on('click', function(e){
						var target = $(this).find('input[type=radio]');
						var which = parseInt(target.val());
						if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
						 else $('#form-field-select-4').removeClass('tag-input-style');
					});
				}
			
			
				$('[data-rel=tooltip]').tooltip({container:'body'});
				$('[data-rel=popover]').popover({container:'body'});
			
				autosize($('textarea[class*=autosize]'));
				
				$('textarea.limited').inputlimiter({
					remText: '%n character%s remaining...',
					limitText: 'max allowed : %n.'
				});
			
				$.mask.definitions['~']='[+-]';
				$('.input-mask-date').mask('99/99/9999');
				$('.input-mask-phone').mask('(999) 999-9999');
				$('.input-mask-eyescript').mask('~9.99 ~9.99 999');
				$(".input-mask-product").mask("a*-999-a999",{placeholder:" ",completed:function(){alert("You typed the following: "+this.val());}});
			
			
			
				$( "#input-size-slider" ).css('width','200px').slider({
					value:1,
					range: "min",
					min: 1,
					max: 8,
					step: 1,
					slide: function( event, ui ) {
						var sizing = ['', 'input-sm', 'input-lg', 'input-mini', 'input-small', 'input-medium', 'input-large', 'input-xlarge', 'input-xxlarge'];
						var val = parseInt(ui.value);
						$('#form-field-4').attr('class', sizing[val]).attr('placeholder', '.'+sizing[val]);
					}
				});
			
				$( "#input-span-slider" ).slider({
					value:1,
					range: "min",
					min: 1,
					max: 12,
					step: 1,
					slide: function( event, ui ) {
						var val = parseInt(ui.value);
						$('#form-field-5').attr('class', 'col-xs-'+val).val('.col-xs-'+val);
					}
				});
			
			
				
				//"jQuery UI Slider"
				//range slider tooltip example
				$( "#slider-range" ).css('height','200px').slider({
					orientation: "vertical",
					range: true,
					min: 0,
					max: 100,
					values: [ 17, 67 ],
					slide: function( event, ui ) {
						var val = ui.values[$(ui.handle).index()-1] + "";
			
						if( !ui.handle.firstChild ) {
							$("<div class='tooltip right in' style='display:none;left:16px;top:-6px;'><div class='tooltip-arrow'></div><div class='tooltip-inner'></div></div>")
							.prependTo(ui.handle);
						}
						$(ui.handle.firstChild).show().children().eq(1).text(val);
					}
				}).find('span.ui-slider-handle').on('blur', function(){
					$(this.firstChild).hide();
				});
				
				
				$( "#slider-range-max" ).slider({
					range: "max",
					min: 1,
					max: 10,
					value: 2
				});
				
				$( "#slider-eq > span" ).css({width:'90%', 'float':'left', margin:'15px'}).each(function() {
					// read initial values from markup and remove that
					var value = parseInt( $( this ).text(), 10 );
					$( this ).empty().slider({
						value: value,
						range: "min",
						animate: true
						
					});
				});
				
				$("#slider-eq > span.ui-slider-purple").slider('disable');//disable third item
			
				
				$('#id-input-file-1 , #id-input-file-2').ace_file_input({
					no_file:'No File ...',
					btn_choose:'Choose',
					btn_change:'Change',
					droppable:false,
					onchange:null,
					thumbnail:false //| true | large
					//whitelist:'gif|png|jpg|jpeg'
					//blacklist:'exe|php'
					//onchange:''
					//
				});
				//pre-show a file name, for example a previously selected file
				//$('#id-input-file-1').ace_file_input('show_file_list', ['myfile.txt'])
			
			
				$('#id-input-file-3').ace_file_input({
					style: 'well',
					btn_choose: 'Drop files here or click to choose',
					btn_change: null,
					no_icon: 'ace-icon fa fa-cloud-upload',
					droppable: true,
					thumbnail: 'small'//large | fit
					//,icon_remove:null//set null, to hide remove/reset button
					/**,before_change:function(files, dropped) {
						//Check an example below
						//or examples/file-upload.html
						return true;
					}*/
					/**,before_remove : function() {
						return true;
					}*/
					,
					preview_error : function(filename, error_code) {
						//name of the file that failed
						//error_code values
						//1 = 'FILE_LOAD_FAILED',
						//2 = 'IMAGE_LOAD_FAILED',
						//3 = 'THUMBNAIL_FAILED'
						//alert(error_code);
					}
			
				}).on('change', function(){
					//console.log($(this).data('ace_input_files'));
					//console.log($(this).data('ace_input_method'));
				});
				
				
				//$('#id-input-file-3')
				//.ace_file_input('show_file_list', [
					//{type: 'image', name: 'name of image', path: 'http://path/to/image/for/preview'},
					//{type: 'file', name: 'hello.txt'}
				//]);
			
				
				
			
				//dynamically change allowed formats by changing allowExt && allowMime function
				$('#id-file-format').removeAttr('checked').on('change', function() {
					var whitelist_ext, whitelist_mime;
					var btn_choose
					var no_icon
					if(this.checked) {
						btn_choose = "Drop images here or click to choose";
						no_icon = "ace-icon fa fa-picture-o";
			
						whitelist_ext = ["jpeg", "jpg", "png", "gif" , "bmp"];
						whitelist_mime = ["image/jpg", "image/jpeg", "image/png", "image/gif", "image/bmp"];
					}
					else {
						btn_choose = "Drop files here or click to choose";
						no_icon = "ace-icon fa fa-cloud-upload";
						
						whitelist_ext = null;//all extensions are acceptable
						whitelist_mime = null;//all mimes are acceptable
					}
					var file_input = $('#id-input-file-3');
					file_input
					.ace_file_input('update_settings',
					{
						'btn_choose': btn_choose,
						'no_icon': no_icon,
						'allowExt': whitelist_ext,
						'allowMime': whitelist_mime
					})
					file_input.ace_file_input('reset_input');
					
					file_input
					.off('file.error.ace')
					.on('file.error.ace', function(e, info) {
						//console.log(info.file_count);//number of selected files
						//console.log(info.invalid_count);//number of invalid files
						//console.log(info.error_list);//a list of errors in the following format
						
						//info.error_count['ext']
						//info.error_count['mime']
						//info.error_count['size']
						
						//info.error_list['ext']  = [list of file names with invalid extension]
						//info.error_list['mime'] = [list of file names with invalid mimetype]
						//info.error_list['size'] = [list of file names with invalid size]
						
						
						/**
						if( !info.dropped ) {
							//perhapse reset file field if files have been selected, and there are invalid files among them
							//when files are dropped, only valid files will be added to our file array
							e.preventDefault();//it will rest input
						}
						*/
						
						
						//if files have been selected (not dropped), you can choose to reset input
						//because browser keeps all selected files anyway and this cannot be changed
						//we can only reset file field to become empty again
						//on any case you still should check files with your server side script
						//because any arbitrary file can be uploaded by user and it's not safe to rely on browser-side measures
					});
					
					
					/**
					file_input
					.off('file.preview.ace')
					.on('file.preview.ace', function(e, info) {
						console.log(info.file.width);
						console.log(info.file.height);
						e.preventDefault();//to prevent preview
					});
					*/
				
				});
			
				$('#spinner1').ace_spinner({value:0,min:0,max:200,step:10, btn_up_class:'btn-info' , btn_down_class:'btn-info'})
				.closest('.ace-spinner')
				.on('changed.fu.spinbox', function(){
					//console.log($('#spinner1').val())
				}); 
				$('#spinner2').ace_spinner({value:0,min:0,max:10000,step:100, touch_spinner: true, icon_up:'ace-icon fa fa-caret-up bigger-110', icon_down:'ace-icon fa fa-caret-down bigger-110'});
				$('#spinner3').ace_spinner({value:0,min:-100,max:100,step:10, on_sides: true, icon_up:'ace-icon fa fa-plus bigger-110', icon_down:'ace-icon fa fa-minus bigger-110', btn_up_class:'btn-success' , btn_down_class:'btn-danger'});
				$('#spinner4').ace_spinner({value:0,min:-100,max:100,step:10, on_sides: true, icon_up:'ace-icon fa fa-plus', icon_down:'ace-icon fa fa-minus', btn_up_class:'btn-purple' , btn_down_class:'btn-purple'});
			
				//$('#spinner1').ace_spinner('disable').ace_spinner('value', 11);
				//or
				//$('#spinner1').closest('.ace-spinner').spinner('disable').spinner('enable').spinner('value', 11);//disable, enable or change value
				//$('#spinner1').closest('.ace-spinner').spinner('value', 0);//reset to 0
			
			
				//datepicker plugin
				//link
				 var date = new Date();
                                    var dd = date.getDate();             
                                    var mm = date.getMonth() + 1;
                                    var yyyy = date.getFullYear();
                                    
                                    if(dd<10) {
                                            dd='0'+dd
                                        } 

                                        if(mm<10) {
                                            mm='0'+mm
                                        }
                                  
                                       var ToDate = dd + '/' + mm + '/' + yyyy;
                                      
                                       
				$('.date-picker').datepicker({
                                     defaultDate : ToDate,
					autoclose: true,
					todayHighlight: true
                                       
				}),
                                $('.date-picker').val(ToDate)
				//show datepicker when clicking on the icon
				.next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
			
				//or change it into a date range picker
				$('.input-daterange').datepicker({autoclose:true});
			
			
				//to translate the daterange picker, please copy the "examples/daterange-fr.js" contents here before initialization
				$('input[name=date-range-picker]').daterangepicker({
					'applyClass' : 'btn-sm btn-success',
					'cancelClass' : 'btn-sm btn-default',
					locale: {
						applyLabel: 'Apply',
						cancelLabel: 'Cancel',
					}
				})
				.prev().on(ace.click_event, function(){
					$(this).next().focus();
				});
			
			
				$('#timepicker1').timepicker({
					minuteStep: 1,
					showSeconds: true,
					showMeridian: false,
					disableFocus: true,
					icons: {
						up: 'fa fa-chevron-up',
						down: 'fa fa-chevron-down'
					}
				}).on('focus', function() {
					$('#timepicker1').timepicker('showWidget');
				}).next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
				
				
			
				
				if(!ace.vars['old_ie']) $('#date-timepicker1').datetimepicker({
				 //format: 'MM/DD/YYYY h:mm:ss A',//use this option to display seconds
				 icons: {
					time: 'fa fa-clock-o',
					date: 'fa fa-calendar',
					up: 'fa fa-chevron-up',
					down: 'fa fa-chevron-down',
					previous: 'fa fa-chevron-left',
					next: 'fa fa-chevron-right',
					today: 'fa fa-arrows ',
					clear: 'fa fa-trash',
					close: 'fa fa-times'
				 }
				}).next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
				
			
				$('#colorpicker1').colorpicker();
				//$('.colorpicker').last().css('z-index', 2000);//if colorpicker is inside a modal, its z-index should be higher than modal'safe
			
				$('#simple-colorpicker-1').ace_colorpicker();
				//$('#simple-colorpicker-1').ace_colorpicker('pick', 2);//select 2nd color
				//$('#simple-colorpicker-1').ace_colorpicker('pick', '#fbe983');//select #fbe983 color
				//var picker = $('#simple-colorpicker-1').data('ace_colorpicker')
				//picker.pick('red', true);//insert the color if it doesn't exist
			
			
				$(".knob").knob();
				
				
				var tag_input = $('#form-field-tags');
				try{
					tag_input.tag(
					  {
						placeholder:tag_input.attr('placeholder'),
						//enable typeahead by specifying the source array
						source: ace.vars['US_STATES'],//defined in ace.js >> ace.enable_search_ahead
						/**
						//or fetch data from database, fetch those that match "query"
						source: function(query, process) {
						  $.ajax({url: 'remote_source.php?q='+encodeURIComponent(query)})
						  .done(function(result_items){
							process(result_items);
						  });
						}
						*/
					  }
					)
			
					//programmatically add/remove a tag
					var $tag_obj = $('#form-field-tags').data('tag');
					$tag_obj.add('Programmatically Added');
					
					var index = $tag_obj.inValues('some tag');
					$tag_obj.remove(index);
				}
				catch(e) {
					//display a textarea for old IE, because it doesn't support this plugin or another one I tried!
					tag_input.after('<textarea id="'+tag_input.attr('id')+'" name="'+tag_input.attr('name')+'" rows="3">'+tag_input.val()+'</textarea>').remove();
					//autosize($('#form-field-tags'));
				}
				
				
				/////////
				$('#modal-form input[type=file]').ace_file_input({
					style:'well',
					btn_choose:'Drop files here or click to choose',
					btn_change:null,
					no_icon:'ace-icon fa fa-cloud-upload',
					droppable:true,
					thumbnail:'large'
				})
				
				//chosen plugin inside a modal will have a zero width because the select element is originally hidden
				//and its width cannot be determined.
				//so we set the width after modal is show
				$('#modal-form').on('shown.bs.modal', function () {
					if(!ace.vars['touch']) {
						$(this).find('.chosen-container').each(function(){
							$(this).find('a:first-child').css('width' , '210px');
							$(this).find('.chosen-drop').css('width' , '210px');
							$(this).find('.chosen-search input').css('width' , '200px');
						});
					}
				})
				/**
				//or you can activate the chosen plugin after modal is shown
				//this way select element becomes visible with dimensions and chosen works as expected
				$('#modal-form').on('shown', function () {
					$(this).find('.modal-chosen').chosen();
				})
				*/
			
				
				
				$(document).one('ajaxloadstart.page', function(e) {
					autosize.destroy('textarea[class*=autosize]')
					
					$('.limiterBox,.autosizejs').remove();
					$('.daterangepicker.dropdown-menu,.colorpicker.dropdown-menu,.bootstrap-datetimepicker-widget.dropdown-menu').remove();
				});
			
			});
		</script>
<script>
    getUnit(${fn:length(salesEstimateForm.sales)});
</script>         
    </body>
    
</html>
