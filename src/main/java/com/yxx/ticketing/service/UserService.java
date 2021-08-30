package com.yxx.ticketing.service;

import com.yxx.ticketing.model.User;
import com.yxx.ticketing.model.UserExample;

import java.util.List;

/**
 *
 *
 */
public interface UserService {

    /**
     * 按条件查询
     * @param example 查询条件
     * @return
     */
    List<User> findByExample(UserExample example);
    int insertUser(User user);
    User selectByUsername(String userName);
    User selectByMobile(String mobile);
}
