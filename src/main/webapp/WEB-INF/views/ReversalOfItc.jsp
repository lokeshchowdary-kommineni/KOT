<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>Accounting-Reversal of ITC</title>

		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/font-awesome/4.5.0/css/font-awesome.min.css" />

		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="assets/css/bootstrap-duallistbox.min.css" />
		<link rel="stylesheet" href="assets/css/bootstrap-multiselect.min.css" />
		<link rel="stylesheet" href="assets/css/select2.min.css" />
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
                <c:when test="${group.moduleName=='ReversalOfItc'}">
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
								<a href="ReversalOfItcGrid.html">Reversal Of Itc Reports</a>
							</li>
                                                        
							<li class="active">Reversal of ITC </li>
						</ul><!-- /.breadcrumb -->

					
                                            		
					</div>

					<div class="page-content">
						
						<div class="page-header">
							<h1>Reversal of ITC </h1>
                                                        
							
						</div><!-- /.page-header -->
                                         
                                                            <!-- /.row -->
                                                                       						<div class="col-xs-12" id="addrowRDiv">
								<!-- PAGE CONTENT BEGINS -->
                                                                <form:form class="form-horizontal" action="SaveReversalOfItc.html"  commandName="reversalFrom" method="POST" id="rForm">
								<div class="widget-box lighter  widget-color-blue2">
                                                                    <div class="widget-header">
                                                                <h5 class="widget-title">Reversal of ITC</h5>
                                                                    </div>
                                                                         <div class="widget-body">
                                                                <div class="widget-main alert-success">
                                                                    <div class="row">
									
                                                                    
                                                                    <div class="form-group col-xs-3">
										<label class="col-sm-12" for="form-field-1"> Purchase Date</label>

										<div class="col-sm-12">
                                                                                    <form:input path="reversalofitc.date" class="form-control "  placeholder="date" type="text" id="Box1"  readonly="true" data-date-format="dd/mm/yyyy" required='true'/>
                                                                                    <form:errors path="reversalofitc.date" />
                                        
											
										</div>
									</div>
                                                                        
                                                                        <div class="form-group col-xs-3">
										<label class="col-sm-8" for="form-field-1">Bill No </label>

										<div class="col-sm-12">
										<form:input type="text" path="reversalofitc.billNo" id="form-field-1" placeholder="invoice no" class="form-control" readonly="true"/>
                                                                                        <form:errors path="reversalofitc.billNo"/>	
                                                                                </div>
									</div>
                                                                                    
                                                                        <div class="form-group col-xs-3">
										<label class="col-sm-8" for="form-field-1">Return Date<span style="color:red;">*</span></label>

										<div class="col-sm-12">
                                                                                    <form:input path="reversalofitc.returnDate" class="form-control date-picker" id="id-date-picker-1" placeholder="date" type="text" data-date-format="dd/mm/yyyy" onchange="setCustomValidity('')"  oninvalid="setCustomValidity('Please Select Date')" required="true"/>
                                                                                    <form:errors path="reversalofitc.returnDate" />
                                        
											
										</div>
									</div>
									
									
									
									<div class="form-group col-xs-3">
										<label class="col-sm-12"> Category For Reversal <span style="color:red;">*</span></label>

										<div class="col-sm-12">
                                                                                     <form:select path="reversalofitc.categoryForReversal" class="form-control" required="true" placeholder="categoryForReversal" oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Please Select Category')" id="form-field-select-1">
                                                                                     <form:option value="" label="--- Select ---"/>
                                                                                     <c:forEach items="${itcList}" var="itclist">
                                                                                         <form:option value="${itclist.idItc}" label="${itclist.categoryName}"/>
                                                                                      </c:forEach> 
											</form:select>
                                                                                        <form:errors path="reversalofitc.categoryForReversal"/>
										</div>
									</div>
									
									
									
								</div>
                                                              
								<div class="row">
                                                                        <div class="form-group col-xs-3">
										<label class="col-sm-6" for="form-field-1"> Mode</label>

										<div class="col-sm-12">
                                                                                    
                                                                                    <form:input type="text" path="reversalofitc.mode" id="form-field-1" placeholder="mode" class="form-control" readonly="true"/>
                                                                                        <form:errors path="reversalofitc.mode"/>										
										</div>
									</div>
                                                                    	<div class="form-group col-xs-3">
										<label class="col-sm-6" for="form-field-1"> Cash Supplier </label>

										<div class="col-sm-12">
                                                                                <form:input type="text" path="reversalofitc.cashSupplier" id="form-field-1" placeholder="Cash Supplier" class="form-control" readonly="true"/>
                                                                                        <form:errors path="reversalofitc.cashSupplier"/>
											
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
                                                    <span class="red" style="padding-left: 443px;color: red;font-size: 15px;">${Message}</span>
                                                    
                            <div class="col-xs-12 col-sm-12 widget-container-col" id="widget-container-col-2">
								<div class="widget-box widget-color-blue" id="widget-box-2">
                                    <div class="widget-body">
                                        <div class="widget-main no-padding">
                                            <table class="table table-striped table-bordered table-hover" id="tableReversal">
                                                <thead class="thin-border-bottom">
                                                    <tr>
                                                        <th rowspan="2" style="width: 10%;">                                                                                                                       
                                                           Item Code
                                                        </th>
                                                        <th rowspan="2" style="width: 30%;">
                                                            Name Of the Item
                                                        </th>
                                                        
                                                         <th rowspan="2" style="width: 6%;">
                                                            Return.Qty<span style="color:red;">*</span>
                                                        </th>
                                                        <th rowspan="2" style="width: 6%;">
                                                            Qty
                                                        </th>
                                                       
                                                        <th rowspan="2" style="width: 6%;">
							Unit
                                                        </th >                                                       
                                                        <th rowspan="2" style="width: 8%;">														
                                                            EP                                                            
                                                        </th>                                                        
							<th rowspan="2" style="width: 8%;">														
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
                                                      <th  style="width:50px; "> Rate </th>
                                                      <th  style="width:70px; "> Amt </th>
                                                      <th  style="width:50px; "> Rate </th>
                                                      <th  style="width:70px; "> Amt </th>
                                                      <th  style="width:50px; "> Rate </th>
                                                      <th  style="width:70px; "> Amt </th>
                                                      
                                                  </tr>
                                                </thead>                                                                 
                                                                                                                                                                                                                                                                      
                                                <c:forEach items="${reversalFrom.reversalofitcs}" var="reversalofitcs" varStatus="status">
                                                    <c:set var = "reversal" value = "${status.index}"/> 
                                                                                                <tbody>
                                                                                                        <tr class="txtMult">
                                                                                                                 <form:hidden path="reversalofitcs[${status.index}].id"/>
                                                                                                                 <form:hidden path="" id="purchasesTax${status.index}" />
                                                                                                                <input type="hidden"  id="hiddenUnit${status.index}" value="${reversalofitcs.unit}"/>
                                                                                                                <form:hidden path="reversalofitcs[${status.index}].tax"/>
                                                                                                                <td><form:input path="reversalofitcs[${status.index}].itemCode"  id="reversalofitcsItemCode${status.index}"  type="text"  class="form-control ui-autocomplete-input" autocomplete="off" onKeypress="autoCompleteForPurchase('${status.index}'); reversalCalculation(${fn:length(reversalFrom.reversalofitcs)});getUnit(${fn:length(reversalFrom.reversalofitcs)}) " readonly="true"/>
                                                                                                                <td><form:input path="reversalofitcs[${status.index}].nameOfTheItem"  id="reversalofitcsNameOfTheItem${status.index}"  type="text"  class="form-control" readonly="true"/></td>
                                                                                                              
                                                                                                                <c:if test="${CurrentQty!='0.0'}">
                                                                                                                <td><form:input path="reversalofitcs[${status.index}].returnQty" style="text-align:center;"  id="reversalofitcsreturnQty${status.index}"  type="text"  class="form-control" onClick="SelectName(${status.index});" required="true" onkeypress="return isNumberKey(event)"  onChange="onChangeRQty('${status.index}');  reversalCalculation(${fn:length(reversalFrom.reversalofitcs)}); "  readonly="${(reversalofitcs.qty eq 0.0) ? 'true': 'false'}" /><form:errors path="reversalofitcs[${status.index}].returnQty" /></td>
                                                                                                                </c:if>
                                                                                                                <td><form:input path="reversalofitcs[${status.index}].qty"  id="reversalofitcsQty${status.index}" style="text-align:center;" type="text"  class="form-control" readonly="true" /></td>
                                                                                                                
                                                                                                                <td><form:select path="reversalofitcs[${status.index}].unit" style="text-align:center;" id="reversalofitcsUnit${status.index}" class="inputs form-control" readonly="true">
                                                                                                                        
                                                                                                                </form:select></td>
                                                                                                                
                                                                                                                <td><form:input path="reversalofitcs[${status.index}].ep"  id="reversalofitcsEp${status.index}"  type="text"  class="form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="reversalofitcs[${status.index}].amount"  id="reversalofitcsAmount${status.index}"  type="text"  class="form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="reversalofitcs[${status.index}].taxIgst" style="text-align:center;" id="reversalofitcstaxIgst${status.index}"  type="text"  class="form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="reversalofitcs[${status.index}].igst"  id="reversalofitcsIgst${status.index}"  type="text"  class="form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="reversalofitcs[${status.index}].taxCgst" style="text-align:center;" id="reversalofitcstaxCgst${status.index}"  type="text"  class="form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="reversalofitcs[${status.index}].cgst"  id="reversalofitcsCgst${status.index}"  type="text"  class="form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="reversalofitcs[${status.index}].taxSgst" style="text-align:center;" id="reversalofitcstaxSgst${status.index}"  type="text"  class="form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="reversalofitcs[${status.index}].vat"  id="reversalofitcsVat${status.index}"  type="text"  class="form-control" readonly="true"/></td>
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
									 <form:input type="text" path="reversalofitc.totalAmount" id="reversalofitcsAmount" placeholder="invoice no" class="form-control" readonly="true"/>
                                                                                        <form:errors path="reversalofitc.totalAmount"/>
                                                                </div>
						</div>
                                                                 <div class="form-group col-xs-2">
							<label class="col-sm-12" for="form-field-1"> IGST </label>
								<div class="col-sm-12">
                                                                     <form:input type="text" path="reversalofitc.totalIgst" id="RIgst"  class="form-control" readonly="true"/>
                                                                                        <form:errors path="reversalofitc.totalIgst"/>
                                   	                        </div>
						</div>
                        
                                                <div class="form-group col-xs-2">
							<label class="col-sm-12" for="form-field-1"> CGST </label>
								<div class="col-sm-12">
                                                                     <form:input type="text" path="reversalofitc.totalCgst" id="RCgst" placeholder="invoice no" class="form-control" readonly="true"/>
                                                                                        <form:errors path="reversalofitc.totalCgst"/>
                                   	                        </div>
						</div>

						<div class="form-group col-xs-2">
							<label class="col-sm-6" for="form-field-1"> SGST </label>
								<div class="col-sm-12">
								 <form:input type="text" path="reversalofitc.totalVat" id="RVat" placeholder="invoice no" class="form-control" readonly="true"/>
                                                                                        <form:errors path="reversalofitc.totalVat"/>	
                                                                </div>
						</div>
						
						<div class="form-group col-xs-2">
							<label class="col-sm-6" for="form-field-1"> Add/Less</label>
							<div class="col-sm-12">
							 <form:input type="text" path="reversalofitc.addOrLess" id="RAddLess" placeholder="invoice no" class="form-control" readonly="true"/>
                                                                                        <form:errors path="reversalofitc.addOrLess"/>
							</div>
						</div>
                        <div class="form-group col-xs-2">
							<label class="col-sm-12" for="form-field-1"> Debit Amount</label>
								<div class="col-sm-12">
                                                                                        <form:input type="text" path="reversalofitc.debtAmount" id="RTInvoiceAmount" placeholder="invoice no" class="form-control" readonly="true"/>
                                                                                        <form:errors path="reversalofitc.debtAmount"/>
								</div>
						</div>
						<div class="form-group col-xs-2">
