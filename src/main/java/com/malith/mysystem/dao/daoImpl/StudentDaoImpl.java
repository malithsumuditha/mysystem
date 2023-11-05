package com.malith.mysystem.dao.daoImpl;

import com.malith.mysystem.dao.StudentDao;
import com.malith.mysystem.dto.response.StudentResponseDto;
import com.malith.mysystem.entity.Student;
import com.malith.mysystem.repo.StudentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentDaoImpl implements StudentDao {
    private final StudentRepository studentRepository;

    public StudentDaoImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<Student> findStudentByID(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public boolean existsStudentByName(String name) {
        return studentRepository.existsStudentByName(name);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void studentDeleteById(Long id) {
        studentRepository.deleteById(id);
    }
}
