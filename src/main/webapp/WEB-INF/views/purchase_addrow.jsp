<%@page  pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


                                         
                                                            <!-- /.row -->
                                                                       						<div class="col-xs-12" id="addrowDiv">
								<!-- PAGE CONTENT BEGINS -->
                                                                <form:form class="form-horizontal" action="SavePurchase.html"  commandName="purchaseFrom" method="POST" id="pFrom" autocomplete="Off">
                                                                    <div class="widget-box lighter  widget-color-blue2">
                                                                    <div class="widget-header">
                                                                <h5 class="widget-title">Purchase</h5>
                                                                    </div>
                                                                         <div class="widget-body">
                                                                <div class="widget-main alert-success">
                                                                <div class="row">
									
                                                                    <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Invoice NO<span style="color:red;">*</span></label>
                                                                                    
										<div class="col-sm-12">
                                                                                        <form:input type="text" id="Box1" path="purchase.purchaseInvoiceId" step='0.01' oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Allowed Alphanumeric Only')"   min="0" placeholder="invoice no" class="form-control"   required="true"/>
                                                                                       
										</div>
									</div>
                                                                    <div class="form-group col-xs-2">
										<label class="col-sm-6" for="form-field-1"> Date<span style="color:red;">*</span></label>

										<div class="col-sm-12">
                                                                                        <form:input path="purchase.date" class="form-control date-picker" id="mydate" placeholder="date" type="text" data-date-format="dd/mm/yyyy" required="true"/>
                                                                                    <form:errors path="purchase.date" />
											
										</div>
									</div>
									
									<div class="form-group col-xs-3">
										<label class="col-sm-8"  for="form-field-1"> Category <span style="color:red;">*</span></label>

										<div class="col-sm-12">
                                                                                     
                                                                                    <form:select path="purchase.category" class="form-control TabOnEnter" name="category" id="category" onChange=""   oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Please Select Category')" required="true">
                                                                                       <form:option value="" label="-------Select-------"/>
                                                                                        <form:option value="ITC Purchase" label="ITC Purchase "/>
                                                                                        <form:option value="Purchase from Composite Dealers" label="Purchase from Composite Dealers"/>
                                                                                        <form:option value="Purchases from unregistered Persons" label="Purchases from unregistered Persons"/>                                                                                       
                                                                                        
                                                                                    </form:select>
                                                                                    <form:errors path="purchase.category" />
											
										</div>
									</div>
									
									
									
									<div class="form-group col-xs-3">
										<label class="col-sm-12" for="form-field-1">Name of  Supplier </label>
                                                                                    <form:hidden path="purchase.supplierId" id="supplierNameId"/>
										<div class="col-sm-12">
											<%--<form:input type="text" tabindex="3" path="purchase.cashSupplier" id="cashSupplier"  placeholder="cashSupplier" class="form-control TabOnEnter"/>--%>
											<%--<form:errors path="purchase.cashSupplier" />--%>
                                                                                <form:select path="purchase.cashSupplier" class="form-control" id="form-field-select-1" required="">
                                                                                    <form:option value="" label="choose supplier"/>
                                                                                    <c:forEach items="${supplierList}" var="itemGroup">
                                                                                    <form:option value="${itemGroup.supplierName}" label="${itemGroup.supplierName}"/>
                                                                                    </c:forEach>	
                                                                                </form:select>
										</div>
									</div>
                                                                        <div class="form-group col-xs-3" id="posDiv" >
										<label class="col-sm-12" for="form-field-1"> State</label>
										<div class="col-sm-12">
                                                                                        <form:select path="purchase.pos" tabindex="-1" class="form-control" id="pos" required="true" onChange="posStatesEqual()" oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Please Select Pos')">
                                                                                        <form:option value="" label="------Select------"/>
                                                                                        <form:option value="35" label="Andaman and Nicobar Islands"/>
                                                                                        <form:option value="28" label="Andhra Pradesh"/>
                                                                                         <form:option value="37" label="Andhra Pradesh(New)"/>
                                                                                          <form:option value="12" label="Arunachal Pradesh"/>
                                                                                           <form:option value="18" label="Assam"/>
                                                                                           <form:option value="10" label="Bihar"/>
                                                                                           <form:option value="04" label="Chandigarh"/>
                                                                                           <form:option value="22" label="Chattisgarh"/>
                                                                                           <form:option value="26" label="Dadra and Nagar Haveli"/>
                                                                                           <form:option value="25" label="Daman and Diu"/>
                                                                                           <form:option value="07" label="Delhi"/>
                                                                                           <form:option value="30" label="Goa"/>
                                                                                           <form:option value="24" label="Gujarat"/>
                                                                                           <form:option value="06" label="Haryana"/>
                                                                                           <form:option value="02" label="Himachal Pradesh"/>
                                                                                           <form:option value="01" label="Jammu and Kashmir"/>
                                                                                           <form:option value="20" label="Jharkhand"/>
                                                                                           <form:option value="29" label="Karnataka"/>
                                                                                           <form:option value="32" label="Kerala"/>
                                                                                           <form:option value="31" label="Lakshadweep Islands"/>
                                                                                           <form:option value="23" label="Madhya Pradesh"/>
                                                                                           <form:option value="27" label="Maharashtra"/>
                                                                                           <form:option value="14" label="Manipur"/>
                                                                                           <form:option value="17" label="Meghalaya"/>
                                                                                           <form:option value="15" label="Mizoram"/>
                                                                                           <form:option value="13" label="Nagaland"/>
                                                                                           <form:option value="21" label="Odisha"/>
                                                                                           <form:option value="34" label="Pondicherry"/>
                                                                                           <form:option value="03" label="Punjab"/>
                                                                                           <form:option value="08" label="Rajasthan"/>
                                                                                           <form:option value="11" label="Sikkim"/>
                                                                                           <form:option value="33" label="Tamil Nadu"/>
                                                                                           <form:option value="36" label="Telangana"/>
                                                                                           <form:option value="16" label="Tripura"/>
                                                                                           <form:option value="09" label="Uttar Pradesh"/>
                                                                                           <form:option value="05" label="Uttarakhand"/>
                                                                                           <form:option value="19" label="West Bengal" />
                                                                                    </form:select>
										</div>
									</div>
								</div>
                                                                                
                                                            <input type="hidden" name="CompanyGstno" id="CompanyGstno" value="${CompanyGstno}" />
                                                            <form:hidden   path="purchase.isIgst" id="final" class="form-control" />                            
                                                                      
								<div class="row">
                                                            <div class="form-group col-xs-2" >
										<label class="col-sm-8" for="form-field-1"> Mode<span style="color:red;">*</span></label>

										<div class="col-sm-12">
                                                                                  <form:select path="purchase.mode" class="form-control" placeholder="mode"   required="">
                                                                                    <form:option value="" label="-------Select-------"/>
                                                                                        <form:option value="Cash" lable="Cash"/>
                                                                                        <form:option value="Credit" lable="Credit"/>
                                                                                    </form:select>
                                                                                    <%--<form:errors path="purchase.mode" />--%>
										</div>
									</div>
									
									
                                                                   </div>     
                                                                                
                                                                    
                                                                    </div>
                                                        </div>
                                                    </div>            
                                            <div class="widget-box lighter  widget-color-blue2">
                                                        <div class="widget-body">
                                                                <div class="widget-main alert-info">                                                                    
						<div class="row">
                          <div class="col-xs-12 col-sm-12 widget-container-col" id="widget-container-col-2">
                                 <input type="button" class="btn btn-yellow m-b-sm " value="Add Row" onclick="addRowforPurchase()"/> 
					<div class="widget-box widget-color-blue" id="widget-box-2">
                                    <div class="widget-body">
                                        <div class="widget-main no-padding">
                                            <table class="table table-striped table-bordered table-hover" id="Mytable">
                                                <thead class="thin-border-bottom">
                                                    <tr>
                                                        
                                                        <th rowspan="2" style="width: 10%;">                                                                                                                       
                                                           Item Code
                                                        </th>
                                                        <th rowspan="2" style="width: 26%; ">
                                                            Name Of the Item
                                                        </th>
                                                       <th rowspan="2" style="width: 6%; ">
                                                            Qty
                                                        </th>
                                                        <th rowspan="2" style="width:100px; ">
							Unit
                                                        </th>
                                                         <th rowspan="2" style="width: 9%; ">                                                        
                                                            Basic Price                                                       
                                                        </th>                                                         
						         <th rowspan="2"  style="width: 9%; ">													
                                                           <c:choose>
                                                                                                                                                                         
                                                        <c:when test="${CategoryAuto=='ITC Purchase'}">

                                                             <p id="demo">EP</p> 
                                                        </c:when>   
                                                      
                                                                                                                                                                              
                                                        <c:when test="${CategoryAuto==''}">

                                                             <p id="demo">EP</p> 
                                                        </c:when>  
                                                             
                                                        <c:otherwise>
                                                         <p id="demo">TP</p> 
                                                        </c:otherwise>
                                                       
                                                            
                                                            </c:choose>                                                            
                                                        </th>                                                        
							<th rowspan="2">														
                                                            Amount                                         
                                                        </th> 
                                                        <th  colspan="2">                                                                
                                                            IGST                                                  
                                                        </th>                                                        
                                                        <th style="text-align:center;" colspan="2">                                                                
                                                            CGST                                                  
                                                        </th>                                                         
                                                        <th style="text-align:center;" colspan="2">                                                                
                                                            SGST                                                  
                                                        </th>
                                                       
                                                         <c:if test="${empty purchaseFrom.purchase.invoiceNo}">
                                                        <th rowspan="2">                                                                
                                                            Delete                                                  
                                                        </th> 
                                                        </c:if>
                                                        
                                                        
                                                    </tr>
                                                    <tr>           
                                                      <th rowspan="2" style="width:50px; "> % </th>
                                                      <th rowspan="2" style="width:70px; " > Amt </th>
                                                      <th rowspan="2" style="width:50px; "> % </th>
                                                      <th rowspan="2" style="width:70px; "> Amt </th>
                                                      <th rowspan="2" style="width:50px; "> % </th>
                                                      <th rowspan="2" style="width:70px; "> Amt </th>
                                                      
                                                  </tr>
                                                                                                            
                                                </thead>                                                                 
                                                                                                                                                                                                                                                                      
                                                <c:forEach items="${purchaseFrom.purchases}" var="purchases" varStatus="status">
                                                      <c:set var = "pur" value = "${status.index}"/>
                                                                                                 <tbody>
                                                                                                        <tr class="txtMult">
                                                                                                                 <form:hidden path="purchases[${status.index}].id"/>
                                                                                                                  <form:hidden path="purchases[${status.index}].cpPriceUpdate" id="priceUpdateId${status.index}"  value="1"/>
                                                                                                               <input type="hidden" id="tableLength${status.index}" value="${fn:length(purchaseFrom.purchases)}" />
                                                                                                                 <form:hidden path="purchases[${status.index}].tax" id="purchasesTax${status.index}" />
                                                                                                                 <input type="hidden"  id="hiddenUnit${status.index}" value="${purchases.unit}"/>
                                                                                                                <td><form:input path="purchases[${status.index}].itemCode" id="purchaseItemCode${status.index}"  type="text"  class=" inputs form-control ui-autocomplete-input" autocomplete="off" onKeypress="purchaseCategoryCheck('${status.index}');" onChange="purchasesCalculation(${fn:length(purchaseFrom.purchases)}); " oninvalid="setCustomValidity('  Enter Item Code')"   required="true"/><form:errors path="purchases[${status.index}].itemCode" /></td>
                                                                                                                <td><form:input path="purchases[${status.index}].nameOfTheItem"   id="purchaseNameOfTheItem${status.index}" type="text"  class=" inputs form-control" onKeypress="purchaseCategoryCheck('${status.index}');" onChange="purchasesCalculation(${fn:length(purchaseFrom.purchases)}); "/></td>
                                                                                                                <td><form:input path="purchases[${status.index}].qty" id="purchaseQty${status.index}" type="text" style="text-align:center;" class=" inputs form-control" required="true"  onClick="SelectName(${status.index});" onChange="onChangeQty('${status.index}'); purchasesCalculation(${fn:length(purchaseFrom.purchases)})" step='0.01' oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Allowed Number Only')"  onkeypress="return isNumberKey(event)" min="0"/></td>                                                                                                                
                                                                                                                <td><form:select path="purchases[${status.index}].unit" id="purchaseUnit${status.index}" style="text-align:right;" class="inputs form-control">
                                                                                                                        
                                                                                                                </form:select></td>
                                                                                                                <td><form:input path="purchases[${status.index}].rate"  id="purchaseRate${status.index}" type="text" style="text-align:right" class=" inputs form-control" onkeyup="ItemMasterPopUP(event,'${status.index}');"  onChange="purchasesCalculation(${fn:length(purchaseFrom.purchases)}); getUnit(${fn:length(purchaseFrom.purchases)})" step='0.01' oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Allowed Number Only')"  onkeypress="return isNumberKey(event)" min="0"/></td>                                                                                                               
                                                                                                                <td><form:input path="purchases[${status.index}].ep" id="purchaseEp${status.index}" type="text" style="text-align:right" class="inputs lst form-control"  onChange="onChangeQty('${status.index}'); purchasesCalculation(${fn:length(purchaseFrom.purchases)});UpdateInfo('${status.index}');" step='0.01' oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Allowed Number Only')"  onkeypress="return isNumberKey(event)" min="0"/></td>
                                                                                                                <td><form:input path="purchases[${status.index}].amount" id="purchaseAmount${status.index}" type="text" style="text-align:right" class="inputs form-control " readonly="true"/></td>
                                                                                                     
                                                                                                                <td><form:input path="purchases[${status.index}].taxIgst" id="purchasetaxIgst${status.index}" type="text" style="text-align:center;" class="inputs lst form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="purchases[${status.index}].igst" id="purchaseIgst${status.index}" type="text" style="text-align:right" class="inputs lst form-control" readonly="true"/></td>  
                                                                                                                <td><form:input path="purchases[${status.index}].taxCgst" id="purchasetaxCgst${status.index}" type="text" style="text-align:center;" class="inputs form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="purchases[${status.index}].cgst" id="purchaseCgst${status.index}" type="text" style="text-align:right" class="inputs form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="purchases[${status.index}].taxSgst" id="purchasetaxSgst${status.index}" type="text" style="text-align:center;" class="inputs lst form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="purchases[${status.index}].vat" id="purchaseVat${status.index}" type="text" style="text-align:right" class="inputs lst form-control" readonly="true"/></td>   
                                                                                                                
                                                                                                                
                                                                                                                     <c:if test="${empty purchaseFrom.purchase.invoiceNo}">
                                                                                                                    
                                                                                                                 <td style="text-align:center;"><a href="#" class="ace-icon fa fa-trash-o bigger-120 btn-danger"  onclick="deleteFunction(this,${fn:length(purchaseFrom.purchases)},${status.index})"></a></td>
                                                                                                                 </c:if>
                                                                                                                   
                                                                                                                
                                                                                                                
                                                                                                                
                                                                                                        </tr>

                                                                                                        
                                                                                                </tbody>
                                                                                                </c:forEach>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                 </div>
                            </div>
                                                                 </div>
                                                            </div>
                                                </div>

                           <div class="widget-box lighter  widget-color-blue2">
		
		<div class="widget-body">
			<div class="widget-main  alert-info">                                                      
					 <div class="row">
									
                        <div class="form-group col-xs-2">
							<label class="col-sm-12" for="form-field-1"> Total Amount</label>
								<div class="col-sm-12">
									 <form:input type="text" path="purchase.totalAmount" id="purchaseAmount" placeholder="totalAmount" class="form-control"  readonly="true"/>
											<form:errors path="purchase.totalAmount" />
								</div>
						</div>
                       
                                                 <div class="form-group col-xs-2">
							<label class="col-sm-12" for="form-field-1"> IGST </label>
								<div class="col-sm-12">
                                                                         <form:input type="number" path="purchase.defIgst" onkeyup="purchaseTotal();"  id="totalIgst" placeholder="totalIgst" step='0.01' oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Allowed Number Only')"  onkeypress="return isNumberKey(event)" min="0"  class="form-control" />
											
											 
								</div>
						</div>
                                                                         <form:hidden  path="purchase.totalIgst"   id="Igst"  class="form-control" />                                                
                        <div class="form-group col-xs-2">
							<label class="col-sm-12" for="form-field-1"> CGST </label>
								<div class="col-sm-12">
                                                                         <form:input type="number" path="purchase.defCgst" onkeyup="purchaseTotal();"  id="totalCgst" placeholder="totalCgst" step='0.01' oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Allowed Number Only')"  onkeypress="return isNumberKey(event)" min="0"  class="form-control" />
											<form:errors path="purchase.totalCgst" />
											 
								</div>
						</div>
                                                   <form:hidden  path="purchase.totalCgst"   id="Cgst" placeholder="totalCgst" class="form-control" />

						<div class="form-group col-xs-2">
							<label class="col-sm-6" for="form-field-1"> SGST </label>
								<div class="col-sm-12">
								 <form:input type="number" path="purchase.defVat"  onkeyup="purchaseTotal();" id="totalVat" placeholder="totalVat" step='0.01' oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Allowed Number Only')"  onkeypress="return isNumberKey(event)" min="0" class="form-control" />
											<form:errors path="purchase.totalVat" />
								</div>
						</div>
						<%--<form:hidden  path="purchase.totalVat" id="purchaseVat" placeholder="totalVat" class="form-control"/>--%>
						<div class="form-group col-xs-2">
                                                    <label class="col-sm-6" for="form-field-1"> Add/Less</label>
                                                    <div class="col-sm-12">
                                                        <form:input type="text" path="purchase.addOrLess"    id="AddLess"  onkeyup="Addorless();" placeholder="" class="form-control"   readonly=""/>

                                                    </div>
                                                </div>
                                                        <form:hidden  path="purchase.defAddless"    id="defAddless" placeholder="" class="form-control" readonly="true"/>
                                                        <input type="hidden" id="compGstin" value="${CompanyGstin}"   />
                                                                                       
                                                        <form:hidden  id="final" path="purchase.isIgst" />
                                                           <div class="form-group col-xs-2">
							<label class="col-sm-12" for="form-field-1"> Invoice Amount</label>
								<div class="col-sm-12">
                                                                    <form:input type="text" path="purchase.totalInvoiceAmount" id="TInvoiceAmount" placeholder="totalInvoiceAmount" class="form-control vatAll" readonly="true"/>
											<form:errors path="purchase.totalInvoiceAmount" />
								</div>
						</div>
						
						
					</div> 
                           </div>
                                                            </div>
                                                                
                                                                </div>
                                               
			                                    <c:if test="${pId==null || empty pId }">
									<div style="text-align:center">
									<button id="btnSubmit"class="btn btn-success">Save</button>
                                                                         &nbsp; &nbsp; &nbsp;
                                                                        <button class="btn" type="reset" onClick="window.location.reload()">  Reset </button>
                                                                       </div>
                                                               </c:if>           
									  <div class="addrowIMDiv">		
                                                                <div id="modal-form" class="modal" tabindex="-1">
                                                                   
									<div class="modal-dialog" style="width:90% !important;">
										
								</div>
                                                                              </div>
                                                                
									  </div>
                                                                      </div>
                 <!--model pop Up-->                                                        
                                                                          
              <div class="modal fade" id="myModal" tabindex="22" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                     <div class="modal-dialog" style="width: 890px;">
                      <div class="modal-content" style="width: 975px;">
                        <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                          <h4 class="modal-title" id="myModalLabel"> Unit Conversion </h4>
                        </div>
                        <div class="modal-body">
                         <div class="main-content">
                                                  <div class="main-content-inner">
                                      
                                                          <div class="page-content">
                                                                  <div class="page-header" style="background-color: #438EB9;">
                                                                          <h1 style="color: #fff;">Unit Conversion</h1>
                                                                         
                                                                  </div>
                                                          <form>
                                                                <input type="hidden" name="" id="itemIndexValue"/> 
                                                              
                                                              ml : <select name="input1" id="input1" onkeyup="calc()">
                                                                         <option value="180">180</option>
                                                                         <option value="375">375</option>
                                                                         <option value="750">750</option>
                                                                         <option value="1000">1000</option>
                                                                   </select>

                                                        No Of Qty :<input type="text" name="input2" id="input2" onkeyup="calc()" value=""> 

                                                             Case :   <input type="text" name="input3" id="input3" onkeyup="calc()" value=""> <a href="javascript: void(0)" onClick="calc()"></a>

                                                            Liter :  <input type="text" name="output"  id="output" value="">
                                                                
                                                             <input type="button" id="xd" value="add row">


                                                          </form>
                                                          <table id="Table">
                                                              <thead>
                                                              <tr>
                                                                  <td rowspan="2" style="width: 10%; ">ml</td>
                                                                  <td rowspan="2" style="width: 10%; ">No of Quantity</td>
                                                                  <td rowspan="2" style="width: 10%; ">Case</td>
                                                                  <td rowspan="2" style="width: 10%; ">Liter</td>
                                                                  <td rowspan="2" style="width: 10%; ">Delete</td>
                                                              </tr>
                                                              </thead>
                                                              <tbody id="TableBody">
                                                              </tbody>
                                                              <tfoot id="TableFooter">
                                                              <tr>
                                                                  <td colspan="3">Total</td>
                                                                  <td class ="someTotalPrice" id="tid"></td>
                                                              </tr>
                                                              </tfoot>
                                                          </table>

                                                     
                                                      </div>
                                                  </div>
                                          </div>
                        </div>
                        <div class="modal-footer">
                          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                          <button type="button" class="btn btn-primary"  onclick="SetName();" data-dismiss="modal">Save changes</button>
                        </div>
                      </div>
                    </div> 
              </div>                                                 
									</form:form> 
                                                      
                                                                    
                                                                  

                             
									
                                                                
                                                        

