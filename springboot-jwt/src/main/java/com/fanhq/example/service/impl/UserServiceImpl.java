package com.fanhq.example.service.impl;

import com.fanhq.example.entity.User;
import com.fanhq.example.repository.UserRepository;
import com.fanhq.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fanhaiqiu
 * @date 2019/3/11
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
       return userRepository.findByUserName(username);
    }
}
