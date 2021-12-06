
CREATE TABLE  Address (
      AddressID INT NOT NULL auto_increment,
      HouseNumber VARCHAR(50) NULL DEFAULT NULL,
      Street VARCHAR(50) NULL DEFAULT NULL,
      Postcode VARCHAR(50) NULL DEFAULT NULL,
      City VARCHAR(50) NULL DEFAULT NULL,
      PRIMARY KEY (AddressID))
      
CREATE TABLE Guest (
      FirstName VARCHAR(50) NOT NULL,
      LastName VARCHAR(50) NOT NULL,
      MobileNumber VARCHAR(50) NOT NULL,
      Email VARCHAR(50) NOT NULL,
      GuestID INT NOT NULL AUTO_INCREMENT,
      AddressID INT NOT NULL,
      PRIMARY KEY (GuestID),
      FOREIGN KEY (AddressID)  REFERENCES Address (AddressID))
      
    CREATE TABLE Guest_Passwords (
      GuestID INT NOT NULL,
      Passwords VARCHAR(50) NOT NULL,
      PRIMARY KEY (GuestID),
      FOREIGN KEY (GuestID) REFERENCES Guest (GuestID))
      
    CREATE TABLE Host (
      HostID INT NOT NULL AUTO_INCREMENT,
      FirstName VARCHAR(50) NOT NULL,
      LastName VARCHAR(50) NOT NULL,
      IsSuperHost BIT NOT NULL,
      Email VARCHAR(50) NOT NULL,
      AddressID INT NOT NULL,
      PRIMARY KEY (HostID),
      FOREIGN KEY (AddressID) REFERENCES Address (AddressID))
        
CREATE TABLE Host_Passwords (
      HostID INT NOT NULL,
      Passwords VARCHAR(50) NOT NULL,
      PRIMARY KEY (HostID),
      FOREIGN KEY (HostID) REFERENCES Host (HostID))
      

      
CREATE TABLE Property (
      PropertyID INT NOT NULL AUTO_INCREMENT,
      HostID INT NOT NULL,
      ShortName VARCHAR(50) NOT NULL,
      Descriptions VARCHAR(50) NOT NULL,
      AddressID INT NOT NULL,
      Breakfast BIT NOT NULL,
      PRIMARY KEY (PropertyID),
	  FOREIGN KEY (AddressID) REFERENCES Address (AddressID),
      FOREIGN KEY (HostID) REFERENCES Host (HostID))

CREATE TABLE Bathing_Facility (
      PropertyID INT NOT NULL,
      BathroomCount INT NOT NULL,
      HairDryer BIT NULL DEFAULT NULL,
      Shampoo BIT NULL DEFAULT NULL,
      ToiletPaper BIT NULL DEFAULT NULL,
      Toilet BIT NOT NULL,
      Bath BIT NOT NULL,
      Shower BIT NOT NULL,
      IsShared BIT NOT NULL,
      PRIMARY KEY (PropertyID, BathroomCount),
      FOREIGN KEY (PropertyID) REFERENCES Property (PropertyID))

CREATE TABLE Bookings (
      BookingID INT NOT NULL AUTO_INCREMENT,
      PropertyID INT NULL DEFAULT NULL,
      GuestID INT NULL DEFAULT NULL,
      StartDate VARCHAR(50) NULL DEFAULT NULL,
      EndDate VARCHAR(50) NULL DEFAULT NULL,
      Provisional VARCHAR(50) NULL DEFAULT NULL,
      PRIMARY KEY (BookingID),
      FOREIGN KEY (GuestID) REFERENCES Guest (GuestID),
      FOREIGN KEY (PropertyID) REFERENCES Property (PropertyID))
CREATE TABLE Charge_Band (
      StartDate DATE NOT NULL,
      EndDate DATE NOT NULL,
      Price VARCHAR(50) NOT NULL,
      PropertyID INT NOT NULL,
      PricePerNight DECIMAL(9,2) NULL DEFAULT NULL,
      ServiceCharge DECIMAL(9,2) NULL DEFAULT NULL,
      CleaningCharge DECIMAL(9,2) NULL DEFAULT NULL,
      PRIMARY KEY (PropertyID, StartDate),
      FOREIGN KEY (PropertyID)  REFERENCES Property (PropertyID))


