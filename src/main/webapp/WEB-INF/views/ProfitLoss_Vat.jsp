<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"prefix="fn" %> 

<!DOCTYPE html>
<html>
   
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accounting - Profit and Loss</title>
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
    </head>
    <body class="no-skin">
         <jsp:include page="Header.jsp" />
  
        <c:set var="listLength" value="${fn:length(listGroup)}"/>
        <c:forEach items="${listGroup}" var="group">
            <c:choose>
                <c:when test="${group.moduleName=='Bank Clearance'}">
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
        
         <div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>
        
        <div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="#">Home</a>
							</li>

							<li>
								<a href="#">Profit and Loss VAT Formula</a>
							</li>
							
						</ul><!-- /.breadcrumb -->

					</div>

					<div class="page-content">
						<!--- ACE Settings for template -->

						<div class="page-header">
							<h1>
								Profit and Loss 
								<!-- <small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									Common form elements and layouts
								</small> -->
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<form  action="GetPFVAT.html" method="POST">
								<div class="row">
                                                                    <div class="form-group col-xs-3">
                                                                  

                                                                     
                                                                                <div class="col-xs-8 col-sm-10">
                                                                                        <div class="input-daterange input-group">
                                                                                                <input type="text"  class="form-control date-picker" id="id-date-picker-1" name="startDate" data-date-format="yyyy-mm-dd"/>
                                                                                                <span class="input-group-addon">
                                                                                                        <i class="fa fa-exchange"></i>
                                                                                                </span>

                                                                                                <input type="text" class="form-control date-picker" id="id-date-picker-1" name="endDate" data-date-format="yyyy-mm-dd"/>
                                                                                        </div>
                                                                                </div>
                                                                     
                                                                    </div>
                                                                 <div class="form-group col-xs-3">
                                                                   
                                                                      
                                                                                <div class="col-xs-8 col-sm-10">
                                                                                       
                                                                                               <input type="submit" class="btn btn-success" value="Get Details">
                                                                                     
                                                                                </div>
                                                                        </div>
                                                                    </div>
                                                              
								
									
									
									
									
									</form>
								</div>
								</div><!-- PAGE CONTENT ENDS -->
                                                                
                                                           
                                                                
                                                        <div class="col-xs-12">
                                                            <h4> Profit and Loss Summary</h4>
                                                      <table class="table  table-bordered table-hover">

                                                             <thead>
                                                                 
                                                                   
                                                                    <tr>


                                                                           <th>Particulars</th>
                                                                           <th>Particulars</th>

                                                                    </tr>
							    </thead>
                                                             <colgroup>
                                                                <col class="col-md-6">
                                                                <col class="col-md-6">
                                                            </colgroup><!-- Gross Profit and Loss -->
                                                                <tbody>
                                                                   

                                                           

                                                                <!-- Net Profit and Loss -->
                                                                <tr>
                                                                        <td >
                                                                            
                                                                                <table class="table">
                                                                                        <col class="col-md-6">
                                                                                        <col class="col-md-3">
                                                                                        <col class="col-md-3">
                                                                                        <tbody>
                                                                                                
                                                                                            <tr>
                                                                                                <th>Opening Stock</th>
                                                                                                <th > </th>
                                                                                                <th class="text-right">Amount </th>
                                                                                           </tr>
                                                                                             <c:forEach items="${openingStock_item_master}" var="op">
                                                                                                 <c:set var="openingStockValue" value="${openingStockValue + op[1]}" />
                                                                                             
                                                                                            </c:forEach>
                                                                                            <c:forEach items="${openingStack}" var="op">
                                                                                                 <c:set var="openingTot" value="${openingTot + op[4]}" />
                                                                                               <tr><td>${op[2]}</td><td>${op[4]}</td><td class="text-right"></td></tr>
                                                                                            </c:forEach>
                                                                                               <c:set var="openingTotStockOverAll" value="${openingTot+openingStockValue}" />
                                                                                               <tr><td></td><td></td><td class="text-right">${openingTotStockOverAll}</td></tr>
                                                                                        
                                                                                        </tbody>
                                                                               </table>
                                                                        </td>
                                                                         <td >
                                                                            <table class="table">
                                                                                        <col class="col-md-6">
                                                                                        <col class="col-md-3">
                                                                                        <col class="col-md-3">
                                                                                        <tbody>
                                                                                                
                                                                                            <tr>
                                                                                                <th>Sales Accounts</th>
                                                                                                <th></th>
                                                                                                <th class="text-right">Amount </th>
                                                                                           </tr>
                                                                                          <c:set var="s_sumCrDrAmt" value="0" />
                                                                                           <c:set var="s_totalAmount" value="0" />
                                                                                            <c:forEach items="${SigleGroupLedgerSUM_S}" var="s_sum">
                                                                                                
                                                                                                 <c:set var="s_sumCrDrAmt" value="${s_sum[1] - s_sum[2]}" />
                                                                                                 <c:set var="s_totalAmount" value="${s_totalAmount + s_sumCrDrAmt}" />
                                                                                               <tr><td>${s_sum[6]}</td><td>${s_sumCrDrAmt < 0 ? -s_sumCrDrAmt:s_sumCrDrAmt}</td><td class="text-right"></td></tr>
                                                                                            </c:forEach>
                                                                                             <c:forEach items="${SigleGroupLedgerOPening_S}" var="sledger">
                                                                                                
                                                                                             <c:set var="s_ledgerOPening" value="${sledger[0]-sledger[1]}" />
                                                                                            
                                                                                            </c:forEach>   
                                                                                            <c:forEach items="${SigleGroupOPening_S}" var="s_opening">
                                                                                                
                                                                                             <c:set var="s_previousBalance" value="${s_opening[1]-s_opening[0]}" />
                                                                                            
                                                                                            </c:forEach>    
                                                                                              <c:set var="currentsales_Negative_Balance" value="${s_totalAmount+s_ledgerOPening+s_previousBalance}" />
                                                                                              <c:set var="currentsalesBalance" value="${currentsales_Negative_Balance < 0 ? -currentsales_Negative_Balance:currentsales_Negative_Balance}" />
                                                                                               <tr><td></td><td></td><td class="text-right">${currentsalesBalance}</td></tr>
                                                                                        
                                                                                        </tbody>
                                                                               </table>
                                                                             
                                                                        </td>
                                                                      
                                                                </tr>
                                                                           <tr>
                                                                        <td >
                                                                            
                                                                                <table class="table">
                                                                                        <col class="col-md-6">
                                                                                        <col class="col-md-3">
                                                                                        <col class="col-md-3">
                                                                                        <tbody>
                                                                                                
                                                                                            <tr>
                                                                                                <th>Purchase Accounts</th>
                                                                                                <th > </th>
                                                                                                <th class="text-right">Amount </th>
                                                                                           </tr>
                                                                                          <c:set var="psumCrDrAmt" value="0" />
                                                                                           <c:set var="ptotalAmount" value="0" />
                                                                                            <c:forEach items="${SigleGroupLedgerSUM_P}" var="psum">
                                                                                                
                                                                                                 <c:set var="psumCrDrAmt" value="${psum[1] - psum[2]}" />
                                                                                                 <c:set var="ptotalAmount" value="${ptotalAmount + psumCrDrAmt}" />
                                                                                               <tr><td>${psum[6]}</td><td>${psumCrDrAmt}</td><td class="text-right"></td></tr>
                                                                                            </c:forEach>
                                                                                             <c:forEach items="${SigleGroupLedgerOPening_P}" var="pledger">
                                                                                                
                                                                                             <c:set var="ledgerOPening" value="${pledger[0]-pledger[1]}" />
                                                                                            
                                                                                            </c:forEach>   
                                                                                          
                                                                                              <c:set var="currentPurchaseBalance" value="${ptotalAmount+ledgerOPening}" />
                                                                                              <tr><td></td><td></td><td class="text-right">${currentPurchaseBalance}</td></tr>
                                                                                        
                                                                                        </tbody>
                                                                               </table>
                                                                        </td>
                                                                         <td >
                                                                            
                                                                              <table class="table">
                                                                                        <col class="col-md-6">
                                                                                        <col class="col-md-3">
                                                                                        <col class="col-md-3">
                                                                                        <tbody>
                                                                                            <tr>
                                                                                                <th>Closing Stock</th>
                                                                                                <th > </th>
                                                                                                <th class="text-right"> Amount</th>
                                                                                           </tr>
                                                                                            <c:forEach items="${closing_item_master_endDate}" var="clm">
                                                                                               <c:set var="ClosingTot_im" value="${ClosingTot_im + clm[1]}" />
                                                                                              
                                                                                            </c:forEach>
                                                                                            <tr><td>Closing Item Opening</td><td>${ClosingTot_im}</td><td class="text-right"></td></tr>
                                                                                            <c:forEach items="${ClosingStock}" var="cl">
                                                                                               <c:set var="ClosingTot" value="${ClosingTot + cl[4]}" />
                                                                                               <tr><td>${cl[2]}</td><td>${cl[4]}</td><td class="text-right"></td></tr>
                                                                                            </c:forEach>
                                                                                             <c:forEach items="${StockjournalsOutValue}" var="stout">
                                                                                               <c:set var="stoutTot" value="${ stout[3]}" />
                                                                                             
                                                                                            </c:forEach>   
                                                                                               <!--INclude OPening Stock From Item Master--> 
                                                                                                <c:set var="OverAllClosingStock" value="${ClosingTot+stoutTot+ClosingTot_im}" />
                                                                                                <tr><td></td><td></td><td class="text-right">${OverAllClosingStock}</td></tr> 
                                                                                        </tbody>
                                                                               </table>  
                                                                        </td>
                                                                      
                                                                </tr>
                                                                
                                                                   <tr>
                                                                        <td >
                                                                            
                                                                                <table class="table">
                                                                                                <col class="col-md-6">
                                                                                        <col class="col-md-3">
                                                                                        <col class="col-md-3">
                                                                                                <tbody>
                                                                                                
                                                                                            <tr>
                                                                                                <th>Direct Expense</th>
                                                                                                <th > </th>
                                                                                                <th class="text-right">Amount </th>
                                                                                           </tr>
                                                                                          <c:set var="expense_sumCrDrAmt" value="0" />
                                                                                           <c:set var="expense_totalAmount" value="0" />
                                                                                            <c:forEach items="${SigleGroupLedgerSUM_Dexpense}" var="dexpense_sum">
                                                                                                
                                                                                                 <c:set var="dexpense_sumCrDrAmt" value="${dexpense_sum[1] - dexpense_sum[2]}" />
                                                                                                 <c:set var="dexpense_totalAmount" value="${dexpense_totalAmount + dexpense_sumCrDrAmt}" />
                                                                                               <tr><td>${dexpense_sum[6]}</td><td>${dexpense_sumCrDrAmt}</td><td class="text-right"></td></tr>
                                                                                            </c:forEach>
                                                                                             <c:forEach items="${SigleGroupLedgerOPening_Dexpense}" var="dexpense_ledger">
                                                                                                
                                                                                             <c:set var="dexpense_ledgerOPening" value="${dexpense_ledger[0]-dexpense_ledger[1]}" />
                                                                                            
                                                                                            </c:forEach>   
                                                                                           
                                                                                              <c:set var="currentDExpenseBalance" value="${dexpense_totalAmount+dexpense_ledgerOPening}" />
                                                                                               <tr><td></td><td></td><td class="text-right">${currentDExpenseBalance}</td></tr>
                                                                                        
                                                                                        </tbody>
                                                                                
                                                                               </table>
                                                                        </td>
                                                                        <td  >
                                                                            
                                                                           
                                                                        </td>
                                                                      
                                                                </tr>
                                                                 <c:set var="Open_Purchase_DirectExpense" value="${currentDExpenseBalance+currentPurchaseBalance+openingTotStockOverAll}" />
                                                                 <c:set var="Close_Sales" value="${OverAllClosingStock+currentsalesBalance}" />
                                                                 <c:set var="gross_Profit" value="${Close_Sales-Open_Purchase_DirectExpense}" />
                                                                 <c:set var="Open_Purchase_DirectExpense_gross_Profit" value="${Open_Purchase_DirectExpense+gross_Profit}" />
                                                                 <tr >
                                                                        
                                                                     <td >
                                                                           
                                                                       <div style="float:left">Gross Profit C/O</div><div style="float:right">${gross_Profit}</div>
                                                                       </td>
                                                                       <td class="text-right" >
                                                                       
                                                                       </td>
                                                                     
                                                                      
                                                                  </tr>
                                                                  
                                                                   <tr>
                                                                      
                                                                        <td class="text-right" >
                                                                         <div style="float:left"></div><div style="float:right">${Open_Purchase_DirectExpense_gross_Profit}</div>                                                                        
                                                                        </td>
                                                                        <td class="text-right" >
                                                                       <div style="float:left"></div><div style="float:right">${Close_Sales}</div>
                                                                        </td>
                                                                      
                                                                  </tr>
                                                                   <tr>
                                                                      
                                                                        <td class="text-right" >
                                                                         <div style="float:left"></div><div style="float:right"></div>                                                                        
                                                                        </td>
                                                                        <td class="text-right" >
                                                                       <div style="float:left">Gross Profit B/F</div><div style="float:right">${gross_Profit}</div>
                                                                        </td>
                                                                      
                                                                  </tr>
                                                                      <tr>
                                                                        <td >
                                                                            
                                                                                <table class="table">
                                                                                       <col class="col-md-6">
                                                                                        <col class="col-md-3">
                                                                                        <col class="col-md-3">
                                                                                      <tbody>
                                                                                                
                                                                                            <tr>
                                                                                                <th>Indirect Expense</th>
                                                                                                <th > </th>
                                                                                                <th class="text-right">Amount </th>
                                                                                           </tr>
                                                                                          <c:set var="expense_sumCrDrAmt" value="0" />
                                                                                           <c:set var="expense_totalAmount" value="0" />
                                                                                            <c:forEach items="${SigleGroupLedgerSUM_expense}" var="expense_sum">
                                                                                                
                                                                                                 <c:set var="expense_sumCrDrAmt" value="${expense_sum[1] - expense_sum[2]}" />
                                                                                                 <c:set var="expense_totalAmount" value="${expense_totalAmount + expense_sumCrDrAmt}" />
                                                                                               <tr><td>${expense_sum[6]}</td><td>${expense_sumCrDrAmt}</td><td class="text-right"></td></tr>
                                                                                            </c:forEach>
                                                                                             <c:forEach items="${SigleGroupLedgerOPening_expense}" var="expense_ledger">
                                                                                                
                                                                                             <c:set var="expense_ledgerOPening" value="${expense_ledger[0]-expense_ledger[1]}" />
                                                                                            
                                                                                            </c:forEach>   
                                                                                           
                                                                                              <c:set var="currentExpenseBalance" value="${expense_totalAmount+expense_ledgerOPening}" />
                                                                                               <tr><td></td><td></td><td class="text-right">${currentExpenseBalance}</td></tr>
                                                                                        
                                                                                        </tbody>    
                                                                                
                                                                               </table>
                                                                        </td>
                                                                         <td >
                                                                            
                                                                                    <table class="table">
                                                                                        <col class="col-md-6">
                                                                                        <col class="col-md-3">
                                                                                        <col class="col-md-3">
                                                                                       
                                                                                         <tbody>
                                                                                                
                                                                                            <tr>
                                                                                                <th>Indirect incomes</th>
                                                                                                <th > </th>
                                                                                                <th class="text-right">Amount </th>
                                                                                           </tr>
                                                                                          <c:set var="income_sumCrDrAmt" value="0" />
                                                                                           <c:set var="income_totalAmount" value="0" />
                                                                                            <c:forEach items="${SigleGroupLedgerSUM_income}" var="income_sum">
                                                                                                
                                                                                                 <c:set var="income_sumCrDrAmt" value="${income_sum[1] - income_sum[2]}" />
                                                                                                 <c:set var="income_totalAmount" value="${income_totalAmount + income_sumCrDrAmt}" />
                                                                                               <tr><td>${income_sum[6]}</td><td>${income_sumCrDrAmt}</td><td class="text-right"></td></tr>
                                                                                            </c:forEach>
                                                                                             <c:forEach items="${SigleGroupLedgerOPening_income}" var="income_ledger">
                                                                                                
                                                                                             <c:set var="income_ledgerOPening" value="${income_ledger[0]-income_ledger[1]}" />
                                                                                            
                                                                                            </c:forEach>   
                                                                                           
                                                                                              <c:set var="currentIncomeBalance" value="${income_totalAmount+income_ledgerOPening}" />
                                                                                               <tr><td></td><td></td><td class="text-right">${currentIncomeBalance}</td></tr>
                                                                                        
                                                                                        </tbody>
                                                                               </table> 
                                                                        </td>
                                                                      
                                                                </tr>
                                                                 <c:set var="right_total" value="${gross_Profit+currentIncomeBalance}" />
                                                                 <c:set var="NetProfit" value="${right_total-currentExpenseBalance}" />
                                                                 <c:set var="LeftsideTotal" value="${currentExpenseBalance+NetProfit}" />
                                                                 
                                                                 <tr >
                                                                        
                                                                     <td >
                                                                           
                                                                       <div style="float:left">Net Profit</div><div style="float:right">${NetProfit}</div>
                                                                       </td>
                                                                       <td class="text-right" >
                                                                       
                                                                       </td>
                                                                     
                                                                      
                                                                  </tr>
                                                                  
                                                                   <tr>
                                                                      
                                                                        <td class="text-right" >
                                                                         <div style="float:left"></div><div style="float:right">${LeftsideTotal}</div>                                                                        
                                                                        </td>
                                                                        <td class="text-right" >
                                                                       <div style="float:left"></div><div style="float:right">${right_total}</div>
                                                                        </td>
                                                                      
                                                                  </tr>
                                                         

                                                   
                                                        </tbody>
                                                      </table>                                      
                                                            
                                                    </div>
                                                    </div>
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
                                         <jsp:include page="Footer.jsp" />
    </div>
       
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

		<script src="assets/js/moment.min.js"></script>

		<script src="assets/js/jquery.knob.min.js"></script>
		<script src="assets/js/autosize.min.js"></script>
		<script src="assets/js/jquery.inputlimiter.min.js"></script>
		<script src="assets/js/jquery.maskedinput.min.js"></script>
		<script src="assets/js/bootstrap-tag.min.js"></script>

		<!-- ace scripts -->
		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>
                
           
                
              
                
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
				$('.date-picker').datepicker({
					autoclose: true,
					todayHighlight: true
				})
				//show datepicker when clicking on the icon
				.next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
			});
		</script>
    </body>
</html>
  
