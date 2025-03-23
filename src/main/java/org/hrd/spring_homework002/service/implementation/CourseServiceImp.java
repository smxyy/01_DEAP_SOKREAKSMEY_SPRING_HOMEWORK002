package org.hrd.spring_homework002.service.implementation;

import org.hrd.spring_homework002.exception.InstructorNotFoundException;
import org.hrd.spring_homework002.exception.InvalidCourseIdException;
import org.hrd.spring_homework002.model.Course;
import org.hrd.spring_homework002.model.request.CourseRequest;
import org.hrd.spring_homework002.repository.CourseRepository;
import org.hrd.spring_homework002.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImp implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImp(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @Override
    public Course getCourseById(Integer id) {
        if (id <= 0) throw new InvalidCourseIdException("Course ID must be greater than 0!");
        Course course = courseRepository.findCourseById(id);
        if (course == null) throw new InstructorNotFoundException("Course with ID " + id + " not found!");
        return course;
    }

    @Override
    public Course updateCourseById(Integer id, CourseRequest courseRequest) {
        if (id <= 0) throw new InvalidCourseIdException("Course ID must be greater than 0!");
        Course course = courseRepository.updateCourseById(id, courseRequest);
        if (course == null) throw new InstructorNotFoundException("Course with ID " + id + " not found!");
        return course;
    }

    @Override
    public void removeCourseById(Integer id){
        if (id <= 0) throw new InvalidCourseIdException("Course ID must be greater than 0!");
        Boolean result = courseRepository.deleteCourseById(id);
        if (result == false) throw new InstructorNotFoundException("Course with ID " + id + " not found!");
    }

    @Override
    public List<Course> getAllCoursesByPagination(Integer offset, Integer limit){
        var courses = courseRepository.findAllCoursesByPagination(offset, limit);
        return  courses;
    }

    @Override
    public Course addNewCourse(CourseRequest courseRequest){
        return courseRepository.insertNewCourse(courseRequest);
    }
}
