USE EmployeeDB;
GO

-- Table 1
CREATE TABLE Department (
    departmentId INT PRIMARY KEY IDENTITY(1,1),
    departmentName NVARCHAR(100) NOT NULL UNIQUE,
    location NVARCHAR(100),
    createdDate DATETIME DEFAULT GETDATE()
);
GO

-- Table 2
CREATE TABLE Role (
    roleId INT PRIMARY KEY IDENTITY(1,1),
    roleName NVARCHAR(50) NOT NULL UNIQUE,
    description NVARCHAR(200),
    createdDate DATETIME DEFAULT GETDATE()
);
GO

-- Table 3
CREATE TABLE Employee (
    employeeId INT PRIMARY KEY IDENTITY(1,1),
    firstName NVARCHAR(50) NOT NULL,
    lastName NVARCHAR(50) NOT NULL,
    email NVARCHAR(100) NOT NULL UNIQUE,
    phoneNumber NVARCHAR(15),
    salary DECIMAL(10,2) CHECK (salary >= 0),
    departmentId INT NOT NULL,
    roleId INT NOT NULL,
    hireDate DATE NOT NULL,
    isActive BIT DEFAULT 1,
    createdDate DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (departmentId) REFERENCES Department(departmentId),
    FOREIGN KEY (roleId) REFERENCES Role(roleId)
);
GO

-- Table 
CREATE TABLE [User] (
    userId INT PRIMARY KEY IDENTITY(1,1),
    username NVARCHAR(50) NOT NULL UNIQUE,
    password NVARCHAR(255) NOT NULL,
    employeeId INT,
    userRoleId INT NOT NULL DEFAULT 2,
    isActive BIT DEFAULT 1,
    createdDate DATETIME DEFAULT GETDATE(),
    lastLogin DATETIME,
    FOREIGN KEY (employeeId) REFERENCES Employee(employeeId),
    CHECK (userRoleId IN (1, 2)) -- 1=Admin, 2=User i thjesht
);
GO