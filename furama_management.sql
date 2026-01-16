drop database if exists `furama_management`;
create database furama_management;
use furama_management;

create table vi_tri(
	ma_vi_tri int primary key,
    ten_vi_tri varchar(45)
);

create table trinh_do(
	ma_trinh_do int primary key,
    ten_trinh_do varchar(45)
);

create table bo_phan(
	ma_bo_phan int primary key,
    ten_bo_phan varchar(45)
);

create table dich_vu_di_kem (
	ma_dich_vu_di_kem int primary key,
    ten_dich_vu_di_kem varchar(45),
    gia double,
    don_vi varchar(10),
    trang_thai varchar(45)
);

create table loai_khach(
	ma_loai_khach int primary key,
    ten_loai_khach varchar(45)
);


create table kieu_thue (
	ma_kieu_thue int primary key,
    ten_kieu_thue varchar(45)
);

create table loai_dich_vu(
	ma_loai_dich_vu int primary key,
    ten_loai_dich_vu varchar(45)
);


create table nhan_vien(
	ma_nhan_vien int primary key,
    ho_ten varchar(45),
    ngay_sinh date,
    so_cmnd varchar(45),
    luong double,
    so_dien_thoai varchar(45),
    email varchar(45),
    dia_chi varchar(45),
    ma_vi_tri int,
    ma_trinh_do int,
    ma_bo_phan int,
    
    foreign key (ma_vi_tri) references vi_tri(ma_vi_tri)
		on delete restrict
        on update cascade,
	foreign key (ma_trinh_do) references trinh_do(ma_trinh_do)
		on delete restrict
        on update cascade,
	foreign key (ma_bo_phan) references bo_phan(ma_bo_phan)
		on delete restrict
        on update cascade
);

create table khach_hang (
	ma_khach_hang int primary key,
    ma_loai_khach int,
    ho_ten varchar(45),
    ngay_sinh date,
    gioi_tinh bit(1),
    so_cmnd varchar(45),
    so_dien_thoai varchar(45),
    email varchar(45),
    dia_chi varchar(45),
    
    foreign key (ma_loai_khach) references loai_khach(ma_loai_khach)
		on delete restrict
        on update cascade
);

create table dich_vu (
	ma_dich_vu int primary key,
    ten_dich_vu varchar(45),
    dien_tich int,
    chi_phi_thue double,
    so_nguoi_toi_da int, 
    ma_kieu_thue int,
    ma_loai_dich_vu int,
    tieu_chuan_phong varchar(45),
    mo_ta_tien_nghi_khac varchar(45),
    dien_tich_ho_boi double,
    so_tang int,
    
    foreign key (ma_kieu_thue) references kieu_thue(ma_kieu_thue) 
		on delete restrict
        on update cascade,
	foreign key (ma_loai_dich_vu) references loai_dich_vu (ma_loai_dich_vu)
		on delete restrict
        on update cascade
);

create table hop_dong(
	ma_hop_dong int primary key,
    ngay_lam_hop_dong datetime,
    ngay_ket_thuc datetime,
    tien_dat_coc double,
    ma_nhan_vien int,
    ma_khach_hang int,
    ma_dich_vu int,
    
    foreign key (ma_nhan_vien) references nhan_vien(ma_nhan_vien)
		on delete restrict
        on update cascade,
	foreign key (ma_khach_hang) references khach_hang(ma_khach_hang)
		on delete restrict
		on update cascade,
	foreign key (ma_dich_vu) references dich_vu(ma_dich_vu)
		on delete restrict
        on update cascade
);

create table hop_dong_chi_tiet (
	ma_hop_dong_chi_tiet int primary key,
    ma_hop_dong int,
    ma_dich_vu_di_kem int,
    so_luong int,
    foreign key (ma_hop_dong) references hop_dong(ma_hop_dong) 
		on delete restrict
        on update cascade,
	foreign key (ma_dich_vu_di_kem) references dich_vu_di_kem(ma_dich_vu_di_kem)
		on delete restrict
        on update cascade
);

insert into vi_tri (ma_vi_tri, ten_vi_tri)
values 	(1, "Quản lý"),
		(2, "Nhân viên");

insert into trinh_do(ma_trinh_do, ten_trinh_do)
values	(1, "Trung cấp"),
		(2, "Cao Đẳng"),
        (3, "Đại Học"),
        (4, "Sau Đại Học");
        
insert into bo_phan (ma_bo_phan, ten_bo_phan)
values (1, "Sale-Marketing"),
		(2, "Hành chính"),
        (3, "Phục vụ"),
        (4, "Quản lý");
        
