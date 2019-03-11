package com.fanhq.example.service;

import com.fanhq.example.entity.Authority;
import com.fanhq.example.entity.Role;
import com.fanhq.example.entity.User;

import java.util.List;

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


    /**
     * 查询用户角色
     * @param userId
     * @return
     */
    List<Role> getRoles(int userId);

    /**
     * 查询用户权限
     * @param roleId
     * @return
     */
    List<Authority> getAuthorities(int roleId);
}
