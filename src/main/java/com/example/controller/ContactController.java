package com.example.controller;

import com.example.model.Contact;
import com.example.repository.ContactRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    private final ContactRepository repository;

    public ContactController(ContactRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("contacts", repository.findAll());
        return "contacts";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "form";
    }

    @PostMapping
    public String save(@ModelAttribute Contact contact) {
        repository.save(contact);
        return "redirect:/contacts";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("contact", repository.findById(id).orElseThrow());
        return "form";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Contact contact) {
        contact.setId(id);
        repository.save(contact);
        return "redirect:/contacts";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/contacts";
    }
}
