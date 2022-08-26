package com.example.bookmanagement.model;

public class Book {
    private int book_id;
    private String name;
    private String author;
    private int pages;
    private float price;

    public Book(int id, String name, String author, int pages, float price) {
        this.book_id = id;
        this.name = name;
        this.author = author;
        this.pages = pages;
        this.price = price;
    }

    public Book(String name, String author, int pages, float price) {
        this.name = name;
        this.author = author;
        this.pages = pages;
        this.price = price;
    }

    public int getId() {
        return book_id;
    }

    public void setId(int book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                ", price=" + price +
                '}';
    }
}
