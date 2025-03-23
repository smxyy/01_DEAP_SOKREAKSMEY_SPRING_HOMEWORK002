package org.hrd.spring_homework002.service;

import org.hrd.spring_homework002.model.Course;
import org.hrd.spring_homework002.model.request.CourseRequest;

import java.util.List;

public interface CourseService {
    Course getCourseById(Integer id);
    Course updateCourseById(Integer id, CourseRequest courseRequest);
    void removeCourseById(Integer id);
    List<Course> getAllCoursesByPagination(Integer offset, Integer limit);
    Course addNewCourse(CourseRequest courseRequest);
}
