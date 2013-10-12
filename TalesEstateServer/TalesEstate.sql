USE Master
GO
DROP DATABASE TalesEstate
GO
CREATE DATABASE TalesEstate
GO
USE TalesEstate
GO

CREATE TABLE Duchy
(
	DuchyID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	DuchyName VARCHAR(10),
)

INSERT INTO Duchy (DuchyName)
VALUES
('Thegnheim'),
('Sarkland'),
('Ragonvaldr'),
('Svaerstein'),
('Rotheim'),
('Langzerund')

CREATE TABLE Quality
(
	QualityID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	QualityDescription VARCHAR(9)
)

INSERT INTO Quality (QualityDescription)
VALUES
('Poor'),
('Fine'),
('Exquisite')

CREATE TABLE Amount
(
	AmountID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	AmountPlatinum INT,
	AmountGold INT,
	AmountSilver INT
)

INSERT INTO Amount (AmountPlatinum, AmountGold, AmountSilver)
VALUES
(0,40,0),
(0,2,0),
(0,100,0),
(0,5,0),
(0,200,0),
(0,10,0),
(0,30,0),
(0,1,5),
(0,80,0),
(0,4,0),
(0,150,0),
(0,7,5),
(0,15,0),
(0,0,8),
(0,35,0),
(0,1,8),
(0,70,0),
(0,3,5),
(0,15,0),
(0,0,8),
(0,35,0),
(0,1,8),
(0,70,0),
(0,3,5),
(0,20,0),
(0,1,0),
(0,50,0),
(0,2,5),
(0,100,0),
(0,5,0),
(0,20,0),
(0,1,0),
(0,50,0),
(0,2,5),
(0,100,0),
(0,5,0),
(0,0,0),
(0,0,0),
(0,0,0),
(0,0,0),
(0,0,0),
(0,0,0),
(0,0,0),
(0,0,0),
(0,0,0),
(0,0,0),
(0,0,0),
(0,0,0),
(0,0,0),
(0,0,0),
(0,0,0),
(0,0,0),
(0,0,0),
(0,0,0)

CREATE TABLE Price
(
	PriceID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	DuchyID INT FOREIGN KEY REFERENCES Duchy(DuchyID),
	QualityID INT FOREIGN KEY REFERENCES Quality(QualityID),
	PriceLand INT FOREIGN KEY REFERENCES Amount(AmountID),
	PriceMonthlyUpkeep INT FOREIGN KEY REFERENCES Amount(AmountID)
)

INSERT INTO Price (DuchyID, QualityID, PriceLand, PriceMonthlyUpkeep)
VALUES
(1,1,1,2),
(1,2,3,4),
(1,3,5,6),
(2,1,7,8),
(2,2,9,10),
(2,3,11,12),
(3,1,13,14),
(3,2,15,16),
(3,3,17,18),
(4,1,19,20),
(4,2,21,22),
(4,3,23,24),
(5,1,25,26),
(5,2,27,28),
(5,3,29,30),
(6,1,31,32),
(6,2,33,34),
(6,3,35,36)

CREATE TABLE BuildingType
(
	BuildingTypeID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	BuildingTypeDescription VARCHAR(13)
)

INSERT INTO BuildingType (BuildingTypeDescription)
VALUES
('Agricultural'),
('Mining'),
('Manufacturing'),
('Services'),
('Improvements')

CREATE TABLE BuildingAvailability
(
	BuildingAvailabilityID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	BuildingAvailabilityThegnheim BIT,
	BuildingAvailabilitySarkland BIT,
	BuildingAvailabilityRagonvaldr BIT,
	BuildingAvailabilitySvaerstein BIT,
	BuildingAvailabilityRotheim BIT,
	BuildingAvailabilityLangzerund BIT
)

