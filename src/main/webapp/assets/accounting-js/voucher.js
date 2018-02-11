/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function()
{
    var id = $('#pId').val();
    if($('#pId').val() !== '' && $('#changeMode').val() === 'cheque'){
        $('#showCheque').show();
       
        $('#showDate').show();
        $('#showBankGroup').show();
        $('#hideToGroup').hide();
        $('#vouchContraForCheq').show();
        $('#vouchContraForCheqDate').show(); 
         $('#showBankName').hide();
      
        
    }
     else if($('#pId').val() !== '' && $('#changeMode').val() === 'transfer'){
        $('#showCheque').show();
        $('#showBankName').show();
        $('#showDate').show();
        $('#showBankGroup').show();
        $('#hideToGroup').hide();
        $('#vouchContraForCheqTransfer').show();
        $('#vouchContraForCheqDate').show();
      
        
    }
    
    // CHANGE LEDGER ACCOUNTS FROM ONE TO ANOTHER
    $(function() {
//        $('#changeTo').prop('disabled', true);
        var disbledOption = undefined;
        var disbledOption2 = undefined;
//        alert(disbledOption);

        $("#changeFrom").change(function() {
            $('#changeTo').prop('disabled', false);
            if(disbledOption !== "undefined" ){
//                alert(disbledOption);
                $("#changeTo option[value*='"+disbledOption+"']").prop('disabled',false);
                $("#changeTo option[value*='"+disbledOption+"']").prop('disabled',false).css('color', '#858585');
            }
            if(disbledOption2 !== null){
                $("#changeToCash option[value*='"+disbledOption2+"']").prop('disabled',false);
                $("#changeToCash option[value*='"+disbledOption2+"']").prop('disabled',false).css('color', '#858585');
            }

            var a=$(this).val();
            var b = $('.changeToContra').val();
            if($('#changeMode').val() === 'cash' || $('.changeToContra').val() === 'cheque') {
//                alert("Jabez" +a);
                $("#changeTo option[value*='"+a+"']").prop('disabled',true);
                disbledOption = $('#changeTo').find(":disabled").text();
                $('#changeTo').find(":disabled").css('color', 'Gainsboro');
            }
            if($('#changeMode').val() === 'cheque') {
                $("#changeToCash option[value*='"+a+"']").prop('disabled',true);
                disbledOption2 = $('#changeToCash').find(":disabled").text();
                $('#changeToCash').find(":disabled").css('color', 'Gainsboro');
            }
             
        });
    });
    
    
    

    // for receipt insert function
    $('#changeMode').change(function() {
        var a = $('#changeMode').val();
        var type = $('#typeReceipt').val();
        if($('#changeMode').val() === 'cheque') {
            // show form fields
            $('#showCheque').show();
            $('#showBankName').show();
            $('#showDate').show();
            $('#showBankGroup').show();
            $('#hideToGroup').hide();
             
            
            // Empty form field values
            $("#changeTo").prop("selectedIndex", 0);
            $("#vouchCheqBal").val('');
            $("#authValue").val('0');
            $("#vouchCheqType").prop("selectedIndex", 0);
        }
       else {
            // hide form fields
            $('#showCheque').hide();
            $('#showBankName').hide();
            $('#showDate').hide();
            $('#showBankGroup').hide();
            $('#hideToGroup').show();
            
            // Empty form field values
            $("#authValue").val('1');
            $("#changeToCash").prop("selectedIndex", 0);
            $("#vouchCheqBal").val('');
            $("#vouchCheqType").prop("selectedIndex", 0);
        }
    });
    
    
    // change on CONTRA ledger acounts FROM ONE TO ANOTHER
    $(function() {

        var disbledOption = undefined;

        $("#changeFromContra").change(function() {
            $('#changeTo').prop('disabled', false);
            if(disbledOption !== null){
                $("#changeTo option[value*='"+disbledOption+"']").prop('disabled',false);
                $("#changeTo option[value*='"+disbledOption+"']").prop('disabled',false).css('color', '#858585');
            }

            var a=$(this).val();
            var b = $('#changeTo').val();
            if($('#changeMode').val() === 'cash' || $('#changeMode').val() === 'cheque') {
                $("#changeTo option[value*='"+a+"']").prop('disabled',true);
                disbledOption = $('#changeTo').find(":disabled").text();
                $('#changeTo').find(":disabled").css('color', 'Gainsboro');
            }
             
        });
    });
    
    // for CONTRA insert function
    $('.changeModeForContra').change(function() {
//        alert(12312);
        if($('.changeModeForContra').val() === 'cheque'  ) {
           
            $('#vouchContraForCheq').show();
            $('#vouchContraForCheqDate').show();
            $('#showBankName').hide();
        }
        else if($('.changeModeForContra').val() === 'transfer') {
             
            $('#vouchContraForCheq').show();
            $('#vouchContraForCheqDate').show(); 
            $('#showBankName').show();
        }
        else {
            $('#vouchContraForCheq').hide();
            $('#vouchContraForCheqDate').hide();
            $('#showBankName').show();
        }
    });
    
    // change on JOURNAL ledger acounts FROM ONE TO ANOTHER
    $(function() {

        var disbledOption = undefined;

        $("#changeFromjournal").change(function() {
            $('#changeTo').prop('disabled', false);
            if(disbledOption !== null){
                $("#changeTo option[value*='"+disbledOption+"']").prop('disabled',false);
                $("#changeTo option[value*='"+disbledOption+"']").prop('disabled',false).css('color', '#858585');
            }

            var a=$(this).val();
            var b = $('#changeTo').val();
                $("#changeTo option[value*='"+a+"']").prop('disabled',true);
                disbledOption = $('#changeTo').find(":disabled").text();
                $('#changeTo').find(":disabled").css('color', 'Gainsboro');
             
        });
    });
    
      
        
    //  Ajax Call @ResponseBody Starts from here
        
            $('.changeFromRecAndPay').change(function() {
                var id=$(this).val();
                var termA = "id="+id;
                    $.ajax({
                        url: "getLedgerAccount.html",
                        dataType: "json",
                        type: "GET",
                        data: termA,
                        success: function(response){
                            if(response.openingAmount.length===0){
                                
                                $('#vouchBal').val('');
                                $('#vouchCashType').val('');
                            }else{
                                $('#vouchBal').val(response.openingAmount);
                                $('#vouchCashType').val(response.openingType);
                            }
                        }
                    });                       
             });
             $('.changeToRecAndPay').change(function() {
                var id=$(this).val();
                var termA = "id="+id;
                    $.ajax({
                        url: "getLedgerAccount.html",
                        dataType: "json",
                        type: "GET",
                        data: termA,
                        success: function(response){
                            if(response.openingAmount.length===0){
                              
                                $('#vouchCheqBal').val('');
                                $('#vouchCashType').val('');
                            }else{
                                $('#vouchCheqBal').val(response.openingAmount);
                                $('#vouchCheqType').val(response.openingType);
                            }
                        }
                    });                       
             });
             
             $('.changeToCashRecAndPay').change(function() {
                var id=$(this).val();
                var termA = "id="+id;
                    $.ajax({
                        url: "getLedgerAccount.html",
                        dataType: "json",
                        type: "GET",
                        data: termA,
                        success: function(response){
                             if(response.openingAmount.length===0){
                                        
                                        $('#vouchCheqBal').val('');
                                        $('#vouchCheqType').val('');
                                    }else{
                                        $('#vouchCheqBal').val(response.openingAmount);
                                        $('#vouchCheqType').val(response.openingType);
                                    }
                        }
                    });                       
             });
             
             $('.changeFromContra').change(function() {
                 
                var id=$(this).val();
                
                var termA = "id="+id;
                
                    $.ajax({
                        url: "getLedgerAccount.html",
                        dataType: "json",
                        type: "GET",
                        data: termA,
                        success: function(response){
                                    
                                    if(response.openingAmount.length===0){
                                        
                                        $('#vouchBalFrom').val('');
                                        $('#vouchCashType').val('');
                                    }else{
                                       
                                        $('#vouchBalFrom').val(response.openingAmount);
                                        $('#vouchCashType').val(response.openingType);
                                    }
                        }
                    });                       
             });
             //purpose of CCB
               $('.changeFromContraa').click(function(){
              
              var id=$(this).val();
                
                var termA = "id="+id;
                
                    $.ajax({
                        url: "getLedgerAccount.html",
                        dataType: "json",
                        type: "GET",
                        data: termA,
                        success: function(response){
                                    
                                    if(response.openingAmount.length===0){
                                        
                                        $('#vouchBalFrom').val('');
                                        $('#vouchCashType').val('');
                                    }else{
                                       
                                        $('#vouchBalFrom').val(response.openingAmount);
                                        $('#vouchCashType').val(response.openingType);
                                    }
                        }
                    });                                 
                });     
 
             
             
             
             $('.changeToContra').change(function() {
                var id=$(this).val();
                var termA = "id="+id;
                    $.ajax({
                        url: "getLedgerAccount.html",
                        dataType: "json",
                        type: "GET",
                        data: termA,
                        success: function(response){
                            if(response.openingAmount.length===0){
                                        
                                        $('#vouchBalTo').val('');
                                        $('#vouchCheqType').val('');
                                    }else{
                                        $('#vouchBalTo').val(response.openingAmount);
                                        $('#vouchCheqType').val(response.openingType);
                                    }
                        }
                    });                       
             });
             

});

