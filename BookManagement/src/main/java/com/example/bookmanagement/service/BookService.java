package com.example.bookmanagement.service;

import com.example.bookmanagement.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookService {
    boolean addBook(Book newBook) throws SQLException;
    List<Book> getAllBooks() throws SQLException;
    boolean updateBook(Book book) throws SQLException;
    boolean deleteBook(Book book) throws SQLException;
    Book getById(int id) throws SQLException;

}
