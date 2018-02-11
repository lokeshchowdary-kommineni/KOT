 <!DOCTYPE html>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>Accounting-Purchase</title>

		<meta name="description" content="" />
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

		<link rel="stylesheet" href="assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />

                <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
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
                <c:when test="${group.moduleName=='Purchase'}">
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
								<a href="PurchaseGrid.html">Purchase Records</a>
							</li>
							<li class="active">Purchase </li>
						</ul><!-- /.breadcrumb -->

					
                                            		
					</div>

					<div class="page-content">
					
                                         
                                                            <!-- /.row -->
                                                           <div class="col-xs-12" id="addrowDiv">
								<!-- PAGE CONTENT BEGINS -->
                                                                <form:form class="form-horizontal" action="SavePurchase.html"  commandName="purchaseFrom" method="POST" id="pFrom" autocomplete="Off">
                                                                    <div class="widget-box lighter  widget-color-blue2">
                                                                    <div class="widget-header">
                                                                <h5 class="widget-title">Purchase</h5>${pId}
                                                                    </div>
                                                                         <div class="widget-body">
                                                                <div class="widget-main alert-success">
                                                                <div class="row">
									<input type="hidden" name="pid" value="${pId}">
                                                                    
                                                                       
                                                                    <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Invoice NO<span style="color:red;">*</span></label>
                                                                                    
										<div class="col-sm-12">
                                                                                        <form:input type="text" id="Box1" tabindex="1"  path="purchase.purchaseInvoiceId" step='0.01'  class='form-control TabOnEnter'  oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Allowed Alphanumeric Only')"  min="0" placeholder="invoice no"    required="true"/>
                                                                                       
										</div>
								    </div>
                                                                    <div class="form-group col-xs-2">
										<label class="col-sm-6" for="form-field-1"> Date<span style="color:red;">*</span></label>

										<div class="col-sm-12">
                                                                                        <form:input path="purchase.date"  tabindex="4" class="form-control date-picker TabOnEnter"   id="mydate" placeholder="date" type="text" data-date-format="dd/mm/yyyy" required="true"/>
                                                                                    <form:errors path="purchase.date" />
											
										</div>
								    </div>
									
							        	<div class="form-group col-xs-3">
										<label class="col-sm-8"  for="form-field-1"> Category <span style="color:red;">*</span></label>

										<div class="col-sm-12">
                                                                                     
                                                                                    <form:select path="purchase.category" class="form-control TabOnEnter" name="category" id="category" onChange=""   oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Please Select Category')" required="true">
                                                                                       <form:option value="" label="-------Select-------"/>
                                                                                        <form:option value="ITC Purchase" label="ITC Purchase "/>
                                                                                        <form:option value="Purchase from Composite Dealers" label="Purchase from Composite Dealers"/>
                                                                                        <form:option value="Purchases from unregistered Persons" label="Purchases from unregistered Persons"/>                                                                                       
                                                                                        
                                                                                    </form:select>
                                                                                    <form:errors path="purchase.category" />
											
										</div>
									</div>
									
									
									
									<div class="form-group col-xs-3">
										<label class="col-sm-12" for="form-field-1">Name of  Supplier </label>
                                                                                    <form:hidden path="purchase.supplierId" id="supplierNameId"/>
										<div class="col-sm-12">
											<%--<form:input type="text" tabindex="3" path="purchase.cashSupplier" id="cashSupplier"  placeholder="cashSupplier" class="form-control TabOnEnter"/>--%>
											<%--<form:errors path="purchase.cashSupplier" />--%>
                                                                                <form:select path="purchase.cashSupplier" class="form-control" id="form-field-select-1" required="">
                                                                                    <form:option value="" label="choose supplier"/>
                                                                                    <c:forEach items="${supplierList}" var="itemGroup">
                                                                                    <form:option value="${itemGroup.supplierName}" label="${itemGroup.supplierName}"/>
                                                                                    </c:forEach>	
                                                                                </form:select>
										</div>
									</div>
                                                                               <div class="form-group col-xs-3" >               
                                                                        <!--<div class="form-group col-xs-3" id="posDiv" hidden>-->
										<label class="col-sm-12" for="form-field-1" >State</label>
										<div class="col-sm-12">
                                                                                        <form:select path="purchase.pos" tabindex="-1" class="form-control" id="pos" required="true" onChange="posStatesEqual()" oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Please Select Pos')">
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
										</div>
									</div>        
								</div>
                                                        
                                                       <input type="hidden" name="CompanyGstno" id="CompanyGstno" value="${CompanyGstno}" />
                                                       <form:hidden   path="purchase.isIgst" id="final" class="form-control" />    
                                                                                
							<div class="row">
                                                            <div class="form-group col-xs-2" >
								<label class="col-sm-8" for="form-field-1"> Mode<span style="color:red;">*</span></label>

								<div class="col-sm-12">
                                                                    <form:select path="purchase.mode" class="form-control" placeholder="mode"   required="">
                                                                    <form:option value="" label="-------Select-------"/>
                                                                    <form:option value="Cash" lable="Cash"/>
                                                                    <form:option value="Credit" lable="Credit"/>
                                                                    </form:select>
                                                                <%--<form:errors path="purchase.mode" />--%>
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
                                 <input type="button" class="btn btn-yellow m-b-sm " value="Add Row" onclick="addRowforPurchase()"/> 
					<div class="widget-box widget-color-blue" id="widget-box-2">
                                    <div class="widget-body">
                                        <div class="widget-main no-padding">
                                            <table class="table table-striped table-bordered table-hover" id="Mytable">
                                                <thead class="thin-border-bottom">
                                                    <tr>
                                                        
                                                        <th  rowspan="2" style="width: 10%; ">                                                                                                                       
                                                           Item Code
                                                        </th>
                                                        <th rowspan="2" style="width: 26%; ">
                                                            Name Of the Item
                                                        </th>
                                                        <th rowspan="2" style="width: 6%; ">
                                                            Qty
                                                        </th>
                                                        <th rowspan="2" style="width:100px; ">
							Unit
                                                        </th>   
                                                        <th rowspan="2" style="width: 9%; ">                                                        
                                                            Basic Price                                                       
                                                        </th>                                                        
						         <th rowspan="2"  style="width: 9%; ">													
                                                           <c:choose>                                                                                                                     
                                                        <c:when test="${CategoryAuto=='ITC Purchase'}">

                                                             <p id="demo">EP</p> 
                                                        </c:when>   
                                                      
                                                                                                                                                                              
                                                        <c:when test="${CategoryAuto==''}">

                                                             <p id="demo">EP</p> 
                                                        </c:when>  
                                                             
                                                        <c:otherwise>
                                                           <p id="demo">TP</p> 
                                                        </c:otherwise>
                                                       
                                                            </c:choose> 
                                                        </th>                                                        
							<th rowspan="2" >														
                                                            Amount                                         
                                                        </th> 
                                                        <th  colspan="2">                                                                
                                                            IGST                                                  
                                                        </th>                                                        
                                                        <th  colspan="2">                                                                
                                                            CGST                                                  
                                                        </th>                                                         
                                                        <th  colspan="2">                                                                
                                                            SGST                                                  
                                                        </th>
                                                       
                                                         <c:if test="${empty purchaseFrom.purchase.invoiceNo}">
                                                         <th rowspan="2">                                                                
                                                            Delete                                           
                                                        </th> 
                                                        </c:if>
                                                        
                                                        
                                                    </tr>
                                                    <tr>           
                                                      <th rowspan="2" style="width:50px; "> % </th>
                                                      <th rowspan="2" style="width:70px; " > Amt </th>
                                                      <th rowspan="2" style="width:50px; "> % </th>
                                                      <th rowspan="2" style="width:70px; "> Amt </th>
                                                      <th rowspan="2" style="width:50px; "> % </th>
                                                      <th rowspan="2" style="width:70px; "> Amt </th>
                                                      
                                                      
                                                  </tr>
                                                                                                            
                                                </thead>                                                                 
                                                                                                                                                                                                                                                                      
                                                <c:forEach items="${purchaseFrom.purchases}" var="purchases" varStatus="status"> 
                                                                                                <tbody>
                                                                                                        <tr class="txtMult">
                                                                                                                 <form:hidden path="purchases[${status.index}].id"/>
                                                                                                                  <form:hidden path="purchases[${status.index}].cpPriceUpdate" id="priceUpdateId${status.index}"  value="1"/>
                                                                                                               <input type="hidden" id="tableLength${status.index}" value="${fn:length(purchaseFrom.purchases)}" />
                                                                                                                 <form:hidden path="purchases[${status.index}].tax" id="purchasesTax${status.index}" />
                                                                                                                 <input type="hidden"  id="hiddenUnit${status.index}" value="${purchases.unit}"/>
                                                                                                                <td><form:input path="purchases[${status.index}].itemCode" id="purchaseItemCode${status.index}"  type="text"  class=" inputs form-control ui-autocomplete-input" autocomplete="off" onKeypress="purchaseCategoryCheck('${status.index}');" onChange="purchasesCalculation(${fn:length(purchaseFrom.purchases)}); " oninvalid="setCustomValidity('  Enter Item Code')"   required="true"/><form:errors path="purchases[${status.index}].itemCode" /></td>
                                                                                                                <td><form:input path="purchases[${status.index}].nameOfTheItem"   id="purchaseNameOfTheItem${status.index}" type="text"  class=" inputs form-control" onKeypress="purchaseCategoryCheck('${status.index}');" onChange="purchasesCalculation(${fn:length(purchaseFrom.purchases)}); "/></td>
                                                                                                                <td><form:input path="purchases[${status.index}].qty" id="purchaseQty${status.index}" type="text" style="text-align:center;" class=" inputs form-control" required="true" onClick="SelectName(${status.index});"  onChange="onChangeQty('${status.index}'); purchasesCalculation(${fn:length(purchaseFrom.purchases)})" step='0.01' oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Allowed Number Only')"  onkeypress="return isNumberKey(event)" min="0"/></td>                                                                                                                
                                                                                                                <td><form:select path="purchases[${status.index}].unit" id="purchaseUnit${status.index}" style="text-align:right;" class="inputs form-control">
                                                                                                                        
                                                                                                                </form:select></td>
                                                                                                                <td><form:input path="purchases[${status.index}].rate"  id="purchaseRate${status.index}" type="text" style="text-align:right" class=" inputs form-control" onkeyup="ItemMasterPopUP(event,'${status.index}');"  onChange="purchasesCalculation(${fn:length(purchaseFrom.purchases)}); getUnit(${fn:length(purchaseFrom.purchases)})" step='0.01' oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Allowed Number Only')"  onkeypress="return isNumberKey(event)" min="0"/></td>                                                                                                               
                                                                                                                <td><form:input path="purchases[${status.index}].ep" id="purchaseEp${status.index}" type="text" style="text-align:right" class="inputs lst form-control"  onChange="onChangeQty('${status.index}'); purchasesCalculation(${fn:length(purchaseFrom.purchases)});UpdateInfo('${status.index}');" step='0.01' oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Allowed Number Only')"  onkeypress="return isNumberKey(event)" min="0"/></td>
                                                                                                                <td><form:input path="purchases[${status.index}].amount" id="purchaseAmount${status.index}" type="text" style="text-align:right" class="inputs form-control " readonly="true"/></td>
                                                                                                     
                                                                                                                <td><form:input path="purchases[${status.index}].taxIgst" id="purchasetaxIgst${status.index}" type="text" style="text-align:center;" class="inputs lst form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="purchases[${status.index}].igst" id="purchaseIgst${status.index}" type="text" style="text-align:right" class="inputs lst form-control" readonly="true"/></td>  
                                                                                                                <td><form:input path="purchases[${status.index}].taxCgst" id="purchasetaxCgst${status.index}" type="text" style="text-align:center;" class="inputs form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="purchases[${status.index}].cgst" id="purchaseCgst${status.index}" type="text" style="text-align:right" class="inputs form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="purchases[${status.index}].taxSgst" id="purchasetaxSgst${status.index}" type="text" style="text-align:center;" class="inputs lst form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="purchases[${status.index}].vat" id="purchaseVat${status.index}" type="text" style="text-align:right" class="inputs lst form-control" readonly="true"/></td>  
                                                                                                                
                                                                                                                
                                                                                                                     <c:if test="${empty purchaseFrom.purchase.invoiceNo}">
                                                                                                                    
                                                                                                                 <td style="text-align:center;"><a href="#" class="ace-icon fa fa-trash-o bigger-120 btn-danger"  onclick="deleteFunction(this,${fn:length(purchaseFrom.purchases)},${status.index})"></a></td>
                                                                                                                 </c:if>
                                                                                                                   
                                                                                                                
                                                                                                                
                                                                                                                
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
									
                                <div class="form-group col-xs-2">
                                    <label class="col-sm-12" for="form-field-1"> Total Amount</label>
                                        <div class="col-sm-12">
                                            <form:input type="text" path="purchase.totalAmount" id="purchaseAmount" placeholder="totalAmount" class="form-control"  readonly="true"/>
                                            <form:errors path="purchase.totalAmount" />
                                        </div>
                                </div>

                                <div class="form-group col-xs-2">
                                   <label class="col-sm-12" for="form-field-1"> IGST </label>
                                        <div class="col-sm-12">
                                            <form:input type="number" path="purchase.defIgst" onkeyup="purchaseTotal();"  id="totalIgst" placeholder="totalIgst" step='0.01' oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Allowed Number Only')"  onkeypress="return isNumberKey(event)" min="0"  class="form-control" />
                                        </div>
                                </div>
                                        <form:hidden  path="purchase.totalIgst"   id="Igst"  class="form-control" />

                                <div class="form-group col-xs-2">
                                    <label class="col-sm-12" for="form-field-1"> CGST </label>
                                        <div class="col-sm-12">
                                             <form:input type="number" path="purchase.defCgst" onkeyup="purchaseTotal();"  id="totalCgst" placeholder="totalCgst" step='0.01' oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Allowed Number Only')"  onkeypress="return isNumberKey(event)" min="0"  class="form-control" />
                                                  <form:errors path="purchase.totalCgst" />
                                        </div>
                                </div>
                                 <form:hidden  path="purchase.totalCgst"   id="Cgst" placeholder="totalCgst" class="form-control" />

                                <div class="form-group col-xs-2">
                                    <label class="col-sm-6" for="form-field-1"> SGST </label>
                                        <div class="col-sm-12">
                                         <form:input type="number" path="purchase.defVat"  onkeyup="purchaseTotal();" id="totalVat" placeholder="totalVat" step='0.01' oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Allowed Number Only')"  onkeypress="return isNumberKey(event)" min="0" class="form-control" />
                                            <form:errors path="purchase.totalVat" />
                                        </div>
                                </div>
                                <%--<form:hidden  path="purchase.totalVat" id="purchaseVat" placeholder="totalVat" class="form-control"/>--%>
                                <div class="form-group col-xs-2">
                                    <label class="col-sm-6" for="form-field-1"> Add/Less</label>
                                    <div class="col-sm-12">
                                        <form:input type="text" path="purchase.addOrLess"    id="AddLess"  onkeyup="Addorless();" placeholder="" class="form-control"   readonly=""/>

                                    </div>
                                </div>
                                <form:hidden  path="purchase.defAddless"    id="defAddless" placeholder="" class="form-control" readonly="true"/>
                                <div class="form-group col-xs-2">
                                    <label class="col-sm-12" for="form-field-1"> Invoice Amount</label>
                                        <div class="col-sm-12">
                                            <form:input type="text" path="purchase.totalInvoiceAmount" id="TInvoiceAmount" placeholder="totalInvoiceAmount" class="form-control vatAll" readonly="true"/>
                                             <form:errors path="purchase.totalInvoiceAmount" />
                                        </div>
                                </div>
	
			</div> 
                    </div>
                </div>
                                                                
            </div>
                                               
			                                    <%--<c:if test="${pId==null || empty pId }">--%>
									<div style="text-align:center">
									<button id="btnSubmit"class="btn btn-success">Save</button>
                                                                         &nbsp; &nbsp; &nbsp;
                                                                        <button class="btn" type="reset" onClick="window.location.reload()">  Reset </button>
                                                                       </div>
                                                               <%--</c:if>--%>           
									  <div class="addrowIMDiv">		
                                                                <div id="modal-form" class="modal" tabindex="-1">
                                                                   
									<div class="modal-dialog" style="width:90% !important;">
										
								</div>
                                                                              </div>
                                                                
									  </div>
                                                                      </div>
                
    <!--model pop Up-->                                                        
                                                                          
              <div class="modal fade" id="myModal" tabindex="22" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog" style="width: 890px;">
                      <div class="modal-content" style="width: 975px;">
                        <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                          <h4 class="modal-title" id="myModalLabel">Unit Conversion</h4>
                        </div>
                        <div class="modal-body">
                         <div class="main-content">
                                                  <div class="main-content-inner">
                                      
                                                          <div class="page-content">
                                                          <form>
                                                                <input type="hidden" name="" id="itemIndexValue"/> 
                                                              
                                                              ml : <select name="input1" id="input1" onkeyup="calc()">
                                                                         <option value="180">180</option>
                                                                         <option value="375">375</option>
                                                                         <option value="750">750</option>
                                                                         <option value="1000">1000</option>
                                                                   </select>

                                                        No Of Qty :<input type="text" name="input2" id="input2" onkeyup="calc()" value=""> 

                                                             Case :   <input type="text" name="input3" id="input3" onkeyup="calc()" value=""> <a href="javascript: void(0)" onClick="calc()"></a>

                                                            Liter :  <input type="text" name="output"  id="output" value="">
                                                            
                                                             <input type="button" id="xd" value="add row" style="margin-top: 10px;">



                                                          </form>
                                                          <table id="Table" style="margin-top: 20px;">
                                                              <thead style ="background-color: #438EB9;height: 40px;">
                                                              <tr>
                                                                  <td rowspan="2" style="width: 10%; color:#fff;" >ml</td>
                                                                  <td rowspan="2" style="width: 10%; color:#fff;">No of Quantity</td>
                                                                  <td rowspan="2" style="width: 10%; color:#fff;">Case</td>
                                                                  <td rowspan="2" style="width: 10%; color:#fff;">Liter</td>
                                                                  <td rowspan="2" style="width: 10%; color:#fff;">Delete</td>
                                                              </tr>
                                                              </thead>
                                                              <tbody id="TableBody">
                                                              </tbody>
                                                              <tfoot id="TableFooter">
                                                              <tr>
                                                                  <td colspan="3">Total</td>
                                                                  <td class ="someTotalPrice" id="tid"></td>
                                                              </tr>
                                                              </tfoot>
                                                          </table>

                                                     
                                                      </div>
                                                  </div>
                                          </div>
                        </div>
                        <div class="modal-footer">
                          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                          <button type="button" class="btn btn-primary"  onclick="SetName();" data-dismiss="modal">Save changes</button>
                        </div>
                      </div>
                    </div> 
              </div>	
									</form:form> 
                                                      
                                                                    
                                                                  

                                                
					</div>
                                    
				</div>
			</div>
                        </div>    

			 <%@ include file="Footer.jsp" %>

		</div><!-- /.main-container -->
                     

		<!-- <![endif]-->

		<!--[if IE]>
