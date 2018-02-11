<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>KOT-RestaurnatInformation</title>

		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/font-awesome/4.5.0/css/font-awesome.min.css" />

                
                  <link rel="stylesheet" href="assets/calendar/fullcalendar.css" />
                  <!--<link rel="stylesheet" href="assets/calendar/fullcalendar.min.css" />-->
                  <!--<link rel="stylesheet" href="assets/calendar/fullcalendar.print.css" />-->
		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="assets/css/bootstrap-duallistbox.min.css" />
		<link rel="stylesheet" href="assets/css/bootstrap-multiselect.min.css" />
		<link rel="stylesheet" href="assets/css/select2.min.css" />
                <link rel="stylesheet" href="assets/css/jquery-ui.custom.min.css" />
		<link rel="stylesheet" href="assets/css/chosen.min.css" />
		<link rel="stylesheet" href="assets/css/bootstrap-datepicker3.min.css" />
		<link rel="stylesheet" href="assets/css/bootstrap-timepicker.min.css" />
		<link rel="stylesheet" href="assets/css/daterangepicker.min.css" />
		<link rel="stylesheet" href="assets/css/bootstrap-datetimepicker.min.css" />
		<link rel="stylesheet" href="assets/css/bootstrap-colorpicker.min.css" />
                <link rel="stylesheet" href="assets/css/custom.css" />
                <link rel="stylesheet" href="assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/font-awesome/4.5.0/css/font-awesome.min.css" />

		<!-- text fonts -->
		<link rel="stylesheet" href="assets/css/fonts.googleapis.com.css" />
               

		<!-- ace styles -->
		<link rel="stylesheet" href="assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
                <link rel="stylesheet" href="assets/css/jquery-ui.min.css" />
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
	

		<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>
         <c:set var="listLength" value="${fn:length(listGroup)}"/>
        <c:forEach items="${listGroup}" var="group">
            <c:choose>
                <c:when test="${group.moduleName=='Company Information'}">
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

                    
                            
			<div class="main-content">
				<div class="main-content-inner">

					<div class="page-content">
						
                                                <form:form action="saveCompany.html" modelAttribute="companyFrom" method="POST"   onsubmit="return (CheckFileName()&& checkDate())"  enctype="multipart/form-data">
						
                                                                    
                                                    <div class="widget-box lighter  widget-color-blue2">
                                                                    <div class="widget-header">
                                                                <h5 class="widget-title">Restaurant Details</h5>
                                                              
                                                                    </div>
                                                     <div class="widget-body">
                                                          <div class="widget-main alert-success">
                                                              <div style="text-align: center;">
                                                             <span style="color:red;" > ${message} </span>
                                                              </div>
                                                               <div style="text-align: center; ">
                                                             <span style="color:orangered;" > ${CompanyMessage} </span>
                                                             
                                                              </div>
                                                              
                                                    <div class="row" >
