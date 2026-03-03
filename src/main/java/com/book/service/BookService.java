package com.book.service;

import com.book.entity.Book;
import com.book.entity.Borrow;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BookService {
    List<Borrow> getBorrowList();

    void returnBook(String id);


    List<Book> getActiveBookList();


    void addBorrow(int sid, int bid);

    Map<Book, Boolean> getBookList();


    void deleteBook(String id);

    void addBook(String title, String desc, Double price);

}
