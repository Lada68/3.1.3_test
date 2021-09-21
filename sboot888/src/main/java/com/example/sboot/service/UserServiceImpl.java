package com.example.sboot.service;

import com.example.sboot.model.Role;
import com.example.sboot.model.User;
import com.example.sboot.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Set;


@Service
@Transactional
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = (User) userRepository.findByUsername(s);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword()
                , mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());

    }
    @Override
    public List<User> findAllUsers() {
        // sorted by id
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> getOne(Integer id) {
        return userRepository.findById(id);
    }

    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void addNew(User user) {
        userRepository.save(user);
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

}
