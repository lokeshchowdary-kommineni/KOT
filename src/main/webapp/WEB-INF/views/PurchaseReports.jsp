<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>Accounting-PurchaseReports</title>

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
                <c:when test="${group.moduleName=='PurchaseReports'}">
                    <p style="display: none"> ${listLength=listLength+1} </p>
                  
                </c:when>
                <c:otherwise>
                    <p style="display: none">  ${listLength=listLength-1}</p>
                    <c:if test="${listLength==0}">
                        <c:redirect url="sdfsd.html"/>
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
								<a href="Purchase.html">Purchase</a>
							</li>
                                                        <li>
								<a href="PurchaseGrid.html">Purchase Record</a>
							</li>
							<li class="active">Purchase Reports</li>
						</ul><!-- /.breadcrumb -->

					
                                            		
					</div>

					<div class="page-content">
						
                                         
                                                            <!-- /.row -->
                                                       <div class="col-xs-12">
                                                           <form class="form-horizontal" action="GetPurchaseReports.html"  commandName="purchaseFrom" method="POST" >             
                                                               <div class="widget-box lighter  widget-color-blue2">
                                                                    <div class="widget-header">
                                                                <h5 class="widget-title">Purchase Reports</h5>
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
																	<input class="form-control date-picker" id="id-date-picker-1" type="text" value="" name="todate" data-date-format="dd/mm/yyyy" required/>
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
                                                                                                                <th></th>
                                                                                                                <th>Date</th>
                                                                                                                <th>Mode</th>
                                                                                                                <th>Supplier Name</th>
                                                                                                                <th>Category</th>														
                                                                                                                <th>Assessable value</th> 
                                                                                                                 <th>CGST</th> 
                                                                                                                 <th>SGST</th> 
                                                                                                                 <th>ADD/LESS</th> 
														<th >Total Invoice Amount</th> 	
													</tr>
												</thead>

												<tbody>
                                                                                                     <c:set var="totalAmount" value="${0}"/>
                                                                                                     <c:set var="totalCgst" value="${0}"/>
                                                                                                      <c:set var="totalVat" value="${0}"/>
                                                                                                       <c:set var="defAddless" value="${0}"/>
                                                                                                       <c:set var="totalInvoiceAmount" value="${0}"/>
                                                                                                      
                                                                                                       
                                                                                                    <c:set var="FAmount" value="${0}"/>  
                                                                                                     <c:set var="FCgst" value="${0}"/>
                                                                                                      <c:set var="FVat" value="${0}"/>
                                                                                                       <c:set var="FaddOrLess" value="${0}"/>
                                                                                                       <c:set var="FtotalInvoiceAmount" value="${0}"/>
                                                                                                     
                                                                                                    
                                                                                                    
                                                                                                    <c:forEach items="${purchaseListFullinfo}" var="purchaseInfo">
													<tr>
														<th rowspan="2" class="center">${purchaseInfo.purchaseInvoiceId}</th>
                                                                                                                <th>Definition</th>
                                                                                                                <td>${purchaseInfo.date}</td>
                                                                                                                <td>${purchaseInfo.mode}</td>
														<td>${purchaseInfo.nameOfSupplier}</td>
                                                                                                                <td>${purchaseInfo.category}</td>
                                                                                                                <td>${purchaseInfo.totalAmount}</td>                                                                                                                
                                                                                                                <td>${purchaseInfo.totalCgst}</td>
                                                                                                                <td>${purchaseInfo.totalVat}</td>
														<td >${purchaseInfo.defAddless}</td>
														<td>${purchaseInfo.totalInvoiceAmount}</td>
                                                                                                                
                                                                                                                 <s:hidden ${totalAmount=totalAmount + purchaseInfo.totalAmount}/>
                                                                                                                 <s:hidden ${totalCgst=totalCgst + purchaseInfo.totalCgst}/>
                                                                                                                 <s:hidden${totalVat=totalVat + purchaseInfo.totalVat}/>
                                                                                                                 <s:hidden${defAddless=defAddless + purchaseInfo.defAddless}/>
                                                                                                                 <s:hidden${totalInvoiceAmount=totalInvoiceAmount + purchaseInfo.totalInvoiceAmount}/>
                                                                                                                </tr>
                                                                                                       <tr>
																												
                                                                                                                <th>Formula</th>
                                                                                                                <td>${purchaseInfo.date}</td>
                                                                                                                <td>${purchaseInfo.mode}</td>
														<td>${purchaseInfo.nameOfSupplier}</td>
                                                                                                                <td>${purchaseInfo.category}</td>
                                                                                                                <td>${purchaseInfo.assessValue}</td>                                                                                                                
                                                                                                                <td>${purchaseInfo.defCgst}</td>
                                                                                                                <td>${purchaseInfo.defVat}</td>
														<td >${purchaseInfo.addOrLess}</td>
														<td>${purchaseInfo.totalInvoiceAmount}</td>
                                                                                                                 <s:hidden ${FAmount=FAmount + purchaseInfo.totalAmount}/>
                                                                                                                 <s:hidden ${FCgst=FCgst + purchaseInfo.defCgst}/>
                                                                                                                 <s:hidden${FVat=FVat + purchaseInfo.defVat}/>
                                                                                                                 <s:hidden${FaddOrLess=FaddOrLess + purchaseInfo.addOrLess}/>
                                                                                                                 <s:hidden${FtotalInvoiceAmount=FtotalInvoiceAmount + purchaseInfo.totalInvoiceAmount}/>
                                                                                                               
                                                                                                               </tr>
                                                                                                                                                                                                  
													</c:forEach>
												</tbody>
                                                                                                <tr>
														
														<th></th>
                                                                                                                <th></th> 
                                                                                                                <th></th>
                                                                                                                <th></th>
                                                                                                                <th></th>
                                                                                                                <th class="center"><span style="color:Green">Total For Definiton</span></th> 
														<th>${totalAmount}</th>
                                                                                                                <th>${totalCgst}</th> 
                                                                                                                <th>${totalVat}</th> 
                                                                                                                <th>${defAddless}</th>
                                                                                                                <th>${totalInvoiceAmount}</th>

														
													</tr>
                                                                                                        <tr>
														
														<th></th>
                                                                                                                <th></th> 
                                                                                                                <th></th>
                                                                                                                <th></th>
                                                                                                                <th></th>
                                                                                                                <th class="center"><span style="color:Green">Total for Formula</span></th> 
														<th>${FAmount}</th>
                                                                                                                <th>${FCgst}</th> 
                                                                                                                <th>${FVat}</th> 
                                                                                                                <th>${FaddOrLess}</th>
                                                                                                                <th>${FtotalInvoiceAmount}</th>

														
													</tr>
                                                                                                      
											</table>
										</div>
                                                                                                                    </form>
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
