package com.fanhq.example.entity;

import java.sql.Timestamp;

/**
 * @author fanhaiqiu
 * @date 2019/2/27
 */

public abstract class BaseEntity {

    private Integer id;

    private Timestamp createTime;

    private Timestamp modifiedTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
