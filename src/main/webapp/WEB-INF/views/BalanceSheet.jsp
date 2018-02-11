<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<!DOCTYPE html>
<html>
   
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accounting - Balance Sheet</title>
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
       
  
        <c:set var="listLength" value="${fn:length(listGroup)}"/>
        <c:forEach items="${listGroup}" var="group">
            <c:choose>
                <c:when test="${group.moduleName=='BalanceSheet'}">
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
        <jsp:include page="Header.jsp" />  
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
								<a href="#">Balance Sheet</a>
							</li>
							
						</ul><!-- /.breadcrumb -->

					</div>

					<div class="page-content">
						<!--- ACE Settings for template -->

						<div class="page-header">
							<h1>
								Balance Sheet
								<!-- <small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									Common form elements and layouts
								</small> -->
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<form  action="GetBalanceSheetReport.html" method="POST">
								<div class="row">
                                                                    <div class="form-group col-xs-3">
                                                                  

                                                                     
                                                                                <div class="col-xs-8 col-sm-10">
                                                                                        <div class="input-daterange input-group">
                                                                                                <input type="text"  class="form-control date-picker" id="id-date-picker-1" name="startDate" value="${finalDate}" data-date-format="dd/mm/yyyy"/>
                                                                                                <span class="input-group-addon">
                                                                                                        <i class="fa fa-exchange"></i>
                                                                                                </span>

                                                                                                <input type="text" class="form-control date-picker" id="id-date-picker-1" name="endDate" value="${curDate}" data-date-format="dd/mm/yyyy"/>
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
                                                            <h4> Balance Sheet Summary</h4>
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
                                                                        <td>
                                                                             <table class="table">
                                                                                        <col class="col-md-6">
                                                                                        <col class="col-md-3">
                                                                                        <col class="col-md-3">
                                                                                  <tbody>
                                                                                                
                                                                                           <tr>
                                                                                                <th>Capital Accounts</th>
                                                                                                <th></th>
                                                                                                <th class="text-right">Amount </th>
                                                                                           </tr>
                                                                                          <c:set var="c_sumCrDrAmt" value="0" />
                                                                                           <c:set var="c_totalAmount" value="0" />
                                                                                            <c:forEach items="${SigleGroupLedgerSUM_Capital}" var="c_sum">
                                                                                                
                                                                                                 <c:set var="c_sumCrDrAmt" value="${c_sum[2] - c_sum[1]}" />
                                                                                                 <c:set var="c_totalAmount" value="${c_totalAmount + c_sumCrDrAmt}" />
                                                                                               <tr><td>${c_sum[6]}</td><td>${c_sumCrDrAmt}</td><td class="text-right"></td></tr>
                                                                                            </c:forEach>
                                                                                             <c:forEach items="${SigleGroupLedgerOPening_Capital}" var="cledger">
                                                                                                
                                                                                             <c:set var="c_ledgerOPening" value="${cledger[0]-cledger[1]}" />
                                                                                            
                                                                                            </c:forEach>   
                                                                                          
                                                                                              <c:set var="currentCapitalBalance" value="${c_totalAmount+c_ledgerOPening}" />
                                                                                               <tr><td></td><td></td><td class="text-right">${currentCapitalBalance}</td></tr>
                                                                                        
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
                                                                                                <th>Current Assetss</th>
                                                                                                <th></th>
                                                                                                <th class="text-right">Amount </th>
                                                                                           </tr>
                                                                                          <c:set var="a_sumCrDrAmt" value="0" />
                                                                                           <c:set var="a_totalAmount" value="0" />
                                                                                            <tr><td>Closing Stock</td><td>${closing_stock}</td><td class="text-right"></td></tr>
                                                                                            <c:forEach items="${SigleGroupLedgerSUM_assets}" var="a_sum">
                                                                                                
                                                                                                 <c:set var="a_sumCrDrAmt" value="${a_sum[2] - a_sum[1]}" />
                                                                                                 <c:set var="a_totalAmount" value="${a_totalAmount + a_sumCrDrAmt}" />
                                                                                               <tr><td>${a_sum[6]}</td><td>${a_sumCrDrAmt}</td><td class="text-right"></td></tr>
                                                                                            </c:forEach>
                                                                                             <c:forEach items="${SigleGroupLedgerOPening_assets}" var="Aledger">
                                                                                                
                                                                                             <c:set var="a_ledgerOPening" value="${Aledger[0]-Aledger[1]}" />
                                                                                            
                                                                                            </c:forEach>   
                                                                                        
                                                                                              <c:set var="currentAssetsBalance" value="${a_totalAmount+a_ledgerOPening+closing_stock}" />
                                                                                               <tr><td></td><td></td><td class="text-right">${currentAssetsBalance}</td></tr>
                                                                                      
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
                                                                                                <th>Loans (Liability)</th>
                                                                                                <th > </th>
                                                                                                <th class="text-right">Amount </th>
                                                                                           </tr>
                                                                                          <c:set var="lsumCrDrAmt" value="0" />
                                                                                           <c:set var="ltotalAmount" value="0" />
                                                                                            <c:forEach items="${SigleGroupLedgerSUM_Loan}" var="lsum">
                                                                                                
                                                                                                 <c:set var="lsumCrDrAmt" value="${lsum[1] - lsum[2]}" />
                                                                                                 <c:set var="ltotalAmount" value="${ltotalAmount + lsumCrDrAmt}" />
                                                                                               <tr><td>${lsum[6]}</td><td>${lsumCrDrAmt}</td><td class="text-right"></td></tr>
                                                                                            </c:forEach>
                                                                                             <c:forEach items="${SigleGroupLedgerOPening_Loan}" var="lledger">
                                                                                                
                                                                                             <c:set var="l_ledgerOPening" value="${lledger[0]-lledger[1]}" />
                                                                                            
                                                                                            </c:forEach>   
                                                                                          
                                                                                              <c:set var="currentloanBalance" value="${ltotalAmount+l_ledgerOPening}" />
                                                                                              <tr><td></td><td></td><td class="text-right">${currentloanBalance}</td></tr>
                                                                                        
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
                                                                                                <th>Fixed Assetss</th>
                                                                                                <th></th>
                                                                                                <th class="text-right">Amount </th>
                                                                                           </tr>
                                                                                          <c:set var="fa_sumCrDrAmt" value="0" />
                                                                                           <c:set var="fa_totalAmount" value="0" />
                                                                                            <c:forEach items="${SigleGroupLedgerSUM_f_assets}" var="fa_sum">
                                                                                                
                                                                                                 <c:set var="fa_sumCrDrAmt" value="${fa_sum[2] - fa_sum[1]}" />
                                                                                                 <c:set var="fa_totalAmount" value="${fa_totalAmount + fa_sumCrDrAmt}" />
                                                                                               <tr><td>${fa_sum[6]}</td><td>${fa_sumCrDrAmt}</td><td class="text-right"></td></tr>
                                                                                            </c:forEach>
                                                                                             <c:forEach items="${SigleGroupLedgerOPening_f_assets}" var="fAledger">
                                                                                                
                                                                                             <c:set var="fa_ledgerOPening" value="${fAledger[0]-fAledger[1]}" />
                                                                                            
                                                                                            </c:forEach>   
                                                                                        
                                                                                              <c:set var="currentFixedAssetsBalance" value="${fa_totalAmount+fa_ledgerOPening}" />
                                                                                               <tr><td></td><td></td><td class="text-right">${currentFixedAssetsBalance}</td></tr>
                                                                                        
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
                                                                                                <th>Current Liabilities</th>
                                                                                                <th > </th>
                                                                                                <th class="text-right">Amount </th>
                                                                                           </tr>
                                                                                          <c:set var="cl_sumCrDrAmt" value="0" />
                                                                                           <c:set var="cl_totalAmount" value="0" />
                                                                                            <c:forEach items="${SigleGroupLedgerSUM_liabilities}" var="cl_sum">
                                                                                                
                                                                                                 <c:set var="cl_sumCrDrAmt" value="${cl_sum[1] - cl_sum[2]}" />
                                                                                                 <c:set var="cl_totalAmount" value="${cl_totalAmount + cl_sumCrDrAmt}" />
                                                                                               <tr><td>${cl_sum[6]}</td><td>${cl_sumCrDrAmt}</td><td class="text-right"></td></tr>
                                                                                            </c:forEach>
                                                                                             <c:forEach items="${SigleGroupLedgerOPening_liabilities}" var="cl_ledger">
                                                                                                
                                                                                             <c:set var="cl_ledgerOPening" value="${cl_ledger[0]-cl_ledger[1]}" />
                                                                                            
                                                                                            </c:forEach>   
                                                                                            <c:set var="cl" value="${cl_totalAmount+cl_ledgerOPening}" />
                                                                                              <c:set var="currentLiabilitieseBalance" value="${cl < 0 ? -cl:cl}" />
                                                                                               <tr><td></td><td></td><td class="text-right">${currentLiabilitieseBalance}</td></tr>
                                                                                        
                                                                                        </tbody>
                                                                                
                                                                               </table>
                                                                        </td>
                                                                        <td  >
                                                                            
                                                                           
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
                                                                                                <th>Profit and Loss A/c</th>
                                                                                                <th > </th>
                                                                                                <th class="text-right">Amount </th>
                                                                                           </tr>
                                                                                      
                                                                                          
                                                                                               <tr><td>Opening Balance</td><td>${Profit_openingBalanceOfLedger}</td><td class="text-right"></td></tr>
                                                                                      
                                                                                           
                                                                                             
                                                                                               <tr><td>Current Period</td><td>${netProfit}</td><td class="text-right"></td></tr>
                                                                                               <tr><td></td><td></td><td class="text-right">${Profit_openingBalanceOfLedger+netProfit}</td></tr>
                                                                                        
                                                                                        </tbody>    
                                                                                
                                                                               </table>
                                                                        </td>
                                                                         <td >
                                                                            
                                                                                  
                                                                        </td>
                                                                      
                                                                </tr>
                                                                 <c:set var="left" value="${currentCapitalBalance+currentloanBalance+currentLiabilitieseBalance+netProfit}" />
                                                                 <c:set var="right" value="${currentAssetsBalance+currentFixedAssetsBalance}" />
                                                               
                                                                   <tr>
                                                                      
                                                                        <td class="text-right" >
                                                                         <div style="float:left"></div><div style="float:right">${left}</div>                                                                        
                                                                        </td>
                                                                        <td class="text-right" >
                                                                       <div style="float:left"></div><div style="float:right">${right}</div>
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

		<!-- ace scripts -->
		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>
                
                  <!-- Voucher based java scripts !-->
                <script src="assets/accounting-js/voucher.js"></script>
                
               
                
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
  