INSERT INTO nhan_vien (ma_nhan_vien, ho_ten, ngay_sinh, so_cmnd, luong, so_dien_thoai, email, dia_chi, ma_vi_tri, ma_trinh_do, ma_bo_phan)
VALUES 
    (1, 'Nguyễn Văn An', '1970-11-07', '456231786', 10000000, '0901234121', 'annguyen@gmail.com', '295 Nguyễn Tất Thành, Đà Nẵng', 1, 3, 1),
    (2, 'Lê Văn Bình', '1997-04-09', '654231234', 7000000, '0934212314', 'binhlv@gmail.com', '22 Yên Bái, Đà Nẵng', 1, 2, 2),
    (3, 'Hồ Thị Yến', '1995-12-12', '999231723', 14000000, '0412352315', 'thiyen@gmail.com', 'K234/11 Điện Biên Phủ, Gia Lai', 1, 3, 2),
    (4, 'Võ Công Toản', '1980-04-04', '123231365', 17000000, '0374443232', 'toan0404@gmail.com', '77 Hoàng Diệu, Quảng Trị', 1, 4, 4),
    (5, 'Nguyễn Bỉnh Phát', '1999-12-09', '454363232', 6000000, '0902341231', 'phatphat@gmail.com', '43 Yên Bái, Đà Nẵng', 2, 1, 1),
    (6, 'Khúc Nguyễn An Nghi', '2000-11-08', '964542311', 7000000, '0978653213', 'annghi20@gmail.com', '294 Nguyễn Tất Thành, Đà Nẵng', 2, 2, 3),
    (7, 'Nguyễn Hữu Hà', '1993-01-01', '534323231', 8000000, '0941234553', 'nhh0101@gmail.com', '4 Nguyễn Chí Thanh, Huế', 2, 3, 2),
    (8, 'Nguyễn Hà Đông', '1989-09-03', '234414123', 9000000, '0642123111', 'donghanguyen@gmail.com', '111 Hùng Vương, Hà Nội', 2, 4, 4),
    (9, 'Tòng Hoang', '1982-09-03', '256781231', 6000000, '0245144444', 'hoangtong@gmail.com', '213 Hàm Nghi, Đà Nẵng', 2, 4, 4),
    (10, 'Nguyễn Công Đạo', '1994-01-08', '755434343', 8000000, '0988767111', 'nguyencongdao12@gmail.com', '6 Hoà Khánh, Đồng Nai', 2, 3, 2);

insert into loai_khach (ma_loai_khach, ten_loai_khach)
values (1, "Diamond"),
		(2, 'Platinium'),
        (3, 'Gold'),
        (4, 'Silver'),
        (5, 'Member');
        
INSERT INTO khach_hang (ma_khach_hang, ma_loai_khach, ho_ten, ngay_sinh, gioi_tinh, so_cmnd, so_dien_thoai, email, dia_chi)
VALUES 
    (1, 5, 'Nguyễn Thị Hào', '1970-11-07', 0, '643431213', '0945423362', 'thihao07@gmail.com', '23 Nguyễn Hoàng, Đà Nẵng'),
    (2, 3, 'Phạm Xuân Diệu', '1992-08-08', 1, '865342123', '0954333333', 'xuandieu92@gmail.com', 'K77/22 Thái Phiên, Quảng Trị'),
    (3, 1, 'Trương Đình Nghệ', '1990-02-27', 1, '488645199', '0373213122', 'nghenhan2702@gmail.com', 'K323/12 Ông Ích Khiêm, Vinh'),
    (4, 1, 'Dương Văn Quan', '1981-07-08', 1, '543432111', '0490039241', 'duongquan@gmail.com', 'K453/12 Lê Lợi, Đà Nẵng'),
    (5, 4, 'Hoàng Trần Nhi Nhi', '1995-12-09', 0, '795453345', '0312345678', 'nhinhi123@gmail.com', '224 Lý Thái Tổ, Gia Lai'),
    (6, 4, 'Tôn Nữ Mộc Châu', '2005-12-06', 0, '732434215', '0988888844', 'tonnuchau@gmail.com', '37 Yên Thế, Đà Nẵng'),
    (7, 1, 'Nguyễn Mỹ Kim', '1984-04-08', 0, '856453123', '0912345698', 'kimcuong84@gmail.com', 'K123/45 Lê Lợi, Hồ Chí Minh'),
    (8, 3, 'Nguyễn Thị Hào', '1999-04-08', 0, '965656433', '0763212345', 'haohao99@gmail.com', '55 Nguyễn Văn Linh, Kon Tum'),
    (9, 1, 'Trần Đại Danh', '1994-07-01', 1, '432341235', '0643343433', 'danhhai99@gmail.com', '24 Lý Thường Kiệt, Quảng Ngãi'),
    (10, 2, 'Nguyễn Tâm Đắc', '1989-07-01', 1, '344343432', '0987654321', 'dactam@gmail.com', '22 Ngô Quyền, Đà Nẵng');
    
insert into kieu_thue (ma_kieu_thue, ten_kieu_thue)
values (1, 'year'),
		(2, 'month'),
        (3, 'day'),
        (4, 'hour');
        
insert into loai_dich_vu (ma_loai_dich_vu, ten_loai_dich_vu)
values (1, 'Villa'),
	(2, 'House'),
    (3, 'Room');
    
