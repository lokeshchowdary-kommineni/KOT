

<%@page import="com.accounting.service.CompanyService"%>
<%@page import="com.accounting.dao.CompanyDao"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.accounting.bean.CompanyInformation"%>
<% response.setHeader("Cache-Control","no-cache");//HTTP 1.1 
 response.setHeader("Pragma","no-cache");//HTTP 1.0 
 response.setDateHeader ("Expires",0);//prevents caching at the proxy server  %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="my" uri="/WEB-INF/tlds/myFunctions.tld" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${sessionScope.logInOut == null || sessionScope.logInOut == 0}">
    <c:redirect url="Index.html"/>
</c:if>


<html>
    <head>
<script src="assets/accounting-js/jquery.tablenav.min.js"></script>  
    </head>
    <body>
<div id="navbar" class="navbar navbar-default          ace-save-state">
			<div class="navbar-container ace-save-state" id="navbar-container">
				<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
					<span class="sr-only">Toggle sidebar</span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>
				</button>
                            
                            <div class="navbar-header pull-left" >
                                <a href="#" class="navbar-brand">
						<small>    
                                        
                                                   <!--<div class="col-md-4">-->                                        
                                                                                    <!--<div class="image-crop">-->
                                                                                        <img src="assets/images/logo-kot.png"  style="width: 30px; height: 32px;"/>
                                                                                    <!--</div>-->                                                                        
                                                   <!--</div>-->
                                             
							KOT Ver 1.0
						</small>
					</a>
				</div>

				<div class="navbar-buttons navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						

						

						

						<li class="light-blue dropdown-modal">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<!--<img class="nav-user-photo" src="assets/images/avatars/user.jpg" alt="Jason's Photo" />-->
								<span class="user-info">
									<small>Welcome,</small>
	                                                                   ${userName}
								</span>

								<i class="ace-icon fa fa-caret-down"></i>
							</a>

							<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								
<!--								<li>
									<a href="Unit.html">
										<i class="ace-icon fa fa-user"></i>
										Profile
									</a>
								</li>-->

								<!--<li class="divider"></li>-->

								<li>
									<a href="Logout.html">
										<i class="ace-icon fa fa-power-off"></i>
										Logout
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
                            <nav role="navigation" class="navbar-menu pull-right collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li>
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								Admin
	  		&nbsp;
								<i class="ace-icon fa fa-angle-down bigger-110"></i>
							</a>

							<ul class="dropdown-menu dropdown-light-blue dropdown-caret">
								<li>
									<a href="Companyinformation.html">
										
										Restaurant Information
									</a>
								</li>
                                                                
                                                                <li>
									<a href="TableMaster.html">
										
										Table Information
									</a>
								</li>
                                                                
                                                                <li>
									<a href="WaiterMaster.html">
										
										Waiter Information
									</a>
								</li>
                                                                

								<li>
									<a href="DefaultSettings.html">
										
										Default Settings
									</a>
								</li>
                                                                
							</ul>
						</li>
						
						<li>
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								Master Files
	  		&nbsp;
								<i class="ace-icon fa fa-angle-down bigger-110"></i>
							</a>

							<ul class="dropdown-menu dropdown-light-blue dropdown-caret">
								<li>
									<a href="Unit.html">
										
										Unit Master
									</a>
								</li>

								<li>
									<a href="Item_Group.html">
										
										Item Groups
									</a>
								</li>
                                                                <li>
									<a href="ccode.html">
										
										HSN Code
									</a>
								</li>

								<li>
									<a href="ItemMaster.html">
										
										Item Master
									</a>
								</li>
								
								<li>
									<a href="ItemMasterQuickEdit.html">
										
										Items Quick Edit
									</a>
								</li>
								
								<li>
									<a href="Buyer.html">
										
										Customer Master
									</a>
								</li>

								<li>
									<a href="Supplier.html">
										
										Supplier Master
									</a>
								</li>
							</ul>
						</li>
						
						<li>
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								Transaction Files
	  		&nbsp;
								<i class="ace-icon fa fa-angle-down bigger-110"></i>
							</a>

							<ul class="dropdown-menu dropdown-light-blue dropdown-caret">
                                                            
                                                               <li>
									<a href="KotForm.html">
										
										KOT Form
									</a>
								</li>
								<li>
									<a href="SalesInvoicelist.html">
										
										Sales Invoice List
									</a>
								</li>
                                                                    
								<li>
									<a href="AuditSalesInvoicelist.html">
										
										Sales Audit Invoice List
									</a>
								</li>
<!--                                                                <li>
									<a href="#">
									<span style="color:red;">-----------------------------------</span>
									</a>
								</li>-->
                                                                 <li>
									<a href="Purchase.html">
										
										Purchase
									</a>
								</li>
                                                                <li>
									<a href="PurchaseGrid.html">
										
										Purchase Records
									</a>
								</li>
                                                                <li>
									<a href="ReversalOfItcGrid.html">
										
										Reversal Of ITC
									</a>
								</li>
							</ul>
						</li>
						
                                                                            <li>
                                                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                                            Registers
                                    &nbsp;
                                                                            <i class="ace-icon fa fa-angle-down bigger-110"></i>
                                                                    </a>

                                                                    <ul class="dropdown-menu dropdown-light-blue dropdown-caret">
                                                                            
                                                                        <li>
									<a href="StockReport.html">
										
									   Hot Drinks Stock
									</a>
                                                                        </li>
                                                                         <li>
									<a href="StockReportBeer.html">
										
									   Beer Stock
									</a>
                                                                        </li>
                                                                         <li>
									<a href="WineStock.html">
										
									   Wine Stock
									</a>
                                                                        </li>
                                                                        <li>
									<a href="SalesReport.html">
										
									  Sales Report
									</a>
                                                                        </li>
                                                                         <li>
									<a href="SalesReportByItem.html">
										
									  Sales Report Item
									</a>
                                                                        </li>
<!--                                                                        <li>
									<a href="BackupDatabase.html">
										
									  Backup Database
									</a>
                                                                        </li>
                                                                        <li>
                                                                        <a href="RestoreDatabase.html">
										
									  Restore Database
									</a>    
                                                                        </li>-->
                                                                        <li>
                                                                        <a href="DBImportExport.html">
										
									  DB Import & Export
									</a>    
                                                                        </li>
                                                                        <li>
									<a href="WaiterReport.html">
										
										Waiter Report
									</a>
								</li>
                                                                 <li>
									<a href="AllWaiterReport.html">
										
										All Waiter Report
									</a>
								</li>
                                                                    </ul>
                                                            </li>
                                                                         
                                                
                                                      <li>
                                                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                                            User
                                    &nbsp;
                                                                            <i class="ace-icon fa fa-angle-down bigger-110"></i>
                                                                    </a>

                                                                    <ul class="dropdown-menu dropdown-light-blue dropdown-caret">
                                                                            <li>
                                                                                    <a href="AccessControl.html">
                                                                                           
                                                                                            Access Control
                                                                                    </a>
                                                                            </li>

                                                                            <li>
                                                                                    <a href="Group_Details.html">
                                                                                           
                                                                                            Group Details
                                                                                    </a>
                                                                            </li>

                                                                            <li>
                                                                                    <a href="UserMaster.html">
                                                                                            
                                                                                            User Master
                                                                                    </a>
                                                                            </li>

                                                                        
                                                                    </ul>
                                                            </li>
                                                            
					</ul>
				</nav>
			</div><!-- /.navbar-container -->
		</div>
        </body>
</html>
