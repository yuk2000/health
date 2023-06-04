package com.yuk2000.dao;

import com.yuk2000.pojo.Role;

import java.util.Set;

public interface RoleDao {
    public Set<Role> findByUserId(int id);
}