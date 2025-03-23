package org.hrd.spring_homework002.service.implementation;

import org.hrd.spring_homework002.exception.InstructorNotFoundException;
import org.hrd.spring_homework002.exception.InvalidCourseIdException;
import org.hrd.spring_homework002.model.Student;
import org.hrd.spring_homework002.model.request.StudentRequest;
import org.hrd.spring_homework002.repository.StudentRepository;
import org.hrd.spring_homework002.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImp(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public Student getStudentById(Integer id){
        if (id <= 0) throw new InvalidCourseIdException("Student ID must be greater than 0!");
        Student student = studentRepository.findStudentById(id);
        if (student == null) throw new InstructorNotFoundException("Student with ID " + id + " not found!");
        return student;
    }

    @Override
    public List<Student> getAllStudents(Integer offset, Integer limit) {
        var students = studentRepository.findAllStudents(offset, limit);
        return students;
    }

    @Override
    public Student addNewStudent(StudentRequest studentRequest) {
        for (Integer courseId : studentRequest.getCoursesId()) {
            if (courseId <= 0) throw new InvalidCourseIdException("Course ID must be greater than 0!");
        }

        Integer studentId  = studentRepository.insertNewStudent(studentRequest);
        for(Integer courseId : studentRequest.getCoursesId()){
            studentRepository.insertNewStudentCourse(studentId, courseId);
        };
        return studentRepository.findStudentById(studentId);
    }

    @Override
    public Student updateStudentById(Integer id, StudentRequest studentRequest) {
        if (id <= 0) throw new InvalidCourseIdException("Student ID must be greater than 0!");
        Integer studentId = studentRepository.updateStudentById(id, studentRequest);
        studentRepository.deleteStudentCourses(studentId);
        for(Integer courseId : studentRequest.getCoursesId()){
            studentRepository.insertNewStudentCourse(studentId, courseId);
        };
        return studentRepository.findStudentById(studentId);
    }

    @Override
    public void removeStudentById(Integer id) {
        if (id <= 0) throw new InvalidCourseIdException("Student ID must be greater than 0!");
        Boolean result = studentRepository.deleteStudentById(id);
        studentRepository.deleteStudentCourses(id);
        if (result == false) throw new InstructorNotFoundException("Student with ID " + id + " not found!");
    }
}
