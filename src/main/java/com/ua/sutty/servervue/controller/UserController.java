package com.ua.sutty.servervue.controller;

import com.ua.sutty.servervue.model.User;
import com.ua.sutty.servervue.repository.UserRepository;
import com.ua.sutty.servervue.validator.EmailValidator;
import com.ua.sutty.servervue.validator.UsernameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private final UserRepository userRepository;

    private final EmailValidator emailValidator;

    private final UsernameValidator usernameValidator;

    @Autowired
    public UserController(UserRepository userRepository, EmailValidator emailValidator,
                          UsernameValidator usernameValidator) {
        this.userRepository = userRepository;
        this.emailValidator = emailValidator;
        this.usernameValidator = usernameValidator;
    }

    @InitBinder
    public void addBinder(WebDataBinder binder) {
        binder.addValidators(emailValidator);
        binder.addValidators(usernameValidator);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = this.userRepository.findAll();

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody @Valid User user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        User user = this.userRepository.findUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.userRepository.deleteUserById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
