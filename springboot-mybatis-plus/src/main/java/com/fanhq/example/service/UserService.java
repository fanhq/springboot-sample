package com.fanhq.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fanhq.example.entity.User;
import com.fanhq.example.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author fanhaiqiu
 * @date 2019/3/25
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {


}
