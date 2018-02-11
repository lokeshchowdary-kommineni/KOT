<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<!DOCTYPE html>
<html>
   
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trial Balance - Account Reports</title>
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
								<a href="#">Trial Balance</a>
							</li>
							
						</ul><!-- /.breadcrumb -->

					</div>

					<div class="page-content">
						<!--- ACE Settings for template -->

						<div class="page-header">
							<h1>
								Trial Balance
								<!-- <small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									Common form elements and layouts
								</small> -->
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<form  action="GetTrialBalance.html" method="POST">
								<div class="row">
                                                                    <div class="form-group col-xs-3">
                                                                    <label>Date Range</label>

                                                                        <div class="row">
                                                                                <div class="col-xs-8 col-sm-10">
                                                                                        <div class="input-daterange input-group">
                                                                                            <input type="text"  class="form-control date-picker" id="id-date-picker-1" name="startDate" data-date-format="dd/mm/yyyy" value="${gsFromDate}"  required/>
                                                                                                <span class="input-group-addon">
                                                                                                        <i class="fa fa-exchange"></i>
                                                                                                </span>

                                                                                                <input type="text" class="form-control date-picker" id="id-date-picker-1" name="endDate" data-date-format="dd/mm/yyyy" value="${gsToDate}"  required/>
                                                                                        </div>
                                                                                </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group col-xs-3 hideForLedger1">
                                                                        <label class="col-sm-8" for="form-field-1">&nbsp; </label>

                                                                                    <div class="col-sm-10" id="replaceLedger">
                                                                                       <input type="submit" class="btn btn-success" value="Get Results">
                                                                                    </div>
                                                                    </div>
                                                                    

                                                                    </div>
									
									</form>
								</div>
								</div><!-- PAGE CONTENT ENDS -->
                                                                
                                                                <div class="space-4"></div> <div class="space-4"></div>
                                                                
                                                        <div class="row">
                                                            <div class="col-xs-12">

                                                                    <!-- <div class="clearfix">
                                                                            <div class="pull-right tableTools-container"></div>
                                                                    </div> -->
                                                                    <div class="table-header">
                                                                            Results for "Trial Balance"
                                                                    </div>

                                                                    <!-- div.table-responsive -->

                                                                    <!-- div.dataTables_borderWrap -->
                                                                    <div>
                                                                    
                                                                   
                                                                            <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                                                                                    <thead>
                                                                                            <tr>
                                                                                                    <th rowspan="2">Group </th>  
                                                                                                    <th rowspan="2">Ledger </th>                                                                                                      
                                                                                                    <th  colspan="2">Closing Balance</th>
                                                                                            </tr>
                                                                                             <tr>
                                                                                                    <th >Debit</th>
                                                                                                    <th >Credit </th>  
                                                                                                    
                                                                                            </tr>
                                                                                           
                                                                                    </thead>

                                                                                    <tbody>
                                                                                   
                                                                                     
                                                                                <c:forEach items="${tb}" var="tb" varStatus="loop">                                                                                
                                                                                    <c:set var="totalDr" value="${totalDr+tb[7]}"/>
                                                                                    <c:set var="totalCr" value="${totalCr+tb[8]}"/>
                                                                                            <tr>
                                                                                                <td style="font-weight: bold">${tb[2]}</td>                                                                                                  
                                                                                                    <td>${tb[0]}</td>  
                                                                                                    <c:if test="${tb[7]>0}">                                                                                                      
                                                                                                         <td>${tb[7]}</td>                                                                           
                                                                                                         <td></td>
                                                                                                    </c:if>
                                                                                                    <c:if test="${tb[8]>0}">                                                                                                      
                                                                                                         <td></td>                                                                           
                                                                                                         <td>${tb[8]} </td>
                                                                                                    </c:if>   
                                                                                                    <c:if test="${tb[8]==0 && tb[7]==0 }">                                                                                                      
                                                                                                         <td></td>                                                                           
                                                                                                         <td></td>
                                                                                                    </c:if>        
                                                                                                 
                                                                                                    
                                                                                            </tr>
                                                                                  
                                                                                 </c:forEach>
                                                                                
                                                                                    </tbody>
                                                                                      <tr>
                                                                                                <td colspan="1"><b>Total </b></td>
                                                                                                <td colspan="1"><b> </b></td>
                                                                                                <td id="dr1"><b>${totalDr}</b></td>
                                                                                                <td id="cr1"><b>${totalCr}</b></td>
                                                                                              
                                                                                               
                                                                                             
                                                                                     </tr>
                                                                            </table>
                                                                  
                                                                    </div>
                                                            </div>
                                                                                            
                                                            
                                                    </div>
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
		
		<script src="assets/js/moment.min.js"></script>
		
		 <script src="assets/js/jquery.dataTables.min.js"></script>
		<script src="assets/js/jquery.dataTables.bootstrap.min.js"></script>
		
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
                
            
                
		<!-- inline scripts related to this page -->
		 <script type="text/javascript">
		
				var myTable = 
				$('#dynamic-table')
				//.wrap("<div class='dataTables_borderWrap' />")   //if you are applying horizontal scrolling (sScrollX)
				.DataTable( {
					 "columnDefs": [
                                            { "visible": false, "targets": 0 }
                                        ],
                                        "order": [[ 0, 'asc' ]],
                                        "displayLength": 25,
                                        "drawCallback": function ( settings ) {
                                            var api = this.api();
                                            var rows = api.rows( {page:'current'} ).nodes();
                                            var last=null;

                                            api.column(0, {page:'current'} ).data().each( function ( group, i ) {
                                                if ( last !== group ) {
                                                    $(rows).eq( i ).before(
                                                        '<tr class="group"><td colspan="4">'+group+'</td></tr>'
                                                    );

                                                    last = group;
                                                }
                                            } );
                                        }
			    } );
                    
                    $('.date-picker').datepicker({
					autoclose: true,
					todayHighlight: true
				})
				//show datepicker when clicking on the icon
				.next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
			
                        
                        
		</script>
    </body>
</html>
  
