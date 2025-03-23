package org.hrd.spring_homework002.repository;

import org.apache.ibatis.annotations.*;
import org.hrd.spring_homework002.model.Course;
import org.hrd.spring_homework002.model.Student;

import java.util.List;

@Mapper
public interface StudentRepository {
    @Select("""
        SELECT * FROM students
        WHERE student_id = #{id};
    """)
    @Results(id = "studentMapper", value = {
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "studentName", column = "student_name"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "courses", column = "student_id",
                many = @Many(select = "getAllCoursesByStudentId")
            )
    })
    Student findStudentById(Integer id);

    @Select("""
        SELECT c.course_name FROM courses c
        INNER JOIN student_course sc
        ON c.course_id = sc.course_id
        WHERE student_id = #{studentId}
    """)
    @Result(property = "courseId", column = "course_id")
    @Result(property = "courseName", column = "course_name")
    @Result(property = "instructor", column = "instructor_id",
            one = @One(select = "org.hrd.spring_homework002.repository.InstructorRepository.findInstructorById")
    )
    List<Course> getAllCoursesByStudentId(Integer studentId);
}
