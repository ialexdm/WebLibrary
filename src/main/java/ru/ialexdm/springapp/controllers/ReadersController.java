package ru.ialexdm.springapp.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ialexdm.springapp.dao.BookDAO;
import ru.ialexdm.springapp.dao.ReaderDAO;
import ru.ialexdm.springapp.models.Reader;
import ru.ialexdm.springapp.util.ReaderValidator;

@Controller
@RequestMapping("/readers")
public class ReadersController {
    private final ReaderDAO readerDAO;
    private final BookDAO bookDAO;
    private final ReaderValidator readerValidator;
    public ReadersController(ReaderDAO readerDAO, BookDAO bookDAO, ReaderValidator readerValidator) {
        this.readerDAO = readerDAO;
        this.bookDAO = bookDAO;
        this.readerValidator = readerValidator;
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
    public String update(@ModelAttribute("reader")@Valid Reader reader, BindingResult bindingResult, @PathVariable("id") Integer id){
        readerValidator.validate(reader,bindingResult);

        if (bindingResult.hasErrors()){
            return "readers/edit";
        }
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
    @PostMapping()
    public String create(@ModelAttribute("reader")@Valid Reader reader, BindingResult bindingResult) {
        readerValidator.validate(reader,bindingResult);

        if (bindingResult.hasErrors()){
            return "readers/new";
        }
        readerDAO.save(reader);
        return "redirect:/readers";
    }


}
