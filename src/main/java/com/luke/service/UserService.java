package com.luke.service;

import com.luke.domain.User;

public interface UserService {
    public User findByUsername(String name);

    public User findById(Integer id);
}
