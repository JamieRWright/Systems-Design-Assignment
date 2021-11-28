CREATE TABLE  `Address` (
      `AddressID` INT(11) NOT NULL AUTO_INCREMENT,
      `HouseNumber` VARCHAR(50) NULL DEFAULT NULL,
      `Street` VARCHAR(50) NULL DEFAULT NULL,
      `Postcode` VARCHAR(50) NULL DEFAULT NULL,
      `City` VARCHAR(50) NULL DEFAULT NULL,
      PRIMARY KEY (`AddressID`))
      
      
CREATE TABLE `Bathing_Facility` (
      `PropertyID` INT(11) NOT NULL,
      `BathroomCount` INT(11) NOT NULL,
      `HairDryer` BIT(1) NULL DEFAULT NULL,
      `Shampoo` BIT(1) NULL DEFAULT NULL,
      `ToiletPaper` BIT(1) NULL DEFAULT NULL,
      `Toilet` BIT(1) NOT NULL,
      `Bath` BIT(1) NOT NULL,
      `Shower` BIT(1) NOT NULL,
      `IsShared` BIT(1) NOT NULL,
      PRIMARY KEY (`PropertyID`, `BathroomCount`),
      CONSTRAINT FOREIGN KEY (`PropertyID`) REFERENCES `Property` (`PropertyID`))
CREATE TABLE `Beds` (
	`BedType` VARCHAR(50) NOT NULL,
    `PeopleInBed` INT(11) NOT NULL,
    PRIMARY KEY (`BedType`, `PeopleInBed`))

CREATE TABLE `Bookings` (
      `BookingID` INT(11) NOT NULL AUTO_INCREMENT,
      `PropertyID` INT(50) NULL DEFAULT NULL,
      `HostID` INT(50) NULL DEFAULT NULL,
      `GuestID` INT(50) NULL DEFAULT NULL,
      `StartDate` VARCHAR(50) NULL DEFAULT NULL,
      `EndDate` VARCHAR(50) NULL DEFAULT NULL,
      `Provisional` VARCHAR(50) NULL DEFAULT NULL,
      PRIMARY KEY (`BookingID`),
      INDEX `GuestID` (`GuestID` ASC) VISIBLE,
      INDEX `HostID` (`HostID` ASC) VISIBLE,
      INDEX `PropertyID` (`PropertyID` ASC) VISIBLE,
      CONSTRAINT FOREIGN KEY (`GuestID`) REFERENCES `Guest` (`GuestID`),
      CONSTRAINT  FOREIGN KEY (`HostID`) REFERENCES `Host` (`HostID`),
      CONSTRAINT FOREIGN KEY (`PropertyID`) REFERENCES `Property` (`PropertyID`))
CREATE TABLE `Charge_Band` (
      `StartDate` DATE NOT NULL,
      `EndDate` DATE NOT NULL,
      `Price` VARCHAR(50) NOT NULL,
      `PropertyID` INT(11) NOT NULL,
      `PricePerNight` DECIMAL(9,2) NULL DEFAULT NULL,
      `ServiceCharge` DECIMAL(9,2) NULL DEFAULT NULL,
      `CleaningCharge` DECIMAL(9,2) NULL DEFAULT NULL,
      PRIMARY KEY (`PropertyID`, `StartDate`),
      CONSTRAINT FOREIGN KEY (`PropertyID`)  REFERENCES `Property` (`PropertyID`))
