package com.fanhq.example.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author fanhaiqiu
 * @date 2019/5/9
 */

@Entity
@Table(name="order")
@Data
public class OrderEntity {

    @Id
    private long id;
    private long userId;
    private String status;
}