INSERT INTO BuildingAvailability (BuildingAvailabilityThegnheim, BuildingAvailabilitySarkland,
BuildingAvailabilityRagonvaldr, BuildingAvailabilitySvaerstein, 
BuildingAvailabilityRotheim, BuildingAvailabilityLangzerund)
VALUES
(1,0,0,0,0,0), /*Thegnheim*/
(0,1,0,0,0,0), /*Sarkland*/
(0,0,1,0,0,0), /*Ragonvaldr*/
(0,0,0,1,0,0), /*Svaerstein*/
(0,0,0,0,1,0), /*Rotheim*/
(0,0,0,0,0,1), /*Langzerund*/
(0,0,1,0,1,0), /*Ragonvaldr, Rotheim*/
(0,1,0,1,0,0), /*Sarkland, Svaerstein*/
(0,0,1,0,0,1), /*Ragonvaldr, Langzerund*/
(0,1,1,0,0,0), /*Sarkland, Ragonvaldr*/
(1,1,1,1,1,1)  /*Anywhere*/

CREATE TABLE BuildingPrerequisite
(
	BuildingPrerequisiteID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	BuildingPrerequisiteDescription VARCHAR(45)
)

INSERT INTO BuildingPrerequisite (BuildingPrerequisiteDescription)
VALUES
('None'),
('Poor land'),
('Fine land'),
('Exquisite land'),
('Not Exquisite land'),
('Suitable water'),
('Near water'),
('Wild land'),
('Bay of Maresco'),
('Suitable Source material'),
('Iron mine'),
('Vineyard'),
('Appropriate Primary source'),
('Timber plantation'),
('Flour mill'),
('Livestock farm or hunters'),
('Barracks'),
('Inside walls')

CREATE TABLE Picture
(
	PictureID INT IDENTITY(0, 1) NOT NULL PRIMARY KEY,
	PictureDisplayHeight INT,
	PictureDisplayWidth INT,
	PictureXOffset INT,
	PictureYOffset INT,
	PictureImageName VARCHAR(100)
)

INSERT INTO Picture(PictureDisplayHeight, PictureDisplayWidth, PictureXOffset, PictureYOffset, PictureImageName)
VALUES
(0, 0, 0, 0, 'poor land 0.125.png'),
(0, 0, 0, 0, 'fine land 0.125.png'),
(0, 0, 0, 0, 'exquisite land 0.125.png'),
(0, 0, 0, 0, 'water 0.125.png'),
(0, 0, 0, 0, 'Arnhelm.jpg'),
(0, 0, 0, 0, 'Farm corn 0.125.png'),
(0, 0, 0, 0, 'CharacterSelect.png'),
(0, 0, 0, 0, 'BuyNew.png'),
(0, 0, 0, 0, 'MyProp.png'),
(0, 0, 0, 0, 'admin.png'),
(0, 0, 0, 0, 'Search.png'),
(0, 0, 0, 0, 'logo.png'),
(0, 0, 0, 0, 'PropertyManageTitle.png'),
(0, 0, 0, 0, 'Main.png'),
(0, 0, 0, 0, 'AdminMenu.png'),
(0, 0, 0, 0, 'background.jpg'),
(0, 0, 0, 0, 'exspand.png'),
(0, 0, 0, 0, 'play.jpg'),
(0, 0, 0, 0, 'SearchMenu.png'),
(0, 0, 0, 0, 'DoSearch.gif'),
(0, 0, 0, 0, 'view.png'),
(0, 0, 0, 0, 'Mine zinc 0.125.png'),
(0, 0, 0, 0, 'Mine quarry 0.125.png'),
(0, 0, 0, 0, 'Farm cattle 0.125.png'),
(0, 0, 0, 0, 'Farm rice 0.125.png'),
(0, 0, 0, 0, 'Farm sheep 0.125.png'),
(0, 0, 0, 0, 'Farm stud 0.125.png'),
(0, 0, 0, 0, 'Farm vegetable 0.125.png'),
(0, 0, 0, 0, 'Mine clay 0.125.png'),
(0, 0, 0, 0, 'Mine coal 0.125.png'),
(0, 0, 0, 0, 'Mine copper 0.125.png'),
(0, 0, 0, 0, 'Mine diamond 0.125.png'),
(0, 0, 0, 0, 'Mine emerald 0.125.png'),
(0, 0, 0, 0, 'Mine gold 0.125.png'),
(0, 0, 0, 0, 'Mine iron 0.125.png'),
(0, 0, 0, 0, 'Mine lead 0.125.png'),
(0, 0, 0, 0, 'Mine malechite 0.125.png'),
(0, 0, 0, 0, 'Mine ruby 0.125.png'),
(0, 0, 0, 0, 'Mine sapphire 0.125.png'),
(0, 0, 0, 0, 'Mine semi precious 0.125.png'),
(0, 0, 0, 0, 'Mine silver 0.125.png'),
(0, 0, 0, 0, 'stables 0.5.png'),
(0, 0, 0, 0, 'timber plantation 0.125.png'),
(0, 0, 0, 0, 'Vineyard 0.125.png'),
(0, 0, 0, 0, 'playerManagement.png')



