CREATE TABLE DEFAULT_SCHEMA.containersize (
       containerSizeId BIGINT(20) NOT NULL AUTO_INCREMENT
     , size VARCHAR(20) NOT NULL
     , description VARCHAR(100)
)TYPE=InnoDB;

CREATE TABLE DEFAULT_SCHEMA.containertype (
       containerTypeId BIGINT(20) NOT NULL AUTO_INCREMENT
     , type VARCHAR(20) NOT NULL
     , description VARCHAR(100)
)TYPE=InnoDB;

CREATE TABLE DEFAULT_SCHEMA.country (
       countryId BIGINT(20) NOT NULL AUTO_INCREMENT
     , code VARCHAR(5) NOT NULL
     , name VARCHAR(100) NOT NULL
)TYPE=InnoDB;

CREATE TABLE DEFAULT_SCHEMA.location (
       locationId BIGINT(20) NOT NULL AUTO_INCREMENT
     , code TEXT
     , location TEXT
     , isport CHAR(1)
)TYPE=InnoDB;

CREATE TABLE DEFAULT_SCHEMA.office (
       officeId BIGINT(20) NOT NULL AUTO_INCREMENT
     , office TEXT
     , description TEXT
     , code TEXT
)TYPE=InnoDB;

CREATE TABLE DEFAULT_SCHEMA.pack (
       packId BIGINT(20) NOT NULL AUTO_INCREMENT
     , type TEXT
     , description TEXT
)TYPE=InnoDB;

CREATE TABLE DEFAULT_SCHEMA.commodity (
       commodityId BIGINT(20) NOT NULL AUTO_INCREMENT
     , code TEXT
     , name TEXT
     , description TEXT
)TYPE=InnoDB;

CREATE TABLE DEFAULT_SCHEMA.vessel (
       vesselId BIGINT(20) NOT NULL AUTO_INCREMENT
     , vesselCode TEXT
     , vesselName TEXT
     , flag BIGINT(20)
     , status BIGINT(20)
     , company TEXT
     , port BIGINT(20)
)TYPE=InnoDB;

CREATE TABLE DEFAULT_SCHEMA.voyage (
       voyageId BIGINT(20) NOT NULL AUTO_INCREMENT
     , voyage TEXT
     , export BIGINT(20)
     , vesselId BIGINT(20)
     , officeId BIGINT(20)
     , status BIGINT(20)
     , firstLeavedPort BIGINT(20)
     , lastLeavedPort BIGINT(20)
     , arrivalDate DATE
     , departureDate DATE
     , nameOfCaptain TEXT
)TYPE=InnoDB;

CREATE TABLE DEFAULT_SCHEMA.bl (
       blId BIGINT(20) NOT NULL AUTO_INCREMENT
     , voyageId BIGINT(20)
     , blNo TEXT
     , companyName TEXT
     , status BIGINT(20)
     , reportType BIGINT(20)
     , placeOfOrigin BIGINT(20)
     , placeOfReceipt BIGINT(20)
     , portOfLoading BIGINT(20)
     , portOfDischarge BIGINT(20)
     , finalDischargePlace BIGINT(20)
     , finalDestination BIGINT(20)
     , description TEXT
     , description2 TEXT
     , shipper TEXT
     , consignee TEXT
     , notify TEXT
     , notify2 TEXT
     , issuePlace BIGINT(20) DEFAULT 0
     , issueDate DATE
)TYPE=InnoDB;

CREATE TABLE DEFAULT_SCHEMA.container (
       containerId BIGINT(20) NOT NULL AUTO_INCREMENT
     , containerNo TEXT
     , blId BIGINT(20)
     , containerType BIGINT(20)
     , sealNo TEXT
     , otherSealNo TEXT
     , containerSize BIGINT(20)
     , taraWeight DOUBLE
     , status BIGINT(20)
     , relcom BIGINT(20)
)TYPE=InnoDB;

CREATE TABLE DEFAULT_SCHEMA.cargo (
       cargoId BIGINT(20) NOT NULL AUTO_INCREMENT
     , containerId BIGINT(20) NOT NULL
     , commodityId BIGINT(20) NOT NULL
     , cargoType TEXT
     , packId BIGINT(20)
     , imco TEXT
     , unno TEXT
     , netWeight DOUBLE DEFAULT 0
     , packTotal DOUBLE
)TYPE=InnoDB;

ALTER TABLE DEFAULT_SCHEMA.containersize
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (containerSizeId);

ALTER TABLE DEFAULT_SCHEMA.containertype
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (containerTypeId);

ALTER TABLE DEFAULT_SCHEMA.country
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (countryId);

ALTER TABLE DEFAULT_SCHEMA.location
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (locationId);

