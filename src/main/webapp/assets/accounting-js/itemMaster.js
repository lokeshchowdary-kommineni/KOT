function onChangeComCode() {  
    var id=$("#comCode").val();
   
    $.ajax({  
     type : "POST",   
     url : "ComCodeOnChenage.html",   
     data : {id:id},
     dataType: "json",
     success : function(response) {
        //Assign value to textbox
        if(response.category===null){
            $("#category").val('');
            $("#tr").val('');
        }else{
            $("#category").val(response.category);
            $("#tr").val(response.taxRate);
            $("#taxSgstItem").val(response.sgstRate);
            $("#taxCgstItem").val(response.cgstRate);
            $("#igstItem").val(response.igstRate);
        }
     },  
     error : function(e) {   
      alert('Error: ' + e);   
     }  
    });  
}

function onChangeAltUnit() {  
    var unit=$("#unit").val();
    var altUnit=$("#altUnit").val();
   
    if(altUnit.length===0){
        $("#altUnitError").text("");
        $('#whereAltUnit').attr('readonly', true);
        $('#vapAlt').attr('readonly', true);
        $('#vbpAlt').attr('readonly', true);
        $('#capAlt').attr('readonly', true);
        $('#cbpAlt').attr('readonly', true);
        
        $('#whereAltUnit').val('');
        $("#lpAlt").val('');
        $("#epAlt").val('');
        $("#tpAlt").val('');
        $("#vapAlt").val('');
        $("#vbpAlt").val('');
        $("#capAlt").val('');
        $("#cbpAlt").val('');
    }
    else if(unit===altUnit){
        $("#altUnitError").text("Unit and Alt.Unit shoud not same");
        $('#whereAltUnit').attr('readonly', true);
        $('#vapAlt').attr('readonly', true);
        $('#vbpAlt').attr('readonly', true);
        $('#capAlt').attr('readonly', true);
        $('#cbpAlt').attr('readonly', true);
    }
    else{
       $("#altUnitError").text("");
       $('#whereAltUnit').attr('readonly', false);
       $('#vapAlt').attr('readonly', false);
       $('#vbpAlt').attr('readonly', false);
       $('#capAlt').attr('readonly', false);
       $('#cbpAlt').attr('readonly', false);
    }
}
function onChangeBasicPriceVat() {
    var pv=$("#basicVatPrice").val();
    if(pv.length===0){
     $("#basicPrice").val('');
    }
    onChangeBasicPrice();
    
}

