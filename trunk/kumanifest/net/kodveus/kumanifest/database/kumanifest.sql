ALTER TABLE voyage DROP FOREIGN KEY FK_voyage_location_1;

ALTER TABLE cargo DROP FOREIGN KEY FK_cargo_4;

ALTER TABLE vessel DROP FOREIGN KEY FK_vessel_country;

ALTER TABLE cargo DROP FOREIGN KEY FK_cargo_2;

ALTER TABLE container DROP FOREIGN KEY FK_container_3;

ALTER TABLE container DROP FOREIGN KEY FK_container_2;

ALTER TABLE container DROP FOREIGN KEY FK_container_containerType;

ALTER TABLE vessel DROP FOREIGN KEY FK_vessel_location;

ALTER TABLE bl DROP FOREIGN KEY FK_bl_1;

ALTER TABLE voyage DROP FOREIGN KEY FK_voyage_1;

ALTER TABLE voyage DROP FOREIGN KEY FK_voyage_office;

ALTER TABLE bl DROP FOREIGN KEY FK_bl_location_1;

ALTER TABLE cargo DROP FOREIGN KEY FK_cargo_3;

ALTER TABLE office DROP PRIMARY KEY;

ALTER TABLE location DROP PRIMARY KEY;

ALTER TABLE country DROP PRIMARY KEY;

ALTER TABLE cargo DROP PRIMARY KEY;

ALTER TABLE bl DROP PRIMARY KEY;

ALTER TABLE commodity DROP PRIMARY KEY;

ALTER TABLE containertype DROP PRIMARY KEY;

ALTER TABLE voyage DROP PRIMARY KEY;

ALTER TABLE pack DROP PRIMARY KEY;

ALTER TABLE containersize DROP PRIMARY KEY;

ALTER TABLE vessel DROP PRIMARY KEY;

ALTER TABLE container DROP PRIMARY KEY;

ALTER TABLE container DROP INDEX containerSize;

ALTER TABLE bl DROP INDEX finalDischargePlace;

ALTER TABLE cargo DROP INDEX packId;

ALTER TABLE container DROP INDEX containerType;

ALTER TABLE vessel DROP INDEX port;

ALTER TABLE cargo DROP INDEX commodityId;

ALTER TABLE bl DROP INDEX finalDestination;

ALTER TABLE bl DROP INDEX placeOfOrigin;

ALTER TABLE voyage DROP INDEX vesselId;

ALTER TABLE bl DROP INDEX portOfDischarge;

ALTER TABLE bl DROP INDEX portOfLoading;

ALTER TABLE voyage DROP INDEX officeId;

ALTER TABLE bl DROP INDEX voyageId;

ALTER TABLE container DROP INDEX blId;

ALTER TABLE voyage DROP INDEX firstLeavedPort;

ALTER TABLE cargo DROP INDEX containerId;

ALTER TABLE voyage DROP INDEX lastLeavedPort;

ALTER TABLE vessel DROP INDEX flag;

ALTER TABLE bl DROP INDEX placeOfReceipt;

DROP VIEW vwboflading;

DROP VIEW vwcontainerlist;

DROP TABLE containersize;

DROP TABLE country;

DROP TABLE bl;

DROP TABLE pack;

DROP TABLE vessel;

DROP TABLE voyage;

DROP TABLE cargo;

DROP TABLE office;

DROP TABLE location;

DROP TABLE commodity;

DROP TABLE container;

DROP TABLE containertype;

CREATE TABLE containersize (
	containerSizeId BIGINT NOT NULL,
	size VARCHAR(20) DEFAULT '' NOT NULL,
	description VARCHAR(100),
	PRIMARY KEY (containerSizeId)
) ENGINE=InnoDB;

CREATE TABLE country (
	countryId BIGINT NOT NULL,
	code VARCHAR(5) DEFAULT '' NOT NULL,
	name VARCHAR(100) DEFAULT '' NOT NULL,
	PRIMARY KEY (countryId)
) ENGINE=InnoDB;

CREATE TABLE bl (
	blId BIGINT NOT NULL,
	voyageId BIGINT,
	blNo TEXT(65535),
	companyName TEXT(65535),
	status BIGINT,
	reportType BIGINT,
	placeOfOrigin BIGINT,
	placeOfReceipt BIGINT,
	portOfLoading BIGINT,
	portOfDischarge BIGINT,
	finalDischargePlace BIGINT,
	finalDestination BIGINT,
	description TEXT(65535),
	description2 TEXT(65535),
	shipper TEXT(65535),
	consignee TEXT(65535),
	notify TEXT(65535),
	notify2 TEXT(65535),
	issuePlace BIGINT DEFAULT 0,
	issueDate DATE,
	PRIMARY KEY (blId)
) ENGINE=InnoDB;

CREATE TABLE pack (
	packId BIGINT NOT NULL,
	type TEXT(65535),
	description TEXT(65535),
	PRIMARY KEY (packId)
) ENGINE=InnoDB;