<script src="assets/accounting-js/purchase.js"></script>
               <script>
var i = $('table tr').length;

$(document).on('keydown', '.lst', function(e) {
  var code = (e.keyCode ? e.keyCode : e.which);
  if (code == 13) {
   e.preventDefault();
   addRowforPurchase();
  }
});
// Creating Tabindex Automatically for all inputs
var tabindex = 10;
    $('input,select,button').each(function() {
        if (this.type != "hidden") {
            var $input = $(this);
            $input.attr("tabindex", tabindex);
            tabindex++;
        }
});  
// Convert tab to enter key functionality
 $("#pFrom").enableEnterToTab({ captureTabKey: true });
// Assign Manual Tabindex order 
//document.getElementById("Box1").tabIndex = 1;
//document.getElementById("mydate").tabIndex =2;
document.getElementById("category").tabIndex = 3;
//document.getElementById("mode").tabIndex = 4;
//document.getElementById("cashSupplier").tabIndex = 5;
//document.getElementById("tinNo").tabIndex =6;
//document.getElementById("purchase.narration").tabIndex =7;
//document.getElementById("InvoiceAmount").tabIndex =8;
 $('#btnSubmit').keydown(function(event){
          //Check if the key pressed is ENTER key using the keyCode=13
          if(event.keyCode === 13){
//           alert('Successfully!'); 
          }
          event.cancelBubble = true;
             if (event.stopPropagation) event.stopPropagation();
 });
