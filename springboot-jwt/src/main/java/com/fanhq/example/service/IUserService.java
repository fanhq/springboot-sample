package com.fanhq.example.service;

import com.fanhq.example.entity.User;

/**
 * @author fanhaiqiu
 * @date 2019/3/11
 */
public interface IUserService {

    /**
     * 查询用户
     * @param username
     * @return
     */
    User findByUsername(String username);

}
