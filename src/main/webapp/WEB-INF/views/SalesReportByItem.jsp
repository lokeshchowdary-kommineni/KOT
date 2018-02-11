<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>Sales Report By Item</title>

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
                <link rel="stylesheet" href="assets/css/jquery-ui.min.css"/>
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

							<li class="active">Sales Report By Item</li>
						</ul><!-- /.breadcrumb -->

					
                                            		
					</div>
                                             
                                                             <div style="text-align: center;">
                                                                <span style="color:red; font-size: 1.2em;" > ${msg}</span>
                                                             </div>
                                                            <br>
 
                                    
                                    
                                    

					<div class="page-content">
					    <div class="row">
                                                <div class="col-xs-12">
                                                        <!--<h3 class="header smaller lighter blue">Buyer List</h3>-->
                                                      <div class="table-header">
                                                           Sales Report By Item
                                                       </div>
                                                       
                                                        <div class="clearfix">
                                                                <div class="pull-right tableTools-container"></div>
                                                        </div>

                                                          
                                                           <form action="SalesReportItem.html" method="post">                                                 
                                                        <div class="row">
                                                        <div class="form-group">
                                                            <div class="form-group col-sm-3 ">
                                                                <label for="form-field-mask-1"> Item Name <span style="color:red;">*</span></label>
                                                                <select name='itemname' class="form-control" >
                                                                    <option value="${selected}" selected>${selected}</option>
                                                                    <c:forEach items="${ItemList}" var="item">
                                                                        <c:if test="${item.id != selected}">
                                                                            <option value="${item.id}">${item.itemName}</option>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                           
                                                            <div class="form-group col-sm-3 ">
								<label class="col-sm-8 " ><span style="color:red;">*</span> From Date </label>
                                                                <input type="text" class="form-control" id="startDatePicker" name="FromDate">
                                                            </div>
                                                                    
                                                             <div class="form-group col-sm-3 ">
                                                                <label class="col-sm-8 " ><span style="color:red;">*</span> To Date </label>
                                                                <input type="text" class="form-control" id="endDatePicker" name="ToDate">
                                                            </div>
                                                                    
                                                            <div class="form-group col-sm-3 ">
                                                                &emsp;
                                                                <label for="form-field-mask-2" style="visibility: hidden"><span style="color:red;">Action</span></label>
                                                                <input type="submit"  id="btnSubmit"  class="form-control btn btn-info" value="Get Report"/>
                                                            </div> 
                                                       
                                                        </div>
                                                        </form>  
                                                            <br>  
                                                            
                                                        <div>
                                                           <br>
                                                           <br>  
                                                           <br>  
                                                                <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                                                                        <thead>
                                                                                <tr>
                                                                                        <th> Item Name </th> 
                                                                                        <th> Item Type </th>
                                                                                        <th> In Quantity</th>
                                                                                        <th> Out Quantity </th>
                                                                                        <th> Transaction Type </th>
                                                                                        <th> Invoice No </th>
                                                                                        <th> Date </th>
                                                                                        
                                                                                </tr>
                                                                        </thead>
                                                                      <tbody>
                                            <c:forEach items="${iList}" var="i">
                                                <tr>
                                                   <td>${i[0]}</td> 
                                                   <td>${i[1]}</td> 
                                                   <td>${i[2]}</td> 
                                                   <td>${i[3]}</td> 
                                                   <td>${i[4]}</td> 
                                                   <td>${i[5]}</td> 
                                                   <td>${i[6]}</td> 
                                               </tr>
                                                </c:forEach>
                                               
                                        </tbody>

                                                                </table>
                                                        </div>
                                                </div>
				        </div>	
						
                       
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
                
                
                <script src="assets/js/jquery-ui.min.js"></script>
		<!-- ace scripts -->
		<script src="assets/js/ace-elements.min.js"></script>
                <script src="assets/js/ace-elements.min.js"></script>
		
                <script>
                    $(document).ready(function() {
                        $('#example1').dataTable();
                        $('#startDatePicker').datepicker({
                            dateFormat: "yy-mm-dd",
                            changeMonth: true,
                            changeYear: true

                        });
                        $('#endDatePicker').datepicker({
                            dateFormat: "yy-mm-dd",
                            changeMonth: true,
                            changeYear: true

                        });
                    });
                </script>

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
	</body>
</html>
