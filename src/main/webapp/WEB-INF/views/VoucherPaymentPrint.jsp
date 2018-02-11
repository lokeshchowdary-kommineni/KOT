<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="now" value="<%=new java.util.Date()%>" />
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Voucher Payment Bill</title>
	<link rel="stylesheet" href="assets/css/inv.css" />
        <style>
            .marginAlign{
                margin-left: 50px;
                font-size: 12px;
                font-family: Arial;
            }
            #company{
                font-size: 12px;
                font-family: Arial;
            }
            table{
                font-size: 13px;
            }
            .alignParalel{
                display: inline-block;
                width: 195px;
                text-align: left;
              }
        </style>
  </head>
  <body>
    <header class="clearfix">
      <div id="logo">
           <c:if test="${not empty companyFrom.companyLogo}">    
            <div class="col-md-3">                                        
            <div class="image-crop">
             <img class="cropper-hidden" src="${pageContext.request.contextPath}/Logo/${companyFrom.companyLogo}" >
            </div>                                                                        
           </div>
      </c:if>  
        <!--<img src="assets/images/logo_New.png">-->
        <h2>${company.companyName}</h2>
      </div>
        <c:if test="${(vouch.vouchType=='payment')  && (vouch.vouchMode=='cash')}">
            <h1>PAYMENT CASH VOUCHER</h1>
        </c:if>
        <c:if test="${(vouch.vouchType=='payment') && (vouch.vouchMode=='cheque')}">
            <h1>PAYMENT CHEQUE VOUCHER</h1>
        </c:if>
        <c:if test="${(vouch.vouchType=='receipt')  && (vouch.vouchMode=='cash')}">
            <h1>RECEIPT CASH VOUCHER</h1>
        </c:if>
        <c:if test="${(vouch.vouchType=='receipt') && (vouch.vouchMode=='cheque')}">
            <h1>RECEIPT CHEQUE VOUCHER</h1>
        </c:if>
      <div id="company" class="clearfix">
          <div>
        <div>&nbsp;</div> 
        <div><b>${fn:toUpperCase(company.companyName)},</b></div>
        <div>${company.address}</div>
        <div>${company.city}</div>        
        <br>
        <div><span style="display:inline-table;width:92px;text-align:left;"><b>Tin</b></span>: ${company.companyTin}</div>
        <div><span style="display:inline-table;width:92px;text-align:left;"><b>Email</b></span>: ${company.companyEmail}</div>
        <div><span style="display:inline-table;width:92px;text-align:left;"><b>Contact No</b></span>: ${company.phoneNo}</div>
        <div><span style="display:inline-table;width:92px;text-align:left;"><b>Date</b></span>:<fmt:formatDate value="${vouch.vouchCashDate}" pattern="dd/MM/yyyy"/> </div>
      </div>
          </div>
      <div id="project">
          <div class="marginAlign">&nbsp;</div>
<!--          <div class="marginAlign">${vouch.vouchType}</div>-->
          
        <c:if test="${(vouch.vouchType=='payment')  && (vouch.vouchMode=='cash')}">
            <div class="marginAlign"><span class="alignParalel"><b>Paid To</b></span>: ${fn:toUpperCase(ledger.nameOfLedger)}</div>
        </c:if>
        <c:if test="${(vouch.vouchType=='payment') && (vouch.vouchMode=='cheque')}">
            <div class="marginAlign"><span class="alignParalel"><b>Paid To</b></span>: ${fn:toUpperCase(ledger.nameOfLedger)}</div>
          
        </c:if>
        <c:if test="${(vouch.vouchType=='receipt')  && (vouch.vouchMode=='cash')}">
            <div class="marginAlign"><span class="alignParalel"><b>Received with thanks from</b></span>: ${fn:toUpperCase(ledger.nameOfLedger)}</div>
        </c:if>
        <c:if test="${(vouch.vouchType=='receipt') && (vouch.vouchMode=='cheque')}">
            <div class="marginAlign"><span class="alignParalel"><b>Received with thanks from</b></span>: ${fn:toUpperCase(ledger.nameOfLedger)}</div>
            
        </c:if>
         
          <div class="marginAlign"><span class="alignParalel"><b>Address</b></span>: ${ledger.address}</div>
          <div class="marginAlign"><span class="alignParalel"><b>Contact No</b></span>: ${ledger.cellNo}</div>
          <div class="marginAlign"><span class="alignParalel"><b>Pan No</b></span>: ${ledger.panNo}</div>
          
          <br>
          <c:if test="${vouch.vouchMode=='cheque'}">
            <div class="marginAlign"><span class="alignParalel"><b>Cheque No</b></span>: ${vouch.vouchCheqNo}</div>
          </c:if>
          <c:if test="${(vouch.vouchType=='receipt') && (vouch.vouchMode=='cheque')}">
           
            <div class="marginAlign"><span class="alignParalel"><b>Bank</b></span>: ${vouch.vouchNameofBank}</div>
         </c:if>
         <c:if test="${(vouch.vouchType=='payment') && (vouch.vouchMode=='cheque')}">
            <!--<div class="marginAlign"><span class="alignParalel"><b>Paid To</b></span>: ${fn:toUpperCase(ledger.nameOfLedger)}</div>-->
            <div class="marginAlign"><span class="alignParalel"><b>Bank</b></span>: ${vouch.vouchNameofBank}</div>
        </c:if>   
          <div class="marginAlign"><span class="alignParalel"><b>Date</b></span>: <fmt:formatDate value="${vouch.vouchCheqDate}" pattern="dd/MM/yyyy"/></div>
        
      </div>
    </header>
    <main>
        <div class="marginAlign"><span><b>  <c:if test="${(vouch.vouchType=='receipt') && (vouch.vouchMode=='cheque')}"> Subject to Realisation</c:if>  Towards Account :</b></span> <tt>${vouch.vouchNarration}</tt></div>
        <br>
      <table>
        <tbody>
          <tr>
            <td>Amount In Words (INR) :</td>
            <td class="total"><b>${amountInWords}</b></td>
            <td colspan="2">Amount (INR) :</td>
            <td class="total"><b>${vouch.vouchAmount}</b></td>
            <td>${vouch.vouchCashType}</td>
          </tr>
        </tbody>
      </table>
          <br>
      <c:if test="${(vouch.vouchType=='receipt')}"> 
          <div style="float:right;"><span><b>For </b></span> ____________________________</div>
      </c:if>
      <c:if test="${(vouch.vouchType=='payment')}"> 
          <div style="float:right;"><span><b>Receiver's Signature:</b></span> ____________________________</div>
      </c:if>     
     
    </main>
<!--    <footer>
      Receipt/Payment was created on a computer and is valid without the signature and seal.
    </footer>-->
  </body>
</html>
