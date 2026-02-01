package org.example.employeemanagementsystem.dao;

import org.example.employeemanagementsystem.helper.DbConnection;
import org.example.employeemanagementsystem.model.User;

import java.sql.*;

public class UserDAO {

    // GET USER BY USERNAME
    public User getUserByUsername(String username) {
        String sql = "{CALL sp_GetUserByUsername(?)}";


        try (Connection conn = DbConnection.connect();
             PreparedStatement st = conn.prepareCall(sql)) {

            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                User user = new  User();
                user.setUserId(rs.getInt("userId"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));

                // HANDLING NULLABE EMPLOYEEID

                int employeeId = rs.getInt("employeeId");
                user.setEmployeeId(rs.wasNull() ? null : employeeId);

                user.setUserRoleId(rs.getInt("userRoleId"));
                user.setActive(rs.getBoolean("isActive"));
                user.setCreatedDate(rs.getDate("createdDate"));

                Timestamp lastLogin = rs.getTimestamp("lastLogin");
                user.setLastLogin(lastLogin);

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // update last login time

    public boolean updateLastLogin(int userId){
        String sql = "{CALL sp_UpdateLastLogin(?)}";

        try (Connection conn = DbConnection.connect();
             PreparedStatement st = conn.prepareCall(sql)) {

            st.setInt(1, userId);

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
}
