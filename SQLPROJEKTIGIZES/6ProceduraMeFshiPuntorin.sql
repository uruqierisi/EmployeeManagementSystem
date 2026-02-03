-- 3. Delete Employee
CREATE PROCEDURE sp_DeleteEmployee
    @employeeId INT
AS
BEGIN
    DELETE FROM Employee WHERE employeeId = @employeeId;
END
GO