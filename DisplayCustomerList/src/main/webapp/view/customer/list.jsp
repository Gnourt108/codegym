<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2026-01-29
  Time: 1:50 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách khách hàng</title>
    <c:import url="../layout/library.jsp"/>
</head>
<body>
    <c:import url="../layout/nav.jsp"/>
    <div class="row mx-3">
        <a href="/customer?action=add">
            <button class="btn btn-warning my-3">Thêm mới</button>
        </a>
        <table class="table table-striped table-dark">
            <thead>
                <tr>
                    <th scope="col">STT</th>
                    <th scope="col">Tên</th>
                    <th scope="col">Ngày sinh</th>
                    <th scope="col">Địa chỉ</th>
                    <th scope="col">Hình ảnh</th>
                </tr>
            </thead>
            <c:forEach items="${customerList}" var="customer" varStatus="status">
                <tr>
                    <th>${status.count}</th>
                    <th>${customer.getName()}</th>
                    <td>
                        <fmt:formatDate value="${customer.getDate()}" pattern="dd/MM/yyyy"/>
                    </td>
                    <td>${customer.getAddress()}</td>
                    <td>
                        <img src="${pageContext.request.contextPath}/uploads/${customer.img}"
                             alt="Ảnh khách hàng"
                             width="80"
                             height="80"
                             style="object-fit: cover; border-radius: 8px;">
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>
