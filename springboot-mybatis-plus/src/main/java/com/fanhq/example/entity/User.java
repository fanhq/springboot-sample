package com.fanhq.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * @author fanhaiqiu
 * @date 2019/3/22
 */
@Data
@TableName("users")
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Date createTime;
}
