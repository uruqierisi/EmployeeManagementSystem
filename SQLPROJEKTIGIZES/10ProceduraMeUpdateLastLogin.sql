-- 7. Update Last Login
CREATE PROCEDURE sp_UpdateLastLogin
    @userId INT
AS
BEGIN
    UPDATE [User] SET lastLogin = GETDATE() WHERE userId = @userId;
END
GO