-- Insert data into dich_vu table
INSERT INTO dich_vu (ma_dich_vu, ten_dich_vu, dien_tich, chi_phi_thue, so_nguoi_toi_da, ma_kieu_thue, ma_loai_dich_vu, tieu_chuan_phong, mo_ta_tien_nghi_khac, dien_tich_ho_boi, so_tang)
VALUES 
    (1, 'Villa Beach Front', 25000, 10000000, 10, 3, 1, 'vip', 'Có hồ bơi', 500, 4),
    (2, 'House Princess 01', 14000, 5000000, 7, 2, 2, 'vip', 'Có thêm bếp nướng', NULL, 3),
    (3, 'Room Twin 01', 5000, 1000000, 2, 4, 3, 'normal', 'Có tivi', NULL, NULL),
    (4, 'Villa No Beach Front', 22000, 9000000, 8, 3, 1, 'normal', 'Có hồ bơi', 300, 3),
    (5, 'House Princess 02', 10000, 4000000, 5, 3, 2, 'normal', 'Có thêm bếp nướng', NULL, 2),
    (6, 'Room Twin 02', 3000, 900000, 2, 4, 3, 'normal', 'Có tivi', NULL, NULL);

-- Insert data into dich_vu_di_kem table
INSERT INTO dich_vu_di_kem (ma_dich_vu_di_kem, ten_dich_vu_di_kem, gia, don_vi, trang_thai)
VALUES 
    (1, 'Karaoke', 10000, 'giờ', 'tiện nghi, hiện tại'),
    (2, 'Thuê xe máy', 10000, 'chiếc', 'hỏng 1 xe'),
    (3, 'Thuê xe đạp', 20000, 'chiếc', 'tốt'),
    (4, 'Buffet buổi sáng', 15000, 'suất', 'đầy đủ đồ ăn, tráng miệng'),
    (5, 'Buffet buổi trưa', 90000, 'suất', 'đầy đủ đồ ăn, tráng miệng'),
    (6, 'Buffet buổi tối', 16000, 'suất', 'đầy đủ đồ ăn, tráng miệng');

-- Insert data into hop_dong table
INSERT INTO hop_dong (ma_hop_dong, ngay_lam_hop_dong, ngay_ket_thuc, tien_dat_coc, ma_nhan_vien, ma_khach_hang, ma_dich_vu)
VALUES 
    (1, '2020-12-08', '2020-12-08', 0, 3, 1, 3),
    (2, '2020-07-14', '2020-07-21', 200000, 7, 3, 1),
    (3, '2021-03-15', '2021-03-17', 50000, 3, 4, 2),
    (4, '2021-01-14', '2021-01-18', 100000, 7, 5, 5),
    (5, '2021-07-14', '2021-07-15', 0, 7, 2, 6),
    (6, '2021-06-01', '2021-06-03', 0, 7, 7, 6),
    (7, '2021-09-02', '2021-09-05', 100000, 7, 4, 4),
    (8, '2021-06-17', '2021-06-18', 150000, 3, 4, 1),
    (9, '2020-11-19', '2020-11-19', 0, 3, 4, 3),
    (10, '2021-04-12', '2021-04-14', 0, 10, 3, 5),
    (11, '2021-04-25', '2021-04-25', 0, 2, 2, 1),
    (12, '2021-05-25', '2021-05-27', 0, 7, 10, 1);

-- Insert data into hop_dong_chi_tiet table
INSERT INTO hop_dong_chi_tiet (ma_hop_dong_chi_tiet, ma_hop_dong, ma_dich_vu_di_kem, so_luong)
VALUES 
    (1, 2, 4, 5),
    (2, 2, 5, 8),
    (3, 2, 6, 15),
    (4, 3, 1, 1),
    (5, 3, 2, 11),
    (6, 1, 3, 1),
    (7, 1, 2, 2),
    (8, 12, 2, 2);

-- 2.Hiển thị thông tin của tất cả nhân viên có trong hệ thống có tên bắt đầu là một trong các ký tự “H”, “T” hoặc “K” và có tối đa 15 kí tự.
select *
from nhan_vien
where ho_ten like '% H%' or ho_ten like '% T%' or ho_ten like '% K%';

-- 3.	Hiển thị thông tin của tất cả khách hàng có độ tuổi từ 18 đến 50 tuổi và có địa chỉ ở “Đà Nẵng” hoặc “Quảng Trị”.
select * 
from khach_hang
where (dia_chi like '%Đà Nẵng%' or dia_chi like'%Quảng Trị%') and (YEAR(CURDATE()) - YEAR(ngay_sinh)) BETWEEN 18 AND 50

-- 4.Đếm xem tương ứng với mỗi khách hàng đã từng đặt phòng bao nhiêu lần. Kết quả hiển thị được sắp xếp tăng dần theo số lần đặt phòng của khách hàng. Chỉ đếm những khách hàng nào có Tên loại khách hàng là “Diamond”.
select k.ma_khach_hang, k.ho_ten, count(h.ma_hop_dong) as so_lan_dat
from khach_hang k
inner join hop_dong h on k.ma_khach_hang = h.ma_khach_hang
inner join loai_khach lkh on k.ma_loai_khach = lkh.ma_loai_khach
where lkh.ten_loai_khach = 'Diamond'
group by k.ma_khach_hang, k.ho_ten
order by so_lan_dat asc;

