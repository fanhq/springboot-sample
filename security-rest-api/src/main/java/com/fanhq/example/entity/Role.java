package com.fanhq.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author fanhaiqiu
 * @date 2019/2/26
 */
@Entity
@Table(name = "role")
public class Role extends BaseEntity {

    @Column(name = "role_name", nullable = true, length = 64)
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
