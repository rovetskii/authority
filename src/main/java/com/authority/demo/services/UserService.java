package com.authority.demo.services;

import com.authority.demo.exception.ExceptionInfo;
import com.authority.demo.exception.ServiceException;
import com.authority.demo.models.User;
import com.authority.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Transactional
    public User saveUser(User u) {
        return userRepository.saveUser(u);
    }

    @Transactional
    public Optional<User> findUserByUserName(String username) {
        return userRepository.findUserByUserName(username);
    }
}