/*All cost related values assumed to be gold*/
CREATE TABLE Building
(
	BuildingID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	BuildingTypeID INT FOREIGN KEY REFERENCES BuildingType(BuildingTypeID),
	PictureID INT FOREIGN KEY REFERENCES Picture(PictureID),
	BuildingTypeOfIndustry VARCHAR(60),
	BuildingAvailabilityID INT FOREIGN KEY REFERENCES BuildingAvailability(BuildingAvailabilityID),
	BuildingPrerequisiteID INT FOREIGN KEY REFERENCES BuildingPrerequisite(BuildingPrerequisiteID),
	BuildingCost INT,				/*Gold Crowns*/
	BuildingSetupCost INT,			/*Gold Crowns*/
	BuildingMonthlyIncome FLOAT,	/*Gold Crowns*/
	BuildingWorkersNeeded INT,	
	BuildingTimeToBuild INT,		/*In weeks*/
	BuildingSizeRequired FLOAT,		/*In acres*/
	BuildingHappiness INT,			/*Positive, or negative*/
	BuildingDefenseValue FLOAT
)

INSERT INTO Building (BuildingTypeID, PictureID, BuildingTypeOfIndustry, BuildingAvailabilityID,
BuildingPrerequisiteID, BuildingCost, BuildingSetupCost, BuildingMonthlyIncome,
BuildingWorkersNeeded, BuildingTimeToBuild, BuildingSizeRequired, BuildingHappiness, BuildingDefenseValue)
VALUES
(1, 23, 'Farm (Cattle)(Dairy and Meat)',							1,	1,	25,		50,		4,		10,	1,	1,		0,	0),
(1, 25, 'Farm (Sheep)',												7,	1,	25,		25,		3,		10,	1,	1,		0,	0),
(1, 24, 'Farm (Grain)(Rice, Maize)',								8,	3,	25,		25,		3.5,	10,	2,	1,		0,	0),
(1, 43, 'Vineyard',													6,	5,	25,		25,		2.5,	10,	2,	1,		0,	0),
(1, 27, 'Farm (Vegetable)',											11,	1,	20,		20,		2,		10,	1,	1,		0,	0),
(1, 26, 'Farm (Work Animals)(Dray horses, Oxen, Bison etc.)',		11,	1,	25,		25,		3,		10,	1,	1,		0,	0),
(1, 5, 'Fishing Operation',											11,	6,	50,		50,		5,		20,	2,	1,		0,	0),
(1, 5, 'Hunting Operation',											11,	8,	25,		25,		2.5,	5,	1,	1,		0,	0),
(1, 5, 'Orchards (Fruit)',											11,	3,	25,		50,		6,		15,	2,	1,		0,	0),
(1, 5, 'Plantations (Tea, Coffee, Cocoa, Spices)',					2,	4,	25,		100,	12,		20,	4,	1,		0,	0),
(1, 41, 'Horse Stud Farm',											11,	1,	50,		125,	10,		20,	1,	1,		0,	0),
(1, 42, 'Timber Plantations',										11,	1,	25,		50,		4,		10,	1,	1,		0,	0),

