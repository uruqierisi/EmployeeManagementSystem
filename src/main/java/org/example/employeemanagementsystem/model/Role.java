package org.example.employeemanagementsystem.model;

import java.util.Date;

public class Role {

    private int roleId;
    private String roleName;
    private String description;
    private Date createdDate;

    public Role(){

    }

    public Role(String roleName, String description){
        this.roleName = roleName;
        this.description = description;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString(){
        return String.format("ID: $d | Name: %s | Description: %s",
                roleId, roleName, description);
    }
}
