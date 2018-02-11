<%-- 
    Document   : ItemMaster
    Created on : Feb 22, 2017, 3:09:10 PM
    Author     : MR
--%>
   
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>Item Quick Edit-Accounting </title>

		<meta name="description" content="Common form elements and layouts" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/font-awesome/4.5.0/css/font-awesome.min.css" />

		<!-- page specific plugin styles -->
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
<style>
h1 { 
    display: block;
    font-size: 1.2em;
    margin-top: 0.67em;
    margin-bottom: 0.67em;
    margin-left: 0;
    margin-right: 0;
    font-weight: bold;
}
input.wide { width:100% !important ;}
</style>
<style>
                    table
                    {
                        border-collapse: collapse !important; /* 'cellspacing' equivalent */
                    }

                    table td
                    {
                        padding: 0 !important; /* 'cellpadding' equivalent */
                   
                    }
                    table th
                    {
                        text-align: center !important;
                    }
                </style>
	</head>

	<body class="no-skin" >
		
		 
                 <c:set var="listLength" value="${fn:length(listGroup)}"/>
        <c:forEach items="${listGroup}" var="group">
            <c:choose>
                <c:when test="${group.moduleName=='ItemMaster'}">
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
							<h1>Item Quick Edit</h1>
							
						</div>
						

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<form:form class="form-horizontal" action="SaveItemQuick.html" method="POST" modelAttribute="itemForm">

                                                                    <form:hidden path="id"/>
                                                                    <form:hidden path="currentStock"/>
                                                                     <form:hidden path="cp" />
                                                                     
<!--                                                                     <input type="text" data-column="1" class="search-input-text" placeholder="Search Item Name">
                                                                     <input type="text" data-column="2" class="search-input-text" placeholder="Search Item Code">-->
<!--									<div class="hr hr-24"></div>-->

<!--									<div class="row">
										<div class="col-xs-12 col-sm-12">
											<div class="widget-box lighter  widget-color-blue2">
												<div class="widget-header">
													<h5 class="widget-title">Item Details</h5>
												</div>

												<div class="widget-body">
													<div class="widget-main">
													<div class="row">
														<div class="col-md-2">
															<label for="form-field-mask-1">Item Code <span style="color:red;">*</span></label>

															<div class="input-group">
                                                                                                                            <form:input path="itemCode" class="form-control" type="text" id="form-field-mask-1" required="true"/>
                                                                                                                                <form:errors path="itemCode" style="color:red;"/>
                                                                                                                        </div>
														</div>

														
														<div class="col-md-3">
															<label for="form-field-mask-2">Item Name <span style="color:red;">*</span></label>

															<div class="input-group col-md-12">
																<form:input path="itemName" class="form-control" type="text" id="form-field-mask-2" required="true"/>
                                                                                                                                <form:errors path="itemName" style="color:red;"/>
															</div>
														</div>
														
														<div class="col-md-2">
															<label for="form-field-mask-2">Item Group</label>

															<div class="input-group">
                                                                                                                            <form:select path="itemGroup" class="form-control" id="form-field-select-1" required="true">
                                                                                                                                <form:option value="" label="--------Select-------"/>
                                                                                                                                <c:forEach items="${itemGroupList}" var="itemGroup">
                                                                                                                                <form:option value="${itemGroup.idItem}" label="${itemGroup.itemGroupName}"/>
																</c:forEach>	
                                                                                                                            </form:select>
                                                                                                                            <form:errors path="itemGroup"/>
                                                                                                                        </div>
														</div>
														
														<div class="col-md-2">
															<label for="form-field-mask-2"> HSN Code <span style="color:red;">*</span></label>

															<div class="input-group">
                                                                                                                            <form:select path="comCode" id="comCode" class="form-control"  required="true" onChange="onChangeComCode()">
                                                                                                                                <form:option value="" label="--------Select-------"/>
																<c:forEach items="${cCodeList}" var="codeList">
                                                                                                                                <form:option value="${codeList.ccodeId}" label="${codeList.ccode}"/>
																</c:forEach>
                                                                                                                            </form:select>
                                                                                                                            <form:errors path="comCode" style="color:red;"/>
															</div>
														</div>
														
														<div class="col-md-3">
															<label for="form-field-mask-3">Category</label>

															<div class="input-group col-md-12">
																<form:input path="category" id="category"  class="form-control " type="text" readonly="true"/>
                                                                                                                                <form:errors path="category"/>
                                                                                                                        </div>
														</div>

														
														<div>
															
														</div>
														</div>
													</div>
												</div>
											</div>
										</div> /.span 
									</div>-->
										
										
										
<!--									<div class="row">
										<div class="col-xs-12 col-sm-6">
											<div class="widget-box lighter  widget-color-blue2">
												<div class="widget-header">
													<h5 class="widget-title">Unit Details</h5>
												</div>

												<div class="widget-body">
													<div class="widget-main">
													<div class="row">
														<div class="col-md-4">
															<label for="form-field-mask-2">Unit <span style="color:red;">*</span></label>

															<div class="input-group col-md-12">
																<form:select path="unit" class="form-control" required="true" id="unit" >
                                                                                                                                            <c:forEach items="${unitList}" var="unitList">
                                                                                                                                            <form:option value="${unitList.idUnit}" label="${unitList.unitSymbol}"/>
                                                                                                                                            </c:forEach>
                                                                                                                                </form:select>
                                                                                                                                <form:errors path="unit" style="color:red;"/>
															</div>
														</div>
														
														<div class="col-md-4">
															<label for="form-field-mask-2">Alt.Unit</label>

															<div class="input-group">
																<form:select path="altUnit"  class="form-control" id="altUnit" onChange="onChangeAltUnit()">
																	 <form:option value="" label="--------Select-------"/>
                                                                                                                                            <c:forEach items="${unitList}" var="unitList">
                                                                                                                                            <form:option value="${unitList.idUnit}" label="${unitList.unitSymbol}"/>
                                                                                                                                            </c:forEach>
                                                                                                                                </form:select>
                                                                                                                                
                                                                                                                                <form:errors path="altUnit"/>
                                                                                                                                <span id="altUnitError" style="color:red;"></span>
                                                                                                                        </div>
														</div>
														
														<div class="col-md-4">
															<label for="form-field-mask-3">Where Alt.Unit=1 Unit</label>

															<div class="input-group col-md-12">
																<form:input path="totalUnit" class="form-control" type="text" id="whereAltUnit" readonly="true" onkeyup="onChangeBasicPrice()"/>
                                                                                                                                <form:errors path="totalUnit"/>
															</div>
														</div>

														
														<div>
															
														</div>
														</div>
													</div>
												</div>
											</div>
										</div> /.span 
										
										<div class="col-xs-12 col-sm-6">
											<div class="widget-box lighter  widget-color-blue2">
												<div class="widget-header">
													
                                                                                                    <form:hidden path="stockDate" class="form-control " id="form-field-mask-2" />
                                                                                               
                                                                                                    <h5 class="widget-title">Total Opening Stock value of all items as on :</h5> <span style="color:yellow"><b><fmt:formatDate value="${itemForm.stockDate}" pattern="dd/MM/yyyy"  /></b></span> &nbsp;&nbsp; <h6 class="widget-title" >Total Amount: </h6><span style="color:yellow"><b><c:choose><c:when test="${sumOfStockValue==null || sumOfStockValue == 'null'}">0</c:when><c:otherwise>${sumOfStockValue}</c:otherwise></c:choose></b></span>
												
                                                                                                    <form:hidden path="stockDate"/>
													
												
												</div>

												<div class="widget-body">
													<div class="widget-main">
													<div class="row">
														<div class="col-md-1">
															<label for="form-field-mask-1">Bits</label>

															<div class="input-group">
                                                                                                                            <form:checkbox path="bit_item" />
                                                                                                                                <form:errors path="bit_item"/>
															</div>
														</div>

														
														<div class="col-md-3">
															<label for="form-field-mask-2">ROL</label>

															<div class="input-group col-md-12">
																<form:input path="rol" class="form-control" type="text" id="form-field-mask-2"/>
                                                                                                                                <form:errors path="rol"/>
                                                                                                                        </div>
														</div>
														
														<div class="col-md-4">
															<label for="form-field-mask-2">Basic Price <span style="color:red;">*</span></label>

															<div class="input-group">
																<form:input path="basicPrice" class="form-control" type="text" id="basicPrice" required="true" onkeyup="onChangeBasicPrice()"/>
                                                                                                                                <form:errors path="basicPrice" style="color:red;"/>
															</div>
                                                                                                                        
														</div>
														
														<div class="col-md-4">
															<label for="form-field-mask-2">(Basic + GST) Price </label>

															<div class="input-group">
                                                                                                                            <form:input path="basicVatPrice" class="form-control" type="text" id="basicVatPrice"  onchange="onChangeBasicPriceVat()"/>
                                                                                                                                <form:errors path="basicVatPrice"/>
															</div>
														</div>
														

														
														<div>
															
														</div>
														</div>
													</div>
												</div>
											</div>
										</div> /.span 
									</div> /.row -->

									
