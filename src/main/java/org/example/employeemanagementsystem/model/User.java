package org.example.employeemanagementsystem.model;

import java.util.Date;

public class User {

    private int userId;
    private String username;
    private String password;
    private Integer employeeId; // munet me kon Null
    private int userRoleId; // 1 = Admin, 2 = User i thjesht
    private boolean isActive;
    private Date createdDate;
    private Date lastLogin;

    public User(){

    }

    public User(String username, String password, int userRoleId){
        this.username = username;
        this.password = password;
        this.userRoleId = userRoleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isAdmin() {
        return userRoleId == 1;
    }
    public String getRoleType(){
        return userRoleId == 1 ? "Administrator" : "Regular User";
    }

    @Override
    public String toString(){
        return String.format("Username: %s | Role: %s | Active: %s",
                username, getRoleType(), isActive);
    }
}
