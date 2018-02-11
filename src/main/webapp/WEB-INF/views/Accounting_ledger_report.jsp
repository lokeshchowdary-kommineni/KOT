<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<!DOCTYPE html>
<html>
   
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accounting - Account Reports</title>
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

        <link rel="stylesheet" href="assets/css/ace-skins.min.css" />
        <link rel="stylesheet" href="assets/css/ace-rtl.min.css" />

        <script src="assets/js/ace-extra.min.js"></script>

    </head>
    <body class="no-skin">
        <c:set var="listLength" value="${fn:length(listGroup)}"/>
        <c:forEach items="${listGroup}" var="group">
            <c:choose>
                <c:when test="${group.moduleName=='Accounting_ledger_report'}">
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
								<a href="#">Account Reports</a>
							</li>
							
						</ul><!-- /.breadcrumb -->

					</div>

					<div class="page-content">
						<!--- ACE Settings for template -->

						<div class="page-header">
							<h1>
								Account Reports
								<!-- <small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									Common form elements and layouts
								</small> -->
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
                                                                <form  target="_blank"action="LedgerPreview.html" method="GET" onsubmit="if($('input[name=type]:checked').length<=0){ alert('Select Atleast one Checkbox'); return false;}" >
								<div class="row">
                                                                    <div class="form-group col-xs-3">
                                                                    <label>Date Range</label>

                                                                       
                                                                                <div class="col-xs-8 col-sm-10">
                                                                                        <div class="input-daterange input-group">
                                                                                            <input type="text"  class="form-control date-picker" id="id-date-picker-1" name="startDate" data-date-format="dd/mm/yyyy" value="${finalDate}"  required/>
                                                                                                <span class="input-group-addon">
                                                                                                        <i class="fa fa-exchange"></i>
                                                                                                </span>

                                                                                                <input type="text" class="form-control date-picker" id="id-date-picker-1" name="endDate" data-date-format="dd/mm/yyyy" value="${curDate}"  required/>
                                                                                        </div>
                                                                                </div>
                                                                      
                                                                    </div>
                                                                    <div class="form-group col-xs-3 ">
                                                                                    <label class="col-sm-8" for="form-field-1"> List of Ledgers Accounts </label>

                                                                                    <div class="col-sm-10" id="replaceLedger">
                                                                                        <select class="form-control changeGetLedger mySelects" id="changeGetLedger" name="byLedger" required>
                                                                                            <option value="">---choose---</option>
                                                                                            <c:forEach items="${listAllLedger}" var="list">
                                                                                                <option value="${list.idLedger}" ${list.idLedger==byLedger_id ? 'Selected':''}>${list.nameOfLedger}</option>
                                                                                            </c:forEach>
                                                                                        </select>
                                                                                    </div>
                                                                            </div>
                                                                        <div class="form-group col-xs-3  ">
                                                                                    <label class="col-sm-8" for="form-field-1"> List of Account Groups</label>

                                                                                    <div class="col-sm-10">
                                                                                        <select class="form-control mySelects" id="changeGetAcGroups" name="byAccountGroups">
                                                                                            <option value="">---choose---</option>
                                                                                            <option value="0">Primary</option>
                                                                                            
                                                                                            <c:forEach items="${accountGroupList}" var="list">
                                                                                                <option value="${list.idAccount}" ${list.idAccount==byAccountGroups ? 'Selected':''}>${list.accountGroupName}</option>
                                                                                            </c:forEach>
                                                                                        </select>
                                                                                    </div>
                                                                            </div>
<!--                                                                        <div class="form-group col-xs-3 " >
                                                                                    <label class="col-sm-8" for="form-field-1"> List of Account  </label>

                                                                                    <div class="col-sm-10">
                                                                                        <select name="id" class="form-control getAccountGroups" id="getAccountGroups">
                                                                                            <option value="-1">---choose---</option>
                                                                                        </select>
                                                                                    </div>
                                                                        </div>-->

                                                                    </div>
                                                                        <div class="row">			
                                                <div class="form-group col-sm-2 ">
                                                    <input type="radio" class="ace ace-checkbox-2 ace-save-state " name="type"  value="0"  />
                                                                                
										<label class="lbl" for="ace-settings-navbar"> Voucher  </label>
                                                                                
															
						</div>                                                                    
                                   			 			
                                                 <div class="form-group col-sm-2">
                                                     <input type="radio" class="ace ace-checkbox-2 ace-save-state" name="type"  value="1"  />
                                                                                
										<label class="lbl" for="ace-settings-navbar">Date</label>
                                                                                
						</div>  
                                                       <div class="form-group col-sm-2">
                                                     <input type="radio" class="ace ace-checkbox-2 ace-save-state" name="type"  value="2"  />
                                                                                
										<label class="lbl" for="ace-settings-navbar">Month</label>
                                                                                
						</div>  
                                                     <div class="form-group col-sm-2">
                                                     <div class="col-sm-12">
                                            <select   name="format" class="form-control" id="" >
                                                <option value="HTML">HTML</option> 
                                                <option value="PDF">PDF</option>                                                 
                                                <option value="EXCEL">EXCEL</option>                                                       
                                                 </select> 
					            </div>
						</div> 
                                                   <input type="hidden" name="groupName" id="groupName">
                                                       <div class="form-group col-sm-4">
                                                       
										<input type="submit" class=" btn btn-sm btn-primary " value="Get Results"/>
								
						</div>				                                                             								 
											                                                             								 
							</div><!-- /.row -->     
						
									
									</form>
								</div>
								</div><!-- PAGE CONTENT ENDS -->
                                                                
                                                   
                                                    </div>
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
                                         <jsp:include page="Footer.jsp" />
    </div>
       
        <script src="assets/js/jquery-2.1.4.min.js"></script>

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
                
                  <!-- Voucher based java scripts !-->
                <script src="assets/accounting-js/voucher.js"></script>
                
                <script>
                    
                        $('#changeGetAcGroups').change(function() {
                      
                        $.ajax({
                            type:"GET",
                            url : "SelectLedgerDropDownBasedOnGroup.html",
                            data : "parentId_group="+$(this).val(),
                            async: false,
                            success : function(response) {
                               $('#replaceLedger').html(response)
                               // return response;
                            },
                            error: function() {
                                alert('Error occured');
                            }
                        });
                      
                    });
                    
                </script>
                
		<!-- inline scripts related to this page -->
		<script type="text/javascript">

				$('.date-picker').datepicker({
					autoclose: true,
					todayHighlight: true
				})
				//show datepicker when clicking on the icon
				

		</script>
    </body>
</html>
  
