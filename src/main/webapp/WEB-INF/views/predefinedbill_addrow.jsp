<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script>
    getUnit(${fn:length(predefineFrom.sales)});
//     $('#Mytable').tableNav();
</script>
<div class="col-xs-12" id="addrowPreDiv">
								<!-- PAGE CONTENT BEGINS -->   
                                                                <form:form class="form-horizontal" action="SavePredefinedBill.html"  commandName="predefineFrom" method="POST" id="pDForm">
                                                                  
                                                                    <div class="widget-box lighter  widget-color-blue2">
                                                        <div class="widget-header">
                                                                <h5 class="widget-title">Pre-defined Sale Bill</h5>
                                                        </div>

                                                        <div class="widget-body">
                                                                <div class="widget-main alert-success">
                                                                    
                                                                <div class="row">
								    <div class="form-group col-xs-1">
										<label class="col-sm-12" for="form-field-1"> s.no</label>
                                                                               
										<div class="col-sm-12">
                                                                                    <form:input type="text" path="sale.predefinedBillId" id="Box1" tabindex="1"  class="form-control" readonly="true"/>
                                                                                        <form:errors path="sale.invoiceNo"/>
										</div>
									</div>
                                                                    <form:input type="hidden" path="sale.predefinedBillId" id="Box1"  class="form-control" readonly="true"/>
                                                                        <div class="form-group col-xs-3">
										<label class="col-sm-12" for="form-field-1"> Category <span style="color:red;">*</span></label>

										<div class="col-sm-12">
                                                                                   <form:select path="sale.category"   class="form-control" id="category" onChange="onChangeCategory();show();" oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Please Select Category')">
                                                                                        <form:option value="" label="-------Select-------"/>
                                                                                        <form:option value="Consumers(B2C)" label="Consumers(B2C)"/>
                                                                                        <form:option value="GST Dealers(B2B)" label="GST Dealers(B2B)"/>
                                                                                        <form:option value="Interstate Sales" label="Interstate Sales"/>
                                                                                        <form:option value="Zero Rate Export" label="Zero Rate Export"/>
                                                                                        <form:option value="Zero Rate Non Export" label="Zero Rate Non Export"/>
                                                                                        
                                                                                        
                                                                                    </form:select>
                                                                                    <form:errors path="sale.category" />
											<!-- <label class="col-sm-12 control-label">Error</label> -->
										</div>
									</div>
                                                                          <div class="form-group col-xs-3" id="posDiv" >
										<label class="col-sm-12" for="form-field-1"> POS<span style="color:red;">*</span></label>
                                                                                    <div class="col-sm-12">
                                                                                    <form:select path="sale.pos"  class="form-control" id="pos" required="true" onChange="posStatesEqual()" oninput="setCustomValidity('')"  oninvalid="setCustomValidity('  Please Select Pos')">
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
                                                                                    
                                                                                    <form:errors path="sale.pos" />
										</div>
									</div>	
                                                                        <input type="hidden" name="CompanyGstno" id="CompanyGstno" value="${CompanyGstno}" />
                                                                        <form:hidden   path="sale.isIgst" id="IgstCalc" class="form-control" />                       
                                                                        <div class="form-group col-xs-3">
										<label class="col-sm-12" for="form-field-1"> Name of Bill  <span style="color:red;">*</span></label>

										<div class="col-sm-12">
											<form:input type="text" tabindex="3" path="sale.billName" id="form-field-1"  class="form-control" required="true"/>
                                                                                        <form:errors path="sale.billName" />
										</div>
									</div>
                                                                                        
                                                                        <c:choose>
                                                                        <c:when test="${predefineFrom.sale.predefinedBillId==null ||  empty predefineFrom.sale.predefinedBillId==null}">

                                                                    </c:when>  
                                                                       <c:otherwise>
                                                                                            <div class="form-group col-xs-2">
                                                                        <label class="col-sm-6">&nbsp; </label>
                                                                        <div class="col-sm-12">
                                                                            <a href="PreConvertToSales.html?eId=${predefineFrom.sale.predefinedBillId}" class="btn btn-success">Convert to Sale</a>
                                                                        </div>
                                                                        </div>
                                                                        </c:otherwise>
                                                                        </c:choose>

									
									
								</div>
									
																
                                                            </div>
                                                        </div>
                                                    </div>                                                              
                                                                    
                                                                    
                                                <div class="widget-box lighter  widget-color-blue2">
                                                        <div class="widget-body">
                                                                <div class="widget-main alert-info">
                                                                        <div class="row">
                                                                        <div class="col-xs-12 col-sm-12 widget-container-col" id="widget-container-col-2">
                                                                           
                                                                           <input type="button" class="btn btn-yellow m-b-sm" value="Add Row" onclick="addRowforPredefined()"/> 
                                                                         
                                                                            <div class="widget-box widget-color-blue" id="widget-box-2">
                                                                                            
                                                                        <div class="widget-body">
                                                                                <div class="widget-main no-padding">
                                                                                       <table class="table table-striped table-bordered table-hover" id="Mytable">
                                                                                                <thead class="thin-border-bottom">
                                                                                                        <tr>
                                                                                                                <th rowspan="2" style="width: 7%;">
                                                                                                                       
                                                                                                                        Item code
                                                                                                                </th>

                                                                                                                <th rowspan="2" style="width: 30%;">
                                                                                                                        Name of the item
                                                                                                                </th>
                                                                                                                <th rowspan="2" style="width: 4%;">
                                                                                                                        Qty
                                                                                                                </th>
                                                                                                                <th rowspan="2" style="width:6%;">
                                                                                                                    &nbsp;&nbsp;&nbsp;Unit&nbsp;&nbsp;
                                                                                                                </th>
                                                                                                                <th rowspan="2">
                                                                                                                        Margin
                                                                                                                </th>
                                                                                                                <th rowspan="2" style="width: 7%;">
                                                                                                                        Rate
                                                                                                                </th>
                                                                                                                 <th rowspan="2" style="width: 7%;">
                                                                                                                       <c:choose>
                                                                                                                    
                                                                                                                    <c:when test="${CategoryAuto=='Consumers(B2C)'}">
                                                                                                                        
                                                                                                                         <p id="demo">CP+GST</p> 
                                                                                                                    </c:when> 
                                                                                                                          <c:when test="${CategoryAuto==''}">
                                                                                                                        
                                                                                                                         <p id="demo">CP+GST</p> 
                                                                                                                    </c:when> 
                                                                                                                    <c:otherwise>
                                                                                                                        CP
                                                                                                                    </c:otherwise>
                                                                                                                        </c:choose> 
                                                                                                                </th>
                                                                                                                 <th rowspan="2" style="width: 10px;">
                                                                                                                        Discount
                                                                                                                </th>
                                                                                                                 <th rowspan="2" style="width: 7%;">
                                                                                                                        Amount
                                                                                                                </th>
                                                                                                                <th style="text-align:center;" colspan="2">
                                                                                                                        IGST
                                                                                                                </th>
                                                                                                                 <th style="text-align:center;" colspan="2">
                                                                                                                        CGST
                                                                                                                </th>
                                                                                                                 <th style="text-align:center;" colspan="2">
                                                                                                                        SGST
                                                                                                                </th>
                                                                                                                <th rowspan="2">                                                                
                                                                                                                   Delete                                                   
                                                                                                                </th>
                                                                                                        </tr>
                                                                                                        <tr>
                                                                                                            <th style="width: 4%;"> % </th>
                                                                                                            <th style="width: 6%;"> Amt </th>
                                                                                                            <th style="width: 4%;"> % </th>
                                                                                                            <th style="width: 6%;"> Amt </th>
                                                                                                            <th style="width: 4%;"> % </th>
                                                                                                            <th style="width: 6%;"> Amt </th>
                                                                                                            

                                                                                                        </tr>
                                                                                                </thead>
                                                                                                <c:forEach items="${predefineFrom.sales}" var="sale" varStatus="status">
                                                                                                <tbody id="siva">
                                                                                                    <c:set var = "pb" value = "${status.index}"/>
                                                                                                        <tr>    
                                                                                                                <form:hidden path="sales[${status.index}].id"/>
                                                                                                                <input type="hidden"  id="hiddenUnit${status.index}" value="${sale.unit}"/>
                                                                                                                <form:hidden path="sales[${status.index}].tax" id="salesTax${status.index}" />
                                                                                                                <form:hidden path="sales[${status.index}].cpcpvAmount" id="salesAmountCpCpv${status.index}" />
                                                                                                                <form:hidden path="sales[${status.index}].mcmcaAmount" id="salesAmountMcMca${status.index}" />
                                                                                                                <form:hidden path="sales[${status.index}].mcmca" id="salesMcMca${status.index}" />
                             
                                                                                                                <td><form:input path="sales[${status.index}].itemCode" type="text" tabindex="4" id="salesItemCode${status.index}" class="form-control ui-autocomplete-input" autocomplete="off" onKeypress="salesCategoryCheck('${status.index}');" onChange="salesCalculation(${fn:length(predefineFrom.sales)});  getUnit(${fn:length(predefineFrom.sales)})" onfocus="checkpos()"/><form:errors path="sales[${status.index}].itemCode"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].itemName" type="text" tabindex="5" id="salesItemName${status.index}"  class="form-control"  onKeypress="salesCategoryCheck('${status.index}');" onChange="salesCalculation(${fn:length(predefineFrom.sales)});  getUnit(${fn:length(predefineFrom.sales)})" onfocus="checkpos()"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].quantity" type="text" tabindex="6" id="salesQuantity${status.index}"  style="text-align:center;" class="form-control" onkeyup="onChangeQtyOrRate(${status.index}); salesCalculation(${fn:length(predefineFrom.sales)})" onkeypress="return isNumberKey(event)"/></td>
                                                                                                                <td><form:select tabindex="7" path="sales[${status.index}].unit" id="salesUnit${status.index}" class="form-control" onChange="onChangeMargin(${status.index}); salesCalculation(${fn:length(predefineFrom.sales)})">
                                                                                                                        
                                                                                                                    </form:select></td>
                                                                                                                <td><form:select tabindex="8" path="sales[${status.index}].margin" id="salesMargin${status.index}" class="form-control" onChange="onChangeMargin(${status.index}); salesCalculation(${fn:length(predefineFrom.sales)})">
                                                                                                                        <form:option value="A" label="A"/>
                                                                                                                        <form:option value="B" label="B"/>
                                                                                                                        <form:option value="R" label="R"/>
                                                                                                                    </form:select></td>
                                                                                                                <td><form:input path="sales[${status.index}].rate" type="text" id="salesRate${status.index}" style="text-align:right" class="form-control" readonly="true" onkeyup="onChangeQtyOrRate(${status.index}); salesCalculation(${fn:length(predefineFrom.sales)})"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].cpcpV" type="text" id="salesCpcpV${status.index}" style="text-align:right" class="form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].discount" type="text" id="salesDiscount${status.index}" style="text-align:right" class="form-control" onChange="onChangeDiscount(${status.index}); salesCalculation(${fn:length(predefineFrom.sales)})" required="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].amount" type="text" id="salesAmount${status.index}" style="text-align:right" class="form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].taxIgst" type="text" style="text-align:center;" id="salestaxIgst${status.index}"  class="inputs form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].igst" type="text" id="salesIgst${status.index}" style="text-align:right" class="form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].taxCgst" type="text" style="text-align:center;" id="salestaxCgst${status.index}"  class="inputs form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].cgst" type="text" id="salesCgst${status.index}" style="text-align:right" class="form-control" readonly="true"/></td>
                                                                                                                <td><form:input path="sales[${status.index}].taxSgst" type="text" style="text-align:center;" id="salestaxSgst${status.index}"  class="inputs lst form-control" readonly="true"/></td>  
                                                                                                                <td><form:input path="sales[${status.index}].vat" type="text" id="salesVat${status.index}" style="text-align:right" class="form-control" readonly="true" /></td>
                                                                                                             
                                                                                                                <td style="text-align:center;"><a href="#"  class="ace-icon fa fa-trash-o bigger-120 btn-danger" onclick="myFunctionPre(this,${fn:length(predefineFrom.sales)},${status.index})"></a></td>
                                                                                                              
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
									
                                                                    <div class="form-group col-xs-1">
										<label class="col-sm-12" for="form-field-1"> VAA</label>

										<div class="col-sm-12">
											<form:input type="text" tabindex="15" path="sale.vaa" id="salesVaa" class="form-control" readonly="true"/>
                                                                                        <form:errors path="sale.vaa"/>
                                                                                </div>
									</div>
                        
                                                                        <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> MCA </label>

										<div class="col-sm-12">
                                                                                    <form:input type="text" tabindex="16" path="sale.mca" id="salesMCA"  class="form-control"/>
											<form:errors path="sale.mca"/>
										</div>
									</div>

									<div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Assess Value </label>

										<div class="col-sm-12">
											<form:input type="text" tabindex="17" path="sale.assessValue" id="salesAssessValue" class="form-control" readonly="true"/>
											<form:errors path="sale.assessValue" />
										</div>
									</div>
                                                                         <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1">IGST</label>

										<div class="col-sm-12">
											<form:input type="text"  tabindex="19" path="sale.totalIgst" id="Igst" class="form-control" readonly="true"/>
                                                                                        <form:errors path="sale.totalIgst"/>
										</div>
                                                                        </div>        
                                                                                <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1">CGST</label>

										<div class="col-sm-12">
											<form:input type="text"  tabindex="19" path="sale.totalCgst" id="Cgst" class="form-control" readonly="true"/>
                                                                                        <form:errors path="sale.totalCgst"/>
										</div>
                                                                        </div>
                                                                        <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> SGST</label>

										<div class="col-sm-12">
											<form:input type="text" tabindex="18" path="sale.totalVat" id="salesVat" class="form-control" readonly="true"/>
                                                                                        <form:errors path="sale.totalVat"/>
										</div>
                                                                        </div>
                                                                        
								</div>
                                                                    <div class="row">
									
                                                                    
                                                                    
                                                                    <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Add/Less</label>

										<div class="col-sm-12">
                                                                                    <form:input type="text" tabindex="20" path="sale.addLess" id="salesAddOrLess" class="form-control" onkeyup="onChangeAddOrLess()"/>
                                                                                    <form:errors path="sale.addLess"/>
										</div>
									</div>
                                                                        <div class="form-group col-xs-2">
										<label class="col-sm-12" for="form-field-1"> Invoice Amount </label>

										<div class="col-sm-12">
                                                                                    <form:input type="text" tabindex="21" path="sale.invoiceAmount" id="salesInvoiceAmount" class="form-control" readonly="true"/>
											<form:errors path="sale.invoiceAmount"/>
										</div>
									</div>
                                                                        <div class="form-group col-xs-8">
										<label class="col-sm-12" for="form-field-11">Narration</label>
										<div class="col-sm-11">
                                                                                    <form:input  tabindex="22" path="sale.narration" class="form-control"/>	
                                                                                </div>
										
									</div>
								</div>									
			</div>
		</div>
	</div>

									<div class="space-4"></div>
								 
									<div style="text-align:center">
                                                                             <input type="submit" id="btnSubmit" class="btn btn-info" value="Save"/>
										 &nbsp; &nbsp; &nbsp;
                                                                                                       <input type="reset" class="btn" value="Reset" onClick="window.location.reload()">
									</div>
                                                                
									</form:form>
                                 
                                                </div>
                               <script>
var i = $('table tr').length;

$(document).on('keydown', '.lst', function(e) {
  var code = (e.keyCode ? e.keyCode : e.which);
  if (code == 13) {
   e.preventDefault();    
   addRowforPredefined(); 
  }
});
</script>
 <script>
                    function show()
                    {
                     
                        var myselect = document.getElementById("category").value;
                        
                       if(myselect == "Consumers(B2C)")
                       
                        {                           
                             txt = "CP+GST";
                  }
                  else {
                             txt = "CP";
                         }
                  document.getElementById("demo").innerHTML = txt;
                        
                    }
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
				$('input[id=id-date-range-picker-1]').daterangepicker({
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
                         $('#salesItemCode${pb}').focus();
		</script>
                
            <script src="assets/accounting-js/sales.js"></script>
