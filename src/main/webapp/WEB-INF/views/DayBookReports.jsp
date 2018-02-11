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

        <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

        <!--[if lte IE 8]>
        <script src="assets/js/html5shiv.min.js"></script>
        <script src="assets/js/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="no-skin">
          <c:set var="listLength" value="${fn:length(listGroup)}"/>
        <c:forEach items="${listGroup}" var="group">
            <c:choose>
                <c:when test="${group.moduleName=='SaleReturnReportForm'}">
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
								<a href="#">DayBook Reports</a>
							</li>
							
						</ul><!-- /.breadcrumb -->

					</div>

					<div class="page-content">
						<!--- ACE Settings for template -->

						<div class="page-header">
							<h1>
								DayBook Reports
								<!-- <small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									Common form elements and layouts
								</small> -->
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
                                                                <form  target="_blank"action="DayBookPreview.html" method="GET"  >
								<div class="row">
                                                                    <div class="form-group col-xs-3">
                                                                    <label>Date Range</label>

                                                                       
                                                                                <div class="col-xs-8 col-sm-10">
                                                                                        <div class="input-daterange input-group">
                                                                                            <input type="text"  class="form-control date-picker" id="id-date-picker-1" name="startDate" value="${finalDate}" data-date-format="dd/mm/yyyy" value="${gsFromDate}"  required/>
                                                                                                <span class="input-group-addon">
                                                                                                        <i class="fa fa-exchange"></i>
                                                                                                </span>

                                                                                                <input type="text" class="form-control date-picker" id="id-date-picker-1" name="endDate" value="${curDate}" data-date-format="dd/mm/yyyy" value="${gsToDate}"  required/>
                                                                                        </div>
                                                                                </div>
                                                                      
                                                                    </div>
                                                                                        <div class="form-group col-sm-2">
                                                                                             <label>Type</label>
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

                                                                    </div>
                               						
									
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
  
