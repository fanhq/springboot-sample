package com.fanhq.example.service.impl;

import com.fanhq.example.entity.Authority;
import com.fanhq.example.entity.Role;
import com.fanhq.example.entity.User;
import com.fanhq.example.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fanhaiqiu
 * @date 2019/3/11
 */
@Service
public class UserServiceImpl implements IUserService {
    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public List<Role> getRoles(int userId) {
        return null;
    }

    @Override
    public List<Authority> getAuthorities(int roleId) {
        return null;
    }
}
