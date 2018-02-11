<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
					
<div class="addrowIMDiv">
<div class="modal-content">
                <div class="modal-header">
                        <button type="button" class="close"  data-dismiss="modal">&times;</button>
                        <h4 class="blue bigger">Purchase Entry Flow of Item Master</h4>
                </div>
						
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<form:form class="form-horizontal" action=""  method="POST" commandName="itemForm" id="myforms">

                                                                    <form:hidden path="id"/>
                                                                    <form:hidden path="currentStock"/>
                                                                    <input type="hidden" name="" id="itemIndexValue"/> 
                                                                    <input type="hidden" name="" id="itemTableValue"/>
                                                                    <form:hidden path="cp"/>
									<div class="row">
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
                                                                                                                            <form:input path="itemCode" class="form-control" type="text" id="form-field-mask-1"/>
                                                                                                                                <form:errors path="itemCode" style="color:red;"/>
                                                                                                                        </div>
														</div>

														
														<div class="col-md-3">
															<label for="form-field-mask-2">Item Name <span style="color:red;">*</span></label>

															<div class="input-group col-md-12">
																<form:input path="itemName" class="form-control" type="text" id="form-field-mask-2"/>
                                                                                                                                <form:errors path="itemName" style="color:red;"/>
															</div>
														</div>
														
<!--														<div class="col-md-2">
															<label for="form-field-mask-2">Item Group</label>

															<div class="input-group">
                                                                                                                            <form:select path="itemGroup" class="form-control" id="form-field-select-1">
                                                                                                                                <form:option value="" label="--------Select-------"/>
                                                                                                                                <c:forEach items="${itemGroupList}" var="itemGroup">
                                                                                                                                <form:option value="${itemGroup.idItem}" label="${itemGroup.itemGroupName}"/>
																</c:forEach>	
                                                                                                                            </form:select>
                                                                                                                            <form:errors path="itemGroup"/>
                                                                                                                        </div>
														</div>
														
														<div class="col-md-2">
															<label for="form-field-mask-2">Com.Code <span style="color:red;">*</span></label>

															<div class="input-group">
                                                                                                                            <form:select path="comCode" id="comCode" class="form-control" onChange="onChangeComCode()">
                                                                                                                                <form:option value="" label="--------Select-------"/>
																<c:forEach items="${cCodeList}" var="codeList">
                                                                                                                                <form:option value="${codeList.ccodeId}" label="${codeList.ccode}"/>
																</c:forEach>
                                                                                                                            </form:select>
                                                                                                                            <form:errors path="comCode" style="color:red;"/>
															</div>
														</div>-->
														
<!--														<div class="col-md-3">
															<label for="form-field-mask-3">Category</label>

															<div class="input-group col-md-12">
																<form:input path="category" id="category" class="form-control " type="text" readonly="true"/>
                                                                                                                                <form:errors path="category"/>
                                                                                                                        </div>
														</div>-->

														<hr>
														<div>
															
														</div>
														</div>
													</div>
												</div>
											</div>
										</div><!-- /.span -->
									</div>
										
										
										
									<div class="row">
										<div class="col-xs-12 col-sm-6">
                                                                                    <div class="widget-box lighter  widget-color-blue2">
<!--												<div class="widget-header">
													<h5 class="widget-title">C</h5>
												</div>-->

												<div class="widget-body">
													<div class="widget-main">
													<div class="row">
<!--														<div class="col-md-1">
															<label for="form-field-mask-1">Bits</label>

															<div class="input-group">
                                                                                                                            <form:checkbox path="bit_item" />
                                                                                                                                <form:errors path="bit_item"/>
															</div>
														</div>-->

														
<!--														<div class="col-md-3">
															<label for="form-field-mask-2">ROL</label>

															<div class="input-group col-md-12">
																<form:input path="rol" class="form-control" type="text" id="form-field-mask-2"/>
                                                                                                                                <form:errors path="rol"/>
                                                                                                                        </div>
														</div>-->
														
														<div class="col-md-4">
															<label for="form-field-mask-2">Basic Price <span style="color:red;">*</span></label>

															<div class="input-group">
																<form:input path="basicPrice" class="form-control" type="text" id="basicPrice" step='0.01' oninput="setCustomValidity('')" onkeypress="return isNumberKey(event)" oninvalid="setCustomValidity(' Number Allowed with 2 decimal places Only')"   min="0" onkeyup="onChangeBasicPrice()"/>
                                                                                                                                <form:errors path="basicPrice" style="color:red;"/>
															</div>
                                                                                                                        
														</div>
														
														<div class="col-md-4">
															<label for="form-field-mask-2">(Basic + GST) Price <span style="color:red;">*</span></label>

															<div class="input-group">
                                                                                                                            <form:input path="basicVatPrice" class="form-control" type="text" id="basicVatPrice" onkeyup="onChangeBasicPrice()"/>
                                                                                                                                <form:errors path="basicVatPrice"/>
															</div>
														</div>
														

														<hr>
														<div>
															
														</div>
														</div>
													</div>
												</div>
											</div>
