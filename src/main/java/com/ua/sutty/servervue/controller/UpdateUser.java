package com.ua.sutty.servervue.controller;

import com.ua.sutty.servervue.model.User;
import com.ua.sutty.servervue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UpdateUser {

    @Autowired
    private UserRepository userRepository;

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody @Valid User user, @PathVariable Long id) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!userRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        user.setId(id);
        this.userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