<script src="assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
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
                
                    <script src="assets/accounting-js/purchase.js"></script>
                       <script src="assets/accounting-js/enter2tab.js"></script>
		<!-- inline scripts related to this page -->
  <c:if test="${pId==null || empty pId }">                
   <script>
                window.onload = function() {
                    onChangePMode();
}
   </script>
   
  </c:if>      
 
                <script>
                    function show()
                    {
                        var myselect = document.getElementById("category").value;
                       
                       if(myselect == "ITC Purchase")
                       
                        {
                             txt = "EP";
                  }
                  else {
                             txt = "TP";
                         }
                  document.getElementById("demo").innerHTML = txt;
                        
                    }
                </script>
               <script>
var i = $('table tr').length;

$(document).on('keydown', '.lst', function(e) {
  var code = (e.keyCode ? e.keyCode : e.which);
  if (code == 13) {
   e.preventDefault();    
   addRowforPurchase(); 
  }
});
</script>
                 <script>
                
 var tabindex = 10;
    $('input,select,button,textarea').each(function() {
        if (this.type != "hidden") {
            var $input = $(this);
            $input.attr("tabindex", tabindex);
            tabindex++;
        }
});  
 $("#pFrom").enableEnterToTab({ captureTabKey: true });
  // Assign Manual Tabindex order 

