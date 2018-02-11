<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<!DOCTYPE html>
<html>
   
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accounting - Account Reports</title>
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
                <c:when test="${group.moduleName=='Accounts Reports'}">
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
								<a href="#">Account Reports</a>
							</li>
							
						</ul><!-- /.breadcrumb -->

					</div>

					<div class="page-content">
						<!--- ACE Settings for template -->

						<div class="page-header">
							<h1>
								Account Reports
								<!-- <small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									Common form elements and layouts
								</small> -->
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<form  action="GetEntryReports.html" method="POST">
								<div class="row">
                                                                    <div class="form-group col-xs-3">
                                                                    <label>Date Range</label>

                                                                        <div class="row">
                                                                                <div class="col-xs-8 col-sm-10">
                                                                                        <div class="input-daterange input-group">
                                                                                            <input type="text"  class="form-control date-picker" id="id-date-picker-1" name="startDate" data-date-format="yyyy-mm-dd" value="${gsFromDate}"  required/>
                                                                                                <span class="input-group-addon">
                                                                                                        <i class="fa fa-exchange"></i>
                                                                                                </span>

                                                                                                <input type="text" class="form-control date-picker" id="id-date-picker-1" name="endDate" data-date-format="yyyy-mm-dd" value="${gsToDate}"  required/>
                                                                                        </div>
                                                                                </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group col-xs-3 hideForLedger1">
                                                                                    <label class="col-sm-8" for="form-field-1"> List of Ledgers Accounts </label>

                                                                                    <div class="col-sm-10" id="replaceLedger">
                                                                                        <select class="form-control changeGetLedger mySelects" id="changeGetLedger" name="byLedger" >
                                                                                            <option value="">---choose---</option>
                                                                                            <c:forEach items="${listAllLedger}" var="list">
                                                                                                <option value="${list.idLedger}" ${list.idLedger==byLedger_id ? 'Selected':''}>${list.nameOfLedger}</option>
                                                                                            </c:forEach>
                                                                                        </select>
                                                                                    </div>
                                                                            </div>
                                                                        <div class="form-group col-xs-3 hideForLedger2 ">
                                                                                    <label class="col-sm-8" for="form-field-1"> List of Account Groups</label>

                                                                                    <div class="col-sm-10">
                                                                                        <select class="form-control mySelects" id="changeGetAcGroups" name="byAccountGroups">
                                                                                            <option value="">---choose---</option>
                                                                                            <option value="0">Primary</option>
                                                                                            
                                                                                            <c:forEach items="${accountGroupList}" var="list">
                                                                                                <option value="${list.idAccount}" ${list.idAccount==byAccountGroups ? 'Selected':''}>${list.accountGroupName}</option>
                                                                                            </c:forEach>
                                                                                        </select>
                                                                                    </div>
                                                                            </div>
<!--                                                                        <div class="form-group col-xs-3 " >
                                                                                    <label class="col-sm-8" for="form-field-1"> List of Account  </label>

                                                                                    <div class="col-sm-10">
                                                                                        <select name="id" class="form-control getAccountGroups" id="getAccountGroups">
                                                                                            <option value="-1">---choose---</option>
                                                                                        </select>
                                                                                    </div>
                                                                        </div>-->

                                                                    </div>
                                                                    <div class="row">
										<div class="col-xs-12 col-sm-5">
											<div class="control-group">
												<div class="radio">
													<label>
														<input type="radio" value="Daily" class="ace input-lg" name="reportByType" value="1"  ${type==1 ? 'Checked=true':''}/>
														<span class="lbl bigger-120"> Daily Summary</span>
													</label>
												</div>

												

												
                                                                                                
                                                                                        </div>
										</div>
                                                                                <div class="col-xs-12 col-sm-4">
											<div class="control-group">
												<div class="radio">
													<label>
														<input type="radio" value="Monthly" class="ace input-lg" name="reportByType" value="2"  ${type==2 ? 'Checked=true':''}/>
														<span class="lbl bigger-120"> Monthly Summary</span>
													</label>
												</div>
