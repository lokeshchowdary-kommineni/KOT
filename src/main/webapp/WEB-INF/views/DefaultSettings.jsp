<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="account"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>Default Settings-Accounting </title>

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
                <c:when test="${group.moduleName=='Default Setting'}">
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
							<h1>Default Settings</h1>
							
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-5">
								<!-- PAGE CONTENT BEGINS -->
								<account:form class="form-horizontal" role="form" action="defaultsave.html" modelAttribute="defaultForm" method="POST">
                                                                    
                                                                    <c:if  test="${not empty defaultForm.idSetting}">
                                                                    
                                                                    
                                                                    <div class="form-group">
										

										<div class="col-sm-9">
											<account:input type="hidden" path="idSetting" id="form-field-1" placeholder="account id" class="form-control "/>
										</div>
									</div>
                                                                                
                                                                          </c:if>      
                                                                                
									<div class="form-group">
										<label class="col-sm-6 control-label no-padding-right" for="form-field-1">  HSN Code in ITEM Master </label>

										<div class="col-sm-6">
										
                                                                                                                   
                                                                                         
                                                                                                                        <account:select path="categoryItemMaster" id="Box1" tabindex="1" class="form-control" >
                                                                                                                                <form:option value="" label="--------Select-------"/>
                                                                                                                                <c:forEach items="${cCodeList}" var="codeList">
                                                                                                                                <form:option value="${codeList.ccodeId}" label="${codeList.ccode}"/>
																</c:forEach>	
                                                                                                                           </account:select> 
										</div>
                                                                               
									</div>
                                                                    <div class="form-group">
										<label class="col-sm-6 control-label no-padding-right" for="form-field-1">  Unit in ITEM Master </label>

										<div class="col-sm-6">
											 <account:select path="unitItemMaster" tabindex="2" class="form-control" >
                                                                                              <account:option value="" label="--------Select-------"/>                         
                                                                                             <account:options  items="${defaultList}" itemValue="idUnit" itemLabel="unitSymbol"/>
                                                                                                                   
                                                                                         </account:select> 
										</div>
                                                                               
									</div>
                                                                    <div class="form-group">
										<label class="col-sm-6 control-label no-padding-right" for="form-field-1"> Margin in ITEM Master </label>

										<div class="col-sm-6">
											 <account:select path="marginItemMaster" tabindex="3" class="form-control" >
                                                                                                                        <account:option value="" label="--------Select-------"/> 
                                                                                                                        <account:option value="a" label="A"/>
                                                                                                                        <account:option value="b" label="B"/>
                                                                                                                         <account:option value="r" label="R"/>
                                                                                                                   
                                                                                         </account:select> 
										</div>
                                                                               
									</div>
                                                                     <div class="form-group">
										<label class="col-sm-6 control-label no-padding-right" for="form-field-1">Purchase Category </label>

										<div class="col-sm-6">
											 <account:select path="purchaseCategory" tabindex="4" class="form-control" >
                                                                                             <account:option value="" label="--------Select-------"/>                          
                                                                                             <account:option value="ITC Purchase" label="ITC Purchase"/>
                                                                                                                         <account:option value="Purchase from Composite Dealers" label="Purchase from Composite Dealers"/>
                                                                                                                          <account:option value="Purchases from unregistered Persons" label="Purchases from unregistered Persons"/>
                                                                                                                          <account:option value="ITC Purchase of Interstate" label="ITC Purchase of Interstate"/>
                                                                                                                   
                                                                                         </account:select> 
										</div>
                                                                               
									</div>
                                                                     <div class="form-group">
										<label class="col-sm-6 control-label no-padding-right" for="form-field-1">Purchase Mode </label>

										<div class="col-sm-6">
											 <account:select path="purchaseMode" tabindex="5" class="form-control" >
                                                                                                                        <account:option value="" label="--------Select-------"/> 
                                                                                                                        <account:option value="Cash" label="Cash"/>
                                                                                                                         <account:option value="Credit" label="Credit"/>
                                                                                                                          
                                                                                                                   
                                                                                         </account:select> 
										</div>
                                                                               
									</div>
                                                                      <div class="form-group">
										<label class="col-sm-6 control-label no-padding-right" for="form-field-1">Sales Category </label>

										<div class="col-sm-6">
											 <account:select path="salesCategory" tabindex="6" class="form-control" >
                                                                                                                         <account:option value="" label="--------Select-------"/> 
                                                                                                                        <account:option value="Consumers(B2C)" label="Consumers(B2C)"/>
                                                                                                                        <account:option value="GST Dealers(B2B)" label="GST Dealers(B2B)"/>
                                                                                                                        <account:option value="Interstate" label="Interstate"/>
                                                                                                                        <account:option value="Zero Rate Export" label="Zero Rate Export"/>
                                                                                                                        <account:option value="Zero Rate Non Export" label="Zero Rate Non Export"/>
                                                                                                                       
                                                                                                                   
                                                                                         </account:select> 
										</div>
                                                                               
									</div>
                                                                    <div class="form-group">
										<label class="col-sm-6 control-label no-padding-right" for="form-field-1">Sales Mode </label>

										<div class="col-sm-6">
											 <account:select path="salesMode" tabindex="7"  class="form-control" >
                                                                                                                        <account:option value="" label="--------Select-------"/> 
                                                                                                                        <account:option value="Cash" label="Cash"/>
                                                                                                                        <account:option value="Credit" label="Credit"/>
                                                                                                                          
                                                                                                                   
                                                                                         </account:select> 
										</div>
                                                                               
									</div>
                                                                     <div class="form-group">
										<label class="col-sm-6 control-label no-padding-right" for="form-field-1">Reversal of ITC Category </label>

										<div class="col-sm-6">
											 <account:select path="reversalItcCategory" tabindex="8" class="form-control" >
                                                                                                                        <account:option value="" label="--------Select-------"/> 
                                                                                                                       <account:options  items="${itcList}" itemValue="idItc" itemLabel="categoryName"/>
                                                                                                                   
                                                                                         </account:select> 
										</div>
                                                                               
									</div>
                                                                    <div class="form-group">
										<label class="col-sm-6 control-label no-padding-right" for="form-field-1">Reversal of ITC Mode </label>

										<div class="col-sm-6">
											 <account:select path="reversalItcMode" tabindex="9" class="form-control" >
                                                                                                                        <account:option value="" label="--------Select-------"/> 
                                                                                                                        <account:option value="cash" label="Cash"/>
                                                                                                                        <account:option value="debit" label="Debit"/>
                                                                                                                          
                                                                                                                   
                                                                                         </account:select> 
										</div>
                                                                               
									</div>
                                                                    <div class="form-group">
										<label class="col-sm-6 control-label no-padding-right" for="form-field-1">Receipt Mode </label>

										<div class="col-sm-6">
											 <account:select path="receiptMode" tabindex="10" class="form-control" >
                                                                                                                        <account:option value="" label="--------Select-------"/> 
                                                                                                                        <account:option value="cash" label="Cash"/>
                                                                                                                        <account:option value="cheque" label="DD/Cheque/Transfer"/>
                                                                                                                          
                                                                                                                   
                                                                                         </account:select> 
										</div>
                                                                               
									</div>
                                                                    <div class="form-group">
										<label class="col-sm-6 control-label no-padding-right" for="form-field-1">Payment Mode </label>

										<div class="col-sm-6">
											 <account:select path="paymentMode" tabindex="11" class="form-control" >
                                                                                                                        <account:option value="" label="--------Select-------"/> 
                                                                                                                        <account:option value="cash" label="Cash"/>
                                                                                                                        <account:option value="cheque" label="DD/Cheque/Transfer"/>
                                                                                                                          
                                                                                                                   
                                                                                         </account:select> 
										</div>
                                                                               
									</div>
                                                                    
