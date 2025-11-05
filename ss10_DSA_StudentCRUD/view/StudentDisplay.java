package ss10_DSA_StudentCRUD.view;

import ss10_DSA_StudentCRUD.entity.Student;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class StudentDisplay {
    public static void showStudentList(List<Student> students){
        if (students.isEmpty()) {
            System.out.println("Danh sách trống!");
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("\n===== DANH SÁCH SINH VIÊN =====");
        System.out.printf("%-5s | %-25s | %-15s | %-5s%n", "ID", "Tên", "Ngày sinh", "Điểm");
        System.out.println("---------------------------------------------------------------");

        for (Student s : students) {
            System.out.printf("%-5d | %-25s | %-15s | %-5.2f%n",
                    s.getId(),
                    s.getName(),
                    s.getDob().format(formatter),
                    s.getScore());
        }
        System.out.println("---------------------------------------------------------------");
    }

    public static void showStudentDetail(Student s) {
        System.out.println("=== Thông tin sinh viên ===");
        System.out.println("Mã: " + s.getId());
        System.out.println("Tên: " + s.getName());
        System.out.println("Ngày sinh: " + s.getDob());
        System.out.println("Điểm trung bình: " + s.getScore());
        System.out.println("=============================");
    }
}
