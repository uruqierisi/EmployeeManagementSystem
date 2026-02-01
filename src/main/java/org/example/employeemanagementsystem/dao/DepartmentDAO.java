package org.example.employeemanagementsystem.dao;

import org.example.employeemanagementsystem.helper.DbConnection;
import org.example.employeemanagementsystem.model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

    // get all departments
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        String sql = "{CALL sp_GetAllDepartments()}";

        try (Connection conn = DbConnection.connect();
             PreparedStatement st = conn.prepareCall(sql);
             ResultSet rs = st.executeQuery()) {

                while (rs.next()){
                    Department dept = new Department();
                    dept.setDepartmentId(rs.getInt("departmentId"));
                    dept.setDepartmentName(rs.getString("departmentName"));
                    dept.setLocation(rs.getString("location"));
                    dept.setCreatedDate(rs.getDate("createdDate"));
                    departments.add(dept);
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departments;
    }

    // get department by id

    public Department getDepartmentById(int departmentId) {
     String sql = "SELECT * FROM Department WHERE DepartmentId = ?"; // kisha harru mi kriju procedur

        try (Connection conn = DbConnection.connect();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, departmentId);
            ResultSet rs = st.executeQuery();

            if (rs.next()){
                Department dept = new Department();
                dept.setDepartmentId(rs.getInt("departmentId"));
                dept.setDepartmentName(rs.getString("departmentName"));
                dept.setLocation(rs.getString("location"));
                dept.setCreatedDate(rs.getDate("createdDate"));
                return dept;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
