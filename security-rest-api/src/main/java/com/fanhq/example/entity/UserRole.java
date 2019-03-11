package com.fanhq.example.entity;

/**
 * @author fanhaiqiu
 * @date 2019/2/26
 */
public class UserRole extends BaseEntity {

    private Integer userId;

    private Integer roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}
