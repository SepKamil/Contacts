package com.contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Kamil on 2017-03-25.
 */
@RestController
public class ContactsController {
    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/api/contacts")
    public List<Contact> getAll(){
        return contactRepository.findAll();
    }

    @DeleteMapping("/api/contacts/{id}")
    public ResponseEntity deleteContact(@PathVariable Long id){
        if(contactRepository.exists(id)){
            contactRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/contacts/{id}")
    public ResponseEntity getContact(@PathVariable Long id){
        if(contactRepository.exists(id)){
            Contact contact = contactRepository.findOne(id);
            return new ResponseEntity<>(contact, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/api/contacts")
    public ResponseEntity addContact(@RequestBody @Valid Contact contact){
        contactRepository.save(contact);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/api/contacts/{id}")
    public ResponseEntity editContact(@RequestBody @Valid Contact contact, @PathVariable Long id){
        if(contactRepository.exists(id)){
            contact.setId(id);
            contactRepository.save(contact);
            return new ResponseEntity<>(contact, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