CREATE TABLE Kitchen_Facility (
      PropertyID INT NOT NULL DEFAULT '0',
      Refrigerator BIT NULL DEFAULT NULL,
      Microwave BIT NULL DEFAULT NULL,
      Oven BIT NULL DEFAULT NULL,
      Stove BIT NULL DEFAULT NULL,
      Dishwasher BIT NULL DEFAULT NULL,
      Tableware BIT NULL DEFAULT NULL,
      Cookware BIT NULL DEFAULT NULL,
      basicProvision BIT NULL DEFAULT NULL,
      PRIMARY KEY (PropertyID),
      FOREIGN KEY (PropertyID) REFERENCES Property (PropertyID))

    CREATE TABLE Living_Facility (
      PropertyID INT NOT NULL,
      WIFI BIT NULL DEFAULT NULL,
      Television BIT NULL DEFAULT NULL,
      Satellite BIT NULL DEFAULT NULL,
      Streaming BIT NULL DEFAULT NULL,
      DVDPlayer BIT NULL DEFAULT NULL,
      BoardGames BIT NULL DEFAULT NULL,
      PRIMARY KEY (PropertyID),
      FOREIGN KEY (PropertyID)  REFERENCES Property (PropertyID))
      
CREATE TABLE Outdoor_Facility (
      PropertyID INT NOT NULL,
      Parking VARCHAR(50) NULL DEFAULT NULL,
      Patio BIT NULL DEFAULT NULL,
      Barbeque BIT NULL DEFAULT NULL,
      PRIMARY KEY (PropertyID),
      FOREIGN KEY (PropertyID) REFERENCES Property (PropertyID))


CREATE TABLE Reviews (
      PropertyID INT NOT NULL DEFAULT '0',
      GuestID INT NOT NULL DEFAULT '0',
      HostID INT NULL DEFAULT NULL,
      Cleanliness INT NULL DEFAULT NULL,
      Communication INT NULL DEFAULT NULL,
      CheckIn INT NULL DEFAULT NULL,
      Accuracy INT NULL DEFAULT NULL,
      Location INT NULL DEFAULT NULL,
      Value_for_money INT NULL DEFAULT NULL,
      OptionalDescription VARCHAR(50) NULL DEFAULT NULL,
      PRIMARY KEY (GuestID, PropertyID),
      FOREIGN KEY (PropertyID) REFERENCES Property (PropertyID),
      FOREIGN KEY (GuestID) REFERENCES Guest (GuestID),
      FOREIGN KEY (HostID) REFERENCES Host (HostID))

CREATE TABLE Sleeping_Facility (
      PropertyID INT NOT NULL,
      BedroomNumber INT NOT NULL,
      BedLinen BIT NULL DEFAULT NULL,
      Towels BIT NULL DEFAULT NULL,
      Bed1Type VARCHAR(50) NOT NULL,
      PeopleInBed1 INT NOT NULL,
      Bed2Type VARCHAR(50) NULL DEFAULT NULL,
      PeopleInBed2 INT NULL DEFAULT NULL,
      PRIMARY KEY (PropertyID, BedroomNumber),
      FOREIGN KEY (PropertyID) REFERENCES Property (PropertyID))
      
CREATE TABLE Utility_Facility (
      PropertyID INT NOT NULL,
      CentralHeating BIT NULL DEFAULT NULL,
      WashingMachine BIT NULL DEFAULT NULL,
      DryingMachine BIT NULL DEFAULT NULL,
      FireExtinguisher BIT NULL DEFAULT NULL,
      SmokeAlarm BIT NULL DEFAULT NULL,
      FirstAid BIT NULL DEFAULT NULL,
      PRIMARY KEY (PropertyID),
      FOREIGN KEY (PropertyID) REFERENCES Property (PropertyID));
