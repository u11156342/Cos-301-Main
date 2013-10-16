USE TalesEstate
GO

/*******************************************
 * Cynthia Brown
 *******************************************/
DECLARE @charid INT;
DECLARE @amountid INT;
DECLARE @plotid INT;

INSERT INTO UserCharacter (UserCharacterName, UserCharacterStatus, ProdUserID, ProdCharacterID)
	VALUES
	('Valentina Leonora Adela Costa', 0, 'CFC3572A-ACE5-4C77-9481-5DE31FBA1C76', '5EC2CD6A-3579-4263-8582-32F831CD416C')
	SET @charid = @@IDENTITY
	
INSERT INTO Amount (AmountPlatinum, AmountGold, AmountSilver)
	VALUES
	(0, 5, 95)
	SET @amountid = @@IDENTITY
	
INSERT INTO Plot (PlotOwnedBy, PlotAmount, PlotDuchy, PlotSize, PlotGroundArray, PlotBuildingArray, 
PlotHappiness, PlotMonthlyIncome, PlotWorkersUsed, PlotWorkerMax, PlotAcreExquisite, PlotAcreExquisiteMax, 
PlotAcreFine, PlotAcreFineMax, PlotAcrePoor, PlotAcrePoorMax, PlotDefenseValue, PlotEstateNumber, 
PlotEstateName)
	VALUES
	(@charid, @amountid, 2, 6, 
	'0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;', 
	'-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;', 
	22, 44.5, 102, 140, 0, 0, 1, 1, 4.5, 5, 20, 'SAK0001', '')
	SET @plotid = @@IDENTITY;

INSERT INTO Building (BuildingTypeID, PictureID, BuildingTypeOfIndustry, BuildingAvailabilityID,
	BuildingPrerequisiteID, BuildingCost, BuildingSetupCost, BuildingMonthlyIncome,
	BuildingWorkersNeeded, BuildingTimeToBuild, BuildingSizeRequired, BuildingHappiness, BuildingDefenseValue)
	VALUES
	(3, 5, 'Artisan (Weaver)', 11, 10, 25, 50, 2, 5, 1, 0.125, 2, 0),
	(3, 5, 'Artisan (Tailor)', 11, 10, 25, 50, 2, 5, 1, 0.125, 2, 0),
	(4, 5, 'Merchant (Silk)', 11, 10, 25, 25, 3, 4, 1, 0.125, 0, 0),
	(4, 5, 'Merchant (Tailor)', 11, 10, 25, 25, 3, 4, 1, 0.125, 0, 0),
	(4, 5, 'Merchant (Glass Art)', 11, 10, 25, 25, 3, 4, 1, 0.125, 0, 0),
	(3, 5, 'Artisan (Glassblower)', 11, 10, 25, 50, 2, 5 ,1, 0.125, 2, 0)

INSERT INTO BuildLog (BuildLogCharacterID, BuildLogPlotID, BuildLogBuildingID,
	BuildLogDateTimeBuilt, BuildLogTimeToComplete, BuildLogCompleted)
	VALUES
	(@charid, @plotid, 40, '2013-02-01 00:00:00', 4, 1),
	(@charid, @plotid, 40, '2013-03-01 00:00:00', 4, 1),
	(@charid, @plotid, 60, '2013-04-01 00:00:00', 1, 1),
	(@charid, @plotid, 61, '2013-04-01 00:00:00', 0, 1),
	(@charid, @plotid, 77, '2013-05-01 00:00:00', 1, 1),
	(@charid, @plotid, 78, '2013-05-01 00:00:00', 1, 1),
	(@charid, @plotid, 52, '2013-05-01 00:00:00', 1, 1),
	(@charid, @plotid, 70, '2013-05-01 00:00:00', 2, 1),
	(@charid, @plotid, 79, '2013-05-01 00:00:00', 1, 1),
	(@charid, @plotid, 80, '2013-05-01 00:00:00', 1, 1),
	(@charid, @plotid, 81, '2013-10-01 00:00:00', 1, 1),
	(@charid, @plotid, 54, '2013-10-01 00:00:00', 1, 1),
	(@charid, @plotid, 40, '2013-06-01 00:00:00', 4, 1),
	(@charid, @plotid, 40, '2013-06-01 00:00:00', 4, 1),
	(@charid, @plotid, 69, '2013-07-01 00:00:00', 1, 1),
	(@charid, @plotid, 38, '2013-08-01 00:00:00', 2, 1),
	(@charid, @plotid, 34, '2013-08-14 00:00:00', 2, 1),
	(@charid, @plotid, 82, '2013-09-01 00:00:00', 1, 1)

 /*add defense value modifier!*/
 /*add happiness percentages*/
 /****************************/
INSERT INTO EventLog (PlotID, EventLogName, EventLogDescription, EventLogDateAdded,
	EventLogEffectPlatinum, EventLogEffectGold, EventLogEffectSilver, EventLogEffectHappiness, 
	EventLogEffectIncome)
	VALUES
	(@plotid, 'Bandit raid', 'Loss of 1 week of build or 10 gold', '2013-02-28', 0, -10, 0, 0, 0),
	(@plotid, 'Rebuilding', 'Rebuilding', '2013-03-31', 0, 0, 0, 1, 0),
	(@plotid, 'Soldier training', 'Soldier training', '2013-03-31', 0, 0, 0, 0, 0),
	(@plotid, 'Novelty run', 'Novelty run', '2013-03-31', 0, 0, 0, 0, 15),
	(@plotid, 'Shady figures', 'Shady figures', '2013-03-31', 0, 0, 0, 0, 0),
	(@plotid, 'Wedding', 'Orders additional income', '2013-03-31', 0, 0, 0, 0, 5),
	(@plotid, 'Bandit raid', 'There is a bandit raid on the estate, but the soldiers see it off', '2013-03-31', 0, 0, 0, 0, 0),
	(@plotid, 'Post bandit raid', 'The bandit raid left the workers jumpy and they loose productivity', '2013-03-31', 0, 0, 0, 0, -10)