CREATE TABLE vessel (
	vesselId BIGINT NOT NULL,
	vesselCode TEXT(65535),
	vesselName TEXT(65535),
	flag BIGINT,
	status BIGINT,
	company TEXT(65535),
	port BIGINT,
	PRIMARY KEY (vesselId)
) ENGINE=InnoDB;

CREATE TABLE voyage (
	voyageId BIGINT NOT NULL,
	voyage TEXT(65535),
	export BIGINT,
	vesselId BIGINT,
	officeId BIGINT,
	status BIGINT,
	firstLeavedPort BIGINT,
	lastLeavedPort BIGINT,
	arrivalDate DATE,
	departureDate DATE,
	nameOfCaptain TEXT(65535),
	PRIMARY KEY (voyageId)
) ENGINE=InnoDB;

CREATE TABLE cargo (
	cargoId BIGINT NOT NULL,
	containerId BIGINT DEFAULT '' NOT NULL,
	commodityId BIGINT DEFAULT '' NOT NULL,
	cargoType TEXT(65535),
	packId BIGINT,
	imco TEXT(65535),
	unno TEXT(65535),
	netWeight DOUBLE DEFAULT 0,
	packTotal DOUBLE,
	PRIMARY KEY (cargoId)
) ENGINE=InnoDB;

CREATE TABLE office (
	officeId BIGINT NOT NULL,
	office TEXT(65535),
	description TEXT(65535),
	code TEXT(65535),
	PRIMARY KEY (officeId)
) ENGINE=InnoDB;

CREATE TABLE location (
	locationId BIGINT NOT NULL,
	code TEXT(65535),
	location TEXT(65535),
	isport CHAR(1),
	PRIMARY KEY (locationId)
) ENGINE=InnoDB;

CREATE TABLE commodity (
	commodityId BIGINT NOT NULL,
	code TEXT(65535),
	name TEXT(65535),
	description TEXT(65535),
	PRIMARY KEY (commodityId)
) ENGINE=InnoDB;

CREATE TABLE container (
	containerId BIGINT NOT NULL,
	relCom BIGINT,
	containerNo TEXT(65535),
	blId BIGINT,
	containerType BIGINT,
	sealNo TEXT(65535),
	otherSealNo TEXT(65535),
	containerSize BIGINT,
	taraWeight DOUBLE,
	status BIGINT,
	PRIMARY KEY (containerId)
) ENGINE=InnoDB;

CREATE TABLE containertype (
	containerTypeId BIGINT NOT NULL,
	type VARCHAR(20) DEFAULT '' NOT NULL,
	description VARCHAR(100),
	PRIMARY KEY (containerTypeId)
) ENGINE=InnoDB;

CREATE VIEW vwboflading (blId, blNo, shipper, consignee, notify, notify2, description, issueDate, voyage, vesselCode, vesselName, placeOfReceipt, portOfDischarge, portOfLoading, finalDischargePlace, issuePlace) AS
/* ALGORITHM=UNDEFINED */ select `kumanifest`.`bl`.`blId` AS `blId`,`kumanifest`.`bl`.`blNo` AS `blNo`,`kumanifest`.`bl`.`shipper` AS `shipper`,`kumanifest`.`bl`.`consignee` AS `consignee`,`kumanifest`.`bl`.`notify` AS `notify`,`kumanifest`.`bl`.`notify2` AS `notify2`,`kumanifest`.`bl`.`description` AS `description`,`kumanifest`.`bl`.`issueDate` AS `issueDate`,`kumanifest`.`voyage`.`voyage` AS `voyage`,`kumanifest`.`vessel`.`vesselCode` AS `vesselCode`,`kumanifest`.`vessel`.`vesselName` AS `vesselName`,`kumanifest`.`location`.`location` AS `placeOfReceipt`,`location_1`.`location` AS `portOfDischarge`,`location_2`.`location` AS `portOfLoading`,`location_3`.`location` AS `finalDischargePlace`,`location_4`.`location` AS `issuePlace` from (((((((`kumanifest`.`bl` join `kumanifest`.`voyage` on((`kumanifest`.`bl`.`voyageId` = `kumanifest`.`voyage`.`voyageId`))) join `kumanifest`.`vessel` on((`kumanifest`.`voyage`.`vesselId` = `kumanifest`.`vessel`.`vesselId`))) left join `kumanifest`.`location` `location_2` on((`kumanifest`.`bl`.`portOfLoading` = `location_2`.`locationId`))) left join `kumanifest`.`location` `location_3` on((`kumanifest`.`bl`.`finalDischargePlace` = `location_3`.`locationId`))) left join `kumanifest`.`location` `location_1` on((`kumanifest`.`bl`.`portOfDischarge` = `location_1`.`locationId`))) left join `kumanifest`.`location` on((`kumanifest`.`bl`.`portOfLoading` = `kumanifest`.`location`.`locationId`))) left join `kumanifest`.`location` `location_4` on((`kumanifest`.`bl`.`issuePlace` = `location_4`.`locationId`))) 
WITH CASCADED CHECK OPTION;

