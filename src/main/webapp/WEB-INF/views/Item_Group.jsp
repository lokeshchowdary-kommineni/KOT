<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="account"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>Item Group-Accounting</title>

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
                <c:when test="${group.moduleName=='Item Groups'}">
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
<!--					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="Dashboard.html">Home</a>
							</li>

							
							<li class="active">Item Group </li>
						</ul> /.breadcrumb 

					
                                            		
					</div>-->

					<div class="page-content">
						
						<div class="page-header">
							<h1>Item Group Master</h1>
                                                        <div style="text-align: center; ">
                                                             <span style="color:orangered;" > ${message} </span>
                                                              </div>
							
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-5">
								<!-- PAGE CONTENT BEGINS -->
								<account:form class="form-horizontal" role="form" action="itemsave.html" modelAttribute="itemgroupForm" method="POST">
                                                                    
                                                                    <c:if  test="${not empty itemgroupForm.idItem}"	>
                                                                    
                                                                    
                                                                    <div class="form-group">
										

										<div class="col-sm-9">
											<account:input type="hidden" path="idItem" id="form-field-1" placeholder="unit id" class="form-control " readonly="true"/>
                                                                                        <input type="hidden" path="itemGroupName" name="itemGroupname" value="${itemgroupForm.itemGroupName}"/>
										</div>
									</div>
                                                                                
                                                                          </c:if>      
                                                                                
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> ItemGroup Name <span style="color:red;">*</span> </label>

										<div class="col-sm-9">
											<account:input type="text" path="itemGroupName" tabindex="1" id="Box1" pattern="[a-zA-Z . \s]+" oninvalid="setCustomValidity('Please enter GroupName ')" onchange="try{setCustomValidity('')}catch(e){}"  maxlength="50" style="text-transform:uppercase" placeholder="Itemgroup Name" class="form-control " required="true"/>
                                                                                        <span class="red"><account:errors path="itemGroupName"  /></span>
                                                                                        <span style="color: red;">${val}</span>
										</div>
									</div>
                                                                         
                                                                         <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Group Under </label>

										<div class="col-sm-9">
											 <account:select path="groupUnder" tabindex="2" class="form-control" >
                                                                                                                        
                                                                                                                         <option value="PRIMARY">PRIMARY</option>
                                                                                                                        <account:options  items="${itemList}" itemValue="itemGroupName" itemLabel="itemGroupName" />
                                                                                                                       
                                                                                         </account:select> 
										</div>
									</div>
                                                                                

									<div class="clearfix">
										<div class="col-md-offset-3 col-md-9">
                                                                                                        
                                                                                                        <input type="submit" id="btnSubmit"  class="btn btn-info" value="Save"/>
                                                                                                        &nbsp; &nbsp; &nbsp;
                                                                                                        <button class="btn" type="reset">  Reset </button>
                                                                                                          </div>           
                                                                                                           
									</div>

									<div class="hr hr-24"></div>

								</account:form>

								
							</div><!-- /.col -->
                                                        <div class="col-xs-7">
										
										<div class="table-header">
											Item Group List
										</div>

										<!-- div.table-responsive -->

										<!-- div.dataTables_borderWrap -->
										<div>
											<table id="dynamic-table" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
<!--														<th class="center">
															<label class="pos-rel">
																<input type="checkbox" class="ace" />
																<span class="lbl"></span>
															</label>
														</th>-->
														
														<th>Item Group Name</th>
                                                                                                                <th>Group Under</th>
                                                                                                               <th> Edit </th>
                                                                                                                 <th> Delete </th>

														
													</tr>
												</thead>

												<tbody>
                                                                                                      <c:forEach items="${itemList}" var="il">
													<tr>
<!--														<td class="center">
															<label class="pos-rel">
																<input type="checkbox" class="ace" />
																<span class="lbl"></span>
															</label>
														</td>-->
                                                                                                              

														<td>${il.itemGroupName}</td>
														
														<td>${il.groupUnder}</td>
														
														

														

														<td>
															<div class="hidden-sm hidden-xs action-buttons">
                                                                                                                             <c:if  test="${il.itemGroupName !='BEVRAGES' && il.itemGroupName !='KITCHEN' && il.itemGroupName !='BEER'&& il.itemGroupName !='WINE'&& il.itemGroupName !='HOT DRINKS'&& il.itemGroupName !='SOFT DRINKS' }">              
																<a class="green" href="itemEdit.html?eid=${il.idItem}" title="Edit">
																	<i class="ace-icon fa fa-pencil bigger-130"></i>
																</a>
                                                                                                                             </c:if>  
																
                                                                                                                            <c:if  test="${il.itemGroupName =='BEVRAGES' || il.itemGroupName =='KITCHEN'|| il.itemGroupName =='BEER'|| il.itemGroupName =='WINE'|| il.itemGroupName =='HOT DRINKS'|| il.itemGroupName =='SOFT DRINKS' }">              
																
                                                                                                                             </c:if>                      
																
															</div>

															
														</td>
                                                                                                                <td>
                                                                                                                    <div class="hidden-sm hidden-xs action-buttons">
                                                                                                                         <c:if  test="${il.itemGroupName !='BEVRAGES' && il.itemGroupName !='KITCHEN' && il.itemGroupName !='BEER'&& il.itemGroupName !='WINE'&& il.itemGroupName !='HOT DRINKS'&& il.itemGroupName !='SOFT DRINKS' }">                           
																<a class="red" href="itemDelete.html?did=${il.idItem}" title="Delete">
																	<i class="ace-icon fa fa-trash-o bigger-130" ></i>
																</a>
                                                                                                                             </c:if>  
                                                                                                                     <c:if  test="${il.itemGroupName =='BEVRAGES' || il.itemGroupName =='KITCHEN'|| il.itemGroupName =='BEER'|| il.itemGroupName =='WINE'|| il.itemGroupName =='HOT DRINKS'|| il.itemGroupName =='SOFT DRINKS' }">                            
																
                                                                                                                             </c:if>  
                                                                                                                    </div>
                                                                                                                </td>
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
  $(document).ready(function() {
                                     
  var table = $('#dynamic-table').DataTable();
  
    $('.dataTables_filter input').on( 'keyup click', function () {   // for text boxes
    
    var v =$(this).val();  // getting search input value
    table.columns(0).search('^' +v,true,false).draw();
    
});
}); 
   
  
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
               
	</body>
        
        
        
        
        
        
</html>