-- 5.	Hiển thị ma_khach_hang, ho_ten, ten_loai_khach, ma_hop_dong, ten_dich_vu, ngay_lam_hop_dong, ngay_ket_thuc, tong_tien (Với tổng tiền được tính theo công thức như sau: Chi Phí Thuê + Số Lượng * Giá, với Số Lượng và Giá là từ bảng dich_vu_di_kem, hop_dong_chi_tiet) cho tất cả các khách hàng đã từng đặt phòng. (những khách hàng nào chưa từng đặt phòng cũng phải hiển thị ra).
select 
	k.ma_khach_hang,
    k.ho_ten,
    lk.ten_loai_khach,
    hd.ma_hop_dong,
    dv.ten_dich_vu,
    hd.ngay_lam_hop_dong,
    hd.ngay_ket_thuc,
    (dv.chi_phi_thue + ifnull(SUM(hdct.so_luong * dvdk.gia), 0)) as tong_tien
from khach_hang k
left join loai_khach lk on k.ma_loai_khach = lk.ma_loai_khach
left join hop_dong hd on k.ma_khach_hang = hd.ma_khach_hang
left join dich_vu dv on hd.ma_dich_vu = dv.ma_dich_vu
left join hop_dong_chi_tiet hdct on hd.ma_hop_dong = hdct.ma_hop_dong
left join dich_vu_di_kem dvdk on hdct.ma_dich_vu_di_kem = dvdk.ma_dich_vu_di_kem
group by k.ma_khach_hang, k.ho_ten, lk.ten_loai_khach, hd.ma_hop_dong, 
         dv.ten_dich_vu, hd.ngay_lam_hop_dong, hd.ngay_ket_thuc, dv.chi_phi_thue;
         
-- 6. Hiển thị ma_dich_vu, ten_dich_vu, dien_tich, chi_phi_thue, ten_loai_dich_vu của tất cả các loại dịch vụ chưa từng được khách hàng thực hiện đặt từ quý 1 của năm 2021 (Quý 1 là tháng 1, 2, 3).
select ma_dich_vu, ten_dich_vu, dien_tich, chi_phi_thue, ldv.ten_loai_dich_vu
from dich_vu dv
inner join loai_dich_vu ldv on dv.ma_loai_dich_vu = ldv.ma_loai_dich_vu
where dv.ma_dich_vu not in (
	select distinct ma_dich_vu
    from hop_dong
    where year(ngay_lam_hop_dong) = 2021
    and quarter(ngay_lam_hop_dong) = 1
);

-- 7.	Hiển thị thông tin ma_dich_vu, ten_dich_vu, dien_tich, so_nguoi_toi_da, chi_phi_thue, ten_loai_dich_vu của tất cả các loại dịch vụ đã từng được khách hàng đặt phòng trong năm 2020 nhưng chưa từng được khách hàng đặt phòng trong năm 2021.
select ma_dich_vu, ten_dich_vu, dien_tich, so_nguoi_toi_da, chi_phi_thue, ldv.ten_loai_dich_vu
from dich_vu dv
inner join loai_dich_vu ldv on dv.ma_loai_dich_vu = ldv.ma_loai_dich_vu
where dv.ma_dich_vu in (
	select distinct ma_dich_vu
    from hop_dong
    where year(ngay_lam_hop_dong) = 2020
)
and dv.ma_dich_vu not in (
	select distinct ma_dich_vu
    from hop_dong
    where year(ngay_lam_hop_dong) = 2021
);

-- 8.Hiển thị thông tin ho_ten khách hàng có trong hệ thống, với yêu cầu ho_ten không trùng nhau. Học viên sử dụng theo 3 cách khác nhau để thực hiện yêu cầu trên.
-- cách 1: distinct
select distinct ho_ten
from khach_hang;

-- cách 2: group by
select ho_ten
from khach_hang
group by ho_ten;

-- Cách 3: union
select ho_ten
from khach_hang
union
select ho_ten
from khach_hang;

-- 9.	Thực hiện thống kê doanh thu theo tháng, nghĩa là tương ứng với mỗi tháng trong năm 2021 thì sẽ có bao nhiêu khách hàng thực hiện đặt phòng.
select month(ngay_lam_hop_dong) as thang, count(ma_khach_hang) as so_luong_khach_hang
from hop_dong
where year(ngay_lam_hop_dong) = 2021
group by month(ngay_lam_hop_dong)
order by thang;
    
-- 10.Hiển thị thông tin tương ứng với từng hợp đồng thì đã sử dụng bao nhiêu dịch vụ đi kèm. Kết quả hiển thị bao gồm ma_hop_dong, ngay_lam_hop_dong, ngay_ket_thuc, tien_dat_coc, so_luong_dich_vu_di_kem (được tính dựa trên việc sum so_luong ở dich_vu_di_kem).
select hd.ma_hop_dong, ngay_lam_hop_dong, ngay_ket_thuc, tien_dat_coc, ifnull(sum(hdct.so_luong), 0) as so_luong_dich_vu_di_kem
from hop_dong hd
left join hop_dong_chi_tiet hdct on hd.ma_hop_dong = hdct.ma_hop_dong
group by hd.ma_hop_dong, hd.ngay_lam_hop_dong, hd.ngay_ket_thuc, hd.tien_dat_coc;

