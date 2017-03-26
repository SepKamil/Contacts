package com.contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/users")
    public List<User> getAll(){
        return userRepository.findAll();
    }
    @DeleteMapping("/api/users/{login}")
    public ResponseEntity deleteUser(@PathVariable String login){
        if(userRepository.exists(login)){
            userRepository.delete(login);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/users/{login}")
    public ResponseEntity getUser(@PathVariable String login){
        if(userRepository.exists(login)){
            User user = userRepository.findOne(login);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/api/users")
    public ResponseEntity addUser(@RequestBody @Valid User user){
        if(userRepository.exists(user.getLogin())){
            return new ResponseEntity<>( HttpStatus.CONFLICT);
        }
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/api/users/{login}")
    public ResponseEntity editUser(@RequestBody @Valid User user, @PathVariable String login){
        if(userRepository.exists(login)){
            User oldUser = userRepository.findOne(login);
            userRepository.delete(oldUser);
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
