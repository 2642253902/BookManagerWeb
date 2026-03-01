package com.book.filter;


import com.book.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class MainFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        // 设置请求和响应的字符编码为 UTF-8，确保正确处理中文等非 ASCII 字符
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");

        String url = req.getRequestURL().toString();
        if (!(url.contains("/static/") || url.contains("/login"))) {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                res.sendRedirect("login");
                return;
            }

        }

        // 继续执行过滤链，调用下一个过滤器或目标资源（如 Servlet）
        chain.doFilter(req, res);
    }
}
