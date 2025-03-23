package org.hrd.spring_homework002.service;

import org.hrd.spring_homework002.model.Instructor;
import org.hrd.spring_homework002.model.request.InstructorRequest;

import java.util.List;

public interface InstructorService {
    List<Instructor> getAllInstructors();
    List<Instructor> getAllInstructorsByPagination(Integer offset, Integer limit);
    Instructor getInstructorById(Integer id);
    Instructor createInstructor(InstructorRequest instructorRequest);
    Instructor updateInstructorInfoById(Integer id, InstructorRequest instructorRequest);
    void removeInstructorById(Integer id);
}
