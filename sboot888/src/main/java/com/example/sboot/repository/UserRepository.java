package com.example.sboot.repository;

import com.example.sboot.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);

    User findById(int id);


    @Override
    void deleteById(Integer integer);
}