function onChangeBasicPrice() {  
/////////////////////////////////////First Box Calculation///////////////////////////////////////////////
    var p=$("#basicPrice").val();
    var pv=$("#basicVatPrice").val();
    var lr=$("#lr").val();
    var er=$("#er").val();
    var tr=parseFloat($("#tr").val());
    var whereAltUnit=$('#whereAltUnit').val();
    var la=$("#la").val();
    var lp=$("#lp").val();
    var ea=$("#ea").val();
    var ep=$("#ep").val();
    var ta=$("#ta").val();
    var tp=$("#tp").val();
    
    //Make basic+vat price readonly when enter value in basic price
    
    if(p.length>0){
     $("#basicVatPrice").attr('readonly', true);
     
    }
    if(pv.length>0){
       
       $("#basicVatPrice").attr('readonly', false);
       
       //Calculate basic price and make it readonly
        p=(pv*100)/(100+tr);
        var round=parseFloat(p.toFixed(2));
        
        $("#basicPrice").val(round);
        $("#basicPrice").attr('readonly', true);
        
       //Remove LR% and ER% value and make it readonly
        $("#lr").val('');
        $("#er").val('');
        $("#lr").attr('readonly', true);
        $("#er").attr('readonly', true);
    }
   
    
    
    
    //Assign value 0 when LR% is empty
    if(lr.length===0){
        lr=0;
    }
    //Assign value 0 when ER% is empty
    if(er.length===0){
        er=0;
    }
     //Assign value 0 when LA is empty
    if(la.length===0){
        la=0;
    }
    //Assign value 0 when LP is empty
    if(lp.length===0){
        lp=0;
    }
    //Assign value 0 when EA is empty
    if(ea.length===0){
        ea=0;
    }
    //Assign value 0 when EP is empty
    if(ep.length===0){
        ep=0;
    }
    //Assign value 0 when TA is empty
    if(ta.length===0){
        ta=0;
    }
    //Assign value 0 when TP is empty
    if(tp.length===0){
        tp=0;
    }
   
     
     //LA formula implementation
     la=(lr/100)*p;
     $("#la").val(la.toFixed(2));
     
     //LP formula implementation
     lp=(p-la);
     $("#lp").val(lp.toFixed(2));
     
     //EA formula implementation
     ea=(er/100)*lp;
     $("#ea").val(ea.toFixed(2));
     
     //EP formula implementation
     ep=(lp+ea);
     $("#ep").val(ep.toFixed(2));
     
     //EA formula implementation
     ta=(tr/100)*ep;
     $("#ta").val(ta.toFixed(2));
     
     //EP formula implementation
     tp=(ep+ta);
     $("#tp").val(tp.toFixed(2));
     
/////////////////////////////////////End First Box Calculation///////////////////////////////////////////////
/////////////////////////////////////Sale Price for VAT Dealers Box Calculation///////////////////////////////////////////////    
    var va=$("#va").val();
    var vb=$("#vb").val();
    var ca=$("#ca").val();
    var cb=$("#cb").val();
    var vap=$("#vap").val();
    var vbp=$("#vbp").val();
    var cap=$("#cap").val();
    var cbp=$("#cbp").val();
    
    //Assign value 0 when VA% is empty
    if(va.length===0){
        va=0;
    }
    //Assign value 0 when VB% is empty
    if(vb.length===0){
        vb=0;
    }
    //Assign value 0 when CA% is empty
    if(ca.length===0){
        ca=0;
    }
    //Assign value 0 when CB% is empty
    if(cb.length===0){
        cb=0;
    }
    //Assign value 0 when VAP is empty
    if(vap.length===0){
        vap=0;
    }
    //Assign value 0 when VBP is empty
    if(vbp.length===0){
        vbp=0;
    }
    //Assign value 0 when CAP is empty
    if(cap.length===0){
        cap=0;
    }
    //Assign value 0 when CBP is empty
    if(cbp.length===0){
        cbp=0;
    }
    
    //VAP formula implementation
     vap=(ep*100)/(100-va);
   //alert(vap);
     $("#vap").val(rounding(vap));
    // alert(rounding(vap));
    //VBP formula implementation
     vbp=(ep*100)/(100-vb);
     $("#vbp").val(rounding(vbp));
     
    //CAP formula implementation
//     cap=(ep*100+ep*tr)/(100-ca);
         cap=tp+(ca*tp/100);
     //alert(cap);
     $("#cap").val((cap.toFixed(2)));

    //CBP formula implementation
     cbp=tp+(cb*tp/100);
     //alert(cbp);
     $("#cbp").val((cbp.toFixed(2)));


/////////////////////////////////////Sale Price for VAT Dealers Box Calculation///////////////////////////////////////////////         
    //When where Alt Unit not empty
    if(whereAltUnit.length>0){
        $("#lpAlt").val((lp/whereAltUnit).toFixed(2));
        $("#epAlt").val((ep/whereAltUnit).toFixed(2));
        $("#tpAlt").val((tp/whereAltUnit).toFixed(2));
        
        $("#vapAlt").val(rounding(vap/whereAltUnit));
        $("#vbpAlt").val(rounding(vbp/whereAltUnit));
        $("#capAlt").val((cap/whereAltUnit).toFixed(2));
        $("#cbpAlt").val((cbp/whereAltUnit).toFixed(2));
        $("#vapHid").val(rounding(vap/whereAltUnit));
        $("#vbpHid").val(rounding(vbp/whereAltUnit));
        $("#capHid").val(rounding(cap/whereAltUnit));
        $("#cbpHid").val(rounding(cbp/whereAltUnit));
    }
    else
    {
         $("#vapHid").val('');
        $("#vbpHid").val('');
        $("#capHid").val('');
        $("#cbpHid").val('');
    }   
    
   
    
    if(p.length===0 && pv.length===0){
      
        $("#basicVatPrice").attr('readonly', false);
        $("#basicPrice").attr('readonly', false);
         
        $("#lr").val('');
        $("#er").val('');
        $("#lr").attr('readonly', false);
        $("#er").attr('readonly', false);
        
        $("#la").val('');
        $("#lp").val('');
        $("#lpAlt").val('');
        $("#ea").val('');
        $("#ep").val('');
        $("#epAlt").val('');
        $("#ta").val('');
        $("#tp").val('');
        $("#tpAlt").val('');
        
        var vap=$("#vap").val('');
        var vbp=$("#vbp").val('');
        var cap=$("#cap").val('');
        var cbp=$("#cbp").val('');
    }
    
    $('#vapCheckBox').attr('checked', false);
    $('#vbpCheckbox').attr('checked', false);
    $('#capCheckbox').attr('checked', false);
    $('#cbpCheckbox').attr('checked', false);
    $("#vap").attr('readonly', true);
    $("#vbp").attr('readonly', true);
    $("#cap").attr('readonly', true);
    $("#cbp").attr('readonly', true);
    
}
function rounding(a){
           
            var a_fixed=parseFloat(a).toFixed(2);
                    
            var before=a_fixed.toString().split(".")[0];//before
           
            var after=a_fixed.toString().split(".")[1];//after
       
           
             var length=a_fixed.toString().split(".")[0].length;//before
            
             if(length == 1){
                 var calc=(Math.ceil(after / 10) * 10);
            
           

if(calc > 10 && calc!=100){
var finalResult= before+"."+calc;  
var check=parseFloat(finalResult).toFixed(2);
return check;

}
else if( calc == 10){

var finalResult=before+"."+calc;
var check=parseFloat(finalResult).toFixed(2);
return check;

}
else if(isNaN(calc) || calc == 0 )  {
   
 var finalResult=parseInt(before+"."+00);
var check=parseFloat(finalResult).toFixed(2);
return check;

}


if( calc == 100)
{
  
var finalResult=(parseInt(before)+1).toFixed(2);
var check=parseFloat(finalResult).toFixed(2);
return check;

}
   } else if(length == 2)
   {
       
        var calc=(Math.ceil(after / 25) * 25);
       
        
if(calc > 25 && calc!=100){
    var finalResult=before+"."+calc;
var check=parseFloat(finalResult).toFixed(2);
return check;


}
else if( calc == 25){
 var finalResult=before+"."+calc;
var check=parseFloat(finalResult).toFixed(2);
return check;

}
else if(isNaN(calc) || calc == 0 )  {
   
 var finalResult=parseInt(before+"."+00);
var check=parseFloat(finalResult).toFixed(2);
return check;

}
else if( calc == 100)
{
    
 var finalResult=(parseInt(before)+1).toFixed(2);
var check=parseFloat(finalResult).toFixed(2);
return check;
//document.getElementById("cc").value=(parseInt(before)+1).toFixed(2);
}
   }
   else if(length == 3)
   {
    var calc=(Math.ceil(after / 10) * 10);
   
   if(isNaN(calc) || calc == 0){
      var finalResult=parseInt(before+"."+00);
var check=parseFloat(finalResult).toFixed(2);
return check;  
   } 
var before=a.toString().split(".")[0]; //before

var after=a.toString().split(".")[1]; //after

var calc=(parseInt(before)+1);


var finalResult=calc.toFixed(2);
var check=parseFloat(finalResult).toFixed(2);
return check;
//document.getElementById("cc").value=calc.toFixed(2);  
       
   }
   else if(length == 4)
   {
     
var before=a.toString().split(".")[0]; //before

var after=a.toString().split(".")[1]; //after

var calc=(Math.ceil(before / 10) * 10);

 var calcAfter=(Math.ceil(after / 10) * 10);


if(isNaN(calcAfter) || calcAfter == 0){
      var finalResult=parseInt(before+"."+00);
var check=parseFloat(finalResult).toFixed(2);
return check;  
   } 
 

var finalResult=calc.toFixed(2);
var check=parseFloat(finalResult).toFixed(2);
return check;
//document.getElementById("cc").value=calc.toFixed(2);  
   }
    else if(length >= 5)
   {
     
var before=a.toString().split(".")[0]; //before

var after=a.toString().split(".")[1]; //after

var calc=(Math.ceil(before / 100) * 100);

var calcAfter=(Math.ceil(after / 100) * 100);

if(isNaN(calcAfter) || calcAfter == 0){
      var finalResult=parseInt(before+"."+00);
var check=parseFloat(finalResult).toFixed(2);
return check;  
   } 

var finalResult=calc.toFixed(2);
var check=parseFloat(finalResult).toFixed(2);
return check;
//document.getElementById("cc").value=calc.toFixed(2);  
   }
           
            }
            