<!--									<div class="row">
									<div class="col-xs-12 col-sm-12">
									
									<div class="row">
									
										<div class="col-md-6">
										
											<label class="col-sm-6 control-label" for="form-field-mask-2">Opening Stock details as on</label>

											<div class="input-group col-sm-6">
												
											</div>
										</div>
									</div>
									</div>
									</div>-->
									
									<div class="row">
										<div class="col-xs-12 col-sm-6">
<!--											<div class="widget-box  widget-color-blue2">
												<div class="widget-header">
                                                                                                    <form:hidden path="stockDate" class="form-control " id="form-field-mask-2" />
                                                                                               
                                                                                                    <h5 class="widget-title">Opening Stock details as on : <span style="color:yellow"><b><fmt:formatDate value="${itemForm.stockDate}" pattern="dd/MM/yyyy"/></b></span></h5>
												
                                                                                                    <form:hidden path="stockDate"/>
													
												</div>

												<div class="widget-body">
													<div class="widget-main">
                                                                                          
													<div class="row">
														<div class="col-md-4">
															<label for="form-field-mask-2">Stock</label>

															<div class="input-group col-md-12">
                                                                                                                            <form:input path="openingStock" class="form-control" type="text" id="stock" onkeyup="onChangeStock()"/>
                                                                                                                                <form:errors path="openingStock"/>
															</div>
														</div>
														
														<div class="col-md-3">
															<label for="form-field-mask-2">Rate</label>

															<div class="input-group">
                                                                                                                            <form:input path="rate" class="form-control" type="text" id="rate" readonly="true" onkeyup="onChangeStock()"/>
                                                                                                                                <form:errors path="rate"/>
															</div>
														</div>
														
														<div class="col-md-5">
															<label for="form-field-mask-3">Stock Value</label>

															<div class="input-group col-md-12">
																<form:input path="stockValue" class="form-control" type="text" id="stockValue" readonly="true"/>
                                                                                                                                <form:errors path="stockValue"/>
															</div>
														</div>

														
														<div>
															
														</div>
														</div>
													</div>
												</div>
											</div>-->
											
											<div class="widget-box  widget-color-blue2">
												

												<div class="widget-body">
													<div class="widget-main alert-success">
                                                                                                            <div class="row">
													
														<!-- Accounting LA starts-->
<!--														<div class="col-md-2">
															<label for="form-field-mask-2">LR%</label>

															<div class="input-group col-md-12">
																<form:input path="lr"  class="form-control" type="text"  id="lr" onkeyup="onChangeBasicPrice()"/>
                                                                                                                                <form:errors path="lr"/>
															</div>
														</div>-->
														
<!--														<div class="col-md-2">
															<label for="form-field-mask-2">LA</label>

															<div class="input-group">
                                                                                                                            <form:input path="la" class="form-control" type="text"  id="la" readonly="true"/>
                                                                                                                                <form:errors path="la"/>
															</div>
														</div>-->
														
														<div class="col-md-4">
															<label for="form-field-mask-3">LP</label>

															<div class="input-group col-md-12">
																<form:input path="lp" class="form-control" type="text"  id="lpItem" readonly="true"/>
                                                                                                                                <form:errors path="lp"/>
															</div>
														</div>
                                                                                                                        
                                                                                                                        <div class="col-md-4">
															<label for="form-field-mask-3">EP</label>

															<div class="input-group col-md-12">
																<form:input path="ep" class="form-control" type="text"  id="epItem" readonly="true"/>
                                                                                                                                <form:errors path="ep"/>
															</div>
														</div>
                                                                                                                        
                                                                                                                        <div class="col-md-4">
															<label for="form-field-mask-3">TP</label>

															<div class="input-group col-md-12">
																<form:input path="tp" class="form-control" type="text"  id="tpItem" readonly="true"/>
                                                                                                                                <form:errors path="tp"/>
															</div>
														</div>
														
<!--														<div class="col-md-4">
															<label for="form-field-mask-3">LP(Alt)</label>

															<div class="input-group col-md-12">
																<form:input path="lpAlt" class="form-control" type="text"  id="lpAlt" readonly="true"/>
                                                                                                                                <form:errors path="lpAlt"/>
															</div>
														</div>-->
														</div>
                                                                                                            
                                                                                                            
                                                                                                            
													
														<div class="row">
                                                                                                                    
                                                                                                                    <div class="space-4"></div>
<!--														<div class="col-md-4">
															<label for="form-field-mask-2">VB%</label>

															<div class="input-group col-md-12">
																<form:input path="vb" class="form-control" type="text"  id="vb" onkeyup="onChangeBasicPrice()"/>
                                                                                                                                <form:errors path="vb"/>
															</div>
														</div>-->
														
<!--														<div class="col-md-3">
                                                                                                                    <label for="form-field-mask-2">VBP <form:checkbox path="vbpCheckbox" id="vbpCheckbox" onChange="onChangeVBPCheckBox()"/></label>

															<div class="input-group">
																<form:input path="vbp" class="form-control" type="text"  id="vbp" readonly="true" onkeyup="onChangeVBP()"/>
                                                                                                                                <form:errors path="vbp"/>
															</div>
														</div>-->
														
<!--														<div class="col-md-5">
															<label for="form-field-mask-3">VBP(Alt)</label>

															<div class="input-group col-md-12">
																<form:input path="vbpAlt" class="form-control" type="text"  id="vbpAlt" readonly="true"/>
                                                                                                                                <form:errors path="vbpAlt"/>
															</div>
														</div>-->

														
														<div>
															
														</div>
														</div>
													</div>
												</div>
                                                                                        </div>
