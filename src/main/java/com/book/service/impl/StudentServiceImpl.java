package com.book.service.impl;

import com.book.dao.BookMapper;
import com.book.dao.StudentMapper;
import com.book.entity.Book;
import com.book.entity.Borrow;
import com.book.entity.Student;
import com.book.service.BookService;
import com.book.service.StudentService;
import com.book.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class StudentServiceImpl implements StudentService {


    @Override
    public List<Student> getStudentList() {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            return mapper.getStudentList();
        }
    }
}
