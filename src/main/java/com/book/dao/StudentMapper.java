package com.book.dao;

import com.book.entity.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentMapper {
    @Select("SELECT * FROM student ")
    List<Student> getStudentList();

}
