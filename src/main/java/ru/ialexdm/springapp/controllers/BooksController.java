package ru.ialexdm.springapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ialexdm.springapp.dao.BookDAO;
import ru.ialexdm.springapp.models.Book;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDao;

    public BooksController(BookDAO bookDao) {
        this.bookDao = bookDao;
    }
    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDao.index());
        return "books/index";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("book") Book book)
    {
        bookDao.save(book);
        return "redirect:/books";
    }

}
