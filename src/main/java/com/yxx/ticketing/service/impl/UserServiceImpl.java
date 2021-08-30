package com.yxx.ticketing.service.impl;

import com.yxx.ticketing.dao.UserMapper;
import com.yxx.ticketing.model.User;
import com.yxx.ticketing.model.UserExample;
import com.yxx.ticketing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> findByExample(UserExample example) {
        return userMapper.selectByExample(example);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User selectByUsername(String userName) {
        return userMapper.selectByUsername(userName);
    }

    @Override
    public User selectByMobile(String mobile) {
        return userMapper.selectByMobile(mobile);
    }


}
