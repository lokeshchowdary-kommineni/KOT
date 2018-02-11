<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Purchase Estimate Invoice</title>
    <script src="assets/accounting-js/number-to-words.js"></script>
	<link rel="stylesheet" href="assets/css/inv.css" />
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
      
      <h1>Purchase Estimate </h1>
      <div id="company" class="clearfix">
          
        <div><b>${company.companyName}</b></div>
        <div>${company.address}</div>
        <div>${company.city}</div>
        
        <br>
        <div><span><b>Tin :</b></span> ${company.companyTin}</div>
        <div><span><b>Email :</b></span> ${company.companyEmail}</div>
        <div><span><b>Contact No :</b></span> ${company.phoneNo}</div>
        
      </div>
      <div id="project">       
          <br>
          <div><span><b>Purchase Estimate No : </b></span>${PurchaseEstimate.purchaseEstimateId}</div>
          <div><span><b>Date : </b></span><fmt:formatDate value="${PurchaseEstimate.date}" pattern="dd/MM/yyyy"/></div>
        
      </div>
    </header>
    <main>
      <table>
        <thead>
          <tr>
              <th><b>S.NO</b></th>
            <th><b>ITEM NAME</b></th>           
            <th><b>QTY</b></th>
            <th><b>UNIT</b></th>
            <th><b>TP RATE</b></th>
            <th><b>IGST</b></th>
            <th><b>CGST</b></th>
            <th><b>SGST</b></th>
             <th><b>AMOUNT</b></th>
           </tr>
        </thead>
        <tbody>
        <c:forEach items="${PurchaseEstimateItem}" var="PurchaseEstimateItem" varStatus="status">
          <tr>
            <td>${status.index+1}</td>
              
            <td>${PurchaseEstimateItem[0]}</td>   
            <td>${PurchaseEstimateItem[1]}</td> 
            <td>${PurchaseEstimateItem[2]}</td>
            <td>${PurchaseEstimateItem[3]}</td>
            <td>${PurchaseEstimateItem[7]}</td>
            <td>${PurchaseEstimateItem[5]}</td>
            <td>${PurchaseEstimateItem[6]}</td>
             <td>${PurchaseEstimateItem[4]}</td>
       
          </tr>
        </c:forEach>
          <tr>   
            <td></td>
            <td id="word"> <script> word.innerHTML=convertNumberToWords(${PurchaseEstimate.totalAmount});</script>Only</td>
            <td></td>
            <td colspan="5" class="grand total"><b>GRAND TOTAL</b></td>
            <td class="grand total">${PurchaseEstimate.totalAmount}</td>
          </tr>
        </tbody>
      </table>
     
    </main>
    <footer>
      Invoice was created on a computer and is valid without the signature and seal.
    </footer>
  </body>
</html>