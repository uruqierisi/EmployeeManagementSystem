-- 8. Get All Departments
CREATE PROCEDURE sp_GetAllDepartments
AS
BEGIN
    SELECT * FROM Department ORDER BY departmentName;
END
GO