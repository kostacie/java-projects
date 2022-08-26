package com.example.bookmanagement.dao;

import com.example.bookmanagement.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    boolean addBook(Book newBook) throws SQLException;
    boolean updateBook(Book book) throws SQLException;
    boolean deleteBook(Book book) throws SQLException;
    List<Book> getAllBooks() throws SQLException;
    Book getBookById(int id) throws SQLException;
}
