package com.book.dao;

import com.book.entity.Borrow;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookMapper {

    @Results({
            @Result(property = "book_id", column = "bid"),
            @Result(property = "book_name", column = "title"),
            @Result(property = "time", column = "time"),
            @Result(property = "student_id", column = "sid"),
            @Result(property = "student_name", column = "name")
    })
    @Select("select * from borrow,student,student,book where borrow.bid =book.bid and student.sid=borrow.sid")
    List<Borrow> getBorrowList();
}
