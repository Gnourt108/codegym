DROP DATABASE IF EXISTS quan_ly_sinh_vien;
CREATE DATABASE quan_ly_sinh_vien;
USE quan_ly_sinh_vien;

CREATE TABLE class (
    class_id INT NOT NULL AUTO_INCREMENT,
    class_name VARCHAR(60) NOT NULL,
    start_date DATETIME NOT NULL,
    status BIT,
    PRIMARY KEY (class_id)
);

CREATE TABLE student (
    student_id INT NOT NULL AUTO_INCREMENT,
    student_name VARCHAR(30) NOT NULL,
    address VARCHAR(50),
    phone VARCHAR(20),
    status BIT,
    class_id INT NOT NULL,
    PRIMARY KEY (student_id),
    FOREIGN KEY (class_id) REFERENCES class(class_id)
);

CREATE TABLE subject (
    sub_id INT NOT NULL AUTO_INCREMENT,
    sub_name VARCHAR(30) NOT NULL,
    credit TINYINT NOT NULL DEFAULT 1,
    status BIT DEFAULT 1,
    PRIMARY KEY (sub_id),
    CONSTRAINT chk_credit CHECK (credit >= 1)
);

CREATE TABLE mark (
    mark_id INT NOT NULL AUTO_INCREMENT,
    sub_id INT NOT NULL,
    student_id INT NOT NULL,
    mark FLOAT DEFAULT 0,
    exam_times TINYINT DEFAULT 1,
    PRIMARY KEY (mark_id),
    UNIQUE KEY uk_sub_student (sub_id, student_id),
    FOREIGN KEY (sub_id) REFERENCES subject(sub_id),
    FOREIGN KEY (student_id) REFERENCES student(student_id),
    CONSTRAINT chk_mark CHECK (mark BETWEEN 0 AND 100)
);

-- Insert dữ liệu vào bảng class
INSERT INTO class (class_name, start_date, status) VALUES
('Lớp A1', '2023-09-01 08:00:00', 1),
('Lớp A2', '2023-12-01 08:00:00', 1),
('Lớp B1', '2023-12-15 08:00:00', 1),
('Lớp B2', '2023-11-15 08:00:00', 0),
('Lớp C1', '2023-10-01 08:00:00', 1);

-- Insert dữ liệu vào bảng student
INSERT INTO student (student_name, address, phone, status, class_id) VALUES
('Hoàng Văn An', 'Hà Nội', '0912345678', 1, 1),
('Trần Thị Bích', 'Đà Nẵng', '0923456789', 1, 1),
('Hà Minh Châu', 'Hải Phòng', '0934567890', 1, 2),
('Nguyễn Văn Hùng', 'Hà Nội', '0945678901', 1, 3),
('Lê Thị Hoa', 'Huế', '0956789012', 1, 2),
('Phạm Văn Hung', 'Đà Nẵng', '0967890123', 1, 1),
('Hoàng Thị Lan', 'TP.HCM', '0978901234', 1, 3),
('Vũ Văn Nam', 'Hà Nội', '0989012345', 0, 4),
('Đỗ Thị Hương', 'Đà Nẵng', '0990123456', 1, 5),
('Bùi Văn Tài', 'Hải Phòng', '0901234567', 1, 5);

-- Insert dữ liệu vào bảng subject
INSERT INTO subject (sub_name, credit, status) VALUES
('Toán', 3, 1),
('Lý', 4, 1),
('Hóa', 3, 1),
('Văn', 5, 1),
('Anh', 4, 1),
('Tin học', 2, 1),
('Thể dục', 1, 1),
('Lịch sử', 3, 1);

-- Insert dữ liệu vào bảng mark
INSERT INTO mark (sub_id, student_id, mark, exam_times) VALUES
(1, 1, 85.5, 1),
(2, 1, 75.0, 1),
(3, 1, 90.0, 1),
(1, 2, 75.0, 1),
(2, 2, 80.5, 1),
(4, 2, 85.5, 1),
(1, 3, 92.0, 1),
(3, 3, 88.0, 1),
(5, 3, 75.0, 1),
(1, 4, 70.0, 2),
(2, 4, 65.5, 1),
(4, 4, 85.5, 1),
(1, 5, 88.0, 1),
(3, 5, 92.0, 1),
(5, 5, 78.5, 1),
(2, 6, 85.5, 1),
(4, 6, 90.0, 1),
(6, 6, 75.0, 1),
(1, 7, 95.0, 1),
(3, 7, 85.5, 1),
(5, 7, 92.0, 1);

-- Câu 1: Hiển thị thông tin sinh viên 
select *
from student
where student_name like 'H%' or student_name like 'h%';

-- Câu 2: Hiển thị các thông tin lớp học có thời gian bắt đầu vào tháng 12
select *
from class 
where month(start_date) = 12;

-- Câu 3 : Hiển thị tất cả các thông tin môn học có credit trong khoảng từ 3-5 
select * 
from `subject`
where credit between 3 and 5;

-- Câu 4: Thay đổi mã lớp(ClassID) của sinh viên có tên ‘Hung’ là 2
set sql_safe_updates = 0;
update student
set class_id = 2
where student_name like '%Hung%';
set sql_safe_updates = 1;

-- Câu 5: Hiển thị các thông tin: StudentName, SubName, Mark. Dữ liệu sắp xếp theo điểm thi (mark) giảm dần. nếu trùng sắp theo tên tăng dần.
select 
	s.student_name AS StudentName,
	sub.sub_name AS SubName,
	m.mark AS Mark
from mark m
inner join student s on m.student_id = s.student_id
inner join `subject` sub on m.sub_id = sub.sub_id 
order by m.mark DESC, s.student_name ASC;