(2, 34, 'Mining Operation (Iron)',									5,	1,	75,		50,		8,		15,	2,	1,		0,	0),
(2, 30, 'Mining Operation (Copper)',								9,	1,	75,		50,		8,		15,	2,	1,		0,	0),
(2, 40, 'Mining Operation (Silver)',								3,	1,	75,		75,		10,		20,	3,	1,		0,	0),
(2, 33, 'Mining Operation (Gold)',									3,	1,	100,	100,	13,		20,	3,	1,		0,	0),
(2, 35, 'Mining Operation (Lead)',									3,	1,	50,		50,		6,		15,	2,	1,		0,	0),
(2, 21, 'Mining Operation (Zinc)',									3,	1,	50,		50,		5,		15,	2,	1,		0,	0),
(2, 29, 'Mining Operation (Coal)',									4,	1,	50,		25,		3,		15,	2,	1,		0,	0),
(2, 5, 'Oil Refinery (Pitch, Lamp oil, Trade with Hoblings)',		1,	9,	100,	75,		10,		25,	4,	1,		0,	0),
(2, 22, 'Quarry',													1,	1,	25,		25,		2,		15,	1,	1,		0,	0),
(2, 31, 'Mining Operation (Diamond)',								5,	1,	100,	150,	15,		30,	4,	1,		0,	0),
(2, 36, 'Mining Operation (Malachite)',								6,	1,	50,		50,		5,		15,	2,	1,		0,	0),
(2, 37, 'Mining Operation (Ruby)',									3,	1,	75,		75,		8,		20,	3,	1,		0,	0),
(2, 38, 'Mining Operation (Sapphire)',								3,	1,	75,		75,		8,		20,	3,	1,		0,	0),
(2, 32, 'Mining Operation (Emerald)',								5,	1,	75,		75,		8,		20,	3,	1,		0,	0),
(2, 39, 'Mining Operation (Semi Precious)',							11,	1,	50,		50,		3,		15,	2,	1,		0,	0),
(2, 28, 'Clay',														2,	1,	25,		25,		2,		10,	1,	1,		0,	0),

(3, 5, 'Artisan (Specify)',											11,	10,	25,		50,		2,		5,	1,	0.125,	2,	0),
(3, 5, 'Blacksmith',												11,	1,	25,		50,		3,		8,	1,	0.25,	1,	0),
(3, 5, 'Brewery',													11,	1,	50,		50,		2,		8,	1,	0.5,	3,	0),
(3, 5, 'Docks/Piers',												11,	6,	100,	50,		7.5,	15,	2,	0.5,	-1,	0),
(3, 5, 'Forge',														11,	11,	50,		50,		5,		10,	2,	0.5,	0,	0),
(3, 5, 'Laboratory (Potions)',										11,	1,	25,		75,		8,		5,	2,	0.125,	-2,	0),
(3, 5, 'Laboratory (Scrolls)',										11,	1,	25,		75,		8,		5,	2,	0.125,	-2,	0),
(3, 5, 'Laboratory (Alchemy)',										11,	1,	25,		50,		6,		5,	2,	0.125,	-1,	0),
(3, 5, 'Laboratory (Traps)',										11,	1,	25,		50,		6,		5,	2,	0.125,	-1,	0),
(3, 5, 'Glassworks',												10,	1,	50,		50,		5,		10,	2,	0.25,	1,	0),
(3, 5, 'Silkworms (Caterpillar)',									2,	1,	50,		50,		7,		10,	3,	0.5,	1,	0),
(3, 5, 'Silkworms (Spiders)',										2,	1,	50,		125,	12,		10,	4,	0.5,	2,	0),
(3, 5, 'Winery',													11,	12,	75,		75,		5,		10,	2,	0.5,	1,	0),
(3, 5, 'Distillery',												11,	13,	75,		75,		6,		10,	2,	0.5,	0,	0),
(3, 5, 'Sawmill',													11,	14,	50,		25,		4,		15,	2,	0.5,	0,	0),
(3, 5, 'Flour mill',												11,	7,	25,		25,		2,		5,	1,	0.5,	1,	0),
(3, 5, 'Bakery',													11,	15,	25,		25,		1,		4,	1,	0.125,	2,	0),
(3, 5, 'Butcher',													11,	16,	25,		25,		1,		4,	1,	0.125,	2,	0),
(3, 5, 'Tannery',													11,	16,	25,		50,		8,		8,	1,	0.5,	-2,	0),
(3, 5, 'Shipyard',													11,	6,	75,		75,		10,		30,	4,	1,		0,	0),

