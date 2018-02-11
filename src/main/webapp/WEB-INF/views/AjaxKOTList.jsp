<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false"%>
<html>
<head>
    <script src="assets/accounting-js/kot.js"></script>
</head>
<body>
<c:if test="${ not empty kotList}">

    
        <div class="table-header">
            KOT List  
        </div>

        <div>
            <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                <tr>
                    <th>KOT</th>
                    <th>KOT Value</th>
                    <th>Time</th>
                </tr>
                <c:forEach var="kotlist" items="${kotList}">
                <tr>
                    <td style="text-align: center"><a class="green" href="#" onclick="editKOT('${kotlist.kotid}')">${kotlist.kotNo}</a></td>
                    <td style="text-align: center">${kotlist.cap}</td>
                    <td style="text-align: center">${kotlist.kotTimestamp}</td>
                </tr>
                </c:forEach>
            </table>
        </div>
    

</c:if>

   
    


</body>
</html>
