
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- ace settings handler -->
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
                
                <script src="assets/accounting-js/editkotfromSales.js"></script>
                

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
                 

<div class="widget-box lighter  widget-color-blue2">
    <div class="widget-body">
        <div class="widget-main alert-info">
            <div class="row">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            
                <form:form  class="eventInsForm" method="post" id="kotForm" action="saveSalesKOT.html" modelAttribute="kotForm" autocomplete="off" onsubmit="return validateForm();">
                    <div class="row">
                        <div class="form-group col-md-4">
                            <label  style="text-align:right">Selected Tables</label>
                            <form:input type="text" class="form-control" id="selectedTables" path="kot.tableName" required="true" autocomplete="off" onKeypress="autoCompleteForTable();"/>

                        </div>
                        <div class="form-group col-md-4">
                            <label class="control-label" style="text-align:right">KOT No</label>
                            <form:input type="text" class="form-control" id="kotNo" path="kot.kotNo" required="true"/>
                            <form:input type="hidden" class="form-control" id="kotid" path="kot.id"/>
                        </div>    

                        <div class="form-group col-md-4">    
                            <label class="control-label" style="text-align:right">Waiter Name</label>
                            <input type="text" class="form-control" id="waiterName" name="waiterName" required="true" autocomplete="off" onKeypress="autoCompleteForWaiter();"/>
                            <form:input type="hidden" class="form-control"  path="kot.waiterId" id="waiterId" />
                        </div>
                    </div>
                    <hr style="display: block;margin-top: 0.0em;margin-bottom: 0.5em;margin-left: auto;margin-right: auto;border-style: inset;border-width: 1px;">

                    <div class="widget-box lighter  widget-color-blue2">
                        <div class="widget-body">
                            <div class="widget-main alert-info">
                                <div class="row">
                                    <div class="col-xs-12 col-sm-12 widget-container-col" id="widget-container-col-2">
                                        <input type="button" class="btn btn-yellow m-b-sm" value="Add Row" onclick="addRowforKOTFromSales()"/>
                                        <div class="widget-box widget-color-blue" id="widget-box-2">
                                            <div class="widget-body">
                                                <div class="widget-main no-padding">
                                                    <table class="table table-striped table-bordered table-hover" id="example1">
                                                        <thead>
                                                        <tr>
                                                            <th style="text-align: center" rowspan="2">Item Code</th>
                                                            <th style="text-align: center" rowspan="2">Name</th>
                                                            <th style="text-align: center" rowspan="2">Unit</th>
                                                            <th style="text-align: center" rowspan="2">Qty</th>
                                                            <th style="text-align: center" rowspan="2">Rate</th>
                                                            <th style="text-align: center" colspan="2">CGST</th>
                                                            <th style="text-align: center" colspan="2">SGST</th>
                                                            <th style="text-align: center" rowspan="2">Total</th>
                                                            <th style="text-align: center" rowspan="2">Delete</th>
                                                        </tr>
                                                        <tr>
                                                            <th rowspan="2" style="width:50px; "> % </th>
                                                            <th rowspan="2" style="width:70px; "> Amt </th>
                                                            <th rowspan="2" style="width:50px; "> % </th>
                                                            <th rowspan="2" style="width:70px; "> Amt </th>
                                                        </tr>
                                                        </thead>
                                                        <c:forEach items="${kotForm.kotItem}" var="kotItems" varStatus="status">
                                                        <tbody>
                                                        <tr>
                                                            <input type="hidden"  id="hiddenUnit${status.index}" value="${kotItems.unit}"/>

                                                            <td>
                                                                <form:input type="hidden" id="hiddenItemId${status.index}" path="kotItem[${status.index}].id"/>
                                                                <form:input type="text" class="form-control" id="itemcode${status.index}" path="kotItem[${status.index}].itemCode" autocomplete="off" onKeypress="autoCompleteForKOTfromSales('${status.index}');"/>
                                                                
                                                            </td>
                                                            <td width="30%"><form:input type="text" class="form-control" id="itemName${status.index}" path="kotItem[${status.index}].itemName" readonly="true"/></td>
                                                            <td width="10%">
                                                                <form:select class="form-control" id="unit${status.index}" path="kotItem[${status.index}].unit" onchange="changeRate(${status.index});" onblur="getTotal(${status.index});kotCalculation(${fn:length(kotForm.kotItem)})"></form:select>
                                                            </td>
                                                            <td><form:input type="text" class="form-control" id="quantity${status.index}" path="kotItem[${status.index}].quantity" onkeypress="return isNumberKey(event);" onkeyup="getTotal(${status.index});kotCalculation(${fn:length(kotForm.kotItem)});"/></td>
                                                            <td>
                                                                <form:input type="text" class="form-control" id="rate${status.index}" path="kotItem[${status.index}].rate" readonly="true"/>
                                                                <form:input type="hidden" class="form-control" id="auditRate${status.index}" path="kotItem[${status.index}].auditRate" readonly="true"/>
                                                            </td>
                                                            <td><form:input type="text" class="form-control" id="taxCgstPercent${status.index}" path="kotItem[${status.index}].cgstPercent" readonly="true"/></td>
                                                            <td><form:input type="text" class="form-control" id="taxCgst${status.index}" path="kotItem[${status.index}].taxCgst" readonly="true"/></td>
                                                            <td><form:input type="text" class="form-control" id="taxSgstPercent${status.index}" path="kotItem[${status.index}].sgstPercent" readonly="true"/></td>
                                                            <td><form:input type="text" class="form-control" id="taxSgst${status.index}" path="kotItem[${status.index}].taxSgst" readonly="true"/></td>
                                                            <td width="10%">
                                                                <form:input type="text" class="form-control" id="cap${status.index}" path="kotItem[${status.index}].cap" readonly="true"/>
                                                                <form:input type="hidden" class="form-control" id="vap${status.index}" path="kotItem[${status.index}].vap" readonly="true"/>
                                                            </td>
                                                            <td style="text-align:center;"><a href="#"  class="ace-icon fa fa-trash-o bigger-120 btn-danger" onclick="myFunction(this,${fn:length(kotForm.kotItem)},${status.index})"></a></td>
                                                        </tr>
                                                        </tbody>
                                                        </c:forEach>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div><br>
                                <div class="row">
                                    <div class="form-group col-md-4">
                                        <label  style="text-align:right">Total CGST</label>
                                        <form:input type="text" class="form-control" id="totalCGST" path="kot.totalCgst" readonly="true" required="true"/>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label  style="text-align:right">Total CGST</label>
                                        <form:input type="text" class="form-control" id="totalSGST" path="kot.totalSgst" readonly="true" required="true"/>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label  style="text-align:right">Total KOT Value</label>
                                        <form:input type="text" class="form-control" id="totalKOTValue" path="kot.totalKotvalue" readonly="true" required="true"/>
                                    </div>
                                </div>
                               
                            </div>
                        </div>
                    </div>
                                     <div class="row">            
                                    <div class="form-group  col-sm-1">
                                        <input  type="submit" id="btnSubmit" class="btn btn-info" value="Save" style="float: left;"/>
                                    </div>
                                    
                </form:form>
                                         <form:form method="post"  action="SalesInvoicelist.html" >
                                         <div class="form-group  col-sm-1">
                                        <input  type="submit" class="btn btn-info" value="Close"/>
                                    </div> 
                                </div>
                                         </form:form>
            </div>        
        </div>        
    </div>   
                <script>
                    getUnit(${fn:length(kotForm.kotItem)});
                    getWaiterNameByIdfromsalesinvoicelist($('#waiterId').val());
                </script>

      