package com.fanhq.example.entity;

import javax.persistence.*;

/**
 * @author fanhaiqiu
 * @date 2019/6/25
 */

@Entity
@Table(name = "t_token")
public class Token extends BaseEntity{

    @Column(name = "token")
    private String token;

    @Column(name = "expire_time")
    private long expireTime;

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }
}
