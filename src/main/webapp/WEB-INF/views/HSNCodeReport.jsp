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
                <c:when test="${group.moduleName=='HSNCodeReport'}">
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
                                                       
                                                        <li class="active">HSN Code Reports</li>
						</ul><!-- /.breadcrumb -->

					
                                            		
					</div>

					<div class="page-content">
						
						<div class="page-header">
							<h1>HSN Code Reports</h1>
                                                        
							
						</div><!-- /.page-header -->
                                         
                                                            <!-- /.row -->
                                                      
                                                           <div class="col-xs-12">
                                                               <form id="wizardForm" action="GetHSNCodeReports.html"  method="POST" target="_blank" onsubmit="if($('input[name=type]:checked').length<=0){ alert('Select Atleast one Checkbox'); return false;}">
                                                               <div class="widget-box lighter  widget-color-blue2">
                                                                    <div class="widget-header">
                                                                <h5 class="widget-title">HSN Code Reports</h5>
                                                                    </div>
                                                                         <div class="widget-body">
                                                                           
                                                                <div class="widget-main alert-success">
                                                               <div class="row">			
    <div class="form-group col-sm-3">
                                    <label for="id-date-picker-1">From Date</label>



                                                                                    <div class="input-group">
                                                                                            <input class="form-control date-picker" id="id-date-picker-1" type="text" name="fromdate" value="${finalDate}" data-date-format="dd/mm/yyyy" required="true"/>
                                                                                            <span class="input-group-addon">
                                                                                                    <i class="fa fa-calendar bigger-110"></i>
                                                                                            </span>
                                                                                    </div>

                            </div>                                                                    

     <div class="form-group col-sm-3">
                                    <label for="id-date-picker-1">To Date</label>



                                                                                    <div class="input-group">
                                                                                            <input class="form-control date-picker" id="id-date-picker-1" type="text" name="todate" value="${curDate}" data-date-format="dd/mm/yyyy" required="true"/>
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
                                                                   </div> 
                                                <div class="row">
                                                                   
                                                                                                                      
                                                                   <div class="form-group col-sm-2 ">
                                                    <input type="radio" class="ace ace-checkbox-2 ace-save-state " name="type"  value="0"  />
                                                                                
										<label class="lbl" for="ace-settings-navbar"> Sales</label>
                                                                                
															
						</div>                                                                    
                                   			 			
                                                 <div class="form-group col-sm-2">
                                                     <input type="radio" class="ace ace-checkbox-2 ace-save-state" name="type"  value="1"  />
                                                                                
										<label class="lbl" for="ace-settings-navbar"> Sales Return</label>
                                                                                
						</div> 
                                                      <div class="form-group col-sm-2">
                                                     <input type="radio" class="ace ace-checkbox-2 ace-save-state" name="type"  value="2"  />
                                                                                
										<label class="lbl" for="ace-settings-navbar"> Purchase </label>
                                                                                
						</div> 
                                                      <div class="form-group col-sm-2">
                                                     <input type="radio" class="ace ace-checkbox-2 ace-save-state" name="type"  value="3"  />
                                                                                
										<label class="lbl" for="ace-settings-navbar"> Purchase Return</label>
                                                                                
						</div> 
                                                                   
                                                                  <div class="form-group col-md-3">
										<input type="submit" class="width-60 pull-right btn btn-sm btn-primary bigger-60" value="Get Details"/>
									</div>
                                   								                                                             								 
											                                                             								 
							</div>
							
                                                                    </div>
                                                        </div>
                                                    </div>
										
                                                                                       </form>                          </div>
										
									</div>
                                                                    
                        </div>
                                    
				</div>
			<!--</div>-->
                            

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
                            },
                            error: function() {
                                alert('Error occured');
                            }
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