INSERT INTO IncomeLog (PlotID, IncomeValue, IncomeDateProcessed)
	VALUES
	(@plotid, -1.5, '2013-02-28'),
	(@plotid, 12, '2013-02-28'),
	(@plotid, -10, '2013-02-28'),
	(@plotid, -1.5, '2013-03-31'),
	(@plotid, 12, '2013-03-31'),
	(@plotid, 12, '2013-03-31'),
	(@plotid, -1.5, '2013-04-30'),
	(@plotid, 12, '2013-04-30'),
	(@plotid, 12, '2013-04-30'),
	(@plotid, -1.5, '2013-04-30'),
	(@plotid, -20, '2013-04-30'),
	(@plotid, -20, '2013-05-01'),
	(@plotid, -1.5, '2013-05-31'),
	(@plotid, 17.2, '2013-05-31'),
	(@plotid, 17.2, '2013-05-31'),
	(@plotid, -1.5, '2013-05-31'),
	(@plotid, 0, '2013-05-31'),
	(@plotid, -20, '2013-05-31'),
	(@plotid, 2.8, '2013-05-31'),
	(@plotid, 2.8, '2013-05-31'),
	(@plotid, 1.4, '2013-05-31'),
	(@plotid, -4, '2013-05-31'),
	(@plotid, -2, '2013-05-31'),
	(@plotid, 4.3, '2013-05-31'),
	(@plotid, 4.3, '2013-05-31'),
	(@plotid, -25, '2013-06-01'),
	(@plotid, -1.5, '2013-06-30'),
	(@plotid, 13.2, '2013-06-30'),
	(@plotid, 13.2, '2013-06-30'),
	(@plotid, -1.5, '2013-06-30'),
	(@plotid, 0, '2013-06-30'),
	(@plotid, -20, '2013-06-30'),
	(@plotid, 2.2, '2013-06-30'),
	(@plotid, 2.2, '2013-06-30'),
	(@plotid, 1.1, '2013-06-30'),
	(@plotid, -4, '2013-06-30'),
	(@plotid, -2, '2013-06-30'),
	(@plotid, 3.3, '2013-06-30'),
	(@plotid, 3.3, '2013-06-30'),
	(@plotid, -1.5, '2013-06-30'),
	(@plotid, 13.2, '2013-06-30'),
	(@plotid, 13.2, '2013-06-30'),
	(@plotid, -30, '2013-07-01'),
	(@plotid, -1.5, '2013-07-31'),
	(@plotid, 13.8, '2013-07-31'),
	(@plotid, 13.8, '2013-07-31'),
	(@plotid, -1.5, '2013-07-31'),
	(@plotid, 0, '2013-07-31'),
	(@plotid, -23, '2013-07-31'),
	(@plotid, 2.3, '2013-07-31'),
	(@plotid, 2.3, '2013-07-31'),
	(@plotid, 1.15, '2013-07-31'),
	(@plotid, -4, '2013-07-31'),
	(@plotid, -2.3, '2013-07-31'),
	(@plotid, 3.45, '2013-07-31'),
	(@plotid, 3.45, '2013-07-31'),
	(@plotid, -1.5, '2013-07-31'),
	(@plotid, 13.8, '2013-07-31'),
	(@plotid, 13.8, '2013-07-31'),
	(@plotid, -1.5, '2013-07-31'),
	(@plotid, 0, '2013-07-31'),
	(@plotid, -30, '2013-08-01'),
	(@plotid, -1.5, '2013-08-31'),
	(@plotid, 13.2, '2013-08-31'),
	(@plotid, 13.2, '2013-08-31'),
	(@plotid, -1.5, '2013-08-31'),
	(@plotid, 0, '2013-08-31'),
	(@plotid, -22, '2013-08-31'),
	(@plotid, 2.2, '2013-08-31'),
	(@plotid, 2.2, '2013-08-31'),
	(@plotid, 1.1, '2013-08-31'),
	(@plotid, -4, '2013-08-31'),
	(@plotid, -2.2, '2013-08-31'),
	(@plotid, 3.3, '2013-08-31'),
	(@plotid, 3.3, '2013-08-31'),
	(@plotid, -1.5, '2013-08-31'),
	(@plotid, 13.2, '2013-08-31'),
	(@plotid, 13.2, '2013-08-31'),
	(@plotid, -1.5, '2013-08-31'),
	(@plotid, 0, '2013-08-31'),
	(@plotid, -1.5, '2013-08-31'),
	(@plotid, 5.5, '2013-08-31'),
	(@plotid, 8.8, '2013-08-31'),
	(@plotid, -50, '2013-09-01'),
	(@plotid, -1.5, '2013-09-30'),
	(@plotid, 12, '2013-09-30'),
	(@plotid, 12, '2013-09-30'),
	(@plotid, -1.5, '2013-09-30'),
	(@plotid, 0, '2013-09-30'),
	(@plotid, -20, '2013-09-30'),
	(@plotid, 2, '2013-09-30'),
	(@plotid, 2, '2013-09-30'),
	(@plotid, 1, '2013-09-30'),
	(@plotid, -4, '2013-09-30'),
	(@plotid, -2, '2013-09-30'),
	(@plotid, 3, '2013-09-30'),
	(@plotid, 3, '2013-09-30'),
	(@plotid, -1.5, '2013-09-30'),
	(@plotid, 12, '2013-09-30'),
	(@plotid, 12, '2013-09-30'),
	(@plotid, -1.5, '2013-09-30'),
	(@plotid, 0, '2013-09-30'),
	(@plotid, -1.5, '2013-09-30'),
	(@plotid, 5, '2013-09-30'),
	(@plotid, 8, '2013-09-30'),
	(@plotid, 2, '2013-09-30'),
	(@plotid, -35, '2013-10-01')

/*******************************************
 * Christopher Luke Dean						
 *******************************************/
INSERT INTO UserCharacter (UserCharacterName, UserCharacterStatus, 
	ProdUserID, ProdCharacterID)
	VALUES
	('Jacomus Jonas', 0, '362F2E60-490D-45F0-8C00-3DBBE467BDC2', 'F33D73C2-9975-4189-9421-7EE100B870A0');
	SET @charid = @@IDENTITY
	
INSERT INTO Amount (AmountPlatinum, AmountGold, AmountSilver)
	VALUES
	(0, 25, 0)
	SET @amountid = @@IDENTITY

INSERT INTO Plot (PlotOwnedBy, PlotAmount, PlotDuchy, PlotSize, PlotGroundArray, PlotBuildingArray, 
PlotHappiness, PlotMonthlyIncome, PlotWorkersUsed, PlotWorkerMax, PlotAcreExquisite, PlotAcreExquisiteMax, 
PlotAcreFine, PlotAcreFineMax, PlotAcrePoor, PlotAcrePoorMax, PlotDefenseValue, PlotEstateNumber, 
PlotEstateName)
	VALUES
	(@charid, @amountid, 2, 2, 
	'0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;', 
	'-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;', 
	4, 25, 40, 40, 0, 0, 0, 0, 2, 2, 0, 'SAK0002', '')
	SET @plotid = @@IDENTITY;

INSERT INTO BuildLog (BuildLogCharacterID, BuildLogPlotID, BuildLogBuildingID,
	BuildLogDateTimeBuilt, BuildLogTimeToComplete, BuildLogCompleted)
	VALUES
	(@charid, @plotid, 39, '2013-05-01 00:00:00', 3, 1),
	(@charid, @plotid, 39, '2013-05-22 00:00:00', 3, 1),
	(@charid, @plotid, 39, '2013-09-16 00:00:00', 3, 1),
	(@charid, @plotid, 39, '2013-09-16 00:00:00', 3, 1)

