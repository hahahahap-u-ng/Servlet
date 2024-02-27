package com.example.Bai6.Auth;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
       String username = request.getParameter("username");
       String password = request.getParameter("password");
       User userExist = UserDAO.validate(username, password);
        HttpSession session = request.getSession();
       if (userExist != null) {
//           Cookie user = new Cookie("username", userExist.getUsername() );
//           Cookie pass = new Cookie("password", userExist.getPassword() );
//           Cookie role = new Cookie("role", userExist.getRole() );
            session.setAttribute("username", userExist.getUsername());
            session.setAttribute("password", userExist.getPassword());
            session.setAttribute("role", userExist.getRole());

           RequestDispatcher dispatcher = request.getRequestDispatcher("view-books");
           dispatcher.forward(request, response);
       } else {
           RequestDispatcher dispatcher = request.getRequestDispatcher("login");
           dispatcher.forward(request, response);

       }
    }

}
