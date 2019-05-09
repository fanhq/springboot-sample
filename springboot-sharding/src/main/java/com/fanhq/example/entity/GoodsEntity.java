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
@Table(name="t_goods")
@Data
public class GoodsEntity {

    @Id
    private Long id;

    private String name;

    private Long type;
}