(4, 5, 'Merchant (Specify)',										11,	10,	25,		25,		3,		4,	1,	0.125,	0,	0),
(4, 5, 'Inn',														11,	1,	50,		50,		6,		10,	1,	0.5,	0,	0),
(4, 5, 'Stables',													11,	1,	25,		25,		2,		6,	1,	0.5,	0,	0),
(4, 5, 'Tavern',													11,	1,	25,		25,		1,		5,	1,	0.25,	3,	0),
(4, 5, 'Barber',													11,	1,	25,		25,		2,		3,	1,	0.125,	0,	0),
(4, 5, 'Guild (Bards)',												11,	1,	25,		10,		1,		5,	1,	0.125,	3,	0),
(4, 5, 'Build (Duelist)',											11,	1,	50,		25,		3,		5,	2,	0.5,	0,	0),
(4, 5, 'Guild (Healers)',											11,	1,	50,		50,		5,		5,	2,	0.25,	-1,	0),
(4, 5, 'Guild (Mages)',												11,	1,	50,		50,		5,		5,	2,	0.25,	-2,	0),
(4, 5, 'Guild (Smiths)',											11,	1,	50,		25,		3,		8,	2,	0.5,	1,	0),
(4, 5, 'Guild (Alchemists)',										11,	1,	50,		25,		3,		5,	2,	0.5,	0,	0),

(5, 5, 'Barracks (Sleeps 20)',										11,	1,	50,		0,		0,		0,	1,	0.5,	0,	0),
(5, 5, 'Soldiers (20)',												11,	17,	0,		0,		-20,	0,	0,	0,		2,	0),
(5, 5, 'Military Outpost (Sleeps 40)',								11,	1,	75,		0,		0,		0,	2,	1,		0,	0),
(5, 5, 'Roads (Infrastructure per existing acre)',					11,	1,	20,		0,		-1,		4,	2,	0,		2,	0),
(5, 5, 'Educational Academy (Specify)',								11,	1,	75,		50,		2,		10,	2,	0.5,	4,	0),
(5, 5, 'School',													11,	1,	25,		25,		0,		5,	1,	0.5,	1,	0),
(5, 5, 'Village Hall (50)',											11,	1,	50,		30,		1,		8,	2,	0.25,	3,	0),
(5, 5, 'Town Hall (100)',											11,	1,	75,		30,		2,		10,	2,	0.5,	5,	0),
(5, 5, 'Marketplace',												11,	1,	10,		10,		0,		4,	1,	0.5,	2,	0),
(5, 5, 'Graveyard',													11,	1,	25,		25,		0,		2,	1,	1,		1,	0),
(5, 5, 'Manor House',												11,	1,	50,		0,		-2,		8,	2,	0.5,	0,	0),
(5, 5, 'Summer House',												11,	1,	25,		0,		-2,		5,	2,	0.5,	0,	0),
(5, 5, 'Protected Well',											11,	18,	25,		0,		0,		0,	1,	0,		0,	0),
(5, 5, 'Walls (6" Wood)',											11,	1,	50,		0,		-2,		3,	2,	0,		1,	0),
(5, 5, 'Walls (10" Wood)',											11,	1,	100,	0,		-4,		5,	2,	0,		1,	0),
(5, 5, 'Walls (6" Stone)',											11,	1,	100,	0,		-5,		5,	4,	0,		2,	0),
(5, 5, 'Walls (10" Stone)',											11,	1,	200,	0,		-8,		6,	4,	0,		3,	0)

CREATE TABLE UserCharacter
(
	UserCharacterID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	UserCharacterName NVARCHAR(MAX),
	UserCharacterAmount INT FOREIGN KEY REFERENCES Amount(AmountID),
	UserCharacterStatus INT
)