<!--							<label class="col-sm-12" for="form-field-1"> Assess Value</label>-->
								<div class="col-sm-12">
                                        <form:input type="hidden" path="reversalofitc.assessValue" id="RAssessValue" placeholder="RAssessValue" class="form-control" readonly="true"/>
                                                                                        <form:errors path="reversalofitc.assessValue"/>
								</div>
						</div>
						
					</div> 
			    </div>
                                </div>
                            </div>
									<div class="space-4"></div>
							 <c:if test="${roId=='0' || empty roId}">		 
									<div style="text-align:center">
										<button class="btn btn-success">Save</button>
                                                                                
                                                                                 &nbsp; &nbsp; &nbsp;
                                                                        <button class="btn" type="reset" onClick="window.location.reload()">  Reset </button>
									</div>
                                                         </c:if>	
									</form:form>
                    
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
                            

			 <%@ include file="Footer.jsp" %>

		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->


		<!-- <![endif]-->

		<!--[if IE]>
<script src="assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
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
                    <script src="assets/accounting-js/reversalofitc.js"></script>
                    <script>
                      $('#rForm').submit( function() {
  
    var i=0;
    var check=false;
    $('input[id^="reversalofitcsreturnQty"]').each(function(){
       var reqty=parseFloat($('#reversalofitcsreturnQty'+i).val());
       var qty=parseFloat($('#reversalofitcsQty'+i).val());
       
        // Compare _a to _b
        if(reqty > qty) {
            alert("Returned quantity should not exceed the purchased quantity"); 
            $('#reversalofitcsreturnQty'+i).focus(); 
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
                /// To Type Only Number in Textbox
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
                    </script>
                <script type="text/javascript">
			jQuery(function($) {
				//initiate dataTables plugin
				var myTable = 
				$('#dynamic-table')
				//.wrap("<div class='dataTables_borderWrap' />")   //if you are applying horizontal scrolling (sScrollX)
				.DataTable( {
					
			    } );
			})
                         $('#reversalofitcsItemCode${reversal}').focus();
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

				$('.date-picker').datepicker({
					autoclose: true,
					todayHighlight: true
				})
				//show datepicker when clicking on the icon
				.next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
			
				//or change it into a date range picker
				$('.input-daterange').datepicker({autoclose:true});
			
			
				//to translate the daterange picker, please copy the "examples/daterange-fr.js" contents here before initialization
				$('input[id=id-date-range-picker-1]').daterangepicker({
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
                  $("#Box1").focus();
                  
                  </script>
<script>
    getUnit(${fn:length(reversalFrom.reversalofitcs)});
    reversalCalculation(${fn:length(reversalFrom.reversalofitcs)});
   
</script>
<c:if test="${viewId==null || empty viewId}">
    <script type="text/javascript">
    $(document).ready(function() {
            $("#reversalofitcsAmount").val("0");
            $("#RCgst").val("0");
            $("#RVat").val("0");
            $("#RIgst").val("0");
            $("#RAddLess").val("0");
            $("#RTInvoiceAmount").val("0");
            });
</script>

</c:if>
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
            var tliter=totalPrice;
            $(".someTotalPrice").html(totalPrice);
        });
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
               document.getElementById("reversalofitcsreturnQty"+index).value=totalLiter;
               onChangeRQty(index);
               reversalCalculation(${fn:length(reversalFrom.reversalofitcs)}); 
               
        }
</script>
	</body>
</html>
