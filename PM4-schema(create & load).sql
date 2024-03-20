CREATE SCHEMA IF NOT EXISTS SeattleTravelKit;
USE SeattleTravelKit;

DROP TABLE IF EXISTS HotelReviews;
DROP TABLE IF EXISTS Hotels;
DROP TABLE IF EXISTS Reviews;
DROP TABLE IF EXISTS RestaurantReviews;
DROP TABLE IF EXISTS AttractionReviews;
DROP TABLE IF EXISTS Attractions;
DROP TABLE IF EXISTS Crimes;
DROP TABLE IF EXISTS Restaurants;
DROP TABLE IF EXISTS Address;
DROP TABLE IF EXISTS CreditCards;
DROP TABLE IF EXISTS Users;


CREATE TABLE Hotels (
    HotelId INT NOT NULL AUTO_INCREMENT,
    HotelName VARCHAR(255),
    Rating DECIMAL(3, 2),
    Website VARCHAR(255),
    Phone VARCHAR(20),
    Details TEXT,
    Address TEXT,
    ZipCode INT,
    City VARCHAR(255),
    CONSTRAINT pk_Hotels_HotelId PRIMARY KEY (HotelId)
);


  CREATE TABLE HotelReviews (
    ReviewId INT PRIMARY KEY AUTO_INCREMENT,
    HotelId INT,
    Service FLOAT,
    Cleanliness FLOAT,
    Location FLOAT,
    SleepQuality FLOAT,
    CONSTRAINT fk_HotelReviews_HotelId FOREIGN KEY (HotelId)
		REFERENCES  Hotels (HotelId)
		ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE Attractions (
    AttractionId INT AUTO_INCREMENT PRIMARY KEY,
    AttractionsName VARCHAR(255),
    Phone INT,
    Website VARCHAR(2048),
    ZipCode INT,
    Area VARCHAR(255)
);


CREATE TABLE Reviews (
    ReviewId INT PRIMARY KEY AUTO_INCREMENT,
    UserName VARCHAR(255) NOT NULL,
    CreatedTime TIMESTAMP NOT NULL,
    Content TEXT NOT NULL,
    Rating DECIMAL(3, 1) NOT NULL
);


CREATE TABLE RestaurantReviews (
    ReviewId INT PRIMARY KEY AUTO_INCREMENT,
    RestaurantId INT NOT NULL,
    service DECIMAL(2,1) NOT NULL,
    foodQuality DECIMAL(2,1) NOT NULL,
    OperationTime VARCHAR(11) NOT NULL
);


CREATE TABLE AttractionReviews (
    ReviewId INT PRIMARY KEY AUTO_INCREMENT,
    AttractionId INT NOT NULL,
    Duration VARCHAR(15) NOT NULL
);


CREATE TABLE Crimes (
    CaseNumber VARCHAR(50),
    CreatedDateTime DATETIME,
    Address VARCHAR(255),
    ZipCode INT,
   CONSTRAINT pk_Crimes_CaseNumber PRIMARY KEY (CaseNumber)
);


CREATE TABLE Restaurants (
    RestaurantId INT NOT NULL AUTO_INCREMENT,
    RestaurantName VARCHAR(255),
    Address TEXT,
    Rating DECIMAL(3, 2),
    Area VARCHAR(255),
    Category VARCHAR(255), -- Changed from ENUM to VARCHAR for broader compatibility
    Service TEXT,
    ZipCode INT,
    CONSTRAINT pk_Restaurants_RestaurantID PRIMARY KEY (RestaurantId)
);


CREATE TABLE  Users (
    UserName VARCHAR(50) UNIQUE,
    Email VARCHAR(100) UNIQUE,
    Password VARCHAR(255),
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Phone INT,
    CONSTRAINT pk_Users_UserName PRIMARY KEY (UserName)
);


CREATE TABLE Address (
    UserName VARCHAR(255),
    City VARCHAR(255),
    Street1 VARCHAR(255),
    Street2 VARCHAR(255),
    State VARCHAR(255),
    ZipCode INT,
    Country VARCHAR(255),
    CONSTRAINT pk_Address_UserName PRIMARY KEY (UserName),
    CONSTRAINT fk_Address_UserName FOREIGN KEY (UserName)
        REFERENCES Users (UserName)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE CreditCards (
	UserName VARCHAR(255),
	CardNumber VARCHAR(255)NOT NULL,
    Expiration VARCHAR(50),
    CONSTRAINT pk_CreditCard_CardNumber PRIMARY KEY (CardNumber),
    CONSTRAINT fk_CreditCard_UserName FOREIGN KEY (UserName)
        REFERENCES Users (UserName)
        ON UPDATE CASCADE ON DELETE CASCADE
);


LOAD DATA LOCAL INFILE '/Users/a123/Desktop/5200/SeattleTravelKit-Tables/Hotels.csv' INTO TABLE Hotels
  FIELDS TERMINATED BY ','  ENCLOSED BY '\"'
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (HotelName,Rating,Website,Phone,Details,Address,ZipCode,City);

LOAD DATA LOCAL INFILE "/Users/a123/Desktop/5200/SeattleTravelKit-Tables/HotelReviews.csv" INTO TABLE HotelReviews
  FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"'
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (ReviewId,HotelId,Service,Cleanliness,Location,SleepQuality);

LOAD DATA LOCAL INFILE '/Users/a123/Desktop/5200/SeattleTravelKit-Tables/Reviews.csv' INTO TABLE Reviews
	FIELDS TERMINATED BY ',' ENCLOSED BY '"'
	LINES TERMINATED BY '\n' 
	IGNORE 1 LINES
	(ReviewId, UserName, CreatedTime, Content, Rating);


LOAD DATA LOCAL INFILE '/Users/a123/Desktop/5200/SeattleTravelKit-Tables/RestaurantReviews.csv' INTO TABLE RestaurantReviews
	FIELDS TERMINATED BY ',' ENCLOSED BY '"'
	LINES TERMINATED BY '\n'
	IGNORE 1 LINES
	(ReviewId, RestaurantID, Service, FoodQuality, OperationTime);

LOAD DATA LOCAL INFILE '/Users/a123/Desktop/5200/SeattleTravelKit-Tables/Reviews.csv' INTO TABLE Reviews
	FIELDS TERMINATED BY ',' ENCLOSED BY '"'
	LINES TERMINATED BY '\n'
	IGNORE 1 LINES
	(ReviewId, UserName, CreatedTime, Content, Rating);
  
    
LOAD DATA LOCAL INFILE '/Users/a123/Desktop/5200/SeattleTravelKit-Tables/Attractions.csv' INTO TABLE Attractions
	FIELDS TERMINATED BY ','  ENCLOSED BY '"'
	LINES TERMINATED BY '\n'
	IGNORE 1 ROWS
    ( AttractionsName,Phone, Website, ZipCode, Area);

LOAD DATA LOCAL INFILE '/Users/a123/Desktop/5200/SeattleTravelKit-Tables/AttractionReviews.csv' INTO TABLE AttractionReviews
	FIELDS TERMINATED BY ',' ENCLOSED BY '"'
	LINES TERMINATED BY '\n'
	IGNORE 1 LINES
    (ReviewId,AttractionId,Duration);


LOAD DATA LOCAL INFILE "/Users/a123/Desktop/5200/SeattleTravelKit-Tables/Crimes.csv" INTO TABLE Crimes
  FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"'
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@CaseNumber, @CreatedDateTime, @Address, @ZipCode)
  SET CaseNumber = @CaseNumber,
    CreatedDateTime = IF(@CreatedDateTime = '0000-00-00 00:00:00', NULL, STR_TO_DATE(@CreatedDateTime, '%Y-%m-%d %H:%i:%s')),
    Address = @Address,
    ZipCode = IF(TRIM(@ZipCode) = '', 0, @ZipCode);

LOAD DATA LOCAL INFILE "/Users/a123/Desktop/5200/SeattleTravelKit-Tables/Restaurants.csv" INTO TABLE Restaurants
  FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"'
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;

LOAD DATA LOCAL INFILE '/Users/a123/Desktop/5200/SeattleTravelKit-Tables/Users.csv' INTO TABLE Users
	FIELDS TERMINATED BY ','  ENCLOSED BY ""
	LINES TERMINATED BY '\n'
	IGNORE 1 ROWS;

LOAD  DATA LOCAL INFILE '/Users/a123/Desktop/5200/SeattleTravelKit-Tables/Address.csv' INTO TABLE Address
	FIELDS TERMINATED BY ','  ENCLOSED BY ""
	LINES TERMINATED BY '\n'
	IGNORE 1 ROWS;

LOAD DATA LOCAL INFILE '/Users/a123/Desktop/5200/SeattleTravelKit-Tables/CreditCards.csv' INTO TABLE CreditCards
	FIELDS TERMINATED BY ','  ENCLOSED BY ""
	LINES TERMINATED BY '\n'
	IGNORE 1 ROWS;
    

