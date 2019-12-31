package com.luke.service.impl;

import com.luke.domain.User;
import com.luke.mapper.UserMapper;
import com.luke.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String name) {
        return userMapper.findByUsername(name);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }
}
