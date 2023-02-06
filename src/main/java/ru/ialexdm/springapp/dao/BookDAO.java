package ru.ialexdm.springapp.dao;

import org.springframework.stereotype.Component;
import ru.ialexdm.springapp.models.Book;

import java.util.ArrayList;
import java.util.List;
@Component
public class BookDAO {

    private static int GENERATED_ID;
    private List<Book> books;
    {
        books = new ArrayList<>();
        books.add(new Book(++GENERATED_ID, "Dictionary", "Dal" ,2008));
        books.add(new Book(++GENERATED_ID, "Code Complete","Steve McConnell", 2017));
        books.add(new Book(++GENERATED_ID, "Harry Potter and Sorcerer's stone", "Joanne K Rowling",2004));
    }

    public List<Book> index() {
        return books;
    }
}
