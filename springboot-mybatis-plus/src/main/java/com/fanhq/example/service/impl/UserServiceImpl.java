package com.fanhq.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fanhq.example.entity.User;
import com.fanhq.example.mapper.UserMapper;
import com.fanhq.example.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author fanhaiqiu
 * @date 2019/3/25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Override
    public User getById(int id) {
        return super.getById(id);
    }
}
