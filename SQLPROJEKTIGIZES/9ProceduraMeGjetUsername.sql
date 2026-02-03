-- 6. Get User By Username
CREATE PROCEDURE sp_GetUserByUsername
    @username NVARCHAR(50)
AS
BEGIN
    SELECT * FROM [User] WHERE username = @username;
END
GO