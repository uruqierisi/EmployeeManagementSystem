-- 2. Update Employee
CREATE PROCEDURE sp_UpdateEmployee
    @employeeId INT,
    @firstName NVARCHAR(50),
    @lastName NVARCHAR(50),
    @email NVARCHAR(100),
    @phoneNumber NVARCHAR(15),
    @salary DECIMAL(10,2),
    @departmentId INT,
    @roleId INT
AS
BEGIN
    UPDATE Employee 
    SET firstName = @firstName,
        lastName = @lastName,
        email = @email,
        phoneNumber = @phoneNumber,
        salary = @salary,
        departmentId = @departmentId,
        roleId = @roleId
    WHERE employeeId = @employeeId;
END
GO