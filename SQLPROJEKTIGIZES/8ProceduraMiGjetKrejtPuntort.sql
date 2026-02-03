-- 5. Get All Employees
CREATE PROCEDURE sp_GetAllEmployees
AS
BEGIN
    SELECT e.*, d.departmentName, r.roleName
    FROM Employee e
    JOIN Department d ON e.departmentId = d.departmentId
    JOIN Role r ON e.roleId = r.roleId
    WHERE e.isActive = 1
    ORDER BY e.employeeId;
END
GO