CREATE VIEW vwcontainerlist (blId, containerNo, type, size, sealNo) AS
/* ALGORITHM=UNDEFINED */ select `kumanifest`.`container`.`blId` AS `blId`,`kumanifest`.`container`.`containerNo` AS `containerNo`,`kumanifest`.`containertype`.`type` AS `type`,`kumanifest`.`containersize`.`size` AS `size`,`kumanifest`.`container`.`sealNo` AS `sealNo` from ((`kumanifest`.`container` left join `kumanifest`.`containertype` on((`kumanifest`.`container`.`containerType` = `kumanifest`.`containertype`.`containerTypeId`))) left join `kumanifest`.`containersize` on((`kumanifest`.`container`.`containerSize` = `kumanifest`.`containersize`.`containerSizeId`))) 
WITH CASCADED CHECK OPTION;

CREATE UNIQUE INDEX containerSize ON container (containerSize ASC);

CREATE UNIQUE INDEX finalDischargePlace ON bl (finalDischargePlace ASC);

CREATE UNIQUE INDEX packId ON cargo (packId ASC);

CREATE UNIQUE INDEX containerType ON container (containerType ASC);

CREATE UNIQUE INDEX port ON vessel (port ASC);

CREATE UNIQUE INDEX commodityId ON cargo (commodityId ASC);

CREATE UNIQUE INDEX finalDestination ON bl (finalDestination ASC);

CREATE UNIQUE INDEX placeOfOrigin ON bl (placeOfOrigin ASC);

CREATE UNIQUE INDEX vesselId ON voyage (vesselId ASC);

CREATE UNIQUE INDEX portOfDischarge ON bl (portOfDischarge ASC);

CREATE UNIQUE INDEX portOfLoading ON bl (portOfLoading ASC);

CREATE UNIQUE INDEX officeId ON voyage (officeId ASC);

CREATE UNIQUE INDEX voyageId ON bl (voyageId ASC);

CREATE UNIQUE INDEX blId ON container (blId ASC);

CREATE UNIQUE INDEX firstLeavedPort ON voyage (firstLeavedPort ASC);

CREATE UNIQUE INDEX containerId ON cargo (containerId ASC);

CREATE UNIQUE INDEX lastLeavedPort ON voyage (lastLeavedPort ASC);

CREATE UNIQUE INDEX flag ON vessel (flag ASC);

CREATE UNIQUE INDEX placeOfReceipt ON bl (placeOfReceipt ASC);

ALTER TABLE office ADD PRIMARY KEY (officeId);

ALTER TABLE location ADD PRIMARY KEY (locationId);

ALTER TABLE country ADD PRIMARY KEY (countryId);

ALTER TABLE cargo ADD PRIMARY KEY (cargoId);

ALTER TABLE commodity ADD PRIMARY KEY (commodityId);

ALTER TABLE containertype ADD PRIMARY KEY (containerTypeId);

ALTER TABLE voyage ADD CONSTRAINT FK_voyage_location_1 FOREIGN KEY (firstLeavedPort, lastLeavedPort)
	REFERENCES location (locationId);

ALTER TABLE cargo ADD CONSTRAINT FK_cargo_4 FOREIGN KEY (commodityId)
	REFERENCES commodity (commodityId);

ALTER TABLE vessel ADD CONSTRAINT FK_vessel_country FOREIGN KEY (flag)
	REFERENCES country (countryId);

ALTER TABLE cargo ADD CONSTRAINT FK_cargo_2 FOREIGN KEY (packId)
	REFERENCES pack (packId);

ALTER TABLE container ADD CONSTRAINT FK_container_3 FOREIGN KEY (containerSize)
	REFERENCES containersize (containerSizeId);

ALTER TABLE container ADD CONSTRAINT FK_container_2 FOREIGN KEY (blId)
	REFERENCES bl (blId);

ALTER TABLE container ADD CONSTRAINT FK_container_containerType FOREIGN KEY (containerType)
	REFERENCES containertype (containerTypeId);

ALTER TABLE vessel ADD CONSTRAINT FK_vessel_location FOREIGN KEY (port)
	REFERENCES location (locationId);

ALTER TABLE bl ADD CONSTRAINT FK_bl_1 FOREIGN KEY (voyageId)
	REFERENCES voyage (voyageId);

ALTER TABLE voyage ADD CONSTRAINT FK_voyage_1 FOREIGN KEY (vesselId)
	REFERENCES vessel (vesselId);

ALTER TABLE voyage ADD CONSTRAINT FK_voyage_office FOREIGN KEY (officeId)
	REFERENCES office (officeId);

ALTER TABLE bl ADD CONSTRAINT FK_bl_location_1 FOREIGN KEY (placeOfOrigin, placeOfReceipt, portOfLoading, portOfDischarge, finalDischargePlace, finalDestination)
	REFERENCES location (locationId);

ALTER TABLE cargo ADD CONSTRAINT FK_cargo_3 FOREIGN KEY (containerId)
	REFERENCES container (containerId);