CREATE TABLE `Guest` (
      `FirstName` VARCHAR(50) NOT NULL,
      `LastName` VARCHAR(50) NOT NULL,
      `MobileNumber` VARCHAR(50) NOT NULL,
      `Email` VARCHAR(50) NOT NULL,
      `GuestID` INT(11) NOT NULL AUTO_INCREMENT,
      `AddressID` INT(11) NOT NULL,
      PRIMARY KEY (`GuestID`),
      INDEX `FK_Guest` (`AddressID` ASC) VISIBLE,
      CONSTRAINT FOREIGN KEY (`AddressID`)  REFERENCES `Address` (`AddressID`))
      
    CREATE TABLE `Guest_Passwords` (
      `GuestID` INT(11) NOT NULL,
      `Passwords` VARCHAR(50) NOT NULL,
      PRIMARY KEY (`GuestID`),
      CONSTRAINT FOREIGN KEY (`GuestID`) REFERENCES `Guest` (`GuestID`))
      
    CREATE TABLE `Host` (
      `HostID` INT(11) NOT NULL AUTO_INCREMENT,
      `FirstName` VARCHAR(50) NOT NULL,
      `LastName` VARCHAR(50) NOT NULL,
      `IsSuperHost` BIT(1) NOT NULL,
      `Email` VARCHAR(50) NOT NULL,
      `AddressID` INT(11) NOT NULL,
      PRIMARY KEY (`HostID`),
      INDEX `AddressID` (`AddressID` ASC) VISIBLE,
      CONSTRAINT FOREIGN KEY (`AddressID`) REFERENCES `team054`.`Address` (`AddressID`))
        
CREATE TABLE `Host_Passwords` (
      `HostID` INT(11) NOT NULL,
      `Passwords` VARCHAR(50) NOT NULL,
      PRIMARY KEY (`HostID`),
      CONSTRAINT FOREIGN KEY (`HostID`) REFERENCES `Host` (`HostID`))

CREATE TABLE `Kitchen_Facility` (
      `PropertyID` INT(11) NOT NULL DEFAULT '0',
      `Refrigerator` BIT(1) NULL DEFAULT NULL,
      `Microwave` BIT(1) NULL DEFAULT NULL,
      `Oven` BIT(1) NULL DEFAULT NULL,
      `Stove` BIT(1) NULL DEFAULT NULL,
      `Dishwasher` BIT(1) NULL DEFAULT NULL,
      `Tableware` BIT(1) NULL DEFAULT NULL,
      `Cookware` BIT(1) NULL DEFAULT NULL,
      `basicProvision` BIT(1) NULL DEFAULT NULL,
      PRIMARY KEY (`PropertyID`),
      CONSTRAINT FOREIGN KEY (`PropertyID`) REFERENCES `Property` (`PropertyID`))

    CREATE TABLE `Living_Facility` (
      `PropertyID` INT(11) NOT NULL,
      `WIFI` BIT(1) NULL DEFAULT NULL,
      `Television` BIT(1) NULL DEFAULT NULL,
      `Satellite` BIT(1) NULL DEFAULT NULL,
      `Streaming` BIT(1) NULL DEFAULT NULL,
      `DVDPlayer` BIT(1) NULL DEFAULT NULL,
      `BoardGames` BIT(1) NULL DEFAULT NULL,
      PRIMARY KEY (`PropertyID`),
      CONSTRAINT FOREIGN KEY (`PropertyID`)  REFERENCES `Property` (`PropertyID`))
      
CREATE TABLE `Outdoor_Facility` (
      `PropertyID` INT(11) NOT NULL DEFAULT '0',
      `Parking` VARCHAR(50) NULL DEFAULT NULL,
      `Patio` BIT(1) NULL DEFAULT NULL,
      `Barbeque` BIT(1) NULL DEFAULT NULL,
      PRIMARY KEY (`PropertyID`),
      CONSTRAINT FOREIGN KEY (`PropertyID`) REFERENCES `Property` (`PropertyID`))
      
