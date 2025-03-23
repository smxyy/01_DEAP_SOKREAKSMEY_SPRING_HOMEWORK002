package org.hrd.spring_homework002.repository;

import org.apache.ibatis.annotations.*;
import org.hrd.spring_homework002.model.Instructor;
import org.hrd.spring_homework002.model.request.InstructorRequest;

import java.util.List;

@Mapper
public interface InstructorRepository {

    @Select("SELECT * FROM instructors")
    @Results(id = "instructorMapper", value={
            @Result(property = "instructorId", column = "instructor_id"),
            @Result(property = "instructorName", column = "instructor_name")
    })
    List<Instructor> findAllInstructors();

    @Select("""
        SELECT * FROM instructors
        OFFSET #{size} * (#{page} - 1)
        LIMIT #{size}
    """)
    @ResultMap("instructorMapper")
    List<Instructor> findAllInstructorsByPagination(int page, int size);

    @Select("""
        SELECT * FROM instructors
        WHERE instructor_id = #{id}
    """)
    @ResultMap("instructorMapper")
    Instructor findInstructorById(Integer id);

    @Select("""
        INSERT INTO instructors(instructor_name, email)
        VALUES(#{instructor.instructorName}, #{instructor.email})
        RETURNING *;
    """)
    @ResultMap("instructorMapper")
    Instructor insertNewInstructor(@Param("instructor") InstructorRequest instructorRequest);

    @Select("""
        UPDATE instructors
        SET instructor_name = #{instructor.instructorName}, email = #{instructor.email}
        WHERE instructor_id = #{id}
        RETURNING *;
    """)
    @ResultMap("instructorMapper")
    Instructor updateInstructorInfoById(Integer id, @Param("instructor") InstructorRequest instructorRequest);

    @Delete("""
        DELETE FROM instructors
        WHERE instructor_id = #{id};
    """)
    Boolean deleteInstructorById(Integer id);
}
