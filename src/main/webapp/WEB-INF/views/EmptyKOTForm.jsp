<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<script src="assets/accounting-js/kot.js"></script>

<body>
    <form:form method="post" id="kotForm" action="saveKOT.html" modelAttribute="kotForm" autocomplete="off"  onsubmit="return validateForm();">
        <div class="row">
            <div class="form-group col-md-4">
                <label  style="text-align:right">Selected Tables</label>
                <form:input type="text" class="form-control" id="selectedTables" path="kot.tableName" required="true" autocomplete="off" onKeypress="autoCompleteForTable();"/>

            </div>
            <div class="form-group col-md-4">
                <label class="control-label" style="text-align:right">KOT No</label>
                <form:input type="text" class="form-control" id="kotNo" path="kot.kotNo" required="true"/>
                <form:input type="hidden" class="form-control" id="kotid" path="kot.id"/>
            </div>    

            <div class="form-group col-md-4">    
                <label class="control-label" style="text-align:right">Waiter Name</label>
                <input type="text" class="form-control" id="waiterName" name="waiterName" required="true" autocomplete="off" onKeypress="autoCompleteForWaiter();"/>
                <form:input type="hidden" class="form-control"  path="kot.waiterId" id="waiterId" />
            </div>
        </div>
        <hr style="display: block;margin-top: 0.0em;margin-bottom: 0.5em;margin-left: auto;margin-right: auto;border-style: inset;border-width: 1px;">

        <div class="widget-box lighter  widget-color-blue2">
            <div class="widget-body">
                <div class="widget-main alert-info">
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 widget-container-col" id="widget-container-col-2">
                            <input type="button" class="btn btn-yellow m-b-sm" value="Add Row" onclick="addRowforKOT()"/>
                            <div class="widget-box widget-color-blue" id="widget-box-2">
                                <div class="widget-body">
                                    <div class="widget-main no-padding">
                                        <table class="table table-striped table-bordered table-hover" id="example1">
                                            <thead>
                                            <tr>
                                                <th style="text-align: center" rowspan="2">Item Code</th>
                                                <th style="text-align: center" rowspan="2">Name</th>
                                                <th style="text-align: center" rowspan="2">Unit</th>
                                                <th style="text-align: center" rowspan="2">Qty</th>
                                                <th style="text-align: center" rowspan="2">Rate</th>
                                                <th style="text-align: center" colspan="2">CGST</th>
                                                <th style="text-align: center" colspan="2">SGST</th>
                                                <th style="text-align: center" rowspan="2">Total</th>
                                                <th style="text-align: center" rowspan="2">Delete</th>
                                            </tr>
                                            <tr>
                                                <th rowspan="2" style="width:50px; "> % </th>
                                                <th rowspan="2" style="width:70px; "> Amt </th>
                                                <th rowspan="2" style="width:50px; "> % </th>
                                                <th rowspan="2" style="width:70px; "> Amt </th>
                                            </tr>
                                            </thead>
                                            <c:forEach items="${kotForm.kotItem}" var="kotItems" varStatus="status">
                                                <c:set var = "pur" value = "${status.index}"/>
                                            <tbody>
                                            <tr>
                                                <input type="hidden"  id="hiddenUnit${status.index}" value="${kotItems.unit}"/>
                                               
                                                <td>
                                                    <form:input type="hidden" id="hiddenItemId${status.index}" path="kotItem[${status.index}].id"/>
                                                    <form:input type="text" class="form-control" id="itemcode${status.index}" path="kotItem[${status.index}].itemCode" autocomplete="off" onKeypress="checkTableSelection('${status.index}');"/>
                                                </td>
                                                <td width="30%"><form:input type="text" class="form-control" id="itemName${status.index}" path="kotItem[${status.index}].itemName" readonly="true"/></td>
                                                <td width="10%">
                                                    <form:select class="form-control" id="unit${status.index}" path="kotItem[${status.index}].unit" onchange="changeRate(${status.index});" onblur="getTotal(${status.index});kotCalculation(${fn:length(kotForm.kotItem)})"></form:select>
                                                </td>
                                                <td><form:input type="text" class=" lst form-control" id="quantity${status.index}" path="kotItem[${status.index}].quantity" onkeypress="return isNumberKey(event);" onkeyup="getTotal(${status.index});kotCalculation(${fn:length(kotForm.kotItem)});"/></td>
                                                <td>
                                                    <form:input type="text" class="form-control" id="rate${status.index}" path="kotItem[${status.index}].rate" readonly="true"/>
                                                    <form:input type="hidden" class="form-control" id="auditRate${status.index}" path="kotItem[${status.index}].auditRate" readonly="true"/>
                                                </td>
                                                <td><form:input type="text" class="form-control" id="taxCgstPercent${status.index}" path="kotItem[${status.index}].cgstPercent" readonly="true"/></td>
                                                <td><form:input type="text" class="form-control" id="taxCgst${status.index}" path="kotItem[${status.index}].taxCgst" readonly="true"/></td>
                                                <td><form:input type="text" class="form-control" id="taxSgstPercent${status.index}" path="kotItem[${status.index}].sgstPercent" readonly="true"/></td>
                                                <td><form:input type="text" class="form-control" id="taxSgst${status.index}" path="kotItem[${status.index}].taxSgst" readonly="true"/></td>
                                                <td width="10%">
                                                    <form:input type="text" class="form-control" id="cap${status.index}" path="kotItem[${status.index}].cap" readonly="true"/>
                                                    <form:input type="hidden" class="form-control" id="vap${status.index}" path="kotItem[${status.index}].vap" readonly="true"/>
                                                </td>
                                                <td style="text-align:center;"><a href="#"  class="ace-icon fa fa-trash-o bigger-120 btn-danger" onclick="myFunction(this,${fn:length(kotForm.kotItem)},${status.index})"></a></td>
                                            </tr>
                                            </tbody>
                                            </c:forEach>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div><br>
                    <div class="row">
                        <div class="form-group col-md-4">
                            <label  style="text-align:right">Total CGST</label>
                            <form:input type="text" class="form-control" id="totalCGST" path="kot.totalCgst" readonly="true" required="true"/>
                        </div>
                        <div class="form-group col-md-4">
                            <label  style="text-align:right">Total CGST</label>
                            <form:input type="text" class="form-control" id="totalSGST" path="kot.totalSgst" readonly="true" required="true"/>
                        </div>
                        <div class="form-group col-md-4">
                            <label  style="text-align:right">Total KOT Value</label>
                            <form:input type="text" class="form-control" id="totalKOTValue" path="kot.totalKotvalue" readonly="true" required="true"/>
                        </div>
                    </div>
                    <div class="row">            
                        <div class="form-group  col-sm-2">
                            <input  type="submit" id="btnSubmit" class="btn btn-info" value="Save (F2)" style="float: left;"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>                                        
        </form:form>
    <form:form id="closeBill" method="post" action="CloseBill.html">
        <input type="hidden" name="hiddenTableName" id="hiddenTableName">
        <input type="hidden" name="hiddenTableCategory" id="hiddenTableCategory">
        <input  type="submit" id="btnSubmit" class="btn btn-info" value="Close Bill (F4)" style="float: right;"/>
    </form:form>     


<script>
    getUnit(${fn:length(kotForm.kotItem)});
    getWaiterNameById($('#waiterId').val());
</script>
 <script>

        var i = $('table tr').length;
        $(document).on('keydown', '.lst', function(e) {
          var code = (e.keyCode ? e.keyCode : e.which);
          if (code == 13) {
           e.preventDefault(); 
           addRowforKOT();
          }
        });
    </script> 
   
    <script>
                    

$(':input:enabled:visible:not([readonly]):first').focus();
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
  $('#itemcode${pur}').focus();
</script>     
 
</body>
</html>