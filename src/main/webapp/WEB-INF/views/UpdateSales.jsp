    

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
   
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accounting - Sales</title>
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
               
                



 
    </head>
    <body class="no-skin">
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
								<a href="SalesReports.html">Sales Register</a>
							</li>
							<li>
								<a href="SalesGrid.html">Sales Records</a>
							</li>
							<li class="active">Sales </li>
						</ul><!-- /.breadcrumb -->

					
                                            		
					</div>

					<div class="page-content">
						<!--- ACE Settings for template -->
                                                <!-- /.page-header -->

						<div class="row">
						<div class="col-xs-12" id="salesFormDiv">
								<!-- PAGE CONTENT BEGINS -->
                                                    <form:form class="form-horizontal" action="SaveSales.html"  commandName="salesForm" method="POST" id="saleForm" onsubmit="return myFunctionSub()" autocomplete="off" >
                                                       
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
										<label class="col-sm-12" for="form-field-1"> Invoice no</label>
                                                                               
										<div class="col-sm-12">
                                                                                    <form:input type="text" path="sale.invoiceNo" id="Box1"   class="form-control" readonly="true"/>
                                                                                        <form:errors path="sale.invoiceNo"/>
										</div>
									</div>
                                                                    <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Date <span style="color:red;">*</span></label>

										<div class="col-sm-12">
                                                                                    <form:input path="sale.date"    class="form-control date-picker" id="id-date-picker-1"  type="text" data-date-format="dd/mm/yyyy" readonly="true" required='true' />
                                                                                    <form:errors path="sale.date" />
										</div>
									</div>
                                                                        <div class="form-group col-xs-3">
										<label class="col-sm-12" for="form-field-1"> Category <span style="color:red;">*</span></label>

										<div class="col-sm-12">
                                                                                   <form:select path="sale.category"   class="form-control" id="category" onChange="onChangeCategory();show();">
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
											<form:input type="text"   path="sale.cashBuyer" id="form-field-1"  class="form-control"/>
                                                                                        <form:errors path="sale.cashBuyer" />
										</div>
									</div>
									<div class="form-group col-xs-3">
										<label class="col-sm-12" for="form-field-1"> GSTIN</label>

										<div class="col-sm-12">
                                                                                    <form:input type="text"  path="sale.tinNo" id="tinNo" class="form-control" pattern="[A-Za-z0-9]+" maxlength="15" />
                                                                                    <form:errors path="sale.tinNo" />
										</div>
									</div>
                                                                        
								</div>
								
								<div class="row">
                                                                    
                                                                    <div class="form-group col-xs-3">
										<label class="col-sm-12" for="form-field-1"> Mode<span style="color:red;">*</span></label>

										<div class="col-sm-12">
                                                                                    <form:select path="sale.mode"  class="form-control" id="mode" required="true" onChange="onChangeMode()">
                                                                                        <form:option value="" label="------Select------"/>
                                                                                        <form:option value="Cash" label="Cash"/>
                                                                                        <form:option value="Credit" label="Credit"/>  
                                                                                    </form:select>
                                                                                    
                                                                                    <form:errors path="sale.mode" />
										</div>
									</div>	
									<div class="form-group col-xs-3">
										<label class="col-sm-12"> Name of buyer <span style="color:red;">*</span></label>
                                                                                <form:hidden path="sale.buyerId" id="buyerNameId"/>
										<div class="col-sm-12">
											<form:input type="text"  path="sale.nameOfBuyer" id="buyerName"  class="form-control ui-autocomplete-input" required="true" autocomplete="off"/>
                                                                                        <form:errors path="sale.nameOfBuyer" />
                                                                                </div>
									</div>
                                                                                
                                                                        <div class="form-group col-xs-3">
										<label class="col-sm-12"> Voucher Closing Balance </label> ${bm.idBuyer}

										<div class="col-sm-9">
											<form:input type="text"  path="sale.buyerBalance" class="form-control" id="buyerBalance" readonly="true"/>
                                                                                        <form:errors path="sale.buyerBalance" />
                                                                                       
										</div>
                                                                                <form:input type="hidden" path=""  class="form-control" id="creditAmount" />
										<div class="col-sm-3"> 
                                                                                <form:input type="text" path="sale.buyerType" class="form-control" id="buyerType" readonly="true"/>
										</div>
                                                                                  
									</div>
                                                                    </div>   
                                                                                   <div class="row">
                                                                        <div class="form-group col-xs-3">
										<label class="col-sm-12" for="form-field-1"> Work site</label>

										<div class="col-sm-12">
                                                                                    <form:input type="text"  path="sale.workSite" id="form-field-1"  required="true" class="form-control"/>
                                                                                    <form:errors path="sale.workSite"/>
										</div>
                                                                        </div>
                                                                        <div class="form-group col-xs-3">
										<label class="col-sm-12"> Name of mediator </label>
                                                                                <form:hidden path="sale.mediatorId" id="mediatorNameId"/>
										<div class="col-sm-12">
											<form:input type="text"  path="sale.nameOfMediator" id="mediator"  class="form-control ui-autocomplete-input" autocomplete="off"/>
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
                                                                                <form:input type="text" path="sale.mediatorType" class="form-control" id="mediatorType" readonly="true"/>
										
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
                                                                            <input type="button" class="btn btn-yellow m-b-sm" value="Add Row" onclick="addRowforSales()"/> 
											<div class="widget-box widget-color-blue" id="widget-box-2">
                                                                                            
                                                                        <div class="widget-body">
                                                                                <div class="widget-main no-padding">
                                                                                        <table class="table table-striped table-bordered table-hover" id="Mytable">
                                                                                                <thead class="thin-border-bottom">
                                                                                                        <tr>
                                                                                                                <th rowspan="2">
                                                                                                                       
                                                                                                                        Item code
                                                                                                                </th>

                                                                                                                <th rowspan="2">
                                                                                                                        Name of the item
                                                                                                                </th>
                                                                                                                <th rowspan="2">
                                                                                                                        Qty
                                                                                                                </th>
                                                                                                                <th rowspan="2">
                                                                                                                    &nbsp;&nbsp;&nbsp;Unit&nbsp;&nbsp;
                                                                                                                </th>
                                                                                                                <th rowspan="2">
                                                                                                                        Margin
                                                                                                                </th>
                                                                                                                <th rowspan="2">
                                                                                                                        Rate
                                                                                                                </th>
                                                                                                                 <th rowspan="2">
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
                                                                                                            <th> Rate </th>
                                                                                                            <th> Amt </th>
                                                                                                            <th> Rate </th>
                                                                                                            <th> Amt </th>

                                                                                                        </tr>
                                                                                                </thead>
                                                                                                <c:forEach items="${salesForm.sales}" var="sale" varStatus="status">
                                                                                                <tbody id="siva">
                                                                                                        <tr>    
                                                                                                             <form:hidden path="sales[${status.index}].withoutTax" id="amountWithoutTax${status.index}" />
                                                                                                                <form:hidden path="sales[${status.index}].id"/>
                                                                                                                <input type="hidden"  id="hiddenUnit${status.index}" value="${sale.unit}"/>
                                                                                                                <form:hidden path="sales[${status.index}].tax" id="salesTax${status.index}" />
                                                                                                                <form:hidden path="sales[${status.index}].cpcpvAmount" id="salesAmountCpCpv${status.index}" />
                                                                                                                <input type="hidden"  id="SalesCp${status.index}" />
                                                                                                                <form:hidden path="sales[${status.index}].mcmcaAmount" id="salesAmountMcMca${status.index}" />
                                                                                                                <form:hidden path="sales[${status.index}].mcmca" id="salesMcMca${status.index}" />

                                                                                                                <td><form:input path="sales[${status.index}].itemCode" type="text" id="salesItemCode${status.index}" class="form-control ui-autocomplete-input" required="true" autocomplete="off" onKeypress="salesCategoryCheck('${status.index}');" onChange="salesCalculation(${fn:length(salesForm.sales)}); getUnit(${fn:length(salesForm.sales)});"/><form:errors path="sales[${status.index}].itemCode"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].itemName" type="text" id="salesItemName${status.index}"  class="form-control" onKeypress="salesCategoryCheck('${status.index}');" onChange="salesCalculation(${fn:length(salesForm.sales)}); getUnit(${fn:length(salesForm.sales)});"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].quantity" type="text" id="salesQuantity${status.index}"  class="form-control" onkeyup="onChangeQtyOrRate(${status.index}); salesCalculation(${fn:length(salesForm.sales)})"/><form:errors path="sales[${status.index}].quantity"/></td>
                                                                                                                <td><form:select path="sales[${status.index}].unit" id="salesUnit${status.index}" class="form-control" onChange="onChangeMargin(${status.index});" onKeyUp="salesCalculation(${fn:length(salesForm.sales)})">
                                                                                                                        
                                                                                                                    </form:select></td>
                                                                                                                <td><form:select path="sales[${status.index}].margin" id="salesMargin${status.index}" class="form-control" onChange="onChangeMargin(${status.index}); salesCalculation(${fn:length(salesForm.sales)})">
                                                                                                                        <form:option value="A" label="A"/>
                                                                                                                        <form:option value="B" label="B"/>
                                                                                                                        <form:option value="R" label="R"/>
                                                                                                                    </form:select></td>
                                                                                                                <td><form:input path="sales[${status.index}].rate" type="text" id="salesRate${status.index}"  class="form-control" readonly="true" onkeyup="onChangeQtyOrRate(${status.index}); salesCalculation(${fn:length(salesForm.sales)})"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].cpcpV" type="text" id="salesCpcpV${status.index}"  class="form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].discount" type="text"  value="" id="salesDiscount${status.index}"  class="form-control" required="true" onChange="onChangeDiscount(${status.index}); salesCalculation(${fn:length(salesForm.sales)})"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].amount" type="text" id="salesAmount${status.index}"  class="form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].taxCgst" type="text" id="salestaxCgst${status.index}"  class="inputs lst form-control"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].cgst" type="text" id="salesCgst${status.index}"  class="form-control"/></td>
                                                                                                                
                                                                                                                <td><form:input path="sales[${status.index}].taxSgst" type="text" id="salestaxSgst${status.index}"  class="inputs lst form-control"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].vat" type="text" id="salesVat${status.index}"  class="form-control"/></td>
                                                                                                                
                                                                                                                     <td><a href="#"   onclick="myFunction(this)">Remove</a></td>
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
										<label class="col-sm-12" for="form-field-1"> VAA</label>

										<div class="col-sm-12">
											<form:input type="text" path="sale.vaa" id="salesVaa" class="form-control"/>
                                                                                        <form:errors path="sale.vaa"/>
                                                                                </div>
									</div>
                                                                    <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Actual MCA</label>

										<div class="col-sm-12">
                                                                                    <form:input type="text" path="sale.actualMca" id="salesActualMCA" class="form-control" value="0"/>
                                                                                    <form:errors path="sale.actualMca"/>
                                                                                </div>
									</div>
                                                                        <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> MCA </label>

										<div class="col-sm-12">
                                                                                    <form:input type="text" path="sale.mca" id="salesMCA"  class="form-control" />
											<form:errors path="sale.mca"/>
										</div>
									</div>

									<div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Assess Value </label>

										<div class="col-sm-12">
											<form:input type="text" path="sale.assessValue" id="salesAssessValue" class="form-control"/>
											<form:errors path="sale.assessValue"/>
										</div>
									</div>
                                                                                <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1">CGST</label>

										<div class="col-sm-12">
											<form:input type="text" path="sale.totalCgst" id="Cgst" class="form-control"/>
                                                                                        <form:errors path="sale.totalCgst"/>
										</div>
                                                                        </div>
                                                                        <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> SGST</label>

										<div class="col-sm-12">
											<form:input type="text" path="sale.totalVat" id="salesVat" class="form-control"/>
                                                                                        <form:errors path="sale.totalVat"/>
										</div>
                                                                        </div>
                                                                        
								</div>
                                                                    <div class="row">
									
                                                                    
                                                                    
                                                                    <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Add/Less</label>

										<div class="col-sm-12">
                                                                                    <form:input type="text" path="sale.addLess" id="salesAddOrLess" class="form-control" value="0" onkeyup="onChangeAddOrLess()"/>
                                                                                    <form:errors path="sale.addLess"/>
										</div>
									</div>
                                                                        <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Invoice Amount </label>

										<div class="col-sm-12">
                                                                                    <form:input type="text" path="sale.invoiceAmount" id="salesInvoiceAmount" class="form-control"/>
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
                                                                            
                                                                            
                                                                        
                                                                            <p  id="total" ></p>
                                                                             <p   id="buId" ></p>  
                                                                             <p   id="ca" ></p>  
                                                                            
                                                                            
                                                                            <c:choose>
                                                                            <c:when test="${salesForm.sale.invoiceNo==null ||  empty salesForm.sale.invoiceNo}">
                                                                                  <input type="submit" id="btnSubmit" class="btn btn-info" value="Save"/>
                                                                            
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
						</div><!-- PAGE CONTENT ENDS -->
					</div><!-- /.col -->
				</div><!-- /.row -->
					</div><!-- /.page-content -->
                                         <jsp:include page="Footer.jsp" />
    </div>
      
		<!-- <![endif]-->

		<!--[if IE]>
