<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Stock Journal Invoice</title>
	<link rel="stylesheet" href="assets/css/inv.css" />
  </head>
  <body>
    <header class="clearfix">
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
      
      <h1>Stock Journal Invoice </h1>
      
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
          <div><span><b>Invoice No : </b></span>${StockJournal.stockJournalId}</div>      
        
      </div>
    </header>
          <p><h2><b>Source Items(Components)&nbsp;&nbsp;<span><b>Date : </b></span><fmt:formatDate value="${StockJournal.date}" pattern="dd/MM/yyyy"/></b></h2></p>

    <main>
      <table>
        <thead>
          <tr>
            <th><b>S.NO</b></th>
            <th><b>ITEM NAME</b></th>           
            <th><b>QTY</b></th>
            <th><b>UNIT</b></th>
            <th><b>RATE</b></th>
            <th><b>AMOUNT</b></th>
           </tr>
        </thead>
        <tbody>
        <c:forEach items="${StockJournallist}" var="StockJournallist" varStatus="status">
          <tr>
           
            <td>${status.index+1}</td>            
            <td>${StockJournallist[1]}</td>   
            <td>${StockJournallist[2]}</td> 
            <td>${StockJournallist[3]}</td>
            <td>${StockJournallist[4]}</td>
            <td>${StockJournallist[5]}</td>
           
          </tr>
        </c:forEach>   
          
          <tr>   
            <td></td>
            <td colspan="4" class="grand total"><b>Total Cost of Components</b></td>
            <td class="grand total">${StockJournal.totalCostComp}</td>
          </tr>
          <tr>   
            <td></td>
            <td colspan="4" class="grand total"><b>Value Addition for Promotion Cost</b></td>
            <td class="grand total">${StockJournal.valuAdditiion}</td>
          </tr>
          <tr>   
            <td></td>
            <td colspan="4" class="grand total"><b>Value Of Finished Goods</b></td>
            <td class="grand total">${StockJournal.valueOfFinishGood}</td>
          </tr>
        </tbody>
      </table>
     
    </main>
          <hr>
        <p><h2><b>Generated Items(Finished Goods)&nbsp;&nbsp;<span><b>Date : </b></span><fmt:formatDate value="${StockJournal.fdate}" pattern="dd/MM/yyyy"/></b></h2></p>

      
    <main>
      <table>
        <thead>
          <tr>
              <th><b>S.NO</b></th>
            <th><b>ITEM NAME</b></th>           
            <th><b>QTY</b></th>
            <th><b>UNIT</b></th>
            <th><b>RATE</b></th>
            <th><b>AMOUNT</b></th>
           </tr>
        </thead>
        <tbody>
        <c:forEach items="${StockJournallistF}" var="StockJournallist" varStatus="status">
          <tr>           
        
             <td>${status.index+1}</td>    
            <td>${StockJournallist[1]}</td>   
            <td>${StockJournallist[2]}</td> 
            <td>${StockJournallist[3]}</td>
            <td>${StockJournallist[4]}</td>
            <td>${StockJournallist[5]}</td>
           
          </tr>
        </c:forEach>  
          
          <tr>   
            <td></td>
            <td colspan="4" class="grand total"><b>Value Of Finished Goods</b></td>
            <td class="grand total">${StockJournal.valueOfFinishGood}</td>
          </tr>
        </tbody>
      </table>    
    </main>
      <hr> 
         <br>
         <br>
         <br>
         <br>
         <br>
         <br>
         <br>
         <br>
         <b>Prepared by</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <b>Verified by</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <b>Authorized by</b>
         <br>
         <br>
         <br>
         
         <hr>
<center><p>Invoice was created on a computer and is valid without the signature and seal.</p></center>
    
  </body>
</html>