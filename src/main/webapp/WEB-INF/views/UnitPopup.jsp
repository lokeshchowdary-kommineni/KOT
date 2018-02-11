<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="account"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
  <meta charset="utf-8">
<title>Unit Conversion</title>
<style type="text/css">
    form{
        margin: 20px 0;
    }
    form input, button{
        padding: 5px;
    }
    table{
        width: 100%;
        margin-bottom: 20px;
		border-collapse: collapse;
    }
    table, th, td{
        border: 1px solid #cdcdcd;
    }
    table th, table td{
        padding: 10px;
        text-align: left;
    }
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
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
            var tliter=totalPrice;
            alert(tliter);
            $(".someTotalPrice").html(totalPrice);
        });
    }

    $(document).ready(function () {
        
        $('#xd').click(function() {
            var lines = '<input type="button" value="Delete" onclick="deleteRow(this)"/>';
            var input1 = $("#input1").val();
            var input2 = $("#input2").val();
            var input3 = $("#input3").val();
            var output = $("#output").val();
             var lines1 = '<td class = "classPrice">' +output+'</td>';
            var markup = "<tr><td>"+input1 +"</td><td>" + input2+ "</td><td>" +input3 + "</td>" + lines1+ "<td>"+lines+"</td></tr>";

            $('#TableBody').append(markup);
            sumOfColumns();
        });
    });
</script>
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
		 
          
		<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>

			 
			<div class="main-content">
				<div class="main-content-inner">
					
					<div class="page-content">
						<div class="page-header" style="background-color: #438EB9;">
							<h1 style="color: #fff;">Unit Conversion</h1>
							<div style="text-align: center; ">
                                                             <span style="color:orangered;" > ${buyerMessage} </span>
                                                             
                                                              </div>
						</div>
			                <form>
                                            ml : <select name="input1" id="input1" onkeyup="calc()">
                                                       <option value="180">180</option>
                                                       <option value="375">375</option>
                                                       <option value="750">750</option>
                                                       <option value="1000">1000</option>
                                                 </select>

                                      No Of Qty :<input type="text" name="input2" id="input2" onkeyup="calc()" value=""> 

                                           Case :   <input type="text" name="input3" id="input3" onkeyup="calc()" value=""> <a href="javascript: void(0)" onClick="calc()"></a>

                                          Liter :  <input type="text" name="output"  id="output" value="">
      
       
    	
                                        </form>
                                        <table id="Table">
                                            <thead>
                                            <tr>
                                                <td>ml</td>
                                                <td>No of Quantity</td>
                                                <td>Case</td>
                                                <td>Liter</td>
                                                <td>Delete</td>
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

                                    <input type="button" id="xd" value="add row">
                                    <input type="submit" class="btn btn-info" name="submit"   value="submit" onclick="SetName();"/>
                                        
                                        
                                        </div>
				</div>

			</div>
                                                             
                      

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
		
<script type="text/javascript">
    function SetName() {
        if (window.opener != null && !window.opener.closed) {
                var purchaseQty = window.opener.document.getElementById("purchaseQty");
                 alert(purchaseQty);
                var pName=0;
                 pName = document.getElementById("tid").innerHTML;
                 purchaseQty.valueOf(pName);
                alert(pName);
        }
        window.close();
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

</body>
</html>
