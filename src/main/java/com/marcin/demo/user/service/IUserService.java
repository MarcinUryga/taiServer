package com.marcin.demo.user.service;

import com.marcin.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAll();

    Optional<User> findById(long id);

    Optional<User> findByEmail(String email);

    User save(User user);

    List<User> save(List<User> user);

    void delete(User user);

    boolean existsById(Long userId);
}
