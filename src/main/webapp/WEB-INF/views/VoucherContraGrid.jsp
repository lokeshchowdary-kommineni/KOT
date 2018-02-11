<%-- 
    Document   : VoucherContraGrid
    Created on : Feb 20, 2017, 4:51:00 PM
    Author     : MR
--%>

<%@page  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>Accounting- Voucher Contra Records</title>

		<meta name="description" content="Static &amp; Dynamic Tables" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/font-awesome/4.5.0/css/font-awesome.min.css" />

		<!-- page specific plugin styles -->

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
                <c:when test="${group.moduleName=='VoucherContraGrid'}">
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
                                    <a href="VoucherContra.html">Contra Voucher</a>
                            </li>
                            <li class="active">Contra Voucher Records</li>
                    </ul><!-- /.breadcrumb -->

                            </div>

                            <div class="page-content">

                                    <div class="page-header">
                                            <h1>
                                                   Contra Voucher  Records
                                                    <small>
                                                            <!--<i class="ace-icon fa fa-angle-double-right"></i>-->

                                                    </small>
                                            </h1>
                                    </div><!-- /.page-header -->

                                    <div class="row">
                                            <div class="col-xs-12">
                                                    <!-- PAGE CONTENT BEGINS -->
                                                    <!-- <div class="hr hr-18 dotted hr-double"></div> -->

                                                    <div class="row">
                                                            <div class="col-xs-12">

                                                                    <!-- <div class="clearfix">
                                                                            <div class="pull-right tableTools-container"></div>
                                                                    </div> -->
                                                                    <div class="table-header">
                                                                           Contra Voucher 
                                                                    </div>

                                                                    <!-- div.table-responsive -->

                                                                    <!-- div.dataTables_borderWrap -->
                                                                    <div>
                                                                            <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                                                                                    <thead>
                                                                                            <tr>
                                                                                                    
                                                                                                    <th>Contra No</th>
                                                                                                    <th>Date</th>
                                                                                                    <th>Amount</th>
                                                                                                    <th>Mode</th>
                                                                                                    <th>From</th>
                                                                                                    <th>Voucher Closing Balance</th>
                                                                                                    <th>To</th>
                                                                                                    <th>Voucher Closing Balance</th>
                                                                                                    <th>ACTION</th>
                                                                                                    
                                                                                            </tr>
                                                                                    </thead>

                                                                                    
                                                                            </table>
                                                                    </div>
                                                            </div>
                                                    </div>



                                                    <!-- PAGE CONTENT ENDS -->
                                            </div><!-- /.col -->
                                    </div><!-- /.row -->
                            </div><!-- /.page-content -->
                    </div>
            </div><!-- /.main-content -->

			
         <jsp:include page="Footer.jsp" />
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
		<script src="assets/js/dataTables.buttons.min.js"></script>
		<script src="assets/js/buttons.flash.min.js"></script>
		<script src="assets/js/buttons.html5.min.js"></script>
		<script src="assets/js/buttons.print.min.js"></script>
		<script src="assets/js/buttons.colVis.min.js"></script>
		<script src="assets/js/dataTables.select.min.js"></script>

		<!-- ace scripts -->
		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->
                 <script>
        
            $(document).ready(function() {
                
    $('#dynamic-table').DataTable( {
        "processing": true,
        "serverSide": true,
        "ajax": "GetDatatableContra.html",
        "fnRowCallback": function( nRow, aData, iDisplayIndex ) { 
            
          $('td:eq(1)', nRow).html(( aData[1].replace( /(\d{4})-(\d{2})-(\d{2})/, "$3/$2/$1") ));      
         $('td:eq(8)', nRow).html('<a href="VoucherContraEdit.html?id='+aData[0]+'" <span class="green"><i class="ace-icon fa fa-pencil-square-o bigger-120"></i></span>Edit</a>'); 
       
            },  
} );

});   
     
</script> 
		
	</body>
</html>

