<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>Stock Summary-Accounting</title>

		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/font-awesome/4.5.0/css/font-awesome.min.css" />

		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="assets/css/bootstrap-duallistbox.min.css" />
		<link rel="stylesheet" href="assets/css/bootstrap-multiselect.min.css" />
		<link rel="stylesheet" href="assets/css/select2.min.css" />
                
                <link rel="stylesheet" href="assets/css/bootstrap-datepicker3.min.css" />

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
								<a href="Dashboard.html">Home</a>
							</li>

							<li class="active">Stock Summary</li>
						</ul><!-- /.breadcrumb -->

					
                                            		
					</div>

					<div class="page-content">
						
						
<form class="form-horizontal" role="form" action="Get_Stock_Summary.html" modelAttribute="reportForm" method="POST">
						<div class="row">			
                                                <div class="form-group col-sm-5">
										<label for="id-date-picker-1">From Date</label>

														
															
																<div class="input-group">
																	<input class="form-control date-picker" id="id-date-picker-1" type="text" name="startDate" data-date-format="dd/mm/yyyy" value="${gsFromDate}"  required/>
																	<span class="input-group-addon">
																		<i class="fa fa-calendar bigger-110"></i>
																	</span>
																</div>
															
									</div>                                                                    
                                   			 			
                                                 <div class="form-group col-sm-5">
										<label for="id-date-picker-1">To Date</label>

														
															
																<div class="input-group">
																	<input class="form-control date-picker" id="id-date-picker-1" type="text" name="endDate" data-date-format="dd/mm/yyyy" value="${gsToDate}" />
																	<span class="input-group-addon">
																		<i class="fa fa-calendar bigger-110"></i>
																	</span>
																</div>
															
									</div>       
                                                        <div class="form-group col-sm-2">
                                                            <label for="id-date-picker-1"></label>
                                                            <div class="input-group">
                                                             <input type="submit" class="width-60 pull-right btn btn-sm btn-primary " value="Get Results"/>
                                                            </div>							   
							</div>                                                                                 
                                   								                                                             								 
											                                                             								 
							</div><!-- /.row -->
                                                        <div class="row">	
                                                          
                                   						
								<div class="col-xs-12">
										
										<div class="table-header">
											Stock Summary
										</div>

										<!-- div.table-responsive -->

										<!-- div.dataTables_borderWrap -->
                                                                                                                                                         
                                                                                    
										<div>
											<table id="dynamic-table" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														
                                                                                                                <th rowspan="2">Item Group Name</th>
                                                                                                                <th rowspan="2">Item Name </th>                                                                                                               
													
                                                                                                                 <th  colspan="3">Closing Balance</th>
                                                                                                                
                                                                                                                
													</tr>
                                                                                                          <tr>           
                                                                                                              <th> Quantity(Nos) </th>
                                                                                                              <th> Rate </th>
                                                                                                              <th> Value </th>
                                                                                                             
                                                                                                          </tr>
												</thead>

												<tbody>
                                                                                                                                                                                                        
                                                                                                      <c:forEach items="${stockSummary}" var="rep">
                                                                                                         
                                                                                                          <c:set var="totaloutQuantity" value="${totaloutQuantity + rep[0]}" />
                                                                                                          <c:set var="totaloutValue" value="${totaloutValue + rep[1]}" />
                                                                                                      
													<tr>
														<td>${rep[4]}</td>
                                                                                                                <td>${rep[3]}</td>
                                                                                                                <td>${rep[0]}</td>                                                                                                                
                                                                                                                <td>${rep[1]/rep[0]}</td>
														<td>${rep[1]}</td>
                                                                                                               
                                                                                                               
													</tr>
                                                                                                                                                                                        
                                                                                                        </c:forEach>
                                                                                                       
                                                                                                                 
												</tbody>
                                                                                                  <tr>
                                                                                                            <td colspan="1" style="text-align:right;">Total</td>
                                                                                                            
                                                                                                          
                                                                                                            <td>${totaloutQuantity}</td>
                                                                                                            <td></td>
                                                                                                            <td>${totaloutValue}</td>
                                                                                                           
                                                                                                             
                                                                                                        </tr>
											</table>
										</div>
                                                                                                                                                         
                                                                            
									</div>			                                                             								 
							</div>
                                                        
</form>
					</div>
				</div>
			</div><!-- /.main-content -->

			 <%@ include file="Footer.jsp" %>

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
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
                <script src="assets/js/jquery.dataTables.min.js"></script>
		<script src="assets/js/jquery.dataTables.bootstrap.min.js"></script>
		<script src="assets/js/jquery.bootstrap-duallistbox.min.js"></script>
		<script src="assets/js/jquery.raty.min.js"></script>
                <script src="assets/js/bootstrap-datepicker.min.js"></script>
		<script src="assets/js/bootstrap-multiselect.min.js"></script>
		<script src="assets/js/select2.min.js"></script>
		<script src="assets/js/jquery-typeahead.js"></script>

		<!-- ace scripts -->
		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>
                <script type="text/javascript">
			jQuery(function($) {
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
			});
                        
                        
		</script>
	</body>
</html>
