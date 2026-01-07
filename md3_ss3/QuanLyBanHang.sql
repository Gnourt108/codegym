-- Tạo cơ sở dữ liệu
CREATE DATABASE IF NOT EXISTS QuanLyBanHang CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE QuanLyBanHang;

CREATE TABLE Customer (
    cID INT PRIMARY KEY AUTO_INCREMENT,
    cName VARCHAR(100) NOT NULL,
    cAge INT CHECK (cAge > 0)
);

CREATE TABLE Product (
    pID INT PRIMARY KEY AUTO_INCREMENT,
    pName VARCHAR(100) NOT NULL,
    pPrice DECIMAL(10,2) NOT NULL CHECK (pPrice >= 0)
);

CREATE TABLE `Order` (
    oID INT PRIMARY KEY AUTO_INCREMENT,
    cID INT NOT NULL,
    oDate DATE NOT NULL,
    oTotalPrice DECIMAL(12,2) DEFAULT NULL,  
    CONSTRAINT FK_Order_Customer 
        FOREIGN KEY (cID) REFERENCES Customer(cID)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE OrderDetail (
    oID INT NOT NULL,
    pID INT NOT NULL,
    odQTY INT NOT NULL CHECK (odQTY > 0),
    PRIMARY KEY (oID, pID), 
    CONSTRAINT FK_OrderDetail_Order 
        FOREIGN KEY (oID) REFERENCES `Order`(oID)
        ON DELETE CASCADE 
        ON UPDATE CASCADE,
    CONSTRAINT FK_OrderDetail_Product 
        FOREIGN KEY (pID) REFERENCES Product(pID)
        ON DELETE RESTRICT 
        ON UPDATE CASCADE
);


CREATE INDEX IDX_Order_cID ON `Order`(cID);
CREATE INDEX IDX_Order_oDate ON `Order`(oDate);
CREATE INDEX IDX_OrderDetail_pID ON OrderDetail(pID);


-- Insert dữ liệu vào bảng Customer
INSERT INTO Customer (cID, cName, cAge) VALUES
(1, 'Minh Quan', 10),
(2, 'Ngoc Oanh', 20),
(3, 'Hong Ha', 50);

-- Insert dữ liệu vào bảng Product
INSERT INTO Product (pID, pName, pPrice) VALUES
(1, 'May Giat', 3),
(2, 'Tu Lanh', 5),
(3, 'Dieu Hoa', 7),
(4, 'Quat', 1),
(5, 'Bep Dien', 2);

-- Insert dữ liệu vào bảng Order
INSERT INTO `Order` (oID, cID, oDate, oTotalPrice) VALUES
(1, 1, '2006-03-21', NULL),
(2, 2, '2006-03-23', NULL),
(3, 1, '2006-03-16', NULL);

-- Insert dữ liệu vào bảng OrderDetail
INSERT INTO OrderDetail (oID, pID, odQTY) VALUES
(1, 1, 3),
(1, 3, 7),
(1, 4, 2),
(2, 1, 1),
(3, 1, 8),
(2, 5, 4),
(2, 3, 3);

-- Câu 1: Hiển thị các thông tin  gồm oID, oDate, oPrice của tất cả các hóa đơn trong bảng Order
select oID, oDate, oTotalPrice
from `Order`;

-- Câu 2: Hiển thị danh sách các khách hàng đã mua hàng, và danh sách sản phẩm được mua bởi các khách
select 
	c.cID,
    c.cName,
    c.cAge,
    p.pID,
    p.pName,
    p.pPrice,
    od.odQTY,
    o.oDate
from Customer c 
inner join `Order` o on c.cID = o.cID
inner join OrderDetail od on o.oID = od.oID
inner join Product p on od.pID = p.pID
order by c.cName, o.oDate

-- Câu 3: Hiển thị tên những khách hàng không mua bất kỳ một sản phẩm nào
select c.cID, c.cName, c.cAge
from Customer c
left join `Order` o ON c.cID = o.cID
where o.oID is null;

-- Câu 4: Hiển thị mã hóa đơn, ngày bán và giá tiền của từng hóa đơn (giá một hóa đơn được tính bằng tổng giá bán của từng loại mặt hàng xuất hiện trong hóa đơn. Giá bán của từng loại được tính = odQTY*pPrice)
select 
	o.oID,
    o.oDate,
    SUM(od.odQTY * p.pPrice) AS oTotalPrice
from `Order` o
inner join OrderDetail od on o.oID = od.oID
inner join Product p on od.pID = p.pID
group by o.oID, o.oDate
order by o.oID;