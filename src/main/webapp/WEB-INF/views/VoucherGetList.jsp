<%-- 
    Document   : GetVoucherList
    Created on : Feb 23, 2017, 5:03:40 PM
    Author     : MR
--%>

<%-- 
    Document   : VoucherBankClearance
    Created on : Feb 21, 2017, 10:54:17 AM
    Author     : MR
--%>

<%@page  pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="Header.jsp" %>
                                                                            <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                                                                                <thead>
                                                                                    <tr>
                                                                                        <th class="center">
                                                                                            <label class="pos-rel">
                                                                                                    <input type="checkbox" class="ace" />
                                                                                                    <span class="lbl"></span>
                                                                                            </label>
                                                                                        </th>
                                                                                        <th>S.No</th>
                                                                                        <th>Voucher No</th>
                                                                                        <th>Date</th>
                                                                                        <th>CH/DD No</th>
                                                                                        <th>Date</th>
                                                                                        <th>Bank Name</th>
                                                                                        <th>Amount</th>
                                                                                        <th>Action</th>
                                                                                    </tr>
                                                                                </thead>

                                                                                <tbody>
                                                                                    <c:forEach items="${getVoucherList}" var="list2">
                                                                                    <tr>
                                                                                        <td class="center">
                                                                                                <label class="pos-rel">
                                                                                                        <input type="checkbox" class="ace" />
                                                                                                        <span class="lbl"></span>
                                                                                                </label>
                                                                                        </td>
                                                                                        <td>${list2[0]}</td>
                                                                                        <td>${list2[0]}</td>
                                                                                        <td>${list2[0]}</td>
                                                                                        <td>${list2[0]}</td>
                                                                                        <td>${list2[0]}</td>
                                                                                        <td>${list2[0]}</td>
                                                                                        <td>${list2[0]}</td>
                                                                                        <td>
                                                                                                <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
                                                                                                        <span class="green">
                                                                                                                <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                                                                                        </span>
                                                                                                </a> |
                                                                                                <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
                                                                                                        <span class="red">
                                                                                                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                                                                        </span>
                                                                                                </a>
                                                                                        </td>
                                                                                    </tr>
                                                                                    </c:forEach>

                                                                                </tbody>
                                                                            </table>
                                                                       