function onChangeMcMca() {  
    var mc=$("#mc").val();
    var mca=$("#mca").val();
    
    if(mc.length===0 && mca.length===0){
        $('#mca').attr('readonly', false);
        $('#mc').attr('readonly', false);
    }
    if(mc.length>0){
        $('#mca').attr('readonly', true);
    }
    if(mca.length>0){
       $('#mc').attr('readonly', true);
    }
}

function onChangeVAPCheckBox() {  
   
    if($("#vapCheckBox").prop('checked') === true){
        $('#vap').attr('readonly', false);
         $('#va').attr('readonly',true);
         
    }
    else{
        $('#vap').attr('readonly', true);
         $('#va').attr('readonly',false);
         
    }
}
function onChangeVAP() {  
    var vap=$("#vap").val();
    var ep=$("#ep").val();
    
    var va=100*(vap-ep)/vap;
    $("#va").val(va.toFixed(3));
}

function onChangeVBPCheckBox() {  
   
    if($("#vbpCheckbox").prop('checked') === true){
        $('#vbp').attr('readonly', false); 
         $('#vb').attr('readonly', true);
    }
    else{
        $('#vbp').attr('readonly', true);
        $('#vb').attr('readonly', false);
    }
}
function onChangeVBP() {  
    var vbp=$("#vbp").val();
    var ep=$("#ep").val();
    
    var vb=100*(vbp-ep)/vbp;
    $("#vb").val(vb.toFixed(3));
}