<!--                                                                                        <div class="widget-box  widget-color-blue2">
                                                                                                <div class="widget-body">
                                                                                                    <div class="widget-main btn-light">
                                                                                                                <div class="row">
													
														 Accounting LA starts
														<div class="col-md-3">
															<label class="col-sm-3 control-label">MC%</label>

                                                                                                                        <div class="col-md-9">
                                                                                                                            <form:input path="mc" class="form-control" type="text"  id="mc" onkeyup="onChangeMcMca()"/>
                                                                                                                                <form:errors path="mc"/>
                                                                                                                        </div>
														</div>
														
														<div class="col-md-3" style="float: right;">
                                                                                                                    <label class="col-sm-3 control-label">MCA</label>

                                                                                                                            <div class="col-md-9">
                                                                                                                            <form:input path="mca" class="form-control" type="text"  id="mca" onkeyup="onChangeMcMca()"/>
                                                                                                                            <form:errors path="mca"/>
                                                                                                                            </div>
                                                                                                                    </div>
														</div>
													</div>
                                                                                                    <div class="col-xs-12 col-sm-12">
                                                                                                        <div class="col-md-3">
                                                                                                                <label for="form-field-mask-2">MC%</label>

                                                                                                                <div class="input-group col-md-12">
                                                                                                                        <form:input path="mc" class="form-control" type="text" id="form-field-mask-2"/>
                                                                                                                        <form:errors path="mc"/>
                                                                                                                </div>
                                                                                                        </div>

                                                                                                        <div class="col-md-3" style="float: right;">
                                                                                                                <label for="form-field-mask-2">MCA</label>

                                                                                                                <div class="input-group">
                                                                                                                <form:input path="mca" class="form-control" type="text" id="form-field-mask-2"/>
                                                                                                                <form:errors path="mca"/>
                                                                                                                </div>
                                                                                                        </div>
                                                                                                    </div>
												</div>
											</div>-->
											
											
											
											
										</div>
										<div class="col-xs-12 col-sm-6">
                                                                                    
                                                                                    <div class="widget-box  widget-color-blue2">
<!--												<div class="widget-header">
													<h5 class="widget-title">Sale Price for GST Dealers</h5>
												</div>-->

												<div class="widget-body">
													<div class="widget-main alert-danger">
                                                                                                            <div class="row">
<!--														<div class="col-md-4">
															<label for="form-field-mask-2">VA%</label>

															<div class="input-group col-md-12">
																<form:input path="va" class="form-control" type="text"  id="va" onkeyup="onChangeBasicPrice()"/>
                                                                                                                                <form:errors path="va"/>
															</div>
														</div>-->
														
														<div class="col-md-3">
                                                                                                                    <label for="form-field-mask-2">VAP </label>

															<div class="input-group">
																<form:input path="vap" class="form-control" type="text"  id="vapItem" readonly="true" onkeyup="onChangeVAP()"/>
                                                                                                                                <form:errors path="vap"/>
															</div>
														</div>
														<div class="col-md-3">
                                                                                                                    <label for="form-field-mask-2">VBP </label>

															<div class="input-group">
																<form:input path="vbp" class="form-control" type="text"  id="vbpItem" readonly="true" onkeyup="onChangeVBP()"/>
                                                                                                                                <form:errors path="vbp"/>
															</div>
														</div>
                                                                                                                        <div class="col-md-3">
                                                                                                                    <label for="form-field-mask-2">CAP </label>

															<div class="input-group">
																<form:input path="cap" class="form-control" type="text"  id="capItem" readonly="true" onkeyup="onChangeCAP()"/>
                                                                                                                                <form:errors path="cap"/>
															</div>
														</div>
                                                                                                                        <div class="col-md-3">
                                                                                                                    <label for="form-field-mask-2">CBP </label>

															<div class="input-group">
																<form:input path="cbp" class="form-control" type="text"  id="cbpItem" readonly="true" onkeyup="onChangeCBP()"/>
                                                                                                                                <form:errors path="cbp"/>
															</div>
														</div>
<!--														<div class="col-md-5">
															<label for="form-field-mask-3">VAP(Alt)</label>

															<div class="input-group col-md-12">
																<form:input path="vapAlt" class="form-control" type="text"  id="vapAlt" readonly="true"/>
                                                                                                                                <form:errors path="vapAlt"/>
															</div>
														</div>-->
														</div>
													<div class="row">
<!--														<div class="col-md-4">
															<label for="form-field-mask-2">CA%</label>

															<div class="input-group col-md-12">
																<form:input path="ca" class="form-control" type="text"  id="ca" onkeyup="onChangeBasicPrice()"/>
                                                                                                                                <form:errors path="ca"/>
															</div>
														</div>-->
														
<!--														<div class="col-md-3">
                                                                                                                    <label for="form-field-mask-2">CAP <form:checkbox path="capCheckbox" id="capCheckbox" onChange="onChangeCAPCheckBox()"/></label>

															<div class="input-group">
																<form:input path="cap" class="form-control" type="text"  id="cap" readonly="true" onkeyup="onChangeCAP()"/>
                                                                                                                                <form:errors path="cap"/>
															</div>
														</div>-->
                                                                                                                 <div class="col-md-3">
															<label for="form-field-mask-3">VAP(Alt)</label>

															<div class="input-group col-md-12">
																<form:input path="vapAlt" class="form-control" type="text"  id="vapAlt" readonly="true"/>
                                                                                                                                <form:errors path="vapAlt"/>
															</div>
														</div>       
														<div class="col-md-3">
															<label for="form-field-mask-3">VBP(Alt)</label>

															<div class="input-group col-md-12">
																<form:input path="vbpAlt" class="form-control" type="text"  id="vbpAlt" readonly="true"/>
                                                                                                                                <form:errors path="vbpAlt"/>
															</div>
														</div>
														<div class="col-md-3">
															<label for="form-field-mask-3">CAP(Alt)</label>

															<div class="input-group col-md-12">
																<form:input path="capAlt" class="form-control" type="text"  id="capAlt" readonly="true"/>
                                                                                                                                <form:errors path="capAlt"/>
															</div>
														</div>
                                                                                                                        <div class="col-md-3">
															<label for="form-field-mask-3">CBP(Alt)</label>

															<div class="input-group col-md-12">
																<form:input path="cbpAtl" class="form-control" type="text"  id="cbpAlt" readonly="true"/>                                                                                                                               
                                                                                                                                <form:errors path="cbpAtl"/>
															</div>
														</div>
														</div>
														<div class="row">
                                                                                                                    <div class="space-4"></div>
                                                                                                                    <div class="space-4"></div>
<!--														<div class="col-md-4">
															<label for="form-field-mask-2">CB%</label>

															<div class="input-group col-md-12">
																<form:input path="cb" class="form-control" type="text"  id="cb" onkeyup="onChangeBasicPrice()"/>
                                                                                                                                <form:errors path="cb"/>
															</div>
														</div>-->
														
<!--														<div class="col-md-3">
                                                                                                                    <label for="form-field-mask-2">CBP <form:checkbox path="cbpCheckbox" id="cbpCheckbox" onChange="onChangeCBPCheckBox()"/></label>

															<div class="input-group">
																<form:input path="cbp" class="form-control" type="text"  id="cbp" readonly="true" onkeyup="onChangeCBP()"/>
                                                                                                                                <form:errors path="cbp"/>
															</div>
														</div>-->
														
