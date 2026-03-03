package com.book.servlet.pages;

import com.book.entity.Book;
import com.book.entity.User;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

    BookService bookService;

    @Override
    public void init() throws ServletException {
        bookService = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("nickname", user.getNickname());

        Map<Book, Boolean> bookList = bookService.getBookList();
        context.setVariable("book_list", bookList.keySet());
        context.setVariable("borrow_list_state", new ArrayList<>(bookList.values()));
        System.out.println(bookList);
        ThymeleafUtil.process("books.html", context, resp.getWriter());
    }

}
