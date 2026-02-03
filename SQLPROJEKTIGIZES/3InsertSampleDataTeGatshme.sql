-- insert sample data prej chatgpt

USE EmployeeDB;
GO

-- Insert Departments
INSERT INTO Department (departmentName, location) VALUES
('IT Department', 'Building A'),
('HR Department', 'Building B'),
('Finance Department', 'Building C'),
('Marketing Department', 'Building D');
GO

-- Insert Roles
INSERT INTO Role (roleName, description) VALUES
('Developer', 'Software development and programming'),
('Manager', 'Team and project management'),
('Analyst', 'Business and data analysis'),
('HR Specialist', 'Human resources management');
GO

-- Insert Sample Employees
INSERT INTO Employee (firstName, lastName, email, phoneNumber, salary, departmentId, roleId, hireDate) VALUES
('Admin', 'User', 'admin@company.com', '555-0000', 80000, 1, 2, '2025-01-01'),
('John', 'Doe', 'john.doe@company.com', '555-0001', 60000, 1, 1, '2025-01-15'),
('Jane', 'Smith', 'jane.smith@company.com', '555-0002', 55000, 2, 4, '2025-02-01');
GO

-- Insert Users (Admin and Regular User)
INSERT INTO [User] (username, password, employeeId, userRoleId) VALUES
('admin', 'admin123', 1, 1),      -- Admin user
('john_doe', 'pass123', 2, 2);    -- Regular user
GO