<!--                                                                    <div class="form-group">
										<label class="col-sm-6 control-label no-padding-right" for="form-field-1">Edit Limit(In days)</label>

										<div class="col-sm-6">
											 <account:select path="editLimit" tabindex="12" class="form-control" >
                                                                                                                        <account:option value="" label="--------Select-------"/> 
                                                                                                                        <account:option value="0" label="0"/>
                                                                                                                        <account:option value="1" label="1"/>
                                                                                                                        <account:option value="2" label="2"/>
                                                                                                                        <account:option value="3" label="3"/>
                                                                                                                        <account:option value="4" label="4"/>
                                                                                                                        <account:option value="5" label="5"/>                  
                                                                                                                        <account:option value="6" label="6"/>                      
                                                                                                                        <account:option value="7" label="7"/>
                                                                                                                        <account:option value="8" label="8"/>
                                                                                                                        <account:option value="9" label="9"/>
                                                                                                                        <account:option value="10" label="10"/>
                                                                                                                        <account:option value="11" label="11"/>
                                                                                                                        <account:option value="12" label="12"/>                  
                                                                                                                        <account:option value="13" label="13"/>                           
                                                                                                                        <account:option value="14" label="14"/>
                                                                                                                        <account:option value="15" label="15"/>
                                                                                                                        <account:option value="16" label="16"/>
                                                                                                                        <account:option value="17" label="17"/>
                                                                                                                        <account:option value="18" label="18"/>
                                                                                                                        <account:option value="19" label="19"/>                  
                                                                                                                        <account:option value="20" label="20"/>                          
                                                                                                                        <account:option value="21" label="21"/>
                                                                                                                        <account:option value="22" label="22"/>
                                                                                                                        <account:option value="23" label="23"/>
                                                                                                                        <account:option value="24" label="24"/>
                                                                                                                        <account:option value="25" label="25"/>
                                                                                                                        <account:option value="26" label="26"/>                  
                                                                                                                        <account:option value="27" label="27"/>           
                                                                                                                        <account:option value="28" label="28"/>
                                                                                                                        <account:option value="29" label="29"/>
                                                                                                                        <account:option value="30" label="30"/>
                                                                                                                        <account:option value="31" label="31"/>
                                                                                                                    
                                                                                         </account:select> 
										</div>
                                                                               
									</div>-->
                                                                    
                                                                     <div class="form-group ">
                                                                         <label class="col-sm-6 control-label no-padding-right" for="form-field-1">Sales Return (in Days) </label>
                                                                         <div class="col-sm-6">
                                                                    <account:select path="salesReturn" tabindex="14" class="form-control" required='true' >
                                                                                                <account:option value="" label="--------Select-------"/> 
                                                                                                <account:option value="0" label="0"/>
                                                                                                <account:option value="1" label="1"/>
                                                                                                <account:option value="2" label="2"/>
                                                                                                <account:option value="3" label="3"/>
                                                                                                <account:option value="4" label="4"/>
                                                                                                <account:option value="5" label="5"/>                  
                                                                                                <account:option value="6" label="6"/>                      
                                                                                                <account:option value="7" label="7"/>
                                                                                                <account:option value="8" label="8"/>
                                                                                                <account:option value="9" label="9"/>
                                                                                                <account:option value="10" label="10"/>
                                                                                                <account:option value="11" label="11"/>
                                                                                                <account:option value="12" label="12"/>                  
                                                                                                <account:option value="13" label="13"/>                           
                                                                                                <account:option value="14" label="14"/>
                                                                                                <account:option value="15" label="15"/>
                                                                                                <account:option value="16" label="16"/>
                                                                                                <account:option value="17" label="17"/>
                                                                                                <account:option value="18" label="18"/>
                                                                                                <account:option value="19" label="19"/>                  
                                                                                                <account:option value="20" label="20"/>                          
                                                                                                <account:option value="21" label="21"/>
                                                                                                <account:option value="22" label="22"/>
                                                                                                <account:option value="23" label="23"/>
                                                                                                <account:option value="24" label="24"/>
                                                                                                <account:option value="25" label="25"/>
                                                                                                <account:option value="26" label="26"/>                  
                                                                                                <account:option value="27" label="27"/>           
                                                                                                <account:option value="28" label="28"/>
                                                                                                <account:option value="29" label="29"/>
                                                                                                <account:option value="30" label="30"/>
                                                                                                <account:option value="31" label="31"/>
                                                                  </account:select> 
                                                                </div>
                                                                      
                                                                         
                                                             </div>
                                                             <div class="form-group">
										<label class="col-sm-6 control-label no-padding-right" for="form-field-1">Sales Serial No. <span style="color:red;">*</span> </label>

										<div class="col-sm-6">
											<form:input type="text" path="salesNumber" id="salesVat" class="form-control" required="true"/>
										</div>
                                                                               
									</div>   
                                                                     <div style="text-align:right">
                                                                <input  type="submit" id="btnSubmit" class="btn btn-info" value="Save"/>
                                                                </button>
                                                             
                                                                </div>

<!--									<div class="hr hr-24"></div>-->

								</account:form>

								
							</div><!-- /.col -->
                                                        
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
