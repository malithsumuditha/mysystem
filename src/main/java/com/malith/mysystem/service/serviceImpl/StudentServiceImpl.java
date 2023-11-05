package com.malith.mysystem.service.serviceImpl;

import com.malith.mysystem.dao.StudentDao;
import com.malith.mysystem.dto.request.StudentRequestDto;
import com.malith.mysystem.dto.response.StudentResponseDto;
import com.malith.mysystem.entity.Student;
import com.malith.mysystem.exception.DuplicateResourceException;
import com.malith.mysystem.exception.RequestValidationException;
import com.malith.mysystem.exception.ResourceNotFoundException;
import com.malith.mysystem.repo.StudentRepository;
import com.malith.mysystem.service.StudentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final ModelMapper modelMapper;
    private final StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(ModelMapper modelMapper, StudentDao studentDao) {
        this.modelMapper = modelMapper;
        this.studentDao = studentDao;
    }


    @Override
    public StudentResponseDto getStudentById(Long id) {
        Optional<Student> student = studentDao.findStudentByID(id);
        if (student.isEmpty()){
            throw new ResourceNotFoundException("Student with id %s not found".formatted(id));
        }
        return modelMapper.map(student, StudentResponseDto.class);
    }

    @Override
    public StudentResponseDto saveStudent(StudentRequestDto studentRequestDto) {

        if (studentDao.existsStudentByName(studentRequestDto.getName())){
            throw new DuplicateResourceException("Student Name %s Already Exist".formatted(studentRequestDto.getName()));
        }else {
            Student student = modelMapper.map(studentRequestDto, Student.class);
            Student savedStudent = studentDao.save(student);
            return modelMapper.map(savedStudent,StudentResponseDto.class);
        }
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        List<Student> students = studentDao.findAllStudents();
        return modelMapper.map(students,new TypeToken<List<StudentResponseDto>>(){}.getType());
    }

    @Override
    public boolean existStudentByName(String name) {
        return studentDao.existsStudentByName(name);
    }

    @Override
    public String deleteStudentById(Long id) {
        StudentResponseDto studentById = getStudentById(id);
        if (studentById==null){
            throw new ResourceNotFoundException("User id %s not found".formatted(id));
        }else {
            studentDao.studentDeleteById(id);
            return "Success";
        }
    }

    @Override
    public StudentResponseDto updateStudent(Long id, StudentRequestDto studentRequestDto) {
        StudentResponseDto student = getStudentById(id);

        boolean changes = false;

        if (studentRequestDto.getName()!=null && !student.getName().equals(studentRequestDto.getName())){
            if (existStudentByName(studentRequestDto.getName())){
                throw new DuplicateResourceException(
                        "Student Name %s Already Exist".formatted(studentRequestDto.getName())
                );
            }
            student.setName(studentRequestDto.getName());
            changes = true;
        }

        if (studentRequestDto.getAddress()!=null && !studentRequestDto.getAddress().equals(student.getAddress())){
            student.setAddress(studentRequestDto.getAddress());
            changes = true;
        }

        if (studentRequestDto.getAge()!=0 && studentRequestDto.getAge()!=student.getAge()){
            student.setAge(studentRequestDto.getAge());
            changes = true;
        }

        if (!changes){
            throw new RequestValidationException("No data change found");
        }
        Student updatedStudent = studentDao.save(modelMapper.map(student, Student.class));
        return modelMapper.map(updatedStudent, StudentResponseDto.class);

    }
}
