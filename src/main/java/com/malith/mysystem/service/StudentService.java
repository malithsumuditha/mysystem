package com.malith.mysystem.service;

import com.malith.mysystem.dto.request.StudentRequestDto;
import com.malith.mysystem.dto.response.StudentResponseDto;

import java.util.List;

public interface StudentService {
    public StudentResponseDto getStudentById(Long id);

    StudentResponseDto saveStudent(StudentRequestDto studentRequestDto);

    List<StudentResponseDto> getAllStudents();

    boolean existStudentByName(String name);

    String deleteStudentById(Long id);

    StudentResponseDto updateStudent(Long id, StudentRequestDto studentRequestDto);
}
