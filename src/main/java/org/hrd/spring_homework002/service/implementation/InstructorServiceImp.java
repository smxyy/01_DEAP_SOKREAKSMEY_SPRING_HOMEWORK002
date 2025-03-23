package org.hrd.spring_homework002.service.implementation;

import org.hrd.spring_homework002.exception.InstructorNotFoundException;
import org.hrd.spring_homework002.model.Instructor;
import org.hrd.spring_homework002.model.request.InstructorRequest;
import org.hrd.spring_homework002.repository.InstructorRepository;
import org.hrd.spring_homework002.service.InstructorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImp implements InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorServiceImp(InstructorRepository instructorRepository){
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> getAllInstructors(){
        var instructors = instructorRepository.findAllInstructors();
        return instructors;
    }

    @Override
    public List<Instructor> getAllInstructorsByPagination(Integer page, Integer size ){
        return instructorRepository.findAllInstructorsByPagination(page, size);
    }

    @Override
    public Instructor getInstructorById(Integer id){
        var instructor = instructorRepository.findInstructorById(id);
        if (instructor == null) throw new InstructorNotFoundException("Instructor with ID " + id + " not found!");
        return instructor;
    }

    @Override
    public Instructor createInstructor(InstructorRequest instructorRequest){
       var instructor = instructorRepository.insertNewInstructor(instructorRequest);
       return instructor;
    }

    @Override
    public Instructor updateInstructorInfoById(Integer id, InstructorRequest instructorRequest){
        var instructor = instructorRepository.updateInstructorInfoById(id, instructorRequest);
        if (instructor == null) throw new InstructorNotFoundException("Instructor with ID " + id + " not found!");
        return instructor;
    }

    @Override
    public void removeInstructorById(Integer id){
        var result = instructorRepository.deleteInstructorById(id);
        if (result == false) throw new InstructorNotFoundException("Instructor with ID " + id + " not found!");
    }
}
