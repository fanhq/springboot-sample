package com.fanhq.example.repository;


import com.fanhq.example.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author fanhaiqiu
 * @date 2019/6/25
 */
public interface TokenRepository extends JpaRepository<Token, Long> {

    /**
     * 判断token是否有效
     * @param token
     * @param now
     * @return
     */
    boolean existsByTokenAndExpireTimeIsGreaterThan(String token, long now);
}