ALTER TABLE DEFAULT_SCHEMA.office
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (officeId);

ALTER TABLE DEFAULT_SCHEMA.pack
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (packId);

ALTER TABLE DEFAULT_SCHEMA.commodity
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (commodityId);

ALTER TABLE DEFAULT_SCHEMA.vessel
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (vesselId);

ALTER TABLE DEFAULT_SCHEMA.voyage
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (voyageId);

ALTER TABLE DEFAULT_SCHEMA.bl
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (blId);

ALTER TABLE DEFAULT_SCHEMA.container
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (containerId);

ALTER TABLE DEFAULT_SCHEMA.cargo
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (cargoId);

ALTER TABLE DEFAULT_SCHEMA.vessel
  ADD CONSTRAINT FK_vessel_country
      FOREIGN KEY (flag)
      REFERENCES DEFAULT_SCHEMA.country (countryId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE DEFAULT_SCHEMA.vessel
  ADD CONSTRAINT FK_vessel_location
      FOREIGN KEY (port)
      REFERENCES DEFAULT_SCHEMA.location (locationId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE DEFAULT_SCHEMA.voyage
  ADD CONSTRAINT FK_voyage_1
      FOREIGN KEY (vesselId)
      REFERENCES DEFAULT_SCHEMA.vessel (vesselId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE DEFAULT_SCHEMA.voyage
  ADD CONSTRAINT FK_voyage_location_1
      FOREIGN KEY (firstLeavedPort)
      REFERENCES DEFAULT_SCHEMA.location (locationId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE DEFAULT_SCHEMA.voyage
  ADD CONSTRAINT FK_voyage_location_2
      FOREIGN KEY (lastLeavedPort)
      REFERENCES DEFAULT_SCHEMA.location (locationId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE DEFAULT_SCHEMA.voyage
  ADD CONSTRAINT FK_voyage_office
      FOREIGN KEY (officeId)
      REFERENCES DEFAULT_SCHEMA.office (officeId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE DEFAULT_SCHEMA.bl
  ADD CONSTRAINT FK_bl_1
      FOREIGN KEY (voyageId)
      REFERENCES DEFAULT_SCHEMA.voyage (voyageId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE DEFAULT_SCHEMA.bl
  ADD CONSTRAINT FK_bl_location_1
      FOREIGN KEY (placeOfOrigin)
      REFERENCES DEFAULT_SCHEMA.location (locationId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE DEFAULT_SCHEMA.bl
  ADD CONSTRAINT FK_bl_location_2
      FOREIGN KEY (placeOfReceipt)
      REFERENCES DEFAULT_SCHEMA.location (locationId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE DEFAULT_SCHEMA.bl
  ADD CONSTRAINT FK_bl_location_3
      FOREIGN KEY (portOfLoading)
      REFERENCES DEFAULT_SCHEMA.location (locationId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE DEFAULT_SCHEMA.bl
  ADD CONSTRAINT FK_bl_location_4
      FOREIGN KEY (portOfDischarge)
      REFERENCES DEFAULT_SCHEMA.location (locationId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE DEFAULT_SCHEMA.bl
  ADD CONSTRAINT FK_bl_location_5
      FOREIGN KEY (finalDischargePlace)
      REFERENCES DEFAULT_SCHEMA.location (locationId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE DEFAULT_SCHEMA.bl
  ADD CONSTRAINT FK_bl_location_6
      FOREIGN KEY (finalDestination)
      REFERENCES DEFAULT_SCHEMA.location (locationId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE DEFAULT_SCHEMA.container
  ADD CONSTRAINT FK_container_2
      FOREIGN KEY (blId)
      REFERENCES DEFAULT_SCHEMA.bl (blId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE DEFAULT_SCHEMA.container
  ADD CONSTRAINT FK_container_3
      FOREIGN KEY (containerSize)
      REFERENCES DEFAULT_SCHEMA.containersize (containerSizeId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE DEFAULT_SCHEMA.container
  ADD CONSTRAINT FK_container_containerType
      FOREIGN KEY (containerType)
      REFERENCES DEFAULT_SCHEMA.containertype (containerTypeId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE DEFAULT_SCHEMA.cargo
  ADD CONSTRAINT FK_cargo_2
      FOREIGN KEY (packId)
      REFERENCES DEFAULT_SCHEMA.pack (packId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE DEFAULT_SCHEMA.cargo
  ADD CONSTRAINT FK_cargo_3
      FOREIGN KEY (containerId)
      REFERENCES DEFAULT_SCHEMA.container (containerId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE DEFAULT_SCHEMA.cargo
  ADD CONSTRAINT FK_cargo_4
      FOREIGN KEY (commodityId)
      REFERENCES DEFAULT_SCHEMA.commodity (commodityId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

