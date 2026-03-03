package com.book.service.impl;

import com.book.dao.BookMapper;
import com.book.entity.Book;
import com.book.entity.Borrow;
import com.book.service.BookService;
import com.book.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.*;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {

    @Override
    public Map<Book, Boolean> getBookList() {
        // 创建一个HashSet用于存放所有已借出的图书ID，方便后续查找
        HashSet<Integer> set = new HashSet<>();
        // 遍历所有借阅记录，把每条借阅记录中的book_id（图书ID）加入set
        this.getBorrowList().forEach(borrow -> set.add(borrow.getBook_id()));
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            // 创建一个LinkedHashMap用于存放图书和其借出状态的对应关系
            Map<Book, Boolean> map = new LinkedHashMap<>();
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);

            // 遍历所有图书列表
            mapper.getBookList().forEach(book -> {
                // 如果set中包含该图书ID，说明已借出，map中value为true，否则为false
                map.put(book, set.contains(book.getBid()));
            });

            // 返回包含所有图书及其借出状态的Map
            return map;
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

    /**
     * 获取当前未被借出的图书列表。
     * 1. 首先获取所有已借出的图书ID，存入HashSet。
     * 2. 然后查询所有图书列表，过滤掉已借出的图书。
     * 3. 返回未被借出的图书列表。
     */
    public List<Book> getActiveBookList() {
        // 创建一个HashSet用于存放已借出的图书ID，方便后续查找
        HashSet<Integer> set = new HashSet<>();
        // 遍历所有借阅记录，把每条借阅记录中的book_id（图书ID）加入set
        this.getBorrowList().forEach(borrow -> set.add(borrow.getBook_id()));
        // 打开数据库会话，准备查询所有图书
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            // 查询所有图书列表
            return mapper.getBookList()
                    .stream()
                    // 过滤：只保留那些没有被借出的图书（即ID不在set中的图书）
                    //set 是一个 HashSet，里面存放了所有已借出的图书的 ID （book_id）。
                    //book.getBid() 是当前遍历到的图书的 ID。
                    //set.contains(book.getBid()) 判断这本书的 ID 是否在已借出的 ID 集合里。
                    .filter(book -> !set.contains(book.getBid()))
                    // 把过滤后的结果收集成List返回
                    .collect(Collectors.toList());
        }
    }

    @Override
    public void addBorrow(int sid, int bid) {

        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            mapper.addBorrow(sid, bid);
        }
    }


    @Override
    public void deleteBook(String id) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            mapper.deleteBook(id);
        }
    }

    @Override
    public void addBook(String title, String desc, Double price) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            mapper.addBook(title, desc, price);
        }
    }



}
