package com.example.controller;

import com.example.model.Contact;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.List;

public class ContactList {

    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Contact> contacts;

    public ContactList() {
    }

    public ContactList(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