<!--														<div class="col-md-5">
															<label for="form-field-mask-3">CBP(Alt)</label>

															<div class="input-group col-md-12">
																<form:input path="cbpAtl" class="form-control" type="text"  id="cbpAlt" readonly="true"/>                                                                                                                               
                                                                                                                                <form:errors path="cbpAtl"/>
															</div>
														</div>-->

														
														<div>
															
														</div>
														</div>
													</div>
												</div>
											</div>
                                                                                       
										</div>
                                                                                                                        
<!--                                                                            <div class="col-xs-12 col-sm-12">
                                                                                <div class="col-xs-12 col-sm-6">
                                                                                  
                                                                                           
                                                                                            <h1>  OPENING ASSETS AND LIABILITES </h1>
                                                                                             
                                                                                                    <label class="col-sm-6 control-label" for="form-field-mask-2">DEBIT(ASSETS)</label>
                                                                                                    

                                                                                                    <div class="input-group col-sm-6">
                                                                                                        
                                                                                                            <input  class="form-control" value="${debit}" type="text" id="debit" readonly="true"/>
                                                                                                          
                                                                                                            
                                                                                                    </div>
                                                                                                              <label class="col-sm-6 control-label" for="form-field-mask-2">CREDIT(LIABILITIES)</label>
                                                                                                              <div class="input-group col-sm-6">
                                                                                                        
                                                                                                          
                                                                                                            <input  class="form-control" value="${credit}" type="text" id="credit" readonly="true"/>
                                                                                                           
                                                                                                    </div>
                                                                                                             <label class="col-sm-6 control-label" for="form-field-mask-2">SHORTAGE</label>
                                                                                                    <div class="input-group col-sm-6">
                                                                                                        
                                                                                                             <c:set var="total" value="${debit-credit}"/>
                                                                                                             <c:if test="${total>0}">                                                                                                     
                                                                                                                  <input  class="form-control" value="${total}  CR" type="text"  readonly="true"/>
                                                                                                             </c:if>
                                                                                                             <c:if test="${total<0}">                                                                                                      
                                                                                                                <input  class="form-control" value="${total < 0 ? -total:total}  DR" type="text"  readonly="true"/>
                                                                                                             </c:if>
                                                                                                             <c:if test="${total eq 0 }">                                                                                                      
                                                                                                                 <input  class="form-control" value="0" type="text" id="shortage" readonly="true"/>
                                                                                                             </c:if>
                                                                                                    </div>
                                                                                            
                                                                                </div>
                                                                                                             
                                                                             <div class="col-xs-12 col-sm-6">

                                                                                    <div class="row">
                                                                                       
                                                                                            <div class="clearfix form-actions" style="margin-bottom: -21px !important; ">
                                                                                                    <div class="col-md-offset-3 col-md-9">
                                                                                                        
                                                                                                        <input type="submit" id="btnSubmit"  class="btn btn-info" value="Save"/>


                                                                                                            &nbsp; &nbsp; &nbsp;
                                                                                                            <button class="btn" type="reset">
                                                                                                                    <i class="ace-icon fa fa-undo bigger-110"></i>
                                                                                                                    Reset
                                                                                                            </button>
                                                                                                    </div>
                                                                                            </div>
                                                                                    </div>
                                                                                </div>
												
                                                                            </div>-->
                                                                              <div class="col-xs-12 col-sm-12">
                                                                            <div class="row">
                                                                                
                                                                                <div class="col-xs-12">
                                                                                        <!--<h3 class="header smaller lighter blue">List of Items</h3>-->

                                                                                        <div class="table-header">
                                                                                                Item Quick Edit
                                                                                        </div>

                                                                                        <!-- div.table-responsive -->

                                                                                        <!-- div.dataTables_borderWrap -->
                                                                                        <div>
                                                                                                <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                                                                                                        <thead>
                                                                                                                <tr>
