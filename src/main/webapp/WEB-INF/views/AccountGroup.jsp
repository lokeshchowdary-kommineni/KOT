<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="account"%>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>AccountGroup- Accounting</title>

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
                <c:when test="${group.moduleName=='Accounts Groups'}">
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

							
							<li class="active">Account Group </li>
						</ul> /.breadcrumb 

					
                                            		
					</div>-->

					<div class="page-content">
						
						<div class="page-header">
							<h1>Account Group Master</h1>
                                                        <div style="text-align: center; ">
                                                             <span style="color:orangered;" > ${message} </span>
                                                              </div>
							
						</div>

						<div class="row">
							<div class="col-xs-5">
								<!-- PAGE CONTENT BEGINS -->
								<account:form class="form-horizontal" role="form" action="accountsave.html" modelAttribute="accountForm" method="POST">
                                                                    
                                                                    <c:if  test="${not empty accountForm.idAccount}"	>
                                                                    
                                                                    
                                                                    <div class="form-group">
										

										<div class="col-sm-9">
											<account:input type="hidden" path="idAccount" id="form-field-1" placeholder="account id" class="form-control " readonly="true"/>
                                                                                        <input type="hidden" name="accGroupName" value="${accountForm.accountGroupName}" path="accountGroupName" />
										</div>
									</div>
                                                                                
                                                                          </c:if>      
                                                                                
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Accounts Group Name <span style="color:red;">*</span></label>

										<div class="col-sm-9">
											<account:input type="text" path="accountGroupName" id="Box1" tabindex="1" pattern="[a-zA-Z /-&_() . \s]+" oninvalid="setCustomValidity('Please enter only Alphabets ')" onchange="try{setCustomValidity('')}catch(e){}" minlength="1" maxlength="50" placeholder="Accountgroup Name" class="form-control " required="true"/>
                                                                                        <span class="red"><account:errors path="accountGroupName"  /></span>
                                                                                        <span class="red">${val}</span>
                                                                                      
										</div>
									</div>
                                                                                        <c:choose>
                                                                                            <c:when test="${not empty accountForm.idAccount && accountForm.predefined=='0'}"  >
                                                                        <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">  Under Account Group</label>
                                                                                <account:hidden path="underGroup" value="${accountForm.underGroup}"></account:hidden>
										<div class="col-sm-9">            
                                                                                    <select name="ug" tabindex="2" class="form-control" id="choose" onChange="ch()" disabled="true" >
                                                                                                                       
                                                                                                                     
                                                                                                                        <c:forEach items="${accountList}" var="account">
                                                                                                                            <c:if test="${account.idAccount == accountForm.underGroup}">
                                                                                                                                <option  ${account.idAccount == accountForm.underGroup?'selected':''}>${account.accountGroupName}</option>
                                                                                                                            </c:if>
                                                                                                                        </c:forEach>
                                                                                                                     
                                                                                         </select> 
										</div>
									</div>
                                                                                                                </c:when>
                                                                                            <c:when test="${not empty accountForm.idAccount && accountForm.predefined=='1'}">
                                                                                                <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">  Under Account Group</label>
                                                                             
										<div class="col-sm-9">            
                                                                                     <account:select path="underGroup" tabindex="2" class="form-control" id="choose" onChange="ch()" >
                                                                                                                        <account:option value="0" label="PRIMARY" />
                                                                                                                     <account:options  items="${accountList}" itemValue="idAccount" itemLabel="accountGroupName"/>
                                                                                                                     
                                                                                         </account:select> 
										</div>
									</div>
                                                                                                
                                                                                            </c:when>
                                                                                            
                                                                                        </c:choose>
                                                                         
                                                                                        <c:if test="${accountForm.idAccount==null}" >
                                                                         <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">  Under Account Group</label>

										<div class="col-sm-9">
											 <account:select path="underGroup" tabindex="2" class="form-control" id="choose" onChange="ch()" >
                                                                                                                        <account:option value="0" label="PRIMARY" />
                                                                                                                     <account:options  items="${accountList}" itemValue="idAccount" itemLabel="accountGroupName"/>
                                                                                                                     
                                                                                         </account:select> 
										</div>
									</div>
                                                                         </c:if>
                                                                                          <c:choose>
                                                                                            <c:when test="${not empty accountForm.idAccount && accountForm.predefined=='0'}"  >
                                                                        <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Nature of Group </label>
                                                                                <account:hidden path="natureOfGroup" value="${accountForm.natureOfGroup}"></account:hidden>
										<div class="col-sm-9">            
                                                                                    <select  class="form-control" id="nat" disabled="true" >
                                                                                                               
                                                                                        <option value="Liabilities" ${'Liabilities' == accountForm.natureOfGroup?'selected':''}>Liabilities</option>
                                                                                        <option value="Assets"  ${'Assets' == accountForm.natureOfGroup?'selected':''}>Assets</option>
                                                                                        <option value="Expenses" ${'Expenses' == accountForm.natureOfGroup?'selected':''}>Expenses</option>
                                                                                        <option value="Incomes" ${'Incomes' == accountForm.natureOfGroup?'selected':''}>Incomes</option>
                                                                                                                     
                                                                                                                     
                                                                                         </select> 
										</div>
									</div>
                                                                                                                </c:when>
                                                                                            <c:when test="${not empty accountForm.idAccount && accountForm.predefined=='1'}">
                                                                                                 <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Nature of Group </label>
                                                                                <account:hidden path="natureOfGroup" value="${accountForm.natureOfGroup}"></account:hidden>
										<div class="col-sm-9">            
                                                                                    <select  class="form-control" id="nat"  >
                                                                                                               
                                                                                        <option value="Liabilities" ${'Liabilities' == accountForm.natureOfGroup?'selected':''}>Liabilities</option>
                                                                                        <option value="Assets"  ${'Assets' == accountForm.natureOfGroup?'selected':''}>Assets</option>
                                                                                        <option value="Expenses" ${'Expenses' == accountForm.natureOfGroup?'selected':''}>Expenses</option>
                                                                                        <option value="Incomes" ${'Incomes' == accountForm.natureOfGroup?'selected':''}>Incomes</option>
                                                                                                                     
                                                                                                                     
                                                                                         </select> 
										</div>
									</div>
                                                                                                
                                                                                            </c:when>
                                                                                            
                                                                                        </c:choose>
                                                                                        <c:if test="${accountForm.idAccount==null}" >
                                                                                            
                                                                                            <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Nature of Group </label>

										<div class="col-sm-9">
											 <account:select path="natureOfGroup" class="form-control" id="nat"  >
                                                                                               <account:option value="" label="--Please Select--"/>
                                                                                                                        <account:option value="Liabilities" label="Liabilities"/>
                                                                                                                        <account:option value="Assets" label="Assets"/>
                                                                                                                        <account:option value="Expenses" label="Expenses"/>
                                                                                                                        <account:option value="Incomes" label="Incomes"/>
                                                                                                                     
                                                                                         </account:select> 
										</div>
									</div>
                                                                                        </c:if>
                                                                                
                                                                                
                                                                                

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
											Account Group List
										</div>

										<!-- div.table-responsive -->

										<!-- div.dataTables_borderWrap -->
										<div>
											<table id="dynamic-table" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														
														
														<th>Account Group Name</th>
                                                                                                                <th>Under Account Group </th>
                                                                                                                <th>Nature of Group</th>
                                                                                                                <th>Edit</th>
                                                                                                                <th>Delete</th>

														
													</tr>
												</thead>

												<tbody>
                                                                                                      <c:forEach items="${accountList}" var="al">
													<tr  <c:choose><c:when test="${al.predefined =='0'}">style="background-color: lightgoldenrodyellow;"</c:when> <c:otherwise>style="background-color: pink;"</c:otherwise></c:choose> >
														
                                                                                                                

														<td>${al.accountGroupName}</td>                                                                                                              
                                                                                                                <c:choose>    
                                                                                                                <c:when test="${al.underGroup==0}">
                                                                                                                    <td>PRIMARY</td>
                                                                                                                </c:when>  
                                                                                                                <c:otherwise>
                                                                                                                    <c:forEach items="${accountList}" var="a2">                                                                                                                 
                                                                                                                    
                                                                                                                    <c:if test="${a2.idAccount eq al.underGroup }">
                                                                                                                         <td>${a2.accountGroupName}</td>
                                                                                                                    </c:if>
                                                                                                                  
                                                                                                                </c:forEach>
                                                                                                                </c:otherwise>
                                                                                                                 </c:choose>    
                                                                                                                
                                                                                                                <td>${al.natureOfGroup}</td>
														
                                                                                                                    <c:choose>
                                                                                                                        <c:when test="${al.predefined ==0}">
                                                                                                                          <td>  
                                                                                                                         <div class="hidden-sm hidden-xs action-buttons">
																

																<a class="green" href="accountEdit.html?eid=${al.idAccount}" title="Edit">
																	<i class="ace-icon fa fa-pencil bigger-130"></i>
																</a>

																
															</div>
                                                                                                                          </td>
                                                                                                                           <td></td>

                                                                                                                        </c:when>
                                                                                                                           
                                                                                                                            
                                                                                                                        <c:otherwise>
																
                                                                                                                            <td>
																<div class="hidden-sm hidden-xs action-buttons">
																

																<a class="green" href="accountEdit.html?eid=${al.idAccount}" title="Edit">
																	<i class="ace-icon fa fa-pencil bigger-130"></i>
																</a>

																
															         </div>
                                                                                                                            </td>
                                                                                                                            <td>

																 <div class="hidden-sm hidden-xs action-buttons">
                                                                                                                    <a class="red" href="accountDelete.html?did=${al.idAccount}" title="Delete">
																	<i class="ace-icon fa fa-trash-o bigger-130" ></i>
																</a>
                                                                                                                    </div>
                                                                                                                            </td>
															</c:otherwise>  
                                                                                                                            
                                                                                                                       
                                                                                                                    </c:choose>
															
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
                   jQuery.extend(jQuery.expr[':'], {
    focusable: function (el, index, selector) {
        return $(el).is('a, button, :input, [tabindex]');
    }
});

$(document).on('keypress', ':focusable', function (e) {
    if (e.which == 13) {
        e.preventDefault();
        // Get all focusable elements on the page
        var inputs =   $(this).parents("form").eq(0).find(":input:visible:not(disabled):not([readonly])"),
            idx = inputs.index(this);

        if (idx == inputs.length - 1) {
            inputs[0].select()
        } else {
            inputs[idx + 1].focus(); // handles submit buttons
            inputs[idx + 1].select();
        }
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
                <script>
                   function ch()
                   {
                       var e = document.getElementById("choose");    
                       var selectedText = e.options[e.selectedIndex].value; 
                       if(selectedText == "0")
                       {
                           document.getElementById("nat").disabled=false;
                       
                       }
                       else{
                           document.getElementById("nat").disabled=true; 
                          
                       }
                   } 
                </script>
	</body>
</html>