<!--											<div class="widget-box lighter  widget-color-blue2">
												<div class="widget-header">
													<h5 class="widget-title">Unit Details</h5>
												</div>

												<div class="widget-body">
													<div class="widget-main">
													<div class="row">
														<div class="col-md-4">
															<label for="form-field-mask-2">Unit <span style="color:red;">*</span></label>

															<div class="input-group col-md-12">
																<form:select path="unit" class="form-control" id="unit">
																	 <form:option value="" label="--------Select-------"/>
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

														<hr>
														<div>
															
														</div>
														</div>
													</div>
												</div>
											</div>-->
										</div><!-- /.span -->
										
										<div class="col-xs-12 col-sm-6">
											
										</div><!-- /.span -->
									</div><!-- /.row -->

									
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
											<div class="widget-box  widget-color-blue2">
												<div class="widget-header">
                                                                                                    <form:hidden path="stockDate" class="form-control date-picker" id="form-field-mask-2" data-date-format="dd/mm/yyyy"/>
                                                                                               
                                                                                                    <h5 class="widget-title">Opening Stock details as on : <span style="color:yellow"><b><fmt:formatDate value="${itemForm.stockDate}" pattern="dd-MMM-yyyy"/></b></span></h5>
												
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

														<hr>
														<div>
															
														</div>
														</div>
													</div>
												</div>
											</div>
											
											<div class="widget-box  widget-color-blue2">
												

												<div class="widget-body">
													<div class="widget-main alert-info">
													<div class="row">
													
														<!-- Accounting LA starts-->
														<div class="col-md-2">
															<label for="form-field-mask-2">LR%</label>

															<div class="input-group col-md-12">
																<form:input path="lr"  class="form-control" type="text"  id="lr" step='0.01' oninput="setCustomValidity('')" onkeypress="return isNumberKey(event)" oninvalid="setCustomValidity(' Number Allowed with 2 decimal places Only')"   min="0" onkeyup="onChangeBasicPrice()"/>
                                                                                                                                <form:errors path="lr"/>
															</div>
														</div>
														
														<div class="col-md-2">
															<label for="form-field-mask-2">LA</label>

															<div class="input-group">
                                                                                                                            <form:input path="la" class="form-control" type="text"  id="la" readonly="true"/>
                                                                                                                                <form:errors path="la"/>
															</div>
														</div>
														
														<div class="col-md-4">
															<label for="form-field-mask-3">LP</label>

															<div class="input-group col-md-12">
																<form:input path="lp" class="form-control" type="text"  id="lp" readonly="true"/>
                                                                                                                                <form:errors path="lp"/>
															</div>
														</div>
														
														<div class="col-md-4">
															<label for="form-field-mask-3">LP(Alt)</label>

															<div class="input-group col-md-12">
																<form:input path="lpAlt" class="form-control" type="text"  id="lpAlt" readonly="true"/>
                                                                                                                                <form:errors path="lpAlt"/>
															</div>
														</div>
														</div>
                                                                                                                        
														<!-- Accounting LA Ends-->
														
														<!-- Accounting ER starts-->
														<div class="row">
														<div class="col-md-2">
															<label for="form-field-mask-2">ER%</label>

															<div class="input-group col-md-12">
																<form:input path="er" class="form-control" type="text" id="er"  step='0.01' oninput="setCustomValidity('')" onkeypress="return isNumberKey(event)" oninvalid="setCustomValidity(' Number Allowed with 2 decimal places Only')"   min="0" onkeyup="onChangeBasicPrice()"/>
                                                                                                                                <form:errors path="er"/>
															</div>
														</div>
														
														<div class="col-md-2">
															<label for="form-field-mask-2">EA</label>

															<div class="input-group">
																<form:input path="ea" class="form-control" type="text"  id="ea" readonly="true"/>
                                                                                                                                <form:errors path="ea"/>
															</div>
														</div>
														
														<div class="col-md-4">
															<label for="form-field-mask-3">EP</label>

															<div class="input-group col-md-12">
																<form:input path="ep" class="form-control" type="text"  id="ep" readonly="true"/>
                                                                                                                                <form:errors path="ep"/>
															</div>
														</div>
														
														<div class="col-md-4">
															<label for="form-field-mask-3">EP(Alt)</label>

															<div class="input-group col-md-12">
																<form:input path="epAlt" class="form-control" type="text"  id="epAlt" readonly="true"/>
                                                                                                                                <form:errors path="epAlt"/>
															</div>
														</div>
														</div>
														<!-- Accounting ER Ends-->
														
														<!-- Accounting TR starts-->
														<div class="row">
														<div class="col-md-2">
															<label for="form-field-mask-2">GST%</label>

															<div class="input-group col-md-12">
																<form:input path="tr" class="form-control" type="text"  id="tr" readonly="true"/>
                                                                                                                                <form:errors path="tr"/>
															</div>
														</div>
														
														<div class="col-md-2">
															<label for="form-field-mask-2">TA</label>

															<div class="input-group">
																<form:input path="ta" class="form-control" type="text"  id="ta" readonly="true"/>
                                                                                                                                <form:errors path="ta"/>
															</div>
														</div>
														
														<div class="col-md-4">
															<label for="form-field-mask-3">TP</label>

															<div class="input-group col-md-12">
																<form:input path="tp" class="form-control" type="text"  id="tp" readonly="true"/>
                                                                                                                                <form:errors path="tp"/>
															</div>
														</div>
														
														<div class="col-md-4">
															<label for="form-field-mask-3">TP(Alt)</label>

															<div class="input-group col-md-12">
																<form:input path="tpAlt" class="form-control" type="text"  id="tpAlt" readonly="true"/>
                                                                                                                                <form:errors path="tpAlt"/>
															</div>
														</div>
														<!-- Accounting TR Ends-->
														
														<hr>
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
<!--										<div class="col-xs-12 col-sm-6">
                                                                                    
                                                                                    <div class="widget-box  widget-color-blue2">
												<div class="widget-header">
													<h5 class="widget-title">Sale Price for GST Dealers</h5>
												</div>

												<div class="widget-body">
													<div class="widget-main alert-success">
													<div class="row">
														<div class="col-md-4">
															<label for="form-field-mask-2">VA%</label>

															<div class="input-group col-md-12">
																<form:input path="va" class="form-control" type="text"  id="va" onkeyup="onChangeBasicPrice()"/>
                                                                                                                                <form:errors path="va"/>
															</div>
														</div>
														
														<div class="col-md-3">
                                                                                                                    <label for="form-field-mask-2">VAP <form:checkbox path="vapCheckbox" id="vapCheckBox" onChange="onChangeVAPCheckBox()"/></label>

															<div class="input-group">
																<form:input path="vap" class="form-control" type="text"  id="vap" readonly="true" onkeyup="onChangeVAP()"/>
                                                                                                                                <form:errors path="vap"/>
															</div>
														</div>
														
														<div class="col-md-5">
															<label for="form-field-mask-3">VAP(Alt)</label>

															<div class="input-group col-md-12">
																<form:input path="vapAlt" class="form-control" type="text"  id="vapAlt" readonly="true"/>
                                                                                                                                <form:errors path="vapAlt"/>
															</div>
														</div>
														</div>
														<div class="row">
                                                                                                                    
                                                                                                                    <div class="space-4"></div>
														<div class="col-md-4">
															<label for="form-field-mask-2">VB%</label>

															<div class="input-group col-md-12">
																<form:input path="vb" class="form-control" type="text"  id="vb" onkeyup="onChangeBasicPrice()"/>
                                                                                                                                <form:errors path="vb"/>
															</div>
														</div>
														
														<div class="col-md-3">
                                                                                                                    <label for="form-field-mask-2">VBP <form:checkbox path="vbpCheckbox" id="vbpCheckbox" onChange="onChangeVBPCheckBox()"/></label>

															<div class="input-group">
																<form:input path="vbp" class="form-control" type="text"  id="vbp" readonly="true" onkeyup="onChangeVBP()"/>
                                                                                                                                <form:errors path="vbp"/>
															</div>
														</div>
														
														<div class="col-md-5">
															<label for="form-field-mask-3">VBP(Alt)</label>

															<div class="input-group col-md-12">
																<form:input path="vbpAlt" class="form-control" type="text"  id="vbpAlt" readonly="true"/>
                                                                                                                                <form:errors path="vbpAlt"/>
															</div>
														</div>

														<hr>
														<div>
															
														</div>
														</div>
													</div>
												</div>
											</div>
                                                                                        <div class="widget-box  widget-color-blue2">
												<div class="widget-header">
													<h5 class="widget-title">Sale Price for Consumers</h5>
												</div>

												<div class="widget-body">
													<div class="widget-main alert-danger">
													<div class="row">
														<div class="col-md-4">
															<label for="form-field-mask-2">CA%</label>

															<div class="input-group col-md-12">
																<form:input path="ca" class="form-control" type="text"  id="ca" onkeyup="onChangeBasicPrice()"/>
                                                                                                                                <form:errors path="ca"/>
															</div>
														</div>
														
														<div class="col-md-3">
                                                                                                                    <label for="form-field-mask-2">CAP <form:checkbox path="capCheckbox" id="capCheckbox" onChange="onChangeCAPCheckBox()"/></label>

															<div class="input-group">
																<form:input path="cap" class="form-control" type="text"  id="cap" readonly="true" onkeyup="onChangeCAP()"/>
                                                                                                                                <form:errors path="cap"/>
															</div>
														</div>
														
														<div class="col-md-5">
															<label for="form-field-mask-3">CAP(Alt)</label>

															<div class="input-group col-md-12">
																<form:input path="capAlt" class="form-control" type="text"  id="capAlt" readonly="true"/>
                                                                                                                                <form:errors path="capAlt"/>
															</div>
														</div>
														</div>
														<div class="row">
                                                                                                                    <div class="space-4"></div>
                                                                                                                    <div class="space-4"></div>
														<div class="col-md-4">
															<label for="form-field-mask-2">CB%</label>

															<div class="input-group col-md-12">
																<form:input path="cb" class="form-control" type="text"  id="cb" onkeyup="onChangeBasicPrice()"/>
                                                                                                                                <form:errors path="cb"/>
															</div>
														</div>
														
														<div class="col-md-3">
                                                                                                                    <label for="form-field-mask-2">CBP <form:checkbox path="cbpCheckbox" id="cbpCheckbox" onChange="onChangeCBPCheckBox()"/></label>

															<div class="input-group">
																<form:input path="cbp" class="form-control" type="text"  id="cbp" readonly="true" onkeyup="onChangeCBP()"/>
                                                                                                                                <form:errors path="cbp"/>
															</div>
														</div>
														
														<div class="col-md-5">
															<label for="form-field-mask-3">CBP(Alt)</label>

															<div class="input-group col-md-12">
																<form:input path="cbpAtl" class="form-control" type="text"  id="cbpAlt" readonly="true"/>
                                                                                                                                <form:errors path="cbpAtl"/>
															</div>
														</div>

														<hr>
														<div>
															
														</div>
														</div>
													</div>
												</div>
											</div>
                                                                                       
											
											
										</div>-->
                                                                                    
                                                                            <div class="col-xs-12 col-sm-12">
