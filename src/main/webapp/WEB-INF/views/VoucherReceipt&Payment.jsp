<%-- Document   : VoucherReceipt
    Created on : Feb 9, 2017, 5:53:28 PM
    Author     : MR
--%>

<%@page  pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 
    prefix="fn" %> 
<!DOCTYPE html>
<html>
   
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accounting - Voucher Receipt</title>
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
        <!--<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />-->

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
        
        <style>
            #showCheque, #showDate, #showBankGroup, #showBankName{
                display:none;
            }
            
        </style>
        
    </head>
    
   
           <c:set var="listLength" value="${fn:length(listGroup)}"/>
        <c:forEach items="${listGroup}" var="group">
            <c:choose>
                <c:when test="${group.moduleName=='Receipt'}">
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
					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="#">Home</a>
							</li>

							<li>
								<a href="ReceiptGrid.html">Receipt Voucher Records</a>
							</li>
							<li class="active">Receipt Voucher</li>
						</ul><!-- /.breadcrumb -->

						
					</div>

					<div class="page-content">
						<!--- ACE Settings for template -->

						<div class="page-header">
							<h1>
								 Receipt Voucher
								<!-- <small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									Common form elements and layouts
								</small> -->
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<form:form  action="SaveVoucherReceipt.html" modelAttribute="vouchPaymentReceipt" method="POST">
								<div class="row">
                                                                    
                                                                    <div class="form-group col-xs-3" id="appendThis">
										<label class="col-sm-6" for="form-field-1"> Receipt No<span style="color:red;">*</span></label>

										<div class="col-sm-10">
                                                                                    <form:input path="vouchId" type="text" id="Box1" placeholder="" class="form-control" readonly="true"/>
											<form:errors path="vouchId" />
										</div>
									</div>
                                                                                 <div class="form-group col-xs-2">
										<label class="col-sm-6" for="form-field-1"> Date </label>

										<div class="col-sm-10"> 
											<form:input path="vouchCashDate" class="form-control date-picker " id="txtDate" placeholder="date" type="text" required="true"   data-date-format="dd/mm/yyyy" />
											 <form:errors path="vouchCashDate" />
										</div>
									</div>
                                                                             <div class="form-group col-xs-3">
										<label class="col-sm-5" for="form-field-1"> Mode</label>
                                                                                    
                                                                                     <div class="col-sm-8">
                                                                                         
                                                                                              <form:select path="vouchMode" class="form-control" id="changeMode">                                                                                         
                                                                                            <form:option value="cash" label="Cash" selected="selected"/>
                                                                                            <form:option value="cheque" label="DD/Cheque/Transfer"/>
										          </form:select>
                                                                                          <form:errors path="vouchMode" /> 
                                                                                          </div>
									</div> 
                                                                                
                                                                    <form:hidden path="vouchId" id="pId"/>
                                                                     <form:hidden path="vouchType" name="vouchType" value="receipt" />
                                                                    <form:input type="hidden" path="authorisation" value="1" id="authValue" placeholder="auth" class="form-control"/>
                                                                        
                                                                        <div class="form-group col-xs-3" id="appendThis">
										<label class="col-sm-6" for="form-field-1"> Voucher Amount<span style="color:red;">*</span></label>

										<div class="col-sm-10">
											<form:input path="vouchAmount" type="number" step="0.01" placeholder="voucher amount" oninvalid="setCustomValidity('Please enter Numbers ')" onchange="try{setCustomValidity('')}catch(e){}" min="0"  class="form-control" required="true" />
											<form:errors path="vouchAmount" />
										</div>
									</div>
                                                                                
                                                                       
                                                                    
									
								</div>
									
								<div class="row">
                                                                                    
                                                                                                   
									<div class="form-group col-xs-3">
										<label class="col-sm-6" for="form-field-1"> Name of Account</label>

										<div class="col-sm-10">

                                                                                    <form:select path="vouchFrom" class="form-control disable changeFromRecAndPay" id="changeFrom" >
                                                                                        <form:option value="" label="---choose---" selected="selected"/>
                                                                                        <c:forEach items="${NameOFAmountListECIH}" var="list">                                            
                                                                                            <form:option value="${list.idLedger}" label="${list.nameOfLedger}"/>
                                                                                        </c:forEach>
                                                                                    </form:select>
                                                                                    <span class="red"><form:errors path="vouchFrom" /> </span>
										</div>
									</div>
                                                                                
                                                                                     
                                                                                         
                                                                                                 <div class="form-group col-xs-4">
										<label class="col-sm-6">Voucher Closing Balance </label>

										<div class="col-sm-7">
                                                                                    <form:input path="vouchBal" id="vouchBal" type="text" placeholder="balance" class="form-control" readonly="true"/>
                                                                                    <form:errors path="vouchBal" />
                                                                                </div>
										<div class="col-sm-3">
                                                                                    <form:input path="vouchCashType" id="vouchCashType" class="form-control"  readonly="true"/>
                                                                                    <form:errors path="vouchCashType" />
                                                                                   
										</div>
									             </div>                                                                                                
                                                                                           
                                                                                 
                                                                                <c:if test="${vouchPaymentReceipt.vouchId!=null}">
																																	<div class="form-group col-xs-4">
                                                                                            <label class="col-sm-6"><span style="color:green;">Current Closing Balance</span></label>

                                                                                <div class="col-sm-7">
                                                                                            <input value="${currentFromBalance}"  type="text" placeholder="balance" class="form-control" readonly="true" />
                                                                                           
                                                                                    </div>
                                                                                <div class="col-sm-3">
                                                                                        <input value="${fromType}"  type="text"  class="form-control" readonly="true"/>
										
										</div>              
                                                                                            
                                                                                 
                                                                                    </div>
                                                                                                  </c:if>
                                                                                
                                                                                
                                                                        
								</div>
                                                                                
                                                                
                                                                                             
                                                                                
                                                                             
								<div class="row" id="hideForCheque">
                                                                    
                                                                                       
                                                                        
                                                                        <div class="form-group col-xs-3" id="hideToGroup">
										<label class="col-sm-6" for="form-field-1"> Cash Ledgers</label>

										<div class="col-sm-10">

                                                                                    <form:select path="vouchTo" class="form-control disable changeToRecAndPay" id="changeTo" >
                                                                                        <form:option value="" label="---choose---"/>
                                                                                        <c:forEach items="${vouchLedgerList}" var="list">
                                                                                            <form:option value="${list[1]}" label="${list[0]}"/>
                                                                                        </c:forEach>
                                                                                    </form:select>
                                                                                    <span class="red"> <form:errors path="vouchTo" /></span>
										</div>
									</div>
                                                             
                                                                        <div class="form-group col-xs-4" id="showCheque">
										<label class="col-sm-6" for="form-field-1">Cheque/DD/Transfer No</label>

										<div class="col-sm-10">
											<form:input path="vouchCheqNo" type="text" id="form-field-1" onkeypress="return isNumberKey(event)"  placeholder="cheque no" class="form-control" maxlength="15"/>
                                                                                        <form:errors path="vouchCheqNo" />
										</div>
									</div>
                                                                      
                                                                        <div class="form-group col-xs-4" id="showDate">
										<label class="col-sm-4" for="form-field-1"> Date </label>

										<div class="col-sm-10">
											<form:input path="vouchCheqDate" class="form-control date-picker" id="id-date-picker-1" placeholder="date" type="text" data-date-format="dd/mm/yyyy"/>
											<form:errors path="vouchCheqDate" />
										</div>
									</div>
                                                                                  <div class="form-group col-xs-4" id="showBankName">
										<label class="col-sm-6" for="form-field-1">Name of the Bank </label>

										<div class="col-sm-10">
											<form:input path="vouchNameofBank" type="text" id="form-field-1" placeholder="bank name" class="form-control" />
											<form:errors path="vouchNameofBank" />
										</div>
									</div>
                                                                       
									
									
									<div class="form-group col-xs-3 hideForType" id="showBankGroup">
										<label class="col-sm-6" for="form-field-1"> Bank Ledgers </label>

										<div class="col-sm-10">
                                                                                    <form:select path="vouchdepositTobank" class="form-control changeToCashRecAndPay" id="changeToCash"  >
                                                                                        <form:option value="0" label="---choose---"/>
                                                                                        <c:forEach items="${vouchCashLedgerList}" var="list">
                                                                                            <form:option value="${list[1]}" label="${list[0]}"/>
                                                                                        </c:forEach>
										    </form:select>
                                                                                    <form:errors path="vouchdepositTobank" />
										</div>
									</div>
                                                                             
                                                                                     <div class="form-group col-xs-4">
										<label class="col-sm-6" for="form-field-1">  Voucher Closing Balance</label>

										<div class="col-sm-7">
											<form:input path="vouchCheqBal" id="vouchCheqBal" type="text" placeholder="balance" class="form-control" readonly="true"/>
                                                                                        <form:errors path="vouchCheqBal" />
                                                                                </div>
										<div class="col-sm-3">
                                                                                    <form:input path="vouchCheqType" class="form-control" id="vouchCheqType" readonly="true"/>
                                                                                    <form:errors path="vouchCheqType" />
										
										</div>
									</div>
                                                                                
                                                                                
                                                                                    <c:if test="${vouchPaymentReceipt.vouchId!=null}" >
                                                                       <div class="form-group col-xs-4">
                                                                           <label class="col-sm-6" for="form-field-1"><span style="color:green;">  Current Closing Balance</span></label>
                                                                           <c:choose>  
                                                                           <c:when test="${vouchPaymentReceipt.vouchMode=='cash'}" >
										<div class="col-sm-7">
											<input value="${currentTOmBalance}"  type="text" placeholder="balance" class="form-control" readonly="true"/>
                                                                                        
                                                                                </div>
                                                                                <div class="col-sm-3">
                                                                                        <input value="${toType}"  type="text"  class="form-control" readonly="true"/>
										
										</div>        
                                                                                        
                                                                                 </c:when>  
                                                                                 <c:otherwise>
                                                                                   <div class="col-sm-7">
											<input value="${currentCmBalance}"  type="text" placeholder="balance" class="form-control" readonly="true"/>
                                                                                        
                                                                                </div>
                                                                                <div class="col-sm-3">
                                                                                        <input value="${cType}"  type="text"  class="form-control" readonly="true"/>
										
										</div>            
                                                                                 </c:otherwise>
                                                                           </c:choose>
                                                                                  
									</div>
                                                                                </c:if>
									
								</div>
                                                                                <div class="row">
                                                                                     <div class="form-group col-xs-8" id="yes">
                                                                                            <label class="col-sm-6" for="form-field-11">Narration</label>
                                                                                            <div class="col-sm-11">
                                                                                                    <form:textarea path="vouchNarration" id="form-field-11" class="autosize-transition form-control col-sm-12" style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 52px;"/>
                                                                                                    <form:errors path="vouchNarration" />
                                                                                            </div>

                                                                                    </div>
                                                                                </div>
								
									
									<div class="space-4"></div>
									
									<div style="text-align:center">
                                                                           
                                                                            <c:if  test="${vouchPaymentReceipt.vouchId==null}">
                                                                                 <input type="submit" id="btnSubmit" class="btn btn-success" value="Save">
                                                                                 <button class="btn" type="reset">  Reset </button>
                                                                            </c:if>
                                                                            
									</div>
									
									</form:form>
								</div>
								</div><!-- PAGE CONTENT ENDS -->
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
                <script>
                $("#txtDate").focus();
                
                </script> 
<!--                <script type="text/javascript">
        $(function () {           
            var d = new Date().getDate();
            var m = new Date().getMonth() + 1; // JavaScript months are 0-11
            var y = new Date().getFullYear();
            $("[id$=txtDate]").val(d + "/" + m + "/" + y);
            $("[id$=txtDate]").datepicker({ weekStart: 1, dateFormat: 'dd/mm/yy' });
        });
    </script>-->
                <script type="text/javascript">
        function isNumberKey(evt)
       {
          var charCode = (evt.which) ? evt.which : evt.keyCode;
          if (charCode != 46 && charCode > 31 
            && (charCode < 48 || charCode > 57))
             return false;

          return true;
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
				$('input[name=date-range-picker]').daterangepicker({
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
		</script>
    
</html>
  