<!--                                                                                                <div class="checkbox">
													<label class="block">
														<input name="form-field-checkbox" type="checkbox" class="ace input-lg">
														<span class="lbl bigger-120"> Sales Register</span>
													</label>
												</div>

												<div class="checkbox">
													<label class="block">
														<input name="form-field-checkbox" type="checkbox" class="ace input-lg">
														<span class="lbl bigger-120"> Purchase Register</span>
													</label>
												</div>-->
                                                                                                
                                                                                                
											</div>
										</div>
<!--                                                                                <div class="col-xs-12 col-sm-5">
											<div class="control-group">
												<div class="radio">
													<label>
														<input type="radio" class="ace input-lg" name="reportByType"/>
														<span class="lbl bigger-120"> VAA MCA Report</span>
													</label>
												</div>
                                                                                            
                                                                                                <div class="checkbox">
													<label class="block">
														<input name="form-field-checkbox" type="checkbox" class="ace input-lg">
														<span class="lbl bigger-120"> Account Status</span>
													</label>
												</div>
                                                                                            
                                                                                                <div class="checkbox">
													<label class="block">
														<input name="form-field-checkbox" type="checkbox" class="ace input-lg">
														<span class="lbl bigger-120"> Address Book</span>
													</label>
												</div>
                                                                                                
											</div>
										</div>-->
                                                                            </div>
								
									
									<div class="space-4"></div>
									<div class="space-4"></div>
									<div style="text-align:center">
                                                                            <input type="submit" class="btn btn-success" value="Submit">
									</div>
									
									</form>
								</div>
								</div><!-- PAGE CONTENT ENDS -->
                                                                
                                                                <div class="space-4"></div> <div class="space-4"></div>
                                                                
                                                        <div class="row">
                                                            <div class="col-xs-12">

                                                                    <!-- <div class="clearfix">
                                                                            <div class="pull-right tableTools-container"></div>
                                                                    </div> -->
                                                                    <div class="table-header">
                                                                            Account Groups
                                                                    </div>

                                                                    <!-- div.table-responsive -->

                                                                    <!-- div.dataTables_borderWrap -->
                                                                    <div>
                                                                        <c:if test="${byLedger!=null}">
                                                                            <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                                                                                    <thead>
                                                                                            <tr>
                                                                                                    <th rowspan="2">S.No</th>
                                                                                                    <th rowspan="2">Particulars</th>
                                                                                                    <th rowspan="2">Voucher Type </th>
                                                                                                    <th rowspan="2">Voucher No </th>
                                                                                                    <th rowspan="2">Date</th>
                                                                                                    <th style="text-align:center;" colspan="2"> Amount </th>
                                                                                                    <th rowspan="2"> Closing Amount</th>
                                                                                                  
                                                                                                    
                                                                                            </tr>
                                                                                                    <tr>           
                                                                                                              <th> DR </th>
                                                                                                              <th> CR </th>
                                                                                                           
                                                                                                            
                                                                                                    </tr>
                                                                                    </thead>

                                                                                    <tbody>
                                                                                        <tr>
                                                                                            <c:if test="${ledgerOpeningBalance<0}">
                                                                                               <td colspan="7"><b>By Opening Balance</b></td> 
                                                                                               <td> ${ledgerOpeningBalance < 0 ? -ledgerOpeningBalance:ledgerOpeningBalance} CR</td>
                                                                                            </c:if>
                                                                                            <c:if test="${ledgerOpeningBalance>=0}">
                                                                                               <td colspan="7"><b>To Opening Balance</b></td> 
                                                                                               <td> ${ledgerOpeningBalance} DR</td>
                                                                                            </c:if>
                                                                                     
                                                                                        </tr>
                                                                                        <c:set var="openingBal" value="${ledgerOpeningBalance}"/>
                                                                                        
                                                                                    <c:forEach items="${byDateAndLedger}" var="list" varStatus="indexId">
                                                                                            <tr>
                                                                                                    <td>${indexId.count}</td>
                                                                                                    <td>${list[0]}</td>
                                                                                                    <td>${list[2]}</td>
                                                                                                    <td>${list[8]}</td>
                                                                                                    <td>${list[1]}</td>
                                                                                                    <td>${list[3]}</td>
                                                                                                    <td>${list[4]}</td>                                                                                              
                                                                                                    <!--set rows closing Balance-->
                                                                                                    <c:if test="${list[7]>0}">                                                                                                      
                                                                                                        <td>${list[7]} CR</td>
                                                                                                     </c:if>
                                                                                                     <c:if test="${list[6]>0}">                                                                                                     
                                                                                                        <td>${list[6]} DR</td>
                                                                                                     </c:if>
                                                                                                    
                                                                                                     <c:if test="${list[7] eq 0 && list[6] eq 0}">                                                                                                      
                                                                                                        <td>0</td>
                                                                                                     </c:if>
                                                                                                   
                                                                                                   
                                                                                            </tr>
                                                                                            <c:set var="totalDr" value="${list[6]}"/>
                                                                                            <c:set var="totalCr" value="${list[7]}"/>
                                                                                    </c:forEach>
                                                                                            
                                                                                             <tr>
                                                                                                <td colspan="7"><b>To Closing Balance</b></td> 
                                                                                                 <!--set closing Balance-->
                                                                                                     <c:if test="${totalDr>0}">                                                                                                     
                                                                                                        <td>${totalDr} DR</td>
                                                                                                     </c:if>
                                                                                                     <c:if test="${totalCr>0}">                                                                                                      
                                                                                                        <td>${totalCr} CR</td>
                                                                                                     </c:if>
                                                                                                     <c:if test="${totalDr eq 0 && totalCr eq 0}">                                                                                                      
                                                                                                        <td>0</td>
                                                                                                     </c:if>
                                                                                               
                                                                                            </tr>
                                                                                    </tbody>
                                                                            </table>
                                                                    </c:if>
                                                                    <c:if test="${byGroup!=null}">
                                                                            <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                                                                                    <thead>
                                                                                            <tr>
                                                                                                    <th rowspan="2">S.No</th>
                                                                                                    <th rowspan="2">Group</th>
                                                                                                    <th rowspan="2"Particulars</th>
                                                                                                    <th rowspan="2">Voucher Type </th>
                                                                                                    <th rowspan="2">Date</th>
                                                                                                    <th style="text-align:center;" colspan="2"> Amount </th>
                                                                                                    <th rowspan="2"> Closing Amount</th>
                                                                                            </tr>
                                                                                            <tr>           
                                                                                                              <th> DR </th>
                                                                                                              <th> CR </th>
                                                                                                            
                                                                                                            
                                                                                            </tr>
                                                                                    </thead>

                                                                                    <tbody>
                                                                                       <tr>
                                                                                            <c:if test="${openingBalanceOfEntries>=0}">
                                                                                            <td colspan="7"><b>Group Previous Opening Balance</b></td>
                                                                                               <td> ${openingBalanceOfEntries} DR</td>
                                                                                            </c:if>
                                                                                            <c:if test="${openingBalanceOfEntries<0}">
                                                                                               <td colspan="7"><b>Opening Balance </b></td>
                                                                                               <td> ${openingBalanceOfEntries < 0 ? -openingBalanceOfEntries:openingBalanceOfEntries} CR</td>
                                                                                            </c:if>
                                                                                         
                                                                                     
                                                                                        </tr>
                                                                                     
                                                                                <c:forEach items="${AccountGroupLedger}" var="agl" varStatus="loop">
                                                                                     <c:set var="totalDr" value="${totalDr+agl[2]}"/>
                                                                                        <c:set var="totalCr" value="${totalCr+agl[1]}"/>
                                                                                          <c:set var="closeDr" value="${agl[9]}"/>
                                                                                        <c:set var="closeCr" value="${agl[10]}"/>
                                                                                  
                                                                                            <tr>
                                                                                                    <td>${loop.count}</td>
                                                                                                    <td>${agl[4]}</td>
                                                                                                    <td>${agl[7]}</td>
                                                                                                    <td>${agl[8]}</td>
                                                                                                    <td>${agl[3]}</td>
                                                                                                    <td>${agl[2]}</td>
                                                                                                    <td>${agl[1]}</td>
                                                                                                     <!--set rows closing Balance-->
                                                                                                    <c:if test="${agl[9]>0}">                                                                                                      
                                                                                                        <td>${agl[9]} DR</td>
                                                                                                     </c:if>
                                                                                                     <c:if test="${agl[10]>0}">                                                                                                     
                                                                                                        <td>${agl[10]} CR</td>
                                                                                                     </c:if>
                                                                                                    
                                                                                                     <c:if test="${agl[9] eq 0 && agl[10] eq 0}">                                                                                                      
                                                                                                        <td>0</td>
                                                                                                     </c:if>
                                                                                                 
                                                                                                
                                                                                            </tr>
                                                                                  
                                                                                 </c:forEach>
                                                                                             <tr>
                                                                                                <td colspan="5"><b>Total </b></td>
                                                                                                <td id="dr1"><b>${totalDr}</b></td>
                                                                                                <td id="cr1"><b>${totalCr}</b></td>
                                                                                                <td id="dr1"><b>-</b></td>
                                                                                               
                                                                                             
                                                                                            </tr>
                                                                                            
                                                                                         
                                                                                              <c:if test="${openingBalanceOfLedger>=0}">
                                                                                               <c:set var="totalClosing" value="${openingBalanceOfLedger}"/>
                                                                                        
                                                                                            
                                                                                              </c:if>
                                                                                              <c:if test="${openingBalanceOfLedger<0}">
                                                                                                
                                                                                                <c:set var="totalClosing" value="${openingBalanceOfLedger}"/>
                                                                                                 
                                                                                             </c:if>
                                                                                
                                                                                            <tr>
                                                                                                <td colspan="7"><b>Total Opening Ledger Balance </b></td>
                                                                                                <c:if test="${totalClosing>0}">
                                                                                                 
                                                                                                   <td> ${totalClosing} DR</td>
                                                                                                </c:if>
                                                                                                 <c:if test="${totalClosing<0}">
                                                                                                  
                                                                                                    <td>${totalClosing < 0 ? -totalClosing:totalClosing} CR</td>
                                                                                                 </c:if>
                                                                                                
                                                                                            </tr>
                                                                                    </tbody>
                                                                            </table>
                                                                    </c:if>
                                                                    </div>
                                                            </div>
                                                                                            
                                                            
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
                
                <script>
                        $(document).ready(function()
                        {
                            $(function() {
                                var idDr1 = $('#dr1').text();
//                                alert(idDr1);
                                var idDr2 = $('#dr2').text();
                                var idCr1 = $('#cr1').text();
                                var idCr2 = $('#cr2').text();
                                
                                var totDr = parseInt(idDr1)+parseInt(idDr2);
                                var totCr = parseInt(idCr1)+parseInt(idCr2);
//                                alert(totDr);
                                $('#totDr').html(totDr.toFixed(1));
                                $('#totCr').html(totCr.toFixed(1));
                            });
                            
                        });
                        $('#changeGetAcGroups').change(function() {
                      
                        $.ajax({
                            type:"GET",
                            url : "SelectLedgerDropDownBasedOnGroup.html",
                            data : "parentId_group="+$(this).val(),
                            async: false,
                            success : function(response) {
                               $('#replaceLedger').html(response)
                               // return response;
                            },
                            error: function() {
                                alert('Error occured');
                            }
                        });
                      
                    });
                    
                </script>
                
		<!-- inline scripts related to this page -->
		<script type="text/javascript">

				$('.date-picker').datepicker({
					autoclose: true,
					todayHighlight: true
				})
				//show datepicker when clicking on the icon
				$('form').submit(function(e) {
    if($("input:checked").length == 0){
    alert('please select  atleast one radio');
    return false;
    }
   if(document.getElementById('changeGetAcGroups').value !== '' || document.getElementById('changeGetLedger').value !== ''  )
    {
       return true;
    }
    else
    {

    alert('Please select at least one DropDown');
    return false;
    }
});
		</script>
    </body>
</html>
  
