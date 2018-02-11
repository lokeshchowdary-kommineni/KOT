<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>KOT - Reprint Bill</title>

		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/font-awesome/4.5.0/css/font-awesome.min.css" />

                
                  <link rel="stylesheet" href="assets/calendar/fullcalendar.css" />
                  <!--<link rel="stylesheet" href="assets/calendar/fullcalendar.min.css" />-->
                  <!--<link rel="stylesheet" href="assets/calendar/fullcalendar.print.css" />-->
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
                <link rel="stylesheet" href="assets/css/custom.css" />
                <link rel="stylesheet" href="assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/font-awesome/4.5.0/css/font-awesome.min.css" />

		<!-- text fonts -->
		<link rel="stylesheet" href="assets/css/fonts.googleapis.com.css" />
               

		<!-- ace styles -->
		<link rel="stylesheet" href="assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
                <link rel="stylesheet" href="assets/css/jquery-ui.min.css" />
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
            <div class="main-container ace-save-state" id="main-container">
                <%@ include file="Header.jsp" %>
                <div class="main-content">
                    <div class="main-content-inner">
                        <div class="page-content">
                           
                            <div class="page-header">
							<h1>Sales Bill</h1>
                                                        
							
						</div>
                           
                         <div class="table-header">
											Sales Bill
										</div>
                          
                            <div>
                               
<!--                                <table class="table table-striped table-bordered table-hover"   id="example" >
                                    <tr>
                                        <th>Table Name</th>
                                        <th>KOT No'S</th>
                                        <th>Sales Date</th>
                                    </tr>
                                   
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                  
                                </table> -->
<form:form id="salesBill"  method="post" action="Duplicateprint.html" target="_blank">
                                            <div class="row">    
                                                <div class="form-group col-xs-2">
                                
                                
                                                                            
										<label class="col-sm-12" for="form-field-1">Bill NO<span style="color:red;">*</span></label>
                                                                                    
										<div class="col-sm-12">
                                                                                    <input type="text" name="invoiceNo" value="${BillName}"  readonly="true"/>
                                                                                       
										</div>
									</div>                          
    
   
                                                                 <div class="form-group col-xs-2">
                                
                                
                                
										<label class="col-sm-12" for="form-field-1"> Table Name<span style="color:red;">*</span></label>
                                                                                    
										<div class="col-sm-12">
                                                                                    <input name="tbn" class="form-control"  value="${tabelname}" readonly="true"/>
                                                                                       
										</div>
									</div>
                                                                                         <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> KOT No'S<span style="color:red;">*</span></label>
                                                                                    
										<div class="col-sm-12">
                                                                                        <form:input path="" value="${kotNos}" readonly="true"/>
                                                                                       
										</div>
									</div>
                                                                                         <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Sales Date<span style="color:red;">*</span></label>
                                                                                    
										<div class="col-sm-12">
                                                                                        <form:input path="" value="${saledate}" readonly="true"/>
                                                                                       
										</div>
									</div>
                                                                          
                                                                          <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1">Waiter Name<span style="color:red;">*</span></label>
                                                                                    
										<div class="col-sm-12">
                                                                                        <form:input path="" value="${waitername}" readonly="true"/>
                                                                                       
										</div>
									</div>              
                                                                    </div>                    
                                    <div class="table-header">
                                        List of Items Ordered
                                    </div>
                                    <div>
                                        <table class="table table-striped table-bordered table-hover"   id="example1" >  
                                            
                                           
                                           
                                           
                                            
                                            <thead>    
                                                <tr>
                                                    <th>ItemName</th>
                                                    <th>Qty</th>
                                                    <th>Cgst</th>
                                                    <th>Sgst</th>
                                                    <th>Price</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${rlist}" var="items">
                                                <tr>  
                                                    <td>${items.itemName}</td>  
                                                    <td>${items.quantity}</td>  
                                                    <td>${items.taxCgst}</td>  
                                                    <td>${items.taxSgst}</td>  
                                                    <td>${items.cap}</td>  
                                                </tr>  
                                            </c:forEach>
                                            </tbody>    
                                        </table>
					
                                    </div>    
                                
                                
                            </div>
                            
                             <div class="widget-box lighter  widget-color-blue2">
		
		<div class="widget-body">
			<div class="widget-main  alert-info">                                                      
					 <div class="row">
									
                                             
                                                 
                                                 
