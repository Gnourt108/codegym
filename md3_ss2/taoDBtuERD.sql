DROP DATABASE IF EXISTS QuanLyVatTu;
CREATE DATABASE QuanLyVatTu CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE QuanLyVatTu;

create table NHACC(
	maNCC varchar(10) primary key,
    tenNCC nvarchar(100) not null,
    email varchar(100),
    website varchar(100)
);

create table VATTU(
	maVTu varchar(10) primary key,
    tenVTu nvarchar(100) not null,
    donViTinh nvarchar(20),
    soLuongTon int default 0,
    ghiChu nvarchar(255)
);

create table PHIEUXUAT(
	soPX varchar(10) primary key,
    ngayXuat date not null,
    nguoiNhan nvarchar(100),
    lyDo nvarchar(255)
);

create table PHIEUNHAP(
	soPN varchar(100) primary key,
    ngayNhap date not null,
    nguoiGiao nvarchar(100),
    ghiChu nvarchar(255)
);

create table DONDH(
	soDH varchar(10) primary key,
    ngayDH date not null,
    maNCC varchar(10) not null,
    trangThai nvarchar(50) default N'Chờ xử lí',
    foreign key (maNCC) references NHACC(maNCC)
		on delete restrict
        on update cascade
);

create table CTPX(
	soPX varchar(10),
    maVTu varchar(10),
    slXuat int not null check (slXuat > 0),
    dgXuat decimal(15,2) not null check (dgXuat > 0),
    primary key (soPX, maVTu),
    foreign key (soPX) references PHIEUXUAT(soPX)
		on delete cascade
        on update cascade,
	foreign key (maVTu) references VATTU(maVTu)
		on update cascade
        on delete cascade
);

create table CTPN(
	soPN varchar(10),
    maVTu varchar(10),
    slNhap int not null check (slNhap > 0),
    dgNhap decimal(15,2) not null check (dgNhap > 0),
    primary key (soPN, maVTu),
    foreign key (soPN) references PHIEUNHAP(soPN)
		on update cascade
        on delete cascade,
	foreign key (maVTU) references VATTU(maVTu)
		on update cascade
        on delete cascade
);

create table CTDH(
	soDH varchar(10),
    maVTu varchar(10),
    soLuong int not null check (soLuong > 0),
    donGia decimal(15,2) check (donGia >= 0),
    primary key (soDH, maVTu),
    foreign key (soDH) references DONDH(soDH)
		on delete cascade
        on update cascade,
	foreign key (maVTu) references VATTU(maVTu)
		on update cascade
        on delete cascade
);

create table SDT_NCC(
	maNCC varchar(10),
    sdt varchar(15),
    primary key (maNCC, sdt),
    foreign key (maNCC) references NHACC(maNCC)
    on delete cascade
    on update cascade
);

create table DIACHI_NCC(
	maNCC varchar(10),
    diaChi nvarchar(255),
    primary key (maNCC, diaChi),
    foreign key(maNCC) references NHACC(maNCC)
		on delete cascade
        on update cascade
);

INSERT INTO NHACC (maNCC, tenNCC, email, website) VALUES
('NCC01', N'Công ty Vật Tư Minh Long', 'minhlong@gmail.com', 'www.minhlong.vn'),
('NCC02', N'Công ty Thiết Bị Hòa Phát', 'hoaphat@gmail.com', 'www.hoaphat.vn'),
('NCC03', N'Công ty Xây Dựng An Phú', 'anphu@gmail.com'customer, 'www.anphu.vn');


INSERT INTO SDT_NCC (maNCC, sdt) VALUES
('NCC01', '0909123456'),
('NCC01', '0283899123'),
('NCC02', '0918123456'),
('NCC03', '0933123456');


INSERT INTO DIACHI_NCC (maNCC, diaChi) VALUES
('NCC01', N'Quận 1, TP.HCM'),
('NCC01', N'Khu công nghiệp Tân Bình'),
('NCC02', N'Quận Bình Thạnh, TP.HCM'),
('NCC03', N'TP. Thủ Đức, TP.HCM');


INSERT INTO VATTU (maVTu, tenVTu, donViTinh, soLuongTon, ghiChu) VALUES
('VT01', N'Xi măng Hà Tiên', N'Bao', 500, N'Xi măng xây dựng'),
('VT02', N'Sắt phi 10', N'Cây', 300, N'Sắt xây dựng'),
('VT03', N'Gạch đỏ', N'Viên', 10000, N'Gạch nung'),
('VT04', N'Cát xây', N'Khối', 200, N'Cát mịn');


INSERT INTO PHIEUNHAP (soPN, ngayNhap, nguoiGiao, ghiChu) VALUES
('PN01', '2024-03-01', N'Nguyễn Văn A', N'Nhập vật tư đầu tháng'),
('PN02', '2024-03-10', N'Trần Văn B', N'Nhập bổ sung');

INSERT INTO CTPN (soPN, maVTu, slNhap, dgNhap) VALUES
('PN01', 'VT01', 200, 85000),
('PN01', 'VT02', 100, 120000),
('PN02', 'VT03', 5000, 1200),
('PN02', 'VT04', 50, 150000);

INSERT INTO PHIEUXUAT (soPX, ngayXuat, nguoiNhan, lyDo) VALUES
('PX01', '2024-03-05', N'Lê Văn C', N'Xuất cho công trình A'),
('PX02', '2024-03-15', N'Phạm Văn D', N'Xuất cho công trình B');

INSERT INTO CTPX (soPX, maVTu, slXuat, dgXuat) VALUES
('PX01', 'VT01', 50, 90000),
('PX01', 'VT02', 30, 130000),
('PX02', 'VT03', 2000, 1500),
('PX02', 'VT04', 20, 180000);

INSERT INTO DONDH (soDH, ngayDH, maNCC, trangThai) VALUES
('DH01', '2024-02-25', 'NCC01', N'Đã duyệt'),
('DH02', '2024-03-08', 'NCC02', N'Chờ xử lí');

INSERT INTO CTDH (soDH, maVTu, soLuong, donGia) VALUES
('DH01', 'VT01', 300, 83000),
('DH01', 'VT02', 150, 118000),
('DH02', 'VT03', 6000, 1150);

