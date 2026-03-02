package com.book.dao;

import com.book.entity.Book;
import com.book.entity.Borrow;
import com.book.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookMapper {

    @Select("SELECT * FROM book ")
    List<Book> getBookList();


    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "book_id", column = "bid"),
            @Result(property = "book_name", column = "title"),
            @Result(property = "time", column = "time"),
            @Result(property = "student_id", column = "sid"),
            @Result(property = "student_name", column = "name")
    })
    @Select("select * from borrow,student,book where borrow.bid =book.bid and student.sid=borrow.sid")
    List<Borrow> getBorrowList();

    @Delete("delete from borrow where id = #{id}")
    void returnBook(String id);

}
