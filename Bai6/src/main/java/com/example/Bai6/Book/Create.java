package com.example.Bai6.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/create-book")
public class Create extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("create-book.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String ID=request.getParameter("id");
        String longDescription=request.getParameter("longDescription");
        String shortDescription=request.getParameter("shortDescription");
        String cost=request.getParameter("cost");
        Book book = new Book();
        book.setID(ID);
        book.setLongDescription(longDescription);
        book.setShortDescription(shortDescription);
        book.setCost(Integer.valueOf(cost));

        int status = BookDAO.save(book);
        if(status>0){
            out.print("<p>Record saved successfully!</p>");
            request.getRequestDispatcher("create-book").forward(request, response);
        }else{
            out.println("Sorry! unable to save record");
        }

        out.close();
    }
}
