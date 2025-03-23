package org.hrd.spring_homework002.repository;

import org.apache.ibatis.annotations.*;
import org.hrd.spring_homework002.model.Course;
import org.hrd.spring_homework002.model.request.CourseRequest;

import java.util.List;

@Mapper
public interface CourseRepository {
    @Select("""
        SELECT * FROM courses
        WHERE course_id = #{id}
    """)
    @Results(id = "courseMapper", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "instructor", column = "instructor_id",
                    one = @One(select = "org.hrd.spring_homework002.repository.InstructorRepository.findInstructorById")
            )
    })
    Course findCourseById(Integer id);

    @Select("""
        UPDATE courses
        SET course_name = #{course.courseName}, 
            description = #{course.description},
            instructor_id = #{course.instructorId}
        WHERE course_id = #{id} 
        RETURNING *;
    """)
    @ResultMap("courseMapper")
    Course updateCourseById(Integer id, @Param("course") CourseRequest courseRequest);

    @Delete("""
        DELETE FROM courses
        WHERE course_id = #{id}
    """)
    Boolean deleteCourseById(Integer id);

    @Select("""
        SELECT * FROM courses
        OFFSET #{size} * (#{page} -1)
        LIMIT #{size};
    """)
    @ResultMap("courseMapper")
    List<Course> findAllCoursesByPagination(Integer page, Integer size);

    @Select("""
        INSERT INTO courses(course_name, description, instructor_id)
        VALUES (#{course.courseName}, #{course.description}, #{course.instructorId})
        RETURNING *;
    """)
    @ResultMap("courseMapper")
    Course insertNewCourse(@Param("course") CourseRequest courseRequest);
}