<!--                                                                                                                        <th class="center">
                                                                                                                                <label class="pos-rel">
                                                                                                                                        <input type="checkbox" class="ace" />
                                                                                                                                        <span class="lbl"></span>
                                                                                                                                </label>
                                                                                                                        </th>-->
                                                                                                                        
                                                                                                                        <th>ITEM CODE</th>
                                                                                                                        <th>ITEM NAME</th>                                                                                                                        
                                                                                                                        <th>BASIC PRICE</th>
                                                                                                                        <th>LR%</th>
                                                                                                                        <th>ER%</th>
                                                                                                                        <th>CA%</th>
                                                                                                                        <th>CB%</th>
                                                                                                                        <th>VA%</th>
                                                                                                                        <th>VB%</th>
                                                                                                                         <th>MC%</th>
                                                                                                                        <th>MCA</th>
                                                                                                                        <th>ROL</th>
                                                                                                                       
                                                                                                                      
                                                                                                                </tr>
                                                                                                        </thead>
                                                                                                        <thead>
                                                                                                            <tr>
                                                                                                               
                                                                                                               
                                                                                                                <td><input type="text" data-column="2" class="search-input-text" placeholder="Search Item Code"></td>
                                                                                                                <td><input type="text" data-column="1" class="search-input-text" placeholder="Search Item Name"></td>
                                                                                                                <td ></td>
                                                                                                                <td></td>
                                                                                                                <td></td>
                                                                                                                <td></td>
                                                                                                                <td></td>
                                                                                                                <td></td>
                                                                                                                 <td></td>
                                                                                                                 <td></td>
                                                                                                                <td></td>
                                                                                                                <td></td>
                                                                                                                
                                                                                                            </tr>
                                                                                                        </thead>
                                                                                                            

                                                                                                        
                                                                                                </table>
                                                                                        </div>
                                                                                </div>
                                                                        </div>
									</div>
									</div>
										
										
									</div>
									
									<div class="row">
									
									</div>
									<div class="row">
									
									</div>
								 <div class="col-xs-12 ">

                                                                                    <div class="row">
                                                                                        <hr>
                                                                                            <div class="clearfix form-actions" style="margin-bottom: -21px !important; ">
                                                                                                    <div class="col-md-offset-3 col-md-9">
                                                                                                        
                                                                                                        <input type="submit" id="btnSubmit"  class="btn btn-info" value="Save"/>


                                                                                                            &nbsp; &nbsp; &nbsp;
                                                                                                           <input type="reset" class="btn" value="Reset" onClick="window.location.reload()">
                                                                                                    </div>
                                                                                            </div>
                                                                                    </div>
                                                                                </div>
								
									
								</form:form>
								
								<!-- <div class="space-24"></div>

								<div class="hr hr-18 dotted hr-double"></div> -->
								
								</div><!-- PAGE CONTENT ENDS -->
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

		<!--[if lte IE 8]>
		  <script src="assets/js/excanvas.min.js"></script>
		<![endif]-->
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

		<!-- Data Tables -->
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
                
                <script src="assets/accounting-js/itemMaster.js"></script>

		<!-- inline scripts related to this page -->
                 <script>
        
            $(document).ready(function() {

    var table=$('#dynamic-table').DataTable( {
        "processing": true,
        "serverSide": true,
        "ajax": "GetDatatableQuickEdit.html",
          "aoColumnDefs": [
        { 
          "bSortable": false, 
          "aTargets": [ -1 ] // <-- gets last column and turns off sorting
         },
         { 
          "bSortable": false, 
          "aTargets": [ -2 ] // <-- gets last before column and turns off sorting
         } 
     ],
       autoWidth: false,
         "fnRowCallback": function( nRow, aData, iDisplayIndex ) {  
             
              
                $('td:eq(0)', nRow).html('<input class="form-control wide" name="code" class="start" id="code'+iDisplayIndex+'"  type="text" readonly="true" value='+aData[0]+'>');  
                $('td:eq(1)', nRow).html('<input class="form-control wide" name="name" type="text" id="name'+iDisplayIndex+'" onclick="calculation('+iDisplayIndex+');" onkeyup="calculation('+iDisplayIndex+');" value='+aData[1]+'>');  
                $('td:eq(2)', nRow).html('<input  class="form-control wide" name="basicPrice" type="text" id="basic'+iDisplayIndex+'" onclick="calculation('+iDisplayIndex+');" onkeyup="calculation('+iDisplayIndex+'); "onkeypress="return isNumberKey(event)" value='+aData[2]+'>');
                $('td:eq(3)', nRow).html('<input class="form-control wide" name="lr" type="text" id="lr'+iDisplayIndex+'" onclick="calculation('+iDisplayIndex+');" onkeyup="calculation('+iDisplayIndex+');" onkeypress="return isNumberKey(event)" value='+aData[3]+'>');
                $('td:eq(4)', nRow).html('<input class="form-control wide" name="er" type="text" id="er'+iDisplayIndex+'" onclick="calculation('+iDisplayIndex+');" onkeyup="calculation('+iDisplayIndex+');" onkeypress="return isNumberKey(event)" value='+aData[4]+'>');
                $('td:eq(5)', nRow).html('<input class="form-control wide" name="ca" id="ca'+iDisplayIndex+'" onclick="calculation('+iDisplayIndex+');" onkeyup="calculation('+iDisplayIndex+');" onkeypress="return isNumberKey(event)" value='+aData[5]+'>');
                $('td:eq(6)', nRow).html('<input class="form-control wide" name="cb" id="cb'+iDisplayIndex+'" onclick="calculation('+iDisplayIndex+');" onkeyup="calculation('+iDisplayIndex+');" onkeypress="return isNumberKey(event)" value='+aData[6]+'>');
                $('td:eq(7)', nRow).html('<input class="form-control wide" name="va" id="va'+iDisplayIndex+'" onclick="calculation('+iDisplayIndex+');" onkeyup="calculation('+iDisplayIndex+');" onkeypress="return isNumberKey(event)" value='+aData[7]+'>');
                $('td:eq(8)', nRow).html('<input class="form-control wide" name="vb" id="vb'+iDisplayIndex+'" onclick="calculation('+iDisplayIndex+');" onkeyup="calculation('+iDisplayIndex+');" onkeypress="return isNumberKey(event)" value='+aData[8]+'>');
                $('td:eq(9)', nRow).html('<input class="form-control wide" name="mc" id="mc'+iDisplayIndex+'" onclick="calculation('+iDisplayIndex+');" onkeyup="calculation('+iDisplayIndex+');" onkeypress="return isNumberKey(event)" value='+aData[9]+'>');
                $('td:eq(10)', nRow).html('<input class="form-control wide" name="mca" id="mca'+iDisplayIndex+'" onclick="calculation('+iDisplayIndex+');" onkeyup="calculation('+iDisplayIndex+');" onkeypress="return isNumberKey(event)" value='+aData[10]+'>');
                $('td:eq(11)', nRow).html('<input class="form-control wide" name="rol" id="rol'+iDisplayIndex+'" onclick="calculation('+iDisplayIndex+');" onkeyup="calculation('+iDisplayIndex+');" onkeypress="return isNumberKey(event)" value='+aData[11]+'> \n\
                 <input class="form-control wide" type="hidden" name="tr" id="tr'+iDisplayIndex+'"  value='+aData[12]+'> \n\
                 <input class="form-control wide" type="hidden" name="alt" id="alt'+iDisplayIndex+'"  value='+aData[13]+'>');
               
              
               // var e= $( nRow ).attr( 'data-row-id', iDisplayIndex);
               // alert("e" +e);
              
               
            },  
} );

$("#dynamic-table_filter").css("display","none");

$('.search-input-text').on('keyup click', function(){
        var i=$(this).attr('data-column');
        var v=$(this).val();
        table.columns(i).search(v).draw();
    }
  );
  
 
     });  
     
