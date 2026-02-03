package org.example.employeemanagementsystem.ui;

import org.example.employeemanagementsystem.model.Employee;
import org.example.employeemanagementsystem.model.User;
import org.example.employeemanagementsystem.service.DepartmentService;
import org.example.employeemanagementsystem.service.EmployeeService;
import org.example.employeemanagementsystem.service.RoleService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainMenu {

    private User currentUser;
    private EmployeeService employeeService;
    private DepartmentService departmentService;
    private RoleService roleService;
    private Scanner scanner;

    public MainMenu(User user){
        this.currentUser = user;
        this.employeeService = new EmployeeService();
        this.departmentService = new DepartmentService();
        this.roleService = new RoleService();
    }

    // main menu-ja stilin e personalizuar nga Profi YllGashi

    public void display(Scanner scanner) {
        this.scanner = scanner;
        boolean running = true;

        while (running) {
            displayMenu();

            System.out.print("Select an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addEmployee();
                    break;
                case "2":
                    updateEmployee();
                    break;
                case "3":
                    viewAllEmployees();
                    break;
                case "4":
                    viewEmployeeDetails();
                    break;
                case "5":
                    if (currentUser.isAdmin()){
                        deleteEmployee();
                    } else{
                        viewDepartments();
                    }
                    break;
                case "6":
                    if (currentUser.isAdmin()){
                        viewDepartments();
                    } else{
                        running = false;
                        logout();
                    }
                    break;
                case "7":
                    if (currentUser.isAdmin()){
                        running = false;
                        logout();
                    } else{
                        System.out.println("Invalid option!");
                    }
                    break;
                default:
                    System.out.println("Invalid option! Please Try Again!");
            }

            if (running && !choice.equals("3") && !choice.equals("5") && !choice.equals("6")) {
                System.out.println("Press Enter to continue....");
                scanner.nextLine();
            }
        }
    }

    // menu options

    private void displayMenu(){
        System.out.println("|---------------------------------|");
        System.out.println("|          MAIN MENU              |");
        System.out.println("|---------------------------------|");
        System.out.println("Logged in as: " + currentUser.getUsername() + " (" + currentUser.getRoleType() + ")");
        System.out.println("___________________________________");
        System.out.println("1. Add Employee");
        System.out.println("2. Update Employee");
        System.out.println("3. View All Employees");
        System.out.println("4. View Employee Details");

        if (currentUser.isAdmin()){
            System.out.println("5. Delete Employee(Admin Only)");
            System.out.println("6. View Departments");
            System.out.println("7. Logout");
        } else{
            System.out.println("5. View Departments");
            System.out.println("6. Logout");
        }

        System.out.println("______________________________________");
    }

    // add new employee method

    private void addEmployee() {
        System.out.println("\n=== Add New Employee ===\n");

        try {
            System.out.print("Enter First Name: ");
            String firstName = scanner.nextLine().trim();

            System.out.print("Enter Last Name: ");
            String lastName = scanner.nextLine().trim();

            System.out.print("Enter Email: ");
            String email = scanner.nextLine().trim();

            System.out.print("Enter Phone Number: ");
            String phoneNumber = scanner.nextLine().trim();

            System.out.print("Enter Salary: ");
            double salary = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Enter Hire Date (yyyy-MM-dd): ");
            String dateStr = scanner.nextLine().trim();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date hireDate = sdf.parse(dateStr);

            // Display departments - USE SERVICE METHOD
            departmentService.displayAllDepartments();
            System.out.print("Select Department ID: ");
            int departmentId = Integer.parseInt(scanner.nextLine().trim());

            // Display roles - USE SERVICE METHOD
            roleService.displayAllRoles();
            System.out.print("Select Role ID: ");
            int roleId = Integer.parseInt(scanner.nextLine().trim());

            // Create employee object
            Employee employee = new Employee(firstName, lastName, email, phoneNumber,
                    salary, departmentId, roleId, hireDate, true);

            // Add employee
            employeeService.addEmployee(employee);

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format!");
        } catch (ParseException e) {
            System.out.println("Error: Invalid date format! Use yyyy-MM-dd");
        }
    }

    private void updateEmployee() {
        System.out.println("--- Update Employee ---");

        try{
            System.out.println("Enter Employee ID to update: ");
            int employeeId = Integer.parseInt(scanner.nextLine().trim());

            // e kqyr a o gjall qaj puntor
            Employee existingEmployee = employeeService.viewEmployeeById(employeeId);
            if (existingEmployee == null) {
                return;
            }

            System.out.println("Current Details: ");
            System.out.println(existingEmployee.toString());
            System.out.println("Enter new details(press Enter to keep current value)");

            System.out.print("First Name (" + existingEmployee.getFirstName() + "): ");
            String firstName = scanner.nextLine().trim();
            if (firstName.isEmpty()) firstName = existingEmployee.getFirstName();

            System.out.print("Last Name (" + existingEmployee.getLastName() + "): ");
            String lastName = scanner.nextLine().trim();
            if (lastName.isEmpty()) lastName = existingEmployee.getLastName();

            System.out.print("Email (" + existingEmployee.getEmail() + "): ");
            String email = scanner.nextLine().trim();
            if (email.isEmpty()) email = existingEmployee.getEmail();

            System.out.print("Phone Number (" + existingEmployee.getPhoneNumber() + "): ");
            String phoneNumber = scanner.nextLine().trim();
            if (phoneNumber.isEmpty()) phoneNumber = existingEmployee.getPhoneNumber();

            System.out.print("Salary (" + existingEmployee.getSalary() + "): ");
            String salaryStr = scanner.nextLine().trim();
            double salary = salaryStr.isEmpty() ? existingEmployee.getSalary() : Double.parseDouble(salaryStr);


            // display all departments
            departmentService.displayAllDepartments();
            System.out.print("Department ID (" + existingEmployee.getDepartmentId() + "): ");
            String deptStr = scanner.nextLine().trim();
            int departmentId = deptStr.isEmpty() ? existingEmployee.getDepartmentId() : Integer.parseInt(deptStr);

            // display roles
            roleService.displayAllRoles();
            System.out.print("Role ID (" + existingEmployee.getRoleId() + "): ");
            String roleStr = scanner.nextLine().trim();
            int roleId = roleStr.isEmpty() ? existingEmployee.getRoleId() : Integer.parseInt(roleStr);

            // update employee object
            existingEmployee.setFirstName(firstName);
            existingEmployee.setLastName(lastName);
            existingEmployee.setEmail(email);
            existingEmployee.setPhoneNumber(phoneNumber);
            existingEmployee.setSalary(salary);
            existingEmployee.setDepartmentId(departmentId);
            existingEmployee.setRoleId(roleId);

            // update ne databaze

            employeeService.updateEmployee(existingEmployee);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number Format!");
        }
    }

    // view all employees

    private void viewAllEmployees() {
        System.out.println("--- All Employees ---");

        List<Employee> employees = employeeService.viewAllEmployees();

        if (employees.isEmpty()){
            System.out.println("No employees found!");
            return;
        }

        System.out.println(String.format("%-5s %-20s %-30s %-20s %-15s %s",
                "ID", "Name", "Email", "Department", "Role", "Salary")); // mi rreshtu stringat
        System.out.println("--".repeat(110)); // 110 viza dalin

        for (Employee emp : employees) {
            String fullName = emp.getFirstName() + " " + emp.getLastName();
            System.out.println(String.format("%-5d %-20s %-30s %-20s %-15s %.2f",
                    emp.getEmployeeId(),
                    fullName,
                    emp.getEmail(),
                    emp.getDepartmentName(),
                    emp.getRoleName(),
                    emp.getSalary()));
        }

        System.out.println("Total Employees: " + employees.size());
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    // view employee details by id

    private void viewEmployeeDetails() {
        System.out.println("--- Employee Details ---");

        try{
            System.out.println("Enter Employee ID: ");
            int employeeId = Integer.parseInt(scanner.nextLine().trim());

            Employee employee = employeeService.viewEmployeeById(employeeId);

            if (employee != null) {
                System.out.println("---------------------------------");
                System.out.println("Employee ID: " + employee.getEmployeeId());
                System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName());
                System.out.println("Email: " + employee.getEmail());
                System.out.println("Phone: " + employee.getPhoneNumber());
                System.out.println("Department: " + employee.getDepartmentName());
                System.out.println("Role: " + employee.getRoleName());
                System.out.println("Salary: " + employee.getSalary());
                System.out.println("Hire Date: " + employee.getHireDate());
                System.out.println("Status: " + (employee.isActive() ? "Active" : "InActive"));
                System.out.println("---------------------------------");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid employee ID!");
        }
    }

    // delete employee admin access only

    private void deleteEmployee() {
        System.out.println("---- Delete Employee (Admin Only) ----");

        try {
            System.out.print("Enter Employee ID: ");
            int employeeId = Integer.parseInt(scanner.nextLine().trim());

            // show employee details first
            Employee employee = employeeService.viewEmployeeById(employeeId);
            if (employee == null) {
                return;
            }

            System.out.println("Employee to delete is: ");
            System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName());
            System.out.println("Department: " + employee.getDepartmentName());
            System.out.println("Role: " + employee.getRoleName());

            System.out.print("Are you sure you want to delete this employee? (yes/no)");
            String confirmation = scanner.nextLine().trim().toLowerCase();


            if (confirmation.equals("yes")) {
                employeeService.deleteEmployee(employeeId, currentUser);
            } else {
                System.out.println("Delete action cancelled");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid employee ID!");
        }
    }

    // VIEW ALL DEPARTMENTS

    private void viewDepartments() {
        departmentService.displayAllDepartments();
        roleService.displayAllRoles();
        System.out.println("Press Enter to continue....");
        scanner.nextLine();
    }

    // logout
    private void logout(){
        System.out.println("---- Logout ---");
        System.out.println("Goodbye, " + currentUser.getUsername() + "!");
    }
}



