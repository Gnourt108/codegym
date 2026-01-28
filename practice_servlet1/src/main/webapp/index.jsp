<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <h1>Ứng dụng Product Discount Calculator</h1>
    <form action="calculator" method="post">
        <input type="text" placeholder="Nhập mô tả của sản phẩm" name="name_product">
        <input type="number" placeholder="Nhập giá niêm yết của sản phẩm" name="price">
        <input type="number" placeholder="Nhập tỷ lệ chiết khấu (%)" name="percent">
        <button>Tính ch</button>
    </form>
    <h2>Sản phẩm tên: ${productName} có giá chiếc khấu là: ${discountPrice}</h2>
</body>
</html>