package ss10_DSA_StudentCRUD.view;

import ss10_DSA_StudentCRUD.common.CheckInput;
import ss10_DSA_StudentCRUD.common.FormatterUtil;
import ss10_DSA_StudentCRUD.controller.StudentController;
import ss10_DSA_StudentCRUD.entity.Student;

import java.security.spec.RSAOtherPrimeInfo;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class StudentView {
    private final StudentController studentController = new StudentController();
    private final Scanner sc = new Scanner(System.in);

    public void view() {
        while (true) {
            System.out.println("\n===== QUẢN LÝ SINH VIÊN =====");
            System.out.println("1. Hiển thị danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Xóa");
            System.out.println("4. Sửa");
            System.out.println("5. Tìm kiếm theo tên");
            System.out.println("0. Quay lại");
            int choice = CheckInput.inputInt("Nhập lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    StudentDisplay.showStudentList(studentController.findAll());
                    break;
                case 2:
                    addStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    searchStudent();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void searchStudent() {
        String keyword = CheckInput.inputStr("Nhập tên cần tìm: ");
        List<Student> results = studentController.searchByName(keyword);
        if (results.isEmpty()) {
            System.out.println("Không tìm thấy sinh viên nào!");
        } else {
            StudentDisplay.showStudentList(results);
        }
    }

    private void updateStudent() {
        int id = CheckInput.inputInt("Nhập mã sinh viên muốn chỉnh sửa: ");
        if(!studentController.isStudentExist(id)){
            System.out.println("Không tồn tại sinh viên có mã: "+id);
            return;
        }
        Student oldStudent = studentController.findById(id);
        StudentDisplay.showStudentDetail(oldStudent);

        System.out.println("Nhập thông tin mới (bỏ trống nếu không muốn thay đổi):");

        String newName = CheckInput.inputStringAllowEmpty("Nhập tên mới: ");
        String newDOB = CheckInput.inputStringAllowEmpty("Nhập ngày sinh mới (dd/MM/yyyy): ");
        String newScore = CheckInput.inputStringAllowEmpty("Điểm trung bình mới: ");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate finalDOB = oldStudent.getDob();
        if (!newDOB.isEmpty()) {
            try {
                finalDOB = LocalDate.parse(newDOB, formatter);
            } catch (DateTimeException e) {
                System.out.println("Định dạng ngày tháng không hợp lệ! Giữ nguyên giá trị cũ.");
            }
        }

        String finalName = newName.isEmpty() ? oldStudent.getName() : newName;
        double finalScore = newScore.isEmpty() ? oldStudent.getScore() : Double.parseDouble(newScore);

        Student updatedStudent = new Student(id, finalName, finalDOB, finalScore);

        if(CheckInput.confirmAction("Bạn có muốn có muốn cập nhật sinh viên này?")){
            studentController.update(updatedStudent);
            System.out.println("Cập nhật thành công");
        }else{
            System.out.println("Đã hủy thao tác cập nhật");
        }
    }

    private void deleteStudent() {
        int id = CheckInput.inputInt("Nhập mã sinh viên muốn xóa: ");
        Student student = studentController.findById(id);
        if(!studentController.isStudentExist(id)){
            System.out.println("Không tồn tại sinh viên có mã: "+id);
            return;
        }
        StudentDisplay.showStudentDetail(studentController.findById(id));
        if(CheckInput.confirmAction("Bạn có muốn xóa sinh viên này?")){
            studentController.delete(id);
            System.out.println("Xóa thành công");
        }else{
            System.out.println("Đã hủy thao tác xóa");
        }
    }

    private void addStudent() {
        int id = CheckInput.inputInt("Nhập ID: ");
        if (studentController.isStudentExist(id)) {
            System.out.println("Mã sinh viên đã tồn tại! Không thể thêm.");
            return;
        }

        String name = CheckInput.inputStr("Nhập tên: ");
        LocalDate dob = CheckInput.inputDate("Nhập ngày sinh");
        double score = CheckInput.inputDouble("Nhập điểm: ");

        if (CheckInput.confirmAction("Xác nhận thêm sinh viên này?")) {
            studentController.save(new Student(id, name, dob, score));
            System.out.println("Thêm thành công!");
        } else {
            System.out.println("Đã hủy thao tác thêm");
        }
    }

}
