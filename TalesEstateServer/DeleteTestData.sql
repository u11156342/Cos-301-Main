USE TalesEstate
GO

DECLARE @userid INT
DECLARE @amountid INT
DECLARE @plotid INT

SELECT @userid = UserCharacterID FROM UserCharacter
	WHERE UserCharacterName LIKE 'test character%'

SELECT @amountid = PlotAmount, @plotid = PlotID FROM Plot
	WHERE PlotOwnedBy = @userid
	
/*Delete data added while testing*/
DELETE FROM EventLog WHERE
	PlotID = @plotid
GO
/*********************************/

DECLARE @userid INT
DECLARE @amountid INT
DECLARE @plotid INT

SELECT @userid = UserCharacterID FROM UserCharacter
	WHERE UserCharacterName LIKE 'test character%'

SELECT @amountid = PlotAmount, @plotid = PlotID FROM Plot
	WHERE PlotOwnedBy = @userid

DELETE FROM BuildLog WHERE
	BuildLogPlotID = @plotid
	
DELETE FROM Plot WHERE
	PlotID = @plotid
	
DELETE FROM UserCharacter WHERE
	UserCharacterID = @userid
	
DELETE FROM Amount WHERE
	AmountID = @amountid
	

	