-- 11.	Hiển thị thông tin các dịch vụ đi kèm đã được sử dụng bởi những khách hàng có ten_loai_khach là “Diamond” và có dia_chi ở “Vinh” hoặc “Quảng Ngãi”.
select dvdk.*
from dich_vu_di_kem dvdk
inner join hop_dong_chi_tiet hdct ON dvdk.ma_dich_vu_di_kem = hdct.ma_dich_vu_di_kem
inner join hop_dong hd ON hdct.ma_hop_dong = hd.ma_hop_dong
inner join khach_hang kh ON hd.ma_khach_hang = kh.ma_khach_hang
inner join loai_khach lk ON kh.ma_loai_khach = lk.ma_loai_khach
where lk.ten_loai_khach = 'Diamond'
  and (kh.dia_chi LIKE '%Vinh%' OR kh.dia_chi LIKE '%Quảng Ngãi%')
group by dvdk.ma_dich_vu_di_kem;

-- 12.Hiển thị thông tin ma_hop_dong, ho_ten (nhân viên), ho_ten (khách hàng), so_dien_thoai (khách hàng), ten_dich_vu, so_luong_dich_vu_di_kem (được tính dựa trên việc sum so_luong ở dich_vu_di_kem), tien_dat_coc của tất cả các dịch vụ đã từng được khách hàng đặt vào 3 tháng cuối năm 2020 nhưng chưa từng được khách hàng đặt vào 6 tháng đầu năm 2021.
select 
	hd.ma_hop_dong,
    nv.ho_ten AS ho_ten_nhan_vien,
    kh.ho_ten AS ho_ten_khach_hang,
    kh.so_dien_thoai,
    dv.ten_dich_vu,
    IFNULL(SUM(hdct.so_luong), 0) AS so_luong_dich_vu_di_kem,
    hd.tien_dat_coc
from hop_dong hd
inner join nhan_vien nv on hd.ma_nhan_vien = nv.ma_nhan_vien
inner join khach_hang kh on hd.ma_khach_hang = kh.ma_khach_hang
inner join dich_vu dv ON hd.ma_dich_vu = dv.ma_dich_vu
left join hop_dong_chi_tiet hdct on hd.ma_hop_dong = hdct.ma_hop_dong
where year(ngay_lam_hop_dong) = 2020
	and month(ngay_lam_hop_dong) in (10, 11, 12)
    and dv.ma_dich_vu not in (
		select distinct ma_dich_vu
        from hop_dong
        where year(ngay_lam_hop_dong) = 2021
        and month(ngay_lam_hop_dong) between 1 and 6
    )
group by hd.ma_hop_dong, nv.ho_ten, kh.ho_ten, kh.so_dien_thoai, 
         dv.ten_dich_vu, hd.tien_dat_coc;
         
-- 13.Hiển thị thông tin các Dịch vụ đi kèm được sử dụng nhiều nhất bởi các Khách hàng đã đặt phòng. (Lưu ý là có thể có nhiều dịch vụ có số lần sử dụng nhiều như nhau).
select dvdk.ma_dich_vu_di_kem, dvdk.ten_dich_vu_di_kem, SUM(hdct.so_luong) AS so_lan_su_dung
from dich_vu_di_kem dvdk
inner join hop_dong_chi_tiet hdct on dvdk.ma_dich_vu_di_kem = hdct.ma_dich_vu_di_kem
group by dvdk.ma_dich_vu_di_kem, dvdk.ten_dich_vu_di_kem
having so_lan_su_dung = (
	select max(total)
    from (
		select sum(so_luong) as total
        from hop_dong_chi_tiet
        group by ma_dich_vu_di_kem
    ) as so_lan_su_dung_nhieu_nhat
);

-- 14.	Hiển thị thông tin tất cả các Dịch vụ đi kèm chỉ mới được sử dụng một lần duy nhất. Thông tin hiển thị bao gồm ma_hop_dong, ten_loai_dich_vu, ten_dich_vu_di_kem, so_lan_su_dung (được tính dựa trên việc count các ma_dich_vu_di_kem).
select
	hd.ma_hop_dong,
    ldv.ten_loai_dich_vu,
    dvdk.ten_dich_vu_di_kem,
    COUNT(hdct.ma_dich_vu_di_kem) AS so_lan_su_dung
from hop_dong hd
inner join dich_vu dv on hd.ma_dich_vu = dv.ma_dich_vu
inner join loai_dich_vu ldv on dv.ma_loai_dich_vu = ldv.ma_loai_dich_vu
inner join hop_dong_chi_tiet hdct on hd.ma_hop_dong = hdct.ma_hop_dong
inner join dich_vu_di_kem dvdk on hdct.ma_dich_vu_di_kem = dvdk.ma_dich_vu_di_kem
group by hd.ma_hop_dong, ldv.ten_loai_dich_vu, dvdk.ten_dich_vu_di_kem
having so_lan_su_dung = 1;

-- 15.Hiển thi thông tin của tất cả nhân viên bao gồm ma_nhan_vien, ho_ten, ten_trinh_do, ten_bo_phan, so_dien_thoai, dia_chi mới chỉ lập được tối đa 3 hợp đồng từ năm 2020 đến 2021.
select
	nv.ma_nhan_vien,
    nv.ho_ten,
    td.ten_trinh_do,
    bp.ten_bo_phan,
    nv.so_dien_thoai,
    nv.dia_chi
