<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Sales Bill</title>
	<link rel="stylesheet" href="assets/css/inv.css" />
         <script src="assets/accounting-js/number-to-words.js"></script>
         <c:if test="${sales.nameOfMediator=='CASH MEDIATOR'}">
         <c:if test="${buyer.openingType=='DR'}">
         <script>
           alert("Your Amount is DR");
             </script>             
             </c:if>             
             </c:if>

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
        <h2>${company.companyName}</h2>
      </div>
              
        <h1>INVOICE</h1>
        
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
          <div><span><b>Name :</b></span> ${buyer.buyerName}</div>
          <div><span><b>Contact No :</b></span>${buyer.cellNo}</div>
          <div><span><b>Address :</b></span>${buyer.address}</div>
      </div>
    </header>
    <main>
        
      <table>
        <thead>
          <tr>
            <th><b>BILL NO</b></th>
            <th><b>DATE</b></th>
            <th><b>AMOUNT</b></th>
            
          </tr>
        </thead>
        <tbody>
        
          <tr>
                       
            <td>${sales.invoiceNo}</td>   
            <td><fmt:formatDate value="${sales.date}" pattern="dd/MM/yyyy"/></td> 
            <td>${sales.mca}</td>
          </tr>
     
          <tr>
              <td id="word"> <script> word.innerHTML=convertNumberToWords(${sales.mca});</script>Only</td>
            <td><b>MCA AMOUNT</b></td>
            <td>${sales.mca}</td>
          </tr>
        </tbody>
      </table>
      <br>
     <div style="float:right;"><span><b>Mediator's Signature:</b></span> ____________________________</div>
    </main>
<!--    <footer>
      Invoice was created on a computer and is valid without the signature and seal.
    </footer>-->
  </body>
</html>