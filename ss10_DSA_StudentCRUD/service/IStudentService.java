package ss10_DSA_StudentCRUD.service;

import ss10_DSA_StudentCRUD.entity.Student;

import java.util.List;

public interface IStudentService {

    List<Student> findAll();

    void save(Student student);

    void update(Student student);

    void delete(int id);

    Student findById(int id);

    List<Student> searchByName(String keyword);

    boolean isStudentExist(int id);
}
