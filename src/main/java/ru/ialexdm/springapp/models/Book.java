package ru.ialexdm.springapp.models;

import jakarta.validation.constraints.*;

import java.time.Year;
import java.util.Calendar;

public class Book {
    private Integer id;

    @NotEmpty(message = "Field should not be empty")
    private String name;
    @NotEmpty(message = "Field should not be empty")
    @Size(min = 2,max = 128, message = "Field should be greater than 2 and less than 128 letters")
    private String author;
    @Min(value = 1800, message = "Year should be greater than 1800 and less than 2023")
    @Max(value = 2023, message = "Year should be greater than 0 and less than 128")
    @NotNull(message = "Field should not be empty")
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
