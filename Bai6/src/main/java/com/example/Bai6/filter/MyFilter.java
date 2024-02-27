package com.example.Bai6.filter;

import com.example.Bai6.Cookie.CookieUtilities;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//@WebFilter("/*")
public class MyFilter implements Filter {
    @Override
    public  void init (FilterConfig arg0) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        String role = (String) req.getAttribute("role");
        System.out.println(role);
        if(role == "A") {
//            chain.doFilter(req, res);
//            RequestDispatcher dispatcher = req.getRequestDispatcher("view-books");
//            dispatcher.forward(req, res);
//        } else if (role == "U") {
//            chain.doFilter(req, res);
//            RequestDispatcher dispatcher = req.getRequestDispatcher("create-books");
//            dispatcher.forward(req, res);
//            res.get
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("login");
            dispatcher.forward(req, res);
        }
//        chain.doFilter(req, res);//sends request to next resource
    }


    @Override
    public  void destroy() {

    }
}
