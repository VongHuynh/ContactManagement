package com.example.ContactsManagement.Repository;

import com.example.ContactsManagement.Entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactReposistory extends JpaRepository<Contact, Integer> {
}
