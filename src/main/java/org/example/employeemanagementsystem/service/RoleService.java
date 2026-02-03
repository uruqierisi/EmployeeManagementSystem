package org.example.employeemanagementsystem.service;

import org.example.employeemanagementsystem.dao.RoleDAO;
import org.example.employeemanagementsystem.model.Role;

import java.util.List;

public class RoleService {

    private RoleDAO roleDAO;

    public RoleService() {
        this.roleDAO = new RoleDAO();
    }

    // get all roles
    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    // get role by id

    public Role getRoleById(int roleId){
        return roleDAO.getRoleById(roleId);
    }

    // display all roles

    public void displayAllRoles(){
        List<Role> roles = getAllRoles();

        if (roles.isEmpty()) {
            System.out.println("No roles found!");
            return;
        }

        System.out.println("\n---- Available Roles ----");
        for(Role role : roles){
            System.out.println(role.toString());
        }
        System.out.println();
    }
}