<!--                                                                                <div class="col-xs-12 col-sm-6">
                                                                                     
                                                                                            <hr>
                                                                                                    <label class="col-sm-6 control-label" for="form-field-mask-2">Total Opening Stock value of all items as on</label>

                                                                                                    <div class="input-group col-sm-6">
                                                                                                            <input value="<fmt:formatDate value="${itemForm.stockDate}" pattern="dd-MMM-yyyy"/>" class="formath-control" id="form-field-mask-2" type="text" readonly="true"/>
                                                                                                            <input value="${sumOfStockValue}" class="form-control" type="text" id="form-field-mask-2" readonly="true"/>
                                                                                                            
                                                                                                    </div>
                                                                                            
                                                                                </div>-->
                                                                             <div class="col-xs-12 col-sm-6">

                                                                                    <div class="row">
                                                                                        <hr>
                                                                                            <div class="clearfix form-actions" style="margin-bottom: -21px !important; ">
                                                                                                    <div class="col-md-offset-3 col-md-9">
                                                                                                        
                                                                                                        <input type="submit" class="btn btn-info" name="submit"   value="submit"/>


                                                                                                            &nbsp; &nbsp; &nbsp;
                                                                                                                     
                                                                                                            <button type="button" hidden id="close" data-dismiss="modal"></button>
                                                                                                    </div>
                                                                                            </div>
                                                                                    </div>
                                                                                </div>
												
                                                                            </div>
                                                                              <div class="col-xs-12 col-sm-12">
                                                                            <div class="row">
                                                                                <hr>
                                                                                <div class="col-xs-12">
                                                                                       
                                                                                        <div>
                                                                                              
                                                                                        </div>
                                                                                </div>
                                                                        </div>
									</div>
									</div>
										
										
									</div>
									<hr>
									<div class="row">
									
									</div>
									<div class="row">
									
									</div>
								
								
									
								</form:form>
								
								<!-- <div class="space-24"></div>

								<div class="hr hr-18 dotted hr-double"></div> -->
								
								</div>
							</div>

<script src="assets/accounting-js/itemMaster.js"></script>
<script src="assets/accounting-js/purchase.js"></script>

		</script>
<c:if test="${not empty itemForm.basicPrice}">
    <script>
    $(document).ready(function(){
        $("#basicVatPrice").attr('readonly', true);
    });
    function isNumberKey(e)
       {
        var keyCode = (e.which) ? e.which : e.keyCode;
            if ((keyCode >= 48 && keyCode <= 57) || (keyCode == 8))
                return true;
            else if (keyCode == 46) {
                var curVal = document.activeElement.value;
                if (curVal != null && curVal.trim().indexOf('.') == -1)
                    return true;
                else
                    return false;
            }
            else
                return false;
       }
        $("#basicPrice").focus();
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