$("#Box1").focus();

</script>
   



<script type="text/javascript">
			jQuery(function($) {
				
                                   var date = new Date();
                                    var dd = date.getDate();             
                                    var mm = date.getMonth() + 1;
                                    var yyyy = date.getFullYear();
                                    
                                    if(dd<10) {
                                            dd='0'+dd
                                        } 

                                        if(mm<10) {
                                            mm='0'+mm
                                        }
                                  
                                       var ToDate = dd + '/' + mm + '/' + yyyy;
                                      
                                       
				$('.date-picker').datepicker({
                                     defaultDate : ToDate,
					autoclose: true,
					todayHighlight: true
                                       
				}),
                                $('.date-picker').val(ToDate)
                                
                                
				//show datepicker when clicking on the icon
				.next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
                                
                                 var columns1 = [{name:'Name', minWidth: '40%', valueField:'label'}, {name: 'Code', minWidth: '20%', valueField: 'code'},{name: 'location', minWidth: '40%', valueField: 'location'}];

    
                                    $("#supplierName").mcautocomplete({
                                  showHeader: true,
                                  columns: columns1,
                                  minLength:0,

                                 source: function(request, response) {
                                        var category = $('#category').val();    
                                        $.ajax({

                                            url: "GetSupplier.html",
                                            dataType: "json",
                                            type: "POST",

                                            data: {

                                                term: request.term,
                                                category: category
                                            },
                                            success: function(data){
                                                response( $.map( data, function( item ) {

                                                    return {
                                                        value: item.supplierName,   
                                                        label: item.supplierName,
                                                        id: item.idSupplier,
                                                        tin:item.tin,
                                                        openingAmount:item.openingAmount,
                                                        openingType:item.openingType,
                                                        code:item.supplierCode,
                                                        location:item.location

                                                    } 
                                                }));
                                             }
                                        });
                                    },
                                     select: function(event, ui) {         
                                        $('#cashSupplier').val(ui.item.cashSupplier);
                                        $('#cashSupplier').attr('readonly', true);        
                                //        $('#currentBalance').val(ui.item.openingAmount);
                                //        $('#openingType').val(ui.item.openingType);  
                                         $('#supplierNameId').val(ui.item.id);
//                                        $('#tinNo').val(ui.item.tin);
//                                        $('#tinNo').attr('readonly', true);

                                    },
                                     open: function(){
                                            $('.ui-autocomplete').css('width', '500px');
                                        },
                                    change:supplierChange

                                }).bind('focus', function(){ $(this).mcautocomplete("search"); } );
			
				
			});
                        
                        $('#purchaseItemCode${pur}').focus();
		</script>
