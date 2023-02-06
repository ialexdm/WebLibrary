package ru.ialexdm.springapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ialexdm.springapp.dao.BookDAO;
import ru.ialexdm.springapp.dao.ReaderDAO;
import ru.ialexdm.springapp.models.Book;
import ru.ialexdm.springapp.models.Reader;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDao;
    private final ReaderDAO readerDao;

    public BooksController(BookDAO bookDao, ReaderDAO readerDao) {
        this.bookDao = bookDao;
        this.readerDao = readerDao;
    }
    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDao.index());
        return "books/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("reader")Reader reader){
        Book book = bookDao.show(id);
        model.addAttribute("book", book);
        if (book.getReaderId()!=null)
        {
            model.addAttribute("bookReader", readerDao.show(book.getReaderId()));
        }
        else {
            model.addAttribute("readers", readerDao.index());
        }
        return  "books/show";
    }
    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDao.show(id));
        return "books/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book")Book book, @PathVariable("id") int id){
        bookDao.update(id, book);
        return "redirect:/books/{id}";
    }
    @PatchMapping("/{id}/give")
    public String give(@ModelAttribute("book")Book book,
                       @ModelAttribute("reader") Reader reader,
                       @PathVariable("id") int id){
        bookDao.give(id, reader.getId());
        return "redirect:/books/{id}";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id)
    {
        bookDao.delete(id);
        return "redirect:/books";
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