/*Effect on status*/
/******************/

INSERT INTO EventLog (PlotID, EventLogName, EventLogDescription, EventLogDateAdded,
	EventLogEffectPlatinum, EventLogEffectGold, EventLogEffectSilver, EventLogEffectHappiness, 
	EventLogEffectIncome)
	VALUES
	(@plotid, 'Overworked peasants', 'Peasants feeling over worked due to wedding demand', '2013-06-01', 0, 0, 0, -4, -10),
	(@plotid, 'Rested peasants', 'Too much rest for the peasants', '2013-07-01', 0, 0, 0, 0, -10),
	(@plotid, 'Local knight', 'The local knight comes round and notices your peasants lazing about.  He doesnt seem impressed', '2013-08-01', 0, 0, 0, 0, 0),
	(@plotid, 'Rabble amoung peasants', 'Theres some rabble rousing amoung the peasants after the events in Svaerstein, but nothing except grumbles come of it', '2013-02-28', 0, 0, 0, -1, 0)
	
INSERT INTO IncomeLog (PlotID, IncomeValue, IncomeDateProcessed)
	VALUES
	(@plotid, -1.5, '2013-05-31'),	
	(@plotid, 7, '2013-05-31'),
	(@plotid, 7, '2013-05-31'),
	(@plotid, -1.5, '2013-06-30'),
	(@plotid, 6.3, '2013-06-30'),
	(@plotid, 6.3, '2013-06-30'),
	(@plotid, -1.5, '2013-07-31'),
	(@plotid, 6.3, '2013-07-31'),
	(@plotid, 6.3, '2013-07-31'),
	(@plotid, -1.5, '2013-08-31'),
	(@plotid, 7, '2013-08-31'),
	(@plotid, 7, '2013-08-31'),
	(@plotid, -47.2, '2013-09-01'),
	(@plotid, -1.5, '2013-10-30'),
	(@plotid, 7, '2013-10-30'),
	(@plotid, 7, '2013-10-30'),
	(@plotid, -1.5, '2013-10-30'),
	(@plotid, 7, '2013-10-30'),
	(@plotid, 7, '2013-10-30')
	
/*******************************************
 * Rean Lubbe
 *******************************************/
INSERT INTO UserCharacter (UserCharacterName, UserCharacterStatus, 
	ProdUserID, ProdCharacterID)
	VALUES
	('Aihre Thule', 0, 'DE47D939-8920-4CCB-B1D1-FE5FE54F2CC6', 'C20D672E-B2B0-45DE-84F1-EFDBBA139BDB');
	SET @charid = @@IDENTITY
	
INSERT INTO Amount (AmountPlatinum, AmountGold, AmountSilver)
	VALUES
	(0, 63, 5)
	SET @amountid = @@IDENTITY

INSERT INTO Plot (PlotOwnedBy, PlotAmount, PlotDuchy, PlotSize, PlotGroundArray, PlotBuildingArray, 
PlotHappiness, PlotMonthlyIncome, PlotWorkersUsed, PlotWorkerMax, PlotAcreExquisite, PlotAcreExquisiteMax, 
PlotAcreFine, PlotAcreFineMax, PlotAcrePoor, PlotAcrePoorMax, PlotDefenseValue, PlotEstateNumber, 
PlotEstateName)
	VALUES
	(@charid, @amountid, 2, 2, 
	'0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;', 
	'-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;', 
	2, 22, 25, 40, 0, 0, 0, 0, 1.125, 2, 0, 'SAK0003', '')
	SET @plotid = @@IDENTITY;

INSERT INTO BuildLog (BuildLogCharacterID, BuildLogPlotID, BuildLogBuildingID,
	BuildLogDateTimeBuilt, BuildLogTimeToComplete, BuildLogCompleted)
	VALUES
	(@charid, @plotid, 39, '2013-05-01 00:00:00', 3, 1),
	(@charid, @plotid, 40, '2013-05-01 00:00:00', 4, 1),
	(@charid, @plotid, 36, '2013-07-01 00:00:00', 2, 1)

INSERT INTO EventLog (PlotID, EventLogName, EventLogDescription, EventLogDateAdded,
	EventLogEffectPlatinum, EventLogEffectGold, EventLogEffectSilver, EventLogEffectHappiness, 
	EventLogEffectIncome)
	VALUES
	(@plotid, 'Boom trade', 'Boom trade due to wedding', '2013-06-01', 0, 0, 0, 0, 50),
	(@plotid, 'Overworked peasants', 'Overworked peasants', '2013-07-01', 0, 0, 0, 0, -10),
	(@plotid, 'Overworked peasants', 'Overworked peasants', '2013-08-01', 0, 0, 0, 0, -10),
	(@plotid, 'Overworked peasants', 'Overworked peasants', '2013-09-01', 0, 0, 0, 0, -10)
	
INSERT INTO IncomeLog (PlotID, IncomeValue, IncomeDateProcessed)
	VALUES
	(@plotid, -1.5, '2013-05-31'),
	(@plotid, 7, '2013-05-31'),
	(@plotid, 12, '2013-05-31'),
	(@plotid, -15.5, '2013-06-01'),
	(@plotid, -1.5, '2013-06-30'),
	(@plotid, 10.5, '2013-06-30'),
	(@plotid, 18, '2013-06-30'),
	(@plotid, -24, '2013-07-01'),
	(@plotid, -1.5, '2013-07-31'),
	(@plotid, 6.3, '2013-07-31'),
	(@plotid, 10.8, '2013-07-31'),
	(@plotid, -1.5, '2013-07-31'),
	(@plotid, 5.4, '2013-07-31'),
	(@plotid, -1.5, '2013-08-31'),
	(@plotid, 6.3, '2013-08-31'),
	(@plotid, 10.8, '2013-08-31'),
	(@plotid, -1.5, '2013-08-31'),
	(@plotid, 5.4, '2013-08-31'),
	(@plotid, -1.5, '2013-09-30'),
	(@plotid, 6.3, '2013-09-30'),
	(@plotid, 10.8, '2013-09-30'),
	(@plotid, -1.5, '2013-09-30'),
	(@plotid, 5.4, '2013-09-30')
	
 /*******************************************
 * Robin Meisal						
 *******************************************/
 	/*E8D36515-DDB2-4467-949D-C47072BC9F54 - PC
	C0BAB518-5BEF-4D33-9A4F-0F455E5F3CCB - charid 1 - invalid one. no userid
	A17024B5-BF5A-429A-8E90-4606D3215AB7 - charid 2*/
	
INSERT INTO UserCharacter (UserCharacterName, UserCharacterStatus, 
	ProdUserID, ProdCharacterID)
	VALUES
	('Sinarfin', 0, '61406C2A-E382-42A9-BFD9-837E204F9154', 'A17024B5-BF5A-429A-8E90-4606D3215AB7');
	SET @charid = @@IDENTITY
	
