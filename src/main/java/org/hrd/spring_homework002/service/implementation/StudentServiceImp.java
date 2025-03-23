package org.hrd.spring_homework002.service.implementation;

import org.hrd.spring_homework002.exception.InstructorNotFoundException;
import org.hrd.spring_homework002.model.Student;
import org.hrd.spring_homework002.repository.StudentRepository;
import org.hrd.spring_homework002.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImp implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImp(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public Student getStudentById(Integer id){
        Student student = studentRepository.findStudentById(id);
        if (student == null) throw new InstructorNotFoundException("Student with ID " + id + " not found!");
        return  student;
    }
}
