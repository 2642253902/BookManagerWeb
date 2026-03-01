package com.book.servlet.auth;

import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import com.book.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        if (req.getSession().getAttribute("error") != null) {
            System.out.println("有错误信息");
            context.setVariable("error", true);
            req.getSession().removeAttribute("error");
        }
        if (req.getSession().getAttribute("user") != null) {
            resp.sendRedirect("index");
            return;
        }
        ThymeleafUtil.process("login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember-me");
        if (userService.auth(username, password, req.getSession())) {
            if (remember != null) {
                // 处理记住我功能，例如设置一个持久化 cookie
                // 这里可以使用 JWT 或其他方式来实现持久登录
            }
            resp.getWriter().write("登录成功");
            resp.sendRedirect("index");
        } else {
            // 将错误信息 "用户名或密码错误" 存入当前用户的 Session，key 为 "error"
            req.getSession().setAttribute("error", "用户名或密码错误");
            this.doGet(req, resp);
        }
    }
}
