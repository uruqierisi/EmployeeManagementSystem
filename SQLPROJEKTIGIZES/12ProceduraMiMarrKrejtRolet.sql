-- 9. Get All Roles
CREATE PROCEDURE sp_GetAllRoles
AS
BEGIN
    SELECT * FROM Role ORDER BY roleName;
END
GO