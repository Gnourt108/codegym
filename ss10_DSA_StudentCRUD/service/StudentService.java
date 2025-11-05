package ss10_DSA_StudentCRUD.service;

import ss10_DSA_StudentCRUD.entity.Student;
import ss10_DSA_StudentCRUD.repository.StudentRepository;

import java.util.List;

public class StudentService implements IStudentService {
    private final StudentRepository studentRepository = new StudentRepository();
    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void update(Student student) {
        studentRepository.update(student);
    }

    @Override
    public void delete(int id) {
        studentRepository.delete(id);
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> searchByName(String keyword) {
        return studentRepository.searchByName(keyword);
    }

    @Override
    public boolean isStudentExist(int id) {
        return studentRepository.isStudentExist(id);
    }

}