//     $(".start").on('keyup click', function(){
//    $(this).closest('tr').find("input").each(function() {
//        alert(this.value);
//    });
//});
     function calculation(iDisplayIndex){
       
         var itemcode= document.getElementById("code"+iDisplayIndex).value;
         var itemname= document.getElementById("name"+iDisplayIndex).value;
         var basic= document.getElementById("basic"+iDisplayIndex).value;
         var itemLR= document.getElementById("lr"+iDisplayIndex).value;
         var itemER= document.getElementById("er"+iDisplayIndex).value;
         var itemCA= document.getElementById("ca"+iDisplayIndex).value;
         var itemCB= document.getElementById("cb"+iDisplayIndex).value;
         var itemVA= document.getElementById("va"+iDisplayIndex).value;
         var itemVB= document.getElementById("vb"+iDisplayIndex).value;
         var itemMC= document.getElementById("mc"+iDisplayIndex).value;
         var itemMCA= document.getElementById("mca"+iDisplayIndex).value;
         var itemROL= document.getElementById("rol"+iDisplayIndex).value;
    
             
    var p=$('#basic'+iDisplayIndex+'').val();
 
    var lr=$('#lr'+iDisplayIndex+'').val();
   
    var er=$('#er'+iDisplayIndex+'').val();
   
    var tr=parseFloat($('#tr'+iDisplayIndex+'').val());
    
    var whereAltUnit=$('#alt'+iDisplayIndex+'').val();
    
   
//    var la=$("#la").val();
//    var lp=$("#lp").val();
//    var ea=$("#ea").val();
//    var ep=$("#ep").val();
//    var ta=$("#ta").val();
//    var tp=$("#tp").val();
    
    //Make basic+vat price readonly when enter value in basic price
    
//    if(p.length>0){
//     $("#basicVatPrice").attr('readonly', true);
//     
//    }
//    if(pv.length>0){
//       
//       $("#basicVatPrice").attr('readonly', false);
//       
//       //Calculate basic price and make it readonly
//        p=(pv*100)/(100+tr);
//        var round=parseFloat(p.toFixed(2));
//        
//        $("#basicPrice").val(round);
//        $("#basicPrice").attr('readonly', true);
//        
//       //Remove LR% and ER% value and make it readonly
//        $("#lr").val('');
//        $("#er").val('');
//        $("#lr").attr('readonly', true);
//        $("#er").attr('readonly', true);
//    }
   
    
    
    
    //Assign value 0 when LR% is empty
    if(lr.length===0){
        lr=0;
    }
    //Assign value 0 when ER% is empty
    if(er.length===0){
        er=0;
    }
//     //Assign value 0 when LA is empty
//    if(la.length===0){
//        la=0;
//    }
//    //Assign value 0 when LP is empty
//    if(lp.length===0){
//        lp=0;
//    }
//    //Assign value 0 when EA is empty
//    if(ea.length===0){
//        ea=0;
//    }
//    //Assign value 0 when EP is empty
//    if(ep.length===0){
//        ep=0;
//    }
//    //Assign value 0 when TA is empty
//    if(ta.length===0){
//        ta=0;
//    }
//    //Assign value 0 when TP is empty
//    if(tp.length===0){
//        tp=0;
//    }
   
    
     //LA formula implementation
     la=(lr/100)*p;
     $("#la").val(la.toFixed(2));
     
     //LP formula implementation
     lp=(p-la);
    
     $("#lpItem").val(lp.toFixed(2));
     
     //EA formula implementation
     ea=(er/100)*lp;
     $("#ea").val(ea.toFixed(2));
     
     //EP formula implementation
     ep=(lp+ea);
     $("#epItem").val(ep.toFixed(2));
    
     
     //EA formula implementation
     ta=(tr/100)*ep;
     $("#ta").val(ta.toFixed(2));
     
     //EP formula implementation
     tp=(ep+ta);
     $("#tpItem").val(tp.toFixed(2));
     
/////////////////////////////////////End First Box Calculation///////////////////////////////////////////////
/////////////////////////////////////Sale Price for VAT Dealers Box Calculation///////////////////////////////////////////////    
    var va=$('#va'+iDisplayIndex+'').val();
    var vb=$('#vb'+iDisplayIndex+'').val();
    var ca=$('#ca'+iDisplayIndex+'').val();
    var cb=$('#cb'+iDisplayIndex+'').val();
//    var vap=$("#vap").val();
//    var vbp=$("#vbp").val();
//    var cap=$("#cap").val();
//    var cbp=$("#cbp").val();
    
    //Assign value 0 when VA% is empty
    if(va.length===0){
        va=0;
    }
    //Assign value 0 when VB% is empty
    if(vb.length===0){
        vb=0;
    }
    //Assign value 0 when CA% is empty
    if(ca.length===0){
        ca=0;
    }
    //Assign value 0 when CB% is empty
    if(cb.length===0){
        cb=0;
    }
    //Assign value 0 when VAP is empty
//    if(vap.length===0){
//        vap=0;
//    }
//    //Assign value 0 when VBP is empty
//    if(vbp.length===0){
//        vbp=0;
//    }
//    //Assign value 0 when CAP is empty
//    if(cap.length===0){
//        cap=0;
//    }
//    //Assign value 0 when CBP is empty
//    if(cbp.length===0){
//        cbp=0;
//    }
    
    //VAP formula implementation
     vap=(ep*100)/(100-va);
  
     $("#vapItem").val(rounding(vap));
    // alert(rounding(vap));
    //VBP formula implementation
     vbp=(ep*100)/(100-vb);
     $("#vbpItem").val(rounding(vbp));
     
    //CAP formula implementation
     cap=(ep*100+ep*tr)/(100-ca);
     //alert(cap);
     $("#capItem").val(rounding(cap.toFixed(2)));

    //CBP formula implementation
     cbp=(ep*100+ep*tr)/(100-cb);
     //alert(cbp);
     $("#cbpItem").val(rounding(cbp.toFixed(2)));


/////////////////////////////////////Sale Price for VAT Dealers Box Calculation///////////////////////////////////////////////         
    //When where Alt Unit not empty
    if(whereAltUnit!= ''){
        
//        $("#lpAlt").val((lp/whereAltUnit).toFixed(2));
//        $("#epAlt").val((ep/whereAltUnit).toFixed(2));
//        $("#tpAlt").val((tp/whereAltUnit).toFixed(2));
     
       
        var vapAlt =(vap/whereAltUnit);
        var vbpAlt =parseFloat(vbp/whereAltUnit).toFixed(2);
        var capAlt =parseFloat(cap/whereAltUnit).toFixed(2);
        var cbpAlt =parseFloat(cbp/whereAltUnit).toFixed(2);
       
         
        $("#vapAlt").val(rounding(vapAlt));
        $("#vbpAlt").val(rounding(vbpAlt));
        $("#capAlt").val(rounding(capAlt));
        $("#cbpAlt").val(rounding(cbpAlt));
        
//        if(vapAlt='Infinity')
//        {
//           $("#vapAlt").val('0');           
//        }
//        if(vbpAlt='Infinity')
//        {
//           $("#vbpAlt").val('0');           
//        }
//        if(capAlt='Infinity')
//        {
//           $("#capAlt").val('0');           
//        }
//        if(cbpAlt='Infinity')
//        {
//           $("#cbpAlt").val('0');           
//        }
       
    }
      
    
     }
   /// ** To allow only Number in Textbox  **  ///
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
    
        </script> 
                
                <script>
                  $("#form-field-mask-1").focus();
                  // to show cursor on first TextBox
                </script>
                 <script>
                    
$(document).ready(function(){
$(document).on('keydown',':tabbable', function (e) {
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
   $('#btnSubmit').keydown(function(event){
          //Check if the key pressed is ENTER key using the keyCode=13
          if(event.keyCode === 13){
//           alert('Successfully!'); 
          }
          event.cancelBubble = true;
             if (event.stopPropagation) event.stopPropagation();
         });
          
});
});
</script>
 <script>    
                     
        var s = document.getElementById("shortage");
        var shortage=s.value;
       
        
        var dr= document.getElementById("debit");
        var debit=dr.value;
       
        
        var cr = document.getElementById("credit");
        var credit=cr.value;
        
   
        
        if(shortage == 0 && debit>0 && credit>0 )
        {
     
            var d= document.getElementById("stock");
   
            d.setAttribute('readonly', 'readonly');
   
         }
         else 
         {
            
            document.getElementById("stock").readOnly=false; 
            
         }
                 
</script>
                <script>
                    var s = document.getElementById("name");
        var selectedvalue = s.options[s.selectedIndex].value;
      
        
        
        if(selectedvalue == "Exempted goods")
        {
     
   var d= document.getElementById("set");
   d.value = 0;
   d.setAttribute('readonly', 'readonly');
   
         }
                </script>
<c:if test="${not empty itemForm.id}" >
<script>
   $(document).ready(function(){ 
       
         var basicPrice=$('#basicPrice').val();
         var basicVatPrice=$('#basicVatPrice').val();
         
        
         if(basicVatPrice!=''){
            $('#basicPrice').attr('readonly',true); 
        }
        else{
            $('#basicVatPrice').attr('readonly',true);
            $('#basicPrice').attr('readonly',false); 
        }
        
         if($("#vapCheckBox").prop('checked') === true){
        $('#vap').attr('readonly', false);
        9$('#va').attr('readonly',true);
         
        }
        else{
        $('#vap').attr('readonly', true);
         $('#va').attr('readonly',false);
         
            }
            
       });
   
