<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>Accounting-VAA_MCA Reports</title>

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
                <c:when test="${group.moduleName=='VAAMCAReports'}">
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

							<li class="active">VAA MCA Records</li>
						</ul><!-- /.breadcrumb -->

					
                                            		
					</div>

					<div class="page-content">
						
						<div class="page-header">
                                                    <h1>VAA MCA Register</h1>
                                                        
							
						</div><!-- /.page-header -->
                                         
                                                            <!-- /.row -->
                                                       <div class="col-xs-12">
                                                           <form class="form-horizontal" action="GetVAAMCAReports.html"  method="POST" target="_blank">             
                                                                   <div class="widget-box lighter  widget-color-blue2">
                                                                    <div class="widget-header">
                                                                <h5 class="widget-title">VAA MCA Records</h5>
                                                                    </div>
                                                                         <div class="widget-body">
                                                                           
                                                                <div class="widget-main alert-success">
                                                               <div class="row">
                                                           
										

														
															
																
																	<input class="form-control " id="num_nights" type="hidden" name="num_nights" readonly/>
																	
																
															
									
                                                <div class="form-group col-sm-3">
										<label for="id-date-picker-1">From Date</label>

														
															
																<div class="input-group">
																	<input class="form-control date-picker" id="fromdate" type="text" name="fromdate" value="${finalDate}" data-date-format="dd/mm/yyyy"  required/>
																	<span class="input-group-addon">
																		<i class="fa fa-calendar bigger-110"></i>
																	</span>
																</div>
															
									</div>    
                                                                                                                                        
                                                                   
                                   			 			
                                                 <div class="form-group col-sm-3">
										<label for="id-date-picker-1">To Date</label>
                                                                                
														
															
																<div class="input-group">
																	<input class="form-control date-picker" id="todate" type="text" name="todate" value="${curDate}" data-date-format="dd/mm/yyyy"  required/>
																	<span class="input-group-addon">
																		<i class="fa fa-calendar bigger-110"></i>
																	</span>
																</div>
															
									</div>  
                                                                   
                                                                   <div class="form-group col-md-3">
                                                                               <label class="col-sm-8 " for="form-field-1">View Type </label>

                                                                               <div class="col-sm-12">
                                                                                <select class="chosen-select form-control"  name="viewType" id="form-field-select-3" >                                                                                       
                                                                                        <option value="HTML"/>HTML</option>
                                                                                        <option value="PDF"/>PDF</option>
                                                                                        <option value="XSLX"/>XSLX</option>    

                                                                             
                                                                                </select>
                                                                                      
                                                                                   </div>
                                                                        </div>
                                                                   <div class="clearfix form-group col-sm-2">
										<input type="submit" class="width-60 pull-right btn btn-sm btn-primary bigger-60"  value="Get Details"/>
									</div>
                                   								                                                             								 
											                                                             								 
							</div>
							
                                                                    
                                                                     
                                                                    </div>
                                                        </div>
                                                    </div>
										<div>
											
                                                                                    
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
                function showDays() {
                
    var start = $('#fromdate').datepicker('getDate');
    var end = $('#todate').datepicker('getDate');
    if (!start || !end) return;
    var days = (end - start) / 1000 / 60 / 60 / 24;
    $('#num_nights').val(days);
}

$("#fromdate").datepicker({
    dateFormat: 'dd/mm/yy',
    onSelect: showDays,
     onClose: function( selectedDate ) {
         var dParts = selectedDate.split('-');
         var in30Days = new Date(dParts[2] + '/' +
                        dParts[1] + '/' +
                        (+dParts[0] + 30)
               );
        
    $( "#todate" ).datepicker( "option", "minDate", in30Days );
    }
});
$("#todate").datepicker({
    dateFormat: 'dd/mm/yy',
    onSelect: showDays,
     
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
