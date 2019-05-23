package com.fanhq.example.repository;

import com.fanhq.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author fanhaiqiu
 * @date 2019/5/23
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 查询用户
     * @param username
     * @return
     */
    User findByUserName(String username);
}