INSERT INTO Amount (AmountPlatinum, AmountGold, AmountSilver)
	VALUES
	(0, 17, 0)
	SET @amountid = @@IDENTITY

INSERT INTO Plot (PlotOwnedBy, PlotAmount, PlotDuchy, PlotSize, PlotGroundArray, PlotBuildingArray, 
PlotHappiness, PlotMonthlyIncome, PlotWorkersUsed, PlotWorkerMax, PlotAcreExquisite, PlotAcreExquisiteMax, 
PlotAcreFine, PlotAcreFineMax, PlotAcrePoor, PlotAcrePoorMax, PlotDefenseValue, PlotEstateNumber, 
PlotEstateName)
	VALUES
	(@charid, @amountid, 2, 1, 
	'0,0,0;0,0,0;0,0,0;', 
	'-1,-1,-1;-1,-1,-1;-1,-1,-1;', 
	2, 8.5, 10, 20, 0, 0, 0, 0, 0.5, 1, 0, 'SAK0004', '')
	SET @plotid = @@IDENTITY;
	
INSERT INTO BuildLog (BuildLogCharacterID, BuildLogPlotID, BuildLogBuildingID,
	BuildLogDateTimeBuilt, BuildLogTimeToComplete, BuildLogCompleted)
	VALUES
	(@charid, @plotid, 40, '2013-08-01 00:00:00', 4, 1)

INSERT INTO IncomeLog (PlotID, IncomeValue, IncomeDateProcessed)
	VALUES
	(@plotid, -1.5, '2013-08-31'),
	(@plotid, 10, '2013-08-31'),
	(@plotid, -1.5, '2013-09-30'),
	(@plotid, 10, '2013-09-30')
	
 /*******************************************
 * Ryan Young						
 *******************************************/
 /*defense modifier*/
 /*************************/
INSERT INTO UserCharacter (UserCharacterName, UserCharacterStatus, 
	ProdUserID, ProdCharacterID)
	VALUES
	('Antonello Clemente Graziano Atrucci', 0, 'B8D7A952-C7F9-40F7-A833-1733EC281CCD', 'CDBF3FAD-18C2-4B6C-BDE2-E8EC3D0933ED');
	SET @charid = @@IDENTITY
	
INSERT INTO Amount (AmountPlatinum, AmountGold, AmountSilver)
	VALUES
	(0, 19, 5)
	SET @amountid = @@IDENTITY
	
INSERT INTO Plot (PlotOwnedBy, PlotAmount, PlotDuchy, PlotSize, PlotGroundArray, PlotBuildingArray, 
PlotHappiness, PlotMonthlyIncome, PlotWorkersUsed, PlotWorkerMax, PlotAcreExquisite, PlotAcreExquisiteMax, 
PlotAcreFine, PlotAcreFineMax, PlotAcrePoor, PlotAcrePoorMax, PlotDefenseValue, PlotEstateNumber, 
PlotEstateName)
	VALUES
	(@charid, @amountid, 6, 2, 
	'0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;', 
	'-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;', 
	2, 10.5, 40, 60, 0, 0, 1, 1, 1, 1, 0, 'LAN0001', '')
	SET @plotid = @@IDENTITY;

INSERT INTO BuildLog (BuildLogCharacterID, BuildLogPlotID, BuildLogBuildingID,
	BuildLogDateTimeBuilt, BuildLogTimeToComplete, BuildLogCompleted)
	VALUES
	(@charid, @plotid, 12, '2013-05-01 00:00:00', 1, 1),
	(@charid, @plotid, 48, '2013-09-16 00:00:00', 4, 1)

INSERT INTO EventLog (PlotID, EventLogName, EventLogDescription, EventLogDateAdded,
	EventLogEffectPlatinum, EventLogEffectGold, EventLogEffectSilver, EventLogEffectHappiness, 
	EventLogEffectIncome)
	VALUES
	(@plotid, 'Peasants unhappy', 'Peasants desperatly unhappy after a hobling overseer speaks of slavery', '2013-07-01', 0, 0, 0, -8, 0),
	/*one below for 3 months - need defense modifier*/
	(@plotid, 'Sarr raid', 'There is a Sarr raid, but your peasants are so happy with you, they fight it off and a few volunteer to be militia.  Gain a +2 to defense for 6 months', '2013-08-01', 0, 0, 0, 0, 0),
	(@plotid, 'Sarr raid', 'There is a Sarr raid, but your peasants are so happy with you, they fight it off and a few volunteer to be militia.  Gain a +2 to defense for 6 months', '2013-09-01', 0, 0, 0, 0, 0),
	(@plotid, 'Sarr raid', 'There is a Sarr raid, but your peasants are so happy with you, they fight it off and a few volunteer to be militia.  Gain a +2 to defense for 6 months', '2013-10-01', 0, 0, 0, 0, 0),
	(@plotid, 'Grumbling and rabble', 'There is a bit of grumbling and rabble rousing after the events in Svaertstein, but nothing comes of the matter', '2013-08-01', 0, 0, 0, -1, 0)
	
INSERT INTO IncomeLog (PlotID, IncomeValue, IncomeDateProcessed)
	VALUES
	(@plotid, -1, '2013-05-31'),
	(@plotid, 4, '2013-05-31'),
	(@plotid, -1, '2013-06-30'),
	(@plotid, 4, '2013-06-30'),
	(@plotid, -2, '2013-07-31'),
	(@plotid, 4, '2013-07-31'),
	(@plotid, -2, '2013-08-31'),
	(@plotid, 4, '2013-08-31'),
	(@plotid, -2, '2013-09-30'),
	(@plotid, 4, '2013-09-30'),
	(@plotid, -2.5, '2013-09-30'),
	(@plotid, 10, '2013-09-30')
	
 /*******************************************
 * Douw Pretorius						
 *******************************************/
INSERT INTO UserCharacter (UserCharacterName, UserCharacterStatus, 
	ProdUserID, ProdCharacterID)
	VALUES
	('Duquesne', 0, 'BB2A3ECC-9835-4C21-AD1E-59223EFCF98B', '73559087-D8D3-4283-8578-48D2D776AF96');
	SET @charid = @@IDENTITY
	
INSERT INTO Amount (AmountPlatinum, AmountGold, AmountSilver)
	VALUES
	(0, 9, 5)
	SET @amountid = @@IDENTITY
	
INSERT INTO Plot (PlotOwnedBy, PlotAmount, PlotDuchy, PlotSize, PlotGroundArray, PlotBuildingArray, 
PlotHappiness, PlotMonthlyIncome, PlotWorkersUsed, PlotWorkerMax, PlotAcreExquisite, PlotAcreExquisiteMax, 
PlotAcreFine, PlotAcreFineMax, PlotAcrePoor, PlotAcrePoorMax, PlotDefenseValue, PlotEstateNumber, 
PlotEstateName)
	VALUES
	(@charid, @amountid, 6, 3, 
	'0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;', 
	'-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;', 
	1, 6.5, 36, 60, 0, 0, 0, 0, 2.125, 3, 0, 'LAN0002', '')
	SET @plotid = @@IDENTITY;
	
