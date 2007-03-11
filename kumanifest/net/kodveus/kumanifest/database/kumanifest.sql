CREATE TABLE containersize (
       containerSizeId BIGINT(20) NOT NULL AUTO_INCREMENT
     , size VARCHAR(20) NOT NULL
     , description VARCHAR(100)
     , primary key(containerSizeId)
)TYPE=InnoDB;

CREATE TABLE containertype (
       containerTypeId BIGINT(20) NOT NULL AUTO_INCREMENT
     , type VARCHAR(20) NOT NULL
     , description VARCHAR(100)
     , primary key(containerTypeId)
)TYPE=InnoDB;

CREATE TABLE country (
       countryId BIGINT(20) NOT NULL AUTO_INCREMENT
     , code VARCHAR(5) NOT NULL
     , name VARCHAR(100) NOT NULL
     , primary key(countryId)
)TYPE=InnoDB;

CREATE TABLE location (
       locationId BIGINT(20) NOT NULL AUTO_INCREMENT
     , code TEXT
     , location TEXT
     , isport CHAR(1)
     , primary key(locationId)
)TYPE=InnoDB;

CREATE TABLE office (
       officeId BIGINT(20) NOT NULL AUTO_INCREMENT
     , office TEXT
     , description TEXT
     , code TEXT
     , primary key(officeId)
)TYPE=InnoDB;

CREATE TABLE pack (
       packId BIGINT(20) NOT NULL AUTO_INCREMENT
     , type TEXT
     , description TEXT
     , primary key(packId)
)TYPE=InnoDB;

CREATE TABLE commodity (
       commodityId BIGINT(20) NOT NULL AUTO_INCREMENT
     , code TEXT
     , name TEXT
     , description TEXT
     , primary key(commodityId)
)TYPE=InnoDB;

CREATE TABLE vessel (
       vesselId BIGINT(20) NOT NULL AUTO_INCREMENT
     , vesselCode TEXT
     , vesselName TEXT
     , flag BIGINT(20)
     , status BIGINT(20)
     , company TEXT
     , port BIGINT(20)
     , primary key(vesselId)
)TYPE=InnoDB;

CREATE TABLE voyage (
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
     , primary key(voyageId)
)TYPE=InnoDB;

CREATE TABLE bl (
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
     , primary key(blId)
)TYPE=InnoDB;

CREATE TABLE container (
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
     , primary key(containerId)
)TYPE=InnoDB;

CREATE TABLE cargo (
       cargoId BIGINT(20) NOT NULL AUTO_INCREMENT
     , containerId BIGINT(20) NOT NULL
     , commodityId BIGINT(20) NOT NULL
     , cargoType TEXT
     , packId BIGINT(20)
     , imco TEXT
     , unno TEXT
     , netWeight DOUBLE DEFAULT 0
     , packTotal DOUBLE
     , primary key(cargoId)
)TYPE=InnoDB;

ALTER TABLE vessel
  ADD CONSTRAINT FK_vessel_country
      FOREIGN KEY (flag)
      REFERENCES country (countryId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE vessel
  ADD CONSTRAINT FK_vessel_location
      FOREIGN KEY (port)
      REFERENCES location (locationId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE voyage
  ADD CONSTRAINT FK_voyage_1
      FOREIGN KEY (vesselId)
      REFERENCES vessel (vesselId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE voyage
  ADD CONSTRAINT FK_voyage_location_1
      FOREIGN KEY (firstLeavedPort)
      REFERENCES location (locationId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE voyage
  ADD CONSTRAINT FK_voyage_location_2
      FOREIGN KEY (lastLeavedPort)
      REFERENCES location (locationId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE voyage
  ADD CONSTRAINT FK_voyage_office
      FOREIGN KEY (officeId)
      REFERENCES office (officeId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE bl
  ADD CONSTRAINT FK_bl_1
      FOREIGN KEY (voyageId)
      REFERENCES voyage (voyageId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE bl
  ADD CONSTRAINT FK_bl_location_1
      FOREIGN KEY (placeOfOrigin)
      REFERENCES location (locationId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE bl
  ADD CONSTRAINT FK_bl_location_2
      FOREIGN KEY (placeOfReceipt)
      REFERENCES location (locationId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE bl
  ADD CONSTRAINT FK_bl_location_3
      FOREIGN KEY (portOfLoading)
      REFERENCES location (locationId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE bl
  ADD CONSTRAINT FK_bl_location_4
      FOREIGN KEY (portOfDischarge)
      REFERENCES location (locationId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE bl
  ADD CONSTRAINT FK_bl_location_5
      FOREIGN KEY (finalDischargePlace)
      REFERENCES location (locationId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE bl
  ADD CONSTRAINT FK_bl_location_6
      FOREIGN KEY (finalDestination)
      REFERENCES location (locationId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE container
  ADD CONSTRAINT FK_container_2
      FOREIGN KEY (blId)
      REFERENCES bl (blId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE container
  ADD CONSTRAINT FK_container_3
      FOREIGN KEY (containerSize)
      REFERENCES containersize (containerSizeId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE container
  ADD CONSTRAINT FK_container_containerType
      FOREIGN KEY (containerType)
      REFERENCES containertype (containerTypeId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE cargo
  ADD CONSTRAINT FK_cargo_2
      FOREIGN KEY (packId)
      REFERENCES pack (packId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE cargo
  ADD CONSTRAINT FK_cargo_3
      FOREIGN KEY (containerId)
      REFERENCES container (containerId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE cargo
  ADD CONSTRAINT FK_cargo_4
      FOREIGN KEY (commodityId)
      REFERENCES commodity (commodityId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;