package com.marcin.demo.user.service;

import com.marcin.demo.model.User;
import com.marcin.demo.user.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserJpaRepository userJpaRespository;

    @Override
    public List<User> findAll() {
        return userJpaRespository.findAll();
    }

    @Override
    public Optional<User> findById(long id) {
        return userJpaRespository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRespository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        userJpaRespository.save(user);
        return userJpaRespository.findById(user.getId()).get();
    }

    @Override
    public List<User> save(List<User> users) {
        List<User> userList = new ArrayList<>();
        users.forEach(user -> {
            userJpaRespository.save(user);
            userList.add(userJpaRespository.findById(user.getId()).get());
        });
        return userList;
    }

    @Override
    public void delete(User user) {
        userJpaRespository.delete(user);
    }

    @Override
    public boolean existsById(Long userId) {
        return userJpaRespository.existsById(userId);
    }
}