<!--                                                  
								<!-- PAGE CONTENT BEGINS -->
                                                                
								<form:input  type="hidden"  path="idCompany" placeholder="idCompany"  class="form-control " />
									 
                                                                <div class="form-group col-sm-3">
									 
                                                                            <label class="col-sm-8" for="form-field-1"><span style="color:red;">*</span> Restaurant Name </label>

										<div class="col-sm-12">
											<form:input  type="text"  path="companyName" placeholder="Company Name" id="comInfo" class="form-control " required="true"  maxlength="50"/>
                                                                                        <form:errors path="companyName" style="color:red;"/>
                                                                                </div>
									</div>

									<div class="form-group col-sm-3">
										<label class="col-sm-8" for="form-field-1-1"> Address </label>

										<div class="col-sm-12">
                                                                                        <form:input  type="text"  path="address" placeholder="Address" class="form-control " />
                                                                                        <form:errors path="address"  />
										</div>
									</div>
                                                                    <div class="form-group col-sm-3">
										<label class="col-sm-8 " for="form-field-1"><span style="color:red;">*</span> Pin Code </label>

										<div class="col-sm-12">
                                                                                        <form:input  type="text"  path="city" placeholder="Pin Code" class="form-control "  oninvalid="setCustomValidity('Please Enter 6 digit Number ')" onchange="try{setCustomValidity('')}catch(e){}" onkeypress="return isNumberKey(event)" minlength="6" maxlength="6"  required="true"/>
                                                                                        <form:errors path="city"  />
										</div>
									</div>
                                                                    
                                                                    <div class="form-group col-sm-3">
										<label class="col-sm-8 " for="form-field-1"> Land Line </label>

										<div class="col-sm-12">											
                                                                                        <form:input  type="text"  path="phoneNo" oninvalid="setCustomValidity('Please Enter 15 digit Number ')" onchange="try{setCustomValidity('')}catch(e){}" onkeypress="return isNumberKey(event)"  minlength="10" maxlength="15"  class="form-control "/>
                                                                                        <form:errors path="phoneNo" />
										</div>
									</div>

									
                                                                   
								 
							</div>
                                                                        <div class="row">     
								<!-- PAGE CONTENT BEGINS -->
                                                                <div class="form-group form-group col-sm-3">
										<label class="col-sm-8 " for="form-field-1-1"> Mobile No </label>

										<div class="col-sm-12">
                                                                                        <form:input  type="text"  path="cellNo" oninvalid="setCustomValidity('Please Enter 10 digit Number ')" onchange="try{setCustomValidity('')}catch(e){}" onkeypress="return isNumberKey(event)"  minlength="10" maxlength="11" placeholder="Cell No" class="form-control " />
                                                                                        <form:errors path="cellNo"/>
										</div>
									</div>
                                                                 <div class="form-group form-group col-sm-3">
										<label class="col-sm-8 " for="form-field-1"> Email </label>

										<div class="col-sm-12">
                                                                                    <form:input  type="email"  path="companyEmail" placeholder="Email" class="form-control " pattern="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$" oninvalid="setCustomValidity('Please Enter an Valid Email Address ')" onchange="try{setCustomValidity('')}catch(e){}" />
                                                                                        <form:errors path="companyEmail" />
										</div>
									</div>
								<div class="form-group form-group col-sm-3">
										<label class="col-sm-8 " for="form-field-1-1"><span style="color:red;">*</span> GSTIN </label>

										<div class="col-sm-12">
                                                                                    <form:input  type="text"  path="companyTin" placeholder="GSTIN" id="tinInfo" class="form-control " required="true" pattern="\d{2}[A-Z]{5}\d{4}[A-Z]{1}\d{1}[A-Z]{1}\d{1}" oninvalid="setCustomValidity('Please Enter alphanumeric Characters ')" onchange="try{setCustomValidity('')}catch(e){};fromGstin();" minlength="15" maxlength="15"  />
                                                                                        <form:errors path="companyTin" />
										</div>
									</div>
									<div class="form-group form-group col-sm-3">
										<label class="col-sm-8 " for="form-field-1"><span style="color:red;">*</span>PAN </label>

										<div class="col-sm-12">
                                                                                    <form:input  type="text"  path="companyPan" placeholder="PAN " id="panInfo" class="form-control "  oninvalid="setCustomValidity('Please Enter alphanumeric Characters ')" onchange="try{setCustomValidity('')}catch(e){}" minlength="10" maxlength="10" required="true" readonly="true"/>
                                                                                  
                                                                                        <form:errors path="companyPan" style="color:red;"/>
										</div>
									</div>

									
                                                                    
            
							</div>
                                                        <div class="row"> 
								<!-- PAGE CONTENT BEGINS -->
								
                                                                        <div class="form-group  col-sm-3"> <span style="color:red;">${msg}</span>
										<label class="col-sm-12" for="form-field-1"> Upload Logo <span style="color:blue;">(Only Accept jpg,jpeg,png)</span></label>

										<div class="col-sm-12">
                                                                                    <input  type="file"  path="companyLogo" name="file"  accept="image/*" id="imgInp"  class="form-control"/>
                                                                                      <br>                                                                                      
                                                                                       <!--<img  id="blah"  height="100" />-->
                                                                                     <input type="hidden" name="imgName"  value="${companyFrom.companyLogo}" />
                                                                                </div>
									</div>

									
                                                                    <div class="form-group col-md-2">
                                                                               <label class="col-sm-12 " for="form-field-1"><span style="color:red;">*</span> Restaurant Type </label>

                                                                               <div class="col-sm-12">
                                                                                <form:select class="chosen-select form-control"  path="taxType" id="form-field-select-3" data-placeholder="Choose a taxType..." required="true">
                                                                                        <form:option value="--- Please Select --- "/></option>
                                                                                        <form:option value="Regular GST Dealer"/>Regular GST Dealer</option>
                                                                                        <form:option value="Composite Dealer"/>Composite Dealer</option>
                                                                                        <form:option value="Exempted Dealer"/>Exempted Dealer</option>    

                                                                             
                                                                                </form:select>
                                                                                        <form:errors path="taxType" />
                                                                                   </div>
                                                                        </div>
                                                                    
                                                                    <div class="form-group form-group col-sm-2">
										<label class="col-sm-12 " for="form-field-1-1"><span style="color:red;">*</span> Financial Year From </label>

										<div class="col-sm-12">
                                                                                  <form:input  type="text"  path="financialYearFrom" placeholder="financialYearFrom" id="reservation" class="form-control " required="true" data-date-format="dd/mm/yyyy"/>
                                                                                        <!--<input type="text"   id="reservation"   />-->
										</div>
									</div>
                                                                        <div class="form-group form-group col-sm-2">
										<label class="col-sm-12 " for="form-field-1-1"><span style="color:red;">*</span> Financial Year To </label>

										<div class="col-sm-12">
                                                                                    <form:input  type="text"  path="financialYearTo" placeholder="Financial Year To" id="fto" class="form-control " required="true" data-date-format="dd/mm/yyyy" readonly="true"/>
                                                                                        <!--<input type="text"   id="reservation"   />-->
										</div>
									</div>                    
                                                                                <div class="form-group col-sm-3 ">
										<label class="col-sm-8 " ><span style="color:red;">*</span> Accounts From </label>

										<div class="col-sm-12 ">
                                                                                    <form:input class="form-control date-picker" id="myBtn" data-target="#myModal" type="text" path="accountsFrom" placeholder="Accounts From" required="true"   data-date-format="dd/mm/yyyy"/>
                                                                                     <form:errors path="accountsFrom" style="color:red;"/>
                                                                                  
                                                                                        
										</div>
									</div>
                                                                                
                                                                                <div class="form-group  col-sm-3">
										<label class="col-sm-8" for="form-field-1"></label>

										<div class="col-sm-12">
                                                                                    <form:input  type="hidden"  path="" id="" placeholder="" required="true" class="form-control " />
                                                                                      
										</div>
									</div>
							</div>
                                                         <div class="row">
                                                                        <div class="form-group  col-sm-6">
									    <c:if test="${not empty companyFrom.companyLogo}">    
                                                                                <div class="col-md-6">                                        
                                                                                    <div class="image-crop">
                                                                                     <img class="cropper-hidden" src="${pageContext.request.contextPath}/Logo/${companyFrom.companyLogo}" style="width: 60%; height: 60%;" alt="">
                                                                                    </div>                                                                        
                                                                                </div>
                                                                              </c:if>   	
									</div>
                                                                         <div class="form-group  col-sm-6" style="padding-right: 140px;">
										<input  type="submit" id="btnSubmit" class="btn btn-info" value="Save" style="float: right;"/>
									</div>
                                                           </div>
						</div>                    
                                                                              </div>
                                                                                    </div>
                                                                                         </div>
                                                                                   
                                                                                </form:form>
                                                            <!-- /.row -->
                                                                        
                                                                            
                                                             
					</div>
                                    
				</div>
			</div>
                            

			 <%@ include file="Footer.jsp" %>

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
		<script src="assets/js/bootstrap-datetimepicker.min.js"></script>
		<script src="assets/js/bootstrap-colorpicker.min.js"></script>
		<script src="assets/js/jquery.knob.min.js"></script>
		<script src="assets/js/autosize.min.js"></script>
		<script src="assets/js/jquery.inputlimiter.min.js"></script>
		<script src="assets/js/jquery.maskedinput.min.js"></script>
		<script src="assets/js/bootstrap-tag.min.js"></script>
                <script type="text/javascript" src="assets/datepicker/daterangepicker.js"></script>
		<!-- ace scripts -->
		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>
      
 <c:if test="${not empty companyFrom.companyTin}">
