<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="account"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>ITC Reversal-Accounting</title>

		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/font-awesome/4.5.0/css/font-awesome.min.css" />

		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="assets/css/bootstrap-duallistbox.min.css" />
		<link rel="stylesheet" href="assets/css/bootstrap-multiselect.min.css" />
		<link rel="stylesheet" href="assets/css/select2.min.css" />

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
                <c:when test="${group.moduleName=='ITC Reversal Master'}">
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
					

					<div class="page-content">
						
						<div class="page-header">
							<h1>ITC Reversal Master</h1>
                                                        <div style="text-align: center; ">
                                                             <span style="color:orangered;" > ${message} </span>
                                                              </div>
							
						</div>

						<div class="row">
							<div class="col-xs-5">
								<!-- PAGE CONTENT BEGINS -->
								<account:form class="form-horizontal" role="form" action="itcsave.html" modelAttribute="itcForm" method="POST">
                                                                    
                                                                    <c:if  test="${not empty itcForm.idItc}"	>
                                                                    
                                                                    
                                                                    <div class="form-group">
										

										<div class="col-sm-9">
											<account:input type="hidden" path="idItc" id="form-field-1" placeholder="unit id" class="form-control " readonly="true"/>
                                                                                        <input type="hidden" name="itcName" value="${itcForm.categoryName}" />
										</div>
									</div>
                                                                                
                                                                          </c:if>      
                                                                                
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Category Name <span style="color:red;">*</span></label>

										<div class="col-sm-9">
											<account:input type="text" path="categoryName" id="Box1" tabindex="1" placeholder="Category name" pattern="[a-zA-Z. \s]+" oninvalid="setCustomValidity('Please enter only Alphabets ')" onchange="try{setCustomValidity('')}catch(e){}" minlength="1" maxlength="50" class="form-control " required='true'/>
                                                                                         <span class="red"><account:errors path="categoryName"  /></span>
                                                                                        <span style="color: red;">${val}</span>
                                                                                        
										</div>
									</div>
                                                                                 <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Under Category </label>

										<div class="col-sm-9">
											 <account:select path="underCategory" tabindex="2" class="form-control" >
                                                                                                                        <account:option value="0" label="primary"/>
                                                                                                                        <account:options  items="${itcList}" itemValue="idItc" itemLabel="categoryName"/>
                                                                                                                         
                                                                                         </account:select> 
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1-1">Sec Code </label>

										<div class="col-sm-9">
											<account:input type="text" path="secCode" tabindex="3" id="form-field-1-1" maxlength="30" placeholder="sec code" class="form-control" />
										</div>
									</div>
                                                                  <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">  Descriptions </label>

										<div class="col-sm-9">
                                                                                    <account:textarea path="descriptions" tabindex="4" id="form-field-1" placeholder="description" class="form-control "/>
										</div>
									</div>


									<div class="clearfix">
										<div class="col-md-offset-3 col-md-9">
                                                                                                        
                                                                                                        <input type="submit" id="btnSubmit"  class="btn btn-info" value="Save"/>
                                                                                                        &nbsp; &nbsp; &nbsp;
                                                                                                       <input type="reset" class="btn" value="Reset" onClick="window.location.reload()">
                                                                                                          </div> 
									</div>

									<div class="hr hr-24"></div>

								</account:form>

								
							</div><!-- /.col -->
                                                        <div class="col-xs-7">
										
										<div class="table-header">
											ITC Reversal List
										</div>

										<!-- div.table-responsive -->

										<!-- div.dataTables_borderWrap -->
										<div>
											<table id="dynamic-table" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														
														
														<th>Category Name</th>
                                                                                                                <th>Sec Code</th>
                                                                                                                 <th>Edit</th>
                                                                                                                 <th>Delete</th>

														
													</tr>
												</thead>

												<tbody>
                                                                                                      <c:forEach items="${itcList}" var="ul">
													<tr <c:choose><c:when test="${ul.predefined =='0'}">style="background-color: lightgoldenrodyellow;"</c:when> <c:otherwise>style="background-color: pink;"</c:otherwise></c:choose> >
														
                                                                                                                

														<td>${ul.categoryName}</td>
														
														<td>${ul.secCode}</td>
														
														

														

														
                                                                                                                    <c:choose>
                                                                                                                    <c:when test="${ul.predefined =='0'}">
                                                                                                                    <td> <a class="green" href="itcEdit.html?eid=${ul.idItc}" title="Edit">
																	<i class="ace-icon fa fa-pencil bigger-130"></i>
																</a>
                                                                                                                    </td>
                                                                                                                    <td></td>
                                                                                                                    </c:when>
                                                                                                                    <c:otherwise>
                                                                                                                        <td> <a class="green" href="itcEdit.html?eid=${ul.idItc}" title="Edit">
																	<i class="ace-icon fa fa-pencil bigger-130"></i>
                                                                                                                            </a></td>

                                                                                                                            <td><a class="red" href="itcDelete.html?did=${ul.idItc}" title="Delete">
																	<i class="ace-icon fa fa-trash-o bigger-130" ></i>
                                                                                                                                </a> </td>
                                                                                                                        
                                                                                                                    </c:otherwise>
                                                                                                                    </c:choose>
														
													</tr>
                                                                                                                                                                                                  
                                                                                                        </c:forEach>
												</tbody>
											</table>
										</div>
									</div>
						</div><!-- /.row -->
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
		<script src="assets/js/bootstrap-multiselect.min.js"></script>
		<script src="assets/js/select2.min.js"></script>
		<script src="assets/js/jquery-typeahead.js"></script>

		<!-- ace scripts -->
		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>
                <script>
                  $("#Box1").focus();
                  // to show cursor on first TextBox
                </script>
                <script>
                   jQuery.extend(jQuery.expr[':'], {
    focusable: function (el, index, selector) {
        return $(el).is('a, button, :input, [tabindex]');
    }
});

$(document).on('keypress', ':focusable', function (e) {
    if (e.which == 13) {
        e.preventDefault();
        // Get all focusable elements on the page
        var $canfocus = $(':focusable');
        var index = $canfocus.index(this) + 1;
        if (index >= $canfocus.length) index = 0;
        $canfocus.eq(index).focus();
    }
   $('#btnSubmit').keypress(function(event){
          //Check if the key pressed is ENTER key using the keyCode=13
          if(event.keyCode === 13){
//           alert('Successfully!'); 
          }
          event.cancelBubble = true;
             if (event.stopPropagation) event.stopPropagation();
         });
          
});
                </script>
                 <script>
  $(document).ready(function() {
                                     
  var table = $('#dynamic-table').DataTable();
  
    $('.dataTables_filter input').on( 'keyup click', function () {   // for text boxes
    
    var v =$(this).val();  // getting search input value
    table.columns(0).search('^' +v,true,false).draw();
    
});
}); 
   
  
</script>
	</body>
</html>
