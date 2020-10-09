package com.zmy.blog.service.impl;

import com.zmy.blog.entity.TagExample;
import com.zmy.blog.entity.User;
import com.zmy.blog.entity.UserExample;
import com.zmy.blog.mapper.UserMapper;
import com.zmy.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zengmy
 * @description:
 * @date 2020-09-22
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByNameOrEmail(String username) {
        return userMapper.getUserByNameOrEmail(username);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
