package com.example.bookmanagement.dao;

import com.example.bookmanagement.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {

    private String url;
    private String uname;
    private String upass;
    private Connection connection;
    private final String driverPath = "com.mysql.cj.jdbc.Driver";

    public BookDaoImpl(String url, String uname, String upass) {
        this.url = url;
        this.uname = uname;
        this.upass = upass;
    }

    public void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName(driverPath);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            connection = DriverManager.getConnection(url, uname, upass);
        }
    }

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed() )
            connection.close();
    }

    private boolean executeUpdate(String query, Book book){
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, book.getName());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getPages());
            ps.setFloat(4, book.getPrice());
            if (book.getId() != 0) ps.setInt(5, book.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addBook(Book newBook) throws SQLException {
        boolean isInserted;
        String sql = "INSERT INTO books(name, author, pages, price) VALUES(?, ?, ?, ?)";
        connect();
        isInserted = executeUpdate(sql, newBook);
        disconnect();
        return isInserted;
    }

    @Override
    public boolean updateBook(Book book) throws SQLException {
        boolean isUpdated;
        String sql = "UPDATE books SET name=?, author=?, pages=?, price=? WHERE book_id=?";
        connect();
        isUpdated = executeUpdate(sql, book);
        disconnect();
        return isUpdated;
    }

    @Override
    public boolean deleteBook(Book book) throws SQLException {
        boolean isDeleted;
        String sql = "DELETE FROM books WHERE book_id=?";
        connect();
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, book.getId());
            isDeleted = ps.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        disconnect();
        return isDeleted;
    }

    @Override
    public List<Book> getAllBooks() throws SQLException {
        String sql = "SELECT * FROM books";
        List<Book> books = new ArrayList<>();
        connect();
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("book_id");
                String name = rs.getString("name");
                String author = rs.getString("author");
                int pages = rs.getInt("pages");
                float price = rs.getFloat("price");
                books.add(new Book(id, name, author, pages, price));
            }
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        disconnect();
        return books;
    }

/*    private void executeQuery(String query, int id){
        Book book = null;
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            if (id != -1){
                ps.setInt(1, id);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("book_id");
                String name = rs.getString("name");
                String author = rs.getString("author");
                int pages = rs.getInt("pages");
                float price F= rs.getFloat("price");
                books.add(new Book(id, name, author, pages, price));
            }
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

    @Override
    public Book getBookById(int id) throws SQLException {
        String sql = "SELECT name, author, pages, price FROM books WHERE book_id=?";
        Book book = null;
        connect();
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                String name = rs.getString("name");
                String author = rs.getString("author");
                int pages = rs.getInt("pages");
                float price = rs.getFloat("price");
                book = new Book(id, name, author, pages, price);
            }
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        disconnect();
        return book;
    }
}
