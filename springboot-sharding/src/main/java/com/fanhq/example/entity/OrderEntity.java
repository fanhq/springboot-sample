package com.fanhq.example.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author fanhaiqiu
 * @date 2019/5/9
 */

@Entity
@Table(name="t_order")
@Data
public class OrderEntity {

    @Id
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "status")
    private String status;
}
