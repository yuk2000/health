package com.yuk2000.dao;

import com.yuk2000.pojo.User;

public interface UserDao {
    public User findByUsername(String username);
}