package com.contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kamil on 2017-03-25.
 */
@RestController
public class ContactsController {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/contacts")
    public List<Contact> getAll(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findOne(auth.getName());
        return contactRepository.findAll().stream().filter(contact -> contact.getUser().equals(user)).collect(Collectors.toList());
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
