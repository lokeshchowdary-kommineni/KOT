<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false"%>
<html>
<head>

</head>
<body>
<c:if test="${ not empty tableList}">

    <%--<c:forEach var="tables" items="${tableList}">
        <input type="button" class="btn btn-success" value="${tables}" onclick="AjaxKOTList(this.value);"/>
    </c:forEach>--%>
    
        <c:forEach items="${tableList}" var="tbllist">
            <c:set var="x" value="0"></c:set>
            <c:forEach items="${orderKOTtableList}" var="orderlist">
                <c:if test="${tbllist == orderlist}" >
                    <c:set var="x" value="1"></c:set>
                </c:if>
            </c:forEach>
            <c:choose>
                <c:when test="${x==1}">
                <div class="col-md-3" style="padding: 5px 20px 5px 5px;">    
                    <input type="button" class="btn btn-danger" value="${tbllist}" onclick="emptyKOTForm(this.value);AjaxKOTList(this.value);" onblur="getSelectedTableName(this.value);" style="width: 100%;"/>
                </div>
                </c:when>

                <c:otherwise>
                <div class="col-md-3" style="padding: 5px 20px 5px 5px;">    
                    <input type="button" class="btn btn-success" value="${tbllist}" onclick="emptyKOTForm(this.value);AjaxKOTList(this.value);" onblur="getSelectedTableName(this.value);" style="width: 100%;"/>
                </div>
                </c:otherwise>
            </c:choose>
        </c:forEach>
              
</c:if>
        
        

<c:if test="${empty tableList}">
    <c:forEach var="tables" items="${tableList}">
        <a>
            <div id="tableNames">
                No Tables Available.
            </div>    
        </a>
    </c:forEach>
</c:if>
</body>
</html>
