package ss10_DSA_StudentCRUD.repository;

import ss10_DSA_StudentCRUD.entity.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    private static List<Student> students = new ArrayList<>();

    static {
        students.add(new Student(1, "Gnourt", LocalDate.of(2002, 1, 10), 9.8));
        students.add(new Student(2, "Trường", LocalDate.of(2001, 3, 15), 9.8));
        students.add(new Student(3, "Trực thăng di động", LocalDate.of(2001, 3, 15), 9.8));
        students.add(new Student(4, "gnourt._", LocalDate.of(2001, 3, 15), 9.8));
    }

    public List<Student> findAll() {
        return students;
    }

    public void save(Student student){
        students.add(student);
    }

    public void update(Student updatedStudent){
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getId().equals(updatedStudent.getId())){
                students.set(i, updatedStudent);
                return;
            }
        }
    }

    public void delete(int id){
        students.removeIf(s -> s.getId() == id);
    }

    public Student findById(int id){
        for (Student s : students){
            if(s.getId() == id){
                return s;
            }
        }
        return null;
    }

    public List<Student> searchByName(String name){
        List<Student> listResult = new ArrayList<>();
        for (Student s : students){
            if(s.getName().toLowerCase().contains(name.toLowerCase())){
                listResult.add(s);
            }
        }
        return listResult;
    }

    public boolean isStudentExist(int id) {
        return students.stream().anyMatch(s -> s.getId() == id);
    }
}
