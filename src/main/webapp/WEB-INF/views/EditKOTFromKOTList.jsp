<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>KOT - KOT Form</title>
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
        
         
                
        <script src="assets/accounting-js/kot.js"></script>
        
	<body class="no-skin">
            <div class="main-container ace-save-state" id="main-container">
                
     <c:set var="listLength" value="${fn:length(listGroup)}"/>
        <c:forEach items="${listGroup}" var="group">
            <c:choose>
                <c:when test="${group.moduleName=='KotForm'}">
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
                    <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                        <ul class="breadcrumb">
                                <li>
                                        <i class="ace-icon fa fa-home home-icon"></i>
                                        <a href="#">Home</a>
                                </li>
                                <li>
                                        <a href="KOTList.html">KOT Records</a>
                                </li>
                                <li class="active">KOT Form </li>
                        </ul>
                    </div>
                    <div class="main-content-inner">
                        <div class="page-content">
                            
                                
                            <%--    <c:if  test="${not empty kotForm.id}"	>
                                                                  
                                    <div class="form-group">
                                        <div class="col-sm-9">
                                            <form:input type="hidden" path="id" id="form-field-1" class="form-control"  readonly="true"/>
                                        </div>
                                    </div>
                                               
                                </c:if> --%>
                                
                                <div class="widget-box lighter  widget-color-blue2">
                                    <div class="widget-header">
                                        <h5 class="widget-title">Kitchen Order Ticket</h5>
                                    </div>
                                    <div class="widget-body">
                                        <div class="widget-main alert-success">
                                            <div class="row" >
                                                <div class="col-md-3 col-sm-12 col-xs-12" style="border-right: 1px #cccccc solid">
        
                                                    <label class="control-label">Table Category</label><br>
                                                    <c:forEach items="${tableCategory}" var="tblCat" varStatus="catCount">
                                                        <input type="button"  class="btn btn-primary" id="category" onclick="emptyKOTForm();getTables(this.value);"  value="${tblCat}"/>
                                                    </c:forEach>
                                                    
                                                    <hr style="display: block;margin-top: 0.5em;margin-bottom: 0.5em;margin-left: auto;margin-right: auto;border-style: inset;border-width: 1px;">

                                                    <label class="control-label">Tables</label><br>
                                                       
                                                    <div id="tableNames" style="height: 150px;overflow:auto">    
                                                             
                                                    </div>
                                                        
                                                    <hr style="display: block;margin-top: 0.5em;margin-bottom: 0.5em;margin-left: auto;margin-right: auto;border-style: inset;border-width: 1px;">
                                                    <div id="kotListDiv" style="height: 150px;overflow:auto">
                                                        
                                                    </div>    
                                                </div>

                                                <div class="col-md-9 col-sm-12 col-xs-12 no-padding-sm col-border-right">
                                                    <div class="col-xs-12" id="kotFormDiv">
                                                        <form:form method="post" id="kotForm" action="saveKOTFromKOTList.html" modelAttribute="kotForm" autocomplete="off" onsubmit="return validateForm();">
                                                            <div class="row">
                                                                <div class="form-group col-md-4">
                                                                    <label  style="text-align:right">Selected Tables</label>
                                                                    <form:input type="text" class="form-control" id="selectedTables" path="kot.tableName" required="true" autocomplete="off" onKeypress="autoCompleteForTable();"/>

                                                                </div>
                                                                
                                                                <div class="form-group col-md-4">
                                                                    <label class="control-label" style="text-align:right">KOT No</label>
                                                                    <form:input type="text" class="form-control" id="kotNo" path="kot.kotNo" readonly="true" required="true"/>
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
                                                                                <input type="button" class="btn btn-yellow m-b-sm" value="Add Row" onclick="addRowforKOTFromKOTList();"/>
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
                                                                                                        <form:input type="text" class="form-control" id="itemcode${status.index}" path="kotItem[${status.index}].itemCode" autocomplete="off" onKeypress="checkTableSelection('${status.index}');"/>
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
                                                                                                    <td style="text-align:center;"><a href="#"  class="ace-icon fa fa-trash-o bigger-120 btn-danger" onclick="myFunctionDelete(this,${fn:length(kotForm.kotItem)},${status.index})"></a></td>
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
                                                                        <div class="row">            
                                                                            <div class="form-group  col-sm-2">
                                                                                <input  type="submit" id="btnSubmit" class="btn btn-info" value="Save (F2)" style="float: left;"/>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>                                        
                                                        </form:form>
                                                        </div>    
                                                </div>
                                            </div>        
                                        </div>            
                                    </div>
                                </div>
                            </div>    
                            <br><br>
                        </div>
                        </div>    
                    </div>
                    <%@ include file="Footer.jsp" %>        
                </div>    
                
            

		<!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
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
                <script src="assets/accounting-js/enter2tab.js"></script>
                <script>
                    getUnit(${fn:length(kotForm.kotItem)});
                    getWaiterNameById($('#waiterId').val());
                    document.onkeyup = KeyCheck;
 
                    function KeyCheck(e)
                    {
                       var KeyID = (window.event) ? event.keyCode : e.keyCode;
                       if(KeyID == 113)
                       {
                            
                        if(validateForm()){
                            document.getElementById("kotForm").submit();
                            document.getElementById("kotForm").method='post';
                        }
                       }
                    }
                </script>     
                <script>
                    var i = $('table tr').length;
                    $(document).on('keydown', '.lst', function(e) {
                      var code = (e.keyCode ? e.keyCode : e.which);
                      if (code == 13) {
                       e.preventDefault();   
                       addRowforKOTFromKOTList();
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
                                inputs[0].select();
                            } else {
                                inputs[idx + 1].focus(); // handles submit buttons
                                inputs[idx + 1].select();
                            }
                        }
                       $('#btnSubmit').keydown(function(event){
                              //Check if the key pressed is ENTER key using the keyCode=13
                              if(event.keyCode === 13){
                               alert('Successfully!'); 
                              }
                              event.cancelBubble = true;
                                 if (event.stopPropagation) event.stopPropagation();
                             });

                    });

            </script>
    
    </body>
</html>