CREATE TABLE `Property` (
      `PropertyID` INT(11) NOT NULL AUTO_INCREMENT,
      `HostID` INT(11) NOT NULL,
      `CurrentTennant` INT(11) NULL DEFAULT NULL,
      `ShortName` VARCHAR(50) NOT NULL,
      `Descriptions` VARCHAR(50) NOT NULL,
      `AddressID` INT(11) NOT NULL,
      `Breakfast` BIT(1) NOT NULL,
      PRIMARY KEY (`PropertyID`),
      INDEX `CurrentTennant` (`CurrentTennant` ASC) VISIBLE,
      INDEX `HostID` (`HostID` ASC) VISIBLE,
      CONSTRAINT FOREIGN KEY (`CurrentTennant`) REFERENCES `Guest` (`GuestID`),
      CONSTRAINT FOREIGN KEY (`HostID`) REFERENCES `Host` (`HostID`))

CREATE TABLE `Reviews` (
      `PropertyID` INT(11) NOT NULL DEFAULT '0',
      `GuestID` INT(11) NOT NULL DEFAULT '0',
      `HostID` INT(11) NULL DEFAULT NULL,
      `Cleanliness` INT(1) NULL DEFAULT NULL,
      `Communication` INT(1) NULL DEFAULT NULL,
      `CheckIn` INT(1) NULL DEFAULT NULL,
      `Accuracy` INT(1) NULL DEFAULT NULL,
      `Location` INT(1) NULL DEFAULT NULL,
      `Value_for_money` INT(1) NULL DEFAULT NULL,
      `OptionalDescription` VARCHAR(50) NULL DEFAULT NULL,
      PRIMARY KEY (`GuestID`, `PropertyID`),
      INDEX `PropertyID` (`PropertyID` ASC) VISIBLE,
      INDEX `HostID` (`HostID` ASC) VISIBLE,
      CONSTRAINT FOREIGN KEY (`PropertyID`) REFERENCES `Property` (`PropertyID`),
      CONSTRAINT FOREIGN KEY (`GuestID`) REFERENCES `Guest` (`GuestID`),
      CONSTRAINT FOREIGN KEY (`HostID`) REFERENCES `Host` (`HostID`))

CREATE TABLE `Sleeping_Facility` (
      `PropertyID` INT(11) NOT NULL,
      `BedroomNumber` INT(11) NOT NULL,
      `BedLinen` BIT(1) NULL DEFAULT NULL,
      `Towels` BIT(1) NULL DEFAULT NULL,
      `Bed1Type` VARCHAR(50) NOT NULL,
      `PeopleInBed1` INT(11) NOT NULL,
      `Bed2Type` VARCHAR(50) NULL DEFAULT NULL,
      `PeopleInBed2` INT(11) NULL DEFAULT NULL,
      PRIMARY KEY (`PropertyID`, `BedroomNumber`),
      INDEX `Bed1Type` (`Bed1Type` ASC, `PeopleInBed1` ASC) VISIBLE,
      INDEX `Bed2Type` (`Bed2Type` ASC, `PeopleInBed2` ASC) VISIBLE,
      CONSTRAINT FOREIGN KEY (`Bed1Type` , `PeopleInBed1`) REFERENCES `Beds` (`BedType` , `PeopleInBed`),
      CONSTRAINT FOREIGN KEY (`Bed2Type` , `PeopleInBed2`) REFERENCES `Beds` (`BedType` , `PeopleInBed`),
      CONSTRAINT FOREIGN KEY (`PropertyID`) REFERENCES `Property` (`PropertyID`))
      
CREATE TABLE `Utility_Facility` (
      `PropertyID` INT(11) NOT NULL,
      `CentralHeating` BIT(1) NULL DEFAULT NULL,
      `WashingMachine` BIT(1) NULL DEFAULT NULL,
      `DryingMachine` BIT(1) NULL DEFAULT NULL,
      `FireExtinguisher` BIT(1) NULL DEFAULT NULL,
      `SmokeAlarm` BIT(1) NULL DEFAULT NULL,
      `FirstAid` BIT(1) NULL DEFAULT NULL,
      PRIMARY KEY (`PropertyID`),
      CONSTRAINT FOREIGN KEY (`PropertyID`) REFERENCES `Property` (`PropertyID`))