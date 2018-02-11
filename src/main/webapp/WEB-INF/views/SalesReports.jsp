<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>Accounting-Sales Grid</title>

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

	</head>

	<body class="no-skin">
            <c:set var="listLength" value="${fn:length(listGroup)}"/>
        <c:forEach items="${listGroup}" var="group">
            <c:choose>
                <c:when test="${group.moduleName=='SalesReports'}">
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
								
								<a href="Sales.html">Sales</a>
							</li>
                                                        <li>
								
								<a href="SalesGrid.html">Sales Records</a>
							</li>
                                                        <li class="active">Sales Register</li>
						</ul><!-- /.breadcrumb -->

					
                                            		
					</div>

					<div class="page-content">
						
						     <!-- /.row -->
                                                      
                                                           <div class="col-xs-12">
                                                               <form id="wizardForm" action="GetSalesReports.html"  method="POST">
                                                               <div class="widget-box lighter  widget-color-blue2">
                                                                    <div class="widget-header">
                                                                <h5 class="widget-title">Sales Register</h5>
                                                                    </div>
                                                                         <div class="widget-body">
                                                                           
                                                                <div class="widget-main alert-success">
                                                               <div class="row">			
                                                <div class="form-group col-sm-3">
										<label for="id-date-picker-1">From Date</label>

														
															
																<div class="input-group">
																	<input class="form-control date-picker" id="id-date-picker-1" type="text" name="fromdate" data-date-format="dd/mm/yyyy" required/>
																	<span class="input-group-addon">
																		<i class="fa fa-calendar bigger-110"></i>
																	</span>
																</div>
															
									</div>                                                                    
                                   			 			
                                                 <div class="form-group col-sm-3">
										<label for="id-date-picker-1">To Date</label>

														
															
																<div class="input-group">
																	<input class="form-control date-picker" id="id-date-picker-1" type="text" name="todate" data-date-format="dd/mm/yyyy" required/>
																	<span class="input-group-addon">
																		<i class="fa fa-calendar bigger-110"></i>
																	</span>
																</div>
															
									</div>     
                                                                   <div class="clearfix form-group col-sm-2">
										<input type="submit" class="width-60 pull-right btn btn-sm btn-primary bigger-60" value="Get Details"/>
									</div>
                                   								                                                             								 
											                                                             								 
							</div>
							
                                                                    </div>
                                                        </div>
                                                    </div>
										<div>
											<table id="dynamic-table" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														
														<th>Bill No</th>
                                                                                                                <th>Date</th> 
                                                                                                                <th>Buyer Name</th>
                                                                                                                <th>Type</th>
                                                                                                                <th>Mode</th>                                                                                                                
                                                                                                                <th>Category</th> 
														<th>Assessable Value</th>
                                                                                                                <th>CGST</th> 
                                                                                                                <th>SGST</th> 
                                                                                                                <th>ADD/LESS</th>
                                                                                                                <th>Total Amount</th>

														
													</tr>
												</thead>

												<tbody>
                                                                                                    <c:set var="totalassessValue" value="${0}"/>
                                                                                                     <c:set var="totaltotalCgst" value="${0}"/>
                                                                                                      <c:set var="totaltotalVat" value="${0}"/>
                                                                                                       <c:set var="totaladdLess" value="${0}"/>
                                                                                                       <c:set var="totalinvoiceAmount" value="${0}"/>
                                                                                                    <c:forEach items="${salesListReportsinfo}" var="salesInfo">
                                                                                                        <tr>
                                                                                                            
                                                                                                            <td>${salesInfo.invoiceNo}</td>
                                                                                                            <td>${salesInfo.date}</td>
                                                                                                            <td>${salesInfo.nameOfBuyer}</td>
                                                                                                            <td>${salesInfo.buyerType}</td>
                                                                                                            <td>${salesInfo.mode}</td>                                                                                                            
                                                                                                            <td>${salesInfo.category}</td>
                                                                                                            <td>${salesInfo.assessValue}</td>
                                                                                                            <td>${salesInfo.totalCgst}</td>
                                                                                                            <td>${salesInfo.totalVat}</td>
                                                                                                            <td>${salesInfo.addLess}</td>
                                                                                                            <td>${salesInfo.invoiceAmount}</td>
                                                                                                               <s:hidden ${totalassessValue=totalassessValue + salesInfo.assessValue}/>
                                                                                                                <s:hidden ${totaltotalCgst=totaltotalCgst + salesInfo.totalCgst}/>
                                                                                                                 <s:hidden${totaltotalVat=totaltotalVat + salesInfo.totalVat}/>
                                                                                                                 <s:hidden${totaladdLess=totaladdLess + salesInfo.addLess}/>
                                                                                                                 <s:hidden${totalinvoiceAmount=totalinvoiceAmount + salesInfo.invoiceAmount}/>
                                                                                                            
                                                                                                        </tr>
                                                                                                                                                                                                  
													</c:forEach>
												</tbody>
                                                                                                
                                                                                                <tr>
														
														<th></th>
                                                                                                                <th></th> 
                                                                                                                <th></th>
                                                                                                                <th></th>
                                                                                                                <th></th>
                                                                                                                <th class="center"><span style="color:Green">Total</span></th> 
														<th>${totalassessValue}</th>
                                                                                                                <th>${totaltotalCgst}</th> 
                                                                                                                <th>${totaltotalVat}</th> 
                                                                                                                <th>${totaladdLess}</th>
                                                                                                                <th>${totalinvoiceAmount}</th>

														
													</tr>
                                                                                                       
											</table>
                                                                                  
                                                                                </div>
                                                                                       </form>                          </div>
										
									</div>
                                                                    
                        </div>
                                    
				</div>
			</div>
                            

			 <%@ include file="Footer.jsp" %>

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
                <script type="text/javascript">
			jQuery(function($) {
				//initiate dataTables plugin
				var myTable = 
				$('#dynamic-table')
				//.wrap("<div class='dataTables_borderWrap' />")   //if you are applying horizontal scrolling (sScrollX)
				.DataTable( {
					
			    } );
			})
		</script>
                 <script type="text/javascript">
			jQuery(function($) {
				//initiate dataTables plugin
//				var myTable = $('#dynamic-table')
//				//.wrap("<div class='dataTables_borderWrap' />")   //if you are applying horizontal scrolling (sScrollX)
//				.DataTable( {
//					
//			    } );
                    
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
                       <script>
$(document).ready(function() {
   
    $('#selectall').click(function(event) { //on click
        if(this.checked) { // check select status
             
            $('.checkbox1').each(function() { //loop through each checkbox
                this.checked = true;//select all checkboxes with class "checkbox1"              
            });
        }else{
             
            $('.checkbox1').each(function() { //loop through each checkbox
                this.checked = false; //deselect all checkboxes with class "checkbox1"                      
            });        
        }
    });
   
});
</script>
	</body>
</html>