INSERT INTO UserCharacter(UserCharacterName, UserCharacterAmount,UserCharacterStatus)
VALUES
('Gilthana', 48, 0),
('Fiorella de Luca', 49, 0),
('QR Character', 50, 0),
('ABCharacter', 51, 0),
('Saachi Namasri', 52, 0),
('Test123', 53, 0),
('Test 123', 54, 0)

CREATE TABLE Plot
(
	PlotID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	PlotOwnedBy INT FOREIGN KEY REFERENCES UserCharacter(UserCharacterID),
	PlotAmount INT FOREIGN KEY REFERENCES Amount(AmountID),
	PlotDuchy INT FOREIGN KEY REFERENCES Duchy(DuchyID),
	PlotSize INT,
	PlotGroundArray NVARCHAR(MAX),
	PlotBuildingArray NVARCHAR(MAX),
	PlotHappiness INT,
	PlotMonthlyIncome FLOAT,
	PlotWorkersUsed INT,
	PlotWorkerMax INT,
	PlotAcreExquisite FLOAT,
	PlotAcreExquisiteMax INT,
	PlotAcreFine FLOAT,
	PlotAcreFineMax INT,
	PlotAcrePoor FLOAT,
	PlotAcrePoorMax INT,
	PlotDefenseValue FLOAT,
	PlotEstateNumber VARCHAR(20),
	PlotEstateName VARCHAR(50),
	Baronie VARCHAR(20)
)

INSERT INTO Plot(PlotOwnedBy, PlotAmount, PlotDuchy, PlotSize, PlotGroundArray, PlotBuildingArray, 
PlotHappiness, PlotMonthlyIncome, PlotWorkersUsed, PlotWorkerMax, PlotAcreExquisite, PlotAcreExquisiteMax, 
PlotAcreFine, PlotAcreFineMax, PlotAcrePoor, PlotAcrePoorMax, PlotDefenseValue, PlotEstateNumber, 
PlotEstateName)
VALUES
(3, 37, 5, 3, '0,0,0;0,0,0;0,0,0;', '-1,-1,-1;-1,-1,-1;-1,-1,-1;', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '', ''),
(3, 38, 4, 3, '0,0,0;0,0,0;0,0,0;', '-1,-1,-1;-1,-1,-1;-1,-1,-1;', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '', ''),
(3, 39, 4, 3, '0,0,0;0,0,0;0,0,0;', '-1,-1,-1;-1,-1,-1;-1,-1,-1;', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '', ''),
(3, 40, 4, 3, '0,0,0;0,0,0;0,0,0;', '-1,-1,-1;-1,-1,-1;-1,-1,-1;', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '', ''),
(3, 41, 6, 3, '0,0,0;0,0,0;0,0,0;', '-1,-1,-1;-1,-1,-1;-1,-1,-1;', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '', ''),
(3, 42, 6, 3, '0,0,0;0,0,0;0,0,0;', '-1,-1,-1;-1,-1,-1;-1,-1,-1;', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '', ''),
(4, 43, 4, 3, '0,0,0;0,0,0;0,0,0;', '-1,-1,-1;-1,-1,-1;-1,-1,-1;', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '', ''),
(4, 44, 4, 3, '0,0,0;0,0,0;0,0,0;', '-1,-1,-1;-1,-1,-1;-1,-1,-1;', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '', ''),
(4, 45, 4, 3, '0,0,0;0,0,0;0,0,0;', '-1,-1,-1;-1,-1,-1;-1,-1,-1;', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '', ''),
(1, 46, 1, 3, '0,0,0;0,0,0;0,0,0;', '-1,-1,-1;-1,-1,-1;-1,-1,-1;', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '', ''),
(1, 47, 1, 3, '0,0,0;0,0,0;0,0,0;', '-1,-1,-1;-1,-1,-1;-1,-1,-1;', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '', '')

CREATE TABLE Tile
(
	TileID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	PictureID INT FOREIGN KEY REFERENCES Picture(PictureID),
	TileDescription VARCHAR(100)
)

INSERT INTO Tile(PictureID, TileDescription)
VALUES
(0, 'Poor'),
(1, 'Fine'),
(2, 'Exquisite'),
(3, 'Water'),
(5, 'World map')

