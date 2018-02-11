<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>HSN Code -Accounting</title>

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
                <c:when test="${group.moduleName=='Commodity Code'}">
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

							<li class="active">Commodity Code</li>
						</ul> /.breadcrumb 

					
                                            		
					</div>-->

					<div class="page-content">
						
						<div class="page-header">
							<h1>HSN  Code Master</h1>
							<div style="text-align: center; ">
                                                             <span style="color:orangered;" > ${message} </span>
                                                             <span style="color:orangered;" > ${alert} </span>
                                                              </div>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-5">
								<!-- PAGE CONTENT BEGINS -->
                                                                <form:form class="form-horizontal" modelAttribute="codeform" method="post" action="save_ccode.html">
                                                                 
                                                                    <c:if  test="${not empty codeform.ccodeId}"	>
                                                                    <div class="form-group">
										

										<div class="col-sm-9">
                                                                                    <form:input path="ccodeId" type="hidden"  placeholder="Code" class="form-control " />
                                                                                    
										</div>
									</div>
                                                                                    <div class="form-group">
										

										<div class="col-sm-9">
                                                                                    <input  name="hidecode" type="hidden" value="${codeform.ccode}"/>
                                                                                    
										</div>
									</div>
                                                                    </c:if >            
                                                                        <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">HSN Code <span style="color:red;">*</span></label>

										<div class="col-sm-9">
                                                                                    <form:input path="ccode" type="text"  id="Box1" class="form-control"  oninvalid="setCustomValidity('Please Enter 0 to 8 digit Number ')" onchange="try{setCustomValidity('')}catch(e){}" onkeypress="return isNumberKey(event)"  minlength="1" maxlength="8"  required='true' />
                                                                                    <span style="color: red;">${val}</span>
										</div>
									</div>

                                                                        <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Item Category </label>

										<div class="col-sm-9">
											 <form:select path="category" tabindex="2" class="form-control" required='true' id="name" onChange="check()" >
                                                                                                                        <%--<form:option value="" label="-- Select --"/>--%>
                                                                                                                        <form:option value="GST Goods" label="GST Goods"/>
                                                                                                                        
                                                                                                                        <form:option value="Exempted goods" label="Exempted Goods"/>
                                                                                         </form:select> 
										</div>
									</div>
                                                                        
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> GST Rate(%) <span style="color:red;">*</span></label>

										<div class="col-sm-9">
                                                                                    <form:input type="number" step=".01"  oninvalid="setCustomValidity('Only 3 digit Number Allowed with two decimal places ')" onchange="try{setCustomValidity('')}catch(e){}" path="taxRate" tabindex="3"  id="set"  placeholder="Tax rate" class="form-control" onkeyup="doCalc(); igstCalc();"  required="true" min="0" max="1000"  />
										
                                                                                </div>
									</div> 
                                                                         <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> CGST Rate(%) <span style="color:red;">*</span></label>

										<div class="col-sm-9">
                                                                                    <form:input type="number" step=".01" oninvalid="setCustomValidity('Only 3 digit Number Allowed with two decimal places ')" onchange="try{setCustomValidity('')}catch(e){}"   path="cgstRate" tabindex="4"  id="cgst"  class="form-control" onkeyup="doCalc(); igstCalc();"  required="true" min="0" max="1000" />
										
                                                                                </div>
									</div>   
                                                                        <div class="form-group"> 
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> SGST Rate(%) </label>

										<div class="col-sm-9">
                                                                                    <form:input type="number" step=".01" path="sgstRate" tabindex="5"  id="sgst"  class="form-control"  readonly="true" min="0" max="1000" />
										
                                                                                </div>
									</div> 
                                                                        <div class="form-group"> 
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> IGST Rate(%) </label>

										<div class="col-sm-9">
                                                                                    <form:input type="number" step=".01" path="igstRate" tabindex="5"  id="igst"  class="form-control"  readonly="true" min="0" max="1000" />
										
                                                                                </div>
									</div> 


									<div class="clearfix form-actions" style="margin-bottom: -21px !important; ">
                                                                                                    <div class="col-md-offset-3 col-md-9">
                                                                                                        
                                                                                                        <input type="submit" id="btnSubmit"  class="btn btn-info" value="Save"/>

                                                                                                         &nbsp; &nbsp; &nbsp;
                                                                                                         <input type="reset" class="btn" value="Reset" onClick="window.location.reload()">
                                                                                                           
                                                                                                    </div>
                                                                        </div>

									<div class="hr hr-24"></div>

								</form:form>

								
							</div><!-- /.col -->
                                                        <div class="col-xs-7">
										
										<div class="table-header">
											HSN Code List 
										</div>

										<!-- div.table-responsive -->

										<!-- div.dataTables_borderWrap -->
										<div>
											<table id="dynamic-table" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														
														
														<th>HSN Code</th>
                                                                                                                <th>Item Category </th>
                                                                                                                <th>GST Rate(%) </th>
                                                                                                                <th> Edit </th>
                                                                                                                 <th> Delete </th>

														
													</tr>
												</thead>

												<tbody>
                                                                                                    <c:forEach items="${codeList}" var="code">
													<tr>
														

														
														<td>${code.ccode}</td>
														
														<td>${code.category}</td>
                                                                                                                <td>${code.taxRate}</td>

														

														<td>
															<div class="hidden-sm hidden-xs action-buttons">
																

																<a class="green" href="CcodeEdit.html?CodeID=${code.ccodeId}" title="Edit">
																	<i class="ace-icon fa fa-pencil bigger-130"></i>
																</a>

																
															</div>

															
														</td>
                                                                                                                <td>
                                                                                                                    <div class="hidden-sm hidden-xs action-buttons">
                                                                                                                    <a class="red" href="CcodeDelete.html?CodeID=${code.ccodeId}" title="Delete">
																	<i class="ace-icon fa fa-trash-o bigger-130" ></i>
																</a>
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
                   jQuery.extend(jQuery.expr[':'], {
    focusable: function (el, index, selector) {
        return $(el).is('a, button, :input, [tabindex]');
    }
});
 function isNumberKey(evt)
       {
          var charCode = (evt.which) ? evt.which : evt.keyCode;
          if (charCode != 46 && charCode > 31 
            && (charCode < 48 || charCode > 57))
             return false;
         
         var keycode = evt.charCode || evt.keyCode;
  if (keycode == 46) {
    return false;
  }

          return true;
          
       }
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
                    function doCalc() 
{ 
    var taxRate=document.getElementById('set').value;
   
    var cgstRate=document.getElementById('cgst').value;
   
    var sgstRate=document.getElementById('sgst').value=taxRate - cgstRate;
   
} 
//// code for calculating IGST -- Abi
 function igstCalc() 
{ 
    
   
    var cgstRate=document.getElementById('cgst').value;
    
    var sgstRate=document.getElementById('sgst').value;
    
    
   var total= document.getElementById('igst').value=parseFloat(cgstRate) + parseFloat(sgstRate);
   
  
   
} 
                    
                </script>
               
                <script>
                 function check() {
                     
        var s = document.getElementById("name");
        var selectedvalue = s.options[s.selectedIndex].value;
      
        
        
        if(selectedvalue == "Exempted goods")
        {
     
   var d= document.getElementById("set");
   d.value = 0;
   d.setAttribute('readonly', 'readonly');
   
   var cgstRate=document.getElementById('cgst');
   cgstRate.value = 0;
   cgstRate.setAttribute('readonly', 'readonly');
   
   var sgstRate=document.getElementById('sgst');
   sgstRate.value = 0;
   sgstRate.setAttribute('readonly', 'readonly');
         }
         else{
            
            var d= document.getElementById("set").readOnly=false;
            var cgstRate=document.getElementById('cgst').readOnly=false;
            var sgstRate=document.getElementById('sgst').readOnly=true;
            
         }
                 }
                 
              
        
        $("#set").on("keypress keyup",function(){
    if($(this).val() == '0'){
      $(this).val('');  
    }
});
                
                </script>
                <script>
                    var s = document.getElementById("name");
        var selectedvalue = s.options[s.selectedIndex].value;
      
        
        
        if(selectedvalue == "Exempted goods")
        {
     
   var d= document.getElementById("set");
   d.value = 0;
   d.setAttribute('readonly', 'readonly');
   
   var cgstRate=document.getElementById('cgst');
   cgstRate.value = 0;
   cgstRate.setAttribute('readonly', 'readonly');
   
         }
         
         
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
