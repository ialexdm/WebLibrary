package ru.ialexdm.springapp.models;

public class Book {
    private Integer id;
    private String name;
    private String author;
    private Integer year;
    private Integer readerId;

    public Integer getReaderId() {
        return readerId;
    }

    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }



    public Book() {
    }

    public Book(Integer id, String name, String author, Integer year, Integer readerId) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.readerId = readerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
