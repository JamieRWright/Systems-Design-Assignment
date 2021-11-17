CREATE DATABASE SystemsDesign;
Use SystemsDesign;

CREATE TABLE Host
(
 HostID      int AUTO_INCREMENT NOT NULL ,
 FirstName  varchar(50) NOT NULL ,
 LastName   varchar(50) NOT NULL ,
 IsSuperHost bit NOT NULL ,
 Email       varchar(50) NOT NULL ,


 PRIMARY KEY CLUSTERED (HostID ASC)
);

CREATE TABLE Host_Passwords
(
 HostID  int NOT NULL ,
 Passwords varchar(50) NOT NULL ,


PRIMARY KEY CLUSTERED (HostID ASC),
FOREIGN KEY (HostID)  REFERENCES Host(HostID)
);

CREATE TABLE Guest
(
 FirstName    varchar(50) NOT NULL ,
 LastName     varchar(50) NOT NULL ,
 MobileNumber varchar(50) NOT NULL ,
 Email         varchar(50) NOT NULL ,
 GuestID       int auto_increment NOT NULL ,


PRIMARY KEY CLUSTERED (GuestID ASC)
);


CREATE TABLE Guest_Passwords
(
 GuestID int NOT NULL ,
 Passwords  varchar(50)   NOT NULL ,
PRIMARY KEY CLUSTERED (GuestID ASC),
FOREIGN KEY (GuestID)  REFERENCES Guest(GuestID)
);

CREATE TABLE Property
(
 PropertyID     int AUTO_INCREMENT NOT NULL ,
 HostID         int NOT NULL ,
 CurrentTennant int NULL ,
 HouseNumber    varchar(50) NOT NULL ,
 Street         varchar(50) NOT NULL ,
 Postcode       varchar(50) NOT NULL ,
 City           varchar(50) NOT NULL ,
 Country        varchar(50) NOT NULL ,
 ShortName      varchar(50) NOT NULL ,
 Descriptions    varchar(50) NOT NULL ,


PRIMARY KEY CLUSTERED (PropertyID ASC),
FOREIGN KEY (CurrentTennant)  REFERENCES Guest(GuestID),
FOREIGN KEY (HostID)  REFERENCES Host(HostID)
);






CREATE TABLE Bathing_Facility
(
 PropertyID    int NOT NULL ,
 BathroomCount int NOT NULL ,
 HairDryer    bit NULL ,
 Shampoo       bit NULL ,
 ToiletPaper  bit NULL ,
 Toilet        bit NOT NULL ,
 Bath         bit NOT NULL ,
 Shower        bit NOT NULL ,
 IsShared      bit NOT NULL ,


PRIMARY KEY CLUSTERED (PropertyID ASC, BathroomCount ASC),
FOREIGN KEY (PropertyID)  REFERENCES Property(PropertyID)
);


CREATE TABLE Beds
(
 BedType    varchar(50) NOT NULL ,
 PeopleInBed int NOT NULL ,

PRIMARY KEY CLUSTERED (BedType ASC, PeopleInBed ASC)
);


CREATE TABLE Charge_Band
(
 StartDate  date NOT NULL ,
 EndDate    date NOT NULL ,
 ChargeBand varchar(50) NOT NULL ,
 Price      varchar(50) NOT NULL ,
 PropertyID int NOT NULL ,
 Period     int NOT NULL ,


PRIMARY KEY CLUSTERED (PropertyID ASC, Period ASC),
FOREIGN KEY (PropertyID)  REFERENCES Property(PropertyID)
);


CREATE TABLE Kitchen_Facility(
PropertyID int,
Refrigerator bit,
Microwave bit,
Oven bit,
Stove bit,
Dishwasher bit,
Tableware bit,
Cookware bit,
basicProvision bit,
PRIMARY KEY CLUSTERED (PropertyID ASC),
FOREIGN KEY (PropertyID)  REFERENCES Property(PropertyID)
);

CREATE TABLE Outdoor_Facility(
PropertyID int,
Parking varchar(50),
Patio bit null,
Barbeque bit null,
PRIMARY KEY CLUSTERED (PropertyID ASC),
FOREIGN KEY (PropertyID)  REFERENCES Property(PropertyID)
);



CREATE TABLE Sleeping_Facility
(
 PropertyID    int NOT NULL ,
 BedroomNumber int NOT NULL ,
 BedLinen     bit NULL ,
 Towels        bit NULL ,
 Bed1Type     varchar(50) NOT NULL ,
 PeopleInBed1  int NOT NULL ,
 Bed2Type     varchar(50) NULL ,
 PeopleInBed2  int NULL ,


PRIMARY KEY CLUSTERED (PropertyID ASC, BedroomNumber ASC),
FOREIGN KEY (Bed1Type, PeopleInBed1)  REFERENCES Beds(BedType, PeopleInBed),
FOREIGN KEY (Bed2Type, PeopleInBed2)  REFERENCES Beds(BedType, PeopleInBed),
FOREIGN KEY (PropertyID)  REFERENCES Property(PropertyID)
);


INSERT INTO Beds(BedType, PeopleInBed)
VALUES ('Single Bed', 1);
INSERT INTO Beds(BedType, PeopleInBed)
VALUES ('Double Bed', 2);
INSERT INTO Beds(BedType, PeopleInBed)
VALUES ('Kingsize Bed', 2);
INSERT INTO Beds(BedType, PeopleInBed)
VALUES ('Bunk Bed', 2);


INSERT INTO Guest(FirstName, LastName, MobileNumber, Email)
VALUES ('John', 'Smith', '07123456789', 'john.smith@sheffield.ac.uk');
INSERT INTO Host(FirstName, LastName, IsSuperHost,  Email)
VALUES ('Land', 'Lord', 1, 'Land.Lord@sheffield.ac.uk');


INSERT INTO Guest_Passwords(GuestID, Passwords)
VALUES (1, 'Admin');

INSERT INTO Host_Passwords(HostID, Passwords)
VALUES (1, 'Admin');

INSERT INTO Property(HostID, CurrentTennant,  HouseNumber, Street, Postcode, City, Country, ShortName, Descriptions)
VALUES (1, 1, '123', 'MakeItUp Lane', 'S1 A23', 'Sheffield', 'England', 'Family Home', 'Quiet Residential 4 Bed Semi-Detached');


INSERT INTO Kitchen_Facility(PropertyID,refrigerator,microwave,oven,stove,dishwasher,tableware,cookware, basicProvision)
VALUES(1, 1, 1, 1, 1, 0, 0, 0, 0);
INSERT INTO Outdoor_Facility(PropertyID,Parking,patio,barbeque)
VALUES(1,'Free On-Site Parking', 0, 0);
INSERT INTO Sleeping_Facility(PropertyID,BedroomNumber, BedLinen, Towels, Bed1Type, PeopleInBed1, Bed2Type, PeopleInBed2)
VALUES(1,1, 0, 0, 'Single Bed', 1, NULL, NULL);
INSERT INTO Sleeping_Facility(PropertyID,BedroomNumber, BedLinen, Towels, Bed1Type, PeopleInBed1, Bed2Type, PeopleInBed2)
VALUES(1,2, 0, 0, 'Kingsize Bed', 2, NULL, NULL);
