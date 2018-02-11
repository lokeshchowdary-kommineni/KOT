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
     <script src="assets/accounting-js/number-to-words.js"></script>
	<link rel="stylesheet" href="assets/css/inv.css" />
        <style>
        body {
            position: relative;
            width: ${not empty print.pageWidth ? print.pageWidth:'21'}cm !important;  
            height: ${not empty print.pageHeight ? print.pageHeight:'29.7'}cm !important; 
           margin-left: ${not empty print.marginLeft ? print.marginLeft:'0'}cm !important;
          margin-right: ${not empty print.marginRight ? print.marginRight:'0'}cm !important;
          margin-top: ${not empty print.marginTop? print.marginTop:'0'}cm !important;
          margin-bottom: ${not empty print.marginBottom ? print.marginBottom:'0'}cm !important;

            color: #001028;
            background: #FFFFFF; 
            font-family: Arial, sans-serif; 
            font-size: 12px; 
            font-family: Arial;
          }    
        </style>
      
  </head>
  <body>
    <header class="clearfix">
      <c:if test="${Type eq 'plain'}">      
      <div id="logo">
        <!--<img src="assets/images/logo_New.png">-->
        <c:if test="${not empty companyFrom.companyLogo}">    
            <div class="col-md-3">                                        
            <div class="image-crop">
             <img class="cropper-hidden" src="${pageContext.request.contextPath}/Logo/${companyFrom.companyLogo}" >
            </div>                                                                        
           </div>
      </c:if>  
        <h2>${company.companyName}</h2>
      </div>
      </c:if>
        <c:if test="${sales.mode=='Cash'}">
            <h1>CASH BILL</h1>
        </c:if>
        <c:if test="${sales.mode=='Credit'}">
        <h1>INVOICE</h1>
        </c:if>
      <c:if test="${Type eq 'plain'}">     
      <div id="company" class="clearfix">
          
        <div><b>${company.companyName}</b></div>
        <div>${company.address}</div>
        <div>${company.city}</div>
        
        <br>
        <div><span><b>Tin :</b></span> ${company.companyTin}</div>
        <div><span><b>Email :</b></span> ${company.companyEmail}</div>
        <div><span><b>Contact No :</b></span> ${company.phoneNo}</div>
        
      </div>
      </c:if>  
      <div id="project">
          <div><span><b>Name :</b></span> ${buyer.buyerName}</div>
          <div><span><b>Address :</b></span>${buyer.address}</div>
          <div><span><b>City :</b></span>${buyer.location}</div>
          <div><span><b>Contact No :</b></span>${buyer.cellNo}</div>
          <div><span><b>Tin :</b></span>${buyer.tin}</div>
          
          <br>
          <div><span><b>Invoice No : </b></span>${sales.invoiceNo}</div>
          <div><span><b>Date : </b></span><fmt:formatDate value="${sales.date}" pattern="dd/MM/yyyy"/></div>
        
      </div>
    </header>
    <main >
     
      <table >
        <thead>
          <tr>
              <th><b>S.NO</b></th>
            <th><b>ITEM NAME</b></th>
           
            <th><b>QTY</b></th>
            <th><b>UNIT</b></th>
            <th><b>RATE</b></th>            
            <c:if test="${sales.category=='GST Dealers(B2B)' || withVat!=null}">   
            <th><b>IGST</b></th>
            <th><b>CGST</b></th>
            <th><b>SGST</b></th>
            </c:if>
            <th><b>AMOUNT</b></th>
          </tr>
        </thead>
        <tbody>
        <c:forEach items="${salesItem}" var="salesItem" varStatus="status">
          <tr>
            <td>${status.index+1}</td>
              
            <td>${salesItem[0]}</td>   
            <td>${salesItem[1]}</td> 
            <td>${salesItem[2]}</td>
            <td>${salesItem[3]}</td>           
            <c:if test="${sales.category=='GST Dealers(B2B)' || withVat!=null}">
            <td>${salesItem[7]}</td>    
            <td>${salesItem[5]}</td>
            <td>${salesItem[6]}</td>
            </c:if>
             <td>${salesItem[4]}</td>
          </tr>
        </c:forEach>
          <tr>
             <c:if test="${sales.category=='GST Dealers(B2B)' || withVat!=null}">
            <td></td>
            <td></td>
            <td></td>
            </c:if>
            <td></td>
            <td colspan="4"><b>GROSS</b></td>
            <!--<td class="total">${sales.assessValue}</td>-->
             <td class="total">${sales.assessValue+sales.totalCgst+sales.totalVat}</td>
          </tr>
          <c:if test="${sales.category=='GST Dealers(B2B)' || withVat!=null}">
          <tr>
           
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td colspan="4"><b>IGST</b></td>
            <td class="total">${sales.totalIgst}</td>
          </tr>
          </c:if>
          <c:if test="${sales.category=='GST Dealers(B2B)' || withVat!=null}">
          <tr>            
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td colspan="4"><b>CGST</b></td>
            <td class="total">${sales.totalCgst}</td>
          </tr>
          </c:if>
          <c:if test="${sales.category=='GST Dealers(B2B)' || withVat!=null}">
          <tr>
           
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td colspan="4"><b>SGST</b></td>
            <td class="total">${sales.totalVat}</td>
          </tr>
          </c:if>
          
          <tr>
            <c:if test="${sales.category=='GST Dealers(B2B)' || withVat!=null}">
            <td></td>
            <td></td>
            <td></td>
            </c:if>
          
            <td></td>
            <td colspan="4"><b>ROUND</b></td>
            <td class="total">${sales.addLess}</td>
          </tr>
          <tr>
            <c:choose>
            <c:when test="${sales.category=='GST Dealers(B2B)' || withVat!=null}">
            <td></td>
             <td id="word" > <script> word.innerHTML=convertNumberToWords(${sales.assessValue+sales.totalCgst+sales.totalVat+sales.totalCgst});</script>Only</td>
             
            <td></td>
            <td colspan="5" class="grand total"><b>GRAND TOTAL</b></td>
             <td class="grand total">${sales.invoiceAmount}</td>
            
            </c:when>
            <c:otherwise>
            <td id="word"> <script> word.innerHTML=convertNumberToWords(${sales.invoiceAmount});</script>Only <br><b>INCLUSIVE OF GST.<br><b>GST INNVOICE WILL BE ISSUED AT THE COUNTER</td>  
           
            <td colspan="4" class="grand total"><b>GRAND TOTAL</b></td>
            
            <td  class="grand total">${sales.invoiceAmount}</td>   
           
            
            </c:otherwise>
            </c:choose>
          </tr>
          
        </tbody>
      </table>
          <div id="footer">
               
          </div>    
   
    </main>
    <footer>
          Invoice was created on a computer and is valid without the signature and seal.
    </footer>
  </body>
</html>