function onChangeCAPCheckBox() {  
   
    if($("#capCheckbox").prop('checked') === true){
        $('#cap').attr('readonly', false);
        $('#ca').attr('readonly', true);
    }
    else{
        $('#cap').attr('readonly', true);
        $('#ca').attr('readonly', false);
    }
}
function onChangeCAP() {
    var whereAltUnit=$('#whereAltUnit').val();
    
    var cap=$("#cap").val();
    var ep=$("#ep").val();
    var tr=$("#tr").val();
    var tp=$("#tp").val();
    var ca=(100*(cap-tp))/tp;
    $("#ca").val(ca.toFixed(3));
    var calalt=(cap/whereAltUnit);
    $("#capAlt").val(calalt.toFixed(2));
    
    
}

function onChangeCBPCheckBox() {  
   
    if($("#cbpCheckbox").prop('checked') === true){
        $('#cbp').attr('readonly', false);
        $('#cb').attr('readonly', true);
    }
    else{
        $('#cbp').attr('readonly', true);
        $('#cb').attr('readonly', false);
}
}
function onChangeCBP() {  
    var whereAltUnit=$('#whereAltUnit').val();
    var cbp=$("#cbp").val();
    var ep=$("#ep").val();
    var tr=$("#tr").val();
    var tp=$("#tp").val();
    var cb=(100*(cbp-tp))/tp;
    $("#cb").val(cb.toFixed(3));
    var cbpAlt=(cbp/whereAltUnit);
    $("#cbpAlt").val(cbpAlt.toFixed(2));
}

function onChangeStock() {  
    var stock=$("#stock").val();
    var rate=$("#rate").val();
    
    if(stock.length>0){
        $('#rate').attr('readonly', false);
        var stockValue=stock*rate;
        $("#stockValue").val(stockValue.toFixed(3));
    }
    else{
       $('#rate').attr('readonly', true); 
    }
}
// alternative unit validation for vapAlt
function vapAlter(){
    var vapInput = document.getElementById('vapAlt').value;
    var varfinal=parseFloat(vapInput).toFixed(2);
    var vapHidInput = document.getElementById('vapHid').value;
     var varfinalHidInput=parseFloat(vapHidInput).toFixed(2);
   
    if(varfinal >= varfinalHidInput){
        return true;
    }
    else{
        alert("Value should be greater than the existing value in VAP(Alt)");
        $('#vapAlt').val(varfinalHidInput);
       // vapInput.value=varfinalHidInput;
       
    }
   
    }
    // alternative unit validation for vbpAlt
function vbpAlter(){
    var vapInput = document.getElementById('vbpAlt').value;
    var varfinal=parseFloat(vapInput).toFixed(2);
    var vapHidInput = document.getElementById('vbpHid').value;
     var varfinalHidInput=parseFloat(vapHidInput).toFixed(2);
   
    if(varfinal >= varfinalHidInput){
        return true;
    }
    else{
        alert("Value should be greater than the existing value in VBP(Alt)");
        $('#vbpAlt').val(varfinalHidInput);
    }
   
    }
    // alternative unit validation for capAlt
function capAlter(){
    var vapInput = document.getElementById('capAlt').value;
    var varfinal=parseFloat(vapInput).toFixed(2);
    var vapHidInput = document.getElementById('capHid').value;
     var varfinalHidInput=parseFloat(vapHidInput).toFixed(2);
   
    if(varfinal >= varfinalHidInput){
        return true;
    }
    else{
        alert("Value should be greater than the existing value in CAP(Alt)");
        $('#capAlt').val(varfinalHidInput);
    }
   
    }
    // alternative unit validation for cbpAlt
function cbpAlter(){
    var vapInput = document.getElementById('cbpAlt').value;
    var varfinal=parseFloat(vapInput).toFixed(2);
    var vapHidInput = document.getElementById('cbpHid').value;
     var varfinalHidInput=parseFloat(vapHidInput).toFixed(2);
   
    if(varfinal >= varfinalHidInput){
        return true;
    }
    else{
        alert("Value should be greater than the existing value in CBP(Alt)");
        $('#cbpAlt').val(varfinalHidInput);
    }
   
    }
