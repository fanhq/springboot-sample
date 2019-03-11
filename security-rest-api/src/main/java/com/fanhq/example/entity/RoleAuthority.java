package com.fanhq.example.entity;

/**
 * @author fanhaiqiu
 * @date 2019/2/26
 */
public class RoleAuthority extends BaseEntity {

    private Integer roleId;

    private Integer authorityId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

}
