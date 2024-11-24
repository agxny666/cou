package com.ustyn.courseproject.service.user;

import com.ustyn.courseproject.document.user.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    boolean existsByUsername(String username);
    User save(User user);
    List<User> findAll();
    User findById(String id);
    void deleteById(String id);
}