<!--                                                  <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Invoice NO<span style="color:red;">*</span></label>
                                                                                    
										<div class="col-sm-12">
                                                                                        
                                                                                       
										</div>
									</div>-->
                                                                                        
                                                   <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1">TotalAmount<span style="color:red;">*</span></label>
                                                                                    
										<div class="col-sm-12">
                                                                                    
                                                                                    <form:input path="" id="customerTotal" value="${cCtotal}" readonly="true"/>
                                                                                   
                                                                                      
                                                                                    
										</div>
									</div> 
                                                                                           
                                                                                   
                                                                                    <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1">CGST Amount<span style="color:red;">*</span></label>
                                                                                    
										<div class="col-sm-12">
                                                                                    
                                                                                    <form:input path="" value="${cgstamount}" readonly="true"/>
                                                                                   
                                                                                      
                                                                                    
										</div>
									</div> 
                                                                                     <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1">SGST Amount<span style="color:red;">*</span></label>
                                                                                    
										<div class="col-sm-12">
                                                                                    
                                                                                    <form:input path="" value="${sgstamount}" readonly="true"/>
                                                                                   
                                                                                      
                                                                                    
										</div>
									</div> 
                                                                                       
                                                                                        <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1">Discount<span style="color:red;">*</span></label>
                                                                                    
										<div class="col-sm-12">
                                                                                    <form:input path="" id="discount" value="${discount}" readonly="true" />
                                                                                       
										</div>
									</div>    
                                                                                        <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1">Service-Charges<span style="color:red;">*</span></label>
                                                                                    
										<div class="col-sm-12">
                                                                                        <form:input path="" id="acCharges" value="${additionalcharges}" readonly="true"/>
                                                                                       
										</div>
									</div>
                                                                                       
                                                                                        
                                                                                        <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1">Grand Total<span style="color:red;">*</span></label>
                                                                                    
										<div class="col-sm-12">
                                                                                    
                                                                                        <form:input path="" id="customerGrandTotal" value="${grandtotal}"  readonly="true"/>
                                                                                       
                                                                                      
                                                                                       
										</div>
									</div>
                                                                                       
                                                                                    
                                                                                     <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1">Round OFF <span style="color:red;">*</span></label>
                                                                                    
										<div class="col-sm-12">
                                                                                    
                                                                                    <form:input path="" id="roundOff1" value="${roundoff}"  readonly="true"/>
                                                                                       
                                                                                      
                                                                                      
										</div>
									</div>
                                                                                         <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1">Net Total <span style="color:red;">*</span></label>
                                                                                    
										<div class="col-sm-12">
                                                                                    
                                                                                    <form:input path="" id="custNetTotal" value="${nettotal}"   readonly="true"/>
                                                                                       
                                                                                      
                                                                                       
										</div>
									</div>
                                                                                     
										
                                                                                    
										
                                                                                    
                                                                                       
										
									
                                                                                        <div class="form-group col-xs-2">
                                                                                            
                                                                                           <label class="col-sm-12" for="form-field-1"> <span style="visibility: hidden">*</span></label>
                                                                                        <div class="col-sm-12">
                                                                                            
                                                                                            <input type="submit" value="Print" onclick="redirectSalesInvoiceList();"/>
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
                <%@ include file="Footer.jsp" %>
            </div>    

		<!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="assets/js/jquery-2.1.4.min.js"></script>

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
                
                        
                 <script>
                  
                                         
            function redirectSalesInvoiceList(){
                setTimeout(function(){document.location.href = "SalesInvoicelist.html";},50);
            }
            
            
 
            


                </script>
                <script>$('#example1').DataTable();</script>      
               


	</body>
</html>