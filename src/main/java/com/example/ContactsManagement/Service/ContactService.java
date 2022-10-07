package com.example.ContactsManagement.Service;

import com.example.ContactsManagement.DTO.ContactDTO;
import com.example.ContactsManagement.Entity.Contact;

import java.util.List;

public interface ContactService {
    List<ContactDTO> getAllContacts();
    ContactDTO addContact(ContactDTO contactDTO);
}
