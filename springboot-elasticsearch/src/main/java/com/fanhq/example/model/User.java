package com.fanhq.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @author fanhaiqiu
 * @date 2019/5/21
 */

@Document(indexName = "user_index", type = "user")
public class User {
    /**
     * 编号
     */
    @Id
    private Long id;
    /**
     * 姓名
     */
    @Field(type = FieldType.Keyword)
    private String name;

    /**
     * 年龄
     */
    @Field(type = FieldType.Integer)
    private Integer age;

    /**
     * 描述
     */
    @Field(type = FieldType.Keyword)
    private String description;

    /**
     * 创建时间
     */
    @Field(type = FieldType.Date)
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