//document.getElementById("Box1").tabIndex = 1;
//document.getElementById("mydate").tabIndex =2;
document.getElementById("category").tabIndex = 3;
//document.getElementById("mode").tabIndex = 4;
//document.getElementById("cashSupplier").tabIndex = 5;
//document.getElementById("tinNo").tabIndex =6;
//document.getElementById("purchase.narration").tabIndex =7;
//document.getElementById("InvoiceAmount").tabIndex =8;
 $('#btnSubmit').keydown(function(event){
          //Check if the key pressed is ENTER key using the keyCode=13
          if(event.keyCode === 13){
//           alert('Successfully!'); 
          }
          event.cancelBubble = true;
             if (event.stopPropagation) event.stopPropagation();
 });
 $("#Box1").focus();
 // to show cursor on first TextBox
                </script>
                




		<script type="text/javascript">
                    
                    $(document).on('keypress','.enter_popup',function(){
    /*Your code*/
});
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
					
				});
				
			
				$('#id-input-file-3').ace_file_input({
					style: 'well',
					btn_choose: 'Drop files here or click to choose',
					btn_change: null,
					no_icon: 'ace-icon fa fa-cloud-upload',
					droppable: true,
					thumbnail: 'small'//large | fit
					
					,
					preview_error : function(filename, error_code) {
					}
			
				}).on('change', function(){
				});
				
				
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
						});
					
				});
			
				$('#spinner1').ace_spinner({value:0,min:0,max:200,step:10, btn_up_class:'btn-info' , btn_down_class:'btn-info'})
				.closest('.ace-spinner')
				.on('changed.fu.spinbox', function(){
					//console.log($('#spinner1').val())
				}); 
				$('#spinner2').ace_spinner({value:0,min:0,max:10000,step:100, touch_spinner: true, icon_up:'ace-icon fa fa-caret-up bigger-110', icon_down:'ace-icon fa fa-caret-down bigger-110'});
				$('#spinner3').ace_spinner({value:0,min:-100,max:100,step:10, on_sides: true, icon_up:'ace-icon fa fa-plus bigger-110', icon_down:'ace-icon fa fa-minus bigger-110', btn_up_class:'btn-success' , btn_down_class:'btn-danger'});
				$('#spinner4').ace_spinner({value:0,min:-100,max:100,step:10, on_sides: true, icon_up:'ace-icon fa fa-plus', icon_down:'ace-icon fa fa-minus', btn_up_class:'btn-purple' , btn_down_class:'btn-purple'});
			
				//datepicker plugin
				//link
                                   var date = new Date();
                                    var dd = date.getDate();  
                                    var mm = date.getMonth()+1;
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
                                

			//date picker current date
				$('#txtSelectedDate').datepicker({
                               showButtonPanel: true,
                                currentText: "Today:" + $.datepicker.formatDate('dd MM , yyyy', new Date())  
});
			
				
			
				
			
				
				
				
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
				
				
				//chosen plugin inside a modal will have a zero width because the select element is originally hidden
				//and its width cannot be determined.
				//so we set the width after modal is show
			
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
                function ItemMasterPopUP(event,i) {                    
                    if(event.keyCode == 13 || event.keyCode == 16 || event.keyCode == 9) {                         
                    event.preventDefault();
                    return true;
                    }
                    else
                    { 
                    $('#modal-form').modal('show');                   
                     var idItem=$("#purchaseItemCode"+i+"").val();
                     var basicValue=$("#purchaseRate"+i+"").val();      
                     var trlength=$("#tableLength"+i+"").val(); 
                    var indexsend=i;
                     $.ajax({  
                      type : "POST",   
                      url : "PopUpWindowItemMaster.html",   
                      data : {id:idItem,index:indexsend},
                      success : function(response) {

                           $('#modal-form').empty().append(response);
                            $('#itemIndexValue').val(i);
                            $('#itemTableValue').val(trlength);
                            $('#basicPrice').val(basicValue);
                            onChangeBasicPrice();
                        },  

                      error : function(e) {  

                       alert('Error: ' + e);   
                      }  
                     }); 
                    }
 
  }