from nhan_vien nv
inner join trinh_do td on nv.ma_trinh_do = td.ma_trinh_do
inner join bo_phan bp on nv.ma_bo_phan = bp.ma_bo_phan
inner join hop_dong hd on nv.ma_nhan_vien = hd.ma_nhan_vien
group by nv.ma_nhan_vien, nv.ho_ten, td.ten_trinh_do, bp.ten_bo_phan, 
         nv.so_dien_thoai, nv.dia_chi
having count(hd.ma_hop_dong) <= 3;


-- 16.Xóa những Nhân viên chưa từng lập được hợp đồng nào từ năm 2019 đến năm 2021.
SET SQL_SAFE_UPDATES = 0;
delete from nhan_vien
where ma_nhan_vien not in (
	select distinct ma_nhan_vien
    from hop_dong
    where year(ngay_lam_hop_dong) between 2019 and 2021
);
SET SQL_SAFE_UPDATES = 1;

-- 17.	Cập nhật thông tin những khách hàng có ten_loai_khach từ Platinum lên Diamond, chỉ cập nhật những khách hàng đã từng đặt phòng với Tổng Tiền thanh toán trong năm 2021 là lớn hơn 10.000.000 VNĐ.
update khach_hang kh
inner join loai_khach lk on kh.ma_loai_khach = lk.ma_loai_khach
set kh.ma_loai_khach = 1
where lk.ten_loai_khach = 'Platinum'
	and kh.ma_khach_hang in (
		select ma_khach_hang
        from (
			select 
              hd.ma_khach_hang,
              SUM(dv.chi_phi_thue + IFNULL(hdct.so_luong * dvdk.gia, 0)) AS tong_tien
            from hop_dong hd 
            inner join dich_vu dv on hd.ma_dich_vu = dv.ma_dich_vu
            left join hop_dong_chi_tiet hdct on hd.ma_hop_dong = hdct.ma_hop_dong
            left join dich_vu_di_kem dvdk on hdct.ma_dich_vu_di_kem = dvdk.ma_dich_vu_di_kem
            where year(ngay_lam_hop_dong) = 2021
            group by hd.ma_khach_hang 
            having tong_tien > 10000000
        ) as temp
    );
    
-- 18.Xóa những khách hàng có hợp đồng trước năm 2021 (chú ý ràng buộc giữa các bảng).
SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS = 0;
delete from khach_hang
where ma_khach_hang in (
	select distinct ma_khach_hang
    from hop_dong
    where year(ngay_lam_hop_dong) < 2021
);
SET FOREIGN_KEY_CHECKS = 1;
SET SQL_SAFE_UPDATES = 1;


-- 19.	Cập nhật giá cho các dịch vụ đi kèm được sử dụng trên 10 lần trong năm 2020 lên gấp đôi.
SET SQL_SAFE_UPDATES = 0;
UPDATE dich_vu_di_kem
SET gia = gia * 2
WHERE ma_dich_vu_di_kem IN (
    SELECT ma_dich_vu_di_kem
    FROM (
        SELECT hdct.ma_dich_vu_di_kem, SUM(hdct.so_luong) AS tong_su_dung
        FROM hop_dong_chi_tiet hdct
        INNER JOIN hop_dong hd ON hdct.ma_hop_dong = hd.ma_hop_dong
        WHERE YEAR(hd.ngay_lam_hop_dong) = 2020
        GROUP BY hdct.ma_dich_vu_di_kem
        HAVING tong_su_dung > 10
    ) AS temp
);
SET SQL_SAFE_UPDATES = 1;

-- 20. Hiển thị thông tin của tất cả các nhân viên và khách hàng có trong hệ thống, thông tin hiển thị bao gồm id (ma_nhan_vien, ma_khach_hang), ho_ten, email, so_dien_thoai, ngay_sinh, dia_chi.
SELECT ma_nhan_vien AS id, ho_ten, email, so_dien_thoai, ngay_sinh, dia_chi
FROM nhan_vien
UNION ALL
SELECT ma_khach_hang AS id, ho_ten, email, so_dien_thoai, ngay_sinh, dia_chi
FROM khach_hang;


-- 21. Tạo khung nhìn có tên là v_nhan_vien để lấy được thông tin của tất cả các nhân viên có địa chỉ là “Hải Châu” và đã từng lập hợp đồng cho một hoặc nhiều khách hàng bất kì với ngày lập hợp đồng là “12/12/2019”.
create view v_nhan_vien as
select *
from nhan_vien nv
inner join hop_dong hd on nv.ma_nhan_vien = hd.ma_nhan_vien
where nv.dia_chi like "%Hải Châu%" and date(hd.ngay_lam_hop_dong) = '2019-12-12';

-- 22.Thông qua khung nhìn v_nhan_vien thực hiện cập nhật địa chỉ thành “Liên Chiểu” đối với tất cả các nhân viên được nhìn thấy bởi khung nhìn này.
set sql_safe_updates = 0;
update nhan_vien
set dia_chi = 'Liên Chiểu';
set sql_safe_updates = 1;