INSERT INTO BuildLog (BuildLogCharacterID, BuildLogPlotID, BuildLogBuildingID,
	BuildLogDateTimeBuilt, BuildLogTimeToComplete, BuildLogCompleted)
	VALUES
	(@charid, @plotid, 4, '2013-05-01 00:00:00', 2, 1),
	(@charid, @plotid, 73, '2013-09-01 00:00:00', 2, 1),
	(@charid, @plotid, 41, '2013-09-01 00:00:00', 2, 1),
	(@charid, @plotid, 36, '2013-09-01 00:00:00', 2, 1),
	(@charid, @plotid, 70, '2013-09-01 00:00:00', 2, 1)

INSERT INTO EventLog (PlotID, EventLogName, EventLogDescription, EventLogDateAdded,
	EventLogEffectPlatinum, EventLogEffectGold, EventLogEffectSilver, EventLogEffectHappiness, 
	EventLogEffectIncome)
	VALUES
	/*social modifier*/
	(@plotid, 'Baron displeased', 'Baron displeased with elves, take a social track hit', '2013-06-01', 0, 0, 0, 0, 0),
	(@plotid, 'Sarr raiders', 'Sarr raiders, protecting gypsies, damage to vinyard', '2013-07-01', 0, -5, 0, 0, 0),
	(@plotid, 'Tournament won', 'Your people hear of you winning another tournament and theyre pretty chuffed with "their" bravo', '2013-08-01', 0, 0, 0, 1, 0),
	(@plotid, 'Tournament won', 'Your people hear of you winning another tournament and theyre pretty chuffed with "their" bravo', '2013-09-01', 0, 0, 0, 1, 0),
	(@plotid, 'Tournament won', 'Your people hear of you winning another tournament and theyre pretty chuffed with "their" bravo', '2013-10-01', 0, 0, 0, 1, 0),
	/*Social modifier*/
	(@plotid, 'No one home', 'Your baron comes around to congratulate you, but no one is home except an elf and Kilo…', '2013-09-01', 0, 0, 0, 0, 0)
	
INSERT INTO IncomeLog (PlotID, IncomeValue, IncomeDateProcessed)
	VALUES
	(@plotid, -1, '2013-05-31'),
	(@plotid, 2.5, '2013-05-31'),
	(@plotid, -1, '2013-06-30'),
	(@plotid, 2.5, '2013-06-30'),
	(@plotid, -3, '2013-06-30'),
	(@plotid, -1, '2013-07-31'),
	(@plotid, 2.5, '2013-07-31'),
	(@plotid, -1, '2013-08-31'),
	(@plotid, 2.5, '2013-08-31'),
	(@plotid, -1, '2013-09-30'),
	(@plotid, 2.5, '2013-09-30'),
	(@plotid, -2, '2013-09-30'),
	(@plotid, -1, '2013-09-30'),
	(@plotid, 5, '2013-09-30'),
	(@plotid, 6, '2013-09-30'),
	(@plotid, -1, '2013-09-30'),
	(@plotid, -2, '2013-09-30')
	
 /*******************************************
 * Christiaan Hattingh						
 *******************************************/
INSERT INTO UserCharacter (UserCharacterName, UserCharacterStatus, 
	ProdUserID, ProdCharacterID)
	VALUES
	('Hemaraja Heti Himansh', 0, '48DEFE5D-364C-4CBC-8C86-EC4AF0FF5FA4', '3846EC00-CAB9-4C69-836A-3D6B7018D2C6');
	SET @charid = @@IDENTITY
	
INSERT INTO Amount (AmountPlatinum, AmountGold, AmountSilver)
	VALUES
	(0, 7, 44)
	SET @amountid = @@IDENTITY
	
INSERT INTO Plot (PlotOwnedBy, PlotAmount, PlotDuchy, PlotSize, PlotGroundArray, PlotBuildingArray, 
PlotHappiness, PlotMonthlyIncome, PlotWorkersUsed, PlotWorkerMax, PlotAcreExquisite, PlotAcreExquisiteMax, 
PlotAcreFine, PlotAcreFineMax, PlotAcrePoor, PlotAcrePoorMax, PlotDefenseValue, PlotEstateNumber, 
PlotEstateName)
	VALUES
	(@charid, @amountid, 5, 4, 
	'0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;', 
	'-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;', 
	1, 18.5, 35, 100, 0, 0, 1, 1, 2.375, 3, 0, 'ROT0001', '')
	SET @plotid = @@IDENTITY;

INSERT INTO Building (BuildingTypeID, PictureID, BuildingTypeOfIndustry, BuildingAvailabilityID,
	BuildingPrerequisiteID, BuildingCost, BuildingSetupCost, BuildingMonthlyIncome,
	BuildingWorkersNeeded, BuildingTimeToBuild, BuildingSizeRequired, BuildingHappiness, BuildingDefenseValue)
	VALUES
	(3, 5, 'Merchant (Alchemy)', 11, 10, 25, 25, 3, 4, 1, 0.125, 0, 0)
	/*(3, 5, 'Scavenger camp', 11, 10, 25, 25, 3, 4, 1, 0.125, 0, 0)*/
	
INSERT INTO BuildLog (BuildLogCharacterID, BuildLogPlotID, BuildLogBuildingID,
	BuildLogDateTimeBuilt, BuildLogTimeToComplete, BuildLogCompleted)
	VALUES
	/*Scavenger camp building details*/
	(@charid, @plotid, 31, '2013-03-01 00:00:00', 1, 1),
	(@charid, @plotid, 30, '2013-04-01 00:00:00', 1, 1),
	(@charid, @plotid, 36, '2013-06-01 00:00:00', 2, 1),
	(@charid, @plotid, 83, '2013-07-01 00:00:00', 1, 1),
	/*(@charid, @plotid, 4, '2013-06-01 00:00:00', , 1),*/
	/*(@charid, @plotid, 4, '2013-10-01 00:00:00', , 1),*/
	(@charid, @plotid, 36, '2013-10-01 00:00:00', 2, 1),
	(@charid, @plotid, 52, '2013-10-01 00:00:00', 1, 1)
	
