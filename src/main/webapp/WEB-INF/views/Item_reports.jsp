<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>Item Report-Accounting</title>

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
                <c:set var="listLength" value="${fn:length(listGroup)}"/>
        <c:forEach items="${listGroup}" var="group">
            <c:choose>
                <c:when test="${group.moduleName=='Item_reports'}">
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
								<a href="Dashboard.html">Home</a>
							</li>

							<li class="active">Item Report</li>
						</ul><!-- /.breadcrumb -->

					
                                            		
					</div>

					<div class="page-content">
						
						
<form class="form-horizontal" role="form" target="_blank" action="ItemPreview.html" modelAttribute="reportForm" method="GET" onsubmit="if($('input[name=type]:checked').length<=0){ alert('Select Atleast one Checkbox'); return false;}">
						<div class="row">			
                                                <div class="form-group col-sm-5">
										<label for="id-date-picker-1">From Date</label>

														
															
																<div class="input-group">
																	<input class="form-control date-picker" id="id-date-picker-1" type="text" name="fromdate" data-date-format="dd/mm/yyyy" value="${finalDate}"  required/>
																	<span class="input-group-addon">
																		<i class="fa fa-calendar bigger-110"></i>
																	</span>
																</div>
															
									</div>                                                                    
                                   			 			
                                                 <div class="form-group col-sm-5">
										<label for="id-date-picker-1">To Date</label>

														
															
																<div class="input-group">
																	<input class="form-control date-picker" id="id-date-picker-1" type="text" name="todate" data-date-format="dd/mm/yyyy" value="${curDate}" required/>
																	<span class="input-group-addon">
																		<i class="fa fa-calendar bigger-110"></i>
																	</span>
																</div>
															
									</div>                                                         
                                   								                                                             								 
											                                                             								 
							</div><!-- /.row -->
                                                        <div class="row">			
                                                      
                                   			 			
                                                <div class="form-group col-sm-5">
										<label class="col-sm-8 " for="form-field-1"> List Of Items </label>
										<div class="col-sm-12" id="replaceItem">
                                            <select   name="item" class="form-control" required>
                                                <option value=""></option>
                                                 <c:forEach items="${itemList}" var="il">
                                                    <option value="${il.id}" ${il.id==item ?'Selected':''}>${il.itemName}</option>
                                                </c:forEach>   
                                                                                         </select> 
										</div>
									</div> 
                                                            
                                                     <div class="form-group col-sm-5">
										<label class="col-sm-8 " for="form-field-1">List Of Item Groups </label>
										<div class="col-sm-12">
                                            <select   name="itemgroup" class="form-control" id="groupid" >
                                                  <option value="PRIMARY">PRIMARY</option>
                                                <c:forEach items="${itemGroupList}" var="ig">
                                                   
                                                    <option value="${ig.idItem}">${ig.itemGroupName}</option>
                                                </c:forEach>                    
                                                                                         </select> 
										</div>
									</div>         
                                               
                                                </div>        
                                               <div class="row">			
                                                <div class="form-group col-sm-2 ">
                                                    <input type="radio" class="ace ace-checkbox-2 ace-save-state " name="type"  value="0"  />
                                                                                
										<label class="lbl" for="ace-settings-navbar"> Voucher </label>
                                                                                
															
						</div>                                                                    
                                   			 			
                                                 <div class="form-group col-sm-2">
                                                     <input type="radio" class="ace ace-checkbox-2 ace-save-state" name="type"  value="1"  />
                                                                                
										<label class="lbl" for="ace-settings-navbar"> Date</label>
                                                                                
						</div>  
                                                       <div class="form-group col-sm-2">
                                                     <input type="radio" class="ace ace-checkbox-2 ace-save-state" name="type"  value="2"  />
                                                                                
										<label class="lbl" for="ace-settings-navbar"> Month</label>
                                                                                
						</div>  
                                                     <div class="form-group col-sm-2">
                                                     <div class="col-sm-12">
                                            <select   name="format" class="form-control" id="groupid" >
                                                <option value="HTML">HTML</option> 
                                                <option value="PDF">PDF</option>                                                 
                                                <option value="EXCEL">EXCEL</option>                                                       
                                                 </select> 
					            </div>
						</div> 
                                                       <div class="form-group col-sm-4">
                                                       
										<input type="submit" class=" btn btn-sm btn-primary " value="Get Results"/>
								
						</div>  
                                   								                                                             								 
											                                                             								 
							</div><!-- /.row -->         
                                                        
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
                         $('#groupid').change(function() {
                      
                        $.ajax({
                            type:"GET",
                            url : "SelectGroupDropDownBasedOnGroup.html",
                            data : "groupid="+$(this).val(),
                            async: false,
                            success : function(response) {
                               $('#replaceItem').html(response)
                               // return response;
                            },
                            error: function() {
                                alert('Error occured');
                            }
                        });
                      
                    });
                        
                        
		</script>
	</body>
</html>