-- 23.Tạo Stored Procedure sp_xoa_khach_hang dùng để xóa thông tin của một khách hàng nào đó với ma_khach_hang được truyền vào như là 1 tham số của sp_xoa_khach_hang.
drop procedure if exists sp_xoa_khach_hang;
DELIMITER //
create procedure sp_xoa_khach_hang(in p_ma_khach_hang int)
begin
	declare customer_exists int;
    
    select count(*) into customer_exists
    from khach_hang  
    where ma_khach_hang = p_ma_khach_hang;
    
    if customer_exists > 0 then
		delete from hop_dong_chi_tiet
		where ma_hop_dong in (
			select ma_hop_dong
			from hop_dong 
			where ma_khach_hang = p_ma_khach_hang 
		); 
    
		delete from hop_dong
		where ma_khach_hang = p_ma_khach_hang;
		
		delete from khach_hang 
        where ma_khach_hang = p_ma_khach_hang;
        
        select concat("Đã xóa khách hàng có mã: ",p_ma_khach_hang) as message;
	else
		select concat('Không tìm thấy khách hàng có mã: ', p_ma_khach_hang) AS message;
    END IF;
end //
DELIMITER ;

-- 24. Tạo Stored Procedure sp_them_moi_hop_dong dùng để thêm mới vào bảng hop_dong với yêu cầu sp_them_moi_hop_dong phải thực hiện kiểm tra tính hợp lệ của dữ liệu bổ sung, với nguyên tắc không được trùng khóa chính và đảm bảo toàn vẹn tham chiếu đến các bảng liên quan.
drop procedure if exists sp_them_moi_hop_dong;
DELIMITER //
create procedure sp_them_moi_hop_dong(
	IN p_ma_hop_dong INT,
    IN p_ngay_lam_hop_dong DATETIME,
    IN p_ngay_ket_thuc DATETIME,
    IN p_tien_dat_coc DOUBLE,
    IN p_ma_nhan_vien INT,
    IN p_ma_khach_hang INT,
    IN p_ma_dich_vu INT
)
begin
	declare hop_dong_exists int;
    declare nhan_vien_exists int;
    declare khach_hang_exists int;
	declare dich_vu_exists int;
    declare error_message varchar(255);
    
    select count(*) into nhan_vien_exists
    from nhan_vien
    where ma_nhan_vien = p_ma_nhan_vien;
    
    if nhan_vien_exists = 0 then
		set error_message = concat('Lỗi: Không tìm thấy nhân viên có mã: ', p_ma_nhan_vien);
		select error_message as message;
	else
		select count(*) into khach_hang_exists
        from khach_hang
        where ma_khach_hang = p_ma_khach_hang;
        
        if khach_hang_exists = 0 then
			set error_message = concat('Lỗi: Không tìm thấy khách hàng có mã: ', p_ma_khach_hang);
			select error_message as message;
		else
			select count(*) as dich_vu_exists
            from dich_vu
            where ma_dich_vu = p_ma_dich_vu;
            
            if dich_vu_exists = 0 then
				set error_message = concat('Lỗi: Không tìm thấy dịch vụ có mã: ', p_ma_dich_vu);
				select error_message as message;
                
			else
				if p_ngay_ket_thuc < p_ngay_lam_hop_dong then
					select 'Ngày làm hợp đồng phải sớm hơn ngày kết thúc!' as message;
				else
					if p_tien_dat_coc < 0 then
						select 'Tiền đặt cọc phải lớn hơn hoặc bằng 0!' as message;
					else
						insert into hop_dong (
							ma_hop_dong,
							ngay_lam_hop_dong,
							ngay_ket_thuc,
							tien_dat_coc,
							ma_nhan_vien,
							ma_khach_hang,
							ma_dich_vu
                        )
                        values (
							p_ma_hop_dong,
							p_ngay_lam_hop_dong,
							p_ngay_ket_thuc,
							p_tien_dat_coc,
							p_ma_nhan_vien,
							p_ma_khach_hang,
							p_ma_dich_vu
                        );
                        select concat('Thêm hợp đồng mã ', p_ma_hop_dong, ' thành công') as message;
				end if;
			end if;
        end if;
	end if;
end if;
end //
DELIMITER ;

-- 27. a.Tạo Function func_dem_dich_vu: Đếm các dịch vụ đã được sử dụng với tổng tiền là > 2.000.000 VNĐ.
drop function if exists func_dem_dich_vu;
DELIMITER //
create function func_dem_dich_vu()
returns int
deterministic
reads sql data
begin
	declare so_luong_dich_vu int;
    
    select count(distinct dv.ma_dich_vu) into so_luong_dich_vu
    from dich_vu dv
    inner join hop_dong hd on dv.ma_dich_vu = hd.ma_dich_vu
    left join hop_dong_chi_tiet hdct on hdct.ma_hop_dong = hd.ma_hop_dong
    left join dich_vu_di_kem dvdk on hdct.ma_dich_vu_di_kem = dvdk.ma_dich_vu_di_kem
    group by dv.ma_dich_vu
    having sum(dv.chi_phi_thue + IFNULL(hdct.so_luong * dvdk.gia, 0)) > 20000000;
    return ifnull((so_luong_dich_vu), 0);