<script>
$(document).ready(function(){
    $("#tinInfo").attr('readonly', true);
});

</script>
</c:if>
<c:if test="${not empty companyFrom.companyPan}">
<script>
    $(document).ready(function(){
    $("#panInfo").attr('readonly', true);
});    


        
</script>
</c:if>
                
<script>
  $("#comInfo").focus();
  // To GET PAN Number from Gstin Number
 function fromGstin() {
     
   
    var str = document.getElementById("tinInfo").value;
   
    var res = str.substring(2, 12);
   
    document.getElementById("panInfo").value = res;
}
 
                  // to show cursor on first TextBox

  

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
       function dateCheck(from,to,check) {
       var fDate,lDate,cDate;
       var fromarray = from.split("/");
       var fdate1 = fromarray[1] + '/' + fromarray[0] + '/' + fromarray[2];
       var toarray = to.split("/");
       var tdate1 = toarray[1] + '/' + toarray[0] + '/' + toarray[2];
       var carray = check.split("/");
       var cdate1 = carray[1] + '/' + carray[0] + '/' + carray[2];
        fDate = new Date(fdate1);
        lDate = new Date(tdate1);
        cDate = new Date(cdate1);
        if((cDate <= lDate && cDate >= fDate)) {              
            return true;
        }
            return false;
        }
      </script>
                 <script>
                    
