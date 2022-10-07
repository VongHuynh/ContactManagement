package com.example.ContactsManagement.Controller;

import com.example.ContactsManagement.Entity.Contact;
import com.example.ContactsManagement.Repository.ContactReposistory2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.EntityManager;
import javax.validation.Valid;


@RestController
public class ContactRestController2 {
    @Autowired
    ContactReposistory2 controller2;
    @Autowired
    EntityManager entityManager;
    @Autowired
    RedisTemplate template;

    @RequestMapping(value = "/table/contacts", method = RequestMethod.GET)
    public DataTablesOutput<Contact> list(@Valid DataTablesInput input) {
        return controller2.findAll(input);
    }


}
