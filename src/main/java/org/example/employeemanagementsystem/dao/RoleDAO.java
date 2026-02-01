package org.example.employeemanagementsystem.dao;

import org.example.employeemanagementsystem.helper.DbConnection;
import org.example.employeemanagementsystem.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO {

    // get all roles

    public List<Role> getAllRoles() {
      List<Role> roles = new ArrayList<>();
      String sql = "{CALL sp_GetAllRoles()}";

      try (Connection conn = DbConnection.connect();
           PreparedStatement st = conn.prepareCall(sql);
           ResultSet rs = st.executeQuery()) {


          while (rs.next()){
              Role role = new Role();
              role.setRoleId(rs.getInt("roleId"));
              role.setRoleName(rs.getString("roleName"));
              role.setCreatedDate(rs.getDate("createdDate"));
              roles.add(role);
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return roles;
    }

    // get role by id

    public Role getRoleById(int roleId) {
      String sql = "SELECT * FROM Role WHERE roleId = ?";

        try (Connection conn = DbConnection.connect();
             PreparedStatement st = conn.prepareCall(sql)) {
                 st.setInt(1, roleId);
                 ResultSet rs = st.executeQuery();

                 if (rs.next()){
                     Role role = new Role();
                     role.setRoleId(rs.getInt("roleId"));
                     role.setRoleName(rs.getString("roleName"));
                     role.setDescription(rs.getString("description"));
                     role.setCreatedDate(rs.getDate("createdDate"));
                     return role;
                 }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
