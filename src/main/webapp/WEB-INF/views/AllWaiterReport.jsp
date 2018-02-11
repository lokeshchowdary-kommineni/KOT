<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>Waiter Report List</title>

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

							<li class="active">Waiter Report List</li>
						</ul><!-- /.breadcrumb -->

					
                                            		
					</div>

					<div class="page-content">
					    <div class="row">
                                                <div class="col-xs-12">
                                                        <!--<h3 class="header smaller lighter blue">Buyer List</h3>-->
                                                      <div class="table-header">
                                                           Waiter Report List
                                                                </div>
                                                       
                                                        <div class="clearfix">
                                                                <div class="pull-right tableTools-container"></div>
                                                        </div>

                                                            <br>

                                                        <div>
                                                                <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                                                                        <thead>
                                                                                <tr>
                                                                                        <th> Waiter Name </th>
                                                                                        <th> Item Name</th>
                                                                                        <th> Basic Price</th>
                                                                                        <th> Quantity </th>
                                                                                        <th> Total Price </th>
                                                                                        <th> Invoice No </th>
                                                                                        <th> Sales Date </th>
                                                                                        <th> Item Type </th>
                                                                                </tr>
                                                                        </thead>
                                                                      

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
                            dateFormat: "dd-mm-yy",
                            changeMonth: true,
                            changeYear: true

                        });
                        $('#endDatePicker').datepicker({
                            dateFormat: "dd-mm-yy",
                            changeMonth: true,
                            changeYear: true

                        });
                    });
                </script>
       <script>
        
            $(document).ready(function() {
                
    $('#dynamic-table').DataTable( {
        "processing": true,
        "serverSide": true,
        "ajax": "GetWaiterReport.html",
         "aoColumnDefs": [
        { 
          "bSortable": false, 
          "aTargets": [ -1 ] // <-- gets last column and turns off sorting
         },
         { 
          "bSortable": false, 
          "aTargets": [ -2 ] // <-- gets last before column and turns off sorting
         } 
     ],
        "fnRowCallback": function( nRow, aData, iDisplayIndex ) {  
   
//        $('td:eq(4)', nRow).html('<a href="buyerEdit.html?eid='+aData[4]+'"<span class="green"><i class="ace-icon fa fa-pencil bigger-130"></i></span></a> | <a href="buyerDelete.html?did='+aData[4]+'"<span class="red"><i class="ace-icon fa fa-trash-o bigger-130" ></i></span></a>');

            },  
} );

});   
     </script> 
	</body>
</html>