end //
DELIMITER ;

-- b.	Tạo Function func_tinh_thoi_gian_hop_dong: Tính khoảng thời gian dài nhất tính từ lúc bắt đầu làm hợp đồng đến lúc kết thúc hợp đồng mà khách hàng đã thực hiện thuê dịch vụ (lưu ý chỉ xét các khoảng thời gian dựa vào từng lần làm hợp đồng thuê dịch vụ, không xét trên toàn bộ các lần làm hợp đồng). Mã của khách hàng được truyền vào như là 1 tham số của function này.
drop function if exists func_tinh_thoi_gian_hop_dong;
DELIMITER //
create function func_tinh_thoi_gian_hop_dong(f_ma_khach_hang int)
returns int
deterministic
reads sql data
begin
	declare thoi_gian_dai_nhat int default 0;
    
   select max(datediff(ngay_ket_thuc, ngay_lam_hop_dong)) into thoi_gian_dai_nhat
   from hop_dong
   where ma_khach_hang = f_ma_khach_hang;
   
   return ifnull((thoi_gian_dai_nhat), 0);
end //
DELIMITER ;

-- 28. Tạo Stored Procedure sp_xoa_dich_vu_va_hd_room để tìm các dịch vụ được thuê bởi khách hàng với loại dịch vụ là “Room” từ đầu năm 2015 đến hết năm 2019 để xóa thông tin của các dịch vụ đó (tức là xóa các bảng ghi trong bảng dich_vu) và xóa những hop_dong sử dụng dịch vụ liên quan (tức là phải xóa những bản gi trong bảng hop_dong) và những bản liên quan khác.
drop procedure if exists sp_xoa_dich_vu_va_hd_room;
DELIMITER //
create procedure sp_xoa_dich_vu_va_hd_room()
begin
	declare so_hdct_xoa int default 0;
    declare so_dv_xoa int default 0;
    declare so_hd_xoa int default 0;
    
    delete from hop_dong_chi_tiet
    where ma_hop_dong in (
		select hd.ma_hop_dong
        from hop_dong hd
        inner join dich_vu dv on hd.ma_dich_vu = dv.ma_dich_vu
        inner join loai_dich_vu ldv on dv.ma_loai_dich_vu = ldv.ma_loai_dich_vu
        where ldv.ten_loai_dich_vu = "Room"
        and year(hd.ngay_lam_hop_dong) between 2015 and 2019
    );
    
    set so_hdct_xoa = row_count();
    
    delete from hop_dong 
    where ma_hop_dong in (
		select hd.ma_hop_dong
        from hop_dong hd 
        inner join dich_vu dv on hd.ma_dich_vu = dv.ma_dich_vu
        inner join loai_dich_vu ldv on dv.ma_loai_dich_vu = ldv.ma_loai_dich_vu
        where ldv.ten_loai_dich_vu = "Room"
        and year(hd.ngay_lam_hop_dong) between 2015 and 2019
    );
    set so_hd_xoa = row_count();
    
    delete from dich_vu
    where ma_dich_vu in (
		select temp.ma_dich_vu
        from (
			select distinct dv.ma_dich_vu
            from dich_vu dv
            inner join loai_dich_vu ldv on dv.ma_loai_dich_vu = ldv.ma_loai_dich_vu
			where ldv.ten_loai_dich_vu = "Room"
			and exists(
				select 1
                from hop_dong hd_check
                where hd_check.ma_dich_vu = dv.ma_dich_vu
                and year(hd_check.ngay_lam_hop_dong) between 2015 and 2019
            ) 
        ) as temp
    );
    
    set so_dv_xoa = row_count();
    
    select so_hdct_xoa as so_hop_dong_chi_tiet_da_xoa;
	select so_dv_xoa as so_dich_vu_da_xoa;
	select so_hd_xoa as so_hop_dong_da_xoa;
end //
DELIMITER ;

-- 25.Tạo Trigger có tên tr_xoa_hop_dong khi xóa bản ghi trong bảng hop_dong thì hiển thị tổng số lượng bản ghi còn lại có trong bảng hop_dong ra giao diện console của database.
drop table if exists history_hop_dong;
create table history_hop_dong (
    id int primary key auto_increment,
    ma_hop_dong_xoa int,
    ngay_xoa datetime default current_timestamp,
    thong_bao varchar(255)
);

drop trigger if exists tr_xoa_hop_dong;
DELIMITER //
create trigger tr_xoa_hop_dong
after delete on hop_dong
for each row
begin
	declare so_ban_ghi_con_lai int;
    
    select count(*) into so_ban_ghi_con_lai
    from hop_dong;
    
    insert into history_hop_dong(ma_hop_dong_xoa, so_ban_ghi_con_lai, thong_bao)
    values (old.ma_hop_dong, so_ban_ghi_con_lai, concat('Đã xóa hợp đồng mã: ', old.ma_hop_dong, 
               '. Số bản ghi còn lại: ', so_ban_ghi_con_lai));
    
end //
DELIMITER ;



