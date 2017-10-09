CREATE TABLE [dbo].[Table]
(
	[customer_id] INT NOT NULL PRIMARY KEY IDENTITY, 
    [customer_name] NCHAR(20) NULL, 
    [customer_age] INT NULL, 
    [password1] NCHAR(20) NULL, 
    [password2] NCHAR(20) NULL
)
