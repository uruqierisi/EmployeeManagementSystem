USE EmployeeDB;
GO

-- 1. Create Employee
CREATE PROCEDURE sp_CreateEmployee
    @firstName NVARCHAR(50),
    @lastName NVARCHAR(50),
    @email NVARCHAR(100),
    @phoneNumber NVARCHAR(15),
    @salary DECIMAL(10,2),
    @departmentId INT,
    @roleId INT,
    @hireDate DATE
AS
BEGIN
    INSERT INTO Employee (firstName, lastName, email, phoneNumber, salary, departmentId, roleId, hireDate)
    VALUES (@firstName, @lastName, @email, @phoneNumber, @salary, @departmentId, @roleId, @hireDate);
    
    SELECT SCOPE_IDENTITY() AS NewEmployeeId;
END
GO