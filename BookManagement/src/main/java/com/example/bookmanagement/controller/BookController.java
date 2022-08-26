package com.example.bookmanagement.controller;


import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.service.BookServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@WebServlet(value = "/book", name = "bookController")
public class BookController extends HttpServlet {
    private BookServiceImpl bookService;

    public void init(){
        try(InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            this.bookService = new BookServiceImpl(
                    prop.getProperty("db.url"),
                    prop.getProperty("db.username"),
                    prop.getProperty("db.password")
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action") != null ? request.getParameter("action") : "none";
        Book book;
        try {
            switch (action) {
                case "new":
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/newBook.jsp");
                    dispatcher.forward(request, response);
                    break;

                case "insert":
                    String name = request.getParameter("name");
                    String author = request.getParameter("author");
                    int pages = Integer.parseInt(request.getParameter("pages"));
                    float price = Float.parseFloat(request.getParameter("price"));

                    book = new Book(name, author, pages, price);
                    this.bookService.addBook(book);
                    response.sendRedirect("book");
                    break;

                case "edit":
                    showEditPage(request, response);
                    break;

                case "update":
                    updateBook(request, response);
                    break;

                case "delete":
                    int id = Integer.parseInt(request.getParameter("id"));
                    book = this.bookService.getById(id);
                    this.bookService.deleteBook(book);
                    response.sendRedirect("book");
                    break;

                default:
                    getListBooks(request, response);
                    break;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        int pages = Integer.parseInt(request.getParameter("pages"));
        float price = Float.parseFloat(request.getParameter("price"));

        Book book = new Book(id, name, author, pages, price);
         this.bookService.updateBook(book);
        response.sendRedirect("book");
    }

    private void showEditPage(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book bookById = this.bookService.getById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/newBook.jsp");
        request.setAttribute("book", bookById);
        dispatcher.forward(request, response);
    }

    private void getListBooks(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Book> books = this.bookService.getAllBooks();
            request.setAttribute("listBook", books);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/bookList.jsp");
            dispatcher.forward(request, response);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
