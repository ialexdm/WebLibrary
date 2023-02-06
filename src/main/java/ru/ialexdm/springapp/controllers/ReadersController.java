package ru.ialexdm.springapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ialexdm.springapp.dao.ReaderDAO;
import ru.ialexdm.springapp.models.Reader;

@Controller
@RequestMapping("/readers")
public class ReadersController {
    private final ReaderDAO readerDAO;
    public ReadersController(ReaderDAO readerDAO) {
        this.readerDAO = readerDAO;
    }
    @GetMapping()
    public String index(Model model){
        model.addAttribute("readers", readerDAO.index());
        return "readers/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model)
    {
        model.addAttribute(readerDAO.show(id));
        return "readers/show";
    }
    @GetMapping("/{id}/edit")
    public String editReader(@PathVariable("id") int id, Model model){
        model.addAttribute("reader", readerDAO.show(id));
        return "readers/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("reader") Reader reader, @PathVariable("id") int id){
        readerDAO.update(id, reader);
        return "redirect:/readers/{id}";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
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
