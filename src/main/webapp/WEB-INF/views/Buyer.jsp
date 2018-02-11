<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="account"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title> Buyer -Accounting</title>

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
		<link rel="stylesheet" href="assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
		<script src="assets/js/ace-extra.min.js"></script>
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
</style>          
	</head>

<body class="no-skin">
		 
                <c:set var="listLength" value="${fn:length(listGroup)}"/>
        <c:forEach items="${listGroup}" var="group">
            <c:choose>
                <c:when test="${group.moduleName=='Buyer Master'}">
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
							<h1>Customer Master</h1>
							<div style="text-align: center; ">
                                                             <span style="color:orangered;" > ${buyerMessage} </span>
                                                             
                                                              </div>
						</div>
						
                            <account:form  action="buyersave.html"  modelAttribute="buyerForm"   method="POST">
                            <div class="row" >
                                <div class="row">
                                        <div class="form-group ">
                                            <c:if  test="${not empty buyerForm.idBuyer}"> 
                                            <div class="form-group">
                                               <div class="col-sm-3">
                                                    <account:input type="hidden" path="idBuyer" id="form-field-1" placeholder="buyer id" class="form-control " readonly="true"/>

                                               </div>
                                            </div>

                                            </c:if>

                                        </div>
                                </div>
                                                    
                                        <div class="row">

                                                    
                                                    <div class="form-group form-group col-sm-3">
                                                    <label class="col-sm-8 " for="form-field-1-1">  Customer Name<span style="color:red;">*</span></label>
                                                    <div class="col-sm-12">
                                                        <account:input type="text" path="customerName"  class="form-control"  />
                                                    </div>
                                                    </div>        


                                                                            <!-- PAGE CONTENT BEGINS -->
                                                    <div class="form-group form-group col-sm-3">
                                                    <label class="col-sm-8 " for="form-field-1-1"> Cell No <span style="color:red;">*</span></label>
                                                    <div class="col-sm-12">
                                                        <account:input type="text" path="customerMobile"  class="form-control"  />
                                                    </div>
                                                    </div>
                                                    
                                                     <div class="form-group form-group col-sm-3">
                                                    <label class="col-sm-8 " for="form-field-1-1"> Email <span style="color:red;">*</span></label>
                                                    <div class="col-sm-12">
                                                        <account:input type="text" path="email"  class="form-control"  />
                                                    </div>
                                                    </div>
                                                    
                                                    <div class="form-group  col-sm-3">
                                                    <label class="col-sm-8" for="form-field-1"><span style="color:red;">*</span> Gender </label>
                                                    <div class="col-sm-12">
                                                    <account:input type="text" path="gender"  placeholder="" class="form-control" />
                                                    </div>
                                                    </div>
                                                    
                                                    
                                                   


                                        </div>
							
                                    <div class="row"> 

                                        <div class="form-group  col-sm-3">
                                        <label class="col-sm-8" for="form-field-1"> Address </label>
                                        <div class="col-sm-12">                                                                                    
                                        <account:textarea class="col-sm-12" id="form-field-11" path="customerAddress" tabindex="7" maxlength="255"   style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 52px;"></account:textarea>
                                        </div>
                                        </div>

                                       

                                        <div class="form-group  col-sm-3">
                                        <label class="col-sm-8" for="form-field-1"><span style="color:red;">*</span> State</label>
                                        <div class="col-sm-12">
                                        <account:input type="text" path="customerState" tabindex="8"  placeholder="" class="form-control" />
                                        </div>
                                        </div>
                                        
                                        <div class="row"> 
                                        <div class="form-group  col-sm-3">
                                        <label class="col-sm-8" for="form-field-1"><span style="color:red;">*</span> Status</label>
                                        <div class="col-sm-12">
                                            <form:select class="chosen-select form-control"  path="customerStatus" id="form-field-select-3" data-placeholder="Choose status" required="true">
                                            <form:option value=""/>
                                               <form:option value="Active" label="Active"/>
                                            <form:option value="Inactive" label="Inactive"/>
                                        </form:select>  
                                        </div>
                                        </div> 			
                                        </div>  
                                       

                                    </div>
                                            
				</div>
                                                                                
                                <div class="row"> 
                                    <div class="clearfix form-actions col-md-12" style="padding-left: 300px;">
                                    <div class="col-sm-6">
                                     <div class="col-md-offset-3 col-md-9">
                                           <input type="submit" id="btnSubmit"  class="btn btn-info" value="Save"/>
                                           &nbsp; &nbsp; &nbsp;
                                           <input type="reset" class="btn" value="Reset" onClick="window.location.reload()">
                                           </div> 
                                    </div>
                                </div>  
                                </div>
                                              
                                                     																	
                                        </account:form>

									<div class="row">
									<div class="col-xs-12">
										<!--<h3 class="header smaller lighter blue">Buyer List</h3>-->
                                                                              <div class="table-header">
                                                                                                Buyer List
                                                                                        </div>
										<div class="clearfix">
											<div class="pull-right tableTools-container"></div>
										</div>
										

										
										<div>
											<table id="dynamic-table" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														 
														
														<th>Customer Name</th>
														<th>Customer City</th>
                                                                                                                <th>Mobile No</th>
                                                                                                                <th>Customer State</th>
                                                                                                                <th>Edit / Delete </th>
                                                                                                                <!--<th>Delete</th>-->

														
													</tr>
												</thead>

												
											</table>
										</div>
									</div>
								</div>
                                                        
					</div>
                                    
				</div>
			</div>
                            

			 <!-- <%@ include file="Footer.jsp" %> -->

		</div>
		<script src="assets/js/jquery-2.1.4.min.js"></script>

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
  
   

                 <script>
        
            $(document).ready(function() {
                
    $('#dynamic-table').DataTable( {
        "processing": true,
        "serverSide": true,
        "ajax": "GetDatatableBuyer.html",
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
        "fnRowCallback": function( nRow, aData, iDisplayIndex ) {  
   
        $('td:eq(4)', nRow).html('<a href="buyerEdit.html?eid='+aData[4]+'"<span class="green"><i class="ace-icon fa fa-pencil bigger-130"></i></span></a> | <a href="buyerDelete.html?did='+aData[4]+'"<span class="red"><i class="ace-icon fa fa-trash-o bigger-130" ></i></span></a>');

            },  
} );

});   
     
</script> 

              
	</body>	
</html>
