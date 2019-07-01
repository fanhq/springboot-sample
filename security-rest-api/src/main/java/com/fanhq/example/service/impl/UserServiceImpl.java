package com.fanhq.example.service.impl;

import com.fanhq.example.entity.Authority;
import com.fanhq.example.entity.Role;
import com.fanhq.example.entity.User;
import com.fanhq.example.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fanhaiqiu
 * @date 2019/3/11
 */
@Service
public class UserServiceImpl implements IUserService {
    @Override
    public User findByUsername(String username) {
        User user = new User();
        user.setUsername("fanhaiqiu");
        // 原文 123@abc
        user.setPassword("$2a$10$loAGFzPCqsU0v1FGmIW7puVeOoBkpknCtV7xt8xxso.8wqteNyJrC");
        return user;
    }

    @Override
    public List<Role> getRoles(Integer userId) {
        Role role = new Role();
        role.setRoleName("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        return roles;
    }

    @Override
    public List<Authority> getAuthorities(Integer roleId) {
        Authority auth = new Authority();
        auth.setAuthorityName("QUERY");
        List<Authority> authorities = new ArrayList<>();
        authorities.add(auth);
        return authorities;
    }
}
