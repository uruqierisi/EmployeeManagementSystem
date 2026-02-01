package org.example.employeemanagementsystem.model;

import java.util.Date;

public class Department {

    private int departmentId;
    private String departmentName;
    private String location;
    private Date createdDate;

    public Department() {
    }

    public Department(String departmentName, String location){
        this.departmentName = departmentName;
        this.location = location;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString(){
        return String.format("ID: $d | Name: %s | Location: %s",
                departmentId, departmentName, location);
    }
}
