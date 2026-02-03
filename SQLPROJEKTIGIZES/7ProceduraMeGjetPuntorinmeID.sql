-- 4. Get Employee By ID
CREATE PROCEDURE sp_GetEmployeeById
    @employeeId INT
AS
BEGIN
    SELECT e.*, d.departmentName, r.roleName
    FROM Employee e
    JOIN Department d ON e.departmentId = d.departmentId
    JOIN Role r ON e.roleId = r.roleId
    WHERE e.employeeId = @employeeId;
END
GO