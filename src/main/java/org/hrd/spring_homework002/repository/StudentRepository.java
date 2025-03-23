package org.hrd.spring_homework002.repository;

import org.apache.ibatis.annotations.*;
import org.hrd.spring_homework002.model.Course;
import org.hrd.spring_homework002.model.Student;
import org.hrd.spring_homework002.model.request.StudentRequest;

import java.util.List;

@Mapper
public interface StudentRepository {
    @Select("""
        SELECT * FROM students
        OFFSET #{size} * (#{page} - 1)
        LIMIT #{size};
    """)
    @Results(id = "StudentMapper", value = {
        @Result(property = "studentId", column = "student_id"),
        @Result(property = "studentName", column = "student_name"),
        @Result(property = "phoneNumber", column = "phone_number"),
        @Result(property = "courses", column = "student_id",
            many = @Many(select = "findAllCoursesByStudentId")
        )
    })
    List<Student> findAllStudents(Integer page, Integer size);


    @Results(id = "CourseMapper", value = {
        @Result(property = "courseId" , column = "course_id"),
        @Result(property = "courseName" , column = "course_name"),
        @Result(property = "instructor" , column = "instructor_id",
            one = @One(select = "org.hrd.spring_homework002.repository.InstructorRepository.findInstructorById")
        )
    })
    @Select("""
        SELECT c.* FROM courses c
        INNER JOIN student_course sc ON c.course_id = sc.course_id
        WHERE sc.student_id = #{studentId}
    """)
    List<Course> findAllCoursesByStudentId(Integer studentId);

    @Select("""
        SELECT * FROM students s 
        WHERE s.student_id = #{id}
    """)
    @ResultMap("StudentMapper")
    Student findStudentById(Integer id);

    @Select("""
        INSERT INTO students(student_name, email, phone_number) 
        VALUES (#{student.studentName}, #{student.email}, #{student.phoneNumber})
        RETURNING student_id;
    """)
    Integer insertNewStudent(@Param("student") StudentRequest studentRequest);

    @Insert("""
        INSERT INTO student_course(student_id, course_id) 
        VALUES (#{studentId}, #{courseId})
    """)
    void insertNewStudentCourse(Integer studentId, Integer courseId);

    @Select("""
        UPDATE students
        SET student_name = #{student.studentName}, email = #{student.email}, phone_number = #{student.phoneNumber}
        WHERE student_id = #{id}
        RETURNING student_id;
    """)
    Integer updateStudentById(Integer id, @Param("student") StudentRequest studentRequest);

    @Delete("""
        DELETE FROM students
        WHERE student_id = #{id};
    """)
    Boolean deleteStudentById(Integer id);

    @Delete("""
        DELETE FROM student_course
        WHERE student_id = #{studentId};
    """)
    void deleteStudentCourses(Integer studentId);
}