INSERT INTO EventLog (PlotID, EventLogName, EventLogDescription, EventLogDateAdded,
	EventLogEffectPlatinum, EventLogEffectGold, EventLogEffectSilver, EventLogEffectHappiness, 
	EventLogEffectIncome)
	VALUES
	/*social modifier*/
	(@plotid, 'Cheap supplies', 'Cheap supplies', '2013-03-31', 0, 0, 0, 0, 6),
	(@plotid, 'Moral drops', 'Moral drops as Scavengers start filtering in', '2013-04-30', 0, 0, 0, -1, 0),
	(@plotid, 'Economic hit', 'Economic hit as Scavengers filter in', '2013-05-31', 0, 0, 0, 0, -50),
	(@plotid, 'Your people work harder', 'Your people realise that you do still care for them and put in a LOT of extra work', '2013-08-01', 0, 0, 0, 0, 10),
	(@plotid, 'Your people work harder', 'Your people realise that you do still care for them and put in a LOT of extra work', '2013-09-01', 0, 0, 0, 0, 10),
	(@plotid, 'Your people work harder', 'Your people realise that you do still care for them and put in a LOT of extra work', '2013-10-01', 0, 0, 0, 0, 10),
	(@plotid, 'Free loot', 'One of the Langzerund Sarr raids spill over into your lands, but are vehemently repulsed by Scavengers, who turn their loot over to you.  Your people very happy', '2013-09-01', 0, 20, 0, 0, 0)
	
INSERT INTO IncomeLog (PlotID, IncomeValue, IncomeDateProcessed)
	VALUES
	(@plotid, -2.5, '2013-03-31'),
	(@plotid, 2.1, '2013-03-31'),
	(@plotid, -2.5, '2013-04-30'),
	(@plotid, 2, '2013-04-30'),
	(@plotid, 3, '2013-04-30'),
	(@plotid, -2.5, '2013-05-31'),
	(@plotid, 1, '2013-05-31'),
	(@plotid, 1.5, '2013-05-31'),
	(@plotid, -2.5, '2013-06-30'),
	(@plotid, 2, '2013-06-30'),
	(@plotid, 3, '2013-06-30'),
	(@plotid, 3, '2013-06-30'),
	(@plotid, -1, '2013-06-30'),
	(@plotid, 1.1, '2013-06-30'),
	(@plotid, -2.5, '2013-07-31'),
	(@plotid, 2, '2013-07-31'),
	(@plotid, 6, '2013-07-31'),
	(@plotid, 3, '2013-07-31'),
	(@plotid, 3, '2013-07-31'),
	(@plotid, -1, '2013-07-31'),
	(@plotid, 1.7, '2013-07-31'),
	(@plotid, -5, '2013-08-15'),
	(@plotid, -2.5, '2013-08-31'),
	(@plotid, 2.2, '2013-08-31'),
	(@plotid, 6.6, '2013-08-31'),
	(@plotid, 3.3, '2013-08-31'),
	(@plotid, 3.3, '2013-08-31'),
	(@plotid, -1, '2013-08-31'),
	(@plotid, 1.87, '2013-08-31'),
	(@plotid, -2.5, '2013-09-30'),
	(@plotid, 2.2, '2013-09-30'),
	(@plotid, 6.6, '2013-09-30'),
	(@plotid, 3.3, '2013-09-30'),
	(@plotid, 3.3, '2013-09-30'),
	(@plotid, -1, '2013-09-30'),
	(@plotid, 1.87, '2013-09-30'),
	(@plotid, 20, '2013-09-30'),
	(@plotid, -55, '2013-10-01')
	
 /*******************************************
 * Yeshin Harisparid						
 *******************************************/
INSERT INTO UserCharacter (UserCharacterName, UserCharacterStatus, 
	ProdUserID, ProdCharacterID)
	VALUES
	('Ansem Siegfried', 0, '8C151ADE-BD2A-4F2B-852C-791E0A9D05B5', '00405D9B-D8F0-45AF-9166-BCAE781D07ED');
	SET @charid = @@IDENTITY
	
INSERT INTO Amount (AmountPlatinum, AmountGold, AmountSilver)
	VALUES
	(0, 30, 1)
	SET @amountid = @@IDENTITY
	
INSERT INTO Plot (PlotOwnedBy, PlotAmount, PlotDuchy, PlotSize, PlotGroundArray, PlotBuildingArray, 
PlotHappiness, PlotMonthlyIncome, PlotWorkersUsed, PlotWorkerMax, PlotAcreExquisite, PlotAcreExquisiteMax, 
PlotAcreFine, PlotAcreFineMax, PlotAcrePoor, PlotAcrePoorMax, PlotDefenseValue, PlotEstateNumber, 
PlotEstateName)
	VALUES
	(@charid, @amountid, 5, 4, 
	'0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;', 
	'-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;', 
	1, 30, 59, 80, 0, 0, 0, 0, 3.5, 4, 0, 'ROT0002', '')
	SET @plotid = @@IDENTITY;
	
INSERT INTO BuildLog (BuildLogCharacterID, BuildLogPlotID, BuildLogBuildingID,
	BuildLogDateTimeBuilt, BuildLogTimeToComplete, BuildLogCompleted)
	VALUES
	(@charid, @plotid, 2, '2013-02-01 00:00:00', 1, 1),
	(@charid, @plotid, 73, '2013-02-01 00:00:00', 2, 1),
	(@charid, @plotid, 47, '2013-03-01 00:00:00', 1, 1),
	(@charid, @plotid, 47, '2013-03-01 00:00:00', 1, 1),
	(@charid, @plotid, 52, '2013-02-01 00:00:00', 1, 1),
	(@charid, @plotid, 52, '2013-03-01 00:00:00', 1, 1),
	(@charid, @plotid, 47, '2013-03-01 00:00:00', 1, 1),
	(@charid, @plotid, 47, '2013-04-01 00:00:00', 1, 1),
	(@charid, @plotid, 63, '2013-05-01 00:00:00', 2, 1)
	
INSERT INTO EventLog (PlotID, EventLogName, EventLogDescription, EventLogDateAdded,
	EventLogEffectPlatinum, EventLogEffectGold, EventLogEffectSilver, EventLogEffectHappiness, 
	EventLogEffectIncome)
	VALUES
	/*social modifier*/
	(@plotid, 'Sheep sickness', 'Sickness amoung the sheep', '2013-02-28', 0, 0, 0, 0, -1),
	(@plotid, 'Replacement sheep', 'All the replacement sheep were pregnant', '2013-03-31', 0, 0, 0, 0, 1),
	(@plotid, 'Happiness bought', 'Happiness Bought', '2013-04-12', 0, 0, 0, 2, 0),
	(@plotid, 'Happiness boost', 'Happiness boost from money spent', '2013-04-30', 0, 0, 0, 2, 0),
	(@plotid, 'Economic boost', 'Economic Boost from fine skins', '2013-05-31', 0, 0, 0, 0, 10),
	(@plotid, 'Estate contentment', 'Settling down on estate contentment', '2013-07-01', 0, 0, 0, 1, 0),
	(@plotid, 'Estate contentment', 'Settling down on estate contentment', '2013-08-01', 0, 0, 0, 1, 0),
	(@plotid, 'Estate contentment', 'Settling down on estate contentment', '2013-09-01', 0, 0, 0, 1, 0),
	(@plotid, 'Happy people', 'Nothing much to report, people are still happy. Happiness +1 extended', '2013-10-01', 0, 0, 0, 1, 0)
	
