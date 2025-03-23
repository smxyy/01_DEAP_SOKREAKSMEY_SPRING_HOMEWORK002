package org.hrd.spring_homework002.service;

import org.hrd.spring_homework002.model.Student;
import org.hrd.spring_homework002.model.request.StudentRequest;

import java.util.List;

public interface StudentService {
    Student getStudentById(Integer id);
    List<Student> getAllStudents(Integer offset, Integer limit);
    Student addNewStudent(StudentRequest studentRequest);
    Student updateStudentById(Integer id, StudentRequest studentRequest);
    void removeStudentById(Integer id);
}
