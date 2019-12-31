package com.luke.mapper;

import com.luke.domain.User;

public interface UserMapper {
     User findByUsername(String username);

     User findById(Integer id);
}
