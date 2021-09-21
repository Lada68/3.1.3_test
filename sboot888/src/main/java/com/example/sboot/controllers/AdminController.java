package com.example.sboot.controllers;

import com.example.sboot.model.User;
import com.example.sboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<Set<User>> getAll() {
        Set<User> users = userService.findAllUsers().stream()
                .sorted(Comparator.comparing(User::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity <User> getOne(@PathVariable("id") Integer id){
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
}

    @PostMapping("/users")
    public ResponseEntity<User> AddNew(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.addNew(user);
            return new ResponseEntity<>(HttpStatus.OK); //user?

    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser (@RequestBody User user) {
            userService.update(user);
            return new ResponseEntity<>(HttpStatus.OK);
    }


  @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "void string";
    }


}



