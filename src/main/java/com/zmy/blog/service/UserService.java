package com.zmy.blog.service;

import com.zmy.blog.entity.User;

public interface UserService {

    User getUserByNameOrEmail(String username);

    int updateUser(User user);
}