$(document).ready(function(){
$(document).on('keydown',':tabbable', function (e) {
    if (e.which == 13) {
        e.preventDefault();
        // Get all focusable elements on the page
        var $canfocus = $(':tabbable');
        var index = $canfocus.index(this) + 1;
        if (index >= $canfocus.length) index = 0;
        $canfocus.eq(index).focus();
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

               <script type="text/javascript">
			jQuery(function($) {
				
				//$('#spinner1').ace_spinner('disable').ace_spinner('value', 11);
				//or
				//$('#spinner1').closest('.ace-spinner').spinner('disable').spinner('enable').spinner('value', 11);//disable, enable or change value
				//$('#spinner1').closest('.ace-spinner').spinner('value', 0);//reset to 0
			
			
				//datepicker plugin
				//link
				 var date = new Date();
                                    var dd = date.getDate();             
                                    var mm = date.getMonth()+1;
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
                                       
				});
                                $('#reservation').datepicker({
                                     defaultDate : ToDate,
					autoclose: true,
					todayHighlight: true
                                       
				});
                                 $('#reservation').datepicker().on('changeDate', function() {
                                    var temp = $(this).datepicker('getDate');
                                    var minValue = $(this).val();   
                                     var date = new Date(temp);
                                    var datestringFrom =("0" + date.getDate()).slice(-2)  + "/" + ("0" + (date.getMonth() + 1)).slice(-2)+ "/" + date.getFullYear() ;
                                    date.setMonth(date.getMonth() + 12);
                                    date.setDate(date.getDate()-1); 
                                    var datestringTo =("0" + date.getDate()).slice(-2)  + "/" + ("0" + (date.getMonth() + 1)).slice(-2)+ "/" + date.getFullYear() ;
                                    $("#fto").val(datestringTo);
                                });
                               
                                
			});
		</script>             

<script>
</script>

<script>
function CheckFileName() {

    var fileName = document.getElementById("imgInp").value

    if (fileName == "")  
    {  
       
       // return false;  
    }  
    else if (fileName.split(".")[1].toUpperCase() == "PNG")  
    {  
//        alert("A Valid Png file is attached !");  
        return true;  
    }  
    else if (fileName.split(".")[1].toUpperCase() == "JPG")  
    {  
//        alert("A Valid Png file is attached !");  
        return true;  
    }
     else if (fileName.split(".")[1].toUpperCase() == "JPEG")  
    {  
//        alert("A Valid Png file is attached !");  
        return true;  
    }
    else  
    {  
      alert(" Company logo Not a Png,jpg,jpeg File");  
        return false;  
    }

return true;  

}
function checkDate()
{
  
  var accountfrom=$('#myBtn').val();
  var from=$('#reservation').val();
  var to=$('#fto').val();
  if(dateCheck(from,to,accountfrom))
  {
      
  }
//    alert("Availed");
  else
  {
    alert("Account from date within financial year range"); 
    return false;
  }
}
</script>

	</body>
</html>
