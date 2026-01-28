<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2026-01-26
  Time: 10:37 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <form action="/sum" method="post">
    <input type="number" name="number1" placeholder="Nhập số thứ nhất">
    <input type="number" name="number2" placeholder="Nhập số thứ hai">
    <button>Tính toán</button>
  </form>
  <h1>Kết quả của phép tính trên là: ${tong}</h1>
</body>
</html>
