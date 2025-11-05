package ss10_DSA_StudentCRUD.controller;

import ss10_DSA_StudentCRUD.entity.Student;
import ss10_DSA_StudentCRUD.service.IStudentService;
import ss10_DSA_StudentCRUD.service.StudentService;

import java.util.List;

public class StudentController {

    private final IStudentService studentService = new StudentService();

    public List<Student> findAll() {
        return studentService.findAll();
    }

    public void save(Student student){
        studentService.save(student);
    }

    public void delete(int id){
        studentService.delete(id);
    }

    public void update(Student student){
        studentService.update(student);
    }

    public Student findById(int id){
        return studentService.findById(id);
    }

    public List<Student> searchByName(String name){
        return studentService.searchByName(name);
    }

    public boolean isStudentExist(int id) {
        return studentService.isStudentExist(id);
    }
}