<script>
    getUnit(${fn:length(purchaseFrom.purchases)});
</script>

<script>
    function edValueKeyPress()
    {
        var edValue = document.getElementById("edValue");
        var s = edValue.value;
    
        var lblValue = document.getElementById("lblValue");
        lblValue.innerText = "The text box contains: "+s;
    
    }
</script> 
<script>        
                function SelectName(i) {   
                     $('#itemIndexValue').val(i);
                    $('#TableBody').empty();
                    $('#TableBody').html('');
                    $('#myModal').modal('show');
                 
                    
  }
</script>
<script>
function calc() {
    var textValue1 = document.getElementById('input1').value;
    var textValue2 = document.getElementById('input2').value;
    var textValue3 = document.getElementById('input3').value;

    document.getElementById('output').value = textValue1 * textValue2 *textValue3/1000;
}
</script>
<script type="text/javascript">

        // Find and remove selected table rows
        
  function deleteRow(btn) {
        var row = btn.parentNode.parentNode;
        row.parentNode.removeChild(row);
        sumOfColumns();
    }

    function sumOfColumns(){

        var totalPrice = 0;
         $(".classPrice").each(function(){
            totalPrice += parseFloat($(this).html());
        });
        $(".someTotalPrice").html(totalPrice);
    }

    $(document).ready(function () {
        
        $('#xd').click(function() {
            var lines = '<input type="button" value="Delete" onclick="deleteRow(this)"/>';
            var input1 = $("#input1").val();
            var input2 = $("#input2").val();
            var input3 = $("#input3").val();
            var output = $("#output").val();
            var lines1 = '<td class = "classPrice">' +output+'</td>';lines1
            var markup = "<tr><td>"+input1 +"</td><td>" + input2+ "</td><td>" +input3 + "</td>" + lines1+ "<td>"+lines+"</td></tr>";

            $('#TableBody').append(markup);
            sumOfColumns();
        });
    });
</script>
<script type="text/javascript">
    function SetName() {
               var index=document.getElementById("itemIndexValue").value;
               var totalLiter=0;
               totalLiter = document.getElementById("tid").innerHTML;
               document.getElementById("purchaseQty"+index).value=totalLiter;
               onChangeQty(index);
               purchasesCalculation(${fn:length(purchaseFrom.purchases)});
               $('#TableBody').empty();
               $('#TableBody').html('');
        }
</script>