CREATE TABLE BuildLog
(
	BuildLogID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	BuildLogCharacterID INT FOREIGN KEY REFERENCES UserCharacter(UserCharacterID),
	BuildLogPlotID INT FOREIGN KEY REFERENCES Plot(PlotID),
	BuildLogBuildingID INT FOREIGN KEY REFERENCES Building(BuildingID),
	BuildLogDateTimeBuilt DATETIME,
	BuildLogTimeToComplete INT,
	BuildLogCompleted BIT
)

CREATE TABLE PlotAccess
(
	PlotAccessID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	PlotID INT FOREIGN KEY REFERENCES Plot(PlotID),
	UserCharacterID INT FOREIGN KEY REFERENCES UserCharacter(UserCharacterID),
	PlotAccessDeposit BIT,
	PlotAccessWithdraw BIT,
	PlotAccessBuy BIT,
	PlotAccessPlace BIT,
	PlotAccessExpand BIT,
	PlotAccessStatus BIT
)

CREATE TABLE EventLog
(
	EventLogID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	PlotID INT FOREIGN KEY REFERENCES Plot(PlotID),
	EventLogName VARCHAR(50),
	EventLogDescription VARCHAR(MAX),
	EventLogDateAdded DATE,
	EventLogEffectPlatinum INT,
	EventLogEffectGold INT,
	EventLogEffectSilver INT,
	EventLogEffectHappiness INT,
	EventLogEffectIncome INT
)

CREATE TABLE IncomeLog
(
	IncomeLogID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	PlotID INT FOREIGN KEY REFERENCES Plot(PlotID),
	IncomeValue FLOAT,
	IncomeDateProcessed DATE
)

CREATE TABLE EventTriggerLog
(
	EventTriggerLogID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	EventLogID INT FOREIGN KEY REFERENCES EventLog(EventLogID),
	PlotID INT FOREIGN KEY REFERENCES Plot(PlotID),
	EventTriggerLogDateProcessed DATE
)

CREATE TABLE PlotLog
(
	PlotLogID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	PlotID INT FOREIGN KEY REFERENCES Plot(PlotID),
	CharacterID INT FOREIGN KEY REFERENCES UserCharacter(UserCharacterID),
	PlotLogDateTime DATETIME,
	PlotLogMessage VARCHAR(MAX)
)

CREATE TABLE CharacterLog
(
	CharacterLogID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	CharacterID INT FOREIGN KEY REFERENCES UserCharacter(UserCharacterID),
	CharacterLogDateTime DATETIME,
	CharacterLogMessage VARCHAR(MAX)
)

CREATE TABLE County
(
	CountyID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	CountyDuchy VARCHAR(10),
	CountyDescription VARCHAR(12)
)

INSERT INTO County (CountyDuchy, CountyDescription) VALUES
('Thegnheim', 'Aiber'),
('Thegnheim', 'Brynholm'),
('Thegnheim', 'Nordafell'),
('Thegnheim', 'Steadfield'),
('Thegnheim', 'Meidmar'),
('Langzerund', 'Breister'),
('Langzerund', 'Vinhime'),
('Langzerund', 'Kobberholm'),
('Svaerstein', 'Naring'),
('Svaerstein', 'Svaertzdalr'),
('Svaerstein', 'Kulletheim'),
('Svaerstein', 'Kulletfell'),
('Svaerstein', 'Kornheim'),
('Sarkland', 'Granadalr'),
('Sarkland', 'Niudottir'),
('Sarkland', 'Vidar-kentta'),
('Sarkland', 'Liosto'),
('Sarkland', 'Luxendalr'),
('Rotheim', 'Stjernefel'),
('Rotheim', 'Edeline'),
('Rotheim', 'Gnisten-Elv'),
('Rotheim', 'Domar'),
('Rotheim', 'Swardtoft'),
('Ragonvaldr', 'Sudurfell'),
('Ragonvaldr', 'Jarnholdt'),
('Ragonvaldr', 'Dwergstein');

GO