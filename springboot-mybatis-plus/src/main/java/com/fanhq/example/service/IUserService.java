package com.fanhq.example.service;

import com.fanhq.example.entity.User;

/**
 * @author fanhaiqiu
 * @date 2019/3/26
 */
public interface IUserService {

    /**
     * 查询
     * @param id
     * @return
     */
    User getById(int id);
}
