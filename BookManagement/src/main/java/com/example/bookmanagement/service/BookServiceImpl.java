package com.example.bookmanagement.service;

import com.example.bookmanagement.dao.BookDaoImpl;
import com.example.bookmanagement.model.Book;

import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService{
    private BookDaoImpl bookDao;

    public BookServiceImpl(String url, String uname, String upass) {
        this.bookDao = new BookDaoImpl(url, uname, upass);
    }

    @Override
    public boolean addBook(Book newBook) throws SQLException {
        return bookDao.addBook(newBook);
    }

    @Override
    public List<Book> getAllBooks() throws SQLException {
        return bookDao.getAllBooks();
    }

    @Override
    public boolean updateBook(Book book) throws SQLException {
        return bookDao.updateBook(book);
    }

    @Override
    public boolean deleteBook(Book book) throws SQLException {
        return bookDao.deleteBook(book);
    }

    @Override
    public Book getById(int id) throws SQLException {
        return bookDao.getBookById(id);
    }
}
