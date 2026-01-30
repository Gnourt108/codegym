<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Calculator Application</title>
    <c:import url="view/layout/library.jsp"/>
</head>
<body>
    <h1>Chương trình tính toán</h1>
    <div class="container mt-4">
        <div class="row justify-content-center">
            <div class="col-12 col-md-6 col-lg-5">
                <form action="/calculate" method="post">
                    <div class="container mt-3">
                        <label class="me-2" for="">Nhập số thứ nhất</label>
                        <input type="number" placeholder="Nhập số thứ nhất" name="n1">
                    </div>
                    <div class="container mt-3">
                        <label class="form-label">Chọn phép tính</label>
                        <select class="form-select" name="operator">
                            <option selected>-- Chọn --</option>
                            <option value="addition">Cộng</option>
                            <option value="subtraction">Trừ</option>
                            <option value="multiplication">Nhân</option>
                            <option value="division">Chia</option>
                        </select>
                    </div>
                    <div class="container mt-3">
                        <label class="me-2" for="">Nhập số thứ hai</label>
                        <input type="number" placeholder="Nhập số thứ hai" name="n2">
                    </div>
                    <button class="btn btn-primary w-100 mt-3">Tính</button>
                </form>
                <c:if test="${not empty message}">
                    <div class="alert alert-danger mt-3">
                            ${message}
                    </div>
                </c:if>
                <c:if test="${empty message}">
                    <c:if test="${not empty result}">
                        <div class="alert alert-success mt-3">
                            Kết quả: ${result}
                        </div>
                    </c:if>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>