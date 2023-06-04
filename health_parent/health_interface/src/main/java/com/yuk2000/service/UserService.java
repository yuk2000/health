package com.yuk2000.service;

import com.yuk2000.pojo.User;

/**
 * 用户服务接口
 */
public interface UserService {
    public User findByUsername(String username);
}