INSERT INTO IncomeLog (PlotID, IncomeValue, IncomeDateProcessed)
	VALUES
	(@plotid, -1, '2013-02-28'),
	(@plotid, -1, '2013-02-28'),
	(@plotid, -1, '2013-02-28'),
	(@plotid, 2.9, '2013-02-28'),
	(@plotid, 0.9, '2013-02-28'),
	(@plotid, -2, '2013-02-28'),
	(@plotid, -1, '2013-03-31'),
	(@plotid, -1, '2013-03-31'),
	(@plotid, -1, '2013-03-31'),
	(@plotid, 3.1, '2013-03-31'),
	(@plotid, 1.1, '2013-03-31'),
	(@plotid, -2, '2013-03-31'),
	(@plotid, 1.1, '2013-03-31'),
	(@plotid, 8.3, '2013-03-31'),
	(@plotid, 8.3, '2013-03-31'),
	(@plotid, -1, '2013-04-30'),
	(@plotid, 3, '2013-04-30'),
	(@plotid, 8.3, '2013-03-31'),
	(@plotid, -20, '2013-04-01'),
	(@plotid, -1, '2013-04-30'),
	(@plotid, -1, '2013-04-30'),
	(@plotid, 1, '2013-04-30'),
	(@plotid, -2, '2013-04-30'),
	(@plotid, 1, '2013-04-30'),
	(@plotid, 8, '2013-04-30'),
	(@plotid, 8, '2013-04-30'),
	(@plotid, 8, '2013-04-30'),
	(@plotid, -1, '2013-04-30'),
	(@plotid, 8, '2013-04-30'),
	(@plotid, -20, '2013-05-01'),
	(@plotid, -1, '2013-05-31'),
	(@plotid, -1, '2013-05-31'),
	(@plotid, -1, '2013-05-31'),
	(@plotid, 3.3, '2013-05-31'),
	(@plotid, 1.1, '2013-05-31'),
	(@plotid, -2, '2013-05-31'),
	(@plotid, 1.1, '2013-05-31'),
	(@plotid, 8.8, '2013-05-31'),
	(@plotid, 8.8, '2013-05-31'),
	(@plotid, 8.8, '2013-05-31'),
	(@plotid, -1, '2013-05-31'),
	(@plotid, 8, '2013-05-31'),
	(@plotid, -1, '2013-05-31'),
	(@plotid, -1, '2013-06-30'),
	(@plotid, 3, '2013-06-30'),
	(@plotid, -1, '2013-06-30'),
	(@plotid, -2, '2013-06-30'),
	(@plotid, 8, '2013-06-30'),
	(@plotid, 8, '2013-06-30'),
	(@plotid, -1, '2013-06-30'),
	(@plotid, 1, '2013-06-30'),
	(@plotid, 1, '2013-06-30'),
	(@plotid, 8, '2013-06-30'),
	(@plotid, -1, '2013-06-30'),
	(@plotid, 8, '2013-06-30'),
	(@plotid, -1, '2013-06-30'),
	(@plotid, -1, '2013-07-31'),
	(@plotid, 3, '2013-07-31'),
	(@plotid, -1, '2013-07-31'),
	(@plotid, -2, '2013-07-31'),
	(@plotid, 8, '2013-07-31'),
	(@plotid, 8, '2013-07-31'),
	(@plotid, -1, '2013-07-31'),
	(@plotid, 1, '2013-07-31'),
	(@plotid, 1, '2013-07-31'),
	(@plotid, 8, '2013-07-31'),
	(@plotid, -1, '2013-07-31'),
	(@plotid, 8, '2013-07-31'),
	(@plotid, -1, '2013-07-31'),
	(@plotid, -1, '2013-08-31'),
	(@plotid, 3, '2013-08-31'),
	(@plotid, -1, '2013-08-31'),
	(@plotid, -2, '2013-08-31'),
	(@plotid, 8, '2013-08-31'),
	(@plotid, 8, '2013-08-31'),
	(@plotid, -1, '2013-08-31'),
	(@plotid, 1, '2013-08-31'),
	(@plotid, 1, '2013-08-31'),
	(@plotid, 8, '2013-08-31'),
	(@plotid, -1, '2013-08-31'),
	(@plotid, 8, '2013-08-31'),
	(@plotid, -1, '2013-08-31'),
	(@plotid, -137.8, '2013-09-01'),
	(@plotid, -1, '2013-09-30'),
	(@plotid, 3, '2013-09-30'),
	(@plotid, -1, '2013-09-30'),
	(@plotid, -2, '2013-09-30'),
	(@plotid, 8, '2013-09-30'),
	(@plotid, 8, '2013-09-30'),
	(@plotid, -1, '2013-09-30'),
	(@plotid, 1, '2013-09-30'),
	(@plotid, 1, '2013-09-30'),
	(@plotid, 8, '2013-09-30'),
	(@plotid, -1, '2013-09-30'),
	(@plotid, 8, '2013-09-30'),
	(@plotid, -1, '2013-09-30')
	
 /*******************************************
 * Mark-Anthony Fouche						
 *******************************************/
INSERT INTO UserCharacter (UserCharacterName, UserCharacterStatus, 
	ProdUserID, ProdCharacterID)
	VALUES
	('Lutz', 0, '52DE205B-01DE-463F-83B6-FBBB18E053CB', '68E92453-C8DD-4CD6-BA01-7CDCC020B7BB');
	SET @charid = @@IDENTITY
	
INSERT INTO Amount (AmountPlatinum, AmountGold, AmountSilver)
	VALUES
	(0, 0, 4)
	SET @amountid = @@IDENTITY
	
INSERT INTO Plot (PlotOwnedBy, PlotAmount, PlotDuchy, PlotSize, PlotGroundArray, PlotBuildingArray, 
PlotHappiness, PlotMonthlyIncome, PlotWorkersUsed, PlotWorkerMax, PlotAcreExquisite, PlotAcreExquisiteMax, 
PlotAcreFine, PlotAcreFineMax, PlotAcrePoor, PlotAcrePoorMax, PlotDefenseValue, PlotEstateNumber, 
PlotEstateName)
	VALUES
	(@charid, @amountid, 4, 2, 
	'0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;', 
	'-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;', 
	6, 17.4, 33, 40, 0, 0, 0, 0, 1, 2, 0, 'SVR0001', '')
	SET @plotid = @@IDENTITY;
	
