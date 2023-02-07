package ru.ialexdm.springapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ialexdm.springapp.dao.BookDAO;
import ru.ialexdm.springapp.dao.ReaderDAO;
import ru.ialexdm.springapp.models.Reader;

@Controller
@RequestMapping("/readers")
public class ReadersController {
    private final ReaderDAO readerDAO;
    private final BookDAO bookDAO;
    public ReadersController(ReaderDAO readerDAO, BookDAO bookDAO) {
        this.readerDAO = readerDAO;
        this.bookDAO = bookDAO;
    }
    @GetMapping()
    public String index(Model model){
        model.addAttribute("readers", readerDAO.index());
        return "readers/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model)
    {
        Reader reader = readerDAO.show(id);
        model.addAttribute("reader",reader);
        model.addAttribute("readersBooks",bookDAO.showReaderBook(reader.getId()));
        return "readers/show";
    }
    @GetMapping("/{id}/edit")
    public String editReader(@PathVariable("id") Integer id, Model model){
        model.addAttribute("reader", readerDAO.show(id));
        return "readers/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("reader") Reader reader, @PathVariable("id") Integer id){
        readerDAO.update(id, reader);
        return "redirect:/readers/{id}";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id){
        readerDAO.delete(id);
        return "redirect:/readers";
    }
    @GetMapping("/new")
    public String newReader(@ModelAttribute("reader")Reader reader)
    {
        return "readers/new";
    }
    @PostMapping("")
    public String create(@ModelAttribute("reader")Reader reader)
    {
        readerDAO.save(reader);
        return "redirect:/readers";
    }


}