</script>
<script>
    getUnit(${fn:length(purchaseFrom.purchases)});
</script>

<script>
    function edValueKeyPress()
    {
        var edValue = document.getElementById("edValue");
        var s = edValue.value;
    
        var lblValue = document.getElementById("lblValue");
        lblValue.innerText = "The text box contains: "+s;
    
    }
</script> 
<script>        
                function SelectName(i) {   
                     $('#itemIndexValue').val(i);
                    $('#myModal').modal('show');
  }
</script>
<script>
function calc() {
    var textValue1 = document.getElementById('input1').value;
    var textValue2 = document.getElementById('input2').value;
    var textValue3 = document.getElementById('input3').value;

    document.getElementById('output').value = textValue1 * textValue2 *textValue3/1000;
}
</script>
<script type="text/javascript">

        // Find and remove selected table rows
        
  function deleteRow(btn) {
        var row = btn.parentNode.parentNode;
        row.parentNode.removeChild(row);
        sumOfColumns();
    }

    function sumOfColumns(){

        var totalPrice = 0;
        $(".classPrice").each(function(){
            totalPrice += parseFloat($(this).html());
        });
         $(".someTotalPrice").html(totalPrice);
    }

    $(document).ready(function () {
        
        $('#xd').click(function() {
            var lines = '<input type="button" value="Delete" onclick="deleteRow(this)"/>';
            var input1 = $("#input1").val();
            var input2 = $("#input2").val();
            var input3 = $("#input3").val();
            var output = $("#output").val();
            var lines1 = '<td class = "classPrice">' +output+'</td>';
            var markup = "<tr><td>"+input1 +"</td><td>" + input2+ "</td><td>" +input3 + "</td>" + lines1+ "<td>"+lines+"</td></tr>";

            $('#TableBody').append(markup);
            sumOfColumns();
        });
    });
</script>
<script type="text/javascript">
    function SetName() {
               var index=document.getElementById("itemIndexValue").value;
               var totalLiter=0;
               totalLiter = document.getElementById("tid").innerHTML;
               document.getElementById("purchaseQty"+index).value=totalLiter;
               onChangeQty(index);
               purchasesCalculation(${fn:length(purchaseFrom.purchases)});
               $('#TableBody').empty();
               $('#TableBody').html('');
        }
</script>
 
	</body>
</html>
