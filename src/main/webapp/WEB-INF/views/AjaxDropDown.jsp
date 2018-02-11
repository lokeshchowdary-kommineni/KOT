<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:if test="${Ledger=='1'}" >
<select class="form-control "  name="byLedger">
<option value="">---choose---</option>
<c:forEach items="${listAllLedger}" var="list">
    <option value="${list.idLedger}">${list.nameOfLedger}</option>
</c:forEach>
 </select>
</c:if>
<c:if test="${item=='2'}" >
<select   name="item" class="form-control" required>
<option value=""></option>
 <c:forEach items="${listAllItem}" var="il">
    <option value="${il.id}" >${il.itemName}</option>
 </c:forEach>   
  </select> 
</c:if>