</script>
</c:if>
<script>
    $(document).ready(function() {
    var table = $('#example').DataTable();
 
    $('button').click( function() {
        var data = table.$('input, select').serialize();
        alert(
            "The following data would have been submitted to the server: \n\n"+
            data.substr( 0, 120 )+'...'
        );
        return false;
    } );
} );
    </script>

		
		<script type="text/javascript">
			jQuery(function($) {
				$('#id-disable-check').on('click', function() {
					var inp = $('#form-input-readonly').get(0);
					if(inp.hasAttribute('disabled')) {
						inp.setAttribute('readonly' , 'true');
						inp.removeAttribute('disabled');
						inp.value="This text field is readonly!";
					}
					else {
						inp.setAttribute('disabled' , 'disabled');
						inp.removeAttribute('readonly');
						inp.value="This text field is disabled!";
					}
				});
			
			
				if(!ace.vars['touch']) {
					$('.chosen-select').chosen({allow_single_deselect:true}); 
					//resize the chosen on window resize
			
					$(window)
					.off('resize.chosen')
					.on('resize.chosen', function() {
						$('.chosen-select').each(function() {
							 var $this = $(this);
							 $this.next().css({'width': $this.parent().width()});
						})
					}).trigger('resize.chosen');
					//resize chosen on sidebar collapse/expand
					$(document).on('settings.ace.chosen', function(e, event_name, event_val) {
						if(event_name != 'sidebar_collapsed') return;
						$('.chosen-select').each(function() {
							 var $this = $(this);
							 $this.next().css({'width': $this.parent().width()});
						})
					});
			
			
					$('#chosen-multiple-style .btn').on('click', function(e){
						var target = $(this).find('input[type=radio]');
						var which = parseInt(target.val());
						if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
						 else $('#form-field-select-4').removeClass('tag-input-style');
					});
				}
			
			
				$('[data-rel=tooltip]').tooltip({container:'body'});
				$('[data-rel=popover]').popover({container:'body'});
			
				autosize($('textarea[class*=autosize]'));
				
				$('textarea.limited').inputlimiter({
					remText: '%n character%s remaining...',
					limitText: 'max allowed : %n.'
				});
			
				$.mask.definitions['~']='[+-]';
				$('.input-mask-date').mask('99/99/9999');
				$('.input-mask-phone').mask('(999) 999-9999');
				$('.input-mask-eyescript').mask('~9.99 ~9.99 999');
				$(".input-mask-product").mask("a*-999-a999",{placeholder:" ",completed:function(){alert("You typed the following: "+this.val());}});
			
			
			
				$( "#input-size-slider" ).css('width','200px').slider({
					value:1,
					range: "min",
					min: 1,
					max: 8,
					step: 1,
					slide: function( event, ui ) {
						var sizing = ['', 'input-sm', 'input-lg', 'input-mini', 'input-small', 'input-medium', 'input-large', 'input-xlarge', 'input-xxlarge'];
						var val = parseInt(ui.value);
						$('#form-field-4').attr('class', sizing[val]).attr('placeholder', '.'+sizing[val]);
					}
				});
			
				$( "#input-span-slider" ).slider({
					value:1,
					range: "min",
					min: 1,
					max: 12,
					step: 1,
					slide: function( event, ui ) {
						var val = parseInt(ui.value);
						$('#form-field-5').attr('class', 'col-xs-'+val).val('.col-xs-'+val);
					}
				});
			
			
				
				//"jQuery UI Slider"
				//range slider tooltip example
				$( "#slider-range" ).css('height','200px').slider({
					orientation: "vertical",
					range: true,
					min: 0,
					max: 100,
					values: [ 17, 67 ],
					slide: function( event, ui ) {
						var val = ui.values[$(ui.handle).index()-1] + "";
			
						if( !ui.handle.firstChild ) {
							$("<div class='tooltip right in' style='display:none;left:16px;top:-6px;'><div class='tooltip-arrow'></div><div class='tooltip-inner'></div></div>")
							.prependTo(ui.handle);
						}
						$(ui.handle.firstChild).show().children().eq(1).text(val);
					}
				}).find('span.ui-slider-handle').on('blur', function(){
					$(this.firstChild).hide();
				});
				
				
				$( "#slider-range-max" ).slider({
					range: "max",
					min: 1,
					max: 10,
					value: 2
				});
				
				$( "#slider-eq > span" ).css({width:'90%', 'float':'left', margin:'15px'}).each(function() {
					// read initial values from markup and remove that
					var value = parseInt( $( this ).text(), 10 );
					$( this ).empty().slider({
						value: value,
						range: "min",
						animate: true
						
					});
				});
				
				$("#slider-eq > span.ui-slider-purple").slider('disable');//disable third item
			
				
				$('#id-input-file-1 , #id-input-file-2').ace_file_input({
					no_file:'No File ...',
					btn_choose:'Choose',
					btn_change:'Change',
					droppable:false,
					onchange:null,
					thumbnail:false //| true | large
					//whitelist:'gif|png|jpg|jpeg'
					//blacklist:'exe|php'
					//onchange:''
					//
				});
				//pre-show a file name, for example a previously selected file
				//$('#id-input-file-1').ace_file_input('show_file_list', ['myfile.txt'])
			
			
				$('#id-input-file-3').ace_file_input({
					style: 'well',
					btn_choose: 'Drop files here or click to choose',
					btn_change: null,
					no_icon: 'ace-icon fa fa-cloud-upload',
					droppable: true,
					thumbnail: 'small'//large | fit
					//,icon_remove:null//set null, to hide remove/reset button
					/**,before_change:function(files, dropped) {
						//Check an example below
						//or examples/file-upload.html
						return true;
					}*/
					/**,before_remove : function() {
						return true;
					}*/
					,
					preview_error : function(filename, error_code) {
						//name of the file that failed
						//error_code values
						//1 = 'FILE_LOAD_FAILED',
						//2 = 'IMAGE_LOAD_FAILED',
						//3 = 'THUMBNAIL_FAILED'
						//alert(error_code);
					}
			
				}).on('change', function(){
					//console.log($(this).data('ace_input_files'));
					//console.log($(this).data('ace_input_method'));
				});
				
				
				//$('#id-input-file-3')
				//.ace_file_input('show_file_list', [
					//{type: 'image', name: 'name of image', path: 'http://path/to/image/for/preview'},
					//{type: 'file', name: 'hello.txt'}
				//]);
			
				
				
			
				//dynamically change allowed formats by changing allowExt && allowMime function
				$('#id-file-format').removeAttr('checked').on('change', function() {
					var whitelist_ext, whitelist_mime;
					var btn_choose
					var no_icon
					if(this.checked) {
						btn_choose = "Drop images here or click to choose";
						no_icon = "ace-icon fa fa-picture-o";
			
						whitelist_ext = ["jpeg", "jpg", "png", "gif" , "bmp"];
						whitelist_mime = ["image/jpg", "image/jpeg", "image/png", "image/gif", "image/bmp"];
					}
					else {
						btn_choose = "Drop files here or click to choose";
						no_icon = "ace-icon fa fa-cloud-upload";
						
						whitelist_ext = null;//all extensions are acceptable
						whitelist_mime = null;//all mimes are acceptable
					}
					var file_input = $('#id-input-file-3');
					file_input
					.ace_file_input('update_settings',
					{
						'btn_choose': btn_choose,
						'no_icon': no_icon,
						'allowExt': whitelist_ext,
						'allowMime': whitelist_mime
					})
					file_input.ace_file_input('reset_input');
					
					file_input
					.off('file.error.ace')
					.on('file.error.ace', function(e, info) {
						//console.log(info.file_count);//number of selected files
						//console.log(info.invalid_count);//number of invalid files
						//console.log(info.error_list);//a list of errors in the following format
						
						//info.error_count['ext']
						//info.error_count['mime']
						//info.error_count['size']
						
						//info.error_list['ext']  = [list of file names with invalid extension]
						//info.error_list['mime'] = [list of file names with invalid mimetype]
						//info.error_list['size'] = [list of file names with invalid size]
						
						
						/**
						if( !info.dropped ) {
							//perhapse reset file field if files have been selected, and there are invalid files among them
							//when files are dropped, only valid files will be added to our file array
							e.preventDefault();//it will rest input
						}
						*/
						
						
						//if files have been selected (not dropped), you can choose to reset input
						//because browser keeps all selected files anyway and this cannot be changed
						//we can only reset file field to become empty again
						//on any case you still should check files with your server side script
						//because any arbitrary file can be uploaded by user and it's not safe to rely on browser-side measures
					});
					
					
					/**
					file_input
					.off('file.preview.ace')
					.on('file.preview.ace', function(e, info) {
						console.log(info.file.width);
						console.log(info.file.height);
						e.preventDefault();//to prevent preview
					});
					*/
				
				});
			
				$('#spinner1').ace_spinner({value:0,min:0,max:200,step:10, btn_up_class:'btn-info' , btn_down_class:'btn-info'})
				.closest('.ace-spinner')
				.on('changed.fu.spinbox', function(){
					//console.log($('#spinner1').val())
				}); 
				$('#spinner2').ace_spinner({value:0,min:0,max:10000,step:100, touch_spinner: true, icon_up:'ace-icon fa fa-caret-up bigger-110', icon_down:'ace-icon fa fa-caret-down bigger-110'});
				$('#spinner3').ace_spinner({value:0,min:-100,max:100,step:10, on_sides: true, icon_up:'ace-icon fa fa-plus bigger-110', icon_down:'ace-icon fa fa-minus bigger-110', btn_up_class:'btn-success' , btn_down_class:'btn-danger'});
				$('#spinner4').ace_spinner({value:0,min:-100,max:100,step:10, on_sides: true, icon_up:'ace-icon fa fa-plus', icon_down:'ace-icon fa fa-minus', btn_up_class:'btn-purple' , btn_down_class:'btn-purple'});
			
				//$('#spinner1').ace_spinner('disable').ace_spinner('value', 11);
				//or
				//$('#spinner1').closest('.ace-spinner').spinner('disable').spinner('enable').spinner('value', 11);//disable, enable or change value
				//$('#spinner1').closest('.ace-spinner').spinner('value', 0);//reset to 0
			
			
				//datepicker plugin
				//link
				$('.date-picker').datepicker({
					autoclose: true,
					todayHighlight: true
				})
				//show datepicker when clicking on the icon
				.next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
			
				//or change it into a date range picker
				$('.input-daterange').datepicker({autoclose:true});
			
			
				//to translate the daterange picker, please copy the "examples/daterange-fr.js" contents here before initialization
				$('input[name=date-range-picker]').daterangepicker({
					'applyClass' : 'btn-sm btn-success',
					'cancelClass' : 'btn-sm btn-default',
					locale: {
						applyLabel: 'Apply',
						cancelLabel: 'Cancel',
					}
				})
				.prev().on(ace.click_event, function(){
					$(this).next().focus();
				});
			
			
				$('#timepicker1').timepicker({
					minuteStep: 1,
					showSeconds: true,
					showMeridian: false,
					disableFocus: true,
					icons: {
						up: 'fa fa-chevron-up',
						down: 'fa fa-chevron-down'
					}
				}).on('focus', function() {
					$('#timepicker1').timepicker('showWidget');
				}).next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
				
				
			
				
				if(!ace.vars['old_ie']) $('#date-timepicker1').datetimepicker({
				 //format: 'MM/DD/YYYY h:mm:ss A',//use this option to display seconds
				 icons: {
					time: 'fa fa-clock-o',
					date: 'fa fa-calendar',
					up: 'fa fa-chevron-up',
					down: 'fa fa-chevron-down',
					previous: 'fa fa-chevron-left',
					next: 'fa fa-chevron-right',
					today: 'fa fa-arrows ',
					clear: 'fa fa-trash',
					close: 'fa fa-times'
				 }
				}).next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
				
			
				$('#colorpicker1').colorpicker();
				//$('.colorpicker').last().css('z-index', 2000);//if colorpicker is inside a modal, its z-index should be higher than modal'safe
			
				$('#simple-colorpicker-1').ace_colorpicker();
				//$('#simple-colorpicker-1').ace_colorpicker('pick', 2);//select 2nd color
				//$('#simple-colorpicker-1').ace_colorpicker('pick', '#fbe983');//select #fbe983 color
				//var picker = $('#simple-colorpicker-1').data('ace_colorpicker')
				//picker.pick('red', true);//insert the color if it doesn't exist
			
			
				$(".knob").knob();
				
				
				var tag_input = $('#form-field-tags');
				try{
					tag_input.tag(
					  {
						placeholder:tag_input.attr('placeholder'),
						//enable typeahead by specifying the source array
						source: ace.vars['US_STATES'],//defined in ace.js >> ace.enable_search_ahead
						/**
						//or fetch data from database, fetch those that match "query"
						source: function(query, process) {
						  $.ajax({url: 'remote_source.php?q='+encodeURIComponent(query)})
						  .done(function(result_items){
							process(result_items);
						  });
						}
						*/
					  }
					)
			
					//programmatically add/remove a tag
					var $tag_obj = $('#form-field-tags').data('tag');
					$tag_obj.add('Programmatically Added');
					
					var index = $tag_obj.inValues('some tag');
					$tag_obj.remove(index);
				}
				catch(e) {
					//display a textarea for old IE, because it doesn't support this plugin or another one I tried!
					tag_input.after('<textarea id="'+tag_input.attr('id')+'" name="'+tag_input.attr('name')+'" rows="3">'+tag_input.val()+'</textarea>').remove();
					//autosize($('#form-field-tags'));
				}
				
				
				/////////
				$('#modal-form input[type=file]').ace_file_input({
					style:'well',
					btn_choose:'Drop files here or click to choose',
					btn_change:null,
					no_icon:'ace-icon fa fa-cloud-upload',
					droppable:true,
					thumbnail:'large'
				})
				
				//chosen plugin inside a modal will have a zero width because the select element is originally hidden
				//and its width cannot be determined.
				//so we set the width after modal is show
				$('#modal-form').on('shown.bs.modal', function () {
					if(!ace.vars['touch']) {
						$(this).find('.chosen-container').each(function(){
							$(this).find('a:first-child').css('width' , '210px');
							$(this).find('.chosen-drop').css('width' , '210px');
							$(this).find('.chosen-search input').css('width' , '200px');
						});
					}
				})
				/**
				//or you can activate the chosen plugin after modal is shown
				//this way select element becomes visible with dimensions and chosen works as expected
				$('#modal-form').on('shown', function () {
					$(this).find('.modal-chosen').chosen();
				})
				*/
			
				
				
				$(document).one('ajaxloadstart.page', function(e) {
					autosize.destroy('textarea[class*=autosize]')
					
					$('.limiterBox,.autosizejs').remove();
					$('.daterangepicker.dropdown-menu,.colorpicker.dropdown-menu,.bootstrap-datetimepicker-widget.dropdown-menu').remove();
				});
			
			});
		</script>
<c:if test="${not empty itemForm.basicPrice}">
    <script>
    $(document).ready(function(){
        $("#basicVatPrice").attr('readonly', true);
    });
    </script>
</c:if>
<c:if test="${not empty itemForm.basicVatPrice}">
<script>
$(document).ready(function(){
    $("#basicPrice").attr('readonly', true);
});
</script>
</c:if>
<c:if test="${not empty itemForm.rate}">
<script>
$(document).ready(function(){
    $("#rate").attr('readonly', false);
});
</script>
</c:if>
<c:if test="${not empty itemForm.mc}">
<script>
$(document).ready(function(){
    $("#mca").attr('readonly', true);
});
</script>
</c:if>
<c:if test="${not empty itemForm.mca}">
<script>
$(document).ready(function(){
    $("#mc").attr('readonly', true);
});
</script>
</c:if>
	</body>
</html>
