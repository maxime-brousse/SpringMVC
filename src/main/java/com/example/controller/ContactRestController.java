package com.example.controller;

import com.example.model.Contact;
import com.example.repository.ContactRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/contacts", produces = "application/xml")
public class ContactRestController {

    private final ContactRepository repository;

    public ContactRestController(ContactRepository repository) {
        this.repository = repository;
    }

    // GET /api/contacts
    @GetMapping
    public ContactList getAllContacts() {
        return new ContactList(repository.findAll());
    }

    // GET /api/contacts/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
