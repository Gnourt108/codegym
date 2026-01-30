<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Thêm khách hàng mới</title>
    <c:import url="../layout/library.jsp"/>
</head>
<body>
    <c:import url="../layout/nav.jsp"/>
    <div class="container mt-4">
        <div class="row justify-content-center">
            <div class="col-12 col-md-6 col-lg-5">
                <form action="/customer?action=add" method="post" enctype="multipart/form-data">
                    <div class="input-group mb-3">
                        <span class="input-group-text">🙎</span>
                        <input type="text" class="form-control" placeholder="Username" name="name">
                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text">🎂</span>
                        <input type="date" class="form-control" name="birthday">
                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text">📍</span>
                        <input type="text" class="form-control" placeholder="Địa chỉ" name="address">
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Ảnh khách hàng</label>
                        <input type="file" class="form-control" name="image">
                    </div>

                    <button class="btn btn-primary w-100">Thêm khách hàng</button>
                </form>
            </div>
        </div>
    </div>

</body>
</html>
