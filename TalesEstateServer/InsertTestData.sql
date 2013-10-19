USE TalesEstate
GO

DECLARE @userid VARCHAR(255)
DECLARE @username VARCHAR(255)
DECLARE @amountid INT
DECLARE @plotid INT
DECLARE @fullname VARCHAR(255)

INSERT INTO UserCharacter VALUES 
('test character&*&', 0, 'T35T-ID', 'T35T-ID2', 0)
SET @userid = @@IDENTITY

SELECT @username = UserCharacterName FROM UserCharacter WHERE
ProdUserID = 'T35T-ID'

SET @fullname = @username + '' + @userid

UPDATE UserCharacter SET
	UserCharacterName = @fullname
	WHERE UserCharacterID = @userid

INSERT INTO Amount VALUES (50,50,50)
SET @amountid = @@IDENTITY

INSERT INTO Plot VALUES 
(@userid, @amountid, 1, 1, '0,0,0;0,0,0;0,0,0;', '-1,-1,-1;-1,-1,-1;-1,-1,-1;',
	10, 10.1, 10, 20, 1, 1, 1, 1, 1, 1, 10, 'TEST0001', 'test estate', 1)
SET @plotid = @@IDENTITY

INSERT INTO BuildLog VALUES(@plotid, 7, '2013-10-10 00:00:00', 4, 1, 0)
        
INSERT INTO BuildLog VALUES(@plotid, 9, '2013-10-10 00:00:00', 4, 1, 0)
                
INSERT INTO PlotLog VALUES(@plotid, GETDATE(), 'test plot')

INSERT INTO CharacterLog VALUES(@userid, GETDATE(), 'test character log')