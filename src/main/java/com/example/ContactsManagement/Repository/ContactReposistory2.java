package com.example.ContactsManagement.Repository;

import com.example.ContactsManagement.Entity.Account;

import com.example.ContactsManagement.Entity.Contact;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface ContactReposistory2 extends DataTablesRepository<Contact, Integer> {
}