<script src="assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
                
    <script>
   function myFunctionSub() {
         var bla = parseFloat($('#buyerBalance').val());
         var sia = parseFloat($('#salesInvoiceAmount').val());
         var total=bla+sia;
         document.getElementById("total").innerHTML = total;
        
      
        var ca= parseFloat($('#creditAmount').val());
//                        alert(ca); 
//                         document.getElementById("ca").innerHTML = ca;
          if (total<ca) {
//            alert("success"); 
            return true;
        } else {
            
           alert("Your credit amount limit is exceeded"); 
           return false;
        }
          }
 </script>
              
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

                 
  <script>
var i = $('table tr').length;

$(document).on('keydown', '.lst', function(e) {
  var code = (e.keyCode ? e.keyCode : e.which);
  if (code == 13) {
   e.preventDefault();    
   addRowforSales(); 
  }
});
</script>
 <script>
                    

$(':input:enabled:visible:not([readonly]):first').focus();
$(document).on('keydown',':tabbable', function (e) {
    if (e.which == 13) {
        e.preventDefault();
        // Get all focusable elements on the page
          var inputs =   $(this).parents("form").eq(0).find(":input:visible:not(disabled):not([readonly])"),
            idx = inputs.index(this);

        if (idx == inputs.length - 1) {
            inputs[0].select()
        } else {
            inputs[idx + 1].focus(); // handles submit buttons
            inputs[idx + 1].select();
        }
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
			});
		</script>
                <script>
//    $(document).on('ready', function() {
//  $('#Mytable').tableNav();
//  return $('#Mytable input').eq(1).click();
//});
</script>  
<script>
    getUnit(${fn:length(salesForm.sales)});
</script>
    </body>
    
</html>
