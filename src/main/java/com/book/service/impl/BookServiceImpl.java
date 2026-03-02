package com.book.service.impl;

import com.book.dao.BookMapper;
import com.book.dao.UserMapper;
import com.book.entity.Book;
import com.book.entity.Borrow;
import com.book.entity.User;
import com.book.service.BookService;
import com.book.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BookServiceImpl implements BookService {

    @Override
    public List<Book> getBookList() {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            return mapper.getBookList();
        }
    }


    @Override
    public List<Borrow> getBorrowList() {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            return mapper.getBorrowList();
        }
    }

    @Override
    public void returnBook(String id) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            mapper.returnBook(id);
        }
    }

    public void getActiveBookList() {

      // Implementation for getting active book list
    }


}
