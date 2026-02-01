package org.example.employeemanagementsystem.dao;

import org.example.employeemanagementsystem.helper.DbConnection;
import org.example.employeemanagementsystem.model.Employee;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // me kta e krijojm 1 employee te ri tu perdor proceduren qe e kena bo ne SQL

    public boolean createEmployee(Employee employee) {
        String sql = "{CALL sp_CreateEmployee(?, ?, ?, ?,?, ?, ?, ?)}";

        try (Connection conn = DbConnection.connect();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, employee.getFirstName());
            st.setString(2, employee.getLastName());
            st.setString(3, employee.getEmail());
            st.setString(4, employee.getPhoneNumber());
            st.setDouble(5, employee.getSalary());
            st.setInt(6, employee.getDepartmentId());
            st.setInt(7, employee.getRoleId());
            st.setDate(8, new java.sql.Date(employee.getHireDate().getTime()));

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // me kta e krijojm update employee te ri tu perdor proceduren qe e kena bo ne SQL
    public boolean updateEmployee(Employee employee) {
        String sql = "{CALL sp_UpdateEmployee(?, ?, ?, ?,?, ?, ?, ?)}";

        try (Connection conn = DbConnection.connect();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, employee.getEmployeeId());
            st.setString(2,employee.getFirstName());
            st.setString(3,employee.getLastName());
            st.setString(4,employee.getEmail());
            st.setString(5,employee.getPhoneNumber());
            st.setDouble(6, employee.getSalary());
            st.setInt(7,employee.getDepartmentId());
            st.setInt(8,employee.getRoleId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // me kta e bojm delete employee  tu perdor proceduren qe e kena bo ne SQL

    public boolean deleteEmployee(int employeeId) {
        String sql = "{CALL sp_DeleteEmployee(?)}";

        try (Connection conn = DbConnection.connect();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, employeeId);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
            return true;
            }

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // me kta e bojm get employee by id

    public Employee getEmployeeById(int employeeId) {
        String sql = "{CALL sp_GetEmployeeById(?)}";

        try (Connection conn = DbConnection.connect();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, employeeId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Employee employee = new Employee();

                employee.setEmployeeId(rs.getInt("employeeId"));
                employee.setFirstName(rs.getString("firstName"));
                employee.setLastName(rs.getString("lastName"));
                employee.setEmail(rs.getString("email"));
                employee.setPhoneNumber(rs.getString("phoneNumber"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setDepartmentId(rs.getInt("departmentId"));
                employee.setRoleId(rs.getInt("roleId"));
                employee.setHireDate(rs.getDate("hireDate"));
                employee.setActive(rs.getBoolean("isActive"));
                employee.setCreatedDate(rs.getDate("createdDate"));
                employee.setDepartmentName(rs.getString("departmentName"));
                employee.setRoleName(rs.getString("roleName"));

                return employee;

            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    // get all employees

    public List<Employee> getAllEmployess() {
        List<Employee> employees = new ArrayList<>();
        String sql = "{CALL sp_GetAllEmployees()}";

        try (Connection conn = DbConnection.connect();
             PreparedStatement st = conn.prepareCall(sql);
             ResultSet rs = st.executeQuery()) {


            while (rs.next()) {
                Employee employee = new Employee();

                employee.setEmployeeId(rs.getInt("employeeId"));
                employee.setFirstName(rs.getString("firstName"));
                employee.setLastName(rs.getString("lastName"));
                employee.setEmail(rs.getString("email"));
                employee.setPhoneNumber(rs.getString("phoneNumber"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setDepartmentId(rs.getInt("departmentId"));
                employee.setRoleId(rs.getInt("roleId"));
                employee.setHireDate(rs.getDate("hireDate"));
                employee.setActive(rs.getBoolean("isActive"));
                employee.setCreatedDate(rs.getDate("createdDate"));
                employee.setDepartmentName(rs.getString("departmentName"));
                employee.setRoleName(rs.getString("roleName"));

               employees.add(employee);

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return employees;
    }

    }




