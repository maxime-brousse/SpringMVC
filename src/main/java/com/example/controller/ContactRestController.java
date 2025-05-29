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

    // POST (Créer)
    @PostMapping(consumes = "application/xml")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        Contact saved = repository.save(contact);
        return ResponseEntity.ok(saved);
    }

    // PUT (Modifier)
    @PutMapping(value = "/{id}", consumes = "application/xml")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact updated) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setNom(updated.getNom());
                    existing.setPrenom(updated.getPrenom());
                    existing.setNumero(updated.getNumero());
                    repository.save(existing);
                    return ResponseEntity.ok(existing);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
