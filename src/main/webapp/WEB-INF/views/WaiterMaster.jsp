<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>KOT - Waiter Information</title>

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
           <c:set var="listLength" value="${fn:length(listGroup)}"/>
        <c:forEach items="${listGroup}" var="group">
            <c:choose>
                <c:when test="${group.moduleName=='WaiterMaster'}">
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
                            <form:form method="post" action="saveWaiter.html" modelAttribute="waiterForm">
                                
                                <c:if  test="${not empty waiterForm.id}"	>
                                                                  
                                    <div class="form-group">
                                        <div class="col-sm-9">
                                            <form:input type="hidden" path="id" id="form-field-1" placeholder="unit id" class="form-control"  readonly="true"/>
                                        </div>
                                    </div>
                                               
                                </c:if>
                                
                                <div class="widget-box lighter  widget-color-blue2">
                                    <div class="widget-header">
                                        <h5 class="widget-title">Waiter Details</h5>
                                    </div>
                                    <div class="widget-body">
                                        <div class="widget-main alert-success">
                                            <div class="row" >
                                                <div class="form-group col-sm-3">

                                                    <label class="col-sm-8" for="form-field-1"><span style="color:red;">*</span> Waiter ID </label>

                                                        <div class="col-sm-12">
                                                            <form:input  type="text"  path="waiterId" placeholder="Waiter ID" id="waiterId" class="form-control " required="true"  maxlength="50"></form:input>
                                                                <form:errors path="waiterId" style="color:red;"/>
                                                        </div>
                                                </div>

                                                <div class="form-group col-sm-3">

                                                    <label class="col-sm-8" for="form-field-1"><span style="color:red;">*</span> Waiter Name </label>

                                                        <div class="col-sm-12">
                                                                <form:input  type="text"  path="waiterName" placeholder="Waiter Name" id="waiterInfo" class="form-control " required="true"  maxlength="50"/>
                                                                <form:errors path="waiterName" style="color:red;"/>
                                                        </div>
                                                </div>

                                                <div class="form-group form-group col-sm-3">
                                                        <label class="col-sm-8 " for="form-field-1-1"> Mobile No </label>

                                                        <div class="col-sm-12">
                                                                <form:input  type="text"  path="mobileNo" oninvalid="setCustomValidity('Please Enter 10 digit Number ')" onchange="try{setCustomValidity('')}catch(e){}" onkeypress="return isNumberKey(event)"  minlength="10" maxlength="11" placeholder="Mobile No" class="form-control" required="true" />
                                                                <form:errors path="mobileNo"/>
                                                        </div>
                                                </div>        

                                                <div class="form-group col-sm-3">
                                                        <label class="col-sm-8" for="form-field-1-1"> Address </label>

                                                        <div class="col-sm-12">
                                                                <form:input  type="text"  path="address" placeholder="Address" class="form-control " required="true"/>
                                                                <form:errors path="address"  />
                                                        </div>
                                                </div>
                                            </div>
                                            <div class="row">    
                                                
                                                <div class="form-group col-sm-3">
                                                        <label class="col-sm-8 " for="form-field-1"><span style="color:red;">*</span>Gender</label>

                                                        <div class="col-sm-12">

                                                            <form:select class="chosen-select form-control"  path="gender" id="form-field-select-3" data-placeholder="Choose gender..." required="true">
                                                                <form:option value="">--- Please Select --- </form:option>
                                                                <form:option value="Male">Male</form:option>
                                                                <form:option value="Female">Female</form:option>
                                                                <form:option value="Others">Others</form:option>   
                                                            </form:select>

                                                        </div>
                                                </div>
                                                
                                                <div class="form-group col-sm-3">

                                                    <label class="col-sm-8" for="form-field-1"><span style="color:red;">*</span> From Serial </label>

                                                        <div class="col-sm-12">
                                                            <form:input  type="text"  path="fromSerial" placeholder="From KOT No" id="fromSerial" class="form-control " maxlength="50"></form:input>
                                                                <form:errors path="fromSerial" style="color:red;"/>
                                                        </div>
                                                </div>
                                                
                                                <div class="form-group col-sm-3">

                                                    <label class="col-sm-8" for="form-field-1"><span style="color:red;">*</span> To Serial </label>

                                                        <div class="col-sm-12">
                                                            <form:input  type="text"  path="toSerial" placeholder="To KOT No" id="toSerial" class="form-control "  maxlength="50"></form:input>
                                                                <form:errors path="toSerial" style="color:red;"/>
                                                        </div>
                                                </div>
                                                
                                                <div class="form-group  col-sm-3">
                                                    <label class="col-sm-8" for="form-field-1" style="visibility: hidden">Hidden Label </label>
                                                    <div class="col-sm-12" style="padding-right: 140px;">
                                                        <input  type="submit" id="btnSubmit" class="btn btn-info" value="Save" style="float: right;"/>
                                                    </div>    
                                                </div>        
                                                        
                                            </div>
                                               
                                        </div>
                                    </div>
                                </div>    
                            </form:form>
                            
                            <br><br>
                            
                            <div class="table-header">
                                     Waiter List  
                            </div>

                            <div>
                                <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>

                                            <th>Waiter ID</th>
                                            <th>Waiter Name</th>
                                            <th>Mobile No</th>
                                            <th>Gender</th>
                                            <th>Address</th>
                                            <th>From KOT No---To KOT No</th>
                                            <th>Edit</th>
                                            <th>Delete</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <c:forEach items="${waiterList}" var="wl">
                                            <tr>

                                                <td>${wl.waiterId}</td>

                                                <td>${wl.waiterName}</td>

                                                <td>${wl.mobileNo}</td>

                                                <td>${wl.gender}</td>
                                                
                                                <td>${wl.address}</td>
                                                
                                                <td width="15%">${wl.fromSerial} - ${wl.toSerial}</td>

                                                <td>
                                                    <div class="hidden-sm hidden-xs action-buttons">


                                                        <a class="green" href="EditWaiter.html?wid=${wl.id}">
                                                            <i class="ace-icon fa fa-pencil bigger-130"></i>
                                                        </a>


                                                    </div>


                                                </td>
                                                <td>
                                                    <div class="hidden-sm hidden-xs action-buttons">
                                                        <a class="red" href="DeleteWaiter.html?wid=${wl.id}">
                                                            <i class="ace-icon fa fa-trash-o bigger-130"></i>
                                                        </a>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
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
        $(document).ready(function() {

            var table = $('#dynamic-table').DataTable();

//            $('.dataTables_filter input').on( 'keyup click', function () {   // for text boxes
//                var v =$(this).val();  // getting search input value
//
//                table.columns(0).search('^' +v,true,false).draw();
//
//            } );
        }); 
   
  
    </script>                
                


	</body>
</html>