INSERT INTO Building (BuildingTypeID, PictureID, BuildingTypeOfIndustry, BuildingAvailabilityID,
	BuildingPrerequisiteID, BuildingCost, BuildingSetupCost, BuildingMonthlyIncome,
	BuildingWorkersNeeded, BuildingTimeToBuild, BuildingSizeRequired, BuildingHappiness, BuildingDefenseValue)
	VALUES
	(3, 5, 'Artisan (Lithographer)', 11, 10, 25, 50, 2, 5, 1, 0.125, 2, 0)
	
INSERT INTO BuildLog (BuildLogCharacterID, BuildLogPlotID, BuildLogBuildingID,
	BuildLogDateTimeBuilt, BuildLogTimeToComplete, BuildLogCompleted)
	VALUES
	(@charid, @plotid, 63, '2013-05-01 00:00:00', 2, 1),
	(@charid, @plotid, 36, '2013-05-01 00:00:00', 2, 1),
	(@charid, @plotid, 36, '2013-06-01 00:00:00', 2, 1),
	(@charid, @plotid, 84, '2013-08-01 00:00:00', 1, 1),
	(@charid, @plotid, 63, '2013-05-01 00:00:00', 2, 1),
	(@charid, @plotid, 36, '2013-05-01 00:00:00', 2, 1),
	(@charid, @plotid, 52, '2013-06-01 00:00:00', 1, 1)
	
INSERT INTO EventLog (PlotID, EventLogName, EventLogDescription, EventLogDateAdded,
	EventLogEffectPlatinum, EventLogEffectGold, EventLogEffectSilver, EventLogEffectHappiness, 
	EventLogEffectIncome)
	VALUES
	/*social modifier*/
	(@plotid, 'Bump in profits', 'Bump in profits as peasants drink because of wedding news', '2013-06-01', 0, 0, 0, 0, 25),
	(@plotid, 'High sales', 'Sales remain high as wedding prep continues', '2013-07-01', 0, 0, 0, 0, 10),
	(@plotid, 'Wedding depression', 'Wedding depression sets in', '2013-08-01', 0, 0, 0, -1, 0),
	(@plotid, 'Care for your workers', 'Your care to your workers shows in sharp contrast due to Svaerstein general unhappiness. August penaltry wiped out 3 months early', '2013-09-01', 0, 0, 0, 0, 0)
	
INSERT INTO IncomeLog (PlotID, IncomeValue, IncomeDateProcessed)
	VALUES
	(@plotid, -0.8, '2013-05-31'),
	(@plotid, -1, '2013-05-31'),
	(@plotid, 6, '2013-05-31'),
	(@plotid, -0.8, '2013-05-31'),
	(@plotid, -1, '2013-05-31'),
	(@plotid, 6, '2013-05-31'),
	(@plotid, -8, '2013-06-01'),
	(@plotid, -0.8, '2013-06-30'),
	(@plotid, -1, '2013-06-30'),
	(@plotid, 7.5, '2013-06-30'),
	(@plotid, 7.5, '2013-06-30'),
	(@plotid, -0.8, '2013-06-30'),
	(@plotid, -1, '2013-06-30'),
	(@plotid, 7.5, '2013-06-30'),
	(@plotid, 1.25, '2013-06-30'),
	(@plotid, -0.8, '2013-07-31'),
	(@plotid, -1, '2013-07-31'),
	(@plotid, 6.6, '2013-07-31'),
	(@plotid, 6.6, '2013-07-31'),
	(@plotid, -0.8, '2013-07-31'),
	(@plotid, -1, '2013-07-31'),
	(@plotid, 6.6, '2013-07-31'),
	(@plotid, 1.1, '2013-07-31'),
	(@plotid, -37.85, '2013-08-01'),
	(@plotid, -0.8, '2013-08-31'),
	(@plotid, -1, '2013-08-31'),
	(@plotid, 6, '2013-08-31'),
	(@plotid, 6, '2013-08-31'),
	(@plotid, 2, '2013-08-31'),
	(@plotid, -0.8, '2013-08-31'),
	(@plotid, -1, '2013-08-31'),
	(@plotid, 6, '2013-08-31'),
	(@plotid, 1, '2013-08-31'),
	(@plotid, -17.4, '2013-09-01'),
	(@plotid, -0.8, '2013-09-30'),
	(@plotid, -1, '2013-09-30'),
	(@plotid, 6, '2013-09-30'),
	(@plotid, 6, '2013-09-30'),
	(@plotid, 2, '2013-09-30'),
	(@plotid, -0.8, '2013-09-30'),
	(@plotid, -1, '2013-09-30'),
	(@plotid, 6, '2013-09-30'),
	(@plotid, 1, '2013-09-30'),
	(@plotid, -17, '2013-10-01')
	
 /*******************************************
 * Angelique Venter						
 *******************************************/
INSERT INTO UserCharacter (UserCharacterName, UserCharacterStatus, 
	ProdUserID, ProdCharacterID)
	VALUES
	('Ylva Smedvagn', 0, '5C3A8142-62A9-461D-B45F-1C3505E08400', 'FBC8E7D1-2136-49E2-A58B-6EA970A68BD6');
	SET @charid = @@IDENTITY
	
INSERT INTO Amount (AmountPlatinum, AmountGold, AmountSilver)
	VALUES
	(0, 7, 4)
	SET @amountid = @@IDENTITY
	
INSERT INTO Plot (PlotOwnedBy, PlotAmount, PlotDuchy, PlotSize, PlotGroundArray, PlotBuildingArray, 
PlotHappiness, PlotMonthlyIncome, PlotWorkersUsed, PlotWorkerMax, PlotAcreExquisite, PlotAcreExquisiteMax, 
PlotAcreFine, PlotAcreFineMax, PlotAcrePoor, PlotAcrePoorMax, PlotDefenseValue, PlotEstateNumber, 
PlotEstateName)
	VALUES
	(@charid, @amountid, 4, 2, 
	'0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;0,0,0;', 
	'-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;-1,-1,-1;', 
	3, 7.4, 33, 40, 0, 0, 0, 0, 1.75, 2, 0, 'SVR0002', '')
	SET @plotid = @@IDENTITY;
	
INSERT INTO BuildLog (BuildLogCharacterID, BuildLogPlotID, BuildLogBuildingID,
	BuildLogDateTimeBuilt, BuildLogTimeToComplete, BuildLogCompleted)
	VALUES
	(@charid, @plotid, 11, '2013-09-16 00:00:00', 1, 1),
	(@charid, @plotid, 70, '2013-09-16 00:00:00', 2, 1),
	(@charid, @plotid, 52, '2013-09-16 00:00:00', 1, 1)

/*No events for this character on record*/
	
INSERT INTO IncomeLog (PlotID, IncomeValue, IncomeDateProcessed)
	VALUES
	(@plotid, -0.8, '2013-09-30'),
	(@plotid, 10, '2013-09-30'),
	(@plotid, -0.8, '2013-09-30'),
	(@plotid, -2, '2013-09-30'),
	(@plotid, 1, '2013-09-30')