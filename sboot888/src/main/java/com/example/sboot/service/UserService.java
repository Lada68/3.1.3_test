package com.example.sboot.service;

import com.example.sboot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {


    User findByUsername(String username);

   // List<User> getAll();

    User findById(int id);
    Optional<User> getOne(Integer id);

    void addNew(User user);

    void update(User user);
    void delete(Integer id);


    List<User> findAll();

    List<User> findAllUsers();
}