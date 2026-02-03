package org.example.employeemanagementsystem.service;

import org.example.employeemanagementsystem.dao.DepartmentDAO;
import org.example.employeemanagementsystem.model.Department;

import java.util.List;

public class DepartmentService {

    private DepartmentDAO departmentDAO;

    public DepartmentService() {
        this.departmentDAO = new DepartmentDAO();
    }

    // get all departments
    public List<Department> getAllDepartments() {
        return departmentDAO.getAllDepartments();
    }

    public Department getDepartmentById(int id) {
        return departmentDAO.getDepartmentById(id);
    }

    // display all departments

    public void displayAllDepartments() {
        List<Department> departments = getAllDepartments();

        if (departments.isEmpty()) {
            System.out.println("No departments found!");
            return;
        }

        System.out.println("\n------ Available Departments ------");
        for(Department dept: departments){
            System.out.println(dept.toString());
        }
        System.out.println();
    }
}
