package org.example.employeemanagementsystem.service;

import org.example.employeemanagementsystem.dao.EmployeeDAO;
import org.example.employeemanagementsystem.model.Employee;
import org.example.employeemanagementsystem.model.User;

import java.util.List;

public class EmployeeService {

    private EmployeeDAO employeeDAO;

    public EmployeeService() {
        this.employeeDAO = new EmployeeDAO();
    }

    public boolean addEmployee(Employee employee) {
        if (employee.getFirstName() == null || employee.getFirstName().trim().isEmpty()) {
            System.out.println("First name is required");
            return false;
        }

        if (employee.getLastName() == null || employee.getLastName().trim().isEmpty()) {
            System.out.println("Last name is required");
            return false;
        }

        if (employee.getEmail() == null || !employee.getEmail().trim().contains("@")) {
            System.out.println("Invalid email address!!");
            return false;
        }

        if (employee.getSalary() < 0) {
            System.out.println("Salary cannot be negative!");
            return false;
        }
        if (employee.getSalary() > 1000000) {
            System.out.println("Salary is too high! (spagujn shume ne Kosove)");
            return false;
        }

        boolean result = employeeDAO.createEmployee(employee);

        if (result) {
            System.out.println("Employee added successfully");
        } else {
            System.out.println("Failed to add employee");
        }

        return result;
    }

    // update employee
    public boolean updateEmployee(Employee employee) {

        if (employee.getEmployeeId() <= 0) {
            System.out.println("Invalid employee ID!");
            return false;
        }

        if (employee.getFirstName() == null || employee.getFirstName().trim().isEmpty()) {
            System.out.println("First name is required");
            return false;
        }

        if (employee.getLastName() == null || employee.getLastName().trim().isEmpty()) {
            System.out.println("Last name is required");
            return false;
        }

        if (employee.getEmail() == null || !employee.getEmail().trim().contains("@")) {
            System.out.println("Invalid email address!");
            return false;
        }

        boolean result = employeeDAO.updateEmployee(employee);

        if (result) {
            System.out.println("Employee updated successfully");
        } else {
            System.out.println("Failed to update employee");
        }

        return result;
    }

    // delete employee amo veq admini munet
    public boolean deleteEmployee(int employeeId, User currentUser) {
        // authorization checkk

        if (currentUser.getUserRoleId() != 1){
            System.out.println("Only administrators can delete employees!");
            System.out.println("You do not have permission for this operation.");
            return false;
        }

        // validimi
        if (employeeId <= 0) {
            System.out.println("Invalid employee ID!");
        }

        Employee employee = employeeDAO.getEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Employee not found!");
            return false;
        }

        boolean result = employeeDAO.deleteEmployee(employeeId);
        if (result){
            System.out.print("Employee deleted successfully");
        }else {
            System.out.println("Failed to delete employee!");
        }
        return result;

    }

    // view all employees
    public List<Employee> viewAllEmployees() {
        return employeeDAO.getAllEmployess();
    }

    // view employee by id
    public Employee viewEmployeeById(int employeeId) {
         if (employeeId <= 0) {
             System.out.println("Invalid employee ID!");
             return null;
         }
         Employee employee = employeeDAO.getEmployeeById(employeeId);

         if (employee == null) {
             System.out.println("Employee not found!");
             return null;
         }

         return employee;
    }
}
