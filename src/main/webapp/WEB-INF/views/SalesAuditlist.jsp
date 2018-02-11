<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
            


		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>Accounting - Table Information</title>

		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

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
                
                 <c:set var="listLength" value="${fn:length(listGroup)}"/>
        <c:forEach items="${listGroup}" var="group">
            <c:choose>
                <c:when test="${group.moduleName=='AuditSalesInvoicelist'}">
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
                <div class="main-content">
                    <div class="main-content-inner">
                        <div class="page-content">
                           
                            <div class="page-header">
							<h1>Sales Invoice List</h1>
                                                        
							
						</div>
                           
                        
                          
                           
                               
<form:form method="post" action="AuditSalesInvoicelist.html">
   
    
     <div class="col-xs-12">
										
										<div class="table-header">
											Sales Invoice Records
										</div>

										<div>
											<table id="dynamic-table" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														
						<th>Bill No</th>
                                                <th>Sale Date</th>
                                                <th>Waiter Name</th>
                                                <th>Table Name</th>
                                                <th>Net Total</th>
                                                <th>Kot No's</th>
                                               

														
													</tr>
												</thead>

												<tbody>
                          
                                                       
                                                        
                                                        
                                                   
                                </tbody>
											</table>
										</div>
									</div>
                            
                      
                               <!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content" id="addBookDialog">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Select Payement Mode</h4>
      </div>
      <div class="modal-body">
       
        <input type="text" name="bookId" id="bookId" value=""/>
        
        <label>CASH</label> <input type="radio" name="bookId" id="bookId" value="cash"/>
        &nbsp;
        <label>CARD</label><input type="radio" name="bookId" id="bookId" value="card"/>
      </div>
      <div class="modal-footer">
           <button type="button" class="btn btn btn-info" data-dismiss="modal">Save</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

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
              
            </div>    
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
                    
        // Checking date for Return Limit with Invoice Date ---> Abi
        
            $(document).ready(function() {
                
    $('#dynamic-table').DataTable( {
        "processing": true,
        "serverSide": true,
        "ajax": "GetDatatablesAuditGrid.html",
        "fnRowCallback": function( nRow, aData, iDisplayIndex,returnLimit ) { 
            
       
       
        
        
        
            
          
           

          
    }
} );

});   
     
</script>
 
<!--GetDatatablesGrid-->
<!--<script>
   $(document).on("click", ".open-AddBookDialog", function popup(){
        var myBookId=document.getElementById("id"); 
        var ids=document.getElementById("bookId").value;
        myBookId.value=(ids);
                    }
    
</script>-->

	</body>
</html>