package com.ua.sutty.servervue.controller;

import com.ua.sutty.servervue.form.LoginForm;
import com.ua.sutty.servervue.model.User;
import com.ua.sutty.servervue.repository.UserRepository;
import com.ua.sutty.servervue.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody LoginForm loginForm) {
        if (loginForm == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findUserByUsername(loginForm.getUsername());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (passwordEncoder.matches(loginForm.getPassword(), user.getPassword())) {
            String jwt = jwtToken.generateToken(user);
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
